package cn.com.eju.deal.model.sweepStreets;

import cn.com.eju.deal.model.common.PageDto;
import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.followMap.FollowRecordDto;
import cn.com.eju.deal.model.followMap.WjdcRecordDto;
import cn.com.eju.deal.store.model.StoreAuthCheck;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/4/17.
 * service的dto加属性时，请在wechat里的dto
 */
public class StoreNewDto extends PageDto {
    private Integer storeId;//门店编号
    private String storeNo;//门店编号
    private String storeName;//门店招牌
    private String longitude;//经度
    private String latitude;//纬度
    private String isDelete;//删除标记
    private String cityNo;//城市编号
    private String districtNo;//区域编号
    private String address;//地址
    private Integer userCreate;//录入人
    private String dateCreate;//创建时间
    private Integer userUpdate;//最近修改人
    private String dateUpdate;//最近修改时间
    private Integer contractType;//签约合同类型
    private String cityName;//城市
    private String districtName;//区域
    private String addressDetail;//详细地址
    private String userNameCreate;//录入人
    private String userNameUpdate;//最近修改人
    private String inputSource;//数据来源 CRMWeb,CRWWechat,WX

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
    private String nowUserSystemName;//当前使用系统名称
    private String mainBusiness;//主营业务
    private String mainBusinessName;//主营业务名称
    private String transactionMode;//交易方式
    private String transactionModeName;//交易方式名称
    private String businessPlaceType;//经营场所类型
    private String businessPlaceTypeName;//经营场所类型名称
    private Integer auditStatus;//审核状态
    private String auditStatusName;//审核状态名称
    private String auditDate;
    private String pictureRefId;//图片关联id
    private String auditReturnReason;//审核退回原因
    private String createLongitude;//创建人经度
    private String createLatitude;//创建人纬度
    private String distance;//距离
    private Integer companyId;//公司id
    private String userCode;//工号
    private String followId;//跟进id
    private String signTime;//签到时间
    private String signOutTime;//签退时间
    private String picString;//上传图片字符串
    private String maintainer;//维护人工号
    private String maintainerName;//维护人姓名
    private String storeStatus;//门店状态
    private String showType;//显示类型（show显示，notShow不显示）

    private String fpStatus;//翻牌状态
    private String contractTypeName;//合同类型名称
    private String contractStatus;//合同状态
    private String storePicUrl;//门店图片
    private Integer isCollection;//是否收藏（0否，1是）
    private String centerName;//维护人中心名称
    private String storeCenterName;//门店中心名称
    private Integer isCancel;//是否撤销（17201正常，17202撤销中，17203已撤销）
    private Integer yqDateNum;//逾期天数
    private double depositFee;//每门店保证金
    private Double receivedMoney;//已收保证金
    private Double receivingMoney;//在途保证金
    private Double amount;//本次保证金
    private Double serviceAmount;
    private Double serviceLockAmt;
    private String fyAccount;
    /**
     * 资质等级 类型（甲、乙）
     */
    private Integer ABTypeStore;

    private Integer storeType;
    private String storeTypeName;
    
    //门店跟进页面  2019/07/01  hzg
    private String fpStatusStr;//翻牌状态
    private String contractTypeNameStr;//合同类型名称
    private String contractStatusStr;//合同状态
    private String whetherStatus;//是否显示 翻牌状态、合同类型名称、合同状态

    private String checkCityNo;//待判断的城市

    public String getCheckCityNo() {
        return checkCityNo;
    }

    public void setCheckCityNo(String checkCityNo) {
        this.checkCityNo = checkCityNo;
    }

    public String getWhetherStatus() {
		return whetherStatus;
	}

	public void setWhetherStatus(String whetherStatus) {
		this.whetherStatus = whetherStatus;
	}

	public String getFpStatusStr() {
		return fpStatusStr;
	}

	public void setFpStatusStr(String fpStatusStr) {
		this.fpStatusStr = fpStatusStr;
	}

