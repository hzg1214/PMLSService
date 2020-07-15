package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.*;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**   
* 服务层
* @author qianwei
* @date 2016年3月22日 下午6:05:44
*/

@RestController
@RequestMapping(value = "contract")
public class ContractController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "contractService")
    private ContractService contractService;
    
    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;
    /** 
    * 查询-对象
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id)
    {
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        //查询
        ContractInfoDto ctaDto =new  ContractInfoDto();
        try
        {
        	//Mod By NingChao 2017/04/07 Start
        	//ctaDto = contractService.getById(id);
        	ctaDto = contractService.getById(id, 0);
        	//Mod By NingChao 2017/04/07 End
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getById", "", -1, "", "查询-对象失败", e);
        }
        
        resultData.setReturnData(ctaDto);
        
        return resultData.toString();
    }
    //Add By NingChao 2017/04/07 Start
    /** 
    * 查询续签合同信息
    * @param id
    * @param storeId 续签门店ID
    * @return
    */
    @RequestMapping(value = "/{id}/{storeId}", method = RequestMethod.GET)
    public String getRenewById(@PathVariable int id, @PathVariable int storeId)
    {
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        //查询
        ContractInfoDto ctaDto =new  ContractInfoDto();
        try
        {
        	ctaDto = contractService.getById(id, storeId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getById", "", -1, "", "查询-对象失败", e);
        }
        
        resultData.setReturnData(ctaDto);
        
        return resultData.toString();
    }
  //Add By NingChao 2017/04/07 End
    /** 
     * 获取产品信息
     * @return
     */
    @RequestMapping(value = "/p", method = RequestMethod.GET)
    public String getProductInfo()
    {
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        //查询
        ContractInfoDto ctaDto = new ContractInfoDto();
        try
        {
            ctaDto = contractService.getProductInfo();
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getProductInfo", "", -1, "", "获取产品信息失败", e);
        }
        
        resultData.setReturnData(ctaDto);
        
        return resultData.toString();
    }
    
    /** 
    * 查询-list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //Add By DengLin 2017/04/07 Start
        if(queryParam.containsKey("searchKey")&& queryParam.get("searchKey")!=null){
        	queryParam.put("searchKey", queryParam.get("searchKey").toString().trim());
        }
        //Add By DengLin 2017/04/07 End
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = contractService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "list", "", -1, "", "查询-list失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 验证处理
     * @param param
     * @return
     */
    @RequestMapping(value = "/check/{param}", method = RequestMethod.GET)
    public String checkStoreLock(@PathVariable String param)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        try
        {
        	//协议书编号重复check
        	String agreementNo = queryParam.get("agreementNo").toString();
        	ResultData<List<Contract>> resultDataList = contractService.getContractByAgreementNo(agreementNo);
        	List<Contract> list = resultDataList.getReturnData();
        	if(list.size()>0)
        	{
        		String strContractNo="";
        		int flag=0;
        		for (Contract contract : list) {        			
        			//审核中或审核通过
        			if(10402 == contract.getContractStatus() || 10403 == contract.getContractStatus())
        			{
        				strContractNo += contract.getContractNo()+",";
        				flag = 1;
        			}
        			//草签或审核未通过
        			if(10401 == contract.getContractStatus() || 10404 == contract.getContractStatus())
        			{
        				strContractNo += contract.getContractNo()+",";
        				flag = 2;
        			}
				}
        		
        		if(strContractNo.length()>0)
        		{
        			strContractNo = strContractNo.substring(0, strContractNo.length()-1);
        		}
        		if(flag==1)
        		{
        			String strMsg = "系统中存在相同协议书编号的合同，合同编号为"+strContractNo+"。请勿重复提交！";
        			resultData.setFail(strMsg);
        			return resultData.toString();
        		}else if(flag==2){
        			String strMsg = "系统中存在相同协议书编号的草签合同，合同编号为"+strContractNo+"。如有必要请作废后再操作，请勿重复提交！";
        			resultData.setFail(strMsg);
        			return resultData.toString();
        		}        		
        	}
        	
        	if(queryParam.get("checkType") != null && "1".equals(queryParam.get("checkType"))) {
        	    return resultData.toString();
        	}
            resultData = contractService.checkStoreLock(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "checkStoreLock", "", 0, "", "验证处理失败", e);
        }
        
        return resultData.toString();
    }
    
    //Add By cning 2017/07/13 Start
   /* ** 
     * 验证处理--该公司的所有合同是否有审核中或者审核通过的,如果有，不允许修改公司信息
     * @param param
     * @return
     **/
    @RequestMapping(value = "/checkCompany/{companyId}", method = RequestMethod.GET)
    public String checkCompanyContract(@PathVariable Integer companyId)
    {
        //构建返回
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();

        try
        {
            resultData = contractService.checkCompanyContract(companyId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "checkStoreLock", "", 0, "", "验证处理失败", e);
        }
        
        return resultData.toString();
    }
    
    /* ** 
     * 根据公司ID查询合同状态为未签，审核未通过的合同
     * @param param
     * @return
     **/
    @RequestMapping(value = "/getByCompanyId/{companyId}", method = RequestMethod.GET)
    public String getContractByCompanyId(@PathVariable Integer companyId)
    {
        //构建返回
        ResultData<List<Contract>> resultData = new ResultData<List<Contract>>();

        try
        {
            resultData = contractService.getContractByCompanyId(companyId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "checkStoreLock", "", 0, "", "验证处理失败", e);
        }
        
        return resultData.toString();
    }
    //Add By cning 2017/07/13 End
    
    /** 
     * 创建房友账号
     * @param param
     * @return
     */
    @RequestMapping(value = "/createFangyou/{param}", method = RequestMethod.GET)
    public String createFangyou(@PathVariable String param)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        try
        {
            resultData = contractService.createFangyou(queryParam);
        }
        catch (Exception e)
        {
            String exptMsg = AppMsg.getString("CONTRACT.CREATEFANGYOU_FAIURE");
            resultData.setFail();
            resultData.setReturnMsg(exptMsg);
            
            logger.error("Contract", "ContractContrller", "createFangyou", "", -1, "", exptMsg, e);
        }
        return resultData.toString();
    }
    
    /** 
    * 创建
    * @param jsonDto 对象字符串
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(jsonDto, ContractInfoDto.class);
        try
        {
        	resultData = contractService.create(contractInfoDto);
            Integer count = resultData.getReturnData();
            // 乙转甲
            if(null != count && count == 0){
            	String msg = resultData.getReturnMsg();
            	resultData.setFail();
                resultData.setReturnMsg(msg);
            }
            if(null == count || (count != 0 && count != 1))
            {
                resultData.setFail();
            }
            //Add By NingChao 2017/04/07 Start
            else{
            	contractService.updateRenewFlag(contractInfoDto.getStoreDetails().get(0).getStoreId());
            }
            //Add By NingChao 2017/04/07 End
            
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "create", "", -1, "", "创建失败", e);
        }
        return resultData.toString();
    }
    
    /** 
     * 更新
     * @param param
     * @return
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@RequestBody String contractInfoDtoJson)
    {
        //构建返回
        ResultData<Integer> result = new ResultData<Integer>();
        
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);

        try
        {        	
        	result = contractService.update(contractInfoDto);
        	Integer count = result.getReturnData();
            // 乙转甲
            if(null != count && count == 0){
            	String msg = result.getReturnMsg();
            	resultData.setFail();
                resultData.setReturnMsg(msg);
            }
            if(null == count || (count != 0 && count != 1))
            {
                resultData.setFail();
            }
        	
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "update", "", -1, "", "更新失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 更新
     * @param param
     * @return
     */
    @RequestMapping(value = "updateById", method = RequestMethod.POST)
    public String updateByContractId(@RequestBody String contractInfoDtoJson)
    {
        ResultData<Integer> result = new ResultData<Integer>();
        
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        
        try
        {
        	result = contractService.updateByContractId(contractInfoDto);
        	Integer count = result.getReturnData();
            if(null == count || (count != 0 && count != 1))
            {
                resultData.setFail();
            }
        	
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "update", "", -1, "", "更新失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 合同作废后，释放门店
     * @param param
     * @return
     */
    @RequestMapping(value = "/invalid/{id}/{updateId}", method = RequestMethod.DELETE)
    public String invalid(@PathVariable int id, @PathVariable int updateId)
    {
        //构建返回
        ResultData<ContractDto> resultData = new ResultData<ContractDto>();
        
        try
        {
            contractService.invalid(id, updateId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "invalid", "", -1, "", "合同作废后，释放门店", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 审核
     * @param param
     * @return
     */
    @RequestMapping(value = "audit", method = RequestMethod.PUT)
    public String audit(@RequestBody String contractInfoDtoJson)
    {
        
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        
        int count = 0;
        try
        {
            count = contractService.audit(contractInfoDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "audit", "", 0, "", "审核失败", e);
        }
        
        if (count <= 0)
        {
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /** 
     * 根据storeId和companyId判断是否签署了B版合同
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/sc/{param}", method = RequestMethod.GET)
    public String queryContractTypeByStoreCompanyId(@PathVariable String param)
    {
        //构建返回
        ResultData<Contract> resultData = new ResultData<Contract>();
        
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        try
        {
            resultData = contractService.queryContractTypeByStoreCompanyId(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "queryContractTypeByStoreCompanyId", "", -1, "", "根据storeId和companyId判断是否签署了B版合同失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 更新FlowId
     * @param param
     * @return
     */
    @RequestMapping(value = "flowId", method = RequestMethod.PUT)
    public String updateFlowIdById(@RequestBody String contractInfoDtoJson)
    {
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        try
        {
            int count = contractService.updateFlowIdById(contractInfoDto);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "updateFlowIdById", "", -1, "", "更新FlowId失败", e);
        }
        return resultData.toString();
    }
    
    /** 
     * 更新ContractStatus
     * @param param
     * @return
     */
    @RequestMapping(value = "contractStatus", method = RequestMethod.PUT)
    public String updateContractStatusByFlowId(@RequestBody String contractInfoDtoJson)
    {
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        try
        {
            int count = contractService.updateContractStatusByFlowId(contractInfoDto);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "updateContractStatusByFlowId", "", -1, "", "更新ContractStatus失败", e);
        }
        return resultData.toString();
    }
    
    /** 
     * 更新合同状态
     * @param param
     * @return
     */
    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public String updateById(@RequestBody String contractDtoJson)
    {
        //构建返回
        ResultData<ContractDto> resultData = new ResultData<ContractDto>();
        
        ContractDto dto = JsonUtil.parseToObject(contractDtoJson, ContractDto.class);
        
        Contract mo = new Contract();
        
        //赋值
        BeanUtils.copyProperties(dto, mo);
        
        int count = 0;
        try
        {
            count = contractService.updateById(mo);
        }
        catch (Exception e)
        {
            logger.error("Contract", "ContractController", "updateById", "", 0, "", "更新合同状态失败", e);
            
            resultData.setFail();
        }
        
        if (count <= 0)
        {
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /** 
     * 定时批量更新状态
     * @param param 条件
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/job/state/{param}", method = RequestMethod.GET)
    public String updateStatusByParam(@PathVariable String param)
    {
        //构建返回
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<List<StoreDecorationDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        logger.info("CRM Service 定时任务，param = ", param);
        
        List<String> passList = (List<String>)queryParam.remove("pass");
        
        List<String> nopassList = (List<String>)queryParam.remove("noPass");
        
        try
        { 
        //审核通过
        if (null != passList && !passList.isEmpty())
        {
            logger.info("CRM Service 定时任务，passList = ", passList);
            
            Map<String, Object> reqMap = new HashMap<String, Object>();
            String[] arr = (String[])passList.toArray(new String[passList.size()]);
            reqMap.put("flowIdList", arr);
            reqMap.put("contractStatus", DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS);
            //审核通过新增业绩确认时间
            reqMap.put("performDate",new Date());
            reqMap.put("dateUpdate", new Date());
            contractService.updateStatusByParam(reqMap);
            
            //记录quartz定时拉取合同状态
            
            //审核通过，开通房友账号
//            try
//            {
//                contractService.createFY(passList);
//            }
//            catch (Exception e)
//            {
//                logger.error("Contract",
//                    "ContractController",
//                    "updateStatusByParam",
//                    passList.toString(),
//                    -1,
//                    "",
//                    "定时批量更新状态,开通房友账号失败",
//                    e);
//            }
            
        }
        
       
            //审核不通过
            if (null != nopassList && !nopassList.isEmpty())
            {
                logger.info("CRM Service 定时任务，nopassList = ", nopassList);
                
                Map<String, Object> reqMap = new HashMap<String, Object>();
                String[] arr = (String[])nopassList.toArray(new String[nopassList.size()]);
                reqMap.put("flowIdList", arr);
                reqMap.put("contractStatus", DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS);
                
                //审核不通过新增合同更新时间
                reqMap.put("dateUpdate", new Date());
                contractService.updateStatusByParam(reqMap);
                
//            for (String flowId : nopassList)
//            {
//                // 根据flowId 获取合同Id
//                Contract contract = contractService.getByFlowId(flowId);
//                
//                if (null != contract && null != contract.getId())
//                {
//                    
//                    //合同作废，门店状态设置为未锁定、签约类型显示为空
//                    try
//                    {
//                        contractService.invalid(contract.getId(), 0);
//                    }
//                    catch (Exception e)
//                    {
//                        logger.error("PMLSService",
//                            "ContractContrller",
//                            "updateStatusByParam",
//                            contract.getId() + "",
//                            0,
//                            "",
//                            "",
//                            e);
//                    }
//                }
//                
//            }
                
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "updateStatusByParam", "", -1, "", "定时批量更新状态失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 参数转换
     * @param queryParam
     */
    protected void switchParam(Map<String, Object> queryParam)
    {
        //TODO
        //权限控制，List类型 做为参数进行查询会报错，所以，进行了参数转换为数组，
        List<Integer> userIdList = (List<Integer>)queryParam.remove("userIdList");
        
        if (null != userIdList && !userIdList.isEmpty())
        {
            Integer[] arr = (Integer[])userIdList.toArray(new Integer[userIdList.size()]);
            queryParam.put("userIdList", arr);
        }
        
    }
    
    /** 
     * 获取产品信息
     * @return
     */
    @RequestMapping(value = "company/{companyId}", method = RequestMethod.GET)
    public String getContractsByCompanyId(@PathVariable Integer companyId)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        //查询
        Integer num = null;
        try
        {
            num = contractService.getContractsByCompanyId(companyId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getContractsByCompanyId", "", -1, "", "获取产品信息失败", e);
        }
        if (null != num)
        {
            resultData.setReturnData(num);
        }
        else
        {
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /**
     * 根据公司名称查询该公司是否存在意向合同
     * @param companyName 公司名称
     */
    @RequestMapping(value = "/getContract/{companyName}", method = RequestMethod.GET)
    public String getContractInfo(@PathVariable String companyName)
    {
        ResultData<Contract> resultData = new ResultData<>();
        // 根据公司名称查询该公司是否存在意向合同
        Contract info = null;
        try
        {
            info = contractService.getContractInfo(companyName);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getContractInfo", "", -1, "", "根据公司名称查询该公司是否存在意向合同失败", e);
        }
        if (info != null)
        {
            resultData.setReturnData(info);
        }
        else
        {
            resultData.setFail("查询失败");
        }
        return resultData.toString();
    }
    
    /** 
     *  根据门店Id查询签约的合同
     * @param storeId 门店Id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/store/{param}", method = RequestMethod.GET)
    public String getSignHisByStoreId(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();
        try
        {
            resultData = contractService.getSignHisByStoreId(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getSignHisByStoreId", "", -1, "", "根据门店Id查询签约的合同失败", e);
        }
        return resultData.toString();
    }
    
    /** 
     * 根据公司Id查询审核通过的合同
     * @param  companyId 公司Id
     * @return
     */
    @RequestMapping(value = "getAuditpassContract/{companyId}", method = RequestMethod.GET)
    public String getAuditpassByCompanyId(@PathVariable Integer companyId) {
        ResultData<ContractDto> resultData =new  ResultData<ContractDto>();
        try
        {
            resultData = contractService.getAuditpassByCompanyId(companyId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getAuditpassByCompanyId", "", -1, "", "根据公司Id查询审核通过的合同失败", e);
        }
        return resultData.toString();
    }
    
   /** 
    * 查询当前User及其下属User的合同列表信息（提供给CRM微信端）
    * @param param
    * @return
    */
    @RequestMapping(value = "/qContract/{param}", method = RequestMethod.GET)
     public String queryContractList(@PathVariable String param)
     {
         //构建返回
         ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();
         
         try
         {
        	 @SuppressWarnings("unchecked")
             Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
             
             //权限控制,参数转换
             authParam(queryParam);
             
             resultData = contractService.queryContractList(queryParam);
         }
         catch (Exception e)
         {
             resultData.setFail();
             logger.error("contract", "ContractController", "queryContractList", param, 0, "", "查询当前User及其下属User的合同列表信息（提供给CRM微信端）", e);
         }
         
         return resultData.toString();
     }
    
    /** 
    * 根据合同ID查询合同信息
    * @param contractId 合同ID
    * @return
    */
    @RequestMapping(value = "/queryContract/{contractId}", method = RequestMethod.GET)
	public String getContractById(@PathVariable int contractId) {
		// 构建返回
		ResultData<ContractDto> resultData = new ResultData<ContractDto>();

		try {

			// 查询操作
			resultData = contractService.getContractById(contractId);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractController", "getContractById", "",
					-1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}

	/**
	 * 根据合同ID查询 分账页面信息
	 * 
	 * @param contractId
	 *            合同ID
	 * @return
	 */
	@RequestMapping(value = "/getSplitInfo/{contractId}", method = RequestMethod.GET)
	public String getSplitInfo(@PathVariable Integer contractId) {
		// 构建返回
		ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
		// 查询
		ContractInfoDto ctaDto = new ContractInfoDto();
		try {
			ctaDto = contractService.getSplitInfo(contractId);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("Contract", "ContractContrller", "getSplitInfo", "",
					-1, "", "查询-对象失败", e);
		}
		resultData.setReturnData(ctaDto);
		return resultData.toString();
	}

	/**
	 * 更新保证金信息到合同门店关系表
	 * 
	 * @param contractId
	 *            合同ID 门店Id
	 * @return 更新记录数
	 */
	@RequestMapping(value = "/updateCtctStore/{contractId}/{storeIds}", method = RequestMethod.GET)
	public String updateCtctStore(@PathVariable Integer contractId,
			@PathVariable String storeIds) {
		// 构建返回
		ResultData<Integer> resultData = new ResultData<Integer>();
		try {
			Integer nums = 0;
			String[] storeIdArr = storeIds.split(",");
			for (int i = 0; i < storeIdArr.length; i++) {
				ContractStore contractStore = new ContractStore();
				contractStore.setContractId(contractId);
				contractStore.setStoreId(Integer.valueOf(storeIdArr[i]));
				// 是否分账:1.已分账
				contractStore.setIsArrivalAct(1);
				// 分账日期：当前系统时间
				contractStore.setDateArrivalAct(new Date());
				Integer num = contractService
						.updateContractStore(contractStore);
				nums = nums + num;
				//修改oms的保证金到账时间
				extOmsService.updatePerformNodeRecord(contractId,Integer.valueOf(storeIdArr[i]));
			}
			resultData.setReturnData(nums);
			resultData.setReturnMsg("更新保证金信息成功!");
		} catch (Exception e) {
			resultData.setReturnMsg("更新保证金信息失败!");
			resultData.setFail();
			logger.error("Contract", "ContractContrller", "updateCtctStore",
					"", -1, "", "查询-对象失败", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 根据flowID获取合同信息
	 * @param flowId
	 * @return 合同信息
	 */
	@RequestMapping(value = "/flowId/{flowId}", method = RequestMethod.GET)
	public String getContractInfoByFlowId(@PathVariable String flowId) {
		// 构建返回
		ResultData<ContractDto> resultData = new ResultData<ContractDto>();
		try {
			Contract contract = contractService.getByFlowId(flowId);
			ContractDto dto = new ContractDto();
			BeanUtils.copyProperties(contract, dto);
			resultData.setReturnData(dto);
		} catch (Exception e) {
			resultData.setFail();
			resultData.setReturnMsg("根据flowID获取合同信息失败！");
			logger.error("Contract", "ContractContrller", "getContractInfoByFlowId", "",
					-1, "", "根据flowID获取合同信息失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 手动获取合同状态、定时获取合同状态更新门店装修数据共用
	 * @param flowId
	 * @return 合同信息
	 */
	@RequestMapping(value = "/insertDecoration/{param}", method = RequestMethod.GET)
	public String insertNewDecorationRecord(@PathVariable String param) {
	    //构建返回
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<List<StoreDecorationDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        List<String> passList = (List<String>)queryParam.remove("pass");
        try
        {
            if (null != passList && !passList.isEmpty())
            {
                Map<String, Object> reqMap = new HashMap<String, Object>();
                String[] arr = (String[])passList.toArray(new String[passList.size()]);
                reqMap.put("flowIdList", arr);
                reqMap.put("dateUpdate", new Date());
                
                resultData = contractService.insertNewDecorationRecord(reqMap);
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            resultData.setReturnMsg("根据flowId插入门店装修信息失败！");
            logger.error("Contract", "ContractContrller", "insertNewDecorationRecord", "",
                    -1, "", "手动获取合同状态、定时获取合同状态更新门店装修数据！", e);
        }
	    return resultData.toString();
	}
	
	/**
	 * 根据OA flowId 查询保证金不等于0的合同
	 * @param flowId
	 * @return 合同信息
	 */
	@RequestMapping(value = "/get/{flowId}", method = RequestMethod.GET)
	public String getDepoistNozeroCtrctByFlowId(@PathVariable String flowId) {
		// 构建返回
		ResultData<ContractDto> resultData = new ResultData<ContractDto>();
		try {
			Contract contract = contractService.getDepoistNozeroCtrctByFlowId(flowId);
			ContractDto dto = new ContractDto();
			if(null != contract) {
				BeanUtils.copyProperties(contract, dto);
				resultData.setReturnData(dto);
			}
		} catch (Exception e) {
			resultData.setFail();
			resultData.setReturnMsg("根据flowID获取合同信息失败！");
			logger.error("Contract", "ContractContrller", "getContractInfoByFlowId", "",
					-1, "", "根据flowID获取合同信息失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 
	* 获取合同门店关联表的业绩撤销状态
	* @param contractId
	* @return
	 */
    @RequestMapping(value = "/contractStoreIsCancel/{contractId}", method = RequestMethod.GET)
    public String getContractStoreIsCancel(@PathVariable Integer contractId) {
        // 构建返回
        ResultData<String> resultData = new ResultData<String>();
        try {
            resultData = contractService.getContractStoreIsCancel(contractId);
        } catch (Exception e) {
            resultData.setFail();
            resultData.setReturnMsg("获取合同门店关联表的业绩撤销状态失败！");
            logger.error("Contract", "ContractContrller", "getContractStoreIsCancel", "",
                    -1, "", "获取合同门店关联表的业绩撤销状态失败！", e);
        }
        return resultData.toString();
    }
    
    /**
     * 
    * 获取合同剩余未分账保证金-用于校验
    * @param contractId
    * @return
     */
    @RequestMapping(value = "/checkRestDeposit/{contractId}", method = RequestMethod.GET)
    public String checkRestDeposit(@PathVariable Integer contractId) {
        // 构建返回
        ResultData<String> resultData = new ResultData<String>();
        try {
            resultData = contractService.checkRestDeposit(contractId);
        } catch (Exception e) {
            resultData.setFail();
            resultData.setReturnMsg("获取合同剩余未分账保证金-用于校验失败！");
            logger.error("Contract", "ContractContrller", "checkRestDeposit", "",
                    -1, "", "获取合同剩余未分账保证金-用于校验失败！", e);
        }
        return resultData.toString();
    }

    //Add by WangLei 2017/04/07 Start
    /**
     * 获取原合同信息
     * @param storeId
     * @return
     */
    @RequestMapping(value ="/depositFee/{storeId}/{originalContractNo}",method = RequestMethod.GET)
    public String getOrgContractInfo(@PathVariable Integer storeId,@PathVariable String originalContractNo)
    {
    	// 构建返回    	
    	 ResultData<ContractDto> resultData = new ResultData<ContractDto>();
        try 
        {
        	ContractDto dto=new ContractDto();
        	ContractStore cs  = contractService.getOrgContractInfo(storeId,originalContractNo);
			BeanUtils.copyProperties(cs, dto);
        	resultData.setReturnData(dto);
        }
      
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getRefundAmount", "", -1, "", "根据门店Id,合同Id查询到账保证金", e);
        }
        
        return resultData.toString();
    }
    
    //Add by WangLei 2017/04/07 End
    
    //Add By GUOPENGFEI 2017/04/24 Start
    @RequestMapping(value = "/Opinions/{flowId}", method = RequestMethod.GET)
    public String getOaOpinions(@PathVariable String flowId) throws Exception
    {
        List<OaContractApprovalInfoDto> dto = null;
    	// 构建返回    	
   	 	ResultData<List<OaContractApprovalInfoDto>> resultData = new ResultData<List<OaContractApprovalInfoDto>>();
    	
    	try
    	{
    		dto =contractService.getOaOpinions(flowId);
    		int cnt = dto.size();
    		if(cnt == 0)
    		{
    			resultData.setTotalCount("0");
    		}
    		else
    		{
    			resultData.setTotalCount(String.valueOf(cnt));
    			resultData.setReturnData(dto);
    		}
    		
    	}
    	catch(Exception e)
    	{
            logger.error("Contract", "ContractContrller", "getOaOpinions", "", -1, "", "调OA获取审核批注原因错误", e);
            resultData.setFail();
            
    	}
    	return resultData.toString();
    	
    }
    
    //Add By GUOPENGFEI 2017/04/24 End
	
    //Add By GuoPengFei 2017/05/25 合规性 start
    /**
     * 根据门店ID取得该门店审核通过的B和A2B合同信息
     * @param storeid 门店ID
     * @return
     * @throws Exception
     */    
    @RequestMapping(value = "/storeNewestContract/{storeId}", method = RequestMethod.GET)
    public String getContractByStoreId(@PathVariable Integer storeId)
    {
        //构建返回
        ResultData<Contract> resultData = new ResultData<Contract>();
        
        
        try
        {
            resultData = contractService.getContractByStoreId(storeId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getContractByStoreId", "", -1, "", "根据门店ID取得该门店审核通过的B和A2B合同信息失败", e);
        }
        
        return resultData.toString();
    } 
    
    
    /**
     * 根据合同No查找该合同门店的乙转甲变更单号
     * @param contractNo 合同编号
     * @return
     */
    @RequestMapping(value = "/B2AChangeNo/{contractNo}", method = RequestMethod.GET)
    public String getcontractB2AChangeNo(@PathVariable String contractNo)
    {
        //构建返回
        ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();
        
        
        try
        {
            resultData = contractService.getcontractB2AChangeNo(contractNo);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getcontractB2AChangeNo", "", -1, "", "根据合同No查找该合同门店的乙转甲变更单号", e);
        }
        
        return resultData.toString();
    } 
    
    
    /** 
     * 根据flowId 更新 合同里的 公司地址
     * @param param
     * @return
     */
    @RequestMapping(value = "/contractcompanyadress", method = RequestMethod.POST)
    public String updateContractCompanyAdressByFlowId(@RequestBody String contractJson)
    {
        //构建返回
        ResultData<Contract> resultData = new ResultData<Contract>();
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractJson, ContractInfoDto.class);
        try
        {
            int count = contractService.updateContractCompanyAdressByFlowId(contractInfoDto);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "updateContractStatusByFlowId", "", -1, "", "根据flowId更新 合同里的 公司地址失败", e);
        }
        return resultData.toString();
    }
    
    //Add By GuoPengFei 2017/05/25 合规性 end    
    
    @RequestMapping(value = "/getByNo/{contractNo}", method = RequestMethod.GET)
    public String getContractByNo(@PathVariable String contractNo){
        ResultData<Contract> result = new ResultData<>();
        
        try {
            Contract contract = contractService.getContractByNo(contractNo);
            result.setReturnData(contract);
        }catch(Exception e) {
            result.setFail();
            logger.error("Contract", "ContractContrller", "getContractByNo", "", -1, "", "根据合同编号查询合同信息", e);
        }
        
        return result.toString();
    }

    @RequestMapping(value = "/selectNewestContract/{storeId}", method = RequestMethod.GET)
    public String selectNewestContract(@PathVariable Integer storeId){
        ResultData<Contract> result = new ResultData<>();

        try {
            Contract contract;
            contract = contractService.selectNewestContract(storeId);
            result.setReturnData(contract);
        }catch(Exception e) {
            result.setFail();
            logger.error("Contract", "ContractContrller", "selectNewestContract", "", -1, "", "根据门店ID查询最新合同信息", e);
        }

        return result.toString();
    }

    @RequestMapping(value = "/selectNewestContractByCompanyId/{companyId}", method = RequestMethod.GET)
    public String selectNewestContractByCompanyId(@PathVariable Integer companyId){
        ResultData<List<Contract>> result = new ResultData<>();

        try {
            List<Contract> contract;
            contract = contractService.selectNewestContractByCompanyId(companyId);
            result.setReturnData(contract);
        }catch(Exception e) {
            result.setFail();
            logger.error("Contract", "ContractContrller", "selectNewestContractByCompanyId", "", -1, "", "根据公司ID查询最新合同信息异常", e);
        }

        return result.toString();
    }
    
    /**
     * 【描述】: 上传补充协议
     *
     * @author yinkun
     * @date: 2018年2月28日 下午1:58:07
     * @param contractId
     * @param fileRecordMainIds
     * @return
     */
    @RequestMapping(value = "/uploadAdditional/{contractId}/{fileRecordMainIds}", method = RequestMethod.GET)
    public String uploadAdditional(@PathVariable Integer contractId,@PathVariable String fileRecordMainIds){
        ResultData<Contract> result = new ResultData<>();
        
        try {
            contractService.uploadAdditional(contractId,fileRecordMainIds);
        }catch(Exception e) {
            result.setFail();
            logger.error("Contract", "ContractContrller", "uploadAdditional", "", -1, "", "上传补充协议异常", e);
        }
        
        return result.toString();
    }
    /** 
     * 运营变更合同状态并补记录
     * @param param
     * @return
     */
    @RequestMapping(value = "operateChangeCt", method = RequestMethod.PUT)
    public String operateChangeCt(@RequestBody String contractInfoDtoJson) {
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        int count = 0;
        try {
            count = contractService.operateChangeCt(contractInfoDto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "operateChangeCt", "", 0, "", "状态变更失败", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }



    /**
     * 更新门店续签状态
     * @param contractId
     * @return
     */
    @RequestMapping(value = "/updateStoreReNewFlag/{contractId}", method = RequestMethod.GET)
    public String updateStoreReNewFlag(@PathVariable Integer contractId)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();

        int count = 0;
        try{
            count = contractService.updateStoreReNewFlag(contractId);
            resultData.setReturnData(count);
        }
        catch (Exception e){
            logger.error("Contract", "ContractController", "updateStoreReNewFlag", "", 0, "", "更新门店续签状态", e);
            resultData.setFail();
        }

        return resultData.toString();
    }


    /**
     * 查询合同到期时间
     * @param storeId
     * @return
     */
    @RequestMapping(value = "/selectDateLifeEnd/{storeId}", method = RequestMethod.GET)
    public String selectDateLifeEnd(@PathVariable Integer storeId)
    {
        //构建返回
        ResultData<Contract> resultData = new ResultData<Contract>();

        Contract contract = null;
        try{
            contract = contractService.selectDateLifeEnd(storeId);
            resultData.setReturnData(contract);
        }
        catch (Exception e){
            logger.error("Contract", "ContractController", "selectDateLifeEnd", "", 0, "", "查询合同到期时间", e);
            resultData.setFail();
        }

        return resultData.toString();
    }
}
