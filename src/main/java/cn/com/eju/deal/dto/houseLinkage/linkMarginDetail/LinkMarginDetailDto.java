package cn.com.eju.deal.dto.houseLinkage.linkMarginDetail;

import java.io.Serializable;

/**
 * desc:联动资金成本(保证金、诚意金)  dto
 * @author :zhenggang.Huang
 * @date   :2019年9月12日
 */
public class LinkMarginDetailDto implements Serializable {

	private static final long serialVersionUID = 477597371720865651L;

	private String rowNum ;//序号
	private String yearly ;//年份

	private String gsCityNo;
	private String gsCityName;//项目归属城市

	private String costCode;
	private String costName;//成本中心

	private String kh_code;
	private String kh_name;

	private String hs_code;
	private String hs_name;

	private String projectNo;
	private String projectName;//项目名称

	private String oaNo;//预付款单号

	private String ksId;
	private String ksCode;
	private String ksName;//客商/供应商

	private String marginTypeCode;
	private String marginTypeName;//类型(26701、26702、26703、26704)

	private String yfhkDate;//预计归还日期

	private String oaAmount ;//预付款金额
	private String sjfkAmountTotal ;//实际付款金额
	private String hkAmountTotal ;//回款金额/正常回款
	private String hxAmount ;//核销
	private String cyAmountTotal ;//未收回金额

	private String totalAmount ;//总累计
	private String preTotal;//当年以前
	private String thisTotal;//当年新增

	private String marginjan ;//保证金一月
	private String marginfeb ;
	private String marginmar ;
	private String marginapr ;
	private String marginmay ;
	private String marginjun ;
	private String marginjul ;
	private String marginaug ;
	private String marginsep ;
	private String marginoct ;
	private String marginnov ;
	private String margindec ;

	private String marginhkjan ;//保证金回款一月
	private String marginhkfeb ;
	private String marginhkmar ;
	private String marginhkapr ;
	private String marginhkmay ;
	private String marginhkjun ;
	private String marginhkjul ;
	private String marginhkaug ;
	private String marginhksep ;
	private String marginhkoct ;
	private String marginhknov ;
	private String marginhkdec ;

	private String marginCbjan ;//资金成本一月
	private String marginCbfeb ;
	private String marginCbmar ;
	private String marginCbapr ;
	private String marginCbmay ;
	private String marginCbjun ;
	private String marginCbjul ;
	private String marginCbaug ;
	private String marginCbsep ;
	private String marginCboct ;
	private String marginCbnov ;
	private String marginCbdec ;

	public String getKh_code() {
		return kh_code;
	}

	public void setKh_code(String kh_code) {
		this.kh_code = kh_code;
	}

	public String getKh_name() {
		return kh_name;
	}

	public void setKh_name(String kh_name) {
		this.kh_name = kh_name;
	}

	public String getHs_code() {
		return hs_code;
	}

	public void setHs_code(String hs_code) {
		this.hs_code = hs_code;
	}

	public String getHs_name() {
		return hs_name;
	}

	public void setHs_name(String hs_name) {
		this.hs_name = hs_name;
	}

