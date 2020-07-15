package cn.com.eju.deal.houseLinkage.estate.model;

import java.math.BigDecimal;
import java.util.Date;

public class EstateSupport {

	private Integer id;
	
	private String supportId;

	private String estateId;
	// 配套
	private String title;
	// 描述
	private String description;
	private Integer crtEmpId;

	private Integer uptEmpId;

	private Boolean delFlg;

	private Date crtDt;

	private Date uptDt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSupportId() {
		return supportId;
	}

	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}

	public String getEstateId() {
		return estateId;
	}

	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getCrtEmpId() {
		return crtEmpId;
	}

	public void setCrtEmpId(Integer crtEmpId) {
		this.crtEmpId = crtEmpId;
	}

	public Integer getUptEmpId() {
		return uptEmpId;
	}

	public void setUptEmpId(Integer uptEmpId) {
		this.uptEmpId = uptEmpId;
	}

	public Boolean getDelFlg() {
		return delFlg;
	}

	public void setDelFlg(Boolean delFlg) {
		this.delFlg = delFlg;
	}

	public Date getCrtDt() {
		return crtDt;
	}

	public void setCrtDt(Date crtDt) {
		this.crtDt = crtDt;
	}

	public Date getUptDt() {
		return uptDt;
	}

	public void setUptDt(Date uptDt) {
		this.uptDt = uptDt;
	}
}