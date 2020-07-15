package cn.com.eju.pmls.jsStatement.dto;

import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementDtl;

import java.math.BigDecimal;
import java.util.List;

public class PmlsJsStatementDtlBo extends PmlsJsReportDto {

    private Integer id;

    private String serviceFeeDes;

    private BigDecimal contractYdAmount;

    private BigDecimal jsAmount;

    private BigDecimal kpAmount;

    private BigDecimal kpTaxAmount;

    private Integer jsStatementType;

    private List<PmlsJsHSCodeDto> hsCodeList;

    private String hsCode;

    private String hsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceFeeDes() {
        return serviceFeeDes;
    }

    public void setServiceFeeDes(String serviceFeeDes) {
        this.serviceFeeDes = serviceFeeDes;
    }

    public BigDecimal getContractYdAmount() {
        return contractYdAmount;
    }

    public void setContractYdAmount(BigDecimal contractYdAmount) {
        this.contractYdAmount = contractYdAmount;
    }

    public BigDecimal getJsAmount() {
        return jsAmount;
    }

    public void setJsAmount(BigDecimal jsAmount) {
        this.jsAmount = jsAmount;
    }

    public BigDecimal getKpAmount() {
        return kpAmount;
    }

    public void setKpAmount(BigDecimal kpAmount) {
        this.kpAmount = kpAmount;
    }

    public BigDecimal getKpTaxAmount() {
        return kpTaxAmount;
    }

    public void setKpTaxAmount(BigDecimal kpTaxAmount) {
        this.kpTaxAmount = kpTaxAmount;
    }

    public Integer getJsStatementType() {
        return jsStatementType;
    }

    public void setJsStatementType(Integer jsStatementType) {
        this.jsStatementType = jsStatementType;
    }

    public List<PmlsJsHSCodeDto> getHsCodeList() {
        return hsCodeList;
    }

    public void setHsCodeList(List<PmlsJsHSCodeDto> hsCodeList) {
        this.hsCodeList = hsCodeList;
    }

    public String getHsCode() {
        return hsCode;
    }

    public void setHsCode(String hsCode) {
        this.hsCode = hsCode;
    }

    public String getHsName() {
        return hsName;
    }

    public void setHsName(String hsName) {
        this.hsName = hsName;
    }
}
