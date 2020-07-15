package cn.com.eju.deal.keFuOrder.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.keFuOrder.model.*;
import cn.com.eju.deal.user.model.User;
import cn.com.eju.deal.base.dto.code.CommonCodeDto;

public interface KeFuOrderMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(KeFuOrder record);

    int insertSelective(KeFuOrder record);

    KeFuOrder selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KeFuOrder record);

    int updateByPrimaryKey(KeFuOrder record);
    
    /**
     * 查询客服反馈工单列表
     * @param reqMap
     * @return
     */
    List<KeFuOrderDto> getKeFuOrderList(Map<String, Object> reqMap);
    /**
     * 根据id查询客服工单
     * @param id
     * @return
     */
	KeFuOrderDto getKeFuOrderById(Integer id);
	
	List<User> getOperatorList(Map<String, Object> reqMap);

	List<CommonCodeDto> getCategoryTwo(Map<?, ?> param);

    WxKefuOrder getKeFuOrderByIdForWx(Integer id);

    List<WxKefuOrderReply> getReplyListById(Integer id);

    int insertReply(WxKefuOrderReply reply);
    /**
	 * 根据storeId查询客服工单列表
	 * @param storeId
	 * @return
	 */
	List<KeFuOrderDto> getKeFuOrderListByStoreId(Integer storeId);

	List<KeFuOrderCkDto> getKefuOrderDtls(Map<String, Object> reqMap);
}