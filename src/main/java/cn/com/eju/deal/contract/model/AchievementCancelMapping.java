package cn.com.eju.deal.contract.model;


/**
 * 
* 门店业绩撤销Model
* @author wushuang
* @date 2016年10月20日 上午10:01:26
 */
public class AchievementCancelMapping {
    
    private Integer id;
    //业绩撤销Id
	private Integer achievementCancelId;
	// 门店Id
	private Integer storeId;
	// 合同Id
	private Integer contractId;
	// 是否删除
	private Boolean isDelete;
	
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    public Integer getAchievementCancelId()
    {
        return achievementCancelId;
    }
    public void setAchievementCancelId(Integer achievementCancelId)
    {
        this.achievementCancelId = achievementCancelId;
    }
    public Integer getStoreId()
    {
        return storeId;
    }
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }
    public Integer getContractId()
    {
        return contractId;
    }
    public void setContractId(Integer contractId)
    {
        this.contractId = contractId;
    }
    public Boolean getIsDelete()
    {
        return isDelete;
    }
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }
	
}