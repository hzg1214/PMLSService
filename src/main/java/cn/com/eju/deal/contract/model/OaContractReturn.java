/**
 *OA审批结果DTO 
 */
package cn.com.eju.deal.contract.model;

import java.util.Date;

public class OaContractReturn
{
    // 主键
    private Integer id;
    
    // 合同编号
    private String contractNo;
    
    // OA对接No
    private String flowId;
    
    // 合同状态
    private Integer contractStatus;
    
    //合同类别(新签:18601；续签：18602)
    private Integer originalContractdistinction;
    
    // OA审批时间
    private Date OAPerformDate;
    
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

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public Integer getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(Integer contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Integer getOriginalContractdistinction() {
		return originalContractdistinction;
	}

	public void setOriginalContractdistinction(Integer originalContractdistinction) {
		this.originalContractdistinction = originalContractdistinction;
	}

	public Date getOAPerformDate() {
		return OAPerformDate;
	}

	public void setOAPerformDate(Date oAPerformDate) {
		OAPerformDate = oAPerformDate;
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

    
    
}