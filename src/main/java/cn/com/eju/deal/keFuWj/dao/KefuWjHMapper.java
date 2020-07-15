package cn.com.eju.deal.keFuWj.dao;

import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.keFuWj.dto.KeFuWjDto;
import cn.com.eju.deal.keFuWj.model.KefuWjH;

import java.util.List;
import java.util.Map;

public interface KefuWjHMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KefuWjH record);

    int insertSelective(KefuWjH record);

    KefuWjH selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KefuWjH record);

    int updateByPrimaryKey(KefuWjH record);
    
    /**
     * desc:获取适用城市列表
     * 2019年6月18日
     * author:zhenggang.Huang
     */
    List<City> getWjCityList();

    
    /**
     * desc:问卷调查列表
     * 2019年6月18日
     * author:zhenggang.Huang
     */
    List<Map<String,Object>> getKeFuWjList(Map<String,Object> map);
    
    //获取已调查列表
    List<Map<String,Object>> getInvestedList(Map<String,Object> map);

    KeFuWjDto queryKeFuWjList(int id);

    void updateByWjCode(KefuWjH kefuWjH);

    int removeOne(Integer id);

    List<Map<String,Object>> getWjFlByWjcode(String wjCode);

    KeFuWjDto getSurveyDetailByCode(String wjCode);

    List<Map<String,Object>> getEvaluationList(Map<String, Object> reqMap);
}