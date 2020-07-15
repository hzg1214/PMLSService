package cn.com.eju.pmls.sceneTrade.dto;

import java.util.Date;

public class CustomVaildDet {

    private String estateNm;

    private String progressNm;

    private String confirmStatusNm;

    private String companyNm;

    private Date bizOperDate;

    public String getEstateNm() {
        return estateNm;
    }

    public void setEstateNm(String estateNm) {
        this.estateNm = estateNm;
    }

    public String getProgressNm() {
        return progressNm;
    }

    public void setProgressNm(String progressNm) {
        this.progressNm = progressNm;
    }

    public String getConfirmStatusNm() {
        return confirmStatusNm;
    }

    public void setConfirmStatusNm(String confirmStatusNm) {
        this.confirmStatusNm = confirmStatusNm;
    }

    public String getCompanyNm() {
        return companyNm;
    }

    public void setCompanyNm(String companyNm) {
        this.companyNm = companyNm;
    }

    public Date getBizOperDate() {
        return bizOperDate;
    }

    public void setBizOperDate(Date bizOperDate) {
        this.bizOperDate = bizOperDate;
    }
}
