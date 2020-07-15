package cn.com.eju.deal.linkageWechat.model;

import java.util.Date;

/**
 * Created by Sky on 2016/4/20.
 * 渠道Token对象
 */
public class ChannelToken {

    /**
     * 主键
     */
    private Integer id;

    /**
     * token类型
     */
    private Integer tokenType;

    /**
     * 创建时间
     */
    private Date dateCreate;

    /**
     * 过期时间
     */
    private Date dateExpiration;

    /**
     * token
     */
    private String token;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTokenType() {
        return tokenType;
    }

    public void setTokenType(Integer tokenType) {
        this.tokenType = tokenType;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
}
