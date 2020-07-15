package cn.com.eju.deal.staffMaintain.dao;

import cn.com.eju.deal.Report.dto.UsrOrgHisDto;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.dto.staffMaintain.YFCenterUserDto;
import cn.com.eju.deal.staffMaintain.model.YFCenterUser;

import java.util.List;
import java.util.Map;

public interface YFCenterUserMapper {

    int insert(YFCenterUser record);

    int update(YFCenterUser record);

    int delete(Integer id);

    YFCenterUserDto getById(Integer id);

    YFCenterUserDto getByUserCode(Map<?, ?> param);

    List<YFCenterUserDto> queryList(Map<?, ?> param);

    List<City> getAreaCity(Map<?, ?> param);

    List<CenterDto> getCenterGroup(Map<?, ?> param);

    List<Map<?, ?> > getCenterAuth(Map<?, ?> param);

    Integer queryYfCenterUser(String userCode);
}