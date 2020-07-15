package cn.com.eju.deal.user.dao;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.api.user.dto.APPUserDto;
import cn.com.eju.deal.user.model.Auth;
import cn.com.eju.deal.user.model.ExchangeCenter;
import cn.com.eju.deal.user.model.Role;
import cn.com.eju.deal.user.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper
{
    /** 
    * 获取所有用户 
    * @return
    */
    List<User> getAllUser(Map<?, ?> param) throws Exception;
    
    int deleteByPrimaryKey(User user) throws Exception;
    
    int insert(User record) throws Exception;
    
    int insertSelective(User record) throws Exception;
    
    User selectByPrimaryKey(Integer userId) throws Exception;
    
    UserInfo getUserByWechatId(String wechatId) throws Exception;
    
    int updateByPrimaryKeySelective(User record) throws Exception;
    
    int updateByPrimaryKey(User record) throws Exception;
    
    List<UserInfo> queryUserList() throws Exception;
    
    List<UserInfo> listUser() throws Exception;
    
    UserInfo queryByUserId(Integer userId) throws Exception;
    
    List<UserInfo> queryList(Map<String, Object> map) throws Exception;
    
    List<Role> queryRoleByUserId(Integer userId) throws Exception;
    
    List<Auth> queryAuthByUserId(Integer userId) throws Exception;
    
    List<User> selectByGroupId(Integer groupId) throws Exception;
    
    //根据用户名获取用户信息
    UserInfo selectByLoginName(String loginName) throws Exception;
    
    List<UserInfo> queryUserListByAuthId(Map<String, Object> map) throws Exception;
    
    List<UserInfo> getUserInfoListByGroupAndPost(Map<String, Object> map) throws Exception;
    
    int queryByUserCode(String userCode) throws Exception;
    
    int queryBycredentialsNum(String credentialsNum) throws Exception;
    
    List<User> getSubordinatesUser(Map<String, Object> map) throws Exception;
    
    List<User> getAllSubordinatesUser(Map<String, Object> map) throws Exception;
    
    List<User> selectApUserListByPostId(Integer postId) throws Exception;
    
    List<User> selectUserListByPostId(Integer postId) throws Exception;
    
    User queryByUserCodeShiro(String userCode) throws Exception;
    
    /**
     * 根据userId得到cityNo
     *
     * @param userId
     * @return
     */
    UserInfo getCityNoByUserId(Integer userId) throws Exception;
    /**
     * 根据userId得到中心集合
     *
     * @param userId
     * @return
     */
    List<ExchangeCenter> getCenterListByUserId(Integer userId) throws Exception;
    
    /** 
    * @Title: getHouseLinkUserList 
    * @Description: 查询新房联动的案场负责人
    * @param cityNo
    * @return     
    */
    List<User> getHouseLinkUserList(String cityNo) throws Exception;
    List<User> getHouseLinkUserList2(Map<?, ?> param) throws Exception;
    
    /**
     * 根据postId得到cityNo
     *
     * @param postId
     * @return
     */
    UserInfo getCityNoByPostId(Integer postId) throws Exception;

    /**
     * 根据selectOrgId查询所属大区
     * @param param
     */
    UserInfo getGNBySelectOrgId(Map<String,Object> param) throws Exception;

    /**
     * 根据用户Code查用户
     * @param userCode
     * @return
     */
    public User getUserByCode(@Param(value="userCode") String userCode);
    
    /**
     * 查询拓展人员
     * @param param
     * @return
     */
    List<User> getExpanderUser(Map<?, ?> param) throws Exception;
    /**
     * 查询维护人员
     * @param param
     * @return
     */
    List<User> getWeiHuUser(Map<?, ?> param) throws Exception;

    /**
     * 查询联动业绩人员
     * @param param
     * @return
     */
    List<User> getLinkUser(Map<?, ?> param) throws Exception;
    /**
     * 查询维护人员
     * @param param
     * @return
     */
    List<User> getMaintenanceUser(Map<?, ?> param) throws Exception;
    
    /**
     * 根据用户UserId查用户
     * @param userId
     * @return
     */
    public User getUserByUserId(@Param(value="userId") Integer userId);

    /**
     * 根据房友APP传入的经服人员工号或手机号码
     * @param param
     * @return
     * @throws Exception
     */
    List<APPUserDto> getUserForFYAPP(Map<?, ?> param) throws Exception;
    
    
    /**
     * 根据code获取一个User
     * */
    User getUserByUserCode(String userCode);
    List<User> getLinkUserByCondition(Map<?, ?> param) throws Exception;
    
//    项目负责人
    List<User>  getProjectLeaderList(Map<?, ?> param) throws Exception;
}