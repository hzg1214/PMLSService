package cn.com.eju.deal.store.service;


import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.CenterPositionDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.dao.StoreMapMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("storeMapService")
public class StoreMapService extends BaseService {

    @Autowired
    private StoreMapMapper storeMapMapper;

    public ResultData<List<StoreDto>> getMapInfo(Map<String, Object> queryParam) {
        ResultData<List<StoreDto>> resultData = new ResultData<>();
        List<StoreDto> list = storeMapMapper.getMapInfo(queryParam);
        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData getStoreCount(Map<String, Object> queryParam) {
        ResultData resultData = new ResultData<>();
        List<CenterPositionDto> list = storeMapMapper.getStoreCount(queryParam);
        resultData.setReturnData(list);
        return resultData;
    }
    public ResultData getCenterPosition(Map<String, Object> queryParam) {
        ResultData resultData = new ResultData<>();
        List<CenterPositionDto> list = storeMapMapper.getCenterPosition(queryParam);
        resultData.setReturnData(list);
        return resultData;
    }
}
