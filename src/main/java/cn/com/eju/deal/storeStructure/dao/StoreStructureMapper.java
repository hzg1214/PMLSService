package cn.com.eju.deal.storeStructure.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.storeStructure.StoreStructureDto;

import java.util.List;
import java.util.Map;

public interface StoreStructureMapper extends IDao<StoreStructureDto> {

    /**
     * 根据当期 查询门店结构报表
     * @param dto
     * @return
     */
    List<StoreStructureDto> queryStoreStructure(Map<?, ?> queryParam) throws Exception;

}