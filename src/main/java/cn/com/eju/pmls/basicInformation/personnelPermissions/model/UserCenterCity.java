package cn.com.eju.pmls.basicInformation.personnelPermissions.model;

import java.io.Serializable;

public class UserCenterCity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4236809832575476359L;
	private Integer id;//主键ID
    private String userCode;//用户编码
    private String userName;//用户名
    private Integer centerId;//中心id
    private Integer cityId;//城市id
    private String cityNo;//城市编号
    private String dateCreate;//创建时间
    private Integer userIdCreate;//创建人
    private Integer delFlag;//删除标识
    //private Integer isPmlsCenter;//是否跑马拉松标识 1是  0否
    
//	public Integer getIsPmlsCenter() {
//		return isPmlsCenter;
//	}
//	public void setIsPmlsCenter(Integer isPmlsCenter) {
//		this.isPmlsCenter = isPmlsCenter;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Integer getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
    
}
