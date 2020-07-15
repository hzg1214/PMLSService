package cn.com.eju.deal.scene.commission.dao;

import cn.com.eju.deal.scene.commission.model.LnkYjBrokerage;
import cn.com.eju.deal.scene.commission.model.LnkYjSjdy;

import java.util.List;
import java.util.Map;

/**
 * 佣金-实际垫佣
 */
public interface LnkYjSjdyMapper {
    void mergeInsert(Map<String, Object> map);

    List<Map<String,Object>> getList(Map<String, Object> map);

    int insert(LnkYjSjdy lnkYjSjdy);

    List<LnkYjBrokerage> getYJtableList(Map<String, Object> map);
}
