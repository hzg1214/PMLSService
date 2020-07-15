package cn.com.eju.deal.mapper.personalInfo;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.dto.personalInfo.PersonalInfoDto;

import java.util.List;


public interface PersonalInfoMapper {

    List<PersonalInfoDto> getPersonList(UserInfo dto);
    PersonalInfoDto getPersonalInfo(UserInfo dto);
}
