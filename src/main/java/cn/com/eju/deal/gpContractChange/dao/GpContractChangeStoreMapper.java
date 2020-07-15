package cn.com.eju.deal.gpContractChange.dao;

import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeStore;

import java.util.List;
import java.util.Map;

/**
 * 公盘合同终止门店
 */
public interface GpContractChangeStoreMapper  {

	int create(GpContractChangeStore gpContractChangeStore);

	int updateStr(GpContractChangeStore gpContractChangeStore);

	List<GpContractChangeStore> getGpContractStopStoreInfoById(Integer id);

	GpContract getGpContractById(Integer gpContractId);

	Integer updateStatus(Map<String, Object> queryParam);

	int updateById(Integer id);

	Integer checkGPContractStoreForZZ(Map<String, Object> map);
	
}