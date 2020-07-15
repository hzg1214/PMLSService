package cn.com.eju.deal.contacts.model;

import java.math.BigDecimal;
import java.util.Date;

public class Contacts
{
    private Integer id;//编号
    
    private String contactsNo;//联系人编号
    
    private String name;//姓名
    
    private String mobilePhone;//手机号
    
    private String email;//邮箱
    
    private String remark;//备注
    
    private Integer sex;//性别
    
    private Integer companyId;//公司编号
    
    private Boolean isDefault;//是否默认联系人
    
    private Boolean isDelete;//是否删除
    
    private String cityNo;//城市编号
    
    private String districtNo;//区域编号
    
    private String areaNo;//板块编号
    
    private String address;//地址
    
    private String wechat;//微信号
    
    private String qQ;//QQ
    
    private Integer userCreate;//创建者
    
    private Date dateCreate;//创建时间
    
    //下边是拓展字段
    
    private String cityName;//城市
    
    private String districtName;//区域
    
    private String areaName;//板块
    
    private String companyName;//客户名（公司名）
    
    private Integer storeId;//门店Id
    
    private String userName;//创建人名称
    
    public String getUserName()
    {
        return userName;
    }

    /**
     * 所属门店名称
     */
    private String storeName;
    
    /**
     * 创建者名称
     */
    private String userCreateName;
    
    /**
     * 经度
     */
    private BigDecimal longitude;
    
    /**
     * 纬度
     */
    private BigDecimal latitude;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getContactsNo()
    {
        return contactsNo;
    }
    
    public void setContactsNo(String contactsNo)
    {
        this.contactsNo = contactsNo;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getMobilePhone()
    {
        return mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }
    
    public String getEmail()
    {
        return email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getRemark()
    {
        return remark;
    }
    
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    public Integer getSex()
    {
        return sex;
    }
    
    public void setSex(Integer sex)
    {
        this.sex = sex;
    }
    
    public Integer getCompanyId()
    {
        return companyId;
    }
    
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }
    
    public Boolean getIsDefault()
    {
        return isDefault;
    }
    
    public void setIsDefault(Boolean isDefault)
    {
        this.isDefault = isDefault;
    }
    
    public Boolean getIsDelete()
    {
        return isDelete;
    }
    
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }
    
    public String getCityNo()
    {
        return cityNo;
    }
    
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }
    
    public String getDistrictNo()
    {
        return districtNo;
    }
    
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }
    
    public String getAreaNo()
    {
        return areaNo;
    }
    
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }
    
    public String getAddress()
    {
        return address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getWechat()
    {
        return wechat;
    }
    
    public void setWechat(String wechat)
    {
        this.wechat = wechat;
    }
    
    public String getqQ()
    {
        return qQ;
    }
    
    public void setqQ(String qQ)
    {
        this.qQ = qQ;
    }
    
    //    public Integer getContactId()
    //    {
    //        return contactId;
    //    }
    //    
    //    public void setContactId(Integer contactId)
    //    {
    //        this.contactId = contactId;
    //    }
    
    public String getCityName()
    {
        return cityName;
    }
    
    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }
    
    public String getDistrictName()
    {
        return districtName;
    }
    
    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }
    
    public String getAreaName()
    {
        return areaName;
    }
    
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
    
    public String getCompanyName()
    {
        return companyName;
    }
    
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public Date getDateCreate()
    {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }

    public Integer getUserCreate()
    {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    
    /** 
    * 获取门店ID
    * @return storeId 门店ID
    */
    public Integer getStoreId()
    {
        return storeId;
    }
    
    /** 
    * 设置门店ID
    * @param storeId 门店ID
    */
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }

	/** 
	* 获取门店名称
	* @return storeName 门店名称
	*/
	public String getStoreName() {
		return storeName;
	}

	/** 
	* 设置门店名称
	* @param storeName 门店名称
	*/
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	/** 
	* 获取创建者名称
	* @return userCreateName 创建者名称
	*/
	public String getUserCreateName() {
		return userCreateName;
	}

	/** 
	* 设置创建者名称
	* @param userCreateName 创建者名称
	*/
	public void setUserCreateName(String userCreateName) {
		this.userCreateName = userCreateName;
	}

	/** 
	* 获取经度
	* @return longitude 经度
	*/
	public BigDecimal getLongitude() {
		return longitude;
	}

	/** 
	* 设置经度
	* @param longitude 经度
	*/
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	/** 
	* 获取纬度
	* @return latitude 纬度
	*/
	public BigDecimal getLatitude() {
		return latitude;
	}

	/** 
	* 设置纬度
	* @param latitude 纬度
	*/
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
   
    
}