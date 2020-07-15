package cn.com.eju.deal.dto.contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class ContractSearchResultDto implements Serializable
{
    
    private static final long serialVersionUID = 268231807297165902L;
    
    private Integer id;
    
    // 合同编号
    private String contractNo;
    
    // 协议书编号
    private String agreementNo;
    
    // 签约日期
    private Date dateCreate;
    
    // 合同状态
    private Integer contractStatus;
    
    // 客户名称
    private String companyName;
    
    // 合同状态名
    private String contractStatusName;
    
    private Integer userCreate;
    
    private String flowId;
    
    // 合同类型
    private Integer contractType;
    
    private String contractTypeName;
    
    // 归属人
    private String expandingPersonnel;
    
    private String expandingPersonnelId;
    
    private Integer companyId;
    
    private String userNameCreate;

    private String storeStateName;
    // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
    private Integer storeState;
    
    // 是否变更:0 or 1
    private Boolean isChanged;
    
    // 是否变更:是 or 否
    private String isChangedStr;
    
    // 已到账保证金
    private BigDecimal arrivalDepositFee;
    
    // 保证金状态
    private String depositFeeStatus;
    // 保证金状态
    private String depositFeeStateCode;
    
    // 总保证金
    private BigDecimal totleDepositFee;
    
    // 每门店保证金
    private BigDecimal depositFee;
    
    // 合作门店数
    private Integer storeNum;
    
    //剩余未分配到账金额
    private BigDecimal restSplitDepositFee;
   
    //保证金退款状态
    private String refundState;
    
    //保证金退款状态名称
    private String refundStateName;
    
    //合同到期日期
    private Date DateLifeEnd;
    
    //新保证金开关,用来控制分账按钮
    private String newDepositOpenFlag;

    private String isDeposit;

    public String getIsDeposit() {
        return isDeposit;
    }

    public void setIsDeposit(String isDeposit) {
        this.isDeposit = isDeposit;
    }
    
    public Date getDateLifeEnd() {
		return DateLifeEnd;
	}

	public void setDateLifeEnd(Date dateLifeEnd) {
		DateLifeEnd = dateLifeEnd;
	}

	public String getDepositFeeStateCode() {
		return depositFeeStateCode;
	}

	public void setDepositFeeStateCode(String depositFeeStateCode) {
		this.depositFeeStateCode = depositFeeStateCode;
	}

	public String getRefundStateName()
    {
        return refundStateName;
    }

    public void setRefundStateName(String refundStateName)
    {
        this.refundStateName = refundStateName;
    }

    public String getRefundState()
    {
        return refundState;
    }

    public void setRefundState(String refundState)
    {
        this.refundState = refundState;
    }

    public BigDecimal getRestSplitDepositFee()
    {
        return restSplitDepositFee;
    }

    public void setRestSplitDepositFee(BigDecimal restSplitDepositFee)
    {
        this.restSplitDepositFee = restSplitDepositFee;
    }

    public Integer getStoreNum() {
		return storeNum;
	}

	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	public BigDecimal getDepositFee() {
		return depositFee;
	}

	public void setDepositFee(BigDecimal depositFee) {
		this.depositFee = depositFee;
	}

	public BigDecimal getTotleDepositFee() {
		return totleDepositFee;
	}

	public void setTotleDepositFee(BigDecimal totleDepositFee) {
		this.totleDepositFee = totleDepositFee;
	}

	public BigDecimal getArrivalDepositFee() {
		return arrivalDepositFee;
	}

	public void setArrivalDepositFee(BigDecimal arrivalDepositFee) {
		this.arrivalDepositFee = arrivalDepositFee;
	}

	public String getDepositFeeStatus() {
		return depositFeeStatus;
	}

	public void setDepositFeeStatus(String depositFeeStatus) {
		this.depositFeeStatus = depositFeeStatus;
	}

	public void setIsChangedStr(String isChangedStr) {
		this.isChangedStr = isChangedStr;
	}

	public Boolean getIsChanged() {
		return isChanged;
	}

	public void setIsChanged(Boolean isChanged) {
		this.isChanged = isChanged;
	}

	public Integer getStoreState() {
		return storeState;
	}

	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
	public String getStoreStateName() {
		return storeStateName;
	}

	public void setStoreStateName(String storeStateName) {
		this.storeStateName = storeStateName;
	}

    public String getUserNameCreate()
    {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate)
    {
        this.userNameCreate = userNameCreate;
    }

    public String getExpandingPersonnelId()
    {
        return expandingPersonnelId;
    }
    
    public void setExpandingPersonnelId(String expandingPersonnelId)
    {
        this.expandingPersonnelId = expandingPersonnelId;
    }
    
    public String getExpandingPersonnel()
    {
        return expandingPersonnel;
    }
    
    public void setExpandingPersonnel(String expandingPersonnel)
    {
        this.expandingPersonnel = expandingPersonnel;
    }
    
    public Integer getContractType()
    {
        return contractType;
    }
    
    public void setContractType(Integer contractType)
    {
        this.contractType = contractType;
    }
    
    public String getContractTypeName()
    {
        return contractTypeName;
    }
    
    public void setContractTypeName(String contractTypeName)
    {
        this.contractTypeName = contractTypeName;
    }
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getContractNo()
    {
        return contractNo;
    }
    
    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }
    
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    public Integer getContractStatus()
    {
        return contractStatus;
    }
    
    public void setContractStatus(Integer contractStatus)
    {
        this.contractStatus = contractStatus;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    public String getAgreementNo()
    {
        return agreementNo;
    }
    
    public void setAgreementNo(String agreementNo)
    {
        this.agreementNo = agreementNo;
    }
    
    public String getContractStatusName()
    {
        return SystemParam.getDicValueByDicCode(this.contractStatus + "");
    }
    
    public void setContractStatusName(String contractStatusName)
    {
        this.contractStatusName = contractStatusName;
    }
    
    public Integer getUserCreate()
    {
        return userCreate;
    }
    
    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    
    public String getFlowId()
    {
        return flowId;
    }
    
    public void setFlowId(String flowId)
    {
        this.flowId = flowId;
    }
    
    public Integer getCompanyId()
    {
        return companyId;
    }
    
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }

    //Add By tong 2017/04/07 Start
    //合同生效状态
    private Integer valid;
    
    private String validName;
    
	public Integer getValid() {
		return valid;
	}

	public void setValid(Integer valid) {
		this.valid = valid;
	}
	
    public String getValidName() {
		return validName;
	}

	public void setValidName(String validName) {
		this.validName = validName;
	}
	//Add By tong 2017/04/07 End

	public String getIsChangedStr() {
		return isChangedStr;
	}

    /**
     * @return the newDepositOpenFlag
     */
    public String getNewDepositOpenFlag() {
        return newDepositOpenFlag;
    }

    /**
     * @param newDepositOpenFlag the newDepositOpenFlag to set
     */
    public void setNewDepositOpenFlag(String newDepositOpenFlag) {
        this.newDepositOpenFlag = newDepositOpenFlag;
    }
	
}