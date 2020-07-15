package cn.com.eju.deal.store.dao;

import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.store.model.StoreDeposit;

import java.util.Map;

public interface StoreDepositMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StoreDeposit record);

    int insertSelective(StoreDeposit record);

    StoreDeposit selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StoreDeposit record);

    int updateByPrimaryKey(StoreDeposit record);
    
    StoreDeposit findByCondition(StoreDeposit record);
    
    Contract getTopOneContract(Map<String,Object> qParam);
    
    String getNewDepositOpenFlagByCityNo(String cityNo);

    String getNewDepositOpenFlagByCenterId(Integer centerId);

    int updateByStoreNo(StoreDeposit record);
}