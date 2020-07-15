package cn.com.eju.deal.user.api;

import java.util.List;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.model.UserBankInfo;

/**   
* 用户订单统计接口
* @author xulong
* @date 2015年11月26日 下午2:29:19
*/
public interface IUserBankInfoAPI
{
    /** 
    * 根据用户Id查询银行信息
    * @param userId 用户Id
    * @return 用户银行信息对象
    */
    public ResultData<List<UserBankInfo>> getByUserId(int userId);
    
}
