package cn.com.eju.pmls.borrowMoneyFrameContract.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.pmls.borrowMoneyFrameContract.model.BorrowMoneyFrameContractDto;
import cn.com.eju.pmls.borrowMoneyFrameContract.service.BorrowMoneyFrameContractService;

/**
* @Title: FrameContractController
* @Description: 联动框架合同
 */
@RestController
@RequestMapping(value = "borrowMoneyFrameContract")
public class BorrowMoneyFrameContractController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "borrowMoneyFrameContractService")
    private BorrowMoneyFrameContractService borrowMoneyFrameContractService;
    /**
     * 查询借佣框架协议列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getList/{param}", method = RequestMethod.GET)
    public ResultData getBorrowMoneyFrameContractList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = borrowMoneyFrameContractService.getBorrowMoneyFrameContractList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("PMLS","BorrowMoneyFrameContractContrller", "getBorrowMoneyFrameContractList", reqMap.toString(), null, "", "查询借佣框架协议列表list异常", e);
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
            resultData = this.borrowMoneyFrameContractService.getBriefById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("PMLS", "BorrowMoneyFrameContractContrller", "getBriefById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 根据公司编号获取最新审核通过的框架合同的银行相关信息
     */
    @RequestMapping(value="/getOldBMFCBankInfo/{param}",method= RequestMethod.GET)
	public ResultData<?> getOldBMFCBankInfo(@PathVariable String param){
    	ResultData<?> resultData = new ResultData<>();
    	try{
    		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = borrowMoneyFrameContractService.getOldBMFCBankInfo(queryParam);
    	}catch(Exception e){
    		 logger.error("PMLS", "BorrowMoneyFrameContractContrller", "getOldBMFCBankInfo", "parameter = " + param, null, null, "根据公司编号获取最新审核通过的框架合同的银行相关信息", e);
             resultData.setFail();
    	}
		return resultData;
	 }
    
    /**
     * 查询新增框架合同时候选择公司列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getBorrowMoneyFrameContractCompanyList/{param}", method = RequestMethod.GET)
    public String getBorrowMoneyFrameContractCompanyList(@PathVariable String param)
    {
    	//构建返回
    	ResultData<List<CompanyDto>> resultData = new ResultData<List<CompanyDto>>();
    	try{
    		Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = this.borrowMoneyFrameContractService.getBorrowMoneyFrameContractCompanyList(queryParam);
    	}
    	catch (Exception e)
    	{
    		logger.error("PMLS", "BorrowMoneyFrameContractContrller", "getBorrowMoneyFrameContractCompanyList", "", 0, "", "新增框架合同时候选择公司列表 ", e);
    		resultData.setFail();
    	}
    	return resultData.toString();
    }
    
    /**
     * 
     * desc:开户行列表
     * 2020年4月28日
     */
    @RequestMapping(value = "/getBankInfoList/{param}", method = RequestMethod.GET)
    public ResultData getBankInfoList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = borrowMoneyFrameContractService.getBankInfoList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("PMLS", "BorrowMoneyFrameContractContrller", "getBankInfoList", reqMap.toString(), null, "", "查询借佣框架协议开户行信息列表异常", e);
        }
        return resultData;
    }
    
    /**
     * desc:创建借佣框架协议
     * 2020年4月28日
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultData<?> create(@RequestBody String param)
    {
    	Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
    	BorrowMoneyFrameContractDto borrowMoneyFrameContractDto = JsonUtil.parseToObject(param, BorrowMoneyFrameContractDto.class);
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            int count = borrowMoneyFrameContractService.create(borrowMoneyFrameContractDto);
            if (count <= 0) {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
        	e.printStackTrace();
            resultData.setFail();
            logger.error("PMLS", "BorrowMoneyFrameContractContrller", "create", param, 0, "", "创建失败", e);

        }
        return resultData;
    }
    
    /**
     * desc:更新
     * 2020年4月29日
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public void update(@RequestBody String param){
        try
        {
        	BorrowMoneyFrameContractDto borrowMoneyFrameContractDto = JsonUtil.parseToObject(param, BorrowMoneyFrameContractDto.class);
            borrowMoneyFrameContractService.updateBorrowMoneyFrameContract(borrowMoneyFrameContractDto);
        }catch (Exception e){
        	e.printStackTrace();
            logger.error("PMLS", "BorrowMoneyFrameContractContrller", "update", "", null, "", "", e);
        }
    }
    
    /**
     * desc:提交oa审核
     * 2020年5月7日
     */
    @RequestMapping(value = "/submitToOA/{param}", method = RequestMethod.GET)
    public String submitToOA(@PathVariable String param){
        ResultData resultData = new ResultData();
        Map<String, String> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = borrowMoneyFrameContractService.submitToOA(reqMap);
        }catch (Exception e){
            resultData.setFail("系统未知异常");
            logger.error("PMLS", "BorrowMoneyFrameContractContrller", "submitToOA", param, null, "", "借用框架协议提交OA异常", e);
        }
        return resultData.toString();
    }
    
    /**
     * 
     * desc:移除
     * 2020年5月8日
     */
    @RequestMapping(value="/remove/{param}",method= RequestMethod.GET)
	public ResultData<?> remove(@PathVariable String param){
    	ResultData<?> resultData = new ResultData<>();
    	try{
    		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = borrowMoneyFrameContractService.remove(queryParam);
    	}catch(Exception e){
    		 logger.error("PMLS", "BorrowMoneyFrameContractContrller", "remove", "parameter = " + param, null, null, "借用框架协议，编辑-移除失败！", e);
             resultData.setFail();
    	}
		return resultData;
	 }
}
