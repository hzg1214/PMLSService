package cn.com.eju.pmls.report.frontPanel.dao;

import cn.com.eju.pmls.report.frontPanel.model.PmlsNumWeek;

import java.util.Map;

public interface PmlsNumWeekMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsNumWeek record);

    int insertSelective(PmlsNumWeek record);

    PmlsNumWeek selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsNumWeek record);

    int updateByPrimaryKey(PmlsNumWeek record);

    PmlsNumWeek getPmlsFrontPanelNum(Map<String, Object> queryParam);
}