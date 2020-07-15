package cn.com.eju.deal.offSetReport.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.offSetReport.service.OffSetReportService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/11/29.
 */
@RestController
@RequestMapping(value = "offSetReport")
public class OffSetReportController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "offSetReportService")
    private OffSetReportService offSetReportService;

    @RequestMapping(value = "/getOffSetReportList/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try
        {
            resultData = offSetReportService.getOffSetReportList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("offSetReport", "OffSetReportController", "list", "", null, "", " 查询-list", e);
        }

        return resultData.toString();
    }
}
