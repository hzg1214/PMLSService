package cn.com.eju.deal.otherReport.controller;

import cn.com.eju.deal.api.houseLinkage.constant.HouseLinkageConstant;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.otherReport.model.LnkQtReport;
import cn.com.eju.deal.otherReport.service.QtReportService;
import cn.com.eju.deal.otherReport.service.QtSuccessSaleService;
import com.alibaba.fastjson.JSON;
import org.apache.http.Consts;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.Map;

@RestController
@RequestMapping(value = "qtSuccessSale")
public class QtSuccessSaleController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "qtSuccessSaleService")
    private QtSuccessSaleService qtSuccessSaleService;

    @Resource(name = "qtReportService")
    private QtReportService qtReportService;

    @RequestMapping(value = "/successSale", method = RequestMethod.POST)
    public String successSale(@RequestBody String jsonStr) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("201");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);

            Map<String, Object> reqMap = JsonUtil.parseToObject(jsonStr, Map.class);

            resultData = qtSuccessSaleService.successSale(reqMap);
            return resultData.toString();
        } catch (Exception e) {
            logger.error("qtSuccessSale", "QtSuccessSaleController", "successSale", jsonStr, null, "", "其他收入添加成销记录", e);
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            return resultData.toString();
        }
    }

    /**
     * 获取报备信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getDealRecord/{id}", method = RequestMethod.GET)
    public String getDealRecord(@PathVariable("id") Integer id) {
        ResultData resultData = new ResultData();
        try {
            resultData = qtReportService.getQtReportById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("qtSuccessSale", "QtSuccessSaleController", "getDealRecord", id.toString(), null, "", "获取报备信息", e);
        }
        return resultData.toString();
    }

    /**
     * 添加退成销记录
     *
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/dealBack", method = RequestMethod.POST)
    public String dealBack(@RequestBody String jsonStr) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("201");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);

            LnkQtReport lnkQtReport = JSON.parseObject(jsonData, LnkQtReport.class);
            resultData = qtSuccessSaleService.dealBack(lnkQtReport);
            return resultData.toString();
        } catch (Exception e) {
            logger.error("qtSuccessSale", "QtSuccessSaleController", "dealBack", jsonStr, null, "", "其他收入添加退成销记录", e);
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            return resultData.toString();
        }
    }

}
