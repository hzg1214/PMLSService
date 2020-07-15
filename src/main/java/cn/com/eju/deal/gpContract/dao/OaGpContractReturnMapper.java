package cn.com.eju.deal.gpContract.dao;

import cn.com.eju.deal.gpContract.model.OaGpContractReturn;
import cn.com.eju.deal.gpContractChange.model.OaGpContractChangeReturn;

import java.util.List;

public interface OaGpContractReturnMapper
{
    
    /** 
    * 取得未处理的OA审批结果
    * @return
    */
    public List<OaGpContractReturn> getOAGpContractReturn()throws Exception;
    

     
     /**
      * 更新OA审批结果
      */
     public Integer updateOAGpContractReturn(OaGpContractReturn oAContractReturn) throws Exception;
     public Integer updateOAContractChangeReturn(OaGpContractChangeReturn oAContractReturn) throws Exception;



	public List<OaGpContractChangeReturn> getOAGpContractChangeReturn();
     

}