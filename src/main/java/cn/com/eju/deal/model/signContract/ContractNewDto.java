package cn.com.eju.deal.model.signContract;

import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.model.cashier.BankInfo;
import cn.com.eju.deal.model.common.PageDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by xu on 2017/6/5.
 */
public class ContractNewDto extends PageDto {
    private Integer contractId;//合同id
    private String contractNo;//合同编号
    private Integer companyId;//公司id
    private Integer companyNo;
    private String companyName;//公司名称
    private String contactNumber;//公司联系人手机号
    private String companyAddress;//公司地址

    private String businessLicenseNo;//公司营业执照号
    private String legalPerson;//公司法人姓名

    private String storeIdStr;//门店id字符串
    private Integer storeId;//门店id
    private String storeName;//门店名称
    private String maintainer;//门店负责人-员工工号
    private String fileRecordMainIds;//图片id字符串
    private String delFileRecordMainIds;//删除图片id字符串
    private String oaFileIdBusiness;//oa营业执照图片id
    private String oaFileIdCard;//oa身份证图片id
    private String oaFileIdPhoto;//oa合同图片id
    private String oaFileIdStore;//oa门店图片id
    private String oaFileIdInstall;//oa房友申请单图片id
    private String oaFileIdNotice;//oa重要提示函
    private String oaFileIdComplement;//oa补充协议
    private String oaFileIdOther;//oa其他图片id
    private String userCode;//用户编号
    private String userName;//用户名称
    private Integer userCreate;//创建人id
    private String dateCreate;//创建时间
    private Integer businessStatus;

    private String contractType;//合同类型
    private String contractTypeNm;//合同类型名称
    private String contractStatus;//合同状态
    private String contractStatusNm;//合同状态名称

    private String expandingPersonnelId;//业绩归属人id
    private String expandingPersonnel;//业绩归属人名称
    private String cityNo;//收件人地址—城市编号
    private String cityName;
    private String districtNo;//收件人地址—区域编号
    private String areaNo;//板块编号
    private String registrId;//营业执照编号
    private String pfmcAtbtDepmt;//业绩归属人部门
    private String addressDetail;//门店详细地址
    private Integer storeStatus;//门店状态（0未锁定，1已锁定）
    private Integer storeNum;//签约门店数量
    private String lealPerson;//法人
    private String partyB;//公司名称
    private String partyBCityNo;//公司地址—城市编号
    private String partyBDistrictNo;//公司地址—区域编号
    private String partyBAddress;//公司地址
    private Integer centerId;//中心id
    private String centerName;//中心名称

    private String originalContractdistinction;//标记新签，续签
    private String originalContractNo;
    private Integer valid;//是否生效
    private String validName;//是否生效

    private String dateLifeStart;//合同生效时间
    private String dateLifeEnd;//合同到期时间
    private String flowId;//与OA对接使用  获取合同审批列表


    private String agreementNo;//合作协议书编号
    private String authRepresentative;//甲方授权代表
    private double depositFee;//每门店保证金
    private double totleDepositFee;//总保证金
    private double dzDepositFee;//到账保证金
    private double penaltyFee;//违约金
    private String agentContact;//中介联系方式
    private String companyBankNo;//客户公司账号
    private String accountName;    //开户名
    private String bankAccount;//开户银行
    private String recipients;//收件人
    private String recipientsAddress;//收件地址
    private String agreementType;//合作协议书类型
    private String oaApproveType;//合同OA审批流程类别
    private String contractB2A; //是否乙转甲（ 20201；是 20202；否）
    private String remark;//合同备注
    private String inputSource;//数据来源
    private String acCityNo;//业绩归属城市编号

    private String storeListJsonStr;//签约选择的门店json字符串

    private List<StoreNewDto> contractStoreList = new ArrayList<>();

