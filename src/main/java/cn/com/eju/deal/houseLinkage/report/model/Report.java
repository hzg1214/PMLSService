package cn.com.eju.deal.houseLinkage.report.model;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Report {

	private Integer id;
	// 楼盘ID
	private String estateId;
	// 报备编码
	private String reportId;
	// 楼盘名称
	private String estateNm;
	// 公司名称
	private String companyNm;
	// 公司编号
	private String companyId;
	//报备来源
	private Integer customerFrom;
	// 客户编号
	private String customerId;
	// 客户名
	private String customerNm;
	// 客户电话
	private String customerTel;
	// 客户关系
	private String customerParty;
	// 关系人名
	private String partyNm;
	// 关系人电话
	private String partyTel;
	// 门店名
	private String storeNm;
	// 门店ID
	private String storeId;
	// 门店维护人
    private String contactId;
    // 门店维护人姓名
    private String contactNm;
	// 部门名
	private String deptNm;
	// 部门ID
	private String deptId;
	// 员工名
	private String empNm;
	// 员工ID
	private String empId;
	// 城市
	private String cityNo;
	// 楼盘类型
	private String estateType;
	// 最新进度
	private Integer latestProgress;
	// 确认状态
	private Integer confirmStatus;
	// 报备日
	private Date reportDate;
	// 跟进日
	private Date followDate;
	// 有效期
	private Date validDate;
	// 有效带看时间
	private Date validRelationDate;
	private Integer crtEmpId;
	private Integer uptEmpId;
	private Boolean delFlg;
	private Date crtDt;
	private Date uptDt;
	// 客户人数
	private String customerNum;
    // 客户需求
	private String customerRequire;
    // 备注
	private String memo;
	// 以下是扩展字段
	// 楼盘类型
	private String estateTypeNm;
	// 最新进度
	private String latestProgressNm;
	// 确认状态
	private String confirmStatusNm;

	private Integer commissionProgress;
	
	private String latestReportFollowDate;
	private String latestRelationFollowDate;
	private String latestPledgedFollowDate;
	private String latestSubscribedFollowDate;
	private String latestBargainFollowDate;
	private String latestBackFollowDate;
	private String latestCommissionFollowDate;
	private String latestCommissionFollowDate2;
	//大定时间
	private Date roughDate;
	//成销时间
	private Date dealDate;
	//带看时间
	private Date seeDate;
	//认筹时间
	private Date pledgedDate;
	//退筹时间
	private Date pledgedBackDate;
	//退定时间
	private Date roughBackDate;
	//退房时间
	private Date dealBackDate;
	
    // 合同类型
    private Integer contractType;
    
    private String operUserCode;
    
    private String operUserName;

    private String projectNo;
    
    
    private String fyReportId;
    private String reportAgent;
    private String reportAgentTel;
    
    
	// 客户编号2
	private String customerIdTwo;
	// 客户名2
	private String customerNmTwo;
	// 客户电话2
	private String customerTelTwo;
	private String centerGroupId;
	private String centerGroupName;//业绩归属中心

	private String buildingNo;

	private BigDecimal roughArea;

	private BigDecimal roughAmount;

	//大定审核状态
	private String roughAuditStatus;

	//大定审核时间
	private Date roughAuditTime;

	//大定审核人
	private Long roughAuditId;

	//大定审核原因
	private String roughAuditDesc;

	private List<FileRecordMainDto> picList;
	
	//结佣
	private String brokerageStatus;
	private Integer brokerageUptEmpId;
	private Date brokerageUptDt;
	private String brokerageYm;
	private String brokerageSendStatus;//结佣发送状态
	private Integer brokerageSendCount;
	
	
	private String customerFromNm;
	private String roughAuditUserCode;
	private String roughAuditUserName;
	private String roughAuditStatusNm;
	
	private String brokerageStatusNm;
	private String brokerageUptEmpNm;

	private Date roughInputDate;
	
	private List<FangyouReportFile> fangyouFileList;//房友图片

	private String orderNumber;

	//是否立项
	private Integer isApproval;

	private Integer detailId;

	private Integer inComeStatus;

	private String acCityNo;

	private Boolean rebackFlag;

	private String contractNo;

	private Integer fyCenterId;

	private String fyCenterName;

	private String htedition;

    private  Integer branchId;
    
    private BigDecimal sjFyAmount;

	private String partnerAbbreviationNm;
	private String partnerNm;
	private String developerName;
	private String devCompany;

	private String preBack;

	public BigDecimal getSjFyAmount() {
		return sjFyAmount;
	}

	public void setSjFyAmount(BigDecimal sjFyAmount) {
		this.sjFyAmount = sjFyAmount;
	}
	public Boolean getRebackFlag() {
		return rebackFlag;
	}

	public void setRebackFlag(Boolean rebackFlag) {
		this.rebackFlag = rebackFlag;
	}


	public Integer getInComeStatus() {
		return inComeStatus;
	}

	public void setInComeStatus(Integer inComeStatus) {
		this.inComeStatus = inComeStatus;
	}

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getIsApproval() {
		return isApproval;
	}

	public void setIsApproval(Integer isApproval) {
		this.isApproval = isApproval;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	/**
     * @return  the 【operUserCode】
     */
    public String getOperUserCode() {
    
        return operUserCode;
    }

    /**
     * @param operUserCode the 【operUserCode】 to set
     */
    public void setOperUserCode(String operUserCode) {
    
        this.operUserCode = operUserCode;
    }

    /**
     * @return  the 【operUserName】
     */
    public String getOperUserName() {
    
        return operUserName;
    }

    /**
     * @param operUserName the 【operUserName】 to set
     */
    public void setOperUserName(String operUserName) {
    
        this.operUserName = operUserName;
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

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getEstateNm() {
		return estateNm;
	}

	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}

	public String getCompanyNm() {
		return companyNm;
	}

	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getCustomerParty() {
		return customerParty;
	}

	public void setCustomerParty(String customerParty) {
		this.customerParty = customerParty;
	}

	public String getPartyNm() {
		return partyNm;
	}

	public void setPartyNm(String partyNm) {
		this.partyNm = partyNm;
	}

	public String getPartyTel() {
		return partyTel;
	}

	public void setPartyTel(String partyTel) {
		this.partyTel = partyTel;
	}

	public String getStoreNm() {
		return storeNm;
	}

	public void setStoreNm(String storeNm) {
		this.storeNm = storeNm;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getEmpNm() {
		return empNm;
	}

	public void setEmpNm(String empNm) {
		this.empNm = empNm;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public Date getReportDate() {
		return reportDate;
	}

	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}

	public Date getFollowDate() {
		return followDate;
	}

	public void setFollowDate(Date followDate) {
		this.followDate = followDate;
	}

	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

	public Date getValidRelationDate() {
		return validRelationDate;
	}

	public void setValidRelationDate(Date validRelationDate) {
		this.validRelationDate = validRelationDate;
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

	public Integer getCommissionProgress() {
		return commissionProgress;
	}

	public void setCommissionProgress(Integer commissionProgress) {
		this.commissionProgress = commissionProgress;
	}

	public String getEstateType() {
		return estateType;
	}

	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}

	public Integer getLatestProgress() {
		return latestProgress;
	}

	public void setLatestProgress(Integer latestProgress) {
		this.latestProgress = latestProgress;
	}

	public Integer getConfirmStatus() {
		return confirmStatus;
	}

	public void setConfirmStatus(Integer confirmStatus) {
		this.confirmStatus = confirmStatus;
	}

	public String getEstateTypeNm() {
//		return SystemParam.getDicValueByDicCode(String.valueOf(estateType));
		return getStrByCode(estateType);
	}

	public void setEstateTypeNm(String estateTypeNm) {
		this.estateTypeNm = estateTypeNm;
	}

	public String getLatestProgressNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(latestProgress));
	}

	public void setLatestProgressNm(String latestProgressNm) {
		this.latestProgressNm = latestProgressNm;
	}

	public String getConfirmStatusNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(confirmStatus));
	}

	public void setConfirmStatusNm(String confirmStatusNm) {
		this.confirmStatusNm = confirmStatusNm;
	}

	public String getLatestReportFollowDate() {
		return latestReportFollowDate;
	}

	public void setLatestReportFollowDate(String latestReportFollowDate) {
		this.latestReportFollowDate = latestReportFollowDate;
	}

	public String getLatestRelationFollowDate() {
		return latestRelationFollowDate;
	}

	public void setLatestRelationFollowDate(String latestRelationFollowDate) {
		this.latestRelationFollowDate = latestRelationFollowDate;
	}

	public String getLatestPledgedFollowDate() {
		return latestPledgedFollowDate;
	}

	public void setLatestPledgedFollowDate(String latestPledgedFollowDate) {
		this.latestPledgedFollowDate = latestPledgedFollowDate;
	}

	public String getLatestSubscribedFollowDate() {
		return latestSubscribedFollowDate;
	}

	public void setLatestSubscribedFollowDate(String latestSubscribedFollowDate) {
		this.latestSubscribedFollowDate = latestSubscribedFollowDate;
	}

	public String getLatestBargainFollowDate() {
		return latestBargainFollowDate;
	}

	public void setLatestBargainFollowDate(String latestBargainFollowDate) {
		this.latestBargainFollowDate = latestBargainFollowDate;
	}

	public String getLatestBackFollowDate() {
		return latestBackFollowDate;
	}

	public void setLatestBackFollowDate(String latestBackFollowDate) {
		this.latestBackFollowDate = latestBackFollowDate;
	}

	public String getLatestCommissionFollowDate() {
		return latestCommissionFollowDate;
	}

	public void setLatestCommissionFollowDate(String latestCommissionFollowDate) {
		this.latestCommissionFollowDate = latestCommissionFollowDate;
	}

	public String getLatestCommissionFollowDate2() {
		return latestCommissionFollowDate2;
	}

	public void setLatestCommissionFollowDate2(String latestCommissionFollowDate2) {
		this.latestCommissionFollowDate2 = latestCommissionFollowDate2;
	}

	public String getCustomerNum() {
		return customerNum;
	}

	public void setCustomerNum(String customerNum) {
		this.customerNum = customerNum;
	}

	public String getCustomerRequire() {
		return customerRequire;
	}

	public void setCustomerRequire(String customerRequire) {
		this.customerRequire = customerRequire;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

    public String getContactId()
    {
        return contactId;
    }

    public void setContactId(String contactId)
    {
        this.contactId = contactId;
    }

    public String getContactNm()
    {
        return contactNm;
    }

    public void setContactNm(String contactNm)
    {
        this.contactNm = contactNm;
    }

	public Integer getCustomerFrom() {
		return customerFrom;
	}

	public void setCustomerFrom(Integer customerFrom) {
		this.customerFrom = customerFrom;
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

	public Integer getContractType() {
		return contractType;
	}

	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}

    private String getStrByCode(String code) {
        String value = "";
        if (StringUtil.isNotEmpty(code)) {
            String[] typeArr = code.split(",");
            for (String type : typeArr) {
                if (StringUtil.isEmpty(value)) {
                    value = SystemParam.getDicValueByDicCode(type);
                } else {
                    value = value + "," + SystemParam.getDicValueByDicCode(type);
                }
            }
        }
        return value;
    }

	public String getFyReportId() {
		return fyReportId;
	}

	public void setFyReportId(String fyReportId) {
		this.fyReportId = fyReportId;
	}

	public String getReportAgent() {
		return reportAgent;
	}

	public void setReportAgent(String reportAgent) {
		this.reportAgent = reportAgent;
	}

	public String getReportAgentTel() {
		return reportAgentTel;
	}

	public void setReportAgentTel(String reportAgentTel) {
		this.reportAgentTel = reportAgentTel;
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

	public String getCenterGroupName() {
		return centerGroupName;
	}

	public void setCenterGroupName(String centerGroupName) {
		this.centerGroupName = centerGroupName;
	}

	public String getBuildingNo() {
		return buildingNo;
	}

	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}

	public BigDecimal getRoughArea() {
		return roughArea;
	}

	public void setRoughArea(BigDecimal roughArea) {
		this.roughArea = roughArea;
	}

	public BigDecimal getRoughAmount() {
		return roughAmount;
	}

	public void setRoughAmount(BigDecimal roughAmount) {
		this.roughAmount = roughAmount;
	}

    public String getRoughAuditStatus() {
        return roughAuditStatus;
    }

    public void setRoughAuditStatus(String roughAuditStatus) {
        this.roughAuditStatus = roughAuditStatus;
    }

    public List<FileRecordMainDto> getPicList() {
        return picList;
    }

    public void setPicList(List<FileRecordMainDto> picList) {
        this.picList = picList;
    }

	public Date getRoughAuditTime() {
		return roughAuditTime;
	}

	public void setRoughAuditTime(Date roughAuditTime) {
		this.roughAuditTime = roughAuditTime;
	}

	public Long getRoughAuditId() {
		return roughAuditId;
	}

	public void setRoughAuditId(Long roughAuditId) {
		this.roughAuditId = roughAuditId;
	}

	public String getRoughAuditDesc() {
		return roughAuditDesc;
	}

	public void setRoughAuditDesc(String roughAuditDesc) {
		this.roughAuditDesc = roughAuditDesc;
	}

	public String getBrokerageStatus() {
		return brokerageStatus;
	}

	public void setBrokerageStatus(String brokerageStatus) {
		this.brokerageStatus = brokerageStatus;
	}

	public Integer getBrokerageUptEmpId() {
		return brokerageUptEmpId;
	}

	public void setBrokerageUptEmpId(Integer brokerageUptEmpId) {
		this.brokerageUptEmpId = brokerageUptEmpId;
	}

	public Date getBrokerageUptDt() {
		return brokerageUptDt;
	}

	public void setBrokerageUptDt(Date brokerageUptDt) {
		this.brokerageUptDt = brokerageUptDt;
	}

	public String getBrokerageYm() {
		return brokerageYm;
	}

	public void setBrokerageYm(String brokerageYm) {
		this.brokerageYm = brokerageYm;
	}

	public String getBrokerageSendStatus() {
		return brokerageSendStatus;
	}

	public void setBrokerageSendStatus(String brokerageSendStatus) {
		this.brokerageSendStatus = brokerageSendStatus;
	}

	public String getCustomerFromNm() {
		return customerFromNm;
	}

	public void setCustomerFromNm(String customerFromNm) {
		this.customerFromNm = customerFromNm;
	}

	public String getRoughAuditUserCode() {
		return roughAuditUserCode;
	}

	public void setRoughAuditUserCode(String roughAuditUserCode) {
		this.roughAuditUserCode = roughAuditUserCode;
	}

	public String getRoughAuditUserName() {
		return roughAuditUserName;
	}

	public void setRoughAuditUserName(String roughAuditUserName) {
		this.roughAuditUserName = roughAuditUserName;
	}

	public String getRoughAuditStatusNm() {
		return roughAuditStatusNm;
	}

	public void setRoughAuditStatusNm(String roughAuditStatusNm) {
		this.roughAuditStatusNm = roughAuditStatusNm;
	}

	public String getBrokerageStatusNm() {
		return brokerageStatusNm;
	}

	public void setBrokerageStatusNm(String brokerageStatusNm) {
		this.brokerageStatusNm = brokerageStatusNm;
	}

	public List<FangyouReportFile> getFangyouFileList() {
		return fangyouFileList;
	}

	public void setFangyouFileList(List<FangyouReportFile> fangyouFileList) {
		this.fangyouFileList = fangyouFileList;
	}

	public String getBrokerageUptEmpNm() {
		return brokerageUptEmpNm;
	}

	public void setBrokerageUptEmpNm(String brokerageUptEmpNm) {
		this.brokerageUptEmpNm = brokerageUptEmpNm;
	}

	public Integer getBrokerageSendCount() {
		return brokerageSendCount;
	}

	public void setBrokerageSendCount(Integer brokerageSendCount) {
		this.brokerageSendCount = brokerageSendCount;
	}
	//楼盘业绩城市编号,大定审核时候使用
	private String estateCityNo;
	public String getEstateCityNo() {
		return estateCityNo;
	}
	public void setEstateCityNo(String estateCityNo) {
		this.estateCityNo = estateCityNo;
	}

	public Date getRoughInputDate() {
		return roughInputDate;
	}

	public void setRoughInputDate(Date roughInputDate) {
		this.roughInputDate = roughInputDate;
	}

	public String getCenterGroupId() {
		return centerGroupId;
	}

	public void setCenterGroupId(String centerGroupId) {
		this.centerGroupId = centerGroupId;
	}
	//业绩归属人所属中心
	private Integer centerId;
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	//第二组客户置为null的专用，
	private String customerTwoFlag;
	public String getCustomerTwoFlag() {
		return customerTwoFlag;
	}
	public void setCustomerTwoFlag(String customerTwoFlag) {
		this.customerTwoFlag = customerTwoFlag;
	}
	private String accountProject;
	private String accountProjectNo;

	public String getAccountProject() {
		return accountProject;
	}

	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}

	public String getAccountProjectNo() {
		return accountProjectNo;
	}

	public void setAccountProjectNo(String accountProjectNo) {
		this.accountProjectNo = accountProjectNo;
	}

	// 门店编号
	private String storeNo;
	private String contractTypeNm;

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getContractTypeNm() {
		return SystemParam.getDicValueByDicCode(String.valueOf(contractType));
	}

	public void setContractTypeNm(String contractTypeNm) {
		this.contractTypeNm = contractTypeNm;
	}

	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}


	private String buildingNoId;
	private Integer isWrap;

	public String getBuildingNoId() {
		return buildingNoId;
	}

	public void setBuildingNoId(String buildingNoId) {
		this.buildingNoId = buildingNoId;
	}

	public Integer getIsWrap() {
		return isWrap;
	}

	public void setIsWrap(Integer isWrap) {
		this.isWrap = isWrap;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public Integer getFyCenterId() {
		return fyCenterId;
	}

	public void setFyCenterId(Integer fyCenterId) {
		this.fyCenterId = fyCenterId;
	}

	public String getFyCenterName() {
		return fyCenterName;
	}

	public void setFyCenterName(String fyCenterName) {
		this.fyCenterName = fyCenterName;
	}

	public String getHtedition() {
		return htedition;
	}

	public void setHtedition(String htedition) {
		this.htedition = htedition;
	}
    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }


	private Integer planId;
	public Integer getPlanId() {
		return planId;
	}
	public void setPlanId(Integer planId) {
		this.planId = planId;
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

	public String getPartnerAbbreviationNm() {
		return partnerAbbreviationNm;
	}

	public void setPartnerAbbreviationNm(String partnerAbbreviationNm) {
		this.partnerAbbreviationNm = partnerAbbreviationNm;
	}

	public String getPartnerNm() {
		return partnerNm;
	}

	public void setPartnerNm(String partnerNm) {
		this.partnerNm = partnerNm;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}

	public String getDevCompany() {
		return devCompany;
	}

	public void setDevCompany(String devCompany) {
		this.devCompany = devCompany;
	}

	public String getPreBack() {
		return preBack;
	}

	public void setPreBack(String preBack) {
		this.preBack = preBack;
	}

	private String isGlobalControl;
	private BigDecimal dyRatio;//垫佣比例

	public String getIsGlobalControl() {
		return isGlobalControl;
	}

	public void setIsGlobalControl(String isGlobalControl) {
		this.isGlobalControl = isGlobalControl;
	}

	public BigDecimal getDyRatio() {
		return dyRatio;
	}

	public void setDyRatio(BigDecimal dyRatio) {
		this.dyRatio = dyRatio;
	}
}