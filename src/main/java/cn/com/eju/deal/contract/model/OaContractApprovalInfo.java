/**
 *OA审批结果意见信息DTO 
 */
package cn.com.eju.deal.contract.model;

import java.util.Date;

public class OaContractApprovalInfo
{
    // 主键
    private Integer id;
    
    // 合同编号
    private String contractNo;
    
    // OA对接No
    private String flowId;
    
    // 排序字段
    private Integer sortIndex;
    
    // 审批人ID
    private String empnumber;
    
    // 审批人名
    private String empname;
    
    // 审批处理意见
    private String content;
    
    // 审批时间
    private Date create_date; 
    
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

	public Integer getSortIndex() {
		return sortIndex;
	}

	public void setSortIndex(Integer sortIndex) {
		this.sortIndex = sortIndex;
	}

	public String getEmpnumber() {
		return empnumber;
	}

	public void setEmpnumber(String empnumber) {
		this.empnumber = empnumber;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
    
}