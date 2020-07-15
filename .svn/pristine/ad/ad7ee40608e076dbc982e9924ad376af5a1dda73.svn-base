package cn.com.eju.deal.cashbill.dao;

import cn.com.eju.deal.cashbill.model.CashBillReportAdjustTax;
import cn.com.eju.deal.cashbill.model.OaCashBillReportReturn;

import java.util.List;
import java.util.Map;

public interface OaCashBillReportReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaCashBillReportReturn record);

    int insertSelective(OaCashBillReportReturn record);

    OaCashBillReportReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaCashBillReportReturn record);

    int updateByPrimaryKey(OaCashBillReportReturn record);

    List<CashBillReportAdjustTax> getCashBillAdjustTax(Map<String,Object> parms);
}