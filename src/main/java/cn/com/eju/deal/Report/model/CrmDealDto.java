package cn.com.eju.deal.Report.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 成销
 */

public class CrmDealDto implements Serializable{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1613525192907529825L;

	private Integer crmUserId;

    private Long registerId;

    private String customerName;

    private String customerCellNumber;

    private String houseAddress;

    private BigDecimal houseArea;

    private BigDecimal totalPrice;

    private Date dealTime;

	public long getCrmUserId() {
		return crmUserId;
	}

	public Long getRegisterId() {
		return registerId;
	}

	public void setCrmUserId(Integer crmUserId) {
		this.crmUserId = crmUserId;
	}



	public void setRegisterId(Long registerId) {
		this.registerId = registerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCellNumber() {
		return customerCellNumber;
	}

	public void setCustomerCellNumber(String customerCellNumber) {
		this.customerCellNumber = customerCellNumber;
	}

	public String getHouseAddress() {
		return houseAddress;
	}

	public void setHouseAddress(String houseAddress) {
		this.houseAddress = houseAddress;
	}

	public BigDecimal getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(BigDecimal houseArea) {
		this.houseArea = houseArea;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getDealTime() {
		return dealTime;
	}

	public void setDealTime(Date dealTime) {
		this.dealTime = dealTime;
	}
}
