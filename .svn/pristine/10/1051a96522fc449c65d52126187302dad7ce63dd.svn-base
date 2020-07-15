package cn.com.eju.deal.mapper.followMap;


import cn.com.eju.deal.model.followMap.StoreStopAuditDto;

import java.util.List;
import java.util.Map;

/**
 * 门店停业上报审批
 */
public interface StoreStopAuditMapper {

    /**
     * 根据中心ID 获取中心负责人信息
     * @param centerId
     * @return
     * @throws Exception
     */
    Map<String,Object> getCentralDirectorByCenterId(Integer centerId) throws Exception;


    /**
     * 根据门店ID 获取门口停业上报审批记录 并且在审批中的
     * @param storeId
     * @return
     * @throws Exception
     */
    StoreStopAuditDto getStoreStopAuditByStoreId(Integer storeId) throws Exception;
    //获取门店信息
    StoreStopAuditDto getStoreInfoById(Integer storeId) throws Exception;
    //获取逾期未审核的停业上报列表
    List<StoreStopAuditDto> getNotStoreStopAuditList() throws Exception;

    /**
     * 根据ID 获取门店停业上报审批记录信息
     * @param Id
     * @return
     * @throws Exception
     */
    StoreStopAuditDto getStoreStopAuditById(Integer Id) throws Exception;

    /**
     * 创建门店停业上报审批记录
     * @param dto
     * @return
     * @throws Exception
     */
    int addStoreStopAudit(StoreStopAuditDto dto) throws Exception;

    /**
     * 审批通过 门店停业上报审批
     * @param dto
     * @return
     * @throws Exception
     */
    int updateStoreStopAudit(StoreStopAuditDto dto) throws Exception;

    /**
     * 审批通过 修改门店停业状态
     * @param dto
     * @return
     * @throws Exception
     */
    int updateStoreStopStatus(StoreStopAuditDto dto) throws Exception;


}