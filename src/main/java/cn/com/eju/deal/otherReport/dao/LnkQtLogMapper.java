package cn.com.eju.deal.otherReport.dao;

import cn.com.eju.deal.otherReport.model.LnkQtLog;

public interface LnkQtLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkQtLog record);

    int insertSelective(LnkQtLog record);

    LnkQtLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkQtLog record);

    int updateByPrimaryKey(LnkQtLog record);
}