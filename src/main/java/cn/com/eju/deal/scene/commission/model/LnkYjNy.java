package cn.com.eju.deal.scene.commission.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 佣金-内佣
 */
public class LnkYjNy {
    private Integer id;
    private Integer reportId;
    private String companyNo;
    private Integer num;
    private BigDecimal postAmount;
    private BigDecimal totalAmount;
    private Date recordDate;
    private Integer detailId;
    private Integer delFlg;
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

    public BigDecimal getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(BigDecimal postAmount) {
        this.postAmount = postAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getDelFlg() {
        return delFlg;
    }

    public void setDelFlg(Integer delFlg) {
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
