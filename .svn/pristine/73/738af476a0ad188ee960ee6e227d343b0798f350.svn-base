package cn.com.eju.deal.controller.operationLog;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.operationLog.WXOperationLogDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.operationLog.OperationLogService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by xu on 2017/6/27.
 */
@RestController
@RequestMapping(value = "operationLogController")
public class OperationLogController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "operationLogService")
    private OperationLogService operationLogService;

    @RequestMapping(value = "/addOperationLogs", method = RequestMethod.POST)
    public String addOperationLogs(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            WXOperationLogDto dto = JsonUtil.parseToObject(jsonDto, WXOperationLogDto.class);
            resultData=operationLogService.addOperationLogs(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("operationLogController", "operationLogController", "addOperationLogs", "", null, "", "添加操作日志", e);
        }
        return resultData.toString();
    }
}
