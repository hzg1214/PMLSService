package cn.com.eju.deal.user.api.impl;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.PmlsUserCenterCitySettingMapper;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.CityFirstLetter;
import cn.com.eju.deal.common.model.PmlsUserCenterCitySetting;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.user.UserDto;
import cn.com.eju.deal.user.api.IAuthAPI;
import cn.com.eju.deal.user.api.IExchangeCenterAPI;
import cn.com.eju.deal.user.api.IUserAPI;
import cn.com.eju.deal.user.dao.AuthMapper;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.PostMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.*;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;

@Service("userAPI")
public class UserAPIImpl implements IUserAPI
{
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private IAuthAPI authAPI;
    
    @Resource
    private PostMapper postDao;
    
    @Resource
    private GroupMapper groupDao;
    
    @Resource
    private IExchangeCenterAPI exchangeCenterAPI;
    
    @Resource
    private UserMapper userDao;

    @Resource
    private AuthMapper authDao;
    @Resource
    private PmlsUserCenterCitySettingMapper userCenterCityDao;
    
    @Override
    public ResultData<User> getUserById(int userId)
    {
        ResultData<User> userResult = new ResultData<>();
        try
        {
            User user = this.userDao.selectByPrimaryKey(userId);
            if (user != null)
            {
                userResult.setReturnCode(ReturnCode.SUCCESS);
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(user);
            }
            else
            {
                userResult.setReturnCode(ReturnCode.FAILURE);
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            userResult.setReturnCode(ReturnCode.FAILURE);
            userResult.setReturnMsg("调用出错");
        }
        return userResult;
    }
    
    /**
     * 根据登录用户名，获取用户信息
     *
     * @param loginName 用户登录名
     */
    @Override
    public ResultData<UserInfo> getUserByLoginName(String loginName)
    {
        ResultData<UserInfo> userResult = new ResultData<>();
        try
        {
            UserInfo user = this.userDao.selectByLoginName(loginName);
            if (user != null)
            {
                userResult.setReturnCode("200");
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(user);
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
    
    @Override
    public ResultData<List<User>> getUserByGroupId(int operateUserId, int groupId)
    {
        ResultData<List<User>> userResult = new ResultData<>();
        try
        {
            List<User> userList = this.userDao.selectByGroupId(groupId);
            if (userList.size() > 0)
            {
                userResult.setReturnCode("200");
                userResult.setReturnMsg("调用成功");
                userResult.setReturnData(userList);
            }
            else
            {
                userResult.setReturnCode("-1");
                userResult.setReturnMsg("未找到数据");
            }
        }
        catch (Exception e)
        {
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        return userResult;
    }
    
    @Override
    public ResultData<Integer> updateUser(int operateUserId, User user)
    {
        ResultData<Integer> userResult = new ResultData<>();
        try
        {
            int result = this.userDao.updateByPrimaryKeySelective(user);
            userResult.setReturnCode("200");
            userResult.setReturnMsg("调用成功");
            userResult.setReturnData(result);
        }
        catch (Exception e)
        {
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
            userResult.setReturnData(0);
        }
        return userResult;
    }
    
    @Override
    public ResultData<Integer> addUser(int operateUserId, User user)
    {
        ResultData<Integer> userResult = new ResultData<>();
        try
        {
            int result = this.userDao.insertSelective(user);
            userResult.setReturnCode("200");
            userResult.setReturnMsg("调用成功");
            userResult.setReturnData(result);
        }
        catch (Exception e)
        {
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
            userResult.setReturnData(0);
        }
        return userResult;
    }
    
    @Override
    public ResultData<UserInfo> getUserInfoById(int operateUserId, int userId)
    {
        ResultData<UserInfo> userResult = new ResultData<>();
        UserInfo userinfo;
        try
        {
            userinfo = this.userDao.queryByUserId(userId);
            this.setUserInformation(userinfo);
            userResult.setReturnCode("200");
            userResult.setReturnMsg("调用成功");
            userResult.setReturnData(userinfo);
        }
        catch (Exception e)
        {
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        return userResult;
    }
    
    
    @Override
    public ResultData<UserInfo> getUserInfoByIdPost(Integer operateUserId, Integer userId, Integer selectedPostId)
    {
        ResultData<UserInfo> userResult = new ResultData<>();
        UserInfo userinfo;
        try
        {
            userinfo = this.userDao.queryByUserId(userId);
            this.setUserInformationByPost(userinfo, selectedPostId);
            userResult.setReturnCode("200");
            userResult.setReturnMsg("调用成功");
            userResult.setReturnData(userinfo);
        }
        catch (Exception e)
        {
            userResult.setReturnCode("0");
            userResult.setReturnMsg("调用出错");
        }
        return userResult;
    }
    
    /**
     * 依据微信号，获取用户信息
     *
     * @param wechatId 微信编号
     * @return 用户信息
     */
    @Override
    public ResultData<UserInfo> getUserByWechatId(String wechatId)
    {
        ResultData<UserInfo> resultData = new ResultData<>();
        UserInfo userInfo = null;
        try
        {
            userInfo = this.userDao.getUserByWechatId(wechatId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("user", "UserAPIImpl", "getUserByWechatId", "", null, "依据微信号，获取用户信息失败！", "", e);
        }
        
        if(null == userInfo){
            resultData.setFail();
        }else{
            //获取所有岗位
            List<Post> postList = null;
            Post defpost = null;
            try
            {
                defpost = this.postDao.queryDefaultPostByUserId(userInfo.getUserId());
                postList = this.postDao.queryAllPostByUserId(userInfo.getUserId());
            }
            catch (Exception e)
            {
               logger.error("user", "UserAPIImpl", "getUserByWechatId", "", null, "", "获取微信用户多个岗位失败！", e);
               resultData.setFail();
            }
            
            //对岗位进行筛选
            List<Auth> authTmpList = null;
            if(null != postList && !postList.isEmpty()){
                for (int i = postList.size()-1; i>=0 ; i--) {
                    authTmpList = new ArrayList<Auth>();
                    
                    ResultData<List<Auth>> authbr = this.authAPI.getMentListByPostId(userInfo.getUserId(), postList.get(i).getPostId());
                    
                    if (ReturnCode.SUCCESS.equals(authbr.getReturnCode())) {
                        authTmpList = authbr.getReturnData();
                    }
                    if (authTmpList != null && authTmpList.size() > 0) {
                        boolean delFlag = true;
                        // 剔除没有CRM权限的岗位
                        for(Auth auth : authTmpList) {
                            if (0 == auth.getParentId()) {
                                delFlag = false;
                                break;
                            }
                        }
                        if (delFlag) {
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
                //踢出后判断岗位列表是否为空
                if(null != postList && !postList.isEmpty()){
                    userInfo.setSelectpostId(postList.get(0).getPostId());  
                }
            }else{
                resultData.setFail();
            }
        }
        setUserInformation(userInfo);
        resultData.setReturnData(userInfo);
        
        return resultData;
    }
    
    /**
     * 依据岗位组获取用户信息
     *
     * @param operateUserId 操作人Id
     * @param groupId       组id
     * @param postType      岗位类型编码
     */
    public ResultData<List<UserInfo>> getUserInfoListByGroupAndPost(int operateUserId, int groupId, String postType)
    {
        ResultData<List<UserInfo>> br = new ResultData<>();
        if ((groupId <= 0) || StringUtil.isEmpty(postType))
        {
            br.setReturnCode("传入参数不正确！");
            br.setReturnMsg("调用出错");
            return br;
        }
        Map<String, Object> map;
        map = new HashMap<>();
        map.put("groupId", groupId);
        map.put("typeCode", postType);
        //对sql不进行处理
        map.put(Constant.SQL_UN_CONTROL, false);
        try
        {
            List<UserInfo> list = this.userDao.getUserInfoListByGroupAndPost(map);
            if (list != null)
            {
                br.setReturnCode(ReturnCode.SUCCESS);
                br.setReturnMsg("调用成功");
                br.setReturnData(list);
            }
            else
            {
                br.setReturnCode(ReturnCode.FAILURE);
                br.setReturnMsg("调用出错");
            }
        }
        catch (Exception e)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("调用出错");
        }
        
        return br;
    }
    
    /**
     * 查找某个用户的领导
     *
     * @param operateUserId 操作人Id
     * @param userId        用户对象
     * @param oneFlg        true：直属领导  false：所有领导
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<User>> selectLeaders(Integer operateUserId, Integer userId, Boolean oneFlg)
    {
        ResultData<List<User>> br = new ResultData<>();
        if (userId == null || userId <= 0)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("参数不正确。");
            return br;
        }
        
        Post p = null;
        try
        {
            p = this.postDao.selectMainPostByUserId(userId);
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "查找某个用户的领导失败！", e);
        }
        if (p == null)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("用户参数,无法获得岗位信息。");
            return br;
        }
        
        if (oneFlg == null)
        {
            oneFlg = false;
        }
        
        List<Group> groupList = null;
        try
        {
            groupList = this.groupDao.selectAllParentByGroupId(p.getGroupId());
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "查找某个用户的领导失败！", e);
        }
        List<User> relist = new ArrayList<>();
        if( null != groupList){
            for (int i = groupList.size() - 1; i > 0; i--)
            {
                
                int groupId = ((Group)groupList.get(i)).getGroupId();
                //查找该Group的所有有审批权限的岗位
                List<Post> ps = null;
                try
                {
                    ps = this.postDao.selectApPostByGroupId(groupId);
                }
                catch (Exception e)
                {
                    br.setFail();
                    logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "查找某个用户的领导失败！", e);
                }
                if(null != ps){
                    //循环岗位
                    for (int pindex = 0; pindex < ps.size(); pindex++)
                    {
                        Post o = ps.get(pindex);
                        //如果 是申请岗位所在 group
                        if (groupId == p.getGroupId())
                        {
                            //只有审批权限大于自身的才可以审批
                            if (o.getApproveIdx() > p.getApproveIdx())
                            {
                                List<User> apUserList = null;
                                try
                                {
                                    apUserList = this.userDao.selectApUserListByPostId(o.getPostId());
                                }
                                catch (Exception e)
                                {
                                    br.setFail();
                                    logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "查找某个用户的领导失败！", e);
                                }
                               if( null != apUserList){
                                    for (User u : apUserList)
                                    {
                                        if (oneFlg && relist.size() > 0)
                                        {
                                            break;
                                        }
                                        relist.add(u);
                                    }
                               }
                            }
                        }
                        else
                        {
                            List<User> apUserList = null;
                            try
                            {
                                apUserList = this.userDao.selectUserListByPostId(o.getPostId());
                            }
                            catch (Exception e)
                            {
                                br.setFail();
                                logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "查找某个用户的领导失败！", e);
                            }
                            if(null != apUserList){
                                for (User u : apUserList)
                                {
                                    if (oneFlg && relist.size() > 0)
                                    {
                                        break;
                                    }
                                    relist.add(u);
                                }
                            }
                        }
                        
                        if (oneFlg && relist.size() > 0)
                        {
                            break;
                        }
                    }
                    
                    if (oneFlg && relist.size() > 0)
                    {
                        break;
                    }
                }
            }
        }
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnMsg("调用成功");
        br.setReturnData(relist);
        
        return br;
    }
    
    /**
     * 查找某个用户的领导
     *
     * @param operateUserId 操作人Id
     * @param userId        用户对象
     * @param allFlg        true：所有的下属  false：同组的下属
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<User>> selectSubordinates(Integer operateUserId, Integer userId, Boolean allFlg)
    {
        ResultData<List<User>> br = new ResultData<>();
        if (userId == null || userId <= 0)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("参数不正确");
            return br;
        }
        
        Post p = null;
        try
        {
            p = this.postDao.selectMainPostByUserId(userId);
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "用户信息上面的岗位，不是审批岗位，无下属成员！", e);
        }
        if (p == null || p.getApproveIdx() == 0)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("用户信息上面的岗位，不是审批岗位，无下属成员");
            return br;
        }
        Map<String, Object> map;
        map = new HashMap<>();
        map.put("userId", userId);
        map.put("postId", p.getPostId());
        map.put("groupId", p.getGroupId());
        // 不进行拦截Sql处理
        map.put(Constant.SQL_UN_CONTROL, false);
        //找到 同组下的所有post
        List<User> selectList = null;
        try
        {
            if (allFlg == null || !allFlg)
            {
                selectList = this.userDao.getSubordinatesUser(map);
            }
            else
            {
                selectList = this.userDao.getAllSubordinatesUser(map);
            }
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectLeaders", "", null, "", "找到 同组下的所有post失败！", e);
        }
        if (selectList != null)
        {
            br.setReturnCode(ReturnCode.SUCCESS);
            br.setReturnMsg("调用成功");
            br.setReturnData(selectList);
        }
        else
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("调用出错");
        }
        
        return br;
    }
    
    @Override
    public ResultData<UserDto> queryShiro(String userCode)
    {
        ResultData<UserDto> resultData = new ResultData<UserDto>();
        
        resultData.setReturnCode(ReturnCode.SUCCESS);
        
        User user = null;
        try
        {
            user = userDao.queryByUserCodeShiro(userCode);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("user", "UserAPIImpl", "queryShiro", "", null, "", "", e);
        }
        if (null == user)
        {
            resultData.setReturnCode(ReturnCode.FAILURE);
        }
        
        //转换
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(user, userDto);
        
        resultData.setReturnData(userDto);
        
        return resultData;
    }
    
    /**
     * 根据用户ID和岗位ID查询用户数据
     *
     * @param userId 用户ID
     * @param postId 岗位ID
     * @param type   查询类型【1-查询同组及子组下属，2-查询同组下属，3-查询直属上级，4-查询所有上级】
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<Integer>> selectUserIdsByParam(Integer userId, Integer postId, Integer type,
        boolean isSelfUser)
    {
        //返回
        ResultData<List<Integer>> resultData = new ResultData<List<Integer>>();
        
        ResultData<List<User>> userBr = this.selectUserListByParam(userId, postId, type, isSelfUser);
        
        List<User> users = userBr.getReturnData();
        
        List<Integer> userIds = new ArrayList<Integer>();
        
        for (User user : users)
        {
            userIds.add(user.getUserId());
        }
        //去除集合中的重复元素
        for(int i = userIds.size()-1;i >= 0; i--){
        	for(int j=0,length=userIds.size();j<length;j++){
        		if(userIds.get(i).equals(userIds.get(j)) && i!=j){
        			userIds.remove(i);
        			break;
        		}
        	}
        }
        resultData.setReturnData(userIds);
        
        return resultData;
    }
    
    /**
     * 查找某个用户的领导
     *
     * @param postId 岗位ID
     * @param oneFlg true：直属领导  false：所有领导
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<User>> selectLeadersByParam(Integer postId, Boolean oneFlg)
    {
        ResultData<List<User>> br = new ResultData<List<User>>();
        if (postId == null || postId <= 0)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("参数不正确。");
            return br;
        }
        
        Post p = null;
        try
        {
            p = this.postDao.selectByPrimaryKey(postId);
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectLeadersByParam", "", null, "", "查找某个用户的领导", e);
        }
        if (p == null)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("用户参数,无法获得岗位信息。");
            return br;
        }
        
        if (oneFlg == null)
        {
            oneFlg = false;
        }
        List<Group> groupList = null;
        try
        {
            groupList = this.groupDao.selectAllParentByGroupId(p.getGroupId());
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectLeadersByParam", "", null, "", "用户参数,无法获得岗位信息", e);
        }
        List<User> relist = new ArrayList<User>();
        if(null != groupList){
            for (int i = groupList.size() - 1; i > 0; i--)
            {
                
                int groupId = ((Group)groupList.get(i)).getGroupId();
                //查找该Group的所有有审批权限的岗位
                List<Post> ps = null;
                try
                {
                    ps = this.postDao.selectApPostByGroupId(groupId);
                }
                catch (Exception e)
                {
                    br.setFail();
                    logger.error("user", "UserAPIImpl", "selectLeadersByParam", "", null, "", "查找该Group的所有有审批权限的岗位失败", e);
                }
                if(null != ps){
                    //循环岗位
                    for (int pindex = 0; pindex < ps.size(); pindex++)
                    {
                        Post o = ps.get(pindex);
                        //如果 是申请岗位所在 group
                        try
                        {
                            if (groupId == p.getGroupId())
                            {
                                //只有审批权限大于自身的才可以审批
                                if (o.getApproveIdx() > p.getApproveIdx())
                                {
                                    List<User> apUserList = this.userDao.selectApUserListByPostId(o.getPostId());
                                    for (User u : apUserList)
                                    {
                                        if (oneFlg && relist.size() > 0)
                                        {
                                            break;
                                        }
                                        relist.add(u);
                                    }
                                }
                            }
                            else
                            {
                                List<User> apUserList = this.userDao.selectUserListByPostId(o.getPostId());
                                for (User u : apUserList)
                                {
                                    if (oneFlg && relist.size() > 0)
                                    {
                                        break;
                                    }
                                    relist.add(u);
                                }
                            }
                        }
                        catch (Exception e)
                        {
                            br.setFail();
                            logger.error("user", "UserAPIImpl", "selectLeadersByParam", "", null, "如果 是申请岗位所在 group失败", "如果 是申请岗位所在 group失败", e);
                        }
                        
                        if (oneFlg && relist.size() > 0)
                        {
                            break;
                        }
                    }
               
                    if (oneFlg && relist.size() > 0)
                    {
                        break;
                    }
                }
            }
        }
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnMsg("调用成功");
        br.setReturnData(relist);
        
        return br;
    }
    
    /**
     * 根据用户ID和岗位ID查询用户数据
     *
     * @param userId 用户ID
     * @param postId 岗位ID
     * @param type   查询类型【1-查询同组及子组下属，2-查询同组下属，3-查询直属上级，4-查询所有上级】
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<User>> selectUserListByParam(Integer userId, Integer postId, Integer type, boolean isSelfUser)
    {
        ResultData<List<User>> br = new ResultData<List<User>>();
        if (userId == null || postId == null || type == null)
        {
            br.setReturnCode(ReturnCode.FAILURE);
            br.setReturnMsg("参数不正确");
            return br;
        }
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("postId", postId);
        User user = null;
        Post post = null;
        try
        {
            user = userDao.selectByPrimaryKey(userId);
            post = postDao.selectByPrimaryKey(postId);
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectUserListByParam", "", null, "", "根据用户ID和岗位ID查询用户数据失败", e);
        }
        // 不进行拦截Sql处理
        map.put(Constant.SQL_UN_CONTROL, false);
        List<User> selectList = new ArrayList<User>();
        try
        {
          if(null != post){  
                if (type.equals(1))
                {//查询用户同组及子组下属用户以及本身用户
                    map.put("groupId", post.getGroupId());
                    selectList = this.userDao.getAllSubordinatesUser(map);
                }
                else if (type.equals(2))
                {//查询用户同组下属用户
                    map.put("groupId", post.getGroupId());
                    selectList = this.userDao.getSubordinatesUser(map);
                }
                else if (type.equals(3))
                {//查询直属上级用户
                    br = this.selectLeadersByParam(postId, true);
                    if (!br.getReturnCode().equals("200"))
                    {
                        return br;
                    }
                    else
                    {
                        selectList = br.getReturnData();
                    }
                }
                else if (type.equals(4))
                {//查询所有上级用户
                    br = this.selectLeadersByParam(postId, false);
                    if (!br.getReturnCode().equals("200"))
                    {
                        return br;
                    }
                    else
                    {
                        selectList = br.getReturnData();
                    }
                }
            }
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "selectUserListByParam", "", null, "", "查询用户同组及子组下属用户以及本身用户失败", e);
        }
        if (isSelfUser)
        {//是否包含当前用户
            selectList.add(user);
        }
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnMsg("调用成功");
        br.setReturnData(selectList);
        return br;
    }
    
    /**
     * 设置用户的附属信息
     *
     * @param userInfo 用户对象
     */
    private void setUserInformation(UserInfo userInfo)
    {
        //清空密码
        userInfo.setPassword("");
        
        setUserPost(userInfo);
        
        setUserCity(userInfo);
    }
    
    /**
     * 设置用户的附属信息
     *
     * @param userInfo 用户对象
     */
    private void setUserInformationByPost(UserInfo userInfo, Integer selectedPostId) throws Exception
    {
        setUserCityByPost(userInfo,selectedPostId);
    }
    
    /**
     * 设置用户岗位
     *
     * @param userInfo 用户信息
     */
    private void setUserPost(UserInfo userInfo)
    {
        Post defpost = null;
        List<Post> postList = null;
        try
        {
            defpost = this.postDao.queryDefaultPostByUserId(userInfo.getUserId());
            postList = this.postDao.queryAllPostByUserId(userInfo.getUserId());
        }
        catch (Exception e)
        {
            logger.error("user", "UserAPIImpl", "setUserPost", "", null, "", "设置用户岗位失败", e);
        }
        List<Auth> authTmpList;
        if(null != postList){
            for (Post p : postList)
            {
                authTmpList = new ArrayList<>();
                
                ResultData<List<Auth>> authbr = this.authAPI.getMentListByPostId(userInfo.getUserId(), p.getPostId());
                if (ReturnCode.SUCCESS.equals(authbr.getReturnCode()))
                {
                    authTmpList = authbr.getReturnData();
                }
                
                if (authTmpList != null && authTmpList.size() > 0)
                {
                    p.setAuthList(authTmpList);
                    
                    if (defpost != null && defpost.getPostId() == p.getPostId())
                    {
                        defpost.setAuthList(authTmpList);
                    }
                }
            }
        
            if (postList != null && postList.size() > 0)
            {
                if (defpost == null)
                {
                    userInfo.setSelectpostId(postList.get(0).getPostId());
                    userInfo.setSelectPost(postList.get(0));
                }
                else
                {
                    userInfo.setSelectpostId(defpost.getPostId());
                    userInfo.setSelectPost(defpost);
                }
            }
        }
        userInfo.setPostList(postList);
    }
    
    /**
     * 设置用户城市
     *
     * @param userInfo 用户信息
     */
    private void setUserCity(UserInfo userInfo)
    {
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
            logger.error("user", "UserAPIImpl", "setUserCity", "", null, "", "设置用户城市失败", e);
        }
        if (null != userInfoTemp)
        {
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
    }
    
    /** 
    * 获取所有用户 
    * @return
     * @throws Exception 
    */
    @Override
    public ResultData<List<UserDto>> getAllUser(Map<?, ?> param) throws Exception
    {
        
        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        
        List<User> moList = userDao.getAllUser(param);
        
        //转换
        List<UserDto> dtoList = convertData(moList);
        
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        
        resultData.setReturnData(dtoList);
        
        
        return resultData;
    }
    
    /** 
     * 对象转换MO--DTO
     * @param moList
     * @return List<StudentDto>
     */
    private List<UserDto> convertData(List<User> moList)
        throws Exception
    {
        List<UserDto> dtoList = new ArrayList<UserDto>();
        
        if (null != moList && !moList.isEmpty())
        {
            UserDto dto = null;
            for (User mo : moList)
            {
                dto = new UserDto();
                BeanUtils.copyProperties(mo, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    /**
     * 根据登录用户组，获取用户组信息
     *
     * @param groupId 用户登录名
     */
    @Override
    public ResultData<List<Group>> selectAllChildrenByGroupId(Integer groupId) throws Exception{
        ResultData<List<Group>> groupResult = new ResultData<>();
        try {
            List<Group> group = this.groupDao.selectAllChildrenByGroupId(groupId);
            if (group != null) {
                groupResult.setReturnCode("200");
                groupResult.setReturnMsg("调用成功");
                groupResult.setReturnData(group);
            } else {
                groupResult.setReturnCode("-1");
                groupResult.setReturnMsg("未找到数据");
            }
        } catch (Exception e) {
            e.printStackTrace();
            groupResult.setReturnCode("0");
            groupResult.setReturnMsg("调用出错");
        }
        return groupResult;
    }

    @Override
    public ResultData<List<Group>> getGroupByTypeId(Integer typeId) {
        ResultData<List<Group>> br = new ResultData<List<Group>>();

        List<Group> groupList = null;
        try
        {
            groupList = this.groupDao.selectGroupByTypeId(typeId);
        }
        catch (Exception e)
        {
            br.setFail();
            logger.error("user", "UserAPIImpl", "getGroupByTypeId", "", null, "", "getGroupByTypeId失败", e);
        }
        if (groupList == null) {
            br.setReturnMsg("获取数据失败。");
            br.setReturnCode(ReturnCode.FAILURE);
        }
        br.setReturnCode(ReturnCode.SUCCESS);
        br.setReturnData(groupList);
        return br;
    }
    
    @Override
    public ResultData<UserInfo> getNewUserInfo(Integer userId, Integer selectedPostId)
    {
        UserInfo userInfo = new UserInfo();
        // 根据用户Id获得用户信息
        ResultData<UserInfo> resultData =  this.getUserInfoByIdPost(userId, userId, selectedPostId);
        if ("0".equals(resultData.getReturnCode())){
            return resultData;
        }else{
            userInfo = (UserInfo)resultData.getReturnData();
            //密码清空
            userInfo.setPassword("");
        }
        // 跟据用户Id查询岗位
        List<Post> postList = null;
        try
        {
            postList = this.postDao.queryAllPostByUserId(userInfo.getUserId());
        }
        catch (Exception e)
        {
            logger.error("user", "UserAPIImpl", "getNewUserInfo", "", null, "", "根据用户Id获得用户信息失败", e);
        }
        List<Auth> authTmpList = null;
        if (null != postList && !postList.isEmpty()) {
            for (int i = postList.size()-1; i>=0 ; i--) {
                authTmpList = new ArrayList<Auth>();
                ResultData<List<Auth>> authbr = this.authAPI.getMentListByPostId(userInfo.getUserId(), postList.get(i).getPostId());
                if(ReturnCode.SUCCESS.equals(authbr.getReturnCode())){
                    authTmpList =authbr.getReturnData();
                }
                if (authTmpList != null && authTmpList.size() > 0){
                    boolean delFlag = true;
                    // 剔除没有CRM权限的岗位
                    for(Auth auth : authTmpList) {
                        if (0 == auth.getParentId()) {
                            delFlag = false;
                            break;
                        }
                    }
                    if(delFlag){
                        postList.remove(postList.get(i));
                    }else{
                        postList.get(i).setAuthList(authTmpList);
                    }
                }else{
                    postList.remove(postList.get(i));
                }
            }
            for (Post o : postList) {
                if (selectedPostId.intValue() == o.getPostId().intValue()){
                    userInfo.setSelectPost(o);
                    userInfo.setSelectpostId(o.getPostId());
                    break;
                }
            }
            userInfo.setPostList(postList);
            resultData.setReturnCode(ReturnCode.SUCCESS);
            resultData.setReturnData(userInfo);
        } else {
            resultData.setReturnCode(ReturnCode.FAILURE);
            resultData.setReturnMsg("岗位为空");
        }
        return resultData;
    }
    
    /**
     * 设置用户城市 By PostId
     *
     * @param userInfo 用户信息
     */
    private void setUserCityByPost(UserInfo userInfo,Integer selectedPostId) throws Exception
    {
        //设置cityNo/CityName
        UserInfo userInfoTemp = userDao.getCityNoByPostId(selectedPostId);
        if (null != userInfoTemp)
        {
            userInfo.setCityNo(userInfoTemp.getCityNo());
            userInfo.setCityName(userInfoTemp.getCityName());
        }
    }

    /**
     * 根据selectOrgId查询所属大区
     * @Title: getGNBySelectOrgId
     * @param selectOrgId
     */
    @Override
    public ResultData<UserInfo> getGNBySelectOrgId(String selectOrgId) throws Exception
    {
        ResultData<UserInfo> resultData = new ResultData<UserInfo>();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("selectOrgId", selectOrgId);
        param.put(Constant.SQL_UN_CONTROL, false);
        UserInfo userInfo = this.userDao.getGNBySelectOrgId(param);
        resultData.setReturnData(userInfo);
        return resultData;
    }
    
    /**
     * 根据用户Code查用户
     */
    public ResultData<User> getUserByCode(String userCode) throws Exception{
    	ResultData<User> resultData = new ResultData<User>();
    	User user = userDao.getUserByCode(userCode);
    	resultData.setReturnData(user);
    	return resultData;
    }
    
    /** 
     * 查询拓展人员
     */
     @Override
     public ResultData<List<UserDto>> getExpanderUser(Map<?, ?> param) throws Exception
     {
         ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
         List<User> moList = userDao.getExpanderUser(param);
         //转换
         List<UserDto> dtoList = convertData(moList);
         resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
         resultData.setReturnData(dtoList);
         return resultData;
     }

    @Override
    public ResultData<List<UserDto>> getLinkUser(Map<?, ?> param) throws Exception {

        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        List<User> moList = userDao.getLinkUser(param);
        List<UserDto> dtoList = convertData(moList);
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(dtoList);

        return resultData;
    }
    @Override
    public ResultData<List<UserDto>> getMaintenanceUser(Map<?, ?> param) throws Exception {
    	
    	ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
    	List<User> moList = userDao.getMaintenanceUser(param);
    	List<UserDto> dtoList = convertData(moList);
    	resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
    	resultData.setReturnData(dtoList);
    	
    	return resultData;
    }

    /**
      * 门店->新增维护人
      */
      @SuppressWarnings({ "rawtypes", "unchecked" })
      @Override
      public ResultData<List<UserDto>> getStoreExpanderUser(Map param) throws Exception
      {
          ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
         /* List<User> moList = userDao.getExpanderUser(param);
          if(null!=moList && !moList.isEmpty()){
			String typeCode = SystemParam.getWebConfigValue(SystemParam.getWebConfigValue("centerConfig"));
			for (User u : moList) {
				String userCode = u.getUserCode();
				String centerName = groupDao.getCenterNameByUser(userCode, typeCode);
				u.setCenterName(centerName);
			}
          }*/
          // BTEJBM
          String typeCode = SystemParam.getWebConfigValue("centerConfig");
          param.put("typeCode", typeCode);
          List<User> moList = userDao.getWeiHuUser(param);
          //转换
          List<UserDto> dtoList = convertData(moList);
          resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
          resultData.setReturnData(dtoList);
          return resultData;
      }

      
	@Override
	public User getUserByUserCode(String userCode) {
		User user = userDao.getUserByUserCode(userCode);
		return user;
	}
	@Override
    public ResultData<List<UserDto>> getLinkUserByCondition(Map<?, ?> param) throws Exception {

        ResultData<List<UserDto>> resultData = new ResultData<List<UserDto>>();
        List<User> moList = userDao.getLinkUserByCondition(param);
        List<UserDto> dtoList = convertData(moList);
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(dtoList);

        return resultData;
    }

    @Override
    public ResultData<UserInfo> getWechatUserByWechatId(String wechatId) {
        ResultData<UserInfo> resultData = new ResultData<>();
        UserInfo userInfo = null;
        try
        {
            userInfo = this.userDao.getUserByWechatId(wechatId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("user", "UserAPIImpl", "getWechatUserByWechatId", "", null, "依据微信号，获取用户信息失败！", "", e);
        }

        if(null == userInfo){
            resultData.setFail();
        }
        resultData.setReturnData(userInfo);

        return resultData;
    }
    
    
    @Override
    public ResultData<UserInfo> getPmlsNewUserInfo(Integer userId, String selectedCityNo)
    {
        UserInfo userInfo = new UserInfo();
        // 根据用户Id获得用户信息
        ResultData<UserInfo> resultData =  this.getUserInfoById(userId,userId);
        if ("0".equals(resultData.getReturnCode())){
            return resultData;
        }else{
            userInfo = (UserInfo)resultData.getReturnData();
            //密码清空
            userInfo.setPassword("");
        }
        
        
        Map<String, Object> queryParam =new  HashMap<String, Object>();
        queryParam.put("systemCode", "PMLS");
        queryParam.put("userId", userId);
        queryParam.put("userCode", userInfo.getUserCode());
        //菜单
        try {
        	  List<Auth> authTmpList = authDao.getAuthListByParam(queryParam);
              if(authTmpList!=null && authTmpList.size()>0){
              	userInfo.setAuths(authTmpList);
              }else{
            	  resultData.setReturnMsg("用户未指定菜单权限");
            	  resultData.setReturnCode(ReturnCode.FAILURE);
                  return resultData;
               }
		} catch (Exception e) {
			// TODO: handle exception
			resultData.setReturnMsg("获取菜单数据失败。");
			resultData.setReturnCode(ReturnCode.FAILURE);
              logger.error("user", "LoginAPIImpl", "login", "获取菜单", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
              return resultData;
		}
      
       //设置cityNo/CityName
            List<City> ciList = null;
            try
            {
            	ciList= userCenterCityDao.queryCityList(queryParam);
            	
            	if(ciList!=null&&ciList.size()>0){
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
            	}else{
            		resultData.setReturnMsg("用户未指定对应中心，城市权限");
            		resultData.setReturnCode(ReturnCode.FAILURE);
                  return resultData;            		
            	}
            }
            catch (Exception e)
            {
            	resultData.setReturnMsg("获取数据失败。");
            	resultData.setReturnCode(ReturnCode.FAILURE);
                logger.error("user", "LoginAPIImpl", "login", "设置cityNo/CityName", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
            }
            if (null != ciList) {
            	boolean  selected=false;
            	for (City city : ciList) {
					if(selectedCityNo.equals(city.getCityNo())){
		                userInfo.setCityNo(city.getCityNo());
	                	userInfo.setCityName(city.getCityName());
	                	selected=true;
	                	break;
					}
				}
            	if(!selected){
                	City  selectCity= ciList.get(0);
            		 userInfo.setCityNo(selectCity.getCityNo());
	                	userInfo.setCityName(selectCity.getCityName());
            	}
            	
            }
            
            List<PmlsUserCenterCitySetting> centeres = null;
            try
            {
            	centeres= userCenterCityDao.queryCenterList(queryParam);
            	
            	if(ciList!=null&&ciList.size()>0){
            		userInfo.setCities(ciList);
            	}else{
            		resultData.setReturnMsg("用户未指定对应中心，城市权限");
            		resultData.setReturnCode(ReturnCode.FAILURE);
                  return resultData;            		
            	}
            }
            catch (Exception e)
            {
            	resultData.setReturnMsg("获取数据失败。");
            	resultData.setReturnCode(ReturnCode.FAILURE);
            
                logger.error("user", "LoginAPIImpl", "login", "设置cityNo/CityName", null, "", "登录校验，登录成功并获取登录用户信息失败！", e);
                return resultData; 
            }
            if (null != centeres) {
            	userInfo.setCenteres(centeres);
            }
            	
            resultData.setReturnCode(ReturnCode.SUCCESS);
            resultData.setReturnData(userInfo);
      
        return resultData;
    }
}
