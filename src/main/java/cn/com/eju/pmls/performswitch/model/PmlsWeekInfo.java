package cn.com.eju.pmls.performswitch.model;

import java.io.Serializable;
import java.util.Date;

public class PmlsWeekInfo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String year;

    private String month;

    private Integer weekNo;

    private Date weekStartDate;

    private Date weekEndDate;

    private String weekStartDateStr;

    private String weekEndDateStr;

    private Integer yearWeek;

    private Integer sortNo;

    private Date createDate;

    private String createUserCode;

    private Date updateDate;

    private String updateUserCode;

    private Boolean delFlag;

    private Integer monthWeekNum;//非表字段 

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Integer getWeekNo() {
        return weekNo;
    }

    public void setWeekNo(Integer weekNo) {
        this.weekNo = weekNo;
    }

    public Date getWeekStartDate() {
        return weekStartDate;
    }

    public void setWeekStartDate(Date weekStartDate) {
        this.weekStartDate = weekStartDate;
    }

    public Date getWeekEndDate() {
        return weekEndDate;
    }

    public void setWeekEndDate(Date weekEndDate) {
        this.weekEndDate = weekEndDate;
    }

    public String getWeekStartDateStr() {
        return weekStartDateStr;
    }

    public void setWeekStartDateStr(String weekStartDateStr) {
        this.weekStartDateStr = weekStartDateStr == null ? null : weekStartDateStr.trim();
    }

    public String getWeekEndDateStr() {
        return weekEndDateStr;
    }

    public void setWeekEndDateStr(String weekEndDateStr) {
        this.weekEndDateStr = weekEndDateStr == null ? null : weekEndDateStr.trim();
    }

    public Integer getYearWeek() {
        return yearWeek;
    }

    public void setYearWeek(Integer yearWeek) {
        this.yearWeek = yearWeek;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode == null ? null : createUserCode.trim();
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUserCode() {
        return updateUserCode;
    }

    public void setUpdateUserCode(String updateUserCode) {
        this.updateUserCode = updateUserCode == null ? null : updateUserCode.trim();
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getMonthWeekNum() {
        return monthWeekNum;
    }

    public void setMonthWeekNum(Integer monthWeekNum) {
        this.monthWeekNum = monthWeekNum;
    }
}