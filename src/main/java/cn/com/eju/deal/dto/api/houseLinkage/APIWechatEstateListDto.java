package cn.com.eju.deal.dto.api.houseLinkage;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.util.StringUtil;

import java.io.Serializable;
import java.math.BigDecimal;

/** 
* @ClassName: APIWechatEstateListDto 
* @Description: 供房友新房联动公众号调取的楼盘列表对象
* @author lxd 
* @date 2016年5月19日 下午2:01:21 
*  
*/
public class APIWechatEstateListDto implements Serializable
{
    
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = -4295980578847081014L;
    
    private Integer id;//自增编号
    
    private String estateId;//楼盘编号
    
    private String estateNm;// 楼盘名
    
    private String mgtKbn;//住房（物业）类型
    
    private String mgtKbnStr;//住房（物业）类型
    
    private String districtNo; // 区域
    
    private String districtNm; // 区域名称
    
    private String awardTypeList;//奖励
    
    private Integer commissionKbn;// 佣金方式
    
    private BigDecimal commission;//佣金
    
    private String commissionStr;//佣金
    
    private BigDecimal commissionPeriod;// 结佣期限
    
    private BigDecimal salePriceUnitMin;//总价段--起始值
    
    private BigDecimal salePriceUnitMax;//总价段--最大值
    
    private String coverImgPath;//封面图片路径

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the estateId
     */
    public String getEstateId()
    {
        return estateId;
    }

    /**
     * @param estateId the estateId to set
     */
    public void setEstateId(String estateId)
    {
        this.estateId = estateId;
    }

    /**
     * @return the estateNm
     */
    public String getEstateNm()
    {
        return estateNm;
    }

    /**
     * @param estateNm the estateNm to set
     */
    public void setEstateNm(String estateNm)
    {
        this.estateNm = estateNm;
    }

    /**
     * @return the mgtKbn
     */
    public String getMgtKbn()
    {
        return mgtKbn;
    }

    /**
     * @param mgtKbn the mgtKbn to set
     */
    public void setMgtKbn(String mgtKbn)
    {
        this.mgtKbn = mgtKbn;
    }

    /**
     * @return the mgtKbnStr
     */
    public String getMgtKbnStr()
    {
        if (StringUtil.isNotEmpty(mgtKbn)) {
            String[] typeArr = mgtKbn.split(",");
            for (String type : typeArr) {
                if (StringUtil.isEmpty(mgtKbnStr)) {
                    mgtKbnStr = SystemParam.getDicValueByDicCode(type);
                } else {
                    mgtKbnStr = mgtKbnStr + "," + SystemParam.getDicValueByDicCode(type);
                }
            }
        }
        return mgtKbnStr;
    }

    /**
     * @param mgtKbnStr the mgtKbnStr to set
     */
    public void setMgtKbnStr(String mgtKbnStr)
    {
        this.mgtKbnStr = mgtKbnStr;
    }

    /**
     * @return the districtNo
     */
    public String getDistrictNo()
    {
        return districtNo;
    }

    /**
     * @param districtNo the districtNo to set
     */
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }

    /**
     * @return the districtNm
     */
    public String getDistrictNm()
    {
        districtNm=SystemParam.getDistrictNameByDistrictNo(districtNo);
        return districtNm;
    }

    /**
     * @param districtNm the districtNm to set
     */
    public void setDistrictNm(String districtNm)
    {
        this.districtNm = districtNm;
    }

    

    /**
     * @return the awardTypeList
     */
    public String getAwardTypeList()
    {
        return awardTypeList;
    }

    /**
     * @param awardTypeList the awardTypeList to set
     */
    public void setAwardTypeList(String awardTypeList)
    {
        this.awardTypeList = awardTypeList;
    }

    /**
     * @return the commissionKbn
     */
    public Integer getCommissionKbn()
    {
        return commissionKbn;
    }

    /**
     * @param commissionKbn the commissionKbn to set
     */
    public void setCommissionKbn(Integer commissionKbn)
    {
        this.commissionKbn = commissionKbn;
    }

    /**
     * @return the commission
     */
    public BigDecimal getCommission()
    {
        return commission;
    }

    /**
     * @param commission the commission to set
     */
    public void setCommission(BigDecimal commission)
    {
        this.commission = commission;
    }

    /**
     * @return the commissionStr
     */
    public String getCommissionStr()
    {
       /* if (null != commission)
        {
            if (commissionKbn == DictionaryConstants.COMMISSION_YUAN)
            {
                commissionStr = commission.toString() + "元";
            }
            else if (commissionKbn == DictionaryConstants.COMMISSION_PERCENTAGE)
            {
                commissionStr=commission.toString() + "%";
            }
        }*/
        return commissionStr;
    }

    /**
     * @param commissionStr the commissionStr to set
     */
    public void setCommissionStr(String commissionStr)
    {
        this.commissionStr = commissionStr;
    }

    /**
     * @return the commissionPeriod
     */
    public BigDecimal getCommissionPeriod()
    {
        return commissionPeriod;
    }

    /**
     * @param commissionPeriod the commissionPeriod to set
     */
    public void setCommissionPeriod(BigDecimal commissionPeriod)
    {
        this.commissionPeriod = commissionPeriod;
    }

    /**
     * @return the salePriceUnitMin
     */
    public BigDecimal getSalePriceUnitMin()
    {
        return salePriceUnitMin;
    }

    /**
     * @param salePriceUnitMin the salePriceUnitMin to set
     */
    public void setSalePriceUnitMin(BigDecimal salePriceUnitMin)
    {
        this.salePriceUnitMin = salePriceUnitMin;
    }

    /**
     * @return the salePriceUnitMax
     */
    public BigDecimal getSalePriceUnitMax()
    {
        return salePriceUnitMax;
    }

    /**
     * @param salePriceUnitMax the salePriceUnitMax to set
     */
    public void setSalePriceUnitMax(BigDecimal salePriceUnitMax)
    {
        this.salePriceUnitMax = salePriceUnitMax;
    }

    /**
     * @return the coverImgPath
     */
    public String getCoverImgPath()
    {
        return coverImgPath;
    }

    /**
     * @param coverImgPath the coverImgPath to set
     */
    public void setCoverImgPath(String coverImgPath)
    {
        this.coverImgPath = coverImgPath;
    }
    
    
}
