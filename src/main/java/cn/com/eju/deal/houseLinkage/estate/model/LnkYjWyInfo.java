package cn.com.eju.deal.houseLinkage.estate.model;

import java.util.Date;

public class LnkYjWyInfo {
    private Integer id;

    private String wyTypeCode;

    private String wyTypName;

    private Date createDate;

    private Integer userIdCreate;

    private Boolean delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWyTypeCode() {
        return wyTypeCode;
    }

    public void setWyTypeCode(String wyTypeCode) {
        this.wyTypeCode = wyTypeCode == null ? null : wyTypeCode.trim();
    }

    public String getWyTypName() {
        return wyTypName;
    }

    public void setWyTypName(String wyTypName) {
        this.wyTypName = wyTypName == null ? null : wyTypName.trim();
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

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}