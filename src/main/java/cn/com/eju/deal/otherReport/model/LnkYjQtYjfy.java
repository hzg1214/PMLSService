package cn.com.eju.deal.otherReport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LnkYjQtYjfy implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer reportId;

    private String companyNo;

    private Integer num;

    private BigDecimal befTaxAmount;

    private BigDecimal aftTaxAmount;

    private Date recordDate;

    private Boolean delFlg;

    private Date crtDt;

    private Integer crtEmpId;

    private Integer uptEmpId;

    private Date uptDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getBefTaxAmount() {
        return befTaxAmount;
    }

    public void setBefTaxAmount(BigDecimal befTaxAmount) {
        this.befTaxAmount = befTaxAmount;
    }

    public BigDecimal getAftTaxAmount() {
        return aftTaxAmount;
    }

    public void setAftTaxAmount(BigDecimal aftTaxAmount) {
        this.aftTaxAmount = aftTaxAmount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Boolean getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Boolean delFlg) {
        this.delFlg = delFlg;
    }

    public Date getCrtDt() {
        return crtDt;
    }

    public void setCrtDt(Date crtDt) {
        this.crtDt = crtDt;
    }

    public Integer getCrtEmpId() {
        return crtEmpId;
    }

    public void setCrtEmpId(Integer crtEmpId) {
        this.crtEmpId = crtEmpId;
    }

    public Integer getUptEmpId() {
        return uptEmpId;
    }

    public void setUptEmpId(Integer uptEmpId) {
        this.uptEmpId = uptEmpId;
    }

    public Date getUptDt() {
        return uptDt;
    }

    public void setUptDt(Date uptDt) {
        this.uptDt = uptDt;
    }
}