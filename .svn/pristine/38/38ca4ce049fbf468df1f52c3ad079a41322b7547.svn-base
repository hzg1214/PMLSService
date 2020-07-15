package cn.com.eju.deal.store.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreReceiveDto;
import cn.com.eju.deal.dto.store.StoreReceiveInfoDto;
import cn.com.eju.deal.open.controller.UploadOAUtil;
import cn.com.eju.deal.open.service.APIOaService;
import cn.com.eju.deal.store.dao.StoreDepositMapper;
import cn.com.eju.deal.store.dao.StoreReceiveDtlMapper;
import cn.com.eju.deal.store.dao.StoreReceiveMapper;
import cn.com.eju.deal.store.dao.StoreReceiveReverseMapper;
import cn.com.eju.deal.store.model.*;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service("storeReceiveService")
public class StoreReceiveService {
	
	/**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
	 @Resource
	 private StoreReceiveMapper storeReceiveMapper;
	 
	 @Resource
	 private StoreReceiveDtlMapper storeReceiveDtlMapper;
	 
	 @Resource
	 private FileRecordMainMapper fileRecordMainMapper;

    @Resource
	 private StoreReceiveReverseMapper storeReceiveReverseMapper;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Resource
    private BasCenterSettingService basCenterSettingService;

    @Resource
    private BasOaSupplierService basOaSupplierService;

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private FileRecordMainService fileService;

    @Resource(name="storeReceiveService")
    private StoreReceiveService storeReceiveService;

    @Resource(name = "apiOaService")
    private APIOaService apiOaService;

    @Resource(name = "storeDepositMapper")
    private StoreDepositMapper storeDepositMapper;
	 
	 public ResultData getStoreReceiveList(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        
        List<StoreReceiveDto> storeReceiveList =  storeReceiveMapper.getStoreReceiveList(reqMap);
        if(storeReceiveList !=null && storeReceiveList.size()>0){
        	
        	 resultData.setReturnData(storeReceiveList);
	         resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
	         resultData.setSuccess();
        }
        return resultData;
    }
	 /**
	  * 保证金信息详情表
	  * @param id
	  * @return
	  * @throws Exception
	  */
    public ResultData<StoreReceiveInfoDto> getBriefById(int id) throws Exception {
        //构建返回
    	ResultData resultData = new ResultData();
        resultData.setFail();
        StoreReceiveInfoDto storeReceiveInfoDto = new StoreReceiveInfoDto();
        //获取保证金收款信息
        StoreReceiveDto storeReceiveDto =storeReceiveMapper.getBriefById(id);
        List<StoreDto> storeDtoList = storeReceiveMapper.getStoreInfoById(id);
        if(storeReceiveDto != null && storeDtoList != null){
        	storeReceiveInfoDto.setStoreReceive(storeReceiveDto);
        	storeReceiveInfoDto.setStoreDetails(storeDtoList);
        }
    	// 获取文件信息
        String fileRecordMainIds = "";
        // 附件
        List<ContractFileDto> attachmentFileList = new ArrayList<ContractFileDto>();
		FileRecordMain attachmentFile = new FileRecordMain();
		attachmentFile.setRefId(id);
		attachmentFile.setIsDelete(false);
		List<FileRecordMain> fileMdlList = fileRecordMainMapper.getAttachmentFileByReceiveId(attachmentFile);
		fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, attachmentFileList);
		if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
			fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
		}
		storeReceiveInfoDto.setFileRecordMainAttachment(attachmentFileList);
		storeReceiveInfoDto.setFileRecordMainIds(fileRecordMainIds);
        if(storeReceiveInfoDto != null){
        	resultData.setReturnData(storeReceiveInfoDto);
        	resultData.setSuccess();
        }
		return resultData;
    } 
    /**
     * 图片信息
     *
     * @param
     * @return
     */
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
    * @param contractFileDto
    * @param fileRecordMain
    * @throws Exception
    */
    private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
        throws Exception {
        //获取图片路径
         contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
         contractFileDto.setFileName(fileRecordMain.getFileFullName());
         contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
         contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
         contractFileDto.setUrl50(fileRecordMain.getUrl50());
    }
    
    /** 
     * @Title: updateStr 
     * @Description: 更新门店信息（供接口调用）
     * @param param
     * @return ResultData<StoreDto>    
     */
     public ResultData updateStr(String param)throws Exception {
    	 Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    	 Integer resultRow	=	null;
    	 ResultData<Object> resultData = new ResultData<Object>();
 		resultData.setFail();
 		try {
 			resultRow = storeReceiveMapper.updateStr(queryParam);
 		}catch(Exception e){
 			logger.error("[PMLSService invoke storeReceiveMapper.updateStr error...]");
 			return resultData;
 		}
 		if(resultRow == null || resultRow.intValue() == 0) {
 			return resultData;
 		}
 		resultData.setSuccess();
 		resultData.setReturnData(resultRow);
 		return resultData;
     }
     
    /**
     * 查询需要发oa的收款单
     * @return
     */
    public List<StoreReceive> getReceiveListForOa() {
        return storeReceiveMapper.getReceiveListForOa();
    }
    
    /**
     * 批量更新
     * @param param
     */
    public void batchUpdate(Map<String, Object> param) {
        storeReceiveMapper.bathUpdate(param);
    }
    
    /**
     * 根据主键动态更新
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(StoreReceive record) {
        return storeReceiveMapper.updateByPrimaryKeySelective(record);
    }
    
    /**
     * 通过主表ID获取子表数据
     * @param id
     * @return
     */
    public List<StoreReceiveDtl> getDtlList(Integer id) {
        Map map = new HashMap();
        map.put("id", id);
        return storeReceiveDtlMapper.getListByParentId(map);
    }
    
    /**
     * 通过主表ID更新明细表信息
     * @param updateDtl
     * @return
     */
    public int batchUpdateDtl(StoreReceiveDtl updateDtl) {
        return storeReceiveDtlMapper.batchUpdateDtl(updateDtl);
    }

    /**
     * 查询需要发oa的收款调整单
     * @return
     */
    public List<StoreReceive> getReceiveTDListForOa() {
        return storeReceiveMapper.getReceiveTDListForOa();
    }

    /**
     * 保证金红冲
     * @param map
     * @return
     * @throws Exception
     */
    public ResultData<?> reverse(Map<String, Object> map) throws Exception{
        ResultData<?> backResult = new ResultData<>();

        final Integer userId = Integer.valueOf(map.get("userId").toString());
        final Integer receiveId = Integer.valueOf(map.get("receiveId").toString());

        StoreReceiveDto receiveDto = storeReceiveMapper.getBriefById(receiveId);
        if(receiveDto.getReverseId() != null && receiveDto.getReverseApproveStatus().intValue() != 21604){
            backResult.setFail("该保证金正在红冲，请勿重复提交");
            return backResult;
        }

        //生成红冲表记录
        StoreReceiveReverse reverseDto = new StoreReceiveReverse();
        reverseDto.setReceiveId(receiveId);

        ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_RECEIVE_OANO");
        if(data != null && data.getReturnCode().equals("200")) {
            reverseDto.setReverseOaNo(data.getReturnData());
        }else {
            backResult.setFail("生成红冲单失败");
            return backResult;
        }
        reverseDto.setApproveStatus(21601);
        reverseDto.setSubmitOaStatus(21201);
        reverseDto.setUserIdCreate(userId);

        final int i = storeReceiveReverseMapper.create(reverseDto);
        if(i == 0){
            backResult.setFail("生成红冲单失败");
            return backResult;
        }

        backResult = this.reverseToOa(receiveId,reverseDto,receiveDto);

        return backResult;
    }

    private ResultData<?> reverseToOa(Integer receiveId,StoreReceiveReverse reverseDto,StoreReceiveDto receiveDto){
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("红冲单发 begin，this time is" + new Date());

        //更新为处理中
        StoreReceiveReverse reverse = new StoreReceiveReverse();
        reverse.setId(reverseDto.getId());
        reverse.setSubmitOaStatus(21202);
        storeReceiveReverseMapper.update(reverse);

        //拼装xml并提交OA
        try {
            String contractNo = receiveDto.getContractNo();

            BasCenterSetting centerSetting = basCenterSettingService.selectByContractNo(contractNo);
            if(centerSetting == null) {
                throw new NullPointerException("根据合同编号:"+contractNo+"(合同里的centerId)查询BasCenterSetting为空");
            }

            String defaultPayoutId = SystemParam.getWebConfigValue("default_payout_id");
            String defaultPayoutName = SystemParam.getWebConfigValue("default_payout_name");

            BasOaSupplier oaSupplier = basOaSupplierService.selectByContractNo(contractNo);
            if(oaSupplier == null) {
                //查询供应商默认值
                String defaultSupplierCode = SystemParam.getWebConfigValue("default_supplier_code");
                String defaultSupplierName = SystemParam.getWebConfigValue("default_supplier_name");

                if(receiveDto.getFeeType().intValue() == 17905){
                    defaultSupplierCode = "K1003724";
                    defaultSupplierName = "二手房系统收款";
                }

                oaSupplier = new BasOaSupplier();
                oaSupplier.setOaSupplierCode(defaultSupplierCode);
                oaSupplier.setOaSupplierName(defaultSupplierName);
            }

            Map<String, Object> citySetting = citySettingMapper.getCitySettingByContractAcCityNo(contractNo);
            if(citySetting == null||StringUtil.isEmpty(citySetting.get("storeReceiveTpl").toString())) {
                throw new NullPointerException("根据合同编号:"+contractNo+"(合同里的acCityNo)查询Bas_CitySetting为空");
            }

            Map<String,Object> sendOaData = new HashMap<>();

            // 门店收款OA模板
            String oaTemplateCode = citySetting.get("storeReceiveTpl").toString();
            // 模板编号
            sendOaData.put("templateCode", oaTemplateCode);
            // 发起者的登录名（登录协同的登录名）
            sendOaData.put("senderLoginName", centerSetting.getSendUserCode());
            // 协同的标题
            sendOaData.put("subject", "保证金收款单");

            //支付方式
            receiveDto.setReceiveTypeNm("银行转账");

            //门店信息xml
            List<StoreReceiveDtl> dtlList = storeReceiveService.getDtlList(receiveId);
            StringBuilder sb = new StringBuilder();
            if (dtlList != null && dtlList.size() > 0)
            {
                for (StoreReceiveDtl dtl : dtlList)
                {
                    sb.append("<row>" + "<column name=\"收入类别\"><value>" + defaultPayoutName + "</value></column>"
                            + "<column name=\"收入类别编码\"><value>" + defaultPayoutId + "</value></column>"
                            + "<column name=\"供应商\"><value>" + oaSupplier.getOaSupplierName() + "</value></column>"
                            + "<column name=\"供应商编码\"><value>" + oaSupplier.getOaSupplierCode() + "</value></column>"
                            + "<column name=\"收款金额\"><value>" + BigDecimal.ZERO.subtract(dtl.getAmount()).setScale(2) + "</value></column>"
                            + "<column name=\"门店编号\"><value>" + dtl.getStoreNo() + "</value></column>"
                            + "<column name=\"门店名称\"><value>" + dtl.getStoreName() + "</value></column>"
                            + "</row>");
                }
            }

            //附件集合汇总
            List<Long> attachList = new ArrayList<>();
            // 银行凭证
            this.getOaAttachment(attachList,receiveId,1027,centerSetting.getSendUserCode());

            // 组装向OA发送的参数信息
            String dataXml =
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                            + "<summary id=\"-8896389543745008481\" name=\"formmain_3960\"/>" + "<definitions/>" + "<values>"
                            + "<column name=\"编码\"><value>"
                            + reverseDto.getReverseOaNo()
                            + "</value></column>"
                            + "<column name=\"核算主体\"><value>"
                            + centerSetting.getAccountProject()
                            + "</value></column>"
                            + "<column name=\"核算主体编码\"><value>"
                            + centerSetting.getAccountProjectCode()
                            + "</value></column>"
                            + "<column name=\"考核主体\"><value>"
                            + centerSetting.getCheckBodyName()
                            + "</value></column>"
                            + "<column name=\"考核主体编码\"><value>"
                            + centerSetting.getCheckBodyId()
                            + "</value></column>"
                            + "<column name=\"成本中心\"><value>"
                            + centerSetting.getCostName()
                            + "</value></column>"
                            + "<column name=\"成本中心编码\"><value>"
                            + centerSetting.getCostCode()
                            + "</value></column>"
                            + "<column name=\"收款银行\"><value>"
                            + receiveDto.getOaBankName()
                            + "</value></column>"
                            + "<column name=\"收款银行编码\"><value>"
                            + receiveDto.getOaBankId()
                            + "</value></column>"
                            + "<column name=\"入账日期\"><value>"
                            + DateUtil.fmtDate(new Date(),"yyyy-MM-dd")
                            + "</value></column>"
                            + "<column name=\"合同编号\"><value>"
                            + contractNo
                            + "</value></column>"
                            + "<column name=\"收款方式\"><value>"
                            + receiveDto.getReceiveTypeNm()
                            + "</value></column>"
                            + "<column name=\"收款人\"><value>"
                            + receiveDto.getUserName()
                            + "</value></column>"
                            + "<column name=\"收款人工号\"><value>"
                            + receiveDto.getUserCode()
                            + "</value></column>"
                            + "<column name=\"收款金额合计\"><value>"
                            + BigDecimal.ZERO.subtract(receiveDto.getTotalAmount()).setScale(2)
                            + "</value></column>"
                            + "<column name=\"备注\"><value>"
                            + "红冲"+receiveDto.getOaNo()
                            + "</value></column>"
                            + "</values>"
                            + "<subForms>"
                            + "<subForm>"
                            + "<definitions>"
                            + "<column id=\"field0017\" type=\"0\" name=\"收入类别\" length=\"255\"/>"
                            + "<column id=\"field0023\" type=\"0\" name=\"收入类别编码\" length=\"255\"/>"
                            + "<column id=\"field0018\" type=\"0\" name=\"供应商\" length=\"255\"/>"
                            + "<column id=\"field0019\" type=\"0\" name=\"供应商编码\" length=\"255\"/>"
                            + "<column id=\"field0020\" type=\"0\" name=\"收款金额\" length=\"255\"/>"
                            + "<column id=\"field0021\" type=\"0\" name=\"门店编号\" length=\"255\"/>"
                            + "<column id=\"field0022\" type=\"0\" name=\"门店名称\" length=\"255\"/>"
                            + "</definitions>"
                            + "<values>"
                            + sb.toString() + "</values>" + "</subForm>" + "</subForms>"+ "</formExport>";

            // 附件，Long型数组，值为附件的Id。
            sendOaData.put("attachments",attachList);
            sendOaData.put("data", dataXml);
            // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
            sendOaData.put("state", "0");

            // 取得REST动态客户机实例
            CTPRestClient client = getClient();
            String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
            //为登录验证后获取的身份令牌
            sendOaData.put("token", token);

            StoreReceiveReverse updateRR = new StoreReceiveReverse();
            updateRR.setId(reverseDto.getId());

            //发起申请 返回FlowId
            Long flowId = null;
            try{
                //提交OA
                flowId = apiOaService.toOaAuth(sendOaData, citySetting.get("storeReceiveTpl").toString());
            }
            catch (Exception e){
                logger.error("storeReceive", "StoreReceiveService", "reverseToOa", "", 0, "", "提交OA收款红冲单失败,合同编号:"+contractNo, e);
            }

            //成功或失败更新
            if(flowId != null) {
                updateRR.setSubmitOaStatus(21203);
                updateRR.setApproveStatus(21602);
                updateRR.setFlowId(flowId.toString());

                //成功时保证金退款锁定金额修改
                for (StoreReceiveDtl dtl : dtlList){
                    StoreDeposit storeDeposit = new StoreDeposit();
                    storeDeposit.setStoreNo(dtl.getStoreNo());
                    storeDeposit.setPaymentLockAmt(dtl.getAmount());
                    storeDepositMapper.updateByStoreNo(storeDeposit);
                }
            }else {
                updateRR.setSubmitOaStatus(21204);
            }
            storeReceiveReverseMapper.update(updateRR);

        } catch (Exception e) {
            logger.error("storeReceive", "StoreReceiveService", "reverseToOa", "", -1, "", "提交OA收款红冲单失败,收款单id:"+receiveId, e);

            StoreReceiveReverse exeUpt = new StoreReceiveReverse();
            exeUpt.setId(reverseDto.getId());
            //提交失败
            exeUpt.setSubmitOaStatus(21204);
            storeReceiveReverseMapper.update(exeUpt);

            resultData.setFail("该收款单发红冲失败");
        }

        logger.info("红冲单发 end，this time is" + new Date());
        return resultData;
    }

    private CTPRestClient getClient() {
        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance(SystemParam.getWebConfigValue("oaUrl"));
        // 取得REST动态客户机实例
        CTPRestClient client = clientManager.getRestClient();
        return client;
    }

    private void getOaAttachment(List<Long> attachList, int receiveId, Integer fileTypeCode, String userCode) throws Exception {
        StringBuilder oaFile = new StringBuilder();
        // 文件Code
        String sbFileCode = null;
        try {
            Map<String, Object> param = new HashMap<>();
            String attachStrs = "";
            param.put("refId", receiveId);
            param.put("fileTypeId",fileTypeCode);
            List<FileRecordMainDto> backResult = fileService.queryList(param);
            for(FileRecordMainDto fileMain : backResult) {
                String fileUrl = fileMain.getFileUrl();

                String fileFullName = fileMain.getFileFullName();
                String attachmentId = UploadOAUtil.oaAttachmentUpload(fileUrl,fileFullName, userCode,"");
                if (StringUtil.isNotEmpty(attachmentId)) {
                    // 信息拼接
                    sbFileCode = "<value>" + attachmentId + "</value>";
                    oaFile.append(sbFileCode);

                    attachList.add(Long.valueOf(attachmentId));
                    if (StringUtil.isEmpty(attachStrs)) {
                        attachStrs = attachmentId;
                    } else {
                        attachStrs = attachStrs + "|" + attachmentId;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("store_recieve",
                    "APITimerController",
                    "getOaAttachment",
                    "",
                    Integer.valueOf(userCode),
                    "",
                    "上传文件Code查询失败！",
                    e);
        }
    }
}
  