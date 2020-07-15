package cn.com.eju.deal.frameContract.service;


import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.code.service.CommonCodeService;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.XmlTransferUtil;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.frameContract.dao.FrameContractMapper;
import cn.com.eju.deal.frameContract.dao.LogFrameContractMapper;
import cn.com.eju.deal.frameContract.dao.OaLnkFrameContractReturnMapper;
import cn.com.eju.deal.frameContract.model.*;
import cn.com.eju.deal.oa.model.OaOperator;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.open.controller.UploadOAUtil;
import cn.com.eju.deal.open.service.APIOaService;

import cn.com.eju.pmls.channelBusiness.model.BusinessManagerDto;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("frameContractService")
public class FrameContractService {

	/**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());

     @Resource
	 private FrameContractMapper frameContractMapper;


     @Resource
	 private LogFrameContractMapper  logFrameContractMapper;

     @Resource
	 private FileRecordMainMapper fileRecordMainMapper;

     @Resource
     private APIOaService apiOaService;

    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;

    @Resource
    private CommonCodeService commonCodeService;

    @Resource
    private FileRecordMainService fileService;
    @Resource
    private OaLnkFrameContractReturnMapper oaLnkFrameContractReturnMapper;

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

	 /**
     * 联动框架合同列表
     * @param reqMap
     * @return
     */
     public ResultData getFrameContractList(Map<String,Object> reqMap)throws Exception{
         ResultData resultData = new ResultData();
         resultData.setFail();
         List<FrameContractDto> frameContractList =  frameContractMapper.getFrameContractList(reqMap);
         if(frameContractList !=null && frameContractList.size()>0){
        	 for (FrameContractDto frameContractDto : frameContractList) {
				Date dateCreate = frameContractDto.getDateCreate();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String format2 = format.format(dateCreate);
				frameContractDto.setFormat2(format2);

				if(reqMap.get("oaFile")!=null && reqMap.get("oaFile").equals("1")){//查询盖章合同附件
					List<OaLnkFrameContractFileReturnDto> oaFileList= frameContractMapper.getOaFileList(frameContractDto);
					frameContractDto.setOaFileList(oaFileList);
				}
			}
         	 resultData.setReturnData(frameContractList);
 	         resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
 	         resultData.setSuccess();
         }else{
			 resultData.setSuccess();
		 }
         return resultData;
     }
     /**
	  * 框架合同详情
	  * @param id
	  * @return
	  * @throws Exception
	  */
    public ResultData<FrameContractDto> getBriefById(int id) throws Exception {
        //构建返回
    	ResultData resultData = new ResultData();
        resultData.setFail();
        //根据框架合同id获取其详细信息
        FrameContractDto frameContractDto =frameContractMapper.getBriefById(id);
        if(frameContractDto != null){
        	/**
        	 *  获取文件信息
        	 */
        	String fileRecordMainIds = "";
        	FileRecordMain attachmentFile = new FileRecordMain();
        	attachmentFile.setRefId(id);
        	attachmentFile.setIsDelete(false);
        	//TODO
        	//营业证
        	List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        	List<FileRecordMain> fileMdlList1 = fileRecordMainMapper.getBusinessByFrameContractId(attachmentFile);
        	fileRecordMainIds = pushFileRecord(fileMdlList1, fileRecordMainIds, fileBusinessList);
        	//合同照片
        	List<ContractFileDto> fileContractList = new ArrayList<ContractFileDto>();
        	List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getContractFileByFrameContractId(attachmentFile);
        	fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, fileContractList);
        	//附件其他文件信息
        	List<ContractFileDto> attachmentFileList = new ArrayList<ContractFileDto>();
        	List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getAttachmentFileByFrameContractId(attachmentFile);
        	fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, attachmentFileList);

