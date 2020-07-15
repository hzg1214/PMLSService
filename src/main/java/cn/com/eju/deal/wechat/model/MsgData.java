package cn.com.eju.deal.wechat.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 企业号微信消息共通信息(相同字段)模型
 * @author wenhui.zhang 
 * date: 2017年4月11日 下午3:37:05
 */
public class MsgData implements Serializable {
	// 序列化
	private static final long serialVersionUID = 1L;

	// 主键
	private Integer id;

	// 成员ID列表
	private String toUser;

	// 部门ID列表
	private String toParty;

	// 标签ID列表
	private String totag;

	// 消息类型
	private String msgtype;

	// 企业应用的id
	private Integer agentid;

	// 否是保密消息 0表示否，1表示是，默认0
	private Integer safe;

	// 消息是否发送（0：未发送，1：已发送）
	private String sendFinishFlag;

	// 删除标志 （0：未删除，1：已删除）
	private Boolean delFlag;

	// 创建时间
	private Date dateCreate;

	// 更新时间
	private Date dateUpdate;

	// MsgDataDetail list
	private List<MsgDataDetail> msgDataDetailList;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser == null ? null : toUser.trim();
	}

	public String getToParty() {
		return toParty;
	}

	public void setToParty(String toParty) {
		this.toParty = toParty == null ? null : toParty.trim();
	}

	public String getTotag() {
		return totag;
	}

	public void setTotag(String totag) {
		this.totag = totag == null ? null : totag.trim();
	}

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype == null ? null : msgtype.trim();
	}

	public Integer getAgentid() {
		return agentid;
	}

	public void setAgentid(Integer agentid) {
		this.agentid = agentid;
	}

	public Integer getSafe() {
		return safe;
	}

	public void setSafe(Integer safe) {
		this.safe = safe;
	}

	public String getSendFinishFlag() {
		return sendFinishFlag;
	}

	public void setSendFinishFlag(String sendFinishFlag) {
		this.sendFinishFlag = sendFinishFlag == null ? null : sendFinishFlag.trim();
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
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

	public List<MsgDataDetail> getMsgDataDetailList() {
		return msgDataDetailList;
	}

	public void setMsgDataDetailList(List<MsgDataDetail> msgDataDetailList) {
		this.msgDataDetailList = msgDataDetailList;
	}

	
}