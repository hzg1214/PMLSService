package cn.com.eju.deal.api.user.controller;

import cn.com.eju.deal.api.user.dto.APPUserDto;
import cn.com.eju.deal.api.user.service.APIUserService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.apache.http.Consts;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2018/3/14.
 */
@RestController
@RequestMapping(value = "APIUser")
public class APIUserController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "apiUserService")
    private APIUserService apiUserService;

    /**
     *根据房友APP传入的经服人员工号或手机号码
     * @param
     * @return
     */
    @RequestMapping(value = "/app/getUser", method = RequestMethod.POST)
    public String getUserForFYAPP(@RequestBody String jsonStr){
        //构建返回
        ResultData<List<APPUserDto>> resultData = new ResultData<>();
        try
        {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            Map<?, ?> queryParam = JsonUtil.parseToObject(jsonData, Map.class);
            resultData = apiUserService.getUserForFYAPP(queryParam);
        }
        catch (Exception e)
        {
            logger.error("APIUser", "APIUserController", "getUserForFYAPP", "", null, "", "查询经服人员信息失败 ", e);
            resultData.setFail("查询经服人员信息失败");
        }
        return resultData.toString();
    }
}
