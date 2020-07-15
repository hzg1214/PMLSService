package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.model.ReportQueueTotal;
import cn.com.eju.deal.Report.service.ReportQueueService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/5/7.
 */
@RestController
@RequestMapping(value = "reportQueue")
public class ReportQueueController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "reportQueueService")
    private ReportQueueService reportQueueService;

    @RequestMapping(value="/selectReportListByUser/{param}",method = RequestMethod.GET)
    public String selectReportListByUser(@PathVariable String param)
    {
        //构建返回
        ResultData<List<ReportQueueAjax>> resultData = new ResultData<>();

        ReportQueueAjax reportQueueAjax = JsonUtil.parseToObject(param, ReportQueueAjax.class);

        List<ReportQueueAjax> list = null;
        try {
            list = reportQueueService.selectReportListByUser(reportQueueAjax);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("reportQueue", "reportQueueController", "selectReportListByUser", "", null, "", "列表查询失败", e);
        }

        if(!list.isEmpty())
        {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.size()+"");
        }

        return resultData.toString();
    }

    @RequestMapping(value="/updateReportQueueTotal",method = RequestMethod.POST)
    public void updateReportQueueTotal(@RequestBody ReportQueueTotal reportQueueTotal)
    {

        try {
            reportQueueService.updateReportQueueTotal(reportQueueTotal);
        } catch (Exception e) {
            logger.error("reportQueue", "reportQueueController", "updateReportQueueTotal", "", null, "", "列表查询失败", e);
        }

    }

    @RequestMapping(value="/addReportQueueAjax",method = RequestMethod.POST)
    public String addReportQueueAjax(@RequestBody ReportQueueAjax reportDb)
    {
        ResultData<Integer> reback = new ResultData<>();
        Integer id = null;
        try {
            id = reportQueueService.addReportQueueAjax(reportDb);
        } catch (Exception e) {
            logger.error("reportQueue", "reportQueueController", "addReportQueueAjax", "", null, "", "列表查询失败", e);
        }
        reback.setReturnData(id);
        return reback.toString();
    }

    @RequestMapping(value="/selectReportQueueTotalTopOne/{reportKey}",method = RequestMethod.GET)
    public String selectReportQueueTotalTopOne(@PathVariable String reportKey)
    {
        //构建返回
        ResultData<ReportQueueTotal> resultData = new ResultData<>();

        try {
            ReportQueueTotal reportQueueTotal = reportQueueService.selectReportQueueTotalTopOne(reportKey);
            resultData.setReturnData(reportQueueTotal);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("reportQueue", "reportQueueController", "selectReportQueueTotalTopOne", "", null, "", "列表查询失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value="/uptReportQueueAjax",method = RequestMethod.POST)
    public void uptReportQueueAjax(@RequestBody ReportQueueAjax reportQueueAjax)
    {

        try {
            reportQueueService.uptReportQueueAjax(reportQueueAjax);
        } catch (Exception e) {
            logger.error("reportQueue", "reportQueueController", "uptReportQueueAjax", "", null, "", "列表查询失败", e);
        }

    }

    @RequestMapping(value="/selectReportQueueAjaxTopOne",method = RequestMethod.POST)
    public String selectReportQueueAjaxTopOne(@RequestBody ReportQueueAjax reportQueueAjax)
    {
        //构建返回
        ResultData<ReportQueueAjax> resultData = new ResultData<>();

        try {
            ReportQueueAjax reportQueue = reportQueueService.selectReportQueueAjaxTopOne(reportQueueAjax);
            resultData.setReturnData(reportQueue);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("reportQueue", "reportQueueController", "selectReportQueueTotalTopOne", "", null, "", "列表查询失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value="/delReportQueueAjax",method = RequestMethod.POST)
    public void delReportQueueAjax(@RequestBody ReportQueueAjax reportQueueAjax)
    {

        try {
            reportQueueService.delReportQueueAjax(reportQueueAjax);
        } catch (Exception e) {
            logger.error("reportQueue", "reportQueueController", "selectReportQueueTotalTopOne", "", null, "", "列表查询失败", e);
        }
    }

    @RequestMapping(value="/selectByPrimaryKey/{id}",method = RequestMethod.GET)
    public String selectByPrimaryKey(@PathVariable String id)
    {
        //构建返回
        ResultData<ReportQueueAjax> resultData = new ResultData<>();

        try {
            ReportQueueAjax reportQueue = reportQueueService.selectByPrimaryKey(id);
            resultData.setReturnData(reportQueue);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("reportQueue", "reportQueueController", "selectReportQueueTotalTopOne", "", null, "", "列表查询失败", e);
        }
        return resultData.toString();
    }

}
