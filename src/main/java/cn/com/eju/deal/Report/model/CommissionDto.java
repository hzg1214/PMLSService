package cn.com.eju.deal.Report.model;

import java.io.Serializable;
import java.util.List;

/**
 * 结佣
 */

public class CommissionDto implements Serializable{
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 721410615233276203L;
	
	private List<CommissionInfoDto> commissionInfoDto;

	public List<CommissionInfoDto> getCommissionInfoDto() {
		return commissionInfoDto;
	}

	public void setCommissionInfoDto(List<CommissionInfoDto> commissionInfoDto) {
		this.commissionInfoDto = commissionInfoDto;
	}
}
