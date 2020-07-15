package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.PostRoleMapping;

/**
 * 岗位-角色关联信息接口
 * @author yujun
 *
 */
public interface PostRoleMapper extends IDao<PostRoleMapping>
{
    
    int insertPostRole(PostRoleMapping postRoleMapping) throws Exception;
    
    int deleteByPostId(Integer postId) throws Exception;
    
    PostRoleMapping selectByPostRole(PostRoleMapping postRoleMapping) throws Exception;
    
    int updateByPostRole(PostRoleMapping postRoleMapping) throws Exception;
    
    List<PostRoleMapping> queryMapList(Map<String, Object> map) throws Exception;
    
    List<PostRoleMapping> queryList(PostRoleMapping postRoleMapping) throws Exception;
    
    List<PostRoleMapping> queryByPostIdList(Integer postId) throws Exception;
}