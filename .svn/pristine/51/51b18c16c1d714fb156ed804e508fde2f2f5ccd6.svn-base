package cn.com.eju.pmls.report.refund.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.refund.service.PmlsRefundTraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "pmlsRefundTraceController")
public class PmlsRefundTraceController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsRefundTraceService pmlsRefundTraceService;


    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        logger.info("联动项目周回款跟踪：查询bengin##param="+ param);
        //构建返回
        ResultData<List<?>> resultData = new ResultData<List<?>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = pmlsRefundTraceService.queryList(queryParam);
        } catch (Exception e) {
            logger.error("联动项目周回款跟踪：查询error##param="+ param);
            resultData.setFail();
            logger.error("refund", "PmlsRefundTraceController", "queryList", "",
                    null, "", "", e);
        }
        logger.info("联动项目周回款跟踪：查询end##param="+ param);
        return resultData.toString();
    }

    @RequestMapping(value = "/export", method = RequestMethod.POST)
    public ResultData<String> export(@RequestBody String param) {
        logger.info("联动项目周回款跟踪：导出begin！"+param+"###################");
        ResultData<String> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = pmlsRefundTraceService.export(reqMap);
        } catch (Exception e) {
            logger.error("联动项目周回款跟踪：导出error！"+param+"###################");
            resultData.setFail("联动项目周回款跟踪导出异常！");
            logger.error("refund", "PmlsRefundTraceController",
                    "export", param, -1, "", "联动项目周回款跟踪导出异常！", e);
        }
        logger.info("联动项目周回款跟踪：导出end！"+param+"###################");
        return resultData;
    }

}
