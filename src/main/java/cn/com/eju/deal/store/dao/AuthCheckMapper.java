package cn.com.eju.deal.store.dao;

import cn.com.eju.deal.model.sweepStreets.WXPictureDto;
import cn.com.eju.deal.store.model.StoreAuthCheck;
import cn.com.eju.deal.store.model.StoreAuthCheckDto;
import cn.com.eju.deal.store.model.StoreAuthCheckLog;

import java.util.List;
import java.util.Map;

public interface AuthCheckMapper {
    int create(StoreAuthCheck storeAuthCheck);
    
    int Autocreate(StoreAuthCheck storeAuthCheck);

    void createLog(StoreAuthCheckLog log);

    List<StoreAuthCheck> queryList(Map<String, Object> queryParam);

    StoreAuthCheck getAuthCheckById(StoreAuthCheck storeAuthCheck);

    List<StoreAuthCheckLog> getAuthCheckLog(StoreAuthCheck storeAuthCheck);

    List<WXPictureDto> getAuthCheckImg(StoreAuthCheck storeAuthCheck);

    void updateStore(StoreAuthCheck storeAuthCheck);

    Map<String, Object> getLastContractId(Integer storeId);
    /**
     * 查询授牌验收申请列表
     */
	List<StoreAuthCheckDto> getStoreAuthCheckList(Map<String, Object> reqMap);
	/**
     * 查询授牌验收详情
     */
	StoreAuthCheckDto getStoreAuthCheckInfoById(Integer id);

	int updateStoreAuthCheck(StoreAuthCheck storeAuthCheck);

	int updateStoreByParam(Map<String, Object> hashMap);

    Map<String, Object> getStore(StoreAuthCheck storeAuthCheck);

    Map<String, Object> getSPCXCount(Map<String, Object> reqMap);
}
