package cn.com.eju.deal.keFuOrder.model;

import java.util.List;

public class WxKefuOrder {
    private Integer id;
    private String orderNo;
    private Integer companyId;
    private String customerName;
    private String customerTel;
    private Integer dealStatus;
    private Integer checkStatus;
    private String companyName;
    private String companyNo;
    private String storeName;
    private String storeNo;
    private String storeAddress;
    private String consultProductNm;
    private String questionDesc;
    private String questionLevelNm;
    private String dealStatusNm;
    private String checkStatusNm;

    private String userCode;
    private String userName;

    private List<WxKefuOrderReply> replyList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public Integer getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public List<WxKefuOrderReply> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<WxKefuOrderReply> replyList) {
        this.replyList = replyList;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getConsultProductNm() {
        return consultProductNm;
    }

    public void setConsultProductNm(String consultProductNm) {
        this.consultProductNm = consultProductNm;
    }

    public String getQuestionDesc() {
        return questionDesc;
    }

    public void setQuestionDesc(String questionDesc) {
        this.questionDesc = questionDesc;
    }

    public String getQuestionLevelNm() {
        return questionLevelNm;
    }

    public void setQuestionLevelNm(String questionLevelNm) {
        this.questionLevelNm = questionLevelNm;
    }

    public String getDealStatusNm() {
        return dealStatusNm;
    }

    public void setDealStatusNm(String dealStatusNm) {
        this.dealStatusNm = dealStatusNm;
    }

    public String getCheckStatusNm() {
        return checkStatusNm;
    }

    public void setCheckStatusNm(String checkStatusNm) {
        this.checkStatusNm = checkStatusNm;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
