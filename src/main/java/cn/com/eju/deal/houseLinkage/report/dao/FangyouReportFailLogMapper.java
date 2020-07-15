package cn.com.eju.deal.houseLinkage.report.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFailLog;


public interface FangyouReportFailLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FangyouReportFailLog record);

    int insertSelective(FangyouReportFailLog record);

    FangyouReportFailLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FangyouReportFailLog record);

    int updateByPrimaryKey(FangyouReportFailLog record);
    //查询节点
    FangyouReportFailLog selectByReportType(Map<String,Object> reqMap);
    
    //查询所有
    List<FangyouReportFailLog> selectAllFailRecord();
}