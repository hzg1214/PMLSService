package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.model.LnkYjBrokerage;
import cn.com.eju.deal.scene.commission.model.LnkYjYjfy;

import java.util.List;
import java.util.Map;

/**
 * 佣金-应计返佣
 */
public interface LnkYjYjfyMapper {
    List<LnkYjYjfy> queryList(LnkYjYjfy lnkYjSjfy);

    void mergeInsert(CommissionResultDto cDto);

    List<CommissionResultDto> getYJFYCommissionList(Map<String, Object> map);

    Integer getCountByReportId(String reportId);

    List<Map<String, Object>> checkYjfyTotal(Map<String, Object> map);

    List<LnkYjBrokerage> getYJtableList(Map<String, Object> map);

    LnkYjYjfy getStatistcsBrokerage (Map<String, Object> map);
}
