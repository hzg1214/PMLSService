package cn.com.eju.deal.keFuWj.model;

import java.math.BigDecimal;
import java.util.Date;

public class KefuWjSatisfaction {
    private Integer id;

    private String dcNo;

    private String storeNo;

    private String wjCode;

    private Date wjdcdate;

    private String wjdcjd;

    private String wjdcStatus;

    private String storeStatus;

    private String dcObjectName;

    private String dcObjectTel;

    private String dzStatus;

    private String bjqStatus;

    private String ktbStatus;

    private String otherRemark;

    private String comments;

    private Integer userCreate;

    private Date dateCreate;

    private Boolean delFlag;

    private Date wjfkdate;

    private String wjfkTel;

    private BigDecimal wjdcTotalscore;

    private String acCityNo;

    private BigDecimal cpTotalScore;

    public String getAcCityNo() {
        return acCityNo;
    }

    public void setAcCityNo(String acCityNo) {
        this.acCityNo = acCityNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDcNo() {
        return dcNo;
    }

    public void setDcNo(String dcNo) {
        this.dcNo = dcNo == null ? null : dcNo.trim();
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo == null ? null : storeNo.trim();
    }

    public String getWjCode() {
        return wjCode;
    }

    public void setWjCode(String wjCode) {
        this.wjCode = wjCode == null ? null : wjCode.trim();
    }

    public Date getWjdcdate() {
        return wjdcdate;
    }

    public void setWjdcdate(Date wjdcdate) {
        this.wjdcdate = wjdcdate;
    }

    public String getWjdcjd() {
        return wjdcjd;
    }

    public void setWjdcjd(String wjdcjd) {
        this.wjdcjd = wjdcjd == null ? null : wjdcjd.trim();
    }

    public String getWjdcStatus() {
        return wjdcStatus;
    }

    public void setWjdcStatus(String wjdcStatus) {
        this.wjdcStatus = wjdcStatus == null ? null : wjdcStatus.trim();
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus == null ? null : storeStatus.trim();
    }

    public String getDcObjectName() {
        return dcObjectName;
    }

    public void setDcObjectName(String dcObjectName) {
        this.dcObjectName = dcObjectName == null ? null : dcObjectName.trim();
    }

    public String getDcObjectTel() {
        return dcObjectTel;
    }

    public void setDcObjectTel(String dcObjectTel) {
        this.dcObjectTel = dcObjectTel == null ? null : dcObjectTel.trim();
    }

    public String getDzStatus() {
        return dzStatus;
    }

    public void setDzStatus(String dzStatus) {
        this.dzStatus = dzStatus == null ? null : dzStatus.trim();
    }

    public String getBjqStatus() {
        return bjqStatus;
    }

    public void setBjqStatus(String bjqStatus) {
        this.bjqStatus = bjqStatus == null ? null : bjqStatus.trim();
    }

    public String getKtbStatus() {
        return ktbStatus;
    }

    public void setKtbStatus(String ktbStatus) {
        this.ktbStatus = ktbStatus == null ? null : ktbStatus.trim();
    }

    public String getOtherRemark() {
        return otherRemark;
    }

    public void setOtherRemark(String otherRemark) {
        this.otherRemark = otherRemark;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments == null ? null : comments.trim();
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public Date getWjfkdate() {
        return wjfkdate;
    }

    public void setWjfkdate(Date wjfkdate) {
        this.wjfkdate = wjfkdate;
    }

    public String getWjfkTel() {
        return wjfkTel;
    }

    public void setWjfkTel(String wjfkTel) {
        this.wjfkTel = wjfkTel == null ? null : wjfkTel.trim();
    }

    public BigDecimal getWjdcTotalscore() {
        return wjdcTotalscore;
    }

    public void setWjdcTotalscore(BigDecimal wjdcTotalscore) {
        this.wjdcTotalscore = wjdcTotalscore;
    }

    public BigDecimal getCpTotalScore() {
        return cpTotalScore;
    }

    public void setCpTotalScore(BigDecimal cpTotalScore) {
        this.cpTotalScore = cpTotalScore;
    }
}