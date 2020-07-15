package cn.com.eju.deal.service.personalInfo;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.personalInfo.PersonalInfoDto;
import cn.com.eju.deal.mapper.personalInfo.PersonalInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


@Service("personalInfoService")
public class PersonalInfoService {

    @Resource
    private PersonalInfoMapper personalInfoMapper;

    public ResultData<List<PersonalInfoDto>> getPersonList(UserInfo dto) {
        ResultData<List<PersonalInfoDto>> resultData = new ResultData<>();
        List<PersonalInfoDto> result = personalInfoMapper.getPersonList(dto);
        resultData.setReturnData(result);

        return resultData;
    }
    public ResultData<PersonalInfoDto> getPersonalInfo(UserInfo dto) {
        ResultData<PersonalInfoDto> resultData = new ResultData<>();
        PersonalInfoDto result = personalInfoMapper.getPersonalInfo(dto);
        resultData.setReturnData(result);

        return resultData;
    }
}
