package cn.com.eju.deal.controller.maintainStore;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.model.sweepStreets.WXPushInfoDto;
import cn.com.eju.deal.service.maintainStore.MaintainStoreService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/5/22.
 */
@RestController
@RequestMapping(value = "maintainStoreController")
public class MaintainStoreController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "maintainStoreService")
    private MaintainStoreService maintainStoreService;

    @RequestMapping(value = "/getStoreListData", method = RequestMethod.POST)
    public String getStoreListData(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = maintainStoreService.getStoreListData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("maintainStoreController", "maintainStoreController", "getStoreListData", "", null, "", "获取维护门店列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getOverdueNotFollowStore", method = RequestMethod.POST)
    public String getOverdueNotFollowStore()
    {
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        try
        {
            resultData = maintainStoreService.getOverdueNotFollowStore();
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("maintainStoreController", "maintainStoreController", "getOverdueNotFollowStore", "", null, "", "逾期未跟进的门店信息", e);
        }
        return resultData.toString();
    }

    //获取离职的维护人列表
    @RequestMapping(value = "/checkMaintainerStatus", method = RequestMethod.POST)
    public String checkMaintainerStatus(@RequestBody String jsonDto)
    {
        //StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData resultData = new ResultData();
        try
        {
            resultData = maintainStoreService.checkMaintainerStatus();
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("maintainStoreController", "maintainStoreController", "checkMaintainerStatus", "", null, "", "获取离职的维护人列表", e);
        }
        return resultData.toString();
    }
    //获取离职的维护人列表
    @RequestMapping(value = "/addPushInfo", method = RequestMethod.POST)
    public String addPushInfo(@RequestBody String jsonDto)
    {
        WXPushInfoDto dto = JsonUtil.parseToObject(jsonDto, WXPushInfoDto.class);
        ResultData resultData = new ResultData();
        try
        {
            resultData = maintainStoreService.addPushInfo(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("maintainStoreController", "maintainStoreController", "addPushInfo", jsonDto, null, "", "推送消息记录", e);
        }
        return resultData.toString();
    }
}
