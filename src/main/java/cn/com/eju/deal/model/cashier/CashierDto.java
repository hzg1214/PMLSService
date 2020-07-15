package cn.com.eju.deal.model.cashier;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/4/7 0007.
 */
public class CashierDto {

    private Integer id;
    /**
     * collectionType
     * 17904：保证金
     * 17905：平台费
     */
    private String receiveNo;
    private String collectionType;
    private String collectionMethod;
    private String bankCode;
    private String bankName;
    private String accountName;
    private String cardNo;
    private String oaBankId;
    private String oaBankRemak1;
    private String remark;

    private String userCode;//创建人
    private Integer userId;//创建人
    private String cityNo;//城市
    private int contractId;//合同ID
    private String contractNo;//合同ID
    private int status;
    private String delFlag;
    private String responseCode;//返回支付状态
    private BigDecimal payMoney;//支付金额
    private BigDecimal payTotalFee;//支付税额
    private String toolCode;//支付工具
    private String paySeq;//流水号

    private String storeNoStr;
    private String storeNameStr;
    private String totalMoneyStr;
    private String receivedMoneyStr;
    private String receivingMoneyStr;
    private String nowMoneyStr;
    private String fileRecordMainIdStr;

    private String storeNo;
    private String centerId;//中心id

    public String getCenterId() {
        return centerId;
    }

    public void setCenterId(String centerId) {
        this.centerId = centerId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getNowMoneyStr() {
        return nowMoneyStr;
    }

    public void setNowMoneyStr(String nowMoneyStr) {
        this.nowMoneyStr = nowMoneyStr;
    }

    public String getReceivedMoneyStr() {
        return receivedMoneyStr;
    }

    public void setReceivedMoneyStr(String receivedMoneyStr) {
        this.receivedMoneyStr = receivedMoneyStr;
    }

    public String getTotalMoneyStr() {
        return totalMoneyStr;
    }

    public void setTotalMoneyStr(String totalMoneyStr) {
        this.totalMoneyStr = totalMoneyStr;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getFileRecordMainIdStr() {
        return fileRecordMainIdStr;
    }

    public void setFileRecordMainIdStr(String fileRecordMainIdStr) {
        this.fileRecordMainIdStr = fileRecordMainIdStr;
    }

    public String getStoreNameStr() {
        return storeNameStr;
    }

    public void setStoreNameStr(String storeNameStr) {
        this.storeNameStr = storeNameStr;
    }

    public String getStoreNoStr() {
        return storeNoStr;
    }

    public void setStoreNoStr(String storeNoStr) {
        this.storeNoStr = storeNoStr;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getReceivingMoneyStr() {
        return receivingMoneyStr;
    }

    public void setReceivingMoneyStr(String receivingMoneyStr) {
        this.receivingMoneyStr = receivingMoneyStr;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
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

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
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

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOaBankId() {
        return oaBankId;
    }

    public void setOaBankId(String oaBankId) {
        this.oaBankId = oaBankId;
    }

    public String getOaBankRemak1() {
        return oaBankRemak1;
    }

    public void setOaBankRemak1(String oaBankRemak1) {
        this.oaBankRemak1 = oaBankRemak1;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPaySeq() {
        return paySeq;
    }

    public void setPaySeq(String paySeq) {
        this.paySeq = paySeq;
    }

    public BigDecimal getPayTotalFee() {
        return payTotalFee;
    }

    public void setPayTotalFee(BigDecimal payTotalFee) {
        this.payTotalFee = payTotalFee;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
