package cn.com.eju.deal.Report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 结佣
 */

public class BrokerageDto implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 721410615233276203L;
	
	private BigDecimal preamount;
	private BigDecimal afteramount;
	private String ym;
	private String amountType;
	private Date opDt;
	private String userName;
	private Long opEmpId;
	
	//成销详情begin-pmls
	private String reportId;
    private BigDecimal yjsrBeforeAmount;//应计收入税前金额
	private BigDecimal yjsrAfterAmount;//应计税后金额
    //结佣详情 end
	private BigDecimal yjfyBeforeAmount;//应计返佣税前金额
	private BigDecimal yjfyAfterAmount;//应计返佣税后金额
	
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public BigDecimal getYjsrBeforeAmount() {
		return yjsrBeforeAmount;
	}
	public void setYjsrBeforeAmount(BigDecimal yjsrBeforeAmount) {
		this.yjsrBeforeAmount = yjsrBeforeAmount;
	}
	public BigDecimal getYjsrAfterAmount() {
		return yjsrAfterAmount;
	}
	public void setYjsrAfterAmount(BigDecimal yjsrAfterAmount) {
		this.yjsrAfterAmount = yjsrAfterAmount;
	}
	public BigDecimal getYjfyBeforeAmount() {
		return yjfyBeforeAmount;
	}
	public void setYjfyBeforeAmount(BigDecimal yjfyBeforeAmount) {
		this.yjfyBeforeAmount = yjfyBeforeAmount;
	}
	public BigDecimal getYjfyAfterAmount() {
		return yjfyAfterAmount;
	}
	public void setYjfyAfterAmount(BigDecimal yjfyAfterAmount) {
		this.yjfyAfterAmount = yjfyAfterAmount;
	}
	public BigDecimal getPreamount() {
		return preamount;
	}
	public void setPreamount(BigDecimal preamount) {
		this.preamount = preamount;
	}
	public BigDecimal getAfteramount() {
		return afteramount;
	}
	public void setAfteramount(BigDecimal afteramount) {
		this.afteramount = afteramount;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
	public String getAmountType() {
		return amountType;
	}
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	public Date getOpDt() {
		return opDt;
	}
	public void setOpDt(Date opDt) {
		this.opDt = opDt;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getOpEmpId() {
		return opEmpId;
	}
	public void setOpEmpId(Long opEmpId) {
		this.opEmpId = opEmpId;
	}
}
