package cn.com.eju.deal.student.dao;

import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.OaCashBillReturn;

import java.util.Map;

/**
 * Created by eju on 2019/12/25.
 */
public interface StudentTestMapper {

    int oaFrameContract(Map<String, Object> map);

    int oaProject(Map<String, Object> map);

    String getNewId();

    int oaSign(Map<String, Object> map);

    int oaApproach(Map<String, Object> map);

    int oaDistribution(Map<String, Object> map);

    int update(Map<String, Object> map);

    int oaStatement(Map<String, Object> map);

    int oaAgreement(Map<String, Object> map);

    int oaReceivables(Map<String, Object> map);

    int oaExpenditure(Map<String, Object> map);

    int updateNext(Map<String, Object> map);

    int updateQk(Map<String, Object> map);

    int oaCashBillProject(Map<String, Object> map);

    int oaCashBill(Map<String, Object> map);

    Integer queryCashBillReturnId(Map<String, Object> map);
    //check 不能重复审核
    CashBillCompany selCashBillCompanyByOaNo(Map<String, Object> map);
}
