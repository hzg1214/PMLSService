package cn.com.eju.pmls.developer.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.developer.dto.DeveloperDto;
import cn.com.eju.pmls.developer.service.DeveloperService;

@RestController
@RequestMapping("developer")
public class DeveloperController extends BaseController {
    @Resource
    private DeveloperService developerService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    //获取开发商列表
    @RequestMapping(value = "/getDeveloperList/{param}", method = RequestMethod.GET)
    public String getDeveloperList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = developerService.getDeveloperList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("DeveloperDto", "DeveloperController", "getDeveloperList", "", null, "", "获取渠道品牌列表", e);
        }
        return resultData.toString();
    }
    
    //获取开发商信息
    @RequestMapping(value = "/getDeveloperInfo", method = RequestMethod.POST)
    public String getDeveloperInfo(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
            resultData = developerService.getDeveloperInfo(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("DeveloperDto", "DeveloperController", "getDeveloperInfo", "", null, "", "获取开发商信息", e);
        }
        return resultData.toString();
    }
    
    //获取开发商信息
    @RequestMapping(value = "/getDeveloperInfo2", method = RequestMethod.POST)
    public String getDeveloperInfo2(@RequestBody String json)
    {
    	Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
    	ResultData resultData = new ResultData();
    	try{
    		DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
    		resultData = developerService.getDeveloperInfo2(dto);
    	}catch (Exception e)
    	{
    		resultData.setFail();
    		logger.error("DeveloperDto", "DeveloperController", "getDeveloperInfo2", "", null, "", "获取开发商信息", e);
    	}
    	return resultData.toString();
    }
    
    //判断开发商是否已存在
    @RequestMapping(value = "/getDeveloperCountByName", method = RequestMethod.POST)
    public String getDeveloperCountByName(@RequestBody String json)
    {
    	Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
    	ResultData resultData = new ResultData();
    	try{
    		DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
    		resultData = developerService.getDeveloperCountByName(dto);
    	}catch (Exception e)
    	{
    		resultData.setFail();
    		logger.error("DeveloperDto", "DeveloperController", "getDeveloperCountByName", "", null, "", "获取开发商信息", e);
    	}
    	return resultData.toString();
    }
    
    //新增开发商信息
    @RequestMapping(value = "/addDeveloper", method = RequestMethod.POST)
    public String addDeveloper(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
            resultData = developerService.addDeveloper(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("DeveloperDto", "DeveloperController", "addDeveloper", "", null, "", "新增渠道品牌信息", e);
        }
        return resultData.toString();
    }
    
    //修改开发商信息
    @RequestMapping(value = "/updateDeveloper", method = RequestMethod.POST)
    public String updateDeveloper(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
            resultData = developerService.updateDeveloper(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("DeveloperDto", "DeveloperController", "updateDeveloper", "", null, "", "修改渠道品牌信息", e);
        }
        return resultData.toString();
    }
    
    //删除开发商信息
    @RequestMapping(value = "/deleteDeveloper", method = RequestMethod.POST)
    public String deleteDeveloper(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
            resultData = developerService.deleteDeveloper(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("DeveloperDto", "DeveloperController", "deleteDeveloper", "", null, "", "删除渠道品牌信息", e);
        }
        return resultData.toString();
    }
    
    //新增开发商城市关系表
    @RequestMapping(value = "/addDeveloperReleaseCity", method = RequestMethod.POST)
    public String addDeveloperReleaseCity(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	DeveloperDto dto = JsonUtil.parseToObject(json, DeveloperDto.class);
            resultData = developerService.addDeveloperReleaseCity(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("DeveloperDto", "DeveloperController", "addDeveloperReleaseCity", "", null, "", "新增开发商城市关系表失败", e);
        }
        return resultData.toString();
    }
    
  //根据开发商品牌获取垫佣和大客户
    @RequestMapping(value = "/getCustomerAndYjDy", method = RequestMethod.POST)
    public String getCustomerAndYjDy(@RequestBody String param)
    {
    	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    	ResultData resultData = new ResultData();
    	try{
    		resultData = developerService.getCustomerAndYjDy(queryParam);
    	}catch (Exception e)
    	{
    		resultData.setFail();
    		logger.error("DeveloperDto", "DeveloperController", "getCustomerAndYjDy", "", null, "", "根据开发商品牌获取垫佣和大客户", e);
    	}
    	return resultData.toString();
    }

}
