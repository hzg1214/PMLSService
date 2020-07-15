package cn.com.eju.deal.houseLinkage.storedepositSerial.dao;

import cn.com.eju.deal.houseLinkage.storeDepositDeatil.model.StoreDepositDeatilDto;
import cn.com.eju.deal.houseLinkage.storedepositSerial.model.StoreDepositSerialDto;

import java.util.List;
import java.util.Map;

public interface StoreDepositSerialMapper {
    
    /**
     * 查询保证金流水
     * @param reqMap
     */
    List<StoreDepositSerialDto> queryStoreDepositSerialList(Map<String,Object> reqMap) throws Exception;
    /**
     * 查询所有核算主体及其业绩城市
     */
    List<StoreDepositSerialDto> queryCityList() throws Exception;
    /**
     * 根据编号城市编号查询其核算主体
     */
    List<StoreDepositSerialDto> queryAccountProject();
}