package cn.com.eju.deal.dto.houseLinkage.linkZjcbDetail;

import java.io.Serializable;

/**
 * desc:联动明细(资金成本)  dto
 *
 * @author :zhenggang.Huang
 * @date :2019年7月18日
 */
public class LinkZjcbDetailDto implements Serializable {

    private static final long serialVersionUID = 477597371720865651L;
    /**
     * 序号
     */
    private String rowNum;
    private String rcityid;
    private String yearly;

    private String regionCode;
    private String regionName;//业绩归属区域

    private String areaCityCode;
    private String areaCityName;//业绩归属城市

    private String projectCityId;
    private String projectCityName;//项目归属城市

    private String cityGroupId;
    private String cityGroupName;//项目所在城市

    private String projectGroupId;
    private String projectGroupName;//项目归属中心

    private String centerGroupId;
    private String centerGroupName;//业绩归属中心

    private String projectNo;//项目编号
    private String estateNm;//项目名称/楼盘名

    private String reportId;//报备编号
    private String buildingNo;//楼室号

    private String qcfy;
    private String qcdy;
    private String qchk;


    private String totalAmount;//总累计资金成本
    private String preTotal;//当年以前
    private String thisTotal;//当年新增

    private String dyjan;//1月垫佣金额
    private String dyfeb;
    private String dymar;
    private String dyapr;
    private String dymay;
    private String dyjun;
    private String dyjul;
    private String dyaug;
    private String dysep;
    private String dyoct;
    private String dynov;
    private String dydec;

    private String dyhkjan;//1月垫佣回款金额
    private String dyhkfeb;
    private String dyhkmar;
    private String dyhkapr;
    private String dyhkmay;
    private String dyhkjun;
    private String dyhkjul;
    private String dyhkaug;
    private String dyhksep;
    private String dyhkoct;
    private String dyhknov;
    private String dyhkdec;

    private String zjcbjan;//1月资金成本
    private String zjcbfeb;
    private String zjcbmar;
    private String zjcbapr;
    private String zjcbmay;
    private String zjcbjun;
    private String zjcbjul;
    private String zjcbaug;
    private String zjcbsep;
    private String zjcboct;
    private String zjcbnov;
    private String zjcbdec;

    public String getCityGroupId() {
        return cityGroupId;
    }

    public void setCityGroupId(String cityGroupId) {
        this.cityGroupId = cityGroupId;
    }

    public String getCityGroupName() {
        return cityGroupName;
    }

    public void setCityGroupName(String cityGroupName) {
        this.cityGroupName = cityGroupName;
    }

    public String getCenterGroupId() {
        return centerGroupId;
    }

    public void setCenterGroupId(String centerGroupId) {
        this.centerGroupId = centerGroupId;
    }

    public String getCenterGroupName() {
        return centerGroupName;
    }

