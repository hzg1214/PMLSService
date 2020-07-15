package cn.com.eju.deal.storeRelocation.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dao.DistrictMapper;
import cn.com.eju.deal.base.linkage.model.District;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.XmlTransferUtil;
import cn.com.eju.deal.contract.dao.ContractChangeMapper;
import cn.com.eju.deal.contract.dao.ContractChangeNewMapper;
import cn.com.eju.deal.contract.dao.ContractChangeStoreMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.model.ContractChangeStore;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.open.controller.UploadOAUtil;
import cn.com.eju.deal.storeRelocation.model.StoreRelocationDto;

import org.springframework.stereotype.Service;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StoreRelocationService extends BaseService<ContractChange> {
	//附件数据Map key
    private final static String ATTACH_KEY = "attachment";
	//表单数据Map key
    private final static String XML_DATA_KEY = "xmlData";
    //日期
    private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";
    
    //组装表单数据 -修改表单 Map key
    private final static String FLAG_MODIFY_KEY = "oaModify";
    
    @Resource
    private ContractChangeNewMapper contractChangeNewMapper;
    @Resource
    private DistrictMapper districtMapper;
    
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource
    private ContractChangeMapper contractChangeMapper;
    @Resource
    private ContractChangeStoreMapper contractChangeStoreMapper;
    @Resource
    private CitySettingMapper citySettingMapper;
    @Resource
    private SeqNoAPIImpl seqNoAPI;
    @Resource
    private FileRecordMainService fileService;
    
    @Resource
    private ContractStoreMapper contractStoreMapper;
    @Resource
    private ContractMapper contractMapper;
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    public StoreRelocationDto getContractAndStoreInfo(Integer storeId, Integer contractId) {
        StoreRelocationDto storeRelocation = new StoreRelocationDto();
        ContractDto contract = contractChangeNewMapper.getContractById(contractId);
        storeRelocation.setContract(contract);
        StoreDto store = contractChangeNewMapper.getStoreById(storeId);
        String cityNo = store.getCityNo();
        List list = new ArrayList();
        list.add(store);
        storeRelocation.setStoreList(list);
        //查询区域列表
        final List<District> moList = districtMapper.getDistrictByCityNo(cityNo);
        storeRelocation.setDistrictList(moList);
        return storeRelocation;
    }
    /**
     * 根据id进行查询
     * @throws Exception
     */
    public StoreRelocationDto getStoreRelocationById(Integer id) throws Exception{
    	StoreRelocationDto changeDto = contractChangeMapper.getStoreRelocationById(id);
    	if(null != changeDto) {
    		String storeCityName = SystemParam.getCityNameByCityNo(changeDto.getStoreCity());
    		String storeDistrictName = SystemParam.getDistrictNameByDistrictNo(changeDto.getStoreDistrict());
    		changeDto.setStoreCityName(storeCityName);
    		changeDto.setStoreDistrictName(storeDistrictName);//getStoreRelocation
    		StoreDto store = contractChangeMapper.getStoreRelocationStoreById(id);
    		List list = new ArrayList();
    		list.add(store);
    		changeDto.setStoreList(list);
    		final List<District> moList = districtMapper.getDistrictByCityNo(changeDto.getStoreCity());
    		changeDto.setDistrictList(moList);
    		
    		String fileRecordMainIds = "";
    		FileRecordMain attachmentFile = new FileRecordMain();
	       	attachmentFile.setRefId(id);
	       	attachmentFile.setIsDelete(false);
	       	//补充协议 fileComplement
	       	List<ContractFileDto> fileComplementList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList1 = fileRecordMainMapper.getfileComplementListById(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList1, fileRecordMainIds, fileComplementList);
	       	//房友确认单 fileFyConfirmation
	       	List<ContractFileDto> fileFyConfirmationList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getfileFyConfirmationById(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, fileFyConfirmationList);
	       	//门店照片 fileStorePhoto
	       	List<ContractFileDto> fileStorePhotoList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getfileStorePhotoById(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, fileStorePhotoList);
	       	//其他 fileOtherList
	       	List<ContractFileDto> fileOtherList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList4 = fileRecordMainMapper.getfileOtherListById(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList4, fileRecordMainIds, fileOtherList);
	       	if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
	       		fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
	       	}
	       	changeDto.setFileComplement(fileComplementList);
	       	changeDto.setFileFyConfirmation(fileFyConfirmationList);
	       	changeDto.setFileOtherList(fileOtherList);
	       	changeDto.setFileStorePhoto(fileStorePhotoList);
	       	changeDto.setFileRecordMainIds(fileRecordMainIds);
    	}
        return changeDto;
    }
    /**
     * 图片信息
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
     * 公盘提交OA审核
     * @param map
     * @return
     * @throws Exception 
     */
    public ResultData<String> storeRelocationSubmitOa(Map<String, Object> map) throws Exception {
        ResultData<String> resultData = new ResultData<>();
        resultData.setReturnMsg("门店迁址申请提交成功");
        String errorMsg = "";
        if(map.containsKey("id")) { 
       	 Integer contractStopId = Integer.valueOf(map.get("id").toString());
       	 Integer userIdCreate = Integer.valueOf(map.get("userIdCreate").toString());
       	 String userCode = map.get("userCode").toString();
       	 String userName = map.get("userName").toString();
       	 //更新时候再用
       	 ContractChange contractChange = new ContractChange();
       	 contractChange.setId(contractStopId);
       	 //根据id查询其门店迁址
       	 StoreRelocationDto storeRelocationDto = contractChangeMapper.getContractInfoById(contractStopId);
       	 if(1 == storeRelocationDto.getApproveState().intValue() || 2 == storeRelocationDto.getApproveState().intValue() ) {
      		 resultData.setFail("该门店迁址申请已提交OA审核，请刷新数据！");
  			 return resultData;
      	 }
       	 
		 String oaTemplateCode = "";
		 String busCodeString = "";
		 String busNoCity = "";
		 Map<String, Object> oaMap = new HashMap<String, Object>();
		 //组装表单发起数据
         try {
         //准备发起OA
		 //门店迁址城市
		 String changeCityNo = storeRelocationDto.getCityNo();//存入的合同的业绩城市
   		 // 根据城市编号获取模板
   		 Map<String, Object> oaTempLateCodeMap = getOaTempLateCode(changeCityNo);
   		 if(oaTempLateCodeMap == null) {
   			resultData.setFail();
    			resultData.setReturnMsg("根据城市编号获取模板失败");
	            return resultData;
   		 }
   		 oaTemplateCode = oaTempLateCodeMap.get("oaTemplateCode").toString();
   		 busCodeString = oaTempLateCodeMap.get("busCodeString").toString();
   		 //改为城市名称
   		 busNoCity = SystemParam.getCityNameByCityNo(changeCityNo);
   		 map.put("changeCityNo", changeCityNo);
   		 map.put("oaTemplateCode", oaTemplateCode);
   		 map.put("busNo", busCodeString);
   		 map.put("busNoCity", busNoCity);
   		 
   		//ContractChange contractChange = new ContractChange();
   		 //更新
   		 contractChange.setSubmitOAStatus(21202);
   		 //更新人
   		 contractChange.setUpdateCreate(userIdCreate);
   		 contractChange.setUpdateDate(new Date());
   		 contractChange.setApproveState(1);
   		 this.contractChangeMapper.updateFlowId(contractChange);
   		 //组装数据
   		 setToOaInfo(oaMap, map,oaTemplateCode);
         } catch (Exception e1) {
            logger.error("storeRelocation", "StoreRelocationService", "setToOaInfo", "", userIdCreate, "", "", e1);
            contractChange.setApproveState(0);
            contractChange.setSubmitOAStatus(21204);//处理失败
        	contractChange.setUpdateCreate(userIdCreate);
      		contractChange.setUpdateDate(new Date());
        	this.contractChangeMapper.updateFlowId(contractChange);
            resultData.setFail();
            resultData.setReturnMsg("组装门店迁址数据错误");
            return resultData;
         }
         logger.info("CRM 提交审核 storeRelocationSubmitOa reqMap=", map);
         //发起申请 返回FlowId
         Long flowId;
		 try {
			flowId = this.toOaAuth(oaMap, oaTemplateCode);
			map.put("flowId", flowId);
			contractChange.setSubmitOAStatus(21203);
			contractChange.setFlowId(flowId+"");
			contractChange.setApproveState(1);
			contractChange.setUpdateCreate(userIdCreate);
      		contractChange.setUpdateDate(new Date());
			this.contractChangeMapper.updateFlowId(contractChange);
		 } catch (Exception e) {
			contractChange.setApproveState(0);
			contractChange.setSubmitOAStatus(21204);//处理失败
			contractChange.setUpdateCreate(userIdCreate);
      		contractChange.setUpdateDate(new Date());
        	this.contractChangeMapper.update(contractChange);
			resultData.setFail();
			resultData.setReturnMsg("向OA发起提交审核失败");
			return resultData;
		 }
 		 //跟新合同门店表的approveState
		 List<ContractChangeStore> contractChangeStoreList  = contractChangeStoreMapper.getContractChangeStoreInfoById(storeRelocationDto.getId());
		 for (ContractChangeStore contractChangeStore : contractChangeStoreList) {
		  //根据门店id跟新门店表中的状态
			Integer integer  = 1 ;
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("storeId", contractChangeStore.getStoreId());
			paramMap.put("contractId", storeRelocationDto.getContractId());
			paramMap.put("contractStopId", storeRelocationDto.getId());
			paramMap.put("approveState", integer);
			contractChangeMapper.updateContractStoreByParam(paramMap);
			
			/*// 更新变更门店为终止
    		ContractStore contractStore = new ContractStore();
    		// 门店ID
    		contractStore.setStoreId(contractChangeStore.getStoreId());
    		// 合同ID
    		contractStore.setContractId(storeRelocationDto.getContractId());
    		// 门店状态 (1 : 变更中)
    		contractStore.setStoreState(1);
    		Integer num =contractStoreMapper.update(contractStore);*/
	 	 }
      }
      return resultData;
    }
    //获取模板编号
	private Map<String, Object> getOaTempLateCode(String gpContractCityNo) {
		Map<String, Object> hashMap = new HashMap<>();
		Map<String, Object> reposeMap = citySettingMapper.getCitySettingByCityNo(gpContractCityNo);
		
		String busCodeString="";
		String oaTemplateCode = "";
		if(null != reposeMap) {
			oaTemplateCode = reposeMap.get("contractChgTpl").toString();
			busCodeString=reposeMap.get("busCode").toString();
			hashMap.put("oaTemplateCode", oaTemplateCode);
			hashMap.put("busCodeString", busCodeString);
		}
		return hashMap;
	}
	 /** 
    * 组装表单发起数据
    * @param id
    * @param oaMap 
    * @param reqMap  传入参数 
    * @param templateCode 模板
    * 
    */
   private Map<String, Object> setToOaInfo(Map<String, Object> oaMap,Map<String, Object> reqMap, String oaTemplateCode) throws Exception {
	    String userCode = reqMap.get("userCode").toString();
	   	//末班编号
	   	reqMap.put("templateCode", oaTemplateCode);
       //发起者的登录名（登录协同的登录名）
       oaMap.put("senderLoginName", userCode);
       //协同的标题
       oaMap.put("subject", "合同变更报批单");
       //表单数据、附件数据
       Map<String, Object> rspMap = setOaAuditData(reqMap);

       //附件，Long型数组，值为附件的Id。
       oaMap.put("attachments", rspMap.get(ATTACH_KEY));

       //HTML正文流程为html内容；表单流程为XML格式的表单数据
       oaMap.put("data", rspMap.get(XML_DATA_KEY));
       
       //为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
       oaMap.put("state", "0");

       // 取得REST动态客户机实例
       CTPRestClient client = getClient();
       // 获取token认证
       String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/"+ SystemParam.getWebConfigValue("oaPassword"),String.class, "text/plain");
       //为登录验证后获取的身份令牌
       oaMap.put("token", token);

       return rspMap;
   }
   /** 
    * 组装发送OA审核的 表单数据、附件数据
   * @param achievementCancelNo 
    * @param id
    * @return
    */
  private Map<String, Object> setOaAuditData(Map<String, Object> reqMap) throws Exception {
      //返回表单数据、返回附件数据
      Map<String, Object> rspMap = new HashMap<String, Object>();
      String dateHtml = "";
      // 1. 获取变更申请信息
      Integer contractStopId = Integer.valueOf(reqMap.get("id").toString());
      StoreRelocationDto storeRelocationDto = contractChangeMapper.getContractInfoById(contractStopId);
      // 合同ID
      int contractId = storeRelocationDto.getContractId();
      // 3.获取合同信息
      Contract ctDto = contractMapper.getById(contractId);
      // 4.获取事业部区域
      String bussineArea = reqMap.get("busNo").toString();
      //String busNoCity = (String)reqMap.get("busNoCity");//事业部改为城市名称
      // 合作门店地址
      String addressDetail = "";
      /*String addressDetailDb = contractChangeStoreMapper.getChgStoreAddr(contractStopId);
      if(null != addressDetailDb){
			addressDetail = addressDetailDb;
	  }*/
      // 编码--取合同变更编号
      String contractStopNo = storeRelocationDto.getContractStopNo();
      // 报批日期
      String submitDate = DateUtil.fmtDate(new Date(), YYYYMMDD_FORMAT);
      //公司编码
      String configValue = SystemParam.getWebConfigValue(bussineArea);
      String[] values = configValue.split("\\|");
      String companyNo = values[0];
      //公司名称
      String companyName = values[1];
      rspMap.put("companyNo",companyNo);
      rspMap.put("companyName",companyName);
      
     // 业绩归属拓展人员姓名
      String ownName = ctDto.getExpandingPersonnel();
      // 业绩归属拓展人员工号
      String ownId = ctDto.getExpandingPersonnelId();
      // 经办人工号
      String operatorId =  reqMap.get("userCode").toString();
      // 经办人
      String operatorName =  reqMap.get("userName").toString();
      // 公司经营范围变更
      Integer changeCompanyName = storeRelocationDto.getChangeCompanyName() == null ? 0:storeRelocationDto.getChangeCompanyName();
      // 公司注册地址变更
      Integer changeCompanyAdress = storeRelocationDto.getChangeCompanyAdress() == null ? 0:storeRelocationDto.getChangeCompanyAdress();
      // 门店地址变更
      Integer changeStoreAdress = storeRelocationDto.getChangeStoreAdress() == null ? 0:storeRelocationDto.getChangeStoreAdress();
      // 签约主体变更
      Integer changeCompany = storeRelocationDto.getChangeCompany() == null ? 0:storeRelocationDto.getChangeCompany();
      String  storeCityName = SystemParam.getCityNameByCityNo(storeRelocationDto.getStoreCity());
      String  storeDistrictName = SystemParam.getDistrictNameByDistrictNo(storeRelocationDto.getStoreDistrict());
      addressDetail = storeCityName + storeDistrictName + storeRelocationDto.getStoreAdresss();
      String cooperationPeriod =  DateUtil.fmtDate(ctDto.getDateLifeStart(), YYYYMMDD_FORMAT) + "至"
              + DateUtil.fmtDate(ctDto.getDateLifeEnd(), YYYYMMDD_FORMAT);;  // 合作期限
      String partyB = ctDto.getPartyB();  // 中介全称
      String lealPerson =ctDto.getLealPerson(); // 法定代表人
      
      //公司注册地址
      String cityNameCompany = SystemParam.getCityNameByCityNo(ctDto.getPartyBcityNo());
      String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(ctDto.getPartyBdistrictNo());
      String areaNameCompany = SystemParam.getAreaNameByAreaNo(ctDto.getPartyBareaNo());
      //String companyAdresss = cityNameCompany + districtNameCompany + areaNameCompany; // 执照注册地址
      String companyAdresss = cityNameCompany + districtNameCompany + areaNameCompany + ctDto.getPartyBAddress();
     // 协议书编号String agreementNo = "";    
     // 保证金金额
      BigDecimal receivedAmount = new BigDecimal(0);
      BigDecimal receivedAmountDb = ctDto.getDepositFee();
      if (null != receivedAmountDb) {
          receivedAmount = receivedAmountDb;
      }
      //变更类型
      String changeTypeNm = SystemParam.getDicValueByDicCode(storeRelocationDto.getChangeType().toString());
      // 流程结束时间
      String flowEndDate = "";
      // CRM单据号--取合同编号
      String crmNo = ctDto.getContractNo();
   // ================ 上传的附件 部分 start =============== //
      dateHtml +=
              "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                      + "<summary id=\"6984485710218611336\" name=\"formmain_3884\"/>" + "<definitions/>" + "<values>"
                      + "<column name=\"编号\"><value>"
                      + XmlTransferUtil.transfer(contractStopNo)
                      + "</value></column>"
                      + "<column name=\"报批日期\"><value>"
                      + XmlTransferUtil.transfer(submitDate)
                      + "</value></column>"
                      + "<column name=\"核算主体名称\"><value>"
                      + companyName
                      + "</value></column>"
                      + "<column name=\"核算主体编码\"><value>"
                      + XmlTransferUtil.transfer(companyNo)
                      + "</value></column>"
                      + "<column name=\"区域\"><value>"
                      + XmlTransferUtil.transfer(bussineArea)
                      + "</value></column>"
                      + "<column name=\"业绩归属人员\"><value>"
                      + XmlTransferUtil.transfer(ownName)
                      + "</value></column>"
                      + "<column name=\"归属人员工号\"><value>"
                      + XmlTransferUtil.transfer(ownId)
                      + "</value></column>"
                      + "<column name=\"经办人\"><value>"
                      + XmlTransferUtil.transfer(operatorName)
                      + "</value></column>"
                      + "<column name=\"经办人工号\"><value>"
                      + XmlTransferUtil.transfer(operatorId)
                      + "</value></column>"
                      + "<column name=\"合同变更类型\"><value>"
                      + changeTypeNm
                      + "</value></column>"
                      + "<column name=\"公司经营范围变更\"><value>"
                      + changeCompanyName
                      + "</value></column>"
                      + "<column name=\"公司注册地址变更\"><value>"
                      + changeCompanyAdress
                      + "</value></column>"
                      + "<column name=\"门店地址变更\"><value>"
                      + changeStoreAdress
                      + "</value></column>"
                      + "<column name=\"签约主体变更\"><value>"
                      + changeCompany
                      + "</value></column>"
                      + "<column name=\"变更内容三方变更\"><value>"
                      + ""
                      + "</value></column>"
                      + "<column name=\"中介全称\"><value>"
                      + XmlTransferUtil.transfer(partyB)
                      + "</value></column>"
                      + "<column name=\"法定代表人\"><value>"
                      + XmlTransferUtil.transfer(lealPerson)
                      + "</value></column>"
                      + "<column name=\"执照注册地址\"><value>"
                      + XmlTransferUtil.transfer(companyAdresss)
                      + "</value></column>"
                      + "<column name=\"新签协议书编号\"><value>"
                      + ""
                      + "</value></column>"
                      + "<column name=\"合作期限\"><value>"
                      + XmlTransferUtil.transfer(cooperationPeriod)
                      + "</value></column>"
                      + "<column name=\"保证金金额\"><value>"
                      + receivedAmount
                      + "</value></column>"
                      + "<column name=\"门店经营地址\"><value>"
                      + XmlTransferUtil.transfer(addressDetail)
                      + "</value></column>"
                      + "<column name=\"营业执照注册号\"><value>"
                      +  XmlTransferUtil.transfer(ctDto.getRegistrId())
                      + "</value></column>"
                      + "<column name=\"备注\"><value>"
                      + XmlTransferUtil.transfer(storeRelocationDto.getRemarks())
                      + "</value></column>";
      
      dateHtml += "<column name=\"流程结束时间\"><value>"
                      + XmlTransferUtil.transfer(flowEndDate)
                      + "</value></column>"
                      + "<column name=\"CRM合同编号\"><value>"
                      + XmlTransferUtil.transfer(crmNo)
                      + "</value></column>"
                      + "</values>"
                      + "</formExport>";
      
      //附件集合
      List<Long> attachList = new ArrayList<Long>();
      //补充协议
      this.getOaAttachment(attachList, storeRelocationDto.getId(), 1045, operatorId,"补充协议_");
      //房友确认单
      this.getOaAttachment(attachList, storeRelocationDto.getId(), 1046, operatorId,"房友确认单_");
      //门店照片
      this.getOaAttachment(attachList, storeRelocationDto.getId(), 1047, operatorId,"门店照片_");
      //其它照片
      this.getOaAttachment(attachList, storeRelocationDto.getId(), 1048, operatorId,"其他_");
      
      //附件数据
      rspMap.put(ATTACH_KEY, attachList.toArray());
      //表单数据
      rspMap.put(XML_DATA_KEY, dateHtml.toString());
      logger.info("CRM 提交审核 dateHtml=", dateHtml);
      return rspMap;
  }
  private void getOaAttachment(List<Long> attachList, int refId, Integer fileTypeCode, String userCode,String pre) throws Exception {
      // 文件Code
      try {
   	   Map<String, Object> param = new HashMap<>();
          param.put("refId", refId);
          param.put("fileTypeId", fileTypeCode);
          List<FileRecordMainDto> backResult = fileService.queryList(param);
          for (FileRecordMainDto fileMain : backResult) {
              String fileUrl = fileMain.getFileUrl();

              pre += fileMain.getFileFullName();
              String attachmentId = UploadOAUtil.oaAttachmentUpload(fileUrl, pre, userCode, "");
              if (StringUtil.isNotEmpty(attachmentId)) {
                  attachList.add(Long.valueOf(attachmentId));
              }
          }
      } catch (Exception e) {
          logger.error("gpContractChange","GpContractChangeService","getOaAttachment", "", Integer.valueOf(userCode),"",
                  "上传文件失败！",
                  e);
      }
  }
  public Long toOaAuth(Map<String, Object> reqMap, String oaTemplateCode)
          throws Exception {
      logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);
      // 取得REST动态客户机实例
      CTPRestClient client = getClient();
      // token认证
      client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
              SystemParam.getWebConfigValue("oaPassword"));
      // 表单post
      String url = "flow/" + oaTemplateCode;
      Long flowId = client.post(url, reqMap, Long.class);

      return flowId;
  }
  private CTPRestClient getClient() {
      // 指定协议、IP和端口，获取ClientManager
      CTPServiceClientManager clientManager = CTPServiceClientManager
              .getInstance(SystemParam.getWebConfigValue("oaUrl"));
      // 取得REST动态客户机实例
      CTPRestClient client = clientManager.getRestClient();
      return client;
  }
  public String checkDecorationStatus(int id) throws Exception{
  	String str = "";//不能通过
  	Integer integer = contractChangeMapper.checkDecorationStatus(id);
  	str = integer +"";
    return str;
  }
  public String checkStoreAddress(Map<String, Object> queryParam) throws Exception{
	  String str = "";//地址已使用
	  Integer integer = contractChangeMapper.checkStoreAddress(queryParam);
	  str = integer +"";
	  return str;
  }
  /**
   * 保存门店迁址申请
   */
	public ResultData<?> saveStoreRelocation(Map<String, Object> reqMap) throws Exception {
      ResultData<String> resultData = new ResultData<>();
      ContractChange contractChange  = new ContractChange();
      // 门店城市
      String storeCity = reqMap.get("storeCity").toString();
      //门店区域
      String storeDistrict = reqMap.get("storeDistrict").toString();
      //门店地址
      String storeAddress = reqMap.get("storeAddress").toString();
      //门店原地址详情
      String oldStoreAddressDetail = reqMap.get("oldStoreAddressDetail").toString();
      //备注
      String remarks = reqMap.get("remarks").toString();
      //17004 门店迁址
      Integer changeType = Integer.valueOf((String) reqMap.get("changeType"));
      //文件id集
      String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();
      
      Integer contractId = Integer.valueOf((String) reqMap.get("contractId"));
      //门店信息
      Integer storeId = Integer.valueOf((String) reqMap.get("storeId"));
      //String storeNo = reqMap.get("storeNo").toString();
      //String storeName = reqMap.get("storeName").toString();
      String storeManager = reqMap.get("storeManager").toString();
      String storeManagerPhone = reqMap.get("storeManagerPhone").toString();
      Integer aBTypeStore = Integer.valueOf((String) reqMap.get("storeABTypeStore"));
      Integer bTypeStore = Integer.valueOf((String) reqMap.get("storeBTypeStore"));
      
      //String userCode = reqMap.get("userCode").toString();
      Integer userIdCreate = Integer.valueOf(reqMap.get("userIdCreate").toString());
      
      //公司编号
      String companyBankNo = reqMap.get("companyNo").toString();
      Integer oldCompanyId = Integer.valueOf(reqMap.get("oldCompanyId").toString());
      String oldUpdateCompanyName = reqMap.get("oldUpdateCompanyName").toString();
      String type = reqMap.get("type").toString();
      //合同业绩城市
      String contractAcCityNo = reqMap.get("contractAcCityNo").toString();
      contractChange.setChangeStoreAdress(1);//门店地址变更
      contractChange.setChangeType(changeType);
      contractChange.setRemarks(remarks);
      contractChange.setOldStoreAddressDetail(oldStoreAddressDetail);
      contractChange.setStoreCity(storeCity);
      contractChange.setStoreDistrict(storeDistrict);
      contractChange.setStoreAdresss(storeAddress);
      contractChange.setOldUpdateCompanyName(oldUpdateCompanyName);
      contractChange.setCompanyBankNo(companyBankNo);
      contractChange.setOldCompanyId(oldCompanyId);
      contractChange.setSubmitOAStatus(21201);
      contractChange.setDelFlag(false);
      contractChange.setCityNo(contractAcCityNo);
      contractChange.setContractId(contractId);
	  String contractStopId = "";
	  if("create".equals(type)) {
			if(reqMap.containsKey("contractStopNo")) {
				//编号
			    String contractStopNo = reqMap.get("contractStopNo").toString();
			    contractChange.setContractStopNo(contractStopNo);
				contractChange.setUserIdCreate(userIdCreate);
				contractChange.setDateCreate(new Date());
				contractChange.setApproveState(0);//审核状态(0:未审核 1:审核中 2:审核通过 3:审核失败)
				//保存
				int count = contractChangeMapper.create(contractChange);
				if(count <=0) {
					resultData.setFail("保存门店迁址失败");
					return resultData;
				}
				Integer contractChangeId =  contractChange.getId();
				contractStopId = contractChangeId + "";
				if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
					String[] arrays = fileRecordMainIds.split(",");
					// 关联相关文件(RefId)
					for (int i = 0; i < arrays.length; i++){
						if (StringUtil.isNotEmpty(arrays[i])){
							FileRecordMain fileRecordMain = new FileRecordMain();
							fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
							fileRecordMain.setRefId(contractChangeId);
							fileRecordMain.setIsDelete(false);
							fileRecordMainMapper.update(fileRecordMain);
						}
					}
				}
			}else {
				resultData.setFail("保存门店迁址失败,无变更编号");
				return resultData;
			}
			
	   } else if("update".equals(type)) {
			String oldfileRecordMainIds = reqMap.get("oldfileRecordMainIds").toString();
			//变更主键
			Integer id = Integer.valueOf((String) reqMap.get("id"));
			contractStopId = id + "";
			contractChange.setUpdateCreate(userIdCreate);
			contractChange.setUpdateDate(new Date());
			contractChange.setId(id);
			int count = contractChangeMapper.update(contractChange);
			if(count <=0) {
				resultData.setFail("更新门店迁址失败");
		        return resultData;
			}
			//对原上传文件删除
			if (oldfileRecordMainIds != null && StringUtil.isNotEmpty(oldfileRecordMainIds)) {
				String[] oldfileRecordMainIdsArray = oldfileRecordMainIds.split(",");
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
			contractChangeStoreMapper.updateById(id);
			// 关联相关文件(RefId)
			if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
				String[] arrays = fileRecordMainIds.split(",");
				for (int i = 0; i < arrays.length; i++) {
					if (StringUtil.isNotEmpty(arrays[i])) {
						FileRecordMain fileRecordMain = new FileRecordMain();
						fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
						fileRecordMain.setRefId(Integer.valueOf(id));
						fileRecordMain.setIsDelete(false);
						fileRecordMainMapper.update(fileRecordMain);
					}
				}
			}
			
		}
		//重新建立新的公盘合同变更与门店的关系
		ContractChangeStore changeStore  = new ContractChangeStore();
		changeStore.setContractId(contractId);
		changeStore.setStoreId(storeId);
		changeStore.setAbTypeStore(aBTypeStore);
		changeStore.setBtypeStore(bTypeStore);
		changeStore.setApproveState(0);
		changeStore.setContractStopId(Integer.valueOf(contractStopId));
		changeStore.setDelFlag(false);
		changeStore.setStoreManager(storeManager);
		changeStore.setStoreManagerPhone(storeManagerPhone);
		contractChangeStoreMapper.create(changeStore);
		resultData.setSuccess();
      return resultData;
  }
}
