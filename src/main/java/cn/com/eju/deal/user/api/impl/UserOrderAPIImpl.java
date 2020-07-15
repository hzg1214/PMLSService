package cn.com.eju.deal.user.api.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.user.api.IUserOrderAPI;
import cn.com.eju.deal.user.dao.UserOrderMapper;
import cn.com.eju.deal.user.model.UserOrderCount;
import cn.com.eju.deal.user.model.UserOrderDist;

@Service("userOrderAPI")
public class UserOrderAPIImpl implements IUserOrderAPI
{
    @Resource
    private UserOrderMapper userOrderMapper;
    
    private static LogHelper logger = LogHelper.getLogger(UserOrderAPIImpl.class);
    
    @Override
    public ResultData<UserOrderCount> getCountByUserId(Date startDate, Date endDate, int userId, PostTypeCode postTypeCode, int exchangeCenterId)
    {
        ResultData<UserOrderCount> ResultData = new ResultData<UserOrderCount>();
        try
        {
            UserOrderCount userOrderCount = null;
            switch (postTypeCode)
            {
                case PT_ZSJYY:
                    userOrderCount = userOrderMapper.getBySignedUserId(startDate, endDate, userId, exchangeCenterId);
                    break;
                case PT_JYZG:
                    userOrderCount = userOrderMapper.getBySignedUserId(startDate, endDate, 0, exchangeCenterId);
                    break;
                case PT_DKZY:
                    userOrderCount = userOrderMapper.getByLoanUserId(startDate, endDate, userId, exchangeCenterId);
                    break;
                case PT_BZZY:
                    userOrderCount = userOrderMapper.getByPermitUserId(startDate, endDate, userId, exchangeCenterId);
                    break;
                case PT_QZZG:
                    userOrderCount = userOrderMapper.getByPermitUserId(startDate, endDate, 0, exchangeCenterId);
                    break;
                default:
                    userOrderCount = new UserOrderCount();
                    userOrderCount.setNewNum(0);
                    userOrderCount.setFinishNum(0);
                    userOrderCount.setOnTheWayNum(0);
                    userOrderCount.setAllNum(0);
                    break;
            }
            ResultData.setReturnData(userOrderCount);
            ResultData.setReturnCode(ReturnCode.SUCCESS);
            ResultData.setReturnMsg("根据用户和岗位查询订单统计查询成功");
        }
        catch (Exception e)
        {
            //记入--错误日志表
            logger.error("user", "UserOrderAPIImpl", "getCountByUserId()", null, 0, null, "根据用户和岗位查询订单统计异常", e);
            
            ResultData.setReturnData(null);
            ResultData.setReturnCode(ReturnCode.FAILURE);
            ResultData.setReturnMsg(e.getMessage());
        }
        return ResultData;
    }
    
    @Override
    public ResultData<List<UserOrderDist>> getDistByExchangeCenterId(int exchangeCenterId, PostTypeCode postTypeCode)
    {
        ResultData<List<UserOrderDist>> ResultData = new ResultData<List<UserOrderDist>>();
        try
        {
            List<UserOrderDist> userOrderDistList = null;
            switch (postTypeCode)
            {
                case PT_ZSJYY:
                    userOrderDistList = userOrderMapper.getSignedByExchangeCenterId(exchangeCenterId);
                    break;
                case PT_DKZY:
                    userOrderDistList = userOrderMapper.getLoanByExchangeCenterId(exchangeCenterId);
                    break;
                case PT_BZZY:
                    userOrderDistList = userOrderMapper.getPermitByExchangeCenterId(exchangeCenterId);
                    break;
                default:
                    //----Upd Start By Guowei 2015/12/17  Start  For product error---
                    // userOrderDistList = userOrderMapper.getByPostCodeAndExCenterId(exchangeCenterId, postTypeCode.toString());
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("exchangeCenterId", exchangeCenterId);
                    map.put("PostTypeCode", postTypeCode.toString());
                    map.put(Constant.SQL_UN_CONTROL, false);//精髓，目的就是为了不让sql转大写
                    userOrderDistList = userOrderMapper.getByPostCodeAndExCenterId(map);
                    //----Upd Start By Guowei 2015/12/17  End  For product error---
                    break;
            }
            ResultData.setReturnData(userOrderDistList);
            ResultData.setReturnCode(ReturnCode.SUCCESS);
            ResultData.setReturnMsg("根据交易中心和岗位查询分配人员列表查询成功");
        }
        catch (Exception e)
        {
            //记入--错误日志表
            logger.error("user", "UserOrderAPIImpl", "getDistByExchangeCenterId()", null, 0, null, "根据交易中心和岗位查询分配人员列表异常", e);
            
            ResultData.setReturnData(null);
            ResultData.setReturnCode(ReturnCode.FAILURE);
            ResultData.setReturnMsg(e.getMessage());
        }
        return ResultData;
    }
}
