package cn.com.eju.deal.Report.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.base.linkage.model.City;


@Service("cityService")
public class CityServiceImpl implements ICityService
{
    
    @Resource
    private CityMapper cityMapper;
    
    @Override
    public List<City> getCityByCityName(Map<String, Object> map)
    {
        
        return cityMapper.getCityByCityName(map);
    }
    
    //****************************加载城市************************
    @Override
    public  List<City> getCityByIsService(Map<String,Object> map)
    {

    	List<City> list=new ArrayList<>();
        if(null!=map.get("type")) {
        	list=cityMapper.getCityByExchangeCenter(map);
        } else {
        	list=cityMapper.getCityByCityId(map);
        }
        return list;
    }
    
    
    
}
