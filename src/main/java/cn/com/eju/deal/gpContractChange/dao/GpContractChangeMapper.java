package cn.com.eju.deal.gpContractChange.dao;

import cn.com.eju.deal.api.gpContract.dto.GPContractWXDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContractChange.model.GpContractChange;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeDto;

import java.util.List;
import java.util.Map;

/**
 * 公盘合同终止
 */
public interface GpContractChangeMapper {
	
	//根据公盘id查询其门店信息
	List<StoreDto> queryStoreInfoOfGpContract(Integer gpContractId) throws Exception;
	//根据公盘id查询其门店信息
	List<StoreDto> queryStoreInfoOfGpContractStopEdit(Map<String, Object> param);
	//List<StoreDto> queryStoreInfoOfGpContractStopEdit(Integer id,Integer gpContractId) throws Exception;
	List<StoreDto> queryLockingStoreForStop(Integer gpContractId) throws Exception;
	//新增公盘合同终止申请
	int create(GpContractChange gpContractChange);
	//更新公盘合同终止申请
	int updateStr(GpContractChange gpContractChange);
	/**
	 * 根据id查询公盘合同终止详情
	 */
	GpContractChangeDto getGpContractStopInfoById(Integer id);
	/**
	 * 公盘合同终止列表
	 * @param reqMap
	 * @return
	 */
	List<GpContractChangeDto> getGpContractStopList(Map<String, Object> reqMap);
	GpContractChangeDto getGpStopInfoById(Integer id);
	GpContractChange getContractChangeByFlowId(String flowId);
	
	int updateGpContractStoreByStoreId(Map<String, Object> paramMap);
	Integer getNoStopStoreSum(Integer id);
	int updateGpContractStatus(Integer id);
	Integer updateStatus(Map<String, Object> queryParam);
	List<Integer> getStopedStoreIdlist(Integer id);
	List<Integer> getPassStopStoreIdlistByGpContractId(Integer gpContractId);
	int updateStoreIsGpSign(Integer storeId);

	int insertGpContractChangeReturn(Integer id);

	List<Map<String,Object>> getTodoList(Map<String,Object> param) throws Exception;

	GPContractWXDto getTodoGpDetail(Map<String,Object> param) throws Exception;

	List<Map<String,Object>> getGpZZList(Map<String,Object> param) throws Exception;
}