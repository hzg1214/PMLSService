package cn.com.eju.pmls.remittanceTrack.model;

import java.util.Date;

/**
 * 
 * desc:
 * @author :zhenggang_Huang
 * @date   :2020年4月7日
 */
public class RemittanceTrack {
	
	private Integer Id;
	private Integer year;
	private Integer sortNo;//排序
	private String projectNo;//项目编号
	private Integer yearMonthVersion;//关账版本年月
	private Integer weekVersion;//关账版本周
	private String partnerName;//合作方名称
	private String trackType;//0预计 1实际
	
	private String week1_1;//1月第一周
	private String xjAccount1_1;//1月第一周现金
	private String dfAccount1_1;//1月第一周抵房
	private String week1_2;
	private String xjAccount1_2;
	private String dfAccount1_2;
	private String week1_3;
	private String xjAccount1_3;
	private String dfAccount1_3;
	private String week1_4;
	private String xjAccount1_4;
	private String dfAccount1_4;
	private String week1_5;
	private String xjAccount1_5;
	private String dfAccount1_5;
	private String week1_6;
	private String xjAccount1_6;
	private String dfAccount1_6;

	private String week2_1;
	private String xjAccount2_1;
	private String dfAccount2_1;
	private String week2_2;
	private String xjAccount2_2;
	private String dfAccount2_2;
	private String week2_3;
	private String xjAccount2_3;
	private String dfAccount2_3;
	private String week2_4;
	private String xjAccount2_4;
	private String dfAccount2_4;
	private String week2_5;
	private String xjAccount2_5;
	private String dfAccount2_5;
	private String week2_6;
	private String xjAccount2_6;
	private String dfAccount2_6;

	private String week3_1;
	private String xjAccount3_1;
	private String dfAccount3_1;
	private String week3_2;
	private String xjAccount3_2;
	private String dfAccount3_2;
	private String week3_3;
	private String xjAccount3_3;
	private String dfAccount3_3;
	private String week3_4;
	private String xjAccount3_4;
	private String dfAccount3_4;
	private String week3_5;
	private String xjAccount3_5;
	private String dfAccount3_5;
	private String week3_6;
	private String xjAccount3_6;
	private String dfAccount3_6;

	private String week4_1;
	private String xjAccount4_1;
	private String dfAccount4_1;
	private String week4_2;
	private String xjAccount4_2;
	private String dfAccount4_2;
	private String week4_3;
	private String xjAccount4_3;
	private String dfAccount4_3;
	private String week4_4;
	private String xjAccount4_4;
	private String dfAccount4_4;
	private String week4_5;
	private String xjAccount4_5;
	private String dfAccount4_5;
	private String week4_6;
	private String xjAccount4_6;
	private String dfAccount4_6;

	private String week5_1;
	private String xjAccount5_1;
	private String dfAccount5_1;
	private String week5_2;
	private String xjAccount5_2;
	private String dfAccount5_2;
	private String week5_3;
	private String xjAccount5_3;
	private String dfAccount5_3;
	private String week5_4;
	private String xjAccount5_4;
	private String dfAccount5_4;
	private String week5_5;
	private String xjAccount5_5;
	private String dfAccount5_5;
	private String week5_6;
	private String xjAccount5_6;
	private String dfAccount5_6;

	private String week6_1;
	private String xjAccount6_1;
	private String dfAccount6_1;
	private String week6_2;
	private String xjAccount6_2;
	private String dfAccount6_2;
	private String week6_3;
	private String xjAccount6_3;
	private String dfAccount6_3;
	private String week6_4;
	private String xjAccount6_4;
	private String dfAccount6_4;
	private String week6_5;
	private String xjAccount6_5;
	private String dfAccount6_5;
	private String week6_6;
	private String xjAccount6_6;
	private String dfAccount6_6;

	private String week7_1;
	private String xjAccount7_1;
	private String dfAccount7_1;
	private String week7_2;
	private String xjAccount7_2;
	private String dfAccount7_2;
	private String week7_3;
	private String xjAccount7_3;
	private String dfAccount7_3;
	private String week7_4;
	private String xjAccount7_4;
	private String dfAccount7_4;
	private String week7_5;
	private String xjAccount7_5;
	private String dfAccount7_5;
	private String week7_6;
	private String xjAccount7_6;
	private String dfAccount7_6;

	private String week8_1;
	private String xjAccount8_1;
	private String dfAccount8_1;
	private String week8_2;
	private String xjAccount8_2;
	private String dfAccount8_2;
	private String week8_3;
	private String xjAccount8_3;
	private String dfAccount8_3;
	private String week8_4;
	private String xjAccount8_4;
	private String dfAccount8_4;
	private String week8_5;
	private String xjAccount8_5;
	private String dfAccount8_5;
	private String week8_6;
	private String xjAccount8_6;
	private String dfAccount8_6;

	private String week9_1;
	private String xjAccount9_1;
	private String dfAccount9_1;
	private String week9_2;
	private String xjAccount9_2;
	private String dfAccount9_2;
	private String week9_3;
	private String xjAccount9_3;
	private String dfAccount9_3;
	private String week9_4;
	private String xjAccount9_4;
	private String dfAccount9_4;
	private String week9_5;
	private String xjAccount9_5;
	private String dfAccount9_5;
	private String week9_6;
	private String xjAccount9_6;
	private String dfAccount9_6;

	private String week10_1;
	private String xjAccount10_1;
	private String dfAccount10_1;
	private String week10_2;
	private String xjAccount10_2;
	private String dfAccount10_2;
	private String week10_3;
	private String xjAccount10_3;
	private String dfAccount10_3;
	private String week10_4;
	private String xjAccount10_4;
	private String dfAccount10_4;
	private String week10_5;
	private String xjAccount10_5;
	private String dfAccount10_5;
	private String week10_6;
	private String xjAccount10_6;
	private String dfAccount10_6;

