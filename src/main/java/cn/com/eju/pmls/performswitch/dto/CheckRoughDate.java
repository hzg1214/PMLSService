package cn.com.eju.pmls.performswitch.dto;

import java.util.Date;

/**
 * 验证是否有尚未审核的
 */
public class CheckRoughDate {
    /**
     * 主键
     */
    private Integer id;
    private Date roughDate;
    private String cityNo;
    private String cityName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getRoughDate() {
		return roughDate;
	}
	public void setRoughDate(Date roughDate) {
		this.roughDate = roughDate;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
    
    
    
}
