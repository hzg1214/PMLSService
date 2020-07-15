package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.model.LnkYjBrokerage;

import java.util.List;
import java.util.Map;

/**
 * 佣金-应计垫佣
 */
public interface LnkYjYjdyMapper {
    void mergeInsert(Map<String, Object> map);

    List<Map<String,Object>> getList(Map<String, Object> map);

    void mergeInsertByDto(CommissionResultDto cDto);

    Integer getCountByReportId(String reportId);

    List<Map<String, Object>> checkYjdyTotal(Map<String, Object> map);

    void insertRebackYjdyRecord(Map<String, Object> map);

    List<LnkYjBrokerage> getYJtableList(Map<String, Object> map);

    int checkYJdyRatio(Map<String, Object> map);
}
