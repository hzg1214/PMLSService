package cn.com.eju.deal.user.model;

import java.util.Date;

public class UserBankInfo {
	
	private Integer bankId;
	
	private Integer userId;
	
	private String userName;
	
	private String bankAccount;
	
	private String bankName;
	
	private Date dateCreate;
	
	private Integer userIdCreate;
	
	private String delFlag;
	/**
	 * 是否为常用银行卡，1-是，0-否
	 */
	private Integer isDefault;
	

	

	

	public Integer getBankId() {
		return bankId;
	}

	public void setBankId(Integer bankId) {
		this.bankId = bankId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	
	

}
