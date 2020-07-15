package cn.com.eju.deal.common.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.common.dao.CityCascadeMapper;
import cn.com.eju.deal.common.model.Area;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.District;
import cn.com.eju.deal.common.model.Province;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.AreaDto;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.dto.common.DistrictDto;
import cn.com.eju.deal.dto.common.ProvinceDto;

/** 
* @ClassName: CityCascadeService 
* @Description: 省市区板块级联Service
* @author 陆海丹 
* @date 2016年3月28日 上午11:10:55 
*/
@Service("cityCascadeService")
public class CityCascadeService
{
    @Resource
    private CityCascadeMapper cityCascadeMapper;
    
    /** 
    * @Title: getProvinceList 
    * @Description: 获取省份列表
    * @return     
    */
    public List<ProvinceDto> getProvinceList() throws Exception{
        List<ProvinceDto> provinceDtos=new ArrayList<>();
        List<Province> provinces= this.cityCascadeMapper.queryProvinceList();
        provinceDtos=this.convertListDataProvince(provinces);
        return provinceDtos;
    }
    
    /** 
    * @Title: queryProvinceList 
    * @Description: 获取省份列表（供接口调用）
    * @return     
    */
    public ResultData<List<ProvinceDto>> queryProvinceList() throws Exception
    {
        ResultData<List<ProvinceDto>> resultData=new ResultData<>();
        List<ProvinceDto> provinceDtos=this.getProvinceList();
        if(null!=provinceDtos && !provinceDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(provinceDtos.size()));
            resultData.setReturnData(provinceDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: getCityList 
    * @Description: 根据身份编码获取城市列表
    * @param provinceNo 身份编码
    * @return     
    */
    public List<CityDto> getCityList(String provinceNo) throws Exception
    {
        List<CityDto> cityDtos=new ArrayList<>();
        List<City> cities=this.cityCascadeMapper.queryCityList(provinceNo);
        cityDtos=this.convertListDataCity(cities);
        return cityDtos;
    }
    
    /** 
    * @Title: queryCityList 
    * @Description: 根据身份编码获取城市列表（供接口调用）
    * @param provinceNo 身份编码
    * @return     
    */
    public ResultData<List<CityDto>> queryCityList(String provinceNo) throws Exception
    {
        ResultData<List<CityDto>> resultData=new ResultData<>();
        List<CityDto> cityDtos=this.getCityList(provinceNo);
        if(null!=cityDtos && !cityDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(cityDtos.size()));
            resultData.setReturnData(cityDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    /**
    * @Title: queryCityList
    * @Description: 根据用户id获取城市列表
    * @param userId 用户id
    * @return
    */
    public ResultData<List<CityDto>> queryCityListByUserId(String userId) throws Exception
    {
        ResultData<List<CityDto>> resultData=new ResultData<>();
        List<CityDto> cityDtos=new ArrayList<>();
        List<City> cities=this.cityCascadeMapper.queryCityListByUserId(userId);
        cityDtos=this.convertListDataCity(cities);

        if(null!=cityDtos && !cityDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(cityDtos.size()));
            resultData.setReturnData(cityDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: getDistrictList 
    * @Description: 根据城市编码获取区域列表
    * @param cityNo 城市编码
    * @return     
    */
    public List<DistrictDto> getDistrictList(String cityNo) throws Exception
    {
        List<DistrictDto> districtDtos=new ArrayList<>();
        List<District> districts=this.cityCascadeMapper.queryDistrictList(cityNo);
        districtDtos=this.convertListDataDistrict(districts);
        return districtDtos;
    }
    
    /** 
    * @Title: queryDistrictList 
    * @Description: 根据城市编码获取区域列表（供接口调用）
    * @param cityNo 城市编码
    * @return     
    */
    public ResultData<List<DistrictDto>> queryDistrictList(String cityNo) throws Exception
    {
        ResultData<List<DistrictDto>> resultData=new ResultData<>();
        List<DistrictDto> districtDtos=this.getDistrictList(cityNo);
        if(null!=districtDtos && !districtDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(districtDtos.size()));
            resultData.setReturnData(districtDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /** 
    * @Title: getAreaList 
    * @Description: 根据区域编号获取板块列表
    * @param districtNo 区域编号
    * @return     
    */
    public List<AreaDto> getAreaList(String districtNo) throws Exception
    {
        List<AreaDto> areaDtos=new ArrayList<>();
        List<Area> areas=this.cityCascadeMapper.queryAreaList(districtNo);
        areaDtos=this.convertListDataArea(areas);
        return areaDtos;
    }
    
    /** 
    * @Title: queryAreaList 
    * @Description: 根据区域编号获取板块列表（供接口调用）
    * @param districtNo 区域编号
    * @return     
    */
    public ResultData<List<AreaDto>> queryAreaList(String districtNo) throws Exception
    {
        ResultData<List<AreaDto>> resultData=new ResultData<>();
        List<AreaDto> areaDtos=this.getAreaList(districtNo);
        if(null!=areaDtos && !areaDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(areaDtos.size()));
            resultData.setReturnData(areaDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
    
    /*-----------------------------------------private function-----------------------------------------*/
    
    /** 
    * @Title: convertListDataProvince 
    * @Description: 将省份类List转换成接口传输类List
    * @param provinces
    * @return     
    */
    private List<ProvinceDto> convertListDataProvince(List<Province> provinces) throws Exception{
        List<ProvinceDto> provinceDtos=new ArrayList<ProvinceDto>();
        if(null!=provinces && !provinces.isEmpty()){
            ProvinceDto provinceDto=new ProvinceDto();
            for (Province province : provinces)
            {
                provinceDto=new ProvinceDto();
                BeanUtils.copyProperties(province, provinceDto);
                provinceDtos.add(provinceDto);
            }
        }
        return provinceDtos;
    }
    
    /** 
    * @Title: convertListDataCity 
    * @Description:将城市类List转换成接口传输类List
    * @param cities
    * @return     
    */
    private List<CityDto> convertListDataCity(List<City> cities) throws Exception{
        List<CityDto> cityDtos=new ArrayList<CityDto>();
        if(null!=cities && !cities.isEmpty()){
            CityDto cityDto=new CityDto();
            for (City city : cities)
            {
                cityDto=new CityDto();
                BeanUtils.copyProperties(city, cityDto);
                cityDtos.add(cityDto);
            }
        }
        return cityDtos;
    }
    
    /** 
    * @Title: convertListDataDistrict 
    * @Description: 将区域类List转换成接口传输类List
    * @param districts
    * @return     
    */
    private List<DistrictDto> convertListDataDistrict(List<District> districts) throws Exception
    {
        List<DistrictDto> districtDtos=new ArrayList<>();
        if(null!=districts && !districts.isEmpty())
        {
            DistrictDto districtDto=new DistrictDto();
            for (District district : districts)
            {
                districtDto=new DistrictDto();
                BeanUtils.copyProperties(district, districtDto);
                districtDtos.add(districtDto);
            }
        }
        return districtDtos;
    }
    
    /** 
    * @Title: convertListDataArea 
    * @Description: 将板块类List转换为传输类List
    * @param areas
    * @return     
    */
    private List<AreaDto> convertListDataArea(List<Area> areas) throws Exception
    {
        List<AreaDto> areaDtos=new ArrayList<>();
        if(null!=areas && !areas.isEmpty())
        {
            AreaDto areaDto=new AreaDto();
            for (Area area : areas)
            {
                areaDto=new AreaDto();
                BeanUtils.copyProperties(area, areaDto);
                areaDtos.add(areaDto);
            }
        }
        return areaDtos;
    }
    /*-----------------------------------------private function-----------------------------------------*/

    /** 
     * @Title: queryCityListByIsService 
     * @Description: 获取已开通城市
     * List<CityDto>     
     */
     public ResultData<List<CityDto>> queryCityListByIsService() throws Exception
     {
     	ResultData<List<CityDto>> resultData=new ResultData<>();
         List<CityDto> cityDtos=new ArrayList<>();
         List<City> cities=this.cityCascadeMapper.queryCityListByIsService();
         cityDtos=this.convertListDataCity(cities);
         if(null!=cityDtos && !cityDtos.isEmpty())
         {
             resultData.setTotalCount(String.valueOf(cityDtos.size()));
             resultData.setReturnData(cityDtos);
         }else {
             resultData.setFail();
         }
         return resultData;
     }
     /** 
      * @Title: queryCityListByIsLnkService 
      * @Description: 获取已开通城市
      * List<CityDto>     
      */
      public ResultData<List<CityDto>> queryCityListByIsLnkService() throws Exception
      {
      	ResultData<List<CityDto>> resultData=new ResultData<>();
          List<CityDto> cityDtos=new ArrayList<>();
          List<City> cities=this.cityCascadeMapper.queryCityListByIsLnkService();
          cityDtos=this.convertListDataCity(cities);
          if(null!=cityDtos && !cityDtos.isEmpty())
          {
              resultData.setTotalCount(String.valueOf(cityDtos.size()));
              resultData.setReturnData(cityDtos);
          }else {
              resultData.setFail();
          }
          return resultData;
      }
      /** 
       * @Title: queryCitySettingsList 
       * @Description: 获取发布城市列表
       * List<CityDto>     
       */
      public ResultData<List<CityDto>> queryCitySettingsList() throws Exception
      {
    	  ResultData<List<CityDto>> resultData=new ResultData<>();
    	  List<CityDto> cityDtos=new ArrayList<>();
    	  List<City> cities=this.cityCascadeMapper.queryCitySettingsList();
    	  cityDtos=this.convertListDataCity(cities);
    	  if(null!=cityDtos && !cityDtos.isEmpty())
    	  {
    		  resultData.setTotalCount(String.valueOf(cityDtos.size()));
    		  resultData.setReturnData(cityDtos);
    	  }else {
    		  resultData.setFail();
    	  }
    	  return resultData;
      }
      public ResultData<List<CityDto>> queryCityNameByCityNo(String cityNo) throws Exception
      {
    	  ResultData<List<CityDto>> resultData=new ResultData<>();
    	  List<CityDto> cityDtos=new ArrayList<>();
    	  List<City> cities=this.cityCascadeMapper.queryCityNameByCityNo(cityNo);
    	  cityDtos=this.convertListDataCity(cities);
    	  if(null!=cityDtos && !cityDtos.isEmpty())
    	  {
    		  resultData.setTotalCount(String.valueOf(cityDtos.size()));
    		  resultData.setReturnData(cityDtos);
    	  }else {
    		  resultData.setFail();
    	  }
    	  return resultData;
      }

    public ResultData<List<CityDto>> queryCityListByPlace() throws Exception {
        ResultData<List<CityDto>> resultData=new ResultData<>();
        List<CityDto> cityDtos=new ArrayList<>();
        List<City> cities=this.cityCascadeMapper.queryCityListByPlace();
        cityDtos=this.convertListDataCity(cities);
        if(null!=cityDtos && !cityDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(cityDtos.size()));
            resultData.setReturnData(cityDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }

    public ResultData<List<CityDto>> queryCityListByAffiliation(Integer userId) throws Exception {
        ResultData<List<CityDto>> resultData=new ResultData<>();
        List<CityDto> cityDtos=new ArrayList<>();
        List<City> cities=this.cityCascadeMapper.queryCityListByAffiliation(userId);
        cityDtos=this.convertListDataCity(cities);
        if(null!=cityDtos && !cityDtos.isEmpty())
        {
            resultData.setTotalCount(String.valueOf(cityDtos.size()));
            resultData.setReturnData(cityDtos);
        }else {
            resultData.setFail();
        }
        return resultData;
    }
}
