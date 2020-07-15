package cn.com.eju.deal.wechat.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 企业号微信消息-各种类型消息不同字段的集合
 * @author wenhui.zhang 
 * date: 2017年4月11日 下午3:48:50
 */
public class MsgDataDetail implements Serializable {
	// 序列化
	private static final long serialVersionUID = 1L;

	// 主键
	private Integer id;

	// MsgDate的主键
	private Integer dataId;

	// 消息内容
	private String content;

	// 媒体文件id
	private String mediaid;

	// 标题
	private String title;

	// 描述
	private String discription;

	// 点击后跳转的链接
	private String url;

	// 图文消息的图片链接
	private String picurl;

	// 删除标示（0：未删除，1：已删除）
	private Boolean delFlag;

	// 创建时间
	private Date dateCreate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDataId() {
		return dataId;
	}

	public void setDataId(Integer dataId) {
		this.dataId = dataId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getMediaid() {
		return mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid == null ? null : mediaid.trim();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription == null ? null : discription.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl == null ? null : picurl.trim();
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
}