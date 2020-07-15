package cn.com.eju.pmls.jsStatement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class PmlsJsReportDto {

    private String reportId;
    private String buildingNo;
    private String customerName;
    private String customerTel;
    private String projectCode;
    private String projectName;
    private Integer isGlobalControl;
    private BigDecimal cxArea;
    private BigDecimal cxAmount;
    private BigDecimal vaildDYAmount;
    private BigDecimal vaildFYAmount;
    private BigDecimal oldJsTotalAmount;

    private Integer isSpecialProject;
    private String accountProject;
    private String accountProjectNo;
    private String progress;
    private String confirmStatus;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date dealDate;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getIsGlobalControl() {
        return isGlobalControl;
    }

    public void setIsGlobalControl(Integer isGlobalControl) {
        this.isGlobalControl = isGlobalControl;
    }

    public BigDecimal getCxArea() {
        return cxArea;
    }

    public void setCxArea(BigDecimal cxArea) {
        this.cxArea = cxArea;
    }

    public BigDecimal getCxAmount() {
        return cxAmount;
    }

    public void setCxAmount(BigDecimal cxAmount) {
        this.cxAmount = cxAmount;
    }

    public BigDecimal getVaildDYAmount() {
        return vaildDYAmount;
    }

    public void setVaildDYAmount(BigDecimal vaildDYAmount) {
        this.vaildDYAmount = vaildDYAmount;
    }

    public BigDecimal getVaildFYAmount() {
        return vaildFYAmount;
    }

    public void setVaildFYAmount(BigDecimal vaildFYAmount) {
        this.vaildFYAmount = vaildFYAmount;
    }

    public BigDecimal getOldJsTotalAmount() {
        return oldJsTotalAmount;
    }

    public void setOldJsTotalAmount(BigDecimal oldJsTotalAmount) {
        this.oldJsTotalAmount = oldJsTotalAmount;
    }

    public Integer getIsSpecialProject() {
        return isSpecialProject;
    }

    public void setIsSpecialProject(Integer isSpecialProject) {
        this.isSpecialProject = isSpecialProject;
    }

    public String getAccountProject() {
        return accountProject;
    }

    public void setAccountProject(String accountProject) {
        this.accountProject = accountProject;
    }

    public String getAccountProjectNo() {
        return accountProjectNo;
    }

    public void setAccountProjectNo(String accountProjectNo) {
        this.accountProjectNo = accountProjectNo;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
    }
}
