package cn.com.eju.deal.api.houseLinkage.controller;

import cn.com.eju.deal.Report.model.ApproDecideDto;
import cn.com.eju.deal.Report.model.ApproRegisterDto;
import cn.com.eju.deal.Report.model.ApproShowDto;
import cn.com.eju.deal.Report.model.FileDto;
import cn.com.eju.deal.api.houseLinkage.constant.HouseLinkageConstant;
import cn.com.eju.deal.api.houseLinkage.service.APIHouseLinkageService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.api.houseLinkage.*;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.service.EstateService;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFile;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportLog;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.scene.estate.service.FangyouReportLogService;
import cn.com.eju.deal.scene.estate.service.SceneEstateService;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.service.StoreMaintainerService;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.api.IUserAPI;
import cn.com.eju.deal.user.model.User;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.Consts;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 服务层
 *
 * @author qianwei
 * @date 2016年3月22日 下午6:05:44
 */

@RestController
@RequestMapping(value = "APIHouseLink")
public class APIHouseLinkageController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "apiHouseLinkageService")
    private APIHouseLinkageService apiHouseLinkageService;

    @Resource(name = "storeMaintainerService")
    private StoreMaintainerService storeMaintainerService;

    @Resource
    private SceneInCommissionMapper sceneInCommissionMapper;

    @Resource
    private EstateService estatetService;

    @Resource
    private CompanyService companyService;

    @Resource
    private StoreService storeService;

    @Resource
    private ReportService reportService;

    @Resource(name = "sceneEstatetService")
    private SceneEstateService sceneEstatetService;

    @Resource(name = "fangyouReportLogService")
    private FangyouReportLogService fangyouReportLogService;

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private IUserAPI userAPI;

    /**
     * TODO 暂时没用到
     * 查询-对象
     *
     * @param estateId
     * @return
     */
    @RequestMapping(value = "/{estateId}/{companyId}/{customerId}", method = RequestMethod.GET)
    public String getReport(@PathVariable String estateId, @PathVariable String companyId, @PathVariable String customerId) {
        //构建返回
        ResultData<ReportInfoDto> resultData = new ResultData<ReportInfoDto>();

        //查询
        ReportInfoDto ctaDto = new ReportInfoDto();
        try {
            ctaDto = apiHouseLinkageService.getReport(estateId, companyId, customerId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("api.houseLinkage", "APIHouseLinkageController", "getReport", "", null, "", "", e);
        }

        resultData.setReturnData(ctaDto);

        return resultData.toString();
    }

    /**
     * 查询-获取字段表接口列表(房友专用)
     *
     * @param jsonStr 查询条件
     * @return
     */
    @RequestMapping(value = "/crm/dictionaryList", method = RequestMethod.POST)
    public String dictionaryList(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            logger.info("jsonData======>>" + jsonData);
            APISearchDictionaryListDto apiSearchDictionaryListDto = JSON.parseObject(jsonData, APISearchDictionaryListDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("typeId", apiSearchDictionaryListDto.getTypeId());

            rspMap.put("Result", SystemParam.getCodeListByKey(apiSearchDictionaryListDto.getTypeId()));

            rspMap.put("Status", "1");
            rspMap.put("Message", HouseLinkageConstant.SUCCESS);
            rspMap.put("IsSuccess", true);

        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "dictionaryList", "", null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_SEARCH_REPORT_LIST_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * 查询-报备列表(房友专用)
     *
     * @param jsonStr 查询条件
     * @return
     */
    @RequestMapping(value = "/crm/NewHomeLinkage201ForWeb", method = RequestMethod.POST)
    public String reportList(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            logger.info("jsonData======>>" + jsonData);
            APISearchReportListDto apiSearchReportListDto = JSON.parseObject(jsonData, APISearchReportListDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            //   queryParam.put("cityNo", apiSearchReportListDto.getCityNo());
            queryParam.put("districtId", apiSearchReportListDto.getDistrict());
            queryParam.put("estateType", apiSearchReportListDto.getBuildType());

            if (StringUtil.isEmpty(apiSearchReportListDto.getConfirmStatus()) && StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress())) {
                if (StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress()) && Integer.valueOf(apiSearchReportListDto.getLatestProgress()) < 13505) {
                    queryParam.put("latestProgress", Integer.valueOf(apiSearchReportListDto.getLatestProgress()) + 1);
                } else {
                    queryParam.put("latestProgress", apiSearchReportListDto.getLatestProgress());
                }
                if (StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress()) && Integer.valueOf(apiSearchReportListDto.getLatestProgress()) == 13505) {
                    queryParam.put("confirmStatus", "13601");
                } else {
                    queryParam.put("confirmStatus", "13603");
                }
            }
            if (StringUtil.isNotEmpty(apiSearchReportListDto.getConfirmStatus()) && StringUtil.isEmpty(apiSearchReportListDto.getLatestProgress())) {
                if ("13602".equals(apiSearchReportListDto.getConfirmStatus())) {
                    queryParam.put("latestProgress", apiSearchReportListDto.getLatestProgress());
                    queryParam.put("confirmStatus", apiSearchReportListDto.getConfirmStatus());
                } else {
                    queryParam.put("confirmStatus", "13601");
                }
            }
            if ("13602".equals(apiSearchReportListDto.getConfirmStatus()) && StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress())) {
                queryParam.put("latestProgress", apiSearchReportListDto.getLatestProgress());
                queryParam.put("confirmStatus", apiSearchReportListDto.getConfirmStatus());
            }
            if ("13601".equals(apiSearchReportListDto.getConfirmStatus()) && StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress())) {
                if (StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress()) && Integer.valueOf(apiSearchReportListDto.getLatestProgress()) < 13505) {
                    queryParam.put("latestProgress", Integer.valueOf(apiSearchReportListDto.getLatestProgress()) + 1);
                } else {
                    queryParam.put("latestProgress", apiSearchReportListDto.getLatestProgress());
                }
                if (StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress()) && Integer.valueOf(apiSearchReportListDto.getLatestProgress()) == 13505) {
                    queryParam.put("confirmStatus", apiSearchReportListDto.getConfirmStatus());
                } else {
                    queryParam.put("confirmStatus", "13603");
                }
            }
            queryParam.put("dateTypeKbn", apiSearchReportListDto.getDateTypeKbn());
            queryParam.put("dateKbn", apiSearchReportListDto.getDateType());
            queryParam.put("dateStart", apiSearchReportListDto.getDateMin());
            queryParam.put("dateEnd", apiSearchReportListDto.getDateMax());
            queryParam.put("estateId", apiSearchReportListDto.getEstateId());
            queryParam.put("estateNm", apiSearchReportListDto.getSearchContent());
            queryParam.put("companyId", apiSearchReportListDto.getCompanyId());
            queryParam.put("customerNmWeiXin", apiSearchReportListDto.getCustomerNmWeiXin());
            queryParam.put("empId", apiSearchReportListDto.getEmpId());
            if ("weixin".equals(apiSearchReportListDto.getSource())) {
                queryParam.put("webFlg", "2");// 用于区分外部接口(微信)
            } else {
                queryParam.put("webFlg", "1");// 用于区分外部接口(房友)
            }
            //权限控制,参数转换
            authParam(queryParam);

            List<APIReportListResultDto> list = apiHouseLinkageService.reportList(queryParam);

            if (StringUtil.isEmpty(apiSearchReportListDto.getConfirmStatus()) && StringUtil.isNotEmpty(apiSearchReportListDto.getLatestProgress())) {
                queryParam.put("confirmStatus", "13602");
                queryParam.put("latestProgress", apiSearchReportListDto.getLatestProgress());
                //权限控制,参数转换
                authParam(queryParam);

                List<APIReportListResultDto> list2 = apiHouseLinkageService.reportList(queryParam);
                list.addAll(list2);
            }
            if (StringUtil.isNotEmpty(apiSearchReportListDto.getConfirmStatus()) && StringUtil.isEmpty(apiSearchReportListDto.getLatestProgress())) {
                if ("13601".equals(apiSearchReportListDto.getConfirmStatus())) {
                    queryParam.put("confirmStatus", "13603");
                    //权限控制,参数转换
                    authParam(queryParam);

                    List<APIReportListResultDto> list3 = apiHouseLinkageService.reportList(queryParam);
                    list.addAll(list3);
                }
            }
            rspMap.put("Result", list);

            rspMap.put("Status", "1");
            rspMap.put("Message", HouseLinkageConstant.SUCCESS);
            rspMap.put("IsSuccess", true);

        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "reportList", "", null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_SEARCH_REPORT_LIST_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * 查询-报备列表(微信专用)
     *
     * @param param 查询条件
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/searchReportList/{param}", method = RequestMethod.GET)
    public String searchReportList(@PathVariable String param, HttpServletRequest request) {
        //构建返回
        ResultData<List<APIReportListResultDto>> resultData = new ResultData<List<APIReportListResultDto>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            queryParam.put("cityNo", queryParam.get("CityNo"));
            queryParam.put("districtId", queryParam.get("District"));
            queryParam.put("estateType", queryParam.get("BuildType"));

            if (StringUtil.isEmpty((String) queryParam.get("ConfirmStatus")) && StringUtil.isNotEmpty((String) queryParam.get("LatestProgress"))) {
                if (StringUtil.isNotEmpty((String) queryParam.get("LatestProgress")) && Integer.valueOf((String) queryParam.get("LatestProgress")) < 13505) {
                    queryParam.put("latestProgress", Integer.valueOf((String) queryParam.get("LatestProgress")) + 1);
                } else {
                    queryParam.put("latestProgress", queryParam.get("LatestProgress"));
                }
                if (StringUtil.isNotEmpty((String) queryParam.get("LatestProgress")) && Integer.valueOf((String) queryParam.get("LatestProgress")) == 13505) {
                    queryParam.put("confirmStatus", "13601");
                } else {
                    queryParam.put("confirmStatus", "13603");
                }
            }
            if (StringUtil.isNotEmpty((String) queryParam.get("ConfirmStatus")) && StringUtil.isEmpty((String) queryParam.get("LatestProgress"))) {
                if ("13602".equals(queryParam.get("ConfirmStatus"))) {
                    queryParam.put("latestProgress", queryParam.get("LatestProgress"));
                    queryParam.put("confirmStatus", queryParam.get("ConfirmStatus"));
                } else {
                    queryParam.put("confirmStatus", "13601");
                }
            }
            if ("13602".equals(queryParam.get("ConfirmStatus")) && StringUtil.isNotEmpty((String) queryParam.get("LatestProgress"))) {
                queryParam.put("latestProgress", queryParam.get("LatestProgress"));
                queryParam.put("confirmStatus", queryParam.get("ConfirmStatus"));
            }
            if ("13601".equals(queryParam.get("ConfirmStatus")) && StringUtil.isNotEmpty((String) queryParam.get("LatestProgress"))) {
                if (StringUtil.isNotEmpty((String) queryParam.get("LatestProgress")) && Integer.valueOf((String) queryParam.get("LatestProgress")) < 13505) {
                    queryParam.put("latestProgress", Integer.valueOf((String) queryParam.get("LatestProgress")) + 1);
                } else {
                    queryParam.put("latestProgress", queryParam.get("LatestProgress"));
                }
                if (StringUtil.isNotEmpty((String) queryParam.get("LatestProgress")) && Integer.valueOf((String) queryParam.get("LatestProgress")) == 13505) {
                    queryParam.put("confirmStatus", queryParam.get("ConfirmStatus"));
                } else {
                    queryParam.put("confirmStatus", "13603");
                }
            }
            queryParam.put("dateTypeKbn", queryParam.get("DateTypeKbn"));
            queryParam.put("dateKbn", queryParam.get("DateType"));
            queryParam.put("dateStart", queryParam.get("DateMin"));
            queryParam.put("dateEnd", queryParam.get("DateMax"));
            queryParam.put("estateId", queryParam.get("EstateId"));
            queryParam.put("estateNm", queryParam.get("SearchContent"));
            queryParam.put("companyId", queryParam.get("CompanyId"));
            queryParam.put("customerNmWeiXin", queryParam.get("CustomerNmWeiXin"));
            queryParam.put("empId", queryParam.get("EmpId"));
            if ("weixin".equals(queryParam.get("Source"))) {
                queryParam.put("webFlg", "2");// 用于区分外部接口(微信)
            } else {
                queryParam.put("webFlg", "1");// 用于区分外部接口(房友)
            }
            //权限控制,参数转换
            authParam(queryParam);

            List<APIReportListResultDto> list = apiHouseLinkageService.reportList(queryParam);

            if (StringUtil.isEmpty((String) queryParam.get("ConfirmStatus")) && StringUtil.isNotEmpty((String) queryParam.get("LatestProgress"))) {
                queryParam.put("confirmStatus", "13602");
                queryParam.put("latestProgress", queryParam.get("LatestProgress"));
                //权限控制,参数转换
                authParam(queryParam);

                List<APIReportListResultDto> list2 = apiHouseLinkageService.reportList(queryParam);
                list.addAll(list2);
            }
            if (StringUtil.isNotEmpty((String) queryParam.get("ConfirmStatus")) && StringUtil.isEmpty((String) queryParam.get("LatestProgress"))) {
                if ("13601".equals(queryParam.get("ConfirmStatus"))) {
                    queryParam.put("confirmStatus", "13603");
                    //权限控制,参数转换
                    authParam(queryParam);

                    List<APIReportListResultDto> list3 = apiHouseLinkageService.reportList(queryParam);
                    list.addAll(list3);
                }
            }

            resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);
            return resultData.toString();

        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "searchReportList", "", null, "", "", e);
            resultData.setReturnCode("0");
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_SEARCH_REPORT_LIST_DELIVERY);
            return resultData.toString();
        }
    }

    /**
     * 查询-报备详细(房友专用)
     *
     * @param jsonStr 查询条件
     * @return
     */
    @RequestMapping(value = "/crm/NewHomeLinkage202ForWeb", method = RequestMethod.POST)
    public String reportDetail(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }
            logger.info("jsonData======>>" + jsonData);
            APISearchReportDetailDto apiSearchReportDetailDto = JSON.parseObject(jsonData, APISearchReportDetailDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("estateId", apiSearchReportDetailDto.getEstateId());
            queryParam.put("companyId", apiSearchReportDetailDto.getCompanyId());
            queryParam.put("customerId", apiSearchReportDetailDto.getCustomerId());

            if (StringUtil.isEmpty(apiSearchReportDetailDto.getEstateId()) || StringUtil.isEmpty(apiSearchReportDetailDto.getCompanyId()) || StringUtil.isEmpty(apiSearchReportDetailDto.getCustomerId())) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            } else {
                //权限控制,参数转换
                authParam(queryParam);

                APIReportDetailResultDto dto = apiHouseLinkageService.reportDetail(queryParam);
                rspMap.put("Result", dto);
            }
            rspMap.put("Status", "1");
            rspMap.put("Message", HouseLinkageConstant.SUCCESS);
            rspMap.put("IsSuccess", true);

        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "searchReportDetail", jsonStr, null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_SEARCH_REPORT_DETAIL_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * 查询-报备详细(微信专用)
     *
     * @param jsonStr 查询条件
     * @return
     */
    @RequestMapping(value = "/searchReportDetail", method = RequestMethod.POST)
    public String searchReportDetail(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<APIReportDetailResultDto> resultData = new ResultData<APIReportDetailResultDto>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);
            APISearchReportDetailDto apiSearchReportDetailDto = JSON.parseObject(jsonData, APISearchReportDetailDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("estateId", apiSearchReportDetailDto.getEstateId());
            queryParam.put("companyId", apiSearchReportDetailDto.getCompanyId());
            queryParam.put("customerId", apiSearchReportDetailDto.getCustomerId());

            if (StringUtil.isEmpty(apiSearchReportDetailDto.getEstateId()) || StringUtil.isEmpty(apiSearchReportDetailDto.getCompanyId()) || StringUtil.isEmpty(apiSearchReportDetailDto.getCustomerId())) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                return resultData.toString();
            } else {
                //权限控制,参数转换
                authParam(queryParam);

                APIReportDetailResultDto dto = apiHouseLinkageService.reportDetail(queryParam);
                resultData.setReturnData(dto);
                return resultData.toString();
            }
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "reportDetail", jsonStr, null, "", "", e);
            resultData.setReturnCode("0");
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_SEARCH_REPORT_DETAIL_DELIVERY);
            return resultData.toString();
        }
    }

    /**
     * TODO 废弃不用
     * 退筹
     *
     * @param jsonStr
     * @return
     */
    @RequestMapping(value = "/crm/getBackForWeb", method = RequestMethod.POST)
    public String getBackForWeb(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }
            logger.info("jsonData======>>" + jsonData);
            APISearchBackDto apiSearchBackDto = JSON.parseObject(jsonData, APISearchBackDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("estateId", apiSearchBackDto.getEstateId());
            queryParam.put("progress", apiSearchBackDto.getProgress());
            queryParam.put("companyId", apiSearchBackDto.getCompanyId());
            queryParam.put("customerId", apiSearchBackDto.getCustomerId());
            queryParam.put("confirmStatus", apiSearchBackDto.getConfirmStatus());

            if (StringUtil.isEmpty(apiSearchBackDto.getEstateId()) || StringUtil.isEmpty(apiSearchBackDto.getCompanyId()) || StringUtil.isEmpty(apiSearchBackDto.getCustomerId()) || StringUtil.isEmpty(apiSearchBackDto.getProgress()) || StringUtil.isEmpty(apiSearchBackDto.getConfirmStatus())) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            } else {
                // 进度!=认筹  确认状态!=未认定
                if (!"13503".equals((String) queryParam.get("progress")) && !"13603".equals((String) queryParam.get("confirmStatus"))) {
                    rspMap.put("Status", "4");
                    rspMap.put("Message", HouseLinkageConstant.FAIL_BACK_ERROR);
                    rspMap.put("IsSuccess", false);
                    return JsonUtil.parseToJson(rspMap);
                } else {
                    queryParam.put("latestProgress", 13506);// 退筹
                    queryParam.put("followDate", new Date());
                    int count = apiHouseLinkageService.updateBackProgress(queryParam);

                    if (count <= 0) {
                        rspMap.put("Status", "0");
                        rspMap.put("Message", HouseLinkageConstant.NULL_SELECT);
                        rspMap.put("IsSuccess", false);
                    } else {
                        rspMap.put("Status", "1");
                        rspMap.put("Message", HouseLinkageConstant.SUCCESS);
                        rspMap.put("IsSuccess", true);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "getBackForWeb", jsonStr, null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_BACK_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * 报备登记(房友专用)
     *
     * @param jsonStr 对象字符串
     * @return
     */
    @RequestMapping(value = "/crm/InsertReprotForWeb", method = RequestMethod.POST)
    public String create(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }
            logger.info("jsonData======>>" + jsonData);
            APIInsertReportDto apiInsertDto = JSON.parseObject(jsonData, APIInsertReportDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("estateId", apiInsertDto.getEstateId());
            queryParam.put("estateNm", apiInsertDto.getEstateName());
            queryParam.put("companyNm", apiInsertDto.getCompanyName());
            queryParam.put("companyId", apiInsertDto.getCompanyId());
            queryParam.put("customerId", apiInsertDto.getCustomerId());
            //房友报备来源
            queryParam.put("customerFrom", "17403");
            queryParam.put("customerNm", apiInsertDto.getCustomerName());
            queryParam.put("customerTel", apiInsertDto.getCustomerPhone());
            queryParam.put("customerParty", apiInsertDto.getCustomerRelation());
            queryParam.put("partyNm", apiInsertDto.getKinsfolkName());
            queryParam.put("partyTel", apiInsertDto.getKinsfolkPhone());
            queryParam.put("storeId", apiInsertDto.getStoreId());
            queryParam.put("storeNm", apiInsertDto.getStoreName());
            queryParam.put("deptId", apiInsertDto.getDepartmentId());
            queryParam.put("deptNm", apiInsertDto.getDepartmentName());
            queryParam.put("empId", apiInsertDto.getEmployeeId());
            queryParam.put("empNm", apiInsertDto.getEmployeeName());
            //queryParam.put("cityNo", apiInsertDto.getCityId());
            queryParam.put("customerNum", apiInsertDto.getCustomerNum());// 客户人数
            queryParam.put("validRelationDate", apiInsertDto.getValidRelationDate());// 带看时间
            queryParam.put("customerRequire", apiInsertDto.getCustomerRequire());// 客户需求
            queryParam.put("memo", apiInsertDto.getMemo());// 备注
            queryParam.put("source", apiInsertDto.getSource());// 来源

            if (StringUtil.isEmpty(apiInsertDto.getEstateId()) || StringUtil.isEmpty(apiInsertDto.getEstateName()) || StringUtil.isEmpty(apiInsertDto.getCompanyId()) || StringUtil.isEmpty(apiInsertDto.getCompanyName()) || StringUtil.isEmpty(apiInsertDto.getCustomerId()) || StringUtil.isEmpty(apiInsertDto.getCustomerName()) || StringUtil.isEmpty(apiInsertDto.getCustomerPhone()) || StringUtil.isEmpty(apiInsertDto.getCustomerNum()) || StringUtil.isEmpty(apiInsertDto.getValidRelationDate()) || StringUtil.isEmpty(apiInsertDto.getEmployeeId()) || StringUtil.isEmpty(apiInsertDto.getEmployeeName()) || StringUtil.isEmpty(apiInsertDto.getCityId()) || StringUtil.isEmpty(apiInsertDto.getSource())) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }
            if ("fangyou".equals(apiInsertDto.getSource())) {
                if (StringUtil.isEmpty(apiInsertDto.getDepartmentId()) || StringUtil.isEmpty(apiInsertDto.getDepartmentName())) {
                    rspMap.put("Status", "3");
                    rspMap.put("Message", HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                    rspMap.put("IsSuccess", false);
                    return JsonUtil.parseToJson(rspMap);
                }
            }
            if (apiHouseLinkageService.checkExistReport(queryParam)) {
                rspMap.put("Status", "2");
                rspMap.put("Message", HouseLinkageConstant.REPEAT_REPORT);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }

            int count = apiHouseLinkageService.saveReport(queryParam);
            if (count <= 0) {
                rspMap.put("Status", "0");
                rspMap.put("Message", HouseLinkageConstant.INSERT_DATE_ERROR);
                rspMap.put("IsSuccess", false);
            } else {
                rspMap.put("Status", "1");
                rspMap.put("Message", HouseLinkageConstant.SUCCESS);
                rspMap.put("IsSuccess", true);
            }


        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "create", jsonStr, null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * 报备登记(后台专用)
     *
     * @param jsonStr 对象字符串
     * @return
     */
    @RequestMapping(value = "/insertReportBack", method = RequestMethod.POST)
    public String insertReportBack(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);
            APIInsertReportDto apiInsertDto = JSON.parseObject(jsonData, APIInsertReportDto.class);


            //check门店维护人
            Map<String, Object> reqMap = new HashMap<>();
            reqMap.put("storeId", apiInsertDto.getStoreId());
            ResultData<?> resultData1 = storeMaintainerService.chkMaintainer(reqMap);
            if (ReturnCode.FAILURE.equals(resultData1.getReturnCode())) {
                return resultData1.toString();
            }

            // 报备日期和带看日期加入关账判断
            // added by wang kanlin 2017/09/12
            Map<?, ?> switchMonth  = null;
            try {
                Map<String, Object> queryParam = JsonUtil.parseToObject(jsonStr, Map.class);
                queryParam.put("cityNo", queryParam.get("cityId"));
                //switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(queryParam);
                switchMonth = commonMapper.checkCitySwitchMonth(queryParam.get("cityId").toString());
                int month = Integer.valueOf(switchMonth.get("month").toString());
                int year = Integer.valueOf(switchMonth.get("year").toString());
                Calendar c = Calendar.getInstance();
                c.set(year, month, 1, 0, 0, 0);
                //c.add(Calendar.MONTH, 1);
                c.set(Calendar.MILLISECOND, 0);
                Date switchDate = c.getTime();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date reportDate = format.parse(apiInsertDto.getReportDate());
//    			if (reportDate.before(switchDate)) {
//    				resultData.setReturnCode("3");
//                    resultData.setFail(HouseLinkageConstant.FAIL_REPORT_DATE_INVALID.replace("y", year+"").replace("m", month+""));
//                    return resultData.toString();
//    			}
                if (apiInsertDto.getValidRelationDate() != null && !"".equals(apiInsertDto.getValidRelationDate())) {
                    Date relationDate = format.parse(apiInsertDto.getValidRelationDate());
                    if (relationDate.before(switchDate)) {
                        resultData.setReturnCode("3");
                        resultData.setFail(HouseLinkageConstant.FAIL_REPORT_DATE_INVALID.replace("y", year + "").replace("m", month + ""));
                        return resultData.toString();
                    }
                }
            } catch (Exception e) {
                logger.error("api.houseLinkage", "APIHouseLinkageController", "insertReport", jsonStr, null, "", "", e);
                resultData.setReturnCode("0");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
                return resultData.toString();
            }

            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("estateId", apiInsertDto.getEstateId());
            queryParam.put("estateNm", apiInsertDto.getEstateName());
            queryParam.put("companyNm", apiInsertDto.getCompanyName());
            queryParam.put("companyId", apiInsertDto.getCompanyId());
            queryParam.put("customerId", apiInsertDto.getCustomerId());
            //后台报备来源
            queryParam.put("customerFrom", "17401");
            queryParam.put("customerNm", apiInsertDto.getCustomerName());
            queryParam.put("customerTel", apiInsertDto.getCustomerPhone());
            queryParam.put("customerParty", apiInsertDto.getCustomerRelation());
            queryParam.put("partyNm", apiInsertDto.getKinsfolkName());
            queryParam.put("partyTel", apiInsertDto.getKinsfolkPhone());
            queryParam.put("storeId", apiInsertDto.getStoreId());
            queryParam.put("storeNm", apiInsertDto.getStoreName());
            queryParam.put("deptId", apiInsertDto.getDepartmentId());
            queryParam.put("deptNm", apiInsertDto.getDepartmentName());
            queryParam.put("empId", apiInsertDto.getEmployeeId());
            queryParam.put("reportDate", apiInsertDto.getReportDate());// 报备时间
            queryParam.put("empNm", apiInsertDto.getEmployeeName());
            queryParam.put("cityNo", apiInsertDto.getCityId());
            queryParam.put("customerNum", apiInsertDto.getCustomerNum());// 客户人数
            queryParam.put("validRelationDate", apiInsertDto.getValidRelationDate());// 带看时间
            queryParam.put("customerRequire", apiInsertDto.getCustomerRequire());// 客户需求
            queryParam.put("memo", apiInsertDto.getMemo());// 备注
            queryParam.put("source", apiInsertDto.getSource());// 来源
            queryParam.put("contactId", apiInsertDto.getContactId());// 门店维护人
            queryParam.put("contactNm", apiInsertDto.getContactNm());// 门店维护人姓名
            // 门店的城市编号
            queryParam.put("storeCityNo", apiInsertDto.getStoreCityNo());
            queryParam.put("createId", apiInsertDto.getCreateId());
            queryParam.put("fileList", apiInsertDto.getFileList());


            //upt
            queryParam.put("reportAgent", apiInsertDto.getReportAgent());// 
            queryParam.put("reportAgentTel", apiInsertDto.getReportAgentTel());//
            queryParam.put("customerIdTwo", apiInsertDto.getCustomerIdTwo());// 
            queryParam.put("customerNmTwo", apiInsertDto.getCustomerNmTwo());//
            queryParam.put("customerTelTwo", apiInsertDto.getCustomerTelTwo());// 

            if (StringUtil.isEmpty(apiInsertDto.getEstateId()) || StringUtil.isEmpty(apiInsertDto.getEstateName()) || StringUtil.isEmpty(apiInsertDto.getCompanyId()) || StringUtil.isEmpty(apiInsertDto.getCompanyName()) || StringUtil.isEmpty(apiInsertDto.getCustomerId()) || StringUtil.isEmpty(apiInsertDto.getCustomerName()) || StringUtil.isEmpty(apiInsertDto.getCustomerPhone()) || StringUtil.isEmpty(apiInsertDto.getEmployeeId()) || StringUtil.isEmpty(apiInsertDto.getEmployeeName()) || StringUtil.isEmpty(apiInsertDto.getCityId()) || StringUtil.isEmpty(apiInsertDto.getSource())) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                return resultData.toString();
            }
            if ("fangyou".equals(apiInsertDto.getSource())) {
                if (StringUtil.isEmpty(apiInsertDto.getDepartmentId()) || StringUtil.isEmpty(apiInsertDto.getDepartmentName())) {
                    resultData.setReturnCode("3");
                    resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                    return resultData.toString();
                }
            }
//            if (apiHouseLinkageService.checkExistReport(queryParam))
//            {
//                resultData.setReturnCode("2");
//                resultData.setFail(HouseLinkageConstant.REPEAT_REPORT);
//                return resultData.toString();
//            }

            int count = apiHouseLinkageService.saveReport(queryParam);
            if (count <= 0) {
                resultData.setReturnCode("0");
                resultData.setFail(HouseLinkageConstant.INSERT_DATE_ERROR);
                return resultData.toString();
            } else {
                return resultData.toString();
            }
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "insertReport", jsonStr, null, "", "", e);
            resultData.setReturnCode("0");
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            return resultData.toString();
        }
    }

    /**
     * 报备登记(微信专用)
     *
     * @param jsonStr 对象字符串
     * @return
     */
    @RequestMapping(value = "/insertReport", method = RequestMethod.POST)
    public String insertReport(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);
            APIInsertReportDto apiInsertDto = JSON.parseObject(jsonData, APIInsertReportDto.class);

            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("estateId", apiInsertDto.getEstateId());
            queryParam.put("estateNm", apiInsertDto.getEstateName());
            queryParam.put("companyNm", apiInsertDto.getCompanyName());
            queryParam.put("companyId", apiInsertDto.getCompanyId());
            queryParam.put("customerId", apiInsertDto.getCustomerId());
            //微信报备来源
            queryParam.put("customerFrom", "17402");
            queryParam.put("customerNm", apiInsertDto.getCustomerName());
            queryParam.put("customerTel", apiInsertDto.getCustomerPhone());
            queryParam.put("customerParty", apiInsertDto.getCustomerRelation());
            queryParam.put("partyNm", apiInsertDto.getKinsfolkName());
            queryParam.put("partyTel", apiInsertDto.getKinsfolkPhone());
            queryParam.put("storeId", apiInsertDto.getStoreId());
            queryParam.put("storeNm", apiInsertDto.getStoreName());
            queryParam.put("deptId", apiInsertDto.getDepartmentId());
            queryParam.put("deptNm", apiInsertDto.getDepartmentName());
            queryParam.put("empId", apiInsertDto.getEmployeeId());
            queryParam.put("empNm", apiInsertDto.getEmployeeName());
            queryParam.put("cityNo", apiInsertDto.getCityId());
            queryParam.put("customerNum", apiInsertDto.getCustomerNum());// 客户人数
            queryParam.put("validRelationDate", apiInsertDto.getValidRelationDate());// 带看时间
            queryParam.put("reportDate", apiInsertDto.getReportDate());// 报备时间
            queryParam.put("customerRequire", apiInsertDto.getCustomerRequire());// 客户需求
            queryParam.put("memo", apiInsertDto.getMemo());// 备注
            queryParam.put("source", apiInsertDto.getSource());// 来源
            queryParam.put("contactId", apiInsertDto.getContactId());// 门店维护人
            queryParam.put("contactNm", apiInsertDto.getContactNm());// 门店维护人姓名

            if (StringUtil.isEmpty(apiInsertDto.getEstateId()) || StringUtil.isEmpty(apiInsertDto.getEstateName()) || StringUtil.isEmpty(apiInsertDto.getCompanyId()) || StringUtil.isEmpty(apiInsertDto.getCompanyName()) || StringUtil.isEmpty(apiInsertDto.getCustomerId()) || StringUtil.isEmpty(apiInsertDto.getCustomerName()) || StringUtil.isEmpty(apiInsertDto.getCustomerPhone()) || StringUtil.isEmpty(apiInsertDto.getReportDate()) || StringUtil.isEmpty(apiInsertDto.getEmployeeId()) || StringUtil.isEmpty(apiInsertDto.getEmployeeName()) || StringUtil.isEmpty(apiInsertDto.getCityId()) || StringUtil.isEmpty(apiInsertDto.getSource())) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                return resultData.toString();
            }
            if ("fangyou".equals(apiInsertDto.getSource())) {
                if (StringUtil.isEmpty(apiInsertDto.getDepartmentId()) || StringUtil.isEmpty(apiInsertDto.getDepartmentName())) {
                    resultData.setReturnCode("3");
                    resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                    return resultData.toString();
                }
            }
            if (apiHouseLinkageService.checkExistReport(queryParam)) {
                resultData.setReturnCode("2");
                resultData.setFail(HouseLinkageConstant.REPEAT_REPORT);
                return resultData.toString();
            }

            int count = apiHouseLinkageService.saveReport(queryParam);
            if (count <= 0) {
                resultData.setReturnCode("0");
                resultData.setFail(HouseLinkageConstant.INSERT_DATE_ERROR);
                return resultData.toString();
            } else {
                return resultData.toString();
            }
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "insertReport", jsonStr, null, "", "", e);
            resultData.setReturnCode("0");
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            return resultData.toString();
        }
    }

    /**
     * @param jsonStr
     * @param request
     * @return
     * @Title: getEstateList
     * @Description: 楼盘列表(房友专用)
     */
    @RequestMapping(value = "/crm/NewHomeLinkage101ForWeb", method = RequestMethod.POST)
    public String getEstateList(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        String jsonData;
        try {
            jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }
            logger.info("jsonData======>>" + jsonData);

            Map<String, Object> queryParam = new HashMap<>();
            JSONObject jsonobject = JSONObject.parseObject(jsonData);
            queryParam.put("cityNo", (String) jsonobject.get("CityNo"));
            queryParam.put("saleStatus", (String) jsonobject.get("SaleStatus"));
            queryParam.put("districtId", (String) jsonobject.get("District"));
            queryParam.put("areaId", (String) jsonobject.get("Area"));
            queryParam.put("avgPriceMin", (String) jsonobject.get("AvgPriceMin"));
            queryParam.put("avgPriceMax", (String) jsonobject.get("AvgPriceMax"));
            queryParam.put("buildType", (String) jsonobject.get("BuildType"));
            queryParam.put("awardType", (String) jsonobject.get("AwardType"));
            queryParam.put("searchContent", (String) jsonobject.get("SearchContent"));
            queryParam.put("releaseStatus", DictionaryConstants.ESTATE_PUBLISH_YES);
            String roomCount = (String) jsonobject.get("RoomCount");

            List<String> roomCountList = new ArrayList<>();
            if (StringUtil.isNotEmpty(roomCount)) {
                if ("6".equals(roomCount)) {
                    for (int k = 6; k <= 50; k++) {
                        roomCountList.add(k + "");
                    }
                } else {
                    roomCountList.add(roomCount);
                }
            }
            if (null != roomCountList && !roomCountList.isEmpty()) {
                String[] arr = (String[]) roomCountList.toArray(new String[roomCountList.size()]);
                queryParam.put("roomCount", arr);
            }

            String orderName = "";
            String orderType = "";
            int orderFlg = 0;
            if (null != jsonobject.get("OrderBy")) {
                JSONObject orderBy = JSONObject.parseObject(jsonobject.get("OrderBy").toString());
                String openTime = (String) orderBy.get("OpenTime");
                String commissions = (String) orderBy.get("Commissions");
                String avgPrice = (String) orderBy.get("AvgPrice");

                if (StringUtil.isNotEmpty(openTime)) {
                    orderType = openTime;
                    if (orderFlg != 0) {
                        orderName += ", e.ReleaseDt";
                    } else {
                        orderName = "e.ReleaseDt";
                    }
                    orderFlg++;
                }

                if (StringUtil.isNotEmpty(commissions)) {
                    orderType = commissions;
                    if (orderFlg != 0) {
                        orderName += ", r.CommissionSort";
                    } else {
                        orderName = "r.CommissionSort";
                    }
                    orderFlg++;
                }

                if (StringUtil.isNotEmpty(avgPrice)) {
                    orderType = avgPrice;
                    if (orderFlg != 0) {
                        if (orderType.equals("DESC")) {
                            orderName += ", e.SalePriceUnitMax";
                        } else if (orderType.equals("ASC")) {
                            orderName += ", e.SalePriceUnitMin";
                        }
                    } else {
                        if (orderType.equals("DESC")) {
                            orderName = "e.SalePriceUnitMax";
                        } else if (orderType.equals("ASC")) {
                            orderName = "e.SalePriceUnitMin";
                        }
                    }
                    orderFlg++;
                }
                if (orderFlg != 0) {
                    queryParam.put("orderBy", orderName);
                    queryParam.put("orderType", orderType);
                } else {
                    queryParam.put("orderBy", "e.ReleaseDt");
                    queryParam.put("orderType", "DESC");
                }
            }
            //权限控制,参数转换
            authParam(queryParam);

            rspMap.put("Status", "1");
            rspMap.put("Message", HouseLinkageConstant.SUCCESS);
            rspMap.put("IsSuccess", true);
            rspMap.put("Result", apiHouseLinkageService.getEstateList(queryParam));
        } catch (UnsupportedEncodingException e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "getEstateList", jsonStr, null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_SEARCH_ESTATE_LIST_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "getEstateList", "", null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_SEARCH_ESTATE_LIST_DELIVERY);
            rspMap.put("IsSuccess", false);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * @param jsonStr
     * @return
     * @Title: getEstateDetail
     * @Description: 获取楼盘详情(房友专用)
     */
    @RequestMapping(value = "/crm/loadNewHomeLinkage102ForWeb", method = RequestMethod.POST)
    public String getEstateDetail(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        Map<String, Object> rspMap = new HashMap<String, Object>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            }
            logger.info("jsonData======>>" + jsonData);

            Map<String, Object> queryParam = new HashMap<>();

            JSONObject jsonobject = JSONObject.parseObject(jsonData);
            queryParam.put("cityNo", (String) jsonobject.get("CityNo"));
            queryParam.put("estateId", (String) jsonobject.get("Id"));

            if (StringUtil.isEmpty((String) queryParam.get("estateId")) || StringUtil.isEmpty((String) queryParam.get("cityNo"))) {
                rspMap.put("Status", "3");
                rspMap.put("Message", HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                rspMap.put("IsSuccess", false);
                return JsonUtil.parseToJson(rspMap);
            } else {

                //权限控制,参数转换
                authParam(queryParam);

                rspMap.put("Status", "1");
                rspMap.put("Message", HouseLinkageConstant.SUCCESS);
                rspMap.put("IsSuccess", true);
                rspMap.put("Result", apiHouseLinkageService.getEstateDetail(queryParam));
            }
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "getEstateDetail", jsonStr, null, "", "", e);
            rspMap.put("Status", "0");
            rspMap.put("Message", HouseLinkageConstant.FAIL_REMOTE_SEARCH_ESTATE_DETAIL_DELIVERY);
            rspMap.put("IsSuccess", false);
            return JsonUtil.parseToJson(rspMap);
        }
        logger.info(JsonUtil.parseToJson(rspMap));
        return JsonUtil.parseToJson(rspMap);
    }

    /**
     * @param param
     * @return
     * @Title: wechatEstateList
     * @Description: 获取楼盘详情(微信专用)
     */
    @RequestMapping(value = "wechat/estate/q/{param}/", method = RequestMethod.GET)
    public String wechatEstateList(@PathVariable String param) {
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<APIWechatEstateListDto>> resultData = new ResultData<List<APIWechatEstateListDto>>();
        try {
            resultData = this.apiHouseLinkageService.getWechatEstateList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("api.houseLinkage", "APIHouseLinkageController", "wechatEstateList", "", null, "", "", e);
        }
        String result = resultData.toString();
        return result;
    }

    /**
     * @param id
     * @return
     * @Title: wechatEstateDetail
     * @Description: 获取楼盘详情(微信专用)
     */
    @RequestMapping(value = "wechat/estate/{id}/", method = RequestMethod.GET)
    public String wechatEstateDetail(@PathVariable int id) {
        //构建返回
        ResultData<APIEstateDetailResultDto> resultData = new ResultData<APIEstateDetailResultDto>();
        try {
            resultData = this.apiHouseLinkageService.getWechatEstateDetail(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("api.houseLinkage", "APIHouseLinkageController", "wechatEstateDetail", "", null, "", "", e);
        }
        return resultData.toString();
    }


    /**
     * 报备登记(房友专用新)  insertReportBack
     *
     * @param jsonStr 对象字符串
     * @return
     */
    @RequestMapping(value = "/addFangyouReport", method = RequestMethod.POST)
    public String addFangyouReport(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<String> resultData = new ResultData<>();
        FangyouReportLog fangyouReportLog = new FangyouReportLog();
        fangyouReportLog.setTypeId("1");
        fangyouReportLog.setUserIdCreate(null);

        String cityNo = null;
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            fangyouReportLog.setReqParamJson(jsonData);
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            logger.info("jsonData======>>" + jsonData);

            ApproRegisterDto apiInsertDto = JSON.parseObject(jsonData, ApproRegisterDto.class);


            String opEstateId = apiInsertDto.getProjectNo();//房友项目编号
            String fyReportId = apiInsertDto.getReportId();//房友报备ID
            String reportAgent = apiInsertDto.getReportAgent();//报备经纪人
            String reportAgentTel = apiInsertDto.getReportAgentTel();//报备经纪人手机号

            if (StringUtil.isEmpty(opEstateId) || StringUtil.isEmpty(fyReportId)) {
                resultData.setFail("项目编号和房友订单编号不能为空");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            List<Estate> estateList = estatetService.queryByOpEstateId(opEstateId);
            Company companyDb = companyService.getByCompanyNo(apiInsertDto.getCompanyNo());
            Store storeDb = storeService.getStoreByStoreNo(apiInsertDto.getStoreNo());
            Report reportDb = reportService.getByFyReportId(fyReportId);
            if (CollectionUtils.isEmpty(estateList)) {
                resultData.setFail("CRM系统中项目不存在");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }

            if (companyDb == null) {
                resultData.setFail("公司不存在");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            if (storeDb == null) {
                resultData.setFail("门店不存在");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            if (reportDb != null && reportDb.getReportId() != null) {
                resultData.setFail("房友订单编号重复");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
//            if(StringUtil.isEmpty(apiInsertDto.getOpUserCode())){
//            	resultData.setFail("操作人工号不能为空");
//                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
//                fangyouReportLogService.addLog(fangyouReportLog);
//                return JsonUtil.parseToJson(resultData);
//            }

            String opUserCode = apiInsertDto.getOpUserCode();
            User userDb = null;
            if (StringUtil.isNotEmpty(opUserCode)) {
                userDb = userAPI.getUserByUserCode(opUserCode);
            }
            if (userDb == null) {
                userDb = new User();
            }


            Estate estateDb = estateList.get(0);


            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("estateId", estateDb.getEstateId());
            queryParam.put("estateNm", estateDb.getEstateNm());
            queryParam.put("companyNm", companyDb.getCompanyName());
            queryParam.put("companyId", companyDb.getCompanyNo());
            queryParam.put("customerId", RandomStringUtils.randomNumeric(10));
            //房友报备来源

            queryParam.put("customerFrom", "17403");
            queryParam.put("customerNm", apiInsertDto.getCustomerNm());
            queryParam.put("customerTel", apiInsertDto.getCustomerTel());
            queryParam.put("customerIdTwo", RandomStringUtils.randomNumeric(10));// 
            queryParam.put("customerNmTwo", apiInsertDto.getCustomerNmTwo());//
            queryParam.put("customerTelTwo", apiInsertDto.getCustomerTelTwo());// 

            queryParam.put("customerParty", null);
            queryParam.put("partyNm", null);
            queryParam.put("partyTel", null);
            queryParam.put("storeId", storeDb.getStoreId());
            queryParam.put("storeNm", storeDb.getName());
            queryParam.put("deptId", null);
            queryParam.put("deptNm", null);
            queryParam.put("empId", userDb.getUserCode());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            queryParam.put("reportDate", dateFormat.format(apiInsertDto.getReportDate()));// 报备时间
            queryParam.put("empNm", userDb.getUserName());
            queryParam.put("cityNo", storeDb.getCityNo());
            queryParam.put("customerNum", null);// 客户人数
            queryParam.put("validRelationDate", null);// 带看时间
            queryParam.put("customerRequire", null);// 客户需求
            queryParam.put("memo", null);// 备注
            queryParam.put("source", null);// 来源
            queryParam.put("contactId", storeDb.getMaintainer());// 门店维护人
            queryParam.put("contactNm", storeDb.getMaintainerName());// 门店维护人姓名

            // 门店的城市编号
            queryParam.put("storeCityNo", storeDb.getAcCityNo());
            queryParam.put("createId", userDb.getUserId());


            queryParam.put("fyReportId", fyReportId);// 房友报备ID
            queryParam.put("reportAgent", reportAgent);// 
            queryParam.put("reportAgentTel", reportAgentTel);// 
            queryParam.put("fangyouFileList", apiInsertDto.getFileIdList());


            int count = apiHouseLinkageService.saveReport(queryParam);
            if (count <= 0) {
                resultData.setFail("插入数据失败！");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            cityNo = storeDb.getAcCityNo();
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setFail("操作异常！");
            fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
            fangyouReportLogService.addLog(fangyouReportLog);
            logger.error("api.houseLinkage", "APIHouseLinkageController", "addFangyouReport", jsonStr, null, "", "", e);
            return JsonUtil.parseToJson(resultData);
        }
        resultData.setSuccess();
        resultData.setReturnData(cityNo);
        fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
        fangyouReportLogService.addLog(fangyouReportLog);
        return JsonUtil.parseToJson(resultData);
    }

    /**
     * 带看
     */
    @RequestMapping(value = "/seeConfirm", method = RequestMethod.POST)
    public String seeConfirm(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        FangyouReportLog fangyouReportLog = new FangyouReportLog();
        fangyouReportLog.setTypeId("2");
        fangyouReportLog.setUserIdCreate(null);
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            fangyouReportLog.setReqParamJson(jsonData);
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            logger.info("jsonData======>>" + jsonData);

            ApproShowDto apiInsertDto = JSON.parseObject(jsonData, ApproShowDto.class);

            String opEstateId = apiInsertDto.getProjectNo();//房友项目编号
            String fyReportId = apiInsertDto.getReportId();//房友报备ID
            if (StringUtil.isEmpty(opEstateId) || StringUtil.isEmpty(fyReportId)) {
                resultData.setFail("项目编号和房友订单编号不能为空");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            List<Estate> estateList = estatetService.queryByOpEstateId(opEstateId);
            Report reportDb = reportService.getByFyReportId(fyReportId);
            if (CollectionUtils.isEmpty(estateList)) {
                resultData.setFail("CRM系统中项目不存在");
                return JsonUtil.parseToJson(resultData);
            }
            if (reportDb == null) {
                resultData.setFail("CRM系统中报备不存在");
                return JsonUtil.parseToJson(resultData);
            }
            if (CollectionUtils.isEmpty(apiInsertDto.getFileIdList())) {
                resultData.setFail("带看单附件为空！");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
//            if(StringUtil.isEmpty(apiInsertDto.getOpUserCode())){
//            	resultData.setFail("操作人工号不能为空");
//                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
//                fangyouReportLogService.addLog(fangyouReportLog);
//                return JsonUtil.parseToJson(resultData);
//            }

            String opUserCode = apiInsertDto.getOpUserCode();
            User userDb = null;
            if (StringUtil.isNotEmpty(opUserCode)) {
                userDb = userAPI.getUserByUserCode(opUserCode);
            }
            if (userDb == null) {
                userDb = new User();
            }
            Integer latestProgress = 13502;
            Integer confirmStatus = 13603;
            if (!latestProgress.equals(reportDb.getLatestProgress()) || !confirmStatus.equals(reportDb.getConfirmStatus())) {
                resultData.setFail("报备单状态已变更，操作失败");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            Estate estateDb = estateList.get(0);
            Map<String, Object> queryParam = new HashMap<String, Object>();
            queryParam.put("reportId", reportDb.getReportId());
            queryParam.put("estateId", estateDb.getEstateId());
            //queryParam.put("buildingNo", "");
            queryParam.put("fileRecordMainIds", null);
            queryParam.put("id", reportDb.getId());
            queryParam.put("userName", userDb.getUserName());
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            queryParam.put("operateDate", dateFormat.format(apiInsertDto.getSeeDate()));// 报备时间
            queryParam.put("userCode", userDb.getUserCode());
            queryParam.put("status", "13502");
            resultData = this.sceneEstatetService.sceneRecogonitionConfirm(queryParam);

            if (!"200".equals(resultData.getReturnCode()) || !AppMsg.getString("COMMON.OPERATE_SUCCESS").equals(resultData.getReturnMsg())) {
                resultData.setReturnCode("-1");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            } else {
                FangyouReportFile fangyouReportFile = new FangyouReportFile();
                fangyouReportFile.setReportId(reportDb.getId());
                fangyouReportFile.setReportNo(reportDb.getReportId());
                fangyouReportFile.setTypeId("2");
                this.sceneEstatetService.insertFangyouFile(apiInsertDto.getFileIdList(), fangyouReportFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setFail("操作异常！");
            fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
            fangyouReportLogService.addLog(fangyouReportLog);
            logger.error("api.houseLinkage", "APIHouseLinkageController", "seeConfirm", jsonStr, null, "", "", e);
            return JsonUtil.parseToJson(resultData);
        }

        resultData.setSuccess();
        fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
        fangyouReportLogService.addLog(fangyouReportLog);
        return JsonUtil.parseToJson(resultData);
    }


    /**
     * 大定
     */
    @RequestMapping(value = "/roughConfirm", method = RequestMethod.POST)
    public String roughConfirm(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        FangyouReportLog fangyouReportLog = new FangyouReportLog();
        fangyouReportLog.setTypeId("3");
        fangyouReportLog.setUserIdCreate(null);
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            fangyouReportLog.setReqParamJson(jsonData);
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            logger.info("jsonData======>>" + jsonData);
            ApproDecideDto apiInsertDto = null;
            apiInsertDto = JSON.parseObject(jsonData, ApproDecideDto.class);

            String opEstateId = apiInsertDto.getProjectNo();//房友项目编号
            String fyReportId = apiInsertDto.getReportId();//房友报备ID
            if (CollectionUtils.isEmpty(apiInsertDto.getFileIdList())) {
                resultData.setFail("附件不能为空！");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            //类型type : 1022,带看单 ,   1023,大定单，1024，经纪人名片，
            if (apiInsertDto.getFileIdList() != null && apiInsertDto.getFileIdList().size() > 0) {
                boolean seeStr = true;
                boolean ddStr = true;
                boolean cardStr = true;
                for (FileDto f : apiInsertDto.getFileIdList()) {
                    String fileTypeCode = f.getType();
                    if ("1022".equals(fileTypeCode)) {
                        seeStr = false;
                    }
                    if ("1023".equals(fileTypeCode)) {
                        ddStr = false;
                    }
                    if ("1024".equals(fileTypeCode)) {
                        cardStr = false;
                    }
                }
                if (seeStr) {
                    resultData.setFail("带看单附件为空！");
                    fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                    fangyouReportLogService.addLog(fangyouReportLog);
                    return JsonUtil.parseToJson(resultData);
                }
                if (ddStr) {
                    resultData.setFail("大定单附件为空！");
                    fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                    fangyouReportLogService.addLog(fangyouReportLog);
                    return JsonUtil.parseToJson(resultData);
                }
                if (cardStr) {
                    resultData.setFail("甲方项目负责人名片附件为空！");
                    fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                    fangyouReportLogService.addLog(fangyouReportLog);
                    return JsonUtil.parseToJson(resultData);
                }
            }
            if (StringUtil.isEmpty(opEstateId) || StringUtil.isEmpty(fyReportId) || apiInsertDto.getRoughAmount() == null) {
                resultData.setFail("参数错误");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            List<Estate> estateList = estatetService.queryByOpEstateId(opEstateId);
            Report reportDb = reportService.getByFyReportId(fyReportId);
            if (CollectionUtils.isEmpty(estateList)) {
                resultData.setFail("CRM系统中项目不存在");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            if (reportDb == null) {
                resultData.setFail("CRM系统中报备不存在");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }

            if (StringUtil.isEmpty(apiInsertDto.getCustomerNm()) || StringUtil.isEmpty(apiInsertDto.getCustomerTel())) {
                resultData.setFail("客户信息不完整");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }

            Integer latestProgress = 13503;
            Integer latestProgressTwo = 13505;
            Integer confirmStatus = 13603;
            if (apiInsertDto.getType() == 0 && (!latestProgress.equals(reportDb.getLatestProgress()) || !confirmStatus.equals(reportDb.getConfirmStatus()))) {
                resultData.setFail("报备单状态已变更，大定新增失败");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }

            if (apiInsertDto.getType() == 1 && (!latestProgressTwo.equals(reportDb.getLatestProgress()) || !confirmStatus.equals(reportDb.getConfirmStatus()))) {
                resultData.setFail("报备单状态已变更，大定修改失败");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }

            if (apiInsertDto.getType() == 1 && "1".equals(reportDb.getRoughAuditStatus())) {
                resultData.setFail("报备单大定已经审核通过,不允许修改");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }

//            if(StringUtil.isEmpty(apiInsertDto.getOpUserCode())){
//            	resultData.setFail("操作人工号不能为空");
//                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
//                fangyouReportLogService.addLog(fangyouReportLog);
//                return JsonUtil.parseToJson(resultData);
//            }

            String opUserCode = apiInsertDto.getOpUserCode();
            User userDb = null;
            if (StringUtil.isNotEmpty(opUserCode)) {
                userDb = userAPI.getUserByUserCode(opUserCode);
            }
            if (userDb == null) {
                userDb = new User();
            }
            if (apiInsertDto.getType() == 0) {
                Estate estateDb = estateList.get(0);
                Map<String, Object> queryParam = new HashMap<String, Object>();
                queryParam.put("reportId", reportDb.getReportId());
                queryParam.put("estateId", estateDb.getEstateId());
                queryParam.put("roughAmount", apiInsertDto.getRoughAmount().toString());
                queryParam.put("fileRecordMainIds", null);
                queryParam.put("userName", userDb.getUserName());
                queryParam.put("userCode", userDb.getUserCode());
                queryParam.put("buildingNo", apiInsertDto.getBuildingNo());
                queryParam.put("roughArea", apiInsertDto.getRoughArea().toString());
                queryParam.put("id", reportDb.getId());
                queryParam.put("customerNm", apiInsertDto.getCustomerNm());
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                queryParam.put("operateDate", dateFormat.format(apiInsertDto.getRoughDate()));// 报备时间
                queryParam.put("customerTel", apiInsertDto.getCustomerTel());
                queryParam.put("status", "13504");
                queryParam.put("customerNmTwo", apiInsertDto.getCustomerNmTwo());//
                queryParam.put("customerTelTwo", apiInsertDto.getCustomerTelTwo());//
                resultData = this.sceneEstatetService.sceneRecogonitionConfirm(queryParam);
//                 System.out.println();
            } else if (apiInsertDto.getType() == 1) {
                resultData = this.sceneEstatetService.sceneRecogonitionConfirmUpt(apiInsertDto, reportDb);
//            	 System.out.println();
            } else {
                resultData.setFail("参数错误");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            }
            if (!"200".equals(resultData.getReturnCode()) || !AppMsg.getString("COMMON.OPERATE_SUCCESS").equals(resultData.getReturnMsg())) {
                resultData.setReturnCode("-1");
                fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
                fangyouReportLogService.addLog(fangyouReportLog);
                return JsonUtil.parseToJson(resultData);
            } else {
                FangyouReportFile fangyouReportFile = new FangyouReportFile();
                fangyouReportFile.setReportId(reportDb.getId());
                fangyouReportFile.setReportNo(reportDb.getReportId());
                fangyouReportFile.setTypeId("3");
                //TODO 房友上线后放开
                this.sceneEstatetService.insertFangyouFileDto(apiInsertDto.getFileIdList(), fangyouReportFile);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultData.setFail("操作异常！");
            fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
            fangyouReportLogService.addLog(fangyouReportLog);
            logger.error("api.houseLinkage", "APIHouseLinkageController", "roughConfirm", jsonStr, null, "", "", e);
            return JsonUtil.parseToJson(resultData);
        }

        resultData.setSuccess();
        fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
        fangyouReportLogService.addLog(fangyouReportLog);
        return JsonUtil.parseToJson(resultData);
    }

    /**
     * 报备登记(pc专用)
     *
     * @param jsonStr 对象字符串
     * @return
     */
    @RequestMapping(value = "/insertReportBack2", method = RequestMethod.POST)
    public String insertReportBack2(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);
            APIInsertReportDto apiInsertDto = JSON.parseObject(jsonData, APIInsertReportDto.class);

//            //check门店维护人
//            Map<String,Object> reqMap = new HashMap<>();
//            reqMap.put("storeId",apiInsertDto.getStoreId());
//            ResultData<?> resultData1 = storeMaintainerService.chkMaintainer(reqMap);
//            if(ReturnCode.FAILURE.equals(resultData1.getReturnCode())){
//                return resultData1.toString();
//            }
//            // 报备日期和带看日期加入关账判断
//            Map<String, String> switchMonth = null;
//    		try {
//    	        Map<String, Object> queryParam = JsonUtil.parseToObject(jsonStr, Map.class);
//    	        queryParam.put("cityNo", queryParam.get("cityId"));
//    			switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(queryParam);
//    			int month = Integer.valueOf(switchMonth.get("switchMonth"));
//    			int year = Integer.valueOf(switchMonth.get("switchYear"));
//    			Calendar c = Calendar.getInstance();
//    			c.set(year, month, 1, 0, 0, 0);
//    			c.set(Calendar.MILLISECOND, 0);
//    			Date switchDate = c.getTime();
//    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    			Date reportDate = format.parse(apiInsertDto.getReportDate());
//    			if(apiInsertDto.getValidRelationDate() != null && !"".equals(apiInsertDto.getValidRelationDate()))
//    			{
//    				Date relationDate = format.parse(apiInsertDto.getValidRelationDate());
//        			if (relationDate.before(switchDate)) {
//        				resultData.setReturnCode("3");
//                        resultData.setFail(HouseLinkageConstant.FAIL_REPORT_DATE_INVALID.replace("y", year+"").replace("m", month+""));
//                        return resultData.toString();
//        			}
//    			}
//    		} catch (Exception e) {
//                logger.error("api.houseLinkage", "APIHouseLinkageController", "insertReport", jsonStr, null, "", "", e);
//                resultData.setReturnCode("0");
//                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
//                return resultData.toString();
//    		}
//
            Map<String, Object> queryParam = new HashMap<String, Object>();

            queryParam.put("estateId", apiInsertDto.getEstateId());
            queryParam.put("estateNm", apiInsertDto.getEstateName());
            queryParam.put("companyNm", apiInsertDto.getCompanyName());
            queryParam.put("companyId", apiInsertDto.getCompanyId());
            queryParam.put("customerId", apiInsertDto.getCustomerId());
            //后台报备来源
            queryParam.put("customerFrom", "17401");
            queryParam.put("customerNm", apiInsertDto.getCustomerName());
            queryParam.put("customerTel", apiInsertDto.getCustomerPhone());
            queryParam.put("customerParty", apiInsertDto.getCustomerRelation());
            queryParam.put("partyNm", apiInsertDto.getKinsfolkName());
            queryParam.put("partyTel", apiInsertDto.getKinsfolkPhone());
            queryParam.put("storeId", apiInsertDto.getStoreId());
            queryParam.put("storeNm", apiInsertDto.getStoreName());
            queryParam.put("deptId", apiInsertDto.getDepartmentId());
            queryParam.put("deptNm", apiInsertDto.getDepartmentName());
            queryParam.put("empId", apiInsertDto.getEmployeeId());
            queryParam.put("reportDate", apiInsertDto.getReportDate());// 报备时间
            queryParam.put("empNm", apiInsertDto.getEmployeeName());
            queryParam.put("cityNo", apiInsertDto.getCityId());
            queryParam.put("customerNum", apiInsertDto.getCustomerNum());// 客户人数
            queryParam.put("validRelationDate", apiInsertDto.getValidRelationDate());// 带看时间
            queryParam.put("customerRequire", apiInsertDto.getCustomerRequire());// 客户需求
            queryParam.put("memo", apiInsertDto.getMemo());// 备注
            queryParam.put("source", apiInsertDto.getSource());// 来源
            queryParam.put("contactId", apiInsertDto.getContactId());// 门店维护人
            queryParam.put("contactNm", apiInsertDto.getContactNm());// 门店维护人姓名

            //业绩归属中心Id
            queryParam.put("centerGroupId", apiInsertDto.getCenterGroupId());
            queryParam.put("centerGroupName", apiInsertDto.getCenterGroupName());

            // 门店的城市编号
            queryParam.put("storeCityNo", apiInsertDto.getStoreCityNo());
            queryParam.put("createId", apiInsertDto.getCreateId());
            queryParam.put("fileList", apiInsertDto.getFileList());

            // 渠道ID
            queryParam.put("branchId", apiInsertDto.getBranchId());

            //upt
            queryParam.put("reportAgent", apiInsertDto.getReportAgent());// 
            queryParam.put("reportAgentTel", apiInsertDto.getReportAgentTel());//
            queryParam.put("customerIdTwo", apiInsertDto.getCustomerIdTwo());// 
            queryParam.put("customerNmTwo", apiInsertDto.getCustomerNmTwo());//
            queryParam.put("customerTelTwo", apiInsertDto.getCustomerTelTwo());// 

            if (StringUtil.isEmpty(apiInsertDto.getEstateId()) || StringUtil.isEmpty(apiInsertDto.getEstateName())
                    || StringUtil.isEmpty(apiInsertDto.getCompanyId()) || StringUtil.isEmpty(apiInsertDto.getCompanyName())
                    || StringUtil.isEmpty(apiInsertDto.getCustomerId()) || StringUtil.isEmpty(apiInsertDto.getCustomerName())
                    || StringUtil.isEmpty(apiInsertDto.getCustomerPhone()) || StringUtil.isEmpty(apiInsertDto.getEmployeeId())
                    || StringUtil.isEmpty(apiInsertDto.getEmployeeName()) || StringUtil.isEmpty(apiInsertDto.getCityId())
                    || StringUtil.isEmpty(apiInsertDto.getSource())) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                return resultData.toString();
            }
            if ("fangyou".equals(apiInsertDto.getSource())) {
                if (StringUtil.isEmpty(apiInsertDto.getDepartmentId()) || StringUtil.isEmpty(apiInsertDto.getDepartmentName())) {
                    resultData.setReturnCode("3");
                    resultData.setFail(HouseLinkageConstant.FAIL_MISSING_PARAMETER);
                    return resultData.toString();
                }
            }
            queryParam.put("estateCenterId",apiInsertDto.getEstateCenterId());
            int count = apiHouseLinkageService.saveReport2(queryParam);
            if (count <= 0) {
                resultData.setReturnCode("0");
                resultData.setFail(HouseLinkageConstant.INSERT_DATE_ERROR);
                return resultData.toString();
            } else {
                return resultData.toString();
            }
        } catch (Exception e) {
            logger.error("api.houseLinkage", "APIHouseLinkageController", "insertReport", jsonStr, null, "", "", e);
            resultData.setReturnCode("0");
            resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_REPORT_DELIVERY);
            return resultData.toString();
        }
    }
}
