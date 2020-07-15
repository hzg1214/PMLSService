package cn.com.eju.deal.company.model;

import java.util.Date;

public class Company {

    private Integer id;//客户编号

    private String companyNo;//客户编号

    private String companyName;//公司名称

    private String companyNamePinyin;//公司拼音

    private String storeName;//门店名称

    private Integer sourceId;//门店来源

    private Boolean isDelete;//是否删除

    private Date dateCreate;//创建时间

    private Integer userCreate;//创建者

    private String cityNo;//城市编号

    private String acCityNo; // 业绩归属城市

    private String districtNo;//区域编号

    private String areaNo;//板块编号

    private String address;//地址

    private Integer contractType;//意向合同类型

    private Integer companyScale;//公司人员数量

    private Integer predictAccountCount;//意向账号数量

    private String originalVersions;//旧系统版本

    private Integer companyStatus;//客户状态
    
    private String selectOrgIdStr;//岗位组织架构
    
    private Date openTime;//房友账号开通时间
    
    private Integer userUpdate;//最近更新人
    
    private String oaFileIdBusiness;//营业执照
    
    private String companyAddress;//经营地址
    
    private String fangyouOpenStatus; //开通状态
    private Integer isGpSign ;//是否公盘签约
    
    private Integer establishYear;	//成立年限
    private String dockingPeo;		//对接人
    private String dockingPeoTel;   //对接人电话 
    private Integer storeNumber;	//门店数量
    private Integer comStaffNum;	//公司员工数量
    private Integer channelType;	//渠道分类  typeId=236
    private String channelTypeName;
    private String undertakeType;	//可承接项目类型  typeId=237
    private String undertakeTypeName;
    private String resourcesRange;	//资源覆盖范围 
    private String specificResources;//特有资源  
    private Integer lnkScale;		//一二手联动规模	typeId=238
    private String lnkScaleName;
    private String closeDeveloper;	// 合作密切开发商 
    
    public Integer getIsGpSign() {
        return isGpSign;
    }

    public void setIsGpSign(Integer isGpSign) {
        this.isGpSign = isGpSign;
    }

    public String getFangyouOpenStatus() {
		return fangyouOpenStatus;
	}
	public void setFangyouOpenStatus(String fangyouOpenStatus) {
		this.fangyouOpenStatus = fangyouOpenStatus;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public String getOaFileIdBusiness() {
		return oaFileIdBusiness;
	}
	public void setOaFileIdBusiness(String oaFileIdBusiness) {
		this.oaFileIdBusiness = oaFileIdBusiness;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public String getSelectOrgIdStr()
    {
        return selectOrgIdStr;
    }

    public void setSelectOrgIdStr(String selectOrgIdStr)
    {
        this.selectOrgIdStr = selectOrgIdStr;
    }

    /**
     * 经度
     */
    private float longitude;

    /**
     * 纬度
     */
    private float latitude;

    /**
     * 房友公司编号
     */
    private String fangyouCompanyId;


    //    扩展字段
    /**
     * 首要联系人Id
     */
    private Integer contactsId;

    /**
     * 首要联系人编号
     */
    private String contactsNo;

    /**
     * 联系人名称
     */
    private String contactsName;

    /**
     * 联系人手机
     */
    private String contactsPhone;

    /**
     * 门店数
     */
    private Integer storeCount;
    private String inputSource; // 区分是来自微信还是PC 
    
    //新需求扩展字段
    
    private String businessLicenseNo;//营业执照号
    
    private String legalPerson;//法定代表人
    
    private String contactNumber;//联系电话

    private String bizType;

    private String bizTypeNm;

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getBizTypeNm() {
        return bizTypeNm;
    }

    public void setBizTypeNm(String bizTypeNm) {
        this.bizTypeNm = bizTypeNm;
    }

    private String businessLicenseNature;//营业执照性质
	public String getBusinessLicenseNature() {
		return businessLicenseNature;
	}
	public void setBusinessLicenseNature(String businessLicenseNature) {
		this.businessLicenseNature = businessLicenseNature;
	}
	public String getBusinessLicenseNo()
    {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo)
    {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getLegalPerson()
    {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson)
    {
        this.legalPerson = legalPerson;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber)
    {
        this.contactNumber = contactNumber;
    }

    public String getInputSource()
    {
        return inputSource;
    }

    public void setInputSource(String inputSource)
    {
        this.inputSource = inputSource;
    }

    public Integer getStoreCount()
    {
        return storeCount;
    }

    public void setStoreCount(Integer storeCount)
    {
        this.storeCount = storeCount;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getCompanyNamePinyin() {
        return companyNamePinyin;
    }

    public void setCompanyNamePinyin(String companyNamePinyin) {
        this.companyNamePinyin = companyNamePinyin;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo;
    }

    public String getAreaNo() {
        return areaNo;
    }

    public void setAreaNo(String areaNo) {
        this.areaNo = areaNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }

    public Integer getCompanyScale() {
        return companyScale;
    }

    public void setCompanyScale(Integer companyScale) {
        this.companyScale = companyScale;
    }

    public Integer getPredictAccountCount() {
        return predictAccountCount;
    }

    public void setPredictAccountCount(Integer predictAccountCount) {
        this.predictAccountCount = predictAccountCount;
    }

    public String getOriginalVersions() {
        return originalVersions;
    }

    public void setOriginalVersions(String originalVersions) {
        this.originalVersions = originalVersions;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getFangyouCompanyId() {
        return fangyouCompanyId;
    }

    public void setFangyouCompanyId(String fangyouCompanyId) {
        this.fangyouCompanyId = fangyouCompanyId;
    }

    public Integer getContactsId() {
        return contactsId;
    }

    public void setContactsId(Integer contactsId) {
        this.contactsId = contactsId;
    }

    public String getContactsNo() {
        return contactsNo;
    }

    public void setContactsNo(String contactsNo) {
        this.contactsNo = contactsNo;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }
	public Integer getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(Integer userUpdate) {
		this.userUpdate = userUpdate;
	}
	private Integer companyType;//公司类型

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getEstablishYear() {
		return establishYear;
	}

	public void setEstablishYear(Integer establishYear) {
		this.establishYear = establishYear;
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

    public String getAcCityNo() {
        return acCityNo;
    }

    public void setAcCityNo(String acCityNo) {
        this.acCityNo = acCityNo;
    }
}