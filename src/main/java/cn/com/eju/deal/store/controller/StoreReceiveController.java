package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.store.service.StoreReceiveService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
* @Title: StoreReceiveController
* @Description: 保证金收款
 */
@RestController
@RequestMapping(value = "storeReceive")
public class StoreReceiveController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StoreReceiveService storeReceiveService;
    
    @RequestMapping(value = "/getList/{param}", method = RequestMethod.GET)
    public String getStoreReceiveList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeReceiveService.getStoreReceiveList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "StoreReceiveController", "getStoreReceiveList", reqMap.toString(), null, "", "查询保证金收款列表list异常", e);
        }
        return resultData.toString();
    }
    
    @RequestMapping(value = "/brief/{id}", method = RequestMethod.GET)
    public String getBriefById(@PathVariable int id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.storeReceiveService.getBriefById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("store", "StoreReceiveController", "getBriefById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * @Title: update
     * @Description: 更新保证金信息
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String param){
        //构建返回
    	ResultData resultData = null;
        try
        {
            resultData = this.storeReceiveService.updateStr(param);
        }
        catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("store", "StoreReceiveController", "update", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "reverse/{param}", method = RequestMethod.GET)
    public String reverse(@PathVariable String param){
        //构建返回
        ResultData<?> resultData = null;
        try{
            Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
            resultData = this.storeReceiveService.reverse(map);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("storeReceive", "StoreReceiveController", "reverse", param, null, "", "", e);
        }
        return resultData.toString();
    }
}
  