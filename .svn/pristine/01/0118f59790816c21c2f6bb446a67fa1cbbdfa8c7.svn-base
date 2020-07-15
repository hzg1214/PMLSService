package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.store.model.StoreDecoration;

/**
 * 门店装修DAO
 * @author  wushuang
 * @date 2016年8月16日 下午6:44:48
 */
public interface StoreDecorationMapper {

    /**
     * 
        * @Title: getStoreDecoration
        * @Description:查询门店装历史信息用于显示 
        * @return
        * @throws Exception
     */
    List<StoreDecorationDto> getStoreDecoration(Map<String, Object> reqMap) throws Exception;

   /**
    * 
       * @Title: insertBatch
       * @Description: 用于批量插入门店装修信息
       * @return
       * @throws Exception
    */
    int insertBatch(List<StoreDecoration> insertList) throws Exception;
    /**
     * 
        * @Title: addDecorationRecord
        * @Description: 用于新增装修记录
        * @return
        * @throws Exception
     */

    int addDecorationRecord(StoreDecorationDto storeDecorationDto) throws Exception;
    
    /**
     * 
        * @Title: UpdateDecorationRecord
        * @Description: OMS调用更新门店装修表
        * @return
        * @throws Exception
     */
    int updateDecorationRecord(Map<String, Object> reqMap) throws Exception;
    
    
    /**
     * @Title: updateStoreDecorationPayStatus
     * @Description: OMS调用更新门店装修 装修付款状态
     * @return
     * @throws Exception
     */
    int updateStoreDecorationPayStatus(Map<String, Object> reqMap) throws Exception;

    /**
     * 
        * @Title: UpdateDecorationRecord
        * @Description: OMS调用更新门店装修类型
        * @return
        * @throws Exception
     */
    int updateDecorationType(Map<String, Object> reqMap) throws Exception;
    
    /**
     * 【描述】: 
     *
     * @author yinkun
     * @date: 2018年3月8日 上午11:24:59
     * @param reqMap
     * @return
     * @throws Exception
     */
    int updateDecorationForNew(Map<String, Object> reqMap) throws Exception;

}