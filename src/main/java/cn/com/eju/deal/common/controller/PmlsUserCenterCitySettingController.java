package cn.com.eju.deal.common.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.service.PmlsUserCenterCitySettingService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pmlsUserCenterCitySetting")
public class PmlsUserCenterCitySettingController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private PmlsUserCenterCitySettingService pmlsUserCenterCitySettingService;

    @RequestMapping(value = "/queryHblRegionList", method = RequestMethod.POST)
    public String queryHblRegionList(@RequestBody String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        try {
            resultData = pmlsUserCenterCitySettingService.queryHblRegionList(queryParam);

        } catch (Exception e) {
            resultData.setFail("查询异常");
            logger.error("common", "PmlsUserCenterCitySettingController", "queryHblRegionList",
                    "", null, "", " 查询HBL归属区域失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryHblAreaCityList", method = RequestMethod.POST)
    public String queryHblAreaCityList(@RequestBody String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        try {
            resultData = pmlsUserCenterCitySettingService.queryHblAreaCityList(queryParam);

        } catch (Exception e) {
            resultData.setFail("查询异常");
            logger.error("common", "PmlsUserCenterCitySettingController", "queryHblAreaCityList",
                    "", null, "", " 查询HBL归属板块失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryHblCityList", method = RequestMethod.POST)
    public String queryHblCityList(@RequestBody String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        try {
            resultData = pmlsUserCenterCitySettingService.queryHblCityList(queryParam);

        } catch (Exception e) {
            resultData.setFail("查询异常");
            logger.error("common", "PmlsUserCenterCitySettingController", "queryHblCityList",
                    "", null, "", " 查询HBL归属城市失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/queryHblCenterList", method = RequestMethod.POST)
    public String queryHblCenterList(@RequestBody String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        try {
            resultData = pmlsUserCenterCitySettingService.queryHblCenterList(queryParam);

        } catch (Exception e) {
            resultData.setFail("查询异常");
            logger.error("common", "PmlsUserCenterCitySettingController", "queryHblCenterList",
                    "", null, "", " 查询HBL归属中心失败", e);
        }
        return resultData.toString();
    }
}
