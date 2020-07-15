package cn.com.eju.deal.mapper.sweepStreets;

import cn.com.eju.deal.model.sweepStreets.StoreLocationAuditDto;

import java.util.Map;

/**
 * 门店位置变更审批
 */
public interface StoreLocationAuditMapper {

    /**
     * 根据中心ID 获取中心负责人信息
     * @param centerId
     * @return
     * @throws Exception
     */
    Map<String,Object> getCentralDirectorByCenterId(Integer centerId) throws Exception;

    /**
     * 根据门店ID 获取门口位置变更审批记录 并且在审批中的
     * @param storeId
     * @return
     * @throws Exception
     */
    StoreLocationAuditDto getStoreLocationAuditByStoreIdAndAudit(Integer storeId) throws Exception;

    /**
     * 根据ID 获取门店位置审批记录信息
     * @param Id
     * @return
     * @throws Exception
     */
    StoreLocationAuditDto getStoreLocationAuditById(Integer Id) throws Exception;

    /**
     * 创建门店位置变更审批记录
     * @param dto
     * @return
     * @throws Exception
     */
    int addStoreLocationAudit(StoreLocationAuditDto dto) throws Exception;

    /**
     * 审批通过 门店位置变更审批
     * @param dto
     * @return
     * @throws Exception
     */
    int updateStoreLocationAuditAndAudit(StoreLocationAuditDto dto) throws Exception;

    /**
     * 审批通过 修改门店位置信息
     * @param dto
     * @return
     * @throws Exception
     */
    int updateStoreLocation(StoreLocationAuditDto dto) throws Exception;


}