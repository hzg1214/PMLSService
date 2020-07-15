package cn.com.eju.deal.houseLinkage.estate.model;

import java.util.Date;

/**
 * 垫佣甲方
* @Title: MattressNail
 */
public class MattressNail {
	private Integer id;
	private Integer mattressNailId;
	private String name;
	private Date dateCreate;
	private Integer userIdCreate;
	private Date dateUpt;
	private Integer userIdUpt;
	private String delFlag;
	
	public Integer getMattressNailId() {
		return mattressNailId;
	}
	public void setMattressNailId(Integer mattressNailId) {
		this.mattressNailId = mattressNailId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
  