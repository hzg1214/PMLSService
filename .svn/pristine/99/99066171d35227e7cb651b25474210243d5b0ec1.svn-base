package cn.com.eju.deal.scene.estate.service;

import cn.com.eju.deal.Report.model.ApproDecideDto;
import cn.com.eju.deal.Report.model.FileDto;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateDto;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSearchResultDto;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.dto.scene.estate.SceneEstateSearchResultDto;
import cn.com.eju.deal.dto.scene.estate.SceneRecognitionSearchResultDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateRuleMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.estate.model.EstateRule;
import cn.com.eju.deal.houseLinkage.estate.model.EstateSearchResult;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.AchieveMentChangeLogMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportFileMapper;
import cn.com.eju.deal.houseLinkage.report.dao.LnkReportYJFAMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFile;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.report.model.YFStatusSync;
import cn.com.eju.deal.houseLinkage.report.service.YFInterfaceService;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;
import cn.com.eju.deal.scene.commission.dao.*;
import cn.com.eju.deal.scene.estate.model.SceneRecognitionSearchResult;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("sceneEstatetService")
public class SceneEstateService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private EstateMapper estateMapper;

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private StatisticService statisticService;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private ReportDetailMapper reportDetailMapper;

    @Resource
    private EstateRuleMapper estateRuleMapper;

    @Resource
    private SceneInCommissionMapper sceneInCommissionMapper;

    @Resource
    private AchieveMentChangeLogMapper achieveMentChangeLogMapper;

    @Resource
    private FileRecordMainService fileRecordMainService;

    @Resource
    private FangyouReportFileMapper fangyouReportFileMapper;

    @Resource
    private UserMapper userMapper;

    @Resource(name = "fangyouReportLogService")
    private FangyouReportLogService fangyouReportLogService;

    @Resource
    private YFInterfaceService yFInterfaceService;

    @Resource
    LnkYjYjsrMapper lnkYjYjsrMapper;

    @Resource
    LnkYjYjfyMapper lnkYjYjfyMapper;

    @Resource
    LnkYjYjdyMapper lnkYjYjdyMapper;

    @Resource
    LnkYjNyMapper lnkYjNyMapper;

    @Resource
    LnkYjFyfyMapper lnkYjFyfyMapper;

    @Resource
    private LnkYjSjfyMapper lnkYjSjfyMapper;

    @Resource
    LnkReportYJFAMapper lnkReportYJFAMapper;
    /**
     * 查询-list
     *
     * @param queryParam
     * @return
     */
