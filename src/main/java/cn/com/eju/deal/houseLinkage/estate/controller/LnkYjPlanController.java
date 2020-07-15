package cn.com.eju.deal.houseLinkage.estate.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanDetailActDto;
import cn.com.eju.deal.houseLinkage.estate.model.PmlsCompanyYjPlan;
import cn.com.eju.deal.houseLinkage.estate.service.LnkYjPlanService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "lnkYjPlan")
public class LnkYjPlanController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "lnkYjPlanService")
    private LnkYjPlanService lnkYjPlanService;


    @RequestMapping(value = "/queryQueryYJAmount/{param}", method = RequestMethod.GET)
    public String queryQueryYJAmount(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        ResultData<LnkYjPlanDetailActDto> resultData = new ResultData<LnkYjPlanDetailActDto>();
        try {
            resultData = lnkYjPlanService.queryQueryYJAmount(queryParam);
        } catch (Exception e) {
            logger.error("查询佣金税前金额异常-param:" + param, e);
            resultData.setFail();
            logger.error("houseLinkage.estate", "LnkYjPlanController", "queryQueryYJAmount",
                    param, null, "", "查询佣金税前金额异常", e);
        }
        return resultData.toString();

    }

    @RequestMapping(value = "/queryCntYjPlanList/{param}", method = RequestMethod.GET)
    public String queryCntYjPlanList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<PmlsCompanyYjPlan>> resultData = new ResultData<List<PmlsCompanyYjPlan>>();
        try {
            resultData = lnkYjPlanService.queryCntYjPlanList(queryParam);
        } catch (Exception e) {
            logger.error("取得订单的佣金方案内容-param:" + param, e);
            resultData.setFail();
            logger.error("houseLinkage.estate", "LnkYjPlanController", "queryCntYjPlanList",
                    param, null, "", "取得订单的佣金方案内容", e);
        }
        return resultData.toString();

    }
}
