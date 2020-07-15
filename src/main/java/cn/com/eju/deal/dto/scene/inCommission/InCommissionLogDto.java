package cn.com.eju.deal.dto.scene.inCommission;

import java.util.Date;

public class InCommissionLogDto{
	
	private Integer id;
	
	private String operType;
	
	private String fileName;
	
	private Integer operUserId;
	
	private String operUserName;
	
	private Date operDt;
	
	private String remarks;
	
	private String templateType; //模板类型
	private String amountType; //佣金类型
	private Integer year; //查询年份
	private String projectNo;	 //项目编号
	private String estateId; //楼盘ID
	private String reportId;   //报备ID
	private String estateNm; //楼盘名称
	private String buildingNo; //楼室号
	private String  dealAmount;   //成销总价
	private String dealDate;    //成销日期	
	
    private String subTotalPreTax;   //小计税前(岗位)
    private String beforeAmountPreTax;     //当年以前税前
    private String janPreTax;        //1月税前
    private String febPreTax;        //2月税前
    private String marPreTax;        //3月税前		
    private String aprPreTax;        //4月税前
    private String mayPreTax;	     //5月税前
    private String junPreTax;         //6月税前
    private String julPreTax;          //7月税前
    private String augPreTax;         //8月税前
    private String sepPreTax;          //9月税前
    private String octPreTax;         //10月税前
    private String novPreTax;           //11月税前
    private String decPreTax;          //12月税前
    
	private String subtotalAfterTax; //小计税后(团佣)
    private String beforeAmountAfterTax;   //当年以前税后
    private String janAfterTax;      //1月税后
    private String febAfterTax;      //2月税后
    private String marAfterTax;      //3月税后
    private String aprAfterTax;		 //4月税后
    private String mayAfterTax;       //5月税后
    private String junAfterTax;        //6月税后
    private String julAfterTax;          //7月税后
    private String augAfterTax;         //8月税后
    private String sepAfterTax;        //9月税后
    private String octAfterTax;          //10月税后
    private String novAfterTax;         //11月税后
    private String decAfterTax;         //12月税后
    
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
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getBuildingNo() {
		return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
		this.buildingNo = buildingNo;
	}
	public String getDealAmount() {
		return dealAmount;
	}
	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}
	public String getDealDate() {
		return dealDate;
	}
	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}
	public String getSubTotalPreTax() {
		return subTotalPreTax;
	}
	public void setSubTotalPreTax(String subTotalPreTax) {
		this.subTotalPreTax = subTotalPreTax;
	}
	public String getBeforeAmountPreTax() {
		return beforeAmountPreTax;
	}
	public void setBeforeAmountPreTax(String beforeAmountPreTax) {
		this.beforeAmountPreTax = beforeAmountPreTax;
	}
	public String getJanPreTax() {
		return janPreTax;
	}
	public void setJanPreTax(String janPreTax) {
		this.janPreTax = janPreTax;
	}
	public String getFebPreTax() {
		return febPreTax;
	}
	public void setFebPreTax(String febPreTax) {
		this.febPreTax = febPreTax;
	}
	public String getMarPreTax() {
		return marPreTax;
	}
	public void setMarPreTax(String marPreTax) {
		this.marPreTax = marPreTax;
	}
	public String getAprPreTax() {
		return aprPreTax;
	}
	public void setAprPreTax(String aprPreTax) {
		this.aprPreTax = aprPreTax;
	}
	public String getMayPreTax() {
		return mayPreTax;
	}
	public void setMayPreTax(String mayPreTax) {
		this.mayPreTax = mayPreTax;
	}
	public String getJunPreTax() {
		return junPreTax;
	}
	public void setJunPreTax(String junPreTax) {
		this.junPreTax = junPreTax;
	}
	public String getJulPreTax() {
		return julPreTax;
	}
	public void setJulPreTax(String julPreTax) {
		this.julPreTax = julPreTax;
	}
	public String getAugPreTax() {
		return augPreTax;
	}
	public void setAugPreTax(String augPreTax) {
		this.augPreTax = augPreTax;
	}
	public String getSepPreTax() {
		return sepPreTax;
	}
	public void setSepPreTax(String sepPreTax) {
		this.sepPreTax = sepPreTax;
	}
	public String getOctPreTax() {
		return octPreTax;
	}
	public void setOctPreTax(String octPreTax) {
		this.octPreTax = octPreTax;
	}
	public String getNovPreTax() {
		return novPreTax;
	}
	public void setNovPreTax(String novPreTax) {
		this.novPreTax = novPreTax;
	}
	public String getDecPreTax() {
		return decPreTax;
	}
	public void setDecPreTax(String decPreTax) {
		this.decPreTax = decPreTax;
	}
	public String getSubtotalAfterTax() {
		return subtotalAfterTax;
	}
	public void setSubtotalAfterTax(String subtotalAfterTax) {
		this.subtotalAfterTax = subtotalAfterTax;
	}
	public String getBeforeAmountAfterTax() {
		return beforeAmountAfterTax;
	}
	public void setBeforeAmountAfterTax(String beforeAmountAfterTax) {
		this.beforeAmountAfterTax = beforeAmountAfterTax;
	}
	public String getJanAfterTax() {
		return janAfterTax;
	}
	public void setJanAfterTax(String janAfterTax) {
		this.janAfterTax = janAfterTax;
	}
	public String getFebAfterTax() {
		return febAfterTax;
	}
	public void setFebAfterTax(String febAfterTax) {
		this.febAfterTax = febAfterTax;
	}
	public String getMarAfterTax() {
		return marAfterTax;
	}
	public void setMarAfterTax(String marAfterTax) {
		this.marAfterTax = marAfterTax;
	}
	public String getAprAfterTax() {
		return aprAfterTax;
	}
	public void setAprAfterTax(String aprAfterTax) {
		this.aprAfterTax = aprAfterTax;
	}
	public String getMayAfterTax() {
		return mayAfterTax;
	}
	public void setMayAfterTax(String mayAfterTax) {
		this.mayAfterTax = mayAfterTax;
	}
	public String getJunAfterTax() {
		return junAfterTax;
	}
	public void setJunAfterTax(String junAfterTax) {
		this.junAfterTax = junAfterTax;
	}
	public String getJulAfterTax() {
		return julAfterTax;
	}
	public void setJulAfterTax(String julAfterTax) {
		this.julAfterTax = julAfterTax;
	}
	public String getAugAfterTax() {
		return augAfterTax;
	}
	public void setAugAfterTax(String augAfterTax) {
		this.augAfterTax = augAfterTax;
	}
	public String getSepAfterTax() {
		return sepAfterTax;
	}
	public void setSepAfterTax(String sepAfterTax) {
		this.sepAfterTax = sepAfterTax;
	}
	public String getOctAfterTax() {
		return octAfterTax;
	}
	public void setOctAfterTax(String octAfterTax) {
		this.octAfterTax = octAfterTax;
	}
	public String getNovAfterTax() {
		return novAfterTax;
	}
	public void setNovAfterTax(String novAfterTax) {
		this.novAfterTax = novAfterTax;
	}
	public String getDecAfterTax() {
		return decAfterTax;
	}
	public void setDecAfterTax(String decAfterTax) {
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