//    @Cacheable(cacheName = "testCache")
    public ResultData<List<SceneEstateSearchResultDto>> queryList(Map<?, ?> param) throws Exception {
        //构建返回
        ResultData<List<SceneEstateSearchResultDto>> resultData = new ResultData<List<SceneEstateSearchResultDto>>();
        //查询
        final List<EstateDto> list = estateMapper.getSceneEstateList(param);
        //转换
        List<SceneEstateSearchResultDto> estateDtoList = new ArrayList<SceneEstateSearchResultDto>();
        if (null != list && !list.isEmpty()) {
            SceneEstateSearchResultDto dto = null;
            for (int i = 0; i < list.size(); i++) {
                dto = new SceneEstateSearchResultDto();
                BeanUtils.copyProperties(list.get(i), dto);

                if (StringUtils.isNotBlank(list.get(i).getRealityCityNo()))
                    dto.setRealityCityNm(SystemParam.getCityNameByCityNo(list.get(i).getRealityCityNo()));
                if (StringUtils.isNotBlank(list.get(i).getCityNo()))
                    dto.setCityNoNm(SystemParam.getCityNameByCityNo(list.get(i).getCityNo()));
                dto.setAddress(list.get(i).getAddress());
                dto.setEstateType(list.get(i).getMgtKbn());
                dto.setEstateTypeNm(list.get(i).getMgtKbnStr());
                dto.setEstateNm(list.get(i).getEstateNm());
                estateDtoList.add(dto);
            }
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(estateDtoList);
        return resultData;
    }


    /**
     * 查询-状态未认定list
     *
     * @param queryParam
     * @return
     */
    //@Cacheable(cacheName = "testCache")
    public ResultData<List<SceneRecognitionSearchResultDto>> querySceneRecognitionList(Map<?, ?> param) throws Exception {
        Map<String, Object> reMap = (Map<String, Object>) param;

        //构建返回
        ResultData<List<SceneRecognitionSearchResultDto>> resultData = new ResultData<>();
        //转换
        List<SceneRecognitionSearchResultDto> recognitionDtoList = new ArrayList<>();
        //查询
        if (!StringUtil.isNotEmpty((String) reMap.get("progress"))) {
            reMap.put("progress", "13501,13502,13502,13503,13503,13504,13504,13505,13506,13506,13507,13507,13508,13508");
        }
        String[] progressTypeList = reMap.get("progress").toString().split(",");

//        List<Map<String, String>> proMapList = new ArrayList<>();
        String proMapListStr = "";
        for (int i = 0; i < progressTypeList.length; i++) {
            String proMapStr = "";
//            Map<String, String> proMap = new HashMap<>();
            //添加查询结佣
            if (13508 == Integer.valueOf(progressTypeList[i])) {
//                proMap.put("brokerageStatus", "22002,22003");
//                proMap.put("status", "13505");
//                proMap.put("confirmStatus", "13601");
                proMapStr = "(p.LatestProgress =13505 AND p.ConfirmStatus = 13601 AND p.brokerageStatus in ( 22002,22003))";
            } else if (13507 == Integer.valueOf(progressTypeList[i])) {
//                proMap.put("status", "13505");
//                proMap.put("brokerageStatus", "22001");
                if (i % 2 == 0) {
//                    proMap.put("confirmStatus", "13602");//无效
                    proMapStr = "(p.LatestProgress =13505 AND p.ConfirmStatus = 13602 AND p.brokerageStatus = 22001)";
                } else if (i % 2 == 1) {
//                    proMap.put("confirmStatus", "13601");//有效
                    proMapStr = "(p.LatestProgress =13505 AND p.ConfirmStatus = 13601 AND p.brokerageStatus = 22001)";
                }
            } else {
//                proMap.put("status", progressTypeList[i]);
//                proMap.put("brokerageStatus", "22001");
                if (i % 2 == 0) {
//                    proMap.put("confirmStatus", "13602");//无效
                    proMapStr = "(p.LatestProgress =" + progressTypeList[i] + " AND p.ConfirmStatus = 13602 AND p.brokerageStatus = 22001)";
                } else if (i % 2 == 1) {
//                    proMap.put("confirmStatus", "13603");//未认定
                    proMapStr = "(p.LatestProgress =" + progressTypeList[i] + " AND p.ConfirmStatus = 13603 AND p.brokerageStatus = 22001)";
                }
            }
//            proMapList.add(proMap);
            if (i == 0) {
                proMapListStr = proMapStr;
            } else {
                proMapListStr = proMapListStr + " OR " + proMapStr;
            }
        }
        if (StringUtil.isNotEmpty(proMapListStr)) {
            reMap.put("proMapList", proMapListStr);
        }

//        if (proMapList.size() > 0) {
//            reMap.put("proMapList", proMapList);
//        }
        String uuId= UUID.randomUUID().toString();
        logger.info("查询项目报备列表数据-" + uuId + "-querySceneRecognitionList-Start，" + reMap.toString());
        final List<SceneRecognitionSearchResult> list = reportMapper.selectSceneRecognitionList(reMap);
        logger.info("查询项目报备列表数据-" + uuId + "-querySceneRecognitionList-End");

        if (null != list && !list.isEmpty()) {
            SceneRecognitionSearchResultDto dto = null;
            for (int j = 0; j < list.size(); j++) {
                dto = new SceneRecognitionSearchResultDto();
                BeanUtils.copyProperties(list.get(j), dto);
                // 客户电话
                String tel = list.get(j).getCustomerTel();
                if (StringUtil.isNotEmpty(tel) && tel.length() == 11) {
//                    if ("1".equals(list.get(j).getHideNumberFlg())) {
//                        tel = tel.substring(0, 3) + "-****-" + tel.substring(7);
//                    } else {
//                        tel = tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7);
//                    }
                    if ("1".equals(list.get(j).getHideNumberFlg())) {
                        tel = tel.substring(0, 3) + "****" + tel.substring(7);
                    }

                }
                dto.setCustomerTel(tel);
                dto.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getLatestProgress())));
                dto.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(dto.getConfirmStatus())));
                dto.setAddressDetail(list.get(j).getAddressDetail());
                dto.setAddress(list.get(j).getAddress());
                dto.setCenterName(list.get(j).getCenterName());
                dto.setMaintainerName(list.get(j).getContactNm());
                if (dto.getConfirmStatus() != 13601) {
                    dto.setLatestProgressHandle(dto.getLatestProgress() - 1);
                    dto.setLatestProgressNmHandle(SystemParam.getDicValueByDicCode(String.valueOf(dto.getLatestProgressHandle())));
                    if (dto.getConfirmStatus() == 13603) {
                        dto.setConfirmStatusHandle(13601);
                    } else {
                        dto.setConfirmStatusHandle(13602);
                    }
                    dto.setConfirmStatusNmHandle(SystemParam.getDicValueByDicCode(String.valueOf(dto.getConfirmStatusHandle())));
                } else {
                    dto.setLatestProgressHandle(dto.getLatestProgress());
                    dto.setLatestProgressNmHandle(SystemParam.getDicValueByDicCode(String.valueOf(dto.getLatestProgressHandle())));
                    dto.setConfirmStatusHandle(dto.getConfirmStatus());
                    dto.setConfirmStatusNmHandle(SystemParam.getDicValueByDicCode(String.valueOf(dto.getConfirmStatusHandle())));
                }
                dto.setCustomerFromStr(SystemParam.getDicValueByDicCode(dto.getCustomerFrom() + ""));
                recognitionDtoList.add(dto);
            }
        }
        logger.info("查询项目报备列表数据-" + uuId + "convertData - End");
        resultData.setTotalCount((String) reMap.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(recognitionDtoList);
        return resultData;
    }

    /**
     * 认定报备
     *
     * @param queryParam
     * @return
     */
    public ResultData<Integer> sceneRecogonitionConfirm(Map<?, ?> param)
            throws Exception {
        ResultData<Integer> resultData = new ResultData<Integer>();
        int count = 0;
        String reportId = (String) param.get("reportId");
        String estateId = (String) param.get("estateId");
        String status = (String) param.get("status");
        String operateDate = (String) param.get("operateDate");
        String buildingNo = null;
        String buildingNoId = null;
        String wyTypeCode = null;

        String areaStr = null;
        String roughAreaStr = null;
        String roughAmountStr = null;
        String dealAmountStr = null;
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("estateId", estateId);

        Report report;
        ReportDetail updateDetail;
        ReportDetail insertDetail;
        int latestProgress;//当前最新进度
        int nextProgress;//下一个进度
        int commissionProgress;//结佣进度
        int currProgress = Integer.parseInt(status);// 当前需要认定的进度
        EstateRule rule = estateRuleMapper.queryList(queryParam).get(0);
        Date validateDate;

        //更新报备表
        queryParam.clear();
        queryParam.put("reportId", reportId);
        report = reportMapper.getReport(queryParam).get(0);
        latestProgress = report.getLatestProgress();
        //带看重复问题解决
        if (13502 == currProgress && 13502 != latestProgress) {
            resultData.setReturnMsg("系统中该订单编号已有带看信息！");
            resultData.setReturnData(202);
            return resultData;
        }

        //查询是否已经大定过了，如果已大定过则不让再次大定
        if (13504 == currProgress && 13505 == latestProgress) {
            resultData.setReturnMsg("系统中该订单编号已有大定信息！");
            resultData.setReturnData(202);
            return resultData;
        }

        if (13505 == currProgress && 13505 != latestProgress) {//成销操作判断 (成销操作前必须大定,如果相等则表示已经大定  )
            resultData.setReturnMsg("成销前必须进行大定操作");
            resultData.setReturnData(201);
            return resultData;
        }

        nextProgress = currProgress < 13505 ? currProgress + 1 : currProgress;//成交以后就没有下一个进度
//    		nextProgress = Integer.parseInt(status);
        commissionProgress = nextProgress == 13505 ? 13507 : 0;//认定成交是需要更新结佣进度
        if (!"13602".equals(status)) { // 非无效场合
            report.setLatestProgress(nextProgress);
            report.setCommissionProgress(commissionProgress);
            validateDate = new Date();
            validateDate.setTime(new Date().getTime() + rule.getRelationProtectPeriod().longValue() * 24 * 3600 * 1000);
            report.setValidDate(validateDate);
            report.setFollowDate(new Date());
            if (currProgress == 13505) {
                report.setConfirmStatus(13601);
            }
        } else {
            report.setConfirmStatus(13602);
        }
        report.setUptDt(new Date());
        if (!status.equals("13602")) {
            String checkStr = setDate(report, status, operateDate);
            if (StringUtil.isNotEmpty(checkStr)) {
                resultData.setReturnMsg(checkStr);
                resultData.setReturnData(201);
                return resultData;
            }
        } else {
            //添加日志信息
            AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
            achieveMentChangeLog.setRelateId(report.getId());
            //报备->无效
            String changeMsg = "";
            if (latestProgress == 13502) {
                changeMsg = "案件记录报备（报备时间：" + DateUtil.fmtDate(report.getReportDate(), "yyyy-MM-dd") + "）认定为无效";
            }
            //报备->带看->无效
            if (latestProgress == 13503) {
                changeMsg = "案件记录带看（带看时间：" + DateUtil.fmtDate(report.getSeeDate(), "yyyy-MM-dd") + "）认定为无效";
            }
            //报备->带看->大定->审核驳回->无效
            if (latestProgress == 13505) {
                changeMsg = "案件记录大定驳回（驳回原因：" + report.getRoughAuditDesc() + "）认定为无效";
            }
            achieveMentChangeLog.setChangeContent(changeMsg);
            achieveMentChangeLog.setCreateUserCode(param.get("userCode").toString());
            achieveMentChangeLog.setCreateUserName(param.get("userName").toString());
            achieveMentChangeLog.setCreateDate(new Date());
            achieveMentChangeLog.setDelFlag(false);
            achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);
        }
        if (null != param.get("customerNm")) {
            report.setCustomerNm((String) param.get("customerNm"));
        }
        if (null != param.get("customerTel")) {
            report.setCustomerTel((String) param.get("customerTel"));
        }
        if (null != param.get("customerNmTwo")) {
            report.setCustomerNmTwo((String) param.get("customerNmTwo"));
        }
        if (null != param.get("customerTelTwo")) {
            report.setCustomerTelTwo((String) param.get("customerTelTwo"));
        }
        if (null != param.get("accountProjectNo")) {
            report.setAccountProjectNo((String) param.get("accountProjectNo"));
        }
        if (null != param.get("buildingNo")) {
            report.setBuildingNo((String) param.get("buildingNo"));
        }
        if (null != param.get("buildingNoId")) {
            report.setBuildingNoId((String) param.get("buildingNoId"));
        }
        if (null != param.get("wyTypeCode")) {
            report.setWyTypeCode((String) param.get("wyTypeCode"));
        }

        if (null != param.get("isWrap") && !StringUtils.isEmpty((String) param.get("isWrap"))) {
            report.setIsWrap(Integer.parseInt((String) param.get("isWrap")));
        }
        if (null != param.get("planId") && !StringUtils.isEmpty((String) param.get("planId"))) {
            report.setPlanId(Integer.parseInt((String) param.get("planId")));
        }
      //20200702 提交时更新核算主体(从项目得收入类合同获取核算主体)
