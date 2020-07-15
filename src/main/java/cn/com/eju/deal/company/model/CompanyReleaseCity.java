package cn.com.eju.deal.company.model;

import java.util.Date;

public class CompanyReleaseCity {
    private Integer id;

    private Integer companyId;

    private String releaseCityNo;

    private String releaseCityName;

    private Integer releaseCenterId;

    private String releaseCenterName;

    private Boolean isDelete;

    private Date dateCreate;

    private Integer userCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getReleaseCityNo() {
        return releaseCityNo;
    }

    public void setReleaseCityNo(String releaseCityNo) {
        this.releaseCityNo = releaseCityNo == null ? null : releaseCityNo.trim();
    }

    public Integer getReleaseCenterId() {
        return releaseCenterId;
    }

    public void setReleaseCenterId(Integer releaseCenterId) {
        this.releaseCenterId = releaseCenterId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }


    public String getReleaseCityName() {
        return releaseCityName;
    }

    public void setReleaseCityName(String releaseCityName) {
        this.releaseCityName = releaseCityName;
    }

    public String getReleaseCenterName() {
        return releaseCenterName;
    }

    public void setReleaseCenterName(String releaseCenterName) {
        this.releaseCenterName = releaseCenterName;
    }


}