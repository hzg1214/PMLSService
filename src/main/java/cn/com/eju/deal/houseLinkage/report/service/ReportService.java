package cn.com.eju.deal.houseLinkage.report.service;

import cn.com.eju.deal.Report.model.BrokerageDto;
import cn.com.eju.deal.Report.model.CommissionDto;
import cn.com.eju.deal.Report.model.CommissionInfoDto;
import cn.com.eju.deal.achievement.dao.AchievementMapper;
import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.api.houseLinkage.constant.HouseLinkageConstant;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateReleaseCityDto;
import cn.com.eju.deal.dto.houseLinkage.report.*;
import cn.com.eju.deal.houseLinkage.estate.constant.EstateConstant;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.EstatetReleaseMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.AchieveMentChangeLogMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportFileMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.dao.SendContractFangyouLogMapper;
import cn.com.eju.deal.houseLinkage.report.model.*;
import cn.com.eju.deal.scene.commission.dao.*;
import cn.com.eju.deal.scene.commission.model.*;
import cn.com.eju.deal.scene.estate.constant.SceneEstateConstant;
import cn.com.eju.deal.scene.estate.service.FangyouReportLogService;
import cn.com.eju.deal.scene.estate.service.SceneEstateService;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;
import cn.com.eju.deal.user.model.User;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * service层
 *
 * @author qianwei
 * @date 2016年3月22日 下午7:57:09
 */
@Service("reportService")
public class ReportService extends BaseService {

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private CommonMapper commonMapper;

    @Resource
    private ReportDetailMapper reportDetailMapper;

    @Resource
    private SceneInCommissionMapper sceneInCommissionMapper;

    @Resource
    private AchieveMentChangeLogMapper achieveMentChangeLogMapper;

    @Resource
    private FileRecordMainService fileRecordMainService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private FangyouReportLogService fangyouReportLogService;

    @Resource
    private FangyouReportFileMapper fangyouReportFileMapper;

    @Resource
    private YFInterfaceService yFInterfaceService;

    @Resource
    private SceneEstateService sceneEstatetService;

    @Resource(name = "commonService")
    private CommonService commonService;
    @Resource
    private AchievementMapper achievementMapper;
    @Resource
    private GroupMapper groupMapper;

    @Resource
    private SendContractFangyouLogMapper sendContractFangyouLogMapper;

    @Resource
    private LnkYjYjsrMapper lnkYjYjsrMapper;

    @Resource
    private LnkYjYssrMapper lnkYjYssrMapper;

    @Resource
    private LnkYjYjdyMapper lnkYjYjdyMapper;

    @Resource
    private LnkYjSjfyMapper lnkYjSjfyMapper;

    @Resource
    private LnkYjYjssMapper lnkYjYjssMapper;
    @Resource
    private LnkYjYjfyMapper lnkYjYjfyMapper;
    @Resource
    private LnkYjSjdyMapper lnkYjSjdyMapper;

    @Resource
    EstateMapper estateMapper;

