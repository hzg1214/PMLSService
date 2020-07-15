package cn.com.eju.deal.fangyou.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.fangyou.model.FYCompany;
import cn.com.eju.deal.fangyou.model.FangyouInfoDto;
import cn.com.eju.deal.fangyou.service.FangyouService;

/**
 * Created by Sky on 2016/4/4.
 * 房友接口Controller
 */
@RestController
@RequestMapping(value = "fangyouApi")
public class FangyouController extends BaseService {
    
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "fangyouService")
    private FangyouService fangyouService;

    /**
     * 获取员工信息
     *
     * @return 员工信息列表
     */
    @RequestMapping(value = "/{companyId}/employee", method = RequestMethod.GET)
    public String getEmployee(@PathVariable Integer companyId) {
        String getEmployeeBak = new String();
        try
        {
            getEmployeeBak = fangyouService.getEmployee(companyId);
        }
        catch (Exception e)
        {
            logger.error("fangyou", "FangyouController", "getEmployee", "", null, "", "获取员工信息", e);
        }
        return getEmployeeBak;
    }


    /**
     * 修改密码
     *
     * @return 修改密码
     */
    @RequestMapping(value = "/{companyId}/password", method = RequestMethod.PUT)
    public String changePassword(@PathVariable String companyId, @RequestBody String password) {

        FangyouInfoDto fangyouInfoDto = JsonUtil.parseToObject(password, FangyouInfoDto.class);
        String changePasswordBak = new String();
        try
        {
            changePasswordBak = fangyouService.changePassword(fangyouInfoDto.getAdminPassword(), companyId);
        }
        catch (Exception e)
        {
            logger.error("fangyou", "FangyouController", "changePassword", "", null, "", "修改密码", e);
        }
        return changePasswordBak;
    }

    /**
     * 开通房友账号
     *
     * @return 开通的结果
     */
    @RequestMapping(value = "/company/", method = RequestMethod.POST)
    public String CreateFangyou(@RequestBody String fyCompanyJson) {

        FYCompany fyCompany = JsonUtil.parseToObject(fyCompanyJson, FYCompany.class);
        String createFangyouBak = new String();
        try
        {
            createFangyouBak = fangyouService.createFangyou(fyCompany);
        }
        catch (Exception e)
        {
            logger.error("houseLinkage", "EstateController", "CreateFangyou", "", null, "", "开通房友账号", e);
        }
        return createFangyouBak;
    }


}
