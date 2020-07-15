package cn.com.eju.deal.contract.dao;

import java.util.List;


import cn.com.eju.deal.contract.model.OaFYCompanyReturn;

public interface OaFYCompanyReturnMapper 
{
    
    /** 
    * 取得OA房友公司返回状态结果信息
    * @return
    */
    public List<OaFYCompanyReturn> getOAFYCompanyReturn()throws Exception;
    
	/**
	 * 更新OA房友公司返回的未提交原因
	 */
	public Integer updateOAFYCompanyReason(OaFYCompanyReturn oAFYCompanyReturn) throws Exception;
     
	/**
     * 更新OA房友公司返回的提交状态
     */
	public Integer updateOAFYCompanyHasDeal() throws Exception;
}