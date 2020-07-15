package cn.com.eju.deal.permission.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.permission.model.PermissionDto;

import java.util.List;
import java.util.Map;

public interface PermissionMapper extends IDao<PermissionDto> {
    List<PermissionDto> getPermissionList(Map<?, ?> param) throws Exception;
    void deletePermission(Map<?, ?> param) throws Exception;
    List<PermissionDto> searchList(Map<?, ?> param) throws Exception;
    void savePermission(Map<?, ?> param) throws Exception;
    List<PermissionDto> searchNewList(Map<?, ?> param) throws Exception;

}