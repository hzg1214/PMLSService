package cn.com.eju.deal.keFuOrder.model;

import java.util.Date;
import java.util.List;

import cn.com.eju.deal.dto.contract.ContractFileDto;

/**
 * desc:查看页面的回复信息
 * @author :zhenggang.Huang
 * @date   :2019年3月20日
 */
public class KeFuOrderCkDto {
	private Integer id;//reply 的id
	private String orderId;
	private Date ckDate;//回复时间
	private String ckDesc;//回复内容
	private String userCode; // 回复人Code
	private String ckUserName;//回复人姓名
	private Integer type;   // 工单处理类型
	private List<ContractFileDto> orderCkFileList;// 图片列表

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

	public Date getCkDate() {
		return ckDate;
	}

	public void setCkDate(Date ckDate) {
		this.ckDate = ckDate;
	}

	public String getCkDesc() {
		return ckDesc;
	}

	public void setCkDesc(String ckDesc) {
		this.ckDesc = ckDesc;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getCkUserName() {
		return ckUserName;
	}

	public void setCkUserName(String ckUserName) {
		this.ckUserName = ckUserName;
	}

	public List<ContractFileDto> getOrderCkFileList() {
		return orderCkFileList;
	}

	public void setOrderCkFileList(List<ContractFileDto> orderCkFileList) {
		this.orderCkFileList = orderCkFileList;
	}
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}