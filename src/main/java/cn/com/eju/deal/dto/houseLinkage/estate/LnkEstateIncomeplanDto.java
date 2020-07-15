package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by eju on 2019/12/6.
 */
public class LnkEstateIncomeplanDto implements Serializable {

    private Integer id;

    private String projectNo;

    private String planName;

    private Integer planType;

    private BigDecimal fixAmount;

    private BigDecimal totalPercentage;

    private Boolean isEnable;

    private Date createDate;

    private Integer userIdCreate;

    private Date updateDate;

    private Integer userIdUpdate;

    private Boolean delFlag;

    private String planInfo;

    private String enableType;

    private String userNameCreate;

    private Integer planNum;

    private String planTypeName;

    private BigDecimal sqAmount;

    private Integer EstateId;

    public Integer getEstateId() {
        return EstateId;
    }

    public void setEstateId(Integer estateId) {
        EstateId = estateId;
    }

    public BigDecimal getSqAmount() {
        return sqAmount;
    }

    public void setSqAmount(BigDecimal sqAmount) {
        this.sqAmount = sqAmount;
    }

    public String getPlanTypeName() {
        return planTypeName;
    }

    public void setPlanTypeName(String planTypeName) {
        this.planTypeName = planTypeName;
    }

    public Integer getPlanNum() {
        return planNum;
    }

    public void setPlanNum(Integer planNum) {
        this.planNum = planNum;
    }

    public String getPlanInfo() {
        return planInfo;
    }

    public void setPlanInfo(String planInfo) {
        this.planInfo = planInfo;
    }

    public String getEnableType() {
        return enableType;
    }

    public void setEnableType(String enableType) {
        this.enableType = enableType;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Integer getPlanType() {
        return planType;
    }

    public void setPlanType(Integer planType) {
        this.planType = planType;
    }

    public BigDecimal getFixAmount() {
        return fixAmount;
    }

    public void setFixAmount(BigDecimal fixAmount) {
        this.fixAmount = fixAmount;
    }

    public BigDecimal getTotalPercentage() {
        return totalPercentage;
    }

    public void setTotalPercentage(BigDecimal totalPercentage) {
        this.totalPercentage = totalPercentage;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUserIdUpdate() {
        return userIdUpdate;
    }

    public void setUserIdUpdate(Integer userIdUpdate) {
        this.userIdUpdate = userIdUpdate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

}
