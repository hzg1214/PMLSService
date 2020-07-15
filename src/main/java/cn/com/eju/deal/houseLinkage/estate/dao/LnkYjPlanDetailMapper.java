package cn.com.eju.deal.houseLinkage.estate.dao;

import java.util.Map;

import cn.com.eju.deal.dto.houseLinkage.estate.LnkYjPlanDetailDto;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanDetail;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanDetailActDto;

public interface LnkYjPlanDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjPlanDetail record);

    int insertSelective(LnkYjPlanDetail record);

    LnkYjPlanDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjPlanDetail record);

    int updateByPrimaryKey(LnkYjPlanDetail record);

    LnkYjPlanDetailDto getYjPlanDetailListByPlanId(Map<String,Object> map);

    LnkYjPlanDetailActDto queryQueryYJAmount(Map<String,Object> map);
    
    int updateByPlanId(Map<String,Object> map);

}