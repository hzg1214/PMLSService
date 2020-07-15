package cn.com.eju.deal.keFuWj.model;

import java.util.Date;

public class KefuWjSatisfactionEvaluation {
    private Integer id;

    private Integer satisfactionId;

    private Integer cpTitle;

    private String cpTitleStr;

    private String cpAnswer;

    private String cpAnswerStr;

    private Integer cpScore;

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

    public Integer getCpTitle() {
        return cpTitle;
    }

    public void setCpTitle(Integer cpTitle) {
        this.cpTitle = cpTitle;
    }

    public String getCpTitleStr() {
        return cpTitleStr;
    }

    public void setCpTitleStr(String cpTitleStr) {
        this.cpTitleStr = cpTitleStr == null ? null : cpTitleStr.trim();
    }

    public String getCpAnswer() {
        return cpAnswer;
    }

    public void setCpAnswer(String cpAnswer) {
        this.cpAnswer = cpAnswer == null ? null : cpAnswer.trim();
    }

    public String getCpAnswerStr() {
        return cpAnswerStr;
    }

    public void setCpAnswerStr(String cpAnswerStr) {
        this.cpAnswerStr = cpAnswerStr == null ? null : cpAnswerStr.trim();
    }

    public Integer getCpScore() {
        return cpScore;
    }

    public void setCpScore(Integer cpScore) {
        this.cpScore = cpScore;
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