package cn.com.eju.pmls.report.refund.dao;

import cn.com.eju.pmls.report.refund.dto.RefundTrace;

import java.util.List;
import java.util.Map;

public interface PmlsRefundTraceMapper {

    List<RefundTrace> queryList(Map<String, Object> queryParam);

    void export(Map<String, Object> queryParam);
}
