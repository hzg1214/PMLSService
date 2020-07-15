package cn.com.eju.deal.dto.scene.commission;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haidan on 2018/11/28.
 */
public class ReportdyDto {
    private Integer id;
    private String reportId;
    private BigDecimal befTaxYjAmount;
    private BigDecimal aftTaxYjAmount;
    private BigDecimal befTaxSjAmount;
    private BigDecimal aftTaxSjAmount;
    private Date recordDate;
    private Boolean delFlag;
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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public BigDecimal getBefTaxYjAmount() {
        return befTaxYjAmount;
    }

    public void setBefTaxYjAmount(BigDecimal befTaxYjAmount) {
        this.befTaxYjAmount = befTaxYjAmount;
    }

    public BigDecimal getAftTaxYjAmount() {
        return aftTaxYjAmount;
    }

    public void setAftTaxYjAmount(BigDecimal aftTaxYjAmount) {
        this.aftTaxYjAmount = aftTaxYjAmount;
    }

    public BigDecimal getBefTaxSjAmount() {
        return befTaxSjAmount;
    }

    public void setBefTaxSjAmount(BigDecimal befTaxSjAmount) {
        this.befTaxSjAmount = befTaxSjAmount;
    }

    public BigDecimal getAftTaxSjAmount() {
        return aftTaxSjAmount;
    }

    public void setAftTaxSjAmount(BigDecimal aftTaxSjAmount) {
        this.aftTaxSjAmount = aftTaxSjAmount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
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
