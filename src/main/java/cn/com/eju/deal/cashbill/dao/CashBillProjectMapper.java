package cn.com.eju.deal.cashbill.dao;

import cn.com.eju.deal.cashbill.model.CashBillProject;

import java.util.List;
import java.util.Map;

public interface CashBillProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashBillProject record);

    int insertSelective(CashBillProject record);

    CashBillProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashBillProject record);

    int updateByPrimaryKey(CashBillProject record);

    List<CashBillProject> selectCashBill(Map<String, Object> queryParam);

    List<Map<String,Object>> selectBaseInfoForCash(String reportId);

    List<CashBillProject> checkProjectUnique(CashBillProject project);

    CashBillProject findByDynamic(Map<String, Object> map);

    CashBillProject findByproParentId(Map<String, Object> map);
    
    Map<String,String> getAccountProjectByEstateId(String estateId);

	List<Map<String, Object>> getAccountProjectByCityNo(String cityNo);

    int updateEditByPrimaryKey(CashBillProject record);

}