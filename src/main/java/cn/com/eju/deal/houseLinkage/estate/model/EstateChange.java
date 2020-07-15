package cn.com.eju.deal.houseLinkage.estate.model;

import java.util.Date;

public class EstateChange{

	//变更ID
	private Integer id;

	//对应楼盘ID
	private Integer estateId;

	//变更类型
	private String changeType;

	//变更类型名
	private String changeName;

	//变更详情
	private String changeDetail;

	//变更操作人
	private int operator;

	//变更操作人工号
	private String operatorCode;
	
	//变更操作人名
	private String operatorName;

	//变更操作时间
	private Date changeDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEstateId() {
		return estateId;
	}

	public void setEstateId(Integer estateId) {
		this.estateId = estateId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeName() {
		return changeName;
	}

	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}

	public String getChangeDetail() {
		return changeDetail;
	}

	public void setChangeDetail(String changeDetail) {
		this.changeDetail = changeDetail;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public Date getChangeDate() {
		return changeDate;
	}

	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}
	
	
}