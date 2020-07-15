package cn.com.eju.deal.houseLinkage.badDebts.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.badDebts.service.BadDebtsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by haidan on 2019/8/28.
 */
@RestController
@RequestMapping(value = "badDebts")
public class BadDebtsDetailController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Autowired
    private BadDebtsDetailService badDebtsDetailService;

    /**
     * 查询坏账列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryBadDebtsList/{param}", method = RequestMethod.GET)
    public String queryBadDebtsList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData resultData = new ResultData<>();

        try {
            resultData = badDebtsDetailService.queryBadDebtsList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("badDebts", "BadDebtsDetailController", "queryBadDebtsList", param, null, "", "查询坏账明细报表失败", e);
        }
        return resultData.toString();
    }

    /**
     * 查询坏账列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryLongBadDebtsList/{param}", method = RequestMethod.GET)
    public String queryLongBadDebtsList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData resultData = new ResultData<>();

        try {
            resultData = badDebtsDetailService.queryLongBadDebtsList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("badDebts", "BadDebtsDetailController", "queryLongBadDebtsList", param, null, "", "查询长坏账报表失败", e);
        }
        return resultData.toString();
    }

}
