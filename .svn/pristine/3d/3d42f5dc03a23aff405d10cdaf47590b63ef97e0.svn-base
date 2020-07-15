package cn.com.eju.deal.user.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.user.api.ILoginAPI;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import java.util.Map;

/**
 * 服务层
 *
 * @author (li_xiaodong)
 * @date 2016年1月19日 下午6:05:44
 */

@RestController
@RequestMapping(value = "logins")
public class LoginController extends BaseController {
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private ILoginAPI loginAPI;

    /**
     * 登录
     *
     * @param params 条件
     * @return
     */
    @RequestMapping(value = "/{params}", method = RequestMethod.GET)
    public String login(@PathVariable String params) {
        logger.info("登录测试日志 登录测试日志 登录测试日志 登录测试日志  登录测试日志 登录测试日志");
        logger.error("登录测试日志 登录测试日志 登录测试日志 登录测试日志  登录测试日志 登录测试日志");

        Map<?, ?> queryParam = JsonUtil.parseToObject(params, Map.class);

        ResultData<UserInfo> resultData = new ResultData<UserInfo>();
        try
        {
            //resultData = loginAPI.login(queryParam);
        	
        	resultData = loginAPI.pmlslogin(queryParam);
        }
        catch (Exception e)
        {
            logger.error("User", "LoginController", "login", "", 0, "", "登录", e);
            resultData.setFail();
        }

        return resultData.toString();
    }
}
