package cn.com.eju.deal.controller.workLog;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.model.workLog.WorkLogDto;
import cn.com.eju.deal.service.sweepStreets.SweepStreetsService;
import cn.com.eju.deal.service.workLog.WorkLogService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xu on 2017/5/11.
 */
@RestController
@RequestMapping(value = "workLogController")
public class WorkLogController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "workLogService")
    private WorkLogService workLogService;

    @RequestMapping(value = "/queryCenterStoreList", method = RequestMethod.POST)
    public String queryCenterStoreList(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            WorkLogDto dto = JsonUtil.parseToObject(jsonDto, WorkLogDto.class);
            resultData = workLogService.queryCenterStoreList(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("workLogController", "workLogController", "queryCenterStoreList", "", null, "", "查询中心门店业绩", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/queryCenterMaintainerList", method = RequestMethod.POST)
    public String queryCenterMaintainerList(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            WorkLogDto dto = JsonUtil.parseToObject(jsonDto, WorkLogDto.class);
            resultData = workLogService.queryCenterMaintainerList(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("workLogController", "workLogController", "queryCenterMaintainerList", "", null, "", "查询中心维护人业绩", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/queryMaintainerStoreList", method = RequestMethod.POST)
    public String queryMaintainerStoreList(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            WorkLogDto dto = JsonUtil.parseToObject(jsonDto, WorkLogDto.class);
            resultData = workLogService.queryMaintainerStoreList(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("workLogController", "workLogController", "queryMaintainerStoreList", "", null, "", "查询维护人门店业绩", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public String getUserInfo(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            WorkLogDto dto = JsonUtil.parseToObject(jsonDto, WorkLogDto.class);
            resultData = workLogService.getUserInfo(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("workLogController", "workLogController", "getUserInfo", "", null, "", "查询维护人信息", e);
        }
        return resultData.toString();
    }

}
