package cn.com.eju.deal.achievement.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.achievement.model.CitySetting;
import org.apache.ibatis.annotations.Param;

/**
 * 业绩归属 开关DAO
 * @author wenhui.zhang
 * date: 2017年4月21日 上午9:31:34
 */
public interface CitySettingMapper {
    /**
     * 根据cityNo查询城市业绩开关
     */
	String getOpenFlagByCityNo(@Param("cityNo") String cityNo);
	
	Map<String,Object> getCitySettingByCityNo(@Param("cityNo") String cityNo);

    Map<String,Object> getCitySettingByContractAcCityNo(String contractNo);
    //根据城市编码获取其相应的生成退款编号
    Map<String,Object> getBasCitySettingByCityNo(String cityNo);

    //查询所有城市
    List< Map<String,Object>> getBasCitySettingList();
    //查询归属城市下的归属中心
    List< Map<String,Object>>  getAchivAchievementLevelSettingList(String cityNo);

}