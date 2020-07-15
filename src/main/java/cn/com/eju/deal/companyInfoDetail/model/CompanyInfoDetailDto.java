package cn.com.eju.deal.companyInfoDetail.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @Description: 公司信息明细表
 */
public class CompanyInfoDetailDto implements Serializable {
	private Integer id;
	//城市
	private String cityNo;
	private String cityName;
	//公司编号
	private String companyName;
	private String companyNo;
	//公司详细地址
	private String addressDetail;
	//公司经营地址
	private String companyAddress;
	//法人
	private String legalPerson;
	//成立年限
	private Integer establishYear;
	//统一社会信用代码
	private String businessLicenseNo;
	//对接人
	private String dockingPeo;
	//对接人电话
	private String dockingPeoTel;
	//门店数量
	private Integer storeNumber;
	//员工数量
	private Integer comStaffNum;
	//渠道分类
	private Integer channelType;
	private String channelTypeName;
	//可承接项目类型
	private String undertakeType;
	private String undertakeTypeName;
	//资源覆盖范围
	private String resourcesRange;
	//特有资源
	private String specificResources;
	//一二手联动规模
	private Integer lnkScale;
	private String lnkScaleName;
	//合作密切开发商
	private String closeDeveloper;
	//合作套数
	private Integer cooperationNum;
	//与我司合作大定业绩
	private BigDecimal roughAmount;
	//
	private BigDecimal dealAmount;
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
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public Integer getEstablishYear() {
		return establishYear;
	}
	public void setEstablishYear(Integer establishYear) {
		this.establishYear = establishYear;
	}
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getDockingPeo() {
		return dockingPeo;
	}
	public void setDockingPeo(String dockingPeo) {
		this.dockingPeo = dockingPeo;
	}
	public String getDockingPeoTel() {
		return dockingPeoTel;
	}
	public void setDockingPeoTel(String dockingPeoTel) {
		this.dockingPeoTel = dockingPeoTel;
	}
	public Integer getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(Integer storeNumber) {
		this.storeNumber = storeNumber;
	}
	public Integer getComStaffNum() {
		return comStaffNum;
	}
	public void setComStaffNum(Integer comStaffNum) {
		this.comStaffNum = comStaffNum;
	}
	public Integer getChannelType() {
		return channelType;
	}
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}
	public String getChannelTypeName() {
		return channelTypeName;
	}
	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}
	public String getUndertakeType() {
		return undertakeType;
	}
	public void setUndertakeType(String undertakeType) {
		this.undertakeType = undertakeType;
	}
	public String getUndertakeTypeName() {
		return undertakeTypeName;
	}
	public void setUndertakeTypeName(String undertakeTypeName) {
		this.undertakeTypeName = undertakeTypeName;
	}
	public String getResourcesRange() {
		return resourcesRange;
	}
	public void setResourcesRange(String resourcesRange) {
		this.resourcesRange = resourcesRange;
	}
	public String getSpecificResources() {
		return specificResources;
	}
	public void setSpecificResources(String specificResources) {
		this.specificResources = specificResources;
	}
	public Integer getLnkScale() {
		return lnkScale;
	}
	public void setLnkScale(Integer lnkScale) {
		this.lnkScale = lnkScale;
	}
	public String getLnkScaleName() {
		return lnkScaleName;
	}
	public void setLnkScaleName(String lnkScaleName) {
		this.lnkScaleName = lnkScaleName;
	}
	public String getCloseDeveloper() {
		return closeDeveloper;
	}
	public void setCloseDeveloper(String closeDeveloper) {
		this.closeDeveloper = closeDeveloper;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCooperationNum() {
		return cooperationNum;
	}
	public void setCooperationNum(Integer cooperationNum) {
		this.cooperationNum = cooperationNum;
	}
	public BigDecimal getRoughAmount() {
		return roughAmount;
	}
	public void setRoughAmount(BigDecimal roughAmount) {
		this.roughAmount = roughAmount;
	}
	public BigDecimal getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(BigDecimal dealAmount) {
		this.dealAmount = dealAmount;
	}
	
	
}
  