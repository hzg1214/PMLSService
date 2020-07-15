package cn.com.eju.deal.shareaccount.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 房友推送-公盘会员账号
 * @author zhenggang.Huang
 * date: 2019年02月19日 
 */
public class ShareAccount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5548564311442373677L;

	// 主键
	private Integer id;

	//公司编号
	private String companyNo;
	
	//店东姓名
	private String shopName;
	
	//联系电话
	private String phone;
	
	private Date dateCreate;
	
	private Date dateUpdate;
	
	private Boolean delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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