package cn.com.eju.pmls.report.rptQTLnkDetail.dao;

import cn.com.eju.pmls.report.rptQTLnkDetail.model.RptQTLnkDetail;

import java.util.List;
import java.util.Map;

public interface RptQTLnkDetailMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RptQTLnkDetail record);

    int insertSelective(RptQTLnkDetail record);

    RptQTLnkDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RptQTLnkDetail record);

    int updateByPrimaryKey(RptQTLnkDetail record);

    List<RptQTLnkDetail> queryList(Map<String, Object> queryParam);

    void export(Map<String, Object> queryParam);
}
