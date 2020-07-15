package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class APIReportDetailResultDto  implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	// 楼盘ID
	private String estateId;
	// 楼盘ID
	private String estateNm;
	// 门店名
	private String storeNm;
	// 部门名
	private String deptNm;
	// 公司名称
	private String companyNm;
	// 客户名
	private String customerNm;
	// 客户电话
	private String customerTel;
	// 员工名
	private String empNm;
	// 最新进度
	private String latestProgress;
	private String latestProgressDis;
	// 结佣进度
	private String commissionProgress;
	// 最新确认状态
	private String latestConfirmStatus;
	private String latestConfirmStatusDis;
	// 最新跟进日
	private String latestFollowDate;

	// 客户人数
	private String customerNum;
    // 带看时间
	private String validRelationDate;
    // 客户需求
	private String customerRequire;
    // 备注
	private String memo;
	
	
	// 报备跟进日
	private String latestReportFollowDate;
	// 带看跟进日
	private String latestRelationFollowDate;
	// 认筹跟进日
	private String latestPledgedFollowDate;
	// 认购跟进日
	private String latestSubscribedFollowDate;
	// 成交跟进日
	private String latestBargainFollowDate;
	// 退筹跟进日
	private String latestBackFollowDate;
	// 结佣跟进日
	private String latestCommissionFollowDate;
	private String latestCommissionFollowDate2;
	// 报备详情
	private List progress = new ArrayList();
	// 进度
	private String progressStatus;
	// 确认状态
	private String confirmStatus;
	// 详情
	private String reportMemo;
	// 日期
	private String followDate;
	
	//报备时间
	private String reportDate;
	
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
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
	public String getStoreNm() {
		return storeNm;
	}
	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
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
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public String getLatestProgress() {
		return latestProgress;
	}
	public void setLatestProgress(String latestProgress) {
		this.latestProgress = latestProgress;
	}
	public String getLatestProgressDis() {
		return latestProgressDis;
	}
	public void setLatestProgressDis(String latestProgressDis) {
		this.latestProgressDis = latestProgressDis;
	}
	public String getCommissionProgress() {
		return commissionProgress;
	}
	public void setCommissionProgress(String commissionProgress) {
		this.commissionProgress = commissionProgress;
	}
	public String getLatestConfirmStatus() {
		return latestConfirmStatus;
	}
	public void setLatestConfirmStatus(String latestConfirmStatus) {
		this.latestConfirmStatus = latestConfirmStatus;
	}
	public String getLatestConfirmStatusDis() {
		return latestConfirmStatusDis;
	}
	public void setLatestConfirmStatusDis(String latestConfirmStatusDis) {
		this.latestConfirmStatusDis = latestConfirmStatusDis;
	}
	public String getLatestFollowDate() {
		return latestFollowDate;
	}
	public void setLatestFollowDate(String latestFollowDate) {
		this.latestFollowDate = latestFollowDate;
	}
	public String getLatestReportFollowDate() {
		return latestReportFollowDate;
	}
	public void setLatestReportFollowDate(String latestReportFollowDate) {
		this.latestReportFollowDate = latestReportFollowDate;
	}
	public String getLatestRelationFollowDate() {
		return latestRelationFollowDate;
	}
	public void setLatestRelationFollowDate(String latestRelationFollowDate) {
		this.latestRelationFollowDate = latestRelationFollowDate;
	}
	public String getLatestPledgedFollowDate() {
		return latestPledgedFollowDate;
	}
	public void setLatestPledgedFollowDate(String latestPledgedFollowDate) {
		this.latestPledgedFollowDate = latestPledgedFollowDate;
	}
	public String getLatestSubscribedFollowDate() {
		return latestSubscribedFollowDate;
	}
	public void setLatestSubscribedFollowDate(String latestSubscribedFollowDate) {
		this.latestSubscribedFollowDate = latestSubscribedFollowDate;
	}
	public String getLatestBargainFollowDate() {
		return latestBargainFollowDate;
	}
	public void setLatestBargainFollowDate(String latestBargainFollowDate) {
		this.latestBargainFollowDate = latestBargainFollowDate;
	}
	public String getLatestBackFollowDate() {
		return latestBackFollowDate;
	}
	public void setLatestBackFollowDate(String latestBackFollowDate) {
		this.latestBackFollowDate = latestBackFollowDate;
	}
	public String getLatestCommissionFollowDate() {
		return latestCommissionFollowDate;
	}
	public void setLatestCommissionFollowDate(String latestCommissionFollowDate) {
		this.latestCommissionFollowDate = latestCommissionFollowDate;
	}
	public String getLatestCommissionFollowDate2() {
		return latestCommissionFollowDate2;
	}
	public void setLatestCommissionFollowDate2(String latestCommissionFollowDate2) {
		this.latestCommissionFollowDate2 = latestCommissionFollowDate2;
	}
	public List getProgress() {
		return progress;
	}
	public void setProgress(List progress) {
		this.progress = progress;
	}
	public String getProgressStatus() {
		return progressStatus;
	}
	public void setProgressStatus(String progressStatus) {
		this.progressStatus = progressStatus;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getReportMemo() {
		return reportMemo;
	}
	public void setReportMemo(String reportMemo) {
		this.reportMemo = reportMemo;
	}
	public String getFollowDate() {
		return followDate;
	}
	public void setFollowDate(String followDate) {
		this.followDate = followDate;
	}
	public String getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}
	public String getValidRelationDate() {
		return validRelationDate;
	}
	public void setValidRelationDate(String validRelationDate) {
		this.validRelationDate = validRelationDate;
	}
	public String getCustomerRequire() {
		return customerRequire;
	}
	public void setCustomerRequire(String customerRequire) {
		this.customerRequire = customerRequire;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	
	
    
}