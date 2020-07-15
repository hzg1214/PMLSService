package cn.com.eju.deal.user.dao;

import java.util.List;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.user.model.CityDistrictMapping;


/**
 * 城市、行政区信息接口
 * @author yujun
 *
 */
public interface CityDistrictMapper extends IDao<CityDistrictMapping>{

	int insertGroupCity(CityDistrictMapping cityDistrictMapping) throws Exception;
	
	int insertGroupDistrict(CityDistrictMapping cityDistrictMapping) throws Exception;
	
	int deleteCityByPrimaryKey(Integer id) throws Exception;

	int deleteDistrictByPrimaryKey(Integer id) throws Exception;
	
	int updateCityByCityId(CityDistrictMapping cityDistrictMapping) throws Exception;

	int updateDistrictByDistrictId(CityDistrictMapping cityDistrictMapping) throws Exception;
	
    List<CityDistrictMapping> queryCityList(Integer groupId) throws Exception;
    
    List<CityDistrictMapping> queryDistrictList(Integer groupId) throws Exception;
    
    List<CityDistrictMapping> queryDistrictByCityName(String cityName) throws Exception;
    
    List<CityDistrictMapping> queryAllCity() throws Exception;
	
    CityDistrictMapping selectDistrictByDistrictId(CityDistrictMapping cityDistrictMapping) throws Exception;
    
    CityDistrictMapping selectCityByCityId(CityDistrictMapping cityDistrictMapping) throws Exception;
}