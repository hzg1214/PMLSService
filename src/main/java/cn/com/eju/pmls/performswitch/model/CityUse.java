package cn.com.eju.pmls.performswitch.model;

import cn.com.eju.deal.core.model.BaseModel;

import java.io.Serializable;

/**
 * 城市实体
 */
public class CityUse extends BaseModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5192373775557629986L;
	
	/**
	 * 主键
	 */
	private Integer id;
	
	/**
	 * 城市编号
	 */
	private String cityNo;
	
	/**
	 * 城市名
	 */
	private String cityName;

    /**
     * OA 门店装修申请单模板
     */
	private String oaMdfpTemplateCode;
	/**
     * OA 结算公司名
     */
    private String oaEjuCompanyName;
    /**
     * OA 结算公司Id
     */
    private String oaEjuCompanyId;

	/**
	 * OA门店验收申请模板
	 */
	private String oaMdysTemplateCode;

	private String checkBodyId;

	private String checkBodyNm;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

    public String getOaMdfpTemplateCode()
    {
        return oaMdfpTemplateCode;
    }

    public void setOaMdfpTemplateCode(String oaMdfpTemplateCode)
    {
        this.oaMdfpTemplateCode = oaMdfpTemplateCode;
    }

    public String getOaEjuCompanyName()
    {
        return oaEjuCompanyName;
    }

    public void setOaEjuCompanyName(String oaEjuCompanyName)
    {
        this.oaEjuCompanyName = oaEjuCompanyName;
    }

    public String getOaEjuCompanyId()
    {
        return oaEjuCompanyId;
    }

    public void setOaEjuCompanyId(String oaEjuCompanyId)
    {
        this.oaEjuCompanyId = oaEjuCompanyId;
    }

	public String getOaMdysTemplateCode() {
		return oaMdysTemplateCode;
	}

	public void setOaMdysTemplateCode(String oaMdysTemplateCode) {
		this.oaMdysTemplateCode = oaMdysTemplateCode;
	}
	 //事业部编码
    private String bussineAreaCityConfig;
	public String getBussineAreaCityConfig() {
		return bussineAreaCityConfig;
	}
	public void setBussineAreaCityConfig(String bussineAreaCityConfig) {
		this.bussineAreaCityConfig = bussineAreaCityConfig;
	}

	public String getCheckBodyId() {
		return checkBodyId;
	}

	public void setCheckBodyId(String checkBodyId) {
		this.checkBodyId = checkBodyId;
	}

	public String getCheckBodyNm() {
		return checkBodyNm;
	}

	public void setCheckBodyNm(String checkBodyNm) {
		this.checkBodyNm = checkBodyNm;
	}
}
