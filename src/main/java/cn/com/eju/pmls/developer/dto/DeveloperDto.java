package cn.com.eju.pmls.developer.dto;

import cn.com.eju.pmls.developer.model.Developer;

public class DeveloperDto extends Developer{
	
	private String userCreateCode;//创建人
    private String userCreateName;
    private String userUpdateCode;//跟新人
    private String userUpdateName;
    private String developerBrandName;//开发商品牌
    private String developerBrandCode;//开发商品牌
    private String addressDetail;//注册地址=城市+区域+详细地址
    private Integer updateId;//更新用到的id
    private String cityName;//城市名称
    private String districtName;//区域名称
    private String eatateDeveloperName;//项目关联开发商-开发商名称
    private Integer devCompanyId;//项目关联开发商-开发商ID
    private String partnerStr;//合作方类型
	private String bigCustomerName;
	private String mattressNailName;
    private String eatateHzfName;//项目关联合作方-合作方名称
    private String eatateHzfCompanyId;//项目关联合作方-合作方ID
    
	public String getEatateHzfName() {
		return eatateHzfName;
	}
	public void setEatateHzfName(String eatateHzfName) {
		this.eatateHzfName = eatateHzfName;
	}
	public String getEatateHzfCompanyId() {
		return eatateHzfCompanyId;
	}
	public void setEatateHzfCompanyId(String eatateHzfCompanyId) {
		this.eatateHzfCompanyId = eatateHzfCompanyId;
	}
	public String getPartnerStr() {
		return partnerStr;
	}
	public void setPartnerStr(String partnerStr) {
		this.partnerStr = partnerStr;
	}
	public String getDeveloperBrandCode() {
		return developerBrandCode;
	}
	public void setDeveloperBrandCode(String developerBrandCode) {
		this.developerBrandCode = developerBrandCode;
	}
	public String getEatateDeveloperName() {
		return eatateDeveloperName;
	}
	public void setEatateDeveloperName(String eatateDeveloperName) {
		this.eatateDeveloperName = eatateDeveloperName;
	}
	public Integer getDevCompanyId() {
		return devCompanyId;
	}
	public void setDevCompanyId(Integer devCompanyId) {
		this.devCompanyId = devCompanyId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Integer getUpdateId() {
		return updateId;
	}
	public void setUpdateId(Integer updateId) {
		this.updateId = updateId;
	}
	public String getUserUpdateCode() {
		return userUpdateCode;
	}
	public void setUserUpdateCode(String userUpdateCode) {
		this.userUpdateCode = userUpdateCode;
	}
	public String getUserUpdateName() {
		return userUpdateName;
	}
	public void setUserUpdateName(String userUpdateName) {
		this.userUpdateName = userUpdateName;
	}
	public String getDeveloperBrandName() {
		return developerBrandName;
	}
	public void setDeveloperBrandName(String developerBrandName) {
		this.developerBrandName = developerBrandName;
	}
	public String getUserCreateCode() {
		return userCreateCode;
	}
	public void setUserCreateCode(String userCreateCode) {
		this.userCreateCode = userCreateCode;
	}
	public String getUserCreateName() {
		return userCreateName;
	}
	public void setUserCreateName(String userCreateName) {
		this.userCreateName = userCreateName;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getBigCustomerName() {
		return bigCustomerName;
	}

	public void setBigCustomerName(String bigCustomerName) {
		this.bigCustomerName = bigCustomerName;
	}

	public String getMattressNailName() {
		return mattressNailName;
	}

	public void setMattressNailName(String mattressNailName) {
		this.mattressNailName = mattressNailName;
	}
}
