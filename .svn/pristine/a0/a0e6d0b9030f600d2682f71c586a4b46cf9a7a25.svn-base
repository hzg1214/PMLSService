package cn.com.eju.deal.achievement.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.core.dao.IDao;

import java.util.Map;

/**
 * 业绩归属DAO层
 * @author wenhui.zhang
 * date: 2017年3月14日 下午6:30:43
 */
@Repository
public interface AchievementMapper extends IDao<Achievement>
{
	/**
	 * 删除合同下的业绩归属人
	 * @param relateId 合同Id
	 * @return 影响记录条数
	 */
	int delAchievement(@Param("relateId") Integer relateId) throws Exception;
	
	/**
	 * 获取归属信息(门店维护人)
	 */
	Achievement getAchievementInfo(@Param("userCode") String userCode,@Param("typeCode") String typeCode,@Param("achieveType") String achieveType) throws Exception;

	/**
	 * 获取归属信息(门店)
	 */
	Achievement getAchievementInfoNew(Map<String,Object> reqMap) throws Exception;
	/**
	 * 根据中心获取归属(门店)
	 */
	Achievement getAchievementInfoNewByCenterId(Map<String,Object> reqMap) throws Exception;
	/**
	 * 获取归属信息(门店)
	 */
	Achievement getAchievementInfoContract(Map<String,Object> reqMap) throws Exception;

	/**
	 * 更新业绩归属信息
	 * @param achievement
	 * @return
	 */
	int updateAchieveInfo(Achievement achievement);

	int createQt(Achievement achievement);

	int delQtAchievement(@Param("relateId") Integer relateId) throws Exception;
}