package cn.com.eju.deal.houseLinkage.report.model;

import cn.com.eju.deal.base.support.SystemParam;

import java.math.BigDecimal;
import java.util.Date;

public class ReportDetail {

	private Integer id;
	// 楼盘ID
	private String estateId;
	// 楼盘名
	private String estateNm;
	// 公司ID
	private String companyId;
	//报备来源
	private Integer customerFrom;
	// 公司名
	private String companyNm;
	// 统计明细编号
	private String countId;
	// 进度
	private Integer progress;
	// 确认状态
	private Integer confirmStatus;
	// 详情
	private String reportMemo;
	// 跟进时间
	private Date followDate;
	// 客户编号
	private String customerId;
	// 客户名
	private String customerNm;
	// 客户电话
	private String customerTel;
	// 可否结算
	private Integer accountFlg;
	// 结算状态
	private Integer accountStatus;
	// 佣金结算状态
	private Integer commissionAccountStatus;
	// 认定日
	private Date recognitionDay;
	// 带看奖励
	private BigDecimal relationReward;
	// 已结算带看奖励
	private BigDecimal accountRelationReward;
	// 认筹奖励
	private BigDecimal pledgedReward;
	// 已结算认筹奖励
	private BigDecimal accountPledgedReward;
	// 认购奖励
	private BigDecimal subscribedReward;
	// 已结算认购奖励
	private BigDecimal accountSubscribedReward;
	// 成交奖励
	private BigDecimal bargainReward;
	// 已结算成交奖励
	private BigDecimal accountBargainReward;
	// 佣金
	private BigDecimal commission;
	// 已结算佣金
	private BigDecimal accountCommission;
	// 部门名
	private String deptNm;
	// 员工名
	private String empNm;
	// 报备日
	private Date reportDate;
	private Integer crtEmpId;
	private Integer uptEmpId;
	private Boolean delFlg;
	private Date crtDt;
	private Date uptDt;
	//大定面积
	private BigDecimal roughArea;
	
	// 以下是扩展字段
	// 进度名称
	private String progressNm;
	// 确认状态名称
	private String confirmStatusNm;
	// 可否结算名称
	private String accountFlgNm;
	// 结算状态名称
	private String accountStatusNm;
	// 佣金结算状态名称
	private String commissionAccountStatusNm;
	// 跟进日展示用
	private String followDateDisPlay;
	// 奖励金额
	private BigDecimal reward;
	// 结算金额
	private BigDecimal accountReward;
	
	// 楼市号
	private String buildingNo;

	// 面积
	private BigDecimal area;
	// 大定总价
	private BigDecimal roughAmount;
	// 成销总价
	private BigDecimal dealAmount;
	
	//带看时间
	private Date seeDate;
	//认筹时间
	private Date pledgedDate;
	//大定时间
	private Date roughDate;
	//成销时间
	private Date dealDate;
	//退筹时间
	private Date pledgedBackDate;
	//退定时间
	private Date roughBackDate;
	//退房时间
	private Date dealBackDate;
	
	private String crtEmpName;

	private Date settleConfirmDate;
	
	// 客户编号2
	private String customerIdTwo;
	// 客户名2
	private String customerNmTwo;
	// 客户电话2
	private String customerTelTwo;

	private Date roughInputDate;

	private String roughDateClear;

	private String uptEmpName;

	private Integer inComeStatus;

	private Integer reportId;

