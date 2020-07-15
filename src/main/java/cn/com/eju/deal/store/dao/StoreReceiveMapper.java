package cn.com.eju.deal.store.dao;

import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreReceiveDto;
import cn.com.eju.deal.store.model.StoreReceive;

import java.util.List;
import java.util.Map;

public interface StoreReceiveMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreReceive record);

    int insertSelective(StoreReceive record);

    StoreReceive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreReceive record);

    int updateByPrimaryKey(StoreReceive record);
    /**
     * 查询收款列表
     * @param reqMap
     * @return
     */
    List<StoreReceiveDto> getStoreReceiveList(Map<String,Object> reqMap);

    /**
     * 查询收款详情
     * @param id
     * @return
     */
	StoreReceiveDto getBriefById(int id);
	List<StoreDto> getStoreInfoById(int id);
	/**
	 * 删除收款
	 * @param param
	 * @return
	 */
	Integer updateStr(Map<String,Object> param);

    List<StoreReceive> getReceiveListForOa();

    void bathUpdate(Map<String, Object> param);

    List<StoreReceive> getReceiveTDListForOa();
}