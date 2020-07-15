package cn.com.eju.deal.dto.scene.commission;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by haidan on 2018/11/29.
 */
public class ImportDto {
    private Integer id;
    private String templateType;         //模板类型
    private String amountType;
    private Integer year;
    private String projectNo;	         //项目编号
    private String estateId;             //楼盘ID
    private String estateNm;             //楼盘名称
    private String reportId;             //报备ID
    private String buildingNo;           //楼室号
    private BigDecimal dealAmount;           //成销总价
    private Date dealDate;             //成销日期

    private Integer num;                 //套数
    private Integer detailId;            //报备详细ID

    private BigDecimal subTotalPreTax;     //小计税前(岗位)
    private BigDecimal beforeAmountPreTax; //当年以前税前
    private BigDecimal janPreTax;          //1月税前
    private BigDecimal febPreTax;          //2月税前
    private BigDecimal marPreTax;          //3月税前
    private BigDecimal aprPreTax;          //4月税前
    private BigDecimal mayPreTax;	         //5月税前
    private BigDecimal junPreTax;          //6月税前
    private BigDecimal julPreTax;          //7月税前
    private BigDecimal augPreTax;          //8月税前
    private BigDecimal sepPreTax;          //9月税前
    private BigDecimal octPreTax;          //10月税前
    private BigDecimal novPreTax;          //11月税前
    private BigDecimal decPreTax;          //12月税前

    private BigDecimal subTotalAfterTax;   //小计税后(团佣)
    private BigDecimal beforeAmountAfterTax;//当年以前税后
    private BigDecimal janAfterTax;        //1月税后
    private BigDecimal febAfterTax;        //2月税后
    private BigDecimal marAfterTax;        //3月税后
    private BigDecimal aprAfterTax;		 //4月税后
    private BigDecimal mayAfterTax;        //5月税后
    private BigDecimal junAfterTax;        //6月税后
    private BigDecimal julAfterTax;        //7月税后
    private BigDecimal augAfterTax;        //8月税后
    private BigDecimal sepAfterTax;        //9月税后
    private BigDecimal octAfterTax;        //10月税后
    private BigDecimal novAfterTax;        //11月税后
    private BigDecimal decAfterTax;        //12月税后

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

    public BigDecimal getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(BigDecimal dealAmount) {
        this.dealAmount = dealAmount;
    }

    public Date getDealDate() {
        return dealDate;
    }

