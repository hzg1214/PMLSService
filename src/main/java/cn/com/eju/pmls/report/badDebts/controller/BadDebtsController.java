package cn.com.eju.pmls.report.badDebts.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.badDebts.service.BadDebtsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "badDebts")
public class BadDebtsController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    BadDebtsService badDebtsService;

    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        ResultData<List<?>> resultData = new ResultData<List<?>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);


            resultData = badDebtsService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("badDebts", "BadDebtsController", "queryList", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public ResultData<String> export(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = badDebtsService.export(reqMap);
        } catch (Exception e) {
            resultData.setFail("坏账明细导出异常！");
            logger.error("badDebts", "BadDebtsController",
                    "export", param, -1, "", "坏账明细导出异常！", e);
        }
        return resultData;
    }
}
