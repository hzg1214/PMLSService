package cn.com.eju.deal.Report.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 带看
 */
public class ApproShowDto implements Serializable{

	private static final long serialVersionUID = -1938631351666246339L;
	private String projectNo;//项目编号
    private String reportId;//报备编号
    private Date seeDate;//带看日期
    private List<String> fileIdList;
    private String customerNm;//客户姓名
    private String customerTel;//客户手机
	private String customerNmTwo;//客户名2
	private String customerTelTwo;//客户电话2
	private String opUserCode;//操作人
    
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public Date getSeeDate() {
		return seeDate;
	}
	public void setSeeDate(Date seeDate) {
		this.seeDate = seeDate;
	}
	public List<String> getFileIdList() {
		return fileIdList;
	}
	public void setFileIdList(List<String> fileIdList) {
		this.fileIdList = fileIdList;
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
	public String getCustomerNmTwo() {
		return customerNmTwo;
	}
	public void setCustomerNmTwo(String customerNmTwo) {
		this.customerNmTwo = customerNmTwo;
	}
	public String getCustomerTelTwo() {
		return customerTelTwo;
	}
	public void setCustomerTelTwo(String customerTelTwo) {
		this.customerTelTwo = customerTelTwo;
	}
	public String getOpUserCode() {
		return opUserCode;
	}
	public void setOpUserCode(String opUserCode) {
		this.opUserCode = opUserCode;
	}
}
