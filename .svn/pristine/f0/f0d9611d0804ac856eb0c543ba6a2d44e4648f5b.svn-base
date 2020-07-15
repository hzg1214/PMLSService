package cn.com.eju.deal.keFuWj.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.BaseUtils;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.keFuWj.service.KeFuWjAnalyseService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/7/12.
 */

@RestController
@RequestMapping("keFuWjAnalyse")
public class KeFuWjAnalyseController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private KeFuWjAnalyseService keFuWjAnalyseService;

    @RequestMapping(value = "/queryWjNumber/{param}", method = RequestMethod.GET)
    public String queryWjNumber(@PathVariable String param){
        ResultData resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        List<Map<String,Object>> list = null;
        try {
            list = keFuWjAnalyseService.queryWjNumber(reqMap);
        } catch (Exception e) {
            e.printStackTrace();
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("KeFuWjAnalyse", "KeFuWjAnalyseController", "queryWjNumber", reqMap.toString(), null, "", "查询问卷调查维护列表异常", e);
        }
        resultData.setReturnData(list);
        return resultData.toString();
    }

    @RequestMapping(value = "/getWjAnalyseList/{param}", method = RequestMethod.GET)
    public String getWjAnalyseList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            reqMap.put("wjTypeList", BaseUtils.changeArrayToList((String)reqMap.get("wjType"),","));
            resultData = keFuWjAnalyseService.getWjAnalyseList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("KeFuWjAnalyse", "KeFuWjAnalyseController", "getWjAnalyseList", reqMap.toString(), null, "", "查询客服反馈工单列表异常", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryWjFlListByCode/{wjCode}", method = RequestMethod.GET)
    public String queryWjFlListByCode(@PathVariable String wjCode){
        ResultData resultData = new ResultData<>();
        List<Map<String,Object>> list = null;
        try {
            list = keFuWjAnalyseService.queryWjFlListByCode(wjCode);
        } catch (Exception e) {
            e.printStackTrace();
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("KeFuWjAnalyse", "KeFuWjAnalyseController", "queryWjFlListByCode", wjCode, null, "", "查询问卷调查维护列表异常", e);
        }
        resultData.setReturnData(list);

        return resultData.toString();
    }

    @RequestMapping(value = "/queryWjTMListByCode/{wjCode}", method = RequestMethod.GET)
    public String queryWjTMListByCode(@PathVariable String wjCode){
        ResultData resultData = new ResultData<>();
        List<Map<String,Object>> list = null;
        try {
            list = keFuWjAnalyseService.queryWjTMListByCode(wjCode);
        } catch (Exception e) {
            e.printStackTrace();
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("KeFuWjAnalyse", "KeFuWjAnalyseController", "queryWjTMListByCode", wjCode, null, "", "查询问卷调查维护列表异常", e);
        }
        resultData.setReturnData(list);
        return resultData.toString();
    }

}
