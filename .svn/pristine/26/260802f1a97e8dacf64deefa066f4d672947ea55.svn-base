package cn.com.eju.deal.controller.achieveChange;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.achieveChange.AchieveChangeDto;
import cn.com.eju.deal.service.achieveChange.AchieveChangeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */
@RestController
@RequestMapping(value = "achieveChange")
public class AchieveChangeController extends BaseController  {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    AchieveChangeService achieveChangeService;
    /**
     * 获取维护人中心历史列表
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<AchieveChangeDto>> resultData = new ResultData<List<AchieveChangeDto>>();
        try
        {
            resultData = achieveChangeService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("AchieveChangeController", "AchieveChangeController", "queryList", "" ,null, "", "获取维护人中心历史列表", e);
        }
        return resultData.toString();
    }
}
