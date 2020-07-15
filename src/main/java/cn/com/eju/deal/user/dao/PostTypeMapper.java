package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.user.model.PostType;

/**
 * 岗位类型接口
 * @author len
 *
 */
public interface PostTypeMapper 
{
    
    int insert(PostType record) throws Exception;
    
    int insertSelective(PostType postType) throws Exception;
    
    PostType selectByPrimaryKey(Integer typeId) throws Exception;
    
    int updateByPrimaryKeySelective(PostType postType) throws Exception;
    
    List<PostType> queryList(Map<String, Object> map) throws Exception;
    
    List<PostType> selectTypeList(PostType postType) throws Exception;
    
    int delete(Integer typeId) throws Exception;
}