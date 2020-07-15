package cn.com.eju.deal.storeContribution.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.storeContribution.service.StoreContributionService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "storeContribution")
public class StoreContributionController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "storeContributionService")
    StoreContributionService storeContributionService;

    @RequestMapping(value = "query/{param}", method = RequestMethod.GET)
    public String query(@PathVariable String param){


        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<?> resultData = new ResultData<>();

        try {
            resultData = storeContributionService.query(queryParam);
        } catch (Exception ex) {
            resultData.setFail();
            logger.error("storeContribution", "StoreContributionController", "query", "", 0, "", "门店贡献分析表失败查询失败", ex);
        }
        return resultData.toString();
    }
}
