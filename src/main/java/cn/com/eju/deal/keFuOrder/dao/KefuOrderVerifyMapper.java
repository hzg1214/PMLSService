package cn.com.eju.deal.keFuOrder.dao;

import cn.com.eju.deal.keFuOrder.model.KeFuOrderCkDto;
import cn.com.eju.deal.keFuOrder.model.KefuOrderVerify;

import java.util.List;
import java.util.Map;

public interface KefuOrderVerifyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(KefuOrderVerify record);

    int insertSelective(KefuOrderVerify record);

    KefuOrderVerify selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(KefuOrderVerify record);

    int updateByPrimaryKey(KefuOrderVerify record);

    List<KeFuOrderCkDto> selectVerifyByOrderId(Map<String,Object> map);
}