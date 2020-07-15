package cn.com.eju.deal.user.api;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.model.Auth;
import cn.com.eju.deal.user.model.Group;

/**   
* 登录接口
* @author (li_xiaodong)
* @date 2016年3月19日 下午12:40:21
*/
public interface ILoginAPI
{
    /**
     * 登录校验，登录成功并获取登录用户信息
    * @return
     */
    public ResultData<UserInfo> login(Map<?, ?> queryParam) throws Exception;
    
    /**
     * 登录校验，登录成功并获取登录用户信息
    * @param loginName 登录名
    * @param passWord  登录密码
    * @param systemKbn 登录系统
    * @return
     */
    public ResultData<UserInfo> login(String loginName, String passWord, String systemKbn, String ipAddress);
    
    /**
     * 获取所有类型为交易中心的group
    * @return
     */
    public ResultData<List<Group>> getAllTradCenterGroup();
    
    /**
     * 获取所有类型为交易中心的group
    * @return
     */
    public ResultData<UserInfo> refreshLogin(int operateUserId, int userId);
    
    /**
     * 获取所有的菜单
    * @return
     */
    public ResultData<List<Auth>> getAuth(String systemName);

    /**
     * 判断是否有门店审核权限
     * @return
     */
    public ResultData<Auth> getStoreAuditAuthByCity(Map<String, Object> queryParam) throws Exception;
    

    public ResultData<UserInfo> pmlslogin(Map<?, ?> queryParam) throws Exception;

    /**
     * 登录校验，登录成功并获取登录用户信息
    * @param loginName 登录名
    * @param passWord  登录密码
    * @param systemKbn 登录系统
    * @return
     */
    public ResultData<UserInfo> pmlslogin(String loginName, String passWord, String systemKbn, String ipAddress);
    
}
