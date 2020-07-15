package cn.com.eju.deal.base.linkage.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.core.dao.IDao;

public interface CityMapper extends IDao<City>
{
    /** 
    * 获取所有城市
    * @return
    */
    List<City> getAll();
 //Add By tong 2017/04/07 报表用 Start
    
    //根据城市名称查询城市
    List<City> getCityByCityName(Map<String,Object> map); 
    
    //根据cityNo查询城市
    City getCityByCityNo(String cityNo); 
    
    //根据用户权限查询城市
    List<City> getCityByIsService(Map<String,Object> map);
    
    //根据用户所属城市查询城市
    List<City> getCityByCityId(Map<String,Object> map);
           
    //根据用户权限查询城市(交易中心)
    List<City> getCityByExchangeCenter(Map<String,Object> map);
    
    //
    List<City> getCityToExchangeId(); 
  //Add By tong 2017/04/07 End
    
    Map<String,Object> getCityByGovCityCode(String govCityCode);
}