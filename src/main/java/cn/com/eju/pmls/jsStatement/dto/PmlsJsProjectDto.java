package cn.com.eju.pmls.jsStatement.dto;

import java.math.BigDecimal;
import java.util.Date;

public class PmlsJsProjectDto {
    private String projectNo;
    private String projectName;
    private Date signDate;

    private Integer isSpecialProject;
    private BigDecimal pro_vaildDYAmount;
    private BigDecimal pro_vaildFYAmount;
    private BigDecimal com_vaildDYAmount;
    private BigDecimal com_vaildFYAmount;

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Integer getIsSpecialProject() {
        return isSpecialProject;
    }

    public void setIsSpecialProject(Integer isSpecialProject) {
        this.isSpecialProject = isSpecialProject;
    }

    public BigDecimal getPro_vaildDYAmount() {
        return pro_vaildDYAmount;
    }

    public void setPro_vaildDYAmount(BigDecimal pro_vaildDYAmount) {
        this.pro_vaildDYAmount = pro_vaildDYAmount;
    }

    public BigDecimal getPro_vaildFYAmount() {
        return pro_vaildFYAmount;
    }

    public void setPro_vaildFYAmount(BigDecimal pro_vaildFYAmount) {
        this.pro_vaildFYAmount = pro_vaildFYAmount;
    }

    public BigDecimal getCom_vaildDYAmount() {
        return com_vaildDYAmount;
    }

    public void setCom_vaildDYAmount(BigDecimal com_vaildDYAmount) {
        this.com_vaildDYAmount = com_vaildDYAmount;
    }

    public BigDecimal getCom_vaildFYAmount() {
        return com_vaildFYAmount;
    }

    public void setCom_vaildFYAmount(BigDecimal com_vaildFYAmount) {
        this.com_vaildFYAmount = com_vaildFYAmount;
    }
}
