package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: APISearchEstateListDto 
* @Description: 楼盘列表查询对象
* @author 陆海丹 
* @date 2016年5月16日 下午6:29:11 
*  
*/
public class APISearchEstateListDto  implements Serializable
{

    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 5967444082203551236L;
    
    private String CityNo;
    
    private String SaleStatus;
    
    private String District;
    
    private String Area;
    
    private String RoomCount;
    
    private String AvgPriceMin;
    
    private String AvgPriceMax;
    
    private String BuildType;
    
    private String AwardType;
    
    private String SearchContent;
    
    private List<Map<?, ?>> OrderBy;

    /**
     * @return the cityNo
     */
    public String getCityNo()
    {
        return CityNo;
    }

    /**
     * @param cityNo the cityNo to set
     */
    public void setCityNo(String cityNo)
    {
        CityNo = cityNo;
    }

    /**
     * @return the saleStatus
     */
    public String getSaleStatus()
    {
        return SaleStatus;
    }

    /**
     * @param saleStatus the saleStatus to set
     */
    public void setSaleStatus(String saleStatus)
    {
        SaleStatus = saleStatus;
    }

    /**
     * @return the district
     */
    public String getDistrict()
    {
        return District;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district)
    {
        District = district;
    }

    /**
     * @return the area
     */
    public String getArea()
    {
        return Area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area)
    {
        Area = area;
    }

    /**
     * @return the roomCount
     */
    public String getRoomCount()
    {
        return RoomCount;
    }

    /**
     * @param roomCount the roomCount to set
     */
    public void setRoomCount(String roomCount)
    {
        RoomCount = roomCount;
    }

    /**
     * @return the avgPriceMin
     */
    public String getAvgPriceMin()
    {
        return AvgPriceMin;
    }

    /**
     * @param avgPriceMin the avgPriceMin to set
     */
    public void setAvgPriceMin(String avgPriceMin)
    {
        AvgPriceMin = avgPriceMin;
    }

    /**
     * @return the avgPriceMax
     */
    public String getAvgPriceMax()
    {
        return AvgPriceMax;
    }

    /**
     * @param avgPriceMax the avgPriceMax to set
     */
    public void setAvgPriceMax(String avgPriceMax)
    {
        AvgPriceMax = avgPriceMax;
    }

    /**
     * @return the buildType
     */
    public String getBuildType()
    {
        return BuildType;
    }

    /**
     * @param buildType the buildType to set
     */
    public void setBuildType(String buildType)
    {
        BuildType = buildType;
    }

    /**
     * @return the awardType
     */
    public String getAwardType()
    {
        return AwardType;
    }

    /**
     * @param awardType the awardType to set
     */
    public void setAwardType(String awardType)
    {
        AwardType = awardType;
    }

    /**
     * @return the searchContent
     */
    public String getSearchContent()
    {
        return SearchContent;
    }

    /**
     * @param searchContent the searchContent to set
     */
    public void setSearchContent(String searchContent)
    {
        SearchContent = searchContent;
    }

    /**
     * @return the orderBy
     */
    public List<Map<?, ?>> getOrderBy()
    {
        return OrderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(List<Map<?, ?>> orderBy)
    {
        OrderBy = orderBy;
    }
    
    
}
