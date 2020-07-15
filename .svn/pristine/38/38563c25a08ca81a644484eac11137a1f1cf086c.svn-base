package cn.com.eju.deal.contract.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.contract.model.ContractChangeStore;
import cn.com.eju.deal.core.dao.IDao;

/**
 * 合同变更与门店关联信息Dao层
 * 
 * @author sunmm
 * @date 2016年8月5日 下午4:57:14
 */
public interface ContractChangeStoreMapper extends IDao<ContractChangeStore> {

	// /**
	// * 新增--合同变更与门店关联信息
	// * @param obj 实体对象
	// * @return 影响记录条数
	// */
	// int create(ContractChangeStore obj);

	// /**
	// * 更新-合同变更与门店关联信息
	// * @param obj 实体对象
	// * @return 影响记录条数
	// */
	// int update(ContractChangeStore obj);
	
	/** 
	* 解除合同变更与门店关系
	* @param contractStopId 合同变更ID
	* @return
	*/
	int updateById(Integer id) throws Exception;
	int updateContractChangeStoreById(Map<String, Object> map) throws Exception;
	
	/** 
	* 根据flowID查询合同变更中的门店
	* @param flowID 流程ID
	* @return 合同门店关系列表
	* @throws Exception
	*/
	List<ContractChangeStore> getChangeStoresByFlowId(@Param(value="flowId") String flowId) throws Exception;
	
	/** 
	* 获取该合同变更的门店数
	* @param contractStopId 合同变更ID
	* @return
	* @throws Exception
	*/
	int getStopStoreNum(Integer contractStopId) throws Exception;
	
	/** 
	* 获取该合同变更的门店地址
	* @param contractStopId 合同变更ID
	* @return
	* @throws Exception
	*/
	String getChgStoreAddr(Integer contractStopId) throws Exception;
	
	/** 
	* 根据门店ID查询变更记录
	* @param storeId storeId
	* @return
	* @throws Exception
	*/
	Boolean getChgByStoreId(Integer storeId) throws Exception;
	/**根据storeId查询记录
	 * 
	 * @param storeId
	 * @return
	 * @throws Exception
	 */
	ContractChangeStore quertStoreByStoreId(Integer storeId) throws Exception;
	

	List<ContractChangeStore> getContractChangeStoreById(Map<String, Object> queryParam);

	/**
	 * 根据flowID查询合同变更中的门店（审核中的）
	 * @param flowId 流程ID
	 * @return 合同门店关系列表
	 * @throws Exception
	 */
	List<ContractChangeStore> getChangeStoresByFlowIdApproving(@Param(value="flowId") String flowId) throws Exception;
	List<ContractChangeStore> getContractChangeStoreInfoById(Integer id);
}