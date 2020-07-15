package cn.com.eju.pmls.borrowMoneyFrameContract.model;

import java.io.Serializable;
/**
 * 
 * desc:借佣框架协议公司与关联公司entity
 * @date   :2020年4月29日
 */
public class BorrowMoneyCompanyRelated implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String parentCompanyNo;//借佣框架协议公司编号
	private String parentCompanyName;//借佣框架协议公司名称
	private String companyNo;//关联公司编号
	private String companyName;//公司名称
	private Integer isBind;//是否已经关联 1是 0否
	private String dateCreate;
	private String userIdCreate;
	private Integer delFlag;//删除标识
	private String dateUpdate;
	private String userIdUpdate;
	
	public String getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(String dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public String getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(String userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentCompanyNo() {
		return parentCompanyNo;
	}
	public void setParentCompanyNo(String parentCompanyNo) {
		this.parentCompanyNo = parentCompanyNo;
	}
	public String getParentCompanyName() {
		return parentCompanyName;
	}
	public void setParentCompanyName(String parentCompanyName) {
		this.parentCompanyName = parentCompanyName;
	}
	public Integer getIsBind() {
		return isBind;
	}
	public void setIsBind(Integer isBind) {
		this.isBind = isBind;
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
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public String getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(String userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	
}
