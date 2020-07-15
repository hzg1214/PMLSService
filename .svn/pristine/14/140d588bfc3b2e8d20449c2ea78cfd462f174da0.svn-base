package cn.com.eju.deal.houseLinkage.storedepositSerial.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.storeDepositDeatil.service.StoreDepositDeatilService;
import cn.com.eju.deal.houseLinkage.storedepositSerial.service.StoreDepositSerialService;

/**
* @Title: StoreDepositDeatilController
* @Description: 保证金流水
 */
@RestController
@RequestMapping(value = "storeDepositSerial")
public class StoreDepositSerialController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StoreDepositSerialService storeDepositSerialService;
    /**
     * 查询保证金明细列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryStoreDepositSerialList/{param}", method = RequestMethod.GET)
    public ResultData queryStoreDepositSerialList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeDepositSerialService.queryStoreDepositSerialList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "StoreDepositSerialController", "queryStoreDepositSerialList", reqMap.toString(), null, "", "查询保证金明细异常", e);
        }
        return resultData;
    }
    /**
     * 查询城市节点
     * @param param
     * @return
     */
    @RequestMapping(value = "queryCityList", method = RequestMethod.GET)
    public String queryCityList() {
        ResultData resultData = new ResultData<>();
        try {
            resultData = storeDepositSerialService.queryCityList();
        } catch (Exception e) {
            logger.error("CRM", "StoreDepositSerialController", "queryCityList", "", 0, "", "查询城市异常", e);
            resultData.setFail();
        }

        return resultData.toString();
    }
    /** 
     * 根据城市CityNo获取其核算主体
     */
     @RequestMapping(value = "/queryAccountProject/", method = RequestMethod.GET)
     public String queryAccountProject() {
         
         //构建返回
         ResultData resultData = new ResultData();
         try{
             resultData = storeDepositSerialService.queryAccountProject();
         }
         catch (Exception e){
             logger.error("CRM","StoreDepositSerialController","queryAccountProject","", 0,"", "获取其核算主体失败",e);
             resultData.setFail();
         }
         
         return resultData.toString();
     }
    
}
  