//        reportMapper.update(report);
        reportMapper.updateNew(report);

        //附件信息
        if (param.get("fileRecordMainIds") != null && StringUtil.isNotEmpty(param.get("fileRecordMainIds").toString()) && param.get("id") != null) {
            Integer refId = Integer.valueOf(param.get("id").toString());
            String[] fileIdArr = param.get("fileRecordMainIds").toString().split(",");
            FileRecordMain file;
            for (String fileId : fileIdArr) {
                file = new FileRecordMain();
                file.setRefId(refId);
                file.setFileRecordMainId(Integer.valueOf(fileId));
                fileRecordMainService.update(file);
            }
        }

        //更新报备详细表
        queryParam.clear();
        queryParam.put("estateId", estateId);
        queryParam.put("companyId", report.getCompanyId());
        queryParam.put("customerId", report.getCustomerId());
        queryParam.put("progress", latestProgress);
        List<ReportDetail> updateDetailList = reportDetailMapper.getReportDetail(queryParam);

        if (null == updateDetailList || updateDetailList.isEmpty()) {
            resultData.setFail("查询出数据为空");
            return resultData;
        }
        updateDetail = updateDetailList.get(0);
        if (latestProgress != currProgress && !"13602".equals(status)) {
            insertDetail = new ReportDetail();
            BeanUtils.copyProperties(updateDetail, insertDetail);
            insertDetail.setProgress(currProgress);
            insertDetail.setCrtDt(new Date());
            insertDetail.setUptDt(new Date());
            insertDetail.setRecognitionDay(new Date());
            insertDetail.setFollowDate(new Date());
            insertDetail.setConfirmStatus(13601);
            insertDetail.setCommissionAccountStatus(14202);
            insertDetail.setAccountRelationReward(new BigDecimal(0));
            insertDetail.setAccountPledgedReward(new BigDecimal(0));
            insertDetail.setAccountSubscribedReward(new BigDecimal(0));
            insertDetail.setAccountBargainReward(new BigDecimal(0));
            insertDetail.setAccountCommission(new BigDecimal(0));
            insertDetail.setAccountFlg(15801);
            insertDetail.setAccountStatus(14202);
            insertDetail.setCustomerNm((String) param.get("customerNm"));
            insertDetail.setCustomerTel((String) param.get("customerTel"));

            if (param.get("customerNmTwo") != null) {
                insertDetail.setCustomerNmTwo((String) param.get("customerNmTwo"));
            }
            if (param.get("customerTelTwo") != null) {
                insertDetail.setCustomerTelTwo((String) param.get("customerTelTwo"));
            }

            //计算统计明细编号
            String countId = insertDetail.getCountId();
            String prefix = countId.substring(0, countId.indexOf("-"));
            String suffix = countId.substring(countId.indexOf("-") + 1);
            //String middle = "10".compareTo(suffix) > 0? "-0": "-";
            int suffixNum = Integer.valueOf(suffix) + 1;
            String middle = suffixNum < 10 ? "-0" : "-";
            countId = prefix + middle + String.valueOf(Integer.valueOf(suffix) + 1);
            insertDetail.setCountId(countId);
            insertDetail.setReportNo(reportId);
            // 点击大定时 INSERT楼市号、面积、大定总价、成销总价 (insert 大定有效)
            if ("13504".equals(status) || "13505".equals(status)) {
                buildingNo = (String) param.get("buildingNo");
                buildingNoId = (String) param.get("buildingNoId");
                wyTypeCode = (String) param.get("wyTypeCode");
                areaStr = (String) param.get("area");
                roughAmountStr = (String) param.get("roughAmount");
                dealAmountStr = (String) param.get("dealAmount");
                roughAreaStr = (String) param.get("roughArea");
                insertDetail.setBuildingNo(buildingNo);
                insertDetail.setBuildingNoId(buildingNoId);
                insertDetail.setWyTypeCode(wyTypeCode);

                if (StringUtils.isNotBlank(areaStr))
                    insertDetail.setArea(new BigDecimal(areaStr));

                insertDetail.setRoughAmount(new BigDecimal(roughAmountStr));

                if (StringUtils.isNotBlank(dealAmountStr))
                    insertDetail.setDealAmount(new BigDecimal(dealAmountStr));
                if (StringUtils.isNotBlank(roughAreaStr))
                    insertDetail.setRoughArea(new BigDecimal(roughAreaStr));
            }
            //修改人取当前登录人
            insertDetail.setUptEmpId(Integer.valueOf(param.get("userId").toString()));
            reportDetailMapper.create(insertDetail);
        }
        if (currProgress < 13505 && !"13602".equals(status)) {//成交之前的认定都要插入新的报备详情
            insertDetail = new ReportDetail();
            BeanUtils.copyProperties(updateDetail, insertDetail);
            insertDetail.setProgress(nextProgress);
            insertDetail.setCrtDt(new Date());
            insertDetail.setUptDt(new Date());
            insertDetail.setRecognitionDay(new Date());
            insertDetail.setFollowDate(new Date());
            insertDetail.setConfirmStatus(13603);
            insertDetail.setCommissionAccountStatus(14202);
            insertDetail.setAccountRelationReward(new BigDecimal(0));
            insertDetail.setAccountPledgedReward(new BigDecimal(0));
            insertDetail.setAccountSubscribedReward(new BigDecimal(0));
            insertDetail.setAccountBargainReward(new BigDecimal(0));
            insertDetail.setAccountCommission(new BigDecimal(0));


            // 点击大定时 INSERT楼市号、面积、大定总价、成销总价  (insert 成销无效)
            if ("13504".equals(status)) {
                buildingNo = (String) param.get("buildingNo");
                buildingNoId = (String) param.get("buildingNoId");
                wyTypeCode = (String) param.get("wyTypeCode");
                areaStr = (String) param.get("area");
                roughAmountStr = (String) param.get("roughAmount");
                dealAmountStr = (String) param.get("dealAmount");
                roughAreaStr = (String) param.get("roughArea");
                insertDetail.setBuildingNo(buildingNo);
                insertDetail.setBuildingNoId(buildingNoId);
                insertDetail.setWyTypeCode(wyTypeCode);

                if (StringUtils.isNotBlank(areaStr))
                    insertDetail.setArea(new BigDecimal(areaStr));
                insertDetail.setRoughAmount(new BigDecimal(roughAmountStr));
                if (StringUtils.isNotBlank(dealAmountStr))
                    insertDetail.setDealAmount(new BigDecimal(dealAmountStr));
                if (StringUtils.isNotBlank(roughAreaStr))
                    insertDetail.setRoughArea(new BigDecimal(roughAreaStr));

                insertDetail.setCustomerNm((String) param.get("customerNm"));
                insertDetail.setCustomerTel((String) param.get("customerTel"));
                if (param.get("customerNmTwo") != null) {
                    insertDetail.setCustomerNmTwo((String) param.get("customerNmTwo"));
                }
                if (param.get("customerTelTwo") != null) {
                    insertDetail.setCustomerTelTwo((String) param.get("customerTelTwo"));
                }
                //大定明细相关
                ReportDetail detail = new ReportDetail();
                detail.setId(updateDetail.getId());
                detail.setBuildingNo(buildingNo);
                detail.setBuildingNoId(buildingNoId);
                detail.setWyTypeCode(wyTypeCode);
                detail.setRoughAmount(new BigDecimal(roughAmountStr));
                detail.setRoughArea(new BigDecimal(roughAreaStr));
                //修改人取当前登录人
                detail.setUptEmpId(Integer.valueOf(param.get("userId").toString()));
                reportDetailMapper.update(detail);
            }

            //计算统计明细编号
            String countId = insertDetail.getCountId();
            String prefix = countId.substring(0, countId.indexOf("-"));
            String suffix = countId.substring(countId.indexOf("-") + 1);
            //String middle = "10".compareTo(suffix) > 0? "-0": "-";
            if (latestProgress != currProgress) {
                int suffixNum = Integer.valueOf(suffix) + 2;
                String middle = suffixNum < 10 ? "-0" : "-";
                countId = prefix + middle + String.valueOf(Integer.valueOf(suffix) + 2);
            } else {
                int suffixNum = Integer.valueOf(suffix) + 1;
                String middle = suffixNum < 10 ? "-0" : "-";
                countId = prefix + middle + String.valueOf(Integer.valueOf(suffix) + 1);
            }
            insertDetail.setCountId(countId);
            insertDetail.setReportNo(reportId);
            //修改人取当前登录人
            insertDetail.setUptEmpId(Integer.valueOf(param.get("userId").toString()));
            reportDetailMapper.create(insertDetail);
        }
        if (!"13602".equals(status)) { // 非无效场合
            updateDetail.setConfirmStatus(13601);
            updateDetail.setRecognitionDay(new Date());
            updateDetail.setFollowDate(new Date());
            updateDetail.setAccountFlg(15801);
            updateDetail.setAccountStatus(14202);
            updateDetail.setUptDt(new Date());

            // 点击成销 UPDATE楼市号、面积、大定总价、成销总价
            if ("13505".equals(status)) {
                queryParam.clear();
                queryParam.put("estateId", estateId);
                queryParam.put("companyId", report.getCompanyId());
                queryParam.put("customerId", report.getCustomerId());
                queryParam.put("progress", "13504");
                //查询大定数据是否录入
                List<ReportDetail> list = reportDetailMapper.getReportDetail(queryParam);
                if (list == null || list.size() <= 0) {
                    //未录入时添加大定数据
                    insertDetail = new ReportDetail();
                    BeanUtils.copyProperties(updateDetail, insertDetail);
                    insertDetail.setProgress(nextProgress - 1);
                    insertDetail.setCrtDt(new Date());
                    insertDetail.setUptDt(new Date());
                    insertDetail.setRecognitionDay(new Date());
                    insertDetail.setFollowDate(new Date());
                    insertDetail.setConfirmStatus(13601);
                    insertDetail.setCommissionAccountStatus(14202);
                    insertDetail.setAccountRelationReward(new BigDecimal(0));
                    insertDetail.setAccountPledgedReward(new BigDecimal(0));
                    insertDetail.setAccountSubscribedReward(new BigDecimal(0));
                    insertDetail.setAccountBargainReward(new BigDecimal(0));
                    insertDetail.setAccountCommission(new BigDecimal(0));
                    insertDetail.setCustomerNm((String) param.get("customerNm"));
                    insertDetail.setCustomerTel((String) param.get("customerTel"));
                    if (param.get("customerNmTwo") != null) {
                        insertDetail.setCustomerNmTwo((String) param.get("customerNmTwo"));
                    }
                    if (param.get("customerTelTwo") != null) {
                        insertDetail.setCustomerTelTwo((String) param.get("customerTelTwo"));
                    }

                    // 点击大定时 INSERT楼市号、面积、大定总价、成销总价  (insert 成销无效)
                    buildingNo = (String) param.get("buildingNo");
                    buildingNoId = (String) param.get("buildingNoId");
                    wyTypeCode = (String) param.get("wyTypeCode");
                    areaStr = (String) param.get("area");
                    roughAmountStr = (String) param.get("roughAmount");
                    dealAmountStr = (String) param.get("dealAmount");
                    roughAreaStr = (String) param.get("roughArea");
                    insertDetail.setBuildingNo(buildingNo);
                    insertDetail.setBuildingNoId(buildingNoId);
                    insertDetail.setWyTypeCode(wyTypeCode);


                    if (StringUtils.isNotBlank(areaStr))
                        insertDetail.setArea(new BigDecimal(areaStr));
                    insertDetail.setRoughAmount(new BigDecimal(roughAmountStr));
                    if (StringUtils.isNotBlank(dealAmountStr))
                        insertDetail.setDealAmount(new BigDecimal(dealAmountStr));
                    if (StringUtils.isNotBlank(roughAreaStr))
                        insertDetail.setRoughArea(new BigDecimal(roughAreaStr));

                    //计算统计明细编号
                    String countId = insertDetail.getCountId();
                    String prefix = countId.substring(0, countId.indexOf("-"));
                    String suffix = countId.substring(countId.indexOf("-") + 1);
                    // String middle = "10".compareTo(suffix) > 0? "-0": "-";
                    if (latestProgress != currProgress) {
                        int suffixNum = Integer.valueOf(suffix) + 2;
                        String middle = suffixNum < 10 ? "-0" : "-";
                        countId = prefix + middle + String.valueOf(Integer.valueOf(suffix) + 2);
                    } else {
                        int suffixNum = Integer.valueOf(suffix) + 1;
                        String middle = suffixNum < 10 ? "-0" : "-";
                        countId = prefix + middle + String.valueOf(Integer.valueOf(suffix) + 1);
                    }
                    insertDetail.setCountId(countId);
                    insertDetail.setReportNo(reportId);
                    //修改人取当前登录人
                    insertDetail.setUptEmpId(Integer.valueOf(param.get("userId").toString()));
                    reportDetailMapper.create(insertDetail);
                }
                buildingNo = (String) param.get("buildingNo");
                buildingNoId = (String) param.get("buildingNoId");
                wyTypeCode = (String) param.get("wyTypeCode");
                areaStr = (String) param.get("area");

                roughAmountStr = (String) param.get("roughAmount");
                dealAmountStr = (String) param.get("dealAmount");
                roughAreaStr = (String) param.get("roughArea");
                updateDetail.setBuildingNo(buildingNo);
                updateDetail.setBuildingNoId(buildingNoId);
                updateDetail.setWyTypeCode(wyTypeCode);

                if (StringUtils.isNotBlank(areaStr))
                    updateDetail.setArea(new BigDecimal(areaStr));
                updateDetail.setRoughAmount(new BigDecimal(roughAmountStr));
                if (StringUtils.isNotBlank(dealAmountStr))
                    updateDetail.setDealAmount(new BigDecimal(dealAmountStr));
                if (StringUtils.isNotBlank(roughAreaStr))
                    updateDetail.setRoughArea(new BigDecimal(roughAreaStr));

                updateDetail.setCustomerNm((String) param.get("customerNm"));
                updateDetail.setCustomerTel((String) param.get("customerTel"));
                if (param.get("customerNmTwo") != null) {
                    updateDetail.setCustomerNmTwo((String) param.get("customerNmTwo"));
                }
                if (param.get("customerTelTwo") != null) {
                    updateDetail.setCustomerTelTwo((String) param.get("customerTelTwo"));
                }
                //结算确认日期
                if (param.get("settleConfirmDate") != null) {
                    updateDetail.setSettleConfirmDate(DateUtil.getDate(param.get("settleConfirmDate").toString(), "yyyy-MM-dd"));
                }
                //modify by haidan 2019/05/05 成销增加应计收入和应计返佣信息录入
                CommissionResultDto cDto = new CommissionResultDto();
                cDto.setReportId(reportId);
                cDto.setNum(1);
                cDto.setDetailId(String.valueOf(updateDetail.getId()));
                cDto.setCompanyNo(report.getCompanyId());
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String dealDate = format.format(report.getDealDate());
                cDto.setRecordDate(dealDate);
                cDto.setCrtEmpId(String.valueOf(param.get("userId")));
                cDto.setUptEmpId(String.valueOf(param.get("userId")));

                cDto.setAftTaxAmount(String.valueOf(param.get("aftYJSRAmount")));
                cDto.setBefTaxAmount(String.valueOf(param.get("befYJSRAmount")));

                // add by huangmc 20200304 返佣方案处理 start
                Map<String, Object> yjfyMap = new HashMap<>();
                yjfyMap.put("reportId",reportId);
                yjfyMap.put("crtEmpId", String.valueOf(param.get("userId")));
                yjfyMap.put("uptEmpId", String.valueOf(param.get("userId")));
                // 清空返佣方案记录
                lnkReportYJFAMapper.logicDeleteByReportId(yjfyMap);
                // add by huangmc 20200304 返佣方案处理 End

                lnkYjYjsrMapper.mergeInsert(cDto);
                Map<String, Object> syncMap = new HashMap<>();
                syncMap.put("empId", cDto.getCrtEmpId());
                syncMap.put("tabName", "LNK_YJ_YJSR");
                lnkYjNyMapper.syncLnkImport(syncMap);
                List<Map<String, Object>> fyList = JSON.parseObject(String.valueOf(param.get("fyList")), new TypeReference<List<Map<String, Object>>>() {
                });
                for (Map<String, Object> fyObj : fyList) {
                    cDto.setCompanyNo((String) fyObj.get("companyNo"));
                    cDto.setAftTaxAmount((String) fyObj.get("aftYJFYAmount"));
                    cDto.setBefTaxAmount((String) fyObj.get("befYJFYAmount"));
                    lnkYjYjfyMapper.mergeInsert(cDto);
                    if (!StringUtils.isEmpty((String) fyObj.get("aftYJDYAmount")) && !StringUtils.isEmpty((String) fyObj.get("befYJDYAmount"))) {
                        cDto.setAftTaxAmount((String) fyObj.get("aftYJDYAmount"));
                        cDto.setBefTaxAmount((String) fyObj.get("befYJDYAmount"));
                        lnkYjYjdyMapper.mergeInsertByDto(cDto);
                    }
                    // add by huangmc 20200304 返佣方案处理 start
                    yjfyMap.put("companyNo",(String) fyObj.get("companyNo"));
                    yjfyMap.put("programmeId",(String) fyObj.get("programmeId"));
                    lnkReportYJFAMapper.mergeInsert(yjfyMap);
                    // add by huangmc 20200304 返佣方案处理 End
                }
                syncMap.put("tabName", "LNK_YJ_YJFY");
                lnkYjNyMapper.syncLnkImport(syncMap);

                // Start add by huangmc 20191219 for 成销判断增加房友返佣处理
                if (!StringUtils.isEmpty((String) param.get("befFangyouYJFYAmount")) && !StringUtils.isEmpty((String) param.get("aftFangyouYJFYAmount"))) {
                    Map<String, Object> fyMap = new HashMap<>();

                    fyMap.put("reportId", reportId);
                    fyMap.put("befTaxAmount", String.valueOf(param.get("befFangyouYJFYAmount")));
                    fyMap.put("aftTaxAmount", String.valueOf(param.get("aftFangyouYJFYAmount")));

                    fyMap.put("recordDate", dealDate);
                    fyMap.put("companyNo", report.getCompanyId());
                    fyMap.put("detailId", String.valueOf(updateDetail.getId()));
                    fyMap.put("num", 1);
                    fyMap.put("crtEmpId", String.valueOf(param.get("userId")));
                    fyMap.put("uptEmpId", String.valueOf(param.get("userId")));

                    lnkYjFyfyMapper.mergeInsert(fyMap);
                }
                // End add by huangmc 20191219 for 成销判断增加房友返佣处理
            }
        } else {
            updateDetail.setConfirmStatus(13602);
            updateDetail.setRecognitionDay(new Date());
        }
        //修改人取当前登录人
        updateDetail.setUptEmpId(Integer.valueOf(param.get("userId").toString()));
        count += reportDetailMapper.update(updateDetail);

        //更新带看、认筹、大定、成销时间
        ReportDetail updateTemp = new ReportDetail();
        updateTemp.setSeeDate(report.getSeeDate());
        updateTemp.setPledgedDate(report.getPledgedDate());
        updateTemp.setRoughInputDate(report.getRoughInputDate());
        updateTemp.setDealDate(report.getDealDate());
        updateTemp.setEstateId(estateId);
        updateTemp.setCountId(reportId);
        //修改人取当前登录人
        updateTemp.setUptEmpId(Integer.valueOf(param.get("userId").toString()));
        reportDetailMapper.updateOperateDate(updateTemp);

        //修改报备的历史客户名和联系方式
        updateHistoryCustomer(report.getId(), param);

        resultData.setReturnData(count);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    //修改报备的历史客户名和联系方式
    private void updateHistoryCustomer(int id, Map<?, ?> param) {
        Map<String, Object> historyMap = new HashMap<String, Object>();
        historyMap.put("id", id);
        historyMap.put("customerNm", (String) param.get("customerNm"));
        historyMap.put("customerTel", (String) param.get("customerTel"));
        try {
            reportMapper.updateHistoryCustomer(historyMap);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String setDate(Report report, String status, String operateDate) throws Exception {
        Date date = getFormatStringDate(operateDate + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        Date date1 = getFormatStringDate(operateDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if (report.getSeeDate() != null && !"13502".equals(status)) {//认筹 大定 成销时间必须大于等于带看时间
            if (date.before(report.getSeeDate())) {
                String progressName = "";
                if ("13503".equals(status)) progressName = "认筹";
                else if ("13504".equals(status)) progressName = "大定";
                else if ("13505".equals(status)) progressName = "成销";
                return progressName + "日期必须大于或等于带看日期";
            }
        }

        // 认筹、大定、成销日期加入关账判断
        // added by wang kanlin 2017/09/12

        Date switchDate = new Date();
        int year = 1990;
        int month = 1;
        try {

//            Map<String, Object> queryParam = new HashMap<>();
//            Map<String, String> switchMonth = null;
//            queryParam.put("cityNo", report.getCityNo());
//            switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(queryParam);
            Map<?, ?> switchMonth = commonMapper.checkCitySwitchMonth(report.getCityNo());
            if (switchMonth != null) {
                month = Integer.valueOf(switchMonth.get("month").toString());
                year = Integer.valueOf(switchMonth.get("year").toString());
            }
            Calendar c = Calendar.getInstance();
            c.set(year, month, 1, 0, 0, 0);
            //c.add(Calendar.MONTH, 1);
            c.set(Calendar.MILLISECOND, 0);
            switchDate = c.getTime();

        } catch (Exception e) {
            return "查询关账时间失败";
        }
        String failDate = "新房联动业务已关账至y年m月，请选择未关账日期！";

        if ("13502".equals(status)) {//带看
            if (report.getReportDate() != null && date.before(report.getReportDate())) {
                return "带看日期必须大于等于报备日期";
            }
//    		if(date.before(switchDate)){
//    			return failDate.replace("y", year+"").replace("m", month+"");
//    		}
            report.setSeeDate(date1);
        } else if ("13503".equals(status)) {//认筹
            if ((report.getPledgedBackDate() != null && date.before(report.getPledgedBackDate()))
                    || (report.getRoughBackDate() != null && date.before(report.getRoughBackDate()))
                    || (report.getDealBackDate() != null && date.before(report.getDealBackDate()))) {
                return "认筹日期必须大于等于之前的退筹/退定/退房日期，请重新填写！";
            }
            if (date.before(switchDate)) {
                return failDate.replace("y", year + "").replace("m", month + "");
            }
            report.setPledgedDate(date1);
        } else if ("13504".equals(status)) {//大定
            if (report.getPledgedDate() != null) {
                if (date.before(report.getPledgedDate())) {
                    return "大定日期必须大于等于认筹日期";
                }
            }
            if ((report.getPledgedBackDate() != null && date.before(report.getPledgedBackDate()))
                    || (report.getRoughBackDate() != null && date.before(report.getRoughBackDate()))
                    || (report.getDealBackDate() != null && date.before(report.getDealBackDate()))) {
                return "大定日期必须大于等于之前的退筹/退定/退房日期，请重新填写！";
            }
            if (date.before(switchDate)) {
                return failDate.replace("y", year + "").replace("m", month + "");
            }
            report.setRoughInputDate(date1);
        } else if ("13505".equals(status)) {//成销
            if (report.getRoughDate() != null) {
                if (date.before(report.getRoughDate())) {
                    return "成销日期必须大于或等于大定日期！";
                }
            }
            if ((report.getPledgedBackDate() != null && date.before(report.getPledgedBackDate()))
                    || (report.getRoughBackDate() != null && date.before(report.getRoughBackDate()))
                    || (report.getDealBackDate() != null && date.before(report.getDealBackDate()))) {
                return "成销日期必须大于等于之前的退筹/退定/退房日期，请重新填写！";
            }
            if (date.before(switchDate)) {
                return failDate.replace("y", year + "").replace("m", month + "");
            }
            report.setDealDate(date1);
        }
        if (report.getSeeDate() == null) {
            report.setSeeDate(date1);
        }

        return null;
    }


    /**
     * 查询楼盘名
     *
     * @param estateId
     * @return
     */
    public ResultData<Estate> getByEstateId(String estateId)
            throws Exception {
        ResultData<Estate> resultData = new ResultData<>();
        Estate estate = estateMapper.selectEstateInfo(estateId).get(0);
        if (estate != null && estate.getProjectDepartmentId() != null) {
            Estate estateCenterInfoDb = estateMapper.getCenterInfo(estate.getId());
            estate.setCenterId(estateCenterInfoDb.getCenterId());
            estate.setCenterName(estateCenterInfoDb.getCenterName());
        }
        resultData.setReturnData(estate);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<EstateSearchResultDto>
     */
    private List<EstateSearchResultDto> convertEstateSearchResultData(List<EstateSearchResult> craList) throws
            Exception {
        List<EstateSearchResultDto> estateDtoList = new ArrayList<EstateSearchResultDto>();

        if (null != craList && !craList.isEmpty()) {
            EstateSearchResultDto craDto = null;
            for (EstateSearchResult cra : craList) {
                craDto = new EstateSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                estateDtoList.add(craDto);
            }
        }
        return estateDtoList;
    }

    /**
     * 点击成销带出houseInof
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<ReportDetail> getSceneHouseInfo(Map<?, ?> param)
            throws Exception {
        String reportId = (String) param.get("reportId");
        String estateId = (String) param.get("estateId");
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("reportId", reportId);
        Report report = reportMapper.getReport(queryParam).get(0);
        queryParam.put("estateId", estateId);
        queryParam.put("companyId", report.getCompanyId());
        queryParam.put("customerId", report.getCustomerId());
        queryParam.put("progress", report.getLatestProgress());
        //调用 接口
        ResultData<ReportDetail> resultData = new ResultData<ReportDetail>();
        ReportDetail reportDetail = reportDetailMapper.getSceneHouseInfo(queryParam).get(0);
        resultData.setReturnData(reportDetail);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    /**
     * 点击大定带出houseInof
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<ReportDetail> getSceneHouseInfoDaDing(Map<?, ?> param)
            throws Exception {
        String reportId = (String) param.get("reportId");
        String estateId = (String) param.get("estateId");
        Map<String, Object> queryParam = new HashMap<String, Object>();
        queryParam.put("reportId", reportId);
        Report report = reportMapper.getReport(queryParam).get(0);
        queryParam.put("estateId", estateId);
        queryParam.put("companyId", report.getCompanyId());
        queryParam.put("progress", report.getLatestProgress());
        //调用 接口
        ResultData<ReportDetail> resultData = new ResultData<ReportDetail>();
        ReportDetail reportDetail = reportDetailMapper.getSceneHouseInfoDaDing(queryParam).get(0);
        resultData.setReturnData(reportDetail);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    /**
     * 根据日期字符串返回日期对象
     *
     * @param sDat      日期字符串
     * @param strFormat 格式
     * @return 日期对象
     * @throws ParseException
     */
    private Date getFormatStringDate(String sDat, String strFormat) throws ParseException {
        if (StringUtil.isNotEmpty(sDat)) {
            // 解析日期
            SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
            return myFmt.parse(sDat);
        }
        return null;
    }

    /**
     * 图片
     */
    public void insertFangyouFile(List<String> fileIdList, FangyouReportFile fangyouReportFile) throws Exception {
        fangyouReportFile.setDelFlag("Y");
        fangyouReportFileMapper.updateSelective(fangyouReportFile);
        if (fileIdList != null) {
            for (String o : fileIdList) {
                FangyouReportFile fangyouFileInsert = new FangyouReportFile();
                fangyouFileInsert.setDateCreate(new Date());
                fangyouFileInsert.setDelFlag("N");
                fangyouFileInsert.setReportId(fangyouReportFile.getReportId());
                fangyouFileInsert.setReportNo(fangyouReportFile.getReportNo());
                fangyouFileInsert.setTypeId(fangyouReportFile.getTypeId());
                fangyouFileInsert.setUrl(o);
                fangyouFileInsert.setUserIdCreate(null);
                fangyouReportFileMapper.insertSelective(fangyouFileInsert);
            }
        }
    }

    /**
     * 图片
     */
    public void insertFangyouFileDto(List<FileDto> fileIdList, FangyouReportFile fangyouReportFile) throws
            Exception {
        fangyouReportFile.setDelFlag("Y");
        fangyouReportFileMapper.updateSelective(fangyouReportFile);
        if (fileIdList != null) {
            for (FileDto f : fileIdList) {
                FangyouReportFile fangyouFileInsert = new FangyouReportFile();
                fangyouFileInsert.setDateCreate(new Date());
                fangyouFileInsert.setDelFlag("N");
                fangyouFileInsert.setReportId(fangyouReportFile.getReportId());
                fangyouFileInsert.setReportNo(fangyouReportFile.getReportNo());
                fangyouFileInsert.setTypeId(fangyouReportFile.getTypeId());
                if (f.getType() != null) {
                    fangyouFileInsert.setFileTypeCode(f.getType());
                }
                fangyouFileInsert.setUrl(f.getUrl());
                fangyouFileInsert.setUserIdCreate(null);
                fangyouReportFileMapper.insertSelective(fangyouFileInsert);
            }
        }
    }


    /**
     * 房友更新report2
     *
     * @param queryParam
     * @return
     */
    public ResultData<Integer> sceneRecogonitionConfirmUpt(ApproDecideDto apiInsertDto, Report reportDb) throws
            Exception {
        ResultData<Integer> resultData = new ResultData<Integer>();
        Report reportUpt = new Report();
        reportUpt.setId(reportDb.getId());
//    	reportUpt.setCustomerNm(apiInsertDto.getCustomerNm());  //现有逻辑大定修改了 customerNm和customerTel后 只影响后续reportDetail数据 
//    	reportUpt.setCustomerTel(apiInsertDto.getCustomerTel());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        String roughDateStr = dateFormat.format(apiInsertDto.getRoughDate());
        Date roughInputDate = dateFormat.parse(roughDateStr);
        reportUpt.setRoughInputDate(roughInputDate);
        reportUpt.setRoughAuditStatus("");
        reportUpt.setCustomerNm(apiInsertDto.getCustomerNm());
        reportUpt.setCustomerTel(apiInsertDto.getCustomerTel());
        reportUpt.setCustomerNmTwo(apiInsertDto.getCustomerNmTwo());
        reportUpt.setCustomerTelTwo(apiInsertDto.getCustomerTelTwo());
        reportMapper.update(reportUpt);


        ReportDetail reportDetailUpt = new ReportDetail();
        reportDetailUpt.setCountId(reportDb.getReportId());


        reportDetailUpt.setBuildingNo(apiInsertDto.getBuildingNo());
        reportDetailUpt.setRoughArea(apiInsertDto.getRoughArea());
        reportDetailUpt.setRoughAmount(apiInsertDto.getRoughAmount());
        reportDetailUpt.setRoughInputDate(apiInsertDto.getRoughDate());
        reportDetailUpt.setCustomerNm(apiInsertDto.getCustomerNm());
        reportDetailUpt.setCustomerTel(apiInsertDto.getCustomerTel());
        reportDetailUpt.setCustomerNmTwo(apiInsertDto.getCustomerNmTwo());
        reportDetailUpt.setCustomerTelTwo(apiInsertDto.getCustomerTelTwo());
        reportDetailMapper.updateFangYouDetail(reportDetailUpt);


        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }


    /**
     * 成销 房友
     *
     * @param queryParam
     * @return
     */
    public ResultData<String> dealCrm(Map<?, ?> queryParam) throws Exception {
        /*
        ResultData<String> resultData = new ResultData<String>();
    	CrmDealDto crmDealDto = new CrmDealDto();
    	User user = new User();
    	FangyouReportLog fangyouReportLog = new FangyouReportLog();
      	fangyouReportLog.setTypeId("5");
      	
    	boolean flag = false;
    	try {
    		user = userMapper.getUserByCode((String) queryParam.get("userCode"));
    		
    		Map<String, Object> reportMap = new HashMap<String, Object>();
    		reportMap.put("reportId", (String) queryParam.get("reportId"));
    		Report reportDb = reportMapper.getReport(reportMap).get(0);
    		if(reportDb!=null 
    				&& reportDb.getCustomerFrom()!=null
            		&& "17405".equals(reportDb.getCustomerFrom().toString()) ){
    			flag = true;
    			crmDealDto.setCrmUserId(user.getUserId());
        		crmDealDto.setCustomerCellNumber((String) queryParam.get("customerTel"));
        		crmDealDto.setCustomerName((String) queryParam.get("customerNm"));
        		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        		crmDealDto.setDealTime(dateFormat.parse( (String)queryParam.get("operateDate") ));
        		crmDealDto.setHouseAddress((String) queryParam.get("buildingNo"));
        		crmDealDto.setHouseArea(new BigDecimal((String)queryParam.get("area")));
        		crmDealDto.setRegisterId(Long.parseLong(reportDb.getFyReportId()));
        		BigDecimal dealAmount = new BigDecimal((String)queryParam.get("dealAmount"));
        		dealAmount = dealAmount.divide(new BigDecimal(10000)).setScale(2, BigDecimal.ROUND_HALF_UP);
        		crmDealDto.setTotalPrice(dealAmount);
        		
        		String url = SystemParam.getWebConfigValue("fangyouReportUrl") + "/merchant/site/register/site/approve/deal-crm";
            	
            	String jsonData = JsonUtil.parseToJson(crmDealDto);	                 
	            String opResult = HttpClientUtil.jsonPostFangyou(url,jsonData);
	             
	         	fangyouReportLog.setUserIdCreate(user.getUserId());
	         	fangyouReportLog.setReqParamJson(jsonData);
	         	fangyouReportLog.setRspParamJson(opResult);
                fangyouReportLogService.addLog(fangyouReportLog);
    		}
		} catch (Exception e) {
			resultData.setFail("操作异常");
			if(flag){
				fangyouReportLog.setUserIdCreate(user.getUserId());
	         	fangyouReportLog.setReqParamJson(JsonUtil.parseToJson(queryParam));
	         	fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
	            fangyouReportLogService.addLog(fangyouReportLog);
			}
			e.printStackTrace();
			logger.error("sceneEstateService", "SceneEstateService", "dealCrm", "", null, "", "成销通知房友失败", e);
	        return resultData;
		}
    	
    	resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
        */

        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> reportMap = new HashMap<String, Object>();
        reportMap.put("reportId", (String) queryParam.get("reportId"));
        Report reportDb = reportMapper.getReport(reportMap).get(0);
        if (reportDb != null
                && reportDb.getCustomerFrom() != null
                && "17405".equals(reportDb.getCustomerFrom().toString())) {
            try {
                YFStatusSync yFStatusSync = new YFStatusSync();
                yFStatusSync.setCrm_report_id(reportDb.getReportId());
                yFStatusSync.setReport_status("成销");
                yFStatusSync.setSuccess_sale_time((String) queryParam.get("operateDate"));
                yFStatusSync.setBuilding_house_code((String) queryParam.get("buildingNo"));
                yFStatusSync.setBuilding_area((String) queryParam.get("area"));
                yFStatusSync.setTotal_price((String) queryParam.get("dealAmount"));
                //新增字段 yf_report_id 2019/01/09
                yFStatusSync.setYf_report_id(reportDb.getFyReportId());
                resultData = yFInterfaceService.getyFInterfaceInfo("/CRMReportStatusSync", JSON.toJSONString(yFStatusSync), "5", 0, reportDb.getReportId());
            } catch (Exception e) {
                resultData.setFail("操作异常");
                e.printStackTrace();
                logger.error("sceneEstateService", "SceneEstateService", "dealCrm", "", null, "", "成销通知有房失败", e);
                return resultData;
            }
        }
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    /**
     * 校验实际返佣
     * @param queryParam
     * @return
     */
    public ResultData<?> checkSjfy(Map<String, Object> queryParam) {
        ResultData<?> resultData=new ResultData<>();
        Integer relCount = lnkYjSjfyMapper.checkPreAmountForReback(queryParam);
        if (relCount.intValue() > 0) {
            resultData.setFail("该报备订单已有实际返佣，请通知对口结算解锁退房!");
            return resultData;
        }
        return resultData;
    }


    /**
     * 校验预退
     * @param queryParam
     * @return
     */
    public ResultData<Integer> checkPreBack(Map<String, Object> queryParam) {
        ResultData<Integer> resultData = new ResultData<Integer>();
        try {

            Map<String, Object> reportMap = new HashMap<String, Object>();
            reportMap.put("reportId", (String) queryParam.get("reportId"));
            Report reportDb = reportMapper.getReport(reportMap).get(0);//reportDb数据库已经修改提交
            if ("1".equals(reportDb.getPreBack())) {
                resultData.setFail("该报备订单已预退，不能继续带看、大定、成销!");
                return resultData;
            }
        } catch (Exception e) {
            logger.error("sceneEstateService", "SceneEstateService", "checkPreBack",
                    null, null, "", "校验预退查询报备记录失败", e);
        }
        resultData.setSuccess();
        return resultData;
    }
}
