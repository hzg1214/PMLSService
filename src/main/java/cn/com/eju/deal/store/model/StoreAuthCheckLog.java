package cn.com.eju.deal.store.model;

import java.util.Date;

public class StoreAuthCheckLog {
    private Integer id;
    private Integer authCheckId;
    private Date operDate;
    private String operDesc;

    private Date dateCreate;
    private Integer userCreate;
    private Date dateUpdate;
    private Integer userUpdate;
    private Integer delFlag;

    private String userCreateNm;
    private String operDateStr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAuthCheckId() {
        return authCheckId;
    }

    public void setAuthCheckId(Integer authCheckId) {
        this.authCheckId = authCheckId;
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public String getOperDesc() {
        return operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc;
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

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUserCreateNm() {
        return userCreateNm;
    }

    public void setUserCreateNm(String userCreateNm) {
        this.userCreateNm = userCreateNm;
    }

    public String getOperDateStr() {
        return operDateStr;
    }

    public void setOperDateStr(String operDateStr) {
        this.operDateStr = operDateStr;
    }
}
