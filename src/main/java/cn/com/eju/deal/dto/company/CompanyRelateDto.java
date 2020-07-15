package cn.com.eju.deal.dto.company;

import java.io.Serializable;

/**
 * 接口给OP返回信息 公司信息封装
 * @author wenhui.zhang 
 * date: 2017年3月24日 上午10:03:54
 */
public class CompanyRelateDto implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private String companyNo;
	private String companyName;
	private String cityNo;
	private String cityName;
	private String districtNo;
	private String districtName;
	private String areaNo;
	private String areaName;
	private String address;
	private String longitude;
	private String latitude;
	private String fangyouCompanyId;
	private String businessLicenseNo;
	private String legalPerson;
	private String contactNumber;
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public String getDistrictNo() {
		return districtNo;
	}
	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getAreaNo() {
		return areaNo;
	}
	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFangyouCompanyId() {
		return fangyouCompanyId;
	}
	public void setFangyouCompanyId(String fangyouCompanyId) {
		this.fangyouCompanyId = fangyouCompanyId;
	}
	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}
	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

}
