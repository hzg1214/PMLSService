package cn.com.eju.deal.store.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.store.dao.AuthCheckMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreAuthCheck;
import cn.com.eju.deal.store.model.StoreAuthCheckDto;
import cn.com.eju.deal.store.model.StoreAuthCheckLog;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;

@Service
public class AuthCheckService extends BaseService<StoreAuthCheck> {

    @Resource
    private AuthCheckMapper authCheckMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource(name = "contractService")
    private ContractService contractService;
    @Resource
    private SeqNoAPIImpl seqNoAPI;

    public ResultData<?> create(StoreAuthCheck storeAuthCheck) {
        ResultData<?> resultData = new ResultData<>();

        //微信端重复提交判断
        Map<String, Object> store = authCheckMapper.getStore(storeAuthCheck);
        if (store.get("authCheckStatus") != null && "23301".equals(store.get("authCheckStatus").toString())) {
            resultData.setFail("请勿重复提交");
            return resultData;
        }

        Map<String, Object> contractInfo = authCheckMapper.getLastContractId(storeAuthCheck.getStoreId());

        storeAuthCheck.setContractId(Integer.valueOf(contractInfo.get("ContractId").toString()));
        int count = authCheckMapper.create(storeAuthCheck);
        if (count == 1) {

            authCheckMapper.updateStore(storeAuthCheck);

            StoreAuthCheckLog log = new StoreAuthCheckLog();
            log.setAuthCheckId(storeAuthCheck.getId());
            log.setOperDesc("提交");
            log.setUserCreate(storeAuthCheck.getUserCreate());
            authCheckMapper.createLog(log);

            String fileIds = storeAuthCheck.getFileIds();
            if (StringUtil.isNotEmpty(fileIds)) {
                String[] fileIdArr = fileIds.split(",");
                List ids = new ArrayList();

                for (String fileId : fileIdArr) {
                    ids.add(fileId);
                }
                Map<String, Object> map = new HashMap<>();
                map.put("contractId", storeAuthCheck.getId());
                map.put("ids", ids);
                fileRecordMainMapper.updateByCondition(map);
            }

        } else {
            resultData.setFail("创建授牌验收失败");
        }

        return resultData;
    }

    public List<StoreAuthCheck> queryList(Map<String, Object> queryParam) {
        return authCheckMapper.queryList(queryParam);
    }

    public ResultData<?> getAuthCheckById(StoreAuthCheck storeAuthCheck) {

        ResultData<StoreAuthCheck> resultData = new ResultData<>();
        StoreAuthCheck result = authCheckMapper.getAuthCheckById(storeAuthCheck);

        List<StoreAuthCheckLog> logList = authCheckMapper.getAuthCheckLog(storeAuthCheck);
        result.setLogList(logList);

        List<WXPictureDto> imgList = authCheckMapper.getAuthCheckImg(storeAuthCheck);
        result.setFileRecordMainDtoList(imgList);

        resultData.setReturnData(result);

        return resultData;
    }

