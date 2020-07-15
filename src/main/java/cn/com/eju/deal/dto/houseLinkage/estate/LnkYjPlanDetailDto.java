package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;

import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanDetail;

public class LnkYjPlanDetailDto extends LnkYjPlanDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5712543488324778387L;

	private String equationTypeName;//公式类型名称

	public String getEquationTypeName() {
		return equationTypeName;
	}

	public void setEquationTypeName(String equationTypeName) {
		this.equationTypeName = equationTypeName;
	}
	
}