package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.dto.UsrOrgHisDto;
import cn.com.eju.deal.Report.service.ICommonReportService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2018/1/18.
 */
@RestController
@RequestMapping("commonReport")
public class CommonReportController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "commonReportService")
    private ICommonReportService commonReportService;

    /**
     * 获取归属区域
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getRegion/{param}", method = RequestMethod.GET)
    public String getRegion(@PathVariable String param) {

        ResultData<List<UsrOrgHisDto>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = commonReportService.getRegion(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("commonReport", "CommonReportController", "getRegion", "", -1, "", "查询归属区域失败", e);
        }

        return resultData.toString();

    }

    /**
     * 获取归属城市
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAreaCity/{param}", method = RequestMethod.GET)
    public String getAreaCity(@PathVariable String param) {

        ResultData<List<UsrOrgHisDto>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = commonReportService.getAreaCity(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("commonReport", "CommonReportController", "getAreaCity", "", -1, "", "查询归属城市失败", e);
        }

        return resultData.toString();
    }

    /**
     * 获取所在城市
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCityGroup/{param}", method = RequestMethod.GET)
    public String getCityGroup(@PathVariable String param) {

        ResultData<List<UsrOrgHisDto>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = commonReportService.getCityGroup(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("commonReport", "CommonReportController", "getCityGroup", "", -1, "", "查询所在城市失败", e);
        }

        return resultData.toString();
    }

    /**
     * 获取归属中心
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCenterGroup/{param}", method = RequestMethod.GET)
    public String getCenterGroup(@PathVariable String param) {

        ResultData<List<UsrOrgHisDto>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = commonReportService.getCenterGroup(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("commonReport", "CommonReportController", "getCenterGroup", "", -1, "", "查询归属中心失败", e);
        }

        return resultData.toString();
    }

    /**
     * 获取归属中心
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getDeptGroup/{param}", method = RequestMethod.GET)
    public String getDeptGroup(@PathVariable String param) {

        ResultData<List<UsrOrgHisDto>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = commonReportService.getDeptGroup(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("commonReport", "CommonReportController", "getDeptGroup", "", -1, "", "查询项目归属部门失败", e);
        }

        return resultData.toString();
    }

}
