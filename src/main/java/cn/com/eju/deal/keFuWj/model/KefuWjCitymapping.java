package cn.com.eju.deal.keFuWj.model;

import java.util.Date;

public class KefuWjCitymapping {
    private Integer id;

    private String cityNo;

    private String wjCode;

    private String enbledFlag;

    private Integer userCreate;

    private Date dateCreate;

    private Boolean delFlag;

    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getWjCode() {
        return wjCode;
    }

    public void setWjCode(String wjCode) {
        this.wjCode = wjCode == null ? null : wjCode.trim();
    }

    public String getEnbledFlag() {
        return enbledFlag;
    }

    public void setEnbledFlag(String enbledFlag) {
        this.enbledFlag = enbledFlag == null ? null : enbledFlag.trim();
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }
}