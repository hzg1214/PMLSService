package cn.com.eju.deal.user.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.user.api.IExchangeCenterAPI;
import cn.com.eju.deal.user.dao.ExchangeCenterMapper;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.City;
import cn.com.eju.deal.user.model.ExchangeCenter;

@Service("exchangeCenterAPI")
public class ExchangeCenterAPIImpl implements IExchangeCenterAPI
{
    
    @Resource
    private UserMapper userDao;
    
    @Resource
    private GroupMapper groupDao;
    
    @Resource
    private ExchangeCenterMapper exchangeCenterDao;
    
    /**
     * 根据用户Id查看交易中心
     * 
     * @param userId
     *            用户ID
     * @return
     */
    @Override
    public ResultData<List<ExchangeCenter>> getExchangeCenterByUserId(int userId)
    {
        
        ResultData<List<ExchangeCenter>> userResult = new ResultData<List<ExchangeCenter>>();
        
        try
        {
            
            List<ExchangeCenter> list = this.exchangeCenterDao.queryExchangeCenterByUserId(userId);
            
            if (list.size() > 0)
            {
                userResult.setReturnCode("200");
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(list);
            }
            else
            {
                userResult.setReturnCode("-1");
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        
        return userResult;
    }
    
    /**
     * 根据城市ID查询所有交易中心的信息
     * 
     * @param cityId
     *            城市ID
     * @return
     */
    public ResultData<List<ExchangeCenter>> getExchangeCenterByCityId(int cityId)
    {
        
        ResultData<List<ExchangeCenter>> userResult = new ResultData<List<ExchangeCenter>>();
        
        try
        {
            List<ExchangeCenter> list = this.exchangeCenterDao.getByCityId(cityId);
            
            if (list.size() > 0)
            {
                userResult.setReturnCode("200");
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(list);
            }
            else
            {
                userResult.setReturnCode("-1");
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        
        return userResult;
    }
    
    /**
     * 根据交易中心ID查询交易中心的信息
     * 
     * @param exchangeCenterId
     *            交易中心ID
     * @return
     */
    @Override
    public ResultData<ExchangeCenter> getExchangeCenterByExchangeCenterId(int exchangeCenterId)
    {
        ResultData<ExchangeCenter> userResult = new ResultData<ExchangeCenter>();
        
        try
        {
            
            ExchangeCenter exchangeCenter =
                this.exchangeCenterDao.getExchangeCenterByExchangeCenterId(exchangeCenterId);
            
            if (null != exchangeCenter)
            {
                userResult.setReturnCode(ReturnCode.SUCCESS);
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(exchangeCenter);
            }
            else
            {
                userResult.setReturnCode(ReturnCode.FAILURE);
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        
        return userResult;
    }
    
    /**
     * 根据城市Code查询交易中心信息
     * 
     * @param CityNo
     * @return
     */
    @Override
    public ResultData<List<ExchangeCenter>> getExchangeCenterByCityNo(String CityNo)
    {
        
        ResultData<List<ExchangeCenter>> userResult = new ResultData<List<ExchangeCenter>>();
        try
        {
            
            City city = this.exchangeCenterDao.getCityByCityNo(CityNo);
            
            List<ExchangeCenter> list = this.exchangeCenterDao.getByCityId(city.getId());
            
            if (list.size() > 0)
            {
                userResult.setReturnCode("200");
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(list);
            }
            else
            {
                userResult.setReturnCode("-1");
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        
        return userResult;
    }
    
    /**
     * 根据用户Id查看默认交易中心
     * @param userId
     * @return
     */
    @Override
    public ResultData<ExchangeCenter> getDefaultExchangeCenterByUserId(int userId)
    {
        ResultData<ExchangeCenter> userResult = new ResultData<ExchangeCenter>();
        try
        {
            /*UserExchangeCenterMapping userExchangeCenterMapping = this.exchangeCenterDao.selectExchangeCenterByUserId(userId);*/
            ExchangeCenter exchangeCenter = this.exchangeCenterDao.getDefaultExchangeCenterByExchangeCenterId(userId);
            if (null != exchangeCenter)
            {
                userResult.setReturnCode("200");
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(exchangeCenter);
            }
            else
            {
                userResult.setReturnCode("-1");
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        
        return userResult;
    }
    
}