	public String getContractTypeNameStr() {
		return contractTypeNameStr;
	}

	public void setContractTypeNameStr(String contractTypeNameStr) {
		this.contractTypeNameStr = contractTypeNameStr;
	}

	public String getContractStatusStr() {
		return contractStatusStr;
	}

	public void setContractStatusStr(String contractStatusStr) {
		this.contractStatusStr = contractStatusStr;
	}

	public String getStoreTypeName() {
        return storeTypeName;
    }

    public void setStoreTypeName(String storeTypeName) {
        this.storeTypeName = storeTypeName;
    }

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    /**
     * 乙类合同等级
     */
    private String BTypeStore;

    private String bTypeStoreNm;

    private String storeState;//合同门店状态
    private Integer centerId;//交易中心id
    private String acCityNo;//业绩所属城市编号

    private Integer businessStatus;//门店营业状态
    private String businessStatusName;//门店营业状态名称

    private String btoaAlert;//乙转甲提醒（0关闭，1开启）
    private String btoaAlertDesc;//关闭原因
    private String yqDaysNum;//距离乙转甲到期天数
    private Integer businessPlaceEditFlag;//经营场所类型是否可编辑  0 标识自费装修且有翻牌完成日期 ，1表示我司装修且翻牌申请中，2表示我司装修且翻牌审核通过，其他表示可以编辑
    private Integer agentNum;//经纪人数（手动输入）

    private Integer selectPostId;

    private Integer authCheckStatus;
    private Integer searchStoreStatus;
    private Integer decorationState;
    private Integer isGpSign;

    public Integer getDecorationState() {
		return decorationState;
	}

	public void setDecorationState(Integer decorationState) {
		this.decorationState = decorationState;
	}

	//授牌验收、停业上报、停业撤销id
    private Integer authId;

    public Integer getIsGpSign() {
        return isGpSign;
    }

    public void setIsGpSign(Integer isGpSign) {
        this.isGpSign = isGpSign;
    }

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    private List<StoreAuthCheck> storeAuthCheckList;
    private String storeAuthCheckStatus;//1 显示授牌状态栏 

    public String getStoreAuthCheckStatus() {
		return storeAuthCheckStatus;
	}

	public void setStoreAuthCheckStatus(String storeAuthCheckStatus) {
		this.storeAuthCheckStatus = storeAuthCheckStatus;
	}

