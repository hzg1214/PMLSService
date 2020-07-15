/**   
* @Title: StoreDto.java 
* @Package cn.com.eju.deal.dao.store
* @Description: 门店模块接口类包
* @author 陆海丹
* @date 2016年3月24日 上午9:04:50 
* @version V1.0   
*/
package cn.com.eju.deal.dto.store;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
 * 
* @ClassName: StoreDto 
* @Description: 门店模块接口类
* @author 陆海丹
* @date 2016年3月24日 上午9:04:50 
*  
*/
public class StoreDto implements Serializable
{
    
    /**
    * 序列化
    */
    private static final long serialVersionUID = 9050138878168116517L;
    
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
    private String acCityNo;//城市编号
    private String acCityName;
    
    private String districtNo;//区域编号
    
    private String areaNo;//板块编号
    
    private String address;//地址
    
    private String addressDetail;//详细地址
    
    private Integer storeStatus;//门店状态
    
    private Integer UserCreate;//创建人
    
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
    
    private String companyNo;
    
    private String companyName;//所属公司名称
    
    private String UserNameCreate;//录入人
    
    private String userNameUpdate;//最近修改人
    
    private String searchKey;//查询关键字
    
    //目前门店图片是单张图片    
    private String fileName;//文件名
    
    private String fileUrl;//原图地址
    
    private String fileAbbrUrl;//缩略图地址
    
    private String fileRecordMainId;//文件编号
    
    private String fileNo;//渠道系统关系fileNo
    
    private Integer companyId;//所属公司编号
    
    private Integer userIdFollow;//最新跟进人员
    
    private String userNameFollow;//最新跟进人员
    
    private Integer companyStatus;//客户状态
    
    private String companyStatusStr;//客户状态
    
    private Date dateFollow;//最后跟进日期
    
    private Date dateFollowFrom;//跟进日期范围
    
    private Date dateFollowTo;//跟进日期范围
    
    private Integer relatedCompanyId;//自己建的关联的客户编号
    
    private String contractDept;//签约事业部
    
    private String contractDate;//签约时间
    
    private String subName;//分店名称
    
    private String maintainer;//门店维护人
    
    private String contacts;//门店联系人
    
    private Integer storeScale;//门店规模
    
    private String storeScaleName;
    
    private String maintainerName;//门店维护人姓名
    
    private Integer decorationState; // 维修状态
    
    private String decorationStateNm; // 维修状态
    
    private BigDecimal deposit; // 门店保证金
    
    private BigDecimal decoractionFeeOne; // 装修翻牌费1
    
    private BigDecimal decoractionFeeTwo; // 装修翻牌费2
    
    private Integer contractStatus; // 合同状态
    
    private String contractStatusName; // 合同状态名
    
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
    
    // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
    private Integer storeState;
    private Integer decorationStatus;
    private String decorationStatusName; // 门店装修状态名称

    private String storeStateName; // 合同下的门店状态 名称
    
    // 每门店保证金
    private BigDecimal depositFee;
    
    // 门店的保证金 是否到账
    private Integer isArrivalAct;
    // 门店的保证金 到账日期
    private Date dateArrivalAct;
    
    private String updateDateStr;//更新日期字符类型
    
    private String isCancel; //撤消状态
    //退款金额
    private BigDecimal refundAmount;
    //最后退款时间
    private Date refundDate;
    //退款状态
    private String refundState;
    //退款状态名称
    private String refundStateName;
    //合同关联门店-门店地址
    private String addressDetailContract;//详细地址-合同门店中
    private Integer fangyouId;//绑定房友账号ID

    private Integer stopCancelStatus;

    private String gpCompanyName;

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
    private String nowUserSystemName;//当前使用系统名称
    private String mainBusiness;//主营业务
    private String mainBusinessName;//主营业务名称
    private String transactionMode;//交易方式
    private String transactionModeName;//交易方式名称
    private Integer auditStatus;//审核状态
    private String auditStatusName;//审核状态名称
    private List<WXPictureDto> storePicList=new ArrayList<WXPictureDto>();//门店图片
    private List<WXPictureDto> storeDecorationPicList=new ArrayList<WXPictureDto>();//门店图片

    private List<FileRecordMainDto> fileDtos = new ArrayList<>();
    
    private Integer brandType;//渠道类型
    private String brandTypeStr;
    private String isFyStoreStr;//是否房友门店
    private Integer pmlsCenterId;//联动中心
    private String pmlsGroupName;//联动中心名称
    private String pmlsMaintainCode;//联动维护人
    private String pmlsMaintainName;
    private Integer isUpdateCompanyFlag;//1：更新公司 、日志修改信息-业务类型  0：不跟新公司、分配维护人

    public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public Integer getIsUpdateCompanyFlag() {
		return isUpdateCompanyFlag;
	}

