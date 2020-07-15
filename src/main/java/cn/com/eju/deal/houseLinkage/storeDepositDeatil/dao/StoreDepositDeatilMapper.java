package cn.com.eju.deal.houseLinkage.storeDepositDeatil.dao;

import cn.com.eju.deal.base.linkage.model.District;
import cn.com.eju.deal.houseLinkage.storeDepositDeatil.model.StoreDepositDeatilDto;

import java.util.List;
import java.util.Map;

public interface StoreDepositDeatilMapper {
    
    /**
     * 查询保证金明细
     * @param reqMap
     */
    List<StoreDepositDeatilDto> queryStoreDepositDeatilList(Map<String,Object> reqMap) throws Exception;
    /**
     * 查询所有核算主体及其业绩城市
     */
    List<StoreDepositDeatilDto> queryCityList() throws Exception;
    /**
     * 根据编号城市编号查询其核算主体
     */
    List<StoreDepositDeatilDto> queryAccountProject(String cityNo);
}