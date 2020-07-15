package cn.com.eju.deal.houseLinkage.report.model;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;

import java.math.BigDecimal;
import java.util.Date;

public class ReportSearchResult  {
	
	private Integer id;
    // 城市编号
    private String cityNo;
    // 城市名
    private String cityName;
	//楼盘地址
	private String realityCityNo;
	private String realityCityNm;
    // 报备编码
    private String reportId;
    // 楼盘类型
    private String estateType;
    // 楼盘ID
    private String estateId;
    // 楼盘名
    private String estateNm;
    // 公司ID
    private String companyId;
    // 公司名
    private String companyNm;
    // 员工名
    private String empNm;
    // 客户id
    private String customerId;
    //客户来源
    private Integer customerFrom;
    // 客户名
    private String customerNm;
    // 客户手机
    private String customerTel;
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
    
    // 带看时间
    private Date validRelationDate;
    // 隐号设置
    private String hideNumberFlg;
    
    // 门店维护人
    private String contactId;
    // 门店维护人姓名
    private String contactNm;
    
    // 以下是扩展字段
    
    // 楼盘类型名称
    private String estateTypeNm;
    // 最新进度名称
    private String latestProgressNm;
	// 确认状态名称
    private String confirmStatusNm;
    
    // 区域ID
    private String districtId;
    // 部门名
    private String deptNm;
    
    // 楼市号
    private String buildingNo;
    // 成销面积
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
	//楼盘地址
	private String address;
	//门店地址
	private String storeAddress;
	//维护人所属中心
	private String centerName;

	//大定审核状态
	private String roughAuditStatus;

	//大定审核时间
	private Date roughAuditTime;

	//大定审核人
	private Long roughAuditId;

	private String roughAuditName;

	//大定审核原因
	private String roughAuditDesc;
	
	private String brokerageStatus;
	private Integer brokerageUptEmpId;
	private Date brokerageUptDt;
	private String brokerageYm;
	private String brokerageSendStatus;//结佣发送状态
	private String brokerageStatusNm;

	private Date roughInputDate;


	private String projectDepartmentName;

	private String acCityNo;
	private Boolean rebackFlag;

	public Boolean getRebackFlag() {
		return rebackFlag;
	}

	public void setRebackFlag(Boolean rebackFlag) {
		this.rebackFlag = rebackFlag;
	}
	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getRoughArea()
    {
        return roughArea;
    }
    public void setRoughArea(BigDecimal roughArea)
    {
        this.roughArea = roughArea;
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
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityName() {
		return SystemParam.getCityNameByCityNo(cityNo);
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
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
	public String getEstateTypeNm() {
//		return SystemParam.getDicValueByDicCode(String.valueOf(estateType));
		return getStrByCode(estateType);
	}
	public void setEstateTypeNm(String estateTypeNm) {
		this.estateTypeNm = estateTypeNm;
	}
	public String getLatestProgressNm() {
		return latestProgressNm;
	}
	public void setLatestProgressNm(String latestProgressNm) {
		this.latestProgressNm = latestProgressNm;
	}
	public String getConfirmStatusNm() {
		return confirmStatusNm;
	}
	public void setConfirmStatusNm(String confirmStatusNm) {
		this.confirmStatusNm = confirmStatusNm;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getDeptNm() {
		return deptNm;
	}
	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}
	public Date getValidRelationDate() {
		return validRelationDate;
	}
	public void setValidRelationDate(Date validRelationDate) {
		this.validRelationDate = validRelationDate;
	}
	public String getHideNumberFlg() {
		return hideNumberFlg;
	}
	public void setHideNumberFlg(String hideNumberFlg) {
		this.hideNumberFlg = hideNumberFlg;
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
	public String getRealityCityNo() {
		return realityCityNo;
	}
	public void setRealityCityNo(String realityCityNo) {
		this.realityCityNo = realityCityNo;
	}
	public String getRealityCityNm() {
		return SystemParam.getCityNameByCityNo(realityCityNo);
	}
	public void setRealityCityNm(String realityCityNm) {
		this.realityCityNm = realityCityNm;
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

	public String getRoughAuditStatus() {
		return roughAuditStatus;
	}

	public void setRoughAuditStatus(String roughAuditStatus) {
		this.roughAuditStatus = roughAuditStatus;
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

	public String getRoughAuditName() {
		return roughAuditName;
	}

	public void setRoughAuditName(String roughAuditName) {
		this.roughAuditName = roughAuditName;
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

	public String getBrokerageStatusNm() {
		return brokerageStatusNm;
	}

	public void setBrokerageStatusNm(String brokerageStatusNm) {
		this.brokerageStatusNm = brokerageStatusNm;
	}
	private Integer reportDetailId;

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

	public String getProjectDepartmentName() {
		return projectDepartmentName;
	}

	public void setProjectDepartmentName(String projectDepartmentName) {
		this.projectDepartmentName = projectDepartmentName;
	}

}