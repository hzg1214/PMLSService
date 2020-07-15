package cn.com.eju.deal.api.contractChange.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.api.contractChange.mapper.APIContractChangeMapper;
import cn.com.eju.deal.api.contractChange.model.AchievementCancelEdDto;
import cn.com.eju.deal.api.contractChange.model.CenterSetting;
import cn.com.eju.deal.api.contractChange.model.ContractChangeNewDto;
import cn.com.eju.deal.api.contractChange.model.OaProcessDto;
import cn.com.eju.deal.base.code.dao.CommonCodeMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.XmlTransferUtil;
import cn.com.eju.deal.contract.dao.AchievementCancelMapper;
import cn.com.eju.deal.contract.dao.AchievementCancelMappingMapper;
import cn.com.eju.deal.contract.dao.ContractChangeMapper;
import cn.com.eju.deal.contract.dao.ContractChangeStoreMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.model.ContractChangeStore;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.AchievementCancelDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.oa.dao.OaOperatorMapper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * service层 合同撤销
 */
@Service("apiContractChangeService")
public class APIContractChangeService{
	//表单数据Map key
    private final static String XML_DATA_KEY = "xmlData";
    //日期
    private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";
    @Resource
    private APIContractChangeMapper apiContractChangeMapper;
    
    @Resource
    private AchievementCancelMapper achievementCancelMapper;
    
    @Resource
    private ContractMapper contractMapper;
    
    @Resource
    private CitySettingMapper citySettingMapper;
    
    @Resource
    private AchievementCancelMappingMapper achievementCancelMappingMapper;
    @Resource
    private ContractStoreMapper contractStoreMapper;
    
    @Resource
	private ContractChangeMapper contractChangeMapper;
    
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    
    @Resource
	private ContractChangeStoreMapper contractChangeStoreMapper;
    
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    
    /**
     * 合同撤销列表
     * @param reqMap
     * @return
     */
    public ResultData<List<ContractChangeNewDto>> getContractList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<ContractChangeNewDto>> resultData = new ResultData<List<ContractChangeNewDto>>();
        resultData.setFail();
        Boolean isOperator = true;
//        //判断是否为经办人，非经办人不能查看
//        OaOperator oaOperator = oaOperatorMapper.getByUserCode(reqMap.get("userCode").toString());
//        if(null == oaOperator|| ("").equals(oaOperator.getOperId())){
//        	isOperator = false;
//        }
        if(isOperator) {
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
        	List<ContractChangeNewDto> changeList = apiContractChangeMapper.getContractList(reqMap);
        	resultData.setTotalCount("0");
        	if (null != changeList && changeList.size() > 0) {
        		resultData.setTotalCount(changeList.get(0).getDataCount()+"");
        		int endRow = pageIndex * pageSize;
        		changeList = changeList.subList((pageIndex - 1) * pageSize, endRow > changeList.size() ? changeList.size() : endRow);
        		reqMap.put("pageIndex", pageIndex);
        		reqMap.put("pageSize", pageSize);
        		reqMap.put("curPage", curPage);
        		resultData.setReturnData(changeList);
        		resultData.setSuccess();
        	} 
        }else {
    		resultData.setReturnData(null);
    		resultData.setReturnMsg("不是经办人无法查询");
    	}
        return resultData;
    }
    /**
     * 撤销合同提交页面（根据合同id查）
     * @param map
     * @return
     */
    public ResultData<ContractChangeNewDto> getContractChangeInfo(Map<String, Object> reqMap) throws Exception{
        ResultData<ContractChangeNewDto> resultData = new  ResultData<ContractChangeNewDto>();
//        OaOperator oaOperator = oaOperatorMapper.getByUserCode(reqMap.get("userCode").toString());
//        Boolean isCombine = true;
//        String busCodeByDicCode = "";
//        if(null != oaOperator){
//        	isCombine = oaOperator.getIsCombine();
//        	if(isCombine) {
//        		String busCode = oaOperator.getBusCode();
//        		//获取事业部区域
//        		busCodeByDicCode = getBusCodeByDicCode(reqMap, busCode);
//        		
//        	}
//        }
        ContractChangeNewDto contractChangeNewDto  = apiContractChangeMapper.getContractChangeInfo(reqMap);
       // 经办人是否跨区
       // contractChangeNewDto.setIsCombine(isCombine);
        contractChangeNewDto.setIsCombine(true);
        //事业部信息
//        if(!"".equals(busCodeByDicCode)) {
//        	contractChangeNewDto.setBusNo(busCodeByDicCode);
//		}
        contractChangeNewDto.setBusNo("-1");
        //公司详细地址
        String cityNameCompany = SystemParam.getCityNameByCityNo(contractChangeNewDto.getPartyBCityNo());
        String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(contractChangeNewDto.getPartyBDistrictNo());
        String partyAddressDetail = cityNameCompany + districtNameCompany + contractChangeNewDto.getPartyBAddress();
        contractChangeNewDto.setPartyAddressDetail(partyAddressDetail);
        resultData.setReturnData(contractChangeNewDto);
        return resultData;
    }
