package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.service.StoreMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "storeMap")
public class StoreMapController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private StoreMapService storeMapService;

    @RequestMapping(value = "getMapInfo/{param}", method = RequestMethod.GET)
    public String getMapInfo(@PathVariable String param) {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeMapService.getMapInfo(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("获取门店地图信息异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "getStoreCount/{param}", method = RequestMethod.GET)
    public String getStoreCount(@PathVariable String param) {
        //构建返回
        ResultData resultData = new ResultData();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeMapService.getStoreCount(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("获取门店地图信息异常", e);
        }

        return resultData.toString();
    }
    @RequestMapping(value = "getCenterPosition/{param}", method = RequestMethod.GET)
    public String getCenterPosition(@PathVariable String param) {
        //构建返回
        ResultData resultData = new ResultData();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeMapService.getCenterPosition(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("获取门店地图信息异常", e);
        }

        return resultData.toString();
    }
}
