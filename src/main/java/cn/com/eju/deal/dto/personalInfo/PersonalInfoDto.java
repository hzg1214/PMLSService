package cn.com.eju.deal.dto.personalInfo;

import java.math.BigDecimal;

/**
 * Created by tanlang on 2018-11-01.
 */
public class PersonalInfoDto {

    private String userCode;
    private String userName;
    private String cityName;
    private String centerName;

    private BigDecimal dayXZMDNum;
    private BigDecimal dayXZCQNum;
    private BigDecimal dayXZQYNum;
    private BigDecimal dayXZFPNum;
    private BigDecimal weekXZMDNum;
    private BigDecimal weekXZCQNum;
    private BigDecimal weekXZQYNum;
    private BigDecimal weekXZFPNum;
    private BigDecimal monthXZMDNum;
    private BigDecimal monthXZCQNum;
    private BigDecimal monthXZQYNum;
    private BigDecimal monthXZFPNum;
    private BigDecimal monthDQDQStoreNum;
    private BigDecimal monthDQXQStoreNum;
    private BigDecimal monthXQRate;
    private BigDecimal monthJYQYNum;
    private BigDecimal monthJYJSAmount;
    private BigDecimal monthLDDDAmount;
    private BigDecimal monthLDCXAmount;
    private BigDecimal monthLDJSAmount;
    private BigDecimal quarterXZMDNum;
    private BigDecimal quarterXZCQNum;
    private BigDecimal quarterXZQYNum;
    private BigDecimal quarterXZFPNum;
    private BigDecimal quarterTYStoreNum;
    private BigDecimal quarterTQZZStoreNum;
    private BigDecimal quarterDQZZStoreNum;
    private BigDecimal quarterDQDQStoreNum;
    private BigDecimal quarterDQXQStoreNum;
    private BigDecimal quarterXQRate;
    private BigDecimal quarterJYQYNum;
    private BigDecimal quarterJYJSAmount;
    private BigDecimal quarterLDDDAmount;
    private BigDecimal quarterLDCXAmount;
    private BigDecimal quarterLDJSAmount;
    private BigDecimal yearXZMDNum;
    private BigDecimal yearXZCQNum;
    private BigDecimal yearXZQYNum;
    private BigDecimal yearXZFPNum;
    private BigDecimal yearDQDQStoreNum;
    private BigDecimal yearDQXQStoreNum;
    private BigDecimal yearXQRate;
    private BigDecimal yearJYQYNum;
    private BigDecimal yearJYJSAmount;
    private BigDecimal yearLDDDAmount;
    private BigDecimal yearLDCXAmount;
    private BigDecimal yearLDJSAmount;
    private BigDecimal lastMonthDQMDNum;
    private BigDecimal lastMonthXQMDNum;
    private BigDecimal lastMonthXQRate;
    private BigDecimal lastQuarterFPCHMDNum;
    private BigDecimal lastQuarterDQMDNum;
    private BigDecimal lastQuarterXQMDNum;
    private BigDecimal lastQuarterXQRate;
    private BigDecimal lastYearDQMDNum;
    private BigDecimal lastYearXQMDNum;
    private BigDecimal lastYearXQRate;
    private BigDecimal totalXZMDNum;
    private BigDecimal totalXZCQNum;
    private BigDecimal totalXZQYNum;
    private BigDecimal totalXZFPNum;
    private BigDecimal totalDQDQStoreNum;
    private BigDecimal totalDQXQStoreNum;
    private BigDecimal totalYSBZJMDNum;
    private BigDecimal totalYSBZJAmount;
    private BigDecimal totalSSBZJMDNum;
    private BigDecimal totalSSBZJAmount;
    private BigDecimal totalWSBZJMDNum;
    private BigDecimal totalWSBZJAmount;
    private BigDecimal totalZXSQZMDNum;
    private BigDecimal totalZXSQTGMDNum;
    private BigDecimal totalZXWCMDNum;
    private BigDecimal totalZXYSWCMDNum;
    private BigDecimal totalQYCHMDNum;
    private BigDecimal totalFPCHMDNum;
    private BigDecimal gsTotalYSBZJMDNum;
    private BigDecimal gsTotalYSBZJAmount;
    private BigDecimal gsTotalSSBZJMDNum;
    private BigDecimal gsTotalSSBZJAmount;
    private BigDecimal gsTotalWSBZJMDNum;
    private BigDecimal gsTotalWSBZJAmount;
    private BigDecimal wgsTotalYSBZJMDNum;
    private BigDecimal wgsTotalYSBZJAmount;
    private BigDecimal wgsTotalSSBZJMDNum;
    private BigDecimal wgsTotalSSBZJAmount;
    private BigDecimal wgsTotalWSBZJMDNum;
    private BigDecimal wgsTotalWSBZJAmount;
    private BigDecimal quarterDCMDNum;
    private BigDecimal quarterDRMDNum;
    private BigDecimal quarterQYWHMDNum;
    private BigDecimal quarterFPWHMDNum;
    private BigDecimal chRate;
    private BigDecimal dayFPGJMDNum;
    private BigDecimal dayFPGJCSNum;
    private BigDecimal dayWFPGJMDNum;
    private BigDecimal dayWFPGJCSNum;
    private BigDecimal weekFPGJMDNum;
    private BigDecimal weekFPGJCSNum;
    private BigDecimal weekWFPGJMDNum;
    private BigDecimal weekWFPGJCSNum;
    private BigDecimal monthFPGJMDNum;
    private BigDecimal monthFPGJCSNum;
    private BigDecimal monthWFPGJMDNum;
    private BigDecimal monthWFPGJCSNum;
    private BigDecimal quarterFPGJMDNum;
    private BigDecimal quarterFPGJCSNum;
    private BigDecimal quarterWFPGJMDNum;
    private BigDecimal quarterWFPGJCSNum;
    private BigDecimal yearFPGJMDNum;
    private BigDecimal yearFPGJCSNum;
    private BigDecimal yearWFPGJMDNum;
    private BigDecimal yearWFPGJCSNum;
    private BigDecimal totalGLMDNum;//累计管理门店数
    private BigDecimal totalWHCQMDNum;//累计维护草签门店数
    private BigDecimal totalWHQYMDNum;//累计维护过审门店数
    private BigDecimal totalWHFPMDNum;//累计维护翻牌门店数

