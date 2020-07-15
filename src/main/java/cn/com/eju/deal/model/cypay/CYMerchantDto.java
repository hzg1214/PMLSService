package cn.com.eju.deal.model.cypay;

/**
 * Created by Administrator on 2017/5/2 0002.
 */
public class CYMerchantDto {
    private int merchantId;
    private String service;//服务名称(createMerchant)
    private Long requestTime;//请求时间
    private String memberFlag;//用户标示
    private int memberTypeId;//用户类型（参考附录五）17
    private int loginModeId;//登陆方式编号(22）
    private String memberName;//用户名称
    private String mp;//信息推送手机号
    private String email;//信息推送邮箱
    private int certType;//证件类型(参考附录二)
    private String certName;//证件姓名
    private String certNumber;//证件号(加密处理)
    private String payPassword;//支付密码(加密处理)（非必填，不填会下发随机四位数字密码至所填手机号）
    private String bankCode;//提现银行卡编码
    private String cardNum;//卡号(加密处理)
    private String cardBranchBankName;//卡开户支行名
    private String cardBranchBankProvince;//卡开户支行所在省
    private String cardBranchBankCity;//卡开户支行所在市
    private String cardId;//商户绑定银行卡在我们系统的卡ID，
    private String memberId;//.会员ID(即二级商户ID)
    private String cityNo;//

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }

    public String getMemberFlag() {
        return memberFlag;
    }

    public void setMemberFlag(String memberFlag) {
        this.memberFlag = memberFlag;
    }

    public int getMemberTypeId() {
        return memberTypeId;
    }

    public void setMemberTypeId(int memberTypeId) {
        this.memberTypeId = memberTypeId;
    }

    public int getLoginModeId() {
        return loginModeId;
    }

    public void setLoginModeId(int loginModeId) {
        this.loginModeId = loginModeId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public String getCertName() {
        return certName;
    }

    public void setCertName(String certName) {
        this.certName = certName;
    }

    public String getCertNumber() {
        return certNumber;
    }

    public void setCertNumber(String certNumber) {
        this.certNumber = certNumber;
    }

    public String getPayPassword() {
        return payPassword;
    }

    public void setPayPassword(String payPassword) {
        this.payPassword = payPassword;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getCardBranchBankName() {
        return cardBranchBankName;
    }

    public void setCardBranchBankName(String cardBranchBankName) {
        this.cardBranchBankName = cardBranchBankName;
    }

    public String getCardBranchBankProvince() {
        return cardBranchBankProvince;
    }

    public void setCardBranchBankProvince(String cardBranchBankProvince) {
        this.cardBranchBankProvince = cardBranchBankProvince;
    }

    public String getCardBranchBankCity() {
        return cardBranchBankCity;
    }

    public void setCardBranchBankCity(String cardBranchBankCity) {
        this.cardBranchBankCity = cardBranchBankCity;
    }
}
