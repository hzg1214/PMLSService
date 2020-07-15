package cn.com.eju.deal.api.contractChange.model;

import java.math.BigDecimal;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.model.common.PageDto;
/**
 * 门店信息（撤销专用）
* @Title: StoreChangeDto
 */
public class StoreChangeDto extends PageDto {
	private Integer storeId;//门店编号
    private String storeNo;//门店编号
    private String storeName;//门店招牌
    private String address;//地址
    private String addressDetail;//详细地址
    private Integer centerId;//交易中心id
    private String centerName;//维护人中心名称
    private String maintainer;//维护人工号
    private String maintainerName;//维护人姓名
    private Integer businessStatus;//门店营业状态
    private String isCancel;//门店撤销标记
    private String contractStopNo;//合同终止编号
    private String changeCompany;//签约主体变更
    private String approveState;//门店撤销审核状态
    private Integer storeState;//终止状态
    
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddressDetail() {
		return addressDetail;
	}
	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public String getCenterName() {
		return centerName;
	}
	public void setCenterName(String centerName) {
		this.centerName = centerName;
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
	public Integer getBusinessStatus() {
		return businessStatus;
	}
	public void setBusinessStatus(Integer businessStatus) {
		this.businessStatus = businessStatus;
	}
	public String getIsCancel() {
		return isCancel;
	}
	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}
	public String getContractStopNo() {
		return contractStopNo;
	}
	public void setContractStopNo(String contractStopNo) {
		this.contractStopNo = contractStopNo;
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
	public Integer getStoreState() {
		return storeState;
	}
	public void setStoreState(Integer storeState) {
		this.storeState = storeState;
	}
    //------------终止扩展字段---------------//
	//保证金处理方式
	private Integer depositBalance;
	private String depositBalanceNm;
	//保证金退款金额
	private BigDecimal depositBackMoney;

	private BigDecimal receivedAmount;
	
	public Integer getDepositBalance() {
		return depositBalance;
	}
	public void setDepositBalance(Integer depositBalance) {
		this.depositBalance = depositBalance;
	}
	public BigDecimal getDepositBackMoney() {
		return depositBackMoney;
	}
	public void setDepositBackMoney(BigDecimal depositBackMoney) {
		this.depositBackMoney = depositBackMoney;
	}
	public String getDepositBalanceNm() {
		return SystemParam.getDicValueByDicCode(depositBalance+"");
	}
	public void setDepositBalanceNm(String depositBalanceNm) {
		this.depositBalanceNm = depositBalanceNm;
	}

	public BigDecimal getReceivedAmount() {
		return receivedAmount;
	}

	public void setReceivedAmount(BigDecimal receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
}
  