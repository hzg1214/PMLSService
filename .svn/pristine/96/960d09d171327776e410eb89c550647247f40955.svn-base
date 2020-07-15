package cn.com.eju.pmls.quartz;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.channelBusiness.service.BusinessManagerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("autoRefreshDataJob")
public class AutoRefreshDataJob extends BaseController{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private BusinessManagerService businessManagerService;
    //自动生成借佣结算书附件
    @ResponseBody
    @RequestMapping(value = "/createJssFile", method = RequestMethod.GET)
    public String createJssFile(HttpServletRequest request)
    {
        logger.info("定时任务测试createJssFile  start#######################################");
        ResultData resultData = new ResultData();
        try{
            //获取未生成附件的结算书列表并生成附件
            resultData = businessManagerService.getNotFileJssList(request);
        }catch (Exception e)
        {
            logger.info("定时任务测试createJssFile  error #######################################");
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "createJssFile", "", null, "", "获取未生成附件的结算书列表并生成附件", e);
        }
        logger.info("定时任务测试createJssFile  end #######################################");
        return resultData.toString();
    }
    //自动生成三方协议书合同附件
    @ResponseBody
    @RequestMapping(value = "/createSfxyFile", method = RequestMethod.GET)
    public String createSfxyFile(HttpServletRequest request)
    {
        logger.info("定时任务测试createSfxyFile  start#######################################");
        ResultData resultData = new ResultData();
        try{
            //获取未生成附件的结算书列表并生成附件
            resultData = businessManagerService.createSfxyFile(request);
        }catch (Exception e)
        {
            logger.info("定时任务测试createSfxyFile  error#######################################");
            resultData.setFail();
            logger.error("BusinessManagerDto", "BusinessManagerController", "createJssFile", "", null, "", "获取未生成附件的结算书列表并生成附件", e);
        }
        logger.info("定时任务测试createSfxyFile  end#######################################");
        return resultData.toString();
    }

}
