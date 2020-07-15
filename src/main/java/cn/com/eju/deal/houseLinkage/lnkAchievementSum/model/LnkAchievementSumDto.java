package cn.com.eju.deal.houseLinkage.lnkAchievementSum.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
* @Title: LnkAchievementSumDto
* @Description:  联动业绩流水表
 */
public class LnkAchievementSumDto  implements Serializable{
	
	private Integer id;
	
	//项目归属区域
	private String regionCode;
	private String regionName;
	//项目归属城市
	private String areaCityCode;
	private String areaCityName;
	//项目所在城市
	private String cityNo;
	private String cityName;
	//项目归属部门
	private Integer centerGroupId;
	private String centerGroupName;
	//报备归属城市
	private Integer cityGroupId;
	private String cityGroupName;
	//报备归属中心
	private Integer performCenterId;
	private String performCenterName;
	//项目编号
	//项目名称
	private String projectNo;
	private String projectName;
	//公司编号
	//公司名称
	private String companyId;
	private String companyNm;
	//门店编号
	//门店名称
	private String storeNo;
	private String storeName;
	//经服/渠道
	private String expenderCode;
	private String expenderName;
	//门店类别
	private String storeType;
	//门店区域
	private String districtName;
	/**
	 * 当期累计
	 */
	//报备(当期)
	private BigDecimal dq_bbNum;
	//带看(当期)
	private BigDecimal dq_dkNum;
	//套数(当期大定)
	private BigDecimal dq_ddNum;
	//面积(当期大定)
	private BigDecimal dq_ddArea;
	//金额(当期大定)
	private BigDecimal dq_ddAmount;
	//套数(当期成销)
	private BigDecimal dq_cxNum;
	//面积(当期成销)
	private BigDecimal dq_cxArea;
	//金额(当期成销)
	private BigDecimal dq_cxAmount;
	/**
	 * 年累计
	 */
	//报备(当期)
	private BigDecimal dn_bbNum;
	//带看(当期)
	private BigDecimal dn_dkNum;
	//套数(年累计大定)
	private BigDecimal dn_ddNum;
	//面积(年累计大定)
	private BigDecimal dn_ddArea;
	//金额(年累计大定)
	private BigDecimal dn_ddAmount;
	//套数(年累计成销)
	private BigDecimal dn_cxNum;
	//面积(年累计成销)
	private BigDecimal dn_cxArea;
	//金额(年累计成销)
	private BigDecimal dn_cxAmount;
	
