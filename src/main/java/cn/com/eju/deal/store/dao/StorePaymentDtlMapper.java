package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.dto.contract.ContractStoreDto;
import cn.com.eju.deal.store.model.StorePaymentDtl;

public interface StorePaymentDtlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StorePaymentDtl record);

    int insertSelective(StorePaymentDtl record);

    StorePaymentDtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StorePaymentDtl record);

    int updateByPrimaryKey(StorePaymentDtl record);

	int create(StorePaymentDtl storePaymentDtl);
	//根据门店编号更新保证金退款
	public Integer updateByStoreNo(Map<String,Object> param);
	public Integer updateContractStoreByStoreId(Map<String,Object> param);
	public Integer updateContractByStoreId(Map<String,Object> param);
	//根据合同id查询每个门店的退款状态
	List<ContractStoreDto> getRefundStateByContractId(Integer id);
}