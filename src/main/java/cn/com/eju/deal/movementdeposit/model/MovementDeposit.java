package cn.com.eju.deal.movementdeposit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房友推送保证金信息
 * @author zhenggang.Huang
 * date: 2019年02月19日 
 */
public class MovementDeposit implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8235623008255321981L;

	// 主键
	private Integer id;

	//公盘合同编号
	private String gpContractNo;
	
	//到账金额
	private BigDecimal transferAccount;
	
	//过审时间
	private Date pastTrialDate;
	
	private Date dateCreate;
	
	private Date dateUpdate;
	
	private Boolean delFlag;

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

	public BigDecimal getTransferAccount() {
		return transferAccount;
	}

	public void setTransferAccount(BigDecimal transferAccount) {
		this.transferAccount = transferAccount;
	}

	public Date getPastTrialDate() {
		return pastTrialDate;
	}

	public void setPastTrialDate(Date pastTrialDate) {
		this.pastTrialDate = pastTrialDate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	
}