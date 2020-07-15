package cn.com.eju.deal.user.api.impl;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.common.dao.PmlsUserCenterCitySettingMapper;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.CityFirstLetter;
import cn.com.eju.deal.common.model.PmlsUserCenterCitySetting;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.user.api.IAuthAPI;
import cn.com.eju.deal.user.api.IExchangeCenterAPI;
import cn.com.eju.deal.user.api.ILoginAPI;
import cn.com.eju.deal.user.api.IUserAPI;
import cn.com.eju.deal.user.dao.AuthMapper;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.PostMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Auth;
import cn.com.eju.deal.user.model.ExchangeCenter;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.Post;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * 登录服务实现类
 *
 * @author (li_xiaodong)
 * @date 2016年3月19日 下午12:42:13
 */
@Service("loginAPI")
public class LoginAPIImpl implements ILoginAPI {
    
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 传入参数不能为空
     */
    String ERROR_PARAM_NULL = "参数不能为空";

    /**
     * 用户名或密码不能为空！
     */
    String ERROR_PARAM_NULL_LOGIN = "用户名或密码不能为空！";

    /**
     * 用户账号不正确
     */
    String ERROR_USER_LOGIN = "用户工号不正确！";

    /**
     * 用户密码不正确
     */
    String ERROR_USER_LOGIN_PASSWORD = "用户密码不正确！";

    /**
     * 账户状态异常，被锁定10分钟！
     */
    String ERROR_USER_LOCK_TEN_M = "账户状态异常，被锁定10分钟！";

    /**
     * 账户状态异常，被锁定
     */
    String ERROR_USER_LOCK = "账户状态异常，被锁定";

    @Resource
    private IUserAPI userAPI;

    @Resource
    private IAuthAPI authAPI;

    @Resource
    private PostMapper postDao;

    @Resource
    private GroupMapper groupDao;

    @Resource
    private AuthMapper authDao;

    @Resource
    private IExchangeCenterAPI exchangeCenterAPI;

    @Resource
    private UserMapper userDao;

    @Resource
    private PmlsUserCenterCitySettingMapper userCenterCityDao;

    /**
     * 用户登录
     *
     * @param queryParam
     * @return
     */
    @Override
    public ResultData<UserInfo> login(Map<?, ?> queryParam) throws Exception{
        String loginName = (String) queryParam.get("loginName");
        String password = (String) queryParam.get("password");
        String systemName = (String) queryParam.get("systemName");
        String ipAddress = (String) queryParam.get("ipAddress");

        return login(loginName, password, systemName, ipAddress);
    }
    
