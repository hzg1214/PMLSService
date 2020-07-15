package cn.com.eju.deal.dto.common;

/** 
* @ClassName: AreaDto 
* @Description: 板块--接口传输通用类
* @author 陆海丹 
* @date 2016年3月28日 上午11:13:34 
*  
*/
public class AreaDto extends DistrictDto
{
    private String areaNo;//板块编号
    private String areaName;//板块名称
    /**
     * 板块编号
     * @return the areaNo
     */
    public String getAreaNo()
    {
        return areaNo;
    }
    /**
     * 板块编号
     * @param areaNo the areaNo to set
     */
    public void setAreaNo(String areaNo)
    {
        this.areaNo = areaNo;
    }
    /**
     * 板块名称
     * @return the areaName
     */
    public String getAreaName()
    {
        return areaName;
    }
    /**
     * 板块名称
     * @param areaName the areaName to set
     */
    public void setAreaName(String areaName)
    {
        this.areaName = areaName;
    }
    
}
