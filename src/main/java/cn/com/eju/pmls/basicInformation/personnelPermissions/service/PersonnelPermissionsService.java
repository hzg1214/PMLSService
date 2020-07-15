package cn.com.eju.pmls.basicInformation.personnelPermissions.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import cn.com.eju.pmls.basicInformation.personnelPermissions.dao.PersonnelPermissionsMapper;
import cn.com.eju.pmls.basicInformation.personnelPermissions.dto.UserCenterCityDto;
import cn.com.eju.pmls.basicInformation.personnelPermissions.model.UserCenterCity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("personnelPermissionsService")
public class PersonnelPermissionsService extends BaseService {

    @Resource
    private PersonnelPermissionsMapper personnelPermissionsMapper;

    @Resource
    private UserMapper userMapper;

    // 获取人员信息列表
    public ResultData queryPersonnelPermissionsList(Map<String, Object> queryParam) {
        ResultData<List<UserCenterCityDto>> resultData = new ResultData<>();
        List<UserCenterCityDto> list = personnelPermissionsMapper.queryPersonnelPermissionsList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    //根据城市获取中心
    public ResultData<List<Map<String, Object>>> getCenterGroupByCityNo(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();
        List<Map<String, Object>> list = personnelPermissionsMapper.getCenterGroupByCityNo(map);
        resultData.setReturnData(list);
        return resultData;
    }

    //获取城市列表
    public ResultData<List<Map<String, Object>>> getCityList(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();
        List<Map<String, Object>> list = personnelPermissionsMapper.getCityList(map);
        resultData.setReturnData(list);
        return resultData;
    }

    //获取人员信息
    public ResultData getUserInfo(Map<String, Object> queryParam) {
        ResultData<UserCenterCityDto> resultData = new ResultData<>();
        UserCenterCityDto userCenterCityDto = personnelPermissionsMapper.getPersonnelPermissionsInfo(queryParam);
        resultData.setReturnData(userCenterCityDto);
        return resultData;
    }

    //新增人员信息
    public ResultData addUser(UserCenterCityDto dto) throws Exception {
        ResultData resultData = new ResultData();

        UserCenterCity userCenterCity = getUserCenterCity(dto);
        List<String> centerIds = dto.getCenterIds();

        //保存时须与UMS进行校验
        if (!this.checkSaveWithUMS(userCenterCity)) {
            resultData.setFail("员工工号和员工姓名不一致，请重新填写！");
            resultData.setReturnCode("201");
            return resultData;
        }
        if (centerIds != null && centerIds.size() > 0) {
            for (String centerId : centerIds) {
                userCenterCity.setCenterId(Integer.parseInt(centerId));
                Map<String, Object> map = personnelPermissionsMapper.getCityIdByCenterId(Integer.parseInt(centerId));
                if (map != null) {
                    Integer cityId = (Integer) map.get("cityId");
                    userCenterCity.setCityId(cityId);
                    // 员工信息不允许重复提交, flag：1-新增，2-修改
                    if (this.checkSave(userCenterCity, 1)) {
                        int count = personnelPermissionsMapper.addPersonnelPermissions(userCenterCity);
                    }
                }
            }
        }

        return resultData;
    }

    //封装entity
    private UserCenterCity getUserCenterCity(UserCenterCityDto dto) {
        UserCenterCity userCenterCity = new UserCenterCity();
        userCenterCity.setUserCode(dto.getUserCode());
        userCenterCity.setUserName(dto.getUserName());
        userCenterCity.setCityNo(dto.getCityNo());
        userCenterCity.setCityId(dto.getCityId());
        userCenterCity.setUserIdCreate(dto.getUserIdCreate());
        //userCenterCity.setIsPmlsCenter(dto.getIsPmlsCenter());
        return userCenterCity;
    }

    //人员工号/名称是否一致
    private boolean checkSaveWithUMS(UserCenterCity userCenterCity) {
        // 取得用户信息
        User user = userMapper.getUserByCode(userCenterCity.getUserCode());

        // 如果没有取到用户，或者用户的名称不一致
        if (user == null || user.getUserName() == null || !user.getUserName().equals(userCenterCity.getUserName())) {
            return false;
        }
        return true;
    }

    //是否重复提交
    private boolean checkSave(UserCenterCity userCenterCity, int flag) {
        Map<String, Object> param = new HashMap<>();
        param.put("userCode", userCenterCity.getUserCode());
        param.put("cityNo", userCenterCity.getCityNo());
        //根据工号和城市编号查询该员工已有的中心
        List<UserCenterCity> userCenterCitys = personnelPermissionsMapper.getByUserCode(param);
        if (userCenterCitys != null && userCenterCitys.size() > 0) {
            for (UserCenterCity userCenterCityInfo : userCenterCitys) {
                if (flag == 1) {
                    if (userCenterCityInfo != null && userCenterCityInfo.getUserCode().equals(userCenterCity.getUserCode())
                            && userCenterCityInfo.getCenterId().equals(userCenterCity.getCenterId())
                            && userCenterCityInfo.getCityNo().equals(userCenterCity.getCityNo())) {
                        return false;
                    }
                } else if (flag == 2) {
                    if (userCenterCityInfo != null && userCenterCityInfo.getUserCode().equals(userCenterCity.getUserCode())
                            && userCenterCityInfo.getCenterId().equals(userCenterCity.getCenterId())
                            && userCenterCityInfo.getCityNo().equals(userCenterCity.getCityNo())) {//重复
                        return false;
                    }
                }
            }
        }

        return true;
    }

    //修改人员信息
    @Transactional(rollbackFor = Exception.class)
    public ResultData updateUser(UserCenterCityDto dto) throws Exception {
        ResultData resultData = new ResultData();
        Map<String, Object> map = personnelPermissionsMapper.getCityIdByCenterId(dto.getCenterId());
        Integer cityId = (Integer) map.get("cityId");
        dto.setCityId(cityId);
        UserCenterCity userCenterCity = getUserCenterCity(dto);
        userCenterCity.setCenterId(dto.getCenterId());
        //userCenterCity.setIsPmlsCenter(dto.getIsPmlsCenter());
        userCenterCity.setId(dto.getId());
        if (!this.checkSave(userCenterCity, 2)) {
            resultData.setFail("该员工信息已维护，请勿重复提交！");
            resultData.setReturnCode("202");
            return resultData;
        }
        int count = personnelPermissionsMapper.updatePersonnelPermissions(dto);
        if (count > 0) {
            resultData.setSuccess("修改成功");
        } else {
            resultData.setFail("修改失败");
        }
        return resultData;
    }

    //删除人员信息
    @Transactional(rollbackFor = Exception.class)
    public ResultData deleteUser(UserCenterCityDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int count = personnelPermissionsMapper.deletePersonnelPermissions(dto);
        if (count > 0) {
            resultData.setSuccess("删除成功");
        } else {
            resultData.setFail("删除失败");
        }
        return resultData;
    }
}
