package cn.com.eju.deal.otherReport.controller;

import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Consts;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import cn.com.eju.deal.api.houseLinkage.constant.HouseLinkageConstant;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.otherReport.dto.LnkQtReportDto;
import cn.com.eju.deal.otherReport.model.LnkQtReport;
import cn.com.eju.deal.otherReport.service.QtReportService;

/**
 * desc:其他收入-报备
 */
@RestController
@RequestMapping(value = "qtReport")
public class QtReportController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "qtReportService")
    private QtReportService qtReportService;
    
    /**
     * desc:列表
     * 2019年10月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<LnkQtReportDto>> resultData = new ResultData<>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = qtReportService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("qtReport", "QtReportController", "list", "", null, "", " 查询-list", e);
        }
        
        return resultData.toString();
    }


    /**
     * 查询所有归属城市
     */
    @RequestMapping(value = "getBasCitySettingList", method = RequestMethod.GET)
    public String getBasCitySettingList() {
        ResultData<List< Map<String,Object>>> resultData = new ResultData<>();
        try {
            resultData = qtReportService.getBasCitySettingList();
        } catch (Exception e) {
            resultData.setFail();
            logger.error("qtReport", "QtReportController", "getBasCitySettingList", "", null, "", "获取归属城市异常", e);
        }
        return resultData.toString();
    }


    /**
     * 查询归属城市下的归属中心estate" + "/{id}"
     * */
    @RequestMapping(value = "getAchivAchievementLevelSettingList/{cityNo}", method = RequestMethod.GET)
    public String getAchivAchievementLevelSettingList(@PathVariable String cityNo) {
        ResultData<List< Map<String,Object>>> resultData = new ResultData<>();
        try {
            resultData = qtReportService.getAchivAchievementLevelSettingList(cityNo);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("qtReport", "QtReportController", "getAchivAchievementLevelSettingList", "", null, "", "获取归属城市归属中心异常", e);
        }
        return resultData.toString();
    }



    /**
     * 报备
     */
    @RequestMapping(value = "/createReport", method = RequestMethod.POST)
    public String createReport(@RequestBody String jsonStr, HttpServletRequest request){
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try{
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)){
                resultData.setReturnCode("201");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);

            LnkQtReport lnkQtReport = JSON.parseObject(jsonData, LnkQtReport.class);
            resultData = qtReportService.createReport(lnkQtReport);
            return resultData.toString();
        }
        catch (Exception e){
            logger.error("qtReport", "QtReportController", "insertReport", jsonStr, null, "", "", e);
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            return resultData.toString();
        }
    }

    /**
     * 无效
     */
    @RequestMapping(value = "/validQtReport/{reportId}/{userId}", method = RequestMethod.GET)
    public String validReport(HttpServletRequest request
            ,@PathVariable Integer reportId
            ,@PathVariable Integer userId){
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try{
            resultData = qtReportService.validQtReport(reportId,userId);
            return resultData.toString();
        }
        catch (Exception e){
            logger.error("qtReport", "QtReportController", "validQtReport", reportId.toString(), null, "", "", e);
            resultData.setFail("处理失败");
            return resultData.toString();
        }
    }
    

    /**
     * desc:查看详情
     * 2019年10月15日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getQtReportById/{param}", method = RequestMethod.GET)
    public String getQtReportById(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        Integer id = Integer.parseInt(reqMap.get("id").toString());
        try {
            resultData = qtReportService.getQtReportById(id);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("qtReport", "QtReportController", "getQtReportById", reqMap.toString(), null, "", "查看其他收入详情失败", e);
        }
        return resultData.toString();
    }


    /**
     * desc:业务节点-查看
     * 2019年10月17日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getOperDetail/{param}", method = RequestMethod.GET)
    public String getOperDetail(@PathVariable String param)
    {
        //构建返回
        ResultData resultData = new ResultData<>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
//        Integer id = Integer.parseInt(queryParam.get("id").toString());
        try
        {
            resultData = qtReportService.getOperDetail(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("qtReport", "QtReportController", "getOperDetail", "", null, "", "查询业务节点明细", e);
        }
        
        return resultData.toString();
    }
}
