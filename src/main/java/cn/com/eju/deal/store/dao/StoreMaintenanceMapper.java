package cn.com.eju.deal.store.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.store.model.StoreMaintenanceDto;


public interface StoreMaintenanceMapper {
	List<StoreMaintenanceDto> getStoreMaintenanceList(Map<String,Object> reqMap);
}
  