    // 营业证文件列表
    private List<ContractFileDto> fileRecordMainBusiness;
    // 法人身份证文件列表
    private List<ContractFileDto> fileRecordMainCard;
    // 合同照片文件列表
    private List<ContractFileDto> fileRecordMainPhoto;
    // 门店照片文件列表
    private List<ContractFileDto> fileRecordMainStore;
    // 房友确认单
    private List<ContractFileDto> fileRecordMainInstall;
    //重要提示函列表
    private List<ContractFileDto> fileRecordMainNotice;
    //合同补充协议
    private List<ContractFileDto> fileRecordMainComplement;
    // 其他文件列表
    private List<ContractFileDto> fileRecordMainOther;
    // 直联盘勾选表
    private List<ContractFileDto> fileRecordMainCheck;
    // 易居房友经纪业务共享平台规则
    private List<ContractFileDto> fileRecordMainRule;

    //合同门店列表
    private List<StoreNewDto> storeNewDtoList = new ArrayList<StoreNewDto>();
    private List<StoreNewDto> storeList = new ArrayList<StoreNewDto>();

    //合同审批历史列表
    private List<ContractAuditRecordDto> contractAuditRecordDtoList = new ArrayList<ContractAuditRecordDto>();

    //合同版本号
    private Integer contractVersion;

    /**
     * 合同门店铺关系
     */
    List<ContractStoreNewDto> contractStoreNewDtoList = new ArrayList<ContractStoreNewDto>();

    /**
     * 以下是收款功能新增字段
     */
    private String clickType;
    private String collectionType;
    private String collectionMethod;
    private Double payMoney;
    private Integer cashierContractId;
    private String approveStatus;
    private String checkStatus;
    private String bankName;
    private String cardNo;
    private String receiveNo;
    // 转账凭证
    private List<ContractFileDto> fileRecordMainBond;

    private List<CommonCodeDto> collectionMethodList;
    private List<BankInfo> bankInfoList;

    //核算主体
    private String accountProject;//核算主体名称
    private String accountProjectNo;//核算主体编码

    // 公盘合同签约新增
    private String provinceNo;
    private String provinceName;
    private Integer ourFullId;
    private String ourFullName;
    private String bankAccountNm;//开户行名称
    private String accountNo;//银行账号
    private String cityNo4GpContract;//公盘合同的业绩归属城市
    private String partyBDistrictName;
    private String partyBCityName;
    private String searchText;

    //20190611 hzg 
    private String shoupaiType;
    private String accountProvinceNo;
    private String accountProvinceName;
    private String accountCityNo;
    private String accountCityName;
    //end
    
