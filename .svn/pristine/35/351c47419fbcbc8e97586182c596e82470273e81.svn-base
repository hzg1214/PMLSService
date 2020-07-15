package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.model.LnkYjBrokerage;
import cn.com.eju.deal.scene.commission.model.LnkYjYjsr;

import java.util.List;
import java.util.Map;

/**
 * 佣金-应计收入
 */
public interface LnkYjYjsrMapper {
    List<LnkYjYjsr> queryList(LnkYjYjsr lnkYjYjsr);

    List<CommissionResultDto> getYJSRCommissionList(Map<String, Object> map);

    void mergeInsert(CommissionResultDto cDto);

    Integer getCountByReportId(String reportId);

    List<Map<String, Object>> checkYjsrTotal(Map<String, Object> map);

    List<Map<String, Object>> checkSj(Map<String, Object> map);

    void insertRebackYjsrRecord(Map<String, Object> map);

    List<LnkYjBrokerage> getYJtableList(Map<String, Object> map);


}
