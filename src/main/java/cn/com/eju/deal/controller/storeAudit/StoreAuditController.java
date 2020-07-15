package cn.com.eju.deal.controller.storeAudit;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.store.model.StoreAuthCheckDto;
import cn.com.eju.deal.store.model.StoreBizStop;
import cn.com.eju.deal.store.model.StoreStopCancel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.sweepStreets.StoreAuditRecordDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.storeAudit.StoreAuditService;

/**
 * Created by xu on 2017/4/19.
 */
@RestController
@RequestMapping(value = "storeAuditController")
public class StoreAuditController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "storeAuditService")
    private StoreAuditService storeAuditService;

    @RequestMapping(value = "/getStoreList/{param}", method = RequestMethod.GET)
    public String getStoreList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = storeAuditService.getStoreList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreList", "" ,null, "", "获取门店列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreAuthCheckListData/{param}", method = RequestMethod.GET)
    public String getStoreAuthCheckListData(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = storeAuditService.getStoreAuthCheckListData(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreAuthCheckListData", "" ,null, "", "获取门店授牌验收列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreBizStopListData/{param}", method = RequestMethod.GET)
    public String getStoreBizStopListData(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = storeAuditService.getStoreBizStopListData(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreBizStopListData", "" ,null, "", "获取门店停业上报列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreStopCancelListData/{param}", method = RequestMethod.GET)
    public String getStoreStopCancelListData(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = storeAuditService.getStoreStopCancelListData(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreStopCancelListData", "" ,null, "", "获取门店停业撤销列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getStoreAuditRecordList/{param}", method = RequestMethod.GET)
    public String getStoreAuditRecordList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreAuditRecordDto>> resultData = new ResultData<List<StoreAuditRecordDto>>();
        try
        {
            resultData = storeAuditService.getStoreAuditRecordList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreAuditRecordList", "" ,null, "", "获取门店审核列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreById", method = RequestMethod.POST)
    public String getStoreById(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = storeAuditService.getStoreById(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreList", "", null, "", "获取门店信息", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreAuthCheckById/{param}", method = RequestMethod.GET)
    public String getStoreAuthCheckById(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<StoreAuthCheckDto> resultData = new ResultData();
        try
        {
            resultData = storeAuditService.getStoreAuthCheckById(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreAuthCheckById", "" ,null, "", "获取门店授牌验收明细", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreBizStopById/{param}", method = RequestMethod.GET)
    public String getStoreBizStopById(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<StoreBizStop> resultData = new ResultData();
        try
        {
            resultData = storeAuditService.getStoreBizStopById(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreBizStopById", "" ,null, "", "获取门店停业上报明细", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreStopCancelById/{param}", method = RequestMethod.GET)
    public String getStoreStopCancelById(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<StoreStopCancel> resultData = new ResultData();
        try
        {
            resultData = storeAuditService.getStoreStopCancelById(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreAuditController", "StoreAuditController", "getStoreStopCancelById", "" ,null, "", "获取门店停业撤销明细", e);
        }
        return resultData.toString();
    }

}