        	if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
        		fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
        	}

        	frameContractDto.setFileBusinessList(fileBusinessList);
        	frameContractDto.setFileContractList(fileContractList);
        	frameContractDto.setAttachmentFileList(attachmentFileList);
        	frameContractDto.setFileRecordMainIds(fileRecordMainIds);
        	//返回
    		resultData.setReturnData(frameContractDto);
    		resultData.setSuccess();
        }
		return resultData;
    }
    /**
     * @Title: updateStr
     * @Description: 更新框架合同信息
     * @param param
     */
     public ResultData updateStr(String param)throws Exception {
    	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    	queryParam.put("submitOAStatus",21201);

    	//作废保留原提交状态
		 if(queryParam.get("approveState") != null && queryParam.get("approveState").toString().equals("10405")){
			 queryParam.remove("submitOAStatus");
		 }

    	Integer resultRow	=	null;
    	ResultData<Object> resultData = new ResultData<Object>();
 		resultData.setFail();
 		try {
 			resultRow = frameContractMapper.updateStr(queryParam);
 		}catch(Exception e){
 			logger.error("[pmlsService invoke frameContractMapper.updateStr error...]");
 			return resultData;
 		}
 		if(resultRow == null || resultRow.intValue() == 0) {
 			return resultData;
 		}

 		//更新图片信息
 		if(queryParam.containsKey("frameContractId")) {
			String frameContractDtoId = queryParam.get("frameContractId").toString();
			String fileRecordMainIds = queryParam.get("fileRecordMainIds").toString();
			String oldfileRecordMainIds = queryParam.get("oldfileRecordMainIds").toString();
			if (!queryParam.containsKey("delFlag")) {
				// 关联相关文件并设置RefId为空
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
				// 关联相关文件(RefId)
				if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
					String[] arrays = fileRecordMainIds.split(",");
					for (int i = 0; i < arrays.length; i++) {
						if (StringUtil.isNotEmpty(arrays[i])) {
							FileRecordMain fileRecordMain = new FileRecordMain();
							fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
							fileRecordMain.setRefId(Integer.valueOf(frameContractDtoId));
							fileRecordMain.setIsDelete(false);
							fileRecordMainMapper.update(fileRecordMain);
						}
					}
				}
			}
		}

 		//框架协议作废
 		/*if(queryParam.containsKey("approveState") && "10405".equals(queryParam.get("approveState").toString())){
			frameContractMapper.updateCompanyBizType(queryParam);
		}*/

        resultData.setSuccess();
        resultData.setReturnData(resultRow);
        return resultData;
   }
     /**
      * 查询新增框架合同时候选择公司列表
      * @param param
      * @throws Exception
      */
     public ResultData<List<CompanyDto>> getFrameContractCompanyList(Map<?, ?> param) throws Exception {
     	ResultData<List<CompanyDto>> resultData = new ResultData<List<CompanyDto>>();
     	List<CompanyDto> comanyDtoList = frameContractMapper.getFrameContractCompanyList(param);
     	resultData.setReturnData(comanyDtoList);
     	resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
     	return resultData;
     }
     /**
      * 根据公司id查询信息
      * @param id
      * @return
      * @throws Exception
      */
     public ResultData<CompanyDto> getCompanyInfoById(int id) throws Exception {
     	//构建返回
     	ResultData resultData = new ResultData();
     	resultData.setFail();
     	List<CompanyDto> storeDtoList = frameContractMapper.getCompanyInfoById(id);
     	if(storeDtoList != null && storeDtoList.size()>0){
     		resultData.setReturnData(storeDtoList.get(0));
     		resultData.setSuccess();
     	}
     	return resultData;
     }

     /**
      * 新增框架合同
      * @throws ParseException
      */
     public int create(Map<String, Object> map) throws ParseException{
     	FrameContractDto frameContract = new FrameContractDto();
     	String userIdCreate = map.get("userIdCreate").toString();
     	String userCityNo = map.get("userCityNo").toString();
     	String contractNo = map.get("contractNo").toString();
     	String companyNo = map.get("frameCompanyNo").toString();
     	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
     	String dateLifeStart = map.get("dateLifeStart").toString();
     	String dateLifeEnd = map.get("dateLifeEnd").toString();
     	String signDate = map.get("signDate").toString();
     	//公司业绩归属城市编号
     	String companyAcCityNo = map.get("companyAcCityNo").toString();
     	String accountNm = map.get("accountNm").toString();
     	String bankAccountNm = map.get("bankAccountNm").toString();
     	String bankAccount = map.get("bankAccount").toString();
     	String partyBNm = map.get("partyBNm").toString();
     	String partyBTel = map.get("partyBTel").toString();
//     	String fyAccountNm = map.get("fyAccountNm").toString();
//     	String fyAccountNmTel = map.get("fyAccountNmTel").toString();
     	String remark = map.get("remark").toString();
     	String fileRecordMainIds = map.get("fileRecordMainIds").toString();
     	String contractNote = map.get("contractNote").toString();
     	//公司名称
     	String comapanyName = map.get("comapanyName").toString();
     	//公司城市编号
     	String companyCityNo = map.get("companyCityNo").toString();
     	//区域编号
     	String companyDistrictNo = map.get("companyDistrictNo").toString();
     	//详情地址
     	String address = map.get("address").toString();
     	//统一社会信用代码
     	String businessLicenseNo = map.get("businessLicenseNo").toString();
     	//法人
     	String lealPerson = map.get("lealPerson").toString();
     	//电话信息
     	String companyContactTel = map.get("companyContactTel").toString();
     	//协议类型
     	String agreementType = map.get("agreementType").toString();
     	String reAgreeFlag = map.get("reAgreeFlag").toString();
     	if (StringUtil.isNotEmpty(dateLifeStart)){
     		frameContract.setDateLifeStart(format.parse(dateLifeStart));
        }
     	if (StringUtil.isNotEmpty(dateLifeEnd)){
     		frameContract.setDateLifeEnd(format.parse(dateLifeEnd));
     	}
     	if (StringUtil.isNotEmpty(signDate)){
     		frameContract.setSignDate(format.parse(signDate));
     	}
     	frameContract.setContractNo(contractNo);
     	frameContract.setCompanyNo(companyNo);
     	frameContract.setUserIdCreate(Integer.valueOf(userIdCreate));
     	frameContract.setAccountNm(accountNm);
     	frameContract.setBankAccount(bankAccount);
     	frameContract.setBankAccountNm(bankAccountNm);
     	frameContract.setPartyBNm(partyBNm);
     	frameContract.setPartyBTel(partyBTel);
//     	frameContract.setFyAccountNm(fyAccountNm);
//     	frameContract.setFyAccountNmTel(fyAccountNmTel);
     	frameContract.setRemark(remark);
     	frameContract.setContractNote(contractNote);
     	frameContract.setApproveState(10401);
     	frameContract.setValidStatus(18401);
     	frameContract.setDelFlag("0");
     	//新增保存字段
     	frameContract.setCompanyName(comapanyName);
     	frameContract.setCompanyCityNo(companyCityNo);
     	frameContract.setCompanyDistrictNo(companyDistrictNo);
     	frameContract.setAddress(address);
     	frameContract.setBusinessLicenseNo(businessLicenseNo);
     	frameContract.setLegalPerson(lealPerson);
     	frameContract.setCompanyContactTel(companyContactTel);
     	frameContract.setAgreementType(Integer.valueOf(agreementType));
     	frameContract.setReAgreeFlag(Integer.valueOf(reAgreeFlag));

     	//TODO  OA提交相关
     	frameContract.setSubmitOAStatus(21201);
     	frameContract.setFyNoticeStatus("1");

     	// Start 20190917 modify by huangmc -- 因为公司可能选择的是发布城市里的，所以修改为当前登录人的城市编号
     	//frameContract.setCityNo(companyAcCityNo);
		 frameContract.setCityNo(userCityNo);
		 // End 20190917 modify by huangmc  -- 因为公司可能选择的是发布城市里的，所以修改为当前登录人的城市编号

		 if(map.containsKey("accountProvinceNo")&&map.get("accountProvinceNo")!=null) {
			 frameContract.setAccountProvinceNo(map.get("accountProvinceNo").toString());
		 }
		 if(map.containsKey("accountProvinceNm")&&map.get("accountProvinceNm")!=null) {
			 frameContract.setAccountProvinceNm(map.get("accountProvinceNm").toString());
		 }
		 if(map.containsKey("accountCityNo")&&map.get("accountCityNo")!=null){
			 frameContract.setAccountCityNo(map.get("accountCityNo").toString());
		 };
		 if(map.containsKey("accountCityNm")&&map.get("accountCityNm")!=null) {
			 frameContract.setAccountCityNm(map.get("accountCityNm").toString());
		 }
		//根据登录人的城市编号查询其核算主体
		List<FrameContractDto> accountProjectList = frameContractMapper.getLnkAccountProjectByCityNo(userCityNo);
		if(accountProjectList.size() > 0 && null != accountProjectList){
			String accountProject = accountProjectList.get(0).getAccountProject();
			String accountProjectCode = accountProjectList.get(0).getAccountProjectCode();
			//合同新增时候保存其核算主体及其编号，核算主体及其编号
			if(accountProject != ""){
				frameContract.setAccountProject(accountProject);
			}
			if (accountProjectCode != "") {
				frameContract.setAccountProjectCode(accountProjectCode);
			}
		}
     	//主表插入数据
        int count = frameContractMapper.create(frameContract);
         //返回id
         Integer frameContractId = frameContract.getId();
     	 if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
              String[] arrays = fileRecordMainIds.split(",");
              // 关联相关文件(RefId)
              for (int i = 0; i < arrays.length; i++){
                  if (StringUtil.isNotEmpty(arrays[i])){
                      FileRecordMain fileRecordMain = new FileRecordMain();
                      fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                      fileRecordMain.setRefId(frameContractId);
                      fileRecordMain.setIsDelete(false);
                      fileRecordMainMapper.update(fileRecordMain);
                  }
              }
     	 }

         //通知op,businessType=1:房友，2:op,3:新房
