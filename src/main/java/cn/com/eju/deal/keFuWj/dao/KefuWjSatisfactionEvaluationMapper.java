package cn.com.eju.deal.keFuWj.dao;


import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.keFuWj.model.KefuWjSatisfactionEvaluation;

import java.util.List;
import java.util.Map;

public interface KefuWjSatisfactionEvaluationMapper extends IDao{
    int deleteByPrimaryKey(Integer id);

    int insert(KefuWjSatisfactionEvaluation record);

    int insertSelective(KefuWjSatisfactionEvaluation record);

    KefuWjSatisfactionEvaluation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KefuWjSatisfactionEvaluation record);

    int updateByPrimaryKey(KefuWjSatisfactionEvaluation record);

    List<Map<String,Object>> getEvaluationList(Integer id);
}