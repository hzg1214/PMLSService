package cn.com.eju.deal.keFuWj.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.keFuWj.dao.KefuWjSatisfactionMapper;
import cn.com.eju.deal.keFuWj.dto.KeFuWjEvaluationDto;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/9/24.
 */
@Service("keFuWjEvaluationService")
public class KeFuWjEvaluationService {
    @Resource
    private KefuWjSatisfactionMapper kefuWjSatisfactionMapper;

    public ResultData getWjEvaluationList(Map<String, Object> reqMap) {
        ResultData<List<KeFuWjEvaluationDto>> resultData = new ResultData<>();
        resultData.setFail();
        List<KeFuWjEvaluationDto> list = kefuWjSatisfactionMapper.getWjEvaluationList(reqMap);
        if (!CollectionUtils.isEmpty(list)) {
            resultData.setReturnData(list);
            resultData.setSuccess();
        }
        return resultData;
    }
}
