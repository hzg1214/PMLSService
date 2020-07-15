package cn.com.eju.deal.keFuOrder.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.keFuOrder.model.KeFuOrderCkDto;
import cn.com.eju.deal.keFuOrder.model.KeFuOrderReply;

public interface KeFuOrderReplyMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(KeFuOrderReply record);

    int insertSelective(KeFuOrderReply record);

    KeFuOrderReply selectByPrimaryKey(Integer id);
    

    int updateByPrimaryKeySelective(KeFuOrderReply record);

    int updateByPrimaryKey(KeFuOrderReply record);
    
    int updateByOrderId(KeFuOrderReply record);
    
    KeFuOrderReply selectByOrderId(Map<String,Object> map);
    
    List<KeFuOrderCkDto> selectReplyByOrderId(Map<String,Object> map);
}