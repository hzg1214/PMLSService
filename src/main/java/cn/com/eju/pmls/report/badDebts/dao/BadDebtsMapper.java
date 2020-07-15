package cn.com.eju.pmls.report.badDebts.dao;

import cn.com.eju.pmls.report.rptQTLnkDetail.model.RptQTLnkDetail;

import java.util.List;
import java.util.Map;

public interface BadDebtsMapper {

    List<?> queryList(Map<String, Object> queryParam);

    void export(Map<String, Object> queryParam);
}
