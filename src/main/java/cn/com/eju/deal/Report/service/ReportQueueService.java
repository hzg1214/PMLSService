package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dao.ReportQueueAjaxMapper;
import cn.com.eju.deal.Report.dao.ReportQueueTotalMapper;
import cn.com.eju.deal.Report.model.ReportQueueAjax;
import cn.com.eju.deal.Report.model.ReportQueueTotal;
import cn.com.eju.deal.base.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/5/7.
 */
@Service("reportQueueService")
public class ReportQueueService extends BaseService {

    @Resource
    ReportQueueAjaxMapper reportQueueAjaxMapper;

    @Resource
    ReportQueueTotalMapper reportQueueTotalMapper;

    public List<ReportQueueAjax> selectReportListByUser(ReportQueueAjax reportQueueAjax) {
        List<ReportQueueAjax> list = reportQueueAjaxMapper.selectReportListByUser(reportQueueAjax);
        return list;
    }

    public void updateReportQueueTotal(ReportQueueTotal reportQueueTotal) {
        reportQueueTotalMapper.uptByReportKeySelective(reportQueueTotal);
    }

    public Integer addReportQueueAjax(ReportQueueAjax reportQueueAjax) {
        int i = reportQueueAjaxMapper.insertSelective(reportQueueAjax);
        return reportQueueAjax.getId();
    }

    public ReportQueueTotal selectReportQueueTotalTopOne(String reportKey) {
        ReportQueueTotal reportQueueTotal = reportQueueTotalMapper.selectTopOne(reportKey);
        return reportQueueTotal;
    }

    public void uptReportQueueAjax(ReportQueueAjax reportQueueAjax) {
        reportQueueAjaxMapper.updateByPrimaryKeySelective(reportQueueAjax);
    }


    public ReportQueueAjax selectReportQueueAjaxTopOne(ReportQueueAjax reportQueueAjax){
        ReportQueueAjax reportQueue = reportQueueAjaxMapper.selectTopOneByUser(reportQueueAjax);
        return reportQueue;
    }

    public void delReportQueueAjax(ReportQueueAjax reportQueueAjax) {
        reportQueueAjax.setDelFlag("Y");
        int i = reportQueueAjaxMapper.updateByPrimaryKeySelective(reportQueueAjax);
    }

    public ReportQueueAjax selectByPrimaryKey(String id) {
        ReportQueueAjax reportQueueAjax = reportQueueAjaxMapper.selectByPrimaryKey(Integer.valueOf(id));
        return reportQueueAjax;
    }
}