	public Integer getReportId() {
		return reportId;
	}

	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}

	public Integer getInComeStatus() {
		return inComeStatus;
	}

	public void setInComeStatus(Integer inComeStatus) {
		this.inComeStatus = inComeStatus;
	}

	public String getUptEmpName() {
		return uptEmpName;
	}

	public void setUptEmpName(String uptEmpName) {
		this.uptEmpName = uptEmpName;
	}

	public BigDecimal getRoughArea()
    {
        return roughArea;
    }
    public void setRoughArea(BigDecimal roughArea)
    {
        this.roughArea = roughArea;
    }
    public String getBuildingNo()
    {
        return buildingNo;
    }
    public void setBuildingNo(String buildingNo)
    {
        this.buildingNo = buildingNo;
    }
    public BigDecimal getArea()
    {
        return area;
    }
    public void setArea(BigDecimal area)
    {
        this.area = area;
    }
    public BigDecimal getRoughAmount()
    {
        return roughAmount;
    }
    public void setRoughAmount(BigDecimal roughAmount)
    {
        this.roughAmount = roughAmount;
    }
    public BigDecimal getDealAmount()
    {
        return dealAmount;
    }
    public void setDealAmount(BigDecimal dealAmount)
    {
        this.dealAmount = dealAmount;
    }
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getCountId() {
		return countId;
	}
	public void setCountId(String countId) {
		this.countId = countId;
	}
	public String getReportMemo() {
		return reportMemo;
	}
	public void setReportMemo(String reportMemo) {
		this.reportMemo = reportMemo;
	}
	public Date getFollowDate() {
		return followDate;
	}
	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNm() {
		return customerNm;
	}
	public void setCustomerNm(String customerNm) {
		this.customerNm = customerNm;
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}
	public Date getRecognitionDay() {
		return recognitionDay;
	}
	public void setRecognitionDay(Date recognitionDay) {
		this.recognitionDay = recognitionDay;
	}
	public BigDecimal getRelationReward() {
		return relationReward;
	}
	public void setRelationReward(BigDecimal relationReward) {
		this.relationReward = relationReward;
	}
	public BigDecimal getAccountRelationReward() {
		return accountRelationReward;
	}
	public void setAccountRelationReward(BigDecimal accountRelationReward) {
		this.accountRelationReward = accountRelationReward;
	}
	public BigDecimal getPledgedReward() {
		return pledgedReward;
	}
	public void setPledgedReward(BigDecimal pledgedReward) {
		this.pledgedReward = pledgedReward;
	}
	public BigDecimal getAccountPledgedReward() {
		return accountPledgedReward;
	}
	public void setAccountPledgedReward(BigDecimal accountPledgedReward) {
		this.accountPledgedReward = accountPledgedReward;
	}
	public BigDecimal getSubscribedReward() {
		return subscribedReward;
	}
	public void setSubscribedReward(BigDecimal subscribedReward) {
		this.subscribedReward = subscribedReward;
	}
	public BigDecimal getAccountSubscribedReward() {
		return accountSubscribedReward;
	}
	public void setAccountSubscribedReward(BigDecimal accountSubscribedReward) {
		this.accountSubscribedReward = accountSubscribedReward;
	}
	public BigDecimal getBargainReward() {
		return bargainReward;
	}
	public void setBargainReward(BigDecimal bargainReward) {
		this.bargainReward = bargainReward;
	}
	public BigDecimal getAccountBargainReward() {
		return accountBargainReward;
	}
	public void setAccountBargainReward(BigDecimal accountBargainReward) {
		this.accountBargainReward = accountBargainReward;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getAccountCommission() {
		return accountCommission;
	}
	public void setAccountCommission(BigDecimal accountCommission) {
		this.accountCommission = accountCommission;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public String getEmpNm() {
		return empNm;
	}
	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getCrtEmpId() {
		return crtEmpId;
	}
	public void setCrtEmpId(Integer crtEmpId) {
		this.crtEmpId = crtEmpId;
	}
	public Integer getUptEmpId() {
		return uptEmpId;
	}
	public void setUptEmpId(Integer uptEmpId) {
		this.uptEmpId = uptEmpId;
	}
	public Boolean getDelFlg() {
		return delFlg;
	}
	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public Date getUptDt() {
		return uptDt;
	}
	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public Integer getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public Integer getAccountFlg() {
		return accountFlg;
	}
	public void setAccountFlg(Integer accountFlg) {
		this.accountFlg = accountFlg;
	}
	public Integer getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(Integer accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Integer getCommissionAccountStatus() {
		return commissionAccountStatus;
	}
	public void setCommissionAccountStatus(Integer commissionAccountStatus) {
		this.commissionAccountStatus = commissionAccountStatus;
	}
	public String getProgressNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(progress));
	}
	public void setProgressNm(String progressNm) {
		this.progressNm = progressNm;
	}
	public String getConfirmStatusNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(confirmStatus));
	}
	public void setConfirmStatusNm(String confirmStatusNm) {
		this.confirmStatusNm = confirmStatusNm;
	}
	public String getAccountFlgNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(accountFlg));
	}
	public void setAccountFlgNm(String accountFlgNm) {
		this.accountFlgNm = accountFlgNm;
	}
	public String getAccountStatusNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(accountStatus));
	}
	public void setAccountStatusNm(String accountStatusNm) {
		this.accountStatusNm = accountStatusNm;
	}
	public String getCommissionAccountStatusNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(commissionAccountStatus));
	}
	public void setCommissionAccountStatusNm(String commissionAccountStatusNm) {
		this.commissionAccountStatusNm = commissionAccountStatusNm;
	}
	public String getFollowDateDisPlay() {
		return followDateDisPlay;
	}
	public void setFollowDateDisPlay(String followDateDisPlay) {
		this.followDateDisPlay = followDateDisPlay;
	}
	public BigDecimal getReward() {
		return reward;
	}
	public void setReward(BigDecimal reward) {
		this.reward = reward;
	}
	public BigDecimal getAccountReward() {
		return accountReward;
	}
	public void setAccountReward(BigDecimal accountReward) {
		this.accountReward = accountReward;
	}
	public Integer getCustomerFrom() {
		return customerFrom;
	}
	public void setCustomerFrom(Integer customerFrom) {
		this.customerFrom = customerFrom;
	}
	public Date getSeeDate() {
		return seeDate;
	}
	public void setSeeDate(Date seeDate) {
		this.seeDate = seeDate;
	}
	public Date getPledgedDate() {
		return pledgedDate;
	}
	public void setPledgedDate(Date pledgedDate) {
		this.pledgedDate = pledgedDate;
	}
	public Date getRoughDate() {
		return roughDate;
	}
	public void setRoughDate(Date roughDate) {
		this.roughDate = roughDate;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public Date getPledgedBackDate() {
		return pledgedBackDate;
	}
	public void setPledgedBackDate(Date pledgedBackDate) {
		this.pledgedBackDate = pledgedBackDate;
	}
	public Date getRoughBackDate() {
		return roughBackDate;
	}
	public void setRoughBackDate(Date roughBackDate) {
		this.roughBackDate = roughBackDate;
	}
	public Date getDealBackDate() {
		return dealBackDate;
	}
	public void setDealBackDate(Date dealBackDate) {
		this.dealBackDate = dealBackDate;
	}
    /**
     * @return  the 【crtEmpName】
     */
    public String getCrtEmpName() {
    
        return crtEmpName;
    }
    /**
     * @param crtEmpName the 【crtEmpName】 to set
     */
    public void setCrtEmpName(String crtEmpName) {
    
        this.crtEmpName = crtEmpName;
    }

	public Date getSettleConfirmDate() {
		return settleConfirmDate;
	}

	public void setSettleConfirmDate(Date settleConfirmDate) {
		this.settleConfirmDate = settleConfirmDate;
	}
	public String getCustomerIdTwo() {
		return customerIdTwo;
	}
	public void setCustomerIdTwo(String customerIdTwo) {
		this.customerIdTwo = customerIdTwo;
	}
	public String getCustomerNmTwo() {
		return customerNmTwo;
	}
	public void setCustomerNmTwo(String customerNmTwo) {
		this.customerNmTwo = customerNmTwo;
	}
	public String getCustomerTelTwo() {
		return customerTelTwo;
	}
	public void setCustomerTelTwo(String customerTelTwo) {
		this.customerTelTwo = customerTelTwo;
	}

	public Date getRoughInputDate() {
		return roughInputDate;
	}

	public void setRoughInputDate(Date roughInputDate) {
		this.roughInputDate = roughInputDate;
	}

	public String getRoughDateClear() {
		return roughDateClear;
	}

	public void setRoughDateClear(String roughDateClear) {
		this.roughDateClear = roughDateClear;
	}
	//第二组客户置为null的专用，
	private String customerTwoFlag;
	public String getCustomerTwoFlag() {
		return customerTwoFlag;
	}
	public void setCustomerTwoFlag(String customerTwoFlag) {
		this.customerTwoFlag = customerTwoFlag;
	}

	private String reportNo;//报备编号

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	// 楼市号ID
	private String buildingNoId;
	public String getBuildingNoId() {
		return buildingNoId;
	}
	public void setBuildingNoId(String buildingNoId) {
		this.buildingNoId = buildingNoId;
	}


	private String wyTypeCode;
	private String wyTypName;

	public String getWyTypeCode() {
		return wyTypeCode;
	}

	public void setWyTypeCode(String wyTypeCode) {
		this.wyTypeCode = wyTypeCode;
	}

	public String getWyTypName() {
		return wyTypName;
	}

	public void setWyTypName(String wyTypName) {
		this.wyTypName = wyTypName;
	}
}