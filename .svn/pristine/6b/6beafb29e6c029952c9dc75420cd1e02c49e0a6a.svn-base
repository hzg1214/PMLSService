package cn.com.eju.deal.keFuWj.dao;

import cn.com.eju.deal.keFuWj.dto.KeFuWjAnalyseDto;
import cn.com.eju.deal.keFuWj.dto.KeFuWjEvaluationDto;
import cn.com.eju.deal.keFuWj.model.KefuWjSatisfaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface KefuWjSatisfactionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KefuWjSatisfaction record);

    int insertSelective(KefuWjSatisfaction record);

    KefuWjSatisfaction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KefuWjSatisfaction record);

    int updateByPrimaryKey(KefuWjSatisfaction record);

    Map<String,Object> getStoreData(Integer id);

    Integer checkSurvey(Map<String, Object> reqMap);

    Map<String,Object> getSurveyData(Integer id);

    int savePushInfo(Map<String, Object> reqMap);

    Integer querySatisfaction(@Param("id") Integer id);

    int selectTelCount(String userTel);

    List<Map<String,Object>> queryWjNumber(Map<String, Object> reqMap);

    List<KeFuWjAnalyseDto> getWjAnalyseList(Map<String, Object> reqMap);

    List<Map<String,Object>> queryWjFlListByCode(String wjCode);

    List<KeFuWjEvaluationDto> getWjEvaluationList(Map<String, Object> reqMap);
}