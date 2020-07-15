package cn.com.eju.deal.houseLinkage.estate.model;

import cn.com.eju.deal.base.support.SystemParam;

import java.util.Date;

public class EstateSearchResult  {
	
    private Integer id;
    // 楼盘ID 项目编号
    private String estateId;
    // 楼盘名
    private String estateNm;
    // 楼盘地址
    private String address;
    // 城市编号
    private String cityNo;
    // 区域编号
    private String districtId;
    // 合作方
    private String partner;
    // 合作人
    private String partnerNm;
    // 审核状态
    private String auditStatus;
    // 发布状态
    private String releaseStatus;
    //录入人
    private Integer empId;
	// 录入人名
    private String empName;
    // 录入人部门
    private String deptName;
    // 合作期自
    private Date cooperationDtStart;
    // 合作期至
    private Date cooperationDtEnd;
    // 录入日
    private Date crtDt;
    //项目所在地
  	private String realityCityNo;
    
    // 以下是扩展字段
    // 城市名
    private String cityName;
    // 区域名称
    private String districtNm;
    // 合作方名称
    private String partnerDis;
    // 审核状态名称
    private String auditStatusName;
    // 发布状态名称
    private String releaseStatusName;
    // 项目状态名称
    private String projectStatusName;
    //项目所在地
  	private String realityCityNm;
  	// 发布时间
    private String releaseDt;

    // 项目状态
    private int projectStatus;
	//跟单日期
	private Date startDate;
	//结案日期
	private Date endDate;
	
	private Date measureDate;
	
	private Integer cooperationMode;
	
	private String projectNo;

	private Integer estatePosition;
	private String countryNo;
	private String countryNm;

	private Integer sceneEmpId;
    
	public String getReleaseDt() {
		return releaseDt;
	}
	public void setReleaseDt(String releaseDt) {
		this.releaseDt = releaseDt;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	public String getPartnerNm() {
		return partnerNm;
	}
	public void setPartnerNm(String partnerNm) {
		this.partnerNm = partnerNm;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getReleaseStatus() {
		return releaseStatus;
	}
	public void setReleaseStatus(String releaseStatus) {
		this.releaseStatus = releaseStatus;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
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
	public Date getCrtDt() {
		return crtDt;
	}
	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}
	public String getCityName() {
		return SystemParam.getCityNameByCityNo(cityNo);
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictNm() {
		return SystemParam.getDistrictNameByDistrictNo(districtId);
	}
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	public String getPartnerDis() {
		return SystemParam.getDicValueByDicCode(String.valueOf(partner));
	}
	public void setPartnerDis(String partnerDis) {
		this.partnerDis = partnerDis;
	}
	public String getAuditStatusName() {
		return SystemParam.getDicValueByDicCode(String.valueOf(auditStatus));
	}
	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}
	public String getReleaseStatusName() {
		return SystemParam.getDicValueByDicCode(String.valueOf(releaseStatus));
	}
	public void setReleaseStatusName(String releaseStatusName) {
		this.releaseStatusName = releaseStatusName;
	}
	public String getProjectStatusName() {
		return SystemParam.getDicValueByDicCode(String.valueOf(projectStatus));
	}
	public void setProjectStatusName(String projectStatusName) {
		this.projectStatusName = projectStatusName;
	}
    /**
     * @return the empId
     */
    public Integer getEmpId()
    {
        return empId;
    }
    /**
     * @param empId the empId to set
     */
    public void setEmpId(Integer empId)
    {
        this.empId = empId;
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
	public int getProjectStatus() {
		return projectStatus;
	}
	public void setProductStatus(int projectStatus) {
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
	public Date getMeasureDate() {
		return measureDate;
	}
	public void setMeasureDate(Date measureDate) {
		this.measureDate = measureDate;
	}
	public Integer getCooperationMode() {
		return cooperationMode;
	}
	public void setCooperationMode(Integer cooperationMode) {
		this.cooperationMode = cooperationMode;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
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

	public Integer getSceneEmpId() {
		return sceneEmpId;
	}

	public void setSceneEmpId(Integer sceneEmpId) {
		this.sceneEmpId = sceneEmpId;
	}
}