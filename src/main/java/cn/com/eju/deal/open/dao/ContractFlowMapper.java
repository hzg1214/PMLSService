package cn.com.eju.deal.open.dao;

import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.open.model.ContractFlowDto;

/**   
* 合同审批流审批信息Dao
* @author sunmm
* @date 2016年8月24日 下午6:02:36
*/
public interface ContractFlowMapper extends IDao<ContractFlowDto> {

	/** 
	* 根据门店ID、合同ID查询审核通过"-1"的记录 (供OMS-业绩查看用)
	* @param queryParam ( storeID:门店ID、contractId:合同ID )
	* @return
	*/
	ContractFlowDto getContractFlowByStoreId(Map<String, Object> queryParam) throws Exception;

}
