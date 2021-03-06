package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.store.model.StoreMaintainer;

/**   
* 门店维护人关系Dao
* @author 张文辉
* @date 2016年6月7日 下午6:05:56
*/
public interface StoreMaintainerMapper {
    /**
     * 新增门店维护人历史
     */
    public int insert(StoreMaintainer record) throws Exception;
    
    public int insertNew(StoreMaintainer record) throws Exception;
    
    /**
     * 查询门店维护人历史
     */
    public List<StoreMaintainer> getStoreMaintainerHisList(Map<?, ?> param) throws Exception;
  //Add 2017.04/10 cning --->
    public List<StoreMaintainer> getStoreMaintainerHisListNew(Map<?, ?> param) throws Exception;
    /**
     * 查询门店维护人历史
     */
    public StoreMaintainer getByStoreId(int storeId) throws Exception;
    //Add 2017.04/10 cning <---
    
    /**
     * 查询门店是否有维护人
     */
    public Boolean hasMtner(Integer storeId) throws Exception;
}