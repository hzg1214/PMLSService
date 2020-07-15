package cn.com.eju.deal.performanceSum.controller;


import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.performanceSum.service.PerformanceSumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Controller层
 *
 * @author tanlang
 */
@RestController
@RequestMapping(value = "performanceSum")
public class PerformanceSumController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PerformanceSumService performanceSumService;

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);

            resultData = performanceSumService.queryList(queryParam);
        } catch (Exception e) {
            logger.error("查询list异常", e);

            resultData.setFail();
        }

        return resultData.toString();
    }

    @RequestMapping(value = "queryCityList/{param}", method = RequestMethod.GET)
    public String queryCityList(@PathVariable String param) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);

            resultData = performanceSumService.queryCityList(queryParam);
        } catch (Exception e) {
            logger.error("查询城市异常", e);

            resultData.setFail();
        }

        return resultData.toString();
    }

    @RequestMapping(value = "queryCenterList/{param}", method = RequestMethod.GET)
    public String queryCenterList(@PathVariable String param) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);

            resultData = performanceSumService.queryCenterList(queryParam);
        } catch (Exception e) {
            logger.error("查询中心异常", e);

            resultData.setFail();
        }

        return resultData.toString();
    }

}
