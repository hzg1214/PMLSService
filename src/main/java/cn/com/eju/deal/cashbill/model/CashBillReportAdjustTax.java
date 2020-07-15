package cn.com.eju.deal.cashbill.model;

import java.math.BigDecimal;
import java.util.Date;

public class CashBillReportAdjustTax {
    private Integer id;

    private Integer repParentId;

    private String reportNo;

    private BigDecimal adjustBeforeTaxAmount;

    private BigDecimal adjustTaxAmount;

    private BigDecimal adjustAfterTaxAmount;

    private Integer requestType;

    private Integer hasDeal;

    private Date dateUpdate;

    private Date dateCreate;

    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepParentId() {
        return repParentId;
    }

    public void setRepParentId(Integer repParentId) {
        this.repParentId = repParentId;
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public BigDecimal getAdjustBeforeTaxAmount() {
        return adjustBeforeTaxAmount;
    }

    public void setAdjustBeforeTaxAmount(BigDecimal adjustBeforeTaxAmount) {
        this.adjustBeforeTaxAmount = adjustBeforeTaxAmount;
    }

    public BigDecimal getAdjustTaxAmount() {
        return adjustTaxAmount;
    }

    public void setAdjustTaxAmount(BigDecimal adjustTaxAmount) {
        this.adjustTaxAmount = adjustTaxAmount;
    }

    public BigDecimal getAdjustAfterTaxAmount() {
        return adjustAfterTaxAmount;
    }

    public void setAdjustAfterTaxAmount(BigDecimal adjustAfterTaxAmount) {
        this.adjustAfterTaxAmount = adjustAfterTaxAmount;
    }
    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }
    public Integer getHasDeal() {
        return hasDeal;
    }

    public void setHasDeal(Integer hasDeal) {
        this.hasDeal = hasDeal;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}