package cn.com.eju.deal.common.model;

/** 
* @ClassName: District 
* @Description: 区域
* @author 陆海丹 
* @date 2016年3月28日 上午9:35:57 
*  
*/
public class District extends City
{
    private String districtNo;//区域编号
    private String districtName;//区域名称
    /**
     * 区域编号
     * @return the districtNo
     */
    public String getDistrictNo()
    {
        return districtNo;
    }
    /**
     * 区域编号
     * @param districtNo the districtNo to set
     */
    public void setDistrictNo(String districtNo)
    {
        this.districtNo = districtNo;
    }
    /**
     * 区域名称
     * @return the districtName
     */
    public String getDistrictName()
    {
        return districtName;
    }
    /**
     * 区域名称
     * @param districtName the districtName to set
     */
    public void setDistrictName(String districtName)
    {
        this.districtName = districtName;
    }
    
}
