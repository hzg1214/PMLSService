package cn.com.eju.deal.dto.contract;

import java.util.Date;

/**
 * 保证金到账
 * @author zhaoshengying
 * @date 2016年9月2日 下午6:12:26
 */
public class PerformNodeRecordDto {

	/**
	 * 主键ID
	 */
    private Integer id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 门店编号
     */
    private String storeNo;

    /**
     * 门店店招
     */
    private String storeName;

    /**
     * 门店地址
     */
    private String storeAddressDetail;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同状态(草签、作废、审核通过、终止)
     */
    private String contractState;

    /**
     * 合同类型(A版、B版、C版、A转B版、B转A版)
     */
    private String contractType;

    /**
     * OA审批通过时间
     */
    private Date oaApprovalDate;
    /**
     * OA审批通过时间str
     */
    private String oaApprovalDateStr;
    /**
     * 保证金到账时间
     */
    private Date depositArrivalDate;
    /**
     * 保证金到账时间str
     */
    private String depositArrivalDateStr;
    /**
     * 翻牌验收完成时间
     */
    private Date flipPassDate;

    /**
     * 更新时间
     */
    private Date dateUpdate;

    /**
     * 创建人
     */
    private Integer userIdCreate;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 删除标识(0:未删除  1:已删除)
     */
    private Boolean delFlag;
    /**
     * 城市No
     */
    private String cityNo;
    
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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

	public String getStoreAddressDetail() {
		return storeAddressDetail;
	}

	public void setStoreAddressDetail(String storeAddressDetail) {
		this.storeAddressDetail = storeAddressDetail;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractState() {
		return contractState;
	}

	public void setContractState(String contractState) {
		this.contractState = contractState;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public Date getOaApprovalDate() {
		return oaApprovalDate;
	}

	public void setOaApprovalDate(Date oaApprovalDate) {
		this.oaApprovalDate = oaApprovalDate;
	}

	public Date getDepositArrivalDate() {
		return depositArrivalDate;
	}

	public void setDepositArrivalDate(Date depositArrivalDate) {
		this.depositArrivalDate = depositArrivalDate;
	}

	public Date getFlipPassDate() {
		return flipPassDate;
	}

	public void setFlipPassDate(Date flipPassDate) {
		this.flipPassDate = flipPassDate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Integer getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public void setOaApprovalDateStr(String oaApprovalDateStr) {
		this.oaApprovalDateStr = oaApprovalDateStr;
	}

	public void setDepositArrivalDateStr(String depositArrivalDateStr) {
		this.depositArrivalDateStr = depositArrivalDateStr;
	}

	public String getOaApprovalDateStr() {
		return oaApprovalDateStr;
	}

	public String getDepositArrivalDateStr() {
		return depositArrivalDateStr;
	}

	
    
}
