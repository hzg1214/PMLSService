package cn.com.eju.deal.scene.commission.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.scene.commission.model.CompanyMatin;
import cn.com.eju.deal.scene.commission.model.LnkYjReport;
import cn.com.eju.deal.scene.commission.model.LnkYjReportLog;
import cn.com.eju.deal.scene.commission.model.YjCompany;

/**
 * desc:
 * @author :zhenggang.Huang
 * @date   :2019年4月29日
 */
public interface LnkYjReportMapper {
    void mergeInsert(Map<String, Object> map);

    //返佣对象列表
    List<Map<String,Object>> getLnkYjReportList(Map<String, Object> map);
    
    //返佣对象详情
    List<Map<String,Object>> getYjReportDeatilById(Map<String,Object> reqMap);
    
    //模糊查询公司
    List<YjCompany> getYjCompany(Map<String,Object> reqMap);
    
    //插入佣金表
    int insertSelective(LnkYjReport LnkYjReport);
    
    //插入记录表
    int insertLnkYjReportLog(LnkYjReportLog lnkYjReportLog);
    //插入记录表
    int insertLog(Map<String,Object> reqMap);
    
    
    //更新
    int updateByPrimaryKeySelective(LnkYjReport LnkYjReport);
    
    //根据报备编号查询
    List<LnkYjReport> selYjReportByReportId(Map<String,Object> map);

    //查看维护记录
    List<CompanyMatin> selYjLogByReportId(Map<String,Object> map);
    
    //批量查询源维护对象是否可编辑
    int decideYjReportByReportId(Map<String,Object> map) ;

    
    //根据公司名称查询公司编号
    Company selCompanyByCompanyNo(Map<String,Object> map);
    
    //根据报备编号查询是否已返佣
    Map<String,Object> selLnkReportByReportId(Map<String,Object> map);
    

    //更新
    int deleteOther(Map<String,Object> map);
    
    //删除记录表数据
    int delYjReportLog(Map<String,Object> map);
    //删除记录表数据
    int deleteByReportAndCompany(Map<String,Object> map);
    //删除对应aftTaxAmount=0，befTaxAmount=0得yjdy、yjfy、sjdy、shfy数据
    int deleteDataByReportId(Map<String,Object> map);
}
