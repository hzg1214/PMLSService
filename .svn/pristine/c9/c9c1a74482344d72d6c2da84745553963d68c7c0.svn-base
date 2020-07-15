package cn.com.eju.deal.token.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.token.model.ChannelToken;
import cn.com.eju.deal.token.service.ChannelTokenService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by Sky on 2016/4/20.
 * 渠道Token接口
 */
@RestController
@RequestMapping(value = "channelToken")
public class ChannelTokenController extends BaseController {
    
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 服务层
     */
    @Resource(name = "channelTokenService")
    private ChannelTokenService channelTokenService;


    /**
     * 获取token
     *
     * @param typeId 类型编号
     * @return token信息
     */
    @RequestMapping(value = "/type/{typeId}")
    public String getTokenByType(@PathVariable int typeId) {

        ResultData<ChannelToken> resultData = new ResultData<>();
        ChannelToken channelToken = new ChannelToken();
        try
        {
            channelToken = channelTokenService.getTokenByTypeId(typeId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("token", "ChannelTokenController", "getTokenByType", "", null, "", "获取token失败！", e);
        }

        if (channelToken != null)
            resultData.setSuccess();
        else
            resultData.setFail("查询失败");

        resultData.setReturnData(channelToken);

        return resultData.toString();
    }


    /**
     * 创建token
     *
     * @param jsonDto tokenjson
     * @return 添加状态
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addToken(@RequestBody String jsonDto) {
        ChannelToken channelToken = JsonUtil.parseToObject(jsonDto, ChannelToken.class);

        Integer apiResult = this.channelTokenService.addToken(channelToken);

        ResultData<ChannelToken> resultData = new ResultData<>();
        if (apiResult > 0)
            resultData.setSuccess();
        else
            resultData.setFail("添加失败");

        return resultData.toString();
    }


}
