package cn.com.eju.deal.otherReport.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class LnkYjQtReport implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer detailId;

    private Integer reportId;

    private String companyNo;

    private String companyName;

    private BigDecimal yjfybefTaxAmount;

    private BigDecimal yjfyaftTaxAmount;

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

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getYjfybefTaxAmount() {
        return yjfybefTaxAmount;
    }

    public void setYjfybefTaxAmount(BigDecimal yjfybefTaxAmount) {
        this.yjfybefTaxAmount = yjfybefTaxAmount;
    }

    public BigDecimal getYjfyaftTaxAmount() {
        return yjfyaftTaxAmount;
    }

    public void setYjfyaftTaxAmount(BigDecimal yjfyaftTaxAmount) {
        this.yjfyaftTaxAmount = yjfyaftTaxAmount;
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