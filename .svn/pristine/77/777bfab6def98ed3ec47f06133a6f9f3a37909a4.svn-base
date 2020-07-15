package cn.com.eju.deal.keFuWj.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.keFuWj.service.KeFuWjEvaluationService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by haidan on 2019/9/24.
 */
@RestController
@RequestMapping("keFuWjEvaluation")
public class KeFuWjEvaluationController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private KeFuWjEvaluationService keFuWjEvaluationService;

    @RequestMapping(value = "/getWjEvaluationList/{param}", method = RequestMethod.GET)
    public String getWjEvaluationList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = keFuWjEvaluationService.getWjEvaluationList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("KeFuWjAnalyse", "KeFuWjEvaluationController", "getWjEvaluationList", reqMap.toString(), null, "", "查询门店测评列表异常", e);
        }
        return resultData.toString();
    }

}