	private String week11_1;
	private String xjAccount11_1;
	private String dfAccount11_1;
	private String week11_2;
	private String xjAccount11_2;
	private String dfAccount11_2;
	private String week11_3;
	private String xjAccount11_3;
	private String dfAccount11_3;
	private String week11_4;
	private String xjAccount11_4;
	private String dfAccount11_4;
	private String week11_5;
	private String xjAccount11_5;
	private String dfAccount11_5;
	private String week11_6;
	private String xjAccount11_6;
	private String dfAccount11_6;

	private String week12_1;
	private String xjAccount12_1;
	private String dfAccount12_1;
	private String week12_2;
	private String xjAccount12_2;
	private String dfAccount12_2;
	private String week12_3;
	private String xjAccount12_3;
	private String dfAccount12_3;
	private String week12_4;
	private String xjAccount12_4;
	private String dfAccount12_4;
	private String week12_5;
	private String xjAccount12_5;
	private String dfAccount12_5;
	private String week12_6;
	private String xjAccount12_6;
	private String dfAccount12_6;
	
	private Date dateCreate;
	private Date dateUpdate;
	private Integer userIdCreate;
	private Integer userIdUpdate;
	private Boolean delFlag;
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
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
	public Integer getYearMonthVersion() {
		return yearMonthVersion;
	}
	public void setYearMonthVersion(Integer yearMonthVersion) {
		this.yearMonthVersion = yearMonthVersion;
	}
	public Integer getWeekVersion() {
		return weekVersion;
	}
	public void setWeekVersion(Integer weekVersion) {
		this.weekVersion = weekVersion;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getTrackType() {
		return trackType;
	}
	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}
	public String getWeek1_1() {
		return week1_1;
	}
	public void setWeek1_1(String week1_1) {
		this.week1_1 = week1_1;
	}
	public String getXjAccount1_1() {
		return xjAccount1_1;
	}
	public void setXjAccount1_1(String xjAccount1_1) {
		this.xjAccount1_1 = xjAccount1_1;
	}
	public String getDfAccount1_1() {
		return dfAccount1_1;
	}
	public void setDfAccount1_1(String dfAccount1_1) {
		this.dfAccount1_1 = dfAccount1_1;
	}
	public String getWeek1_2() {
		return week1_2;
	}
	public void setWeek1_2(String week1_2) {
		this.week1_2 = week1_2;
	}
	public String getXjAccount1_2() {
		return xjAccount1_2;
	}
	public void setXjAccount1_2(String xjAccount1_2) {
		this.xjAccount1_2 = xjAccount1_2;
	}
	public String getDfAccount1_2() {
		return dfAccount1_2;
	}
	public void setDfAccount1_2(String dfAccount1_2) {
		this.dfAccount1_2 = dfAccount1_2;
	}
	public String getWeek1_3() {
		return week1_3;
	}
	public void setWeek1_3(String week1_3) {
		this.week1_3 = week1_3;
	}
	public String getXjAccount1_3() {
		return xjAccount1_3;
	}
	public void setXjAccount1_3(String xjAccount1_3) {
		this.xjAccount1_3 = xjAccount1_3;
	}
	public String getDfAccount1_3() {
		return dfAccount1_3;
	}
	public void setDfAccount1_3(String dfAccount1_3) {
		this.dfAccount1_3 = dfAccount1_3;
	}
	public String getWeek1_4() {
		return week1_4;
	}
	public void setWeek1_4(String week1_4) {
		this.week1_4 = week1_4;
	}
	public String getXjAccount1_4() {
		return xjAccount1_4;
	}
	public void setXjAccount1_4(String xjAccount1_4) {
		this.xjAccount1_4 = xjAccount1_4;
	}
	public String getDfAccount1_4() {
		return dfAccount1_4;
	}
	public void setDfAccount1_4(String dfAccount1_4) {
		this.dfAccount1_4 = dfAccount1_4;
	}
	public String getWeek1_5() {
		return week1_5;
	}
	public void setWeek1_5(String week1_5) {
		this.week1_5 = week1_5;
	}
	public String getXjAccount1_5() {
		return xjAccount1_5;
	}
	public void setXjAccount1_5(String xjAccount1_5) {
		this.xjAccount1_5 = xjAccount1_5;
	}
	public String getDfAccount1_5() {
		return dfAccount1_5;
	}
	public void setDfAccount1_5(String dfAccount1_5) {
		this.dfAccount1_5 = dfAccount1_5;
	}
	public String getWeek1_6() {
		return week1_6;
	}
	public void setWeek1_6(String week1_6) {
		this.week1_6 = week1_6;
	}
	public String getXjAccount1_6() {
		return xjAccount1_6;
	}
	public void setXjAccount1_6(String xjAccount1_6) {
		this.xjAccount1_6 = xjAccount1_6;
	}
	public String getDfAccount1_6() {
		return dfAccount1_6;
	}
	public void setDfAccount1_6(String dfAccount1_6) {
		this.dfAccount1_6 = dfAccount1_6;
	}
	public String getWeek2_1() {
		return week2_1;
	}
	public void setWeek2_1(String week2_1) {
		this.week2_1 = week2_1;
	}
	public String getXjAccount2_1() {
		return xjAccount2_1;
	}
	public void setXjAccount2_1(String xjAccount2_1) {
		this.xjAccount2_1 = xjAccount2_1;
	}
	public String getDfAccount2_1() {
		return dfAccount2_1;
	}
	public void setDfAccount2_1(String dfAccount2_1) {
		this.dfAccount2_1 = dfAccount2_1;
	}
	public String getWeek2_2() {
		return week2_2;
	}
	public void setWeek2_2(String week2_2) {
		this.week2_2 = week2_2;
	}
	public String getXjAccount2_2() {
		return xjAccount2_2;
	}
	public void setXjAccount2_2(String xjAccount2_2) {
		this.xjAccount2_2 = xjAccount2_2;
	}
	public String getDfAccount2_2() {
		return dfAccount2_2;
	}
	public void setDfAccount2_2(String dfAccount2_2) {
		this.dfAccount2_2 = dfAccount2_2;
	}
	public String getWeek2_3() {
		return week2_3;
	}
	public void setWeek2_3(String week2_3) {
		this.week2_3 = week2_3;
	}
	public String getXjAccount2_3() {
		return xjAccount2_3;
	}
	public void setXjAccount2_3(String xjAccount2_3) {
		this.xjAccount2_3 = xjAccount2_3;
	}
	public String getDfAccount2_3() {
		return dfAccount2_3;
	}
	public void setDfAccount2_3(String dfAccount2_3) {
		this.dfAccount2_3 = dfAccount2_3;
	}
	public String getWeek2_4() {
		return week2_4;
	}
	public void setWeek2_4(String week2_4) {
		this.week2_4 = week2_4;
	}
	public String getXjAccount2_4() {
		return xjAccount2_4;
	}
	public void setXjAccount2_4(String xjAccount2_4) {
		this.xjAccount2_4 = xjAccount2_4;
	}
	public String getDfAccount2_4() {
		return dfAccount2_4;
	}
	public void setDfAccount2_4(String dfAccount2_4) {
		this.dfAccount2_4 = dfAccount2_4;
	}
	public String getWeek2_5() {
		return week2_5;
	}
	public void setWeek2_5(String week2_5) {
		this.week2_5 = week2_5;
	}
	public String getXjAccount2_5() {
		return xjAccount2_5;
	}
	public void setXjAccount2_5(String xjAccount2_5) {
		this.xjAccount2_5 = xjAccount2_5;
	}
	public String getDfAccount2_5() {
		return dfAccount2_5;
	}
	public void setDfAccount2_5(String dfAccount2_5) {
		this.dfAccount2_5 = dfAccount2_5;
	}
	public String getWeek2_6() {
		return week2_6;
	}
	public void setWeek2_6(String week2_6) {
		this.week2_6 = week2_6;
	}
	public String getXjAccount2_6() {
		return xjAccount2_6;
	}
	public void setXjAccount2_6(String xjAccount2_6) {
		this.xjAccount2_6 = xjAccount2_6;
	}
	public String getDfAccount2_6() {
		return dfAccount2_6;
	}
	public void setDfAccount2_6(String dfAccount2_6) {
		this.dfAccount2_6 = dfAccount2_6;
	}
	public String getWeek3_1() {
		return week3_1;
	}
	public void setWeek3_1(String week3_1) {
		this.week3_1 = week3_1;
	}
	public String getXjAccount3_1() {
		return xjAccount3_1;
	}
	public void setXjAccount3_1(String xjAccount3_1) {
		this.xjAccount3_1 = xjAccount3_1;
	}
	public String getDfAccount3_1() {
		return dfAccount3_1;
	}
	public void setDfAccount3_1(String dfAccount3_1) {
		this.dfAccount3_1 = dfAccount3_1;
	}
	public String getWeek3_2() {
		return week3_2;
	}
	public void setWeek3_2(String week3_2) {
		this.week3_2 = week3_2;
	}
	public String getXjAccount3_2() {
		return xjAccount3_2;
	}
	public void setXjAccount3_2(String xjAccount3_2) {
		this.xjAccount3_2 = xjAccount3_2;
	}
	public String getDfAccount3_2() {
		return dfAccount3_2;
	}
	public void setDfAccount3_2(String dfAccount3_2) {
		this.dfAccount3_2 = dfAccount3_2;
	}
	public String getWeek3_3() {
		return week3_3;
	}
	public void setWeek3_3(String week3_3) {
		this.week3_3 = week3_3;
	}
	public String getXjAccount3_3() {
		return xjAccount3_3;
	}
	public void setXjAccount3_3(String xjAccount3_3) {
		this.xjAccount3_3 = xjAccount3_3;
	}
	public String getDfAccount3_3() {
		return dfAccount3_3;
	}
	public void setDfAccount3_3(String dfAccount3_3) {
		this.dfAccount3_3 = dfAccount3_3;
	}
	public String getWeek3_4() {
		return week3_4;
	}
	public void setWeek3_4(String week3_4) {
		this.week3_4 = week3_4;
	}
	public String getXjAccount3_4() {
		return xjAccount3_4;
	}
	public void setXjAccount3_4(String xjAccount3_4) {
		this.xjAccount3_4 = xjAccount3_4;
	}
	public String getDfAccount3_4() {
		return dfAccount3_4;
	}
	public void setDfAccount3_4(String dfAccount3_4) {
		this.dfAccount3_4 = dfAccount3_4;
	}
	public String getWeek3_5() {
		return week3_5;
	}
	public void setWeek3_5(String week3_5) {
		this.week3_5 = week3_5;
	}
	public String getXjAccount3_5() {
		return xjAccount3_5;
	}
	public void setXjAccount3_5(String xjAccount3_5) {
		this.xjAccount3_5 = xjAccount3_5;
	}
	public String getDfAccount3_5() {
		return dfAccount3_5;
	}
	public void setDfAccount3_5(String dfAccount3_5) {
		this.dfAccount3_5 = dfAccount3_5;
	}
	public String getWeek3_6() {
		return week3_6;
	}
	public void setWeek3_6(String week3_6) {
		this.week3_6 = week3_6;
	}
	public String getXjAccount3_6() {
		return xjAccount3_6;
	}
	public void setXjAccount3_6(String xjAccount3_6) {
		this.xjAccount3_6 = xjAccount3_6;
	}
	public String getDfAccount3_6() {
		return dfAccount3_6;
	}
	public void setDfAccount3_6(String dfAccount3_6) {
		this.dfAccount3_6 = dfAccount3_6;
	}
	public String getWeek4_1() {
		return week4_1;
	}
	public void setWeek4_1(String week4_1) {
		this.week4_1 = week4_1;
	}
	public String getXjAccount4_1() {
		return xjAccount4_1;
	}
	public void setXjAccount4_1(String xjAccount4_1) {
		this.xjAccount4_1 = xjAccount4_1;
	}
	public String getDfAccount4_1() {
		return dfAccount4_1;
	}
	public void setDfAccount4_1(String dfAccount4_1) {
		this.dfAccount4_1 = dfAccount4_1;
	}
	public String getWeek4_2() {
		return week4_2;
	}
	public void setWeek4_2(String week4_2) {
		this.week4_2 = week4_2;
	}
	public String getXjAccount4_2() {
		return xjAccount4_2;
	}
	public void setXjAccount4_2(String xjAccount4_2) {
		this.xjAccount4_2 = xjAccount4_2;
	}
	public String getDfAccount4_2() {
		return dfAccount4_2;
	}
	public void setDfAccount4_2(String dfAccount4_2) {
		this.dfAccount4_2 = dfAccount4_2;
	}
	public String getWeek4_3() {
		return week4_3;
	}
	public void setWeek4_3(String week4_3) {
		this.week4_3 = week4_3;
	}
	public String getXjAccount4_3() {
		return xjAccount4_3;
	}
	public void setXjAccount4_3(String xjAccount4_3) {
		this.xjAccount4_3 = xjAccount4_3;
	}
	public String getDfAccount4_3() {
		return dfAccount4_3;
	}
	public void setDfAccount4_3(String dfAccount4_3) {
		this.dfAccount4_3 = dfAccount4_3;
	}
	public String getWeek4_4() {
		return week4_4;
	}
	public void setWeek4_4(String week4_4) {
		this.week4_4 = week4_4;
	}
	public String getXjAccount4_4() {
		return xjAccount4_4;
	}
	public void setXjAccount4_4(String xjAccount4_4) {
		this.xjAccount4_4 = xjAccount4_4;
	}
	public String getDfAccount4_4() {
		return dfAccount4_4;
	}
	public void setDfAccount4_4(String dfAccount4_4) {
		this.dfAccount4_4 = dfAccount4_4;
	}
	public String getWeek4_5() {
		return week4_5;
	}
	public void setWeek4_5(String week4_5) {
		this.week4_5 = week4_5;
	}
	public String getXjAccount4_5() {
		return xjAccount4_5;
	}
	public void setXjAccount4_5(String xjAccount4_5) {
		this.xjAccount4_5 = xjAccount4_5;
	}
	public String getDfAccount4_5() {
		return dfAccount4_5;
	}
	public void setDfAccount4_5(String dfAccount4_5) {
		this.dfAccount4_5 = dfAccount4_5;
	}
	public String getWeek4_6() {
		return week4_6;
	}
	public void setWeek4_6(String week4_6) {
		this.week4_6 = week4_6;
	}
	public String getXjAccount4_6() {
		return xjAccount4_6;
	}
	public void setXjAccount4_6(String xjAccount4_6) {
		this.xjAccount4_6 = xjAccount4_6;
	}
	public String getDfAccount4_6() {
		return dfAccount4_6;
	}
	public void setDfAccount4_6(String dfAccount4_6) {
		this.dfAccount4_6 = dfAccount4_6;
	}
	public String getWeek5_1() {
		return week5_1;
	}
	public void setWeek5_1(String week5_1) {
		this.week5_1 = week5_1;
	}
	public String getXjAccount5_1() {
		return xjAccount5_1;
	}
	public void setXjAccount5_1(String xjAccount5_1) {
		this.xjAccount5_1 = xjAccount5_1;
	}
	public String getDfAccount5_1() {
		return dfAccount5_1;
	}
	public void setDfAccount5_1(String dfAccount5_1) {
		this.dfAccount5_1 = dfAccount5_1;
	}
	public String getWeek5_2() {
		return week5_2;
	}
	public void setWeek5_2(String week5_2) {
		this.week5_2 = week5_2;
	}
	public String getXjAccount5_2() {
		return xjAccount5_2;
	}
	public void setXjAccount5_2(String xjAccount5_2) {
		this.xjAccount5_2 = xjAccount5_2;
	}
	public String getDfAccount5_2() {
		return dfAccount5_2;
	}
	public void setDfAccount5_2(String dfAccount5_2) {
		this.dfAccount5_2 = dfAccount5_2;
	}
	public String getWeek5_3() {
		return week5_3;
	}
	public void setWeek5_3(String week5_3) {
		this.week5_3 = week5_3;
	}
	public String getXjAccount5_3() {
		return xjAccount5_3;
	}
	public void setXjAccount5_3(String xjAccount5_3) {
		this.xjAccount5_3 = xjAccount5_3;
	}
	public String getDfAccount5_3() {
		return dfAccount5_3;
	}
	public void setDfAccount5_3(String dfAccount5_3) {
		this.dfAccount5_3 = dfAccount5_3;
	}
	public String getWeek5_4() {
		return week5_4;
	}
	public void setWeek5_4(String week5_4) {
		this.week5_4 = week5_4;
	}
	public String getXjAccount5_4() {
		return xjAccount5_4;
	}
	public void setXjAccount5_4(String xjAccount5_4) {
		this.xjAccount5_4 = xjAccount5_4;
	}
	public String getDfAccount5_4() {
		return dfAccount5_4;
	}
	public void setDfAccount5_4(String dfAccount5_4) {
		this.dfAccount5_4 = dfAccount5_4;
	}
	public String getWeek5_5() {
		return week5_5;
	}
	public void setWeek5_5(String week5_5) {
		this.week5_5 = week5_5;
	}
	public String getXjAccount5_5() {
		return xjAccount5_5;
	}
	public void setXjAccount5_5(String xjAccount5_5) {
		this.xjAccount5_5 = xjAccount5_5;
	}
	public String getDfAccount5_5() {
		return dfAccount5_5;
	}
	public void setDfAccount5_5(String dfAccount5_5) {
		this.dfAccount5_5 = dfAccount5_5;
	}
	public String getWeek5_6() {
		return week5_6;
	}
	public void setWeek5_6(String week5_6) {
		this.week5_6 = week5_6;
	}
	public String getXjAccount5_6() {
		return xjAccount5_6;
	}
	public void setXjAccount5_6(String xjAccount5_6) {
		this.xjAccount5_6 = xjAccount5_6;
	}
	public String getDfAccount5_6() {
		return dfAccount5_6;
	}
	public void setDfAccount5_6(String dfAccount5_6) {
		this.dfAccount5_6 = dfAccount5_6;
	}
	public String getWeek6_1() {
		return week6_1;
	}
	public void setWeek6_1(String week6_1) {
		this.week6_1 = week6_1;
	}
	public String getXjAccount6_1() {
		return xjAccount6_1;
	}
	public void setXjAccount6_1(String xjAccount6_1) {
		this.xjAccount6_1 = xjAccount6_1;
	}
	public String getDfAccount6_1() {
		return dfAccount6_1;
	}
	public void setDfAccount6_1(String dfAccount6_1) {
		this.dfAccount6_1 = dfAccount6_1;
	}
	public String getWeek6_2() {
		return week6_2;
	}
	public void setWeek6_2(String week6_2) {
		this.week6_2 = week6_2;
	}
	public String getXjAccount6_2() {
		return xjAccount6_2;
	}
	public void setXjAccount6_2(String xjAccount6_2) {
		this.xjAccount6_2 = xjAccount6_2;
	}
	public String getDfAccount6_2() {
		return dfAccount6_2;
	}
	public void setDfAccount6_2(String dfAccount6_2) {
		this.dfAccount6_2 = dfAccount6_2;
	}
	public String getWeek6_3() {
		return week6_3;
	}
	public void setWeek6_3(String week6_3) {
		this.week6_3 = week6_3;
	}
	public String getXjAccount6_3() {
		return xjAccount6_3;
	}
	public void setXjAccount6_3(String xjAccount6_3) {
		this.xjAccount6_3 = xjAccount6_3;
	}
	public String getDfAccount6_3() {
		return dfAccount6_3;
	}
	public void setDfAccount6_3(String dfAccount6_3) {
		this.dfAccount6_3 = dfAccount6_3;
	}
	public String getWeek6_4() {
		return week6_4;
	}
	public void setWeek6_4(String week6_4) {
		this.week6_4 = week6_4;
	}
	public String getXjAccount6_4() {
		return xjAccount6_4;
	}
	public void setXjAccount6_4(String xjAccount6_4) {
		this.xjAccount6_4 = xjAccount6_4;
	}
	public String getDfAccount6_4() {
		return dfAccount6_4;
	}
	public void setDfAccount6_4(String dfAccount6_4) {
		this.dfAccount6_4 = dfAccount6_4;
	}
	public String getWeek6_5() {
		return week6_5;
	}
	public void setWeek6_5(String week6_5) {
		this.week6_5 = week6_5;
	}
	public String getXjAccount6_5() {
		return xjAccount6_5;
	}
	public void setXjAccount6_5(String xjAccount6_5) {
		this.xjAccount6_5 = xjAccount6_5;
	}
	public String getDfAccount6_5() {
		return dfAccount6_5;
	}
	public void setDfAccount6_5(String dfAccount6_5) {
		this.dfAccount6_5 = dfAccount6_5;
	}
	public String getWeek6_6() {
		return week6_6;
	}
	public void setWeek6_6(String week6_6) {
		this.week6_6 = week6_6;
	}
	public String getXjAccount6_6() {
		return xjAccount6_6;
	}
	public void setXjAccount6_6(String xjAccount6_6) {
		this.xjAccount6_6 = xjAccount6_6;
	}
	public String getDfAccount6_6() {
		return dfAccount6_6;
	}
	public void setDfAccount6_6(String dfAccount6_6) {
		this.dfAccount6_6 = dfAccount6_6;
	}
	public String getWeek7_1() {
		return week7_1;
	}
	public void setWeek7_1(String week7_1) {
		this.week7_1 = week7_1;
	}
	public String getXjAccount7_1() {
		return xjAccount7_1;
	}
	public void setXjAccount7_1(String xjAccount7_1) {
		this.xjAccount7_1 = xjAccount7_1;
	}
	public String getDfAccount7_1() {
		return dfAccount7_1;
	}
	public void setDfAccount7_1(String dfAccount7_1) {
		this.dfAccount7_1 = dfAccount7_1;
	}
	public String getWeek7_2() {
		return week7_2;
	}
	public void setWeek7_2(String week7_2) {
		this.week7_2 = week7_2;
	}
	public String getXjAccount7_2() {
		return xjAccount7_2;
	}
	public void setXjAccount7_2(String xjAccount7_2) {
		this.xjAccount7_2 = xjAccount7_2;
	}
	public String getDfAccount7_2() {
		return dfAccount7_2;
	}
	public void setDfAccount7_2(String dfAccount7_2) {
		this.dfAccount7_2 = dfAccount7_2;
	}
	public String getWeek7_3() {
		return week7_3;
	}
	public void setWeek7_3(String week7_3) {
		this.week7_3 = week7_3;
	}
	public String getXjAccount7_3() {
		return xjAccount7_3;
	}
	public void setXjAccount7_3(String xjAccount7_3) {
		this.xjAccount7_3 = xjAccount7_3;
	}
	public String getDfAccount7_3() {
		return dfAccount7_3;
	}
	public void setDfAccount7_3(String dfAccount7_3) {
		this.dfAccount7_3 = dfAccount7_3;
	}
	public String getWeek7_4() {
		return week7_4;
	}
	public void setWeek7_4(String week7_4) {
		this.week7_4 = week7_4;
	}
	public String getXjAccount7_4() {
		return xjAccount7_4;
	}
	public void setXjAccount7_4(String xjAccount7_4) {
		this.xjAccount7_4 = xjAccount7_4;
	}
	public String getDfAccount7_4() {
		return dfAccount7_4;
	}
	public void setDfAccount7_4(String dfAccount7_4) {
		this.dfAccount7_4 = dfAccount7_4;
	}
	public String getWeek7_5() {
		return week7_5;
	}
	public void setWeek7_5(String week7_5) {
		this.week7_5 = week7_5;
	}
	public String getXjAccount7_5() {
		return xjAccount7_5;
	}
	public void setXjAccount7_5(String xjAccount7_5) {
		this.xjAccount7_5 = xjAccount7_5;
	}
	public String getDfAccount7_5() {
		return dfAccount7_5;
	}
	public void setDfAccount7_5(String dfAccount7_5) {
		this.dfAccount7_5 = dfAccount7_5;
	}
	public String getWeek7_6() {
		return week7_6;
	}
	public void setWeek7_6(String week7_6) {
		this.week7_6 = week7_6;
	}
	public String getXjAccount7_6() {
		return xjAccount7_6;
	}
	public void setXjAccount7_6(String xjAccount7_6) {
		this.xjAccount7_6 = xjAccount7_6;
	}
	public String getDfAccount7_6() {
		return dfAccount7_6;
	}
	public void setDfAccount7_6(String dfAccount7_6) {
		this.dfAccount7_6 = dfAccount7_6;
	}
	public String getWeek8_1() {
		return week8_1;
	}
	public void setWeek8_1(String week8_1) {
		this.week8_1 = week8_1;
	}
	public String getXjAccount8_1() {
		return xjAccount8_1;
	}
	public void setXjAccount8_1(String xjAccount8_1) {
		this.xjAccount8_1 = xjAccount8_1;
	}
	public String getDfAccount8_1() {
		return dfAccount8_1;
	}
	public void setDfAccount8_1(String dfAccount8_1) {
		this.dfAccount8_1 = dfAccount8_1;
	}
	public String getWeek8_2() {
		return week8_2;
	}
	public void setWeek8_2(String week8_2) {
		this.week8_2 = week8_2;
	}
	public String getXjAccount8_2() {
		return xjAccount8_2;
	}
	public void setXjAccount8_2(String xjAccount8_2) {
		this.xjAccount8_2 = xjAccount8_2;
	}
	public String getDfAccount8_2() {
		return dfAccount8_2;
	}
	public void setDfAccount8_2(String dfAccount8_2) {
		this.dfAccount8_2 = dfAccount8_2;
	}
	public String getWeek8_3() {
		return week8_3;
	}
	public void setWeek8_3(String week8_3) {
		this.week8_3 = week8_3;
	}
	public String getXjAccount8_3() {
		return xjAccount8_3;
	}
	public void setXjAccount8_3(String xjAccount8_3) {
		this.xjAccount8_3 = xjAccount8_3;
	}
	public String getDfAccount8_3() {
		return dfAccount8_3;
	}
	public void setDfAccount8_3(String dfAccount8_3) {
		this.dfAccount8_3 = dfAccount8_3;
	}
	public String getWeek8_4() {
		return week8_4;
	}
	public void setWeek8_4(String week8_4) {
		this.week8_4 = week8_4;
	}
	public String getXjAccount8_4() {
		return xjAccount8_4;
	}
	public void setXjAccount8_4(String xjAccount8_4) {
		this.xjAccount8_4 = xjAccount8_4;
	}
	public String getDfAccount8_4() {
		return dfAccount8_4;
	}
	public void setDfAccount8_4(String dfAccount8_4) {
		this.dfAccount8_4 = dfAccount8_4;
	}
	public String getWeek8_5() {
		return week8_5;
	}
	public void setWeek8_5(String week8_5) {
		this.week8_5 = week8_5;
	}
	public String getXjAccount8_5() {
		return xjAccount8_5;
	}
	public void setXjAccount8_5(String xjAccount8_5) {
		this.xjAccount8_5 = xjAccount8_5;
	}
	public String getDfAccount8_5() {
		return dfAccount8_5;
	}
	public void setDfAccount8_5(String dfAccount8_5) {
		this.dfAccount8_5 = dfAccount8_5;
	}
	public String getWeek8_6() {
		return week8_6;
	}
	public void setWeek8_6(String week8_6) {
		this.week8_6 = week8_6;
	}
	public String getXjAccount8_6() {
		return xjAccount8_6;
	}
	public void setXjAccount8_6(String xjAccount8_6) {
		this.xjAccount8_6 = xjAccount8_6;
	}
	public String getDfAccount8_6() {
		return dfAccount8_6;
	}
	public void setDfAccount8_6(String dfAccount8_6) {
		this.dfAccount8_6 = dfAccount8_6;
	}
	public String getWeek9_1() {
		return week9_1;
	}
	public void setWeek9_1(String week9_1) {
		this.week9_1 = week9_1;
	}
	public String getXjAccount9_1() {
		return xjAccount9_1;
	}
	public void setXjAccount9_1(String xjAccount9_1) {
		this.xjAccount9_1 = xjAccount9_1;
	}
	public String getDfAccount9_1() {
		return dfAccount9_1;
	}
	public void setDfAccount9_1(String dfAccount9_1) {
		this.dfAccount9_1 = dfAccount9_1;
	}
	public String getWeek9_2() {
		return week9_2;
	}
	public void setWeek9_2(String week9_2) {
		this.week9_2 = week9_2;
	}
	public String getXjAccount9_2() {
		return xjAccount9_2;
	}
	public void setXjAccount9_2(String xjAccount9_2) {
		this.xjAccount9_2 = xjAccount9_2;
	}
	public String getDfAccount9_2() {
		return dfAccount9_2;
	}
	public void setDfAccount9_2(String dfAccount9_2) {
		this.dfAccount9_2 = dfAccount9_2;
	}
	public String getWeek9_3() {
		return week9_3;
	}
	public void setWeek9_3(String week9_3) {
		this.week9_3 = week9_3;
	}
	public String getXjAccount9_3() {
		return xjAccount9_3;
	}
	public void setXjAccount9_3(String xjAccount9_3) {
		this.xjAccount9_3 = xjAccount9_3;
	}
	public String getDfAccount9_3() {
		return dfAccount9_3;
	}
	public void setDfAccount9_3(String dfAccount9_3) {
		this.dfAccount9_3 = dfAccount9_3;
	}
	public String getWeek9_4() {
		return week9_4;
	}
	public void setWeek9_4(String week9_4) {
		this.week9_4 = week9_4;
	}
	public String getXjAccount9_4() {
		return xjAccount9_4;
	}
	public void setXjAccount9_4(String xjAccount9_4) {
		this.xjAccount9_4 = xjAccount9_4;
	}
	public String getDfAccount9_4() {
		return dfAccount9_4;
	}
	public void setDfAccount9_4(String dfAccount9_4) {
		this.dfAccount9_4 = dfAccount9_4;
	}
	public String getWeek9_5() {
		return week9_5;
	}
	public void setWeek9_5(String week9_5) {
		this.week9_5 = week9_5;
	}
	public String getXjAccount9_5() {
		return xjAccount9_5;
	}
	public void setXjAccount9_5(String xjAccount9_5) {
		this.xjAccount9_5 = xjAccount9_5;
	}
	public String getDfAccount9_5() {
		return dfAccount9_5;
	}
	public void setDfAccount9_5(String dfAccount9_5) {
		this.dfAccount9_5 = dfAccount9_5;
	}
	public String getWeek9_6() {
		return week9_6;
	}
	public void setWeek9_6(String week9_6) {
		this.week9_6 = week9_6;
	}
	public String getXjAccount9_6() {
		return xjAccount9_6;
	}
	public void setXjAccount9_6(String xjAccount9_6) {
		this.xjAccount9_6 = xjAccount9_6;
	}
	public String getDfAccount9_6() {
		return dfAccount9_6;
	}
	public void setDfAccount9_6(String dfAccount9_6) {
		this.dfAccount9_6 = dfAccount9_6;
	}
	public String getWeek10_1() {
		return week10_1;
	}
	public void setWeek10_1(String week10_1) {
		this.week10_1 = week10_1;
	}
	public String getXjAccount10_1() {
		return xjAccount10_1;
	}
	public void setXjAccount10_1(String xjAccount10_1) {
		this.xjAccount10_1 = xjAccount10_1;
	}
	public String getDfAccount10_1() {
		return dfAccount10_1;
	}
	public void setDfAccount10_1(String dfAccount10_1) {
		this.dfAccount10_1 = dfAccount10_1;
	}
	public String getWeek10_2() {
		return week10_2;
	}
	public void setWeek10_2(String week10_2) {
		this.week10_2 = week10_2;
	}
	public String getXjAccount10_2() {
		return xjAccount10_2;
	}
	public void setXjAccount10_2(String xjAccount10_2) {
		this.xjAccount10_2 = xjAccount10_2;
	}
	public String getDfAccount10_2() {
		return dfAccount10_2;
	}
	public void setDfAccount10_2(String dfAccount10_2) {
		this.dfAccount10_2 = dfAccount10_2;
	}
	public String getWeek10_3() {
		return week10_3;
	}
	public void setWeek10_3(String week10_3) {
		this.week10_3 = week10_3;
	}
	public String getXjAccount10_3() {
		return xjAccount10_3;
	}
	public void setXjAccount10_3(String xjAccount10_3) {
		this.xjAccount10_3 = xjAccount10_3;
	}
	public String getDfAccount10_3() {
		return dfAccount10_3;
	}
	public void setDfAccount10_3(String dfAccount10_3) {
		this.dfAccount10_3 = dfAccount10_3;
	}
	public String getWeek10_4() {
		return week10_4;
	}
	public void setWeek10_4(String week10_4) {
		this.week10_4 = week10_4;
	}
	public String getXjAccount10_4() {
		return xjAccount10_4;
	}
	public void setXjAccount10_4(String xjAccount10_4) {
		this.xjAccount10_4 = xjAccount10_4;
	}
	public String getDfAccount10_4() {
		return dfAccount10_4;
	}
	public void setDfAccount10_4(String dfAccount10_4) {
		this.dfAccount10_4 = dfAccount10_4;
	}
	public String getWeek10_5() {
		return week10_5;
	}
	public void setWeek10_5(String week10_5) {
		this.week10_5 = week10_5;
	}
	public String getXjAccount10_5() {
		return xjAccount10_5;
	}
	public void setXjAccount10_5(String xjAccount10_5) {
		this.xjAccount10_5 = xjAccount10_5;
	}
	public String getDfAccount10_5() {
		return dfAccount10_5;
	}
	public void setDfAccount10_5(String dfAccount10_5) {
		this.dfAccount10_5 = dfAccount10_5;
	}
	public String getWeek10_6() {
		return week10_6;
	}
	public void setWeek10_6(String week10_6) {
		this.week10_6 = week10_6;
	}
	public String getXjAccount10_6() {
		return xjAccount10_6;
	}
	public void setXjAccount10_6(String xjAccount10_6) {
		this.xjAccount10_6 = xjAccount10_6;
	}
	public String getDfAccount10_6() {
		return dfAccount10_6;
	}
	public void setDfAccount10_6(String dfAccount10_6) {
		this.dfAccount10_6 = dfAccount10_6;
	}
	public String getWeek11_1() {
		return week11_1;
	}
	public void setWeek11_1(String week11_1) {
		this.week11_1 = week11_1;
	}
	public String getXjAccount11_1() {
		return xjAccount11_1;
	}
	public void setXjAccount11_1(String xjAccount11_1) {
		this.xjAccount11_1 = xjAccount11_1;
	}
	public String getDfAccount11_1() {
		return dfAccount11_1;
	}
	public void setDfAccount11_1(String dfAccount11_1) {
		this.dfAccount11_1 = dfAccount11_1;
	}
	public String getWeek11_2() {
		return week11_2;
	}
	public void setWeek11_2(String week11_2) {
		this.week11_2 = week11_2;
	}
	public String getXjAccount11_2() {
		return xjAccount11_2;
	}
	public void setXjAccount11_2(String xjAccount11_2) {
		this.xjAccount11_2 = xjAccount11_2;
	}
	public String getDfAccount11_2() {
		return dfAccount11_2;
	}
	public void setDfAccount11_2(String dfAccount11_2) {
		this.dfAccount11_2 = dfAccount11_2;
	}
	public String getWeek11_3() {
		return week11_3;
	}
	public void setWeek11_3(String week11_3) {
		this.week11_3 = week11_3;
	}
	public String getXjAccount11_3() {
		return xjAccount11_3;
	}
	public void setXjAccount11_3(String xjAccount11_3) {
		this.xjAccount11_3 = xjAccount11_3;
	}
	public String getDfAccount11_3() {
		return dfAccount11_3;
	}
	public void setDfAccount11_3(String dfAccount11_3) {
		this.dfAccount11_3 = dfAccount11_3;
	}
	public String getWeek11_4() {
		return week11_4;
	}
	public void setWeek11_4(String week11_4) {
		this.week11_4 = week11_4;
	}
	public String getXjAccount11_4() {
		return xjAccount11_4;
	}
	public void setXjAccount11_4(String xjAccount11_4) {
		this.xjAccount11_4 = xjAccount11_4;
	}
	public String getDfAccount11_4() {
		return dfAccount11_4;
	}
	public void setDfAccount11_4(String dfAccount11_4) {
		this.dfAccount11_4 = dfAccount11_4;
	}
	public String getWeek11_5() {
		return week11_5;
	}
	public void setWeek11_5(String week11_5) {
		this.week11_5 = week11_5;
	}
	public String getXjAccount11_5() {
		return xjAccount11_5;
	}
	public void setXjAccount11_5(String xjAccount11_5) {
		this.xjAccount11_5 = xjAccount11_5;
	}
	public String getDfAccount11_5() {
		return dfAccount11_5;
	}
	public void setDfAccount11_5(String dfAccount11_5) {
		this.dfAccount11_5 = dfAccount11_5;
	}
	public String getWeek11_6() {
		return week11_6;
	}
	public void setWeek11_6(String week11_6) {
		this.week11_6 = week11_6;
	}
	public String getXjAccount11_6() {
		return xjAccount11_6;
	}
	public void setXjAccount11_6(String xjAccount11_6) {
		this.xjAccount11_6 = xjAccount11_6;
	}
	public String getDfAccount11_6() {
		return dfAccount11_6;
	}
	public void setDfAccount11_6(String dfAccount11_6) {
		this.dfAccount11_6 = dfAccount11_6;
	}
	public String getWeek12_1() {
		return week12_1;
	}
	public void setWeek12_1(String week12_1) {
		this.week12_1 = week12_1;
	}
	public String getXjAccount12_1() {
		return xjAccount12_1;
	}
	public void setXjAccount12_1(String xjAccount12_1) {
		this.xjAccount12_1 = xjAccount12_1;
	}
	public String getDfAccount12_1() {
		return dfAccount12_1;
	}
	public void setDfAccount12_1(String dfAccount12_1) {
		this.dfAccount12_1 = dfAccount12_1;
	}
	public String getWeek12_2() {
		return week12_2;
	}
	public void setWeek12_2(String week12_2) {
		this.week12_2 = week12_2;
	}
	public String getXjAccount12_2() {
		return xjAccount12_2;
	}
	public void setXjAccount12_2(String xjAccount12_2) {
		this.xjAccount12_2 = xjAccount12_2;
	}
	public String getDfAccount12_2() {
		return dfAccount12_2;
	}
	public void setDfAccount12_2(String dfAccount12_2) {
		this.dfAccount12_2 = dfAccount12_2;
	}
	public String getWeek12_3() {
		return week12_3;
	}
	public void setWeek12_3(String week12_3) {
		this.week12_3 = week12_3;
	}
	public String getXjAccount12_3() {
		return xjAccount12_3;
	}
	public void setXjAccount12_3(String xjAccount12_3) {
		this.xjAccount12_3 = xjAccount12_3;
	}
	public String getDfAccount12_3() {
		return dfAccount12_3;
	}
	public void setDfAccount12_3(String dfAccount12_3) {
		this.dfAccount12_3 = dfAccount12_3;
	}
	public String getWeek12_4() {
		return week12_4;
	}
	public void setWeek12_4(String week12_4) {
		this.week12_4 = week12_4;
	}
	public String getXjAccount12_4() {
		return xjAccount12_4;
	}
	public void setXjAccount12_4(String xjAccount12_4) {
		this.xjAccount12_4 = xjAccount12_4;
	}
	public String getDfAccount12_4() {
		return dfAccount12_4;
	}
	public void setDfAccount12_4(String dfAccount12_4) {
		this.dfAccount12_4 = dfAccount12_4;
	}
	public String getWeek12_5() {
		return week12_5;
	}
	public void setWeek12_5(String week12_5) {
		this.week12_5 = week12_5;
	}
	public String getXjAccount12_5() {
		return xjAccount12_5;
	}
	public void setXjAccount12_5(String xjAccount12_5) {
		this.xjAccount12_5 = xjAccount12_5;
	}
	public String getDfAccount12_5() {
		return dfAccount12_5;
	}
	public void setDfAccount12_5(String dfAccount12_5) {
		this.dfAccount12_5 = dfAccount12_5;
	}
	public String getWeek12_6() {
		return week12_6;
	}
	public void setWeek12_6(String week12_6) {
		this.week12_6 = week12_6;
	}
	public String getXjAccount12_6() {
		return xjAccount12_6;
	}
	public void setXjAccount12_6(String xjAccount12_6) {
		this.xjAccount12_6 = xjAccount12_6;
	}
	public String getDfAccount12_6() {
		return dfAccount12_6;
	}
	public void setDfAccount12_6(String dfAccount12_6) {
		this.dfAccount12_6 = dfAccount12_6;
	}
	public Date getDateCreate() {
		return dateCreate;
	}
	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}
	public Date getDateUpdate() {
		return dateUpdate;
	}
	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}
	public Integer getUserIdCreate() {
		return userIdCreate;
	}
	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}
	public Integer getUserIdUpdate() {
		return userIdUpdate;
	}
	public void setUserIdUpdate(Integer userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}
	public Boolean getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}
