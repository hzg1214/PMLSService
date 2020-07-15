package cn.com.eju.deal.frameContract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.frameContract.model.FrameContract;
import cn.com.eju.deal.frameContract.service.FrameContractService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
* @Title: FrameContractController
* @Description: 联动框架合同
 */
@RestController
@RequestMapping(value = "frameContract")
public class FrameContractController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private FrameContractService frameContractService;
    /**
     * 查询联动框架合同列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getList/{param}", method = RequestMethod.GET)
    public ResultData getFrameContractList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = frameContractService.getFrameContractList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "FrameContractController", "getFrameContractList", reqMap.toString(), null, "", "查询联动框架合同列表list异常", e);
        }
        return resultData;
    }
    /**
     * 查询框架合同详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/brief/{id}", method = RequestMethod.GET)
    public String getBriefById(@PathVariable int id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.frameContractService.getBriefById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "FrameContractController", "getBriefById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * @Title: update
     * @Description: 更新框架合同信息
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String param){
        //构建返回
    	ResultData resultData = null;
        try
        {
            resultData = this.frameContractService.updateStr(param);
        }
        catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("CRM", "FrameContractController", "update", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 查询新增框架合同时候选择公司列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getFrameContractCompanyList/{param}", method = RequestMethod.GET)
    public String getFrameContractCompanyList(@PathVariable String param)
    {
    	//构建返回
    	ResultData<List<CompanyDto>> resultData = new ResultData<List<CompanyDto>>();
    	try{
    		Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = this.frameContractService.getFrameContractCompanyList(queryParam);
    	}
    	catch (Exception e)
    	{
    		logger.error("frameContract", "FrameContractController", "getFrameContractCompanyList", "", 0, "", "新政保证金查询合同列表失败 ", e);
    		resultData.setFail();
    	}
    	return resultData.toString();
    }

    /**
     * 查询公司详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getCompanyInfoById/{id}", method = RequestMethod.GET)
    public String getCompanyInfoById(@PathVariable int id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.frameContractService.getCompanyInfoById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("store", "FrameContractController", "getCompanyInfoById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 新增框架合同
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultData<?> create(@RequestBody String param)
    {
    	Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            int count = frameContractService.create(reqMap);
            if (count <= 0) {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "FrameContractController", "create", param, 0, "", "创建", e);

        }
        return resultData;
    }

/**
     * 根据companyNo查询公司详情
     * @param companyNo
     * @return
     */
    @RequestMapping(value = "/getCompanyInfoByCompanyNo/{companyNo}", method = RequestMethod.GET)
    public String getCompanyInfoByCompanyNo(@PathVariable String companyNo){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.frameContractService.getCompanyInfoByCompanyNo(companyNo);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("store", "FrameContractController", "getCompanyInfoByCompanyNo", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 框架合同提交OA
     * @param param
     * @return
     */
    @RequestMapping(value = "/submitToOA/{param}", method = RequestMethod.GET)
    public String submitToOA(@PathVariable String param){
        ResultData resultData = new ResultData();
        Map<String, String> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.frameContractService.submitToOA(reqMap);
        }catch (Exception e){
            resultData.setFail("系统未知异常");
            logger.error("frameContract", "FrameContractController", "submitToOA", param, null, "", "框架合同提交OA异常", e);
        }
        return resultData.toString();
    }
    /***
     * 根据公司编号查询框架协议状态为未签，审核未通过
     * @param companyNo
     * @return
     **/
    @RequestMapping(value = "/getFrameContractByCompanyNo/{companyNo}", method = RequestMethod.GET)
    public String getFrameContractByCompanyNo(@PathVariable String companyNo){
        //构建返回
        ResultData resultData = new ResultData();
        try {
            resultData = frameContractService.getFrameContractByCompanyNo(companyNo);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("frameContract", "FrameContractController", "getFrameContractByCompanyNo", "", 0, "", "根据公司编号查询框架协议失败", e);
        }

        return resultData.toString();
    }
    /**
     * 更新框架协议中公司的相关信息
     * @param param
     * @return
     */
    @RequestMapping(value = "updateCompanyInfo", method = RequestMethod.POST)
    public String updateCompanyInfo(@RequestBody String param){
    	 //构建返回
    	ResultData resultData = null;
        try{
            resultData = this.frameContractService.updateCompanyInfo(param);
        } catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("frameContract", "FrameContractController", "updateCompanyInfo", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 根据员工编号获取其核算主体信息
     * @return
     */
    @RequestMapping(value = "/getUserMappingAccountProject/{userCode}", method = RequestMethod.GET)
    public String getUserMappingAccountProject(@PathVariable String userCode){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.frameContractService.getUserMappingAccountProject(userCode);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "FrameContractController", "getUserMappingAccountProject", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 根据登录人对应城市获取其核算主体信息
     * @return
     */
    @RequestMapping(value = "/getLoginCityAccountProject/{cityNo}", method = RequestMethod.GET)
    public String getLoginCityAccountProject(@PathVariable String cityNo){
    	//构建返回
    	ResultData resultData = null;
    	try{
    		resultData = this.frameContractService.getLoginCityAccountProject(cityNo);
    	}catch (Exception e){
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("CRM", "FrameContractController", "getLoginCityAccountProject", "", null, "", "", e);
    	}
    	return resultData.toString();
    }

    @RequestMapping(value = "/selectNewestContractByCompanyId/{companyId}", method = RequestMethod.GET)
    public String selectNewestContractByCompanyId(@PathVariable Integer companyId){
        ResultData<List<FrameContract>> result = new ResultData<>();

        try {
            List<FrameContract> contract;
            contract = frameContractService.selectNewestContractByCompanyId(companyId);
            result.setReturnData(contract);
        }catch(Exception e) {
            result.setFail();
            logger.error("FrameContract", "FrameContractContrller", "selectNewestContractByCompanyId", "", -1, "", "根据公司ID查询最新合同信息", e);
        }

        return result.toString();
    }
    /**
     * 运营变更合同状态并补记录
     * @param param
     * @return
     */
    @RequestMapping(value = "operateChangeCt", method = RequestMethod.PUT)
    public String operateChangeCt(@RequestBody String param) {
    	//构建返回
    	ResultData resultData = new ResultData();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        int count = 0;
        try {
            count = frameContractService.operateChangeCt(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("FrameContract", "FrameContractContrller", "operateChangeCt", "", 0, "", "状态变更失败", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }
    /**
     * 根据公司编号获取最新审核通过的框架合同的银行相关信息
     */
    @RequestMapping(value="/getOldFtBankInfo/{param}",method= RequestMethod.GET)
	public ResultData<?> getOldFtBankInfo(@PathVariable String param){
    	ResultData<?> resultData = new ResultData<>();
    	try{
    		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = frameContractService.getOldFtBankInfo(queryParam);
    	}catch(Exception e){
    		 logger.error("FrameContract", "FrameContractContrller", "getOldFtBankInfo", "parameter = " + param, null, null, "根据公司编号获取最新审核通过的框架合同的银行相关信息", e);
             resultData.setFail();
    	}
		return resultData;
	 }

    /**
     * 框架合同自动提交OA
     * @param param
     * @return
     */
    @RequestMapping(value = "/AutosubmitToOA", method = RequestMethod.GET)
    public String authosubmitToOA(){
//        ResultData resultData = new ResultData();
//        Map<String, String> reqMap = JsonUtil.parseToObject(param, Map.class);
//        try{
//            resultData = this.frameContractService.submitToOA(reqMap);
//        }catch (Exception e){
//            resultData.setFail("系统未知异常");
//            logger.error("frameContract", "FrameContractController", "submitToOA", param, null, "", "框架合同提交OA异常", e);
//        }
        return "";
    }
    //查询渠道开户行信息列表
    @RequestMapping(value = "/getBankInfoList/{param}", method = RequestMethod.GET)
    public ResultData getBankInfoList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = frameContractService.getBankInfoList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "FrameContractController", "getBankInfoList", reqMap.toString(), null, "", "查询渠道开户行信息列表异常", e);
        }
        return resultData;
    }
    //删除对公账户
    @RequestMapping(value = "/deleteBankInfo/{param}", method = RequestMethod.GET)
    public ResultData deleteBankInfo(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = frameContractService.deleteBankInfo(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "FrameContractController", "deleteBankInfo", reqMap.toString(), null, "", "删除对公账户", e);
        }
        return resultData;
    }


}
