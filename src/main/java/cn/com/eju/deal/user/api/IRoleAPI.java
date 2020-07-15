package cn.com.eju.deal.user.api;

import java.util.List;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.model.Role;

/**   
* 角色接口
* @author 郭伟
* @date 2015年10月27日 10:35:00
*/
public interface IRoleAPI
{
    /**
     * 根据用户id获取用户角色
    * @param operateUserId
    * @param userId
    * @return
     */
    public ResultData<List<Role>> getRoleByUserId(int operateUserId, int userId);
}
