package cn.com.eju.deal.storeRelocation.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.storeRelocation.model.StoreRelocationDto;
import cn.com.eju.deal.storeRelocation.service.StoreRelocationService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("storeRelocation")
public class StoreRelocationController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreRelocationService storeRelocationService;
    /**
     * 新增初始化页面，根据合同及门店id查询信息
     * @return
     */
    @RequestMapping(value = "/getContractAndStoreInfo/{storeId}/{contractId}", method = RequestMethod.GET)
    public String getContractAndStoreInfo(@PathVariable Integer storeId,@PathVariable Integer contractId){
        ResultData<StoreRelocationDto> resultData = new ResultData<>();
        try{
        	StoreRelocationDto storeRelocation = storeRelocationService.getContractAndStoreInfo(storeId,contractId);
            resultData.setReturnData(storeRelocation);
        }catch (Exception e){
            resultData.setFail("合同及门店信息查询失败！");
            logger.error("storeRelocation", "StoreRelocationController",
                    "getContractAndStoreInfo", "contractId="+contractId+",storeId="+storeId, -1, "", "合同及门店信息查询失败！", e);
        }
        return resultData.toString();
    }
    
    /**
     * 保存门店迁址申请
     */
	@RequestMapping(value = "/saveStoreRelocation",method = RequestMethod.POST)
    public ResultData<?> saveStoreRelocation(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = storeRelocationService.saveStoreRelocation(reqMap);
        }catch (Exception e){
            resultData.setFail("保存门店迁址申请失败！");
            logger.error("storeRelocation", "StoreRelocationController",
                    "saveStoreRelocation", param, -1, "", "保存门店迁址申请失败！", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/getStoreRelocationById/{id}", method = RequestMethod.GET)
    public String getStoreRelocationById(@PathVariable Integer id){
        ResultData<StoreRelocationDto> resultData = new ResultData<>();
        try{
        	StoreRelocationDto changeDto = storeRelocationService.getStoreRelocationById(id);
            resultData.setReturnData(changeDto);
        }catch (Exception e){
            resultData.setFail("查询门店迁址失败");
            logger.error("contractChangeNew", "ContractChangeNewController",
                    "getStoreRelocationById", "id="+id, -1, "", "查询门店迁址失败！", e);
        }

        return resultData.toString();
    }
    /**
     * 提交OA审核
     * @param reqMap
     */
	@RequestMapping(value = "/storeRelocationSubmitOa",method = RequestMethod.POST)
    public String storeRelocationSubmitOa(@RequestBody String param){

        ResultData<String> resultData = new ResultData<>();
        try{
            Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
            resultData = storeRelocationService.storeRelocationSubmitOa(map);
        }catch (Exception e){
			if(resultData == null){
					resultData = new ResultData();
			}
            resultData.setFail("门店迁址提交异常");
            logger.error("storeRelocation", "StoreRelocationController", "storeRelocationSubmitOa", param, null, "", "门店迁址提交失败", e);
        }

        return resultData.toString();
    }
	@RequestMapping(value = "/checkDecorationStatus/{id}", method = RequestMethod.GET)
    public String checkDecorationStatus(@PathVariable int id){
        //构建返回
        ResultData<String> resultData = new ResultData<>();
        try{
            String str = storeRelocationService.checkDecorationStatus(id);
            resultData.setReturnData(str);
        }catch (Exception e){
            resultData.setFail();
            logger.error("storeRelocation", "StoreRelocationController", "checkDecorationStatus", "", null, "", "check门店装修状态异常", e);
        }
        return resultData.toString();
    }
	@RequestMapping(value = "/checkStoreAddress/{param}", method = RequestMethod.GET)
	public String checkStoreAddress(@PathVariable String param){
		//构建返回
		ResultData<String> resultData = new ResultData<>();
		try{
			Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
			String str = storeRelocationService.checkStoreAddress(queryParam);
			resultData.setReturnData(str);
		}catch (Exception e){
			resultData.setFail();
			logger.error("storeRelocation", "StoreRelocationController", "checkStoreAddress", "", null, "", "check门店地址失败", e);
		}
		return resultData.toString();
	}
}
