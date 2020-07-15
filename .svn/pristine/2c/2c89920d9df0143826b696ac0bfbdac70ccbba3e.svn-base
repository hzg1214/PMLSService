package cn.com.eju.deal.mapper.maintainStore;

import cn.com.eju.deal.model.sweepStreets.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/4/13.
 */
public interface MaintainStoreMapper {
    List<StoreNewDto> getStoreListData(StoreNewDto dto) throws Exception;
    List<MaintainerInfoDto> checkMaintainerStatus() throws Exception;
    void addPushInfo(WXPushInfoDto dto)throws Exception;
    void updateMaintainerCenterData() throws Exception;

    List<Map<String,Object>> getOverdueNotFollowStore()throws Exception;
}
