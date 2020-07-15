package cn.com.eju.deal.houseLinkage.report.dao;

import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFile;
import cn.com.eju.deal.houseLinkage.report.model.Report;

import java.util.List;
import java.util.Map;

public interface FangyouReportFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FangyouReportFile record);

    int insertSelective(FangyouReportFile record);

    FangyouReportFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FangyouReportFile record);

    int updateByPrimaryKey(FangyouReportFile record);
    //更新图片
    int updateSelective(FangyouReportFile fangyouReportFile);
    
    //根据报备id查询房友图片
    List<FangyouReportFile> selectFangyouPic(Integer id);

    void deletePartReportFile(Report reportDb);

    List<FangyouReportFile> selectByCondition(Map<String,Object> map);
}