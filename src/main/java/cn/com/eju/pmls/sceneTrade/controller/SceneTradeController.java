package cn.com.eju.pmls.sceneTrade.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.sceneTrade.dto.CustomVaildDet;
import cn.com.eju.pmls.sceneTrade.dto.CustomVaildLeg;
import cn.com.eju.pmls.sceneTrade.service.SceneTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "sceneTrade")
public class SceneTradeController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Autowired
    SceneTradeService sceneTradeService;

    @RequestMapping(value = "/getCustomLegList/{param}", method = RequestMethod.GET)
    public String getCustomLegList(@PathVariable String param) {
        //构建返回
        ResultData<List<CustomVaildLeg>> resultData = new ResultData<List<CustomVaildLeg>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = sceneTradeService.getCustomLegList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("sceneTrade", "SceneTradeController", "getCustomLegList", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getCustomDetList/{param}", method = RequestMethod.GET)
    public String getCustomDetList(@PathVariable String param) {
        //构建返回
        ResultData<List<CustomVaildDet>> resultData = new ResultData<List<CustomVaildDet>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = sceneTradeService.getCustomDetList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("sceneTrade", "SceneTradeController", "getCustomDetList", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }


}
