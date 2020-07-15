package cn.com.eju.deal.dto.statisfactionSurvey;

import java.math.BigDecimal;

/**
 * Created by eju on 2019/6/28.
 */
public class SurveyDaDto {

    private Integer tmid;

    private String wjtmType;

    private String wjdaids;

    private String wjdacomments;

    private Integer directScore;

    private Integer wjtmMaxScore;

    private String wjtmflType;


    public Integer getWjtmMaxScore() {
        return wjtmMaxScore;
    }

    public void setWjtmMaxScore(Integer wjtmMaxScore) {
        this.wjtmMaxScore = wjtmMaxScore;
    }

    public Integer getDirectScore() {
        return directScore;
    }

    public void setDirectScore(Integer directScore) {
        this.directScore = directScore;
    }

    public String getWjtmflType() {
        return wjtmflType;
    }

    public void setWjtmflType(String wjtmflType) {
        this.wjtmflType = wjtmflType;
    }

    public String getWjtmType() {
        return wjtmType;
    }

    public void setWjtmType(String wjtmType) {
        this.wjtmType = wjtmType;
    }

    public String getWjdaids() {
        return wjdaids;
    }

    public void setWjdaids(String wjdaids) {
        this.wjdaids = wjdaids;
    }

    public String getWjdacomments() {
        return wjdacomments;
    }

    public void setWjdacomments(String wjdacomments) {
        this.wjdacomments = wjdacomments;
    }

    public Integer getTmid() {
        return tmid;
    }

    public void setTmid(Integer tmid) {
        this.tmid = tmid;
    }
}
