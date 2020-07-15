package cn.com.eju.deal.gpContract.dao;

import cn.com.eju.deal.contract.model.ContractSearchResult;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.gpContract.model.GpContractStore;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GpContractStoreMapper extends IDao<GpContractStore>
{
    Integer getContractStoreContractId(Map<String, Object> mop);

    GpContractStore getStoreByContractIdStoreId(@Param("contractId")Integer contractId, @Param("storeId")Integer storeId) throws Exception;

    List<GpContractStore> selStoreByContractId(Integer contractId) throws Exception;

    int deleteByContractId(Integer contractId) throws Exception;

    /**
     * 合同撤销/作废后 解除门店合同关系[flag置1]
     */
    int updateFlag(Map<String,Object> param);

    /**
     * 同步合同的门店地址
     * @param
     * @return
     */
    int updateByContractId(Integer contractId);

    List<GpContractStore> getContractStoresByFlowId(@Param(value="flowId") String flowId) throws Exception;
    List<ContractSearchResult> getGpContractByStoreId(@Param(value="storeId") Integer storeId) throws Exception;

    void updateTypeByGpContractNo(String gpContractNo);
}