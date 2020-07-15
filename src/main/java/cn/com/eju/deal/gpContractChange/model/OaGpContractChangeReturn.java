/**
 *OA审批结果DTO 
 */
package cn.com.eju.deal.gpContractChange.model;

import java.util.Date;

public class OaGpContractChangeReturn
{
    // 主键
    private Integer id;
    
    // 公盘合同终止编号
    private String gpContractStopNo;
    // OA编号
    private String gpContractStopOaNo;
    
    // OA对接No
    private String flowId;
    
    // 合同状态
    private Integer approveStatus;
    
    // OA审批时间
    private Date approveDate;
    
    // 处理状态（0:未处理；1:已处理）
    private int hasDeal;
    
	//更新时间
    private Date dateUpdate;

    //创建时间
    private Date dateCreate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGpContractStopNo() {
		return gpContractStopNo;
	}

	public void setGpContractStopNo(String gpContractStopNo) {
		this.gpContractStopNo = gpContractStopNo;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public Integer getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}

	public Date getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(Date approveDate) {
		this.approveDate = approveDate;
	}

	public int getHasDeal() {
		return hasDeal;
	}

	public void setHasDeal(int hasDeal) {
		this.hasDeal = hasDeal;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getGpContractStopOaNo() {
		return gpContractStopOaNo;
	}

	public void setGpContractStopOaNo(String gpContractStopOaNo) {
		this.gpContractStopOaNo = gpContractStopOaNo;
	}
    
    
    
}