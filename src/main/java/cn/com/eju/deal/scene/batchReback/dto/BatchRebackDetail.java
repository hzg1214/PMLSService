package cn.com.eju.deal.scene.batchReback.dto;

import java.math.BigDecimal;
import java.util.Date;

public class BatchRebackDetail {
    private Integer batchDetailId;
    private Integer batchId;//批量成销主表id
    private String reportId;//报备编号
    private String floor;//楼室号
    private String customerName1;//客户姓名1
    private String customerPhone1;//客户手机号1
    private String customerName2;//客户姓名2
    private String customerPhone2;//客户手机号2
    private BigDecimal saleArea;//成销面积
    private BigDecimal saleAmount;//成交金额
    private String accountProject;
    private String accountProjectNo;
    private BigDecimal befYjsrTaxAmount;
    private BigDecimal aftYjsrTaxAmount;
    private BigDecimal befYssrTaxAmount;
    private BigDecimal aftYssrTaxAmount;
//    private BigDecimal befYjdyTaxAmount;
//    private BigDecimal aftYjdyTaxAmount;
    private Date rebackDate;
    private Boolean delFlag;//是否删除
    private String empCode;//操作人code
    private String empName;//操作人姓名
    private Date empDate;//操作时间

    public String getAccountProjectNo() {
        return accountProjectNo;
    }

    public void setAccountProjectNo(String accountProjectNo) {
        this.accountProjectNo = accountProjectNo;
    }

    public String getAccountProject() {
        return accountProject;
    }

    public void setAccountProject(String accountProject) {
        this.accountProject = accountProject;
    }

    /*public BigDecimal getBefYjdyTaxAmount() {
        return befYjdyTaxAmount;
    }

    public void setBefYjdyTaxAmount(BigDecimal befYjdyTaxAmount) {
        this.befYjdyTaxAmount = befYjdyTaxAmount;
    }

    public BigDecimal getAftYjdyTaxAmount() {
        return aftYjdyTaxAmount;
    }

    public void setAftYjdyTaxAmount(BigDecimal aftYjdyTaxAmount) {
        this.aftYjdyTaxAmount = aftYjdyTaxAmount;
    }*/

    public Integer getBatchDetailId() {
        return batchDetailId;
    }

    public void setBatchDetailId(Integer batchDetailId) {
        this.batchDetailId = batchDetailId;
    }

    public Integer getBatchId() {
        return batchId;
    }

    public void setBatchId(Integer batchId) {
        this.batchId = batchId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getCustomerName1() {
        return customerName1;
    }

    public void setCustomerName1(String customerName1) {
        this.customerName1 = customerName1;
    }

    public String getCustomerPhone1() {
        return customerPhone1;
    }

    public void setCustomerPhone1(String customerPhone1) {
        this.customerPhone1 = customerPhone1;
    }

    public String getCustomerName2() {
        return customerName2;
    }

    public void setCustomerName2(String customerName2) {
        this.customerName2 = customerName2;
    }

    public String getCustomerPhone2() {
        return customerPhone2;
    }

    public void setCustomerPhone2(String customerPhone2) {
        this.customerPhone2 = customerPhone2;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public Date getEmpDate() {
        return empDate;
    }

    public void setEmpDate(Date empDate) {
        this.empDate = empDate;
    }

    public BigDecimal getBefYjsrTaxAmount() {
        return befYjsrTaxAmount;
    }

    public void setBefYjsrTaxAmount(BigDecimal befYjsrTaxAmount) {
        this.befYjsrTaxAmount = befYjsrTaxAmount;
    }

    public BigDecimal getAftYjsrTaxAmount() {
        return aftYjsrTaxAmount;
    }

    public void setAftYjsrTaxAmount(BigDecimal aftYjsrTaxAmount) {
        this.aftYjsrTaxAmount = aftYjsrTaxAmount;
    }

    public BigDecimal getBefYssrTaxAmount() {
        return befYssrTaxAmount;
    }

    public void setBefYssrTaxAmount(BigDecimal befYssrTaxAmount) {
        this.befYssrTaxAmount = befYssrTaxAmount;
    }

    public BigDecimal getAftYssrTaxAmount() {
        return aftYssrTaxAmount;
    }

    public void setAftYssrTaxAmount(BigDecimal aftYssrTaxAmount) {
        this.aftYssrTaxAmount = aftYssrTaxAmount;
    }

    public BigDecimal getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(BigDecimal saleArea) {
        this.saleArea = saleArea;
    }

    public Date getRebackDate() {
        return rebackDate;
    }

    public void setRebackDate(Date rebackDate) {
        this.rebackDate = rebackDate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}
