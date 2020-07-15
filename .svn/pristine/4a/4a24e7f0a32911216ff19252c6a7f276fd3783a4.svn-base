package cn.com.eju.deal.gpContractChange.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeDto;
import cn.com.eju.deal.gpContractChange.service.GpContractChangeService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("gpContractChange")
public class GpContractChangeController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private GpContractChangeService  gpContractChangeService;
    
    /**
	 * 根据公盘合同ID查询门店信息 -新增页面用
	 * @param contractId 公盘合同ID
	 */
	@RequestMapping(value = "/queryStoreInfoOfGpContract/{id}/{gpContractId}/{type}", method = RequestMethod.GET)
	public String queryStoreInfoOfGpContract(@PathVariable Integer id,@PathVariable Integer gpContractId,@PathVariable String type) {
		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
		try {
			// 查询操作
			resultData = gpContractChangeService.queryStoreInfoOfGpContract(id,gpContractId,type);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("gpContractChange", "GpContractChangeController",
					"queryStoreInfoOfGpContract", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	/**
     * 保存公盘合同终止申请
     */
	@RequestMapping(value = "/saveGpContractChange",method = RequestMethod.POST)
    public ResultData<?> saveGpContractChange(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = gpContractChangeService.saveGpContractChange(reqMap);
        }catch (Exception e){
            resultData.setFail("保存合同终止申请失败！");
            logger.error("gpContractChange", "GpContractChangeController",
                    "saveGpContractChange", param, -1, "", "保存合同终止申请失败！", e);
        }

        return resultData;
    }
	/**
     * 查询公盘合同终止列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getGpContractStopList/{param}", method = RequestMethod.GET)
    public String getCashBillList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = gpContractChangeService.getGpContractStopList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "CashBillController", "getCashBillList", reqMap.toString(), null, "", "查询请款单列表list异常", e);
        }
        return resultData.toString();
    }
	/**
     * 根据id查询公盘合同终止详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getGpContractStopInfoById/{id}", method = RequestMethod.GET)
    public String getGpContractStopInfoById(@PathVariable Integer id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.gpContractChangeService.getGpContractStopInfoById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("gpContractChange", "GpContractChangeController", "getGpContractStopInfoById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 公盘提交OA审核
     * @param reqMap
     */
	@RequestMapping(value = "/gpContractStopSubmitOa",method = RequestMethod.POST)
    public String gpContractStopSubmitOa(@RequestBody String param){

        ResultData<String> resultData = new ResultData<>();
        try{
            Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
            resultData = gpContractChangeService.gpContractStopSubmitOa(map);
        }catch (Exception e){
			if(resultData == null){
					resultData = new ResultData();
			}
            resultData.setFail("公盘合同终止提交异常");
            logger.error("gpContractChange", "GpContractChangeController", "gpContractStopSubmitOa", param, null, "", "公盘合同终止提交提交失败", e);
        }

        return resultData.toString();
    }
	/**
     * @Description: 作废
     */
    @RequestMapping(value = "/updateStatus", method = RequestMethod.PUT)
    public String update(@RequestBody String param){
        //构建返回
    	ResultData resultData = null;
        try{
            resultData = this.gpContractChangeService.updateStatus(param);
        }
        catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("gpContractChange", "GpContractChangeController", "updateStatus", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 运营变更合同状态并补记录
     * @param
     * @return
     */
    @RequestMapping(value = "operateChangeCt/{id}", method = RequestMethod.GET)
    public String operateChangeCt(@PathVariable Integer id) {
        //构建返回
        ResultData<GpContractChangeDto> resultData = new ResultData<GpContractChangeDto>();
        int count = 0;
        try {
            count = gpContractChangeService.operateChangeCt(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("gpContractChange", "GpContractChangeController", "operateChangeCt", "", 0, "", "状态变更失败", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }
	
}
