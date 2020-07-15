package cn.com.eju.deal.user.api;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.model.Auth;

/**   
* 权限接口
* @author 郭伟
* @date 2015年10月26日 16:46:00
*/
public interface IAuthAPI
{
    /**
     * 根据用户id获取用户权限
    * @param operateUserId
    * @param postId
    * @return
     */
    public ResultData<List<Auth>> getListByPostId(int operateUserId, int postId); 
    /**
     * 根据用户id获取处理后的权限(按钮以外)
    * @param operateUserId
    * @param postId
    * @return
     */
    public  ResultData<List<Auth>> getMentListByPostId(int operateUserId, int postId);
    
    /**
     * 根据url和type查询权限
     * @param params
     * @return
     */
    public ResultData<Auth> getAuthByUrlAndType(Map<String,Object> params);
}
