package cn.com.eju.deal.Report.model;

import cn.com.eju.deal.core.model.BaseModel;


public class Templet  extends BaseModel {

	private Integer id;
	
	private String templetId;
	
	private String templetName;
	
	private String templetAddress;
	
	private Integer templetType;

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getTempletId()
    {
        return templetId;
    }

    public void setTempletId(String templetId)
    {
        this.templetId = templetId;
    }

    public String getTempletName()
    {
        return templetName;
    }

    public void setTempletName(String templetName)
    {
        this.templetName = templetName;
    }

    public String getTempletAddress()
    {
        return templetAddress;
    }

    public void setTempletAddress(String templetAddress)
    {
        this.templetAddress = templetAddress;
    }

    public Integer getTempletType()
    {
        return templetType;
    }

    public void setTempletType(Integer templetType)
    {
        this.templetType = templetType;
    }
	
	
}
