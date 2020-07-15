package cn.com.eju.deal.scene.commission.model;

import java.util.Date;
/**
 * 
 * desc:返佣对象维护记录
 * @author :zhenggang.Huang
 * @date   :2019年4月30日
 */
public class LnkYjReportLog {
	private Integer id;
    private String reportId;//报备编号
    private String companyNo;//公司编号
    private String content;//变更内容
    private String delFlag;
    private Date crtDt;
    private Date uptDt;
    private Integer crtEmpId;//维护人
    
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
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
	public Date getUptDt() {
		return uptDt;
	}
	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}
	public Integer getCrtEmpId() {
		return crtEmpId;
	}
	public void setCrtEmpId(Integer crtEmpId) {
		this.crtEmpId = crtEmpId;
	}
    
}
