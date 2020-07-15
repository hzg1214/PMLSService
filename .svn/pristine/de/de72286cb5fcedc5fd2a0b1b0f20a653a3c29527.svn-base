package cn.com.eju.deal.houseLinkage.linkConversionRate.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.houseLinkage.linkConversionRate.LinkConversionRateDto;
import cn.com.eju.deal.houseLinkage.linkConversionRate.service.LinkConversionRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/8/28.
 */
@RestController
@RequestMapping(value = "linkConversionRate")
public class LinkConversionRateController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Autowired
    private LinkConversionRateService linkConversionRateService;

    /**
     * 查询联动转化率列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<LinkConversionRateDto>> resultData = new ResultData<>();

        try {
            resultData = linkConversionRateService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("linkConversionRate", "LinkConversionRateController", "queryList", param, null, "", "查询联动转化率列表失败", e);
        }
        return resultData.toString();
    }
}
