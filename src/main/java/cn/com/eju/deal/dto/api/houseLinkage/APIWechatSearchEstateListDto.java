package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;

/** 
* @ClassName: APIWechatSearchEstateListDto 
* @Description: 房友新房联动公众号查询条件对象
* @author 陆海丹 
* @date 2016年5月19日 下午3:23:53 
*  
*/
public class APIWechatSearchEstateListDto implements Serializable
{
    
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */
    private static final long serialVersionUID = -5790351682333026179L;
    
    private String cityNo;
    
    private String districtNo;
    
    private Integer mgtKbn;
    
    private String searchContent;
    
    /**
     * @return the cityNo
     */
    public String getCityNo()
    {
        return cityNo;
    }
    
    /**
     * @param cityNo the cityNo to set
     */
    public void setCityNo(String cityNo)
    {
        this.cityNo = cityNo;
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
     * @return the mgtKbn
     */
    public Integer getMgtKbn()
    {
        return mgtKbn;
    }
    
    /**
     * @param mgtKbn the mgtKbn to set
     */
    public void setMgtKbn(Integer mgtKbn)
    {
        this.mgtKbn = mgtKbn;
    }
    
    /**
     * @return the searchContent
     */
    public String getSearchContent()
    {
        return searchContent;
    }
    
    /**
     * @param searchContent the searchContent to set
     */
    public void setSearchContent(String searchContent)
    {
        this.searchContent = searchContent;
    }
    
}
