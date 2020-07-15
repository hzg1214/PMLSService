package cn.com.eju.deal.open.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * OMS系统中合同Dto层
 * 
 * @author sunmm
 * @date 2016年8月20日 下午8:36:39
 */
public class OmsContractDto implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = -8453112421648255958L;

	/**
	 * 合同编号
	 */
	private String contractNo;

	/**
	 * 合同类型
	 */
	private String contractType;

	/**
	 * 合同状态
	 */
	private String contractStatus;

	/**
	 * 营业执照编码
	 */
	private String registrId;

	/**
	 * 公司名称
	 */
	private String companyName;

	/**
	 * 合作开始日期
	 */
	private Date dateLifeStart;

	/**
	 * 合作到期日期
	 */
	private Date dateLifeEnd;

	/**
	 * 合作门店数
	 */
	private Integer storeNum;

	/**
	 * 业绩草签日期
	 */
	private Date dateCreate;

	/**
	 * 业绩生效日期
	 */
	private Date performDate;

	/**
	 * 每店保证金额
	 */
	private BigDecimal depositFee;

	/**
	 * 业绩归属拓展专员工号
	 */
	private String expandingPersonnelId;

	/**
	 * 业绩归属拓展专员
	 */
	private String expandingPersonnel;

	/**
	 * 负记录原因
	 */
	private String recordReason;

	/**
	 * 获取合同编号
	 * 
	 * @return contractNo 合同编号
	 */
	public String getContractNo() {
		return contractNo;
	}

	/**
	 * 设置合同编号
	 * 
	 * @param contractNo
	 *            合同编号
	 */
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	/**
	 * 获取合同类型
	 * 
	 * @return contractType 合同类型
	 */
	public String getContractType() {
		return contractType;
	}

	/**
	 * 设置合同类型
	 * 
	 * @param contractType
	 *            合同类型
	 */
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	/**
	 * 获取合同状态
	 * 
	 * @return contractStatus 合同状态
	 */
	public String getContractStatus() {
		return contractStatus;
	}

	/**
	 * 设置合同状态
	 * 
	 * @param contractStatus
	 *            合同状态
	 */
	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	/**
	 * 获取营业执照编码
	 * 
	 * @return registrId 营业执照编码
	 */
	public String getRegistrId() {
		return registrId;
	}

	/**
	 * 设置营业执照编码
	 * 
	 * @param registrId
	 *            营业执照编码
	 */
	public void setRegistrId(String registrId) {
		this.registrId = registrId;
	}

	/**
	 * 获取公司名称
	 * 
	 * @return companyName 公司名称
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * 设置公司名称
	 * 
	 * @param companyName
	 *            公司名称
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * 获取合作开始日期
	 * 
	 * @return dateLifeStart 合作开始日期
	 */
	public Date getDateLifeStart() {
		return dateLifeStart;
	}

	/**
	 * 设置合作开始日期
	 * 
	 * @param dateLifeStart
	 *            合作开始日期
	 */
	public void setDateLifeStart(Date dateLifeStart) {
		this.dateLifeStart = dateLifeStart;
	}

	/**
	 * 获取合作到期日期
	 * 
	 * @return dateLifeEnd 合作到期日期
	 */
	public Date getDateLifeEnd() {
		return dateLifeEnd;
	}

	/**
	 * 设置合作到期日期
	 * 
	 * @param dateLifeEnd
	 *            合作到期日期
	 */
	public void setDateLifeEnd(Date dateLifeEnd) {
		this.dateLifeEnd = dateLifeEnd;
	}

	/**
	 * 获取合作门店数
	 * 
	 * @return storeNum 合作门店数
	 */
	public Integer getStoreNum() {
		return storeNum;
	}

	/**
	 * 设置合作门店数
	 * 
	 * @param storeNum
	 *            合作门店数
	 */
	public void setStoreNum(Integer storeNum) {
		this.storeNum = storeNum;
	}

	/**
	 * 获取业绩草签日期
	 * 
	 * @return dateCreate 业绩草签日期
	 */
	public Date getDateCreate() {
		return dateCreate;
	}

	/**
	 * 设置业绩草签日期
	 * 
	 * @param dateCreate
	 *            业绩草签日期
	 */
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	/**
	 * 获取业绩生效日期
	 * 
	 * @return performDate 业绩生效日期
	 */
	public Date getPerformDate() {
		return performDate;
	}

	/**
	 * 设置业绩生效日期
	 * 
	 * @param performDate
	 *            业绩生效日期
	 */
	public void setPerformDate(Date performDate) {
		this.performDate = performDate;
	}

	/** 
	* 获取每店保证金金额
	* @return depositFee 每店保证金金额
	*/
	public BigDecimal getDepositFee() {
		return depositFee;
	}

	/** 
	* 设置每店保证金金额
	* @param depositFee 每店保证金金额
	*/
	public void setDepositFee(BigDecimal depositFee) {
		this.depositFee = depositFee;
	}

	/**
	 * 获取业绩归属拓展专员工号
	 * 
	 * @return expandingPersonnelId 业绩归属拓展专员工号
	 */
	public String getExpandingPersonnelId() {
		return expandingPersonnelId;
	}

	/**
	 * 设置业绩归属拓展专员工号
	 * 
	 * @param expandingPersonnelId
	 *            业绩归属拓展专员工号
	 */
	public void setExpandingPersonnelId(String expandingPersonnelId) {
		this.expandingPersonnelId = expandingPersonnelId;
	}

	/**
	 * 获取获取业绩归属拓展专员
	 * 
	 * @return expandingPersonnel 业绩归属拓展专员
	 */
	public String getExpandingPersonnel() {
		return expandingPersonnel;
	}

	/**
	 * 设置业绩归属拓展专员
	 * 
	 * @param expandingPersonnel
	 *            业绩归属拓展专员
	 */
	public void setExpandingPersonnel(String expandingPersonnel) {
		this.expandingPersonnel = expandingPersonnel;
	}

	/**
	 * 获取负记录原因
	 * 
	 * @return recordReason 负记录原因
	 */
	public String getRecordReason() {
		return recordReason;
	}

	/**
	 * 设置负记录原因
	 * 
	 * @param recordReason
	 *            负记录原因
	 */
	public void setRecordReason(String recordReason) {
		this.recordReason = recordReason;
	}
}
