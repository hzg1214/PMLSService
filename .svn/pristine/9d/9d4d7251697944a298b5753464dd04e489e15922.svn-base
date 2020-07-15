package cn.com.eju.deal.store.controller;

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
import cn.com.eju.deal.store.service.StoreMaintenanceService;

/**
 * 门店维护人
* @Title: StoreMaintenanceController
* @Description: 
* @date:2017年12月21日下午3:08:57
 */
@RestController
@RequestMapping(value = "storeMaintenance")
public class StoreMaintenanceController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StoreMaintenanceService storeMaintenanceService;
    
    @RequestMapping(value = "/getList/{param}", method = RequestMethod.GET)
    public ResultData getStoreMaintenanceList(@PathVariable String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = storeMaintenanceService.getStoreMaintenanceList(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "StoreMaintenanceController", "getStoreMaintenanceList", reqMap.toString(), null, "", "查询维护人管理list", e);

        }
        return resultData;
    }
    
    @RequestMapping(value = "/saveMaintenance", method = RequestMethod.POST)
    public ResultData saveMaintenance(@RequestBody String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = storeMaintenanceService.saveMaintenance(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("变更维护人异常！");
            }
            logger.error("CRM", "StoreMaintenanceController", "saveMaintenance", reqMap.toString(), null, "", "保存联动业绩调整异常", e);

        }
        return resultData;
    }
    
}
  