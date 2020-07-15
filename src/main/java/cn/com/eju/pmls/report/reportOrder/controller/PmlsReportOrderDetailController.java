package cn.com.eju.pmls.report.reportOrder.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.reportOrder.service.PmlsReportOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "pmlsReportOrderDetail")
public class PmlsReportOrderDetailController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsReportOrderDetailService pmlsReportOrderDetailService;


    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        logger.info("订单明细：查询bengin##param="+ param);
        //构建返回
        ResultData<List<?>> resultData = new ResultData<List<?>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = pmlsReportOrderDetailService.queryList(queryParam);
        } catch (Exception e) {
            logger.error("订单明细：查询error##param="+ param,e);
            resultData.setFail();
            logger.error("pmlsReportOrderDetail", "PmlsReportOrderDetailController", "queryList", "",
                    null, "", "", e);
        }
        logger.info("订单明细：查询end##param="+ param);
        return resultData.toString();
    }

    @RequestMapping(value = "export/{param}", method = RequestMethod.GET)
    public ResultData<String> export(@PathVariable String param) {
        logger.info("订单明细##CSV生成 start！"+param+"###################");
        ResultData<String> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = pmlsReportOrderDetailService.export(reqMap);
        } catch (Exception e) {
            logger.error("订单明细##CSV生成 error！"+param+"###################",e);
            resultData.setFail("订单明细！");
            logger.error("pmlsReportOrderDetail", "PmlsReportOrderDetailController",
                    "export", param, -1, "", "订单明细CSV生成异常！", e);
        }

        return resultData;
    }

}
