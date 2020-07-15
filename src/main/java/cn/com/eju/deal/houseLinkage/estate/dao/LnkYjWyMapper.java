package cn.com.eju.deal.houseLinkage.estate.dao;

import java.util.Map;

import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanWyDto;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjWy;

public interface LnkYjWyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjWy record);

    int insertSelective(LnkYjWy record);

    LnkYjWy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjWy record);

    int updateByPrimaryKey(LnkYjWy record);
    
    LnkYjPlanWyDto getYjPlanWyListByPlanId(Map<String,Object> map);
    
    int updateByPlanId(Map<String,Object> map);
    
}