package cn.com.eju.pmls.sceneTrade.service;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.sceneTrade.dao.SceneTradeMapper;
import cn.com.eju.pmls.sceneTrade.dto.CustomVaildDet;
import cn.com.eju.pmls.sceneTrade.dto.CustomVaildLeg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("sceneTradeService")
public class SceneTradeService {

    @Autowired
    SceneTradeMapper sceneTradeMapper;

    public ResultData<List<CustomVaildLeg>> getCustomLegList(Map<String, Object> queryParam) {

        ResultData<List<CustomVaildLeg>> resultData = new ResultData<List<CustomVaildLeg>>();
        List<CustomVaildLeg> list = sceneTradeMapper.getCustomLegList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<CustomVaildDet>> getCustomDetList(Map<String, Object> queryParam) {

        ResultData<List<CustomVaildDet>> resultData = new ResultData<List<CustomVaildDet>>();
        List<CustomVaildDet> list = sceneTradeMapper.getCustomDetList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

}