    private BigDecimal lastQuarterFPCHMDNum2;//延续门店
    private BigDecimal totalZXYZMDNum;//装修应装门店数

    public BigDecimal getTotalZXYZMDNum() {
        return totalZXYZMDNum;
    }

    public void setTotalZXYZMDNum(BigDecimal totalZXYZMDNum) {
        this.totalZXYZMDNum = totalZXYZMDNum;
    }

    public BigDecimal getLastQuarterFPCHMDNum2() {
        return lastQuarterFPCHMDNum2;
    }

    public void setLastQuarterFPCHMDNum2(BigDecimal lastQuarterFPCHMDNum2) {
        this.lastQuarterFPCHMDNum2 = lastQuarterFPCHMDNum2;
    }

    public BigDecimal getTotalWHCQMDNum() {
        return totalWHCQMDNum;
    }

    public void setTotalWHCQMDNum(BigDecimal totalWHCQMDNum) {
        this.totalWHCQMDNum = totalWHCQMDNum;
    }

    public BigDecimal getTotalWHQYMDNum() {
        return totalWHQYMDNum;
    }

    public void setTotalWHQYMDNum(BigDecimal totalWHQYMDNum) {
        this.totalWHQYMDNum = totalWHQYMDNum;
    }

    public BigDecimal getTotalWHFPMDNum() {
        return totalWHFPMDNum;
    }

    public void setTotalWHFPMDNum(BigDecimal totalWHFPMDNum) {
        this.totalWHFPMDNum = totalWHFPMDNum;
    }

    public BigDecimal getTotalGLMDNum() {
        return totalGLMDNum;
    }

