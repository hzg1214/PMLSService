package cn.com.eju.deal.scene.commission.model;

import java.util.Date;

/**
 * 
 * desc:佣金管理对象  查看返回数据-返回返佣对象
 * @author :zhenggang.Huang
 * @date   :2019年4月30日
 */
public class CompanyMatin {
	private String reportId;
	private Date crtDt;
    private String companyName;
    private String companyNo;
    private String userName;
    private String content;
    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
}