	public void setIsUpdateCompanyFlag(Integer isUpdateCompanyFlag) {
		this.isUpdateCompanyFlag = isUpdateCompanyFlag;
	}
    public String getPmlsGroupName() {
		return pmlsGroupName;
	}

	public void setPmlsGroupName(String pmlsGroupName) {
		this.pmlsGroupName = pmlsGroupName;
	}

	public String getIsFyStoreStr() {
		return isFyStoreStr;
	}

	public void setIsFyStoreStr(String isFyStoreStr) {
		this.isFyStoreStr = isFyStoreStr;
	}

	public String getBrandTypeStr() {
		return brandTypeStr;
	}

	public void setBrandTypeStr(String brandTypeStr) {
		this.brandTypeStr = brandTypeStr;
	}

	public Integer getBrandType() {
		return brandType;
	}

	public void setBrandType(Integer brandType) {
		this.brandType = brandType;
	}

	public Integer getPmlsCenterId() {
		return pmlsCenterId;
	}

	public void setPmlsCenterId(Integer pmlsCenterId) {
		this.pmlsCenterId = pmlsCenterId;
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

    private List<FileRecordMainDto> fileDecorationDtos = new ArrayList<>();

    private String pictureRefId;
    private String storePicListJson;//门店图片字符串

    private String receivablesTypeName;//收款类型名称
    private String receivablesStyleName;//收款方式名称
    private Double bondMoney;//保证金金额
    private String remarks;//备注
    private String payStatus;//支付状态
    private Double payMoney;//支付金额
    private Double payTotalFee;//支付税
    private Date chargingCycleStart;//收费周期开始时间
    private Date chargingCycleEnd;//收费周期结束时间

    private BigDecimal chargeStandard;//收费标准

    private Double centerLongitude;//中心经度
    private Double centerLatitude;//中心纬度
    
    /**
     * 类型
     */
    private Integer ABTypeStore;

    private String ABTypeStoreName;

    private String oldAbtypeStore;
    
    /**
     * 乙类合同等级
     */
    private String BTypeStore;
    
    private String BTypeStoreName; //门店等级
    
  //Add By WJJ 合规性 2017/06/07 Start
  	private String changeCompany;//主体变更
    
    private String approveState;//变更审核状态
    
    private String contractStopNo;//合同终止编号
  //Add By WJJ 合规性 2017/06/07 End    

    private Integer centerId;//门店中心Id
    private String centerName;//门店名称
    
    //Add ning 2017/07/03 Start
	private Integer groupId;
    private String groupName;
    
 //Add By QJP 门店修改 2017/07/017 Start     
    private String oldStoreLogName;  //原门店店招   门店修改日志用
    private String oldCityNo;  //原门店城市No   门店修改日志用
    private String oldDistrictNo;  //原门店区域No  门店修改日志用
    private String oldStoreAddress;  //原门店地址
    private String oldAddressDetail;  //原门店详细地址
    private int changeName;    //门店修改门店店招是否被更改   
    private int changeAddress;  //门店修改门店地址是否被更改
//Add By QJP 门店修改 2017/07/17 End

    private Integer businessStatus;
    private String businessStatusName;
    
    private Integer BToAAlert;
    private String BToAAlertDesc;
    
    private String depositState;

    //1代表可选，2代表不可选
    private String disabledFlag;
    
    //保证金显示标识
    private String depositFlag;

    private String flag;
    private Integer isGpSign;

    private Integer contractStopId;

    /**
     * 门店业务类型 typeId=225
     */
    private Integer bizType;
    private String bizTypeVal;

    private String changeBusinessPlaceType;
    private Integer oldBusinessPlace;
    private Integer newBusinessPlace;
    private String changeStoreSizeScale;
    private Integer oldStoreSize;
    private Integer newStoreSize;
    private Integer oldStoreType;
    private Integer newStoreType;

    public Integer getOldStoreType() {
        return oldStoreType;
    }

    public void setOldStoreType(Integer oldStoreType) {
        this.oldStoreType = oldStoreType;
    }

    public Integer getNewStoreType() {
        return newStoreType;
    }

    public void setNewStoreType(Integer newStoreType) {
        this.newStoreType = newStoreType;
    }

    private Integer gpContractStatus; // 合同状态

    private String gpContractStatusName; // 合同状态名

    public Integer getGpContractStatus() {
        return gpContractStatus;
    }

    public void setGpContractStatus(Integer gpContractStatus) {
        this.gpContractStatus = gpContractStatus;
    }

    public String getGpContractStatusName() {
        return SystemParam.getDicValueByDicCode(this.gpContractStatus + "");
    }

    public void setGpContractStatusName(String gpContractStatusName) {
        this.gpContractStatusName = gpContractStatusName;
    }

    public Integer getBizType() {
        return bizType;
    }

    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }

    public String getBizTypeVal() {
        return bizTypeVal;
    }

    public void setBizTypeVal(String bizTypeVal) {
        this.bizTypeVal = bizTypeVal;
    }

