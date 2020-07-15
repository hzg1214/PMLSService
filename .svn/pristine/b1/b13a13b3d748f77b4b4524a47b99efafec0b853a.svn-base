package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.store.service.StoreDepositService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by haidan on 2018/3/23.
 */
@RestController
@RequestMapping(value = "storeDeposit")
public class StoreDepositController extends BaseController{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "storeDepositService")
    private StoreDepositService storeDepositService;

    /**
     *校验门店是否有审核中的保证金收款或退款记录
     * @param storeNo
     * @return
     */
    @RequestMapping(value = "/checkStoreDepositLock/{storeNo}", method = RequestMethod.GET)
    public String checkStoreDepositLock(@PathVariable String storeNo)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        try
        {
            Integer result = storeDepositService.checkStoreDepositLock(storeNo);
            resultData.setReturnData(result);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "StoreDepositController", "checkStoreDepositLock", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 校验门店是否有未退款，在途保证金
     * @param storeNo
     * @return
     */
    @RequestMapping(value = "/checkStoreDeposit/{param}", method = RequestMethod.GET)
    public String checkStoreDeposit(@PathVariable("param") String param)
    {
        //构建返回
        ResultData<Map<String,Object>> resultData = new ResultData<>();
        Map<String, Object> querryMap = JsonUtil.parseToObject(param, Map.class);
        try
        {
            Map<String,Object> result = storeDepositService.checkStoreDeposit(querryMap);
            resultData.setReturnData(result);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "StoreDepositController", "checkStoreDeposit", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 新门店保证金开关
     * @param cityNo
     * @return
     */
    @RequestMapping("/openFlag/{cityNo}")
    public String openFlag(@PathVariable("cityNo") String cityNo) {
        //构建返回
        ResultData<String> resultData = new ResultData<>();
        try
        {
            String flag = storeDepositService.getNewDepositOpenFlagByCityNo(cityNo);
            resultData.setReturnData(flag);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("storeDeposit", "StoreDepositController", "openFlag", "", null, "", "", e);
        }
        return resultData.toString();
    }
}
