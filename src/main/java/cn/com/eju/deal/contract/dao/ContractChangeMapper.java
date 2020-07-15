package cn.com.eju.deal.contract.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.model.ContractChangeStore;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.storeRelocation.model.StoreRelocationDto;

/**
 * 合同变更信息Dao层
 * 
 * @author sunmm
 * @date 2016年8月5日 下午4:57:14
 */
public interface ContractChangeMapper extends IDao<ContractChange> {

	// /**
	// * 新增--合同变更信息
	// * @param obj 实体对象
	// * @return 影响记录条数
	// */
	// int create(ContractChange obj);

	// /**
	// * 更新-合同变更信息
	// * @param obj 实体对象
	// * @return 影响记录条数
	// */
	// int update(ContractChange obj);

	/** 
	* 根据合同变更ID查询合同变更信息
	* @param id 合同变更ID
	* @return
	* @throws Exception
	*/
	ContractChange queryById(Integer id) throws Exception;
	
	/** 
	* 根据合同ID查询合同变更信息
	* @param contractId 合同ID
	* @return
	* @throws Exception
	*/
	List<ContractChangeDto> getContractChange(Integer contractId) throws Exception;
	
	/** 
	* 获取合同变更状态为审核中的FlowIdList
	* @param params 合同变更ID
	* @return
	* @throws Exception
	*/
	List<ContractChange> queryByApproveState(Map<String,Object> params) throws Exception;
	
	
	/** 
    * 更新 合同变更记录表 审核状态
    * @param reqMap
    * @return
    */
    public int updateChgApprovaeState(Map<String, Object> params) throws Exception;
  
    /**
 	 * 更新--将flowId和审核状态(approveState)更新到变更记录中
 	 * @param obj 实体对象
 	 * @return 影响记录条数
 	 */
 	 int updateFlowId(ContractChange obj) throws Exception;
    
 	/** 
 	* 查询该合同ID对应的所有approveState是变更中的门店信息
 	* @param id 合同变更ID
 	* @return
 	* @throws Exception
 	*/
 	List<ContractChangeDto> getContractChangeById(Integer id) throws Exception;
 	
 	/**
 	 * 根据门店ID查找合同门店变更信息 
 	 * @param id
 	 * @return
 	 * @throws Exception
 	 */
 	List<ContractChangeDto> getContractChangeByStoreId(Integer id) throws Exception;
 	
 	/**
 	 * 根据合同No查找该合同门店的乙转甲变更单号
 	 * @param contractNo
 	 * @return
 	 * @throws Exception
 	 */
 	ContractChangeDto getcontractB2AChangeNo(String contractNo) throws Exception;
 	
	/** 
	* 根据flowId查询合同变更信息
	* @param flowId 
	* @return
	* @throws Exception
	*/
 	ContractChange getContractChangeByFlowId(String flowId) throws Exception;

 	/**
 	 * 【描述】: 根据id拷贝主表
 	 *
 	 * @author yinkun
 	 * @date: 2017年12月13日 下午5:00:38
 	 * @param param
 	 * @return
 	 */
    int copyForOaSplit(Map<String, Object> param);

    /**
     * 【描述】: 拷贝副表
     *
     * @author yinkun
     * @date: 2017年12月13日 下午5:17:59
     * @param param1
     * @return
     */
    int copySubForOaSplit(Map<String, Object> param1);

    /**
     * 【描述】: 拷贝文件
     *
     * @author yinkun
     * @date: 2017年12月13日 下午5:18:24
     * @param param2
     * @return
     */
    int copyFileForOaSplit(Map<String, Object> param2);

    int delOmsDecoration(Map<String, Object> param);

    int delCrmDecoration(Map<String, Object> param);
    /**
     * 查询合同未终止的门店数量
     * @param contractId
     * @return
     */
	Integer queryStoreOfCountByConractId(Integer contractId);

    int deleteRecordById(Integer id);

    StoreRelocationDto getContractInfoById(Integer contractStopId);
    int updateContractStoreByParam(Map<String, Object> paramMap);

	Integer checkDecorationStatus(int id);
	Integer checkStoreAddress(Map<String, Object> queryParam);

	StoreRelocationDto getStoreRelocationById(Integer id);

	StoreDto getStoreRelocationStoreById(Integer id);
	Integer getDecorationApplyStatus(int id);

	ContractChangeStore getContractChangeStoreByIds(Map<String, Object> param);
}