    public Integer getIsGpSign() {
        return isGpSign;
    }

    public void setIsGpSign(Integer isGpSign) {
        this.isGpSign = isGpSign;
    }

    public String getDisabledFlag() {
        return disabledFlag;
    }

    public void setDisabledFlag(String disabledFlag) {
        this.disabledFlag = disabledFlag;
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

    public String getBusinessStatusName() {
        return SystemParam.getDicValueByDicCode(businessStatus + "");
    }

    public void setBusinessStatusName(String businessStatusName) {
        this.businessStatusName = businessStatusName;
    }

    public String getOldAddressDetail() {
		return oldAddressDetail;
	}

	public void setOldAddressDetail(String oldAddressDetail) {
		this.oldAddressDetail = oldAddressDetail;
	}

    public String getOldCityNo() {
		return oldCityNo;
	}

	public void setOldCityNo(String oldCityNo) {
		this.oldCityNo = oldCityNo;
	}

	public String getOldDistrictNo() {
		return oldDistrictNo;
	}

	public void setOldDistrictNo(String oldDistrictNo) {
		this.oldDistrictNo = oldDistrictNo;
	}

	public String getOldStoreAddress() {
		return oldStoreAddress;
	}

	public void setOldStoreAddress(String oldStoreAddress) {
		this.oldStoreAddress = oldStoreAddress;
	}
    public int getChangeName() {
		return changeName;
	}

	public void setChangeName(int changeName) {
		this.changeName = changeName;
	}

	public int getChangeAddress() {
		return changeAddress;
	}

	public void setChangeAddress(int changeAddress) {
		this.changeAddress = changeAddress;
	}
	public String getOldStoreLogName() {
		return oldStoreLogName;
	}

	public void setOldStoreLogName(String oldStoreLogName) {
		this.oldStoreLogName = oldStoreLogName;
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

	public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public String getStorePicListJson() {
        return storePicListJson;
    }

    public void setStorePicListJson(String storePicListJson) {
        this.storePicListJson = storePicListJson;
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

    public Integer getABTypeStore() {
		return ABTypeStore;
	}

    public String getBTypeStoreName() {
		return BTypeStoreName;
	}

	public void setBTypeStoreName(String bTypeStoreName) {
		BTypeStoreName = bTypeStoreName;
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

	public String getStoreLeaseDueTime() {
        return storeLeaseDueTime;
    }

    public void setStoreLeaseDueTime(String storeLeaseDueTime) {
        this.storeLeaseDueTime = storeLeaseDueTime;
    }

    public BigDecimal getChargeStandard() {
        return chargeStandard;
    }

    public void setChargeStandard(BigDecimal chargeStandard) {
        this.chargeStandard = chargeStandard;
    }
    public List<WXPictureDto> getStorePicList() {
        return storePicList;
    }

    public void setStorePicList(List<WXPictureDto> storePicList) {
        this.storePicList = storePicList;
    }

    public Double getBondMoney() {
        return bondMoney;
    }

    public void setBondMoney(Double bondMoney) {
        this.bondMoney = bondMoney;
    }

    public Date getChargingCycleEnd() {
        return chargingCycleEnd;
    }

    public void setChargingCycleEnd(Date chargingCycleEnd) {
        this.chargingCycleEnd = chargingCycleEnd;
    }

    public Date getChargingCycleStart() {
        return chargingCycleStart;
    }

    public void setChargingCycleStart(Date chargingCycleStart) {
        this.chargingCycleStart = chargingCycleStart;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Double getPayTotalFee() {
        return payTotalFee;
    }

    public void setPayTotalFee(Double payTotalFee) {
        this.payTotalFee = payTotalFee;
    }

    public String getReceivablesStyleName() {
        return receivablesStyleName;
    }

    public void setReceivablesStyleName(String receivablesStyleName) {
        this.receivablesStyleName = receivablesStyleName;
    }

    public String getReceivablesTypeName() {
        return receivablesTypeName;
    }

    public void setReceivablesTypeName(String receivablesTypeName) {
        this.receivablesTypeName = receivablesTypeName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStorePersonNumName() {
        return storePersonNumName;
    }

    public void setStorePersonNumName(String storePersonNumName) {
        this.storePersonNumName = storePersonNumName;
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


    // 合同生效时间
    private Date dateLifeStart;
    // 合同到期时间
    private Date dateLifeEnd;
    
    // 合同生效时间
    private String dateLifeStartStr;
    // 合同到期时间
    private String dateLifeEndStr;
    
    //Add By WangLei 2017/04/07 Start
    
    //待续签Flag 0：正常  1：待续签
    private Integer renewFlag;
    private Integer neededRenew;
    private Integer contractId;
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

	public Integer getNeededRenew() {
		return neededRenew;
	}

	public void setNeededRenew(Integer neededRenew) {
		this.neededRenew = neededRenew;
	}
	//Add By WangLei 2017/04/07 End
    
    public BigDecimal getRefundAmount()
    {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount)
    {
        this.refundAmount = refundAmount;
    }

    public Date getRefundDate()
    {
        return refundDate;
    }

    public void setRefundDate(Date refundDate)
    {
        this.refundDate = refundDate;
    }

    public String getRefundState()
    {
        return refundState;
    }

    public void setRefundState(String refundState)
    {
        this.refundState = refundState;
    }

    public String getRefundStateName()
    {
        return refundStateName;
    }

    public void setRefundStateName(String refundStateName)
    {
        this.refundStateName = refundStateName;
    }

    public String getIsCancel()
    {
        return isCancel;
    }

    public void setIsCancel(String isCancel)
    {
        this.isCancel = isCancel;
    }

    public String getUpdateDateStr()
    {
        return updateDateStr;
    }

    public void setUpdateDateStr(String updateDateStr)
    {
        this.updateDateStr = updateDateStr;
    }

    public Integer getIsArrivalAct() {
        return isArrivalAct;
    }

    public void setIsArrivalAct(Integer isArrivalAct) {
        this.isArrivalAct = isArrivalAct;
    }

    public Date getDateArrivalAct() {
        return dateArrivalAct;
    }

    public void setDateArrivalAct(Date dateArrivalAct) {
        this.dateArrivalAct = dateArrivalAct;
    }

    public BigDecimal getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(BigDecimal depositFee) {
        this.depositFee = depositFee;
    }
    /**
     * 合同变更页面中的门店状态（正常、变更中）
     */
    private String contractChangeState;

    public Integer getDecorationStatus() {
        return decorationStatus;
    }

    public void setDecorationStatus(Integer decorationStatus) {
        this.decorationStatus = decorationStatus;
    }

    public String getDecorationStatusName()
    {
        //return SystemParam.getDicValueByDicCode(decorationStatus + "");
    	if(decorationStatus==null){
    		return decorationStatusName;
    	}else{
    		return SystemParam.getDicValueByDicCode(decorationStatus + "");
    	}
    }

    public void setDecorationStatusName(String decorationStatusName)
    {
        this.decorationStatusName = decorationStatusName;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }
    
	public String getStoreStateName() {
		return storeStateName;
	}

	public void setStoreStateName(String storeStateName) {
		this.storeStateName = storeStateName;
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
    
    public String getContractStatusName()
    {
        return SystemParam.getDicValueByDicCode(this.contractStatus + "");
    }
    
    public void setContractStatusName(String contractStatusName)
    {
        this.contractStatusName = contractStatusName;
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
    
    public String getDecorationStateNm()
    {
        return SystemParam.getDicValueByDicCode(this.decorationState + "");
    }
    
    public void setDecorationStateNm(String decorationStateNm)
    {
        this.decorationStateNm = decorationStateNm;
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
        return maintainerName;
    }
    
    public void setMaintainerName(String maintainerName)
    {
        this.maintainerName = maintainerName;
    }
    
    public String getStoreScaleName()
    {
        return SystemParam.getDicValueByDicCode(this.storeScale + "");
    }
    
    public void setStoreScaleName(String storeScaleName)
    {
        this.storeScaleName = storeScaleName;
    }
    
    public Integer getStoreScale()
    {
        return storeScale;
    }
    
    public void setStoreScale(Integer storeScale)
    {
        this.storeScale = storeScale;
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
        return maintainer;
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
    
    /**
     * 所属公司名称
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
    
    /**
     * 所属公司名称
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }
    
    /**
     * 录入人
     * @return the userNameCreate
     */
    public String getUserNameCreate()
    {
        return UserNameCreate;
    }
    
    /**
     * 录入人
     * @param userNameCreate the userNameCreate to set
     */
    public void setUserNameCreate(String userNameCreate)
    {
        UserNameCreate = userNameCreate;
    }
    
    /**
     * 最近修改人
     * @return the userNameUpdate
     */
    public String getUserNameUpdate()
    {
        return userNameUpdate;
    }
    
    /**
     * 最近修改人
     * @param userNameUpdate the userNameUpdate to set
     */
    public void setUserNameUpdate(String userNameUpdate)
    {
        this.userNameUpdate = userNameUpdate;
    }
    
    /**
     * @return the searchKey
     */
    public String getSearchKey()
    {
        return searchKey;
    }
    
    /**
     * @param searchKey the searchKey to set
     */
    public void setSearchKey(String searchKey)
    {
        this.searchKey = searchKey;
    }
    
    /**
     * 文件名
     * @return the fileName
     */
    public String getFileName()
    {
        return fileName;
    }
    
    /**
     * 文件名
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }
    
    /**
     * 原图路径
     * @return the fileUrl
     */
    public String getFileUrl()
    {
        return fileUrl;
    }
    
    /**
     * 原图路径
     * @param fileUrl the fileUrl to set
     */
    public void setFileUrl(String fileUrl)
    {
        this.fileUrl = fileUrl;
    }
    
    /**
     * 缩略图路径
     * @return the fileAbbrUrl
     */
    public String getFileAbbrUrl()
    {
        return fileAbbrUrl;
    }
    
    /**
     * 缩略图路径
     * @param fileAbbrUrl the fileAbbrUrl to set
     */
    public void setFileAbbrUrl(String fileAbbrUrl)
    {
        this.fileAbbrUrl = fileAbbrUrl;
    }
    
    /**
     * 文件id
     * @return the fileRecordMainId
     */
    public String getFileRecordMainId()
    {
        return fileRecordMainId;
    }
    
    /**
     * 文件id
     * @param fileRecordMainId the fileRecordMainId to set
     */
    public void setFileRecordMainId(String fileRecordMainId)
    {
        this.fileRecordMainId = fileRecordMainId;
    }
    
    /**
     * 所属公司编号
     * @return the companyId
     */
    public Integer getCompanyId()
    {
        return companyId;
    }
    
    /**
     * 所属公司编号
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }
    
    /**
     * 跟进人员编号
     * @return the userIdFollow
     */
    public Integer getUserIdFollow()
    {
        return userIdFollow;
    }
    
    /**
     * 跟进人员编号
     * @param userIdFollow the userIdFollow to set
     */
    public void setUserIdFollow(Integer userIdFollow)
    {
        this.userIdFollow = userIdFollow;
    }
    
    /**
     * 跟进人员姓名
     * @return the userNameFollow
     */
    public String getUserNameFollow()
    {
        return userNameFollow;
    }
    
    /**
     * 跟进人员姓名
     * @param userNameFollow the userNameFollow to set
     */
    public void setUserNameFollow(String userNameFollow)
    {
        this.userNameFollow = userNameFollow;
    }
    
    /**
     * 客户状态
     * @return the companyStatus
     */
    public Integer getCompanyStatus()
    {
        return companyStatus;
    }
    
    /**
     * 客户状态
     * @param companyStatus the companyStatus to set
     */
    public void setCompanyStatus(Integer companyStatus)
    {
        this.companyStatus = companyStatus;
    }
    
    /**
     * 客户状态
     * @return the companyStatusStr
     */
    public String getCompanyStatusStr()
    {
        return companyStatusStr;
    }
    
    /**
     * 客户状态
     * @param companyStatusStr the companyStatusStr to set
     */
    public void setCompanyStatusStr(String companyStatusStr)
    {
        this.companyStatusStr = companyStatusStr;
    }
    
    /**
     * 最新跟进日期
     * @return the dateFollow
     */
    public Date getDateFollow()
    {
        return dateFollow;
    }
    
    /**
     * 最新跟进日期
     * @param dateFollow the dateFollow to set
     */
    public void setDateFollow(Date dateFollow)
    {
        this.dateFollow = dateFollow;
    }
    
    /**
     * 跟进日期范围
     * @return the dateFollowFrom
     */
    public Date getDateFollowFrom()
    {
        return dateFollowFrom;
    }
    
    /**
     * 跟进日期范围
     * @param dateFollowFrom the dateFollowFrom to set
     */
    public void setDateFollowFrom(Date dateFollowFrom)
    {
        this.dateFollowFrom = dateFollowFrom;
    }
    
    /**
     * 跟进日期范围
     * @return the dateFollowTo
     */
    public Date getDateFollowTo()
    {
        return dateFollowTo;
    }
    
    /**
     * 跟进日期范围
     * @param dateFollowTo the dateFollowTo to set
     */
    public void setDateFollowTo(Date dateFollowTo)
    {
        this.dateFollowTo = dateFollowTo;
    }
    
    /**
     * 自己建的关联的客户编号
     * @return the relatedCompanyId
     */
    public Integer getRelatedCompanyId()
    {
        return relatedCompanyId;
    }
    
    /**
     * 自己建的关联的客户编号
     * @param relatedCompanyId the relatedCompanyId to set
     */
    public void setRelatedCompanyId(Integer relatedCompanyId)
    {
        this.relatedCompanyId = relatedCompanyId;
    }
    
    /**
     * 签约事业部
     * @return the contractDept
     */
    public String getContractDept()
    {
        return contractDept;
    }
    
    /**
     * 签约事业部
     * @param contractDept the contractDept to set
     */
    public void setContractDept(String contractDept)
    {
        this.contractDept = contractDept;
    }
    
    /**
     * 签约时间
     * @return the contractDate
     */
    public String getContractDate()
    {
        return contractDate;
    }
    
    /**
     * 签约时间
     * @param contractDate the contractDate to set
     */
    public void setContractDate(String contractDate)
    {
        this.contractDate = contractDate;
    }
    
    public Integer getStoreId()
    {
        return storeId;
    }
    
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }
    
    public String getStoreNo()
    {
        return storeNo;
    }
    
    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getNamePinyin()
    {
        return namePinyin;
    }
    
    public void setNamePinyin(String namePinyin)
    {
        this.namePinyin = namePinyin;
    }
    
    public BigDecimal getLongitude()
    {
        return longitude;
    }
    
    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }
    
    public BigDecimal getLatitude()
    {
        return latitude;
    }
    
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

    public Boolean getIsDelete()
    {
        return isDelete;
    }
    
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }
    
    public String getCityNo()
    {
        return cityNo;
    }
    
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    
    public String getDistrictNo()
    {
        return districtNo;
    }
    
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }
    
    public String getAreaNo()
    {
        return areaNo;
    }
    
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getAddressDetail()
    {
        return addressDetail;
    }
    
    public void setAddressDetail(String addressDetail)
    {
        this.addressDetail = addressDetail;
    }
    
    public Integer getStoreStatus()
    {
        return storeStatus;
    }
    
    public void setStoreStatus(Integer storeStatus)
    {
        this.storeStatus = storeStatus;
    }
    
    public Integer getUserCreate()
    {
        return UserCreate;
    }
    
    public void setUserCreate(Integer userCreate)
    {
        UserCreate = userCreate;
    }
    
    public Date getDateCreate()
    {
        return dateCreate;
    }
    
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    
    public Integer getUserUpdate()
    {
        return UserUpdate;
    }
    
    public void setUserUpdate(Integer userUpdate)
    {
        UserUpdate = userUpdate;
    }
    
    public Date getDateUpdate()
    {
        return dateUpdate;
    }
    
    public void setDateUpdate(Date dateUpdate)
    {
        this.dateUpdate = dateUpdate;
    }
    
    public Integer getContractType()
    {
        return contractType;
    }
    
    public void setContractType(Integer contractType)
    {
        this.contractType = contractType;
    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public String getDistrictName()
    {
        return districtName;
    }
    
    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }
    
    public String getAreaName()
    {
        return areaName;
    }
    
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
    
    public String getProvinceNo()
    {
        return provinceNo;
    }
    
    public void setProvinceNo(String provinceNo)
    {
        this.provinceNo = provinceNo;
    }
    
    public String getProvinceName()
    {
        return provinceName;
    }
    
    public void setProvinceName(String provinceName)
    {
        this.provinceName = provinceName;
    }
    
    public String getStoreStatusName()
    {
        return storeStatusName;
    }
    
    public void setStoreStatusName(String storeStatusName)
    {
        this.storeStatusName = storeStatusName;
    }
    
    public Date getDateCreateFrom()
    {
        return dateCreateFrom;
    }
    
    public void setDateCreateFrom(Date dateCreateFrom)
    {
        this.dateCreateFrom = dateCreateFrom;
    }
    
    public Date getDateCreateTo()
    {
        return dateCreateTo;
    }
    
    public void setDateCreateTo(Date dateCreateTo)
    {
        this.dateCreateTo = dateCreateTo;
    }
    
    public String getContractTypeName() {       
        return contractTypeName;
    }
    
    public void setContractTypeName(String contractTypeName)
    {
        this.contractTypeName = contractTypeName;
    }

    public String getFileNo()
    {
        return fileNo;
    }

    public void setFileNo(String fileNo)
    {
        this.fileNo = fileNo;
    }

	/** 
	* 获取合同变更页面中的门店状态（正常、变更中）
	* @return
	*/
	public String getContractChangeState() {
		return contractChangeState;
	}

	/** 
	* 设置合同变更页面中的门店状态（正常、变更中）
	* @param contractChangeState
	*/
	public void setContractChangeState(String contractChangeState) {
		this.contractChangeState = contractChangeState;
	}

	public String getAddressDetailContract() {
		return addressDetailContract;
	}

	public void setAddressDetailContract(String addressDetailContract) {
		this.addressDetailContract = addressDetailContract;
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

	public String getDateLifeStartStr() {
		return dateLifeStartStr;
	}

	public void setDateLifeStartStr(String dateLifeStartStr) {
		this.dateLifeStartStr = dateLifeStartStr;
	}

	public String getDateLifeEndStr() {
		return dateLifeEndStr;
	}

	public void setDateLifeEndStr(String dateLifeEndStr) {
		this.dateLifeEndStr = dateLifeEndStr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getChangeCompany() {
		return changeCompany;
	}

	public void setChangeCompany(String changeCompany) {
		this.changeCompany = changeCompany;
	}

	public String getApproveState() {
		return approveState;
	}

	public void setApproveState(String approveState) {
		this.approveState = approveState;
	}

	public String getContractStopNo() {
		return contractStopNo;
	}

	public void setContractStopNo(String contractStopNo) {
		this.contractStopNo = contractStopNo;
	}

	public Integer getFangyouId() {
		return fangyouId;
	}

	public void setFangyouId(Integer fangyouId) {
		this.fangyouId = fangyouId;
	}

    private String mapMarkerStyle;//地图上图片名字

    public String getMapMarkerStyle() {
        return mapMarkerStyle;
    }

    public void setMapMarkerStyle(String mapMarkerStyle) {
        this.mapMarkerStyle = mapMarkerStyle;
    }
    private Integer decorateStatus;
    private Integer decorationType;
    private Integer oaMdysSumJsj;
    private String decorateCompany;
    
    private BigDecimal receivedAmount;
    /**
   	 * 保证金退还金额
   	 */
   	private BigDecimal depositBackMoney;
   	//保证金处理方式
   	private Integer depositBalance;



	public Integer getDecorateStatus() {
		return decorateStatus;
	}

	public void setDecorateStatus(Integer decorateStatus) {
		this.decorateStatus = decorateStatus;
	}

	public Integer getDecorationType() {
		return decorationType;
	}

	public void setDecorationType(Integer decorationType) {
		this.decorationType = decorationType;
	}

	public Integer getOaMdysSumJsj() {
		return oaMdysSumJsj;
	}

	public void setOaMdysSumJsj(Integer oaMdysSumJsj) {
		this.oaMdysSumJsj = oaMdysSumJsj;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}

	public BigDecimal getDepositBackMoney() {
		return depositBackMoney;
	}

	public void setDepositBackMoney(BigDecimal depositBackMoney) {
		this.depositBackMoney = depositBackMoney;
	}

	public Integer getDepositBalance() {
		return depositBalance;
	}

	public void setDepositBalance(Integer depositBalance) {
		this.depositBalance = depositBalance;
	}

	public String getDecorateCompany() {
		return decorateCompany;
	}

	public void setDecorateCompany(String decorateCompany) {
		this.decorateCompany = decorateCompany;
	}
	private Integer oldId;



	public Integer getOldId() {
		return oldId;
	}

	public void setOldId(Integer oldId) {
		this.oldId = oldId;
	}
	private Integer decorateSituate;
	private BigDecimal decorateAmount;



	public Integer getDecorateSituate() {
		return decorateSituate;
	}

	public void setDecorateSituate(Integer decorateSituate) {
		this.decorateSituate = decorateSituate;
	}

	public BigDecimal getDecorateAmount() {
		return decorateAmount;
	}

	public void setDecorateAmount(BigDecimal decorateAmount) {
		this.decorateAmount = decorateAmount;
	}

	public Integer getContractStopId() {
		return contractStopId;
	}

	public void setContractStopId(Integer contractStopId) {
		this.contractStopId = contractStopId;
	}
	//经纪公司的城市
	private String companyCityNo;
	private String companyCityName;
	
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
	//店招编号
    private String signageNo;

	public String getSignageNo() {
		return signageNo;
	}
	public void setSignageNo(String signageNo) {
		this.signageNo = signageNo;
	}
	//应收金额
	private BigDecimal totalAmount;
	//已收
	private BigDecimal receiveAmount;
	//已退
	private BigDecimal paymentAmount;
	//本次收款
	private BigDecimal amount;
	//供应商编码
	private String providerCode;
	//供应商名称
	private String providerName;
	//支出类别编码
	private String payoutId;
	//支出类别名称
	private String payoutName;

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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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

    /**
     * @return the depositFlag
     */
    public String getDepositFlag() {
        return depositFlag;
    }

    /**
     * @param depositFlag the depositFlag to set
     */
    public void setDepositFlag(String depositFlag) {
        this.depositFlag = depositFlag;
    }

	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}

	public Double getCenterLongitude() {
		return centerLongitude;
	}

	public void setCenterLongitude(Double centerLongitude) {
		this.centerLongitude = centerLongitude;
	}

	public Double getCenterLatitude() {
		return centerLatitude;
	}

	public void setCenterLatitude(Double centerLatitude) {
		this.centerLatitude = centerLatitude;
	}

	public String getProviderCode() {
		return providerCode;
	}

	public void setProviderCode(String providerCode) {
		this.providerCode = providerCode;
	}

	public String getProviderName() {
		return providerName;
	}

	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

	public String getPayoutId() {
		return payoutId;
	}

	public void setPayoutId(String payoutId) {
		this.payoutId = payoutId;
	}

	public String getPayoutName() {
		return payoutName;
	}

	public void setPayoutName(String payoutName) {
		this.payoutName = payoutName;
	}

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
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
	    
	private String storeSizeScaleName;

	public Integer getStoreSizeScale() {
		return storeSizeScale;
	}

	public void setStoreSizeScale(Integer storeSizeScale) {
		this.storeSizeScale = storeSizeScale;
	}

	public String getStoreSizeScaleName() {
		return SystemParam.getDicValueByDicCode(this.storeSizeScale + "");
	}

	public void setStoreSizeScaleName(String storeSizeScaleName) {
		this.storeSizeScaleName = storeSizeScaleName;
	}
	
	private Boolean deleteFlag;//公司门店取消关联标识

	public Boolean getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Boolean deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	//公盘合同id
	private Integer gpContractId;

	public Integer getGpContractId() {
		return gpContractId;
	}

	public void setGpContractId(Integer gpContractId) {
		this.gpContractId = gpContractId;
	}
	//房友账号
	private String fyAccount;

	public String getFyAccount() {
		return fyAccount;
	}

	public void setFyAccount(String fyAccount) {
		this.fyAccount = fyAccount;
	}

    public String getABTypeStoreName() {
        return ABTypeStoreName;
    }

    public void setABTypeStoreName(String ABTypeStoreName) {
        this.ABTypeStoreName = ABTypeStoreName;
    }

    public String getOldAbtypeStore() {
        return oldAbtypeStore;
    }

    public void setOldAbtypeStore(String oldAbtypeStore) {
        this.oldAbtypeStore = oldAbtypeStore;
    }

    public String getChangeBusinessPlaceType() {
        return changeBusinessPlaceType;
    }

    public void setChangeBusinessPlaceType(String changeBusinessPlaceType) {
        this.changeBusinessPlaceType = changeBusinessPlaceType;
    }

    public Integer getOldBusinessPlace() {
        return oldBusinessPlace;
    }

    public void setOldBusinessPlace(Integer oldBusinessPlace) {
        this.oldBusinessPlace = oldBusinessPlace;
    }

    public Integer getNewBusinessPlace() {
        return newBusinessPlace;
    }

    public void setNewBusinessPlace(Integer newBusinessPlace) {
        this.newBusinessPlace = newBusinessPlace;
    }

    public String getChangeStoreSizeScale() {
        return changeStoreSizeScale;
    }

    public void setChangeStoreSizeScale(String changeStoreSizeScale) {
        this.changeStoreSizeScale = changeStoreSizeScale;
    }

    public Integer getOldStoreSize() {
        return oldStoreSize;
    }

    public void setOldStoreSize(Integer oldStoreSize) {
        this.oldStoreSize = oldStoreSize;
    }

    public Integer getNewStoreSize() {
        return newStoreSize;
    }

    public void setNewStoreSize(Integer newStoreSize) {
        this.newStoreSize = newStoreSize;
    }
  //公盘合同变更门店id
    private Integer gpContractStoreStopId;

	public Integer getGpContractStoreStopId() {
		return gpContractStoreStopId;
	}

	public void setGpContractStoreStopId(Integer gpContractStoreStopId) {
		this.gpContractStoreStopId = gpContractStoreStopId;
	}

    public Integer getStopCancelStatus() {
        return stopCancelStatus;
    }

    public void setStopCancelStatus(Integer stopCancelStatus) {
        this.stopCancelStatus = stopCancelStatus;
    }
    //门店是否可以迁址标识
  	private String storeRelocationStatus;
  	public String getStoreRelocationStatus() {
  		return storeRelocationStatus;
  	}
  	public void setStoreRelocationStatus(String storeRelocationStatus) {
  		this.storeRelocationStatus = storeRelocationStatus;
  	}
    //门店是否可以三方变更标识
  	private String storePartyChangeStatus;
	public String getStorePartyChangeStatus() {
		return storePartyChangeStatus;
	}

	public void setStorePartyChangeStatus(String storePartyChangeStatus) {
		this.storePartyChangeStatus = storePartyChangeStatus;
	}
	//门店是否可以乙转甲标识
	private String storeB2AChangeStatus;

	public String getStoreB2AChangeStatus() {
		return storeB2AChangeStatus;
	}

	public void setStoreB2AChangeStatus(String storeB2AChangeStatus) {
		this.storeB2AChangeStatus = storeB2AChangeStatus;
	}
	//授牌验收状态
	private String authCheckStatusNm;
	public String getAuthCheckStatusNm() {
		return authCheckStatusNm;
	}
	public void setAuthCheckStatusNm(String authCheckStatusNm) {
		this.authCheckStatusNm = authCheckStatusNm;
	}
	//门店类型
    private String storeTypeNm;
	public String getStoreTypeNm() {
		return storeTypeNm;
	}
	public void setStoreTypeNm(String storeTypeNm) {
		this.storeTypeNm = storeTypeNm;
	}

    public String getGpCompanyName() {
        return gpCompanyName;
    }

    public void setGpCompanyName(String gpCompanyName) {
        this.gpCompanyName = gpCompanyName;
    }

    private Integer storeType;

    public Integer getStoreType() {
        return storeType;
    }

    public void setStoreType(Integer storeType) {
        this.storeType = storeType;
    }

    public String getAcCityName() {
        return acCityName;
    }

    public void setAcCityName(String acCityName) {
        this.acCityName = acCityName;
    }
}
