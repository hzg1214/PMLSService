package cn.com.eju.pmls.report.frontPanel.dao;

import cn.com.eju.pmls.report.frontPanel.model.PmlsNumMonth;

import java.util.Map;

public interface PmlsNumMonthMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsNumMonth record);

    int insertSelective(PmlsNumMonth record);

    PmlsNumMonth selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsNumMonth record);

    int updateByPrimaryKey(PmlsNumMonth record);

    PmlsNumMonth getPmlsFrontPanelNum(Map<String, Object> queryParam);
}