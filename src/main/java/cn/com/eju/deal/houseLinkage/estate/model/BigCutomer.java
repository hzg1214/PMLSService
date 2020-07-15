package cn.com.eju.deal.houseLinkage.estate.model;

import java.util.Date;

/**
 * 大客户
* @Title: BigCutomer
 */
public class BigCutomer {
	private Integer id;
	private Integer bigCustomerId;
	private String name;
	private Date dateCreate;
	private Integer userIdCreate;
	private Date dateUpt;
	private Integer userIdUpt;
	private String delFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBigCustomerId() {
		return bigCustomerId;
	}
	public void setBigCustomerId(Integer bigCustomerId) {
		this.bigCustomerId = bigCustomerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Date getDateUpt() {
		return dateUpt;
	}
	public void setDateUpt(Date dateUpt) {
		this.dateUpt = dateUpt;
	}
	public Integer getUserIdUpt() {
		return userIdUpt;
	}
	public void setUserIdUpt(Integer userIdUpt) {
		this.userIdUpt = userIdUpt;
	}
	
	
	
}
  