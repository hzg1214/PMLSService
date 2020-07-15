package cn.com.eju.deal.open.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 给TMS的 业绩归属信息DTO
 * 
 * @author wenhui.zhang
 * @date 2016年12月19日 下午5:46:44
 */
public class ExchangeAchievementDto implements Serializable {

	private static final long serialVersionUID = 1L;

	// 业绩类型
	private String achieveType;

	// 外表主键Id（门店业绩关联合同表；联动业绩关联报备表）
	private Integer relateId;

	// 业绩归属人Code
	private String expenderCode;

	// 业绩归属人姓名
	private String expenderName;

	// 组Id
	private Integer groupId;

	// 组名
	private String groupName;

	// 组负责人Code
	private String groupLeaderCode;

	// 组负责人姓名
	private String groupLeaderName;

	// 中心 组Id
	private Integer centerGroupId;

	// 中心 组名
	private String centerGroupName;

	// 中心负责人Code
	private String centerLeaderCode;

	// 中心负责人姓名
	private String centerLeaderName;

	// 区域 组Id
	private Integer areaGroupId;

	// 区域 组名
	private String areaGroupName;

	// 区域负责人Code
	private String areaLeaderCode;

	// 区域负责人姓名
	private String areaLeaderName;

	// 城市 组Id
	private Integer cityGroupId;

	// 城市 组名称
	private String cityGroupName;

	// 城市负责人Code
	private String cityLeaderCode;

	// 城市负责人姓名
	private String cityLeaderName;

	// 删除标识
	private Boolean delFlag;

	// 创建日期
	private Date dateCreate;

	public String getAchieveType() {
		return achieveType;
	}

	public void setAchieveType(String achieveType) {
		this.achieveType = achieveType == null ? null : achieveType.trim();
	}

	public Integer getRelateId() {
		return relateId;
	}

	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}

	public String getExpenderCode() {
		return expenderCode;
	}

	public void setExpenderCode(String expenderCode) {
		this.expenderCode = expenderCode == null ? null : expenderCode.trim();
	}

	public String getExpenderName() {
		return expenderName;
	}

	public void setExpenderName(String expenderName) {
		this.expenderName = expenderName == null ? null : expenderName.trim();
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName == null ? null : groupName.trim();
	}

	public String getGroupLeaderCode() {
		return groupLeaderCode;
	}

	public void setGroupLeaderCode(String groupLeaderCode) {
		this.groupLeaderCode = groupLeaderCode == null ? null : groupLeaderCode.trim();
	}

	public String getGroupLeaderName() {
		return groupLeaderName;
	}

	public void setGroupLeaderName(String groupLeaderName) {
		this.groupLeaderName = groupLeaderName == null ? null : groupLeaderName.trim();
	}

	public Integer getCenterGroupId() {
		return centerGroupId;
	}

	public void setCenterGroupId(Integer centerGroupId) {
		this.centerGroupId = centerGroupId;
	}

	public String getCenterGroupName() {
		return centerGroupName;
	}

	public void setCenterGroupName(String centerGroupName) {
		this.centerGroupName = centerGroupName == null ? null : centerGroupName.trim();
	}

	public String getCenterLeaderCode() {
		return centerLeaderCode;
	}

	public void setCenterLeaderCode(String centerLeaderCode) {
		this.centerLeaderCode = centerLeaderCode == null ? null : centerLeaderCode.trim();
	}

	public String getCenterLeaderName() {
		return centerLeaderName;
	}

	public void setCenterLeaderName(String centerLeaderName) {
		this.centerLeaderName = centerLeaderName == null ? null : centerLeaderName.trim();
	}

	public Integer getAreaGroupId() {
		return areaGroupId;
	}

	public void setAreaGroupId(Integer areaGroupId) {
		this.areaGroupId = areaGroupId;
	}

	public String getAreaGroupName() {
		return areaGroupName;
	}

	public void setAreaGroupName(String areaGroupName) {
		this.areaGroupName = areaGroupName == null ? null : areaGroupName.trim();
	}

	public String getAreaLeaderCode() {
		return areaLeaderCode;
	}

	public void setAreaLeaderCode(String areaLeaderCode) {
		this.areaLeaderCode = areaLeaderCode == null ? null : areaLeaderCode.trim();
	}

	public String getAreaLeaderName() {
		return areaLeaderName;
	}

	public void setAreaLeaderName(String areaLeaderName) {
		this.areaLeaderName = areaLeaderName == null ? null : areaLeaderName.trim();
	}

	public Integer getCityGroupId() {
		return cityGroupId;
	}

	public void setCityGroupId(Integer cityGroupId) {
		this.cityGroupId = cityGroupId;
	}

	public String getCityGroupName() {
		return cityGroupName;
	}

	public void setCityGroupName(String cityGroupName) {
		this.cityGroupName = cityGroupName == null ? null : cityGroupName.trim();
	}

	public String getCityLeaderCode() {
		return cityLeaderCode;
	}

	public void setCityLeaderCode(String cityLeaderCode) {
		this.cityLeaderCode = cityLeaderCode == null ? null : cityLeaderCode.trim();
	}

	public String getCityLeaderName() {
		return cityLeaderName;
	}

	public void setCityLeaderName(String cityLeaderName) {
		this.cityLeaderName = cityLeaderName == null ? null : cityLeaderName.trim();
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
}
