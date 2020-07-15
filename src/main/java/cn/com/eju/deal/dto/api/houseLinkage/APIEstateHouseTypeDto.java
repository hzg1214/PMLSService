package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;

/** 
* @ClassName: APIEstateHouseTypeDto 
* @Description: 梯户
* @author lxd 
* @date 2016年5月16日 下午2:13:57 
*  
*/
public class APIEstateHouseTypeDto  implements Serializable
{
    
 /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 6444889583451343743L;
// 户
    private String households;
    // 梯
    private String stairs;
    /**
     * @return the households
     */
    public String getHouseholds()
    {
        return households;
    }
    /**
     * @param households the households to set
     */
    public void setHouseholds(String households)
    {
        this.households = households;
    }
    /**
     * @return the stairs
     */
    public String getStairs()
    {
        return stairs;
    }
    /**
     * @param stairs the stairs to set
     */
    public void setStairs(String stairs)
    {
        this.stairs = stairs;
    }
    
    
}
