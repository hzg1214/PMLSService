package cn.com.eju.deal.common.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.*;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.service.CityCascadeService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.common.AreaDto;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.dto.common.DistrictDto;
import cn.com.eju.deal.dto.common.ProvinceDto;
import cn.com.eju.deal.dto.store.StoreDto;

/** 
* @ClassName: CityCascadeController 
* @Description: 省市区板块级联
* @author 陆海丹 
* @date 2016年3月28日 下午1:45:37 
*/
@RestController
@RequestMapping(value = "citycascade")
public class CityCascadeController extends BaseController
{
    
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
    @Resource(name="cityCascadeService")
    private CityCascadeService cityCascadeService;
    
    /** 
    * @Title: provincelist 
    * @Description: 获取省份列表
    * @return     
    */
    @RequestMapping(value = "/province", method = RequestMethod.GET)
    public String provincelist()
    {
        //构建返回
        ResultData<List<ProvinceDto>> resultData = new ResultData<List<ProvinceDto>>();
        try
        {
            resultData = this.cityCascadeService.queryProvinceList();
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "CityCascadeController", "provincelist", "", null, "", "", e);
        }    
        return resultData.toString();
    }
    
    /** 
    * @Title: citylist 
    * @Description: 根据省份编号获取城市列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/city/{param}", method = RequestMethod.GET)
    public String citylist(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String provinceNo=(String)queryParam.get("provinceNo");
        //构建返回
        ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
        try
        {
            resultData = this.cityCascadeService.queryCityList(provinceNo);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "CityCascadeController", "citylist", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * @Title: citylist
     * @Description: 根据省份编号获取城市列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryCityListByUserId/{param}", method = RequestMethod.GET)
    public String queryCityListByUserId(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String userId=queryParam.get("userId")+"";
        //构建返回
        ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
        try
        {
            resultData = this.cityCascadeService.queryCityListByUserId(userId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "CityCascadeController", "queryCityListByUserId", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: districtlist 
    * @Description: 根据城市编号获取区域列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/district/{param}", method = RequestMethod.GET)
    public String districtlist(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String cityNo=(String)queryParam.get("cityNo");
        //构建返回
        ResultData<List<DistrictDto>> resultData = new ResultData<List<DistrictDto>>();
        try
        {
            resultData = this.cityCascadeService.queryDistrictList(cityNo);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "CityCascadeController", "districtlist", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: arealist 
    * @Description: 根据区域编号获取板块列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/area/{param}", method = RequestMethod.GET)
    public String arealist(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String districtNo=(String)queryParam.get("districtNo");
        //构建返回
        ResultData<List<AreaDto>> resultData = new ResultData<List<AreaDto>>();
        try
        {
            resultData = this.cityCascadeService.queryAreaList(districtNo);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "CityCascadeController", "arealist", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /** 
     * @Title: queryCityListByIsService 
     * @Description: 获取已开通城市
     * ResultData<List<CityDto>>  
     */
     @RequestMapping(value = "/queryCityListByIsService", method = RequestMethod.GET)
     public String queryCityListByIsService()
     {
         //构建返回
         ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
         try
         {
             resultData =  this.cityCascadeService.queryCityListByIsService();
         }
         catch (Exception e)
         {
             resultData.setFail();
             logger.error("city", "CityCascadeController", "queryCityListByIsService", "", null, "", "", e);
         }
         return resultData.toString();
     }

     @RequestMapping(value = "/queryCityListByPlace", method = RequestMethod.GET)
     public String queryCityListByPlace()
     {
         //构建返回
         ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
         try
         {
             resultData =  this.cityCascadeService.queryCityListByPlace();
         }
         catch (Exception e)
         {
             resultData.setFail();
             logger.error("city", "CityCascadeController", "queryCityListByPlace", "", null, "", "", e);
         }
         return resultData.toString();
     }

     @RequestMapping(value = "/queryCityListByAffiliation/{userId}", method = RequestMethod.GET)
     public String queryCityListByAffiliation(@PathVariable Integer userId)
     {
         //构建返回
         ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
         try
         {
             resultData =  this.cityCascadeService.queryCityListByAffiliation(userId);
         }
         catch (Exception e)
         {
             resultData.setFail();
             logger.error("city", "CityCascadeController", "queryCityListByAffiliation", "", null, "", "", e);
         }
         return resultData.toString();
     }

     /** 
      * @Title: queryCityListByIsService 
      * @Description: 获取已开通城市
      * ResultData<List<CityDto>>  
      */
      @RequestMapping(value = "/queryCityListByIsLnkService", method = RequestMethod.GET)
      public String queryCityListByIsLnkService()
      {
          //构建返回
          ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
          try
          {
              resultData =  this.cityCascadeService.queryCityListByIsLnkService();
          }
          catch (Exception e)
          {
              resultData.setFail();
              logger.error("city", "CityCascadeController", "queryCityListByIsService", "", null, "", "", e);
          }
          return resultData.toString();
      }
      /** 
       * @Title: queryCityListByIsService 
       * @Description: 获取发布城市列表
       */
      @RequestMapping(value = "/queryCityNameByCityNo/{param}", method = RequestMethod.GET)
      public String queryCityNameByCityNo(@PathVariable String param){
    	  Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
          String cityNo=(String)queryParam.get("cityNo");
    	  //构建返回
    	  ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
    	  try
    	  {
    		  resultData =  this.cityCascadeService.queryCityNameByCityNo(cityNo);
    	  }
    	  catch (Exception e)
    	  {
    		  resultData.setFail();
    		  logger.error("city", "CityCascadeController", "queryCityNameByCityNo", "", null, "", "", e);
    	  }
    	  return resultData.toString();
      }
      @RequestMapping(value = "/queryCitySettingsList", method = RequestMethod.GET)
      public String queryCitySettingsList()
      {
    	  //构建返回
    	  ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
    	  try
    	  {
    		  resultData =  this.cityCascadeService.queryCitySettingsList();
    	  }
    	  catch (Exception e)
    	  {
    		  resultData.setFail();
    		  logger.error("city", "CityCascadeController", "queryCitySettingsList", "", null, "", "", e);
    	  }
    	  return resultData.toString();
      }
}
