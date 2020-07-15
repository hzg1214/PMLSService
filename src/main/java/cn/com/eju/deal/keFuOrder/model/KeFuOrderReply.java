package cn.com.eju.deal.keFuOrder.model;

import java.util.Date;

/**
 * 
 * desc:回复
 * @author :zhenggang.Huang
 * @date   :2019年3月20日
 */
public class KeFuOrderReply {
    private Integer id;
    private String orderId;
    private Date replyDate;
    private String replyDesc;
    private Integer dealStatus;
    private Date dateCreate;
    private Integer userCreate;
    private Date dateUpdate;
    private Integer userUpdate;
    private Boolean delFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public Date getReplyDate() {
		return replyDate;
	}
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	public String getReplyDesc() {
		return replyDesc;
	}
	public void setReplyDesc(String replyDesc) {
		this.replyDesc = replyDesc;
	}
	public Integer getDealStatus() {
		return dealStatus;
	}
	public void setDealStatus(Integer dealStatus) {
		this.dealStatus = dealStatus;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Integer getUserCreate() {
		return userCreate;
	}
	public void setUserCreate(Integer userCreate) {
		this.userCreate = userCreate;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public Integer getUserUpdate() {
		return userUpdate;
	}
	public void setUserUpdate(Integer userUpdate) {
		this.userUpdate = userUpdate;
	}
	public Boolean getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

}