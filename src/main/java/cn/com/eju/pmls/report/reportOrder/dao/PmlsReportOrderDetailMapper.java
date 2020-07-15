package cn.com.eju.pmls.report.reportOrder.dao;

import java.util.List;
import java.util.Map;

public interface PmlsReportOrderDetailMapper {

    List<List<Map<String,Object>>> queryList(Map<String, Object> queryParam);

    void export(Map<String, Object> queryParam);
}
