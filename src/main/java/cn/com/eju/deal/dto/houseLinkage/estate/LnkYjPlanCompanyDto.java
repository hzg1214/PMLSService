package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;

import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanCompany;

public class LnkYjPlanCompanyDto extends LnkYjPlanCompany implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3130332148194738364L;

	private String companyNo;//公司编号
	
	private String companyName;//公司名称

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
	
}