package cn.com.eju.deal.houseLinkage.storedepositSerial.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.model.common.PageDto;

/**
* @Title: StoreDepositSerialDto
* @Description:  保证金流水
 */
public class StoreDepositSerialDto  implements Serializable{
	
	private Integer id;
	
	//城市编号
	private String acCityNo;
	private String cityName;
	
	//核算主体与其编号
	private String accountProject;
    private String accountProjectCode;
    
    //门店名称、门店编号、门店所属中心
    private String storeName;
    private String storeNo;
    private String groupName;
    
    //经纪公司编号和名称
    private String companyNo;
    private String companyName;
    
    //协议书编号
    //oa单号
    private String agreementNo;
    private String oaNo;
    //供应商名称和其编号
    private String providerCode;
    private String providerName;
    
	//实收金额
    private BigDecimal receiveAmout;
	//实退金额
	private BigDecimal paymentAmout;
	
	private String orderTypeVal;
	private Integer orderType;
	//入账日期
	private Date dateRecorded;
	//退账日期
	private Date refundDate;
	
	private Integer rowNum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAcCityNo() {
		return acCityNo;
	}
	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}
	public String getCityName() {
		return SystemParam.getCityNameByCityNo(acCityNo);
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getAccountProject() {
		return accountProject;
	}
	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}
	public String getAccountProjectCode() {
		return accountProjectCode;
	}
	public void setAccountProjectCode(String accountProjectCode) {
		this.accountProjectCode = accountProjectCode;
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
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
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
	
	public Date getDateRecorded() {
		return dateRecorded;
	}
	public void setDateRecorded(Date dateRecorded) {
		this.dateRecorded = dateRecorded;
	}
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}

	public Date getRefundDate() {
		return refundDate;
	}
	public void setRefundDate(Date refundDate) {
		this.refundDate = refundDate;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getOaNo() {
		return oaNo;
	}
	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}
	public String getOrderTypeVal() {
		return orderTypeVal;
	}
	public void setOrderTypeVal(String orderTypeVal) {
		this.orderTypeVal = orderTypeVal;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	public BigDecimal getReceiveAmout() {
		return receiveAmout;
	}
	public void setReceiveAmout(BigDecimal receiveAmout) {
		this.receiveAmout = receiveAmout;
	}
	public BigDecimal getPaymentAmout() {
		return paymentAmout;
	}
	public void setPaymentAmout(BigDecimal paymentAmout) {
		this.paymentAmout = paymentAmout;
	}
	
}
  