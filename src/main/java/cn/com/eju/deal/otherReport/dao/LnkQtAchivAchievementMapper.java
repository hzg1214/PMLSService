package cn.com.eju.deal.otherReport.dao;

import cn.com.eju.deal.otherReport.model.LnkQtAchivAchievement;

public interface LnkQtAchivAchievementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkQtAchivAchievement record);

    int insertSelective(LnkQtAchivAchievement record);

    LnkQtAchivAchievement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkQtAchivAchievement record);

    int updateByPrimaryKey(LnkQtAchivAchievement record);
}