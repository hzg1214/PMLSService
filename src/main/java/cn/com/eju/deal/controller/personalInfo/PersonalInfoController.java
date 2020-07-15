package cn.com.eju.deal.controller.personalInfo;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.personalInfo.PersonalInfoDto;
import cn.com.eju.deal.service.personalInfo.PersonalInfoService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by tanlang on 2018/11/02.
 */
@RestController
@RequestMapping(value = "personalInfoController")
public class PersonalInfoController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "personalInfoService")
    private PersonalInfoService personalInfoService;

    @RequestMapping(value = "/getPersonList", method = RequestMethod.POST)
    public String getPersonList(@RequestBody String jsonDto) {
        UserInfo dto = JsonUtil.parseToObject(jsonDto, UserInfo.class);
        ResultData<List<PersonalInfoDto>> resultData = new ResultData<>();
        try {
            resultData = personalInfoService.getPersonList(dto);
            resultData.setSuccess();
        } catch (Exception e) {
            resultData.setFail("获取个人信息列表失败");
            logger.error("PersonalInfoController", "PersonalInfoController", "getPersonalInfo", "", null, "", "获取个人信息列表", e);
        }

        return resultData.toString();
    }
    @RequestMapping(value = "/getPersonalInfo", method = RequestMethod.POST)
    public String getPersonalInfo(@RequestBody String jsonDto) {
        UserInfo dto = JsonUtil.parseToObject(jsonDto, UserInfo.class);
        ResultData<PersonalInfoDto> resultData = new ResultData<>();
        try {
            resultData = personalInfoService.getPersonalInfo(dto);
            resultData.setSuccess();
        } catch (Exception e) {
            resultData.setFail("获取个人信息列表失败");
            logger.error("PersonalInfoController", "PersonalInfoController", "getPersonalInfo", "", null, "", "获取个人信息列表", e);
        }

        return resultData.toString();
    }

}
