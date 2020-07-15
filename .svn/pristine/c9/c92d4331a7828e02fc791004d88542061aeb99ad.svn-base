package cn.com.eju.deal.houseLinkage.estate.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.dto.houseLinkage.estate.LnkEstateIncomeplanDto;
import cn.com.eju.deal.houseLinkage.estate.model.LnkEstateIncomeplan;
import cn.com.eju.pmls.cooperation.model.CooperationDto;

public interface LnkEstateIncomeplanMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkEstateIncomeplan record);

    int insertSelective(LnkEstateIncomeplan record);

    LnkEstateIncomeplan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkEstateIncomeplan record);

    int updateByPrimaryKey(LnkEstateIncomeplan record);

    int queryCountByProject(String projectNo);

    List<LnkEstateIncomeplanDto> queryPlanListByProjectNo(Map<String, Object> param);

    LnkEstateIncomeplanDto queryPlanById(Integer id);

    LnkEstateIncomeplanDto queryCXSQAmount(Map<?, ?> queryParam);

    LnkEstateIncomeplanDto queryTopCXSQAmount(Map<?, ?> queryParam);

    List<LnkEstateIncomeplanDto> queryIncomeplanList(Map<String, Object> param);

    int updatePlan(LnkEstateIncomeplan plan);

    LnkEstateIncomeplanDto queryById(Integer id);
    
    List<CooperationDto> getCompanyList(Map<?, ?> param) throws Exception;
}