package cn.com.eju.deal.contract.dao;

import cn.com.eju.deal.contract.model.OaContractChangeReturn;

import java.util.List;

public interface OaContractChangeReturnMapper
{
    
    /** 
    * 获取未处理的合同终止和合同变更OA审批结果
    * @return
    */
    List<OaContractChangeReturn> getOaContractChangeReturn()throws Exception;

    /**
     * 获取未处理的合同撤销OA审批结果
     * @return
     * @throws Exception
     */
    List<OaContractChangeReturn> getOaContractCancelReturn()throws Exception;

     
     /**
      * 更新OA审批结果
      */
     Integer updateOaContractChangeReturn(OaContractChangeReturn oAContractReturn) throws Exception;
     

}