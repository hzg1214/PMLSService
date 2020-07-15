package cn.com.eju.deal.houseLinkage.report.dao;

import cn.com.eju.deal.houseLinkage.report.model.LnkReportYJFA;

import java.util.Map;

public interface LnkReportYJFAMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkReportYJFA record);

    int insertSelective(LnkReportYJFA record);

    LnkReportYJFA selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkReportYJFA record);

    int updateByPrimaryKey(LnkReportYJFA record);

    int logicDeleteByReportId (Map<String, Object> queryMap);

    void mergeInsert(Map<String, Object> queryMap);
}