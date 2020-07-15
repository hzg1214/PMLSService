package cn.com.eju.deal.user.dao;

import java.util.List;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.City;
import cn.com.eju.deal.user.model.ExchangeCenter;
import cn.com.eju.deal.user.model.UserExchangeCenterMapping;

public interface ExchangeCenterMapper extends IDao<ExchangeCenter>
{
    
    List<ExchangeCenter> selectExchange() throws Exception;
    
    int addExchangeCenter(ExchangeCenter exchangeCenter) throws Exception;
    
    int updateExchangeCenter(ExchangeCenter exchangeCenter) throws Exception;
    
    int deleteExchangeCenter(ExchangeCenter exchangeCenter) throws Exception;
    
    int checkExchangeCenterName(String exchangeCenterName) throws Exception;
    
    ExchangeCenter queryExchangeCenterByExchangeCode(String exchangeCode) throws Exception;
    
    List<ExchangeCenter> queryExchangeCenterByUserId(int userId) throws Exception;
    
    List<ExchangeCenter> getByCityId(int cityId) throws Exception;
    
    ExchangeCenter getExchangeCenterByExchangeCenterId(int exchangeCenterId) throws Exception;
    
    City getCityByCityNo(String cityNo) throws Exception;
    
    UserExchangeCenterMapping selectExchangeCenterByUserId(int userId) throws Exception;
    
    ExchangeCenter getDefaultExchangeCenterByExchangeCenterId(Integer exchangeCenterId) throws Exception;
    
}
