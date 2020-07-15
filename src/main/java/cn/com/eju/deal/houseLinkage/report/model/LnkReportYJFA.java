package cn.com.eju.deal.houseLinkage.report.model;

import java.util.Date;

public class LnkReportYJFA {
    private Integer id;

    private String reportId;

    private String companyNo;

    private Integer programmeId;

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

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo == null ? null : companyNo.trim();
    }

    public Integer getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Integer programmeId) {
        this.programmeId = programmeId;
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