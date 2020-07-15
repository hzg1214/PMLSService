package cn.com.eju.deal.open.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.open.DepositRefundDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.open.model.StorePerformDto;
import cn.com.eju.deal.open.service.APIOmsService;


/**
 * 提供给OMS系统的API
 * 
 * @author wenhui.zhang
 * @date 2016年8月18日 下午3:52:34
 */
@RestController
@RequestMapping(value = "crm")
public class APIOmsController extends BaseController {

	/**
	 * 日志
	 */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Resource
	private APIOmsService apiOmsService;

	/**
	 * 查询-根据合同No查询合同下面的门店
	 * 
	 * @param contractNo
	 * @return 门店列表
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "store/{param}", method = RequestMethod.GET)
	public String getStoreInfoByContractNo(@PathVariable String param) {
		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
		try {
			Map<String, Object> queryParam = JsonUtil.parseToObject(param,
					Map.class);
			// 查询
			List<StoreDto> storeList = apiOmsService.getStoreInfoByContractNo(queryParam);
			resultData.setReturnData(storeList);
			if (null != storeList) {
				resultData.setTotalCount(storeList.size() + "");
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("APIOms", "APIOmsController",
					"getStoreInfoByContractNo", "contractNo", 1, "",
					"根据合同No查询合同下面的门店信息失败！", e);
		}
		return resultData.toString();
	}

	/**
     * 更新OMS收取的保证金 到 CRM合同到账金额
     */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "contract/{param}", method = RequestMethod.GET)
    public String updateArvDepositFee(@PathVariable String param) 
    {
    	// 构建返回
    	ResultData<Integer> resultData = new ResultData<Integer>();
		try {
			Map<String, Object> queryParam = JsonUtil.parseToObject(param,Map.class);
			String contractNo = (String)queryParam.get("contractNo");
			BigDecimal arrivalDepositFee = new BigDecimal(queryParam.get("receiveAmount").toString());
			Integer num = apiOmsService.updateArvDepositFee(contractNo,arrivalDepositFee);
			resultData.setReturnData(num);
		} catch (Exception e) {
			resultData.setReturnMsg("更新到账保证金失败!");
			resultData.setFail();
			logger.error("APIOms", "APIOmsController",
					"updateArvDepositFee", "contractNo", 1, "",
					"根据合同No查询合同下面的门店信息失败！", e);
		}
		return resultData.toString();
    }
	
	/** 
	* 获取业绩查看详情信息
	* @param param
	* @return
	*/
	@RequestMapping(value = "/storeperform/detail/{param}", method = RequestMethod.GET)
    public String getDetailInfo(@PathVariable String param)
    {
        //构建返回
        ResultData<StorePerformDto> resultData = new ResultData<StorePerformDto>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        // 合同No
        String contractNo = (String) queryParam.get("contractNo");
        // 门店No
        String storeNo = (String) queryParam.get("storeNo");
        
        //查询
        StorePerformDto storePerformDto =new  StorePerformDto();
        try
        {
        	// 查询操作
        	storePerformDto = apiOmsService.getDetailInfo(contractNo, storeNo);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("APIOms", "APIOmsController", "getDetailInfo", "", -1, "", "查询业绩查看详情信息失败", e);
        }
        
        resultData.setReturnData(storePerformDto);
        return resultData.toString();
    }
	
    /** 
     * OMS更新装修进度-调用CRM接口
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/storedecoration/{param}", method = RequestMethod.GET)
    public String toUpdateStoreDecoration(@PathVariable("param") String param)
    {
        
        Map<String,Object> acceptMap = JsonUtil.parseToObject(param, Map.class);
        
        ResultData<String> resultData = new ResultData<String>();
        String decorationNo = String.valueOf(acceptMap.get("decorationNo"));
        String decorationStatus = String.valueOf(acceptMap.get("decorationStatus"));
        String userId = String.valueOf(acceptMap.get("userId"));
        //check接收数据
        if(StringUtils.isBlank(decorationNo) && StringUtils.isBlank(decorationStatus)){
            resultData.setFail();
            resultData.setReturnData("decorationNo = " + decorationNo +","+ "decorationStatus = "+ decorationStatus+","+"userId = "+userId);
            return resultData.toString();
        }

        Integer decorationStatusNum = Integer.valueOf(decorationStatus);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("decorationNo", decorationNo);
        reqMap.put("decorationStatus", decorationStatusNum);
        
        //判断是否为空，OMS改逻辑后，userId无法提供，现在判断是否存在，不存在则不更新
        if(!StringUtils.isBlank(userId)){
            Integer updateUserId = Integer.valueOf(userId);
            reqMap.put("updateUserId", updateUserId);
        }

        //更新到门店装修表
        try
        {
            resultData = apiOmsService.updateDecorationRecord(reqMap);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "toUpdateStoreDecoration", "parameter = " + reqMap, null, null, "OMS调用接口更新门店装修进度失败", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    
    /**
     *  OMS翻牌申请调用接口，根据合同编号获取申请数据返回
    * @param param
    * @return
     */
    @RequestMapping(value = "/returndecoration/{param}", method = RequestMethod.GET)
    public String returnDataToDecApplication(@PathVariable("param") String param)
    {
        
        Map<String,Object> acceptMap = JsonUtil.parseToObject(param, Map.class);
        
        ResultData<Map> resultData = new ResultData<Map>();
        
        String contractNo = String.valueOf(acceptMap.get("contractNo"));
        
        //check接收数据
        if(StringUtils.isBlank(contractNo)){
            resultData.setFail();
            resultData.setReturnMsg("申请获取数据,源数据为空！contractNo = " + contractNo);
            return resultData.toString();
        }
        
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("contractNo", contractNo);
        
        
        try
        {
            //获取需要返回的数据
            ResultData<Contract>  result = apiOmsService.returnDataToDecApplication(reqMap);
            //返回数据判空
            if(!result.getReturnCode().equals(ReturnCode.SUCCESS)){
                resultData.setFail();
                resultData.setReturnMsg("根据合同编号查询数据空！");
                return resultData.toString();
            }
            //去除数据准备转换
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Contract contract = result.getReturnData();
            
            //数据转换成Map 返回给OMS
            resultMap.put("ContractNo", contract.getContractNo());
            resultMap.put("PartyB", contract.getPartyB());
            resultMap.put("AgreementNo", contract.getAgreementNo());
            
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
            
            resultMap.put("DateLifeStart", sdf.format(contract.getDateLifeStart()));
            resultMap.put("DateLifeEnd", sdf.format(contract.getDateLifeEnd()));
            
            resultData.setReturnData(resultMap);
            
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "returnDataToDecApplication", "parameter = " + reqMap, null, null, "OMS翻牌申请调用接口，根据获取申请数据返回失败!", e);
            resultData.setFail();
            resultData.setReturnMsg("OMS翻牌申请调用接口，根据合同编号获取申请数据返回失败!");
        }
        
        return resultData.toString();
    }
    
    /** 
     * OMS更新装修付款状态-调用CRM接口
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updatePayStatus/{param}", method = RequestMethod.GET)
    public String updateStoreDecorationPayStatus(
    		@PathVariable("param") String param){
        ResultData<String> resultData = new ResultData<String>();
        
        Map<String,Object> paramMap = JsonUtil.parseToObject(param, Map.class);
        String decorationNo = String.valueOf(paramMap.get("decorationNo"));
        String paymentStatusStr = String.valueOf(paramMap.get("paymentStatus"));
        
        //check接收数据
        if(StringUtils.isBlank(decorationNo) || StringUtils.isBlank(paymentStatusStr)){
            resultData.setFail();
            resultData.setReturnData("存在空参数decorationNo = " + decorationNo +","+ "paymentStatus = "+ paymentStatusStr);
            return resultData.toString();
        }
        Integer paymentStatus = Integer.parseInt(paymentStatusStr);
        if(paymentStatus.equals(17101)){
        	paymentStatus = 17601;
        }else if(paymentStatus.equals(17102)){
        	paymentStatus = 17602;
        }else if(paymentStatus.equals(17103)){
        	paymentStatus = 17603;
        }else{
        	  resultData.setFail();
              resultData.setReturnData("paymentStatus参数错误##decorationNo= " + decorationNo +","+ "paymentStatus = "+ paymentStatus);
              return resultData.toString();
        }
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("decorationNo", decorationNo);
        reqMap.put("paymentStatus", paymentStatus);
        
        
        //更新到门店装修表
        try
        {
            resultData = apiOmsService.updateStoreDecorationPayStatus(reqMap);
        }
        catch (Exception e)
        {
            logger.error("Store", "APIOmsController", "updateStoreDecorationPayStatus", "parameter = " + reqMap, null, null, "OMS调用接口更新装修付款状态失败", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /** 
     * OMS更新装修类型-调用CRM接口
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updateDecorationtype/{param}", method = RequestMethod.GET)
    public String updateDecorationType(@PathVariable("param") String param)
    {
        
        Map<String,Object> acceptMap = JsonUtil.parseToObject(param, Map.class);
        
        ResultData<String> resultData = new ResultData<String>();
        String decorationNo = String.valueOf(acceptMap.get("decorationNo"));
        String decorationType = String.valueOf(acceptMap.get("decorationType"));
        //check接收数据
        if(StringUtils.isBlank(decorationNo) && StringUtils.isBlank(decorationType)){
            resultData.setFail();
            resultData.setReturnData("decorationNo = " + decorationNo +","+ "decorationType = "+ decorationType);
            return resultData.toString();
        }
        
        //Integer decorationTypeNum = Integer.valueOf(decorationType);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("decorationNo", decorationNo);
        
        if(decorationType.equals("17001")){
            reqMap.put("decorationType", 17701);
            reqMap.put("paymentStatus", 17604);
        }else if(decorationType.equals("17002")){
            reqMap.put("decorationType", 17702);
            reqMap.put("paymentStatus",17601);
        }else{
            resultData.setFail();
            resultData.setReturnData("decorationType参数错误##decorationNo= " + decorationNo +","+ "decorationType = "+ decorationType);
            return resultData.toString();
         }
       
        //更新到门店装修类型
        try
        {
            resultData = apiOmsService.updateDecorationType(reqMap);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "updateDecorationType", "parameter = " + reqMap, null, null, "OMS调用接口更新门店装修类型失败", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /** 
     * 获取保证金退款编辑所需详情信息
     * @param param
     * @return
     */
     @RequestMapping(value = "/depositrefund/detail/{param}", method = RequestMethod.GET)
     public String getDepositRefundDetail(@PathVariable String param)
     {
         //构建返回
         ResultData<DepositRefundDto> resultData = new ResultData<>();
         
         Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
         // 合同No
         String contractNo = (String) queryParam.get("contractNo");
         try
         {
             // 查询操作
             resultData = apiOmsService.getDepositRefundDetail(contractNo);
         }
         catch (Exception e)
         {
             resultData.setFail("获取保证金退款编辑所需详情信息失败");
             logger.error("APIOms", "APIOmsController", "getDepositRefundDetail", "", -1, "", "获取保证金退款编辑所需详情信息失败", e);
             return resultData.toString();
         }
         
         return resultData.toString();
     }
     
     /** 
      * 获取保证金退款-校验金额
      * @param param
      * @return
      */
      @RequestMapping(value = "/depositrefund/checkAmount/{param}", method = RequestMethod.GET)
      public String getDepositRefundAmount(@PathVariable String param)
      {
          //构建返回
          ResultData<String> resultData = new ResultData<>();
          
          Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
          // 合同No
          String contractNo = (String) queryParam.get("contractNo");
          try
          {
              // 查询操作
              resultData = apiOmsService.getDepositRefundAmount(contractNo);
          }
          catch (Exception e)
          {
              resultData.setFail("获取保证金退款-校验金额失败");
              logger.error("APIOms", "APIOmsController", "getDepositRefundAmount", "", -1, "", "获取保证金退款-校验金额失败", e);
              return resultData.toString();
          }
          
          return resultData.toString();
      }
      
      /** 
       * OMS退款后-更新金额、状态到本地
       * @param param
       * @return
       */
       @RequestMapping(value = "/depositrefund/updateAmount", method = RequestMethod.POST)
       public String saveDepositRefundState(@RequestBody String param)
       {
           //构建返回
           ResultData<String> resultData = new ResultData<>();
           
           List<?> queryParam = JsonUtil.parseToObject(param, List.class);
           try
           {
               // 查询操作
               resultData = apiOmsService.saveDepositRefundState(queryParam);
           }
           catch (Exception e)
           {
               resultData.setFail("获取保证金退款-校验金额失败");
               logger.error("APIOms", "APIOmsController", "saveDepositRefundState", "", -1, "", "OMS退款后-更新金额、状态到本地失败", e);
               return resultData.toString();
           }
           
           return resultData.toString();
       }

}
