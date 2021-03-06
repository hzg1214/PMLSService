package cn.com.eju.deal.base.linkage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.base.linkage.dto.CityBrandDto;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.linkage.dao.AreaMapper;
import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.base.linkage.dao.DistrictMapper;
import cn.com.eju.deal.base.linkage.dao.ProvinceMapper;
import cn.com.eju.deal.base.linkage.dto.AreaDto;
import cn.com.eju.deal.base.linkage.dto.CityDto;
import cn.com.eju.deal.base.linkage.dto.DistrictDto;
import cn.com.eju.deal.base.linkage.model.Area;
import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.base.linkage.model.District;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.model.Group;

/**   
* 省、市、区、板块 联动  服务类
* @author li_xiaodong
* @date 2016年2月2日 下午7:57:09
*/
@Service("linkageService")
public class LinkageService extends BaseService<Object>
{
    @Resource
    private ProvinceMapper provinceMapper;
    
    @Resource
    private CityMapper cityMapper;
    
    @Resource
    private DistrictMapper districtMapper;
    
    @Resource
    private AreaMapper areaMapper;
    
    @Resource
    private GroupMapper groupMapper;
    
    /** 
     * 获取城市list
     * @param queryParam
     * @return
     */
    public ResultData<?> getCityList()
        throws Exception
    {
        
        //构建返回
        ResultData<List<CityDto>> resultData = new ResultData<List<CityDto>>();
        
        //查询
        final List<City> moList = cityMapper.getAll();
        
        //转换
        List<CityDto> dtoList = convertCityData(moList);
        
        resultData.setReturnData(dtoList);
        
        return resultData;
    }
    
    /** 
     * 根据城市CityNo获取其行政区List
     * @param queryParam
     * @return
     */
    public ResultData<?> getDistrictListByCityNo(String cityNo)
        throws Exception
    {
        
        //构建返回
        ResultData<List<DistrictDto>> resultData = new ResultData<List<DistrictDto>>();
        
        //查询
        final List<District> moList = districtMapper.getDistrictByCityNo(cityNo);
        
        //转换
        List<DistrictDto> dtoList = convertDistrictData(moList);
        
        resultData.setReturnData(dtoList);
        
        return resultData;
    }
    /**
     * 根据城市CityNo获取品牌List
     * @param queryParam
     * @return
     */
    public ResultData<?> getBrandListByCityNo(String cityNo)
        throws Exception
    {

        //构建返回
        ResultData<List<CityBrandDto>> resultData = new ResultData<List<CityBrandDto>>();

        //查询
        final List<CityBrandDto> cbList = districtMapper.getBrandListByCityNo(cityNo);

        resultData.setReturnData(cbList);

        return resultData;
    }
    /**
     * 根据城市CityNo获取品牌List
     * @param cityNo
     * @return
     */
    public ResultData<?> getIsShowQRCode(String cityNo)
        throws Exception
    {

        //构建返回
        ResultData resultData = new ResultData();
        //查询
        int isShow = districtMapper.getIsShowQRCode(cityNo);
        resultData.setReturnData(isShow);

        return resultData;
    }
    
    /** 
     * 根据行政区DistrictNo获取其板块List
     * @param queryParam
     * @return
     */
    public ResultData<?> getAreaListByDistrictNo(String districtNo)
        throws Exception
    {
        
        //构建返回
        ResultData<List<AreaDto>> resultData = new ResultData<List<AreaDto>>();
        
        //查询
        final List<Area> moList = areaMapper.getAreaByDistrictNo(districtNo);
        
        //转换
        List<AreaDto> dtoList = convertAreaData(moList);
        
        resultData.setReturnData(dtoList);
        
        return resultData;
    }
    
    /** 
     * 根据城市cityNo获取城市所有板块List
     * @param cityNo
     * @return
     */
    public ResultData<?> getAreaListByCityNo(String cityNo)
        throws Exception
    {
        
        //构建返回
        ResultData<List<AreaDto>> resultData = new ResultData<List<AreaDto>>();
        
        //查询
        final List<Area> moList = areaMapper.getAreaListByCityNo(cityNo);
        
        //转换
        List<AreaDto> dtoList = convertAreaData(moList);
        
        resultData.setReturnData(dtoList);
        
        return resultData;
    }
    
    /** 
     * 对象转换MO--DTO
     * @param stuList
     * @return List<StudentDto>
     */
    private List<CityDto> convertCityData(List<City> moList)
        throws Exception
    {
        List<CityDto> dtoList = new ArrayList<CityDto>();
        
        if (null != moList && !moList.isEmpty())
        {
            CityDto dto = null;
            for (City mo : moList)
            {
                dto = new CityDto();
                BeanUtils.copyProperties(mo, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    /** 
     * 对象转换MO--DTO
     * @param stuList
     * @return List<StudentDto>
     */
    private List<DistrictDto> convertDistrictData(List<District> moList)
        throws Exception
    {
        List<DistrictDto> dtoList = new ArrayList<DistrictDto>();
        
        if (null != moList && !moList.isEmpty())
        {
            DistrictDto dto = null;
            for (District mo : moList)
            {
                dto = new DistrictDto();
                BeanUtils.copyProperties(mo, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    /** 
     * 对象转换MO--DTO
     * @param stuList
     * @return List<StudentDto>
     */
    private List<AreaDto> convertAreaData(List<Area> moList)
        throws Exception
    {
        List<AreaDto> dtoList = new ArrayList<AreaDto>();
        
        if (null != moList && !moList.isEmpty())
        {
            AreaDto dto = null;
            for (Area mo : moList)
            {
                dto = new AreaDto();
                BeanUtils.copyProperties(mo, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
  //Add By ningchao 2017/07/03 Start
    public City getCityByCityNo(String cityNo)
    {
    	return cityMapper.getCityByCityNo(cityNo);
    }
    //Add By ningchao 2017/07/03 End
    
    public Map<String,Object> getCityByGovCityCode(String govCityCode){
    	return cityMapper.getCityByGovCityCode(govCityCode);
    }
}
