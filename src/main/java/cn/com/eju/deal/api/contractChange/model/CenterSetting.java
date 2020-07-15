package cn.com.eju.deal.api.contractChange.model;
/**
 * 发起人和申请人权限
* @Title: CenterSetting
 */
public class CenterSetting {
	private Integer id;
	private Integer centerId;
	private String mdcxSendUserCode;
	private String mdcxSendUserName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMdcxSendUserCode() {
		return mdcxSendUserCode;
	}
	public void setMdcxSendUserCode(String mdcxSendUserCode) {
		this.mdcxSendUserCode = mdcxSendUserCode;
	}
	public String getMdcxSendUserName() {
		return mdcxSendUserName;
	}
	public void setMdcxSendUserName(String mdcxSendUserName) {
		this.mdcxSendUserName = mdcxSendUserName;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	
	
}
  