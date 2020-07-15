package cn.com.eju.deal.keFuWj.model;

import java.math.BigDecimal;
import java.util.Date;

public class KefuWjSatisfactionD {
    private Integer id;

    private Integer satisfactionId;

    private Integer wjtmid;

    private String wjdaids;

    private String wjdacomments;

    private Integer tmScore;

    private Integer userCreate;

    private Date dateCreate;

    private Boolean delFlag;

    private BigDecimal wjtmScore;

    public BigDecimal getWjtmScore() {
        return wjtmScore;
    }

    public void setWjtmScore(BigDecimal wjtmScore) {
        this.wjtmScore = wjtmScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSatisfactionId() {
        return satisfactionId;
    }

    public void setSatisfactionId(Integer satisfactionId) {
        this.satisfactionId = satisfactionId;
    }

    public Integer getWjtmid() {
        return wjtmid;
    }

    public void setWjtmid(Integer wjtmid) {
        this.wjtmid = wjtmid;
    }

    public String getWjdaids() {
        return wjdaids;
    }

    public void setWjdaids(String wjdaids) {
        this.wjdaids = wjdaids == null ? null : wjdaids.trim();
    }

    public String getWjdacomments() {
        return wjdacomments;
    }

    public void setWjdacomments(String wjdacomments) {
        this.wjdacomments = wjdacomments == null ? null : wjdacomments.trim();
    }

    public Integer getTmScore() {
        return tmScore;
    }

    public void setTmScore(Integer tmScore) {
        this.tmScore = tmScore;
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
}