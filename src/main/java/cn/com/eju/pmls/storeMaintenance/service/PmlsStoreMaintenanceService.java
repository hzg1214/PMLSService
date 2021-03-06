package cn.com.eju.pmls.storeMaintenance.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StoreMaintainerDto;
import cn.com.eju.deal.store.dao.StoreMaintainerMapper;
import cn.com.eju.deal.store.model.StoreMaintainer;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.pmls.storeMaintenance.dao.PmlsStoreMaintenanceMapper;

@Service("pmlsStoreMaintenanceService")
public class PmlsStoreMaintenanceService extends BaseService {
	
	private final LogHelper logger = LogHelper.getLogger(this.getClass());
	
    @Resource
    private PmlsStoreMaintenanceMapper pmlsStoreMaintenanceMapper;
    
    @Resource
    private StoreMaintainerMapper storeMaintainerMapper;
    
    @Resource(name = "storeService")
    private StoreService storeService;
    
    /**
     * desc:列表
     * 2020年7月10日
     */
    public ResultData queryList(Map<String, Object> queryParam) throws Exception{
    	//构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        List<Map<String,Object>> lstStoreDtos = pmlsStoreMaintenanceMapper.queryList(queryParam);
        if (null != lstStoreDtos && !lstStoreDtos.isEmpty()){
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(lstStoreDtos);
        }
        return resultData;
    }
    
    /**
     * desc:批量变更维护人
     * 2020年7月8日
     */
    public ResultData<?> batchChangeMaintenance(Map<String, Object> queryParam) throws Exception{
    	
        ResultData<StoreMaintainer> resultData=new ResultData<>();
        
        // 新增维护人
        StoreMaintainerDto insertMaintainer = new StoreMaintainerDto();
        StoreMaintainer storeMaintainer = new StoreMaintainer();
        insertMaintainer.setUserCode(queryParam.get("maintainer").toString().trim());
        insertMaintainer.setDateCreate(new Date());
        insertMaintainer.setDelFlag("N");
        insertMaintainer.setUserIdCreate(Integer.valueOf(queryParam.get("userId").toString()));
        insertMaintainer.setSetUserCode(queryParam.get("userCode").toString().trim());
        insertMaintainer.setSetUserName(queryParam.get("userName").toString().trim());
        insertMaintainer.setDateMtcStart(new Date());
        BeanUtils.copyProperties(insertMaintainer, storeMaintainer);
        
        //更新门店维护人
        StoreDto storeDto = new StoreDto();
        storeDto.setPmlsMaintainCode(queryParam.get("maintainer").toString().trim());
        storeDto.setPmlsMaintainName(queryParam.get("maintainerName").toString().trim());
    	storeDto.setCityNo(queryParam.get("cityNo").toString());
    	storeDto.setUserUpdate(Integer.valueOf(queryParam.get("userId").toString()));
        storeDto.setIsUpdateCompanyFlag(0);
        storeDto.setPmlsCenterId(Integer.valueOf(queryParam.get("pmlsCenterId").toString()));
        
        //storeId   24012,24011,24010
        String storeIds = queryParam.get("ids").toString();
        if(!StringUtils.isEmpty(storeIds)) {
        	String[] ids = storeIds.split(",");
            for (String id : ids) {
                storeMaintainer.setStoreId(Integer.valueOf(id));
                Integer count=storeMaintainerMapper.insertNew(storeMaintainer);
                logger.info("门店维护-批量变更维护人，新增维护人返回标志count:"+count+";门店id:"+id);
                storeDto.setStoreId(Integer.valueOf(id));
                if(count>0) {
                	storeService.updateMtcToStore(storeDto);
                	logger.info("门店维护-批量变更维护人，更新门店维护人,入参id:"+id+",pmlsCenterId:"+queryParam.get("pmlsCenterId"));
                }
            }
        }else {
        	resultData.setFail();
        	resultData.setReturnMsg("请至少选择一个需要门店维护的记录");
        	return resultData;
        }
        
        resultData.setSuccess();
        resultData.setReturnCode(ReturnCode.SUCCESS);
        resultData.setReturnMsg("批量变更维护人成功");
        return resultData;
    }
    
}
