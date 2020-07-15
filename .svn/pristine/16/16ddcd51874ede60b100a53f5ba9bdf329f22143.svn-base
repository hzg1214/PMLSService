package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.Auth;
import cn.com.eju.deal.user.model.Role;

public interface RoleMapper 
{
    int deleteByPrimaryKey(Integer roleId) throws Exception;
    
    int insert(Role record) throws Exception;
    
    int insertSelective(Role record) throws Exception;
    
    Role selectByPrimaryKey(Integer roleId) throws Exception;
    
    int updateByPrimaryKeySelective(Role record) throws Exception;
    
    int updateByPrimaryKey(Role record) throws Exception;
    
    List<Role> selectByUserId(Integer userId) throws Exception;
    
    List<Role> queryList(Map<String, Object> map) throws Exception;
    
    List<Auth> queryAuthByRoleId(Map<String, Object> map) throws Exception;
    
    int batchEdit(@Param(value = "role") Role obj) throws Exception;
    
    int delete(Role role) throws Exception;
    
    List<Role> list() throws Exception;
}