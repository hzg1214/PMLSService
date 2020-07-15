package cn.com.eju.deal.store.model;

import java.util.Date;

public class StoreDecoration
{
    private Integer id;//id
    
    private Integer storeId;//门店Id
    
    private String decorationNo;//装修编号
    
    private String contractNo;//合同编号
    
    private String storeNo;//门店编号
    
    private Integer decorationStatus;//装修状态
    
    private Integer userCreate;//创建人
    
    private Date dateCreate;//创建日期
    
    private Integer updateUserId;//修改人
    
    private Date updateDate;//修改时间
    
    private Boolean delFlag;//删除标记
    
    private Integer paymentStatus;//装修-付款状态

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getStoreId()
    {
        return storeId;
    }

    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }

    public String getDecorationNo()
    {
        return decorationNo;
    }

    public void setDecorationNo(String decorationNo)
    {
        this.decorationNo = decorationNo;
    }

    public String getContractNo()
    {
        return contractNo;
    }

    public void setContractNo(String contractNo)
    {
        this.contractNo = contractNo;
    }

    public String getStoreNo()
    {
        return storeNo;
    }

    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }

    public Integer getDecorationStatus()
    {
        return decorationStatus;
    }

    public void setDecorationStatus(Integer decorationStatus)
    {
        this.decorationStatus = decorationStatus;
    }

    public Integer getUserCreate()
    {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }

    public Date getDateCreate()
    {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    public Integer getUpdateUserId()
    {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId)
    {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateDate()
    {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate)
    {
        this.updateDate = updateDate;
    }

    public Boolean getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag)
    {
        this.delFlag = delFlag;
    }

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
}