/**
 * 
 */
package cn.com.eju.deal.dto.store;

import cn.com.eju.deal.core.model.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author yinkun
 *
 */
public class StoreReceiveDto extends BaseModel{

    /**
     * 
     */
    private static final long serialVersionUID = -2044680546225875863L;
    private Integer Id;
    private String receiveNo;
    private Integer orderType;
    private String contractNo;
    private Integer feeType;
    private Integer receiveType;
    private BigDecimal totalAmount;
    private Integer approveStatus;
    private Integer submitOaStatus;
    private Integer status;
    private Date confirmTime;
    private String flowId;
    private String traceNo;
    private String oaNo;
    private Date dateCreate;
    private Integer userIdCreate;
    private Date dateUpt;
    private Integer userIdUpt;
    private String delFlag;
    private String accountProject;
    private Date dateRecorded;
    private Date approvePassDate;
    private String paySeq;
    private Date responseTime;
    private List<StoreReceiveDtlDto> dtlList;
    /**
     * 保证金收款查询字段
     */
    private String companyName;
    private String oaStatusName;
    private String userName;
    private String agreementNo;
    private String contractTypeName;
    private String approveStatusNm;
    private String receiveTypeNm;
    //提交OA状态
    private String submitOaStatusNm;
    //调整单用
    private String storeNo;
    private String storeName;
    private String addressDetail;

    private Integer reverseId;
    private Integer reverseApproveStatus;
    private String reverseApproveStatusNm;
    private Date reverseApproveDate;
    private String reverseOaNo;

    private String oaBankName;
    private String oaBankId;
    private String userCode;
    private String reverseApproveDesc;
    private String remark;

    private String feeTypeNm;

    private String accountName;

    private String accountProjectCode;

    public String getStoreNo() {
		return storeNo;
	}
	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	/**
     * @return the id
     */
    public Integer getId() {
        return Id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        Id = id;
    }
    /**
     * @return the receiveNo
     */
    public String getReceiveNo() {
        return receiveNo;
    }
    /**
     * @param receiveNo the receiveNo to set
     */
    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
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
     * @return the feeType
     */
    public Integer getFeeType() {
        return feeType;
    }
    /**
     * @param feeType the feeType to set
     */
    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }
    /**
     * @return the receiveType
     */
    public Integer getReceiveType() {
        return receiveType;
    }
    /**
     * @param receiveType the receiveType to set
     */
    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
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
     * @return the traceNo
     */
    public String getTraceNo() {
        return traceNo;
    }
    /**
     * @param traceNo the traceNo to set
     */
    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }
    /**
     * @return the oaNo
     */
    public String getOaNo() {
        return oaNo;
    }
    /**
     * @param oaNo the oaNo to set
     */
    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
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
    public List<StoreReceiveDtlDto> getDtlList() {
        return dtlList;
    }
    /**
     * @param dtlList the dtlList to set
     */
    public void setDtlList(List<StoreReceiveDtlDto> dtlList) {
        this.dtlList = dtlList;
    }
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getOaStatusName() {
		return oaStatusName;
	}
	public void setOaStatusName(String oaStatusName) {
		this.oaStatusName = oaStatusName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getContractTypeName() {
		return contractTypeName;
	}
	public void setContractTypeName(String contractTypeName) {
		this.contractTypeName = contractTypeName;
	}
	public String getApproveStatusNm() {
		return approveStatusNm;
	}
	public void setApproveStatusNm(String approveStatusNm) {
		this.approveStatusNm = approveStatusNm;
	}
	public String getReceiveTypeNm() {
		return receiveTypeNm;
	}
	public void setReceiveTypeNm(String receiveTypeNm) {
		this.receiveTypeNm = receiveTypeNm;
	}
	public String getAccountProject() {
		return accountProject;
	}
	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}
	public Date getDateRecorded() {
		return dateRecorded;
	}
	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}
	public Date getApprovePassDate() {
		return approvePassDate;
	}
	public void setApprovePassDate(Date approvePassDate) {
		this.approvePassDate = approvePassDate;
	}
	public String getPaySeq() {
		return paySeq;
	}
	public void setPaySeq(String paySeq) {
		this.paySeq = paySeq;
	}
	public String getSubmitOaStatusNm() {
		return submitOaStatusNm;
	}
	public void setSubmitOaStatusNm(String submitOaStatusNm) {
		this.submitOaStatusNm = submitOaStatusNm;
	}
	public Date getResponseTime() {
		return responseTime;
	}
	public void setResponseTime(Date responseTime) {
		this.responseTime = responseTime;
	}

    public Integer getReverseId() {
        return reverseId;
    }

    public void setReverseId(Integer reverseId) {
        this.reverseId = reverseId;
    }

    public Integer getReverseApproveStatus() {
        return reverseApproveStatus;
    }

    public void setReverseApproveStatus(Integer reverseApproveStatus) {
        this.reverseApproveStatus = reverseApproveStatus;
    }

    public Date getReverseApproveDate() {
        return reverseApproveDate;
    }

    public void setReverseApproveDate(Date reverseApproveDate) {
        this.reverseApproveDate = reverseApproveDate;
    }

    public String getReverseOaNo() {
        return reverseOaNo;
    }

    public void setReverseOaNo(String reverseOaNo) {
        this.reverseOaNo = reverseOaNo;
    }

    public String getReverseApproveStatusNm() {
        return reverseApproveStatusNm;
    }

    public void setReverseApproveStatusNm(String reverseApproveStatusNm) {
        this.reverseApproveStatusNm = reverseApproveStatusNm;
    }

    public String getOaBankName() {
        return oaBankName;
    }

    public void setOaBankName(String oaBankName) {
        this.oaBankName = oaBankName;
    }

    public String getOaBankId() {
        return oaBankId;
    }

    public void setOaBankId(String oaBankId) {
        this.oaBankId = oaBankId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getReverseApproveDesc() {
        return reverseApproveDesc;
    }

    public void setReverseApproveDesc(String reverseApproveDesc) {
        this.reverseApproveDesc = reverseApproveDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFeeTypeNm() {
        return feeTypeNm;
    }

    public void setFeeTypeNm(String feeTypeNm) {
        this.feeTypeNm = feeTypeNm;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountProjectCode() {
        return accountProjectCode;
    }

    public void setAccountProjectCode(String accountProjectCode) {
        this.accountProjectCode = accountProjectCode;
    }
}
