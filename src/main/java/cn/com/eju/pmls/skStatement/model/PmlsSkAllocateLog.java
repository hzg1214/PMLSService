package cn.com.eju.pmls.skStatement.model;

import java.util.Date;

public class PmlsSkAllocateLog {
    private Integer id;

    private String skSerialNo;

    private String logMsg;

    private Boolean delFlag;

    private Date dateCreate;

    private Integer userIdCreate;

    private Date dateUpdate;

    private Integer userIdUpdate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSkSerialNo() {
        return skSerialNo;
    }

    public void setSkSerialNo(String skSerialNo) {
        this.skSerialNo = skSerialNo == null ? null : skSerialNo.trim();
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg == null ? null : logMsg.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getUserIdUpdate() {
        return userIdUpdate;
    }

    public void setUserIdUpdate(Integer userIdUpdate) {
        this.userIdUpdate = userIdUpdate;
    }
}