package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.model.LnkYjBrokerage;
import cn.com.eju.deal.scene.commission.model.LnkYjSjfy;

import java.util.List;
import java.util.Map;

/**
 * 佣金-实际返佣
 */
public interface LnkYjSjfyMapper {
    List<LnkYjSjfy> queryList(LnkYjSjfy lnkYjSjfy);

    void mergeInsert(CommissionResultDto cDto);

    List<CommissionResultDto> getSJFYCommissionList(Map<String, Object> map);

    int insert(LnkYjSjfy lnkYjSjfy);

    Integer checkPreAmountForReback(Map<?, ?> map);

    List<LnkYjBrokerage> getYJtableList(Map<String, Object> map);

    LnkYjSjfy getStatistcsBrokerage (Map<String, Object> map);
}
