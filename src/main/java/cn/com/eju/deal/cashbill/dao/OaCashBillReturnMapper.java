package cn.com.eju.deal.cashbill.dao;

import cn.com.eju.deal.cashbill.model.OaCashBillReturn;

import java.util.List;
import java.util.Map;

public interface OaCashBillReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OaCashBillReturn record);

    int insertSelective(OaCashBillReturn record);

    OaCashBillReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OaCashBillReturn record);

    int updateByPrimaryKey(OaCashBillReturn record);

    List<OaCashBillReturn> getOaCashBillReturn();

    List<Map<String, Object>>getOALnkAccountProjectList(String projectNo);

    List<Map<String, Object>>getOAFrmAgreementList(Map<String,Object> param);

    List<Map<String, Object>>getOAReceiveBankList(Map<String,Object> param);

    List<Map<String ,Object>>getOACheckBodyList(Map<String,Object> param);
}