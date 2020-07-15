package cn.com.eju.deal.user.api;

import java.util.List;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.model.ExchangeCenter;


public interface IExchangeCenterAPI {
	
	/**
	 * 根据用户Id查看交易中心
	 * @param userId  用户ID
	 * @return
	 */
	public ResultData<List<ExchangeCenter>> getExchangeCenterByUserId(int userId);
	
	
	/**
	 * 根据城市ID查询所有交易中心的信息
	 * @param cityId 城市ID
	 * @return
	 */
	public ResultData<List<ExchangeCenter>> getExchangeCenterByCityId(int cityId);
	
	
	/**
	 * 根据城市Code查询交易中心信息
	 * @param CityNo
	 * @return
	 */
	public ResultData<List<ExchangeCenter>> getExchangeCenterByCityNo(String CityNo);
	
	
	/**
	 * 根据交易中心ID查询交易中心的信息
	 * @param exchangeCenterId  交易中心ID
	 * @return
	 */
	public ResultData<ExchangeCenter> getExchangeCenterByExchangeCenterId(int exchangeCenterId);
	
	
	/**
	 * 根据用户Id查看默认交易中心
	 * @param userId
	 * @return
	 */
	public ResultData<ExchangeCenter> getDefaultExchangeCenterByUserId(int userId);

}
