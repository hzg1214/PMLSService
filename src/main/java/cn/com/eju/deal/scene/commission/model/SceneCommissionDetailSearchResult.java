package cn.com.eju.deal.scene.commission.model;

import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class SceneCommissionDetailSearchResult  {
	
	// 楼盘ID
	private String estateId;
	// 楼盘名
	private String estateNm;
	// 报备编码
	private String reportId;
	// 城市
	private String cityNo;
	// 客户编号
	private String customerId;
	// 客户名
	private String customerNm;
	// 客户电话
	private String customerTel;
	
	// 公司名称
	private String companyNm;
	// 公司Id
	private String companyId;
	// 部门名
	private String deptNm;
	// 员工名
	private String empNm;
	
	// 奖励金额
	private String reward;
	// 结算金额
	private String accountReward;
	// 报备日
	private Date reportDate;
	
	// 认定日期
	private Date recognitionDay;
	// 结算状态
	private Integer accountStatus;
	private String accountStatusNm;
	// 进度(奖励类型)
	private Integer progress;
	private String progressNm;
	// 结算金额(更新用)
	private String updAccountReward;
	// 隐号设置
	private String hideNumberFlg;
	
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public String getAccountReward() {
		return accountReward;
	}
	public void setAccountReward(String accountReward) {
		this.accountReward = accountReward;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Date getRecognitionDay() {
		return recognitionDay;
	}
	public void setRecognitionDay(Date recognitionDay) {
		this.recognitionDay = recognitionDay;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getAccountStatusNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(accountStatus));
	}
	public void setAccountStatusNm(String accountStatusNm) {
		this.accountStatusNm = accountStatusNm;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public String getProgressNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(progress));
	}
	public void setProgressNm(String progressNm) {
		this.progressNm = progressNm;
	}
	public String getUpdAccountReward() {
		return updAccountReward;
	}
	public void setUpdAccountReward(String updAccountReward) {
		this.updAccountReward = updAccountReward;
	}
	public String getHideNumberFlg() {
		return hideNumberFlg;
	}
	public void setHideNumberFlg(String hideNumberFlg) {
		this.hideNumberFlg = hideNumberFlg;
	}
    
    
}