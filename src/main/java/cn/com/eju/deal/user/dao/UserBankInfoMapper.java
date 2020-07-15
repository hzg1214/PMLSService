package cn.com.eju.deal.user.dao;

import java.util.List;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.UserBankInfo;

public interface UserBankInfoMapper extends IDao<UserBankInfo>
{
    
    List<UserBankInfo> queryList(UserBankInfo userBankInfo) throws Exception;
    
    int insert(UserBankInfo userBankInfo) throws Exception;
    
    int delete(Integer bankId) throws Exception;
    
    int updateByBankId(UserBankInfo userBankInfo) throws Exception;
    
    UserBankInfo selectByPrimaryKey(Integer bankId) throws Exception;
    
    List<UserBankInfo> getByUserId(int userId) throws Exception;
    
}
