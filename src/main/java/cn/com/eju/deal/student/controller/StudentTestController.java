package cn.com.eju.deal.student.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.student.service.StudentTestService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by eju on 2019/12/25.
 */
@RestController
@RequestMapping(value = "test")
public class StudentTestController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "studentTestService")
    private StudentTestService studentTestService;

    /**
     * 创建
     * @param jsonDto 对象字符串
     * @return
     */
    @RequestMapping(value = "/oaFrameContract", method = RequestMethod.POST)
    public ResultData<?> oaFrameContract(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaFrameContract(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaFrameContract", "StudentTestController", "oaFrameContract", jsonDto, 0, "", "框架合同审核失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaProject", method = RequestMethod.POST)
    public ResultData<?> oaProject(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaProject(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaProject", "StudentTestController", "oaProject", jsonDto, 0, "", "立项失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaSignOrApproach", method = RequestMethod.POST)
    public ResultData<?> oaSignOrApproach(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaSignOrApproach(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaSignOrApproach", "StudentTestController", "oaSignOrApproach", jsonDto, 0, "", "新签/进场确认失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaDistribution", method = RequestMethod.POST)
    public ResultData<?> oaDistribution(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaDistribution(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaDistribution", "StudentTestController", "oaDistribution", jsonDto, 0, "", "分销合同审核失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaStatement", method = RequestMethod.POST)
    public ResultData<?> oaStatement(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaStatement(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaStatement", "StudentTestController", "oaStatement", jsonDto, 0, "", "结算书审核失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaAgreement", method = RequestMethod.POST)
    public ResultData<?> oaAgreement(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaAgreement(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaAgreement", "StudentTestController", "oaAgreement", jsonDto, 0, "", "借佣三方协议审核失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaReceivables", method = RequestMethod.POST)
    public ResultData<?> oaReceivables(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaReceivables(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaReceivables", "StudentTestController", "oaReceivables", jsonDto, 0, "", "借佣请款单审核失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaExpenditure", method = RequestMethod.POST)
    public ResultData<?> oaExpenditure(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaExpenditure(map);
            if (count <= 0){
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaExpenditure", "StudentTestController", "oaExpenditure", jsonDto, 0, "", "借佣支出单审核失败", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/oaQk", method = RequestMethod.POST)
    public ResultData<?> oaQk(@RequestBody String jsonDto)
    {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        Map<String, Object> map = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
            int count = studentTestService.oaQk(map);
            if (count <= 0){
                resultData.setFail();
            }else if(count ==10) {
            	resultData.setFail();
            	resultData.setReturnCode("-10");
            	resultData.setReturnMsg("请款单oa编号:"+map.get("oaNo")+"，不能重复审核");
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("oaQk", "StudentTestController", "oaQk", jsonDto, 0, "", "请款单审核失败", e);

        }
        return resultData;
    }


}
