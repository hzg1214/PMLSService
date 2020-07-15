package cn.com.eju.deal.store.model;

import java.util.Date;

public class ConcernedStore {
    private Integer concernedId;//关注编号

    private Integer userId;//用户编号

    private Integer storeId;//门店编号

    private Date dateCreate;//创建时间

    private Boolean isDelete;//删除标记（是否关注）

    /**
     * 关注编号
     * @return the concernedId
     */
    public Integer getConcernedId()
    {
        return concernedId;
    }

    /**
     * 关注编号
     * @param concernedId the concernedId to set
     */
    public void setConcernedId(Integer concernedId)
    {
        this.concernedId = concernedId;
    }

    /**
     * 用户编号
     * @return the userId
     */
    public Integer getUserId()
    {
        return userId;
    }

    /**
     * 用户编号
     * @param userId the userId to set
     */
    public void setUserId(Integer userId)
    {
        this.userId = userId;
    }

    /**
     * 门店编号
     * @return the storeId
     */
    public Integer getStoreId()
    {
        return storeId;
    }

    /**
     * 门店编号
     * @param storeId the storeId to set
     */
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }

    /**
     * 创建时间
     * @return the dateCreate
     */
    public Date getDateCreate()
    {
        return dateCreate;
    }

    /**
     * 创建时间
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    /**
     * 删除标记
     * @return the isDelete
     */
    public Boolean getIsDelete()
    {
        return isDelete;
    }

    /**
     * 删除标记
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }

    
}