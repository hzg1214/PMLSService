package cn.com.eju.deal.controller.satisfactionSurvey;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.statisfactionSurvey.SurveyDto;
import cn.com.eju.deal.service.satisfactionSurvey.SatisfactionSurveyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by haidan on 2019/6/25.
 */
@RestController
@RequestMapping("satisfactionSurvey")
public class SatisfactionSurveyController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private SatisfactionSurveyService satisfactionSurveyService;

    /**
     * 判断是否可以进行满意度调查
     *
     * @param storeNo
     * @return
     */
    @RequestMapping(value = "/checkSurvey/{storeNo}", method = RequestMethod.GET)
    public String checkSurvey(@PathVariable String storeNo) {
        //构建返回
        ResultData resultData = null;
        try {
            resultData = this.satisfactionSurveyService.checkSurvey(storeNo);
        } catch (Exception e) {
            resultData = new ResultData();
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "checkSurvey", "", null, "", "判断是否可以进行满意度调查", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getStoreData/{id}", method = RequestMethod.GET)
    public String getStoreData(@PathVariable Integer id) {
        //构建返回
        ResultData resultData = null;
        try {
            resultData = this.satisfactionSurveyService.getStoreData(id);
        } catch (Exception e) {
            resultData = new ResultData();
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "getStoreData", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getSurveyData/{id}", method = RequestMethod.GET)
    public String getSurveyData(@PathVariable Integer id) {
        //构建返回
        ResultData resultData = null;
        try {
            resultData = this.satisfactionSurveyService.getSurveyData(id);
        } catch (Exception e) {
            resultData = new ResultData();
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "getSurveyData", "", null, "", "获取问卷详情", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
    public String saveSurvey(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        Map<String, Object> reqMap = new HashMap<>();
        try {
            reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
            resultData = satisfactionSurveyService.saveSurvey(reqMap);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "saveSurvey", "", null, "", "新增门店问卷调查", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/savePushInfo", method = RequestMethod.POST)
    public String savePushInfo(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        Map<String, Object> reqMap = new HashMap<>();
        try {
            reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
            resultData = satisfactionSurveyService.savePushInfo(reqMap);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("satisfactionSurvey", "followMapController", "savePushInfo", "", null, "", "验证码", e);
        }
        return resultData.toString();
    }



    @RequestMapping(value = "/getSurveyDetail/{wjCode}", method = RequestMethod.GET)
    public String getSurveyDetail(@PathVariable String wjCode){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.satisfactionSurveyService.getSurveyDetailByCode(wjCode);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "getSurveyDetailByCode", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/updateSurvey", method = RequestMethod.POST)
    public String updateSurvey(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            SurveyDto dto = JsonUtil.parseToObject(jsonDto, SurveyDto.class);
            resultData = satisfactionSurveyService.updateSurvey(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "updateSurvey", "", null, "", "提交问卷", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/querySatisfaction/{id}", method = RequestMethod.GET)
    public String querySatisfaction(@PathVariable Integer id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.satisfactionSurveyService.querySatisfaction(id);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("satisfactionSurvey", "SatisfactionSurveyController", "querySatisfaction", "", null, "", "", e);
        }
        return resultData.toString();
    }
}
