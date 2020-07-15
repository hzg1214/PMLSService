package cn.com.eju.deal.store.model;

import java.util.Date;

public class StoreStopCancelLog {

    private Integer id;
    private Integer contractId;
    private Integer renewContractId;
    private Integer storeId;
    private Date statDate;
    private Integer calcFlag;
    private Integer new_calcFlag;
    private Integer sortFlag;
    private String signStatus;
    private String remark;
    private Integer cancelId;

    private Integer userCreate;
    private Date dateCreate;
    private Integer userUpdate;
    private Date dateUpdate;
    private Integer delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getRenewContractId() {
        return renewContractId;
    }

    public void setRenewContractId(Integer renewContractId) {
        this.renewContractId = renewContractId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Date getStatDate() {
        return statDate;
    }

    public void setStatDate(Date statDate) {
        this.statDate = statDate;
    }

    public Integer getCalcFlag() {
        return calcFlag;
    }

    public void setCalcFlag(Integer calcFlag) {
        this.calcFlag = calcFlag;
    }

    public Integer getNew_calcFlag() {
        return new_calcFlag;
    }

    public void setNew_calcFlag(Integer new_calcFlag) {
        this.new_calcFlag = new_calcFlag;
    }

    public Integer getSortFlag() {
        return sortFlag;
    }

    public void setSortFlag(Integer sortFlag) {
        this.sortFlag = sortFlag;
    }

    public String getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(String signStatus) {
        this.signStatus = signStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCancelId() {
        return cancelId;
    }

    public void setCancelId(Integer cancelId) {
        this.cancelId = cancelId;
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

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }
}
