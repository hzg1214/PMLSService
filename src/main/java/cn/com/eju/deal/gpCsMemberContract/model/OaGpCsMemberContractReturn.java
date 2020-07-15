package cn.com.eju.deal.gpCsMemberContract.model;

import java.util.Date;

public class OaGpCsMemberContractReturn {
    private Integer id;

    private String gpCsMemberContractNo;

    private String flowId;

    private String oaNo;

    private Date approveDate;

    private Integer hasDeal;

    private Date dateUpdate;

    private Date dateCreate;

    private Integer approveStatus;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGpCsMemberContractNo() {
        return gpCsMemberContractNo;
    }

    public void setGpCsMemberContractNo(String gpCsMemberContractNo) {
        this.gpCsMemberContractNo = gpCsMemberContractNo == null ? null : gpCsMemberContractNo.trim();
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId == null ? null : flowId.trim();
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo == null ? null : oaNo.trim();
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public Integer getHasDeal() {
        return hasDeal;
    }

    public void setHasDeal(Integer hasDeal) {
        this.hasDeal = hasDeal;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}