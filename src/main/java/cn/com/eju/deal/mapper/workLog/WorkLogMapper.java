package cn.com.eju.deal.mapper.workLog;

import cn.com.eju.deal.model.workLog.UserInfoNewDto;
import cn.com.eju.deal.model.workLog.WorkLogDto;

import java.util.List;

/**
 * Created by xu on 2017/5/10.
 */
public interface WorkLogMapper {

    List<WorkLogDto> queryCenterStoreList(WorkLogDto dto);
    List<WorkLogDto> queryCenterMaintainerList(WorkLogDto dto);
    List<WorkLogDto> queryMaintainerStoreList(WorkLogDto dto);
    UserInfoNewDto getUserInfo(WorkLogDto dto);
}