    /**
     * 转成GpContract对象
     */
    public static GpContract convertToGpContract(ContractNewDto dto) throws ParseException {
        GpContract gpContract = new GpContract();
        BeanUtils.copyProperties(dto, gpContract);
        if (dto.getContractStatus() == null) {
            gpContract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_PENDING);
        }
        //默认新签
        gpContract.setContractType(DictionaryConstants.OriginalContractdistinction_TYPE_N);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long nd = System.currentTimeMillis();
        Date dateLifeStart = format.parse(dto.getDateLifeStart());
        Date dateLifeEnd = format.parse(dto.getDateLifeEnd());
        long ds = dateLifeStart.getTime();
        long de = dateLifeEnd.getTime();
        if (nd >= ds && ds <= de) {
            gpContract.setValid(DictionaryConstants.VALID_TYPE_SX);//有效标识
        } else if (nd > de) {
            gpContract.setValid(DictionaryConstants.VALID_TYPE_GQ);//过期标识
        } else {
            gpContract.setValid(DictionaryConstants.VALID_TYPE_DSX);//待生效标识
        }
        gpContract.setId(dto.getContractId());
        gpContract.setDepositFee(new BigDecimal(dto.getDepositFee()));
        gpContract.setDateLifeStart(dateLifeStart);
        gpContract.setDateLifeEnd(dateLifeEnd);
        gpContract.setPartyB(dto.getCompanyName());
        gpContract.setDateCreate(new Date());
        gpContract.setGpContractNo(dto.getContractNo());
        gpContract.setRegisterId(dto.getBusinessLicenseNo());
        gpContract.setGpAgreementNo(dto.getAgreementNo());
        gpContract.setExPersonId(dto.getExpandingPersonnelId());
        gpContract.setExPerson(dto.getExpandingPersonnel());
        gpContract.setAccountProvinceNo(dto.getProvinceNo());
        gpContract.setAccountProvinceNm(dto.getProvinceName());
        gpContract.setAccountCityNo(dto.getCityNo());
        gpContract.setAccountCityNm(dto.getCityName());
        gpContract.setAccountNm(dto.getAccountName());
        gpContract.setBankAccount(dto.getAccountNo());
        gpContract.setPartyBNm(dto.getAuthRepresentative());
        gpContract.setPartyBTel(dto.getAgentContact());
        gpContract.setCityNo(dto.getCityNo4GpContract());
        gpContract.setSubmitOAStatus(21201);
        gpContract.setSubmitOAUserId(dto.getUserCreate());
        return gpContract;
    }

	public String getShoupaiType() {
		return shoupaiType;
	}

	public void setShoupaiType(String shoupaiType) {
		this.shoupaiType = shoupaiType;
	}

	public String getAccountProvinceNo() {
		return accountProvinceNo;
	}

	public void setAccountProvinceNo(String accountProvinceNo) {
		this.accountProvinceNo = accountProvinceNo;
	}

	public String getAccountProvinceName() {
		return accountProvinceName;
	}

	public void setAccountProvinceName(String accountProvinceName) {
		this.accountProvinceName = accountProvinceName;
	}

	public String getAccountCityNo() {
		return accountCityNo;
	}

	public void setAccountCityNo(String accountCityNo) {
		this.accountCityNo = accountCityNo;
	}

	public String getAccountCityName() {
		return accountCityName;
	}

	public void setAccountCityName(String accountCityName) {
		this.accountCityName = accountCityName;
	}

	public List<StoreNewDto> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreNewDto> storeList) {
        this.storeList = storeList;
    }

    public String getContractTypeNm() {
        return contractTypeNm;
    }

    public void setContractTypeNm(String contractTypeNm) {
        this.contractTypeNm = contractTypeNm;
    }

    public String getContractStatusNm() {
        return contractStatusNm;
    }

    public void setContractStatusNm(String contractStatusNm) {
        this.contractStatusNm = contractStatusNm;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getPartyBDistrictName() {
        return partyBDistrictName;
    }

    public void setPartyBDistrictName(String partyBDistrictName) {
        this.partyBDistrictName = partyBDistrictName;
    }

    public String getPartyBCityName() {
        return partyBCityName;
    }

    public void setPartyBCityName(String partyBCityName) {
        this.partyBCityName = partyBCityName;
    }

    public String getCityNo4GpContract() {
        return cityNo4GpContract;
    }

    public void setCityNo4GpContract(String cityNo4GpContract) {
        this.cityNo4GpContract = cityNo4GpContract;
    }

    public String getProvinceNo() {
        return provinceNo;
    }

    public void setProvinceNo(String provinceNo) {
        this.provinceNo = provinceNo;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getBankAccountNm() {
        return bankAccountNm;
    }

    public void setBankAccountNm(String bankAccountNm) {
        this.bankAccountNm = bankAccountNm;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getOurFullId() {
        return ourFullId;
    }

    public void setOurFullId(Integer ourFullId) {
        this.ourFullId = ourFullId;
    }

    public String getOurFullName() {
        return ourFullName;
    }

    public void setOurFullName(String ourFullName) {
        this.ourFullName = ourFullName;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getDzDepositFee() {
        return dzDepositFee;
    }

    public void setDzDepositFee(double dzDepositFee) {
        this.dzDepositFee = dzDepositFee;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public List<ContractFileDto> getFileRecordMainCheck() {
        return fileRecordMainCheck;
    }

    public void setFileRecordMainCheck(List<ContractFileDto> fileRecordMainCheck) {
        this.fileRecordMainCheck = fileRecordMainCheck;
    }

    public List<ContractFileDto> getFileRecordMainRule() {
        return fileRecordMainRule;
    }

    public void setFileRecordMainRule(List<ContractFileDto> fileRecordMainRule) {
        this.fileRecordMainRule = fileRecordMainRule;
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

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public List<CommonCodeDto> getCollectionMethodList() {
        return collectionMethodList;
    }

    public void setCollectionMethodList(List<CommonCodeDto> collectionMethodList) {
        this.collectionMethodList = collectionMethodList;
    }

    public List<BankInfo> getBankInfoList() {
        return bankInfoList;
    }

    public void setBankInfoList(List<BankInfo> bankInfoList) {
        this.bankInfoList = bankInfoList;
    }

    public List<ContractFileDto> getFileRecordMainBond() {
        return fileRecordMainBond;
    }

    public void setFileRecordMainBond(List<ContractFileDto> fileRecordMainBond) {
        this.fileRecordMainBond = fileRecordMainBond;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    public Integer getCashierContractId() {
        return cashierContractId;
    }

    public void setCashierContractId(Integer cashierContractId) {
        this.cashierContractId = cashierContractId;
    }

    public String getCollectionMethod() {
        return collectionMethod;
    }

    public void setCollectionMethod(String collectionMethod) {
        this.collectionMethod = collectionMethod;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public Double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(Double payMoney) {
        this.payMoney = payMoney;
    }

    public String getClickType() {
        return clickType;
    }

    public void setClickType(String clickType) {
        this.clickType = clickType;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(Integer companyNo) {
        this.companyNo = companyNo;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(Integer contractVersion) {
        this.contractVersion = contractVersion;
    }

    public String getDelFileRecordMainIds() {
        return delFileRecordMainIds;
    }

    public void setDelFileRecordMainIds(String delFileRecordMainIds) {
        this.delFileRecordMainIds = delFileRecordMainIds;
    }

    public List<ContractFileDto> getFileRecordMainBusiness() {
        return fileRecordMainBusiness;
    }

    public void setFileRecordMainBusiness(List<ContractFileDto> fileRecordMainBusiness) {
        this.fileRecordMainBusiness = fileRecordMainBusiness;
    }

    public List<ContractFileDto> getFileRecordMainCard() {
        return fileRecordMainCard;
    }

    public void setFileRecordMainCard(List<ContractFileDto> fileRecordMainCard) {
        this.fileRecordMainCard = fileRecordMainCard;
    }

    public List<ContractFileDto> getFileRecordMainComplement() {
        return fileRecordMainComplement;
    }

    public void setFileRecordMainComplement(List<ContractFileDto> fileRecordMainComplement) {
        this.fileRecordMainComplement = fileRecordMainComplement;
    }

    public List<ContractFileDto> getFileRecordMainInstall() {
        return fileRecordMainInstall;
    }

    public void setFileRecordMainInstall(List<ContractFileDto> fileRecordMainInstall) {
        this.fileRecordMainInstall = fileRecordMainInstall;
    }

    public List<ContractFileDto> getFileRecordMainNotice() {
        return fileRecordMainNotice;
    }

    public void setFileRecordMainNotice(List<ContractFileDto> fileRecordMainNotice) {
        this.fileRecordMainNotice = fileRecordMainNotice;
    }

    public List<ContractFileDto> getFileRecordMainOther() {
        return fileRecordMainOther;
    }

    public void setFileRecordMainOther(List<ContractFileDto> fileRecordMainOther) {
        this.fileRecordMainOther = fileRecordMainOther;
    }

    public List<ContractFileDto> getFileRecordMainPhoto() {
        return fileRecordMainPhoto;
    }

    public void setFileRecordMainPhoto(List<ContractFileDto> fileRecordMainPhoto) {
        this.fileRecordMainPhoto = fileRecordMainPhoto;
    }

    public List<ContractFileDto> getFileRecordMainStore() {
        return fileRecordMainStore;
    }

    public void setFileRecordMainStore(List<ContractFileDto> fileRecordMainStore) {
        this.fileRecordMainStore = fileRecordMainStore;
    }

    public String getOaFileIdNotice() {
        return oaFileIdNotice;
    }

    public void setOaFileIdNotice(String oaFileIdNotice) {
        this.oaFileIdNotice = oaFileIdNotice;
    }

    public String getAcCityNo() {
        return acCityNo;
    }

    public void setAcCityNo(String acCityNo) {
        this.acCityNo = acCityNo;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
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

    public String getValidName() {
        return validName;
    }

    public void setValidName(String validName) {
        this.validName = validName;
    }

    public String getOaFileIdOther() {
        return oaFileIdOther;
    }

    public void setOaFileIdOther(String oaFileIdOther) {
        this.oaFileIdOther = oaFileIdOther;
    }

    public String getStoreListJsonStr() {
        return storeListJsonStr;
    }

    public void setStoreListJsonStr(String storeListJsonStr) {
        this.storeListJsonStr = storeListJsonStr;
    }

    public List<ContractStoreNewDto> getContractStoreNewDtoList() {
        return contractStoreNewDtoList;
    }

    public void setContractStoreNewDtoList(List<ContractStoreNewDto> contractStoreNewDtoList) {
        this.contractStoreNewDtoList = contractStoreNewDtoList;
    }

    public String getPartyBCityNo() {
        return partyBCityNo;
    }

    public void setPartyBCityNo(String partyBCityNo) {
        this.partyBCityNo = partyBCityNo;
    }

    public String getPartyBDistrictNo() {
        return partyBDistrictNo;
    }

    public void setPartyBDistrictNo(String partyBDistrictNo) {
        this.partyBDistrictNo = partyBDistrictNo;
    }

    public String getAgreementNo() {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo) {
        this.agreementNo = agreementNo;
    }

    public String getAuthRepresentative() {
        return authRepresentative;
    }

    public void setAuthRepresentative(String authRepresentative) {
        this.authRepresentative = authRepresentative;
    }

    public double getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(double depositFee) {
        this.depositFee = depositFee;
    }

    public double getTotleDepositFee() {
        return totleDepositFee;
    }

    public void setTotleDepositFee(double totleDepositFee) {
        this.totleDepositFee = totleDepositFee;
    }

    public double getPenaltyFee() {
        return penaltyFee;
    }

    public void setPenaltyFee(double penaltyFee) {
        this.penaltyFee = penaltyFee;
    }

    public String getAgentContact() {
        return agentContact;
    }

    public void setAgentContact(String agentContact) {
        this.agentContact = agentContact;
    }

    public String getCompanyBankNo() {
        return companyBankNo;
    }

    public void setCompanyBankNo(String companyBankNo) {
        this.companyBankNo = companyBankNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getRecipientsAddress() {
        return recipientsAddress;
    }

    public void setRecipientsAddress(String recipientsAddress) {
        this.recipientsAddress = recipientsAddress;
    }

    public String getAgreementType() {
        return agreementType;
    }

    public void setAgreementType(String agreementType) {
        this.agreementType = agreementType;
    }

    public String getOaApproveType() {
        return oaApproveType;
    }

    public void setOaApproveType(String oaApproveType) {
        this.oaApproveType = oaApproveType;
    }

    public String getContractB2A() {
        return contractB2A;
    }

    public void setContractB2A(String contractB2A) {
        this.contractB2A = contractB2A;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public List<ContractAuditRecordDto> getContractAuditRecordDtoList() {
        return contractAuditRecordDtoList;
    }

    public void setContractAuditRecordDtoList(List<ContractAuditRecordDto> contractAuditRecordDtoList) {
        this.contractAuditRecordDtoList = contractAuditRecordDtoList;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getDateLifeEnd() {
        return dateLifeEnd;
    }

    public void setDateLifeEnd(String dateLifeEnd) {
        this.dateLifeEnd = dateLifeEnd;
    }

    public String getDateLifeStart() {
        return dateLifeStart;
    }

    public void setDateLifeStart(String dateLifeStart) {
        this.dateLifeStart = dateLifeStart;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public String getOriginalContractdistinction() {
        return originalContractdistinction;
    }

    public void setOriginalContractdistinction(String originalContractdistinction) {
        this.originalContractdistinction = originalContractdistinction;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public List<StoreNewDto> getStoreNewDtoList() {
        return storeNewDtoList;
    }

    public void setStoreNewDtoList(List<StoreNewDto> storeNewDtoList) {
        this.storeNewDtoList = storeNewDtoList;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLealPerson() {
        return lealPerson;
    }

    public void setLealPerson(String lealPerson) {
        this.lealPerson = lealPerson;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getPartyBAddress() {
        return partyBAddress;
    }

    public void setPartyBAddress(String partyBAddress) {
        this.partyBAddress = partyBAddress;
    }

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    public List<StoreNewDto> getContractStoreList() {
        return contractStoreList;
    }

    public void setContractStoreList(List<StoreNewDto> contractStoreList) {
        this.contractStoreList = contractStoreList;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getFileRecordMainIds() {
        return fileRecordMainIds;
    }

    public void setFileRecordMainIds(String fileRecordMainIds) {
        this.fileRecordMainIds = fileRecordMainIds;
    }

    public String getExpandingPersonnelId() {
        return expandingPersonnelId;
    }

    public void setExpandingPersonnelId(String expandingPersonnelId) {
        this.expandingPersonnelId = expandingPersonnelId;
    }

    public String getExpandingPersonnel() {
        return expandingPersonnel;
    }

    public void setExpandingPersonnel(String expandingPersonnel) {
        this.expandingPersonnel = expandingPersonnel;
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

    public String getRegistrId() {
        return registrId;
    }

    public void setRegistrId(String registrId) {
        this.registrId = registrId;
    }

    public String getPfmcAtbtDepmt() {
        return pfmcAtbtDepmt;
    }

    public void setPfmcAtbtDepmt(String pfmcAtbtDepmt) {
        this.pfmcAtbtDepmt = pfmcAtbtDepmt;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getStoreIdStr() {
        return storeIdStr;
    }

    public void setStoreIdStr(String storeIdStr) {
        this.storeIdStr = storeIdStr;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getOaFileIdBusiness() {
        return oaFileIdBusiness;
    }

    public void setOaFileIdBusiness(String oaFileIdBusiness) {
        this.oaFileIdBusiness = oaFileIdBusiness;
    }

    public String getOaFileIdCard() {
        return oaFileIdCard;
    }

    public void setOaFileIdCard(String oaFileIdCard) {
        this.oaFileIdCard = oaFileIdCard;
    }

    public String getOaFileIdPhoto() {
        return oaFileIdPhoto;
    }

    public void setOaFileIdPhoto(String oaFileIdPhoto) {
        this.oaFileIdPhoto = oaFileIdPhoto;
    }

    public String getOaFileIdStore() {
        return oaFileIdStore;
    }

    public void setOaFileIdStore(String oaFileIdStore) {
        this.oaFileIdStore = oaFileIdStore;
    }

    public String getOaFileIdInstall() {
        return oaFileIdInstall;
    }

    public void setOaFileIdInstall(String oaFileIdInstall) {
        this.oaFileIdInstall = oaFileIdInstall;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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

    public String getOaFileIdComplement() {
        return oaFileIdComplement;
    }

    public void setOaFileIdComplement(String oaFileIdComplement) {
        this.oaFileIdComplement = oaFileIdComplement;
    }

    public Integer getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(Integer businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getOriginalContractNo() {
        return originalContractNo;
    }

    public void setOriginalContractNo(String originalContractNo) {
        this.originalContractNo = originalContractNo;
    }
}
