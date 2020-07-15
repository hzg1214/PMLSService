package cn.com.eju.pmls.borrowMoney.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.borrowMoney.model.BorrowMoneyDto;
import cn.com.eju.pmls.borrowMoney.model.HkPlanDto;
import cn.com.eju.pmls.borrowMoney.service.BorrowMoneyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("borrowMoneyController")
public class BorrowMoneyController {
    @Resource
    private BorrowMoneyService borrowMoneyService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    //获取借佣列表
    @RequestMapping(value = "/getBorrowMoneyList/{param}", method = RequestMethod.GET)
    public String getBorrowMoneyList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = borrowMoneyService.getBorrowMoneyList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BorrowMoneyDto", "BorrowMoneyController", "getBorrowMoneyList", "", null, "", "获取借佣列表", e);
        }
        return resultData.toString();
    }
    //获取借佣进度列表
    @RequestMapping(value = "/getBorrowMoneyProgressList/{param}", method = RequestMethod.GET)
    public String getBorrowMoneyProgressList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = borrowMoneyService.getBorrowMoneyProgressList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BorrowMoneyDto", "BorrowMoneyController", "getBorrowMoneyProgressList", "", null, "", "获取借佣进度列表", e);
        }
        return resultData.toString();
    }

    //获取借佣明细列表
    @RequestMapping(value = "/getBorrowMoneyDetailList/{param}", method = RequestMethod.GET)
    public String getBorrowMoneyDetailList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = borrowMoneyService.getBorrowMoneyDetailList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BorrowMoneyDto", "BorrowMoneyController", "getBorrowMoneyDetailList", "", null, "", "获取借佣明细列表", e);
        }
        return resultData.toString();
    }

    //获取借佣详情信息
    @RequestMapping(value = "/getBorrowMoneyInfo", method = RequestMethod.POST)
    public String getBorrowMoneyInfo(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            BorrowMoneyDto dto = JsonUtil.parseToObject(json, BorrowMoneyDto.class);
            resultData = borrowMoneyService.getBorrowMoneyInfo(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("BorrowMoneyDto", "BusinessManagerController", "getBorrowMoneyInfo", "", null, "", "获取借佣详情信息", e);
        }
        return resultData.toString();
    }

    //获取还款计划列表
    @RequestMapping(value = "/getHkPlanList/{param}", method = RequestMethod.GET)
    public String getHkPlanList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = borrowMoneyService.getHkPlanList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("HkPlanDto", "BorrowMoneyController", "getHkPlanList", "", null, "", "获取还款计划列表", e);
        }
        return resultData.toString();
    }
    //获取还款利息列表
    @RequestMapping(value = "/getHkPlanInterestList/{param}", method = RequestMethod.GET)
    public String getHkPlanInterestList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = borrowMoneyService.getHkPlanInterestList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("HkPlanInterestDto", "BorrowMoneyController", "getHkPlanInterestList", "", null, "", "获取还款利息列表", e);
        }
        return resultData.toString();
    }

    //获取还款计划详情
    @RequestMapping(value = "/getHkPlanInfo", method = RequestMethod.POST)
    public String getHkPlanInfo(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            HkPlanDto dto = JsonUtil.parseToObject(json, HkPlanDto.class);
            resultData = borrowMoneyService.getHkPlanInfo(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("HkPlanDto", "BusinessManagerController", "getHkPlanInfo", "", null, "", "获取还款计划详情", e);
        }
        return resultData.toString();
    }

    //还款操作
    @RequestMapping(value = "/updateHkPlan", method = RequestMethod.POST)
    public String updateHkPlan(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            HkPlanDto dto = JsonUtil.parseToObject(json, HkPlanDto.class);
            resultData = borrowMoneyService.updateHkPlan(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("HkPlanDto", "BusinessManagerController", "updateHkPlan", "", null, "", "还款操作", e);
        }
        return resultData.toString();
    }
}
