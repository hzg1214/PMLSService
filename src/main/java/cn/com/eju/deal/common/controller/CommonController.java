package cn.com.eju.deal.common.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.common.model.CompanyFullName;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.dto.common.OrgDto;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用controller
 *
 * @author (li_xiaodong)
 * @date 2015年11月29日 下午5:15:09
 */
@RestController
@RequestMapping("/commons")
public class CommonController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "fesbService")
    private FesbService fesbService;

    @Resource(name = "commonService")
    private CommonService commonService;


    /**
     * 刷新缓存
     *
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public String refresh(HttpServletRequest request, ModelMap mop) {

        //构建返回
        ResultData<String> resultData = new ResultData<>();

        try {
            logger.info("刷新配置");

            SystemParam.refreshCodeMap();
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "refresh", "", null, "", "刷新配置失败", e);
        }
        return resultData.toString();

    }

    /**
     * TODO (获取关账信息)
     *
     * @return
     */
    @RequestMapping(value = "/getSwitchInfo/{cityNo}", method = RequestMethod.GET)
    public String getSwitchInfo(@PathVariable String cityNo) {
        ResultData<Map<String, Object>> resultData = new ResultData<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("relateSystem", "17402");
        try {
            Map<String, Object> result = commonService.getSwitchLnk(map);
            map.clear();
            if (null != result && !result.isEmpty()) {
                if ("52F1D510-DDA2-4A8D-9FD3-E165E933CEEC".equals(cityNo)) {//昆山当做上海处理
                    cityNo = "AAAD4421-21B1-46FD-9DE4-D5A3183CE260";
                }
                String yearMonth = (String) result.get(cityNo);

                if (StringUtil.isNotEmpty(yearMonth)) {
                    map.put("yearMonth", yearMonth);
                } else {
                    map.put("yearMonth", "1970-01-01");
                }
                int diffNum = -1;
                if (null != yearMonth) {
                    diffNum = DateUtil.getDaysDiff(new Date(), DateUtil.getDate(yearMonth, "yyyy-MM-dd"));
                }
                if (diffNum >= 0) {
                    map.put("isShowCurrDate", true);
                }
            } else {
                map.put("yearMonth", "1970-01-01");
                map.put("isShowCurrDate", true);
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "getSwitchInfo", "", null, "", "获取开关账异常", e);
        }
        resultData.setReturnData(map);
        return resultData.toString();
    }

    @RequestMapping(value = "queryOrgList/{param}", method = RequestMethod.GET)
    public String queryOrgList(@PathVariable String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<OrgDto>> resultData = new ResultData<>();
        try {
            resultData = commonService.queryOrgList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "queryOrgList", "", null, "", "获取组织架构异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "queryCityList/{param}", method = RequestMethod.GET)
    public String queryCityList(@PathVariable String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<City>> resultData = new ResultData<>();
        try {
            resultData = commonService.queryCityList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "queryCityList", "", null, "", "获取城市异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "queryCenterList/{param}", method = RequestMethod.GET)
    public String queryCenterList(@PathVariable String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<CenterDto>> resultData = new ResultData<>();
        try {
            resultData = commonService.queryCenterList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "queryCenterList", "", null, "", "获取中心异常", e);
        }

        return resultData.toString();
    }

    /**
     * 根据cityNo查询模板编号
     *
     * @param cityNo
     * @return
     */
    @RequestMapping(value = "qtemplatecode/{cityNo}", method = RequestMethod.GET)
    public String getCitySettingByCityNo(@PathVariable String cityNo) {
        ResultData<Map<String, Object>> resultData = new ResultData<>();
        try {
            resultData = commonService.getCitySettingByCityNo(cityNo);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "getCitySettingByCityNo", "", null, "", "获取模板编号异常", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "queryFullNameList/{param}", method = RequestMethod.GET)
    public String queryFullNameList(@PathVariable String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<CompanyFullName>> resultData = new ResultData<>();
        try {
            resultData = commonService.queryFullNameList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("common", "CommonController", "queryFullNameList", "", null, "", "获取我方全称列表异常", e);
        }

        return resultData.toString();
    }
}
