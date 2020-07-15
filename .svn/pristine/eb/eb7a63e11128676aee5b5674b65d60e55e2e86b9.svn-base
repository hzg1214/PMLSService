package cn.com.eju.deal.houseLinkage.linkAchieveChange.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.LinkAchieveChangeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 联动业绩调整
 * Created by hzy on 2017/10/23.
 */
@RestController
@RequestMapping(value = "linkAchieveChange")
public class LinkAchieveChangeController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "linkAchieveChangeService")
    LinkAchieveChangeService linkAchieveChangeService;

    @RequestMapping(value = "/getList/{param}", method = RequestMethod.GET)
    public ResultData getLinkAchieveChangeList(@PathVariable String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = linkAchieveChangeService.getLinkAchieveChangeList(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "linkAchieveChange", "getLinkAchieveChangeList", reqMap.toString(), null, "", "查询联动业绩调整list", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/saveLinkAchieve", method = RequestMethod.POST)
    public ResultData saveLinkAchieve(@RequestBody String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = linkAchieveChangeService.saveLinkAchieve(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("保存联动业绩调整异常！");
            }
            logger.error("CRM", "linkAchieveChange", "getLinkAchieveChangeList", reqMap.toString(), null, "", "保存联动业绩调整异常", e);

        }
        return resultData;
    }
}
