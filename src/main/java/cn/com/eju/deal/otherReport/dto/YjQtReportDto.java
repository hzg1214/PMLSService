package cn.com.eju.deal.otherReport.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.otherReport.model.LnkYjQtReport;

/**
 * desc:返佣对象  dto
 * @author :zhenggang.Huang
 * @date   :2019年10月15日
 */
public class YjQtReportDto extends LnkYjQtReport implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1274881265870207957L;
	
	private BigDecimal yjbefTaxAmount ;//应计返佣税前
	private BigDecimal yjaftTaxAmount ;//应计返佣税后
	private BigDecimal sjbefTaxAmount;//实际返佣税前
	private BigDecimal sjaftTaxAmount;//实际返佣税后
	private Date OperDetailDate ;//结佣操作日期
	public BigDecimal getYjbefTaxAmount() {
		return yjbefTaxAmount;
	}
	public void setYjbefTaxAmount(BigDecimal yjbefTaxAmount) {
		this.yjbefTaxAmount = yjbefTaxAmount;
	}
	public BigDecimal getYjaftTaxAmount() {
		return yjaftTaxAmount;
	}
	public void setYjaftTaxAmount(BigDecimal yjaftTaxAmount) {
		this.yjaftTaxAmount = yjaftTaxAmount;
	}
	public BigDecimal getSjbefTaxAmount() {
		return sjbefTaxAmount;
	}
	public void setSjbefTaxAmount(BigDecimal sjbefTaxAmount) {
		this.sjbefTaxAmount = sjbefTaxAmount;
	}
	public BigDecimal getSjaftTaxAmount() {
		return sjaftTaxAmount;
	}
	public void setSjaftTaxAmount(BigDecimal sjaftTaxAmount) {
		this.sjaftTaxAmount = sjaftTaxAmount;
	}
	public Date getOperDetailDate() {
		return OperDetailDate;
	}
	public void setOperDetailDate(Date operDetailDate) {
		OperDetailDate = operDetailDate;
	}
}