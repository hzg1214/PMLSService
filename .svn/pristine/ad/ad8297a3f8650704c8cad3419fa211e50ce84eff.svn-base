package cn.com.eju.deal.user.api.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.user.api.IUserBankInfoAPI;
import cn.com.eju.deal.user.dao.UserBankInfoMapper;
import cn.com.eju.deal.user.model.UserBankInfo;

@Service("userBankAPI")
public class UserBankInfoAPIImpl implements IUserBankInfoAPI
{
    @Resource
    private UserBankInfoMapper userBankInfoDao;
    
    private static LogHelper logger = LogHelper.getLogger(UserBankInfoAPIImpl.class);
    
    @Override
    public ResultData<List<UserBankInfo>> getByUserId(int userId)
    {
        ResultData<List<UserBankInfo>> ResultData = new ResultData<List<UserBankInfo>>();
        try
        {
            List<UserBankInfo> userBankInfos = userBankInfoDao.getByUserId(userId);
            ResultData.setReturnData(userBankInfos);
            ResultData.setReturnCode(ReturnCode.SUCCESS);
            ResultData.setReturnMsg("根据用户Id查询银行成功");
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
    
}
