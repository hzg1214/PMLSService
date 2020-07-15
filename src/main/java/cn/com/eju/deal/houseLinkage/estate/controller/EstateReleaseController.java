package cn.com.eju.deal.houseLinkage.estate.controller;

import java.util.List;
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
import cn.com.eju.deal.dto.houseLinkage.estate.EstateInfoDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseDto;
import cn.com.eju.deal.houseLinkage.estate.service.EstateReleaseService;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;

/**   
* 服务层
* @author zhangwanli
* @date 2018年1月16日 下午6:05:44
*/

@RestController
@RequestMapping(value = "estateRelease")
public class EstateReleaseController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "estatetService")
    private EstateService estateService;
    
    @Resource
    private EstateReleaseService estateReleaseService;
    
    @RequestMapping(value = "/queryCityListByEstateId/{param}", method = RequestMethod.GET)
    public String queryCityListByEstateId(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String estateId= queryParam.get("estateId")+"";
        //构建返回
        ResultData<List<EstateReleaseCityDto>> resultData = new ResultData<List<EstateReleaseCityDto>>();
        try
        {
            resultData = this.estateReleaseService.queryCityListByEstateId(estateId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("CRM", "EstateReleaseController", "queryCityListByEstateId", "", null, "", "", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/releaseCity", method = RequestMethod.POST)
    public ResultData<?> releaseCity(@RequestBody String jsonDto){
        
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        
        EstateReleaseDto releaseDto = JsonUtil.parseToObject(jsonDto, EstateReleaseDto.class);
        
        int count = 0;
        try
        {
            count = estateReleaseService.update(releaseDto);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("houseLinkage", "EstateReleaseController", "releaseCity", "", null, "", "更新失败", e);
        }
        
        return resultData;
    }

}