    public void setDealDate(Date dealDate) {
        this.dealDate = dealDate;
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

    public BigDecimal getSubTotalPreTax() {
        return subTotalPreTax;
    }

    public void setSubTotalPreTax(BigDecimal subTotalPreTax) {
        this.subTotalPreTax = subTotalPreTax;
    }

    public BigDecimal getBeforeAmountPreTax() {
        return beforeAmountPreTax;
    }

    public void setBeforeAmountPreTax(BigDecimal beforeAmountPreTax) {
        this.beforeAmountPreTax = beforeAmountPreTax;
    }

    public BigDecimal getJanPreTax() {
        return janPreTax;
    }

    public void setJanPreTax(BigDecimal janPreTax) {
        this.janPreTax = janPreTax;
    }

    public BigDecimal getFebPreTax() {
        return febPreTax;
    }

    public void setFebPreTax(BigDecimal febPreTax) {
        this.febPreTax = febPreTax;
    }

    public BigDecimal getMarPreTax() {
        return marPreTax;
    }

    public void setMarPreTax(BigDecimal marPreTax) {
        this.marPreTax = marPreTax;
    }

    public BigDecimal getAprPreTax() {
        return aprPreTax;
    }

    public void setAprPreTax(BigDecimal aprPreTax) {
        this.aprPreTax = aprPreTax;
    }

    public BigDecimal getMayPreTax() {
        return mayPreTax;
    }

    public void setMayPreTax(BigDecimal mayPreTax) {
        this.mayPreTax = mayPreTax;
    }

    public BigDecimal getJunPreTax() {
        return junPreTax;
    }

    public void setJunPreTax(BigDecimal junPreTax) {
        this.junPreTax = junPreTax;
    }

    public BigDecimal getJulPreTax() {
        return julPreTax;
    }

    public void setJulPreTax(BigDecimal julPreTax) {
        this.julPreTax = julPreTax;
    }

    public BigDecimal getAugPreTax() {
        return augPreTax;
    }

    public void setAugPreTax(BigDecimal augPreTax) {
        this.augPreTax = augPreTax;
    }

    public BigDecimal getSepPreTax() {
        return sepPreTax;
    }

    public void setSepPreTax(BigDecimal sepPreTax) {
        this.sepPreTax = sepPreTax;
    }

    public BigDecimal getOctPreTax() {
        return octPreTax;
    }

    public void setOctPreTax(BigDecimal octPreTax) {
        this.octPreTax = octPreTax;
    }

    public BigDecimal getNovPreTax() {
        return novPreTax;
    }

    public void setNovPreTax(BigDecimal novPreTax) {
        this.novPreTax = novPreTax;
    }

    public BigDecimal getDecPreTax() {
        return decPreTax;
    }

    public void setDecPreTax(BigDecimal decPreTax) {
        this.decPreTax = decPreTax;
    }

    public BigDecimal getSubTotalAfterTax() {
        return subTotalAfterTax;
    }

    public void setSubTotalAfterTax(BigDecimal subTotalAfterTax) {
        this.subTotalAfterTax = subTotalAfterTax;
    }

    public BigDecimal getBeforeAmountAfterTax() {
        return beforeAmountAfterTax;
    }

    public void setBeforeAmountAfterTax(BigDecimal beforeAmountAfterTax) {
        this.beforeAmountAfterTax = beforeAmountAfterTax;
    }

    public BigDecimal getJanAfterTax() {
        return janAfterTax;
    }

    public void setJanAfterTax(BigDecimal janAfterTax) {
        this.janAfterTax = janAfterTax;
    }

    public BigDecimal getFebAfterTax() {
        return febAfterTax;
    }

    public void setFebAfterTax(BigDecimal febAfterTax) {
        this.febAfterTax = febAfterTax;
    }

    public BigDecimal getMarAfterTax() {
        return marAfterTax;
    }

    public void setMarAfterTax(BigDecimal marAfterTax) {
        this.marAfterTax = marAfterTax;
    }

    public BigDecimal getAprAfterTax() {
        return aprAfterTax;
    }

    public void setAprAfterTax(BigDecimal aprAfterTax) {
        this.aprAfterTax = aprAfterTax;
    }

    public BigDecimal getMayAfterTax() {
        return mayAfterTax;
    }

    public void setMayAfterTax(BigDecimal mayAfterTax) {
        this.mayAfterTax = mayAfterTax;
    }

    public BigDecimal getJunAfterTax() {
        return junAfterTax;
    }

    public void setJunAfterTax(BigDecimal junAfterTax) {
        this.junAfterTax = junAfterTax;
    }

    public BigDecimal getJulAfterTax() {
        return julAfterTax;
    }

    public void setJulAfterTax(BigDecimal julAfterTax) {
        this.julAfterTax = julAfterTax;
    }

    public BigDecimal getAugAfterTax() {
        return augAfterTax;
    }

    public void setAugAfterTax(BigDecimal augAfterTax) {
        this.augAfterTax = augAfterTax;
    }

    public BigDecimal getSepAfterTax() {
        return sepAfterTax;
    }

    public void setSepAfterTax(BigDecimal sepAfterTax) {
        this.sepAfterTax = sepAfterTax;
    }

    public BigDecimal getOctAfterTax() {
        return octAfterTax;
    }

    public void setOctAfterTax(BigDecimal octAfterTax) {
        this.octAfterTax = octAfterTax;
    }

    public BigDecimal getNovAfterTax() {
        return novAfterTax;
    }

    public void setNovAfterTax(BigDecimal novAfterTax) {
        this.novAfterTax = novAfterTax;
    }

    public BigDecimal getDecAfterTax() {
        return decAfterTax;
    }

    public void setDecAfterTax(BigDecimal decAfterTax) {
        this.decAfterTax = decAfterTax;
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
