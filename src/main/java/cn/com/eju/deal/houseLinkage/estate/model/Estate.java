package cn.com.eju.deal.houseLinkage.estate.model;

import cn.com.eju.deal.base.support.SystemParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Estate implements Serializable{

	private Integer id;
	private String estateId;
	// 区域
	private String districtId;
	// 板块
	private String areaId;
	// 楼盘名
	private String estateNm;
	// 审核状态
	private Integer auditStatus;
	private String auditMemo;
	// 发布状态
	private Integer releaseStatus;
	// 下架说明
	private String releaseOffMemo;
	// 发布时间
	private String releaseDt;
	// 销售状态
	private Integer salesStatus;
	// 合作方
	private Integer partner;
	// 合作人
	private String partnerNm;
	// 合作期
	private Date cooperationDtStart;
	private Date cooperationDtEnd;

    // 合作模式
    private Integer cooperationMode;

	private String cityNo;

	//楼盘地址
	private String realityCityNo;

	private Integer deptId;
	private Integer empId;
	// 均价
	private BigDecimal salePriceUnit;
	// 总价段
	private BigDecimal salePriceUnitMin;
	private BigDecimal salePriceUnitMax;
	// 地址
	private String address;
	// 楼盘坐标X
	private String addressCoordinateX;
	// 楼盘坐标Y
	private String addressCoordinateY;
	// 标签
	private String mark;
	private Integer openKbn;
	// 开盘时间
	private Date openTime;
	private Integer houseTransferKbn;
	// 交房时间
	private String houseTransferTime;
	// 项目简介
	private String projectDescription;
	// 户型(查询用）
	private String estateTypeSearch;
	// 开发商
	private String devCompany;
	// 案场地址
	private String fieldAddress;
	// 合作有效期
	private BigDecimal cooperationExpDt;
	// 预售许可
	private Integer preSalePermitKbn;
	// 建筑类型
	private String typeKbn;
	// 物业类型
	private String mgtKbn;
	// 产权年限
	private String ownYearKbn;
	// 装修情况
	private String decorationKbn;
	// 规划户数
	private Integer houseCnt;
	// 车位情况
	private Integer parkCnt;
	// 停车费
	private BigDecimal parkFee;
	// 物业公司
	private String mgtCompany;
	// 容积率
	private String rateFAR;
	// 绿化率
	private String rateGreen;
	// 物业费用
	private BigDecimal mgtPrice;
	// 梯户
	private String staircaseHousehold;
	// 供暖方式
	private Integer heatKbn;
	// 水电燃气
	private Integer hydropowerGasKbn;
	// 案场归属部门
	private Integer sceneDeptId;
	// 案场归属人
	private Integer sceneEmpId;
	private Integer crtEmpId;
	private Integer uptEmpId;
	private Boolean delFlg;
	private Date crtDt;
	private Date uptDt;// 日期类型转换

	 //以下是扩展字段
	// 城市名称
	private String cityNm;
	//楼盘地址
	private String realityCityNm;
	// 区域名称
	private String districtNm;
	// 板块名称
	private String areaNm;
	// 合作期
	private String strCooperationDtStart;
	private String strCooperationDtEnd;
	// 开盘时间
	private String strOpenTime;
	// 带看日期
	private String strRelationDtStart;
	private String strRelationDtEnd;
	// 认筹日期
	private String strPledgedDtStart;
	private String strPledgedDtEnd;
	// 认购日期
	private String strSubscribedDtStart;
	private String strSubscribedDtEnd;
	// 报备日期
	private String strReportDtStart;
	private String strReportDtEnd;
	private String strCrtDt;
	private String strUptDt;

	// 备案名
	private String recordName;
	//推广名
	private String promotionName;
	//签约名
	private String signName;

//	private Integer cooperationMode;  //合作模式
	private Date startDate;     //跟单日期
	private Date endDate;     //结案日期
	private Integer projectDepartmentId;   //归属项目部ID
	private String projectDepartment;   //归属项目部名
	//Add By QJP 2017/08/07 oa返回字段 start
    private Integer projectStatus; //项目状态
    private Date signDate; //签约日期
	private String oaSignNo;  //oa签约单据号
	private Date measureDate;   //测算日期
    private String oaMeasureNo;   //oa测算单据号
    private Date budgetDate;     //预算日期
	private String oaBudgetNo;    //oa预算单据号
    //Add By QJP 2017/08/07 oa返回字段  end
	private String projectNo;   //项目编号
	private String devCompanyBroker; //开发商对接人
	private String devCompanyBrokerTel; //开发商对接人电话
	private String empTel; //项目负责人电话

	private String partnerContactNm;
    private String partnerContactTel;

    private String opEstateId;
    private String opEstateNm;

	private Integer estatePosition;
	private String countryNo;
	private String countryNm;

	//核算主体
	private String accountProject;
	private String accountProjectNo;

	//是否垫佣
	private Boolean isYjDy;
	//垫佣金额
	private BigDecimal yjDyAmount;

	private Boolean isPureDy;

//	是否可垫佣甲方    1是 0否
	private Integer custodyFlg;
	//预计当季大定金额
	private BigDecimal subscribedThisQuarter;
	//预计当年大定金额
	private BigDecimal subscribedThisYear;
	//预计明年大定金额
	private BigDecimal subscribedNextYear;

	private BigDecimal subscribedQuarter1;//预计第一季度大定金额
	private BigDecimal subscribedQuarter2;//预计第二季度大定金额
	private BigDecimal subscribedQuarter3;//预计第三季度大定金额
	private BigDecimal subscribedQuarter4;//预计第四季度大定金额

	 //开发商全称
    private String developerName;

    //垫佣甲方简称
    private Integer mattressNailId;

  //垫佣甲方简称
    private String mattressNailName;

    // 业务模式
	private Integer businessModel;

	// 是否特殊项目
    private int isSpecialProject;

	//开发商ID
	private String devCompanyId;
	//刷筹
	private Integer brushRaiseFlag;
	//共场
	private Integer totalFieldFlag;
	//是否借佣
	private Integer isExcuseCommision;
	private Integer centerId;
	private String centerName;
	private String partnerAbbreviationCode;
	private String partnerAbbreviationNm;
	
	//项目负责人
    private Integer projectLeaderEmpId;
    private String projectLeaderName; 
    //项目负责人电话
    private String projectLeaderTel;


    private Integer developerBrandId;
    private String developerBrandName;

	private BigDecimal vendibilityAmount;
	private BigDecimal monthRoughAmount;
	private String cooperationStatus;
	private String projectInfoDesc;
	private String requiredForSupport;

	public Integer getProjectLeaderEmpId() {
		return projectLeaderEmpId;
	}

	public void setProjectLeaderEmpId(Integer projectLeaderEmpId) {
		this.projectLeaderEmpId = projectLeaderEmpId;
	}

	public String getProjectLeaderName() {
		return projectLeaderName;
	}

	public void setProjectLeaderName(String projectLeaderName) {
		this.projectLeaderName = projectLeaderName;
	}

	public String getProjectLeaderTel() {
		return projectLeaderTel;
	}

	public void setProjectLeaderTel(String projectLeaderTel) {
		this.projectLeaderTel = projectLeaderTel;
	}

	public BigDecimal getSubscribedQuarter1() {
		return subscribedQuarter1;
	}

	public void setSubscribedQuarter1(BigDecimal subscribedQuarter1) {
		this.subscribedQuarter1 = subscribedQuarter1;
	}

	public BigDecimal getSubscribedQuarter2() {
		return subscribedQuarter2;
	}

	public void setSubscribedQuarter2(BigDecimal subscribedQuarter2) {
		this.subscribedQuarter2 = subscribedQuarter2;
	}

	public BigDecimal getSubscribedQuarter3() {
		return subscribedQuarter3;
	}

	public void setSubscribedQuarter3(BigDecimal subscribedQuarter3) {
		this.subscribedQuarter3 = subscribedQuarter3;
	}

	public BigDecimal getSubscribedQuarter4() {
		return subscribedQuarter4;
	}

	public void setSubscribedQuarter4(BigDecimal subscribedQuarter4) {
		this.subscribedQuarter4 = subscribedQuarter4;
	}

	public BigDecimal getSubscribedThisQuarter() {
		return subscribedThisQuarter;
	}

	public void setSubscribedThisQuarter(BigDecimal subscribedThisQuarter) {
		this.subscribedThisQuarter = subscribedThisQuarter;
	}

	public String getMattressNailName() {
		return mattressNailName;
	}

	public void setMattressNailName(String mattressNailName) {
		this.mattressNailName = mattressNailName;
	}


	public Integer getMattressNailId() {
		return mattressNailId;
	}

	public void setMattressNailId(Integer mattressNailId) {
		this.mattressNailId = mattressNailId;
	}

	public String getDeveloperName() {
		return developerName;
	}

	public void setDeveloperName(String developerName) {
		this.developerName = developerName;
	}
	public Integer getCustodyFlg() {
		return custodyFlg;
	}

	public void setCustodyFlg(Integer custodyFlg) {
		this.custodyFlg = custodyFlg;
	}

	public BigDecimal getSubscribedThisYear() {
		return subscribedThisYear;
	}

	public void setSubscribedThisYear(BigDecimal subscribedThisYear) {
		this.subscribedThisYear = subscribedThisYear;
	}

	public BigDecimal getSubscribedNextYear() {
		return subscribedNextYear;
	}

	public void setSubscribedNextYear(BigDecimal subscribedNextYear) {
		this.subscribedNextYear = subscribedNextYear;
	}

	public Boolean getPureDy() {
		return isPureDy;
	}

	public void setPureDy(Boolean pureDy) {
		isPureDy = pureDy;
	}

	public Boolean getYjDy() {
		return isYjDy;
	}

	public void setYjDy(Boolean yjDy) {
		isYjDy = yjDy;
	}

	public BigDecimal getYjDyAmount() {
		return yjDyAmount;
	}

	public void setYjDyAmount(BigDecimal yjDyAmount) {
		this.yjDyAmount = yjDyAmount;
	}

	/**
     * @return  the 【partnerContactNm】
     */
    public String getPartnerContactNm() {

        return partnerContactNm;
    }
    /**
     * @param partnerContactNm the 【partnerContactNm】 to set
     */
    public void setPartnerContactNm(String partnerContactNm) {

        this.partnerContactNm = partnerContactNm;
    }
    /**
     * @return  the 【partnerContactTel】
     */
    public String getPartnerContactTel() {

        return partnerContactTel;
    }
    /**
     * @param partnerContactTel the 【partnerContactTel】 to set
     */
    public void setPartnerContactTel(String partnerContactTel) {

        this.partnerContactTel = partnerContactTel;
    }
    public String getDevCompanyBroker() {
		return devCompanyBroker;
	}
	public void setDevCompanyBroker(String devCompanyBroker) {
		this.devCompanyBroker = devCompanyBroker;
	}
	public String getDevCompanyBrokerTel() {
		return devCompanyBrokerTel;
	}
	public void setDevCompanyBrokerTel(String devCompanyBrokerTel) {
		this.devCompanyBrokerTel = devCompanyBrokerTel;
	}
	public String getEmpTel() {
		return empTel;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public Integer getProjectDepartmentId() {
		return projectDepartmentId;
	}
	public void setProjectDepartmentId(Integer projectDepartmentId) {
	    this.projectDepartmentId = projectDepartmentId;
	}
	public String getProjectDepartment() {
		return projectDepartment;
	}
	public void setProjectDepartment(String projectDepartment) {
		this.projectDepartment = projectDepartment;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getOaBudgetNo() {
		return oaBudgetNo;
	}
	public void setOaBudgetNo(String oaBudgetNo) {
		this.oaBudgetNo = oaBudgetNo;
	}
	public Date getBudgetDate() {
		return budgetDate;
	}
	public void setBudgetDate(Date budgetDate) {
		this.budgetDate = budgetDate;
	}
    public String getOaSignNo() {
		return oaSignNo;
	}
	public void setOaSignNo(String oaSignNo) {
		this.oaSignNo = oaSignNo;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public Date getMeasureDate() {
		return measureDate;
	}
	public void setMeasureDate(Date measureDate) {
		this.measureDate = measureDate;
	}
	public String getOaMeasureNo() {
		return oaMeasureNo;
	}
	public void setOaMeasureNo(String oaMeasureNo) {
		this.oaMeasureNo = oaMeasureNo;
	}
	public String getRecordName()
    {
        return recordName;
    }
    public void setRecordName(String recordName)
    {
        this.recordName = recordName;
    }
    public String getPromotionName()
    {
        return promotionName;
    }
    public void setPromotionName(String promotionName)
    {
        this.promotionName = promotionName;
    }
    public String getSignName()
    {
        return signName;
    }
    public void setSignName(String signName)
    {
        this.signName = signName;
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
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditMemo() {
		return auditMemo;
	}
	public void setAuditMemo(String auditMemo) {
		this.auditMemo = auditMemo;
	}
	public Integer getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(Integer releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public String getReleaseOffMemo() {
		return releaseOffMemo;
	}
	public void setReleaseOffMemo(String releaseOffMemo) {
		this.releaseOffMemo = releaseOffMemo;
	}
	public String getReleaseDt() {
		return releaseDt;
	}
	public void setReleaseDt(String releaseDt) {
		this.releaseDt = releaseDt;
	}
	public Integer getPartner() {
		return partner;
	}
	public void setPartner(Integer partner) {
		this.partner = partner;
	}
	public String getPartnerNm() {
		return partnerNm;
	}
	public void setPartnerNm(String partnerNm) {
		this.partnerNm = partnerNm;
	}
	public Date getCooperationDtStart() {
		return cooperationDtStart;
	}
	public void setCooperationDtStart(Date cooperationDtStart) {
		this.cooperationDtStart = cooperationDtStart;
	}
	public Date getCooperationDtEnd() {
		return cooperationDtEnd;
	}
	public void setCooperationDtEnd(Date cooperationDtEnd) {
		this.cooperationDtEnd = cooperationDtEnd;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public Integer getDeptId() {
		return deptId;
	}
	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public BigDecimal getSalePriceUnit() {
		return salePriceUnit;
	}
	public void setSalePriceUnit(BigDecimal salePriceUnit) {
		this.salePriceUnit = salePriceUnit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressCoordinateX() {
		return addressCoordinateX;
	}
	public void setAddressCoordinateX(String addressCoordinateX) {
		this.addressCoordinateX = addressCoordinateX;
	}
	public String getAddressCoordinateY() {
		return addressCoordinateY;
	}
	public void setAddressCoordinateY(String addressCoordinateY) {
		this.addressCoordinateY = addressCoordinateY;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}

	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public String getHouseTransferTime() {
		return houseTransferTime;
	}
	public void setHouseTransferTime(String houseTransferTime) {
		this.houseTransferTime = houseTransferTime;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getEstateTypeSearch() {
		return estateTypeSearch;
	}
	public void setEstateTypeSearch(String estateTypeSearch) {
		this.estateTypeSearch = estateTypeSearch;
	}
	public String getDevCompany() {
		return devCompany;
	}
	public void setDevCompany(String devCompany) {
		this.devCompany = devCompany;
	}
	public String getFieldAddress() {
		return fieldAddress;
	}
	public void setFieldAddress(String fieldAddress) {
		this.fieldAddress = fieldAddress;
	}
	public BigDecimal getCooperationExpDt() {
		return cooperationExpDt;
	}
	public void setCooperationExpDt(BigDecimal cooperationExpDt) {
		this.cooperationExpDt = cooperationExpDt;
	}
	public String getTypeKbn() {
		return typeKbn;
	}
	public void setTypeKbn(String typeKbn) {
		this.typeKbn = typeKbn;
	}
	public String getMgtKbn() {
		return mgtKbn;
	}
	public void setMgtKbn(String mgtKbn) {
		this.mgtKbn = mgtKbn;
	}
	public BigDecimal getParkFee() {
		return parkFee;
	}
	public void setParkFee(BigDecimal parkFee) {
		this.parkFee = parkFee;
	}
	public String getMgtCompany() {
		return mgtCompany;
	}
	public void setMgtCompany(String mgtCompany) {
		this.mgtCompany = mgtCompany;
	}
	public String getRateFAR() {
		return rateFAR;
	}
	public void setRateFAR(String rateFAR) {
		this.rateFAR = rateFAR;
	}
	public String getRateGreen() {
		return rateGreen;
	}
	public void setRateGreen(String rateGreen) {
		this.rateGreen = rateGreen;
	}
	public BigDecimal getMgtPrice() {
		return mgtPrice;
	}
	public void setMgtPrice(BigDecimal mgtPrice) {
		this.mgtPrice = mgtPrice;
	}
	public String getStaircaseHousehold() {
		return staircaseHousehold;
	}
	public void setStaircaseHousehold(String staircaseHousehold) {
		this.staircaseHousehold = staircaseHousehold;
	}
	public Integer getSceneDeptId() {
		return sceneDeptId;
	}
	public void setSceneDeptId(Integer sceneDeptId) {
		this.sceneDeptId = sceneDeptId;
	}
	public Integer getSceneEmpId() {
		return sceneEmpId;
	}
	public void setSceneEmpId(Integer sceneEmpId) {
		this.sceneEmpId = sceneEmpId;
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
	public String getDistrictNm() {
		return SystemParam.getDistrictNameByDistrictNo(districtId);
	}
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	public String getAreaNm() {
		return SystemParam.getAreaNameByAreaNo(areaId);
	}
	public void setAreaNm(String areaNm) {
		this.areaNm = areaNm;
	}
	public String getStrCooperationDtStart() {
		return strCooperationDtStart;
	}
	public void setStrCooperationDtStart(String strCooperationDtStart) {
		this.strCooperationDtStart = strCooperationDtStart;
	}
	public String getStrCooperationDtEnd() {
		return strCooperationDtEnd;
	}
	public void setStrCooperationDtEnd(String strCooperationDtEnd) {
		this.strCooperationDtEnd = strCooperationDtEnd;
	}
	public String getStrOpenTime() {
		return strOpenTime;
	}
	public void setStrOpenTime(String strOpenTime) {
		this.strOpenTime = strOpenTime;
	}
	public String getStrRelationDtStart() {
		return strRelationDtStart;
	}
	public void setStrRelationDtStart(String strRelationDtStart) {
		this.strRelationDtStart = strRelationDtStart;
	}
	public String getStrRelationDtEnd() {
		return strRelationDtEnd;
	}
	public void setStrRelationDtEnd(String strRelationDtEnd) {
		this.strRelationDtEnd = strRelationDtEnd;
	}
	public String getStrPledgedDtStart() {
		return strPledgedDtStart;
	}
	public void setStrPledgedDtStart(String strPledgedDtStart) {
		this.strPledgedDtStart = strPledgedDtStart;
	}
	public String getStrPledgedDtEnd() {
		return strPledgedDtEnd;
	}
	public void setStrPledgedDtEnd(String strPledgedDtEnd) {
		this.strPledgedDtEnd = strPledgedDtEnd;
	}
	public String getStrSubscribedDtStart() {
		return strSubscribedDtStart;
	}
	public void setStrSubscribedDtStart(String strSubscribedDtStart) {
		this.strSubscribedDtStart = strSubscribedDtStart;
	}
	public String getStrSubscribedDtEnd() {
		return strSubscribedDtEnd;
	}
	public void setStrSubscribedDtEnd(String strSubscribedDtEnd) {
		this.strSubscribedDtEnd = strSubscribedDtEnd;
	}
	public String getStrReportDtStart() {
		return strReportDtStart;
	}
	public void setStrReportDtStart(String strReportDtStart) {
		this.strReportDtStart = strReportDtStart;
	}
	public String getStrReportDtEnd() {
		return strReportDtEnd;
	}
	public void setStrReportDtEnd(String strReportDtEnd) {
		this.strReportDtEnd = strReportDtEnd;
	}
	public String getStrCrtDt() {
		return strCrtDt;
	}
	public void setStrCrtDt(String strCrtDt) {
		this.strCrtDt = strCrtDt;
	}
	public String getStrUptDt() {
		return strUptDt;
	}
	public void setStrUptDt(String strUptDt) {
		this.strUptDt = strUptDt;
	}
    /**
     * @return the salesStatus
     */
    public Integer getSalesStatus()
    {
        return salesStatus;
    }
    /**
     * @param salesStatus the salesStatus to set
     */
    public void setSalesStatus(Integer salesStatus)
    {
        this.salesStatus = salesStatus;
    }
    /**
     * @return the openKbn
     */
    public Integer getOpenKbn()
    {
        return openKbn;
    }
    /**
     * @param openKbn the openKbn to set
     */
    public void setOpenKbn(Integer openKbn)
    {
        this.openKbn = openKbn;
    }
    /**
     * @return the houseTransferKbn
     */
    public Integer getHouseTransferKbn()
    {
        return houseTransferKbn;
    }
    /**
     * @param houseTransferKbn the houseTransferKbn to set
     */
    public void setHouseTransferKbn(Integer houseTransferKbn)
    {
        this.houseTransferKbn = houseTransferKbn;
    }
    /**
     * @return the preSalePermitKbn
     */
    public Integer getPreSalePermitKbn()
    {
        return preSalePermitKbn;
    }
    /**
     * @param preSalePermitKbn the preSalePermitKbn to set
     */
    public void setPreSalePermitKbn(Integer preSalePermitKbn)
    {
        this.preSalePermitKbn = preSalePermitKbn;
    }
    /**
     * @return the ownYearKbn
     */
    public String getOwnYearKbn()
    {
        return ownYearKbn;
    }
    /**
     * @param ownYearKbn the ownYearKbn to set
     */
    public void setOwnYearKbn(String ownYearKbn)
    {
        this.ownYearKbn = ownYearKbn;
    }
    /**
     * @return the decorationKbn
     */
    public String getDecorationKbn()
    {
        return decorationKbn;
    }
    /**
     * @param decorationKbn the decorationKbn to set
     */
    public void setDecorationKbn(String decorationKbn)
    {
        this.decorationKbn = decorationKbn;
    }
    /**
     * @return the houseCnt
     */
    public Integer getHouseCnt()
    {
        return houseCnt;
    }
    /**
     * @param houseCnt the houseCnt to set
     */
    public void setHouseCnt(Integer houseCnt)
    {
        this.houseCnt = houseCnt;
    }
    /**
     * @return the parkCnt
     */
    public Integer getParkCnt()
    {
        return parkCnt;
    }
    /**
     * @param parkCnt the parkCnt to set
     */
    public void setParkCnt(Integer parkCnt)
    {
        this.parkCnt = parkCnt;
    }
    /**
     * @return the heatKbn
     */
    public Integer getHeatKbn()
    {
        return heatKbn;
    }
    /**
     * @param heatKbn the heatKbn to set
     */
    public void setHeatKbn(Integer heatKbn)
    {
        this.heatKbn = heatKbn;
    }
    /**
     * @return the hydropowerGasKbn
     */
    public Integer getHydropowerGasKbn()
    {
        return hydropowerGasKbn;
    }
    /**
     * @param hydropowerGasKbn the hydropowerGasKbn to set
     */
    public void setHydropowerGasKbn(Integer hydropowerGasKbn)
    {
        this.hydropowerGasKbn = hydropowerGasKbn;
    }
	public String getCityNm() {
		return SystemParam.getCityNameByCityNo(cityNo);
	}
	public void setCityNm(String cityNm) {
		this.cityNm = cityNm;
	}
	public BigDecimal getSalePriceUnitMin() {
		return salePriceUnitMin;
	}
	public void setSalePriceUnitMin(BigDecimal salePriceUnitMin) {
		this.salePriceUnitMin = salePriceUnitMin;
	}
	public BigDecimal getSalePriceUnitMax() {
		return salePriceUnitMax;
	}
	public void setSalePriceUnitMax(BigDecimal salePriceUnitMax) {
		this.salePriceUnitMax = salePriceUnitMax;
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
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Integer getCooperationMode() {
		return cooperationMode;
	}
	public void setCooperationMode(Integer cooperationMode) {
		this.cooperationMode = cooperationMode;
	}
	/**
     * 实际开盘时间
     */
    private Date realOpenTime;

	public Date getRealOpenTime() {
		return realOpenTime;
	}

	public void setRealOpenTime(Date realOpenTime) {
		this.realOpenTime = realOpenTime;
	}
    /**
     * @return  the 【opEstateId】
     */
    public String getOpEstateId() {

        return opEstateId;
    }
    /**
     * @param opEstateId the 【opEstateId】 to set
     */
    public void setOpEstateId(String opEstateId) {

        this.opEstateId = opEstateId;
    }
    /**
     * @return  the 【opEstateNm】
     */
    public String getOpEstateNm() {

        return opEstateNm;
    }
    /**
     * @param opEstateNm the 【opEstateNm】 to set
     */
    public void setOpEstateNm(String opEstateNm) {

        this.opEstateNm = opEstateNm;
    }
    private String userName;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getEstatePosition() {
		return estatePosition;
	}

	public void setEstatePosition(Integer estatePosition) {
		this.estatePosition = estatePosition;
	}

	public String getCountryNo() {
		return countryNo;
	}

	public void setCountryNo(String countryNo) {
		this.countryNo = countryNo;
	}

	public String getCountryNm() {
		return countryNm;
	}

	public void setCountryNm(String countryNm) {
		this.countryNm = countryNm;
	}

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
	 // 是否独家
    private Integer particularFlag;
    //是否直签
    private Integer directSignFlag;
    //是否大客户
    private Integer bigCustomerFlag;

	public Integer getParticularFlag() {
		return particularFlag;
	}
	public void setParticularFlag(Integer particularFlag) {
		this.particularFlag = particularFlag;
	}
	public Integer getDirectSignFlag() {
		return directSignFlag;
	}
	public void setDirectSignFlag(Integer directSignFlag) {
		this.directSignFlag = directSignFlag;
	}
	public Integer getBigCustomerFlag() {
		return bigCustomerFlag;
	}
	public void setBigCustomerFlag(Integer bigCustomerFlag) {
		this.bigCustomerFlag = bigCustomerFlag;
	}
	private Integer bigCustomerId;

	public Integer getBigCustomerId() {
		return bigCustomerId;
	}
	public void setBigCustomerId(Integer bigCustomerId) {
		this.bigCustomerId = bigCustomerId;
	}

	public Integer getBusinessModel() {
		return businessModel;
	}

	public void setBusinessModel(Integer businessModel) {
		this.businessModel = businessModel;
	}
	public int getIsSpecialProject() {
		return isSpecialProject;
	}

	public void setIsSpecialProject(int isSpecialProject) {
		this.isSpecialProject = isSpecialProject;
	}

	public String getDevCompanyId() {
		return devCompanyId;
	}

	public void setDevCompanyId(String devCompanyId) {
		this.devCompanyId = devCompanyId;
	}

	public Integer getBrushRaiseFlag() {
		return brushRaiseFlag;
	}

	public void setBrushRaiseFlag(Integer brushRaiseFlag) {
		this.brushRaiseFlag = brushRaiseFlag;
	}

	public Integer getTotalFieldFlag() {
		return totalFieldFlag;
	}

	public void setTotalFieldFlag(Integer totalFieldFlag) {
		this.totalFieldFlag = totalFieldFlag;
	}

	public Integer getIsExcuseCommision() {
		return isExcuseCommision;
	}

	public void setIsExcuseCommision(Integer isExcuseCommision) {
		this.isExcuseCommision = isExcuseCommision;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public String getPartnerAbbreviationCode() {
		return partnerAbbreviationCode;
	}

	public void setPartnerAbbreviationCode(String partnerAbbreviationCode) {
		this.partnerAbbreviationCode = partnerAbbreviationCode;
	}

	public String getPartnerAbbreviationNm() {
		return partnerAbbreviationNm;
	}

	public void setPartnerAbbreviationNm(String partnerAbbreviationNm) {
		this.partnerAbbreviationNm = partnerAbbreviationNm;
	}

	public Integer getDeveloperBrandId() {
		return developerBrandId;
	}

	public void setDeveloperBrandId(Integer developerBrandId) {
		this.developerBrandId = developerBrandId;
	}

	public String getDeveloperBrandName() {
		return developerBrandName;
	}

	public void setDeveloperBrandName(String developerBrandName) {
		this.developerBrandName = developerBrandName;
	}

	public BigDecimal getVendibilityAmount() {
		return vendibilityAmount;
	}

	public void setVendibilityAmount(BigDecimal vendibilityAmount) {
		this.vendibilityAmount = vendibilityAmount;
	}

	public BigDecimal getMonthRoughAmount() {
		return monthRoughAmount;
	}

	public void setMonthRoughAmount(BigDecimal monthRoughAmount) {
		this.monthRoughAmount = monthRoughAmount;
	}

	public String getCooperationStatus() {
		return cooperationStatus;
	}

	public void setCooperationStatus(String cooperationStatus) {
		this.cooperationStatus = cooperationStatus;
	}

	public String getProjectInfoDesc() {
		return projectInfoDesc;
	}

	public void setProjectInfoDesc(String projectInfoDesc) {
		this.projectInfoDesc = projectInfoDesc;
	}

	public String getRequiredForSupport() {
		return requiredForSupport;
	}

	public void setRequiredForSupport(String requiredForSupport) {
		this.requiredForSupport = requiredForSupport;
	}
}
