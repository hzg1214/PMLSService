package cn.com.eju.deal.dto.store;

import java.io.Serializable;

import cn.com.eju.deal.store.model.ConcernedStore;

/** 
* @ClassName: ConcernDto 
* @Description: 关注
* @author 陆海丹 
* @date 2016年4月5日 下午3:46:27 
*  
*/
public class ConcernDto extends ConcernedStore implements Serializable
{
    /**
    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = -2766453027741290131L;
    private StoreDto storeDto;//门店信息
    
    private Boolean isConcerned;//是否被关注

    /**
     * @return the storeDto
     */
    public StoreDto getStoreDto()
    {
        return storeDto;
    }

    /**
     * @param storeDto the storeDto to set
     */
    public void setStoreDto(StoreDto storeDto)
    {
        this.storeDto = storeDto;
    }

    /**
     * @return the isConcerned
     */
    public Boolean getIsConcerned()
    {
        return isConcerned;
    }

    /**
     * @param isConcerned the isConcerned to set
     */
    public void setIsConcerned(Boolean isConcerned)
    {
        this.isConcerned = isConcerned;
    }
    
    
}
