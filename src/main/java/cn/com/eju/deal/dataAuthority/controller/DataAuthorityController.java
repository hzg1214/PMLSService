package cn.com.eju.deal.dataAuthority.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dataAuthority.model.CityRelation;
import cn.com.eju.deal.dataAuthority.model.DataAuthority;
import cn.com.eju.deal.dataAuthority.service.DataAuthorityService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "dataAuthority")
public class DataAuthorityController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    DataAuthorityService dataAuthorityService;
    /**
     * @Title: getEstateRuleByEstateId
     * @Description: 根据楼盘编号获取联动规则
     * @param param
     * @return
     */
    @RequestMapping(value = "/dataAuthorityList/{param}/", method = RequestMethod.GET)
    public String getEstateRuleByEstateId(@PathVariable("param") String param)
    {
        //构建返回
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<DataAuthority>> resultData = new ResultData<>();
        try
        {
            resultData = this.dataAuthorityService.getDataAuthorityList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("dataAuthority", "DataAuthorityController", "dataAuthorityList", "", null, "", "数据列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getQyInfo/{param}/", method = RequestMethod.GET)
    public String getQyInfo(@PathVariable("param") String param)
    {
        //构建返回
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<CityRelation>> resultDataQy = new ResultData<>();

        try
        {
            resultDataQy = this.dataAuthorityService.getQyInfo(queryParam);

        }
        catch (Exception e)
        {
            resultDataQy.setFail();
            logger.error("dataAuthority", "DataAuthorityController", "getQyInfo", "", null, "", "数据列表", e);
        }
        return resultDataQy.toString();
    }




    @RequestMapping(value = "/saveData", method = RequestMethod.POST)
    public ResultData saveData(@RequestBody String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = dataAuthorityService.saveData(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("保存异常！");
            }
            logger.error("CRM", "DataAuthorityController", "saveData", reqMap.toString(), null, "", "保存异常", e);

        }
        return resultData;
    }


    @RequestMapping(value = "/deleteData", method = RequestMethod.POST)
    public ResultData deleteData(@RequestBody String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = dataAuthorityService.deleteData(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("删除异常！");
            }
            logger.error("CRM", "DataAuthorityController", "deleteData", reqMap.toString(), null, "", "删除异常", e);

        }
        return resultData;
    }
}

