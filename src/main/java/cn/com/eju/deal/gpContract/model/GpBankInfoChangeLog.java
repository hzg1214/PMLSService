package cn.com.eju.deal.gpContract.model;

import java.util.Date;
/**
 * 
* @Description: 银行信息调整详情日志表
 */
public class GpBankInfoChangeLog {
    private Integer id;

    private Integer companyId;

    private String partyB;

    private Integer gpContractId;

    private String gpContractNo;

    private String changeContent;

    private String oldAccountNm;

    private String oldAccountProvinceNo;

    private String oldAccountProvinceNm;

    private String oldAccountCityNo;

    private String oldAccountCityNm;

    private String oldBankAccountNm;

    private String oldBankAccount;

    private String accountNm;

    private String accountProvinceNo;

    private String accountProvinceNm;

    private String accountCityNo;

    private String accountCityNm;

    private String bankAccountNm;

    private String bankAccount;

    private Integer startFlag;

    private Date dateCreate;

    private Integer userIdCreate;

    private String userName;

    private String userCode;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB == null ? null : partyB.trim();
    }

    public Integer getGpContractId() {
        return gpContractId;
    }

    public void setGpContractId(Integer gpContractId) {
        this.gpContractId = gpContractId;
    }

    public String getGpContractNo() {
        return gpContractNo;
    }

    public void setGpContractNo(String gpContractNo) {
        this.gpContractNo = gpContractNo == null ? null : gpContractNo.trim();
    }

    public String getChangeContent() {
        return changeContent;
    }

    public void setChangeContent(String changeContent) {
        this.changeContent = changeContent == null ? null : changeContent.trim();
    }

    public String getOldAccountNm() {
        return oldAccountNm;
    }

    public void setOldAccountNm(String oldAccountNm) {
        this.oldAccountNm = oldAccountNm == null ? null : oldAccountNm.trim();
    }

    public String getOldAccountProvinceNo() {
        return oldAccountProvinceNo;
    }

    public void setOldAccountProvinceNo(String oldAccountProvinceNo) {
        this.oldAccountProvinceNo = oldAccountProvinceNo == null ? null : oldAccountProvinceNo.trim();
    }

    public String getOldAccountProvinceNm() {
        return oldAccountProvinceNm;
    }

    public void setOldAccountProvinceNm(String oldAccountProvinceNm) {
        this.oldAccountProvinceNm = oldAccountProvinceNm == null ? null : oldAccountProvinceNm.trim();
    }

    public String getOldAccountCityNo() {
        return oldAccountCityNo;
    }

    public void setOldAccountCityNo(String oldAccountCityNo) {
        this.oldAccountCityNo = oldAccountCityNo == null ? null : oldAccountCityNo.trim();
    }

    public String getOldAccountCityNm() {
        return oldAccountCityNm;
    }

    public void setOldAccountCityNm(String oldAccountCityNm) {
        this.oldAccountCityNm = oldAccountCityNm == null ? null : oldAccountCityNm.trim();
    }

    public String getOldBankAccountNm() {
        return oldBankAccountNm;
    }

    public void setOldBankAccountNm(String oldBankAccountNm) {
        this.oldBankAccountNm = oldBankAccountNm == null ? null : oldBankAccountNm.trim();
    }

    public String getOldBankAccount() {
        return oldBankAccount;
    }

    public void setOldBankAccount(String oldBankAccount) {
        this.oldBankAccount = oldBankAccount == null ? null : oldBankAccount.trim();
    }

    public String getAccountNm() {
        return accountNm;
    }

    public void setAccountNm(String accountNm) {
        this.accountNm = accountNm == null ? null : accountNm.trim();
    }

    public String getAccountProvinceNo() {
        return accountProvinceNo;
    }

    public void setAccountProvinceNo(String accountProvinceNo) {
        this.accountProvinceNo = accountProvinceNo == null ? null : accountProvinceNo.trim();
    }

    public String getAccountProvinceNm() {
        return accountProvinceNm;
    }

    public void setAccountProvinceNm(String accountProvinceNm) {
        this.accountProvinceNm = accountProvinceNm == null ? null : accountProvinceNm.trim();
    }

    public String getAccountCityNo() {
        return accountCityNo;
    }

    public void setAccountCityNo(String accountCityNo) {
        this.accountCityNo = accountCityNo == null ? null : accountCityNo.trim();
    }

    public String getAccountCityNm() {
        return accountCityNm;
    }

    public void setAccountCityNm(String accountCityNm) {
        this.accountCityNm = accountCityNm == null ? null : accountCityNm.trim();
    }

    public String getBankAccountNm() {
        return bankAccountNm;
    }

    public void setBankAccountNm(String bankAccountNm) {
        this.bankAccountNm = bankAccountNm == null ? null : bankAccountNm.trim();
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount == null ? null : bankAccount.trim();
    }

    public Integer getStartFlag() {
        return startFlag;
    }

    public void setStartFlag(Integer startFlag) {
        this.startFlag = startFlag;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode == null ? null : userCode.trim();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}