package cn.com.eju.deal.achievement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.com.eju.deal.achievement.model.AchievementSetting;

/**
 * 业绩归属配置表DAO
 * @author wenhui.zhang
 * date: 2017年3月14日 下午6:24:41
 */
@Repository
public interface AchievementSettingMapper {
	/**
	 * 根据dicCode查询配置信息
	 * @return AchievementSetting
	 */
	AchievementSetting getByDicCodeAndCityNo(@Param(value="dicCode") Integer dicCode,@Param(value="cityNo") String cityNo) throws Exception;
}