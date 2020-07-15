package cn.com.eju.deal.store.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.core.model.BaseModel;

public class StorePayment extends BaseModel{

    /**
     * 
     */
    private static final long serialVersionUID = 7026235037828209864L;
    private Integer id;
    private Integer orderType;
    private String paymentNo;
    private String contractNo;
    private Integer paymentType;
    private BigDecimal totalAmount;
    private Integer approveStatus;
    private Integer submitOaStatus;
    private Integer status;
    private Date confirmTime;
    private String flowId;
    private Date dateCreate;
    private Integer userIdCreate;
    private Date dateUpt;
    private Integer userIdUpt;
    private String delFlag;
    private Date refundDate;
    private String cityNo;
    private List<StorePaymentDtl> dtlList;
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the orderType
     */
    public Integer getOrderType() {
        return orderType;
    }
    /**
     * @param orderType the orderType to set
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    /**
     * @return the paymentNo
     */
    public String getPaymentNo() {
        return paymentNo;
    }
    /**
     * @param paymentNo the paymentNo to set
     */
    public void setPaymentNo(String paymentNo) {
        this.paymentNo = paymentNo;
    }
    /**
     * @return the contractNo
     */
    public String getContractNo() {
        return contractNo;
    }
    /**
     * @param contractNo the contractNo to set
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }
    /**
     * @return the paymentType
     */
    public Integer getPaymentType() {
        return paymentType;
    }
    /**
     * @param paymentType the paymentType to set
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }
    /**
     * @return the totalAmount
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    /**
     * @return the approveStatus
     */
    public Integer getApproveStatus() {
        return approveStatus;
    }
    /**
     * @param approveStatus the approveStatus to set
     */
    public void setApproveStatus(Integer approveStatus) {
        this.approveStatus = approveStatus;
    }
    /**
     * @return the submitOaStatus
     */
    public Integer getSubmitOaStatus() {
        return submitOaStatus;
    }
    /**
     * @param submitOaStatus the submitOaStatus to set
     */
    public void setSubmitOaStatus(Integer submitOaStatus) {
        this.submitOaStatus = submitOaStatus;
    }
    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * @return the confirmTime
     */
    public Date getConfirmTime() {
        return confirmTime;
    }
    /**
     * @param confirmTime the confirmTime to set
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
    /**
     * @return the flowId
     */
    public String getFlowId() {
        return flowId;
    }
    /**
     * @param flowId the flowId to set
     */
    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
    /**
     * @return the dateCreate
     */
    public Date getDateCreate() {
        return dateCreate;
    }
    /**
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }
    /**
     * @return the userIdCreate
     */
    public Integer getUserIdCreate() {
        return userIdCreate;
    }
    /**
     * @param userIdCreate the userIdCreate to set
     */
    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }
    /**
     * @return the dateUpt
     */
    public Date getDateUpt() {
        return dateUpt;
    }
    /**
     * @param dateUpt the dateUpt to set
     */
    public void setDateUpt(Date dateUpt) {
        this.dateUpt = dateUpt;
    }
    /**
     * @return the userIdUpt
     */
    public Integer getUserIdUpt() {
        return userIdUpt;
    }
    /**
     * @param userIdUpt the userIdUpt to set
     */
    public void setUserIdUpt(Integer userIdUpt) {
        this.userIdUpt = userIdUpt;
    }
    /**
     * @return the delFlag
     */
    public String getDelFlag() {
        return delFlag;
    }
    /**
     * @param delFlag the delFlag to set
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * @return the dtlList
     */
    public List<StorePaymentDtl> getDtlList() {
        return dtlList;
    }
    /**
     * @param dtlList the dtlList to set
     */
    public void setDtlList(List<StorePaymentDtl> dtlList) {
        this.dtlList = dtlList;
    }
	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
    private String accountProject;
    private String accountProjectCode;
	public String getAccountProject() {
		return accountProject;
	}
	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}
	public String getAccountProjectCode() {
		return accountProjectCode;
	}
	public void setAccountProjectCode(String accountProjectCode) {
		this.accountProjectCode = accountProjectCode;
	}
    
}
