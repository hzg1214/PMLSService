package cn.com.eju.deal.houseLinkage.storeDepositDeatil.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.model.common.PageDto;

/**
* @Title: StoreDepositDto
* @Description:  保证金明细
 */
public class StoreDepositDeatilDto  implements Serializable{
	
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
    
    //供应商名称和其编号
    private String providerCode;
    private String providerName;
    
	//期初金额
	private BigDecimal firstAmount;
	//本期新增
	private BigDecimal thisPeriodReceiveAmount;
	//本期退款
	private BigDecimal thisPeriodBackAmount;
	//期末余额
	private BigDecimal endAmount;
	
	//入账日期
	private Date dateRecorded;
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
	public BigDecimal getFirstAmount() {
		return firstAmount;
	}
	public void setFirstAmount(BigDecimal firstAmount) {
		this.firstAmount = firstAmount;
	}
	public BigDecimal getThisPeriodReceiveAmount() {
		return thisPeriodReceiveAmount;
	}
	public void setThisPeriodReceiveAmount(BigDecimal thisPeriodReceiveAmount) {
		this.thisPeriodReceiveAmount = thisPeriodReceiveAmount;
	}
	public BigDecimal getThisPeriodBackAmount() {
		return thisPeriodBackAmount;
	}
	public void setThisPeriodBackAmount(BigDecimal thisPeriodBackAmount) {
		this.thisPeriodBackAmount = thisPeriodBackAmount;
	}
	public BigDecimal getEndAmount() {
		return endAmount;
	}
	public void setEndAmount(BigDecimal endAmount) {
		this.endAmount = endAmount;
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
	
	private BigDecimal receiveSumAmout;
	private BigDecimal paymentSumAmout;
	private String contractNo;

	public BigDecimal getReceiveSumAmout() {
		return receiveSumAmout;
	}
	public void setReceiveSumAmout(BigDecimal receiveSumAmout) {
		this.receiveSumAmout = receiveSumAmout;
	}
	public BigDecimal getPaymentSumAmout() {
		return paymentSumAmout;
	}
	public void setPaymentSumAmout(BigDecimal paymentSumAmout) {
		this.paymentSumAmout = paymentSumAmout;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	
}
  