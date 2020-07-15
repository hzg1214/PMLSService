package cn.com.eju.pmls.report.frontPanel.model;

import java.util.Date;

public class PmlsNumWeek {
    private Integer id;

    private String hblCode;

    private String projectNo;

    private String projectName;

    private String cityNo;

    private String year;

    private String weekIndex;

    private Integer bb_Num;

    private Integer dk_Num;

    private Integer dd_Num;

    private Integer cx_Num;

    private Date datecreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHblCode() {
        return hblCode;
    }

    public void setHblCode(String hblCode) {
        this.hblCode = hblCode == null ? null : hblCode.trim();
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo == null ? null : projectNo.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo == null ? null : cityNo.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getWeekIndex() {
        return weekIndex;
    }

    public void setWeekIndex(String weekIndex) {
        this.weekIndex = weekIndex == null ? null : weekIndex.trim();
    }

    public Integer getBb_Num() {
        return bb_Num;
    }

    public void setBb_Num(Integer bb_Num) {
        this.bb_Num = bb_Num;
    }

    public Integer getDk_Num() {
        return dk_Num;
    }

    public void setDk_Num(Integer dk_Num) {
        this.dk_Num = dk_Num;
    }

    public Integer getDd_Num() {
        return dd_Num;
    }

    public void setDd_Num(Integer dd_Num) {
        this.dd_Num = dd_Num;
    }

    public Integer getCx_Num() {
        return cx_Num;
    }

    public void setCx_Num(Integer cx_Num) {
        this.cx_Num = cx_Num;
    }

    public Date getDatecreate() {
        return datecreate;
    }

    public void setDatecreate(Date datecreate) {
        this.datecreate = datecreate;
    }
}