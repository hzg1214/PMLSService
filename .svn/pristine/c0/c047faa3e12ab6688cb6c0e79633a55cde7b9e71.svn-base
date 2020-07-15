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
import cn.com.eju.pmls.developer.dto.DeveloperBrandDto;
import cn.com.eju.pmls.developer.service.DeveloperBrandService;

@RestController
@RequestMapping("developerBrand")
public class DeveloperBrandController extends BaseController {
    @Resource
    private DeveloperBrandService developerBrandService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    //获取合作方品牌列表
    @RequestMapping(value = "/getDeveloperBrandList/{param}", method = RequestMethod.GET)
    public String getDeveloperBrandList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = developerBrandService.getDeveloperBrandList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "getDeveloperBrandList", "", null, "", "获取合作方品牌列表", e);
        }
        return resultData.toString();
    }
    //获取合作方品牌信息
    @RequestMapping(value = "/getDeveloperBrandInfo/{param}", method = RequestMethod.GET)
    public String getDeveloperBrandInfo(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = developerBrandService.getDeveloperBrandInfo(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "getDeveloperBrandInfo", "", null, "", "获取合作方品牌信息", e);
        }
        return resultData.toString();
    }
    //新增合作方品牌信息
    @RequestMapping(value = "/addDeveloperBrand", method = RequestMethod.POST)
    public String addDeveloperBrand(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	DeveloperBrandDto dto = JsonUtil.parseToObject(json, DeveloperBrandDto.class);
            resultData = developerBrandService.addDeveloperBrand(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "addDeveloperBrand", "", null, "", "新增合作方品牌信息", e);
        }
        return resultData.toString();
    }
    //修改合作方品牌信息
    @RequestMapping(value = "/updateDeveloperBrand", method = RequestMethod.POST)
    public String updateDeveloperBrand(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	DeveloperBrandDto dto = JsonUtil.parseToObject(json, DeveloperBrandDto.class);
            resultData = developerBrandService.updateDeveloperBrand(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "updateDeveloperBrand", "", null, "", "修改合作方品牌信息", e);
        }
        return resultData.toString();
    }
    //删除合作方品牌信息
    @RequestMapping(value = "/deleteDeveloperBrand", method = RequestMethod.POST)
    public String deleteDeveloperBrand(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	DeveloperBrandDto dto = JsonUtil.parseToObject(json, DeveloperBrandDto.class);
            resultData = developerBrandService.deleteDeveloperBrand(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "deleteDeveloperBrand", "", null, "", "删除合作方品牌信息", e);
        }
        return resultData.toString();
    }
    
  //是否可以修改合作方品牌信息
    @RequestMapping(value = "/updateCheck", method = RequestMethod.POST)
    public String updateCheck(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	DeveloperBrandDto dto = JsonUtil.parseToObject(json, DeveloperBrandDto.class);
            resultData = developerBrandService.updateCheck(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "updateDeveloperBrand", "", null, "", "修改合作方品牌信息", e);
        }
        return resultData.toString();
    }

    //获取合作方品牌列表
    @RequestMapping(value = "/getDeveloperBrandListByPage/{param}", method = RequestMethod.GET)
    public String getDeveloperBrandListByPage(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = developerBrandService.getDeveloperBrandListByPage(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("developerBrand", "DeveloperBrandController", "getDeveloperBrandList", "", null, "", "获取合作方品牌列表", e);
        }
        return resultData.toString();
    }

}
