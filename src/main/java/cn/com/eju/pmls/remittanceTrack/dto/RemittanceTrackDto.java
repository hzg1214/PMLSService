package cn.com.eju.pmls.remittanceTrack.dto;


import cn.com.eju.pmls.remittanceTrack.model.RemittanceTrack;

public class RemittanceTrackDto extends RemittanceTrack{
	private Integer rowNo;
	private String projectNo;//项目编号
	private String projectName;//项目名称
	private Integer projectStatus;//项目状态
	private String projectStatusStr;
	private String dataMonth;
	
	private String hkTotal;//回款合计
	
	private String weekTotal1;//第一周合计
	private String weekTotal2;
	private String weekTotal3;
	private String weekTotal4;
	private String weekTotal5;
	private String weekTotal6;
	
	private String xjAccount1;//现金
	private String xjAccount2;
	private String xjAccount3;
	private String xjAccount4;
	private String xjAccount5;
	private String xjAccount6;
	
	private String dfAccount1;//抵房
	private String dfAccount2;
	private String dfAccount3;
	private String dfAccount4;
	private String dfAccount5;
	private String dfAccount6;
	
	//周表头
	private String weekDate1;
	private String weekDate2;
	private String weekDate3;
	private String weekDate4;
	private String weekDate5;
	private String weekDate6;
	
	public String getDataMonth() {
		return dataMonth;
	}
	public void setDataMonth(String dataMonth) {
		this.dataMonth = dataMonth;
	}
	public Integer getRowNo() {
		return rowNo;
	}
	public void setRowNo(Integer rowNo) {
		this.rowNo = rowNo;
	}
	public String getWeekDate1() {
		return weekDate1;
	}
	public void setWeekDate1(String weekDate1) {
		this.weekDate1 = weekDate1;
	}
	public String getWeekDate2() {
		return weekDate2;
	}
	public void setWeekDate2(String weekDate2) {
		this.weekDate2 = weekDate2;
	}
	public String getWeekDate3() {
		return weekDate3;
	}
	public void setWeekDate3(String weekDate3) {
		this.weekDate3 = weekDate3;
	}
	public String getWeekDate4() {
		return weekDate4;
	}
	public void setWeekDate4(String weekDate4) {
		this.weekDate4 = weekDate4;
	}
	public String getWeekDate5() {
		return weekDate5;
	}
	public void setWeekDate5(String weekDate5) {
		this.weekDate5 = weekDate5;
	}
	public String getWeekDate6() {
		return weekDate6;
	}
	public void setWeekDate6(String weekDate6) {
		this.weekDate6 = weekDate6;
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
	public Integer getProjectStatus() {
		return projectStatus;
	}
	public void setProjectStatus(Integer projectStatus) {
		this.projectStatus = projectStatus;
	}
	public String getProjectStatusStr() {
		return projectStatusStr;
	}
	public void setProjectStatusStr(String projectStatusStr) {
		this.projectStatusStr = projectStatusStr;
	}
	public String getHkTotal() {
		return hkTotal;
	}
	public void setHkTotal(String hkTotal) {
		this.hkTotal = hkTotal;
	}
	public String getWeekTotal1() {
		return weekTotal1;
	}
	public void setWeekTotal1(String weekTotal1) {
		this.weekTotal1 = weekTotal1;
	}
	public String getWeekTotal2() {
		return weekTotal2;
	}
	public void setWeekTotal2(String weekTotal2) {
		this.weekTotal2 = weekTotal2;
	}
	public String getWeekTotal3() {
		return weekTotal3;
	}
	public void setWeekTotal3(String weekTotal3) {
		this.weekTotal3 = weekTotal3;
	}
	public String getWeekTotal4() {
		return weekTotal4;
	}
	public void setWeekTotal4(String weekTotal4) {
		this.weekTotal4 = weekTotal4;
	}
	public String getWeekTotal5() {
		return weekTotal5;
	}
	public void setWeekTotal5(String weekTotal5) {
		this.weekTotal5 = weekTotal5;
	}
	public String getWeekTotal6() {
		return weekTotal6;
	}
	public void setWeekTotal6(String weekTotal6) {
		this.weekTotal6 = weekTotal6;
	}
	public String getXjAccount1() {
		return xjAccount1;
	}
	public void setXjAccount1(String xjAccount1) {
		this.xjAccount1 = xjAccount1;
	}
	public String getXjAccount2() {
		return xjAccount2;
	}
	public void setXjAccount2(String xjAccount2) {
		this.xjAccount2 = xjAccount2;
	}
	public String getXjAccount3() {
		return xjAccount3;
	}
	public void setXjAccount3(String xjAccount3) {
		this.xjAccount3 = xjAccount3;
	}
	public String getXjAccount4() {
		return xjAccount4;
	}
	public void setXjAccount4(String xjAccount4) {
		this.xjAccount4 = xjAccount4;
	}
	public String getXjAccount5() {
		return xjAccount5;
	}
	public void setXjAccount5(String xjAccount5) {
		this.xjAccount5 = xjAccount5;
	}
	public String getXjAccount6() {
		return xjAccount6;
	}
	public void setXjAccount6(String xjAccount6) {
		this.xjAccount6 = xjAccount6;
	}
	public String getDfAccount1() {
		return dfAccount1;
	}
	public void setDfAccount1(String dfAccount1) {
		this.dfAccount1 = dfAccount1;
	}
	public String getDfAccount2() {
		return dfAccount2;
	}
	public void setDfAccount2(String dfAccount2) {
		this.dfAccount2 = dfAccount2;
	}
	public String getDfAccount3() {
		return dfAccount3;
	}
	public void setDfAccount3(String dfAccount3) {
		this.dfAccount3 = dfAccount3;
	}
	public String getDfAccount4() {
		return dfAccount4;
	}
	public void setDfAccount4(String dfAccount4) {
		this.dfAccount4 = dfAccount4;
	}
	public String getDfAccount5() {
		return dfAccount5;
	}
	public void setDfAccount5(String dfAccount5) {
		this.dfAccount5 = dfAccount5;
	}
	public String getDfAccount6() {
		return dfAccount6;
	}
	public void setDfAccount6(String dfAccount6) {
		this.dfAccount6 = dfAccount6;
	}
}
