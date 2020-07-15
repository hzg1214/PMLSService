package cn.com.eju.deal.contract.model;

import java.util.Date;

public class AchievementCancel
{

    private Integer id;
    
    /**
     * 门店业绩撤销编号
     */
    private String achievementCancelNo;
    
    /**
     * 合同Id
     */
    private int contractId;
    
    /**
     * 撤销原因
     */
    private String cancelReason;
    
    /**
     * 备注
     */
    private String remarks;
    
    /**
     * 创建时间
     */
    private Date dateCreate;
    
    /**
     * 创建人
     */
    private int userCreate;
    
    /**
     * 门店撤销审核状态
     */
    private String approveState;
    
    /**
     * 审核时间
     */
    private Date approveDate;    
    
    /**
     * OA申请编号
     */
    private String flowId;
    
    /**
     * 更新时间
     */
    private Date updateDate;
    
    /**
     * 更新人
     */
    private Integer updateUser;
    
    /**
     * 删除标记：1-删除 ,0-未删除
     */
    private boolean isDelete;
    
    /**
     * 核算主体
     */
    private String accountSubject;
    
    /**
     * 事业部区域
     */
    private String busDepartment;

    private String stopKbn;

    public String getStopKbn() {
        return stopKbn;
    }

    public void setStopKbn(String stopKbn) {
        this.stopKbn = stopKbn;
    }

    public String getAchievementCancelNo()
    {
        return achievementCancelNo;
    }

    public void setAchievementCancelNo(String achievementCancelNo)
    {
        this.achievementCancelNo = achievementCancelNo;
    }

    public Date getApproveDate()
    {
        return approveDate;
    }

    public void setApproveDate(Date approveDate)
    {
        this.approveDate = approveDate;
    }

    public String getFlowId()
    {
        return flowId;
    }

    public void setFlowId(String flowId)
    {
        this.flowId = flowId;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUser()
    {
        return updateUser;
    }

    public void setUpdateUser(Integer updateUser)
    {
        this.updateUser = updateUser;
    }

    public String getAccountSubject()
    {
        return accountSubject;
    }

    public void setAccountSubject(String accountSubject)
    {
        this.accountSubject = accountSubject;
    }

    public String getBusDepartment()
    {
        return busDepartment;
    }

    public void setBusDepartment(String busDepartment)
    {
        this.busDepartment = busDepartment;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public int getContractId()
    {
        return contractId;
    }

    public void setContractId(int contractId)
    {
        this.contractId = contractId;
    }

    public String getCancelReason()
    {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason)
    {
        this.cancelReason = cancelReason;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks(String remarks)
    {
        this.remarks = remarks;
    }

    public Date getDateCreate()
    {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    public int getUserCreate()
    {
        return userCreate;
    }

    public void setUserCreate(int userCreate)
    {
        this.userCreate = userCreate;
    }

    public String getApproveState()
    {
        return approveState;
    }

    public void setApproveState(String approveState)
    {
        this.approveState = approveState;
    }

    public boolean isDelete()
    {
        return isDelete;
    }

    public void setDelete(boolean isDelete)
    {
        this.isDelete = isDelete;
    }
}
