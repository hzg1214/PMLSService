package cn.com.eju.deal.scene.inCommission.model;

import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class SceneInCommissionSearchResult  {
	
	private String id;
    // 楼盘ID
    private String estateId;
    // 报备编码
    private String reportId;
    // 城市
    private String cityNo;
    private String cityNoNm;
    // 区域
    private String districtId;
    private String districtNm;
    // 板块
    private String areaId;
    private String areaIdNm;
    // 楼盘类型
    private Integer estateType;
    private String estateTypeNm;
    // 楼盘名
    private String estateNm;
    // 公司名称
    private String companyNm;
    // 部门名
    private String deptNm;
    // 员工名
    private String empNm;
    // 客户编号
    private String customerId;
    // 客户名
    private String customerNm;
    // 客户电话
    private String customerTel;
    // 最新进度
    private Integer latestProgress;
    private String latestProgressNm;
    // 确认状态
    private Integer confirmStatus;
    private String confirmStatusNm;
    // 报备日
    private String reportDate;
    // 跟进日
    private String followDate;
    // 有效期
    private String validDate;
    // 当前页
    private String curpage;
    // 总业绩
    private String totalPerformance;
    // 带看奖励
    private String relationReward;
    // 认筹奖励
    private String pledgedReward;
    // 认购奖励
    private String subscribedReward;
    // 成交奖励
    private String bargainReward;
    // 佣金
    private String commission;
    // 隐号报备
    private String hideNumberFlg;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityNoNm() {
		return cityNoNm;
	}
	public void setCityNoNm(String cityNoNm) {
		this.cityNoNm = cityNoNm;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDistrictNm() {
		return districtNm;
	}
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getAreaIdNm() {
		return areaIdNm;
	}
	public void setAreaIdNm(String areaIdNm) {
		this.areaIdNm = areaIdNm;
	}
	public Integer getEstateType() {
		return estateType;
	}
	public void setEstateType(Integer estateType) {
		this.estateType = estateType;
	}
	public String getEstateTypeNm() {
		return estateTypeNm;
	}
	public void setEstateTypeNm(String estateTypeNm) {
		this.estateTypeNm = estateTypeNm;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
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
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	public Integer getLatestProgress() {
		return latestProgress;
	}
	public void setLatestProgress(Integer latestProgress) {
		this.latestProgress = latestProgress;
	}
	public String getLatestProgressNm() {
		return latestProgressNm;
	}
	public void setLatestProgressNm(String latestProgressNm) {
		this.latestProgressNm = latestProgressNm;
	}
	public Integer getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getConfirmStatusNm() {
		return confirmStatusNm;
	}
	public void setConfirmStatusNm(String confirmStatusNm) {
		this.confirmStatusNm = confirmStatusNm;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getCurpage() {
		return curpage;
	}
	public void setCurpage(String curpage) {
		this.curpage = curpage;
	}
	public String getTotalPerformance() {
		return totalPerformance;
	}
	public void setTotalPerformance(String totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public String getRelationReward() {
		return relationReward;
	}
	public void setRelationReward(String relationReward) {
		this.relationReward = relationReward;
	}
	public String getPledgedReward() {
		return pledgedReward;
	}
	public void setPledgedReward(String pledgedReward) {
		this.pledgedReward = pledgedReward;
	}
	public String getSubscribedReward() {
		return subscribedReward;
	}
	public void setSubscribedReward(String subscribedReward) {
		this.subscribedReward = subscribedReward;
	}
	public String getBargainReward() {
		return bargainReward;
	}
	public void setBargainReward(String bargainReward) {
		this.bargainReward = bargainReward;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getHideNumberFlg() {
		return hideNumberFlg;
	}
	public void setHideNumberFlg(String hideNumberFlg) {
		this.hideNumberFlg = hideNumberFlg;
	}
    
    
}