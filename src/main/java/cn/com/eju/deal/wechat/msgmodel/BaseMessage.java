package cn.com.eju.deal.wechat.msgmodel;

import java.io.Serializable;

/**
 * 消息模型基类
 * @author wenhui.zhang
 * date: 2017年4月10日 下午4:14:53
 */
public class BaseMessage implements Serializable{
	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;
	
	// 成员ID列表
	private String touser;
	
	// 部门ID列表
	private String toparty;
	
	// 标签ID列表
	private String totag;
	
	// 消息类型
	private String msgtype;
	
	// 企业应用的id
	private Integer agentid;
	
	// 表示是否是保密消息，0表示否，1表示是，默认0
	private Integer safe;
	
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getToparty() {
		return toparty;
	}
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}
	public String getTotag() {
		return totag;
	}
	public void setTotag(String totag) {
		this.totag = totag;
	}
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
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
	
}
