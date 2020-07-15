package cn.com.eju.deal.contract.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.contract.ContractStoreDto;

public interface ContractStoreMapper extends IDao<ContractStore>
{
    int delStoreByContractId(Integer contractId) throws Exception;
    
    int deleteByContractId(Integer contractId) throws Exception;
    
    List<ContractStore> selStoreByContractId(Integer contractId) throws Exception;
    
    // 根据flowID查询合同中的门店
    public List<ContractStore> getContractStoresByFlowId(@Param(value="flowId") String flowId) throws Exception;
    
    public List<ContractStore> getContractStoresByContractFlowId(@Param(value="flowId") String flowId) throws Exception;
    
	/**
	 * 根据storeId、contractId查询合同门店关联信息
	 * 
	 * @param queryParam
	 *            (storeId:门店ID、contractId:合同ID)
	 * @return
	 * @throws Exception
	 */
	ContractStore getContractStore(Map<String, Object> queryParam)
			throws Exception;
	/**
	 * 
	* 更新isCancel状态
	* @param queryParam
	 * @return 
	 */
    int updateIsCancel(Map<String, Object> queryParam) throws Exception;

    /**
     * 
    * 根据OA审核状态更新isCancel状态
    * @param queryParam
    * @return
     */
    int updateIsCancelState(Map<String, Object> queryParam);

    /**
     * 
    * 检验变更门店是否在门店业绩撤销中
    * @param queryParam
    * @return
     */
    List<ContractStore> getStoreCancelState(Map<String, Object> queryParam);

    /**
     * 
    * 根据门店ids和合同id 更新isCancel状态
    * @param queryParam
    * @return
     */
    int updateIsCancelByStoreIds(Map<String, Object> queryParam);

    /**
     * 
    * 更新保证金退款金额 、 退款时间和退款状态 
    * @param mop
    * @return
     */
    int updateRefundInfo(Map<String, Object> mop);
    
    /**
    * 同步合同的门店地址
    * @param 
    * @return
     */
    int updateByContractId(Integer contractId);
    
    
    /**
	 * 根据storeId获取最新门店关联合同信息
	 * @param storeId
	 * @return ContractStore
	 */
    ContractStoreDto getTopContractStoreByStore(Integer storeId);
    
  
    /**
	 * 根据storeId  获取合同类型 776
	 * @param Integer storeId
	 * @return String ContractType
	 */
    String getTopContractTypeByStore(Integer storeId);
    
    //Add by WangLei 2017/04/07 Start
    /**
     * 根据storeId,contractId 获取原合同信息
     * @param storeId
     * @param contractId
     * @return
     * @throws Exception
     */
    ContractStore getOrgContractInfo(Map<String, Object> mop);
    //Add by WangLei 2017/04/07 End
    
	/**
	 * 合同撤销/作废后 解除门店合同关系[flag置1]
	 */
	int updateFlag(Map<String,Object> param);
	
	Integer getContractStoreContractId(Map<String, Object> mop);
	
	ContractStore selStoreByContractIdStoreId(@Param("contractId")Integer contractId, @Param("storeId")Integer storeId) throws Exception;

    //Add by Sucen 2017/07/17 Start
    /**
     * 根据contractId查询合同门店关联信息
     * @param contractId
     * @return
     * @throws Exception
     */
	public List<ContractStore> getContractStoreByContractId(Integer contractId) throws Exception;
    //Add by Sucen 2017/07/17 End
}