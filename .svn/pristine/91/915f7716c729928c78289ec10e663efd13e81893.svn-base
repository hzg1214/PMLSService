package cn.com.eju.deal.user.api;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.user.UserDto;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.User;

/**
 * 用户接口
 *
 * @author 徐龙
 * @date 2015年10月12日 下午3:46:40
 */
public interface IUserAPI
{
    
    
    /** 
    * 获取所有用户 
    * @return
    */
    public ResultData<List<UserDto>> getAllUser(Map<?, ?> param) throws Exception;
    
    /**
     * 根据用户Id获取用户接口
     *
     * @param userId        用户Id
     * @return 用户对象
     */
    public ResultData<User> getUserById(int userId);
    
    /**
     * 根据用户Id获取用户接口
     *
     * @param operateUserId 操作人Id
     * @param userId        用户Id
     * @return 用户对象
     */
    public ResultData<UserInfo> getUserInfoById(int operateUserId, int userId);
    
    /**
     * 根据微信id 获取用户
     *
     * @param wechatId 微信编号
     * @return 用户信息
     */
    public ResultData<UserInfo> getUserByWechatId(String wechatId);
    
    /**
     * 根据用户登录名获取用户接口
     *
     * @param operateUserId 操作人Id
     * @param loginName     用户登录名
     * @return 用户对象
     */
    public ResultData<UserInfo> getUserByLoginName(String loginName);
    
    /**
     * 根据用户的组id 和岗位类型获取人员列表
     *
     * @param operateUserId 操作人Id
     * @param groupId       组id
     * @param postType      岗位类型编码
     * @return
     */
    public ResultData<List<UserInfo>> getUserInfoListByGroupAndPost(int operateUserId, int groupId, String postType);
    
    /**
     * 根据组Id获取用户接口
     *
     * @param operateUserId 操作人Id
     * @param groupId       组Id
     * @return 用户对象ArrayList
     */
    public ResultData<List<User>> getUserByGroupId(int operateUserId, int groupId);
    
    /**
     * 更新用户信息
     *
     * @param operateUserId 操作人Id
     * @param user          用户对象
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<Integer> updateUser(int operateUserId, User user);
    
    /**
     * 新增用户信息
     *
     * @param operateUserId 操作人Id
     * @param user          用户对象
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<Integer> addUser(int operateUserId, User user);
    
    /**
     * 查找某个用户的领导
     *
     * @param operateUserId 操作人Id
     * @param userId        用户对象
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<User>> selectLeaders(Integer operateUserId, Integer userId, Boolean oneFlg);
    
    /**
     * 查找某个用户成员
     *
     * @param operateUserId 操作人Id
     * @param userId        用户对象
     * @param allFlg        true：所有的下属  false：同组的下属
     * @return 1表示成功，-1表示失败，0表示出错
     */
    public ResultData<List<User>> selectSubordinates(Integer operateUserId, Integer userId, Boolean allFlg);
    
    /**
     * shiro权限查询
     *
     * @param userCode
     * @return
     */
    public ResultData<UserDto> queryShiro(String userCode);
    
    /**
     * 根据用户ID和岗位ID查询用户ID集合
     *
     * @param userId     用户ID
     * @param postId     岗位ID
     * @param type       查询类型【1-查询同组及子组下属，2-查询同组下属，3-查询直属上级，4-查询所有上级】
     * @param isSelfUser 是否包含当前用户【userId】 ture-是 false-否
     * @return 1表示成功，-1表示失败，0表示出错  返回用户Id集合
     */
    public ResultData<List<Integer>> selectUserIdsByParam(Integer userId, Integer postId, Integer type,
                                                          boolean isSelfUser);
    
    /**
     * 根据用户ID和岗位ID查询用户数据
     *
     * @param userId     用户ID
     * @param postId     岗位ID
     * @param type       查询类型【1-查询同组及子组下属，2-查询同组下属，3-查询直属上级，4-查询所有上级】
     * @param isSelfUser 是否包含当前用户【userId】 ture-是 false-否
     * @return 1表示成功，-1表示失败，0表示出错  返回用户集合
     */
    public ResultData<List<User>> selectUserListByParam(Integer userId, Integer postId, Integer type, boolean isSelfUser);
    /**
     * 根据登录用户组，获取用户组信息
     * @throws Exception 
     */
    public ResultData<List<Group>> selectAllChildrenByGroupId(Integer groupId) throws Exception;

    ResultData<List<Group>> getGroupByTypeId(Integer typeId);
    
    /**
     * 切换岗位 获得新的userInfo
     */
    public ResultData<UserInfo> getNewUserInfo(Integer userId, Integer selectedPostId);
    
    /**
     * 根据用户Id获取用户接口
     *
     * @param operateUserId 操作人Id
     * @param userId        用户Id
     * @return 用户对象
     */
    public ResultData<UserInfo> getUserInfoByIdPost(Integer operateUserId, Integer userId, Integer selectedPostId);
    
    /**
     * 根据selectOrgId查询所属大区
     * @Title: getGNBySelectOrgId
     * @param selectOrgId
     * @throws Exception 
     */
    public ResultData<UserInfo> getGNBySelectOrgId(String selectOrgId) throws Exception;
    
    /**
     * 根据用户Code查用户
     */
    public ResultData<User> getUserByCode(String userCode) throws Exception;
    
    /** 
     * 查询拓展人员
     */
     public ResultData<List<UserDto>> getExpanderUser(Map<?, ?> param) throws Exception;
     
     /** 
      * 查询拓展人员[带中心]
      */
      public ResultData<List<UserDto>> getStoreExpanderUser(Map<?, ?> param) throws Exception;

    /**
     * 查询联动业绩人员
     */
    public ResultData<List<UserDto>> getLinkUser(Map<?, ?> param) throws Exception;
    /**
     * 查询维护人人员
     */
    public ResultData<List<UserDto>> getMaintenanceUser(Map<?, ?> param) throws Exception;
    
    /**
     * 根据code获取一个User
     * */
    User getUserByUserCode(String userCode);
    /**
     * 模糊查询联动业绩人员
     */
    public ResultData<List<UserDto>> getLinkUserByCondition(Map<?, ?> param) throws Exception;

    ResultData<UserInfo> getWechatUserByWechatId(String wechatId);
    
    /**
     * 切换岗位 获得新的userInfo
     */
    public ResultData<UserInfo> getPmlsNewUserInfo(Integer userId, String selectedCityNo);
    
}
