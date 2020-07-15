package cn.com.eju.deal.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.user.UserDto;
import cn.com.eju.deal.user.api.IAuthAPI;
import cn.com.eju.deal.user.api.ILoginAPI;
import cn.com.eju.deal.user.api.IUserAPI;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Auth;
import cn.com.eju.deal.user.model.ExchangeCenter;
import cn.com.eju.deal.user.model.User;

/**
 * 服务层
 *
 * @author (li_xiaodong)
 * @date 2016年1月19日 下午6:05:44
 */

@RestController
@RequestMapping(value = "users")
public class UserController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private ILoginAPI loginAPI;
    
    @Resource
    private IUserAPI userAPI;
    
    @Resource
    IAuthAPI authAPI;
    
    @Resource
    private UserMapper userDao;

    /**
     * 根据微信用户Id 获取微信的用户信息
     *
     * @param wechatId 微信用户Id
     * @return 用户信息
     */
    @RequestMapping(value = "/wechat/{wechatId}", method = RequestMethod.GET)
    public String getWechatUser(@PathVariable String wechatId)
    {
        ResultData<UserInfo> resultData = userAPI.getUserByWechatId(wechatId);
        return resultData.toString();
    }

    @RequestMapping(value = "/wechatByWechatId/{wechatId}", method = RequestMethod.GET)
    public String getWechatUserByWechatId(@PathVariable String wechatId)
    {
        ResultData<UserInfo> resultData = userAPI.getWechatUserByWechatId(wechatId);
        return resultData.toString();
    }
    
    /**
     * 根据系统Code获取权限
     *
     * @param name 系统Code
     * @return 权限列表
     */
    @RequestMapping(value = "/auth/{name}", method = RequestMethod.GET)
    public String getAuth(@PathVariable String name)
    {
        
        ResultData<List<Auth>> resultData = loginAPI.getAuth(name);
        
        return resultData.toString();
    }
    
    /**
     * 根据系统Code和用户岗位获取权限
     *
     * @return 权限列表
     */
    @RequestMapping(value = "/auth/{userId}/{selectpostId}", method = RequestMethod.GET)
    public String getListByPostId(@PathVariable Integer userId, @PathVariable Integer selectpostId)
    {
        
        ResultData<List<Auth>> resultData = authAPI.getListByPostId(userId, selectpostId);
        
        return resultData.toString();
    }
    
    /**
     * 根据登录用户获取所属中心
     *
     * @param userId 登录用户ID
     * @return 权限列表
     */
    @RequestMapping(value = "/center/{userId}", method = RequestMethod.GET)
    public String getCenterListByUserId(@PathVariable Integer userId)
    {
    	 //构建返回
        ResultData<List<ExchangeCenter>> resultData = new ResultData<List<ExchangeCenter>>();

    	try {
    		List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
			resultData.setReturnData(centerList);
		} catch (Exception e) {
			logger.error("User", "UserController", "getCenterListByUserId", "", 0, "", "获取用户所属中心 ", e);
	        resultData.setFail();
		}
        
        return resultData.toString();
    }
    
    /**
     * 根据用户Code查询用户名和密码
     *
     * @param name 用户Code
     * @return 用户名和密码
     */
    @RequestMapping(value = "/shiro/{name}", method = RequestMethod.GET)
    public String queryShiro(@PathVariable String name)
    {
        
        ResultData<UserDto> resultData = userAPI.queryShiro(name);
        
        return resultData.toString();
    }
    
    /**
     * 根据用户ID和岗位ID查询用户ID集合
     *
     * @return 用户Id集合
     */
    @RequestMapping(value = "/{userId}/{postId}", method = RequestMethod.GET)
    public String delete(@PathVariable int userId, @PathVariable int postId)
    {
        ResultData<List<Integer>> resultData = userAPI.selectUserIdsByParam(userId, postId, 1, true);
        
        return resultData.toString();
    }
    
    /**
     * 获取所有用户 
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/all/{param}", method = RequestMethod.GET)
    public String getAllUser(@PathVariable String param)
    {
        //构建返回
        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        
        try
        {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            
            resultData = userAPI.getAllUser(queryParam);
        }
        catch (Exception e)
        {
            logger.error("User", "UserController", "getAllUser", "", 0, "", "获取所有用户 ", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /**
     * 切换岗位: 根据所选岗位Id 刷新UserInfo
     * @param userId,selectPostId
     * @return 新的userInfo
     */
    @RequestMapping(value = "/userInfo/{userId}/{selectedPostId}", method = RequestMethod.GET)
    public String refreshLogin(@PathVariable Integer userId, @PathVariable Integer selectedPostId) 
    {
        //构建返回
        ResultData<UserInfo> resultData = new ResultData<UserInfo>();
        resultData = userAPI.getNewUserInfo(userId,selectedPostId);
        return resultData.toString();
    }
    
    /**
     * 根据用户组织架构(orgId)查询用户事业部信息
     * @Title: getGNBySelectOrgId
     */
    @RequestMapping(value = "/userInfo/orgId/{selectOrgIdStr}", method = RequestMethod.GET)
    public String getGNBySelectOrgId(@PathVariable String selectOrgIdStr)
    {
        String selectOrgId = selectOrgIdStr.replaceAll("Z", "/");
        ResultData<UserInfo> resultData = new ResultData<>();
        try
        {
            resultData = userAPI.getGNBySelectOrgId(selectOrgId);
        }
        catch (Exception e)
        {
            logger.error("User", "UserController", "queryGNByUserId", "", 0, "", "根据userId查询所属大区 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /**
     * 根据用户Code查用户
     */
    @RequestMapping(value = "/userCode/{userCode}", method = RequestMethod.GET)
    public String getUserByCode(@PathVariable String userCode) throws Exception{
    	ResultData<User> resultData = userAPI.getUserByCode(userCode);
    	return resultData.toString();
    }

    /**
     * 查询拓展人员
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/expander/{param}", method = RequestMethod.GET)
    public String getExpanderUser(@PathVariable String param)
    {
        //构建返回
        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        try
        {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = userAPI.getExpanderUser(queryParam);
        }
        catch (Exception e)
        {
            logger.error("User", "UserController", "getExpanderUser", "", 0, "", "获取拓展人员 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    /**
     * 查询联动业绩人员
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/link/{param}", method = RequestMethod.GET)
    public String getLinkUser(@PathVariable String param)
    {
        //构建返回
        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        try
        {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = userAPI.getLinkUser(queryParam);
        }
        catch (Exception e)
        {
            logger.error("User", "UserController", "getLinkUser", "", 0, "", "查询联动业绩人员 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    /**
     * 查询联动业绩人员
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getMaintenanceUser/{param}", method = RequestMethod.GET)
    public String getMaintenanceUser(@PathVariable String param)
    {
    	//构建返回
    	ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
    	try
    	{
    		Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = userAPI.getMaintenanceUser(queryParam);
    	}
    	catch (Exception e)
    	{
    		logger.error("User", "UserController", "getLinkUser", "", 0, "", "查询联动业绩人员 ", e);
    		resultData.setFail();
    	}
    	return resultData.toString();
    }
    
    /**
     * 查询拓展人员[门店维护人]
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/storeexpander/{param}", method = RequestMethod.GET)
    public String getStoreExpanderUser(@PathVariable String param)
    {
        //构建返回
        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        try
        {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = userAPI.getStoreExpanderUser(queryParam);
        }
        catch (Exception e)
        {
            logger.error("User", "UserController", "getStoreExpanderUser", "", 0, "", "获取门店拓展人员 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /**
     * 【描述】: 通过id获取用户信息
     *
     * @author yinkun
     * @date: 2017年10月12日 上午9:07:15
     * @param
     * @return
     */
    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public String getUserInfoById(@PathVariable Integer id) {
        ResultData<User> result = new ResultData<>();
        
        try {
            result = userAPI.getUserById(id);
        }catch(Exception e) {
            logger.error("User", "UserController", "getUserInfoById", "", 0, "", "通过id获取用户信息", e);
            result.setFail();
        }
        
        return result.toString();
    }

    /**
     * 【描述】: 判断是否有门店审核权限
     *
     * @author jafferson
     * @date: 2018年1月10日 上午9:07:15
     * @param
     * @return
     */
    @RequestMapping(value = "/getStoreAuditAuthByCity", method = RequestMethod.POST)
    public String getStoreAuditAuthByCity(@RequestBody String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try
        {
            resultData = loginAPI.getStoreAuditAuthByCity(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("user", "UserController", "getStoreAuditAuthByCity", "", null, "", "获取该用户是否具有该城市的门店审核信息", e);
        }
        return resultData.toString();
    }
    /**
     * 模糊查询联动业绩人员
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getLinkUserByCondition/{param}", method = RequestMethod.GET)
    public String getLinkUserByCondition(@PathVariable String param)
    {
        //构建返回
        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        try
        {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = userAPI.getLinkUserByCondition(queryParam);
        }
        catch (Exception e)
        {
            logger.error("User", "UserController", "getLinkUserByCondition", "", 0, "", "模糊查询联动业绩人员 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    
    @RequestMapping(value = "/pmlsuserInfo/{userId}/{selectedCityNo}", method = RequestMethod.GET)
    public String pmlsrefreshLogin(@PathVariable Integer userId, @PathVariable String selectedCityNo) 
    {
        //构建返回
        ResultData<UserInfo> resultData = new ResultData<UserInfo>();
        resultData = userAPI.getPmlsNewUserInfo(userId,selectedCityNo);
        return resultData.toString();
    }
    
}
