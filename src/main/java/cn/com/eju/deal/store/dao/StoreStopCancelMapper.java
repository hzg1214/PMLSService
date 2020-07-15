package cn.com.eju.deal.store.dao;

import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.store.model.StoreStopCancel;
import cn.com.eju.deal.store.model.StoreStopCancelLog;

import java.util.List;
import java.util.Map;

public interface StoreStopCancelMapper extends IDao<StoreStopCancel> {
    int create(StoreStopCancel storeStopCancel);

    List<FileRecordMainDto> queryFileList(Integer id);

    Map<String,Object> getLastestContract(Integer storeId);

    int deleteStoreBizStop(Integer storeId);

    AchievementCancel getAchievementCancel(Map<String,Object> queryParam);

    int deleteAchievementCancel(Integer id);

    int insertLog(StoreStopCancelLog log);
}
