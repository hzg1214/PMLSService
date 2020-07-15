package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * desc:垫佣规则变更entity
 * @date   :2020年6月10日
 */
public class MattressControlRuleDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3878735288439544882L;

	//变更ID
	private Integer id;
	private String projectNo;
	private String oaId;
	private String isGlobalControl;//是否垫佣总控，0-否，1-是
	private BigDecimal dyRatio;//垫佣比例
	private Date gCStartDate;//垫佣周期-开始时间
	private Date gCEndDate;//垫佣周期-结束时间
	private Date dateCreate;//创建时间
	private Integer userIdCreate;
	private Integer delFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getOaId() {
		return oaId;
	}
	public void setOaId(String oaId) {
		this.oaId = oaId;
	}
	public String getIsGlobalControl() {
		return isGlobalControl;
	}
	public void setIsGlobalControl(String isGlobalControl) {
		this.isGlobalControl = isGlobalControl;
	}
	public BigDecimal getDyRatio() {
		return dyRatio;
	}
	public void setDyRatio(BigDecimal dyRatio) {
		this.dyRatio = dyRatio;
	}
	public Date getgCStartDate() {
		return gCStartDate;
	}
	public void setgCStartDate(Date gCStartDate) {
		this.gCStartDate = gCStartDate;
	}
	public Date getgCEndDate() {
		return gCEndDate;
	}
	public void setgCEndDate(Date gCEndDate) {
		this.gCEndDate = gCEndDate;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Integer getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}