    /**
     * 登录校验，登录成功并获取登录用户信息
     */
    @Override
    public ResultData<UserInfo> login(String loginName, String password, String systemName, String ipAddress) {
        ResultData<UserInfo> br = new ResultData<UserInfo>();
        UserInfo userInfo = new UserInfo();
        //check
        br = this.loginCheck(loginName, password, systemName, ipAddress);
        if (ReturnCode.FAILURE.equals(br.getReturnCode())) {
            return br;
        } else {
            userInfo = (UserInfo) br.getReturnData();
            //密码清空
            userInfo.setPassword("");
        }
        //get Post
        Post defpost = null;
        List<Post> postList = null;
        try
        {
            defpost = this.postDao.queryDefaultPostByUserId(userInfo.getUserId());
            postList = this.postDao.queryAllPostByUserId(userInfo.getUserId());
        }
        catch (Exception e)
        {
            br.setReturnMsg("获取数据失败。");
            br.setReturnCode(ReturnCode.FAILURE);
            logger.error("user", "LoginAPIImpl", "login", "", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
        }
        List<Auth> authTmpList = null;
        if(null != postList && !postList.isEmpty()){
            for (int i = postList.size()-1; i>=0 ; i--) {
                authTmpList = new ArrayList<Auth>();
                ResultData<List<Auth>> authbr = this.authAPI.getMentListByPostId(userInfo.getUserId(), postList.get(i).getPostId());
                if (ReturnCode.SUCCESS.equals(authbr.getReturnCode())) {
                    authTmpList = authbr.getReturnData();
                }
                if (authTmpList != null && authTmpList.size() > 0) {
                    boolean hasAuthFlag = false;
                    // 剔除没有CRM权限的岗位 [如果权限中有一个可以看见一级菜单 即：parentId == 0 则有改岗位CRM权限]
                    for(Auth auth : authTmpList) {
                        if (0 == auth.getParentId()) {
                        	hasAuthFlag = true;
                            break;
                        }
                    }
                    if (!hasAuthFlag) {
                        postList.remove(postList.get(i));
                    }else{
                        postList.get(i).setAuthList(authTmpList);
                        if (defpost != null && defpost.getPostId() == postList.get(i).getPostId()) {
                            defpost.setAuthList(authTmpList);
                        }
                    }
                } else {
                    postList.remove(postList.get(i));
                }
            }
        }
        
        // 如果登录用户所有岗位都没有crm权限
        if (postList == null || postList.isEmpty()) {
        	br.setReturnMsg("请指定登录用户的岗位。");
            br.setReturnCode(ReturnCode.FAILURE);
            return br;
        }
        // 默认岗位处理
        if (null != defpost) {
        	ResultData<List<Auth>> authList = this.authAPI.getMentListByPostId(userInfo.getUserId(), defpost.getPostId());
        	List<Auth> authDefList = authList.getReturnData();
        	// 默认岗位是否有CRM权限，没有的置成null
            boolean defHasAuthFlag = false;
            if (null != authDefList && !authDefList.isEmpty()) {
            	// 如果权限中有一个可以看见一级菜单 即：parentId == 0 则有改岗位CRM权限
                for(Auth auth : authDefList) {
                    if (0 == auth.getParentId()) {
                    	defHasAuthFlag = true;
                        break;
                    }
                }
                if (!defHasAuthFlag) {
                	if (postList != null && postList.size() > 0) {
                		userInfo.setSelectpostId(postList.get(0).getPostId());
                        userInfo.setSelectPost(postList.get(0));
                	}
                } else {
                	userInfo.setSelectpostId(defpost.getPostId());
                    userInfo.setSelectPost(defpost);
                }
            } else {
            	if (postList != null && postList.size() > 0) {
            		userInfo.setSelectpostId(postList.get(0).getPostId());
                    userInfo.setSelectPost(postList.get(0));
            	}
            }
        }
        //微信端登录才获取中心列表
        if(systemName.equals("CRMWechat")){
            //设置cityNo/CityName
            UserInfo userInfoTemp = null;
            List<ExchangeCenter> centerList=null;
            try
            {
                userInfoTemp = userDao.getCityNoByUserId(userInfo.getUserId());
                centerList=userDao.getCenterListByUserId(userInfo.getUserId());
            }
            catch (Exception e)
            {
                br.setReturnMsg("获取数据失败。");
                br.setReturnCode(ReturnCode.FAILURE);
                logger.error("user", "LoginAPIImpl", "login", "设置cityNo/CityName", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
            }
            if (null != userInfoTemp) {
                userInfo.setExCenterList(centerList);

                if(userInfoTemp.getCenterId()==null || "".equals(userInfoTemp.getCenterId())){
                    if(centerList!=null && centerList.size()>0){
                        userInfo.setCityNo(centerList.get(0).getCityNo());
                        userInfo.setCityName(centerList.get(0).getCityName());
                        userInfo.setCenterId(centerList.get(0).getExchangeCenterId());
                        userInfo.setCenterName(centerList.get(0).getExchangeCenterName());
                        userInfo.setWxGroupName(centerList.get(0).getExchangeCenterName());
                    }
                }else{
                    userInfo.setCityNo(userInfoTemp.getCityNo());
                    userInfo.setCityName(userInfoTemp.getCityName());
                    userInfo.setCenterId(userInfoTemp.getCenterId());
                    userInfo.setCenterName(userInfoTemp.getCenterName());
                    userInfo.setWxGroupName(userInfoTemp.getWxGroupName());
                }
            }
        }else{
        	 //设置cityNo/CityName
            UserInfo userInfoTemp = null;
            try
            {
                userInfoTemp = userDao.getCityNoByUserId(userInfo.getUserId());
            }
            catch (Exception e)
            {
                br.setReturnMsg("获取数据失败。");
                br.setReturnCode(ReturnCode.FAILURE);
                logger.error("user", "LoginAPIImpl", "login", "设置cityNo/CityName", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
            }
            if (null != userInfoTemp) {
                userInfo.setCityNo(userInfoTemp.getCityNo());
                userInfo.setCityName(userInfoTemp.getCityName());
                userInfo.setCenterId(userInfoTemp.getCenterId());
                userInfo.setCenterName(userInfoTemp.getCenterName());
                userInfo.setWxGroupName(userInfoTemp.getWxGroupName());
            }
        }
        userInfo.setPostList(postList);
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnData(userInfo);
        return br;
    }

    /**
     * 登录校验，登录成功并获取登录用户信息
     */
    @Override
    public ResultData<UserInfo> refreshLogin(int operateUserId, int userId) {
        ResultData<UserInfo> br = new ResultData<UserInfo>();
        UserInfo userInfo = new UserInfo();
        br = this.userAPI.getUserInfoById(operateUserId, userId);
        if (ReturnCode.FAILURE.equals(br.getReturnCode())) {
            return br;
        } else {
            userInfo = (UserInfo) br.getReturnData();
            //密码清空
            userInfo.setPassword("");
        }
        List<Post> postList = null;
        try
        {
            postList = this.postDao.queryAllPostByUserId(userInfo.getUserId());
        }
        catch (Exception e)
        {
            br.setReturnMsg("获取数据失败。");
            br.setReturnCode(ReturnCode.FAILURE);
            logger.error("user", "LoginAPIImpl", "refreshLogin", "", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
        }
        List<Auth> authTmpList = null;
        if(null != postList){
            for (Post p : postList) {
                authTmpList = new ArrayList<Auth>();
                //authTmpList = this.authDao.getListByPostId(p.getPostId());
                ResultData<List<Auth>> authbr = this.authAPI.getMentListByPostId(userInfo.getUserId(), p.getPostId());
                if (ReturnCode.SUCCESS.equals(authbr.getReturnCode())) {
                    authTmpList = authbr.getReturnData();
                }
                if (authTmpList != null && authTmpList.size() > 0) {
                    p.setAuthList(authTmpList);
                }
            }
        }else{
            br.setFail();
        }
        userInfo.setPostList(postList);
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnData(userInfo);
        return br;
    }

    @Override
    public ResultData<List<Group>> getAllTradCenterGroup() {
        ResultData<List<Group>> br = new ResultData<List<Group>>();

        List<Group> groupList = null;
        try
        {
            groupList = this.groupDao.selectAllGroupByTypeId();
        }
        catch (Exception e)
        {
            br.setReturnMsg("获取数据失败。");
            br.setReturnCode(ReturnCode.FAILURE);
            logger.error("user", "LoginAPIImpl", "getAllTradCenterGroup", "", null, "", "getAllTradCenterGroup失败！", e);
        }
        if (groupList == null) {
            br.setReturnMsg("获取数据失败。");
            br.setReturnCode(ReturnCode.FAILURE);
        }
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnData(groupList);
        return br;
    }

    /**
     * login check
     */
    private ResultData<UserInfo> loginCheck(String loginName, String password, String systemName, String ipAddress) {
        ResultData<UserInfo> br = new ResultData<UserInfo>();
        if (StringUtil.isEmpty(loginName) || StringUtil.isEmpty(password)) {
            br.setReturnMsg(ERROR_PARAM_NULL_LOGIN);
            br.setReturnCode(ReturnCode.FAILURE);
            return br;
        }
        if (StringUtil.isEmpty(systemName)) {
            br.setReturnMsg("请指定登录的系统。");
            br.setReturnCode(ReturnCode.FAILURE);
            return br;
        }
        //获取db的数据       
        ResultData<UserInfo> ResultData = this.userAPI.getUserByLoginName(loginName);
        
        //判断用户名是否存在
        if (ReturnCode.SUCCESS.equals(ResultData.getReturnCode())) {
            //赋值
        	UserInfo userInfo = (UserInfo) ResultData.getReturnData();
            /*
            //取消数据库密码验证，只返回用户信息
            //数据库的密码
            String dbpassword = userInfo.getPassword();
            String detryptpassword = EncryptUtil.decrypt(dbpassword, EncryptType.AES);
            //比较数据库密码和 输入密码
            if (!password.equals(detryptpassword)) {
                br.setReturnMsg(ERROR_USER_LOGIN_PASSWORD);
                br.setReturnCode(ReturnCode.FAILURE);
                return br;
            } */
            br.setReturnData(userInfo);
            br.setReturnCode(ReturnCode.SUCCESS);
            return br;
        } else {
            br.setReturnMsg(ERROR_USER_LOGIN);
            br.setReturnCode(ReturnCode.FAILURE);
            return br;
        }
    }

    /**
     * 获取所有的菜单
     *
     * @return
     */
    @Override
    public ResultData<List<Auth>> getAuth(String systemName) {
        ResultData<List<Auth>> br = new ResultData<List<Auth>>();
        try {

            List<Auth> brlist = this.authDao.urlNotNulllist(systemName);
            if (brlist != null) {
                br.setReturnData(brlist);
                br.setReturnCode(ReturnCode.SUCCESS);
            } else {
                br.setReturnMsg("获取数据失败。");
                br.setReturnCode(ReturnCode.FAILURE);
            }
        } catch (Exception e) {
            logger.error("user", "LoginAPIImpl", "getAuth", "", null, "", "获取所有的菜单失败！", e);
            br.setReturnMsg("获取数据失败。");
            br.setReturnCode(ReturnCode.FAILURE);
        }

        return br;
    }

    /**
     * 判断是否有门店审核权限
     *
     * @return
     */
    @Override
    public ResultData<Auth> getStoreAuditAuthByCity(Map<String, Object> queryParam) throws Exception{
        ResultData<Auth> resultData = new ResultData<Auth>();
        Auth auth = this.authDao.getStoreAuditAuthByCity(queryParam);
        resultData.setReturnData(auth);
        return resultData;
    }
     /***********************************PMLS Start************************************************/
    /**
     * 用户登录
     *
     * @param queryParam
     * @return
     */
    @Override
    public ResultData<UserInfo> pmlslogin(Map<?, ?> queryParam) throws Exception{
        String loginName = (String) queryParam.get("loginName");
        String password = (String) queryParam.get("password");
        String systemName = (String) queryParam.get("systemName");
        String ipAddress = (String) queryParam.get("ipAddress");

        return pmlslogin(loginName, password, systemName, ipAddress);
    }
    
    
    /**
     * 登录校验，登录成功并获取登录用户信息
     */
    @Override
    public ResultData<UserInfo> pmlslogin(String loginName, String password, String systemName, String ipAddress) {
        ResultData<UserInfo> br = new ResultData<UserInfo>();
        UserInfo userInfo = new UserInfo();
        //check
        br = this.loginCheck(loginName, password, systemName, ipAddress);
        if (ReturnCode.FAILURE.equals(br.getReturnCode())) {
            return br;
        } else {
            userInfo = (UserInfo) br.getReturnData();
            //密码清空
            userInfo.setPassword("");
        }
        
        Integer userId = userInfo.getUserId();
        //
        Map<String, Object> queryParam =new  HashMap<String, Object>();
        queryParam.put("systemCode", systemName);
        queryParam.put("userId", userId);
        queryParam.put("userCode", loginName);
        //菜单
        try {
        	  List<Auth> authTmpList = authDao.getAuthListByParam(queryParam);
              if(authTmpList!=null && authTmpList.size()>0){
              	userInfo.setAuths(authTmpList);
              }else{
            	  br.setReturnMsg("用户未指定菜单权限");
                  br.setReturnCode(ReturnCode.FAILURE);
                  return br;
               }
		} catch (Exception e) {
			// TODO: handle exception
		      br.setReturnMsg("获取菜单数据失败。");
              br.setReturnCode(ReturnCode.FAILURE);
              logger.error("user", "LoginAPIImpl", "login", "获取菜单", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
              return br;
		}
      
       //设置cityNo/CityName
            List<City> ciList = null;
            try
            {
            	ciList= userCenterCityDao.queryCityList(queryParam);

                if (ciList != null && ciList.size() > 0) {
                    userInfo.setCities(ciList);
                    List<CityFirstLetter> flCityList = new ArrayList<>();
                    Map<String, List<City>> cityMap = new HashMap<>();
                    for (City city : ciList) {
                        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
                        // 设置大小写格式
                        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
                        // 设置声调格式：
                        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
                        String firstLetter = PinyinHelper.toHanYuPinyinString(city.getCityName(), defaultFormat, "", true);
                        if (StringUtil.isNotEmpty(firstLetter)) {
                            firstLetter = firstLetter.substring(0, 1);
                        }
                        List<City> tempList = cityMap.get(firstLetter);
                        if (tempList == null) {
                            tempList = new ArrayList<>();
                            tempList.add(city);
                            cityMap.put(firstLetter, tempList);
                        } else {
                            tempList.add(city);
                        }
                    }
                    for (String firstLetter : cityMap.keySet()) {
                        CityFirstLetter city = new CityFirstLetter();
                        city.setFirstLetter(firstLetter);
                        city.setCityList(cityMap.get(firstLetter));
                        flCityList.add(city);
                    }
                    if(!CollectionUtils.isEmpty(flCityList)){
                        Collections.sort(flCityList, new Comparator<CityFirstLetter>() {
                            @Override
                            public int compare(CityFirstLetter o1, CityFirstLetter o2) {
                                return o1.getFirstLetter().compareTo(o2.getFirstLetter());
                            }
                        });
                    }
                    userInfo.setFlCities(flCityList);
                } else {
                    br.setReturnMsg("用户未指定对应中心，城市权限");
                  br.setReturnCode(ReturnCode.FAILURE);
                  return br;            		
            	}
            }
            catch (Exception e)
            {
                br.setReturnMsg("获取数据失败。");
                br.setReturnCode(ReturnCode.FAILURE);
                logger.error("user", "LoginAPIImpl", "login", "设置cityNo/CityName", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
            }
            if (null != ciList) {
            	City  selectCity= ciList.get(0);
                userInfo.setCityNo(selectCity.getCityNo());
                userInfo.setCityName(selectCity.getCityName());
            }
            
            List<PmlsUserCenterCitySetting> centeres = null;
            try
            {
            	centeres= userCenterCityDao.queryCenterList(queryParam);
            	
            	if(centeres!=null&&centeres.size()>0){
            		userInfo.setCenteres(centeres);
            	}else{
            	  br.setReturnMsg("用户未指定对应中心，城市权限");
                  br.setReturnCode(ReturnCode.FAILURE);
                  return br;            		
            	}
            }
            catch (Exception e)
            {
                br.setReturnMsg("获取数据失败。");
                br.setReturnCode(ReturnCode.FAILURE);
                logger.error("user", "LoginAPIImpl", "login", "设置cityNo/CityName", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
            }
            if (null != centeres) {
            	userInfo.setCenteres(centeres);
            }
            		
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnData(userInfo);
        return br;
    }
    
    
    
    /*************************************PMLS End***********************************************/
}
