package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.model.LnkYjBrokerage;
import cn.com.eju.deal.scene.commission.model.LnkYjYssr;

import java.util.List;
import java.util.Map;

/**
 * 佣金-应收收入
 */
public interface LnkYjYssrMapper {
    List<LnkYjYssr> queryList(LnkYjYssr lnkYjYssr);

    List<CommissionResultDto> getYSSRCommissionList(Map<String, Object> map);

    void mergeInsert(CommissionResultDto cDto);

    void insertRebackYssrRecord(Map<String, Object> map);

    List<LnkYjBrokerage> getYJtableList(Map<String, Object> map);

    LnkYjYssr getStatistcsBrokerage (Map<String, Object> map);
}