    /**
     * 查询授牌验收申请列表
     *
     * @param reqMap
     * @return
     */
    public ResultData<List<StoreAuthCheckDto>> getStoreAuthCheckList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<StoreAuthCheckDto>> resultData = new ResultData<List<StoreAuthCheckDto>>();
        resultData.setFail();
        Integer userId = (Integer) reqMap.get("userId");
        List<ExchangeCenter> centerList = userMapper.getCenterListByUserId(userId);
        String centerIdStr = (String) reqMap.get("centerIdStr");
        if (StringUtil.isEmpty(centerIdStr)) {
            if (null != centerList && !centerList.isEmpty()) {
                for (ExchangeCenter exc : centerList) {
                    if (StringUtil.isEmpty(centerIdStr)) {
                        centerIdStr = exc.getExchangeCenterId().toString();
                    } else {
                        centerIdStr = centerIdStr + ',' + exc.getExchangeCenterId().toString();
                    }
                }
            }
            if (!centerIdStr.isEmpty()) {
                reqMap.put("centerIdStr", centerIdStr);
            }
        }
        List<StoreAuthCheckDto> storeAuthCheckDtoList = authCheckMapper.getStoreAuthCheckList(reqMap);
        if (storeAuthCheckDtoList != null && storeAuthCheckDtoList.size() > 0) {
            resultData.setReturnData(storeAuthCheckDtoList);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setSuccess();
        }
        return resultData;
    }

    /**
     * 根据id授牌验收详情
     *
     * @throws Exception
     */
    public ResultData<StoreAuthCheckDto> getStoreAuthCheckInfoById(Integer id) throws Exception {
        //构建返回
        ResultData resultData = new ResultData();
        resultData.setFail();
        //根据框架合同id获取其详细信息
        StoreAuthCheckDto storeAuthCheckDto = authCheckMapper.getStoreAuthCheckInfoById(id);
        if (storeAuthCheckDto != null) {
            String fileRecordMainIds = "";
            FileRecordMain attachmentFile = new FileRecordMain();
            attachmentFile.setRefId(id);
            attachmentFile.setIsDelete(false);
            //挂牌照片
            List<ContractFileDto> plateFileList = new ArrayList<ContractFileDto>();
            List<FileRecordMain> fileMdlList = fileRecordMainMapper.getPlateFileListById(attachmentFile);
            fileRecordMainIds = contractService.pushFileRecord(fileMdlList, fileRecordMainIds, plateFileList);
            if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
                fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
            }
            storeAuthCheckDto.setPlateFileList(plateFileList);
            storeAuthCheckDto.setFileRecordMainIds(fileRecordMainIds);
            //返回
            resultData.setReturnData(storeAuthCheckDto);
            resultData.setSuccess();
        }
        return resultData;
    }

    /**
     * 审核授牌验收申请
     */
    public ResultData<?> auditPass(Map<String, Object> reqMap) throws Exception {
        ResultData<String> resultData = new ResultData<>();
        Integer storeId = Integer.valueOf((String) reqMap.get("storeId"));

        StoreAuthCheck storeAuthCheck = new StoreAuthCheck();
        StoreAuthCheckLog log = new StoreAuthCheckLog();
        //授牌验收申请id
        Integer id = Integer.valueOf((String) reqMap.get("id"));
        Integer userIdCreate = Integer.valueOf(reqMap.get("userIdCreate").toString());
        //审核通过或不通过
        String passType = reqMap.get("passFlag").toString();
        String auditDesc = "";
        if ("noPass".equals(passType)) {//审核不通过
            storeAuthCheck.setCheckStatus(23303);
            //审核不通过原因
            auditDesc = (String) reqMap.get("auditDesc");
            if (!"".equals(auditDesc)) {
                storeAuthCheck.setAuditDesc(auditDesc);
                log.setOperDesc("审核不通过");
            }
        }
        if ("pass".equals(passType)) {//审核通过
            //modify by haidan 2019/5/15 判断授牌合同是否过期和是否撤销
            Map<String, Object> contractInfo = authCheckMapper.getLastContractId(storeId);
            Map<String, Object> checkMap=new HashMap<>();
            checkMap.put("storeId",storeId);
            checkMap.put("contractId",Integer.valueOf(contractInfo.get("ContractId").toString()));
            Map<String, Object> spcxMap = authCheckMapper.getSPCXCount(checkMap);

            Integer spCount = Integer.valueOf(spcxMap.get("spCount").toString());
            Integer cxCount = Integer.valueOf(spcxMap.get("cxCount").toString());
            if (null != spCount && spCount > 0) {
                resultData.setFail("该门店授牌合同已过期，不允许做授牌验收操作，请驳回！");
                return resultData;
            }
            if (null != cxCount && cxCount > 0) {
                resultData.setFail("该门店授牌合同撤销中/已撤销，不允许做授牌验收操作，请驳回！");
                return resultData;
            }
            storeAuthCheck.setCheckStatus(23302);
            log.setOperDesc("审核通过");
        }
        storeAuthCheck.setAuthTime(new Date());
        storeAuthCheck.setDateUpdate(new Date());
        storeAuthCheck.setUserUpdate(userIdCreate);
        storeAuthCheck.setAuditUserId(userIdCreate);
        storeAuthCheck.setId(id);
        int count = authCheckMapper.updateStoreAuthCheck(storeAuthCheck);
        if (count > 0) {
            resultData.setSuccess();
            //插入日志

            log.setAuthCheckId(id);
            log.setUserCreate(userIdCreate);
            authCheckMapper.createLog(log);
            //更新门店中的状态
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("storeId", storeId);
            hashMap.put("checkStatus", storeAuthCheck.getCheckStatus());
            hashMap.put("contractType", 10307);
            hashMap.put("userUpdate", userIdCreate);
            hashMap.put("dateUpdate", new Date());
            int count2 = authCheckMapper.updateStoreByParam(hashMap);
            resultData.setSuccess();
        }
        return resultData;
    }

    public ResultData<?> checkSPCXCount(Integer storeId) {
        ResultData<String> resultData = new ResultData<>();
        resultData.setSuccess();

        Map<String, Object> contractInfo = authCheckMapper.getLastContractId(storeId);
        Map<String, Object> reqMap=new HashMap<>();
        reqMap.put("storeId",storeId);
        reqMap.put("contractId",Integer.valueOf(contractInfo.get("ContractId").toString()));

        Map<String, Object> spcxMap = authCheckMapper.getSPCXCount(reqMap);
        Integer spCount = Integer.valueOf(spcxMap.get("spCount").toString());
        Integer cxCount = Integer.valueOf(spcxMap.get("cxCount").toString());
        if (null != spCount && spCount > 0) {
            resultData.setFail("该门店授牌合同已过期，不允许做授牌验收操作！");
            return resultData;
        }
        if (null != cxCount && cxCount > 0) {
            resultData.setFail("该门店授牌合同撤销中/已撤销，不允许做授牌验收操作！");
            return resultData;
        }
        return resultData;
    }
    
    
    public ResultData<?> spcontractAutocreate(Integer contractId) throws Exception {
        ResultData<?> resultData = new ResultData<>();

        //微信端重复提交判断
        List<Store> storeList = storeMapper.getBycontractId(contractId);
        if(storeList!=null&& storeList.size()>0){
        	for (Store  s : storeList){
        		if (s.getStoreType()!=null&&("23401".equals(s.getStoreType().toString())||"23403".equals(s.getStoreType().toString())))
        		{
        			ResultData<String> data2 = seqNoAPI.getSeqNoByTypeCode("TYPE_AUTHCHECK");
        	         String checkNo = "";
        	         if (data2 != null && data2.getReturnCode().equals("200")) {
        	        	 checkNo = data2.getReturnData();
        	         } else {
        	             new NullPointerException("根据seq_type: TYPE_AUTHCHECK 获取授牌申请单编号为空");
        	         }
        			
        			  StoreAuthCheck storeAuthCheck = new StoreAuthCheck();
        		      StoreAuthCheckLog log = new StoreAuthCheckLog();
        		      storeAuthCheck.setAuthCheckNo(checkNo);
        		      storeAuthCheck.setStoreId(s.getStoreId());
        		      storeAuthCheck.setContractId(contractId);
        		      storeAuthCheck.setCheckDate(new Date());
        		      storeAuthCheck.setCheckStatus(23302);
        		      storeAuthCheck.setAuthTime(new Date());
        		      storeAuthCheck.setRemark("授牌合同中 门店类型为门店和渠道的门店，系统自动生成授牌验收记录");
        		      storeAuthCheck.setAuditUserId(2221);
        		      storeAuthCheck.setDateCreate(new Date());
        		      storeAuthCheck.setUserCreate(2221);
        		      storeAuthCheck.setDateUpdate(new Date());
        		      storeAuthCheck.setUserUpdate(2221);
        		      storeAuthCheck.setAuditDesc("系统自动审核通过.");
        		      storeAuthCheck.setDelFlag(0);
        		      
        		      int count = authCheckMapper.Autocreate(storeAuthCheck);
        		      log.setAuthCheckId(storeAuthCheck.getId());
        	          log.setOperDesc("自动生成验收通过记录");
        	          log.setUserCreate(2221);
        	          authCheckMapper.createLog(log);
        		      
        		}        		
        		
        	}
        	resultData.setSuccess();
        }  else {
            resultData.setFail("创建授牌验收失败");
        } 

        return resultData;
    }
}
