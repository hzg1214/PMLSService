package cn.com.eju.deal.scene.inCommission.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.com.eju.deal.dto.scene.inCommission.InCommissionResultDto;

public class InCommissionLog{
	
	private Integer id;
	
	private String operType;
	
	private String fileName;
	
	private Integer operUserId;
	
	private String operUserName;
	
	private Date operDt;
	
	private String remarks;
	
	private String templateType;
	private String amountType; 
	private Integer year ;
	private String projectNo ;
	private String estateId ;
	private String estateNm ;
	private String reportId ;
	private Integer detailId ;
	private String buildingNo ;
	private Integer num;
	private String dealAmount; 
	private Date dealDate ;
	private Double subTotalPreTax ;
	private Double beforeAmountPreTax; 
	private Double janPreTax ;
	private Double febPreTax ;
	private Double marPreTax ;
	private Double aprPreTax ;
	private Double mayPreTax ;
	private Double junPreTax ;
	private Double julPreTax ;
	private Double augPreTax ;
	private Double sepPreTax ;
	private Double octPreTax ;
	private Double novPreTax ;
	private Double decPreTax ;
	private Double subTotalAfterTax ;
	private Double beforeAmountAfterTax; 
	private Double janAfterTax ;
	private Double febAfterTax ;
	private Double marAfterTax ;
	private Double aprAfterTax ;
	private Double mayAfterTax ;
	private Double junAfterTax ;
	private Double julAfterTax ;
	private Double augAfterTax ;
	private Double sepAfterTax ;
	private Double octAfterTax ;
	private Double novAfterTax ;
	private Double decAfterTax ;
	
