package cn.com.eju.deal.store.controller;

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
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.store.service.StoreDecorationService;

/**
 * 门店装修
 * @author  wushuang
 * @date 2016年8月16日 下午3:48:10
 */
@RestController
@RequestMapping(value = "storeDecoration")
public class StoreDecorationController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "storeDecorationService")
    private StoreDecorationService storeDecorationService;
    
    /** 
     * 初始化查询门店装修记录
     * @return
     */
    @RequestMapping(value = "qDecoration/{param}", method = RequestMethod.GET)
    public String getStoreDecorationRecord(@PathVariable("param") String param)
    {
        Map<String, Object> querryMap = JsonUtil.parseToObject(param, Map.class);
        //转成Integer格式
        Integer storeId = Integer.valueOf((String)querryMap.get("storeId"));
        querryMap.replace("storeId", storeId);
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<>();
        //根据storeId查询装修记录
        try
        {
            resultData = storeDecorationService.getStoreDecoration(querryMap);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "getStoreDecorationRecord", "parameter = " + querryMap, null, null, "初始化查询门店装修记录", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /** 
     * 新增第二次装修记录
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "cDecoration", method = RequestMethod.POST)
    public String toCreateStoreDecoration(@RequestBody String param)
    {
        ResultData<Integer> resultData = new ResultData<Integer>();
        StoreDecorationDto storeDecorationDto = JsonUtil.parseToObject(param, StoreDecorationDto.class);
        try
        {
            resultData = storeDecorationService.addDecorationRecord(storeDecorationDto);
        }
        catch (Exception e)
        {
            logger.error("Store", "StoreDecorationController", "toCreateStoreDecoration", "parameter = " + param, null, null, "再次插入门店装修记录失败", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
   
}
