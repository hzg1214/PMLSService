package cn.com.eju.deal.houseLinkage.linkAchieveChange.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.AchieveChangeLogService;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.service.LinkAchieveChangeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 联动业绩调整日志
 * Created by hzy on 2017/10/23.
 */
@RestController
@RequestMapping(value = "achieveChangeLog")
public class AchieveChangeLogController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "achieveChangeLogService")
    AchieveChangeLogService achieveChangeLogService;

    @RequestMapping(value = "/getList/{relateId}", method = RequestMethod.GET)
    public ResultData getLogList(@PathVariable("relateId") Integer relateId){
        ResultData resultData = null;

        try {

            resultData = achieveChangeLogService.getLogList(relateId);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "achieveChangeLog", "getLogList", relateId + "", null, "", "查询联动业绩调整日志list异常", e);

        }
        return resultData;
    }
}
