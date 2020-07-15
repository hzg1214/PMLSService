package cn.com.eju.deal.cashbill.dao;

import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.CashBillReport;

import java.util.List;
import java.util.Map;

public interface CashBillReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashBillReport record);

    int insertSelective(CashBillReport record);

    CashBillReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashBillReport record);

    int updateByPrimaryKey(CashBillReport record);

    List<CashBillReport> checkReportUnique(CashBillReport report);

    List<CashBillReport> queryByParentId(Integer comParentId);


    List<CashBillReport> findUnreasonableReport(Map map);

    List<CashBillReport> findUnOffsetReport(Map map);

    int updateByComparentId(Integer comParentId);

    CashBillCompany getTotalInfo(Integer comParentId);

    String getProjectNoByReportId(Integer id);

    List<Map<String, Object>> getReportForDeductDetail(Map<String, Object> param);

    Map<String, Object> getOaProjectByProjectNo(Integer id);

    List<CashBillReport> selectByProParentId(Integer proParentId);

}