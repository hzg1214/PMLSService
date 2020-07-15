package cn.com.eju.deal.mapper.storeAudit;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.model.sweepStreets.StoreAuditRecordDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.store.model.StoreAuthCheckDto;
import cn.com.eju.deal.store.model.StoreBizStop;
import cn.com.eju.deal.store.model.StoreStopCancel;

/**
 * Created by xu on 2017/4/13.
 */
public interface StoreAuditMapper {
    List<StoreNewDto> getStoreList(Map<?, ?> queryParam) throws Exception;
    List<StoreNewDto> getStoreAuthCheckListData(Map<?, ?> queryParam) throws Exception;
    List<StoreNewDto> getStoreBizStopListData(Map<?, ?> queryParam) throws Exception;
    List<StoreNewDto> getStoreStopCancelListData(Map<?, ?> queryParam) throws Exception;
    List<StoreAuditRecordDto> getStoreAuditRecordList(Map<?, ?> queryParam) throws Exception;
    StoreNewDto getStoreById(StoreNewDto dto);

    StoreAuthCheckDto getStoreAuthCheckById(Map<String, Object> queryParam);

    StoreBizStop getStoreBizStopById(Map<String, Object> queryParam);

    StoreStopCancel getStoreStopCancelById(Map<String, Object> queryParam);
}
