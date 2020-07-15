package cn.com.eju.deal.user.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.Auth;

public interface AuthMapper extends IDao<Auth>
{
    int deleteByPrimaryKey(Integer authId) throws Exception;
    
    int insert(Auth record) throws Exception;
    
    Auth selectByPrimaryKey(Integer authId) throws Exception;
    
    int updateByPrimaryKey(Auth record) throws Exception;
    
    List<Auth> list(String systemCode) throws Exception;
    
    List<Auth> urlNotNulllist(String systemCode) throws Exception;
    
    List<Auth> getListByParentId(Integer parentId) throws Exception;
    
    List<Auth> getListByPostId(Map<String, Object> map) throws Exception;
    
    Auth getAllowAuthByHeader(Map<String, Object> map) throws Exception;
    
    Auth getAuthByUrlAndType(Map<String, Object> params) throws Exception;

    Auth getStoreAuditAuthByCity(Map<?, ?> queryPara) throws Exception;

    
    List<Auth> getAuthListByParam(Map<String, Object> map) throws Exception;
}