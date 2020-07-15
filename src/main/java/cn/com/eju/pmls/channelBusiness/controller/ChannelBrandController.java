package cn.com.eju.pmls.channelBusiness.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.channelBusiness.model.ChannelBrandDto;
import cn.com.eju.pmls.channelBusiness.service.ChannelBrandService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("channelBrandController")
public class ChannelBrandController extends BaseController {
    @Resource
    private ChannelBrandService channelBrandService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    //获取渠道品牌列表
    @RequestMapping(value = "/getChannelBrandList/{param}", method = RequestMethod.GET)
    public String getChannelBrandList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = channelBrandService.getChannelBrandList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("ChannelBrandDto", "ChannelBrandController", "getChannelBrandList", "", null, "", "获取渠道品牌列表", e);
        }
        return resultData.toString();
    }
    //获取渠道品牌信息
    @RequestMapping(value = "/getBrandInfo/{param}", method = RequestMethod.GET)
    public String getBrandInfo(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = channelBrandService.getBrandInfo(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("ChannelBrandDto", "ChannelBrandController", "getBrandInfo", "", null, "", "获取渠道品牌信息", e);
        }
        return resultData.toString();
    }
    //新增渠道品牌信息
    @RequestMapping(value = "/addBrand", method = RequestMethod.POST)
    public String addBrand(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            ChannelBrandDto dto = JsonUtil.parseToObject(json, ChannelBrandDto.class);
            resultData = channelBrandService.addBrand(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("ChannelBrandDto", "ChannelBrandController", "addBrand", "", null, "", "新增渠道品牌信息", e);
        }
        return resultData.toString();
    }
    //修改渠道品牌信息
    @RequestMapping(value = "/updateBrand", method = RequestMethod.POST)
    public String updateBrand(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            ChannelBrandDto dto = JsonUtil.parseToObject(json, ChannelBrandDto.class);
            resultData = channelBrandService.updateBrand(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("ChannelBrandDto", "ChannelBrandController", "updateBrand", "", null, "", "修改渠道品牌信息", e);
        }
        return resultData.toString();
    }
    //删除渠道品牌信息
    @RequestMapping(value = "/deleteBrand", method = RequestMethod.POST)
    public String deleteBrand(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
            ChannelBrandDto dto = JsonUtil.parseToObject(json, ChannelBrandDto.class);
            resultData = channelBrandService.deleteBrand(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("ChannelBrandDto", "ChannelBrandController", "deleteBrand", "", null, "", "删除渠道品牌信息", e);
        }
        return resultData.toString();
    }

}