//    /**
//     * 根据事业部区域
//     * @param reqMap
//     * @throws Exception
//     */
//	private String getBusCodeByDicCode(Map<String, Object> reqMap, String busCode) throws Exception {
//		String rtnCode="";
//		if (StringUtil.isNotEmpty(busCode)) {
//			String[] strArr = busCode.split("\\|");
//			if(null != strArr && 0 != strArr.length) {
//				String cityNo = reqMap.get("cityNo").toString();
//				 for (String subArr : strArr) {
//					 final Code code = commonCodeMapper.queryByDicCode(subArr);
//					 if(null != code) {
//						if(cityNo.equals(code.getDicGroup())) {
//							rtnCode = SystemParam.getWebConfigValue(subArr);
//							break;
//						}
//					 }
//				 }
//			}
//		}
//		return rtnCode;
//	}
    /**
     * 验证所选的门店是否可以撤销
     * @return
     * @throws Exception
     */
    public ResultData checkContractChange(Map<String, Object> queryParam) throws Exception{
        ResultData resultData = new ResultData();
        String mesg = "";
        if(queryParam.containsKey("contractId")) {
    		//根据合同id查询其中心id
    		CenterSetting centerSetting =apiContractChangeMapper.getSenderLoginNameAndCode(Integer.valueOf(queryParam.get("contractId").toString()));
    		if(null != centerSetting) {
    			if(queryParam.containsKey("storeIds")) {
    				String str = (String)queryParam.get("storeIds");
    				String[] storeIds = str.split(",");
    				Map<String,Object> reqMap = new HashMap<String, Object>();
    				reqMap.put("storeIdList", storeIds);
    				
    				String contractId = (String)queryParam.get("contractId");
    				reqMap.put("contractId", Integer.valueOf(contractId));
    				//根据门店ids查询最新非撤销合同Id
    				List<ContractDto> conList = contractMapper.getTopOneContract(reqMap);
    				if( null != conList && conList.size() >0){
    					for(ContractDto con : conList){
    						String id = con.getId() +"";
    						if(!contractId.equals(id)){
    							mesg = "所撤销门店"+con.getStoreNo()+"已有新签合同,请在新合同中撤销！新签合同编号:"+con.getContractNo();
    							resultData.setFail(mesg);
    							return resultData;
    						}
    					}
    				}
    				//判断装修记录
    				List<StoreDecorationDto> list = achievementCancelMapper.getStoreDecorationList(reqMap);
    				if(null != list && list.size() > 0) {
    					String mesgShz = "";
    					String mesgFyw = "";
    					String mesgn = "";
    					String mesgDh = "";
    					for (StoreDecorationDto storeDecorationDto : list) {
    						String storeNo = storeDecorationDto.getStoreNo();
    						String storeName = storeDecorationDto.getStoreName();
    						String decorationStatus = storeDecorationDto.getDecorationStatus() +"";
    						String oaMdfpStatus = storeDecorationDto.getOaMdfpStatus() +"";
    						if("16301".equals(decorationStatus) && "16502".equals(oaMdfpStatus)){
    							if(!"".equals(mesgShz)){
    								mesgDh = ","; 	    		
    							}
    							mesgShz = mesgShz+mesgDh+storeNo + ":" + storeName;
    						}
    						// 装修状态!=未装修 && 装修状态!=翻拍验收完成
    						if(!"16301".equals(decorationStatus) &&  !"16304".equals(decorationStatus)){
    							if(!"".equals(mesgFyw)){
    								mesgDh = ","; 		    		
    							}
    							mesgFyw = mesgFyw+mesgDh+storeNo + ":" + storeName;
    						}
    					}
    					if(!"".equals(mesgShz)){
    						mesgShz = "【" +mesgShz +"】"+ "门店翻牌申请状态为审核中，不允许合同撤销！";
    						mesgn = "<br/>";
    						
    					}
    					if(!"".equals(mesgFyw)){     
    						mesgFyw = mesgn + "【" +mesgFyw +"】"+ "翻牌验收完成后才能做合同撤销操作！";      
    						
    					}
    					mesg = mesgShz +mesgFyw;
    					if(!"".equals(mesg)) {
    						resultData.setReturnData(0);
    						resultData.setFail();
    						resultData.setReturnMsg(mesg);
    						return resultData;
    					}
    				}
    				//判断是否存在合同变更申请
    				Map<String, Object> checkMap = new HashMap<>();
    				checkMap.put("contractId", Integer.valueOf(contractId));
    				String alertMessage = "";
                    for(int i = 0; i < storeIds.length;i++){
                        Integer storeId =Integer.valueOf(storeIds[i]);
                        checkMap.put("storeId", storeId);
                        final Map checkMapInfoMap = apiContractChangeMapper.checkStoreApplyInfo(checkMap);
                        if(null != checkMapInfoMap){
                        	String approveState = checkMapInfoMap.get("approveState").toString();
                        	if("1".equals(approveState)){
                        		//存在合同变更申请
                        		String storeNo = checkMapInfoMap.get("storeNo").toString();
                        		alertMessage += "门店编号为:"+storeNo+"的门店存在合同变更申请，不允许撤销!";
                        	}
                        }
                        if(!"".equals(alertMessage)) {
                        	resultData.setReturnData(0);
                        	resultData.setFail();
                        	resultData.setReturnMsg(alertMessage);
                        	return resultData;
                        }
                    }
    			}
    		} else {
    			mesg = "申请人无发起撤销oa申请的权限!";
    			resultData.setFail(mesg);
				return resultData;
    		}
    	}
        return resultData;
    }
    /**
     * 门店撤销业绩申请
     * 	1.根据合同id获取模板
     * 	2.组装数据
     * 	3.发起申请
     * 	4.改变本地状态
     */
    public ResultData doSaveAndApplication(Map<String, Object> queryParam){
    	ResultData resultData = new ResultData();
    	String busCodeString="";
    	String oaTemplateCode = "";
    	String accountProjectCode="";
    	String accountProject = "";
    	if(queryParam.containsKey("acCityNo")) {
    		String acCityNo = queryParam.get("acCityNo").toString();
    		/**
    		 * 根据城市编号获取模板
    		 */
    		Map<String, Object> map = citySettingMapper.getCitySettingByCityNo(acCityNo);
    		if(null != map) {
    			oaTemplateCode = map.get("contractAchTpl").toString();
    			busCodeString=map.get("busCode").toString();
    			accountProjectCode=map.get("accountProjectCode").toString();
    			accountProject=map.get("accountProject").toString();
    		} else {
    			resultData.setFail();
    			resultData.setReturnMsg("根据城市编号获取模板失败");
    			return resultData;
    		}
    	}
    	/**
    	 * 组装数据发送
    	 */
    	// OAmap
        Map<String, Object> oaMap = new HashMap<String, Object>();
        queryParam.put("busNo", busCodeString);
        queryParam.put("accountProjectCode", accountProjectCode);
        queryParam.put("accountProject", accountProject);
        //撤销编号
    	//String achievementCancelNo = queryParam.get("achievementCancelNo").toString();
    	try {
			setToOaInfo(oaMap, queryParam, oaTemplateCode);
		} catch (Exception e1) {
			resultData.setFail();
			resultData.setReturnMsg("组装合同数据错误");
			return resultData;
		}
    	
    	//发起申请 返回FlowId
        Long flowId;
		try {
			flowId = this.toOaAuth(oaMap, oaTemplateCode);
			queryParam.put("flowId", flowId);
		} catch (Exception e) {
			resultData.setFail();
			resultData.setReturnMsg("向OA发起提交审核失败");
			return resultData;
		}
        
        /**
         * 本地更改数据库
         * 1.门店撤销记录，合同撤销表
         * 2.合同撤销门店关系表
         * 3.合同门店关系表，更新是否已撤销，解除关系
         */
        try {
			resultData = this.createNewRecord(queryParam,resultData);
			if(resultData.getReturnCode().equals(ReturnCode.FAILURE)) {
				return resultData;
			}
		} catch (Exception e) {
			resultData.setFail();
			resultData.setReturnMsg("更新合同门店相关表状态失败！");
			return resultData;
		}
    	return resultData;
    }
    /** 
     * 组装表单发起数据
     * @param id
     * @param oaMap 
     * @param reqMap  传入参数 
     * @param templateCode 模板
     * 
     */
    private void setToOaInfo(Map<String, Object> oaMap, Map<String, Object> reqMap, String templateCode) throws Exception{
    	
    	//生成的撤销编号
    	String achievementCancelNo = reqMap.get("achievementCancelNo").toString();
    	//发起者的登录名
    	if(reqMap.containsKey("contractId")) {
    		Integer contractId = Integer.valueOf(reqMap.get("contractId").toString());
    		//根据合同id查询其中心id
    		CenterSetting centerSetting =apiContractChangeMapper.getSenderLoginNameAndCode(contractId);
    		if(null != centerSetting) {
    			String mdcxSendUserCode = centerSetting.getMdcxSendUserCode();
    			String mdcxSendUserName = centerSetting.getMdcxSendUserName();
    			reqMap.put("mdcxSendUserCode", mdcxSendUserCode);
    			reqMap.put("mdcxSendUserName", mdcxSendUserName);
    		}
    	}
    	if(reqMap.containsKey("mdcxSendUserCode")){
    		String senderLoginName = reqMap.get("mdcxSendUserCode").toString();
    		oaMap.put("senderLoginName", senderLoginName);
    	}
    	
        //模板编号
        oaMap.put("templateCode", templateCode);
        //协同的标题
        oaMap.put("subject", "易居房产交易中介合作门店撤销申请单");
        
        //表单数据
        Map<String, Object> rspMap = setOaAuditData(achievementCancelNo, reqMap);
		//HTML正文流程为html内容；表单流程为XML格式的表单数据
		oaMap.put("data", rspMap.get(XML_DATA_KEY));
	
        
        
        //为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
        oaMap.put("state", "0");
    }
    /** 
     * 组装发送OA审核的 表单数据、附件数据
    * @param achievementCancelNo 
     * @param id
     * @return
     */
   private Map<String, Object> setOaAuditData(String achievementCancelNo, Map<String, Object> reqMap) throws Exception {
       
       //返回表单数据、返回附件数据
       Map<String, Object> rspMap = new HashMap<String, Object>();
       String dateHtml = "";
       String userCode = "";
       String userName = "";
       if(reqMap.containsKey("mdcxSendUserCode")){
    	   //申请人姓名
    	   userName = reqMap.get("mdcxSendUserName").toString();
    	   //申请人工号
    	   userCode = reqMap.get("mdcxSendUserCode").toString();
       }
       //创建时间
       Date date = new Date();
       //申请日期
       String applicationDate = DateUtil.fmtDate(date, YYYYMMDD_FORMAT);
       //撤销原因
       String cancelReason = (String)reqMap.get("cancelReason");
       //String isCombine = (String)reqMap.get("isCombine");
       //事业部编号
       String busNo = (String)reqMap.get("busNo");
       //获取事业部区域
//       String bussineArea = this.getBussineArea(busNo,userCode);
      String bussineArea =busNo;
       
//       String dicValue = SystemParam.getWebConfigValue(bussineArea);
//       String[] list = new String[0];
       String accountNo =  reqMap.get("accountProjectCode").toString();
       String accountMain =  reqMap.get("accountProject").toString();
//       if (StringUtils.isNotBlank(dicValue)) {
//           list = dicValue.split("\\|");
//           //核算主体编号
//           accountNo = list[0];
//           //核算主体名称
//           accountMain = list[1];
//       }
       //公司名称
       String companyName = (String)reqMap.get("companyName");
       //签约合同类型名称
       String contractTypeName = (String)reqMap.get("contractTypeName");
       
       AchievementCancelDto achievementCancelDto = null;
	   try {
			achievementCancelDto = getOAAuditInfo(reqMap, dateHtml);
	   } catch (Exception e) {
			e.printStackTrace();
	   }
       StringBuilder sb = new StringBuilder();
       String contractNo = "";
       String companyNo = "";
       if (null != achievementCancelDto) {
           //合同编号
           contractNo = achievementCancelDto.getContractNo();
           //公司编号
           companyNo = achievementCancelDto.getCompanyNo();
           //门店列表
           List<StoreDto> storelist = achievementCancelDto.getStorelist();
           if (null != storelist && storelist.size() > 0) {
               for (int i = 0; i < storelist.size(); i++){
            	   StoreDto storeDto = storelist.get(i);
                   String storeNo = storeDto.getStoreNo();
                   String storename =storeDto.getName();
                   String storeAddress = storeDto.getAddress();
                   String decorationStatusName = storeDto.getDecorationStatusName();
                   sb.append("<row>" + "<column name=\"门店店招\"><value>" + XmlTransferUtil.transfer(storename) + "</value></column>"
                       + "<column name=\"门店编号\"><value>" + XmlTransferUtil.transfer(storeNo) + "</value></column>"
                       + "<column name=\"门店地址\"><value>" + XmlTransferUtil.transfer(storeAddress) + "</value></column>"
                       + "<column name=\"装修进度\"><value>" + XmlTransferUtil.transfer(decorationStatusName) + "</value></column>" + "</row>");
               }
           }
       }
       
       //门店列表字串
       String businessAddressXml = sb.toString();
       dateHtml +=
           "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
               + "<summary id=\"2775493308299628457\" name=\"formmain_3984\"/>" + "<definitions/>" + "<values>"
               + "<column name=\"编码\">" + "<value>"
               + XmlTransferUtil.transfer(achievementCancelNo)
               + "</value>"
               + "</column>"
               + "<column name=\"申请人\">"
               + "<value>"
               + XmlTransferUtil.transfer(userName)
               + "</value>"
               + "</column>"
               + "<column name=\"工号\">"
               + "<value>"
               + XmlTransferUtil.transfer(userCode)
               + "</value>"
               + "</column>"
               + "<column name=\"申请日期\">"
               + "<value>"
               + XmlTransferUtil.transfer(applicationDate)
               + "</value>"
               + "</column>"
               + "<column name=\"核算主体\">"
               + "<value>"
               + XmlTransferUtil.transfer(accountMain)
               + "</value>"
               + "</column>"
               + "<column name=\"核算主体编码\">"
               + "<value>"
               + XmlTransferUtil.transfer(accountNo)
               + "</value>"
               + "</column>"
               + "<column name=\"事业部\">"
               + "<value>"
               + XmlTransferUtil.transfer(bussineArea)
               + "</value>"
               + "</column>"
               + "<column name=\"公司名称\">"
               + "<value>"
               + XmlTransferUtil.transfer(companyName)
               + "</value>"
               + "</column>"
               + "<column name=\"公司编码\">"
               + "<value>"
               + XmlTransferUtil.transfer(companyNo)
               + "</value>"
               + "</column>"
               + "<column name=\"签约类型\">"
               + "<value>"
               + XmlTransferUtil.transfer(contractTypeName)
               + "</value>"
               + "</column>"
               + "<column name=\"撤销原因\">"
               + "<value>"
               + XmlTransferUtil.transfer(cancelReason)
               + "</value>"
               + "</column>"
               + "<column name=\"流程结束时间\">"
               + "<value/>"
               + "</column>"
               + "<column name=\"CRM单据号\">"
               + "<value>"
               + XmlTransferUtil.transfer(contractNo)
               + "</value>"
               + "</column>"
               + "</values>"
               + "<subForms>"
               + "<subForm>"
               + "<definitions>"
               + "<column id=\"field0014\" type=\"0\" name=\"门店店招\" length=\"255\"/>"
               + "<column id=\"field0015\" type=\"0\" name=\"门店编号\" length=\"255\"/>"
               + "<column id=\"field0016\" type=\"0\" name=\"门店地址\" length=\"255\"/>"
               + "<column id=\"field0017\" type=\"0\" name=\"装修进度\" length=\"255\"/>"
               + "</definitions>"
               + "<values>"
               + businessAddressXml + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";
       
       //表单数据
       rspMap.put(XML_DATA_KEY, dateHtml.toString());
       
       logger.info("CRM 门店业绩撤销,提交审核 dateHtml=", dateHtml);
       
       //添加到reqMap,用于更新到本地
       reqMap.put("achievementCancelNo", achievementCancelNo);
       //reqMap.put("userCreate", Integer.valueOf(reqMap.get("userCreate").toString()));
       reqMap.put("dateCreate", date);
       reqMap.put("accountSubject", accountNo);
       reqMap.put("busDepartment", bussineArea);
       reqMap.put("isDelete", 0);
       //审核通过后才设定审核时间
       //reqMap.put("approveDate", date);
       
       return rspMap;
   }
   /**
    * 获取门店撤销信息
    * @param reqMap
    * @param dateHtml
    * @throws Exception
    */
   private AchievementCancelDto getOAAuditInfo(Map<String, Object> reqMap, String dateHtml) throws Exception {
	   //获取门店撤销申请撤销所需的[页面]信息
	   AchievementCancelDto achievementCancelDto = achievementCancelMapper.getContractInfo(reqMap);
	   if(achievementCancelDto.getContractNo().isEmpty()){
		   dateHtml = "";
	   }
	   //storeIds转换成数组
	   String str = (String)reqMap.get("storeIds");
	   String[] storeIds = str.split(",");
	   Map<String,Object> hashMap = new HashMap<String, Object>();
	   hashMap.put("storeIdList", storeIds);
	   //获取申请撤销门店信息
	   List<StoreDto> storelist = achievementCancelMapper.getStoreInfo(hashMap);
	   if(storelist.isEmpty()){
		   dateHtml = "";
	   }
	   achievementCancelDto.setStorelist(storelist);
	return achievementCancelDto;
   }
   /**
    * 获取事业部区域
    * @return
    */
