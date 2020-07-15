package cn.com.eju.deal.dto.store;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

/**
 * 门店装修Dto
 * @author  wushuang
 * @date 2016年8月16日 下午6:55:40
 */
public class StoreDecorationDto implements Serializable
{
    /**
    * 随机编号
    */
    private static final long serialVersionUID = 1L;
    
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
    
    private String companyName;//合同中公司名称
    
    private Integer contractType;//合同类型
    
    private Integer userCode;//创建人工号
    
    private String userName;//创建人名称
    
    private String contractTypeName;//合同类型名称
    
    private String decorationStatusName;//门店装修状态名称
    
    private String storeName;//门店名称
    
    private String storeAddress;//门店地址
    
    private Integer upToDateDecorationStatus;//最新的门店装修状态
    
    private Integer contractStatus;//合同状态
    
    private String cityNo;//门店地址
    
    private String dateLifeStartStr;//生效时间String
    
    private String dateLifeEndStr;//结束时间String
    
    private String agreementNo;//协议书编号
    
    private Integer paymentStatus;//装修-付款状态 
    
    private String paymentStatusName;//装修-付款状态名称 
    /**
     * OA申请单中介 
     */
    private String oafilpagency;
    
    
    private Integer decorationType;//装修-装修类型（ 17701：自费装修，17702：我司装修）
    
  //ADD By GUOPENGFEI 2017/04/07 start
    private Integer OriginalContractdistinction; //18601:新签；18602：续签
//ADD By GUOPENGFEI 2017/04/07 end
    
  //ADD By QJP 2017/08/02 start
      private Integer oaMdfpStatus;    //门店装修申请OA审批状态
  //ADD By QJP 2017/08/02 end
    
    public Integer getOaMdfpStatus() {
		return oaMdfpStatus;
	}

	public void setOaMdfpStatus(Integer oaMdfpStatus) {
		this.oaMdfpStatus = oaMdfpStatus;
	}

	/**
     * @return the decorationType
     */
    public Integer getDecorationType()
    {
        return decorationType;
    }

    /**
     * @param decorationType the decorationType to set
     */
    public void setDecorationType(Integer decorationType)
    {
        this.decorationType = decorationType;
    }

    public String getOafilpagency() {
		return oafilpagency;
	}

	public void setOafilpagency(String oafilpagency) {
		this.oafilpagency = oafilpagency;
	}

	public String getDateLifeStartStr() {
		return dateLifeStartStr;
	}

	public void setDateLifeStartStr(String dateLifeStartStr) {
		this.dateLifeStartStr = dateLifeStartStr;
	}

	public String getDateLifeEndStr() {
		return dateLifeEndStr;
	}

	public void setDateLifeEndStr(String dateLifeEndStr) {
		this.dateLifeEndStr = dateLifeEndStr;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getCityNo()
    {
        return cityNo;
    }

    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
    }

    public Integer getContractStatus()
    {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus)
    {
        this.contractStatus = contractStatus;
    }

    public Integer getUpToDateDecorationStatus()
    {
        return upToDateDecorationStatus;
    }

    public void setUpToDateDecorationStatus(Integer upToDateDecorationStatus)
    {
        this.upToDateDecorationStatus = upToDateDecorationStatus;
    }

    public String getStoreName()
    {
        return storeName;
    }

    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }

    public String getStoreAddress()
    {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress)
    {
        this.storeAddress = storeAddress;
    }

    public String getContractTypeName()
    {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName)
    {
        this.contractTypeName = contractTypeName;
    }

    public String getDecorationStatusName()
    {
        return SystemParam.getDicValueByDicCode(this.decorationStatus + "");
    }

    public void setDecorationStatusName(String decorationStatusName)
    {
        this.decorationStatusName = decorationStatusName;
    }

    public String getCompanyName()
    {
        return companyName;
    }

    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    public Integer getContractType()
    {
        return contractType;
    }

    public void setContractType(Integer contractType)
    {
        this.contractType = contractType;
    }

    public Integer getUserCode()
    {
        return userCode;
    }

    public void setUserCode(Integer userCode)
    {
        this.userCode = userCode;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

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

	public String getPaymentStatusName() {
		return paymentStatusName;
	}

	public void setPaymentStatusName(String paymentStatusName) {
		this.paymentStatusName = paymentStatusName;
	}

	public Integer getOriginalContractdistinction() {
		return OriginalContractdistinction;
	}

	public void setOriginalContractdistinction(Integer originalContractdistinction) {
		OriginalContractdistinction = originalContractdistinction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


    // 城市No
    private String acCityNo;
    
	public String getAcCityNo() {
		return acCityNo;
	}

	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}
}
