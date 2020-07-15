package cn.com.eju.deal.Report.dao;


import cn.com.eju.deal.Report.model.ReportQueueAjax;

import java.util.List;

public interface ReportQueueAjaxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportQueueAjax record);

    int insertSelective(ReportQueueAjax record);

    ReportQueueAjax selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportQueueAjax record);

    int updateByPrimaryKey(ReportQueueAjax record);
    
    ReportQueueAjax selectTopOneByUser(ReportQueueAjax record);
    
    //所有未下载文件
    List<ReportQueueAjax> selectReportListByUser(ReportQueueAjax record);
}