//         if(frameContractId != null){
//             if("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
//                 //18版OP
//                 String url18 = SystemParam.getWebConfigValue("opUrl18") + "accounts/apply/{companyNo}?businessType=3&receivePhone="+fyAccountNmTel;
//                 String opResult = null;
//                 try {
//                     opResult = HttpClientUtil.jsonGet(url18, companyNo);
//                 }catch (Exception e){
//                     FrameContract frameContract1 = new FrameContract();
//                     frameContract1.setId(frameContractId);
//                     frameContract1.setFyNoticeStatus("0");
//                     frameContractMapper.updateByPrimaryKeySelective(frameContract1);
//                     logger.error("FrameContract", "FrameContractService", "create", null, null, null, "CRM草签框架合同向OP发起待开通新房账号接口失败！Company："+companyNo+"  opResult: "+opResult, null);
//                 }
//
//                 try{
//                     Map<String,String> opMap = (Map<String,String>) JsonUtil.parseToObject(opResult,  Map.class);
//                     if(opMap.get("returnCode").equals("200")){
//                         logger.error("FrameContract", "FrameContractService", "create", null, null, null, "CRM草签框架合同向OP发起待开通新房账号接口失败！Company："+companyNo+"  opResult: "+opResult, null);
//                     }
//                 }catch (Exception e){
//                     logger.error("FrameContract", "FrameContractService", "create", null, null, null, "CRM草签框架合同向OP发起待开通新房账号接口失败！Company："+companyNo+"  opResult: "+opResult, null);
//                 }
//             }
//         }

  		return count;
  	}

     /**
      * 根据companyNo查询信息
      * @param companyNo
      * @throws Exception
      */
     public ResultData<CompanyDto> getCompanyInfoByCompanyNo(String companyNo) throws Exception {
     	//构建返回
     	ResultData resultData = new ResultData();
     	resultData.setFail();
     	List<CompanyDto> storeDtoList = frameContractMapper.getCompanyInfoByCompanyNo(companyNo);
     	if(storeDtoList != null && storeDtoList.size()>0){
     		resultData.setReturnData(storeDtoList.get(0));
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
          contractFileDto.setFileSuffix(fileRecordMain.getFileSuffix());
          contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
          contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
          contractFileDto.setUrl50(fileRecordMain.getUrl50());
     }

    /**
     * 框架合同提交OA
     * @param param
     * @return
     * @throws Exception
     */
     public ResultData<String> submitToOA(Map<String,String> param) throws Exception{

         ResultData<String> resultData = new ResultData<>();

         ResultData<String> data2 = seqNoAPI.getSeqNoByTypeCode("TYPE_FRAMECTOA");
         String oaNo = "";
         if (data2 != null && data2.getReturnCode().equals("200")) {
      	   oaNo = data2.getReturnData();
      	   System.out.println(oaNo);
         } else {
             new NullPointerException("根据seq_type: TYPE_FRAMECTOA 获取联动合同申请oa编号为空");
         }
         //String oaNo = "FYLD"+DateUtil.fmtDate(new Date(),"yyyyMMddHHmmss");

         try {
             Integer contractId = Integer.valueOf(param.get("contractId"));

             //提交OA状态为处理中
             FrameContract contract = new FrameContract();
             contract.setId(contractId);
             contract.setSubmitOAUserId(Integer.valueOf(param.get("userId")));
             contract.setSubmitTime(new Date());
             contract.setSubmitOAStatus(21202);
             frameContractMapper.updateByPrimaryKeySelective(contract);

             FrameContractDto frameContractDto = frameContractMapper.getBriefById(contractId);
             if (frameContractDto == null) {
                 throw new Exception("\"根据：\" + contractId + \"查询框架合同异常\"");
             }

//             if(StringUtil.isEmpty(frameContractDto.getAccountProvinceNm())||StringUtil.isEmpty(frameContractDto.getAccountCityNm())){
//             	throw new Exception("请选择开户省市！");
//			 }

             //校验供应商信息
             JSONObject jsonObject = checkVendorInfo(frameContractDto);

             if("F".equals(jsonObject.get("state").toString())){
                 throw new Exception("经纪公司名称或统一社会信用代码与OA中供应商信息不一致，请先在OA中做供应商信息变更。");
             }

             if (jsonObject == null) {
                 throw new Exception("检验供应商时出现错误");
             }

             String vendorId = "";
             JSONArray data = (JSONArray) jsonObject.get("data");
             if(data != null && data.size() > 0){
                 JSONObject object = (JSONObject)data.get(0);
                 vendorId = object.get("VENDOR_ID").toString();
             }

             //获取事业部区域
             /*String bussineArea = this.getBussineArea(frameContractDto.getCityNo(), param.get("userCode"));

             String dicValue = SystemParam.getWebConfigValue(bussineArea);
             String[] list;
             String accountNo = "";
             String accountMain = "";
             if (StringUtils.isNotBlank(dicValue)) {
                 list = dicValue.split("\\|");
                 //核算主体编号
                 accountNo = list[0];
                 //核算主体名称
                 accountMain = list[1];
             }*/

            String accountNo = "";
            String accountMain = "";
            //查询框架协议里面的存储的核算主体
     		FrameContractDto frameContractAccountProject = frameContractMapper.getFrameContractAccountProject(contractId);
 			String accountProject = frameContractAccountProject.getAccountProject();
 			String accountProjectCode = frameContractAccountProject.getAccountProjectCode();
 			if(accountProjectCode != "" && accountProjectCode != null){
 				accountNo = accountProjectCode;
 			}
 			if (accountProject != "" && accountProject != null) {
 				accountMain = accountProject;
     		}
 		   // 框架合同OA模板
            Map<String, Object> citySetting = citySettingMapper.getCitySettingByCityNo(frameContractDto.getCityNo());
            String oaTemplateCode = "";
            String oaTemplateCodeAuto = "";
            String  sendUserCodeAuto="";
            String  sendUserNameAuto="";
            if(citySetting != null && citySetting.get("frameContractTpl") != null){
                oaTemplateCode  = citySetting.get("frameContractTpl").toString();
                oaTemplateCodeAuto = citySetting.get("frameContractAutoTpl").toString();
            	sendUserCodeAuto  = citySetting.get("frameContractAutoSendUserCode").toString();
            	sendUserNameAuto  = citySetting.get("frameContractAutoSendUserName").toString();
            }else{
                throw new Exception("根据cityNo:"+frameContractDto.getCityNo()+" 查询框架合同oa模板编号异常");
            }

 			String  sendUserCode= param.get("userCode");
 			String  sendUserName=param.get("userName");
 			if (frameContractDto.getAutoToOa() !=null && frameContractDto.getAutoToOa().intValue()==1){
 				accountMain =frameContractDto.getAccountProject();
 				accountNo =frameContractDto.getAccountProjectCode();
 				sendUserCode=sendUserCodeAuto;
 				sendUserName=sendUserNameAuto;
 				oaTemplateCode=oaTemplateCodeAuto;
 			}

             //附件提交OA
             List<Long> attachList = new ArrayList<>();
             // 联动框架合同-营业执照
             this.getOaAttachment(attachList, contractId, 1028, sendUserCode, "营业执照");
             // 联动框架合同-合同
             this.getOaAttachment(attachList, contractId, 1029, sendUserCode, "合同");
             // 联动框架合同-其它
             this.getOaAttachment(attachList, contractId, 1030, sendUserCode, "其它");
             //获取框架协议类型
             Integer agreementType = frameContractDto.getAgreementType();
             String remark ="";
             if(StringUtil.isNotEmpty(frameContractDto.getRemark())){
            	remark += XmlTransferUtil.transfer(frameContractDto.getRemark())+"\r\n";
             }
             if(agreementType == 22102  ){
            	 remark += "协议类型为变更";
             }
             if(agreementType == 22103 ){
            	 remark += "协议类型为续签";
             }
             String dataXml =
                     "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                             + "<summary id=\"-2125072970207116724\" name=\"formmain_3976\"/>" + "<definitions/>" + "<values>"
                             + "<column name=\"所属板块\"><value>"
                             + "房产交易"
                             + "</value></column>"
                             + "<column name=\"所属板块ID\"><value>"
                             + "FCJY"
                             + "</value></column>"
                             + "<column name=\"表单编码\"><value>"
                             + oaNo
                             + "</value></column>"
                             + "<column name=\"核算主体\"><value>"
                             + accountMain
                             + "</value></column>"
                             + "<column name=\"核算主体编码\"><value>"
                             + accountNo
                             + "</value></column>"
                             + "<column name=\"考核主体\"><value>"
                             + ""
                             + "</value></column>"
                             + "<column name=\"考核主体编码\"><value>"
                             + ""
                             + "</value></column>"
                             + "<column name=\"成本中心\"><value>"
                             + "联动部"
                             + "</value></column>"
                             + "<column name=\"成本中心编码\"><value>"
                             + "47016"
                             + "</value></column>"
                             + "<column name=\"框架合同\"><value>"
                             + "是"
                             + "</value></column>"
                             + "<column name=\"我方经办人\"><value>"
                             + sendUserName
                             + "</value></column>"
                             + "<column name=\"我方经办人工号\"><value>"
                             + sendUserCode
                             + "</value></column>"
                             + "<column name=\"合同名称\"><value>"
                             + "联动框架合同"
                             + "</value></column>"
                             + "<column name=\"经纪公司\"><value>"
                             + XmlTransferUtil.transfer(frameContractDto.getCompanyName())
                             + "</value></column>"
                             + "<column name=\"统一信用代码\"><value>"
                             + frameContractDto.getBusinessLicenseNo()
                             + "</value></column>"
                             + "<column name=\"经纪公司注册地址\"><value>"
                             + XmlTransferUtil.transfer(frameContractDto.getCityNm()+frameContractDto.getDistrictNm()+frameContractDto.getAddress())
                             + "</value></column>"
                             + "<column name=\"法定代表人\"><value>"
                             + frameContractDto.getLegalPerson()
                             + "</value></column>"
                             + "<column name=\"经纪公司联系人\"><value>"
                             + frameContractDto.getPartyBNm()
                             + "</value></column>"
                             + "<column name=\"经纪公司联系方式\"><value>"
                             + frameContractDto.getPartyBTel()
                             + "</value></column>"
                             + "<column name=\"是否有合同\"><value>"
                             + "是"
                             + "</value></column>"
                             + "<column name=\"合同总金额\"><value>"
                             + "0"
                             + "</value></column>"
                             + "<column name=\"费用类别\"><value>"
                             + "主营业务成本-新房联动成本(交易服务)"
                             + "</value></column>"
                             + "<column name=\"费用类别编号\"><value>"
                             + "38378"
                             + "</value></column>"
                             + "<column name=\"合同签订日期\"><value>"
                             + DateUtil.fmtDate(frameContractDto.getSignDate(), "yyyy-MM-dd")
                             + "</value></column>"
                             + "<column name=\"合同生效日期\"><value>"
                             + DateUtil.fmtDate(frameContractDto.getDateLifeStart(), "yyyy-MM-dd")
                             + "</value></column>"
                             + "<column name=\"合同截止日期\"><value>"
                             + DateUtil.fmtDate(frameContractDto.getDateLifeEnd(), "yyyy-MM-dd")
                             + "</value></column>"
                             + "<column name=\"供应商ID\"><value>"
                             + vendorId
                             + "</value></column>"
                             + "<column name=\"供应商属性\"><value>"
                             + "企业法人"
                             + "</value></column>"
                             + "<column name=\"供应商分类ID\"><value>"
                             + "22005"
                             + "</value></column>"
                             + "<column name=\"合同说明\"><value>"
                             + XmlTransferUtil.transfer(frameContractDto.getContractNote())
                             + "</value></column>"
                             + "<column name=\"双方盖章合同扫描件\"><value>"
                             + ""
                             + "</value></column>"
                             + "<column name=\"备注\"><value>"
                             + remark
                             + "</value></column>"
							 + "<column name=\"银行所在省份\"><value>"
							 + frameContractDto.getAccountProvinceNm()
							 + "</value></column>"
							 + "<column name=\"银行账号\"><value>"
							 + frameContractDto.getBankAccount()
							 + "</value></column>"
							 + "<column name=\"开户银行全称\"><value>"
							 + frameContractDto.getBankAccountNm()
							 + "</value></column>"
							 + "<column name=\"经纪公司编号\"><value>"
							 + frameContractDto.getCompanyNo()
							 + "</value></column>"
							 + "<column name=\"银行所在城市\"><value>"
							 + frameContractDto.getAccountCityNm()
							 + "</value></column>"
							 + "<column name=\"是否自动续签\"><value>"
							 + frameContractDto.getReAgreeFlagVal()
							 + "</value></column>"
                             + "</values></formExport>";

             Map<String, Object> sendOaData = new HashMap<>();



             // 模板编号
             sendOaData.put("templateCode", oaTemplateCode);
             // 发起者的登录名（登录协同的登录名）
             sendOaData.put("senderLoginName", sendUserCode);
             // 协同的标题
             sendOaData.put("subject", "新房联动框架合同");

             // 附件，Long型数组，值为附件的Id。
             sendOaData.put("attachments", attachList);
             sendOaData.put("data", dataXml);
             // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
             sendOaData.put("state", "0");

			 // 取得REST动态客户机实例
			 CTPRestClient client = getClient();
			 String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
			 //为登录验证后获取的身份令牌
			 sendOaData.put("token", token);

             //发起申请 返回FlowId
			 Long flowId = null;
			 try {
				 flowId = apiOaService.toOaAuth(sendOaData, oaTemplateCode);
			 }catch (Exception e){
			 	throw new Exception("提交OA失败。请确认OA中是否有联动框架合同表单的发起权限，没有则需上IT任务协办单申请。");
			 }

             if (flowId == null) {
                 throw new Exception("oa 返回flowId 为空");
             }

             //提交OA状态为提交成功
             FrameContract successContract = new FrameContract();
             successContract.setId(contractId);
             successContract.setSubmitOAUserId(Integer.valueOf(param.get("userId")));
             successContract.setSubmitTime(new Date());
             successContract.setSubmitOAStatus(21203);
             successContract.setApproveState(10402);
             successContract.setFlowId(flowId+"");
             successContract.setOaNo(oaNo);
             frameContractMapper.updateByPrimaryKeySelective(successContract);
         }catch (Exception e){
             //提交OA状态为提交失败
             FrameContract eContract = new FrameContract();
             eContract.setId(Integer.valueOf(param.get("contractId")));
             eContract.setSubmitOAUserId(Integer.valueOf(param.get("userId")));
             eContract.setSubmitTime(new Date());
             eContract.setApproveState(10401);
             eContract.setSubmitOAStatus(21204);
             eContract.setOaNo(oaNo);
             frameContractMapper.updateByPrimaryKeySelective(eContract);

             resultData.setFail(e.getMessage());
             logger.error("frameContract", "FrameContractService", "submitToOA", JsonUtil.parseToJson(param), 0, "", "框架合同提交OA异常", e);
         }

         return resultData;
     }

	private CTPRestClient getClient() {
		// 指定协议、IP和端口，获取ClientManager
		CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance(SystemParam.getWebConfigValue("oaUrl"));
		// 取得REST动态客户机实例
		CTPRestClient client = clientManager.getRestClient();
		return client;
	}

    private JSONObject checkVendorInfo(FrameContractDto frameContractDto) throws Exception {
        if(StringUtil.isEmpty(frameContractDto.getBusinessLicenseNo())|| StringUtil.isEmpty(frameContractDto.getCompanyName())){
            logger.error("frameContract", "FrameContractService", "submitToOA", JsonUtil.parseToJson(frameContractDto), 0, "", "框架合同提交OA异常", new Exception("统一社会代码或公司名称为空"));
            return null;
        }

        String url = SystemParam.getWebConfigValue("ebs_formal");
        String oaParam = "{\"permitCode\":\""+frameContractDto.getBusinessLicenseNo()+"\",\"vendorName\":\""+frameContractDto.getCompanyName()+"\"}";
        String jsonStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap12:Body>"
                + "<GetEBSData xmlns=\"http://tempuri.org/\">"
                + "<key>3F8118A3-330A-4A11-B422-C110E28F31C5</key>"
                + "<type>checkVendorInfo</type>"
                + "<param>"+oaParam+"</param>"
                + "</GetEBSData>"
                + "</soap12:Body>"
                + "</soap12:Envelope>";

        String validResult = apiOaService.post(url,jsonStr);

        //多出来 的xml
        if(validResult.indexOf("<?xml") > 0){
            validResult = validResult.substring(0,validResult.indexOf("<?xml"));
        }

        if(StringUtil.isEmpty(validResult)){
            logger.error("frameContract", "FrameContractService", "submitToOA", JsonUtil.parseToJson(frameContractDto), 0, "", "框架合同提交OA异常", new Exception("校验OA供应商信息异常，返回值为空"));
            return null;
        }

        JSONObject jsonObject = JSONObject.parseObject(validResult);

        return jsonObject;
    }

    /**
     * 获取事业部区域
     *
     * @return
     */
    private String getBussineArea(String cityNo,String userCode)
    {
        // 事业部区域
        String bussineArea = null;
        // 查询是否是经办人
        OaOperator oaOperator = null;
        try
        {
            oaOperator = oaOperatorService.getByUserCode(userCode);
        }
        catch (Exception e)
        {
            logger.error("submitToOA", "FrameContractService", "getBussineArea", "cityNo:"+cityNo+",userCode:"+userCode, null, "", "", e);
        }

        if (null != oaOperator)
        {
            // （取经办人的事业部区域，如果是跨区的，取其选择的区域）
            // 暂时不考虑跨区的情况，后期有需求再改
            Boolean isCombine = oaOperator.getIsCombine();
            if (isCombine)
            {
                bussineArea = strToList(cityNo,oaOperator.getBusCode());
            }
            else
            {
                bussineArea = oaOperator.getBusCode();
            }
        }
        return bussineArea;
    }

    private String strToList(String cityNo,String str)
    {
        String rtnCode="";
        if (StringUtil.isNotEmpty(str))
        {
            String[] strArr = str.split("\\|");
            if (null != strArr && 0 != strArr.length)
            {

                for (String subArr : strArr)
                {
                    try {
                        ResultData<?> resultData = commonCodeService.queryByDicCode(subArr);
                        CommonCodeDto dto = (CommonCodeDto)resultData.getReturnData();
                        if(null != dto)
                        {
                            if(dto.getDicGroup().equals(cityNo))
                            {
                                rtnCode = SystemParam.getWebConfigValue(subArr);
                                break;
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        }

        return rtnCode;
    }

    private void getOaAttachment(List<Long> attachList, int receiveId, Integer fileTypeCode, String userCode,String preName) throws Exception {
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

                String fileFullName = preName+"_"+fileMain.getFileFullName();
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
    /**
	 * 取得OA审批结果信息
	 * @return OA审批结果信息
	 * @throws Exception
	 */
    public List<OaLnkFrameContractReturn>getOaLnkFrameContractReturn()throws Exception{
        // 查询操作
        final List<OaLnkFrameContractReturn> oaResult = oaLnkFrameContractReturnMapper.getOaLnkFrameContractReturn();
        return oaResult;
    }
    /**
     * 更新OA审批结果
     * @param oaLnkFrameContractReturn
     * @throws Exception
     */
    public Integer updateOaLnkFrameContractReturn(OaLnkFrameContractReturn oaLnkFrameContractReturn) throws Exception{
    	Integer rtn = oaLnkFrameContractReturnMapper.updateOaLnkFrameContractReturn(oaLnkFrameContractReturn);
    	return rtn;
    }

    public FrameContract getByFlowId(String flowId)throws Exception{
    	FrameContract frameContract = frameContractMapper.getByFlowId(flowId);
        return frameContract;
    }

    /**
     *  根据公司ID查询合同状态为未签，审核未通过的合同
    */
   public ResultData getFrameContractByCompanyNo(String companyNo)throws Exception   {
       //构建返回
       ResultData resultData = new ResultData();
       resultData.setFail();
       List<FrameContractDto> frameContractList = frameContractMapper.getFrameContractByCompanyNo(companyNo);
       resultData.setReturnData(frameContractList);
       resultData.setSuccess();
       return resultData;
   }
   /**
    * @Description: 更新框架协议公司信息
    */
    public ResultData updateCompanyInfo(String param)throws Exception {
	   	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
	   	Integer resultRow	=	null;
	   	ResultData<Integer> resultData = new ResultData<Integer>();
		resultData.setFail();
		try {
			resultRow = frameContractMapper.updateCompanyInfo(queryParam);
		}catch(Exception e){
			logger.error("[pmlsService invoke updateCompanyInfo.updateStr error...]");
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
     * 查询登陆人的核算主体
     * @param companyNo
     * @return
     * @throws Exception
     */
    public ResultData getUserMappingAccountProject(String userCode) throws Exception {
     	//构建返回
     	ResultData resultData = new ResultData();
     	resultData.setFail();
     	List<FrameAccountProjectMappingDto> accountProjectList = frameContractMapper.getUserMappingAccountProject(userCode);
     	if(accountProjectList != null && accountProjectList.size()>0){
     		resultData.setReturnData(accountProjectList);
     		resultData.setSuccess();
     	}
     	return resultData;
     }
    /**
     * 查询登陆人所在城市的核算主体
     * @param companyNo
     * @return
     * @throws Exception
     */
    public ResultData getLoginCityAccountProject(String cityNo) throws Exception {
    	//构建返回
    	ResultData resultData = new ResultData();
    	resultData.setFail();
    	List<FrameContractDto> accountProjectList = frameContractMapper.queryAccountProject(cityNo);
    	if(accountProjectList != null && accountProjectList.size()>0){
    		resultData.setReturnData(accountProjectList.get(0));
    	}else{
    		resultData.setReturnData(null);
    	}
    	resultData.setSuccess();
    	return resultData;
    }

    public List<FrameContract> selectNewestContractByCompanyId(Integer companyId) {
    	return  frameContractMapper.selectNewestContractByCompanyId(companyId);
    }
    /**
     * 运营变更合同状态
     * @param param
     * @return
     */

    public int operateChangeCt(Map<String, Object> queryParam) throws Exception {
    	int count = 0;
    	if(queryParam.containsKey("id")){
    		//变更日期
    		queryParam.put("dateUpt", new Date());
    		// 合同状态变为审核不通过
    		queryParam.put("approveState", DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS);
    		count = frameContractMapper.updateStr(queryParam).intValue();
    	}
        // 更新合同状态
        if(count>0){
        	frameContractMapper.insertFrameContractReturn(queryParam);
        }
        return count;
    }
    /**
     * 根据公司编号获取最新审核通过的框架合同的银行相关信息
     * @return
     */
    public ResultData<?> getOldFtBankInfo(Map<?, ?> param) {
		ResultData resultData = new ResultData();
		FrameContractDto oldFtBankInfo = frameContractMapper.getOldFtBankInfo(param);
		if(null != oldFtBankInfo){
			resultData.setReturnData(oldFtBankInfo);
			resultData.setSuccess();
			return resultData;
		}
		resultData.setReturnData(null);
		return resultData;
	}

    public void spqdAutoCreateFromeContract(Integer contractId){

    	 if(contractId!=null && contractId.intValue()>0){
    		 String frameContractNo = seqNoAPI.getSeqNoByTypeCode("TYPE_HTLD").getReturnData();
    		 Map<String, Object>  map = new HashMap<String, Object>();
    		 map.put("lnkContractNo", frameContractNo);
    		 map.put("contractId", contractId);
    		 //创建框架合同
    		 frameContractMapper.insertFromSpContract(map);
    		 //补文件数据
    		 frameContractMapper.insertFromSpContractFile(map);
    	 }
    }

    /**
     * 框架合同提交OA
     * @param param
     * @return
     * @throws Exception
     */
     public ResultData<String> AutoToOA() throws Exception{

         ResultData<String> resultData = new ResultData<>();

         List<FrameContractDto> submitList= frameContractMapper.getBriefList();


         if(submitList!=null && submitList.size()>0){
        	 for(FrameContractDto subVo:submitList){

                 Integer contractId=subVo.getId();
        		 ResultData<String> data2 = seqNoAPI.getSeqNoByTypeCode("TYPE_FRAMECTOA");
                 String oaNo = "";
                 if (data2 != null && data2.getReturnCode().equals("200")) {
              	   oaNo = data2.getReturnData();
                 }


                 try {
		                 //提交OA状态为处理中
		                 FrameContract contract = new FrameContract();
		                 contract.setId(contractId);
		                 contract.setSubmitOAUserId(2221);
		                 contract.setSubmitTime(new Date());
		                 contract.setSubmitOAStatus(21202);
		                 frameContractMapper.updateByPrimaryKeySelective(contract);


//		                 if(StringUtil.isEmpty(subVo.getAccountProvinceNm())||StringUtil.isEmpty(subVo.getAccountCityNm())){
//		                	 errorBack(contractId, 2221, "查询开户省市信息不完整", oaNo);
//		                	 continue;
//		    			 }

		                 //校验供应商信息
		                 JSONObject jsonObject = checkVendorInfo(subVo);

		                 if("F".equals(jsonObject.get("state").toString())){
		                     errorBack(contractId, 2221, "经纪公司名称或统一社会信用代码与OA中供应商信息不一致，请先在OA中做供应商信息变更。", oaNo);
		                	 continue;
		                 }
		                 if (jsonObject == null) {
		                	 errorBack(contractId, 2221, "检验供应商时出现错误", oaNo);
		                	 continue;
		                 }


		                 String vendorId = "";
		                 JSONArray data = (JSONArray) jsonObject.get("data");
		                 if(data != null && data.size() > 0){
		                     JSONObject object = (JSONObject)data.get(0);
		                     vendorId = object.get("VENDOR_ID").toString();
		                 }

		                 String accountNo = subVo.getAccountProjectCode();
		                 String accountMain = subVo.getAccountProject();


		      		  // 框架合同OA模板
		                Map<String, Object> citySetting = citySettingMapper.getCitySettingByCityNo(subVo.getCityNo());
		                String oaTemplateCode = "";
		                String sendUserCode ="";
		                String sendUserName ="";
		                if(citySetting != null && citySetting.get("frameContractAutoTpl") != null){
		                    oaTemplateCode  = citySetting.get("frameContractAutoTpl").toString();
		                }else{
		                	 errorBack(contractId, 2221, "根据cityNo:"+subVo.getCityNo()+" 查询框架合同oa模板编号异常", oaNo);
		                	 continue;
		                }

		                if(citySetting != null && citySetting.get("frameContractAutoSendUserCode") != null){
		                	sendUserCode  = citySetting.get("frameContractAutoSendUserCode").toString();
		                }else{
		                	 errorBack(contractId, 2221, "根据cityNo:"+subVo.getCityNo()+" 查询框架合同自动发起人工号异常", oaNo);
		                	 continue;
		                }
		                if(citySetting != null && citySetting.get("frameContractAutoSendUserName") != null){
		                	sendUserName  = citySetting.get("frameContractAutoSendUserName").toString();
		                }else{
		                	 errorBack(contractId, 2221, "根据cityNo:"+subVo.getCityNo()+" 查询框架合同自动发起人姓名异常", oaNo);
		                	 continue;
		                }
		                  //附件提交OA
		                  List<Long> attachList = new ArrayList<>();
		                  // 联动框架合同-营业执照
		                  this.getOaAttachment(attachList, subVo.getId(), 1028, sendUserCode, "营业执照");
		                  // 联动框架合同-合同
		                  this.getOaAttachment(attachList, subVo.getId(), 1029, sendUserCode, "合同");
		                  // 联动框架合同-其它
		                  this.getOaAttachment(attachList, subVo.getId(), 1030, sendUserCode, "其它");
		                  //获取框架协议类型
		                  Integer agreementType = subVo.getAgreementType();
		                  String remark ="";
		                  if(StringUtil.isNotEmpty(subVo.getRemark())){
		                 	remark += XmlTransferUtil.transfer(subVo.getRemark())+"\r\n";
		                  }
		                  if(agreementType == 22102  ){
		                 	 remark += "协议类型为变更";
		                  }
		                  if(agreementType == 22103 ){
		                 	 remark += "协议类型为续签";
		                  }
		                  String dataXml =
		                          "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
		                                  + "<summary id=\"-2125072970207116724\" name=\"formmain_3976\"/>" + "<definitions/>" + "<values>"
		                                  + "<column name=\"所属板块\"><value>"
		                                  + "房产交易"
		                                  + "</value></column>"
		                                  + "<column name=\"所属板块ID\"><value>"
		                                  + "FCJY"
		                                  + "</value></column>"
		                                  + "<column name=\"表单编码\"><value>"
		                                  + oaNo
		                                  + "</value></column>"
		                                  + "<column name=\"核算主体\"><value>"
		                                  + accountMain
		                                  + "</value></column>"
		                                  + "<column name=\"核算主体编码\"><value>"
		                                  + accountNo
		                                  + "</value></column>"
		                                  + "<column name=\"考核主体\"><value>"
		                                  + ""
		                                  + "</value></column>"
		                                  + "<column name=\"考核主体编码\"><value>"
		                                  + ""
		                                  + "</value></column>"
		                                  + "<column name=\"成本中心\"><value>"
		                                  + "联动部"
		                                  + "</value></column>"
		                                  + "<column name=\"成本中心编码\"><value>"
		                                  + "47016"
		                                  + "</value></column>"
		                                  + "<column name=\"框架合同\"><value>"
		                                  + "是"
		                                  + "</value></column>"
		                                  + "<column name=\"我方经办人\"><value>"
		                                  + sendUserName
		                                  + "</value></column>"
		                                  + "<column name=\"我方经办人工号\"><value>"
		                                  + sendUserCode
		                                  + "</value></column>"
		                                  + "<column name=\"合同名称\"><value>"
		                                  + "联动框架合同"
		                                  + "</value></column>"
		                                  + "<column name=\"经纪公司\"><value>"
		                                  + XmlTransferUtil.transfer(subVo.getCompanyName())
		                                  + "</value></column>"
		                                  + "<column name=\"统一信用代码\"><value>"
		                                  + subVo.getBusinessLicenseNo()
		                                  + "</value></column>"
		                                  + "<column name=\"经纪公司注册地址\"><value>"
		                                  + XmlTransferUtil.transfer(subVo.getCityNm()+subVo.getDistrictNm()+subVo.getAddress())
		                                  + "</value></column>"
		                                  + "<column name=\"法定代表人\"><value>"
		                                  + subVo.getLegalPerson()
		                                  + "</value></column>"
		                                  + "<column name=\"经纪公司联系人\"><value>"
		                                  + subVo.getPartyBNm()
		                                  + "</value></column>"
		                                  + "<column name=\"经纪公司联系方式\"><value>"
		                                  + subVo.getPartyBTel()
		                                  + "</value></column>"
		                                  + "<column name=\"是否有合同\"><value>"
		                                  + "是"
		                                  + "</value></column>"
		                                  + "<column name=\"合同总金额\"><value>"
		                                  + "0"
		                                  + "</value></column>"
		                                  + "<column name=\"费用类别\"><value>"
		                                  + "主营业务成本-新房联动成本(交易服务)"
		                                  + "</value></column>"
		                                  + "<column name=\"费用类别编号\"><value>"
		                                  + "38378"
		                                  + "</value></column>"
		                                  + "<column name=\"合同签订日期\"><value>"
		                                  + DateUtil.fmtDate(subVo.getSignDate(), "yyyy-MM-dd")
		                                  + "</value></column>"
		                                  + "<column name=\"合同生效日期\"><value>"
		                                  + DateUtil.fmtDate(subVo.getDateLifeStart(), "yyyy-MM-dd")
		                                  + "</value></column>"
		                                  + "<column name=\"合同截止日期\"><value>"
		                                  + DateUtil.fmtDate(subVo.getDateLifeEnd(), "yyyy-MM-dd")
		                                  + "</value></column>"
		                                  + "<column name=\"供应商ID\"><value>"
		                                  + vendorId
		                                  + "</value></column>"
		                                  + "<column name=\"供应商属性\"><value>"
		                                  + "企业法人"
		                                  + "</value></column>"
		                                  + "<column name=\"供应商分类ID\"><value>"
		                                  + "22005"
		                                  + "</value></column>"
		                                  + "<column name=\"合同说明\"><value>"
		                                  + XmlTransferUtil.transfer(subVo.getContractNote())
		                                  + "</value></column>"
		                                  + "<column name=\"双方盖章合同扫描件\"><value>"
		                                  + ""
		                                  + "</value></column>"
		                                  + "<column name=\"备注\"><value>"
		                                  + remark
		                                  + "</value></column>"
		     							 + "<column name=\"银行所在省份\"><value>"
		     							 + subVo.getAccountProvinceNm()
		     							 + "</value></column>"
		     							 + "<column name=\"银行账号\"><value>"
		     							 + subVo.getBankAccount()
		     							 + "</value></column>"
		     							 + "<column name=\"开户银行全称\"><value>"
		     							 + subVo.getBankAccountNm()
		     							 + "</value></column>"
		     							 + "<column name=\"经纪公司编号\"><value>"
		     							 + subVo.getCompanyNo()
		     							 + "</value></column>"
		     							 + "<column name=\"银行所在城市\"><value>"
		     							 + subVo.getAccountCityNm()
		     							 + "</value></column>"
		     							 + "<column name=\"是否自动续签\"><value>"
		     							 + subVo.getReAgreeFlagVal()
		     							 + "</value></column>"
		                                  + "</values></formExport>";

			                  Map<String, Object> sendOaData = new HashMap<>();
			                  // 模板编号
			                  sendOaData.put("templateCode", oaTemplateCode);
			                  // 发起者的登录名（登录协同的登录名）
			                  sendOaData.put("senderLoginName", sendUserCode);
			                  // 协同的标题
			                  sendOaData.put("subject", "新房联动框架合同");

			                  // 附件，Long型数组，值为附件的Id。
			                  sendOaData.put("attachments", attachList);
			                  sendOaData.put("data", dataXml);
			                  // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
			                  sendOaData.put("state", "0");

							 // 取得REST动态客户机实例
							 CTPRestClient client = getClient();
							 String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
							 //为登录验证后获取的身份令牌
							 sendOaData.put("token", token);

			                  //发起申请 返回FlowId
			     			 Long flowId = null;
			     			 try {
			     				 flowId = apiOaService.toOaAuth(sendOaData, oaTemplateCode);
			     			 }catch (Exception e){
			     				 errorBack(contractId, 2221, "提交OA失败。请确认OA中是否有联动框架合同表单的发起权限，没有则需上IT任务协办单申请", oaNo);
			                	 continue;
			     			 }

			                  if (flowId == null) {
			                	  errorBack(contractId, 2221, "oa 返回flowId 为空", oaNo);
			                 	 continue;
			                  }

			                  //提交OA状态为提交成功
			                  FrameContract successContract = new FrameContract();
			                  successContract.setId(contractId);
			                  successContract.setSubmitOAUserId(2221);
			                  successContract.setSubmitTime(new Date());
			                  successContract.setSubmitOAStatus(21203);
			                  successContract.setApproveState(10402);
			                  successContract.setFlowId(flowId+"");
			                  successContract.setOaNo(oaNo);
			                  frameContractMapper.updateByPrimaryKeySelective(successContract);
                 	}catch (Exception e){
                 		errorBack(contractId,2221,"框架合同提交OA异常",oaNo);
                 		//resultData.setFail(e.getMessage());
                 		logger.error("frameContract", "FrameContractService", "submitToOA", "", 0, "", "框架合同提交OA异常", e);
                 	}
        	 }
         }
         resultData.setSuccess();
         return resultData;
     }

     public void errorBack (Integer id, Integer UserId,String  errorMessge, String oaNo) {
    	 FrameContract eContract = new FrameContract();
         eContract.setId(id);
         eContract.setSubmitOAUserId(UserId);
         eContract.setSubmitTime(new Date());
         eContract.setApproveState(10401);
         eContract.setSubmitOAStatus(21204);
         eContract.setOaNo(oaNo);
         frameContractMapper.updateByPrimaryKeySelective(eContract);

         LogFrameContract logVo = new LogFrameContract();
         logVo.setFrameContractId(id);
         logVo.setOperation(errorMessge);
         logVo.setUserIdCreate(UserId);
         logFrameContractMapper.insert(logVo);
	}

	//查询渠道开户行信息列表
	public ResultData getBankInfoList(Map<String,Object> reqMap)throws Exception{
		ResultData resultData = new ResultData();
		resultData.setFail();
		List<FrameContractDto> frameContractList =  frameContractMapper.getBankInfoList(reqMap);
		if(frameContractList !=null && frameContractList.size()>0){
			resultData.setReturnData(frameContractList);
			resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
			resultData.setSuccess();
		}else{
			resultData.setSuccess();
		}
		return resultData;
	}
	//删除对公账户信息
	@Transactional(rollbackFor=Exception.class)
	public ResultData deleteBankInfo(Map<String,Object> reqMap) throws Exception{
		ResultData resultData = new ResultData();
		int count = frameContractMapper.deleteBankInfo(reqMap);
		if(count>0){
			resultData.setSuccess("操作成功");
		}else{
			resultData.setFail("操作失败");
		}
		return resultData;
	}


}
