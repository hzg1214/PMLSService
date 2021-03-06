package cn.com.eju.pmls.channelBusiness.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.channelBusiness.model.BusinessManagerDto;
import cn.com.eju.pmls.channelBusiness.service.BusinessManagerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("businessManagerController")
public class BusinessManagerController extends BaseController {
    @Resource
    private BusinessManagerService businessManagerService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    //获取商户列表
    @RequestMapping(value = "/getBusinessManagerList/{param}", method = RequestMethod.GET)
    public String getBusinessManagerList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = businessManagerService.getBusinessManagerList(queryParam);
        }catch (Exception e)
        {
            logger.error("getBusinessManagerList 获取商户列表发生异常param："+ param,e);
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "getBusinessManagerList", "", null, "", "获取商户列表", e);
        }
        return resultData.toString();
    }
    //获取商户信息
    @RequestMapping(value = "/getBusinessInfo", method = RequestMethod.POST)
    public String getBusinessInfo(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.getBusinessInfo(dto);
        }catch (Exception e)
        {
            logger.error("getBusinessInfo 获取商户信息 json" + json, e);
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "getBusinessInfo", "", null, "", "获取商户信息", e);
        }
        return resultData.toString();
    }
    //获取商户信息
    @RequestMapping(value = "/getBusinessInfo2", method = RequestMethod.POST)
    public String getBusinessInfo2(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.getBusinessInfo2(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "getBusinessInfo2", "", null, "", "获取商户信息", e);
        }
        return resultData.toString();
    }
    //新增商户信息
    @RequestMapping(value = "/addBusiness", method = RequestMethod.POST)
    public String addBusiness(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.addBusiness(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "addBusiness", "", null, "", "新增商户信息", e);
        }
        return resultData.toString();
    }
    //修改商户信息
    @RequestMapping(value = "/updateBusiness", method = RequestMethod.POST)
    public String updateBusiness(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.updateBusiness(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "updateBusiness", "", null, "", "修改商户信息", e);
        }
        return resultData.toString();
    }
    //删除商户信息
    @RequestMapping(value = "/deleteBusiness", method = RequestMethod.POST)
    public String deleteBusiness(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.deleteBusiness(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "deleteBusiness", "", null, "", "删除商户信息", e);
        }
        return resultData.toString();
    }

    //新增联系人信息
    @RequestMapping(value = "/addContacts", method = RequestMethod.POST)
    public String addContacts(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.addContacts(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "addContacts", "", null, "", "新增联系人信息", e);
        }
        return resultData.toString();
    }
    //修改联系人信息
    @RequestMapping(value = "/updateContacts", method = RequestMethod.POST)
    public String updateContacts(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.updateContacts(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "updateContacts", "", null, "", "修改联系人信息", e);
        }
        return resultData.toString();
    }

    //获取联系人列表
    @RequestMapping(value = "/getContactsList/{param}", method = RequestMethod.GET)
    public String getContactsList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = businessManagerService.getContactsList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "getContactsList", "", null, "", "获取联系人列表", e);
        }
        return resultData.toString();
    }
    //获取联系人信息
    @RequestMapping(value = "/getContactsInfo", method = RequestMethod.POST)
    public String getContactsInfo(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.getContactsInfo(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "getContactsInfo", "", null, "", "获取联系人信息", e);
        }
        return resultData.toString();
    }
    //获取公司日志列表
    @RequestMapping(value = "/getOperationLogList/{param}", method = RequestMethod.GET)
    public String getOperationLogList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = businessManagerService.getOperationLogList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "getOperationLogList", "", null, "", "获取公司日志列表", e);
        }
        return resultData.toString();
    }

    //新增商户城市关系表
    @RequestMapping(value = "/addCompanyReleaseCity", method = RequestMethod.POST)
    public String addCompanyReleaseCity(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.addCompanyReleaseCity(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "addCompanyReleaseCity", "", null, "", "新增商户城市关系表", e);
        }
        return resultData.toString();
    }



    //获取维护人列表
    @RequestMapping(value = "/getMaintainerList/{param}", method = RequestMethod.GET)
    public String getMaintainerList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = businessManagerService.getMaintainerList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("MaintainerInfoDto", "BusinessManagerController", "getMaintainerList", "", null, "", "获取维护人列表", e);
        }
        return resultData.toString();
    }

    //获取公司维护人记录列表
    @RequestMapping(value = "/getCompanyMaintainerList/{param}", method = RequestMethod.GET)
    public String getCompanyMaintainerList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = businessManagerService.getCompanyMaintainerList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("MaintainerInfoDto", "BusinessManagerController", "getCompanyMaintainerList", "", null, "", "获取公司维护人记录列表", e);
        }
        return resultData.toString();
    }

    //修改维护人信息
    @RequestMapping(value = "/updateMaintainer", method = RequestMethod.POST)
    public String updateMaintainer(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.updateMaintainer(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "updateMaintainer", "", null, "", "修改维护人信息", e);
        }
        return resultData.toString();
    }

    // 调整为是否借佣渠道
    @RequestMapping(value = "/adjustToProcuration", method = RequestMethod.POST)
    public String adjustToProcuration(@RequestBody String json) {
        ResultData resultData = new ResultData();
        try {
            BusinessManagerDto dto = JsonUtil.parseToObject(json, BusinessManagerDto.class);
            resultData = businessManagerService.adjustToProcuration(dto);
        } catch (Exception e) {
            logger.error("adjustToProcuration:调整为是否借佣渠道json:" + json, e);
            resultData.setFail();
            logger.error("BusinessManager", "BusinessManagerController", "adjustToProcuration",
                    "", null, "", "调整为是否借佣渠道", e);
        }
        return resultData.toString();
    }
}
