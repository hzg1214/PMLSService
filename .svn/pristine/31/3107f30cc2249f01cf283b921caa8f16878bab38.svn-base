package cn.com.eju.deal.houseLinkage.estate.dao;

import java.util.Map;

import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanCompanyDto;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanCompany;

public interface LnkYjPlanCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjPlanCompany record);

    int insertSelective(LnkYjPlanCompany record);

    LnkYjPlanCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjPlanCompany record);

    int updateByPrimaryKey(LnkYjPlanCompany record);
    
    LnkYjPlanCompanyDto getYjPlanWyListByPlanId(Map<String,Object> map);
    
    int updateByPlanId(Map<String,Object> map);
}