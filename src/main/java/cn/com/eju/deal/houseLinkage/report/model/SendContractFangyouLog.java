package cn.com.eju.deal.houseLinkage.report.model;

import java.util.Date;

/**
 * desc:推送合同信息到房友日志记录
 * @author :zhenggang.Huang
 * @date   :2019年2月20日
 */
public class SendContractFangyouLog {
    private Integer id;

    private String typeId;

    private String reqParamJson;

    private String rspParamJson;

    private Date dateCreate;

    private Integer userIdCreate;

    private String delFlag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getReqParamJson() {
        return reqParamJson;
    }

    public void setReqParamJson(String reqParamJson) {
        this.reqParamJson = reqParamJson == null ? null : reqParamJson.trim();
    }

    public String getRspParamJson() {
        return rspParamJson;
    }

    public void setRspParamJson(String rspParamJson) {
        this.rspParamJson = rspParamJson == null ? null : rspParamJson.trim();
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag == null ? null : delFlag.trim();
    }
}