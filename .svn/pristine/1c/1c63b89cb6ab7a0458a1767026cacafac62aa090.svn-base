package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.RoleAuthMapping;

public interface RoleAuthMapper extends IDao<RoleAuthMapping>
{
    int deleteByPrimaryKey(Integer id) throws Exception;
    
    int insert(RoleAuthMapping record) throws Exception;
    
    int insertSelective(RoleAuthMapping record) throws Exception;
    
    RoleAuthMapping selectByPrimaryKey(Integer id) throws Exception;
    
    int updateByPrimaryKeySelective(RoleAuthMapping record) throws Exception;
    
    int updateByPrimaryKey(RoleAuthMapping record) throws Exception;
    
    List<RoleAuthMapping> quertAll(Integer roleId) throws Exception;
    
    void deleteByRoleId(Integer roleId) throws Exception;
    
    int updateByRoleIdAndAuthId(RoleAuthMapping roleAuthMapping) throws Exception;
    
    List<RoleAuthMapping> queryList(Map<?, ?> map);
}