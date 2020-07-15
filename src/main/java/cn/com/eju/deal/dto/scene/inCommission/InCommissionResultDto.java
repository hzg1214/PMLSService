package cn.com.eju.deal.dto.scene.inCommission;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class InCommissionResultDto  implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	private String templateType;         //模板类型
	private String projectNo;	         //项目编号
	private String estateId;             //楼盘ID
	private String reportId;             //报备ID
	private String estateNm;             //楼盘名称
	private String buildingNo;           //楼室号
	private String dealAmount;           //成销总价
	private String dealDate;             //成销日期
	private String cityNo;               //城市编号
	private Integer projectDepartmentId; //项目归属部门
	private Integer num;                 //套数
	private Integer detailId;            //报备详细ID
	
	
    private String subTotalPreTax01;     //小计税前(岗位)
    private String subtotalAfterTax01;   //小计税后(团佣)
    private String beforeAmountPreTax01; //当年以前税前
    private String beforeAmountAfterTax01;//当年以前税后
    private String janPreTax01;          //1月税前
    private String janAfterTax01;        //1月税后
    private String febPreTax01;          //2月税前
    private String febAfterTax01;        //2月税后
    private String marPreTax01;          //3月税前		
    private String marAfterTax01;        //3月税后
    private String aprPreTax01;          //4月税前
    private String aprAfterTax01;		 //4月税后
    private String mayPreTax01;	         //5月税前
    private String mayAfterTax01;        //5月税后
    private String junPreTax01;          //6月税前
    private String junAfterTax01;        //6月税后
    private String julPreTax01;          //7月税前
    private String julAfterTax01;        //7月税后
    private String augPreTax01;          //8月税前
    private String augAfterTax01;        //8月税后
    private String sepPreTax01;          //9月税前
    private String sepAfterTax01;        //9月税后
    private String octPreTax01;          //10月税前
    private String octAfterTax01;        //10月税后
    private String novPreTax01;          //11月税前
    private String novAfterTax01;        //11月税后
    private String decPreTax01;          //12月税前
    private String decAfterTax01;        //12月税后
    
    private String subTotalPreTax02;     //小计税前(岗位)
    private String subtotalAfterTax02;   //小计税后(团佣)
    private String beforeAmountPreTax02; //当年以前税前
    private String beforeAmountAfterTax02;//当年以前税后
    private String janPreTax02;          //1月税前
    private String janAfterTax02;        //1月税后
    private String febPreTax02;          //2月税前
    private String febAfterTax02;        //2月税后
    private String marPreTax02;          //3月税前
    private String marAfterTax02;        //3月税后
    private String aprPreTax02;          //4月税前
    private String aprAfterTax02;        //4月税后
    private String mayPreTax02;        	 //5月税前
    private String mayAfterTax02;		 //5月税后
    private String junPreTax02;			 //6月税前
    private String junAfterTax02;		 //6月税后
    private String julPreTax02;			 //7月税前
    private String julAfterTax02;		 //7月税后
    private String augPreTax02;			 //8月税前
    private String augAfterTax02;		 //8月税后
    private String sepPreTax02;			 //9月税前
    private String sepAfterTax02;		 //9月税后
    private String octPreTax02;			 //10月税前
    private String octAfterTax02;		 //10月税后
    private String novPreTax02;			 //11月税前
    private String novAfterTax02;		 //11月税后
    private String decPreTax02;			 //12月税前
    private String decAfterTax02;		 //12月税后
    
	public String getTemplateType() {
		return templateType;
	}
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
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
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public Integer getProjectDepartmentId() {
		return projectDepartmentId;
	}
	public void setProjectDepartmentId(Integer projectDepartmentId) {
		this.projectDepartmentId = projectDepartmentId;
	}
	public String getSubTotalPreTax01() {
		return subTotalPreTax01;
	}
	public void setSubTotalPreTax01(String subTotalPreTax01) {
		this.subTotalPreTax01 = subTotalPreTax01;
	}
	public String getSubtotalAfterTax01() {
		return subtotalAfterTax01;
	}
	public void setSubtotalAfterTax01(String subtotalAfterTax01) {
		this.subtotalAfterTax01 = subtotalAfterTax01;
	}
	public String getBeforeAmountPreTax01() {
		return beforeAmountPreTax01;
	}
	public void setBeforeAmountPreTax01(String beforeAmountPreTax01) {
		this.beforeAmountPreTax01 = beforeAmountPreTax01;
	}
	public String getBeforeAmountAfterTax01() {
		return beforeAmountAfterTax01;
	}
	public void setBeforeAmountAfterTax01(String beforeAmountAfterTax01) {
		this.beforeAmountAfterTax01 = beforeAmountAfterTax01;
	}
	public String getJanPreTax01() {
		return janPreTax01;
	}
	public void setJanPreTax01(String janPreTax01) {
		this.janPreTax01 = janPreTax01;
	}
	public String getJanAfterTax01() {
		return janAfterTax01;
	}
	public void setJanAfterTax01(String janAfterTax01) {
		this.janAfterTax01 = janAfterTax01;
	}
	public String getFebPreTax01() {
		return febPreTax01;
	}
	public void setFebPreTax01(String febPreTax01) {
		this.febPreTax01 = febPreTax01;
	}
	public String getFebAfterTax01() {
		return febAfterTax01;
	}
	public void setFebAfterTax01(String febAfterTax01) {
		this.febAfterTax01 = febAfterTax01;
	}
	public String getMarPreTax01() {
		return marPreTax01;
	}
	public void setMarPreTax01(String marPreTax01) {
		this.marPreTax01 = marPreTax01;
	}
	public String getMarAfterTax01() {
		return marAfterTax01;
	}
	public void setMarAfterTax01(String marAfterTax01) {
		this.marAfterTax01 = marAfterTax01;
	}
	public String getAprPreTax01() {
		return aprPreTax01;
	}
	public void setAprPreTax01(String aprPreTax01) {
		this.aprPreTax01 = aprPreTax01;
	}
	public String getAprAfterTax01() {
		return aprAfterTax01;
	}
	public void setAprAfterTax01(String aprAfterTax01) {
		this.aprAfterTax01 = aprAfterTax01;
	}
	public String getMayPreTax01() {
		return mayPreTax01;
	}
	public void setMayPreTax01(String mayPreTax01) {
		this.mayPreTax01 = mayPreTax01;
	}
	public String getMayAfterTax01() {
		return mayAfterTax01;
	}
	public void setMayAfterTax01(String mayAfterTax01) {
		this.mayAfterTax01 = mayAfterTax01;
	}
	public String getJunPreTax01() {
		return junPreTax01;
	}
	public void setJunPreTax01(String junPreTax01) {
		this.junPreTax01 = junPreTax01;
	}
	public String getJunAfterTax01() {
		return junAfterTax01;
	}
	public void setJunAfterTax01(String junAfterTax01) {
		this.junAfterTax01 = junAfterTax01;
	}
	public String getJulPreTax01() {
		return julPreTax01;
	}
	public void setJulPreTax01(String julPreTax01) {
		this.julPreTax01 = julPreTax01;
	}
	public String getJulAfterTax01() {
		return julAfterTax01;
	}
	public void setJulAfterTax01(String julAfterTax01) {
		this.julAfterTax01 = julAfterTax01;
	}
	public String getAugPreTax01() {
		return augPreTax01;
	}
	public void setAugPreTax01(String augPreTax01) {
		this.augPreTax01 = augPreTax01;
	}
	public String getAugAfterTax01() {
		return augAfterTax01;
	}
	public void setAugAfterTax01(String augAfterTax01) {
		this.augAfterTax01 = augAfterTax01;
	}
	public String getSepPreTax01() {
		return sepPreTax01;
	}
	public void setSepPreTax01(String sepPreTax01) {
		this.sepPreTax01 = sepPreTax01;
	}
	public String getSepAfterTax01() {
		return sepAfterTax01;
	}
	public void setSepAfterTax01(String sepAfterTax01) {
		this.sepAfterTax01 = sepAfterTax01;
	}
	public String getOctPreTax01() {
		return octPreTax01;
	}
	public void setOctPreTax01(String octPreTax01) {
		this.octPreTax01 = octPreTax01;
	}
	public String getOctAfterTax01() {
		return octAfterTax01;
	}
	public void setOctAfterTax01(String octAfterTax01) {
		this.octAfterTax01 = octAfterTax01;
	}
	public String getNovPreTax01() {
		return novPreTax01;
	}
	public void setNovPreTax01(String novPreTax01) {
		this.novPreTax01 = novPreTax01;
	}
	public String getNovAfterTax01() {
		return novAfterTax01;
	}
	public void setNovAfterTax01(String novAfterTax01) {
		this.novAfterTax01 = novAfterTax01;
	}
	public String getDecPreTax01() {
		return decPreTax01;
	}
	public void setDecPreTax01(String decPreTax01) {
		this.decPreTax01 = decPreTax01;
	}
	public String getDecAfterTax01() {
		return decAfterTax01;
	}
	public void setDecAfterTax01(String decAfterTax01) {
		this.decAfterTax01 = decAfterTax01;
	}
	public String getSubTotalPreTax02() {
		return subTotalPreTax02;
	}
	public void setSubTotalPreTax02(String subTotalPreTax02) {
		this.subTotalPreTax02 = subTotalPreTax02;
	}
	public String getSubtotalAfterTax02() {
		return subtotalAfterTax02;
	}
	public void setSubtotalAfterTax02(String subtotalAfterTax02) {
		this.subtotalAfterTax02 = subtotalAfterTax02;
	}
	public String getBeforeAmountPreTax02() {
		return beforeAmountPreTax02;
	}
	public void setBeforeAmountPreTax02(String beforeAmountPreTax02) {
		this.beforeAmountPreTax02 = beforeAmountPreTax02;
	}
	public String getBeforeAmountAfterTax02() {
		return beforeAmountAfterTax02;
	}
	public void setBeforeAmountAfterTax02(String beforeAmountAfterTax02) {
		this.beforeAmountAfterTax02 = beforeAmountAfterTax02;
	}
	public String getJanPreTax02() {
		return janPreTax02;
	}
	public void setJanPreTax02(String janPreTax02) {
		this.janPreTax02 = janPreTax02;
	}
	public String getJanAfterTax02() {
		return janAfterTax02;
	}
	public void setJanAfterTax02(String janAfterTax02) {
		this.janAfterTax02 = janAfterTax02;
	}
	public String getFebPreTax02() {
		return febPreTax02;
	}
	public void setFebPreTax02(String febPreTax02) {
		this.febPreTax02 = febPreTax02;
	}
	public String getFebAfterTax02() {
		return febAfterTax02;
	}
	public void setFebAfterTax02(String febAfterTax02) {
		this.febAfterTax02 = febAfterTax02;
	}
	public String getMarPreTax02() {
		return marPreTax02;
	}
	public void setMarPreTax02(String marPreTax02) {
		this.marPreTax02 = marPreTax02;
	}
	public String getMarAfterTax02() {
		return marAfterTax02;
	}
	public void setMarAfterTax02(String marAfterTax02) {
		this.marAfterTax02 = marAfterTax02;
	}
	public String getAprPreTax02() {
		return aprPreTax02;
	}
	public void setAprPreTax02(String aprPreTax02) {
		this.aprPreTax02 = aprPreTax02;
	}
	public String getAprAfterTax02() {
		return aprAfterTax02;
	}
	public void setAprAfterTax02(String aprAfterTax02) {
		this.aprAfterTax02 = aprAfterTax02;
	}
	public String getMayPreTax02() {
		return mayPreTax02;
	}
	public void setMayPreTax02(String mayPreTax02) {
		this.mayPreTax02 = mayPreTax02;
	}
	public String getMayAfterTax02() {
		return mayAfterTax02;
	}
	public void setMayAfterTax02(String mayAfterTax02) {
		this.mayAfterTax02 = mayAfterTax02;
	}
	public String getJunPreTax02() {
		return junPreTax02;
	}
	public void setJunPreTax02(String junPreTax02) {
		this.junPreTax02 = junPreTax02;
	}
	public String getJunAfterTax02() {
		return junAfterTax02;
	}
	public void setJunAfterTax02(String junAfterTax02) {
		this.junAfterTax02 = junAfterTax02;
	}
	public String getJulPreTax02() {
		return julPreTax02;
	}
	public void setJulPreTax02(String julPreTax02) {
		this.julPreTax02 = julPreTax02;
	}
	public String getJulAfterTax02() {
		return julAfterTax02;
	}
	public void setJulAfterTax02(String julAfterTax02) {
		this.julAfterTax02 = julAfterTax02;
	}
	public String getAugPreTax02() {
		return augPreTax02;
	}
	public void setAugPreTax02(String augPreTax02) {
		this.augPreTax02 = augPreTax02;
	}
	public String getAugAfterTax02() {
		return augAfterTax02;
	}
	public void setAugAfterTax02(String augAfterTax02) {
		this.augAfterTax02 = augAfterTax02;
	}
	public String getSepPreTax02() {
		return sepPreTax02;
	}
	public void setSepPreTax02(String sepPreTax02) {
		this.sepPreTax02 = sepPreTax02;
	}
	public String getSepAfterTax02() {
		return sepAfterTax02;
	}
	public void setSepAfterTax02(String sepAfterTax02) {
		this.sepAfterTax02 = sepAfterTax02;
	}
	public String getOctPreTax02() {
		return octPreTax02;
	}
	public void setOctPreTax02(String octPreTax02) {
		this.octPreTax02 = octPreTax02;
	}
	public String getOctAfterTax02() {
		return octAfterTax02;
	}
	public void setOctAfterTax02(String octAfterTax02) {
		this.octAfterTax02 = octAfterTax02;
	}
	public String getNovPreTax02() {
		return novPreTax02;
	}
	public void setNovPreTax02(String novPreTax02) {
		this.novPreTax02 = novPreTax02;
	}
	public String getNovAfterTax02() {
		return novAfterTax02;
	}
	public void setNovAfterTax02(String novAfterTax02) {
		this.novAfterTax02 = novAfterTax02;
	}
	public String getDecPreTax02() {
		return decPreTax02;
	}
	public void setDecPreTax02(String decPreTax02) {
		this.decPreTax02 = decPreTax02;
	}
	public String getDecAfterTax02() {
		return decAfterTax02;
	}
	public void setDecAfterTax02(String decAfterTax02) {
		this.decAfterTax02 = decAfterTax02;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getDetailId() {
		return detailId;
	}
	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}
}


