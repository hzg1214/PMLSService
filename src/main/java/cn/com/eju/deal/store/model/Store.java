package cn.com.eju.deal.store.model;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Store
{
    private Integer storeId;//门店编号

    private String storeNo;//门店编号

    private String name;//门店招牌

    private String namePinyin;//门店招牌拼音

    private BigDecimal longitude;//经度

    private BigDecimal latitude;//纬度

    private String createLongitude;//创建人经度
    private String createLatitude;//创建人纬度

    private Boolean isDelete;//删除标记

    private String cityNo;//城市编号

    private String districtNo;//区域编号

    private String areaNo;//板块编号

    private String address;//地址

    private String addressDetail;//详细地址

    private Integer storeStatus;//门店状态

    private Integer UserCreate;//最近修改人

    private Date dateCreate;//创建时间

    private Integer UserUpdate;//最近修改人

    private Date dateUpdate;//最近修改时间

    private Integer contractType;//签约合同类型

    //以下是扩展字段

    private String cityName;//城市

    private String districtName;//区域

    private String areaName;//板块

    private String provinceNo;//省份编号

    private String provinceName;//省份

    private String storeStatusName;//门店状态

    private Date dateCreateFrom;//录入起始时间（查询用）

    private Date dateCreateTo;//录入结束时间（查询用）

    private String contractTypeName;//签约合同类型

    private String subName;//分店名称

    private String maintainer;//门店维护人

    private String contacts;//门店联系人

    private Integer storeScale;//门店规模

    private String maintainerName;//门店维护人姓名

    private Integer decorationState; // 维修状态

    private BigDecimal deposit; // 门店保证金

    private BigDecimal decoractionFeeOne; // 装修翻牌费1

    private BigDecimal decoractionFeeTwo; // 装修翻牌费2

    private Integer contractStatus; // 合同状态

    private Date datePayDcrtFeeOne; // 装修翻牌费支付日期1 

    private Date datePayDcrtFeeTwo; // 装修翻牌费支付日期2 

    private String pleasePayNoOne; //装修翻牌费请款单号1

    private String pleasePayNoTwo; //装修翻牌费请款单号2

    private Date dateAccountDeposit; //保证金到期日期
    private String decorationCompNm; // 装修公司名称
    private String decorationContractNo; // 装修合同会签单号
    private Date dateDecorationBill; // 装修发票开具日期
    private String oaFlopNo; // OA翻牌验收单号
    private Date dateFlopCkAccept; // 翻牌验收通过日期
    private String inputSource; // 区分是来自微信还是PC 
    private Integer decorationStatus;
    private String decorationStatusName; // 门店装修状态名称

    private String isCancel; //撤消状态

    private Integer fangyouId;//绑定房友账号ID

    //新增字段
    private String storeManager;//门店负责人
    private String storeManagerPhone;//门店负责人联系电话
    private String linkageSituation;//连锁情况
    private String chainBrand;//连锁品牌
    private String chainBrandName;//连锁品牌名称
    private String storeDueTime;//加盟到期时间
    private String storeLeaseDueTime;//门店租赁到期时间
    private String storePersonNum;//经纪人数
    private String storePersonNumName;//经纪人数名称
    private String nowUserSystem;//当前使用系统
    private String mainBusiness;//主营业务
    private String mainBusinessName;//主营业务名称
    private String transactionMode;//交易方式
    private String transactionModeName;//交易方式名称
    private Integer auditStatus;//审核状态
    private String auditStatusName;//审核状态名称
    private List<WXPictureDto> storePicList=new ArrayList<WXPictureDto>();//门店图片
    private List<WXPictureDto> storeDecorationPicList=new ArrayList<WXPictureDto>();//门店图片
    private String pictureRefId;

    private Integer brandType;//渠道类型
    private String brandTypeStr;
    private String isFyStoreStr;//是否房友门店
    private Integer pmlsCenterId;
    private String pmlsGroupName;
    private String pmlsMaintainCode;
    private String pmlsMaintainName;
    
    public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public String getBrandTypeStr() {
		return brandTypeStr;
	}

	public void setBrandTypeStr(String brandTypeStr) {
		this.brandTypeStr = brandTypeStr;
	}

	public String getIsFyStoreStr() {
		return isFyStoreStr;
	}

	public void setIsFyStoreStr(String isFyStoreStr) {
		this.isFyStoreStr = isFyStoreStr;
	}

	public Integer getPmlsCenterId() {
		return pmlsCenterId;
	}

	public void setPmlsCenterId(Integer pmlsCenterId) {
		this.pmlsCenterId = pmlsCenterId;
	}

	public String getPmlsGroupName() {
		return pmlsGroupName;
	}

	public void setPmlsGroupName(String pmlsGroupName) {
		this.pmlsGroupName = pmlsGroupName;
	}

	public String getPmlsMaintainCode() {
		return pmlsMaintainCode;
	}

	public void setPmlsMaintainCode(String pmlsMaintainCode) {
		this.pmlsMaintainCode = pmlsMaintainCode;
	}

	public String getPmlsMaintainName() {
		return pmlsMaintainName;
	}

	public void setPmlsMaintainName(String pmlsMaintainName) {
		this.pmlsMaintainName = pmlsMaintainName;
	}

	public List<FileRecordMainDto> getFileDtos() {
        return fileDtos;
    }

    public void setFileDtos(List<FileRecordMainDto> fileDtos) {
        this.fileDtos = fileDtos;
    }

    public List<FileRecordMainDto> getFileDecorationDtos() {
        return fileDecorationDtos;
    }

    public void setFileDecorationDtos(List<FileRecordMainDto> fileDecorationDtos) {
        this.fileDecorationDtos = fileDecorationDtos;
    }

    private List<FileRecordMainDto> fileDtos = new ArrayList<>();
    private List<FileRecordMainDto> fileDecorationDtos = new ArrayList<>();

    private Integer centerId;//门店中心Id
    private String centerName;//门店中心名
    //Add ning 2017/07/03 Start
    private Integer groupId;
    private String groupName;
    private String companyNo;
    private String companyName;

    private Integer businessStatus;
    private Integer companyId;

    private Integer BToAAlert;
    private String BToAAlertDesc;

    private String companyCityNo;
    private String companyCityName;

    private String depositState;

    private String acCityNo;

    private String gpCompanyName;

    /**
     * 门店业务类型 typeId=225
     */
    private Integer bizType;

    private Integer stopCancelStatus;

    private Integer isGpSign ;//是否公盘签约 typeId=125

    private Integer gpContractStatus; // 合同状态

    private Integer authCheckStatus;

    public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public Integer getGpContractStatus() {
        return gpContractStatus;
    }

    public void setGpContractStatus(Integer gpContractStatus) {
        this.gpContractStatus = gpContractStatus;
    }

    public Integer getIsGpSign() {
        return isGpSign;
    }

    public void setIsGpSign(Integer isGpSign) {
        this.isGpSign = isGpSign;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getCompanyCityName() {
		return companyCityName;
	}

	public void setCompanyCityName(String companyCityName) {
		this.companyCityName = companyCityName;
	}

	public String getCompanyCityNo() {
		return companyCityNo;
	}

	public void setCompanyCityNo(String companyCityNo) {
		this.companyCityNo = companyCityNo;
	}

	/**
     * @return  the 【bToAAlert】
     */
    public Integer getBToAAlert() {

        return BToAAlert;
    }

    /**
     * @param bToAAlert the 【bToAAlert】 to set
     */
    public void setBToAAlert(Integer bToAAlert) {

        BToAAlert = bToAAlert;
    }

    /**
     * @return  the 【bToAAlertDesc】
     */
    public String getBToAAlertDesc() {

        return BToAAlertDesc;
    }

    /**
     * @param bToAAlertDesc the 【bToAAlertDesc】 to set
     */
    public void setBToAAlertDesc(String bToAAlertDesc) {

        BToAAlertDesc = bToAAlertDesc;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	//Add ning 2017/07/03 End
    public String getCenterName() {
		return centerName;
	}

	public void setCenterName(String centerName) {
		this.centerName = centerName;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	//Add By QJP  2017/05/25 合规性 start
    /**
     * 门店资质等级
     */
    private Integer ABTypeStore;

    /**
     * 乙类门店等级
     */
    private String BTypeStore;

    /**
     * 乙类门店等级(名称)
     */
    private String BTypeStoreName;

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public Integer getABTypeStore() {
		return ABTypeStore;
	}

	public void setABTypeStore(Integer aBTypeStore) {
		ABTypeStore = aBTypeStore;
	}

	public String getBTypeStore() {
		return BTypeStore;
	}

	public void setBTypeStore(String bTypeStore) {
		BTypeStore = bTypeStore;
	}
	//Add By QJP  2017/05/25 合规性 end

	public String getStoreLeaseDueTime() {
        return storeLeaseDueTime;
    }

    public void setStoreLeaseDueTime(String storeLeaseDueTime) {
        this.storeLeaseDueTime = storeLeaseDueTime;
    }

    public List<WXPictureDto> getStorePicList() {
        return storePicList;
    }

    public void setStorePicList(List<WXPictureDto> storePicList) {
        this.storePicList = storePicList;
    }

    /**
     * @return  the 【storeDecorationPicList】
     */
    public List<WXPictureDto> getStoreDecorationPicList() {

        return storeDecorationPicList;
    }

    /**
     * @param storeDecorationPicList the 【storeDecorationPicList】 to set
     */
    public void setStoreDecorationPicList(List<WXPictureDto> storeDecorationPicList) {

        this.storeDecorationPicList = storeDecorationPicList;
    }

    public String getStorePersonNumName() {
        return storePersonNumName;
    }

    public void setStorePersonNumName(String storePersonNumName) {
        this.storePersonNumName = storePersonNumName;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public String getStoreManager() {
        return storeManager;
    }

    public void setStoreManager(String storeManager) {
        this.storeManager = storeManager;
    }

    public String getStoreManagerPhone() {
        return storeManagerPhone;
    }

    public void setStoreManagerPhone(String storeManagerPhone) {
        this.storeManagerPhone = storeManagerPhone;
    }

    public String getLinkageSituation() {
        return linkageSituation;
    }

    public void setLinkageSituation(String linkageSituation) {
        this.linkageSituation = linkageSituation;
    }

    public String getChainBrand() {
        return chainBrand;
    }

    public void setChainBrand(String chainBrand) {
        this.chainBrand = chainBrand;
    }

    public String getChainBrandName() {
        return chainBrandName;
    }

    public void setChainBrandName(String chainBrandName) {
        this.chainBrandName = chainBrandName;
    }

    public String getStoreDueTime() {
        return storeDueTime;
    }

    public void setStoreDueTime(String storeDueTime) {
        this.storeDueTime = storeDueTime;
    }

    public String getStorePersonNum() {
        return storePersonNum;
    }

    public void setStorePersonNum(String storePersonNum) {
        this.storePersonNum = storePersonNum;
    }

    public String getNowUserSystem() {
        return nowUserSystem;
    }

    public void setNowUserSystem(String nowUserSystem) {
        this.nowUserSystem = nowUserSystem;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getMainBusinessName() {
        return mainBusinessName;
    }

    public void setMainBusinessName(String mainBusinessName) {
        this.mainBusinessName = mainBusinessName;
    }

    public String getTransactionMode() {
        return transactionMode;
    }

    public void setTransactionMode(String transactionMode) {
        this.transactionMode = transactionMode;
    }

    public String getTransactionModeName() {
        return transactionModeName;
    }

    public void setTransactionModeName(String transactionModeName) {
        this.transactionModeName = transactionModeName;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getAuditStatusName() {
        return auditStatusName;
    }

    public void setAuditStatusName(String auditStatusName) {
        this.auditStatusName = auditStatusName;
    }

    public String getIsCancel()
    {
        return isCancel;
    }

    public void setIsCancel(String isCancel)
    {
        this.isCancel = isCancel;
    }

    public Integer getDecorationStatus() {
        return decorationStatus;
    }

    public void setDecorationStatus(Integer decorationStatus) {
        this.decorationStatus = decorationStatus;
    }

    public String getDecorationStatusName()
    {
        return decorationStatusName;
    }

    public void setDecorationStatusName(String decorationStatusName)
    {
        this.decorationStatusName = decorationStatusName;
    }

    public String getInputSource()
    {
        return inputSource;
    }

    public void setInputSource(String inputSource)
    {
        this.inputSource = inputSource;
    }

    public String getDecorationCompNm()
    {
        return decorationCompNm;
    }

    public void setDecorationCompNm(String decorationCompNm)
    {
        this.decorationCompNm = decorationCompNm;
    }

    public String getDecorationContractNo()
    {
        return decorationContractNo;
    }

    public void setDecorationContractNo(String decorationContractNo)
    {
        this.decorationContractNo = decorationContractNo;
    }

    public String getOaFlopNo()
    {
        return oaFlopNo;
    }

    public void setOaFlopNo(String oaFlopNo)
    {
        this.oaFlopNo = oaFlopNo;
    }

    public Date getDateDecorationBill()
    {
        return dateDecorationBill;
    }

    public void setDateDecorationBill(Date dateDecorationBill)
    {
        this.dateDecorationBill = dateDecorationBill;
    }

    public Date getDateFlopCkAccept()
    {
        return dateFlopCkAccept;
    }

    public void setDateFlopCkAccept(Date dateFlopCkAccept)
    {
        this.dateFlopCkAccept = dateFlopCkAccept;
    }

    public Date getDateAccountDeposit()
    {
        return dateAccountDeposit;
    }

    public void setDateAccountDeposit(Date dateAccountDeposit)
    {
        this.dateAccountDeposit = dateAccountDeposit;
    }

    public Date getDatePayDcrtFeeOne()
    {
        return datePayDcrtFeeOne;
    }

    public void setDatePayDcrtFeeOne(Date datePayDcrtFeeOne)
    {
        this.datePayDcrtFeeOne = datePayDcrtFeeOne;
    }

    public Date getDatePayDcrtFeeTwo()
    {
        return datePayDcrtFeeTwo;
    }

    public void setDatePayDcrtFeeTwo(Date datePayDcrtFeeTwo)
    {
        this.datePayDcrtFeeTwo = datePayDcrtFeeTwo;
    }

    public String getPleasePayNoOne()
    {
        return pleasePayNoOne;
    }

    public void setPleasePayNoOne(String pleasePayNoOne)
    {
        this.pleasePayNoOne = pleasePayNoOne;
    }

    public String getPleasePayNoTwo()
    {
        return pleasePayNoTwo;
    }

    public void setPleasePayNoTwo(String pleasePayNoTwo)
    {
        this.pleasePayNoTwo = pleasePayNoTwo;
    }

    public Integer getContractStatus()
    {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus)
    {
        this.contractStatus = contractStatus;
    }

    public BigDecimal getDecoractionFeeOne()
    {
        return decoractionFeeOne;
    }

    public void setDecoractionFeeOne(BigDecimal decoractionFeeOne)
    {
        this.decoractionFeeOne = decoractionFeeOne;
    }

    public BigDecimal getDecoractionFeeTwo()
    {
        return decoractionFeeTwo;
    }

    public void setDecoractionFeeTwo(BigDecimal decoractionFeeTwo)
    {
        this.decoractionFeeTwo = decoractionFeeTwo;
    }

    public BigDecimal getDeposit()
    {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit)
    {
        this.deposit = deposit;
    }

    public Integer getDecorationState()
    {
        return decorationState;
    }

    public void setDecorationState(Integer decorationState)
    {
        this.decorationState = decorationState;
    }

    public String getMaintainerName()
    {
        return this.maintainerName == null?"":this.maintainerName;
    }

    public void setMaintainerName(String maintainerName)
    {
        this.maintainerName = maintainerName;
    }

    public String getSubName()
    {
        return subName;
    }

    public void setSubName(String subName)
    {
        this.subName = subName;
    }

    public String getMaintainer()
    {
        return this.maintainer == null?"":this.maintainer;
    }

    public void setMaintainer(String maintainer)
    {
        this.maintainer = maintainer;
    }

    public String getContacts()
    {
        return contacts;
    }

    public void setContacts(String contacts)
    {
        this.contacts = contacts;
    }

    public Integer getStoreScale()
    {
        return storeScale;
    }

    public void setStoreScale(Integer storeScale)
    {
        this.storeScale = storeScale;
    }

    /**
     * 门店编号
     * @return the storeId
     */
    public Integer getStoreId()
    {
        return storeId;
    }

    /**
     * 门店编号
     * @param storeId the storeId to set
     */
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }

    /**
     * 门店编号
     * @return the storeNo
     */
    public String getStoreNo()
    {
        return storeNo;
    }

    /**
     * 门店编号
     * @param storeNo the storeNo to set
     */
    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }

    /**
     * 门店编号
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**门店编号
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * 经度
     * @return the longitude
     */
    public BigDecimal getLongitude()
    {
        return longitude;
    }

    /**
     * 经度
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }

    /**
     * 纬度
     * @return the latitude
     */
    public BigDecimal getLatitude()
    {
        return latitude;
    }

    /**
     * 纬度
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }

    /**
     * @return  the 【createLongitude】
     */
    public String getCreateLongitude() {

        return createLongitude;
    }

    /**
     * @param createLongitude the 【createLongitude】 to set
     */
    public void setCreateLongitude(String createLongitude) {

        this.createLongitude = createLongitude;
    }

    /**
     * @return  the 【createLatitude】
     */
    public String getCreateLatitude() {

        return createLatitude;
    }

    /**
     * @param createLatitude the 【createLatitude】 to set
     */
    public void setCreateLatitude(String createLatitude) {

        this.createLatitude = createLatitude;
    }

    /**
     * 删除标记
     * @return the isDelete
     */
    public Boolean getIsDelete()
    {
        return isDelete;
    }

    /**
     * 删除标记
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }

    /**
     * 城市编号
     * @return the cityNo
     */
    public String getCityNo()
    {
        return cityNo;
    }

    /**
     * 城市编号
     * @param cityNo the cityNo to set
     */
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }

    /**
     * 城市
     * @return the cityName
     */
    public String getCityName()
    {
        return SystemParam.getCityNameByCityNo(cityNo);
    }

    /**
     * 城市
     * @param cityName the cityName to set
     */
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    /**
     * 区域
     * @return the districtNo
     */
    public String getDistrictNo()
    {
        return districtNo;
    }

    /**
     * 区域
     * @param districtNo the districtNo to set
     */
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }

    /**
     * 区域
     * @return the districtName
     */
    public String getDistrictName()
    {
        return SystemParam.getDistrictNameByDistrictNo(districtNo);
    }

    /**
     * 区域
     * @param districtName the districtName to set
     */
    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }

    /**
     * 板块
     * @return the areaNo
     */
    public String getAreaNo()
    {
        return areaNo;
    }

    /**
     * 板块
     * @param areaNo the areaNo to set
     */
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }

    /**
     * 板块
     * @return the areaName
     */
    public String getAreaName()
    {
        return SystemParam.getAreaNameByAreaNo(areaNo);
    }

    /**
     * 板块
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }

    /**
     * 地址
     * @return the address
     */
    public String getAddress()
    {
        return address;
    }

    /**
     * 地址
     * @param address the address to set
     */
    public void setAddress(String address)
    {
        this.address = address;
    }

    /**
     * 详细地址
     * @return the address
     */
    public String getAddressDetail()
    {
        return addressDetail;
    }

    /**
     * 详细地址
     * @param addressDetail the addressDetail to set
     */
    public void setAddressDetail(String addressDetail)
    {
        this.addressDetail = addressDetail;
    }

    /**
     * 门店状态
     * @return the storeStatus
     */
    public Integer getStoreStatus()
    {
        return storeStatus;
    }

    /**
     * 门店状态
     * @param storeStatus the storeStatus to set
     */
    public void setStoreStatus(Integer storeStatus)
    {
        this.storeStatus = storeStatus;
    }

    /**
     * 门店状态
     * @return the storeStatusName
     */
    public String getStoreStatusName()
    {
        return storeStatusName;
    }

    /**
     * 门店状态
     * @param storeStatusName the storeStatusName to set
     */
    public void setStoreStatusName(String storeStatusName)
    {
        this.storeStatusName = storeStatusName;
    }

    /**
     * 录入人员
     * @return the userCreate
     */
    public Integer getUserCreate()
    {
        return UserCreate;
    }

    /**
     * 录入人员
     * @param userCreate the userCreate to set
     */
    public void setUserCreate(Integer userCreate)
    {
        UserCreate = userCreate;
    }

    /**
     * 新增时间
     * @return the dateCreate
     */
    public Date getDateCreate()
    {
        return dateCreate;
    }

    /**
     * 新增时间
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    /**
     * 最近更新人员
     * @return the userUpdate
     */
    public Integer getUserUpdate()
    {
        return UserUpdate;
    }

    /**
     * 最近更新人员
     * @param userUpdate the userUpdate to set
     */
    public void setUserUpdate(Integer userUpdate)
    {
        UserUpdate = userUpdate;
    }

    /**
     * 最近修改时间
     * @return the dateUpdate
     */
    public Date getDateUpdate()
    {
        return dateUpdate;
    }

    /**
     * 最近修改时间
     * @param dateUpdate the dateUpdate to set
     */
    public void setDateUpdate(Date dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }

    /**
     * 录入起始时间（查询用）
     * @return the dateCreateFrom
     */
    public Date getDateCreateFrom()
    {
        return dateCreateFrom;
    }

    /**
     * 录入起始时间（查询用）
     * @param dateCreateFrom the dateCreateFrom to set
     */
    public void setDateCreateFrom(Date dateCreateFrom)
    {
        this.dateCreateFrom = dateCreateFrom;
    }

    /**
     * 录入结束时间（查询用）
     * @return the dateCreateTo
     */
    public Date getDateCreateTo()
    {
        return dateCreateTo;
    }

    /**
     * 录入结束时间（查询用）
     * @param dateCreateTo the dateCreateTo to set
     */
    public void setDateCreateTo(Date dateCreateTo)
    {
        this.dateCreateTo = dateCreateTo;
    }

    /**
     * 身份编号
     * @return the provinceNo
     */
    public String getProvinceNo()
    {
        return provinceNo;
    }

    /**
     * 身份编号
     * @param provinceNo the provinceNo to set
     */
    public void setProvinceNo(String provinceNo)
    {
        this.provinceNo = provinceNo;
    }

    /**
     * 省份
     * @return the provinceName
     */
    public String getProvinceName()
    {
        return provinceName;
    }

    /**
     * 省份
     * @param provinceName the provinceName to set
     */
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }

    /**
     * 门店招牌拼音
     * @return the namePinyin
     */
    public String getNamePinyin()
    {
        return namePinyin;
    }

    /**
     * 门店招牌拼音
     * @param namePinyin the namePinyin to set
     */
    public void setNamePinyin(String namePinyin)
    {
        this.namePinyin = namePinyin;
    }

    /**
     * @return the contractType
     */
    public Integer getContractType()
    {
        return contractType;
    }

    /**
     * @param contractType the contractType to set
     */
    public void setContractType(Integer contractType)
    {
        this.contractType = contractType;
    }

    /**
     * @return the contractTypeName
     */
    public String getContractTypeName()
    {
        if (null == contractType || 0 == contractType)
        {
            contractTypeName = "未签";
        }
        else
        {
            contractTypeName = SystemParam.getDicValueByDicCode(contractType + "");
        }
        return contractTypeName;
    }

    /**
     * @param contractTypeName the contractTypeName to set
     */
    public void setContractTypeName(String contractTypeName)
    {
        this.contractTypeName = contractTypeName;
    }


    // 合同生效时间
    private Date dateLifeStart;
    // 合同到期时间
    private Date dateLifeEnd;

    //Add By WangLei 2017/04/07 Start

    private Integer renewFlag;  //待续签Flag  0：正常  1：待续签
    private Integer contractId; //合同Id
    private String mobilePhone; //联系方式
    private String contactsName; //门店联系人
    private Integer storeMaintainerId;//门店维护人ID
    public Integer getStoreMaintainerId() {
		return storeMaintainerId;
	}

	public void setStoreMaintainerId(Integer storeMaintainerId) {
		this.storeMaintainerId = storeMaintainerId;
	}

    public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
    public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}

	public Integer getRenewFlag() {
		return renewFlag;
	}

	public void setRenewFlag(Integer renewFlag) {
		this.renewFlag = renewFlag;
	}

    // add 2017/04/05 lei end

	// add 2017/04/06  lei start

	private Integer neededRenew;

	public Integer getNeededRenew() {
		return neededRenew;
	}

	public void setNeededRenew(Integer neededRenew) {
		this.neededRenew = neededRenew;
	}

	public Date getDateLifeStart() {
		return dateLifeStart;
	}

	public void setDateLifeStart(Date dateLifeStart) {
		this.dateLifeStart = dateLifeStart;
	}

	public Date getDateLifeEnd() {
		return dateLifeEnd;
	}

	public void setDateLifeEnd(Date dateLifeEnd) {
		this.dateLifeEnd = dateLifeEnd;
	}

	public String getBTypeStoreName() {
		return BTypeStoreName;
	}

	public void setBTypeStoreName(String bTypeStoreName) {
		BTypeStoreName = bTypeStoreName;
	}

	public Integer getFangyouId() {
		return fangyouId;
	}

	public void setFangyouId(Integer fangyouId) {
		this.fangyouId = fangyouId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

    /**
     * @return  the 【companyId】
     */
    public Integer getCompanyId() {

        return companyId;
    }

    /**
     * @param companyId the 【companyId】 to set
     */
    public void setCompanyId(Integer companyId) {

        this.companyId = companyId;
    }

	//Add By WangLei 2017/04/07 End

    //店招编号
    private String signageNo;

	public String getSignageNo() {
		return signageNo;
	}
	public void setSignageNo(String signageNo) {
		this.signageNo = signageNo;
	}
	private BigDecimal receiveAmount;
	private BigDecimal paymentAmount;
	private BigDecimal totalAmount;
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

    /**
     * @return the depositState
     */
    public String getDepositState() {
        return depositState;
    }

    /**
     * @param depositState the depositState to set
     */
    public void setDepositState(String depositState) {
        this.depositState = depositState;
    }

	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}
	//商铺经营类型
	private Integer businessPlaceType;
	private Integer businessPlaceEditFlag;

	private String businessPlaceTypeVal;

	public Integer getBusinessPlaceType() {
		return businessPlaceType;
	}
	public void setBusinessPlaceType(Integer businessPlaceType) {
		this.businessPlaceType = businessPlaceType;
	}
	public String getBusinessPlaceTypeVal() {
		return businessPlaceTypeVal;
	}
	public void setBusinessPlaceTypeVal(String businessPlaceTypeVal) {
		this.businessPlaceTypeVal = businessPlaceTypeVal;
	}

	public Integer getBusinessPlaceEditFlag() {
		return businessPlaceEditFlag;
	}

	public void setBusinessPlaceEditFlag(Integer businessPlaceEditFlag) {
		this.businessPlaceEditFlag = businessPlaceEditFlag;
	}
	private Integer storeSizeScale;//门店规模，大型、小型、微型

	public Integer getStoreSizeScale() {
		return storeSizeScale;
	}

	public void setStoreSizeScale(Integer storeSizeScale) {
		this.storeSizeScale = storeSizeScale;
	}
	//房友账号
	private String fyAccount;

	public String getFyAccount() {
		return fyAccount;
	}

	public void setFyAccount(String fyAccount) {
		this.fyAccount = fyAccount;
	}

    public Integer getStopCancelStatus() {
        return stopCancelStatus;
    }

    public void setStopCancelStatus(Integer stopCancelStatus) {
        this.stopCancelStatus = stopCancelStatus;
    }
    //授牌验收状态
    private String authCheckStatusNm;

	public String getAuthCheckStatusNm() {
		return authCheckStatusNm;
	}

	public void setAuthCheckStatusNm(String authCheckStatusNm) {
		this.authCheckStatusNm = authCheckStatusNm;
	}

    public Integer getAuthCheckStatus() {
        return authCheckStatus;
    }

    public void setAuthCheckStatus(Integer authCheckStatus) {
        this.authCheckStatus = authCheckStatus;
    }

    //门店类型
    private String storeTypeNm;
	public String getStoreTypeNm() {
		return storeTypeNm;
	}
	public void setStoreTypeNm(String storeTypeNm) {
		this.storeTypeNm = storeTypeNm;
	}

	private Integer storeType;

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getGpCompanyName() {
        return gpCompanyName;
    }

    public void setGpCompanyName(String gpCompanyName) {
        this.gpCompanyName = gpCompanyName;
    }
}