package cn.com.eju.pmls.skStatement.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateDtlDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateLogDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateMatDto;
import cn.com.eju.pmls.skStatement.service.SkAllocateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("skAllocate")
public class SkAllocateController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    SkAllocateService skAllocateService;

    /**
     * 按成销日期优先
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAllocateListForDeal/{param}", method = RequestMethod.GET)
    public String getAllocateListForDeal(@PathVariable String param) {
        ResultData<List<PmlsSkAllocateDtlDto>> resultData = new ResultData<>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.getAllocateListForDeal(queryParam);
        } catch (Exception e) {
            logger.error("skAllocate.getAllocateListForDeal 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    /**
     * 按成销日期优先
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAllocateListForBuilding/{param}", method = RequestMethod.GET)
    public String getAllocateListForBuilding(@PathVariable String param) {
        ResultData<List<PmlsSkAllocateDtlDto>> resultData = new ResultData<>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.getAllocateListForBuilding(queryParam);
        } catch (Exception e) {
            logger.error("skAllocate.getAllocateListForBuilding 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/submitAllocate", method = RequestMethod.POST)
    public String submitAllocate(@RequestBody String jsonDto) {
        ResultData<String> resultData = new ResultData<>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);
            resultData = skAllocateService.submitAllocate(queryParam);
        } catch (Exception e) {
            logger.error("skAllocate.submitAllocate 发生异常 param:" + jsonDto, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/recordList/{param}", method = RequestMethod.GET)
    public String recordList(@PathVariable String param) {
        ResultData<List<PmlsSkAllocateMatDto>> resultData = new ResultData<List<PmlsSkAllocateMatDto>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.recordList(queryParam);
        } catch (Exception e) {
            logger.error("SkAllocateController.recordList 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/logList/{param}", method = RequestMethod.GET)
    public String logList(@PathVariable String param) {
        ResultData<List<PmlsSkAllocateLogDto>> resultData = new ResultData<List<PmlsSkAllocateLogDto>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.logList(queryParam);
        } catch (Exception e) {
            logger.error("SkAllocateController.logList 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/cancelAll", method = RequestMethod.POST)
    public String cancelAll(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<String>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.cancelAll(queryParam);
        } catch (Exception e) {
            logger.error("SkAllocateController.cancelAll 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/cancelDtl", method = RequestMethod.POST)
    public String cancelDtl(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<String>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.cancelDtl(queryParam);
        } catch (Exception e) {
            logger.error("SkAllocateController.cancelDtl 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/querySkAllocateDtlList/{param}", method = RequestMethod.GET)
    public String querySkAllocateDtlList(@PathVariable String param) {
        ResultData<List<PmlsSkAllocateDtlDto>> resultData = new ResultData<List<PmlsSkAllocateDtlDto>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = skAllocateService.querySkAllocateDtlList(queryParam);
        } catch (Exception e) {
            logger.error("SkAllocateController.querySkAllocateDtlList 发生异常 param:" + param, e);
            resultData.setFail();
        }
        return resultData.toString();
    }
}
