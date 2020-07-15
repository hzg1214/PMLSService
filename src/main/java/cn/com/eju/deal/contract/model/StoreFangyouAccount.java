/**
 *门店房友账号关联表结果DTO 
 */
package cn.com.eju.deal.contract.model;

import java.util.Date;

public class StoreFangyouAccount
{
    // 主键
    private Integer id;
    
    // 门店ID
    private Integer storeId;

    // 门店编号
    private String storeNo;
    
    // 门店名称
    private String name;
    
    // 房友账号（公司NO）
    private String fangyouNo;

    // 开通状态（0:未开通；1:开通中;2:已开通）
    private String openStatus;

    // 绑定状态（1:绑定；0:解绑）
    private String operType;

    // 创建者
    private Integer userIdCreate;

    //创建时间
    private Date dateCreate;

    // 删除标识
    private String delFlag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getStoreNo() {
		return storeNo;
	}

	public void setStoreNo(String storeNo) {
		this.storeNo = storeNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFangyouNo() {
		return fangyouNo;
	}

	public void setFangyouNo(String fangyouNo) {
		this.fangyouNo = fangyouNo;
	}

	public String getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(String openStatus) {
		this.openStatus = openStatus;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public Integer getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
    
}