package cn.com.eju.deal.houseLinkage.linkAchieveChange.dao;

import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.houseLinkage.report.model.ReportAchieveMentAdjustLog;

import java.util.List;

public interface AchieveMentChangeLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchieveMentChangeLog record);

    int insertSelective(AchieveMentChangeLog record);

    AchieveMentChangeLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchieveMentChangeLog record);

    int updateByPrimaryKey(AchieveMentChangeLog record);

    List<AchieveMentChangeLog> getLogList(Integer relateId);

	int insertAchieveMentAdjustDetailLog(ReportAchieveMentAdjustLog adjustLog);
}