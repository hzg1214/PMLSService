package cn.com.eju.deal.otherReport.dao;

import cn.com.eju.deal.otherReport.dto.YjQtReportDto;
import cn.com.eju.deal.otherReport.model.LnkYjQtReport;
import cn.com.eju.deal.scene.commission.model.LnkYjReport;

import java.util.List;
import java.util.Map;

/**
 * desc:
 * @author :zhenggang.Huang
 * @date   :2019年10月17日
 */
public interface LnkQtYjReportMapper {

    int insert(LnkYjQtReport record);
    //插入佣金表
    int insertSelective(LnkYjReport LnkYjReport);
    
    //更新
    int updateByPrimaryKeySelective(LnkYjReport LnkYjReport);
    
    //返佣对象
    List<YjQtReportDto> getFyObjectByQtReportId(Map<String,Object> map);
    
}