    public void setCenterGroupName(String centerGroupName) {
        this.centerGroupName = centerGroupName;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getRcityid() {
        return rcityid;
    }

    public void setRcityid(String rcityid) {
        this.rcityid = rcityid;
    }

    public String getYearly() {
        return yearly;
    }

    public void setYearly(String yearly) {
        this.yearly = yearly;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAreaCityCode() {
        return areaCityCode;
    }

    public void setAreaCityCode(String areaCityCode) {
        this.areaCityCode = areaCityCode;
    }

    public String getAreaCityName() {
        return areaCityName;
    }

    public void setAreaCityName(String areaCityName) {
        this.areaCityName = areaCityName;
    }

    public String getProjectCityId() {
        return projectCityId;
    }

    public void setProjectCityId(String projectCityId) {
        this.projectCityId = projectCityId;
    }

    public String getProjectCityName() {
        return projectCityName;
    }

    public void setProjectCityName(String projectCityName) {
        this.projectCityName = projectCityName;
    }

    public String getProjectGroupId() {
        return projectGroupId;
    }

    public void setProjectGroupId(String projectGroupId) {
        this.projectGroupId = projectGroupId;
    }

    public String getProjectGroupName() {
        return projectGroupName;
    }

    public void setProjectGroupName(String projectGroupName) {
        this.projectGroupName = projectGroupName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
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

    public String getQcfy() {
        return qcfy;
    }

    public void setQcfy(String qcfy) {
        this.qcfy = qcfy;
    }

    public String getQcdy() {
        return qcdy;
    }

    public void setQcdy(String qcdy) {
        this.qcdy = qcdy;
    }

    public String getQchk() {
        return qchk;
    }

    public void setQchk(String qchk) {
        this.qchk = qchk;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPreTotal() {
        return preTotal;
    }

    public void setPreTotal(String preTotal) {
        this.preTotal = preTotal;
    }

    public String getThisTotal() {
        return thisTotal;
    }

    public void setThisTotal(String thisTotal) {
        this.thisTotal = thisTotal;
    }

    public String getDyjan() {
        return dyjan;
    }

    public void setDyjan(String dyjan) {
        this.dyjan = dyjan;
    }

    public String getDyfeb() {
        return dyfeb;
    }

    public void setDyfeb(String dyfeb) {
        this.dyfeb = dyfeb;
    }

    public String getDymar() {
        return dymar;
    }

    public void setDymar(String dymar) {
        this.dymar = dymar;
    }

    public String getDyapr() {
        return dyapr;
    }

    public void setDyapr(String dyapr) {
        this.dyapr = dyapr;
    }

    public String getDymay() {
        return dymay;
    }

    public void setDymay(String dymay) {
        this.dymay = dymay;
    }

    public String getDyjun() {
        return dyjun;
    }

    public void setDyjun(String dyjun) {
        this.dyjun = dyjun;
    }

    public String getDyjul() {
        return dyjul;
    }

    public void setDyjul(String dyjul) {
        this.dyjul = dyjul;
    }

    public String getDyaug() {
        return dyaug;
    }

    public void setDyaug(String dyaug) {
        this.dyaug = dyaug;
    }

    public String getDysep() {
        return dysep;
    }

    public void setDysep(String dysep) {
        this.dysep = dysep;
    }

    public String getDyoct() {
        return dyoct;
    }

    public void setDyoct(String dyoct) {
        this.dyoct = dyoct;
    }

    public String getDynov() {
        return dynov;
    }

    public void setDynov(String dynov) {
        this.dynov = dynov;
    }

    public String getDydec() {
        return dydec;
    }

    public void setDydec(String dydec) {
        this.dydec = dydec;
    }

    public String getDyhkjan() {
        return dyhkjan;
    }

    public void setDyhkjan(String dyhkjan) {
        this.dyhkjan = dyhkjan;
    }

    public String getDyhkfeb() {
        return dyhkfeb;
    }

    public void setDyhkfeb(String dyhkfeb) {
        this.dyhkfeb = dyhkfeb;
    }

    public String getDyhkmar() {
        return dyhkmar;
    }

    public void setDyhkmar(String dyhkmar) {
        this.dyhkmar = dyhkmar;
    }

    public String getDyhkapr() {
        return dyhkapr;
    }

    public void setDyhkapr(String dyhkapr) {
        this.dyhkapr = dyhkapr;
    }

    public String getDyhkmay() {
        return dyhkmay;
    }

    public void setDyhkmay(String dyhkmay) {
        this.dyhkmay = dyhkmay;
    }

    public String getDyhkjun() {
        return dyhkjun;
    }

    public void setDyhkjun(String dyhkjun) {
        this.dyhkjun = dyhkjun;
    }

    public String getDyhkjul() {
        return dyhkjul;
    }

    public void setDyhkjul(String dyhkjul) {
        this.dyhkjul = dyhkjul;
    }

    public String getDyhkaug() {
        return dyhkaug;
    }

    public void setDyhkaug(String dyhkaug) {
        this.dyhkaug = dyhkaug;
    }

    public String getDyhksep() {
        return dyhksep;
    }

    public void setDyhksep(String dyhksep) {
        this.dyhksep = dyhksep;
    }

    public String getDyhkoct() {
        return dyhkoct;
    }

    public void setDyhkoct(String dyhkoct) {
        this.dyhkoct = dyhkoct;
    }

    public String getDyhknov() {
        return dyhknov;
    }

    public void setDyhknov(String dyhknov) {
        this.dyhknov = dyhknov;
    }

    public String getDyhkdec() {
        return dyhkdec;
    }

    public void setDyhkdec(String dyhkdec) {
        this.dyhkdec = dyhkdec;
    }

    public String getZjcbjan() {
        return zjcbjan;
    }

    public void setZjcbjan(String zjcbjan) {
        this.zjcbjan = zjcbjan;
    }

    public String getZjcbfeb() {
        return zjcbfeb;
    }

    public void setZjcbfeb(String zjcbfeb) {
        this.zjcbfeb = zjcbfeb;
    }

    public String getZjcbmar() {
        return zjcbmar;
    }

    public void setZjcbmar(String zjcbmar) {
        this.zjcbmar = zjcbmar;
    }

    public String getZjcbapr() {
        return zjcbapr;
    }

    public void setZjcbapr(String zjcbapr) {
        this.zjcbapr = zjcbapr;
    }

    public String getZjcbmay() {
        return zjcbmay;
    }

    public void setZjcbmay(String zjcbmay) {
        this.zjcbmay = zjcbmay;
    }

    public String getZjcbjun() {
        return zjcbjun;
    }

    public void setZjcbjun(String zjcbjun) {
        this.zjcbjun = zjcbjun;
    }

    public String getZjcbjul() {
        return zjcbjul;
    }

    public void setZjcbjul(String zjcbjul) {
        this.zjcbjul = zjcbjul;
    }

    public String getZjcbaug() {
        return zjcbaug;
    }

    public void setZjcbaug(String zjcbaug) {
        this.zjcbaug = zjcbaug;
    }

    public String getZjcbsep() {
        return zjcbsep;
    }

    public void setZjcbsep(String zjcbsep) {
        this.zjcbsep = zjcbsep;
    }

    public String getZjcboct() {
        return zjcboct;
    }

    public void setZjcboct(String zjcboct) {
        this.zjcboct = zjcboct;
    }

    public String getZjcbnov() {
        return zjcbnov;
    }

    public void setZjcbnov(String zjcbnov) {
        this.zjcbnov = zjcbnov;
    }

    public String getZjcbdec() {
        return zjcbdec;
    }

    public void setZjcbdec(String zjcbdec) {
        this.zjcbdec = zjcbdec;
    }


    private String dy2019beflj;
    private String hk2019beflj;
    private String zjcb2019beflj;

    public String getDy2019beflj() {
        return dy2019beflj;
    }

    public void setDy2019beflj(String dy2019beflj) {
        this.dy2019beflj = dy2019beflj;
    }

    public String getHk2019beflj() {
        return hk2019beflj;
    }

    public void setHk2019beflj(String hk2019beflj) {
        this.hk2019beflj = hk2019beflj;
    }

    public String getZjcb2019beflj() {
        return zjcb2019beflj;
    }

    public void setZjcb2019beflj(String zjcb2019beflj) {
        this.zjcb2019beflj = zjcb2019beflj;
    }

}