	public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }

    public Integer getBusinessPlaceEditFlag() {
        return businessPlaceEditFlag;
    }

    public void setBusinessPlaceEditFlag(Integer businessPlaceEditFlag) {
        this.businessPlaceEditFlag = businessPlaceEditFlag;
    }

    public String getBusinessPlaceType() {
        return businessPlaceType;
    }

    public void setBusinessPlaceType(String businessPlaceType) {
        this.businessPlaceType = businessPlaceType;
    }

    public String getBusinessPlaceTypeName() {
        return businessPlaceTypeName;
    }

    public void setBusinessPlaceTypeName(String businessPlaceTypeName) {
        this.businessPlaceTypeName = businessPlaceTypeName;
    }

    public Double getServiceAmount() {
        return serviceAmount;
    }

    public void setServiceAmount(Double serviceAmount) {
        this.serviceAmount = serviceAmount;
    }

    public Double getServiceLockAmt() {
        return serviceLockAmt;
    }

    public void setServiceLockAmt(Double serviceLockAmt) {
        this.serviceLockAmt = serviceLockAmt;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getReceivedMoney() {
        return receivedMoney;
    }

    public void setReceivedMoney(Double receivedMoney) {
        this.receivedMoney = receivedMoney;
    }

    public Double getReceivingMoney() {
        return receivingMoney;
    }

    public void setReceivingMoney(Double receivingMoney) {
        this.receivingMoney = receivingMoney;
    }

    public double getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(double depositFee) {
        this.depositFee = depositFee;
    }

    public String getYqDaysNum() {
        return yqDaysNum;
    }

    public void setYqDaysNum(String yqDaysNum) {
        this.yqDaysNum = yqDaysNum;
    }

    public String getBtoaAlert() {
        return btoaAlert;
    }

    public void setBtoaAlert(String btoaAlert) {
        this.btoaAlert = btoaAlert;
    }

    public String getBtoaAlertDesc() {
        return btoaAlertDesc;
    }

    public void setBtoaAlertDesc(String btoaAlertDesc) {
        this.btoaAlertDesc = btoaAlertDesc;
    }

    public String getBusinessStatusName() {
        return businessStatusName;
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getStoreCenterName() {
        return storeCenterName;
    }

    public void setStoreCenterName(String storeCenterName) {
        this.storeCenterName = storeCenterName;
    }

    public Integer getYqDateNum() {
        return yqDateNum;
    }

    public void setYqDateNum(Integer yqDateNum) {
        this.yqDateNum = yqDateNum;
    }

    public String getAcCityNo() {
        return acCityNo;
    }

    public void setAcCityNo(String acCityNo) {
        this.acCityNo = acCityNo;
    }

    public String getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(String auditDate) {
        this.auditDate = auditDate;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getStoreState() {
        return storeState;
    }

    public void setStoreState(String storeState) {
        this.storeState = storeState;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public String getBTypeStore() {
        return BTypeStore;
    }

    public void setBTypeStore(String BTypeStore) {
        this.BTypeStore = BTypeStore;
    }

    public Integer getABTypeStore() {
        return ABTypeStore;
    }

    public void setABTypeStore(Integer ABTypeStore) {
        this.ABTypeStore = ABTypeStore;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getStoreLeaseDueTime() {
        return storeLeaseDueTime;
    }

    public void setStoreLeaseDueTime(String storeLeaseDueTime) {
        this.storeLeaseDueTime = storeLeaseDueTime;
    }

    public Integer getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(Integer isCollection) {
        this.isCollection = isCollection;
    }

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public String getFpStatus() {
        return fpStatus;
    }

    public void setFpStatus(String fpStatus) {
        this.fpStatus = fpStatus;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getStorePicUrl() {
        return storePicUrl;
    }

    public void setStorePicUrl(String storePicUrl) {
        this.storePicUrl = storePicUrl;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getMaintainerName() {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    private List<WXPictureDto> storePicList=new ArrayList<WXPictureDto>();//门店图片

    public List<WXPictureDto> getStorePicList() {
        return storePicList;
    }

    public void setStorePicList(List<WXPictureDto> storePicList) {
        this.storePicList = storePicList;
    }

    public String getPicString() {
        return picString;
    }

    public void setPicString(String picString) {
        this.picString = picString;
    }

    private String mapMarkerStyle;//地图水滴样式

    public String getMapMarkerStyle() {
        return mapMarkerStyle;
    }

    public void setMapMarkerStyle(String mapMarkerStyle) {
        this.mapMarkerStyle = mapMarkerStyle;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(String signOutTime) {
        this.signOutTime = signOutTime;
    }

    private List<StoreAuditRecordDto> auditRecordList;//审核记录列表
    private List<FollowRecordDto> followRecordList;//门店跟进记录表
    private List<ContactsDto> contactsList;//联系人列表
    private List<WjdcRecordDto> wjdcRecordList;//门店问卷调查列表
    private String wjdcRecordStatus;//1 显示 问卷调查列表

    public String getWjdcRecordStatus() {
		return wjdcRecordStatus;
	}

	public void setWjdcRecordStatus(String wjdcRecordStatus) {
		this.wjdcRecordStatus = wjdcRecordStatus;
	}

	public String getUserCode() {
        return userCode;
    }

    public List<WjdcRecordDto> getWjdcRecordList() {
		return wjdcRecordList;
	}

	public void setWjdcRecordList(List<WjdcRecordDto> wjdcRecordList) {
		this.wjdcRecordList = wjdcRecordList;
	}

	public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public List<FollowRecordDto> getFollowRecordList() {
        return followRecordList;
    }

    public void setFollowRecordList(List<FollowRecordDto> followRecordList) {
        this.followRecordList = followRecordList;
    }

    public List<ContactsDto> getContactsList() {
        return contactsList;
    }

    public void setContactsList(List<ContactsDto> contactsList) {
        this.contactsList = contactsList;
    }

    public String getStorePersonNumName() {
        return storePersonNumName;
    }

    public void setStorePersonNumName(String storePersonNumName) {
        this.storePersonNumName = storePersonNumName;
    }



    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
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

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
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

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    public String getUserNameUpdate() {
        return userNameUpdate;
    }

    public void setUserNameUpdate(String userNameUpdate) {
        this.userNameUpdate = userNameUpdate;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
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

    public String getNowUserSystemName() {
        return nowUserSystemName;
    }

    public void setNowUserSystemName(String nowUserSystemName) {
        this.nowUserSystemName = nowUserSystemName;
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

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public String getAuditReturnReason() {
        return auditReturnReason;
    }

    public void setAuditReturnReason(String auditReturnReason) {
        this.auditReturnReason = auditReturnReason;
    }

    public String getCreateLongitude() {
        return createLongitude;
    }

    public void setCreateLongitude(String createLongitude) {
        this.createLongitude = createLongitude;
    }

    public String getCreateLatitude() {
        return createLatitude;
    }

    public void setCreateLatitude(String createLatitude) {
        this.createLatitude = createLatitude;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public List<StoreAuditRecordDto> getAuditRecordList() {
        return auditRecordList;
    }

    public void setAuditRecordList(List<StoreAuditRecordDto> auditRecordList) {
        this.auditRecordList = auditRecordList;
    }

    public Integer getSelectPostId() {
        return selectPostId;
    }

    public void setSelectPostId(Integer selectPostId) {
        this.selectPostId = selectPostId;
    }

    public String getbTypeStoreNm() {
        return bTypeStoreNm;
    }

    public void setbTypeStoreNm(String bTypeStoreNm) {
        this.bTypeStoreNm = bTypeStoreNm;
    }
    //门店规模
    private String storeSizeScaleName;
    private String storeSizeScale;

	public String getStoreSizeScaleName() {
		return storeSizeScaleName;
	}

	public void setStoreSizeScaleName(String storeSizeScaleName) {
		this.storeSizeScaleName = storeSizeScaleName;
	}

	public String getStoreSizeScale() {
		return storeSizeScale;
	}

	public void setStoreSizeScale(String storeSizeScale) {
		this.storeSizeScale = storeSizeScale;
	}
	//是否即将过期标识，1标识未过期，2标识已过期
	private Integer dueStatus;
	private Integer beingDueDay;//即将过期天数
	public Integer getDueStatus() {
		return dueStatus;
	}

	public void setDueStatus(Integer dueStatus) {
		this.dueStatus = dueStatus;
	}

	public Integer getBeingDueDay() {
		return beingDueDay;
	}

	public void setBeingDueDay(Integer beingDueDay) {
		this.beingDueDay = beingDueDay;
	}

	public String getFyAccount() {
		return fyAccount;
	}

	public void setFyAccount(String fyAccount) {
		this.fyAccount = fyAccount;
	}

    public Integer getAuthCheckStatus() {
        return authCheckStatus;
    }

    public void setAuthCheckStatus(Integer authCheckStatus) {
        this.authCheckStatus = authCheckStatus;
    }

    public Integer getSearchStoreStatus() {
        return searchStoreStatus;
    }

    public void setSearchStoreStatus(Integer searchStoreStatus) {
        this.searchStoreStatus = searchStoreStatus;
    }

    public List<StoreAuthCheck> getStoreAuthCheckList() {
        return storeAuthCheckList;
    }

    public void setStoreAuthCheckList(List<StoreAuthCheck> storeAuthCheckList) {
        this.storeAuthCheckList = storeAuthCheckList;
    }

    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
