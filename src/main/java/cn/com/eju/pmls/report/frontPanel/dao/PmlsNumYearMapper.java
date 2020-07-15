package cn.com.eju.pmls.report.frontPanel.dao;

import cn.com.eju.pmls.report.frontPanel.model.PmlsNumYear;

import java.util.Map;

public interface PmlsNumYearMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsNumYear record);

    int insertSelective(PmlsNumYear record);

    PmlsNumYear selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsNumYear record);

    int updateByPrimaryKey(PmlsNumYear record);

    PmlsNumYear getPmlsFrontPanelNum(Map<String, Object> queryParam);
}