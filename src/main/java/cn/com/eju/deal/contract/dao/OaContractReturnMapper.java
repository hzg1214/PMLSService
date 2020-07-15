package cn.com.eju.deal.contract.dao;

import java.util.List;


import cn.com.eju.deal.contract.model.OaContractReturn;

public interface OaContractReturnMapper 
{
    
    /** 
    * 取得未处理的OA审批结果
    * @return
    */
    public List<OaContractReturn> getOAContractReturn()throws Exception;
    

     
     /**
      * 更新OA审批结果
      */
     public Integer updateOAContractReturn(OaContractReturn oAContractReturn) throws Exception;
     

}