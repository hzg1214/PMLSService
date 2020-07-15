package cn.com.eju.deal.api.gpContract.service;

import cn.com.eju.deal.api.contractChange.mapper.APIContractChangeMapper;
import cn.com.eju.deal.api.contractChange.model.OaProcessDto;
import cn.com.eju.deal.api.gpContract.dto.GPContractWXDto;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.contract.dao.ContractChangeMapper;
import cn.com.eju.deal.contract.dao.ContractChangeStoreMapper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.gpContract.dao.GpContractMapper;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeMapper;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeStoreMapper;
import cn.com.eju.deal.gpContractChange.model.GpContractChange;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeStore;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * service层 合同撤销
 */
@Service("apiGPContractService")
public class APIGPContractService {
    @Resource
    private GpContractMapper gpContractMapper;

    @Resource
    private GpContractChangeMapper gpContractChangeMapper;

    @Resource
    private GpContractChangeStoreMapper gpContractChangeStoreMapper;

    @Resource
    private APIContractChangeMapper apiContractChangeMapper;

    @Resource
    private ContractChangeMapper contractChangeMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private ContractChangeStoreMapper contractChangeStoreMapper;

    /**
     * 查询可终止的公盘合同列表
     */
    public ResultData<List<Map<String, Object>>> getTerminateList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        resultData.setFail();
        int pageIndex = 1;
        int pageSize = 10;
        int curPage = 1;
        if (reqMap.get("pageIndex") != null)
            pageIndex = Integer.parseInt(reqMap.get("pageIndex").toString());
        if (reqMap.get("pageSize") != null)
            pageSize = Integer.parseInt(reqMap.get("pageSize").toString());
        if (reqMap.get("curPage") != null)
            curPage = Integer.parseInt(reqMap.get("curPage").toString());
        reqMap.remove("pageIndex");
        reqMap.remove("pageSize");
        reqMap.remove("curPage");
        List<Map<String, Object>> terminateList = gpContractMapper.getTerminateList(reqMap);
        resultData.setTotalCount("0");
        if (terminateList != null && terminateList.size() > 0) {
            resultData.setTotalCount(terminateList.size() + "");
            int endRow = pageIndex * pageSize;
            terminateList = terminateList.subList((pageIndex - 1) * pageSize, endRow > terminateList.size() ? terminateList.size() : endRow);
            reqMap.put("pageIndex", pageIndex);
            reqMap.put("pageSize", pageSize);
            reqMap.put("curPage", curPage);
            resultData.setReturnData(terminateList);
            resultData.setSuccess();
        }
        return resultData;
    }

    /**
     * 合同终止提交填写页面
     *
     * @param reqMap
     * @throws Exception
     */
    public ResultData<GPContractWXDto> getGpContractZZDetail(Map<String, Object> reqMap) throws Exception {
        ResultData<GPContractWXDto> resultData = new ResultData<>();
        GPContractWXDto gpContractWXDto = gpContractMapper.getGpContractZZDetail(reqMap);
        //公司详细地址
        String cityNameCompany = SystemParam.getCityNameByCityNo(gpContractWXDto.getPartyBCityNo());
        String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(gpContractWXDto.getPartyBDistrictNo());
        String partyAddressDetail = cityNameCompany + districtNameCompany + gpContractWXDto.getPartyBAddress();
        gpContractWXDto.setPartyAddressDetail(partyAddressDetail);
        resultData.setReturnData(gpContractWXDto);
        return resultData;
    }

    public ResultData checkGPContractStoreForZZ(Map<String, Object> map) throws Exception {
        Integer num = gpContractChangeStoreMapper.checkGPContractStoreForZZ(map);
        ResultData resultData = new ResultData();
        resultData.setReturnData(num);
        return resultData;
    }

    /**
     * 本地更改数据库
     * 1.保存合同变更信息
     * 2.更新上传图片的refid
     * 3.重新建立新的合同与门店的关系
     * 逻辑同GpContractChangeService.java--saveGpContractChange line:178
     *
     * @param reqMap
     * @return
     */
    public ResultData stopGpContract(Map<String, Object> reqMap) throws Exception {
        ResultData resultData = new ResultData();
        GpContractChange gpContractChange = new GpContractChange();
        //公盘合同id、No、公司id、公司名称、协议书编号
        Integer gpContractId = Integer.valueOf((String) reqMap.get("gpContractId"));
        //根据公盘合同id查询详情
        GpContract gpContract = gpContractChangeStoreMapper.getGpContractById(gpContractId);
        String gpContractNo = gpContract.getGpContractNo();
        Integer companyId = gpContract.getCompanyId();
        String companyName = gpContract.getCompanyName();
        String gpAgreementNo = gpContract.getGpAgreementNo();
        String gpContractCityNo = gpContract.getCityNo();
        //终止类型
        Integer stopType = Integer.valueOf((String) reqMap.get("stopType"));
        //终止理由
        String stopReason = (String) reqMap.get("stopReason");
        //终止方案描述
        String stopDescribe = (String) reqMap.get("stopDescribe");
        //备注
        String remarks = (String) reqMap.get("remarks");
        //终止时间
        String stopDate = (String) reqMap.get("stopDate");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtil.isNotEmpty(stopDate)) {
            // 合作终止时间
            gpContractChange.setStopDate(format.parse(stopDate));
        }
        //预计退款金额
        String stopAmount = (String) reqMap.get("stopAmount");

        String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();

        Integer gpContractStopId = Integer.valueOf((String) reqMap.get("gpContractStopId"));
        Integer userCreate = Integer.valueOf(reqMap.get("userCreate").toString());
        gpContractChange.setGpContractId(gpContractId);
        gpContractChange.setGpContractNo(gpContractNo);
        gpContractChange.setCompanyId(companyId);
        gpContractChange.setCompanyName(companyName);
        gpContractChange.setGpAgreementNo(gpAgreementNo);
        gpContractChange.setGpContractCityNo(gpContractCityNo);
        gpContractChange.setChangeType(17001);
        gpContractChange.setStopType(stopType);
        gpContractChange.setStopReason(stopReason);
        gpContractChange.setStopDescribe(stopDescribe);
        gpContractChange.setRemarks(remarks);
        gpContractChange.setPtBackAmount(new BigDecimal(stopAmount));
        gpContractChange.setApproveState(0);
        gpContractChange.setSubmitOAStatus(21201);
        gpContractChange.setDelFlag("0");

        if (gpContractStopId == 0) {//新增
            if (reqMap.containsKey("gpContractStopNo")) {
                //终止编号
                String gpContractStopNo = (String) reqMap.get("gpContractStopNo");
                gpContractChange.setUserIdCreate(userCreate);
                gpContractChange.setDateCreate(new Date());
                gpContractChange.setGpContractStopNo(gpContractStopNo);
                //保存
                int count = gpContractChangeMapper.create(gpContractChange);
                if (count <= 0) {
                    resultData.setFail("保存公盘合同终止失败");
                    return resultData;
                }
                Integer gpContractChangeId = gpContractChange.getId();
                gpContractStopId = gpContractChangeId;

                if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
                    String[] arrays = fileRecordMainIds.split(",");
                    // 关联相关文件(RefId)
                    for (int i = 0; i < arrays.length; i++) {
                        if (StringUtil.isNotEmpty(arrays[i])) {
                            FileRecordMain fileRecordMain = new FileRecordMain();
                            fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                            fileRecordMain.setRefId(gpContractChangeId);
                            fileRecordMain.setIsDelete(false);
                            fileRecordMainMapper.update(fileRecordMain);
                        }
                    }
                }
            } else {
                resultData.setFail("保存公盘合同终止失败,无终止编号");
                return resultData;
            }
        } else if (gpContractStopId > 0) {//更新
            String delFileRecordMainIds = reqMap.get("delFileRecordMainIds").toString();
            //变更主键
            gpContractChange.setUserIdUpt(userCreate);
            gpContractChange.setDateUpt(new Date());
            gpContractChange.setId(gpContractStopId);
            int count = gpContractChangeMapper.updateStr(gpContractChange);
            if (count <= 0) {
                resultData.setFail("更新公盘合同终止失败");
                return resultData;
            }
            //对原上传文件删除
            if (delFileRecordMainIds != null && StringUtil.isNotEmpty(delFileRecordMainIds)) {
                String[] oldfileRecordMainIdsArray = delFileRecordMainIds.split(",");
                for (int i = 0; i < oldfileRecordMainIdsArray.length; i++) {
                    if (StringUtil.isNotEmpty(oldfileRecordMainIdsArray[i])) {
                        FileRecordMain fileRecordMain = new FileRecordMain();
                        fileRecordMain.setFileRecordMainId(Integer.valueOf(oldfileRecordMainIdsArray[i]));
                        fileRecordMain.setRefId(null);
                        fileRecordMain.setIsDelete(true);
                        fileRecordMainMapper.update(fileRecordMain);
                    }
                }
            }
            //删除门店关系
            gpContractChangeStoreMapper.updateById(gpContractStopId);
            // 关联相关文件(RefId)
            if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
                String[] arrays = fileRecordMainIds.split(",");
                for (int i = 0; i < arrays.length; i++) {
                    if (StringUtil.isNotEmpty(arrays[i])) {
                        FileRecordMain fileRecordMain = new FileRecordMain();
                        fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                        fileRecordMain.setRefId(gpContractStopId);
                        fileRecordMain.setIsDelete(false);
                        fileRecordMainMapper.update(fileRecordMain);
                    }
                }
            }
        }
        //重新建立新的公盘合同变更与门店的关系
        final JSONArray storeListJsonStr = JSON.parseArray(reqMap.get("storeListJsonStr").toString());
        if (!storeListJsonStr.isEmpty()) {
            for (Object storeObj : storeListJsonStr) {
                final Map storeObjMap = JsonUtil.parseToObject(storeObj.toString(), Map.class);
                GpContractChangeStore gpContractChangeStore = new GpContractChangeStore();
                gpContractChangeStore.setGpContractStopId(gpContractStopId);
                Integer storeId = Integer.valueOf(storeObjMap.get("storeId").toString());
                String storeNo = storeObjMap.get("storeNo").toString();
                String storeName = storeObjMap.get("storeName").toString();
                String storeAddress = storeObjMap.get("addressDetail").toString();
                String maintainer = storeObjMap.get("maintainer").toString();
                String maintainerName = storeObjMap.get("maintainerName").toString();
                String storeManager = storeObjMap.get("storeManager").toString();
                String storeManagerPhone = storeObjMap.get("storeManagerPhone").toString();

                gpContractChangeStore.setDelFlag("0");
                gpContractChangeStore.setCancelStatus(0);
                gpContractChangeStore.setStoreId(storeId);
                gpContractChangeStore.setStoreNo(storeNo);
                gpContractChangeStore.setStoreName(storeName);
                gpContractChangeStore.setStoreAddress(storeAddress);
                gpContractChangeStore.setStoreManager(storeManager);
                gpContractChangeStore.setStoreManagerPhone(storeManagerPhone);
                gpContractChangeStore.setMaintainer(maintainer);
                gpContractChangeStore.setMaintainerName(maintainerName);
                //入库操作
                gpContractChangeStore.setUserIdCreate(userCreate);
                gpContractChangeStore.setDateCreate(new Date());
                gpContractChangeStoreMapper.create(gpContractChangeStore);
            }
        }
        resultData.setSuccess();
        return resultData;
    }

    /**
     * 查询已终止的待提交公盘合同列表
     *
     * @param reqMap
     * @return
     */
    public ResultData<List<Map<String, Object>>> getTodoList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        resultData.setFail();
        int pageIndex = 1;
        int pageSize = 10;
        int curPage = 1;
        if (reqMap.get("pageIndex") != null)
            pageIndex = Integer.parseInt(reqMap.get("pageIndex").toString());
        if (reqMap.get("pageSize") != null)
            pageSize = Integer.parseInt(reqMap.get("pageSize").toString());
        if (reqMap.get("curPage") != null)
            curPage = Integer.parseInt(reqMap.get("curPage").toString());
        reqMap.remove("pageIndex");
        reqMap.remove("pageSize");
        reqMap.remove("curPage");
        List<Map<String, Object>> toDoList = gpContractChangeMapper.getTodoList(reqMap);
        resultData.setTotalCount("0");
        if (toDoList != null && toDoList.size() > 0) {
            resultData.setTotalCount(toDoList.size() + "");
            int endRow = pageIndex * pageSize;
            toDoList = toDoList.subList((pageIndex - 1) * pageSize, endRow > toDoList.size() ? toDoList.size() : endRow);
            reqMap.put("pageIndex", pageIndex);
            reqMap.put("pageSize", pageSize);
            reqMap.put("curPage", curPage);
            resultData.setReturnData(toDoList);
            resultData.setSuccess();
        }
        return resultData;
    }

    /**
     * 合同终止查看页面
     *
     * @return
     */
    public ResultData<GPContractWXDto> getTodoGpDetail(Map<String, Object> reqMap) throws Exception {
        ResultData<GPContractWXDto> resultData = new ResultData<>();
        GPContractWXDto gpContractWXDto = gpContractChangeMapper.getTodoGpDetail(reqMap);
        if (null != gpContractWXDto) {

            //公司详细地址
            String cityNameCompany = SystemParam.getCityNameByCityNo(gpContractWXDto.getPartyBCityNo());
            String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(gpContractWXDto.getPartyBDistrictNo());
            String partyAddressDetail = cityNameCompany + districtNameCompany + gpContractWXDto.getPartyBAddress();
            gpContractWXDto.setPartyAddressDetail(partyAddressDetail);

            String flowId = gpContractWXDto.getFlowId();
            //根据flowId查询其流程
            if (!"".equals(flowId) && null != flowId) {
                List<OaProcessDto> oaProcessDtoList = apiContractChangeMapper.getOaProcessByFlowId(flowId);
                if (null != oaProcessDtoList && oaProcessDtoList.size() > 0) {
                    gpContractWXDto.setOaProcesslist(oaProcessDtoList);
                }
            }
            //设置图片
            String fileRecordMainIds = "";
            if (reqMap.containsKey("gpContractStopId")) {
                String gpContractStopId = reqMap.get("gpContractStopId").toString();
                FileRecordMain getFilesParam = new FileRecordMain();
                getFilesParam.setRefId(Integer.valueOf(gpContractStopId));
                getFilesParam.setIsDelete(false);


                //终止合作协议书/单方解除函
                List<ContractFileDto> stopContractFileList = new ArrayList<ContractFileDto>();
                List<FileRecordMain> fileMdlList1 = fileRecordMainMapper.getStopGpContractFileListByStopId(getFilesParam);
                fileRecordMainIds = pushFileRecord(fileMdlList1, fileRecordMainIds, stopContractFileList);
                gpContractWXDto.setStopContractFileList(stopContractFileList);

                //保证金收据
                List<ContractFileDto> receiptFileList = new ArrayList<ContractFileDto>();
                List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getReceiptFileListByStopId(getFilesParam);
                fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, receiptFileList);
                gpContractWXDto.setReceiptFileList(receiptFileList);
                //附件
                List<ContractFileDto> othersFileList = new ArrayList<ContractFileDto>();
                List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getOthersFileListByStopId(getFilesParam);
                fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, othersFileList);
                gpContractWXDto.setOthersFileList(othersFileList);
                if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
                    fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
                }
            }
            gpContractWXDto.setFileRecordMainIds(fileRecordMainIds);
        }
        resultData.setReturnData(gpContractWXDto);
        return resultData;
    }

    private String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList)
            throws Exception {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (int i = 0; i < fileMdlList.size(); i++) {
                ContractFileDto contractFileDto = new ContractFileDto();
                FileRecordMain fileRecordMain = fileMdlList.get(i);
                //对文件数据进行组装处理
                handleFileRecordMain(contractFileDto, fileRecordMain);
                //重新组装返回list
                fileList.add(contractFileDto);
                fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";

            }
        }
        return fileRecordMainIds;
    }

    /**
     * 对文件数据进行组装处理
     *
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
    private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)//, String picId
            throws Exception {
        //获取图片路径
        contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
        contractFileDto.setFileName(fileRecordMain.getFileFullName());
        contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
        contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
        contractFileDto.setUrl50(fileRecordMain.getUrl50());
    }

    /**
     * 公盘终止查询列表
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<List<Map<String, Object>>> getGpZZList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        resultData.setFail();
        int pageIndex = 1;
        int pageSize = 10;
        int curPage = 1;
        if (reqMap.get("pageIndex") != null)
            pageIndex = Integer.parseInt(reqMap.get("pageIndex").toString());
        if (reqMap.get("pageSize") != null)
            pageSize = Integer.parseInt(reqMap.get("pageSize").toString());
        if (reqMap.get("curPage") != null)
            curPage = Integer.parseInt(reqMap.get("curPage").toString());
        reqMap.remove("pageIndex");
        reqMap.remove("pageSize");
        reqMap.remove("curPage");
        List<Map<String, Object>> gpZZList = gpContractChangeMapper.getGpZZList(reqMap);
        resultData.setTotalCount("0");

        if (gpZZList != null && gpZZList.size() > 0) {
            resultData.setTotalCount(gpZZList.size() + "");
            int endRow = pageIndex * pageSize;
            gpZZList = gpZZList.subList((pageIndex - 1) * pageSize, endRow > gpZZList.size() ? gpZZList.size() : endRow);
            reqMap.put("pageIndex", pageIndex);
            reqMap.put("pageSize", pageSize);
            reqMap.put("curPage", curPage);
            resultData.setReturnData(gpZZList);
            resultData.setSuccess();
        }
        return resultData;
    }

}
