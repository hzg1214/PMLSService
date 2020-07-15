package cn.com.eju.deal.keFuWj.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.keFuWj.dao.KefuWjDTmMapper;
import cn.com.eju.deal.keFuWj.dao.KefuWjHMapper;
import cn.com.eju.deal.keFuWj.dao.KefuWjSatisfactionMapper;
import cn.com.eju.deal.keFuWj.dto.KeFuWjAnalyseDto;
import cn.com.eju.deal.keFuWj.model.KefuWjSatisfaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/7/12.
 */

@Service("keFuWjAnalyseService")
public class KeFuWjAnalyseService extends BaseService {

    @Resource
    private KefuWjSatisfactionMapper kefuWjSatisfactionMapper;
    @Resource
    private KefuWjDTmMapper kefuWjDTmMapper;

    public List<Map<String,Object>> queryWjNumber(Map<String, Object> reqMap) {
        return kefuWjSatisfactionMapper.queryWjNumber(reqMap);
    }

    public ResultData getWjAnalyseList(Map<String, Object> reqMap) {
        ResultData<List<KeFuWjAnalyseDto>> resultData = new ResultData<List<KeFuWjAnalyseDto>>();
        resultData.setFail();
        List<KeFuWjAnalyseDto> keFuOrderDtoList = kefuWjSatisfactionMapper.getWjAnalyseList(reqMap);
        resultData.setReturnData(keFuOrderDtoList);
        resultData.setSuccess();
        return resultData;
    }

    public List<Map<String,Object>> queryWjFlListByCode(String wjCode) {
        return kefuWjSatisfactionMapper.queryWjFlListByCode(wjCode);
    }

    public List<Map<String,Object>> queryWjTMListByCode(String wjCode) {
        return kefuWjDTmMapper.queryWjTMListByCode(wjCode);
    }
}
