package cn.com.eju.deal.houseLinkage.estate.dao;

import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanDto;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlan;
import cn.com.eju.deal.houseLinkage.estate.model.PmlsCompanyYjPlan;

import java.util.List;
import java.util.Map;

public interface LnkYjPlanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjPlan record);

    int insertSelective(LnkYjPlan record);

    LnkYjPlan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjPlan record);

    int updateByPrimaryKey(LnkYjPlan record);

    List<LnkYjPlanDto> getYjPlanByProjectNo(Map<String, Object> map);

    List<LnkYjPlanDto> getProgrammeList(Map<String, Object> map);

    LnkYjPlanDto queryById(Map<String, Object> map);

    List<PmlsCompanyYjPlan> getCntYJFYPlan(Map<String, Object> map);

    PmlsCompanyYjPlan getCntYJSRPlan(Map<String, Object> map);
    
    LnkYjPlan selectByCondition(Map<String, Object> map);
    
    //调用存储过程
    void callYjPlan(Map<String, Object> param);

}