package cn.com.eju.deal.scene.estate.model;

import cn.com.eju.deal.base.support.SystemParam;

import java.math.BigDecimal;
import java.util.Date;

public class SceneRecognitionSearchResult  {
	private Integer id;
	//报备明细id
	private Integer reportDetailId;
	
	// 楼盘ID
	private String estateId;
	// 报备编码
	private String reportId;
	// 楼盘名
	private String estateNm;
	// 公司编号
	private String companyId;
	// 公司名称
	private String companyNm;
	// 部门名
	private String deptNm;
	// 员工名
	private String empNm;
	// 客户编号
	private String customerId;
	// 客户名
	private String customerNm;
	// 客户电话
	private String customerTel;
	// 最新进度
	private Integer latestProgress;
	private String latestProgressNm;
	// 确认状态
	private Integer confirmStatus;
	private String confirmStatusNm;
	// 报备日
	private Date reportDate;
	private String reportDate10;// 10位
	// 跟进日
	private Date followDate;
	// 有效期
	private Date validDate;
	// 隐号报备
	private String hideNumberFlg;
	// 有效带看时间
	private String validRelationDate;
	// 带看保护期
	private String relationProtectPeriod;
	
    // 楼市号
    private String buildingNo;
    // 面积
    private BigDecimal area;
    // 大定总价
    private BigDecimal roughAmount;
    // 成销总价
    private BigDecimal dealAmount;
    
    //大定时间
    private Date roughDate;
    //成销时间
    private Date dealDate;
    //带看时间
    private Date seeDate;
    //认筹时间
    private Date pledgedDate;
    // 大定面积
    private BigDecimal roughArea;
	//退筹时间
	private Date pledgedBackDate;
	//退定时间
	private Date roughBackDate;
	//退房时间
	private Date dealBackDate;
	
	private String contactNm;
	
	//报备来源
	private Integer customerFrom;
    private String fyReportId;
    private String reportAgent;
    private String reportAgentTel;

    private String roughAuditStatus;
    
    private String brokerageStatus;

    private Date roughInputDate;
	private Date signDate;
	private String acCityNo;
	private Boolean rebackFlag;

	public Boolean getRebackFlag() {
		return rebackFlag;
	}

	public void setRebackFlag(Boolean rebackFlag) {
		this.rebackFlag = rebackFlag;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	private Integer isSpecialProject;

	public Integer getIsSpecialProject() {
		return isSpecialProject;
	}

	public void setIsSpecialProject(Integer isSpecialProject) {
		this.isSpecialProject = isSpecialProject;
	}

	public Date getRoughDate()
    {
        return roughDate;
    }
    public void setRoughDate(Date roughDate)
    {
        this.roughDate = roughDate;
    }
    public Date getDealDate()
    {
        return dealDate;
    }
    public void setDealDate(Date dealDate)
    {
        this.dealDate = dealDate;
    }
    public Date getSeeDate()
    {
        return seeDate;
    }
    public void setSeeDate(Date seeDate)
    {
        this.seeDate = seeDate;
    }
    public Date getPledgedDate()
    {
        return pledgedDate;
    }
    public void setPledgedDate(Date pledgedDate)
    {
        this.pledgedDate = pledgedDate;
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
	public Date getReportDate() {
		return reportDate;
	}
	public void setReportDate(Date reportDate) {
		this.reportDate = reportDate;
	}
	public String getReportDate10() {
		return reportDate10;
	}
	public void setReportDate10(String reportDate10) {
		this.reportDate10 = reportDate10;
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
	public String getHideNumberFlg() {
		return hideNumberFlg;
	}
	public void setHideNumberFlg(String hideNumberFlg) {
		this.hideNumberFlg = hideNumberFlg;
	}
	public String getValidRelationDate() {
		return validRelationDate;
	}
	public void setValidRelationDate(String validRelationDate) {
		this.validRelationDate = validRelationDate;
	}
	public String getRelationProtectPeriod() {
		return relationProtectPeriod;
	}
	public void setRelationProtectPeriod(String relationProtectPeriod) {
		this.relationProtectPeriod = relationProtectPeriod;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	//门店维护人
	private String maintainerName;
	//门店地址
	private String address;
	//门店详细地址
	private String addressDetail;
	//维护人所属中心
	private String centerName;

	public String getMaintainerName() {
		return maintainerName;
	}
	public void setMaintainerName(String maintainerName) {
		this.maintainerName = maintainerName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}
    /**
     * @return the contactNm
     */
    public String getContactNm() {
        return contactNm;
    }
    /**
     * @param contactNm the contactNm to set
     */
    public void setContactNm(String contactNm) {
        this.contactNm = contactNm;
    }
	public Integer getCustomerFrom() {
		return customerFrom;
	}
	public void setCustomerFrom(Integer customerFrom) {
		this.customerFrom = customerFrom;
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

	public String getRoughAuditStatus() {
		return roughAuditStatus;
	}

	public void setRoughAuditStatus(String roughAuditStatus) {
		this.roughAuditStatus = roughAuditStatus;
	}
	public String getBrokerageStatus() {
		return brokerageStatus;
	}
	public void setBrokerageStatus(String brokerageStatus) {
		this.brokerageStatus = brokerageStatus;
	}
	public Integer getReportDetailId() {
		return reportDetailId;
	}
	public void setReportDetailId(Integer reportDetailId) {
		this.reportDetailId = reportDetailId;
	}

	public Date getRoughInputDate() {
		return roughInputDate;
	}

	public void setRoughInputDate(Date roughInputDate) {
		this.roughInputDate = roughInputDate;
	}


	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}

	public Date getBrokerageUptDt() {
		return brokerageUptDt;
	}

	public void setBrokerageUptDt(Date brokerageUptDt) {
		this.brokerageUptDt = brokerageUptDt;
	}

	private Date brokerageUptDt;
}