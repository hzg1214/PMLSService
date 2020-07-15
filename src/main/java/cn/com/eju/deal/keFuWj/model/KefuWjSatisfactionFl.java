package cn.com.eju.deal.keFuWj.model;

import java.math.BigDecimal;
import java.util.Date;

public class KefuWjSatisfactionFl {
    private Integer id;

    private Integer satisfactionId;

    private String wjflCode;

    private BigDecimal wjflScore;

    private Integer userCreate;

    private Date dateCreate;

    private Boolean delFlag;

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

    public String getWjflCode() {
        return wjflCode;
    }

    public void setWjflCode(String wjflCode) {
        this.wjflCode = wjflCode == null ? null : wjflCode.trim();
    }

    public BigDecimal getWjflScore() {
        return wjflScore;
    }

    public void setWjflScore(BigDecimal wjflScore) {
        this.wjflScore = wjflScore;
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