package cn.com.eju.deal.common.dao;


import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.PmlsUserCenterCitySetting;

import java.util.List;
import java.util.Map;

public interface PmlsUserCenterCitySettingMapper {

    List<City> queryCityList(Map<?, ?> param) throws Exception;

    List<PmlsUserCenterCitySetting> queryCenterList(Map<?, ?> param) throws Exception;

    /*
        HBL归属区域
     */
    List<Map<String, Object>> queryHblRegionList(Map<String, Object> param) throws Exception;

    /*
        HBL归属板块
     */
    List<Map<String, Object>> queryHblAreaCityList(Map<String, Object> param) throws Exception;

    /*
        HBL归属城市
     */
    List<Map<String, Object>> queryHblCityList(Map<String, Object> param) throws Exception;

    /*
        HBL归属中心
     */
    List<Map<String, Object>> queryHblCenterList(Map<String, Object> param) throws Exception;

}
