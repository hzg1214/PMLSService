package cn.com.eju.deal.dto.statisfactionSurvey;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by eju on 2019/6/28.
 */
public class SurveyDto {

    private Integer hid;

    private Integer sId;

    private Integer allMaxScore;

    private Integer alldirectScore;

    private String daList;

    private String wjfkTel;

    public String getWjfkTel() {
        return wjfkTel;
    }

    public void setWjfkTel(String wjfkTel) {
        this.wjfkTel = wjfkTel;
    }

    public Integer getSId() {
        return sId;
    }

    public void setSId(Integer sId) {
        this.sId = sId;
    }

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public Integer getAllMaxScore() {
        return allMaxScore;
    }

    public void setAllMaxScore(Integer allMaxScore) {
        this.allMaxScore = allMaxScore;
    }

    public Integer getAlldirectScore() {
        return alldirectScore;
    }

    public void setAlldirectScore(Integer alldirectScore) {
        this.alldirectScore = alldirectScore;
    }

    public String getDaList() {
        return daList;
    }

    public void setDaList(String daList) {
        this.daList = daList;
    }
}
