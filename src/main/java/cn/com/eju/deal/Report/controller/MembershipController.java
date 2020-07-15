package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.model.Membership;
import cn.com.eju.deal.Report.service.MembershipService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "membership")
public class MembershipController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "membershipService")
    MembershipService membershipService;

    /**
     * 查询数据
     */
    @RequestMapping(value = "queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<Membership>> resultData = new ResultData<>();

        try {
            resultData = membershipService.queryList(queryParam);
        } catch (Exception ex) {
            resultData.setFail();
            logger.error("report", "MembershipController", "queryList", "", 0, "", "公盘会员明细查询失败", ex);
        }
        return resultData.toString();
    }

    /**
     * 查询数据
     */
    @RequestMapping(value = "exportList/{param}", method = RequestMethod.GET)
    public String exportList(@PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<Membership>> resultData = new ResultData<>();
        try {

            queryParam.remove("pageIndex");
            queryParam.remove("pageSize");
            queryParam.remove("curPage");

            resultData = membershipService.queryList(queryParam);

        } catch (Exception ex) {
            logger.error("report", "MembershipController", "exportList", "", 0, "", "公盘会员明细查询失败", ex);
        }

        return resultData.toString();
    }
}
