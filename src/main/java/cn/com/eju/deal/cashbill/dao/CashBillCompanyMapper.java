package cn.com.eju.deal.cashbill.dao;

import cn.com.eju.deal.cashbill.model.CashBillCompany;
import cn.com.eju.deal.cashbill.model.CashBillDto;
import cn.com.eju.deal.cashbill.model.CashBillReport;

import java.util.List;
import java.util.Map;

public interface CashBillCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashBillCompany record);

    int insertSelective(CashBillCompany record);

    CashBillCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CashBillCompany record);

    int updateByPrimaryKey(CashBillCompany record);

    List<CashBillCompany> checkCompanyUnique(CashBillCompany company);

    List<CashBillCompany> queryByParentId(Map<String,Object> map);
    /**
     * 请款单列表
     * @param reqMap
     * @return
     */
    List<CashBillDto> getCashBillList(Map<String,Object> reqMap);
    /**
     * 根据id查询请款单详情CashBillReport列表
     * @param reqMap
     * @return
     */
    List<CashBillReport> getReportListById(Map<String,Object> reqMap);
    /**
     * 根据id查询请款单详情
     * @param reqMap
     * @return
     */
    List<CashBillDto> getCashBillDeatilById(Map<String,Object> reqMap);
    /**
     * 总面积\大定合计\成销合计
     */
    CashBillDto getReportSumById(Map<String,Object> reqMap);

    CashBillCompany getByCashBillNo(Map<String,Object> reqMap);
    
//    根据BB查询对应业绩centerId对应的考核主体
    Map<String,Object> getCheckBodyByReportNo(Map<String,Object> reqMap);
//    根据flowid取中介合作报批单盖章附件信息
    Map<String,Object> getFlowIdByFrameOaNo(Map<String,Object> reqMap);
    
    int remove(Integer id);

    //new
    String getVendorNameByVendorId(String vendorId);

    void execQkUptJs(Map<String,Object> reqMap);

    void execQkZeroSync(Map<String,Object> reqMap);

    int updateEditByPrimaryKey(CashBillCompany record);

    int updateEditOaByPrimaryKey(Integer id);
}