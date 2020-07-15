package cn.com.eju.deal.contract.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.com.eju.deal.contract.model.OaContractApprovalInfo;

public interface OaContractApprovalInfoMapper
{
    
    /** 
    * 取得OA审批意见
    * @return
    */
    public List<OaContractApprovalInfo> GetOaContractApprovalInfo(@Param(value="flowId") String flowId)throws Exception;
   

}