	/**
	 * 总累计
	 */
	//报备(总累计)
	private BigDecimal lj_bbNum;
	//带看(总累计)
	private BigDecimal lj_dkNum;
	//套数(总累计大定)
	private BigDecimal lj_ddNum;
	//面积(总累计大定)
	private BigDecimal lj_ddArea;
	//金额(总累计大定)
	private BigDecimal lj_ddAmount;
	//套数(总累计成销)
	private BigDecimal lj_cxNum;
	//面积(总累计成销)
	private BigDecimal lj_cxArea;
	//金额(总累计成销)
	private BigDecimal lj_cxAmount;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getAreaCityCode() {
		return areaCityCode;
	}
	public void setAreaCityCode(String areaCityCode) {
		this.areaCityCode = areaCityCode;
	}
	public String getAreaCityName() {
		return areaCityName;
	}
	public void setAreaCityName(String areaCityName) {
		this.areaCityName = areaCityName;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public Integer getCenterGroupId() {
		return centerGroupId;
	}
	public void setCenterGroupId(Integer centerGroupId) {
		this.centerGroupId = centerGroupId;
	}
	public String getCenterGroupName() {
		return centerGroupName;
	}
	public void setCenterGroupName(String centerGroupName) {
		this.centerGroupName = centerGroupName;
	}
	public Integer getCityGroupId() {
		return cityGroupId;
	}
	public void setCityGroupId(Integer cityGroupId) {
		this.cityGroupId = cityGroupId;
	}
	public String getCityGroupName() {
		return cityGroupName;
	}
	public void setCityGroupName(String cityGroupName) {
		this.cityGroupName = cityGroupName;
	}
	public Integer getPerformCenterId() {
		return performCenterId;
	}
	public void setPerformCenterId(Integer performCenterId) {
		this.performCenterId = performCenterId;
	}
	public String getPerformCenterName() {
		return performCenterName;
	}
	public void setPerformCenterName(String performCenterName) {
		this.performCenterName = performCenterName;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
	public String getExpenderCode() {
		return expenderCode;
	}
	public void setExpenderCode(String expenderCode) {
		this.expenderCode = expenderCode;
	}
	public String getExpenderName() {
		return expenderName;
	}
	public void setExpenderName(String expenderName) {
		this.expenderName = expenderName;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public BigDecimal getDq_bbNum() {
		return dq_bbNum;
	}
	public void setDq_bbNum(BigDecimal dq_bbNum) {
		this.dq_bbNum = dq_bbNum;
	}
	public BigDecimal getDq_dkNum() {
		return dq_dkNum;
	}
	public void setDq_dkNum(BigDecimal dq_dkNum) {
		this.dq_dkNum = dq_dkNum;
	}
	public BigDecimal getDq_ddNum() {
		return dq_ddNum;
	}
	public void setDq_ddNum(BigDecimal dq_ddNum) {
		this.dq_ddNum = dq_ddNum;
	}
	public BigDecimal getDq_ddArea() {
		return dq_ddArea;
	}
	public void setDq_ddArea(BigDecimal dq_ddArea) {
		this.dq_ddArea = dq_ddArea;
	}
	public BigDecimal getDq_ddAmount() {
		return dq_ddAmount;
	}
	public void setDq_ddAmount(BigDecimal dq_ddAmount) {
		this.dq_ddAmount = dq_ddAmount;
	}
	public BigDecimal getDq_cxNum() {
		return dq_cxNum;
	}
	public void setDq_cxNum(BigDecimal dq_cxNum) {
		this.dq_cxNum = dq_cxNum;
	}
	public BigDecimal getDq_cxArea() {
		return dq_cxArea;
	}
	public void setDq_cxArea(BigDecimal dq_cxArea) {
		this.dq_cxArea = dq_cxArea;
	}
	public BigDecimal getDq_cxAmount() {
		return dq_cxAmount;
	}
	public void setDq_cxAmount(BigDecimal dq_cxAmount) {
		this.dq_cxAmount = dq_cxAmount;
	}
	public BigDecimal getDn_bbNum() {
		return dn_bbNum;
	}
	public void setDn_bbNum(BigDecimal dn_bbNum) {
		this.dn_bbNum = dn_bbNum;
	}
	public BigDecimal getDn_dkNum() {
		return dn_dkNum;
	}
	public void setDn_dkNum(BigDecimal dn_dkNum) {
		this.dn_dkNum = dn_dkNum;
	}
	public BigDecimal getDn_ddNum() {
		return dn_ddNum;
	}
	public void setDn_ddNum(BigDecimal dn_ddNum) {
		this.dn_ddNum = dn_ddNum;
	}
	public BigDecimal getDn_ddArea() {
		return dn_ddArea;
	}
	public void setDn_ddArea(BigDecimal dn_ddArea) {
		this.dn_ddArea = dn_ddArea;
	}
	public BigDecimal getDn_ddAmount() {
		return dn_ddAmount;
	}
	public void setDn_ddAmount(BigDecimal dn_ddAmount) {
		this.dn_ddAmount = dn_ddAmount;
	}
	public BigDecimal getDn_cxNum() {
		return dn_cxNum;
	}
	public void setDn_cxNum(BigDecimal dn_cxNum) {
		this.dn_cxNum = dn_cxNum;
	}
	public BigDecimal getDn_cxArea() {
		return dn_cxArea;
	}
	public void setDn_cxArea(BigDecimal dn_cxArea) {
		this.dn_cxArea = dn_cxArea;
	}
	public BigDecimal getDn_cxAmount() {
		return dn_cxAmount;
	}
	public void setDn_cxAmount(BigDecimal dn_cxAmount) {
		this.dn_cxAmount = dn_cxAmount;
	}
	public BigDecimal getLj_bbNum() {
		return lj_bbNum;
	}
	public void setLj_bbNum(BigDecimal lj_bbNum) {
		this.lj_bbNum = lj_bbNum;
	}
	public BigDecimal getLj_dkNum() {
		return lj_dkNum;
	}
	public void setLj_dkNum(BigDecimal lj_dkNum) {
		this.lj_dkNum = lj_dkNum;
	}
	public BigDecimal getLj_ddNum() {
		return lj_ddNum;
	}
	public void setLj_ddNum(BigDecimal lj_ddNum) {
		this.lj_ddNum = lj_ddNum;
	}
	public BigDecimal getLj_ddArea() {
		return lj_ddArea;
	}
	public void setLj_ddArea(BigDecimal lj_ddArea) {
		this.lj_ddArea = lj_ddArea;
	}
	public BigDecimal getLj_ddAmount() {
		return lj_ddAmount;
	}
	public void setLj_ddAmount(BigDecimal lj_ddAmount) {
		this.lj_ddAmount = lj_ddAmount;
	}
	public BigDecimal getLj_cxNum() {
		return lj_cxNum;
	}
	public void setLj_cxNum(BigDecimal lj_cxNum) {
		this.lj_cxNum = lj_cxNum;
	}
	public BigDecimal getLj_cxArea() {
		return lj_cxArea;
	}
	public void setLj_cxArea(BigDecimal lj_cxArea) {
		this.lj_cxArea = lj_cxArea;
	}
	public BigDecimal getLj_cxAmount() {
		return lj_cxAmount;
	}
	public void setLj_cxAmount(BigDecimal lj_cxAmount) {
		this.lj_cxAmount = lj_cxAmount;
	}
	
}
  