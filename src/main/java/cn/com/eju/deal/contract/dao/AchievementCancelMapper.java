package cn.com.eju.deal.contract.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.contract.AchievementCancelDto;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDto;

/**
 * 
* 门店撤销Mapper
* @author wushuang
* @date 2016年10月12日 下午5:45:56
 */
public interface AchievementCancelMapper extends IDao<AchievementCancel> {
    /**
     * 
    * 门店撤销查询门店数据
    * @param contractId
    * @return
    * @throws Exception
     */
    List<AchievementCancelDto> getAchievementCancelInfo(Integer contractId) throws Exception;
    /**
     * 
    * 获取门店业绩撤销记录    
    * @param contractId
    * @return
     */
    List<AchievementCancelDto> getAchievementCancelRecord(Integer contractId) throws Exception;
    
    /**
     * 
    * 根据合同ID和门店撤销编号获取-查看-所需详细信息
    * @param queryParam
    * @return
     */
    AchievementCancelDto getInfoToView(Map<String, Object> queryParam) throws Exception;
    
    /**
     * 
    * 获取被撤销门店信息
    * @param queryParam
    * @return
    * @throws Exception
     */
    List<StoreDto> getStoreInfoToView(Map<String, Object> queryParam) throws Exception;
    
    /**
     * 
    * 获取门店撤销申请撤销所需的[页面]信息【OA专用】
    * @param queryParam
    * @return
     */
    AchievementCancelDto getContractInfo(Map<String, Object> queryParam) throws Exception;
  
    /**
     * 
    * 获取门店撤销申请撤销所需的[门店]信息【OA专用】
    * @param storeIds
    * @return
     */
    List<StoreDto> getStoreInfo(Map<String, Object> reqMap) throws Exception;
    
    /**
     * 
    * 插入门店撤销记录
    * @param achievementCancel
    * @return
    * @throws Exception
     */
    int createNewCancelRecord(AchievementCancel achievementCancel) throws Exception;
    
    /**
     * 
    * OA审核后调用接口-修改门店撤销审核状态
    * @param queryParam
    * @return
    * @throws Exception
     */
    int updateCancelState(Map<String, Object> queryParam)  throws Exception;
    
    /**
     * 
    * 更新门店撤销记录
    * @param achievementCancel
    * @return
     */
    int updateCancelRecord(AchievementCancel achievementCancel);
    
    /**
     * 
    * 根据NO查询信息
    * @param queryParam
    * @return
     */
    AchievementCancel getAchievementCancelByNo(Map<String, Object> queryParam);
    
    /**
     * 
    * 根据审核状态查询flowId集合【OA定时任务拉取数据用】
    * @param queryParam
    * @return
     */
    List<AchievementCancel> getCancelByapproveState(Map<String, Object> queryParam);
 	
    //Add cning 2017/07/04 Start
    /**
     * 
    * 获取被撤销的门店信息
    * @param contractId
    * @return
     */
    List<AchievementCancel> getCancelStore(Map<String, Object> queryParam) throws Exception;
    //Add cning 2017/07/04 End
    
    /**
     * 查询门店装修记录
     * @param queryParam
     * @return
     * @throws Exception
     */
    List<StoreDecorationDto> getStoreDecorationList(Map<String, Object> queryParam)throws Exception;

    void updateStoreIsCancel(String achievementCancelNo);
}