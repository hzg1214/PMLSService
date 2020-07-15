package cn.com.eju.deal.cashbill.model;

import java.math.BigDecimal;
import java.util.Date;

public class OaCashBillReportReturn {
    private Integer id;

    private Integer oaRetId;

    private String oaNo;

    private String reportNo;

    private BigDecimal befTaxAmount;

    private BigDecimal taxAmount;

    private String description;

    private Date dateCreate;

    private Date dateUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOaRetId() {
        return oaRetId;
    }

    public void setOaRetId(Integer oaRetId) {
        this.oaRetId = oaRetId;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo == null ? null : oaNo.trim();
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
    }

    public BigDecimal getBefTaxAmount() {
        return befTaxAmount;
    }

    public void setBefTaxAmount(BigDecimal befTaxAmount) {
        this.befTaxAmount = befTaxAmount;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }
}