//   private String getBussineArea(String busNo,String userCode) throws Exception {
//       // 事业部区域
//       String bussineArea = "";
//       //根据用户code查询
//       OaOperator oaOperator = oaOperatorMapper.getByUserCode(userCode);
//       if(null != oaOperator|| !("").equals(oaOperator.getOperId())){
//    	   Boolean isCombine = oaOperator.getIsCombine();
//    	   if (isCombine) {
//    		   bussineArea = busNo;
//    	   } else {
//    		   bussineArea = oaOperator.getBusCode();
//    	   }
//       }
//       return bussineArea;
//   }
   /**
    * 合同撤销发OA审核
    * @param reqMap
    * @return
    * @throws Exception
    */
   public Long toOaAuth(Map<String, Object> reqMap, String oaTemplateCode)
           throws Exception {
       logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);
       // 取得REST动态客户机实例
       CTPRestClient client = getClient();
       // token认证
       client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
               SystemParam.getWebConfigValue("oaPassword"));
       // 表单post
       String url = "/flow/" + oaTemplateCode;
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
   /**
    * 本地撤销更改表机构
    * @param queryParam
    * @param resultData
    * @throws Exception
    */
   private ResultData createNewRecord(Map<String, Object> queryParam,ResultData resultData) throws Exception{
	  //门店撤销审核状态初始化code,17301-正常，17302-撤销中，17303-撤销失败，17304-已撤销
       AchievementCancel achievementCancel = new AchievementCancel();
       achievementCancel = MapToEntityConvertUtil.convert(queryParam, AchievementCancel.class, "");
       achievementCancel.setFlowId(queryParam.get("flowId").toString());
       achievementCancel.setUserCreate(Integer.valueOf(queryParam.get("userCreate").toString()));
       achievementCancel.setApproveState(DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLING);
       //插入门店撤销记录
       int count = achievementCancelMapper.createNewCancelRecord(achievementCancel);
       if(count<1){
       	resultData.setFail("插入门店撤销失败！queryParam=" +queryParam.toString());
           return resultData;
       }
       //撤销Id
       Integer id = achievementCancel.getId();
       queryParam.put("achievementCancelId", id);
       String list = (String)queryParam.get("storeIds");
       String[] storeIds = list.split(",");
       for(int i = 0; i < storeIds.length;i++){
           Integer storeId =Integer.valueOf(storeIds[i]);
           queryParam.put("storeId", storeId);
           //插入关联表
           int mappingCount = achievementCancelMappingMapper.createNewRecord(queryParam);
           if(mappingCount<1){
           	resultData.setFail("插入门店撤销关联表失败！queryParam=" +queryParam.toString());
               return resultData;
           }
        }
       //更新contractStore中的isCancel状态为17202-撤销中，--17201为正常，17203为已撤销
       queryParam.put("storeIdList", storeIds);
       queryParam.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCELLING);
       int storeCount = contractStoreMapper.updateIsCancel(queryParam);
       if(queryParam.containsKey("contractId")) {
       	Integer contractId = Integer.valueOf(queryParam.get("contractId").toString());
       	Map<String,Object> param = new HashMap<String,Object>();
       	param.put("contractId", contractId);
       	param.put("storeIdList", storeIds);
       	contractStoreMapper.updateFlag(param);
       }
       if(storeCount<1){
       	resultData.setFail("更新合同门店关联表状态失败！queryParam=" +queryParam.toString());
           return resultData;
       }
	   return resultData;
   }
   /**
    * 查询已撤销的合同列表
    * @param reqMap
    * @return
    */
   public ResultData<List<AchievementCancelEdDto>> getContractChangeEdList(Map<String, Object> reqMap) throws Exception {
       ResultData<List<AchievementCancelEdDto>> resultData = new ResultData<List<AchievementCancelEdDto>>();
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
       List<AchievementCancelEdDto> achievementCancelList = apiContractChangeMapper.getContractChangeEdList(reqMap);
       resultData.setTotalCount("0");
       if (achievementCancelList != null && achievementCancelList.size() > 0) {
    	   resultData.setTotalCount(achievementCancelList.get(0).getDataCount()+"");
    	   int endRow = pageIndex * pageSize;
    	   achievementCancelList = achievementCancelList.subList((pageIndex - 1) * pageSize, endRow > achievementCancelList.size() ? achievementCancelList.size() : endRow);
    	   reqMap.put("pageIndex", pageIndex);
   		   reqMap.put("pageSize", pageSize);
   		   reqMap.put("curPage", curPage);
           resultData.setReturnData(achievementCancelList);
           resultData.setSuccess();
       }
       return resultData;
   }
   /**
    * 撤销详情页面（根据id查）
    * @param map
    * @return
    */
   public ResultData<AchievementCancelEdDto> getContractChangeEdInfo(Map<String, Object> reqMap) throws Exception{
       ResultData<AchievementCancelEdDto> resultData = new  ResultData<AchievementCancelEdDto>();
       AchievementCancelEdDto achievementCancelEdDto  = apiContractChangeMapper.getContractChangeEdInfo(reqMap);
       String flowId = achievementCancelEdDto.getFlowId();
       //根据flowId查询其流程
       List<OaProcessDto> oaProcessDtoList = apiContractChangeMapper.getOaProcessByFlowId(flowId);
       if(null !=oaProcessDtoList && oaProcessDtoList.size()>0){
    	   achievementCancelEdDto.setOaProcesslist(oaProcessDtoList);
       }
       //Integer reback = -1;OA返回码
       //reback = this.getOaState(flowId);
       //achievementCancelEdDto.setOaBack(reback);
       resultData.setReturnData(achievementCancelEdDto);
       return resultData;
   }
   /**
    * 获取OA审核状态
    *
    * @param flowId
    * @return
    * @throws Exception
    */
   public Integer getOaState(String flowId) throws Exception {

       // 取得REST动态客户机实例
       CTPRestClient client = getClient();
       // token认证
       client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
               SystemParam.getWebConfigValue("oaPassword"));

       String url = "flow/state/" + flowId;

       // get流程状态
       Integer state = client.get(url, Integer.class);

       return state;
   }
   /**
    * 查询可终止的合同列表
    */
   public ResultData<List<ContractChangeNewDto>> getContractEndList(Map<String, Object> reqMap) throws Exception {
       ResultData<List<ContractChangeNewDto>> resultData = new ResultData<List<ContractChangeNewDto>>();
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
       List<ContractChangeNewDto> contractEndList = apiContractChangeMapper.getContractEndList(reqMap);
       resultData.setTotalCount("0");
       if (contractEndList != null && contractEndList.size() > 0) {
    	   resultData.setTotalCount(contractEndList.get(0).getDataCount()+"");
    	   int endRow = pageIndex * pageSize;
    	   contractEndList = contractEndList.subList((pageIndex - 1) * pageSize, endRow > contractEndList.size() ? contractEndList.size() : endRow);
    	   reqMap.put("pageIndex", pageIndex);
   		   reqMap.put("pageSize", pageSize);
   		   reqMap.put("curPage", curPage);
           resultData.setReturnData(contractEndList);
           resultData.setSuccess();
       }
       return resultData;
   }
   /**
    * 合同终止提交填写页面
    * @param reqMap
    * @throws Exception
    */
   public ResultData<ContractChangeNewDto> toContractEndSubmit(Map<String, Object> reqMap) throws Exception{
       ResultData<ContractChangeNewDto> resultData = new  ResultData<ContractChangeNewDto>();
       ContractChangeNewDto contractChangeNewDto  = apiContractChangeMapper.toContractEndSubmit(reqMap);
       //公司详细地址
       String cityNameCompany = SystemParam.getCityNameByCityNo(contractChangeNewDto.getPartyBCityNo());
       String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(contractChangeNewDto.getPartyBDistrictNo());
       String partyAddressDetail = cityNameCompany + districtNameCompany + contractChangeNewDto.getPartyBAddress();
       contractChangeNewDto.setPartyAddressDetail(partyAddressDetail);
       resultData.setReturnData(contractChangeNewDto);
       return resultData;
   }
   /**
    * 本地更改数据库
    * 1.保存合同变更信息
    * 2.更新上传图片的refid
    * 3.重新建立新的合同与门店的关系
    * @param reqMap
    * @return
    */
   public ResultData doSaveContractEnd(Map<String, Object> reqMap) throws Exception{
	   ResultData resultData = new ResultData();
	   ContractChange contractChange = new ContractChange();
	   List<StoreDto> storeDtoList = new ArrayList<>();
	   //设置参数准备插表
   	   setContractEndDto(reqMap,contractChange,storeDtoList);

   	   if(contractChange.getId() != null && contractChange.getId().intValue() > 0){
   	   		//更新
		    contractChangeMapper.update(contractChange);

		    //删除图片
		   String delFileRecordMainIds = (String) reqMap.get("delFileRecordMainIds");
		   if (StringUtil.isNotEmpty(delFileRecordMainIds)) {
			   String[] arrays = delFileRecordMainIds.split(",");
			   for (int i = 0; i < arrays.length; i++) {
				   if (StringUtil.isNotEmpty(arrays[i])) {
					   FileRecordMain fileRecordMain = new FileRecordMain();
					   fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
					   fileRecordMain.setIsDelete(true);
					   fileRecordMainMapper.update(fileRecordMain);
				   }
			   }
		   }

		   //删除门店关系
		   contractChangeStoreMapper.updateById(contractChange.getId());
	   }else{
   	   		//新增
		    contractChangeMapper.create(contractChange);
	   }

	   Integer id = contractChange.getId();
	   //更新FileRecordMain的refId字段
	   String fileRecordMainIds = (String) reqMap.get("fileRecordMainIds");
	   if (StringUtil.isNotEmpty(fileRecordMainIds)) {
		   String[] arrays = fileRecordMainIds.split(",");
		   // 关联相关文件(RefId)
		   for (int i = 0; i < arrays.length; i++) {
			   if (StringUtil.isNotEmpty(arrays[i])) {
				   FileRecordMain fileRecordMain = new FileRecordMain();
				   fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
				   fileRecordMain.setRefId(id);
				   fileRecordMain.setIsDelete(false);
				   fileRecordMainMapper.update(fileRecordMain);
			   }
		   }
	   }

	   // 重新建立新的合同变更与门店的关系
	   this.addContractChangeStore(storeDtoList, contractChange.getId(), contractChange.getContractId());
       
       return resultData;
    }
	/**
	* @param reqMap 参数map
	* @param contractChange 
	* @param storeDtoList 门店信息
	* @throws ParseException
	*/
	private void setContractEndDto(Map<String, Object> reqMap, ContractChange contractChange, List<StoreDto> storeDtoList) throws Exception {
		// 合同ID
		String contractId = (String) reqMap.get("contractId");
		// 变更类型
		String changeType = "17001";
		// 终止类型
		String stopType = (String) reqMap.get("stopType");
		// 合作终止时间
		String stopDate = (String) reqMap.get("stopDate");
		// 终止具体原因
		String stopReason = (String) reqMap.get("stopReason");
		// 终止方案阐述
		String stopDescribe = (String) reqMap.get("stopDescribe");
		// 备注
		String remarks = (String) reqMap.get("remarks");
		//refIds
		//String fileRecordMainIds = (String) reqMap.get("fileRecordMainIds");
		//创建人或更新人
		String userCreate = reqMap.get("userCreate").toString();
		
		/**
		 * 创建用
		 */
		if (Integer.valueOf(reqMap.get("contractStopId").toString()).intValue()==0) {
			//合同终止No
			String contractStopNo = (String) reqMap.get("contractStopNo");
			contractChange.setContractStopNo(contractStopNo);
			//创建时间
			contractChange.setDateCreate(new Date());
			//创建人
			contractChange.setUserIdCreate(Integer.valueOf(userCreate));
		}else{
			// 合同变更ID
			String id = (String) reqMap.get("contractStopId");
			contractChange.setUpdateCreate(Integer.valueOf(userCreate));
			contractChange.setUpdateDate(new Date());
			contractChange.setId(Integer.valueOf(id));
		}
		// 合同ID
		contractChange.setContractId(Integer.valueOf(contractId));
		// 变更类型
		contractChange.setChangeType(Integer.valueOf(changeType));
		// 终止类型
		contractChange.setStopType(Integer.valueOf(stopType));
		// 合作终止时间
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isNotEmpty(stopDate)) {
			contractChange.setStopDate(format.parse(stopDate));
		}
		//终止具体原因
		contractChange.setStopReason(stopReason);
		// 终止方案阐述
		contractChange.setStopDescribe(stopDescribe);
		//备注
		contractChange.setRemarks(remarks);
		//删除标识
		contractChange.setDelFlag(false);

		//关联门店列表
		final JSONArray storeListJsonStr = JSON.parseArray(reqMap.get("storeListJsonStr").toString());

		int count = contractChangeMapper.queryStoreOfCountByConractId(Integer.valueOf(contractId));
		if(storeListJsonStr.size() == count){
			//16801 isReleaseFlag 设置合作协议书一并解除
			contractChange.setIsReleaseFlag(16801);
		}else {
			//16802 不设置合作协议书一并解除
			contractChange.setIsReleaseFlag(16802);
		}

		if(!storeListJsonStr.isEmpty()){
			for(Object storeObj : storeListJsonStr){
				final Map storeObjMap = JsonUtil.parseToObject(storeObj.toString(), Map.class);
				StoreDto storeDto = new StoreDto();
				Integer storeId = Integer.valueOf(storeObjMap.get("storeId").toString());
				storeDto.setStoreId(storeId);
				storeDto.setReceivedAmount(new BigDecimal(storeObjMap.get("receivedAmount").toString()));
				storeDto.setDepositBalance(Integer.parseInt(storeObjMap.get("depositBalance").toString()));
				storeDto.setDepositBackMoney(new BigDecimal(storeObjMap.get("depositBackMoney").toString()));

				//查询 装修金额，装修公司，装修状态，装修情况
				final Map storeDecorateInfo = apiContractChangeMapper.getStoreDecorateInfo(storeId);
				storeDto.setDecorateAmount(new BigDecimal(storeDecorateInfo.get("decorateAmount").toString()));
				storeDto.setDecorateCompany(storeDecorateInfo.get("decorateCompany").toString());
				//17001,17002
				storeDto.setDecorationType(Integer.valueOf(storeDecorateInfo.get("decorationType").toString()));
				//16301
				storeDto.setDecorateSituate(Integer.valueOf(storeDecorateInfo.get("decorateSituate").toString()));

				storeDtoList.add(storeDto);
			}
		}
	}
	/**
	 * 建立新的合同变更与门店的关系
	 * @param storeDtoList 门店相关信息
	 * @param contractStopId
	 *            合同变更ID
	 * @param contractId
	 *            合同ID
	 */
	private void addContractChangeStore(List<StoreDto> storeDtoList,
			int contractStopId, int contractId) throws Exception {
		if (null != storeDtoList && !storeDtoList.isEmpty()) {
			for (StoreDto storeDto : storeDtoList) {
				// 合同变更/门店关联信息
				ContractChangeStore bean = new ContractChangeStore();
				bean.setContractStopId(contractStopId);
				if(storeDto.getDecorationType()>0){
					bean.setDecorationType(storeDto.getDecorationType());
				}
				if(storeDto.getDecorateSituate()>0){
					bean.setDecorateSituate(storeDto.getDecorateSituate());
				}
				bean.setDecorateAmount(storeDto.getDecorateAmount());
				bean.setDecorateCompany(storeDto.getDecorateCompany());
				bean.setStoreId(storeDto.getStoreId());
				bean.setReceivedAmount(storeDto.getReceivedAmount());
				bean.setDepositBalance(storeDto.getDepositBalance());
				bean.setDepositBackMoney(storeDto.getDepositBackMoney());
				bean.setDelFlag(false);
				bean.setContractId(contractId);
				bean.setApproveState(0);
				// 入库操作
				contractChangeStoreMapper.create(bean);
			}
		}
	}
	/**
	 * 查询已终止的待提交合同列表
	 * @param reqMap
	 * @return
	 */
	public ResultData<List<ContractChangeNewDto>> getContractEndToSumbmitList(Map<String, Object> reqMap) throws Exception {
		ResultData<List<ContractChangeNewDto>> resultData = new ResultData<List<ContractChangeNewDto>>();
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
		List<ContractChangeNewDto> contractChangeNewDtoList = apiContractChangeMapper.getContractEndToSumbmitList(reqMap);
		resultData.setTotalCount("0");
		if (contractChangeNewDtoList != null && contractChangeNewDtoList.size() > 0) {
			resultData.setTotalCount(contractChangeNewDtoList.get(0).getDataCount()+"");
			int endRow = pageIndex * pageSize;
			contractChangeNewDtoList = contractChangeNewDtoList.subList((pageIndex - 1) * pageSize, endRow > contractChangeNewDtoList.size() ? contractChangeNewDtoList.size() : endRow);
			reqMap.put("pageIndex", pageIndex);
			reqMap.put("pageSize", pageSize);
			reqMap.put("curPage", curPage);
			resultData.setReturnData(contractChangeNewDtoList);
			resultData.setSuccess();
		}
		return resultData;
	}
	/**
	    * 查询已撤销的合同列表
	    * @param reqMap
	    * @return
	    */
	   public ResultData<List<ContractChangeNewDto>> getContractEndProgressList(Map<String, Object> reqMap) throws Exception {
	       ResultData<List<ContractChangeNewDto>> resultData = new ResultData<List<ContractChangeNewDto>>();
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
	       List<ContractChangeNewDto> contractChangeNewDtoList = apiContractChangeMapper.getContractEndProgressList(reqMap);
	       resultData.setTotalCount("0");
	       if (contractChangeNewDtoList != null && contractChangeNewDtoList.size() > 0) {
	    	   resultData.setTotalCount(contractChangeNewDtoList.get(0).getDataCount()+"");
	    	   int endRow = pageIndex * pageSize;
	    	   contractChangeNewDtoList = contractChangeNewDtoList.subList((pageIndex - 1) * pageSize, endRow > contractChangeNewDtoList.size() ? contractChangeNewDtoList.size() : endRow);
	    	   reqMap.put("pageIndex", pageIndex);
	   		   reqMap.put("pageSize", pageSize);
	   		   reqMap.put("curPage", curPage);
	           resultData.setReturnData(contractChangeNewDtoList);
	           resultData.setSuccess();
	       }
	       return resultData;
	   }
	/**
	    * 合同终止查看页面
	    * @return
	    */
	   public ResultData<ContractChangeNewDto> getContractEndToView(Map<String, Object> reqMap) throws Exception{
	       ResultData<ContractChangeNewDto> resultData = new  ResultData<ContractChangeNewDto>();
	       ContractChangeNewDto contractChangeNewDto  = apiContractChangeMapper.getContractEndToView(reqMap);
	       if(null != contractChangeNewDto) {

			   //公司详细地址
			   String cityNameCompany = SystemParam.getCityNameByCityNo(contractChangeNewDto.getPartyBCityNo());
			   String districtNameCompany = SystemParam.getDistrictNameByDistrictNo(contractChangeNewDto.getPartyBDistrictNo());
			   String partyAddressDetail = cityNameCompany + districtNameCompany + contractChangeNewDto.getPartyBAddress();
			   contractChangeNewDto.setPartyAddressDetail(partyAddressDetail);

	    	   String flowId = contractChangeNewDto.getFlowId();
	    	   //根据flowId查询其流程
	    	   if(!"".equals(flowId) && null != flowId){
	    		   List<OaProcessDto> oaProcessDtoList = apiContractChangeMapper.getOaProcessByFlowId(flowId);
	    		   if(null !=oaProcessDtoList && oaProcessDtoList.size()>0){
	    			   contractChangeNewDto.setOaProcesslist(oaProcessDtoList);
	    		   }
	    	   }
	    	   //设置图片
	    	   String fileRecordMainIds = "";
	    	   if(reqMap.containsKey("contractStopId")) {
	    		   String contractStopId = reqMap.get("contractStopId").toString();
	    		   FileRecordMain getFilesParam = new FileRecordMain();
	    		   getFilesParam.setRefId(Integer.valueOf(contractStopId));
	    		   getFilesParam.setIsDelete(false);
	    		   //查询数据返回
	    		   List<FileRecordMainDto> fileRecordMainDtoList = fileRecordMainMapper.getContractChangePics(getFilesParam);
	    		   List<FileRecordMain> fileRecordMainList = new ArrayList<>();
	    		   for(FileRecordMainDto fileRecordMainDto :fileRecordMainDtoList){
	    			   FileRecordMain fileRecordMain = new FileRecordMain();
	    			   BeanUtils.copyProperties(fileRecordMainDto, fileRecordMain);
	    			   fileRecordMainList.add(fileRecordMain);
	    		   }
	    		   Map<?, ?> mop = pushFileRecord(fileRecordMainList, fileRecordMainIds,contractChangeNewDto);
	    		   fileRecordMainIds = (String)mop.get("fileRecordMainIds");
	    		   if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
	    			   fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
	    		   }
	    	   }
	    	   contractChangeNewDto.setFileRecordMainIds(fileRecordMainIds);
	       }
	       resultData.setReturnData(contractChangeNewDto);
	       return resultData;
	   }
   /**
     * 图片信息
     *
     * @param
     * @return
     */
    private Map<String, String> pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds,
    		ContractChangeNewDto contractChangeDto)
        throws Exception{
		if (null != fileMdlList && fileMdlList.size() > 0) {
			List<ContractFileDto> tripartiteFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> stopContractFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> receiptFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> storePhotosFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> returnProveFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> cancellateFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> othersFileList = new ArrayList<ContractFileDto>();
			for (int i = 0; i < fileMdlList.size(); i++) {
				FileRecordMain fileRecordMain = fileMdlList.get(i);
				Integer fileTypeId = fileRecordMain.getFileTypeId();
				//对文件数据进行组装处理
				if (fileTypeId == 1001) {
					ContractFileDto stopContractDto = new ContractFileDto();
					handleFileRecordMain(stopContractDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + stopContractDto.getFileRecordMainId() + ",";
					stopContractFileList.add(stopContractDto);
				} else if (fileTypeId == 1002) {
					ContractFileDto receiptDto = new ContractFileDto();
					handleFileRecordMain(receiptDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + receiptDto.getFileRecordMainId() + ",";
					tripartiteFileList.add(receiptDto);
				}else if (fileTypeId == 1003) {
					ContractFileDto receiptDto = new ContractFileDto();
					handleFileRecordMain(receiptDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + receiptDto.getFileRecordMainId() + ",";
					receiptFileList.add(receiptDto);
				} else if (fileTypeId == 1004) {
					ContractFileDto returnProveDto = new ContractFileDto();
					handleFileRecordMain(returnProveDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + returnProveDto.getFileRecordMainId() + ",";
					returnProveFileList.add(returnProveDto);
				} else if (fileTypeId == 1005) {
					ContractFileDto cancellateDto = new ContractFileDto();
					handleFileRecordMain(cancellateDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + cancellateDto.getFileRecordMainId() + ",";
					cancellateFileList.add(cancellateDto);
				}  else if (fileTypeId == 1009) {
					ContractFileDto othersDto = new ContractFileDto();
					handleFileRecordMain(othersDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + othersDto.getFileRecordMainId() + ",";
					othersFileList.add(othersDto);
				} else if (fileTypeId == 1014) {
					ContractFileDto storePhotosDto = new ContractFileDto();
					handleFileRecordMain(storePhotosDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + storePhotosDto.getFileRecordMainId() + ",";
					storePhotosFileList.add(storePhotosDto);
				}
			}
			contractChangeDto.setStopContractFileList(stopContractFileList);
			contractChangeDto.setReceiptFileList(receiptFileList);
			contractChangeDto.setReturnProveFileList(returnProveFileList);
			contractChangeDto.setCancellateFileList(cancellateFileList);
			contractChangeDto.setOthersFileList(othersFileList);
			contractChangeDto.setStorePhotosFileList(storePhotosFileList);
			contractChangeDto.setTripartiteFileList(tripartiteFileList);
		}
        Map<String, String> mop = new HashMap<String, String>();
        mop.put("fileRecordMainIds", fileRecordMainIds);
        return mop;
    }
    /** 
     * 对文件数据进行组装处理
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
     private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)//, String picId
         throws Exception {
         String fileId = fileRecordMain.getFileId();
          //获取图片路径
          contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
          contractFileDto.setFileName(fileRecordMain.getFileFullName());
          contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
          contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
          contractFileDto.setUrl50(fileRecordMain.getUrl50());
     }

    public ResultData checkContractStoreForTerminate(Map<String, Object> map) throws Exception{
        Integer num = apiContractChangeMapper.checkContractStoreForTerminate(map);
        ResultData resultData = new ResultData();
        resultData.setReturnData(num);
        return resultData;
    }
}