    public void setTotalGLMDNum(BigDecimal totalGLMDNum) {
        this.totalGLMDNum = totalGLMDNum;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public BigDecimal getDayXZMDNum() {
        return dayXZMDNum;
    }

    public void setDayXZMDNum(BigDecimal dayXZMDNum) {
        this.dayXZMDNum = dayXZMDNum;
    }

    public BigDecimal getDayXZCQNum() {
        return dayXZCQNum;
    }

    public void setDayXZCQNum(BigDecimal dayXZCQNum) {
        this.dayXZCQNum = dayXZCQNum;
    }

    public BigDecimal getDayXZQYNum() {
        return dayXZQYNum;
    }

    public void setDayXZQYNum(BigDecimal dayXZQYNum) {
        this.dayXZQYNum = dayXZQYNum;
    }

    public BigDecimal getDayXZFPNum() {
        return dayXZFPNum;
    }

    public void setDayXZFPNum(BigDecimal dayXZFPNum) {
        this.dayXZFPNum = dayXZFPNum;
    }

    public BigDecimal getWeekXZMDNum() {
        return weekXZMDNum;
    }

    public void setWeekXZMDNum(BigDecimal weekXZMDNum) {
        this.weekXZMDNum = weekXZMDNum;
    }

    public BigDecimal getWeekXZCQNum() {
        return weekXZCQNum;
    }

    public void setWeekXZCQNum(BigDecimal weekXZCQNum) {
        this.weekXZCQNum = weekXZCQNum;
    }

    public BigDecimal getWeekXZQYNum() {
        return weekXZQYNum;
    }

    public void setWeekXZQYNum(BigDecimal weekXZQYNum) {
        this.weekXZQYNum = weekXZQYNum;
    }

    public BigDecimal getWeekXZFPNum() {
        return weekXZFPNum;
    }

    public void setWeekXZFPNum(BigDecimal weekXZFPNum) {
        this.weekXZFPNum = weekXZFPNum;
    }

    public BigDecimal getMonthXZMDNum() {
        return monthXZMDNum;
    }

    public void setMonthXZMDNum(BigDecimal monthXZMDNum) {
        this.monthXZMDNum = monthXZMDNum;
    }

    public BigDecimal getMonthXZCQNum() {
        return monthXZCQNum;
    }

    public void setMonthXZCQNum(BigDecimal monthXZCQNum) {
        this.monthXZCQNum = monthXZCQNum;
    }

    public BigDecimal getMonthXZQYNum() {
        return monthXZQYNum;
    }

    public void setMonthXZQYNum(BigDecimal monthXZQYNum) {
        this.monthXZQYNum = monthXZQYNum;
    }

    public BigDecimal getMonthXZFPNum() {
        return monthXZFPNum;
    }

    public void setMonthXZFPNum(BigDecimal monthXZFPNum) {
        this.monthXZFPNum = monthXZFPNum;
    }

    public BigDecimal getMonthDQDQStoreNum() {
        return monthDQDQStoreNum;
    }

    public void setMonthDQDQStoreNum(BigDecimal monthDQDQStoreNum) {
        this.monthDQDQStoreNum = monthDQDQStoreNum;
    }

    public BigDecimal getMonthDQXQStoreNum() {
        return monthDQXQStoreNum;
    }

    public void setMonthDQXQStoreNum(BigDecimal monthDQXQStoreNum) {
        this.monthDQXQStoreNum = monthDQXQStoreNum;
    }

    public BigDecimal getMonthXQRate() {
        return monthXQRate;
    }

    public void setMonthXQRate(BigDecimal monthXQRate) {
        this.monthXQRate = monthXQRate;
    }

    public BigDecimal getMonthJYQYNum() {
        return monthJYQYNum;
    }

    public void setMonthJYQYNum(BigDecimal monthJYQYNum) {
        this.monthJYQYNum = monthJYQYNum;
    }

    public BigDecimal getMonthJYJSAmount() {
        return monthJYJSAmount;
    }

    public void setMonthJYJSAmount(BigDecimal monthJYJSAmount) {
        this.monthJYJSAmount = monthJYJSAmount;
    }

    public BigDecimal getMonthLDDDAmount() {
        return monthLDDDAmount;
    }

    public void setMonthLDDDAmount(BigDecimal monthLDDDAmount) {
        this.monthLDDDAmount = monthLDDDAmount;
    }

    public BigDecimal getMonthLDCXAmount() {
        return monthLDCXAmount;
    }

    public void setMonthLDCXAmount(BigDecimal monthLDCXAmount) {
        this.monthLDCXAmount = monthLDCXAmount;
    }

    public BigDecimal getMonthLDJSAmount() {
        return monthLDJSAmount;
    }

    public void setMonthLDJSAmount(BigDecimal monthLDJSAmount) {
        this.monthLDJSAmount = monthLDJSAmount;
    }

    public BigDecimal getQuarterXZMDNum() {
        return quarterXZMDNum;
    }

    public void setQuarterXZMDNum(BigDecimal quarterXZMDNum) {
        this.quarterXZMDNum = quarterXZMDNum;
    }

    public BigDecimal getQuarterXZCQNum() {
        return quarterXZCQNum;
    }

    public void setQuarterXZCQNum(BigDecimal quarterXZCQNum) {
        this.quarterXZCQNum = quarterXZCQNum;
    }

    public BigDecimal getQuarterXZQYNum() {
        return quarterXZQYNum;
    }

    public void setQuarterXZQYNum(BigDecimal quarterXZQYNum) {
        this.quarterXZQYNum = quarterXZQYNum;
    }

    public BigDecimal getQuarterXZFPNum() {
        return quarterXZFPNum;
    }

    public void setQuarterXZFPNum(BigDecimal quarterXZFPNum) {
        this.quarterXZFPNum = quarterXZFPNum;
    }

    public BigDecimal getQuarterTYStoreNum() {
        return quarterTYStoreNum;
    }

    public void setQuarterTYStoreNum(BigDecimal quarterTYStoreNum) {
        this.quarterTYStoreNum = quarterTYStoreNum;
    }

    public BigDecimal getQuarterTQZZStoreNum() {
        return quarterTQZZStoreNum;
    }

    public void setQuarterTQZZStoreNum(BigDecimal quarterTQZZStoreNum) {
        this.quarterTQZZStoreNum = quarterTQZZStoreNum;
    }

    public BigDecimal getQuarterDQZZStoreNum() {
        return quarterDQZZStoreNum;
    }

    public void setQuarterDQZZStoreNum(BigDecimal quarterDQZZStoreNum) {
        this.quarterDQZZStoreNum = quarterDQZZStoreNum;
    }

    public BigDecimal getQuarterDQDQStoreNum() {
        return quarterDQDQStoreNum;
    }

    public void setQuarterDQDQStoreNum(BigDecimal quarterDQDQStoreNum) {
        this.quarterDQDQStoreNum = quarterDQDQStoreNum;
    }

    public BigDecimal getQuarterDQXQStoreNum() {
        return quarterDQXQStoreNum;
    }

    public void setQuarterDQXQStoreNum(BigDecimal quarterDQXQStoreNum) {
        this.quarterDQXQStoreNum = quarterDQXQStoreNum;
    }

    public BigDecimal getQuarterXQRate() {
        return quarterXQRate;
    }

    public void setQuarterXQRate(BigDecimal quarterXQRate) {
        this.quarterXQRate = quarterXQRate;
    }

    public BigDecimal getQuarterJYQYNum() {
        return quarterJYQYNum;
    }

    public void setQuarterJYQYNum(BigDecimal quarterJYQYNum) {
        this.quarterJYQYNum = quarterJYQYNum;
    }

    public BigDecimal getQuarterJYJSAmount() {
        return quarterJYJSAmount;
    }

    public void setQuarterJYJSAmount(BigDecimal quarterJYJSAmount) {
        this.quarterJYJSAmount = quarterJYJSAmount;
    }

    public BigDecimal getQuarterLDDDAmount() {
        return quarterLDDDAmount;
    }

    public void setQuarterLDDDAmount(BigDecimal quarterLDDDAmount) {
        this.quarterLDDDAmount = quarterLDDDAmount;
    }

    public BigDecimal getQuarterLDCXAmount() {
        return quarterLDCXAmount;
    }

    public void setQuarterLDCXAmount(BigDecimal quarterLDCXAmount) {
        this.quarterLDCXAmount = quarterLDCXAmount;
    }

    public BigDecimal getQuarterLDJSAmount() {
        return quarterLDJSAmount;
    }

    public void setQuarterLDJSAmount(BigDecimal quarterLDJSAmount) {
        this.quarterLDJSAmount = quarterLDJSAmount;
    }

    public BigDecimal getYearXZMDNum() {
        return yearXZMDNum;
    }

    public void setYearXZMDNum(BigDecimal yearXZMDNum) {
        this.yearXZMDNum = yearXZMDNum;
    }

    public BigDecimal getYearXZCQNum() {
        return yearXZCQNum;
    }

    public void setYearXZCQNum(BigDecimal yearXZCQNum) {
        this.yearXZCQNum = yearXZCQNum;
    }

    public BigDecimal getYearXZQYNum() {
        return yearXZQYNum;
    }

    public void setYearXZQYNum(BigDecimal yearXZQYNum) {
        this.yearXZQYNum = yearXZQYNum;
    }

    public BigDecimal getYearXZFPNum() {
        return yearXZFPNum;
    }

    public void setYearXZFPNum(BigDecimal yearXZFPNum) {
        this.yearXZFPNum = yearXZFPNum;
    }

    public BigDecimal getYearDQDQStoreNum() {
        return yearDQDQStoreNum;
    }

    public void setYearDQDQStoreNum(BigDecimal yearDQDQStoreNum) {
        this.yearDQDQStoreNum = yearDQDQStoreNum;
    }

    public BigDecimal getYearDQXQStoreNum() {
        return yearDQXQStoreNum;
    }

    public void setYearDQXQStoreNum(BigDecimal yearDQXQStoreNum) {
        this.yearDQXQStoreNum = yearDQXQStoreNum;
    }

    public BigDecimal getYearXQRate() {
        return yearXQRate;
    }

    public void setYearXQRate(BigDecimal yearXQRate) {
        this.yearXQRate = yearXQRate;
    }

    public BigDecimal getYearJYQYNum() {
        return yearJYQYNum;
    }

    public void setYearJYQYNum(BigDecimal yearJYQYNum) {
        this.yearJYQYNum = yearJYQYNum;
    }

    public BigDecimal getYearJYJSAmount() {
        return yearJYJSAmount;
    }

    public void setYearJYJSAmount(BigDecimal yearJYJSAmount) {
        this.yearJYJSAmount = yearJYJSAmount;
    }

    public BigDecimal getYearLDDDAmount() {
        return yearLDDDAmount;
    }

    public void setYearLDDDAmount(BigDecimal yearLDDDAmount) {
        this.yearLDDDAmount = yearLDDDAmount;
    }

    public BigDecimal getYearLDCXAmount() {
        return yearLDCXAmount;
    }

    public void setYearLDCXAmount(BigDecimal yearLDCXAmount) {
        this.yearLDCXAmount = yearLDCXAmount;
    }

    public BigDecimal getYearLDJSAmount() {
        return yearLDJSAmount;
    }

    public void setYearLDJSAmount(BigDecimal yearLDJSAmount) {
        this.yearLDJSAmount = yearLDJSAmount;
    }

    public BigDecimal getLastMonthDQMDNum() {
        return lastMonthDQMDNum;
    }

    public void setLastMonthDQMDNum(BigDecimal lastMonthDQMDNum) {
        this.lastMonthDQMDNum = lastMonthDQMDNum;
    }

    public BigDecimal getLastMonthXQMDNum() {
        return lastMonthXQMDNum;
    }

    public void setLastMonthXQMDNum(BigDecimal lastMonthXQMDNum) {
        this.lastMonthXQMDNum = lastMonthXQMDNum;
    }

    public BigDecimal getLastMonthXQRate() {
        return lastMonthXQRate;
    }

    public void setLastMonthXQRate(BigDecimal lastMonthXQRate) {
        this.lastMonthXQRate = lastMonthXQRate;
    }

    public BigDecimal getLastQuarterFPCHMDNum() {
        return lastQuarterFPCHMDNum;
    }

    public void setLastQuarterFPCHMDNum(BigDecimal lastQuarterFPCHMDNum) {
        this.lastQuarterFPCHMDNum = lastQuarterFPCHMDNum;
    }

    public BigDecimal getLastQuarterDQMDNum() {
        return lastQuarterDQMDNum;
    }

    public void setLastQuarterDQMDNum(BigDecimal lastQuarterDQMDNum) {
        this.lastQuarterDQMDNum = lastQuarterDQMDNum;
    }

    public BigDecimal getLastQuarterXQMDNum() {
        return lastQuarterXQMDNum;
    }

    public void setLastQuarterXQMDNum(BigDecimal lastQuarterXQMDNum) {
        this.lastQuarterXQMDNum = lastQuarterXQMDNum;
    }

    public BigDecimal getLastQuarterXQRate() {
        return lastQuarterXQRate;
    }

    public void setLastQuarterXQRate(BigDecimal lastQuarterXQRate) {
        this.lastQuarterXQRate = lastQuarterXQRate;
    }

    public BigDecimal getLastYearDQMDNum() {
        return lastYearDQMDNum;
    }

    public void setLastYearDQMDNum(BigDecimal lastYearDQMDNum) {
        this.lastYearDQMDNum = lastYearDQMDNum;
    }

    public BigDecimal getLastYearXQMDNum() {
        return lastYearXQMDNum;
    }

    public void setLastYearXQMDNum(BigDecimal lastYearXQMDNum) {
        this.lastYearXQMDNum = lastYearXQMDNum;
    }

    public BigDecimal getLastYearXQRate() {
        return lastYearXQRate;
    }

    public void setLastYearXQRate(BigDecimal lastYearXQRate) {
        this.lastYearXQRate = lastYearXQRate;
    }

    public BigDecimal getTotalXZMDNum() {
        return totalXZMDNum;
    }

    public void setTotalXZMDNum(BigDecimal totalXZMDNum) {
        this.totalXZMDNum = totalXZMDNum;
    }

    public BigDecimal getTotalXZCQNum() {
        return totalXZCQNum;
    }

    public void setTotalXZCQNum(BigDecimal totalXZCQNum) {
        this.totalXZCQNum = totalXZCQNum;
    }

    public BigDecimal getTotalXZQYNum() {
        return totalXZQYNum;
    }

    public void setTotalXZQYNum(BigDecimal totalXZQYNum) {
        this.totalXZQYNum = totalXZQYNum;
    }

    public BigDecimal getTotalXZFPNum() {
        return totalXZFPNum;
    }

    public void setTotalXZFPNum(BigDecimal totalXZFPNum) {
        this.totalXZFPNum = totalXZFPNum;
    }

    public BigDecimal getTotalDQDQStoreNum() {
        return totalDQDQStoreNum;
    }

    public void setTotalDQDQStoreNum(BigDecimal totalDQDQStoreNum) {
        this.totalDQDQStoreNum = totalDQDQStoreNum;
    }

    public BigDecimal getTotalDQXQStoreNum() {
        return totalDQXQStoreNum;
    }

    public void setTotalDQXQStoreNum(BigDecimal totalDQXQStoreNum) {
        this.totalDQXQStoreNum = totalDQXQStoreNum;
    }

    public BigDecimal getTotalYSBZJMDNum() {
        return totalYSBZJMDNum;
    }

    public void setTotalYSBZJMDNum(BigDecimal totalYSBZJMDNum) {
        this.totalYSBZJMDNum = totalYSBZJMDNum;
    }

    public BigDecimal getTotalYSBZJAmount() {
        return totalYSBZJAmount;
    }

    public void setTotalYSBZJAmount(BigDecimal totalYSBZJAmount) {
        this.totalYSBZJAmount = totalYSBZJAmount;
    }

    public BigDecimal getTotalSSBZJMDNum() {
        return totalSSBZJMDNum;
    }

    public void setTotalSSBZJMDNum(BigDecimal totalSSBZJMDNum) {
        this.totalSSBZJMDNum = totalSSBZJMDNum;
    }

    public BigDecimal getTotalSSBZJAmount() {
        return totalSSBZJAmount;
    }

    public void setTotalSSBZJAmount(BigDecimal totalSSBZJAmount) {
        this.totalSSBZJAmount = totalSSBZJAmount;
    }

    public BigDecimal getTotalWSBZJMDNum() {
        return totalWSBZJMDNum;
    }

    public void setTotalWSBZJMDNum(BigDecimal totalWSBZJMDNum) {
        this.totalWSBZJMDNum = totalWSBZJMDNum;
    }

    public BigDecimal getTotalWSBZJAmount() {
        return totalWSBZJAmount;
    }

    public void setTotalWSBZJAmount(BigDecimal totalWSBZJAmount) {
        this.totalWSBZJAmount = totalWSBZJAmount;
    }

    public BigDecimal getTotalZXSQZMDNum() {
        return totalZXSQZMDNum;
    }

    public void setTotalZXSQZMDNum(BigDecimal totalZXSQZMDNum) {
        this.totalZXSQZMDNum = totalZXSQZMDNum;
    }

    public BigDecimal getTotalZXSQTGMDNum() {
        return totalZXSQTGMDNum;
    }

    public void setTotalZXSQTGMDNum(BigDecimal totalZXSQTGMDNum) {
        this.totalZXSQTGMDNum = totalZXSQTGMDNum;
    }

    public BigDecimal getTotalZXWCMDNum() {
        return totalZXWCMDNum;
    }

    public void setTotalZXWCMDNum(BigDecimal totalZXWCMDNum) {
        this.totalZXWCMDNum = totalZXWCMDNum;
    }

    public BigDecimal getTotalZXYSWCMDNum() {
        return totalZXYSWCMDNum;
    }

    public void setTotalZXYSWCMDNum(BigDecimal totalZXYSWCMDNum) {
        this.totalZXYSWCMDNum = totalZXYSWCMDNum;
    }

    public BigDecimal getTotalQYCHMDNum() {
        return totalQYCHMDNum;
    }

    public void setTotalQYCHMDNum(BigDecimal totalQYCHMDNum) {
        this.totalQYCHMDNum = totalQYCHMDNum;
    }

    public BigDecimal getTotalFPCHMDNum() {
        return totalFPCHMDNum;
    }

    public void setTotalFPCHMDNum(BigDecimal totalFPCHMDNum) {
        this.totalFPCHMDNum = totalFPCHMDNum;
    }

    public BigDecimal getGsTotalYSBZJMDNum() {
        return gsTotalYSBZJMDNum;
    }

    public void setGsTotalYSBZJMDNum(BigDecimal gsTotalYSBZJMDNum) {
        this.gsTotalYSBZJMDNum = gsTotalYSBZJMDNum;
    }

    public BigDecimal getGsTotalYSBZJAmount() {
        return gsTotalYSBZJAmount;
    }

    public void setGsTotalYSBZJAmount(BigDecimal gsTotalYSBZJAmount) {
        this.gsTotalYSBZJAmount = gsTotalYSBZJAmount;
    }

    public BigDecimal getGsTotalSSBZJMDNum() {
        return gsTotalSSBZJMDNum;
    }

    public void setGsTotalSSBZJMDNum(BigDecimal gsTotalSSBZJMDNum) {
        this.gsTotalSSBZJMDNum = gsTotalSSBZJMDNum;
    }

    public BigDecimal getGsTotalSSBZJAmount() {
        return gsTotalSSBZJAmount;
    }

    public void setGsTotalSSBZJAmount(BigDecimal gsTotalSSBZJAmount) {
        this.gsTotalSSBZJAmount = gsTotalSSBZJAmount;
    }

    public BigDecimal getGsTotalWSBZJMDNum() {
        return gsTotalWSBZJMDNum;
    }

    public void setGsTotalWSBZJMDNum(BigDecimal gsTotalWSBZJMDNum) {
        this.gsTotalWSBZJMDNum = gsTotalWSBZJMDNum;
    }

    public BigDecimal getGsTotalWSBZJAmount() {
        return gsTotalWSBZJAmount;
    }

    public void setGsTotalWSBZJAmount(BigDecimal gsTotalWSBZJAmount) {
        this.gsTotalWSBZJAmount = gsTotalWSBZJAmount;
    }

    public BigDecimal getWgsTotalYSBZJMDNum() {
        return wgsTotalYSBZJMDNum;
    }

    public void setWgsTotalYSBZJMDNum(BigDecimal wgsTotalYSBZJMDNum) {
        this.wgsTotalYSBZJMDNum = wgsTotalYSBZJMDNum;
    }

    public BigDecimal getWgsTotalYSBZJAmount() {
        return wgsTotalYSBZJAmount;
    }

    public void setWgsTotalYSBZJAmount(BigDecimal wgsTotalYSBZJAmount) {
        this.wgsTotalYSBZJAmount = wgsTotalYSBZJAmount;
    }

    public BigDecimal getWgsTotalSSBZJMDNum() {
        return wgsTotalSSBZJMDNum;
    }

    public void setWgsTotalSSBZJMDNum(BigDecimal wgsTotalSSBZJMDNum) {
        this.wgsTotalSSBZJMDNum = wgsTotalSSBZJMDNum;
    }

    public BigDecimal getWgsTotalSSBZJAmount() {
        return wgsTotalSSBZJAmount;
    }

    public void setWgsTotalSSBZJAmount(BigDecimal wgsTotalSSBZJAmount) {
        this.wgsTotalSSBZJAmount = wgsTotalSSBZJAmount;
    }

    public BigDecimal getWgsTotalWSBZJMDNum() {
        return wgsTotalWSBZJMDNum;
    }

    public void setWgsTotalWSBZJMDNum(BigDecimal wgsTotalWSBZJMDNum) {
        this.wgsTotalWSBZJMDNum = wgsTotalWSBZJMDNum;
    }

    public BigDecimal getWgsTotalWSBZJAmount() {
        return wgsTotalWSBZJAmount;
    }

    public void setWgsTotalWSBZJAmount(BigDecimal wgsTotalWSBZJAmount) {
        this.wgsTotalWSBZJAmount = wgsTotalWSBZJAmount;
    }

    public BigDecimal getQuarterDCMDNum() {
        return quarterDCMDNum;
    }

    public void setQuarterDCMDNum(BigDecimal quarterDCMDNum) {
        this.quarterDCMDNum = quarterDCMDNum;
    }

    public BigDecimal getQuarterDRMDNum() {
        return quarterDRMDNum;
    }

    public void setQuarterDRMDNum(BigDecimal quarterDRMDNum) {
        this.quarterDRMDNum = quarterDRMDNum;
    }

    public BigDecimal getQuarterQYWHMDNum() {
        return quarterQYWHMDNum;
    }

    public void setQuarterQYWHMDNum(BigDecimal quarterQYWHMDNum) {
        this.quarterQYWHMDNum = quarterQYWHMDNum;
    }

    public BigDecimal getQuarterFPWHMDNum() {
        return quarterFPWHMDNum;
    }

    public void setQuarterFPWHMDNum(BigDecimal quarterFPWHMDNum) {
        this.quarterFPWHMDNum = quarterFPWHMDNum;
    }

    public BigDecimal getChRate() {
        return chRate;
    }

    public void setChRate(BigDecimal chRate) {
        this.chRate = chRate;
    }

    public BigDecimal getDayFPGJMDNum() {
        return dayFPGJMDNum;
    }

    public void setDayFPGJMDNum(BigDecimal dayFPGJMDNum) {
        this.dayFPGJMDNum = dayFPGJMDNum;
    }

    public BigDecimal getDayFPGJCSNum() {
        return dayFPGJCSNum;
    }

    public void setDayFPGJCSNum(BigDecimal dayFPGJCSNum) {
        this.dayFPGJCSNum = dayFPGJCSNum;
    }

    public BigDecimal getDayWFPGJMDNum() {
        return dayWFPGJMDNum;
    }

    public void setDayWFPGJMDNum(BigDecimal dayWFPGJMDNum) {
        this.dayWFPGJMDNum = dayWFPGJMDNum;
    }

    public BigDecimal getDayWFPGJCSNum() {
        return dayWFPGJCSNum;
    }

    public void setDayWFPGJCSNum(BigDecimal dayWFPGJCSNum) {
        this.dayWFPGJCSNum = dayWFPGJCSNum;
    }

    public BigDecimal getWeekFPGJMDNum() {
        return weekFPGJMDNum;
    }

    public void setWeekFPGJMDNum(BigDecimal weekFPGJMDNum) {
        this.weekFPGJMDNum = weekFPGJMDNum;
    }

    public BigDecimal getWeekFPGJCSNum() {
        return weekFPGJCSNum;
    }

    public void setWeekFPGJCSNum(BigDecimal weekFPGJCSNum) {
        this.weekFPGJCSNum = weekFPGJCSNum;
    }

    public BigDecimal getWeekWFPGJMDNum() {
        return weekWFPGJMDNum;
    }

    public void setWeekWFPGJMDNum(BigDecimal weekWFPGJMDNum) {
        this.weekWFPGJMDNum = weekWFPGJMDNum;
    }

    public BigDecimal getWeekWFPGJCSNum() {
        return weekWFPGJCSNum;
    }

    public void setWeekWFPGJCSNum(BigDecimal weekWFPGJCSNum) {
        this.weekWFPGJCSNum = weekWFPGJCSNum;
    }

    public BigDecimal getMonthFPGJMDNum() {
        return monthFPGJMDNum;
    }

    public void setMonthFPGJMDNum(BigDecimal monthFPGJMDNum) {
        this.monthFPGJMDNum = monthFPGJMDNum;
    }

    public BigDecimal getMonthFPGJCSNum() {
        return monthFPGJCSNum;
    }

    public void setMonthFPGJCSNum(BigDecimal monthFPGJCSNum) {
        this.monthFPGJCSNum = monthFPGJCSNum;
    }

    public BigDecimal getMonthWFPGJMDNum() {
        return monthWFPGJMDNum;
    }

    public void setMonthWFPGJMDNum(BigDecimal monthWFPGJMDNum) {
        this.monthWFPGJMDNum = monthWFPGJMDNum;
    }

    public BigDecimal getMonthWFPGJCSNum() {
        return monthWFPGJCSNum;
    }

    public void setMonthWFPGJCSNum(BigDecimal monthWFPGJCSNum) {
        this.monthWFPGJCSNum = monthWFPGJCSNum;
    }

    public BigDecimal getQuarterFPGJMDNum() {
        return quarterFPGJMDNum;
    }

    public void setQuarterFPGJMDNum(BigDecimal quarterFPGJMDNum) {
        this.quarterFPGJMDNum = quarterFPGJMDNum;
    }

    public BigDecimal getQuarterFPGJCSNum() {
        return quarterFPGJCSNum;
    }

    public void setQuarterFPGJCSNum(BigDecimal quarterFPGJCSNum) {
        this.quarterFPGJCSNum = quarterFPGJCSNum;
    }

    public BigDecimal getQuarterWFPGJMDNum() {
        return quarterWFPGJMDNum;
    }

    public void setQuarterWFPGJMDNum(BigDecimal quarterWFPGJMDNum) {
        this.quarterWFPGJMDNum = quarterWFPGJMDNum;
    }

    public BigDecimal getQuarterWFPGJCSNum() {
        return quarterWFPGJCSNum;
    }

    public void setQuarterWFPGJCSNum(BigDecimal quarterWFPGJCSNum) {
        this.quarterWFPGJCSNum = quarterWFPGJCSNum;
    }

    public BigDecimal getYearFPGJMDNum() {
        return yearFPGJMDNum;
    }

    public void setYearFPGJMDNum(BigDecimal yearFPGJMDNum) {
        this.yearFPGJMDNum = yearFPGJMDNum;
    }

    public BigDecimal getYearFPGJCSNum() {
        return yearFPGJCSNum;
    }

    public void setYearFPGJCSNum(BigDecimal yearFPGJCSNum) {
        this.yearFPGJCSNum = yearFPGJCSNum;
    }

    public BigDecimal getYearWFPGJMDNum() {
        return yearWFPGJMDNum;
    }

    public void setYearWFPGJMDNum(BigDecimal yearWFPGJMDNum) {
        this.yearWFPGJMDNum = yearWFPGJMDNum;
    }

    public BigDecimal getYearWFPGJCSNum() {
        return yearWFPGJCSNum;
    }

    public void setYearWFPGJCSNum(BigDecimal yearWFPGJCSNum) {
        this.yearWFPGJCSNum = yearWFPGJCSNum;
    }
}
