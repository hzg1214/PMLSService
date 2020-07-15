package cn.com.eju.deal.youFangTongBind.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.youFangTongBind.dto.YouFangTongBindDto;
import cn.com.eju.deal.youFangTongBind.service.YouFangTongBindService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * Created by eju on 2019/9/3.
 */
@RestController
@RequestMapping("youFangTongBind")
public class YouFangTongBindController extends BaseController {

    @Resource
    private YouFangTongBindService youFangTongBindService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @RequestMapping(value = "/bindYouFangTongInfo", method = RequestMethod.POST)
    public String bindYouFangTongInfo(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try
        {
            YouFangTongBindDto dto = JsonUtil.parseToObject(jsonDto, YouFangTongBindDto.class);
            dto.setCreateDate(new Date());
            resultData = youFangTongBindService.bindYouFangTongInfo(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("youFangTongBind", "YouFangTongBindController", "bindYouFangTongInfo", "", null, "", "绑定关系", e);
        }
        return resultData.toString();
    }

    @ResponseBody
    @RequestMapping(value = "/getBindList", method = RequestMethod.POST)
    public String getBindList(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = youFangTongBindService.getBindList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("YouFangTongBindDto", "YouFangTongBindController", "getBindList", "", null, "", "查询绑定关系列表", e);
        }
        return resultData.toString();
    }
    @ResponseBody
    @RequestMapping(value = "/getBindLogList", method = RequestMethod.POST)
    public String getBindLogList(@RequestBody String json)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = youFangTongBindService.getBindLogList(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("YouFangTongBindDto", "YouFangTongBindController", "getBindLogList", "", null, "", "查询绑定历史列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryYFTBind", method = RequestMethod.POST)
    public ResultData queryYFTBind(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData<>();
        try {
            Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);

            resultData = youFangTongBindService.queryYFTBind(map);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("queryYFTBind", "YouFangTongBindController", "queryYFTBind", "",
                    null, "", "", e);
        }
        return resultData;
    }
}
