package cn.com.eju.deal.houseLinkage.totalPackage.model;

import java.util.Date;

/**
 * Created by haidan on 2018/11/27.
 */
public class TotalPackageLog {
    private Integer id;
    private Integer referId;
    private String referTable;
    private String reqParamJson;
    private String rspParamJson;
    private String sourceSys;
    private Date dateCreate;
    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReferId() {
        return referId;
    }

    public void setReferId(Integer referId) {
        this.referId = referId;
    }

    public String getReferTable() {
        return referTable;
    }

    public void setReferTable(String referTable) {
        this.referTable = referTable;
    }

    public String getReqParamJson() {
        return reqParamJson;
    }

    public void setReqParamJson(String reqParamJson) {
        this.reqParamJson = reqParamJson;
    }

    public String getRspParamJson() {
        return rspParamJson;
    }

    public void setRspParamJson(String rspParamJson) {
        this.rspParamJson = rspParamJson;
    }

    public String getSourceSys() {
        return sourceSys;
    }

    public void setSourceSys(String sourceSys) {
        this.sourceSys = sourceSys;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
