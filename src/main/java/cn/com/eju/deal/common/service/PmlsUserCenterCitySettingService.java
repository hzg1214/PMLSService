package cn.com.eju.deal.common.service;

import cn.com.eju.deal.common.dao.PmlsUserCenterCitySettingMapper;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("pmlsUserCenterCitySettingService")
public class PmlsUserCenterCitySettingService {

    @Resource
    private PmlsUserCenterCitySettingMapper pmlsUserCenterCitySettingMapper;

    public ResultData<List<Map<String, Object>>> queryHblRegionList(Map<String, Object> param) throws Exception {

        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        List<Map<String, Object>> list = pmlsUserCenterCitySettingMapper.queryHblRegionList(param);
        resultData.setReturnData(list);
        return resultData;
    }


    public ResultData<List<Map<String, Object>>> queryHblAreaCityList(Map<String, Object> param) throws Exception {

        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        List<Map<String, Object>> list = pmlsUserCenterCitySettingMapper.queryHblAreaCityList(param);
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<Map<String, Object>>> queryHblCityList(Map<String, Object> param) throws Exception {

        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        List<Map<String, Object>> list = pmlsUserCenterCitySettingMapper.queryHblCityList(param);
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<Map<String, Object>>> queryHblCenterList(Map<String, Object> param) throws Exception {

        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        List<Map<String, Object>> list = pmlsUserCenterCitySettingMapper.queryHblCenterList(param);
        resultData.setReturnData(list);
        return resultData;
    }
}
