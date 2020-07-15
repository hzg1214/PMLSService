package cn.com.eju.deal.scene.batchSale.dto;

import java.math.BigDecimal;

public class ReportDetailDto {
    private Integer id;
    private String estateNm;
    private String reportId;
    private String projectNo;
    private String estateId;
    private String customerId;
    private String customerNm;
    private String customerTel;
    private String buildingNo;
    private BigDecimal roughArea;
    private BigDecimal roughAmount;
    private BigDecimal dealAmount;
    private String customerIdTwo;
    private String customerNmTwo;
    private String customerTelTwo;
    private String estateCityNo;
    private BigDecimal befYjsrTaxAmount;
    private BigDecimal aftYjsrTaxAmount;
    private BigDecimal befYjfyTaxAmount;
    private BigDecimal aftYjfyTaxAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstateNm() {
        return estateNm;
    }

    public void setEstateNm(String estateNm) {
        this.estateNm = estateNm;
    }


    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }



    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public BigDecimal getRoughArea() {
        return roughArea;
    }

    public void setRoughArea(BigDecimal roughArea) {
        this.roughArea = roughArea;
    }

    public BigDecimal getRoughAmount() {
        return roughAmount;
    }

    public void setRoughAmount(BigDecimal roughAmount) {
        this.roughAmount = roughAmount;
    }

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }



    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }


    public String getCustomerIdTwo() {
        return customerIdTwo;
    }

    public void setCustomerIdTwo(String customerIdTwo) {
        this.customerIdTwo = customerIdTwo;
    }

    public String getCustomerNmTwo() {
        return customerNmTwo;
    }

    public void setCustomerNmTwo(String customerNmTwo) {
        this.customerNmTwo = customerNmTwo;
    }

    public String getCustomerTelTwo() {
        return customerTelTwo;
    }

    public void setCustomerTelTwo(String customerTelTwo) {
        this.customerTelTwo = customerTelTwo;
    }


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getEstateCityNo() {
        return estateCityNo;
    }

    public void setEstateCityNo(String estateCityNo) {
        this.estateCityNo = estateCityNo;
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

    public BigDecimal getBefYjfyTaxAmount() {
        return befYjfyTaxAmount;
    }

    public void setBefYjfyTaxAmount(BigDecimal befYjfyTaxAmount) {
        this.befYjfyTaxAmount = befYjfyTaxAmount;
    }

    public BigDecimal getAftYjfyTaxAmount() {
        return aftYjfyTaxAmount;
    }

    public void setAftYjfyTaxAmount(BigDecimal aftYjfyTaxAmount) {
        this.aftYjfyTaxAmount = aftYjfyTaxAmount;
    }
}
