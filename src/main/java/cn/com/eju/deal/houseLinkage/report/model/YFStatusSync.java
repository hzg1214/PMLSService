package cn.com.eju.deal.houseLinkage.report.model;

import java.io.Serializable;
import java.util.Date;

public class YFStatusSync implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4806355957463679616L;
	
	
	private String crm_report_id;
	private String report_status;
	private String success_sale_time;
	private String unpass_suggestion;
	private String building_house_code;
	private String building_area;
	private String total_price;
	private String yf_report_id;
	
	public String getYf_report_id() {
		return yf_report_id;
	}
	public void setYf_report_id(String yf_report_id) {
		this.yf_report_id = yf_report_id;
	}
	public String getCrm_report_id() {
		return crm_report_id;
	}
	public void setCrm_report_id(String crm_report_id) {
		this.crm_report_id = crm_report_id;
	}
	public String getReport_status() {
		return report_status;
	}
	public void setReport_status(String report_status) {
		this.report_status = report_status;
	}
	public String getSuccess_sale_time() {
		return success_sale_time;
	}
	public void setSuccess_sale_time(String success_sale_time) {
		this.success_sale_time = success_sale_time;
	}
	public String getUnpass_suggestion() {
		return unpass_suggestion;
	}
	public void setUnpass_suggestion(String unpass_suggestion) {
		this.unpass_suggestion = unpass_suggestion;
	}
	public String getBuilding_house_code() {
		return building_house_code;
	}
	public void setBuilding_house_code(String building_house_code) {
		this.building_house_code = building_house_code;
	}
	public String getBuilding_area() {
		return building_area;
	}
	public void setBuilding_area(String building_area) {
		this.building_area = building_area;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
}
