package cn.com.eju.deal.achievement.model;

/**
 * 业绩归属 城市开关模型
 * 
 * @author wenhui.zhang 
 * date: 2017年4月21日 上午9:29:48
 */
public class CitySetting {
	private Integer id;

	private String cityNo;

	// 业绩开关(0:旧版，1：新版)
	private String achievementOpenFlag;

	private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo == null ? null : cityNo.trim();
	}

	public String getAchievementOpenFlag() {
		return achievementOpenFlag;
	}

	public void setAchievementOpenFlag(String achievementOpenFlag) {
		this.achievementOpenFlag = achievementOpenFlag == null ? null : achievementOpenFlag.trim();
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag == null ? null : delFlag.trim();
	}
}