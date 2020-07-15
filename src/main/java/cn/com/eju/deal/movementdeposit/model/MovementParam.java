package cn.com.eju.deal.movementdeposit.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 房友推送信息
 * @author zhenggang.Huang
 * date: 2019年02月19日 
 */
public class MovementParam implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 8235623008255321981L;


	//公盘合同编号
	private String gpContractNo;
	
	//到账金额
	private String transferAccount;
	
	//过审时间
	private String pastTrialDate;
	
	//公司编号
	private String companyNo;
	
	//店东姓名
	private String shopName;
	
	//联系电话
	private String phone;

	
	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGpContractNo() {
		return gpContractNo;
	}

	public void setGpContractNo(String gpContractNo) {
		this.gpContractNo = gpContractNo;
	}

	public String getTransferAccount() {
		return transferAccount;
	}

	public void setTransferAccount(String transferAccount) {
		this.transferAccount = transferAccount;
	}

	public String getPastTrialDate() {
		return pastTrialDate;
	}

	public void setPastTrialDate(String pastTrialDate) {
		this.pastTrialDate = pastTrialDate;
	}

}