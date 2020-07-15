package cn.com.eju.deal.Report.model;

import java.io.Serializable;

/**
 * 大定
 * */
public class ApproDecideAuditDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1962300972710708891L;
	
	private Integer crmUserId;
	private Integer  approveState;	
	private String approveInfo;
	
	public Integer getCrmUserId() {
		return crmUserId;
	}
	public void setCrmUserId(Integer crmUserId) {
		this.crmUserId = crmUserId;
	}
	public Integer getApproveState() {
		return approveState;
	}
	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}
	public String getApproveInfo() {
		return approveInfo;
	}
	public void setApproveInfo(String approveInfo) {
		this.approveInfo = approveInfo;
	}
}
