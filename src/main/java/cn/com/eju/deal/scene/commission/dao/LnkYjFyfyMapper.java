package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.scene.commission.model.LnkYjFyfy;

import java.util.List;
import java.util.Map;

public interface LnkYjFyfyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjFyfy record);

    int insertSelective(LnkYjFyfy record);

    LnkYjFyfy selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjFyfy record);

    int updateByPrimaryKey(LnkYjFyfy record);

    List<Map<String,Object>> queryList(Map<String, Object> map);

    void mergeInsert(Map<String, Object> queryMap);
}