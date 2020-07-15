package cn.com.eju.deal.Report.dao;


import cn.com.eju.deal.Report.model.ReportQueueTotal;

public interface ReportQueueTotalMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportQueueTotal record);

    int insertSelective(ReportQueueTotal record);

    ReportQueueTotal selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ReportQueueTotal record);

    int updateByPrimaryKey(ReportQueueTotal record);
    
    //查询总数
    ReportQueueTotal selectTopOne(String reportKey);
    
    //更新数量
    int uptByReportKeySelective(ReportQueueTotal record);
}