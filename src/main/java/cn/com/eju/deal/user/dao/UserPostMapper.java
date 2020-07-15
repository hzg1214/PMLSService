package cn.com.eju.deal.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.user.model.UserPostMapping;

/**
 * 用户岗位关联信息接口
 * @author yujun
 *
 */
public interface UserPostMapper
{
    
    int insertUserPost(UserPostMapping userPostMapping) throws Exception;
    
    List<UserPostMapping> queryListById(@Param("userId") Integer userId) throws Exception;
    
    void deleteById(int postId) throws Exception;
    
    void deleteByUserId(Integer userId) throws Exception;
    
    void deleteByUserIdAndPostId(@Param("userId") Integer userId) throws Exception;
    
    UserPostMapping selectApUserByPostId(Integer postId) throws Exception;
    
    void update(Integer postId) throws Exception;
    
    void updateByUserIdAndPostId(UserPostMapping userPostMapping) throws Exception;
}