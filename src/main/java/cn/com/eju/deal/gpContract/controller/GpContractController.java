package cn.com.eju.deal.gpContract.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContract.model.GpContractDto;
import cn.com.eju.deal.gpContract.service.GpContractService;
import cn.com.eju.deal.user.model.ExchangeCenter;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2018/8/30.
 */
@RestController
@RequestMapping(value = "gpContract")
public class GpContractController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name="gpContractService")
    private GpContractService gpContractService;

    /**
     * 新增
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto){
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        GpContractDto gpContract =  JsonUtil.parseToObject(jsonDto,GpContractDto.class);
        try{
            resultData = gpContractService.create(gpContract);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "create", "", -1, "", "创建失败", e);
        }
        return resultData.toString();
    }

    /**
     * 编辑
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "u", method = RequestMethod.POST)
    public String update(@RequestBody String jsonDto){
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContractDto gpContractDto =  JsonUtil.parseToObject(jsonDto,GpContractDto.class);
        try {
            resultData = gpContractService.updateInfo(gpContractDto);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "update", "", -1, "", "更新失败", e);
        }
        return resultData.toString();
    }

    /**
     * 只更新公盘合同表信息
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "brief", method = RequestMethod.PUT)
    public String updateBrief(@RequestBody String jsonDto){
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContractDto gpContractDto =  JsonUtil.parseToObject(jsonDto,GpContractDto.class);
        try {
            resultData = gpContractService.updateBrief(gpContractDto);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "updateBrief", "", -1, "", "更新失败", e);
        }
        return resultData.toString();
    }


    /**
     * 公盘更新状态
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "status", method = RequestMethod.PUT)
    public String updateStatus(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContractDto gpContractDto =  JsonUtil.parseToObject(jsonDto,GpContractDto.class);
        try {
            resultData = gpContractService.updateStatus(gpContractDto);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "updateStatus", "", -1, "", "公盘状态更新失败", e);
        }
        return resultData.toString();
    }

    /**
     * 合同作废后，释放门店
     * @param
     * @return
     */
    @RequestMapping(value = "/invalid/{id}", method = RequestMethod.DELETE)
    public String invalid(@PathVariable int id)
    {
        //构建返回
        ResultData<ContractDto> resultData = new ResultData<ContractDto>();

        try
        {
            gpContractService.invalid(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "invalid", "", -1, "", "合同作废后，释放门店", e);
        }

        return resultData.toString();
    }

    /**
     * 列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param){
        //构建返回
        ResultData<List<GpContract>> resultData = new ResultData<>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = gpContractService.queryList(queryParam);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "queryList", "", -1, "", "获取列表失败", e);
        }
        return resultData.toString();
    }

    /**
     * 详情
     * @param id--编号
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id){
        //构建返回
        ResultData<GpContractDto> resultData = new ResultData<>();
        try {
            resultData = gpContractService.getById(id);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "getById", "", -1, "", "获取详情失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "flowId", method = RequestMethod.PUT)
    public String updateFlowIdById(@RequestBody String jsonDto)
    {
//构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContractDto gpContract =  JsonUtil.parseToObject(jsonDto,GpContractDto.class);
        try {
            resultData = gpContractService.updateFlowId(gpContract);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "updateFlowIdById", "", -1, "", "更新失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "audit", method = RequestMethod.PUT)
    public String audit(@RequestBody String jsonDto)
    {
//构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContractDto gpContract =  JsonUtil.parseToObject(jsonDto,GpContractDto.class);
        try {
            resultData = gpContractService.audit(gpContract);
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "audit", "", -1, "", "审核失败", e);
        }
        return resultData.toString();
    }
    /** 
     * 根据公司ID查询合同状态为未签，审核未通过的公盘合同
     */
    @RequestMapping(value = "/getGpContractByCompanyId/{companyId}", method = RequestMethod.GET)
    public String getGpContractByCompanyId(@PathVariable Integer companyId) {
        //构建返回
        ResultData<List<GpContract>> resultData = new ResultData<List<GpContract>>();
        try {
            resultData = gpContractService.getGpContractByCompanyId(companyId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "getGpContractByCompanyId", "", 0, "", "根据公司id查询公盘出错", e);
        }
        return resultData.toString();
    }
    /** 
     * 修改公司修改公盘合同里的公司信息
     * @param param
     * @return
     */
    @RequestMapping(value = "updateByGpContractId", method = RequestMethod.POST)
    public String updateByGpContractId(@RequestBody String jsonDto) {
        ResultData<Integer> result = new ResultData<Integer>();
        //构建返回
        ResultData<GpContractDto> resultData = new ResultData<GpContractDto>();
        GpContractDto gpContractDto = JsonUtil.parseToObject(jsonDto, GpContractDto.class);
        try{
        	result = gpContractService.updateByGpContractId(gpContractDto);
        	Integer count = result.getReturnData();
            if(null == count || (count != 0 && count != 1)){
                resultData.setFail();
            }
        }catch (Exception e){
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "updateById", "", -1, "", "更新失败", e);
        }
        return resultData.toString();
    }
    /** 
     * 修改门店信息修改公盘合同里的门店信息
     * @param param
     * @return
     */
    @RequestMapping(value = "updateByGpStoreId", method = RequestMethod.POST)
    public String updateByGpStoreId(@RequestBody String param) {
    	//构建返回
    	ResultData resultData = null;
    	try{
            resultData = this.gpContractService.updateByGpStoreId(param);
        } catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("GpContract", "GpContractController", "updateByGpStoreId", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 运营维护根据公司id查公盘合同
     * @param companyId
     * @return
     */
    @RequestMapping(value = "/selectNewestGpContractByCompanyId/{companyId}", method = RequestMethod.GET)
    public String selectNewestGpContractByCompanyId(@PathVariable Integer companyId){
        ResultData<List<GpContract>> result = new ResultData<>();

        try {
            List<GpContract> contract;
            contract = gpContractService.selectNewestGpContractByCompanyId(companyId);
            result.setReturnData(contract);
        }catch(Exception e) {
            result.setFail();
            logger.error("GpContract", "GpContractController", "selectNewestGpContractByCompanyId", "", -1, "", "根据公司ID查询最新公盘合同信息", e);
        }

        return result.toString();
    }
    /**
     * 运营维护根据门店id查公盘合同
     * @param storeId
     * @return
     */
    @RequestMapping(value = "/selectNewestGpContract/{storeId}", method = RequestMethod.GET)
    public String selectNewestGpContract(@PathVariable Integer storeId){
        ResultData<GpContract> result = new ResultData<>();
        try {
        	GpContract gpContract;
        	gpContract = gpContractService.selectNewestGpContract(storeId);
            result.setReturnData(gpContract);
        }catch(Exception e) {
            result.setFail();
            logger.error("Contract", "ContractContrller", "selectNewestContract", "", -1, "", "根据门店ID查询最新合同信息", e);
        }

        return result.toString();
    }
    /**
     * 保存客户调整信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/saveBankInfoAdjut", method = RequestMethod.POST)
    public ResultData saveBankInfoAdjut(@RequestBody String param){
    	ResultData resultData = null;
    	Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
    	try {
    		resultData = gpContractService.saveBankInfoAdjut(reqMap);
    	} catch (Exception e) {
    		if(resultData == null){
    			resultData = new ResultData();
    			resultData.setFail("保存银行信息变更异常！");
    		}
    		logger.error("CRM", "GpContractController", "saveBankInfoAdjut", reqMap.toString(), null, "", "保存银行信息变更异常", e);
    	}
    	return resultData;
    }
    /**
     * 获取业绩人的登陆中心
     * @param userId
     * @return
     */
    @RequestMapping(value = "/getLinkUserCenter/{userId}", method = RequestMethod.GET)
    public String getLinkUserCenter(@PathVariable Integer userId) {
    	 //构建返回
        ResultData<List<ExchangeCenter>> resultData = new ResultData<List<ExchangeCenter>>();
    	try {
    		List<ExchangeCenter> centerList = gpContractService.getLinkUserCenter(userId);
			resultData.setReturnData(centerList);
		} catch (Exception e) {
			logger.error("CRM", "GpContractController", "getLinkUserCenter", "", 0, "", "获取用户所属中心 ", e);
	        resultData.setFail();
		}
        return resultData.toString();
    }

    /**
     * 运营变更合同状态并补记录
     * @param gpContractDtoJson
     * @return
     */
    @RequestMapping(value = "operateChangeCt", method = RequestMethod.PUT)
    public String operateChangeCt(@RequestBody String gpContractDtoJson) {
        //构建返回
        ResultData<GpContractDto> resultData = new ResultData<GpContractDto>();
        GpContractDto gpContractDto = JsonUtil.parseToObject(gpContractDtoJson, GpContractDto.class);
        int count = 0;
        try {
            count = gpContractService.operateChangeCt(gpContractDto);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpContract", "GpContractContrller", "operateChangeCt", "", 0, "", "状态变更失败", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryDeposit/{param}", method = RequestMethod.GET)
    public String queryDeposit(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<BigDecimal> resultData =new ResultData<BigDecimal>();
        try
        {
            resultData = gpContractService.queryDeposit(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("contacts", "ContactsController", "queryDeposit", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryFileList/{param}", method = RequestMethod.GET)
    public String queryFileList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<GpContractDto> resultData =new ResultData<GpContractDto>();
        try
        {
            resultData = gpContractService.queryFileList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("contacts", "ContactsController", "queryFileList", "", null, "", "", e);
        }
        return resultData.toString();
    }

}
