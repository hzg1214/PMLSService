package cn.com.eju.deal.houseLinkage.report.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.houseLinkage.report.model.ReportAchievement;


public interface ReportAchievementMapper extends IDao<ReportAchievement> {

    int createReportAchievement(ReportAchievement reportAchieve) throws Exception;

}