package cn.com.eju.deal.houseLinkage.report.model;

import java.util.Date;



public class ReportAchieveMentAdjustLog {
	  private Integer id;
	  private Integer relateId;						//报备id
	  private String reportId;	         			//报备编号 
	  private String changeReason;					//变更原因		
	  private String changeContent;           		//变更内容
	  private String oldMaintainerCode;				//原业绩归属人code
	  private String oldMaintainerNm;				//原业绩归属人名字
	  private String maintainerCode;				//当前绩归属人code
	  private String maintainerNm;					//当前绩归属人名字
	  private String oldCenterGroupId;				//原业绩归属中心
	  private String oldCenterGroupName ;			//原业绩归属名称
	  private String centerGroupId;					//当前业绩归属中心Id
	  private String centerGroupName;				//当前业绩归属名称
	  private Date dateCreate;						//创建日期
	  private Integer userIdCreate;              	//创建人ID
	  private String userName;       				//创建人姓名
	  private String userCode;       				//创建人code
	  private String delFlag;        				//删除标识
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getChangeContent() {
		return changeContent;
	}
	public void setChangeContent(String changeContent) {
		this.changeContent = changeContent;
	}
	public String getOldMaintainerCode() {
		return oldMaintainerCode;
	}
	public void setOldMaintainerCode(String oldMaintainerCode) {
		this.oldMaintainerCode = oldMaintainerCode;
	}
	public String getOldMaintainerNm() {
		return oldMaintainerNm;
	}
	public void setOldMaintainerNm(String oldMaintainerNm) {
		this.oldMaintainerNm = oldMaintainerNm;
	}
	public String getMaintainerCode() {
		return maintainerCode;
	}
	public void setMaintainerCode(String maintainerCode) {
		this.maintainerCode = maintainerCode;
	}
	public String getMaintainerNm() {
		return maintainerNm;
	}
	public void setMaintainerNm(String maintainerNm) {
		this.maintainerNm = maintainerNm;
	}
	public String getOldCenterGroupId() {
		return oldCenterGroupId;
	}
	public void setOldCenterGroupId(String oldCenterGroupId) {
		this.oldCenterGroupId = oldCenterGroupId;
	}
	public String getOldCenterGroupName() {
		return oldCenterGroupName;
	}
	public void setOldCenterGroupName(String oldCenterGroupName) {
		this.oldCenterGroupName = oldCenterGroupName;
	}
	public String getCenterGroupId() {
		return centerGroupId;
	}
	public void setCenterGroupId(String centerGroupId) {
		this.centerGroupId = centerGroupId;
	}
	public String getCenterGroupName() {
		return centerGroupName;
	}
	public void setCenterGroupName(String centerGroupName) {
		this.centerGroupName = centerGroupName;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public Integer getRelateId() {
		return relateId;
	}
	public void setRelateId(Integer relateId) {
		this.relateId = relateId;
	}
	public String getChangeReason() {
		return changeReason;
	}
	public void setChangeReason(String changeReason) {
		this.changeReason = changeReason;
	}
	public Integer getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
    
    
}