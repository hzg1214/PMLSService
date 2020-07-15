package cn.com.eju.pmls.storeMaintenance.dao;

import java.util.List;
import java.util.Map;

public interface PmlsStoreMaintenanceMapper {
    //门店维护列表
    List<Map<String,Object>> queryList(Map<String, Object> queryParam);
}