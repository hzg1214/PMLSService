package cn.com.eju.deal.otherReport.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.otherReport.dto.LnkQtReportDto;
import cn.com.eju.deal.otherReport.dto.QtReportDto;
import cn.com.eju.deal.otherReport.model.LnkQtReport;

public interface LnkQtReportMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkQtReport record);

    int insertSelective(LnkQtReport record);

    LnkQtReport selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkQtReport record);

    int updateByPrimaryKey(LnkQtReport record);
    
    List<LnkQtReportDto> queryList(Map<?, ?> param) throws Exception;
    
    QtReportDto getQtReportById(Integer id) throws Exception;
    
}