package cn.com.eju.deal.store.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.store.dao.StoreMaintainerMapper;
import cn.com.eju.deal.store.dao.StoreMaintenanceMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.model.StoreMaintainer;
import cn.com.eju.deal.store.model.StoreMaintenanceDto;

@Service("storeMaintenanceService")
public class StoreMaintenanceService {
	 @Resource
	 private StoreMaintenanceMapper storeMaintenanceMapper;
	 

	 @Resource
	 private StoreMaintainerMapper storeMaintainerMapper;
	 @Resource
	 private StoreMapper storeMapper;
	 public ResultData getStoreMaintenanceList(Map<String,Object> reqMap)throws Exception{
	        ResultData resultData = new ResultData();
	        resultData.setFail();

	        List<StoreMaintenanceDto> storeMaintenanceList =  storeMaintenanceMapper.getStoreMaintenanceList(reqMap);
	        if(storeMaintenanceList !=null && storeMaintenanceList.size()>0){
	        	 resultData.setReturnData(storeMaintenanceList);
		         resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
		         resultData.setSuccess();
	        }
	        return resultData;
	    }
	public ResultData saveMaintenance(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();

        String ids = (String)reqMap.get("ids");
        if(StringUtil.isEmpty(ids)){
            resultData.setFail("请至少选择一条报备!");
            return resultData;
        }
        String selectCenterId = (String)reqMap.get("selectCenterId");
        if(StringUtil.isEmpty(selectCenterId)){
            resultData.setFail("请选择一个业绩归属人！");
            return resultData;
        }
        String userCode = (String)reqMap.get("userCode");
        String userName = (String)reqMap.get("userName");
        Integer createUserId =(Integer)reqMap.get("createUserId");
        String createUserCode = (String)reqMap.get("createUserCode");  
        String createUserName = (String)reqMap.get("createUserName");
        String idArray[] = ids.split(",");
       // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for(int i = 0,length = idArray.length;i < length;i++){
            	Store store = new Store();
            	store.setStoreId(Integer.parseInt(idArray[i]));
            	store.setMaintainer(userCode);
            	store.setMaintainerName(userName);
            	store.setUserUpdate(Integer.parseInt(createUserCode));
            	store.setDateUpdate(new Date());
                storeMapper.update(store);
                
                StoreMaintainer storeMaintainer =new StoreMaintainer();
                storeMaintainer.setStoreId(Integer.parseInt(idArray[i]));
                storeMaintainer.setUserCode(userCode);
                storeMaintainer.setUserIdCreate(createUserId);
                storeMaintainer.setDelFlag("N");
                storeMaintainer.setDateCreate(new Date());
                storeMaintainer.setSetUserCode(createUserCode);
                storeMaintainer.setSetUserName(createUserName);
                storeMaintainer.setDateMtcStart(new Date());
                storeMaintainerMapper.insert(storeMaintainer);
                
            }
            resultData.setSuccess("修改成功！");
        return resultData;
    }
}
  