    @Resource
    private EstatetReleaseMapper estatetReleaseMapper;

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ReportInfoDto getReport(String estateId, String companyId, String customerId) throws Exception {
        ReportInfoDto reportInfoDto = new ReportInfoDto();
        Map<String, String> param = new HashMap<String, String>();
        param.put("estateId", estateId);
        param.put("companyId", companyId);
        param.put("customerId", customerId);
        // 获取报备信息
        ReportDto ctaDto = new ReportDto();
        List<Report> reportMdl = reportMapper.getReport(param);
        if (reportMdl != null && reportMdl.size() > 0) {
            //Model转换Dto
            BeanUtils.copyProperties(reportMdl.get(0), ctaDto);
            ctaDto.setCustomerNum(SystemParam.getDicValueByDicCode(ctaDto.getCustomerNum()));
            //查询收入类型
            ReportDetailDto reportDetail = reportDetailMapper.quertInComeStatusByReportId(ctaDto.getReportId());
            if (reportDetail != null) {
                ctaDto.setInComeStatus(reportDetail.getInComeStatus());
                ctaDto.setInComeName(reportDetail.getInComeName());
            }

            if (StringUtil.isNotEmpty(ctaDto.getBrokerageYm())) {
                Date BrokerageYmDate = cn.com.eju.deal.reportbase.util.DateUtil.parseDateFormat(ctaDto.getBrokerageYm(), "yyyyMM");
                ctaDto.setBrokerageYm(DateUtil.fmtDate(BrokerageYmDate, "yyyy-MM"));
            }
        }
        List<ReportDetail> detail = reportDetailMapper.getReportDetail(param);
        //转换
        List<ReportDetailDto> reportDtoList = convertReportDetailData(ctaDto.getCityNo(), detail);
        // 设置各环节最终日期
        for (int i = 0; i < reportDtoList.size(); i++) {
            if (EstateConstant.PROGRESS_REPORT == reportDtoList.get(i).getProgress() && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-报备、确认状态-有效
                ctaDto.setLatestReportFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(), "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_RELATION == reportDtoList.get(i).getProgress() && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-带看、确认状态-有效
                ctaDto.setLatestRelationFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(), "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_PLEDGED == reportDtoList.get(i).getProgress() && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-认筹、确认状态-有效
                ctaDto.setLatestPledgedFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(), "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_SUBSCRIBED == reportDtoList.get(i).getProgress() && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-认购、确认状态-有效
                ctaDto.setLatestSubscribedFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(), "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_BARGAIN == reportDtoList.get(i).getProgress() && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-成交、确认状态-有效
                ctaDto.setLatestBargainFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(), "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_BACK == reportDtoList.get(i).getProgress() && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-退筹、确认状态-有效
                ctaDto.setLatestBackFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(), "yyyy年MM月dd日"));
            }
        }
        List<ReportDetail> list = reportDetailMapper.checkAccountOk(param);
        String lastAccountDate = checkAccountOk(list);
        if (StringUtil.isNotEmpty(lastAccountDate)) {
            ctaDto.setCommissionProgress(EstateConstant.PROGRESS_COMMISSION);// 报备进度-结佣
            ctaDto.setLatestCommissionFollowDate(lastAccountDate.split(",")[0]);
            ctaDto.setLatestCommissionFollowDate2(lastAccountDate.split(",")[1]);
            ReportDetailDto resultData = new ReportDetailDto();
            resultData.setProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(13507)));// 结佣
            resultData.setProgress(13507);
            resultData.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(13601)));//确认状态有效
            resultData.setReportMemo("");
            resultData.setFollowDateDisPlay(lastAccountDate.split(",")[0]);
            resultData.setBizOccurDate(lastAccountDate.split(",")[0].substring(0, 9));
            reportDtoList.add(resultData);
        }
        if (ctaDto.getConfirmStatus() == 13603) {
            ctaDto.setConfirmStatus(13601);
            ctaDto.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(ctaDto.getConfirmStatus())));
            ctaDto.setLatestProgress(ctaDto.getLatestProgress() - 1);
            ctaDto.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(ctaDto.getLatestProgress())));
        }

        reportInfoDto.setReport(ctaDto);
        reportInfoDto.setReportDetails(reportDtoList);
        return reportInfoDto;
    }

    /**
     * 验证是否结佣
     */
    public String checkAccountOk(List<ReportDetail> list) throws Exception {
        String returnlastAccountDate = "";
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                ReportDetail reportDetail = list.get(i);
                // 结算状态 未结算或者部分结算
                if (reportDetail.getAccountStatus() != 14201) {
                    break;
                } else {// 结算状态 已结算
                    // 最新状态为成销 并且 佣金结算状态 已结佣
                    if (reportDetail.getProgress() == 13505 && reportDetail.getCommissionAccountStatus() == 14201) {
                        returnlastAccountDate = getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm") + "," + getFormatDateString(list.get(i).getUptDt(), "yyyy年MM月dd日");
                        break;
                    }
                }
            }
        }
        if (StringUtil.isNotEmpty(returnlastAccountDate)) {
            return returnlastAccountDate;
        }
        return "";
    }

    /**
     * 查询-list
     *
     * @param queryParam
     * @return
     */
    @SuppressWarnings("unchecked")
    public ResultData<List<ReportSearchResultDto>> queryList(Map<?, ?> param) throws Exception {
        //构建返回
        ResultData<List<ReportSearchResultDto>> resultData = new ResultData<List<ReportSearchResultDto>>();
        //查询
        Map<String, String> reMap = (Map<String, String>) param;
        //查询参数调整
        //确认状态为空，进程非空
        if (StringUtil.isEmpty(reMap.get("confirmStatus")) && StringUtil.isNotEmpty(reMap.get("latestProgress"))) {
            //进程在成销之前-进度加1，否则不变
            Integer latestProgress = Integer.valueOf(reMap.get("latestProgress"));

            if (StringUtil.isNotEmpty(reMap.get("latestProgress")) && Integer.valueOf(reMap.get("latestProgress")) < 13505) {
                reMap.put("latestProgress", String.valueOf(Integer.valueOf(reMap.get("latestProgress")) + 1));
            } else {
                reMap.put("latestProgress", reMap.get("latestProgress"));
            }
            //进程是成销-状态有效，否则状态未认定
            if (latestProgress.intValue() == 13505) {
                reMap.put("confirmStatus", "13601");
                reMap.put("brokerageStatus", "22001");
            } else if (latestProgress.intValue() == 13504) {
                reMap.put("confirmStatus", "13603");
            } else if (latestProgress.intValue() == 13507) {
                reMap.put("latestProgress", "13505");
                reMap.put("confirmStatus", "13601");
                reMap.put("brokerageStatus", "22002,22003");
            }
        }
        String uuId = UUID.randomUUID().toString();
        logger.info("查询订单列表-" + uuId + "-selectReportList-Start，" + reMap.toString());
        //查询REPORT信息
        final List<ReportSearchResult> craList = reportMapper.selectReportList(reMap);
        logger.info("查询订单列表-" + uuId + "-selectReportList-End");

        //转换
        List<ReportSearchResultDto> reportDtoList = convertReportSearchResultData(craList);
//        for(ReportSearchResultDto r:reportDtoList){
//        	r.setCustomerFromStr(SystemParam.getDicValueByDicCode(r.getCustomerFrom()+""));
//        }
        logger.info("查询订单列表-" + uuId + "convertReportSearchResultData - End");
        resultData.setTotalCount((String) reMap.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(reportDtoList);
        return resultData;
    }


    /**
     * 退回带看已确认的状态
     *
     * @param storePerformSet
     * @return
     * @throws Exception
     */
    public ResultData<Integer> updateReback(Map<?, ?> param) throws Exception {
        ResultData<Integer> resultData = new ResultData<Integer>();
        Report reportParm = new Report();
        setReportDto(param, reportParm);

        Map<String, Object> reMap = new HashMap<>();
        Date pledgedBackDate = reportParm.getPledgedBackDate();
        Date roughBackDate = reportParm.getRoughBackDate();
        Date dealBackDate = reportParm.getDealBackDate();

        reMap.put("estateId", reportParm.getEstateId());
        reMap.put("reportId", reportParm.getReportId());
        Report reportDb = reportMapper.getReport(reMap).get(0);

        // 退筹，退定，退房日期和带看日期加入关账判断
        // added by wang kanlin 2017/09/12
        Map<?, ?> switchMonth = null;
        Date switchDate = new Date();
        int year = 0;
        int month = 0;
        try {
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("cityNo", reportDb.getCityNo());
            //switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(queryParam);
            switchMonth = commonMapper.checkCitySwitchMonth(reportDb.getCityNo());
            month = Integer.valueOf(switchMonth.get("month").toString());
            year = Integer.valueOf(switchMonth.get("year").toString());
            Calendar c = Calendar.getInstance();
            c.set(year, month, 1, 0, 0, 0);
            // c.add(Calendar.MONTH, 1);
            c.set(Calendar.MILLISECOND, 0);
            switchDate = c.getTime();
        } catch (Exception e) {
            resultData.setReturnData(201);
            resultData.setReturnMsg("查询关账时间失败");
            return resultData;
        }
        String failDate = "新房联动业务已关账至y年m月，请选择未关账日期！";

        if (pledgedBackDate != null) {
            if (reportDb.getPledgedDate() != null && pledgedBackDate.before(reportDb.getPledgedDate())) {
                resultData.setReturnMsg("退筹时间必须大于等于认筹时间");
                resultData.setReturnData(201);
                return resultData;
            }
            if (pledgedBackDate.before(switchDate)) {
                resultData.setReturnData(201);
                resultData.setFail(failDate.replace("y", year + "").replace("m", month + ""));
                return resultData;
            }
        }

        int num = reportMapper.checkRebackBrokerage(reportDb.getReportId());

        if (roughBackDate != null) {
            if (reportDb.getRoughDate() != null && roughBackDate.before(reportDb.getRoughDate())) {
                resultData.setReturnMsg("退定时间必须大于等于大定时间");
                resultData.setReturnData(201);
                return resultData;
            }
            if (roughBackDate.before(switchDate)) {
                resultData.setReturnData(201);
                resultData.setFail(failDate.replace("y", year + "").replace("m", month + ""));
                return resultData;
            }
            if (num > 0) {
                resultData.setReturnMsg("垫佣金额不为0，不允许退定！请在垫佣导表做冲销处理！");
                resultData.setReturnData(201);
                return resultData;
            }
        }
        if (dealBackDate != null) {
            if (reportDb.getDealDate() != null && dealBackDate.before(reportDb.getDealDate())) {
                resultData.setReturnMsg("退房时间必须大于等于成销时间");
                resultData.setReturnData(201);
                return resultData;
            }
            if (dealBackDate.before(switchDate)) {
                resultData.setReturnData(201);
                resultData.setFail(failDate.replace("y", year + "").replace("m", month + ""));
                return resultData;
            }
            /*if (num > 0) {
                resultData.setReturnMsg("垫佣金额不为0，不允许退房！请在垫佣导表做冲销处理！");
                resultData.setReturnData(201);
                return resultData;
            }*/

//            //判断实际返佣
//            Integer relCount = lnkYjSjfyMapper.checkPreAmountForReback(param);
//            if (relCount.intValue() > 0) {
//                resultData.setReturnMsg("该报备订单已有实际返佣，请通知对口结算解锁退房!");
//                resultData.setReturnData(201);
//                return resultData;
//            }

            // 如果没有进行退房解锁
            if (null == reportDb.getRebackFlag() || !reportDb.getRebackFlag()) {
                //部分结佣和已结佣
                if ("22002".equals(reportDb.getBrokerageStatus()) || "22003".equals(reportDb.getBrokerageStatus())) {
                    resultData.setReturnMsg("该报备订单状态是已结佣或者部分结佣，请通知对口结算解锁退房!");
                    resultData.setReturnData(201);
                    return resultData;
                } else {
                    Integer relCount = lnkYjSjfyMapper.checkPreAmountForReback(param);
                    if (relCount.intValue() > 0) {
                        resultData.setReturnMsg("该报备订单已有实际返佣，请通知对口结算解锁退房!");
                        resultData.setReturnData(201);
                        return resultData;
                    }
                }
            }
        }


        Report updateReport = new Report();
        updateReport.setId(reportDb.getId());
        updateReport.setPledgedBackDate(pledgedBackDate);
        updateReport.setRoughBackDate(roughBackDate);
        updateReport.setDealBackDate(dealBackDate);
        reportMapper.update(updateReport);//更新退筹、退定、退房时间
        reportMapper.updateReback(reportParm);//回退


        //修改detail表
        ReportDetail tempReportDetail = new ReportDetail();
        tempReportDetail.setEstateId(reportParm.getEstateId());
        tempReportDetail.setCountId(reportParm.getReportId());
        //补
        ReportDetail copyPledged = new ReportDetail();

        StringBuilder changeMsg = new StringBuilder();
        if (pledgedBackDate != null)    //退筹
        {
            tempReportDetail.setProgress(13504);
            copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
            changeMsg.append("案件记录由认筹（认筹时间：")
                    .append(DateUtil.fmtDate(reportDb.getPledgedDate(), "yyyy-MM-dd"))
                    .append("）调整为退筹,退筹日期:").append(DateUtil.fmtDate(pledgedBackDate, "yyyy-MM-dd"));
        }
        if (roughBackDate != null)    //退定
        {
            tempReportDetail.setProgress(13505);
            copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
            changeMsg.append("案件记录由大定（客户名:").append(reportDb.getCustomerNm()).append(";")
                    .append("电话:").append(reportDb.getCustomerTel()).append(";")
                    .append("楼室号:").append(copyPledged.getBuildingNo()).append("室;")
                    .append("大定面积:").append(copyPledged.getRoughArea()).append("㎡;")
                    .append("大定总价:").append(copyPledged.getRoughAmount()).append("元;")
                    .append("大定时间:").append(DateUtil.fmtDate(copyPledged.getRoughDate(), "yyyy-MM-dd"))
                    .append("）修改为退定,退定日期:").append(DateUtil.fmtDate(roughBackDate, "yyyy-MM-dd"));
        }
        if (dealBackDate != null)    //退房
        {
            //附件信息
            if (param.get("fileRecordMainIds") != null && StringUtil.isNotEmpty(param.get("fileRecordMainIds").toString())) {
                Integer refId = reportDb.getId();
                String[] fileIdArr = param.get("fileRecordMainIds").toString().split(",");
                FileRecordMain file;
                for (String fileId : fileIdArr) {
                    file = new FileRecordMain();
                    file.setRefId(refId);
                    file.setFileRecordMainId(Integer.valueOf(fileId));
                    fileRecordMainService.update(file);
                }
            }

            tempReportDetail.setProgress(13505);
            copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
            changeMsg.append("案件记录由大定（客户名:").append(reportDb.getCustomerNm()).append(";")
                    .append("电话:").append(reportDb.getCustomerTel()).append(";")
                    .append("楼室号:").append(copyPledged.getBuildingNo()).append("室;")
                    .append("成销面积:").append(copyPledged.getArea()).append("㎡;")
                    .append("成销总价:").append(copyPledged.getDealAmount()).append("元;")
                    .append("成销时间:").append(DateUtil.fmtDate(copyPledged.getDealDate(), "yyyy-MM-dd"))
                    .append("）修改为退房,退房日期:").append(DateUtil.fmtDate(dealBackDate, "yyyy-MM-dd"));

            //可退房标记清空
            Map<String, Object> rebackMap = new HashMap<>();
            rebackMap.put("reportId", reportDb.getId());
            rebackMap.put("rebackFlag", false);
            reportMapper.unlockReback(rebackMap);

            //佣金导入
            Map<String, Object> yjMap = new HashMap<>();
            yjMap.put("reportId", reportParm.getReportId());
            yjMap.put("companyNo", copyPledged.getCompanyId());
            yjMap.put("detailId", copyPledged.getId());
            yjMap.put("userId", param.get("userId"));
            yjMap.put("rebackDate", dealBackDate);
            //应计收入
            lnkYjYjsrMapper.insertRebackYjsrRecord(yjMap);
            //应收收入
            lnkYjYssrMapper.insertRebackYssrRecord(yjMap);
            /*//应计垫佣
            lnkYjYjdyMapper.insertRebackYjdyRecord(yjMap);*/

        }

        if (copyPledged == null) {
            resultData.setReturnMsg("报备数据异常,请联系系统管理员！");
            resultData.setReturnData(201);
            return resultData;
        }
        /*tempReportDetail.setProgress(13503);
        ReportDetail copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
        if(copyPledged==null){
        	 tempReportDetail.setProgress(13504);
        	 copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
        	 if(copyPledged==null){
        		 resultData.setReturnMsg("报备数据异常,请联系系统管理员！");
    			 resultData.setReturnData(201);
    			 return resultData;
        	 }
        }*/
        tempReportDetail.setProgress(null);//清除

        tempReportDetail.setPledgedBackDate(pledgedBackDate);
        tempReportDetail.setRoughBackDate(roughBackDate);
        tempReportDetail.setDealBackDate(dealBackDate);
        //更新退筹、退定、退房时间 和回退
        tempReportDetail.setRoughDateClear("1");
        reportDetailMapper.updateOperateDate(tempReportDetail);
        reportDetailMapper.updateOtherStatus(tempReportDetail);
        //补数据
        String countId = reportDetailMapper.getMaxCountId(tempReportDetail);
        String prefix = countId.substring(0, countId.indexOf("-"));
        String suffix = countId.substring(countId.indexOf("-") + 1);
        //String middle = "10".compareTo(suffix) > 0? "-0": "-";
        int suffixNum = Integer.valueOf(suffix) + 1;
        String middle = suffixNum < 10 ? "-0" : "-";
        countId = prefix + middle + String.valueOf(Integer.valueOf(suffix) + 1);
        copyPledged.setConfirmStatus(13603);
        copyPledged.setProgress(13503);
        copyPledged.setCrtDt(new Date());
        copyPledged.setUptDt(new Date());
        copyPledged.setId(null);
        copyPledged.setCountId(countId);
        copyPledged.setRoughDate(null);
        copyPledged.setReportNo(reportParm.getReportId());
        reportDetailMapper.create(copyPledged);
        //作废reportDetailMapper.updateReback(reportDetail);  已补新数据  改步骤作废

        //删除带看之后的附件
        Map<String, Object> fileRecMap = new HashMap<>();
        fileRecMap.put("refId", reportDb.getId());
        fileRecMap.put("fileSourceId", 5);
        fileRecordMainService.deletePartReportFile(fileRecMap);

        //添加日志信息
        AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
        achieveMentChangeLog.setRelateId(reportDb.getId());
        achieveMentChangeLog.setChangeContent(changeMsg.toString());
        achieveMentChangeLog.setCreateUserCode(reportParm.getOperUserCode());
        achieveMentChangeLog.setCreateUserName(reportParm.getOperUserName());
        achieveMentChangeLog.setCreateDate(new Date());
        achieveMentChangeLog.setDelFlag(false);
        achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);


        if (roughBackDate != null
                && reportDb.getCustomerFrom() != null
                && "17405".equals(reportDb.getCustomerFrom().toString())) {    //退定
            backToFangyou(reportDb, reportParm.getOperUserCode(), 1);
        }
        if (dealBackDate != null
                && reportDb.getCustomerFrom() != null
                && "17405".equals(reportDb.getCustomerFrom().toString())) {    //退房
            backToFangyou(reportDb, reportParm.getOperUserCode(), 2);

        }

        if (roughBackDate != null) {    //退定
            yFInterfaceService.noticeYx(reportDb.getReportId(), "12", reportParm.getOperUserCode(), "0");//退定
        }
        if (dealBackDate != null) {    //退房
            yFInterfaceService.noticeYx(reportDb.getReportId(), "13", reportParm.getOperUserCode(), "0");//退房
        }

        resultData.setReturnData(0);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    public static void setReportDto(Map<?, ?> reqMap, Report report) throws Exception {
        String operUserCode = (String) reqMap.get("operUserCode");
        if (StringUtil.isNotEmpty(operUserCode)) {
            report.setOperUserCode(operUserCode);
        }

        String operUserName = (String) reqMap.get("operUserName");
        if (StringUtil.isNotEmpty(operUserName)) {
            report.setOperUserName(operUserName);
        }

        String estateId = (String) reqMap.get("estateId");
        if (StringUtil.isNotEmpty(estateId)) {
            report.setEstateId(estateId);
        }

        String reportId = (String) reqMap.get("reportId");
        if (StringUtil.isNotEmpty(reportId)) {
            report.setReportId(reportId);
        }

        String status = (String) reqMap.get("status");
        String operateDate = (String) reqMap.get("operateDate");
        Date date = getFormatStringDate(operateDate + " 00:00:00", "yyyy-MM-dd HH:mm:ss");
        if ("13503".equals(status)) {//退筹
            report.setPledgedBackDate(date);
        } else if ("13504".equals(status)) {//退定
            report.setRoughBackDate(date);
        } else if ("13505".equals(status)) {//退房
            report.setDealBackDate(date);
        }
    }

    /**
     * 根据日期字符串返回日期对象
     *
     * @param sDat      日期字符串
     * @param strFormat 格式
     * @return 日期对象
     * @throws ParseException
     */
    private static Date getFormatStringDate(String sDat, String strFormat) throws ParseException {
        if (StringUtil.isNotEmpty(sDat)) {
            // 解析日期
            SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
            return myFmt.parse(sDat);
        }
        return null;
    }

    /**
     * 退定 退房 房友
     *
     * @param queryParam
     * @return
     */
    public void backToFangyou(Report reportDb, String userCode, int type) throws Exception {
    	/*
    	User user = new User();
    	FangyouReportLog fangyouReportLog = new FangyouReportLog();
      	
    	try {
    		user = userMapper.getUserByCode(userCode);
    		String url = SystemParam.getWebConfigValue("fangyouReportUrl") + "/merchant/site/register/site/approve/refund/"+reportDb.getFyReportId();
            Map<String, Object> rspMap = new HashMap<>();
            rspMap.put("type", type);
            rspMap.put("crmUserId", user.getUserId());

        	String jsonData = JsonUtil.parseToJson(rspMap);	                 
            String opResult = HttpClientUtil.jsonPostFangyou(url, jsonData);
            if(type == 1){
            	fangyouReportLog.setTypeId("4");
            }else{
            	fangyouReportLog.setTypeId("6");
			}
	            
         	fangyouReportLog.setUserIdCreate(user.getUserId());
         	fangyouReportLog.setReqParamJson("fyReportId="+reportDb.getFyReportId()+"#reqParam="+jsonData);
         	fangyouReportLog.setRspParamJson(opResult);
            fangyouReportLogService.addLog(fangyouReportLog);

            //删除大定图片
            fangyouReportFileMapper.deletePartReportFile(reportDb);
		} catch (Exception e) {
	        if(type == 1){
            	fangyouReportLog.setTypeId("4");
            }else{
            	fangyouReportLog.setTypeId("6");
			}
			fangyouReportLog.setUserIdCreate(user.getUserId());
         	fangyouReportLog.setReqParamJson(JsonUtil.parseToJson(reportDb));
         	fangyouReportLog.setRspParamJson("退定退成销通知房友操作异常");
            fangyouReportLogService.addLog(fangyouReportLog);
            e.printStackTrace();
			logger.error("sceneEstateService", "SceneEstateService", "dealCrm", "", null, "", "退定退成销通知房友操作异常", e);
		}
    	*/


        User userinfo = new User();
        try {
            String typeId = null;
            userinfo = userMapper.getUserByCode(userCode);
            if (userinfo == null) {
                userinfo = new User();
            }
            YFStatusSync yFStatusSync = new YFStatusSync();
            yFStatusSync.setCrm_report_id(reportDb.getReportId());
            if (type == 1) {
                yFStatusSync.setReport_status("退定");
                typeId = "4";
            } else {
                yFStatusSync.setReport_status("退房");
                typeId = "6";
            }
            //新增字段 yf_report_id 2019/01/09
            yFStatusSync.setYf_report_id(reportDb.getFyReportId());
            yFInterfaceService.getyFInterfaceInfo("/CRMReportStatusSync", JSON.toJSONString(yFStatusSync), typeId, userinfo.getUserId(), reportDb.getReportId());
            //删除大定图片
            fangyouReportFileMapper.deletePartReportFile(reportDb);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("reportService", "ReportService", "backToFangyou", "", null, "", "退定退成销通知有房操作异常", e);
        }

    }

    public int updateReback(ReportDetail reportDetail) throws Exception {
        int count = reportDetailMapper.updateReback(reportDetail);
        return count;
    }


    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ReportSearchResultDto>
     */
    private List<ReportSearchResultDto> convertReportSearchResultData(List<ReportSearchResult> craList) throws Exception {
        List<ReportSearchResultDto> reportDtoList = new ArrayList<ReportSearchResultDto>();

        if (null != craList && !craList.isEmpty()) {
            ReportSearchResultDto craDto = null;
            for (ReportSearchResult cra : craList) {
                // 报备、带看、大定
                if (cra.getLatestProgress() < 13505 || (cra.getLatestProgress() == 13505 && cra.getConfirmStatus() != 13601)) {
                    cra.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getLatestProgress() - 1)));
                    // 未认定的显示 “有效”
                    if (cra.getConfirmStatus() == 13603) {
                        cra.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(13601)));
                    } else {
                        cra.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getConfirmStatus())));
                    }
                }
                // 成销 和 结佣
                else {
                    cra.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getLatestProgress())));
                    cra.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getConfirmStatus())));
                }


                cra.setBrokerageStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(cra.getBrokerageStatus())));

                craDto = new ReportSearchResultDto();
                BeanUtils.copyProperties(cra, craDto);
                craDto.setCustomerFromStr(SystemParam.getDicValueByDicCode(craDto.getCustomerFrom() + ""));
                reportDtoList.add(craDto);
            }
        }
        return reportDtoList;
    }

    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ReportDetailDto>
     */
    private List<ReportDetailDto> convertReportDetailData(String cityNo, List<ReportDetail> craList) throws Exception {
        List<ReportDetailDto> reportDtoList = new ArrayList<ReportDetailDto>();

        if (null != craList && !craList.isEmpty()) {
            ReportDetailDto craDto = null;
            for (ReportDetail cra : craList) {
                if (cra.getConfirmStatus() == 13603 || cra.getConfirmStatus() == 13602) {
                    continue;
                }
                craDto = new ReportDetailDto();
                BeanUtils.copyProperties(cra, craDto);
                craDto.setFollowDateDisPlay(getFormatDateString(cra.getRecognitionDay(), "yyyy-MM-dd HH:mm:ss"));

                //业务节点发生日期
                Date bizOccurDate = null;
                switch (cra.getProgress()) {
                    case 13501:
                        bizOccurDate = cra.getReportDate();
                        break;
                    case 13502:
                        bizOccurDate = cra.getSeeDate();
                        break;
                    case 13503:
                        bizOccurDate = cra.getPledgedDate();
                        if (bizOccurDate == null) {
                            bizOccurDate = cra.getSeeDate();
                        }
                        break;
                    case 13504:
                        bizOccurDate = cra.getRoughInputDate();

                        //判断是否已关账，控制修改按钮
                        if (switchCloseCheck(cityNo, bizOccurDate)) {
                            craDto.setModFlagControl(1);
                        } else {
                            craDto.setModFlagControl(0);
                        }

                        break;
                    case 13505:
                        bizOccurDate = cra.getDealDate();

                        //判断是否已关账，控制修改按钮
                        if (switchCloseCheck(cityNo, bizOccurDate)) {
                            craDto.setModFlagControl(1);
                        } else {
                            craDto.setModFlagControl(0);
                        }

                        break;
                    case 13506:
                        bizOccurDate = cra.getPledgedBackDate();
                        break;
                    case 13507:
                        //TODO
                }
                craDto.setBizOccurDate(getFormatDateString(bizOccurDate, "yyyy-MM-dd"));
                reportDtoList.add(craDto);
            }
        }
        return reportDtoList;
    }

    private boolean switchCloseCheck(String cityNo, Date operDate) throws Exception {

        Map<String, Object> map = new HashMap<>();
        map.put("cityNo", cityNo);
        //switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(map);
        Map<?, ?> switchMonth = commonMapper.checkCitySwitchMonth(cityNo);
        if (switchMonth == null) {
            return false;
        }
        int month = Integer.valueOf(switchMonth.get("month").toString());
        int year = Integer.valueOf(switchMonth.get("year").toString());
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        //c.add(Calendar.MONTH, 1);
        c.set(Calendar.MILLISECOND, 0);
        Date switchDate = c.getTime();
        if (operDate.before(switchDate)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 取得格式化的日期字符串
     *
     * @param dat       日期对象
     * @param strFormat 格式
     * @return 格式化后的字符串
     */
    private String getFormatDateString(Date dat, String strFormat) {
        if (null == dat) {
            return "";
        }
        SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
        return myFmt.format(dat);
    }

    public ResultData<ReportSearchResultDto> getOperDetail(Map<String, Object> queryParam) {

        ResultData<ReportSearchResultDto> result = new ResultData<>();
        ReportSearchResultDto dto = reportDetailMapper.getOperDetail(queryParam);
        if (dto != null) {
            getBrokerageInfo(dto);
            getReportFYInfo(dto);//获取返佣对象
            result.setReturnData(dto);
        } else {
            result.setFail();
        }

        return result;

    }

    /**
     * 获取返佣对象
     *
     * @param dto
     * @return
     */
    private ReportSearchResultDto getReportFYInfo(ReportSearchResultDto dto) {
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportId", dto.getReportId());
        List<ReportFYDto> fyList = reportMapper.getReportFYInfo(reqMap);
        dto.setFyObjectList(fyList);
        return dto;
    }

    public ResultData<?> operDetailUdpate(Map<String, Object> queryParam) throws Exception {
        ResultData<?> result = new ResultData<>();

        String oldCustomerNm = queryParam.get("oldCustomerNm").toString();
        String customerNm = queryParam.get("customerNm").toString();
        String oldCustomerTel = queryParam.get("oldCustomerTel").toString();
        String customerTel = queryParam.get("customerTel").toString();

        String oldCustomerNmTwo = queryParam.get("oldCustomerNmTwo").toString();
        String customerNmTwo = queryParam.get("customerNmTwo").toString();
        String oldCustomerTelTwo = queryParam.get("oldCustomerTelTwo").toString();
        String customerTelTwo = queryParam.get("customerTelTwo").toString();
        String customerIdTwo = queryParam.get("customerIdTwo").toString();
        if (StringUtil.isEmpty(customerIdTwo)) {
            customerIdTwo = RandomStringUtils.randomNumeric(10);
        }


        String oldBuildingNo = queryParam.get("oldBuildingNo").toString();
        String oldBuildingNoId = queryParam.get("oldBuildingNoId").toString();
        String buildingNo = queryParam.get("buildingNo").toString();
        String buildingNoId = queryParam.get("buildingNoId").toString();
        String progress = queryParam.get("progress").toString();

        String oldWYTypeCode = queryParam.get("oldWYTypeCode").toString();
        String oldWYTypeName = queryParam.get("oldWYTypeName").toString();
        String wyTypeCode = queryParam.get("wyTypeCode").toString();
        String wyTypeName = queryParam.get("wyTypeName").toString();

        BigDecimal oldRoughArea = new BigDecimal(queryParam.get("oldRoughArea").toString());
        BigDecimal roughArea = new BigDecimal(queryParam.get("roughArea").toString());
        BigDecimal oldRoughAmount = new BigDecimal(queryParam.get("oldRoughAmount").toString());
        BigDecimal roughAmount = new BigDecimal(queryParam.get("roughAmount").toString());
        String oldBizOperDate = queryParam.get("oldBizOperDate").toString();
        String bizOperDate = queryParam.get("bizOperDate").toString();


        Integer reportId = Integer.valueOf(queryParam.get("reportId").toString());
        //判断是否大定审核通过
        Report reportForId = reportMapper.getById(reportId);
        //为1则大定审核通过，不允许修改
        /*if(Objects.equals(reportForId.getRoughAuditStatus(),"1")){
            result.setFail("该报备房源大定已审核，不能修改信息！");
            return result;
        }*/
        Integer reportDetailId = Integer.valueOf(queryParam.get("reportDetailId").toString());
        ReportDetail reportDetail = new ReportDetail();
        reportDetail.setId(reportDetailId);
        Report report = new Report();
        report.setId(reportId);
        boolean delModFlag = false;
        boolean reportModFlag = false;


        if (null != queryParam.get("buildingNo")) {
            report.setBuildingNo((String) queryParam.get("buildingNo"));
        }
        if (null != queryParam.get("buildingNoId")) {
            report.setBuildingNoId((String) queryParam.get("buildingNoId"));
        }
        if (null != queryParam.get("isWrap") && !StringUtils.isEmpty((String) queryParam.get("isWrap"))) {
            report.setIsWrap(Integer.parseInt((String) queryParam.get("isWrap")));
        }
        if (null != queryParam.get("wyTypeCode")) {
            report.setWyTypeCode((String) queryParam.get("wyTypeCode"));
        }

        if (progress.equals("13505")) {
            String accountProjectNo = queryParam.get("accountProjectNo").toString();
            report.setAccountProjectNo(accountProjectNo);
            if (accountProjectNo != null && accountProjectNo != "") {
                reportModFlag = true;
            }
        }

        StringBuilder changeMsg = new StringBuilder();

        String fileRecordMainIds = queryParam.get("fileRecordMainIds").toString();
        String oldFileRecordMainIds = queryParam.get("oldFileRecordMainIds").toString();


        boolean fileChangeFlag = false;
        if (!fileRecordMainIds.equals(oldFileRecordMainIds)) {
            fileChangeFlag = true;
            //附件信息
            if (queryParam.get("fileRecordMainIds") != null && StringUtil.isNotEmpty(queryParam.get("fileRecordMainIds").toString()) && queryParam.get("id") != null) {
                Integer refId = Integer.valueOf(queryParam.get("id").toString());
                String[] fileIdArr = queryParam.get("fileRecordMainIds").toString().split(",");
                FileRecordMain file;
                for (String fileId : fileIdArr) {
                    file = new FileRecordMain();
                    file.setRefId(refId);
                    file.setFileRecordMainId(Integer.valueOf(fileId));
                    fileRecordMainService.update(file);
                }
            }
            delModFlag = true;
            changeMsg.append("附件信息更新,");
        }

        if (!oldCustomerNm.trim().equals(customerNm.trim())) {
            reportDetail.setCustomerNm(customerNm);
            delModFlag = true;
            reportModFlag = true;
            report.setCustomerNm(customerNm);
            changeMsg.append("客户姓名由").append(oldCustomerNm).append("修改为").append(customerNm).append(",");
        }
        if (!oldCustomerTel.trim().equals(customerTel.trim())) {
            reportDetail.setCustomerTel(customerTel);
            delModFlag = true;
            reportModFlag = true;
            report.setCustomerTel(customerTel);
            changeMsg.append("客户电话由").append(oldCustomerTel).append("修改为").append(customerTel).append(",");
        }

        if (!oldCustomerNmTwo.trim().equals(customerNmTwo.trim())) {
            reportDetail.setCustomerNmTwo(customerNmTwo);
            delModFlag = true;
            reportModFlag = true;
            report.setCustomerNmTwo(customerNmTwo);
            changeMsg.append("客户姓名由").append(oldCustomerNmTwo).append("修改为").append(customerNmTwo).append(",");
        }
        if (!oldCustomerTelTwo.trim().equals(customerTelTwo.trim())) {
            reportDetail.setCustomerTelTwo(customerTelTwo);
            delModFlag = true;
            reportModFlag = true;
            report.setCustomerTelTwo(customerTelTwo);
            changeMsg.append("客户电话由").append(oldCustomerTelTwo).append("修改为").append(customerTelTwo).append(",");
        }

        if (!oldWYTypeCode.trim().equals(wyTypeCode.trim())) {
            reportDetail.setWyTypeCode(wyTypeCode);
            delModFlag = true;
            changeMsg.append("物业类型由").append(oldWYTypeName).append("修改为").append(wyTypeName).append(",");
        }

        if (!oldBuildingNoId.trim().equals(buildingNoId.trim())) {
            reportDetail.setBuildingNoId(buildingNoId);
            delModFlag = true;
        }
        if (!oldBuildingNo.trim().equals(buildingNo.trim())) {
            reportDetail.setBuildingNo(buildingNo);

            delModFlag = true;
            changeMsg.append("楼室号由").append(oldBuildingNo).append("修改为").append(buildingNo).append(",");
        }
        if (progress.equals("13504")) {
            if (oldRoughArea.compareTo(roughArea) != 0) {
                reportDetail.setRoughArea(roughArea);
                delModFlag = true;
                changeMsg.append("大定面积由").append(oldRoughArea).append("修改为").append(roughArea).append(",");
            }
            if (oldRoughAmount.compareTo(roughAmount) != 0) {
                reportDetail.setRoughAmount(roughAmount);
                delModFlag = true;
                changeMsg.append("大定总价由").append(oldRoughAmount).append("修改为").append(roughAmount).append(",");
            }
            if (!oldBizOperDate.equals(bizOperDate)) {
                reportDetail.setRoughInputDate(DateUtil.getDate(bizOperDate, "yyyy-MM-dd"));
                report.setRoughInputDate(DateUtil.getDate(bizOperDate, "yyyy-MM-dd"));
                delModFlag = true;
                reportModFlag = true;
                changeMsg.append("大定时间由").append(oldBizOperDate).append("修改为").append(bizOperDate).append(",");
            }
        }
        if (progress.equals("13505")) {
            BigDecimal oldArea = new BigDecimal(queryParam.get("oldArea").toString());
            BigDecimal area = new BigDecimal(queryParam.get("area").toString());
            BigDecimal oldDealAmount = new BigDecimal(queryParam.get("oldDealAmount").toString());
            BigDecimal dealAmount = new BigDecimal(queryParam.get("dealAmount").toString());

            if (oldArea.compareTo(area) != 0) {
                reportDetail.setArea(area);
                delModFlag = true;
                changeMsg.append("成销面积由").append(oldArea).append("修改为").append(area).append(",");
            }
            if (oldDealAmount.compareTo(dealAmount) != 0) {
                reportDetail.setDealAmount(dealAmount);
                delModFlag = true;
                changeMsg.append("成销总价由").append(oldDealAmount).append("修改为").append(dealAmount).append(",");
            }
            if (!oldBizOperDate.equals(bizOperDate)) {
                reportDetail.setDealDate(DateUtil.getDate(bizOperDate, "yyyy-MM-dd"));
                report.setDealDate(DateUtil.getDate(bizOperDate, "yyyy-MM-dd"));
                delModFlag = true;
                reportModFlag = true;
                changeMsg.append("成销时间由").append(oldBizOperDate).append("修改为").append(bizOperDate).append(",");
            }
            String settleConfirmDateOld = queryParam.get("settleConfirmDateOld").toString();
            String settleConfirmDate = queryParam.get("settleConfirmDate").toString();
            if (!settleConfirmDateOld.equals(settleConfirmDate)) {
                reportDetail.setSettleConfirmDate(DateUtil.getDate(settleConfirmDate, "yyyy-MM-dd"));
                delModFlag = true;
                changeMsg.append("结算确认日期由").append(settleConfirmDateOld).append("修改为").append(settleConfirmDate).append(",");
            }
        }

        if (!oldBizOperDate.equals(bizOperDate)) {
            try {

                Map<String, Object> map = new HashMap<>();
                //map.put("cityNo", queryParam.get("cityNo").toString());
                //switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(map);
                Map<?, ?> switchMonth = commonMapper.checkCitySwitchMonth(queryParam.get("cityNo").toString());
                if (switchMonth != null) {
                    int month = Integer.valueOf(switchMonth.get("month").toString());
                    int year = Integer.valueOf(switchMonth.get("year").toString());
                    Calendar c = Calendar.getInstance();
                    c.set(year, month, 1, 0, 0, 0);
                    //c.add(Calendar.MONTH, 1);
                    c.set(Calendar.MILLISECOND, 0);
                    Date switchDate = c.getTime();
                    Date date = DateUtil.getDate(bizOperDate, "yyyy-MM-dd");
                    if (date.before(switchDate)) {
                        result.setFail(HouseLinkageConstant.FAIL_REPORT_DATE_INVALID.replace("y", year + "").replace("m", month + ""));
                        return result;
                    }
                }
            } catch (Exception e) {
                result.setFail("判定关账月份异常");
                return result;
            }
        }

        if (delModFlag) {
            //修改报备明细表
            reportDetail.setUptEmpId(Integer.valueOf(queryParam.get("userId").toString()));
            reportDetail.setUptDt(new Date());
            reportDetail.setCustomerIdTwo(customerIdTwo);
            reportDetailMapper.update(reportDetail);

            //如果是大定，需要将已经生成的成销明细中的大定信息也修改
            if (progress.equals("13504")) {
                ReportDetail curDetail = reportDetailMapper.getById(reportDetail.getId());
                reportDetail.setEstateId(curDetail.getEstateId());
                reportDetail.setProgress(13505);
                reportDetail.setCountId(curDetail.getCountId());
                reportDetail.setCustomerIdTwo(customerIdTwo);
                reportDetailMapper.updateNextDetail(reportDetail);
            }

            //添加日志信息
            AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
            achieveMentChangeLog.setRelateId(reportId);
            achieveMentChangeLog.setChangeContent(changeMsg.substring(0, changeMsg.length() - 1));
            achieveMentChangeLog.setCreateUserCode(queryParam.get("userCode").toString());
            achieveMentChangeLog.setCreateUserName(queryParam.get("userName").toString());
            achieveMentChangeLog.setCreateDate(new Date());
            achieveMentChangeLog.setDelFlag(false);
            achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);

            //修改报备表-客户姓名，手机号，仅大定日期，成销日期
            //大定修改都更新大定审核状态
            if (progress.equals("13504")) {
                reportModFlag = true;
                report.setRoughAuditStatus("");
            }
            if (reportModFlag) {
                report.setCustomerIdTwo(customerIdTwo);
              //20200702 提交时更新核算主体(从项目得收入类合同获取核算主体)
//                reportMapper.update(report);
                reportMapper.updateNew(report);
            }
        } else {
            if (!fileChangeFlag) {
                result.setFail("未做改动");
            }
        }

        if ("13505".equals(progress)) {
            Map<String, Object> dealParam = new HashMap<String, Object>();
            dealParam.put("reportId", queryParam.get("reportIdHi").toString());

            dealParam.put("operateDate", bizOperDate);
            dealParam.put("buildingNoId", buildingNoId);
            dealParam.put("buildingNo", buildingNo);
            dealParam.put("area", queryParam.get("area").toString());
            dealParam.put("dealAmount", queryParam.get("dealAmount").toString());


            sceneEstatetService.dealCrm(dealParam);
        }

        return result;
    }

    /**
     * 查询公司信息根据fyReportId
     */
    public Report getByFyReportId(String fyReportId) throws Exception {
        Report report = this.reportMapper.getByFyReportId(fyReportId);
        return report;
    }


    /**
     * 结佣 房友
     *
     * @param queryParam
     * @return
     */
    public ResultData<String> sendFangyou() {
        ResultData<String> resultData = new ResultData<String>();
        CommissionDto commissionDto = new CommissionDto();
        String opResult = null;

        FangyouReportLog fangyouReportLog = new FangyouReportLog();
        fangyouReportLog.setTypeId("7");//结佣


        try {
            //结佣记录
            List<CommissionInfoDto> infoDtos = reportMapper.getBrokerageSend();
            if (!CollectionUtils.isEmpty(infoDtos)) {
                String url = SystemParam.getWebConfigValue("fangyouReportUrl") + "/merchant/site/register/site/approve/batch-commission/";
                commissionDto.setCommissionInfoDto(infoDtos);
                String jsonData = JsonUtil.parseToJson(commissionDto);
                opResult = HttpClientUtil.jsonPostFangyou(url, jsonData);
                fangyouReportLog.setUserIdCreate(0);
                fangyouReportLog.setReqParamJson(jsonData);
                fangyouReportLog.setRspParamJson(opResult);
                fangyouReportLogService.addLog(fangyouReportLog);

                Map<String, Object> result = JsonUtil.parseToObject(opResult, Map.class);
                if (200 == Integer.valueOf(result.get("returnCode").toString())) {//成功
                    for (CommissionInfoDto commissionInfoDto : infoDtos) {
                        if (commissionInfoDto.getRegisterId() == null) {
                            continue;
                        }
                        Map<String, Object> uptMap = new HashMap<>();
                        uptMap.put("fyReportId", commissionInfoDto.getRegisterId().toString());
                        uptMap.put("brokerageSendStatus", "2");
                        reportMapper.updateBrokerage(uptMap);
                    }
                } else {//失败
//	            if("".equals(opResult)){
//	            	Map<String, Object> fangyouMap = new HashMap<>();
//	            	fangyouMap = JSON.parseObject(opResult, Map.class);
//	                if(fangyouMap.containsKey("returnCode") && "200".equals(fangyouMap.get("returnCode")))
                    for (CommissionInfoDto commissionInfoDto : infoDtos) {
                        if (commissionInfoDto.getRegisterId() == null) {
                            continue;
                        }
                        Report reportBb = reportMapper.getByFyReportId(commissionInfoDto.getRegisterId().toString());
                        Map<String, Object> uptMap = new HashMap<>();
                        uptMap.put("fyReportId", commissionInfoDto.getRegisterId().toString());
                        if (reportBb.getBrokerageSendCount() == null || reportBb.getBrokerageSendCount() < 4) {
                            uptMap.put("brokerageSendStatus", "1");
                        } else {
                            uptMap.put("brokerageSendStatus", "2");
                        }

                        reportMapper.updateBrokerage(uptMap);
                    }
                }

            }
        } catch (Exception e) {
            resultData.setFail("操作异常");
            fangyouReportLog.setUserIdCreate(0);
            fangyouReportLog.setReqParamJson(JsonUtil.parseToJson(commissionDto));
            fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
            fangyouReportLogService.addLog(fangyouReportLog);
            e.printStackTrace();
            logger.error("reportService", "ReportService", "sendFangyou", "", null, "", "批量结佣通知房友失败", e);
            return resultData;
        }
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    /**
     * 合同信息推送到 房友
     *
     * @param queryParam
     * @return
     */
    public ResultData<String> sendContractDataToFangyou(SendContractDataToFy sendContractDataToFy) {
        ResultData<String> resultData = new ResultData<String>();
        String opResult = null;
        String returnMsg = null;
        SendContractFangyouLog sendContractFangyouLog = new SendContractFangyouLog();


        sendContractFangyouLog.setUserIdCreate(0);
        sendContractFangyouLog.setTypeId("1");

        try {
            //check必填项
            returnMsg = checkParam(sendContractDataToFy);
            if (returnMsg != null) {
                resultData.setReturnMsg(returnMsg);
                logger.info("待推送合同信息,check信息:" + returnMsg);
                return resultData;
            }
            String url = SystemParam.getWebConfigValue("ophyContract") + "api/member/contract_sync";
            //op开发环境
//    			String url = "http://10.122.136.5:53001/api/member/contract_sync";
            String jsonData = JsonUtil.parseToJson(sendContractDataToFy);
            opResult = HttpClientUtil.jsonPostFangyou(url, jsonData);
            sendContractFangyouLog.setReqParamJson(jsonData);
            sendContractFangyouLog.setRspParamJson(opResult);
            sendContractFangyouLogMapper.insertSelective(sendContractFangyouLog);

            Map<String, Object> result = JsonUtil.parseToObject(opResult, Map.class);
            returnMsg = result.get("returnMsg").toString();
            resultData.setReturnMsg(returnMsg);
            logger.info("推送合同信息到房友,返回码：" + result.get("returnCode").toString() + ",入参：" + jsonData + ",响应参数：" + opResult);

        } catch (Exception e) {
            resultData.setFail("操作异常");
            sendContractFangyouLog.setReqParamJson(JsonUtil.parseToJson(sendContractDataToFy));
            sendContractFangyouLog.setRspParamJson(JsonUtil.parseToJson(resultData));
            sendContractFangyouLogMapper.insertSelective(sendContractFangyouLog);
            e.printStackTrace();
            logger.error("reportService", "ReportService", "sendContractDataToFangyou", "", null, "", "合同信息推送房友失败", e);
            return resultData;
        }
        return resultData;
    }

    /**
     * desc:check参数(必填项)
     * 2019年2月21日
     * author:zhenggang.Huang
     */
    private String checkParam(SendContractDataToFy sendContractDataToFy) {
        String returnMsg = null;

        if (StringUtils.isEmpty(sendContractDataToFy)) {
            returnMsg = "待推送合同信息不能为空";
            return returnMsg;
        }
        //合同编号
        String contractNo = sendContractDataToFy.getCompany_no();
        if (StringUtils.isEmpty(contractNo)) {
            returnMsg = "合同编号不能为空";
            return returnMsg;
        }
        //合同状态(1-审核通过)
        Integer status = sendContractDataToFy.getStatus();
        if (StringUtils.isEmpty(status)) {
            returnMsg = "合同状态(1-审核通过)不能为空";
            return returnMsg;
        }
        //合同审核通过时间
        String contractAuditTime = sendContractDataToFy.getContract_audit_time();
        if (StringUtils.isEmpty(contractAuditTime)) {
            returnMsg = "合同审核通过时间不能为空";
            return returnMsg;
        }
        //公司编号
        String companyNo = sendContractDataToFy.getCompany_no();
        if (StringUtils.isEmpty(companyNo)) {
            returnMsg = "公司编号不能为空";
            return returnMsg;
        }
        //公司名称
        String companyName = sendContractDataToFy.getCompany_name();
        if (StringUtils.isEmpty(companyName)) {
            returnMsg = "公司名称不能为空";
            return returnMsg;
        }
        //费用
        BigDecimal amount = sendContractDataToFy.getAmount();
        if (amount == null || amount.compareTo(BigDecimal.ZERO) == -1) {
            returnMsg = "费用不能为空或小于0";
            return returnMsg;
        }
        return returnMsg;
    }

    public Report getById(int id) throws Exception {
        Report report = this.reportMapper.getRoughReportById(id);
        report.setPicList(reportDetailMapper.selectOperDetailPic(id));
        report.setFangyouFileList(fangyouReportFileMapper.selectFangyouPic(id));
        return report;
    }

    public int update(Report mo) {

        if (mo.getRoughAuditStatus() != null) {

            ResultData<String> resultData = sendRoughAudit(mo);

            if ("-1".equals(resultData.getReturnCode())) {
                return 0;
            }

            //添加日志信息
            AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
            achieveMentChangeLog.setRelateId(mo.getId());
            final String roughAuditStatus = mo.getRoughAuditStatus();
            String desc = "";
            if ("1".equals(roughAuditStatus)) {

                Report reportDb = reportMapper.getById(mo.getId());
                List<Estate> estateList = null;
                try {
                    estateList = estateMapper.selectEstateInfo(reportDb.getEstateId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (null != estateList && !estateList.isEmpty()) {
                    Estate estate = estateList.get(0);
                    mo.setPartnerAbbreviationNm(estate.getPartnerAbbreviationNm());
                    mo.setPartnerNm(estate.getPartnerNm());
                    mo.setDeveloperName(estate.getDeveloperName());
                    mo.setDevCompany(estate.getDevCompany());
                }


                desc = "案件记录大定审核通过";
                //更新收入类型
                ReportDetail reportDetail = new ReportDetail();
                reportDetail.setReportId(mo.getId());
                reportDetail.setInComeStatus(mo.getInComeStatus());
                reportDetailMapper.updateForInCome(reportDetail);
            } else {
                desc = "案件记录大定审核驳回,原因:" + mo.getRoughAuditDesc();
            }
            User user = userMapper.getUserByUserId(mo.getRoughAuditId().intValue());
            achieveMentChangeLog.setChangeContent(desc);
            achieveMentChangeLog.setCreateUserCode(user.getUserCode());
            achieveMentChangeLog.setCreateUserName(user.getUserName());
            achieveMentChangeLog.setCreateDate(new Date());
            achieveMentChangeLog.setDelFlag(false);
            achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);
        }


        return this.reportMapper.update(mo);
    }


    /**
     * 大定审核
     */
    public ResultData<String> sendRoughAudit(Report mo) {
    	/*
    	ResultData<String> resultData = new ResultData<String>();
    	ApproDecideAuditDto approDecideAuditDto = new ApproDecideAuditDto();
    	String opResult = null;
    	
    	FangyouReportLog fangyouReportLog = new FangyouReportLog();
      	fangyouReportLog.setTypeId("8");//大定审核
    
		Report reportDb = reportMapper.getById(mo.getId());
		String url = "";
    	try {
    		if(reportDb!=null 
    				&& reportDb.getCustomerFrom()!=null
            		&& "17403".equals(reportDb.getCustomerFrom().toString())) {
        		url = SystemParam.getWebConfigValue("fangyouReportUrl") + "/merchant/site/register/site/approve/decide/"+reportDb.getFyReportId();
        		
        		approDecideAuditDto.setApproveInfo(mo.getRoughAuditDesc());
        		approDecideAuditDto.setApproveState(Integer.parseInt(mo.getRoughAuditStatus()));
        		approDecideAuditDto.setCrmUserId(mo.getRoughAuditId().intValue());
        		
        		String jsonData = JsonUtil.parseToJson(approDecideAuditDto);	  
	            opResult = HttpClientUtil.jsonPostFangyou(url,jsonData);
	            
	        	fangyouReportLog.setUserIdCreate(0);
	         	fangyouReportLog.setReqParamJson("url="+url+"##jsonData"+jsonData);
	         	fangyouReportLog.setRspParamJson(opResult);
                fangyouReportLogService.addLog(fangyouReportLog);

                Map<String,Object> resultMap = JsonUtil.parseToObject(opResult,Map.class);
                resultData.setReturnCode(resultMap.get("returnCode").toString());
                resultData.setReturnMsg(resultMap.get("returnMsg").toString());
    		}
		} catch (Exception e) {
			resultData.setFail("操作异常");
			fangyouReportLog.setUserIdCreate(0);
         	fangyouReportLog.setReqParamJson("url="+url+"##jsonData"+JsonUtil.parseToJson(approDecideAuditDto));
         	fangyouReportLog.setRspParamJson(JsonUtil.parseToJson(resultData));
            fangyouReportLogService.addLog(fangyouReportLog);
			e.printStackTrace();
			logger.error("reportService", "ReportService", "sendRoughAudit", "", null, "", "大定审核通知房友失败", e);
	        return resultData;
		}
        return resultData;
        */

        ResultData<String> resultData = new ResultData<String>();
        Report reportDb = reportMapper.getById(mo.getId());
        try {
            if (reportDb != null
                    && reportDb.getCustomerFrom() != null
                    && "17405".equals(reportDb.getCustomerFrom().toString())) {

                YFStatusSync yFStatusSync = new YFStatusSync();
                yFStatusSync.setCrm_report_id(reportDb.getReportId());
                if ("1".equals(mo.getRoughAuditStatus())) {
                    yFStatusSync.setReport_status("大定审核已通过");
                } else {
                    yFStatusSync.setReport_status("大定审核未通过");
                }
                yFStatusSync.setUnpass_suggestion(mo.getRoughAuditDesc());
                //新增字段 yf_report_id 2019/01/09
                yFStatusSync.setYf_report_id(reportDb.getFyReportId());
                resultData = yFInterfaceService.getyFInterfaceInfo("/CRMReportStatusSync", JSON.toJSONString(yFStatusSync), "8", 0, reportDb.getReportId());
            }
        } catch (Exception e) {
            resultData.setFail("操作异常");
            e.printStackTrace();
            logger.error("reportService", "ReportService", "sendRoughAudit", "", null, "", "大定审核通知有房失败", e);
            return resultData;
        }
        return resultData;
    }

    private ReportSearchResultDto getBrokerageInfo(ReportSearchResultDto dto) {
        //begin pmls-成销与结佣详情相关数据
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportId", dto.getReportId());
        BrokerageDto brokerCx = reportMapper.getBrokerageCxData(reqMap);//成销
        BrokerageDto brokerJy = reportMapper.getBrokerageJyData(reqMap);//结佣

        BigDecimal yjsrBeforeAmount = new BigDecimal(0.00);//应计收入税前金额
        BigDecimal yjsrAfterAmount = new BigDecimal(0.00);//应计税后金额
        if (brokerCx != null) {
            if (brokerCx.getYjsrBeforeAmount() != null) {
                yjsrBeforeAmount = yjsrBeforeAmount.add(brokerCx.getYjsrBeforeAmount());
            }
            if (brokerCx.getYjsrBeforeAmount() != null) {
                yjsrAfterAmount = yjsrAfterAmount.add(brokerCx.getYjsrAfterAmount());
            }

            dto.setYjsrBeforeAmount(yjsrBeforeAmount);
            dto.setYjsrAfterAmount(yjsrAfterAmount);
        }
        //结佣详情
        BigDecimal yjfyBeforeAmount = new BigDecimal(0.00);//应计返佣税前金额
        BigDecimal yjfyAfterAmount = new BigDecimal(0.00);//应计返佣税后金额
        if (brokerJy != null) {
            if (brokerJy.getYjfyBeforeAmount() != null) {
                yjfyBeforeAmount = yjfyBeforeAmount.add(brokerJy.getYjfyBeforeAmount());
            }
            if (brokerJy.getYjfyAfterAmount() != null) {
                yjfyAfterAmount = yjfyAfterAmount.add(brokerJy.getYjfyAfterAmount());
            }
            dto.setYjfyBeforeAmount(yjfyBeforeAmount);
            dto.setYjfyAfterAmount(yjfyAfterAmount);
        }
        //end
        if ("22002".equals(dto.getBrokerageStatus()) || "22003".equals(dto.getBrokerageStatus())) {
            List<BrokerageDto> brokerageDetailList = reportMapper.getBrokerageInfo(reqMap);
            //获取应计

            BigDecimal yjPreamountSum = new BigDecimal(0.00);
            BigDecimal yjAfteramountSum = new BigDecimal(0.00);
            BigDecimal sjPreamountSum = new BigDecimal(0.00);
            BigDecimal sjAfteramountSum = new BigDecimal(0.00);

            for (BrokerageDto brokerageDto : brokerageDetailList) {
                if ("yj".equals(brokerageDto.getAmountType())) {
                    if (brokerageDto.getPreamount() != null) {
                        yjPreamountSum = yjPreamountSum.add(brokerageDto.getPreamount());
                    }
                    if (brokerageDto.getAfteramount() != null) {
                        yjAfteramountSum = yjAfteramountSum.add(brokerageDto.getAfteramount());
                    }
                }

                if ("sj".equals(brokerageDto.getAmountType())) {
                    if (brokerageDto.getPreamount() != null) {
                        sjPreamountSum = sjPreamountSum.add(brokerageDto.getPreamount());
                    }
                    if (brokerageDto.getAfteramount() != null) {
                        sjAfteramountSum = sjAfteramountSum.add(brokerageDto.getAfteramount());
                    }
                }

                if (StringUtil.isNotEmpty(brokerageDto.getYm())) {
                    Date BrokerageYmDate = cn.com.eju.deal.reportbase.util.DateUtil.parseDateFormat(brokerageDto.getYm(), "yyyyMM");
                    brokerageDto.setYm(DateUtil.fmtDate(BrokerageYmDate, "yyyy-MM"));
                }
            }
            dto.setYjPreamountSum(yjPreamountSum);
            dto.setYjAfteramountSum(yjAfteramountSum);
            dto.setSjPreamountSum(sjPreamountSum);
            dto.setSjAfteramountSum(sjAfteramountSum);
            dto.setBrokerageDetailList(brokerageDetailList);
        }
        return dto;
    }

    public ResultData<?> updateDetailRoughDate(ReportDto dto) throws Exception {
        ResultData<?> result = new ResultData<>();

        reportDetailMapper.updateDetailRoughDate(dto);

        return result;
    }


    public boolean getYHApproveCheck(int id) throws Exception {
        boolean flag = false;//不能通过
        Report reportDb = reportMapper.getById(id);
        //渠道的情况，肯定有公司
        if (reportDb.getBranchId() != null) {
            flag = true;
            return flag;
        } else {
            //房友公司 同以前逻辑
            if (StringUtil.isNotEmpty(reportDb.getCompanyNm())) {
                flag = true;
                return flag;
            }

            Map<String, Object> companyInfoMap = reportMapper.getYHCompanyInfo(reportDb.getStoreId());
            if (companyInfoMap.containsKey("companyName") && StringUtil.isNotEmpty((String) companyInfoMap.get("companyName"))) {
                companyInfoMap.put("reportId", reportDb.getReportId());
                reportMapper.updateYHCompanyInfo(companyInfoMap);
                flag = true;
                return flag;
            }
        }
        return flag;
    }

    public ResultData<Integer> getYHApproveCheckArteryType(int id) throws Exception {
        ResultData<Integer> resultData = new ResultData<>();
        Report reportDb = reportMapper.getById(id);

        //房友公司 同以前逻辑
        if (StringUtil.isNotEmpty(reportDb.getCompanyNm())) {
            resultData.setReturnData(1);
            return resultData;
        }
        // 通过订单上的门店ID 取得门店关联的门店信息
        Map<String, Object> companyInfoMap = reportMapper.getYHCompanyInfo(reportDb.getStoreId());
        // 门店未联到公司数据
        if (companyInfoMap == null) {
            resultData.setReturnData(0);
            resultData.setReturnMsg("门店未绑定经纪公司，请驳回通知业务人员绑定!");
            return resultData;
        } else {
            // 门店联到公司数据
            Integer brandType = 29402;  // 29402: 其他， 29401：分销渠道
            if (companyInfoMap.containsKey("brandType") && companyInfoMap.get("brandType") != null) {
                brandType = Integer.parseInt(companyInfoMap.get("brandType").toString());
            }

            // 判断是否有公司
            if (companyInfoMap.containsKey("companyName") && companyInfoMap.get("companyName") != null
                    && StringUtil.isNotEmpty((String) companyInfoMap.get("companyName"))) {

                // 判断这个公司是否是分销渠道
                if (brandType != 29401) {
                    resultData.setReturnData(0);
                    resultData.setReturnMsg("绑定的经纪公司不是分销渠道，请驳回通知业务人员!");
                    return resultData;
                }

                companyInfoMap.put("reportId", reportDb.getReportId());
                reportMapper.updateYHCompanyInfo(companyInfoMap);
                resultData.setReturnData(1);
                return resultData;
            }
        }
        resultData.setReturnData(0);
        resultData.setReturnMsg("门店未绑定经纪公司，请驳回通知业务人员绑定!");
        return resultData;
    }


    public String selectBuildingNoRepeatCount(String buildingNo, String reportId) throws Exception {
        Map<String, Object> queryParam = new HashMap<>();

        queryParam.put("reportId", reportId);
        Report reportDb = reportMapper.getReport(queryParam).get(0);

        queryParam.put("buildingNo", buildingNo);
        queryParam.put("estateId", reportDb.getEstateId());
        String reportIdStr = reportDetailMapper.selectBuildingNoRepeatCount(queryParam);
        return reportIdStr;
    }

    public List<ExchangeCenter> getLinkUserCenter(String userCode) throws Exception {
        User user = userMapper.getUserByUserCode(userCode);
        Integer userId = user.getUserId();
        List<ExchangeCenter> centerList = userMapper.getCenterListByUserId(userId);
        return centerList;
    }

    /**
     * 保存业绩
     *
     * @param param
     * @return
     */
    public ResultData saveAchievementAdjut(Map<String, Object> reqMap) throws Exception {
        ResultData resultData = new ResultData();
        resultData.setFail();
        String maintainerCode = (String) reqMap.get("linkUserCode");
        //变更后的人
        String maintainerNm = (String) reqMap.get("linkUserName");
        if (StringUtil.isEmpty(maintainerCode)) {
            resultData.setFail("请选择一个业绩归属人!");
            return resultData;
        }
        //当前中心
        String newCenterCode = (String) reqMap.get("newCenterCode");
        String newCenterName = (String) reqMap.get("newCenterName");
        if (StringUtil.isEmpty(newCenterCode)) {
            resultData.setFail("请选择一个业绩归属中心！");
            return resultData;
        }
        //操作人
        String userCode = (String) reqMap.get("userCode");
        String userName = (String) reqMap.get("userName");
        Integer userIdCreate = Integer.valueOf((String) reqMap.get("userIdCreate"));
        //当前
        //String linkUserName = (String)reqMap.get("linkUserName");
        //原中心名称
        String oldCenterGroupName = (String) reqMap.get("oldCenterGroupName");
        String oldCenterGroupId = (String) reqMap.get("oldCenterGroupId");
        String reportId = (String) reqMap.get("reportId");
        String changeReason = (String) reqMap.get("changeReason");
        String oldMaintainerCode = (String) reqMap.get("oldContactId");
        String oldMaintainerNm = (String) reqMap.get("oldContactNm");
        Integer id = Integer.valueOf((String) reqMap.get("relateId"));

        String newFyCenterId = (String) reqMap.get("newFyCenterId");
        String newFyCenterName = (String) reqMap.get("newFyCenterName");
        String htedition = (String) reqMap.get("htedition");
        String contractNo = (String) reqMap.get("contractNo");//合作协议编号
        String oldFyCenterName = (String) reqMap.get("oldFyCenterName");//合作协议编号

        //获取CRM关账日期
        Map<String, Object> switchQueryMap = new HashMap<>();
        switchQueryMap.put("relateSystem", "17402");
        Map<String, Object> switchMap = commonService.getSwitchLnk(switchQueryMap);
        String recordReportNo = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Report oldReport = reportMapper.getById(id);
        if (oldReport != null) {
            //处理一下未认定的值
            if (oldReport.getConfirmStatus().intValue() == SceneEstateConstant.CONFIRMSTATUS_NOTSURE) {
                oldReport.setConfirmStatus(SceneEstateConstant.CONFIRMSTATUS_USE);
                oldReport.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(oldReport.getConfirmStatus())));
                oldReport.setLatestProgress(oldReport.getLatestProgress() - 1);
                oldReport.setLatestProgressNm(SystemParam.getDicValueByDicCode(String.valueOf(oldReport.getLatestProgress())));
            }
            //大定和成销,并且有效的时候，需要判断是否关账
            String switchDateStr = (String) switchMap.get(oldReport.getCityNo());
            Date switchDate = sdf.parse(switchDateStr);
            Date reportDate = null;
            if (SceneEstateConstant.PROGRESS_SUBSCRIBED == oldReport.getLatestProgress().intValue()) {
                if (SceneEstateConstant.CONFIRMSTATUS_USE == oldReport.getConfirmStatus().intValue()) {
                    reportDate = oldReport.getRoughDate();
                }
            }
            if (SceneEstateConstant.PROGRESS_BARGAIN == oldReport.getLatestProgress().intValue()) {
                if (SceneEstateConstant.CONFIRMSTATUS_USE == oldReport.getConfirmStatus().intValue()) {
                    reportDate = oldReport.getDealDate();
                }
            }
            if (reportDate != null) {
                //关账
                if (reportDate.getTime() < switchDate.getTime()) {
                    recordReportNo += oldReport.getReportId() + ",";
                }
            }
            if (StringUtil.isNotEmpty(recordReportNo)) {
                recordReportNo = recordReportNo.substring(0, recordReportNo.length() - 1);
                resultData.setFail("报备" + recordReportNo + "已成销或已大定，且已关账，无法调整业绩!");
                return resultData;
            }
            //根据选择的人的所属中心查询出此人的业绩归属信息
            Map<String, Object> achieveMap = new HashMap<>();
            achieveMap.put("centerGroupId", newCenterCode);
            achieveMap.put("achieveType", DictionaryConstants.ACHIEVETYPE_LINK);
            Achievement achievement = achievementMapper.getAchievementInfoContract(achieveMap);
            if (achievement != null) {
                achievement.setExpenderCode(maintainerCode);
                achievement.setExpenderName(maintainerNm);
                achievement.setCenterGroupId(Integer.valueOf(newCenterCode));
                achievement.setCenterGroupName(newCenterName);
                String groupUserFlag = achievement.getGroupUserFlag();
                if ("1".equals(groupUserFlag)) {
                    //为1的时候，需要查找上级归属信息
                    Map<String, String> groupMap = new HashMap<>();
                    //groupMap.put("userCode",userCode);
                    groupMap.put("userCode", maintainerCode);
                    // SJBM
                    groupMap.put("typeCode", SystemParam.getWebConfigValue("lnkGroupConfig"));
                    Map<String, ?> groupInfoMap = groupMapper.getAchieveLinkGroupInfo(groupMap);
                    if (groupInfoMap != null) {
//                        String groupId = groupInfoMap.get("groupId");
//                        if(StringUtil.isNotEmpty(groupId)){
//                            achievement.setGroupId(Integer.parseInt(groupId));
//                        }else{
//                            achievement.setGroupId(null);
//                        }
//                        String groupName = groupInfoMap.get("groupName");
//                        if(StringUtil.isNotEmpty(groupName)){
//                            achievement.setGroupName(groupName);
//                        }else{
//                            achievement.setGroupName(null);
//                        }
//                        String groupLeaderCode = groupInfoMap.get("groupLeaderCode");
//                        if(StringUtil.isNotEmpty(groupLeaderCode)){
//                            achievement.setGroupLeaderCode(groupLeaderCode);
//                        }else{
//                            achievement.setGroupLeaderCode(null);
//                        }
//                        String groupLeaderName = groupInfoMap.get("groupLeaderName");
//                        if(StringUtil.isNotEmpty(groupLeaderName)){
//                            achievement.setGroupLeaderName(groupLeaderName);
//                        }else{
//                            achievement.setGroupLeaderName(null);
//                        }
                        Integer groupId = null;
                        if (null != groupInfoMap.get("groupId") && groupInfoMap.containsKey("groupId")) {
                            groupId = Integer.parseInt(groupInfoMap.get("groupId").toString());
                            achievement.setGroupId(groupId);
                        }
                        if (null != groupInfoMap.get("groupName") && groupInfoMap.containsKey("groupName")) {
                            achievement.setGroupName(groupInfoMap.get("groupName").toString());
                        }
                        if (null != groupInfoMap.get("groupLeaderCode") && groupInfoMap.containsKey("groupLeaderCode")) {
                            achievement.setGroupLeaderCode(groupInfoMap.get("groupLeaderCode").toString());
                        }
                        if (null != groupInfoMap.get("groupLeaderName") && groupInfoMap.containsKey("groupLeaderName")) {
                            achievement.setGroupLeaderName(groupInfoMap.get("groupLeaderName").toString());
                        }
                    } else {
//                        resultData.setFail("选择的人员没有上级业绩归属信息，请确认！");
//                        return resultData;
                        achievement.setGroupId(null);
                        achievement.setGroupName(null);
                        achievement.setGroupLeaderCode(null);
                        achievement.setGroupLeaderName(null);

                    }
                }
                achievement.setRelateId(id);
                int count = achievementMapper.updateAchieveInfo(achievement);
                if (count < 1) {
                    //小于1说明当前选中的报备单还没有业绩信息，此时新增业绩信息
                    achievementMapper.create(achievement);
                }
                //更新联动报备表中的业绩人
                Report report = new Report();
                report.setId(id);
                report.setContactId(maintainerCode);
                report.setContactNm(maintainerNm);
                report.setUptEmpId(userIdCreate);
                report.setUptDt(new Date());
                Integer centerId = Integer.valueOf(newCenterCode);
                report.setCenterId(centerId);
                if ("28301".equals(htedition)) {//pmls
                    report.setFyCenterId(null);
                    report.setFyCenterName(null);
                    reportMapper.updateFyCenter(report);
                } else if ("28302".equals(htedition)) {//房友
//                	report.setFyCenterId(Integer.parseInt(newFyCenterId));
//                	report.setFyCenterName(newFyCenterName);
                    report.setFyCenterId(null);
                    report.setFyCenterName(null);
                }
                reportMapper.update(report);
                report.setContractNo(contractNo);
                report.setHtedition(htedition);
                reportMapper.updateContractNo(report);

                //添加日志
                String changeContent = "";
                String oldName = oldMaintainerNm == null ? "" : oldMaintainerNm;
                String newName = maintainerNm == null ? "" : maintainerNm;
                if (!oldName.equals(newName)) {
                    changeContent += "业绩归属人：" + oldName + "——>" + newName + "</br>";
                }
                String oldAchCenterName = oldCenterGroupName == null ? "" : oldCenterGroupName;
                String newAchCenterName = newCenterName == null ? "" : newCenterName;
                if (!oldAchCenterName.equals(newAchCenterName)) {
                    changeContent += "业绩归属中心：" + oldAchCenterName + "——>" + newAchCenterName + "</br>";
                }
                String oldFangyouCenterName = oldFyCenterName == null ? "" : oldFyCenterName;
                String newFangyouCenterName = newFyCenterName == null ? "" : newFyCenterName;
                if (!oldFangyouCenterName.equals(newFangyouCenterName)) {
                    if (oldFangyouCenterName.equals("")) {
                        changeContent += "房友中心增加" + newFangyouCenterName + "</br>";
                    } else if (newFangyouCenterName.equals("")) {
                        changeContent += "房友中心删除" + oldFangyouCenterName + "</br>";
                    } else {
                        changeContent += "房友中心：" + oldFangyouCenterName + "——>" + newFangyouCenterName + "</br>";
                    }
                }

                AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
                achieveMentChangeLog.setRelateId(id);
                //achieveMentChangeLog.setChangeContent("报备" + oldReport.getReportId() + " 业绩归属人和业绩归属中心、房友中心由" + oldReport.getContactNm() + "(" + oldCenterGroupName +"、"+ oldFyCenterName+")" + "调整为" + maintainerNm + "(" + newCenterName +"、"+newFyCenterName+ ")，变更原因：" + changeReason);
                achieveMentChangeLog.setChangeContent(changeContent + " 变更原因：" + changeReason);
                achieveMentChangeLog.setCreateUserCode(userCode);
                achieveMentChangeLog.setCreateUserName(userName);
                achieveMentChangeLog.setCreateDate(new Date());
                achieveMentChangeLog.setDelFlag(false);
                achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);
                //原日志记录内容相对较多，对业绩变更记录相对不详细，对业绩变更后特加此日志
                ReportAchieveMentAdjustLog adjustLog = new ReportAchieveMentAdjustLog();
                adjustLog.setRelateId(id);
                adjustLog.setReportId(reportId);
                adjustLog.setChangeReason(changeReason);
                //adjustLog.setChangeContent("报备" + reportId + " 业绩归属人和业绩归属中心、房友中心由" + oldMaintainerNm + "（" + oldCenterGroupName +"、"+ oldFyCenterName+ "）调整为" + maintainerNm + "（" + newCenterName +"、"+newFyCenterName+ ")");
                achieveMentChangeLog.setChangeContent("报备" + oldReport.getReportId() + changeContent);
                adjustLog.setOldMaintainerCode(oldMaintainerCode);
                adjustLog.setOldMaintainerNm(oldMaintainerNm);
                adjustLog.setMaintainerCode(maintainerCode);
                adjustLog.setMaintainerNm(maintainerNm);
                adjustLog.setOldCenterGroupId(oldCenterGroupId);
                adjustLog.setOldCenterGroupName(oldCenterGroupName);
                adjustLog.setCenterGroupId(newCenterCode);
                adjustLog.setCenterGroupName(newCenterName);
                adjustLog.setDateCreate(new Date());
                adjustLog.setUserIdCreate(userIdCreate);
                adjustLog.setUserCode(userCode);
                adjustLog.setUserName(userName);
                adjustLog.setDelFlag("0");
                achieveMentChangeLogMapper.insertAchieveMentAdjustDetailLog(adjustLog);
                resultData.setSuccess("修改成功！");
            } else {
                resultData.setFail("选择的人员没有业绩归属信息，请确认！");
            }

        } else {
            resultData.setFail("报备数据异常！");
            return resultData;
        }
        return resultData;
    }

    /**
     * 保存客户信息
     *
     * @param param
     * @return
     */
    public ResultData saveCustomerInfoAdjut(Map<String, Object> reqMap) throws Exception {
        ResultData resultData = new ResultData();
        resultData.setFail();
        String message = "客户信息修改,原客户姓名:";
        //获取参数
        Integer id = Integer.valueOf((String) reqMap.get("relateId"));
        //操作人
        String userCode = (String) reqMap.get("userCode");
        String userName = (String) reqMap.get("userName");
        Integer userIdCreate = Integer.valueOf((String) reqMap.get("userIdCreate"));
        //原来客户名称及手机号码
        String oldCustomerNm = (String) reqMap.get("oldCustomerNm");
        String oldCustomerTel = (String) reqMap.get("oldCustomerTel");
        String oldCustomerNmTwo = "";
        String oldCustomerTelTwo = "";
        if (reqMap.containsKey("oldCustomerNmTwo")) {
            oldCustomerNmTwo = (String) reqMap.get("oldCustomerNmTwo");
            oldCustomerTelTwo = (String) reqMap.get("oldCustomerTelTwo");
        }
        if (!"".equals(oldCustomerNmTwo)) {
            message += oldCustomerNm + "、" + oldCustomerNmTwo + " 客户手机号:";
        } else {
            message += oldCustomerNm + " 客户手机号:";
        }
        if (!"".equals(oldCustomerTelTwo)) {
            message += oldCustomerTel + "、" + oldCustomerTelTwo;
        } else {
            message += oldCustomerTel;
        }
        //现在的客户名称及其手机号码
        String customerNm = (String) reqMap.get("customerNm");
        String customerTel = (String) reqMap.get("customerTel");
        String customerNmTwo = "";
        String customerTelTwo = "";
        if (reqMap.containsKey("customerNmTwo")) {
            customerNmTwo = (String) reqMap.get("customerNmTwo");
            customerTelTwo = (String) reqMap.get("customerTelTwo");
        }

        Report report = new Report();
        //根据id查询详情表id
        List<Integer> detailIdList = reportMapper.getReportDetailIdList(id);
        //更新主表
        report.setId(id);
        report.setCustomerNm(customerNm);
        report.setCustomerTel(customerTel);
        if (!"".equals(customerNmTwo)) {
            report.setCustomerNmTwo(customerNmTwo);
            message += " 修改为: 客户姓名 " + customerNm + "、" + customerNmTwo;
        } else {
            report.setCustomerNmTwo(null);
            message += " 修改为: 客户姓名 " + customerNm;
            report.setCustomerTwoFlag("1");
        }
        if (!"".equals(customerTelTwo)) {
            report.setCustomerTelTwo(customerTelTwo);
            message += " 客户手机号" + customerTel + "、" + customerTelTwo;
        } else {
            report.setCustomerTelTwo(null);
            message += " 客户手机号" + customerTel;
        }
        report.setUptEmpId(userIdCreate);
        report.setUptDt(new Date());
        reportMapper.update(report);

        //更新从表
        if (null != detailIdList && detailIdList.size() > 0) {
            for (Integer integer : detailIdList) {
                ReportDetail reportDetail = new ReportDetail();
                reportDetail.setId(integer);
                reportDetail.setUptDt(new Date());
                reportDetail.setUptEmpId(userIdCreate);
                reportDetail.setCustomerNm(customerNm);
                reportDetail.setCustomerTel(customerTel);
                if (!"".equals(customerNmTwo)) {
                    reportDetail.setCustomerNmTwo(customerNmTwo);
                } else {
                    reportDetail.setCustomerNmTwo(null);
                }
                if (!"".equals(customerTelTwo)) {
                    reportDetail.setCustomerTelTwo(customerTelTwo);
                } else {
                    reportDetail.setCustomerTwoFlag("1");
                    reportDetail.setCustomerTelTwo(null);
                }
                reportDetailMapper.update(reportDetail);
            }
        }
        //添加日志
        AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
        achieveMentChangeLog.setRelateId(id);
        achieveMentChangeLog.setChangeContent(message);
        achieveMentChangeLog.setCreateUserCode(userCode);
        achieveMentChangeLog.setCreateUserName(userName);
        achieveMentChangeLog.setCreateDate(new Date());
        achieveMentChangeLog.setDelFlag(false);
        achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);
        resultData.setSuccess("修改成功！");
        return resultData;
    }

    public ResultData<?> insertYjReport(ReportDto dto) {
        ResultData<?> resultData = new ResultData<>();

        // 公司信息重新获取
        if (dto.getCompanyId() == null || "0".equals(dto.getCompanyId())) {
            Report report = reportMapper.getByReportId(dto.getReportId());
            dto.setCompanyId(report.getCompanyId());
        }
        reportMapper.insertYjReport(dto);

        return resultData;
    }

    public List<Map<String, Object>> getAccountProject(String cityNo) {
        List<Map<String, Object>> list = reportMapper.getAccountProject(cityNo);
        return list;
    }

    /**
     * desc:业务节点-返佣对象(结佣)
     * 2019年10月17日
     *
     * @throws Exception
     */
    public ResultData getJyFyData(Map<String, Object> map) throws Exception {

        ResultData<List<ReportFYDto>> result = new ResultData<>();
        List<ReportFYDto> dto = reportMapper.getJyFyData(map);
        if (dto != null) {
            result.setReturnData(dto);
        } else {
            result.setFail();
        }

        return result;

    }

    /**
     * desc:业务节点-返佣对象(成销)
     * 2019年10月17日
     *
     * @throws Exception
     */
    public ResultData getCxFyData(Map<String, Object> map) throws Exception {

        ResultData<List<ReportFYDto>> result = new ResultData<>();
        List<ReportFYDto> dto = reportMapper.getCxFyData(map);
        if (dto != null) {
            result.setReturnData(dto);
        } else {
            result.setFail();
        }

        return result;

    }


    public ResultData unlockReback(Map<String, Object> reqMap) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        reqMap.put("rebackFlag", true);
        reportMapper.unlockReback(reqMap);
        reportMapper.createUnlockRebackLog(reqMap);
        //添加日志
        AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
        achieveMentChangeLog.setRelateId(Integer.parseInt(reqMap.get("relateId").toString()));
        achieveMentChangeLog.setChangeContent("解锁原因：" + reqMap.get("unlockReason").toString());
        achieveMentChangeLog.setCreateUserCode(reqMap.get("userCode").toString());
        achieveMentChangeLog.setCreateUserName(reqMap.get("userName").toString());
        achieveMentChangeLog.setCreateDate(new Date());
        achieveMentChangeLog.setDelFlag(false);
        achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);
        return resultData;
    }

    /**
     * desc:获取项目发布城市
     * 2019年12月18日
     */
    public ResultData getProjectCityNoList(Map<String, Object> reqMap) throws Exception {
        ResultData resultData = new ResultData<>();
        String projectNo = reqMap.get("projectNo").toString();
        Estate estate = estateMapper.getByProjectNo(projectNo);

        String acCityNo = estate.getCityNo();//项目归属城市
        String estateId = estate.getEstateId();

        String releaseCityStr = acCityNo + ",";
        List<EstateReleaseCityDto> releaseCity = estatetReleaseMapper.queryCityListByEstateId(estateId);
        // 非空判断
        String releaseCityflag = "0";
        EstateReleaseCityDto estateReleaseCity = new EstateReleaseCityDto();
        if (null != releaseCity && !releaseCity.isEmpty()) {
            releaseCityflag = "1";
            for (int i = 0; i < releaseCity.size(); i++) {
                estateReleaseCity = releaseCity.get(i);
                if (!acCityNo.equals(estateReleaseCity.getCityNo())) {
                    releaseCityStr += estateReleaseCity.getCityNo() + ",";
                }
            }
        }
        releaseCityStr = releaseCityStr.substring(0, releaseCityStr.length() - 1);
        Map<String, Object> resultMap = new HashMap<>();
//    	resultMap.put("releaseCityflag", releaseCityflag);
//    	resultMap.put("acCityNo", acCityNo);
        resultMap.put("projectCityNos", releaseCityStr);
        resultData.setReturnData(resultMap);
        return resultData;
    }

    public ResultData<List<LnkYjBrokerage>> getYJtableList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<LnkYjBrokerage>> resultData = new ResultData<List<LnkYjBrokerage>>();
        List<LnkYjBrokerage> list = new ArrayList<>();
        String item = reqMap.get("item").toString();
        switch (item) {
            case "YJSR":
                list = lnkYjYjsrMapper.getYJtableList(reqMap);
                break;
            case "YSSR":
                list = lnkYjYssrMapper.getYJtableList(reqMap);
                break;
            case "YJSS":
                list = lnkYjYjssMapper.getYJtableList(reqMap);
                break;
            case "YJFY":
                list = lnkYjYjfyMapper.getYJtableList(reqMap);
                break;
            case "SJFY":
                list = lnkYjSjfyMapper.getYJtableList(reqMap);
                break;
            case "YJDY":
                list = lnkYjYjdyMapper.getYJtableList(reqMap);
                break;
            case "SJDY":
                list = lnkYjSjdyMapper.getYJtableList(reqMap);
                break;
            default:
                list = new ArrayList<>();
                break;
        }
        resultData.setReturnData(list);
        return resultData;

    }

    public ResultData<List<LnkStatBrokerage>> getStatistcsBrokerage(Map<String, Object> reqMap) throws Exception {
        ResultData<List<LnkStatBrokerage>> resultData = new ResultData<List<LnkStatBrokerage>>();
        List<LnkStatBrokerage> list = new ArrayList<>();
        LnkStatBrokerage record = new LnkStatBrokerage();

        BigDecimal yssrAmount = new BigDecimal("0");
        BigDecimal yjssAmount = new BigDecimal("0");
        BigDecimal yswhAmount = new BigDecimal("0");
        BigDecimal yjfyAmount = new BigDecimal("0");
        BigDecimal sjfyAmount = new BigDecimal("0");
        BigDecimal yjwfAmount = new BigDecimal("0");

        // 应收收入
        LnkYjYssr lnkYjYssr = lnkYjYssrMapper.getStatistcsBrokerage(reqMap);
        if (lnkYjYssr != null) {
            yssrAmount = lnkYjYssr.getBefTaxAmount();
        }
        // 应计实收
        LnkYjYjss lnkYjYjss = lnkYjYjssMapper.getStatistcsBrokerage(reqMap);
        if (lnkYjYjss != null) {
            yjssAmount = lnkYjYjss.getBefTaxAmount();
        }
        // 应收未回
        yswhAmount = yssrAmount.subtract(yjssAmount);

        // 应计返佣
        LnkYjYjfy lnkYjYjfy = lnkYjYjfyMapper.getStatistcsBrokerage(reqMap);
        if (lnkYjYjfy != null) {
            yjfyAmount = lnkYjYjfy.getBefTaxAmount();
        }

        // 实际返佣
        LnkYjSjfy lnkYjSjfy = lnkYjSjfyMapper.getStatistcsBrokerage(reqMap);
        if (lnkYjSjfy != null) {
            sjfyAmount = lnkYjSjfy.getBefTaxAmount();
        }

        //应计未返
        yjwfAmount = yjfyAmount.subtract(sjfyAmount);

        record.setYssrAmount(yssrAmount);
        record.setYjssAmount(yjssAmount);
        record.setYswhAmount(yswhAmount);
        record.setYjfyAmount(yjfyAmount);
        record.setSjfyAmount(sjfyAmount);
        record.setYjwfAmount(yjwfAmount);

        list.add(record);
        resultData.setReturnData(list);
        return resultData;
    }


    public ResultData uptPreBack(Map<String, Object> reqMap) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        Integer detailId = reportMapper.getTopReportDetail(reqMap);
        reqMap.put("detailId", detailId);
        reqMap.put("preBackDate", new Date());
        reportMapper.uptPreBack(reqMap);
        return resultData;
    }


    public ResultData<Report> getReport(Map<String, Object> reportMap) throws Exception {
        ResultData<Report> resultData = new ResultData<>();

        Report reportDb = reportMapper.getReport(reportMap).get(0);

        resultData.setReturnData(reportDb);
        resultData.setSuccess();
        return resultData;
    }
    
    /**
     * 
     * desc:大定审核-获取垫佣控制规则
     * 2020年5月20日
     */
    public ResultData getNewDyRatio(Map<String, Object> reqMap) throws Exception {
        ResultData resultData = new ResultData<>();

        //根据大定日期、projectNo到垫佣规则主表中获取比例(大定日期>=主表周期开始时间的最新一条)，没有比例0
        Map<String, Object> dyRatioMap = reportMapper.getNewDyRatioToRule(reqMap);
        //根据大定日期、projectNo到垫佣规则明细表中获取是否总控(大定日期>=明细表周期开始时间的最新一条),没有房控
        Map<String, Object> controlMap = reportMapper.getNewDyRatioToRuleDetail(reqMap);
        Map<String, Object> updateMap = new HashMap<>();
        String isGlobalControl="0";//默认房控
        BigDecimal dyRatio =BigDecimal.ZERO;//默认比例0
        if(dyRatioMap !=null && !dyRatioMap.isEmpty()) {
        	dyRatio = (BigDecimal) dyRatioMap.get("dyRatio");
        }
        if(controlMap !=null && !controlMap.isEmpty()) {
        	isGlobalControl = (String) controlMap.get("isGlobalControl");
        }
        updateMap.put("reportId", reqMap.get("reportId"));
        updateMap.put("dyRatio", dyRatio);
        updateMap.put("isGlobalControl", isGlobalControl);
        int count = reportMapper.updateByReportId(updateMap);
        resultData.setReturnData(count);
        resultData.setSuccess();
        return resultData;
    }
    
    /**
     * desc:获取项目对应收入类合同得核算主体
     * 2020年7月1日
     */
    public List<Map<String, Object>> getAccountProjectByProjectNo(String projectNo) {
        return reportMapper.getAccountProjectByProjectNo(projectNo);
    }
}