package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.user.model.Post;

/**
 * 岗位信息接口
 * @author yujun
 *
 */
public interface PostMapper 
{
    /**
     * 物理删除（目前没使用）
     * @param postId
     * @return
     */
    int deleteByPrimaryKey(Integer postId) throws Exception;
    
    /**
     * 逻辑删除，更改状态为Y
     * @param postId
     * @return
     */
    int deleteUpDate(Integer postId) throws Exception;
    
    int insert(Post post) throws Exception;
    
    int insertSelective(Post post) throws Exception;
    
    Post selectByPrimaryKey(Integer postId) throws Exception;
    
    int updateByPrimaryKey(Post post) throws Exception;
    
    int updateByPrimaryKeySelective(Post post) throws Exception;
    
    List<Post> selectByGroupId(Integer groupId) throws Exception;
    
    List<Post> queryList(Map<String, Object> map) throws Exception;
    
    List<Post> queryPostList(Post post) throws Exception;
    
    List<Post> selectApPostByGroupId(Integer groupId) throws Exception;
    
    List<Post> list() throws Exception;
    
    List<Post> queryPostByUserId(Map<String, Object> map) throws Exception;
    
    List<Post> queryAllPostByUserId(Integer userId) throws Exception;
    
    Post queryDefaultPostByUserId(Integer userId) throws Exception;
    
    Post selectMainPostByUserId(Integer userId) throws Exception;
    
    /** 
    * 查询拓展人员所属岗位信息
    * @param userId 用户Id
    * @return
    * @throws Exception
    */
    List<Post> getExpandPostByUserId(Integer userId) throws Exception;
    
    
}