	public String getHxAmount() {
		return hxAmount;
	}
	public void setHxAmount(String hxAmount) {
		this.hxAmount = hxAmount;
	}
	public String getOaNo() {
		return oaNo;
	}
	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}
	public String getRowNum() {
		return rowNum;
	}
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	public String getYearly() {
		return yearly;
	}
	public void setYearly(String yearly) {
		this.yearly = yearly;
	}
	public String getGsCityNo() {
		return gsCityNo;
	}
	public void setGsCityNo(String gsCityNo) {
		this.gsCityNo = gsCityNo;
	}
	public String getGsCityName() {
		return gsCityName;
	}
	public void setGsCityName(String gsCityName) {
		this.gsCityName = gsCityName;
	}
	public String getCostCode() {
		return costCode;
	}
	public void setCostCode(String costCode) {
		this.costCode = costCode;
	}
	public String getCostName() {
		return costName;
	}
	public void setCostName(String costName) {
		this.costName = costName;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getKsId() {
		return ksId;
	}
	public void setKsId(String ksId) {
		this.ksId = ksId;
	}
	public String getKsCode() {
		return ksCode;
	}
	public void setKsCode(String ksCode) {
		this.ksCode = ksCode;
	}
	public String getKsName() {
		return ksName;
	}
	public void setKsName(String ksName) {
		this.ksName = ksName;
	}
	public String getMarginTypeCode() {
		return marginTypeCode;
	}
	public void setMarginTypeCode(String marginTypeCode) {
		this.marginTypeCode = marginTypeCode;
	}
	public String getMarginTypeName() {
		return marginTypeName;
	}
	public void setMarginTypeName(String marginTypeName) {
		this.marginTypeName = marginTypeName;
	}

	public String getYfhkDate() {
		return yfhkDate;
	}
	public void setYfhkDate(String yfhkDate) {
		this.yfhkDate = yfhkDate;
	}
	public String getOaAmount() {
		return oaAmount;
	}
	public void setOaAmount(String oaAmount) {
		this.oaAmount = oaAmount;
	}
	public String getSjfkAmountTotal() {
		return sjfkAmountTotal;
	}
	public void setSjfkAmountTotal(String sjfkAmountTotal) {
		this.sjfkAmountTotal = sjfkAmountTotal;
	}
	public String getHkAmountTotal() {
		return hkAmountTotal;
	}
	public void setHkAmountTotal(String hkAmountTotal) {
		this.hkAmountTotal = hkAmountTotal;
	}
	public String getCyAmountTotal() {
		return cyAmountTotal;
	}
	public void setCyAmountTotal(String cyAmountTotal) {
		this.cyAmountTotal = cyAmountTotal;
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
	public String getMarginjan() {
		return marginjan;
	}
	public void setMarginjan(String marginjan) {
		this.marginjan = marginjan;
	}
	public String getMarginfeb() {
		return marginfeb;
	}
	public void setMarginfeb(String marginfeb) {
		this.marginfeb = marginfeb;
	}
	public String getMarginmar() {
		return marginmar;
	}
	public void setMarginmar(String marginmar) {
		this.marginmar = marginmar;
	}
	public String getMarginapr() {
		return marginapr;
	}
	public void setMarginapr(String marginapr) {
		this.marginapr = marginapr;
	}
	public String getMarginmay() {
		return marginmay;
	}
	public void setMarginmay(String marginmay) {
		this.marginmay = marginmay;
	}
	public String getMarginjun() {
		return marginjun;
	}
	public void setMarginjun(String marginjun) {
		this.marginjun = marginjun;
	}
	public String getMarginjul() {
		return marginjul;
	}
	public void setMarginjul(String marginjul) {
		this.marginjul = marginjul;
	}
	public String getMarginaug() {
		return marginaug;
	}
	public void setMarginaug(String marginaug) {
		this.marginaug = marginaug;
	}
	public String getMarginsep() {
		return marginsep;
	}
	public void setMarginsep(String marginsep) {
		this.marginsep = marginsep;
	}
	public String getMarginoct() {
		return marginoct;
	}
	public void setMarginoct(String marginoct) {
		this.marginoct = marginoct;
	}
	public String getMarginnov() {
		return marginnov;
	}
	public void setMarginnov(String marginnov) {
		this.marginnov = marginnov;
	}
	public String getMargindec() {
		return margindec;
	}
	public void setMargindec(String margindec) {
		this.margindec = margindec;
	}
	public String getMarginhkjan() {
		return marginhkjan;
	}
	public void setMarginhkjan(String marginhkjan) {
		this.marginhkjan = marginhkjan;
	}
	public String getMarginhkfeb() {
		return marginhkfeb;
	}
	public void setMarginhkfeb(String marginhkfeb) {
		this.marginhkfeb = marginhkfeb;
	}
	public String getMarginhkmar() {
		return marginhkmar;
	}
	public void setMarginhkmar(String marginhkmar) {
		this.marginhkmar = marginhkmar;
	}
	public String getMarginhkapr() {
		return marginhkapr;
	}
	public void setMarginhkapr(String marginhkapr) {
		this.marginhkapr = marginhkapr;
	}
	public String getMarginhkmay() {
		return marginhkmay;
	}
	public void setMarginhkmay(String marginhkmay) {
		this.marginhkmay = marginhkmay;
	}
	public String getMarginhkjun() {
		return marginhkjun;
	}
	public void setMarginhkjun(String marginhkjun) {
		this.marginhkjun = marginhkjun;
	}
	public String getMarginhkjul() {
		return marginhkjul;
	}
	public void setMarginhkjul(String marginhkjul) {
		this.marginhkjul = marginhkjul;
	}
	public String getMarginhkaug() {
		return marginhkaug;
	}
	public void setMarginhkaug(String marginhkaug) {
		this.marginhkaug = marginhkaug;
	}
	public String getMarginhksep() {
		return marginhksep;
	}
	public void setMarginhksep(String marginhksep) {
		this.marginhksep = marginhksep;
	}
	public String getMarginhkoct() {
		return marginhkoct;
	}
	public void setMarginhkoct(String marginhkoct) {
		this.marginhkoct = marginhkoct;
	}
	public String getMarginhknov() {
		return marginhknov;
	}
	public void setMarginhknov(String marginhknov) {
		this.marginhknov = marginhknov;
	}
	public String getMarginhkdec() {
		return marginhkdec;
	}
	public void setMarginhkdec(String marginhkdec) {
		this.marginhkdec = marginhkdec;
	}
	public String getMarginCbjan() {
		return marginCbjan;
	}
	public void setMarginCbjan(String marginCbjan) {
		this.marginCbjan = marginCbjan;
	}
	public String getMarginCbfeb() {
		return marginCbfeb;
	}
	public void setMarginCbfeb(String marginCbfeb) {
		this.marginCbfeb = marginCbfeb;
	}
	public String getMarginCbmar() {
		return marginCbmar;
	}
	public void setMarginCbmar(String marginCbmar) {
		this.marginCbmar = marginCbmar;
	}
	public String getMarginCbapr() {
		return marginCbapr;
	}
	public void setMarginCbapr(String marginCbapr) {
		this.marginCbapr = marginCbapr;
	}
	public String getMarginCbmay() {
		return marginCbmay;
	}
	public void setMarginCbmay(String marginCbmay) {
		this.marginCbmay = marginCbmay;
	}
	public String getMarginCbjun() {
		return marginCbjun;
	}
	public void setMarginCbjun(String marginCbjun) {
		this.marginCbjun = marginCbjun;
	}
	public String getMarginCbjul() {
		return marginCbjul;
	}
	public void setMarginCbjul(String marginCbjul) {
		this.marginCbjul = marginCbjul;
	}
	public String getMarginCbaug() {
		return marginCbaug;
	}
	public void setMarginCbaug(String marginCbaug) {
		this.marginCbaug = marginCbaug;
	}
	public String getMarginCbsep() {
		return marginCbsep;
	}
	public void setMarginCbsep(String marginCbsep) {
		this.marginCbsep = marginCbsep;
	}
	public String getMarginCboct() {
		return marginCboct;
	}
	public void setMarginCboct(String marginCboct) {
		this.marginCboct = marginCboct;
	}
	public String getMarginCbnov() {
		return marginCbnov;
	}
	public void setMarginCbnov(String marginCbnov) {
		this.marginCbnov = marginCbnov;
	}
	public String getMarginCbdec() {
		return marginCbdec;
	}
	public void setMarginCbdec(String marginCbdec) {
		this.marginCbdec = marginCbdec;
	}

}