	public void setInCommissionResult(InCommissionResultDto icDto, int index){
		setProjectNo(icDto.getProjectNo());
		setEstateId(icDto.getEstateId());
		setEstateNm(icDto.getEstateNm());
		setReportId(icDto.getReportId());
		setDetailId(icDto.getDetailId());
		setBuildingNo(icDto.getBuildingNo());
		setNum(icDto.getNum());
		setDealAmount(icDto.getDealAmount());
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			setDealDate(formatter.parse(icDto.getDealDate()));
		} catch (ParseException e) {}
		if (index == 1){
			String templateType = icDto.getTemplateType(); 
			setTemplateType(templateType);
			if ("20601".equals(templateType)) setAmountType("20701");
			else if ("20602".equals(templateType)) setAmountType("20703");
			else if ("20603".equals(templateType)) setAmountType("20704");
			if (icDto.getSubTotalPreTax01()!=null) setSubTotalPreTax(Double.valueOf(icDto.getSubTotalPreTax01()));
			if (icDto.getBeforeAmountPreTax01()!=null) setBeforeAmountPreTax(Double.valueOf(icDto.getBeforeAmountPreTax01()));
			if (icDto.getJanPreTax01()!=null) setJanPreTax(Double.valueOf(icDto.getJanPreTax01()));
			if (icDto.getFebPreTax01()!=null) setFebPreTax(Double.valueOf(icDto.getFebPreTax01()));
			if (icDto.getMarPreTax01()!=null) setMarPreTax(Double.valueOf(icDto.getMarPreTax01()));
			if (icDto.getAprPreTax01()!=null) setAprPreTax(Double.valueOf(icDto.getAprPreTax01()));
			if (icDto.getMayPreTax01()!=null) setMayPreTax(Double.valueOf(icDto.getMayPreTax01()));
			if (icDto.getJunPreTax01()!=null) setJunPreTax(Double.valueOf(icDto.getJunPreTax01()));
			if (icDto.getJulPreTax01()!=null) setJulPreTax(Double.valueOf(icDto.getJulPreTax01()));
			if (icDto.getAugPreTax01()!=null) setAugPreTax(Double.valueOf(icDto.getAugPreTax01()));
			if (icDto.getSepPreTax01()!=null) setSepPreTax(Double.valueOf(icDto.getSepPreTax01()));
			if (icDto.getOctPreTax01()!=null) setOctPreTax(Double.valueOf(icDto.getOctPreTax01()));
			if (icDto.getNovPreTax01()!=null) setNovPreTax(Double.valueOf(icDto.getNovPreTax01()));
			if (icDto.getDecPreTax01()!=null) setDecPreTax(Double.valueOf(icDto.getDecPreTax01()));
			if (icDto.getSubtotalAfterTax01()!=null) setSubTotalAfterTax(Double.valueOf(icDto.getSubtotalAfterTax01()));
			if (icDto.getBeforeAmountAfterTax01()!=null) setBeforeAmountAfterTax(Double.valueOf(icDto.getBeforeAmountAfterTax01()));
			if (icDto.getJanAfterTax01()!=null) setJanAfterTax(Double.valueOf(icDto.getJanAfterTax01()));
			if (icDto.getFebAfterTax01()!=null) setFebAfterTax(Double.valueOf(icDto.getFebAfterTax01()));
			if (icDto.getMarAfterTax01()!=null) setMarAfterTax(Double.valueOf(icDto.getMarAfterTax01()));
			if (icDto.getAprAfterTax01()!=null) setAprAfterTax(Double.valueOf(icDto.getAprAfterTax01()));
			if (icDto.getMayAfterTax01()!=null) setMayAfterTax(Double.valueOf(icDto.getMayAfterTax01()));
			if (icDto.getJunAfterTax01()!=null) setJunAfterTax(Double.valueOf(icDto.getJunAfterTax01()));
			if (icDto.getJulAfterTax01()!=null) setJulAfterTax(Double.valueOf(icDto.getJulAfterTax01()));
			if (icDto.getAugAfterTax01()!=null) setAugAfterTax(Double.valueOf(icDto.getAugAfterTax01()));
			if (icDto.getSepAfterTax01()!=null) setSepAfterTax(Double.valueOf(icDto.getSepAfterTax01()));
			if (icDto.getOctAfterTax01()!=null) setOctAfterTax(Double.valueOf(icDto.getOctAfterTax01()));
			if (icDto.getNovAfterTax01()!=null) setNovAfterTax(Double.valueOf(icDto.getNovAfterTax01()));
			if (icDto.getDecAfterTax01()!=null) setDecAfterTax(Double.valueOf(icDto.getDecAfterTax01()));
		}else if (index == 2){
			String templateType = icDto.getTemplateType(); 
			setTemplateType(templateType);
			if ("20602".equals(templateType)) return;
			else if ("20601".equals(templateType)) setAmountType("20702");
			else if ("20603".equals(templateType)) setAmountType("20705");
			if (icDto.getSubTotalPreTax02()!=null) setSubTotalPreTax(Double.valueOf(icDto.getSubTotalPreTax02()));
			if (icDto.getBeforeAmountPreTax02()!=null) setBeforeAmountPreTax(Double.valueOf(icDto.getBeforeAmountPreTax02()));
			if (icDto.getJanPreTax02()!=null) setJanPreTax(Double.valueOf(icDto.getJanPreTax02()));
			if (icDto.getFebPreTax02()!=null) setFebPreTax(Double.valueOf(icDto.getFebPreTax02()));
			if (icDto.getMarPreTax02()!=null) setMarPreTax(Double.valueOf(icDto.getMarPreTax02()));
			if (icDto.getAprPreTax02()!=null) setAprPreTax(Double.valueOf(icDto.getAprPreTax02()));
			if (icDto.getMayPreTax02()!=null) setMayPreTax(Double.valueOf(icDto.getMayPreTax02()));
			if (icDto.getJunPreTax02()!=null) setJunPreTax(Double.valueOf(icDto.getJunPreTax02()));
			if (icDto.getJulPreTax02()!=null) setJulPreTax(Double.valueOf(icDto.getJulPreTax02()));
			if (icDto.getAugPreTax02()!=null) setAugPreTax(Double.valueOf(icDto.getAugPreTax02()));
			if (icDto.getSepPreTax02()!=null) setSepPreTax(Double.valueOf(icDto.getSepPreTax02()));
			if (icDto.getOctPreTax02()!=null) setOctPreTax(Double.valueOf(icDto.getOctPreTax02()));
			if (icDto.getNovPreTax02()!=null) setNovPreTax(Double.valueOf(icDto.getNovPreTax02()));
			if (icDto.getDecPreTax02()!=null) setDecPreTax(Double.valueOf(icDto.getDecPreTax02()));
			if (icDto.getSubtotalAfterTax02()!=null) setSubTotalAfterTax(Double.valueOf(icDto.getSubtotalAfterTax02()));
			if (icDto.getBeforeAmountAfterTax02()!=null) setBeforeAmountAfterTax(Double.valueOf(icDto.getBeforeAmountAfterTax02()));
			if (icDto.getJanAfterTax02()!=null) setJanAfterTax(Double.valueOf(icDto.getJanAfterTax02()));
			if (icDto.getFebAfterTax02()!=null) setFebAfterTax(Double.valueOf(icDto.getFebAfterTax02()));
			if (icDto.getMarAfterTax02()!=null) setMarAfterTax(Double.valueOf(icDto.getMarAfterTax02()));
			if (icDto.getAprAfterTax02()!=null) setAprAfterTax(Double.valueOf(icDto.getAprAfterTax02()));
			if (icDto.getMayAfterTax02()!=null) setMayAfterTax(Double.valueOf(icDto.getMayAfterTax02()));
			if (icDto.getJunAfterTax02()!=null) setJunAfterTax(Double.valueOf(icDto.getJunAfterTax02()));
			if (icDto.getJulAfterTax02()!=null) setJulAfterTax(Double.valueOf(icDto.getJulAfterTax02()));
			if (icDto.getAugAfterTax02()!=null) setAugAfterTax(Double.valueOf(icDto.getAugAfterTax02()));
			if (icDto.getSepAfterTax02()!=null) setSepAfterTax(Double.valueOf(icDto.getSepAfterTax02()));
			if (icDto.getOctAfterTax02()!=null) setOctAfterTax(Double.valueOf(icDto.getOctAfterTax02()));
			if (icDto.getNovAfterTax02()!=null) setNovAfterTax(Double.valueOf(icDto.getNovAfterTax02()));
			if (icDto.getDecAfterTax02()!=null) setDecAfterTax(Double.valueOf(icDto.getDecAfterTax02()));
		}
	}
	
	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public String getAmountType() {
		return amountType;
	}
	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public Double getSubTotalPreTax() {
		return subTotalPreTax;
	}
	public void setSubTotalPreTax(Double subTotalPreTax) {
		this.subTotalPreTax = subTotalPreTax;
	}
	public Double getBeforeAmountPreTax() {
		return beforeAmountPreTax;
	}
	public void setBeforeAmountPreTax(Double beforeAmountPreTax) {
		this.beforeAmountPreTax = beforeAmountPreTax;
	}
	public Double getJanPreTax() {
		return janPreTax;
	}
	public void setJanPreTax(Double janPreTax) {
		this.janPreTax = janPreTax;
	}
	public Double getFebPreTax() {
		return febPreTax;
	}
	public void setFebPreTax(Double febPreTax) {
		this.febPreTax = febPreTax;
	}
	public Double getMarPreTax() {
		return marPreTax;
	}
	public void setMarPreTax(Double marPreTax) {
		this.marPreTax = marPreTax;
	}
	public Double getAprPreTax() {
		return aprPreTax;
	}
	public void setAprPreTax(Double aprPreTax) {
		this.aprPreTax = aprPreTax;
	}
	public Double getMayPreTax() {
		return mayPreTax;
	}
	public void setMayPreTax(Double mayPreTax) {
		this.mayPreTax = mayPreTax;
	}
	public Double getJunPreTax() {
		return junPreTax;
	}
	public void setJunPreTax(Double junPreTax) {
		this.junPreTax = junPreTax;
	}
	public Double getJulPreTax() {
		return julPreTax;
	}
	public void setJulPreTax(Double julPreTax) {
		this.julPreTax = julPreTax;
	}
	public Double getAugPreTax() {
		return augPreTax;
	}
	public void setAugPreTax(Double augPreTax) {
		this.augPreTax = augPreTax;
	}
	public Double getSepPreTax() {
		return sepPreTax;
	}
	public void setSepPreTax(Double sepPreTax) {
		this.sepPreTax = sepPreTax;
	}
	public Double getOctPreTax() {
		return octPreTax;
	}
	public void setOctPreTax(Double octPreTax) {
		this.octPreTax = octPreTax;
	}
	public Double getNovPreTax() {
		return novPreTax;
	}
	public void setNovPreTax(Double novPreTax) {
		this.novPreTax = novPreTax;
	}
	public Double getDecPreTax() {
		return decPreTax;
	}
	public void setDecPreTax(Double decPreTax) {
		this.decPreTax = decPreTax;
	}
	public Double getSubTotalAfterTax() {
		return subTotalAfterTax;
	}
	public void setSubTotalAfterTax(Double subTotalAfterTax) {
		this.subTotalAfterTax = subTotalAfterTax;
	}
	public Double getBeforeAmountAfterTax() {
		return beforeAmountAfterTax;
	}
	public void setBeforeAmountAfterTax(Double beforeAmountAfterTax) {
		this.beforeAmountAfterTax = beforeAmountAfterTax;
	}
	public Double getJanAfterTax() {
		return janAfterTax;
	}
	public void setJanAfterTax(Double janAfterTax) {
		this.janAfterTax = janAfterTax;
	}
	public Double getFebAfterTax() {
		return febAfterTax;
	}
	public void setFebAfterTax(Double febAfterTax) {
		this.febAfterTax = febAfterTax;
	}
	public Double getMarAfterTax() {
		return marAfterTax;
	}
	public void setMarAfterTax(Double marAfterTax) {
		this.marAfterTax = marAfterTax;
	}
	public Double getAprAfterTax() {
		return aprAfterTax;
	}
	public void setAprAfterTax(Double aprAfterTax) {
		this.aprAfterTax = aprAfterTax;
	}
	public Double getMayAfterTax() {
		return mayAfterTax;
	}
	public void setMayAfterTax(Double mayAfterTax) {
		this.mayAfterTax = mayAfterTax;
	}
	public Double getJunAfterTax() {
		return junAfterTax;
	}
	public void setJunAfterTax(Double junAfterTax) {
		this.junAfterTax = junAfterTax;
	}
	public Double getJulAfterTax() {
		return julAfterTax;
	}
	public void setJulAfterTax(Double julAfterTax) {
		this.julAfterTax = julAfterTax;
	}
	public Double getAugAfterTax() {
		return augAfterTax;
	}
	public void setAugAfterTax(Double augAfterTax) {
		this.augAfterTax = augAfterTax;
	}
	public Double getSepAfterTax() {
		return sepAfterTax;
	}
	public void setSepAfterTax(Double sepAfterTax) {
		this.sepAfterTax = sepAfterTax;
	}
	public Double getOctAfterTax() {
		return octAfterTax;
	}
	public void setOctAfterTax(Double octAfterTax) {
		this.octAfterTax = octAfterTax;
	}
	public Double getNovAfterTax() {
		return novAfterTax;
	}
	public void setNovAfterTax(Double novAfterTax) {
		this.novAfterTax = novAfterTax;
	}
	public Double getDecAfterTax() {
		return decAfterTax;
	}
	public void setDecAfterTax(Double decAfterTax) {
		this.decAfterTax = decAfterTax;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(Integer operUserId) {
		this.operUserId = operUserId;
	}
	public String getOperUserName() {
		return operUserName;
	}
	public void setOperUserName(String operUserName) {
		this.operUserName = operUserName;
	}
	public Date getOperDt() {
		return operDt;
	}
	public void setOperDt(Date operDt) {
		this.operDt = operDt;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}