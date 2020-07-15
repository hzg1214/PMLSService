package cn.com.eju.deal.controller.followMap;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.followMap.FollowCommentDto;
import cn.com.eju.deal.model.followMap.FollowRecordDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.followMap.FollowMapService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/4/26.
 */
@RestController
@RequestMapping(value = "followMapController")
public class FollowMapController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "followMapService")
    private FollowMapService followMapService;

    @RequestMapping(value = "/getLocalStoreList/{param}", method = RequestMethod.GET)
    public String getStoreListData(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = followMapService.getLocalStoreList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "getLocalStoreList", "", null, "", "获取本地门店列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getStoreFollowList", method = RequestMethod.POST)
    public String getStoreFollowList(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData = followMapService.getStoreFollowList(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "getStoreFollowList", "", null, "", "获取跟进记录列表", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/saveFollowComment", method = RequestMethod.POST)
    public String saveFollowComment(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowCommentDto dto = JsonUtil.parseToObject(jsonDto, FollowCommentDto.class);
            resultData = followMapService.saveFollowComment(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "saveFollowComment", "", null, "", "点评跟进", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/addContacts", method = RequestMethod.POST)
    public String addContacts(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            ContactsDto dto = JsonUtil.parseToObject(jsonDto, ContactsDto.class);
            resultData = followMapService.addContacts(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "addContacts", "", null, "", "添加联系人", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/updateContacts", method = RequestMethod.POST)
    public String updateContacts(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            ContactsDto dto = JsonUtil.parseToObject(jsonDto, ContactsDto.class);
            resultData = followMapService.updateContacts(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "updateContacts", "", null, "", "修改或删除联系人", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addFollow", method = RequestMethod.POST)
    public String addFollow(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowRecordDto dto = JsonUtil.parseToObject(jsonDto, FollowRecordDto.class);
            resultData = followMapService.addFollow(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "addFollow", "", null, "", "添加跟进记录", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/updateFollow", method = RequestMethod.POST)
    public String updateFollow(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowRecordDto dto = JsonUtil.parseToObject(jsonDto, FollowRecordDto.class);
            resultData = followMapService.updateFollow(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "updateFollow", "", null, "", "修改跟进记录", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/addFollowSign", method = RequestMethod.POST)
    public String addFollowSign(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowRecordDto dto = JsonUtil.parseToObject(jsonDto, FollowRecordDto.class);
            resultData = followMapService.addFollowSign(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "addFollowSign", "", null, "", "签到添加跟进记录", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/updateFollowSignOut", method = RequestMethod.POST)
    public String updateFollowSignOut(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowRecordDto dto = JsonUtil.parseToObject(jsonDto, FollowRecordDto.class);
            resultData = followMapService.updateFollowSignOut(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "updateFollowSignOut", "", null, "", "签退修改跟进记录", e);
        }
        return resultData.toString();
    }
    @RequestMapping(value = "/getFollowById", method = RequestMethod.POST)
    public String getFollowById(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowRecordDto dto = JsonUtil.parseToObject(jsonDto, FollowRecordDto.class);
            resultData = followMapService.getFollowById(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "getFollowById", "", null, "", "根据跟进编号查询跟进详情", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getWorkSummaryData", method = RequestMethod.POST)
    public String getWorkSummaryData(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            FollowRecordDto dto = JsonUtil.parseToObject(jsonDto, FollowRecordDto.class);
            resultData = followMapService.getWorkSummaryData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "getWorkSummaryData", "", null, "", "获取工作总结列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/updateEmployDirect", method = RequestMethod.POST)
    public String updateEmployDirect(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            ContactsDto dto = JsonUtil.parseToObject(jsonDto, ContactsDto.class);
            resultData = followMapService.updateEmployDirect(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("followMapController", "followMapController", "updateEmployDirect", "", null, "", "修改直聘账号", e);
        }
        return resultData.toString();
    }
}
