package cn.com.eju.pmls.basicInformation.personnelPermissions.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.pmls.basicInformation.personnelPermissions.dto.UserCenterCityDto;
import cn.com.eju.pmls.basicInformation.personnelPermissions.model.UserCenterCity;

public interface PersonnelPermissionsMapper {
    //新增
    int addPersonnelPermissions(UserCenterCity dto);
    //删除
    int deletePersonnelPermissions(UserCenterCityDto dto);
    //修改
    int updatePersonnelPermissions(UserCenterCityDto dto);
    //获取
    UserCenterCityDto getPersonnelPermissionsInfo(Map<String, Object> queryParam);
    //获取人员权限列表
    List<UserCenterCityDto> queryPersonnelPermissionsList(Map<String, Object> queryParam);
    List<UserCenterCity> getByUserCode(Map<?, ?> param);
    //根据centerId获取cityId
    Map<String,Object> getCityIdByCenterId(Integer CenterId);
    //根据城市获取中心
    List<Map<String,Object>> getCenterGroupByCityNo(Map<String, Object> map);
    //获取城市列表
    List<Map<String,Object>> getCityList(Map<String, Object> map);
}
