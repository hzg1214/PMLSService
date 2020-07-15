package cn.com.eju.deal.gpContract.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haidan on 2018/8/31.
 */
public class GpContract {
    private Integer id;
    private String gpContractNo;

    /**
     * 甲方名称
     */
    private String partyB;
    private String partyBCityNo;
    private String partyBCityName;
    private String partyBDistrictNo;
    private String partyBDistrictName;

    /**
     * 公司注册地址
     */
    private String partyBAddress;
    /**
     * 法定代表/负责人
     */
    private String legalPerson;
    /**
     * 公盘协议书编号
     */
    private String gpAgreementNo;
    /**
     * 统一社会信用代码
     */
    private String registerId;
    /**
     * 我方全称
     */
    private Integer ourFullId;
    private String ourFullName;
    /**
     * 合作门店数
     */
    private Integer storeNum;
    /**
     * 总保证金
     */
    private BigDecimal depositFee;
    /**
     * 合同生效日期
     */
    private Date dateLifeStart;
    /**
     * 合同到期日期
     */
    private Date dateLifeEnd;
    /**
     * 业绩归属拓展人
     */
    private String exPersonId;
    private String exPerson;
    /**
     * 业绩归属中心
     */
    private Integer centerId;
    private String centerName;

    /**
     * 开户名
     */
    private String accountNm;
    /**
     * 开户省份
     */
    private String accountProvinceNo;
    /**
     * 开户省份
     */
    private String accountProvinceNm;
    /**
     * 开户城市
     */
    private String accountCityNo;
    private String accountCityNm;
    /**
     * 开户行
     */
    private String bankAccountNm;
    /**
     * 银行账号
     */
    private String bankAccount;

    /**
     * 甲方授权代表
     */
    private String partyBNm;
    /**
     * 甲方联系方式
     */
    private String partyBTel;
    /**
     * 合同备注
     */
    private String remark;
    private Integer companyId;
    //公司名称
    private String companyName;

    private String cityNo;
    /**
     * 核算主体
     */
    private String accountProject;
    /**
     * 核算主体编号
     */
    private String accountProjectNo;
    /**
     * 公盘合同状态
     */
    private Integer contractStatus;
    /**
     * 审核通过时间
     */
    private Date performDate;
    /**
     * 新签续签
     */
    private Integer contractType;
    private Integer valid;
    private String oaNo;
    private String flowId;
    private Integer submitOAStatus;
    private Integer submitOAUserId;
    private Date submitTime;
    private Integer userCreate;
    private Date dateCreate;
    private Integer userUpdate;
    private Date dateUpdate;
    private String depositStatusNm;

    /**
     * 数据来源
     */
    private String inputSource;
    /**
     * 合同版本号
     */
    private Integer contractVersion;

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public Integer getContractVersion() {
        return contractVersion;
    }

    public void setContractVersion(Integer contractVersion) {
        this.contractVersion = contractVersion;
    }

    public String getDepositStatusNm() {
        return depositStatusNm;
    }

    public void setDepositStatusNm(String depositStatusNm) {
        this.depositStatusNm = depositStatusNm;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getPerformDate() {
        return performDate;
    }

    public void setPerformDate(Date performDate) {
        this.performDate = performDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGpContractNo() {
        return gpContractNo;
    }

    public void setGpContractNo(String gpContractNo) {
        this.gpContractNo = gpContractNo;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getPartyBCityNo() {
        return partyBCityNo;
    }

    public void setPartyBCityNo(String partyBCityNo) {
        this.partyBCityNo = partyBCityNo;
    }

    public String getPartyBCityName() {
        return partyBCityName;
    }

    public void setPartyBCityName(String partyBCityName) {
        this.partyBCityName = partyBCityName;
    }

    public String getPartyBDistrictNo() {
        return partyBDistrictNo;
    }

    public void setPartyBDistrictNo(String partyBDistrictNo) {
        this.partyBDistrictNo = partyBDistrictNo;
    }

    public String getPartyBDistrictName() {
        return partyBDistrictName;
    }

    public void setPartyBDistrictName(String partyBDistrictName) {
        this.partyBDistrictName = partyBDistrictName;
    }

    public String getPartyBAddress() {
        return partyBAddress;
    }

    public void setPartyBAddress(String partyBAddress) {
        this.partyBAddress = partyBAddress;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getGpAgreementNo() {
        return gpAgreementNo;
    }

    public void setGpAgreementNo(String gpAgreementNo) {
        this.gpAgreementNo = gpAgreementNo;
    }

    public String getRegisterId() {
        return registerId;
    }

    public void setRegisterId(String registerId) {
        this.registerId = registerId;
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

    public Integer getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(Integer storeNum) {
        this.storeNum = storeNum;
    }

    public BigDecimal getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(BigDecimal depositFee) {
        this.depositFee = depositFee;
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

    public String getExPersonId() {
        return exPersonId;
    }

    public void setExPersonId(String exPersonId) {
        this.exPersonId = exPersonId;
    }

    public String getExPerson() {
        return exPerson;
    }

    public void setExPerson(String exPerson) {
        this.exPerson = exPerson;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getAccountNm() {
        return accountNm;
    }

    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm;
    }

    public String getAccountProvinceNo() {
        return accountProvinceNo;
    }

    public void setAccountProvinceNo(String accountProvinceNo) {
        this.accountProvinceNo = accountProvinceNo;
    }

    public String getAccountProvinceNm() {
        return accountProvinceNm;
    }

    public void setAccountProvinceNm(String accountProvinceNm) {
        this.accountProvinceNm = accountProvinceNm;
    }

    public String getAccountCityNo() {
        return accountCityNo;
    }

    public void setAccountCityNo(String accountCityNo) {
        this.accountCityNo = accountCityNo;
    }

    public String getAccountCityNm() {
        return accountCityNm;
    }

    public void setAccountCityNm(String accountCityNm) {
        this.accountCityNm = accountCityNm;
    }

    public String getBankAccountNm() {
        return bankAccountNm;
    }

    public void setBankAccountNm(String bankAccountNm) {
        this.bankAccountNm = bankAccountNm;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getPartyBNm() {
        return partyBNm;
    }

    public void setPartyBNm(String partyBNm) {
        this.partyBNm = partyBNm;
    }

    public String getPartyBTel() {
        return partyBTel;
    }

    public void setPartyBTel(String partyBTel) {
        this.partyBTel = partyBTel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getOaNo() {
        return oaNo;
    }

    public void setOaNo(String oaNo) {
        this.oaNo = oaNo;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public Integer getSubmitOAStatus() {
        return submitOAStatus;
    }

    public void setSubmitOAStatus(Integer submitOAStatus) {
        this.submitOAStatus = submitOAStatus;
    }

    public Integer getSubmitOAUserId() {
        return submitOAUserId;
    }

    public void setSubmitOAUserId(Integer submitOAUserId) {
        this.submitOAUserId = submitOAUserId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getContractType() {
        return contractType;
    }

    public void setContractType(Integer contractType) {
        this.contractType = contractType;
    }
}
