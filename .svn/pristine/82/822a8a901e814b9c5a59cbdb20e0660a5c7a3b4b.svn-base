package cn.com.eju.deal.contract.dao;

import java.util.Map;

import cn.com.eju.deal.contract.model.StoreFangyouAccount;

public interface StoreFangyouAccountMapper 
{

	/**
	 * 取得门店房友账号关联信息
	 * @param mop
	 * @return 门店房友账号关联信息
	 * @throws Exception
	 */
    public StoreFangyouAccount getStoreFangyouAccount(Map<String, Object> mop)throws Exception;

    /**
     * 新建门店房友账号关联表数据     
     * @param storeFangyouAccount
     * @return 新建数
     * @throws Exception
     */
    public Integer createStoreFangyouAccount(StoreFangyouAccount storeFangyouAccount)throws Exception; 

	/**
     * 更新门店房友账号关联的开通绑定状态
     * @param storeFangyouAccount
     * @return 更新数 
     * @throws Exception 
     */
    public Integer updateStoreFangyouAccountStatus(StoreFangyouAccount storeFangyouAccount)throws Exception; 

	/**
	 * 
	 * @param companyNo 公司编号
	 * @return
	 */
	int updateStoreFangyouAccountByOp(String companyNo) throws Exception;

 }