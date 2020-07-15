package cn.com.eju.deal.api.houseLinkage.service;

import cn.com.eju.deal.achievement.dao.AchievementSettingMapper;
import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.CityCascadeMapper;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractProductMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.dao.ProductMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.api.houseLinkage.*;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDetailDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.fangyou.service.FangyouService;
import cn.com.eju.deal.houseLinkage.estate.constant.EstateConstant;
import cn.com.eju.deal.houseLinkage.estate.dao.*;
import cn.com.eju.deal.houseLinkage.estate.model.*;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportFileMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportAchievementMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.*;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.store.dao.StoreMaintainerMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.StoreMaintainer;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.PostMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.Post;
import cn.com.eju.deal.user.model.User;
import cn.com.eju.pmls.cooperation.service.CooperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
@Service("apiHouseLinkageService")
public class APIHouseLinkageService extends BaseService<Object> {

    @Resource
    private EstateMapper estateMapper;

    @Resource
    private EstateRuleMapper estateRuleMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private ReportDetailMapper reportDetailMapper;

    @Resource
    private EstateSupportMapper estateSupportMapper;

    @Resource
    private EstateTypeMapper estateTypeMapper;

    @Resource
    private PhotoMapper photoMapper;

    @Resource
    private UserAPIImpl userAPIImpl;

    @Resource
    private GroupMapper groupMapper;

    @Resource
    private FangyouService fangyouService;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private ProductMapper productMapper;

    @Resource
    private ContractProductMapper contractProductMapper;

    @Resource
    private ContractStoreMapper contractStoreMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private CityCascadeMapper cityCascadeMapper;

    @Resource
    private ReportService reportService;

    @Resource(name = "filesService")
    private FilesService filesService;

    @Resource
    private StoreMaintainerMapper storeMaintainerMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PostMapper postMapper;

    @Resource
    private ReportAchievementMapper reportAchieveMapper;

    @Resource(name = "achievementService")
    private AchievementService achievementService;

    @Resource
    private AchievementSettingMapper achievementSettingMapper;

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private FileRecordMainService fileRecordMainService;

    @Resource
    private FangyouReportFileMapper fangyouReportFileMapper;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Resource
    private CooperationService cooperationService;

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ReportInfoDto getReport(String estateId, String companyId, String customerId)
            throws Exception {
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
        }
        List<ReportDetail> detail = reportDetailMapper.getReportDetail(param);
        //转换
        List<ReportDetailDto> reportDtoList = convertReportDetailData(detail);
        // 设置各环节最终日期
        for (int i = 0; i < reportDtoList.size(); i++) {
            if (EstateConstant.PROGRESS_REPORT == reportDtoList.get(i).getProgress()
                    && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-报备、确认状态-有效
                ctaDto.setLatestReportFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(),
                        "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_RELATION == reportDtoList.get(i).getProgress()
                    && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-带看、确认状态-有效
                ctaDto.setLatestRelationFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(),
                        "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_PLEDGED == reportDtoList.get(i).getProgress()
                    && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-认筹、确认状态-有效
                ctaDto.setLatestPledgedFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(),
                        "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_SUBSCRIBED == reportDtoList.get(i).getProgress()
                    && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-认购、确认状态-有效
                ctaDto.setLatestSubscribedFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(),
                        "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_BARGAIN == reportDtoList.get(i).getProgress()
                    && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-成交、确认状态-有效
                ctaDto.setLatestBargainFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(),
                        "yyyy年MM月dd日"));
            } else if (EstateConstant.PROGRESS_BACK == reportDtoList.get(i).getProgress()
                    && EstateConstant.CONFIRM_STATUS_Y == reportDtoList.get(i).getConfirmStatus()) {// 报备进度-退筹、确认状态-有效
                ctaDto.setLatestBackFollowDate(getFormatDateString(reportDtoList.get(i).getRecognitionDay(),
                        "yyyy年MM月dd日"));
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
            resultData.setConfirmStatusNm(SystemParam.getDicValueByDicCode(String.valueOf(13601)));//确认状态有效
            resultData.setReportMemo("");
            resultData.setFollowDateDisPlay(lastAccountDate.split(",")[0]);
            reportDtoList.add(resultData);
        }
        reportInfoDto.setReport(ctaDto);
        reportInfoDto.setReportDetails(reportDtoList);
        return reportInfoDto;
    }

    /**
     * 验证是否结佣
     */
    private String checkAccountOk(List<ReportDetail> list)
            throws Exception {
        int accountOk = 0;
        String lastAccountDate = "";
        String returnlastAccountDate = "";
        if (list != null && list.size() == 4) {
            for (int i = 0; i < list.size(); i++) {
                // 结算状态=已结算
                if (14201 == list.get(i).getAccountStatus()) {
                    accountOk = accountOk + 1;
                    if (lastAccountDate.compareTo(getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm")) < 0) {
                        lastAccountDate = getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm");
                        returnlastAccountDate =
                                getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm") + ","
                                        + getFormatDateString(list.get(i).getUptDt(), "yyyy年MM月dd日");
                    }
                } else {
                    break;
                }
                // 成交 结算状态=已结算  佣金
                if (13505 == list.get(i).getProgress() && 14201 == list.get(i).getCommissionAccountStatus()) {
                    accountOk = accountOk + 1;
                    if (lastAccountDate.compareTo(getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm")) < 0) {
                        lastAccountDate = getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm");
                        returnlastAccountDate =
                                getFormatDateString(list.get(i).getUptDt(), "yyyy-MM-dd HH:mm") + ","
                                        + getFormatDateString(list.get(i).getUptDt(), "yyyy年MM月dd日");
                    }
                }
            }
        }
        if (accountOk == 5) {
            return returnlastAccountDate;
        }
        return "";
    }

    /**
     * 查询-报备列表
     *
     * @param param
     * @return
     */
    //    @Cacheable(cacheName = "testCache")
    public List<APIReportListResultDto> reportList(Map<?, ?> param)
            throws Exception {
        //构建返回
        List<APIReportListResultDto> reportDtoList = new ArrayList<APIReportListResultDto>();
        //查询
        final List<ReportSearchResult> craList = reportMapper.selectReportList(param);

        if (null != craList && !craList.isEmpty()) {
            APIReportListResultDto craDto = null;
            for (ReportSearchResult cra : craList) {
                craDto = new APIReportListResultDto();
                craDto.setId(String.valueOf(cra.getId()));
                craDto.setReportId(String.valueOf(cra.getReportId()));
                craDto.setEstateId(cra.getEstateId());
                craDto.setCityNo(cra.getCityNo());
                craDto.setCityNoDis(SystemParam.getCityNameByCityNo(cra.getCityNo()));
                craDto.setDistrictId(cra.getDistrictId());
                craDto.setDistrictIdDis(SystemParam.getDistrictNameByDistrictNo(cra.getDistrictId()));
                craDto.setEstateId(cra.getEstateId());
                craDto.setEstateNm(cra.getEstateNm());
                craDto.setEstateType(String.valueOf(cra.getEstateType()));
                craDto.setEstateTypeDis(SystemParam.getDicValueByDicCode(craDto.getEstateType()));
                craDto.setCompanyId(cra.getCompanyId());
                craDto.setCompanyNm(cra.getCompanyNm());
                craDto.setEmpNm(cra.getEmpNm());
                craDto.setDeptNm(cra.getDeptNm());
                craDto.setCustomerId(cra.getCustomerId());
                craDto.setCustomerNm(cra.getCustomerNm());
                String tel = cra.getCustomerTel();
                if (StringUtil.isNotEmpty(tel) && tel.length() == 11) {
                    if ("1".equals(cra.getHideNumberFlg())) {
                        tel = tel.substring(0, 3) + "-****-" + tel.substring(7);
                    } else {
                        tel = tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7);
                    }
                }
                craDto.setCustomerTel(tel);

                if (cra.getConfirmStatus() == 13603) {
                    craDto.setConfirmStatus("13601");
                    craDto.setConfirmStatusDis(SystemParam.getDicValueByDicCode(String.valueOf(craDto.getConfirmStatus())));
                    craDto.setLatestProgress(String.valueOf(cra.getLatestProgress() - 1));
                    craDto.setLatestProgressDis(SystemParam.getDicValueByDicCode(String.valueOf(craDto.getLatestProgress())));
                } else {
                    craDto.setLatestProgress(String.valueOf(cra.getLatestProgress()));
                    craDto.setLatestProgressDis(SystemParam.getDicValueByDicCode(craDto.getLatestProgress()));
                    craDto.setConfirmStatus(String.valueOf(cra.getConfirmStatus()));
                    craDto.setConfirmStatusDis(SystemParam.getDicValueByDicCode(craDto.getConfirmStatus()));
                }
                craDto.setReportDate(getFormatDateString(cra.getReportDate(), "yyyy-MM-dd HH:mm:ss"));
                craDto.setFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy-MM-dd HH:mm"));
                craDto.setValidDate(getFormatDateString(cra.getValidDate(), "yyyy-MM-dd"));
                craDto.setValidRelationDate(getFormatDateString(cra.getValidRelationDate(), "yyyy-MM-dd HH:mm:ss"));
                reportDtoList.add(craDto);
            }
        }
        return reportDtoList;
    }

    /**
     * 查询-报备详细
     *
     * @param param
     * @return
     */
    //    @Cacheable(cacheName = "testCache")
    public APIReportDetailResultDto reportDetail(Map<?, ?> param)
            throws Exception {
        //构建返回
        APIReportDetailResultDto head = new APIReportDetailResultDto();
        //查询
        // 获取报备头部信息
        final List<Report> craList = reportMapper.getReport(param);
        if (craList != null && craList.size() > 0) {
            Report reportData = craList.get(0);

            head.setEstateId(reportData.getEstateId());
            head.setEstateNm(reportData.getEstateNm());
            head.setStoreNm(reportData.getStoreNm());
            head.setDeptNm(reportData.getDeptNm());
            head.setCompanyNm(reportData.getCompanyNm());
            head.setCustomerNm(reportData.getCustomerNm());
            head.setCustomerTel(reportData.getCustomerTel());
            head.setEmpNm(reportData.getEmpNm());
            if (reportData.getConfirmStatus() == 13603) {
                head.setLatestConfirmStatus("13601");
                head.setLatestConfirmStatusDis(SystemParam.getDicValueByDicCode(String.valueOf(head.getLatestConfirmStatus())));
                head.setLatestProgress(String.valueOf(reportData.getLatestProgress() - 1));
                head.setLatestProgressDis(SystemParam.getDicValueByDicCode(String.valueOf(head.getLatestProgress())));
            } else {
                head.setLatestProgress(String.valueOf(reportData.getLatestProgress()));
                head.setLatestProgressDis(SystemParam.getDicValueByDicCode(head.getLatestProgress()));
                head.setLatestConfirmStatus(String.valueOf(reportData.getConfirmStatus()));
                head.setLatestConfirmStatusDis(SystemParam.getDicValueByDicCode(head.getLatestConfirmStatus()));
            }
            head.setLatestFollowDate(getFormatDateString(reportData.getFollowDate(), "yyyy-MM-dd HH:mm:ss"));

            head.setCustomerNum(SystemParam.getDicValueByDicCode(reportData.getCustomerNum()));
            head.setMemo(reportData.getMemo());
            head.setCustomerRequire(reportData.getCustomerRequire());
            head.setValidRelationDate(getFormatDateString(reportData.getValidRelationDate(), "yyyy-MM-dd HH:mm:ss"));
        }
        List<APIReportDetailResultDto> bindingList = new ArrayList<APIReportDetailResultDto>();
        // 获取报备明细
        List<ReportDetail> list = reportDetailMapper.getReportDetail(param);
        if (null != list && !list.isEmpty()) {
            APIReportDetailResultDto craDto = null;
            for (ReportDetail cra : list) {
                if (cra.getConfirmStatus() == 13603) {
                    continue;
                }
                craDto = new APIReportDetailResultDto();
                craDto.setProgressStatus(SystemParam.getDicValueByDicCode(String.valueOf(cra.getProgress())));
                craDto.setConfirmStatus(SystemParam.getDicValueByDicCode(String.valueOf(cra.getConfirmStatus())));
                craDto.setReportMemo(cra.getReportMemo());
                craDto.setFollowDate(getFormatDateString(cra.getRecognitionDay(), "yyyy-MM-dd HH:mm"));
                if (cra.getProgress() == 13501 && cra.getConfirmStatus() == 13601) {// 报备
                    head.setLatestReportFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy年MM月dd日"));
                    head.setReportDate(getFormatDateString(cra.getFollowDate(), "yyyy-MM-dd HH:mm"));
                } else if (cra.getProgress() == 13502 && cra.getConfirmStatus() == 13601) {// 带看
                    head.setLatestRelationFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy年MM月dd日"));
                } else if (cra.getProgress() == 13503 && cra.getConfirmStatus() == 13601) {// 认筹
                    head.setLatestPledgedFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy年MM月dd日"));
                } else if (cra.getProgress() == 13504 && cra.getConfirmStatus() == 13601) {// 认购
                    head.setLatestSubscribedFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy年MM月dd日"));
                } else if (cra.getProgress() == 13505 && cra.getConfirmStatus() == 13601) {// 成交
                    head.setLatestBargainFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy年MM月dd日"));
                } else if (cra.getProgress() == 13506 && cra.getConfirmStatus() == 13601) {// 退筹
                    head.setLatestBackFollowDate(getFormatDateString(cra.getFollowDate(), "yyyy年MM月dd日"));
                }
                bindingList.add(craDto);
            }
            // 验证是否结算
            List<ReportDetail> detailList = reportDetailMapper.checkAccountOk(param);
            String lastAccountDate = reportService.checkAccountOk(detailList);
            if (StringUtil.isNotEmpty(lastAccountDate)) {
                head.setCommissionProgress("13507");// 结佣
                head.setLatestCommissionFollowDate(lastAccountDate.split(",")[0]);
                head.setLatestCommissionFollowDate2(lastAccountDate.split(",")[1]);
                APIReportDetailResultDto data = new APIReportDetailResultDto();
                data.setProgressStatus(SystemParam.getDicValueByDicCode(head.getCommissionProgress()));
                data.setConfirmStatus(SystemParam.getDicValueByDicCode("13601"));// 有效
                data.setReportMemo("");
                data.setFollowDate(lastAccountDate.split(",")[0]);
                bindingList.add(data);
            }
            head.setProgress(bindingList);
        }
        return head;
    }

    /**
     * 退筹
     *
     * @param param
     * @return
     */
    //    @TriggersRemove(cacheName = "testCache", removeAll = true)
    public int updateBackProgress(Map<?, ?> param)
            throws Exception {
        // 更新报备
        int count = reportMapper.updateBackProgress(param);
        // 更新报备详细
        count = reportDetailMapper.updateBackProgress(param);
        return count;
    }

    /**
     * 验证重复报备
     *
     * @param param
     * @return
     */
    //    @TriggersRemove(cacheName = "testCache", removeAll = true)
    public Boolean checkExistReport(Map<?, ?> param)
            throws Exception {
        List<Report> checkList = reportMapper.checkExistReport(param);
        if (checkList != null && checkList.size() > 0) {
            return true;
        }
        return false;
    }

    /**
     * 报备登记
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    public int saveReport(Map<?, ?> param)
            throws Exception {

        int cnt = 0;
        Map<String, Object> reMap = (Map<String, Object>) param;

        List<Estate> estateInfo = estateMapper.selectEstateInfo((String) reMap.get("estateId"));
        List<EstateRule> estateRuleInfo = estateRuleMapper.selectEstateRuleInfo((String) reMap.get("estateId"));
        reMap.put("estateType", estateInfo.get(0).getMgtKbn());
        reMap.put("latestProgress", 13502);
        reMap.put("confirmStatus", 13603);
        if (reMap.get("reportDate") != null && !"".equals(reMap.get("reportDate"))) {
            reMap.put("reportDate",
                    getFormatStringDate((String) reMap.get("reportDate"), "yyyy-MM-dd HH:mm:ss"));
        } else {
            reMap.put("reportDate", new Date());
        }
        reMap.put("followDate", new Date());

        Calendar addDate = Calendar.getInstance();
        addDate.setTime(new Date());
        if (estateRuleInfo != null && estateRuleInfo.size() > 0) {
            addDate.add(Calendar.DAY_OF_YEAR, estateRuleInfo.get(0).getRelationProtectPeriod().intValue());
        }
        Date validDate = addDate.getTime();
        reMap.put("validDate", validDate);

        reMap.put("crtDt", new Date());
        reMap.put("delFlg", false);

        //        Calendar relationDate = Calendar.getInstance();
        //        relationDate.setTime(new Date());
        //        if (StringUtil.isNotEmpty(estateRuleInfo.get(0).getAdvanceReportMM())) {
        //          relationDate.add(Calendar.MINUTE, Integer.valueOf(estateRuleInfo.get(0).getAdvanceReportMM()));
        //        }
        //        if (StringUtil.isNotEmpty(estateRuleInfo.get(0).getAdvanceReportHH())) {
        //          relationDate.add(Calendar.HOUR_OF_DAY, Integer.valueOf(estateRuleInfo.get(0).getAdvanceReportHH()));
        //        }
        //        Date validRelationDate = relationDate.getTime();
        if (reMap.get("validRelationDate") != null && !"".equals(reMap.get("validRelationDate"))) {
            reMap.put("validRelationDate",
                    getFormatStringDate((String) reMap.get("validRelationDate"), "yyyy-MM-dd HH:mm:ss"));
        }
        //reMap.put("crtEmpId", estateInfo.get(0).getSceneEmpId());
        reMap.put("crtEmpId", param.get("createId"));

        String cityNo = null;
        String accountProject = null;
        String accountProjectNo = null;
        // 根据楼盘Id 查找城市
        String estateId = reMap.get("estateId").toString();
        List<Estate> estateList = estateMapper.selectEstateInfo(estateId);
        if (null != estateList && !estateList.isEmpty()) {
            Estate estate = estateList.get(0);
            cityNo = estate.getCityNo();
            accountProject = estate.getAccountProject();
            accountProjectNo = estate.getAccountProjectNo();
        }
        reMap.put("cityNo", cityNo);

        String storeId = reMap.get("storeId").toString();
        Integer storeIdInteger = null;
        if (StringUtil.isNotEmpty(storeId)) {
            storeIdInteger = Integer.valueOf(storeId);
            String contractType = contractStoreMapper.getTopContractTypeByStore(Integer.parseInt(storeId));
            reMap.put("contractType", contractType);
        }
        // 门店维护人
        String maintainer = null;
        if (null != reMap.get("contactId")) {
            maintainer = reMap.get("contactId").toString();
            // 业绩归属人所属中心
            Integer centerId = groupMapper.getCenterByUser(maintainer, SystemParam.getWebConfigValue("centerConfig"));
            reMap.put("centerId", centerId);
        }
        // 同步生成报备编号，创建报备
        //cnt = reportNewestReportId(reMap);
        reMap.put("reportId", seqNoAPI.getSeqNoByTypeCode("TYPE_REPORTNO").getReturnData());
        cnt = reportMapper.createReport(reMap);

        //附件信息
        if (param.get("fileList") != null && cnt > 0) {
            List<FileRecordMainDto> fileList = (List<FileRecordMainDto>) param.get("fileList");
            for (FileRecordMainDto fileRecordMainDto : fileList) {
                FileRecordMain file = new FileRecordMain();
                fileRecordMainDto.setRefId(Integer.valueOf(reMap.get("id").toString()));
                BeanUtils.copyProperties(fileRecordMainDto, file);
                fileRecordMainService.update(file);
            }
        }
        if (param.get("fangyouFileList") != null && cnt > 0) {
            List<String> fileIdList = (List<String>) param.get("fangyouFileList");
            for (String url : fileIdList) {
                FangyouReportFile fangyouFile = new FangyouReportFile();
                fangyouFile.setDateCreate(new Date());
                fangyouFile.setDelFlag("N");
                fangyouFile.setReportId(Integer.valueOf(reMap.get("id").toString()));
                fangyouFile.setReportNo(reMap.get("reportId").toString());
                fangyouFile.setTypeId("1");
                fangyouFile.setUrl(url);
                fangyouFile.setUserIdCreate(null);
                fangyouReportFileMapper.insertSelective(fangyouFile);
            }
        }

        String reportId = reMap.get("reportId").toString();
        reMap.put("countId", reportId + "-01");
        reMap.put("progress", 13501);
        reMap.put("confirmStatus", 13601);
        reMap.put("followDate", new Date());
        reMap.put("reportDate", param.get("reportDate"));
        reMap.put("recognitionDay", new Date());
        reMap.put("accountFlg", "");
        reMap.put("accountStatus", "");
        reMap.put("commissionAccountStatus", "");

        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getRelationReward() != null) {
            reMap.put("relationReward", estateRuleInfo.get(0).getRelationReward());
        } else {
            reMap.put("relationReward", 0);
        }
        reMap.put("accountRelationReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getPledgedReward() != null) {
            reMap.put("pledgedReward", estateRuleInfo.get(0).getPledgedReward());
        } else {
            reMap.put("pledgedReward", 0);
        }
        reMap.put("accountPledgedReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getSubscribedReward() != null) {
            reMap.put("subscribedReward", estateRuleInfo.get(0).getSubscribedReward());
        } else {
            reMap.put("subscribedReward", 0);
        }
        reMap.put("accountSubscribedReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getBargainReward() != null) {
            reMap.put("bargainReward", estateRuleInfo.get(0).getBargainReward());
        } else {
            reMap.put("bargainReward", 0);
        }
        reMap.put("accountBargainReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getCommissionSort() != null) {
            reMap.put("commission", estateRuleInfo.get(0).getCommissionSort());
        } else {
            reMap.put("commission", 0);
        }
        reMap.put("accountCommission", 0);
        reMap.put("recognitionDay", new Date());
        reMap.put("crtDt", new Date());
        reMap.put("delFlg", false);

        // 创建报备确认
        cnt = reportDetailMapper.createReportDetail(reMap);

        // 创建带看未确认
        reMap.put("countId", reportId + "-02");
        reMap.put("progress", 13502);
        reMap.put("confirmStatus", 13603);
        cnt = reportDetailMapper.createReportDetail(reMap);

        Boolean boo = true;

        // 门店的城市编号
        String storeCityNo = reMap.get("storeCityNo").toString();
        // 门店所在城市 业绩开关
//        String achievemntTypeFlag = citySettingMapper.getOpenFlagByCityNo(storeCityNo);
//        if("1".equals(achievemntTypeFlag)){
        //创建报备业绩记录
        boo = this.createReportAchievement(reportId, maintainer, storeCityNo, storeIdInteger, accountProject, accountProjectNo);
//        }else {
//        	boo=  this.createReportAchievementOld(reportId,String.valueOf(param.get("storeId")),estateInfo.get(0).getSceneEmpId());
//		}
        if (!boo) {
            logger.error("APIHouseLinkageService", "saveReport", "createReportAchievement", null, null, null, "创建报备记录失败！reportId：" + reportId + "可能是因为没有门店维护人", null);
        }
        return cnt;
    }

    /**
     * add by wang kanlin 2017/09/18
     * 生成报备ID并插入，同步处理
     */
    private synchronized int reportNewestReportId(Map<String, Object> reMap) throws Exception {
        String tempId = "BB" + getFormatDateString(new Date(), "yyyyMMdd");
        String reportId = tempId;
        List<Report> maxReportList = null;
        maxReportList = reportMapper.getMaxReportId(tempId);
        if (maxReportList != null && maxReportList.size() > 0
                && StringUtil.isNotEmpty(maxReportList.get(0).getReportId())) {
            tempId = maxReportList.get(0).getReportId();
            reportId = reportId + getNextNo(tempId.substring(tempId.length() - 5));
        } else {
            reportId = reportId + "00001";
        }

        reMap.put("reportId", reportId);
        int cnt = reportMapper.createReport(reMap);
        return cnt;
    }


    /**
     * @param param
     * @return
     * @Title: getEstateList
     * @Description: 获取楼盘列表
     */
    public List<APIEstateListResultDto> getEstateList(Map<?, ?> param)
            throws Exception {
        List<APIEstateListResultDto> apiEstateListResultDtos = new ArrayList<>();
        List<Estate> estates = this.estateMapper.queryList(param);
        for (Estate estate : estates) {
            APIEstateListResultDto apiEstateListResultDto = new APIEstateListResultDto();
            //楼盘明细及规则
            Map<String, Object> estateMap = new HashMap<>();
            estateMap.put("estateId", estate.getEstateId());
            List<EstateRule> estateRules = this.estateRuleMapper.queryList(estateMap);
            EstateRule estateRule = null;
            if (null != estateRules && !estateRules.isEmpty()) {
                estateRule = estateRules.get(0);
            }
            apiEstateListResultDto = this.convertEstateListResultDto(estate, estateRule);

            //封面图

            Map<String, Object> phoMap = new HashMap<>();
            phoMap.put("estateId", estate.getEstateId());
            APIPhotoDto coverImg = this.getCoverImg(phoMap);
            if (null != coverImg) {
                apiEstateListResultDto.setCoverPhoto(coverImg);
            }

            //户型
            Map<String, Object> typeMap = new HashMap<>();
            typeMap.put("estateId", estate.getEstateId());
            List<APIEstateTypeFormDto> apiEstateTypeFormDtos = new ArrayList<>();
            List<EstateType> estateTypes = this.estateTypeMapper.queryList(typeMap);
            for (EstateType estateType : estateTypes) {
                APIEstateTypeFormDto apiEstateTypeFormDto = this.convertEstateTypeFormDto(estateType, null);
                apiEstateTypeFormDtos.add(apiEstateTypeFormDto);
            }
            apiEstateListResultDto.setEstateTypeFormList(apiEstateTypeFormDtos);

            apiEstateListResultDtos.add(apiEstateListResultDto);
        }

        return apiEstateListResultDtos;
    }

    /**
     * @param param
     * @return
     * @Title: getEstateDetail
     * @Description: 获取楼盘详情
     */
    public APIEstateDetailResultDto getEstateDetail(Map<?, ?> param)
            throws Exception {
        APIEstateDetailResultDto head = new APIEstateDetailResultDto();

        //周边配套
        List<APIEstateSupportTypeDto> supportList = new ArrayList<>();
        Map<String, Object> supportMap = new HashMap<>();
        supportMap.put("estateId", param.get("estateId"));
        List<EstateSupport> estateSupports = this.estateSupportMapper.queryList(supportMap);
        for (EstateSupport estateSupport : estateSupports) {
            APIEstateSupportTypeDto apiEstateSupportTypeDto = new APIEstateSupportTypeDto();
            apiEstateSupportTypeDto.setSupportTitle(estateSupport.getTitle());
            apiEstateSupportTypeDto.setSupportDescription(estateSupport.getDescription());
            supportList.add(apiEstateSupportTypeDto);
        }
        head.setSupportTypeList(supportList);

        //楼盘信息和楼盘规则
        Map<String, Object> estateMap = new HashMap<>();
        estateMap.put("estateId", param.get("estateId"));
        List<Estate> estates = this.estateMapper.queryList(estateMap);
        List<EstateRule> estateRules = this.estateRuleMapper.queryList(estateMap);
        Estate estate = null;
        EstateRule estateRule = null;
        if (null != estates && !estates.isEmpty()) {
            estate = estates.get(0);
        }
        if (null != estateRules && !estateRules.isEmpty()) {
            estateRule = estateRules.get(0);
        }
        APIEstateDto apiEstateDto = this.convertEstateDto(estate, estateRule);
        head.setEstate(apiEstateDto);

        //梯户
        List<APIEstateHouseTypeDto> apiEstateHouseTypeDtos = new ArrayList<>();
        if (null != estate) {
            if (StringUtil.isNotEmpty(estate.getStaircaseHousehold())) {
                String[] sh = estate.getStaircaseHousehold().split(",");
                for (String str : sh) {
                    APIEstateHouseTypeDto apiEstateHouseTypeDto = new APIEstateHouseTypeDto();
                    if (str.length() == 3) {
                        apiEstateHouseTypeDto.setStairs(str.substring(0, 1));
                        apiEstateHouseTypeDto.setHouseholds(str.substring(2, 3));
                        apiEstateHouseTypeDtos.add(apiEstateHouseTypeDto);
                    }
                }
                head.setHouseTypeList(apiEstateHouseTypeDtos);
            }
        }

        //户型
        Map<String, Object> typeMap = new HashMap<>();
        typeMap.put("estateId", param.get("estateId"));
        List<APIEstateTypeFormDto> apiEstateTypeFormDtos = new ArrayList<>();
        List<EstateType> estateTypes = this.estateTypeMapper.queryList(typeMap);
        for (EstateType estateType : estateTypes) {
            APIEstateTypeFormDto apiEstateTypeFormDto = this.convertEstateTypeFormDto(estateType, null);
            apiEstateTypeFormDtos.add(apiEstateTypeFormDto);
        }
        head.setEstateTypeFormList(apiEstateTypeFormDtos);

        //图片        
        //封面图
        Map<String, Object> phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        APIPhotoDto coverImg = this.getCoverImg(phoMap);
        if (null != coverImg) {
            head.setCoverPhoto(coverImg);
        }

        //效果图
        phoMap = new HashMap<>();
        phoMap.put("estateId", (String) param.get("estateId"));
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_DESIGN_IMG);
        List<APIPhotoDto> effectPhotos = this.getImgPath(phoMap);
        head.setEffectPhotos(effectPhotos);

        // 样板间
        phoMap = new HashMap<>();
        phoMap.put("estateId", param.get("estateId"));
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_TEMPLATE_IMG);
        List<APIPhotoDto> templetPhotos = this.getImgPath(phoMap);
        head.setTempletPhotos(templetPhotos);

        // 地理位置
        phoMap = new HashMap<>();
        phoMap.put("estateId", param.get("estateId"));
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_MAP_IMG);
        List<APIPhotoDto> positionPhotos = this.getImgPath(phoMap);
        head.setPositionPhotos(positionPhotos);

        // 区域规划
        phoMap = new HashMap<>();
        phoMap.put("estateId", param.get("estateId"));
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_DISTRICT_IMG);
        List<APIPhotoDto> planningPhotos = this.getImgPath(phoMap);
        head.setPlanningPhotos(planningPhotos);

        // 实景图
        phoMap = new HashMap<>();
        phoMap.put("estateId", param.get("estateId"));
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_REAL_IMG);
        List<APIPhotoDto> actualPhotos = this.getImgPath(phoMap);
        head.setActualPhotos(actualPhotos);
        return head;
    }

    /**
     * @param param
     * @return
     * @Title: getWechatEstateList
     * @Description: 微信--房友新房联动公众号列表
     */
    public ResultData<List<APIWechatEstateListDto>> getWechatEstateList(Map<?, ?> param)
            throws Exception {
        ResultData<List<APIWechatEstateListDto>> resultData = new ResultData<>();
        List<APIWechatEstateListDto> apiWechatEstateListDtos = new ArrayList<>();
        List<Estate> estates = this.estateMapper.queryList(param);
        for (Estate estate : estates) {
            APIWechatEstateListDto apiWechatEstateListDto = this.convertEstateToWechat(estate);
            if (null != apiWechatEstateListDto) {
                apiWechatEstateListDtos.add(apiWechatEstateListDto);
            }
        }
        if (null != apiWechatEstateListDtos && !apiWechatEstateListDtos.isEmpty()) {
            resultData.setReturnData(apiWechatEstateListDtos);
        }
        resultData.setTotalCount((String) param.get(QueryConst.TOTAL_COUNT));
        return resultData;
    }

    private APIWechatEstateListDto convertEstateToWechat(Estate estate)
            throws Exception {
        APIWechatEstateListDto apiWechatEstateListDto = new APIWechatEstateListDto();
        Map<String, Object> map = new HashMap<>();
        map.put("estateId", estate.getEstateId());
        EstateRule estateRule = null;
        List<EstateRule> estateRules = this.estateRuleMapper.queryList(map);
        if (null != estateRules && !estateRules.isEmpty()) {
            estateRule = new EstateRule();
            estateRule = estateRules.get(0);
        }
        if (null != estateRule) {
            String awardType = "";
            //成销(成交)奖励
            if (null != estateRule.getBargainReward()) {
                awardType = String.valueOf(DictionaryConstants.BARGAIN_REWARD);
            }
            //带看奖励
            if (null != estateRule.getRelationReward()) {
                if (StringUtil.isEmpty(awardType)) {
                    awardType = String.valueOf(DictionaryConstants.RELATION_REWARD);
                } else {
                    awardType = awardType + "," + String.valueOf(DictionaryConstants.RELATION_REWARD);
                }
            }
            //认筹奖励
            if (null != estateRule.getPledgedReward()) {
                if (StringUtil.isEmpty(awardType)) {
                    awardType = String.valueOf(DictionaryConstants.PLEDGE_REWARD);
                } else {
                    awardType = awardType + "," + String.valueOf(DictionaryConstants.PLEDGE_REWARD);
                }
            }
            //大定(认购)奖励
            if (null != estateRule.getSubscribedReward()) {
                if (StringUtil.isEmpty(awardType)) {
                    awardType = String.valueOf(DictionaryConstants.SUBSCRIBED_REWARD);
                } else {
                    awardType = awardType + "," + String.valueOf(DictionaryConstants.SUBSCRIBED_REWARD);
                }
            }
            apiWechatEstateListDto.setAwardTypeList(awardType);
            apiWechatEstateListDto.setCommission(estateRule.getCommissionSort());//排序用的字段即为已经计算好的佣金
            apiWechatEstateListDto.setCommissionKbn(estateRule.getCommissionKbn());
            apiWechatEstateListDto.setCommissionPeriod(estateRule.getCommissionPeriod());
        }
        apiWechatEstateListDto.setDistrictNo(estate.getDistrictId());
        apiWechatEstateListDto.setDistrictNm(estate.getDistrictNm());
        apiWechatEstateListDto.setEstateId(estate.getEstateId());
        apiWechatEstateListDto.setEstateNm(estate.getEstateNm());
        apiWechatEstateListDto.setId(estate.getId());
        apiWechatEstateListDto.setMgtKbn(estate.getMgtKbn());
        apiWechatEstateListDto.setSalePriceUnitMax(estate.getSalePriceUnitMax());
        apiWechatEstateListDto.setSalePriceUnitMin(estate.getSalePriceUnitMin());
        List<Photo> photos = this.photoMapper.queryList(map);
        if (null != photos && !photos.isEmpty()) {

            String fileUrl;

            for (Photo photo : photos) {
                if (null == photo.getTypeId()) {
                    if ("Y".equals(photo.getCoverFlg())) {
                        fileUrl = handleFile(photo);
                        apiWechatEstateListDto.setCoverImgPath(fileUrl);
                        break;
                    }
                }
            }
        }
        return apiWechatEstateListDto;
    }

    /**
     * @param id
     * @return
     * @Title: getWechatEstateDetail
     * @Description: 微信--房友新房联动公众号楼盘明细
     */
    public ResultData<APIEstateDetailResultDto> getWechatEstateDetail(int id)
            throws Exception {
        ResultData<APIEstateDetailResultDto> resultData = new ResultData<>();
        APIEstateDetailResultDto head = new APIEstateDetailResultDto();
        //楼盘信息和楼盘规则
        Estate estate = this.estateMapper.getById(id);
        if (null == estate) {
            resultData.setFail("未查出对应的项目详情！");
            return resultData;
        }
        Map<String, Object> estateMap = new HashMap<>();
        estateMap.put("estateId", estate.getEstateId());
        List<EstateRule> estateRules = this.estateRuleMapper.queryList(estateMap);
        EstateRule estateRule = null;
        if (null != estateRules && !estateRules.isEmpty()) {
            estateRule = estateRules.get(0);
        }
        APIEstateDto apiEstateDto = this.convertEstateDto(estate, estateRule);
        head.setEstate(apiEstateDto);

        //周边配套
        List<APIEstateSupportTypeDto> supportList = new ArrayList<>();
        Map<String, Object> supportMap = new HashMap<>();
        supportMap.put("estateId", estate.getEstateId());
        List<EstateSupport> estateSupports = this.estateSupportMapper.queryList(supportMap);
        for (EstateSupport estateSupport : estateSupports) {
            APIEstateSupportTypeDto apiEstateSupportTypeDto = new APIEstateSupportTypeDto();
            apiEstateSupportTypeDto.setSupportTitle(estateSupport.getTitle());
            apiEstateSupportTypeDto.setSupportDescription(estateSupport.getDescription());
            supportList.add(apiEstateSupportTypeDto);
        }
        head.setSupportTypeList(supportList);

        //梯户
        List<APIEstateHouseTypeDto> apiEstateHouseTypeDtos = new ArrayList<>();
        if (null != estate) {
            if (StringUtil.isNotEmpty(estate.getStaircaseHousehold())) {
                String[] sh = estate.getStaircaseHousehold().split(",");
                for (String str : sh) {
                    APIEstateHouseTypeDto apiEstateHouseTypeDto = new APIEstateHouseTypeDto();
                    if (str.length() == 3) {
                        apiEstateHouseTypeDto.setStairs(str.substring(0, 1));
                        apiEstateHouseTypeDto.setHouseholds(str.substring(2, 3));
                        apiEstateHouseTypeDtos.add(apiEstateHouseTypeDto);
                    }
                }
                head.setHouseTypeList(apiEstateHouseTypeDtos);
            }
        }

        //户型
        Map<String, Object> typeMap = new HashMap<>();
        typeMap.put("estateId", estate.getEstateId());
        List<APIEstateTypeFormDto> apiEstateTypeFormDtos = new ArrayList<>();
        List<EstateType> estateTypes = this.estateTypeMapper.queryList(typeMap);
        for (EstateType estateType : estateTypes) {
            APIEstateTypeFormDto apiEstateTypeFormDto = this.convertEstateTypeFormDto(estateType, null);
            apiEstateTypeFormDtos.add(apiEstateTypeFormDto);
        }
        head.setEstateTypeFormList(apiEstateTypeFormDtos);

        //图片
        //封面图
        Map<String, Object> phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        APIPhotoDto coverImg = this.getCoverImg(phoMap);
        if (null != coverImg) {
            head.setCoverPhoto(coverImg);
        }

        //效果图
        phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_DESIGN_IMG);
        List<APIPhotoDto> effectPhotos = this.getImgPath(phoMap);
        head.setEffectPhotos(effectPhotos);

        // 样板间
        phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_TEMPLATE_IMG);
        List<APIPhotoDto> templetPhotos = this.getImgPath(phoMap);
        head.setTempletPhotos(templetPhotos);

        // 地理位置
        phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_MAP_IMG);
        List<APIPhotoDto> positionPhotos = this.getImgPath(phoMap);
        head.setPositionPhotos(positionPhotos);

        // 区域规划
        phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_DISTRICT_IMG);
        List<APIPhotoDto> planningPhotos = this.getImgPath(phoMap);
        head.setPlanningPhotos(planningPhotos);

        // 实景图
        phoMap = new HashMap<>();
        phoMap.put("estateId", estate.getEstateId());
        phoMap.put("photoKbn", DictionaryConstants.ESTATE_REAL_IMG);
        List<APIPhotoDto> actualPhotos = this.getImgPath(phoMap);
        head.setActualPhotos(actualPhotos);

        resultData.setReturnData(head);
        return resultData;
    }

    /**
     * 对象转换MO--DTO
     *
     * @param craList
     * @return List<ReportDetailDto>
     */
    private List<ReportDetailDto> convertReportDetailData(List<ReportDetail> craList)
            throws Exception {
        List<ReportDetailDto> reportDtoList = new ArrayList<ReportDetailDto>();

        if (null != craList && !craList.isEmpty()) {
            ReportDetailDto craDto = null;
            for (ReportDetail cra : craList) {
                craDto = new ReportDetailDto();
                BeanUtils.copyProperties(cra, craDto);
                reportDtoList.add(craDto);
            }
        }
        return reportDtoList;
    }

    /**
     * 取得格式化的日期字符串
     *
     * @param dat       日期对象
     * @param strFormat 格式
     * @return 格式化后的字符串
     */
    private String getFormatDateString(Date dat, String strFormat)
            throws Exception {
        if (null == dat) {
            return "";
        }
        SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
        return myFmt.format(dat);
    }

    /**
     * 得到下一个编号
     *
     * @param sNo 现在的编号（如：[0001]）
     * @return 下一个编号
     */
    public static String getNextNo(String sNo) {
        // 如果没有值则返回空
        if (StringUtil.isNotEmpty(sNo) == false) {
            return "";
        }

        // 加工编号
        String tmp = sNo.trim();
        int iLen = tmp.length();
        int iNo = Integer.parseInt(tmp);
        String tmp2 = String.valueOf(iNo + 1);
        while (tmp2.length() < iLen) {
            tmp2 = "0" + tmp2;
        }
        return tmp2;
    }

    /**
     * @param estate
     * @return
     * @Title: convertEstateDto
     * @Description: 将Estate转换成APIEstateDto
     */
    private APIEstateDto convertEstateDto(Estate estate, EstateRule estateRule)
            throws Exception {
        APIEstateDto apiEstateDto = new APIEstateDto();
        if (null == estate && null == estateRule) {
            apiEstateDto = null;
            return apiEstateDto;
        }
        if (null != estate) {
            apiEstateDto.setAddress(estate.getAddress());
            apiEstateDto.setAddressCoordinateX(estate.getAddressCoordinateX());
            apiEstateDto.setAddressCoordinateY(estate.getAddressCoordinateY());

            apiEstateDto.setAreaId(estate.getAreaId());
            apiEstateDto.setAreaNm(estate.getAreaNm());
            apiEstateDto.setAuditMemo(estate.getAuditMemo());
            apiEstateDto.setAuditStatus(SystemParam.getDicValueByDicCode(estate.getAuditStatus() + ""));

            apiEstateDto.setCityNo(estate.getCityNo());

            apiEstateDto.setCooperationDtEnd(estate.getCooperationDtEnd());
            apiEstateDto.setCooperationDtStart(estate.getCooperationDtStart());
            apiEstateDto.setCooperationExpDt(estate.getCooperationExpDt());
            apiEstateDto.setCrtDt(estate.getCrtDt());
            apiEstateDto.setCrtEmpId(estate.getCrtEmpId() + "");
            apiEstateDto.setDecorationKbn(getStrByCode(estate.getDecorationKbn()));
            String delFlg = "";
            if (estate.getDelFlg()) {
                delFlg = "1";
            } else if (estate.getDelFlg() == false) {
                delFlg = "0";
            }
            apiEstateDto.setDelFlg(delFlg);
            apiEstateDto.setDeptId(estate.getDeptId() + "");
            apiEstateDto.setDevCompany(estate.getDevCompany());
            apiEstateDto.setDistrictId(estate.getDistrictId());
            apiEstateDto.setDistrictNm(estate.getDistrictNm());
            apiEstateDto.setEmpId(estate.getEmpId() + "");
            apiEstateDto.setEstateId(estate.getEstateId());
            apiEstateDto.setEstateNm(estate.getEstateNm());
            apiEstateDto.setEstateTypeSearch(estate.getEstateTypeSearch());
            apiEstateDto.setFieldAddress(estate.getFieldAddress());
            apiEstateDto.setHeatKbn(SystemParam.getDicValueByDicCode(estate.getHeatKbn() + ""));

            apiEstateDto.setHouseCnt(estate.getHouseCnt() == null ? null : new BigDecimal(estate.getHouseCnt()));
            apiEstateDto.setHouseTransferKbn(SystemParam.getDicValueByDicCode(estate.getHouseTransferKbn() + ""));

            //############# 交房时间 格式转换处理 start #############//
            //apiEstateDto.setHouseTransferTime(estate.getHouseTransferTime());
        /*    String strHouseTransferTime = estate.getHouseTransferTime();            
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date dateHouseTransferTime = null;
            try
            {
                dateHouseTransferTime = sdf.parse(strHouseTransferTime);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            apiEstateDto.setHouseTransferTime(this.getFormatDateString(dateHouseTransferTime, "yyyy年MM月"));*/
            String strHouseTransferTime = estate.getHouseTransferTime();
            if (StringUtil.isNotEmpty(strHouseTransferTime)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                Date dateHouseTransferTime = null;
                try {
                    dateHouseTransferTime = sdf.parse(strHouseTransferTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                apiEstateDto.setHouseTransferTime(this.getFormatDateString(dateHouseTransferTime, "yyyy年MM月"));
            } else {
                apiEstateDto.setHouseTransferTime("");
            }
            //############# 交房时间 格式转换处理 end #############//

            apiEstateDto.setHydropowerGasKbn(SystemParam.getDicValueByDicCode(estate.getHydropowerGasKbn() + ""));
            apiEstateDto.setMark(estate.getMark());
            apiEstateDto.setMgtCompany(estate.getMgtCompany());
            apiEstateDto.setMgtKbn(getStrByCode(estate.getMgtKbn()));
            apiEstateDto.setMgtPrice(estate.getMgtPrice());
            apiEstateDto.setOpenKbn(SystemParam.getDicValueByDicCode(estate.getOpenKbn() + ""));
            apiEstateDto.setOpenTime(estate.getOpenTime());
            apiEstateDto.setOwnYearKbn(getStrByCode(estate.getOwnYearKbn()));
            apiEstateDto.setParkCnt(estate.getParkCnt() == null ? null : new BigDecimal(estate.getParkCnt()));
            apiEstateDto.setParkFee(estate.getParkFee());
            apiEstateDto.setPartner(SystemParam.getDicValueByDicCode(estate.getPartner() + ""));
            apiEstateDto.setPartnerNm(estate.getPartnerNm());
            if (null != estate.getPreSalePermitKbn()) {
                switch (estate.getPreSalePermitKbn()) {
                    case 1:
                        apiEstateDto.setPreSalePermitKbn("有");
                        break;
                    case 0:
                        apiEstateDto.setPreSalePermitKbn("无");
                    default:
                        break;
                }
            }

            apiEstateDto.setProjectDescription(estate.getProjectDescription());
            apiEstateDto.setRateFAR(estate.getRateFAR());
            apiEstateDto.setRateGreen(estate.getRateGreen());

            apiEstateDto.setReleaseDt(estate.getReleaseDt());
            apiEstateDto.setReleaseOffMemo(estate.getReleaseOffMemo());
            apiEstateDto.setReleaseStatus(SystemParam.getDicValueByDicCode(estate.getReleaseStatus() + ""));

            apiEstateDto.setSalePriceUnit(estate.getSalePriceUnit());
            apiEstateDto.setSalesStatus(SystemParam.getDicValueByDicCode(estate.getSalesStatus() + ""));
            apiEstateDto.setSceneDeptId(estate.getSceneDeptId() + "");
            apiEstateDto.setSceneEmpId(estate.getSceneEmpId() + "");
            apiEstateDto.setStaircaseHousehold(estate.getStaircaseHousehold());
            apiEstateDto.setStrCooperationDtEnd(this.getFormatDateString(estate.getCooperationDtEnd(), "yyyy年MM月dd日"));
            apiEstateDto.setStrCooperationDtStart(this.getFormatDateString(estate.getCooperationDtStart(),
                    "yyyy年MM月dd日"));
            apiEstateDto.setStrCrtDt(this.getFormatDateString(estate.getCrtDt(), "yyyy年MM月dd日"));
            //            apiEstateDto.setStrOpenTime(this.getFormatDateString(estate.getOpenTime(), "yyyy年MM月dd日"));
            apiEstateDto.setStrOpenTime(this.getFormatDateString(estate.getOpenTime(), "yyyy年MM月"));
            apiEstateDto.setTypeKbn(estate.getTypeKbn());
            apiEstateDto.setUptDt(estate.getUptDt());
            apiEstateDto.setUptEmpId(estate.getUptEmpId() + "");
            apiEstateDto.setSalePriceUnitMax(estate.getSalePriceUnitMax());
            apiEstateDto.setSalePriceUnitMin(estate.getSalePriceUnitMin());
        }
        if (null != estateRule) {
            apiEstateDto.setAdvanceReportHH(estateRule.getAdvanceReportHH());
            apiEstateDto.setAdvanceReportMM(estateRule.getAdvanceReportMM());
            apiEstateDto.setAuthenticationKbn(SystemParam.getDicValueByDicCode(estateRule.getAuthenticationKbn() + ""));
            apiEstateDto.setBargainReward(estateRule.getBargainReward());
            apiEstateDto.setCommission(estateRule.getCommission());
            //            apiEstateDto.setCommissionKbn(SystemParam.getDicValueByDicCode(estateRule.getCommissionKbn()+""));
            apiEstateDto.setCommissionKbn(estateRule.getCommissionKbn() + "");
            apiEstateDto.setCommissionMemo(estateRule.getCommissionMemo());
            apiEstateDto.setCommissionPeriod(estateRule.getCommissionPeriod());
            apiEstateDto.setCommissionRule(estateRule.getCommissionRule());
            apiEstateDto.setCommissionSort(estateRule.getCommissionSort());
            apiEstateDto.setHideNumberFlg(estateRule.getHideNumberFlg());
            apiEstateDto.setPayKbn(SystemParam.getDicValueByDicCode(estateRule.getPayKbn() + ""));
            apiEstateDto.setPledgedDtEnd(estateRule.getPledgedDtEnd());
            apiEstateDto.setPledgedReward(estateRule.getPledgedReward());
            apiEstateDto.setRelationDtEnd(estateRule.getRelationDtEnd());
            apiEstateDto.setRelationDtStart(estateRule.getRelationDtStart());
            apiEstateDto.setRelationProtectPeriod(estateRule.getRelationProtectPeriod());
            apiEstateDto.setRelationReward(estateRule.getRelationReward());
            apiEstateDto.setReportDtEnd(estateRule.getReportDtEnd());
            apiEstateDto.setRelationDtStart(estateRule.getRelationDtStart());
            apiEstateDto.setReportKbn(SystemParam.getDicValueByDicCode(estateRule.getReportKbn() + ""));
            apiEstateDto.setReportRule(estateRule.getReportRule());
            apiEstateDto.setSaleKbn(SystemParam.getDicValueByDicCode(estateRule.getSaleKbn() + ""));
            apiEstateDto.setSubscribedReward(estateRule.getSubscribedReward());
            apiEstateDto.setStrPledgedDtEnd(this.getFormatDateString(estateRule.getPledgedDtEnd(), "yyyy年MM月dd日"));
            apiEstateDto.setStrPledgedDtStart(this.getFormatDateString(estateRule.getPledgedDtStart(), "yyyy年MM月dd日"));
            apiEstateDto.setStrRelationDtEnd(this.getFormatDateString(estateRule.getRelationDtEnd(), "yyyy年MM月dd日"));
            apiEstateDto.setStrRelationDtStart(this.getFormatDateString(estateRule.getRelationDtStart(), "yyyy年MM月dd日"));
            apiEstateDto.setStrReportDtEnd(this.getFormatDateString(estateRule.getReportDtEnd(), "yyyy年MM月dd日"));
            apiEstateDto.setStrReportDtStart(this.getFormatDateString(estateRule.getRelationDtStart(), "yyyy年MM月dd日"));
            apiEstateDto.setStrSubscribedDtEnd(this.getFormatDateString(estateRule.getSubscribedDtEnd(), "yyyy年MM月dd日"));
            apiEstateDto.setStrSubscribedDtStart(this.getFormatDateString(estateRule.getSubscribedDtStart(),
                    "yyyy年MM月dd日"));
            apiEstateDto.setStrUptDt(this.getFormatDateString(estateRule.getUptDt(), "yyyy年MM月dd日"));
            apiEstateDto.setStrSubscribedDtEnd(this.getFormatDateString(estateRule.getSubscribedDtEnd(), "yyyy年MM月dd日"));
            apiEstateDto.setStrSubscribedDtStart(this.getFormatDateString(estateRule.getSubscribedDtStart(),
                    "yyyy年MM月dd日"));
        }
        return apiEstateDto;
    }

    /**
     * @param estateType
     * @return
     * @Title: convertEstateTypeFormDto
     * @Description: 将EstateType转换成APIEstateTypeFormDto
     */
    private APIEstateTypeFormDto convertEstateTypeFormDto(EstateType estateType, APIPhotoDto coverImg)
            throws Exception {
        APIEstateTypeFormDto apiEstateTypeFormDto = new APIEstateTypeFormDto();
        if (null == estateType) {
            return null;
        } else {
            apiEstateTypeFormDto.setBuildSquare(estateType.getBuildSquare());
            apiEstateTypeFormDto.setCountF(estateType.getCountF());
            apiEstateTypeFormDto.setCountFlg(estateType.getCountFlg() + "");
            apiEstateTypeFormDto.setCountT(estateType.getCountT());
            apiEstateTypeFormDto.setCountW(estateType.getCountW());
            apiEstateTypeFormDto.setCountY(estateType.getCountY());
            apiEstateTypeFormDto.setDirectionKbn(SystemParam.getDicValueByDicCode(estateType.getDirectionKbn() + ""));
            apiEstateTypeFormDto.setEstateId(estateType.getEstateId());
            apiEstateTypeFormDto.setLabel(estateType.getLabel());
            apiEstateTypeFormDto.setTypeId(estateType.getTypeId());
            apiEstateTypeFormDto.setUseSquare(estateType.getUseSquare());

            Map<String, Object> phoMap = new HashMap<>();
            //户型图
            phoMap = new HashMap<>();
            phoMap.put("estateId", estateType.getEstateId());
            phoMap.put("typeId", estateType.getTypeId());
            phoMap.put("photoKbn", DictionaryConstants.HOUSE_TYPE_IMG);
            List<APIPhotoDto> houseTypeImgs = this.getImgPath(phoMap);
            apiEstateTypeFormDto.setHouseTypePhotos(houseTypeImgs);
            //样板间
            phoMap = new HashMap<>();
            phoMap.put("estateId", estateType.getEstateId());
            phoMap.put("typeId", estateType.getTypeId());
            phoMap.put("photoKbn", DictionaryConstants.HOUSE_TEMPLATE_IMG);
            List<APIPhotoDto> houseTemplateImgs = this.getImgPath(phoMap);
            apiEstateTypeFormDto.setTempletPhotos(houseTemplateImgs);
        }
        return apiEstateTypeFormDto;
    }

    /**
     * @param param
     * @return
     * @Title: getImgPath
     * @Description: 获取图片路径
     */
    private List<APIPhotoDto> getImgPath(Map<String, Object> param)
            throws Exception {
        List<APIPhotoDto> imgList = new ArrayList<>();
        List<Photo> housePhos = this.photoMapper.queryList(param);
        for (Photo photo : housePhos) {
            APIPhotoDto apiPhotoDto = new APIPhotoDto();
            apiPhotoDto.setContent(photo.getContent());
            apiPhotoDto.setCoverFlg(photo.getCoverFlg());
            apiPhotoDto.setCrtDt(photo.getCrtDt());
            apiPhotoDto.setCrtEmpId(photo.getCrtEmpId() + "");
            String delFlg = "";
            if (photo.getDelFlg()) {
                delFlg = "1";
            } else if (photo.getDelFlg() == false) {
                delFlg = "0";
            }
            apiPhotoDto.setDelFlg(delFlg);
            apiPhotoDto.setEstateId(photo.getEstateId());
            apiPhotoDto.setOrderNo(photo.getOrderNo());
            apiPhotoDto.setPhotoId(photo.getPhotoId());
            apiPhotoDto.setPhotoKbn(SystemParam.getDicValueByDicCode(photo.getPhotoKbn() + ""));
            apiPhotoDto.setPhotoNm(photo.getPhotoNm());
            //String imgPath = FileHelper.getFilePath(photo.getPhotoId());
//            String imgPath = handleFile(photo);
            String imgPath = photo.getFileUrl();
            apiPhotoDto.setPhotoPath(imgPath);
            apiPhotoDto.setTypeId(photo.getTypeId());
            apiPhotoDto.setUptDt(photo.getUptDt());
            apiPhotoDto.setUptEmpId(photo.getUptEmpId() + "");
            imgList.add(apiPhotoDto);
        }
        return imgList;
    }

    /**
     * @param param
     * @return
     * @Title: getCoverImg
     * @Description: 获取楼盘的封面图
     */
    private APIPhotoDto getCoverImg(Map<String, Object> param)
            throws Exception {
        APIPhotoDto coverImg = null;
        List<Photo> housePhos = this.photoMapper.queryList(param);
        for (Photo photo : housePhos) {
            if (photo.getCoverFlg().equals("Y") && null == photo.getTypeId()) {
                String delFlg = "";
                if (photo.getDelFlg()) {
                    delFlg = "1";
                } else if (photo.getDelFlg() == false) {
                    delFlg = "0";
                }
                //String imgPath = FileHelper.getFilePath(photo.getPhotoId());

//                String imgPath = handleFile(photo);
                String imgPath = photo.getFileUrl();

                coverImg = new APIPhotoDto();
                coverImg.setContent(photo.getContent());
                coverImg.setCoverFlg(photo.getCoverFlg());
                coverImg.setCrtDt(photo.getCrtDt());
                coverImg.setCrtEmpId(photo.getCrtEmpId() + "");
                coverImg.setDelFlg(delFlg);
                coverImg.setEstateId(photo.getEstateId());
                coverImg.setOrderNo(photo.getOrderNo());
                coverImg.setPhotoId(photo.getPhotoId());
                coverImg.setPhotoKbn(SystemParam.getDicValueByDicCode(photo.getPhotoKbn() + ""));
                coverImg.setPhotoNm(photo.getPhotoNm());
                coverImg.setPhotoPath(imgPath);
                coverImg.setTypeId(photo.getTypeId());
                coverImg.setUptDt(photo.getUptDt());
                coverImg.setUptEmpId(photo.getUptEmpId() + "");
                break;
            }
        }
        return coverImg;
    }

    private APIEstateListResultDto convertEstateListResultDto(Estate estate, EstateRule estateRule)
            throws Exception {
        APIEstateListResultDto apiEstateListResultDto = new APIEstateListResultDto();
        if (null == estate && null == estateRule) {
            return null;
        }
        if (null != estate) {
            apiEstateListResultDto.setAddress(estate.getAddress());
            apiEstateListResultDto.setAreaId(estate.getAreaId());
            apiEstateListResultDto.setAreaNm(estate.getAreaNm());
            apiEstateListResultDto.setAuditStatus(estate.getAuditStatus() + "");
            apiEstateListResultDto.setAuditStatusDis(SystemParam.getDicValueByDicCode(estate.getAuditStatus() + ""));
            apiEstateListResultDto.setCityNo(estate.getCityNo());
            apiEstateListResultDto.setCityNoDis(estate.getCityNm());
            apiEstateListResultDto.setCooperationDtEnd(this.getFormatDateString(estate.getCooperationDtEnd(),
                    "yyyy-MM-dd hh:mm"));
            apiEstateListResultDto.setCooperationDtStart(this.getFormatDateString(estate.getCooperationDtStart(),
                    "yyyy-MM-dd hh:mm"));
            apiEstateListResultDto.setCrtDt(this.getFormatDateString(estate.getCrtDt(), "yyyy-MM-dd hh:mm"));
            apiEstateListResultDto.setCurpage("0");
            apiEstateListResultDto.setDeptId(estate.getDeptId() + "");
            apiEstateListResultDto.setDeptIdDis(this.getGroupNmByDepId(estate.getDeptId()));
            apiEstateListResultDto.setDistrictId(estate.getDistrictId());
            apiEstateListResultDto.setDistrictNm(estate.getDistrictNm());
            apiEstateListResultDto.setEmpId(estate.getEmpId() + "");
            apiEstateListResultDto.setEmpIdDis(this.getNameByUserId(estate.getEmpId()));
            apiEstateListResultDto.setEstateId(estate.getEstateId());
            apiEstateListResultDto.setEstateNm(estate.getEstateNm());
            apiEstateListResultDto.setMark(estate.getMark());
            apiEstateListResultDto.setPartner(estate.getPartner() + "");
            apiEstateListResultDto.setPartnerDis(SystemParam.getDicValueByDicCode(estate.getPartner() + ""));
            apiEstateListResultDto.setPartnerNm(estate.getPartnerNm());
            apiEstateListResultDto.setReleaseStatus(estate.getReleaseStatus() + "");
            apiEstateListResultDto.setReleaseStatusDis(SystemParam.getDicValueByDicCode(estate.getReleaseStatus() + ""));
            apiEstateListResultDto.setSalePriceUnit(estate.getSalePriceUnit());
            apiEstateListResultDto.setSalePriceUnitMax(estate.getSalePriceUnitMax());
            apiEstateListResultDto.setSalePriceUnitMin(estate.getSalePriceUnitMin());
        }
        if (null != estateRule) {
            apiEstateListResultDto.setBargainReward(estateRule.getBargainReward());
            apiEstateListResultDto.setCommissionPeriod(estateRule.getCommissionPeriod());
            apiEstateListResultDto.setRelationReward(estateRule.getRelationReward());
            apiEstateListResultDto.setCommissionSort(estateRule.getCommissionSort());
            apiEstateListResultDto.setCommission(estateRule.getCommission());
            apiEstateListResultDto.setCommissionKbn(estateRule.getCommissionKbn());
        }
        return apiEstateListResultDto;
    }

    /**
     * @param userId
     * @return
     * @Title: getNameByUserId
     * @Description: 跟进用户编号获取用户姓名
     */
    private String getNameByUserId(Integer userId)
            throws Exception {
        String userName = "";
        if (null != userId) {
            ResultData<User> userData = this.userAPIImpl.getUserById(userId);
            if (userData.getReturnCode() == ReturnCode.SUCCESS) {
                userName = userData.getReturnData().getUserName();
            }
        }
        return userName;
    }

    /**
     * @param depId
     * @return
     * @Title: getGroupNmByDepId
     * @Description: 根据
     */
    private String getGroupNmByDepId(Integer depId)
            throws Exception {
        String groupNm = "";
        if (null != depId) {
            Group group = this.groupMapper.selectByGroupId(depId);
            if (null != group) {
                groupNm = group.getGroupName();
            }
        }
        return groupNm;
    }

    /**
     * 根据日期字符串返回日期对象
     *
     * @param sDat      日期字符串
     * @param strFormat 格式
     * @return 日期对象
     */
    private Date getFormatStringDate(String sDat, String strFormat)
            throws Exception {
        try {
            if (StringUtil.isNotEmpty(sDat)) {
                // 解析日期 
                SimpleDateFormat myFmt = new SimpleDateFormat(strFormat);
                return myFmt.parse(sDat);
            }
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 对文件数据进行组装处理
     *
     * @param photo
     * @param
     * @throws Exception
     */
    private String handleFile(Photo photo)
            throws Exception {
        //1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        // 获取文件渠道系统配置（外部文件系统的配置）
        Map<?, ?> mapTemp = getChannelConfig();

        //取关系表中的文件Code
        String fileId = photo.getPhotoId();

        String fileUrl = null;

        if (StringUtil.isNotEmpty(fileId)) {
            // 查询路径
            String queryUrl = (String) mapTemp.get("CRIC_queryfile_path");
            // 下载路径
            String downloadUrl = (String) mapTemp.get("CRIC_downloadfile_path");
            // 授权号
            String permitCode = (String) mapTemp.get("CRIC_file_permit_code");

            // 获取图片路径（规定图片大小利于手机端加载）
            Map<String, Object> handleMap = new HashMap<>();
            handleMap.put("width", "300");
            handleMap.put("height", "300");
            handleMap.put("waterPic", "0");// 无水印图片
            handleMap.put("waterPosition", "0");//  3:左下角
            handleMap.put("cutType", "0");//0-不裁剪
            fileUrl =
                    FileHelper.getFilePath(fileId,
                            FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                            queryUrl,
                            downloadUrl,
                            permitCode,
                            handleMap);
        } else {
            //取出fileNo
            String fileNo = photo.getFileNo();

            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>) reback.getReturnData();
            if (null != mapTemppp) {

                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");

                //fileCode
                String fileCode = fileDto.getFileCode();
                //渠道Code
                String channelCode = fileDto.getChannelCode();
                if ("CRIC".equals(channelCode)) {
                    // 查询路径
                    String queryUrl = (String) mapTemp.get("CRIC_queryfile_path");
                    // 下载路径
                    String downloadUrl = (String) mapTemp.get("CRIC_downloadfile_path");
                    // 授权号
                    String permitCode = (String) mapTemp.get("CRIC_file_permit_code");

                    //获取图片路径
                    //fileUrl = FileHelper.getFilePath(fileCode);

                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    fileUrl =
                            FileHelper.getFilePath(fileCode,
                                    FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                                    queryUrl,
                                    downloadUrl,
                                    permitCode,
                                    handleMap);

                } else if ("weiphoto".equals(channelCode)) {
                    // 查询路径
                    String fileViewUrl = (String) mapTemp.get("wp_view_url");

                    fileUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "200");

                }

            }
        }

        //获取图片路径
        return fileUrl;
    }

    /**
     * 创建报备业绩记录
     *
     * @param reportNo
     * @param storeId
     * @return
     * @throws Exception
     */
    private Boolean createReportAchievement(String reportNo, String maintainer, String storeCityNo, Integer storeId, String accountProject, String accountProjectNo)
            throws Exception {
        Boolean boo = true;
        //报备Id
        Integer reportId = null;

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportId", reportNo);

        //查询到报备Id
        List<Report> reportlist = reportMapper.getReport(reqMap);
        if (reportlist != null && reportlist.size() > 0
                && StringUtils.isNotBlank(String.valueOf(reportlist.get(0).getId()))) {
            reportId = reportlist.get(0).getId();
        } else {
            boo = false;
        }

        //查询门店维护人相关领导和职位所属组等信息
        if (boo && null != maintainer) {
            boo = achievementService.saveAchievementInfo(maintainer, reportId, "LinkCreate", "Link", DictionaryConstants.ACHIEVETYPE_LINK, storeId, accountProject, accountProjectNo);
        }
        return boo;
    }


    /**
     * 创建报备业绩记录
     *
     * @param reportNo
     * @param storeId
     * @return
     * @throws Exception
     */
    private Boolean createReportAchievementOld(String reportNo, String storeId, Integer userIdCreate)
            throws Exception {
        Boolean boo = true;
        //报备Id
        Integer reportId = null;
        //维护人工号
        String maintainerId = null;
        //维护人名称
//        String maintainer = null;

        ReportAchievement reportAchieve = new ReportAchievement();

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportId", reportNo);

        //查询到报备Id
        List<Report> reportlist = reportMapper.getReport(reqMap);
        if (reportlist != null && reportlist.size() > 0
                && StringUtils.isNotBlank(String.valueOf(reportlist.get(0).getId()))) {
            reportId = reportlist.get(0).getId();
        } else {
            boo = false;
        }

        //查询到门店维护人工号，名称
        reqMap.put("storeId", Integer.valueOf(storeId));
        List<StoreMaintainer> userList = this.storeMaintainerMapper.getStoreMaintainerHisList(reqMap);
        if (userList != null && userList.size() > 0
                && StringUtils.isNotBlank(String.valueOf(userList.get(0).getUserCode()))
                && StringUtils.isNotBlank(String.valueOf(userList.get(0).getUserName()))) {
            maintainerId = userList.get(0).getUserCode();
//            maintainer = userList.get(0).getUserName();
        } else {
            boo = false;
        }

        //查询门店维护人相关领导和职位所属组等信息
        if (boo) {
            // 查询当前【业绩归属维护人】的所属组和所属岗的信息
            // 1.根据userId查询用户信息
            User currentUser = userMapper.getUserByCode(maintainerId);


            reportAchieve.setMaintainer(currentUser.getUserName());
            reportAchieve.setMaintainerId(currentUser.getUserCode());
            // ------------------- 可能多岗位的场合 start -------------- //
            // 有可能存在多岗因此需要用List接收
            List<Post> currentPostList = null;
            // 2.查询门店维护人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
            currentPostList = postMapper.getExpandPostByUserId(currentUser.getUserId());
            Group currentGroup = null;
            // 岗位类型(18:拓展总监   20:拓展经理    21:拓展专员   22:事业部总经理)
            int currentPostType = 0;
            if (currentPostList != null && !currentPostList.isEmpty()) {
                // 多岗的场合
                for (Post currentPost : currentPostList) {
                    String currentPostGroupId = null;
                    if (currentPost.getGroupId() != null) {
                        currentPostGroupId = String.valueOf(currentPost.getGroupId());
                    }
                    String currentUserGgroupId = null;
                    if (currentUser.getGroupId() != null) {
                        currentUserGgroupId = String.valueOf(currentUser.getGroupId());
                    }

                    // 当前岗对应的组
                    if (currentPostGroupId.equals(currentUserGgroupId)) {
                        // 3.查询所属组信息
                        currentGroup = groupMapper.selectByPrimaryKey(currentPost.getGroupId());
                        // 设置当前业绩归属拓展人员信息
                        this.setRelatePersonInfo(currentUser, currentGroup, currentPost, reportAchieve);
                        // 获取当前拓展人员的岗位类型
                        currentPostType = currentPost.getTypeId();
                    }
                }
            }
            // ------------------- 可能多岗位的场合 end -------------- //

            // =============== 查询当前拓展人员的上级信息 start ================ //
            // 当前归属拓展人所属组为【拓展组】的场合
            if (currentGroup != null) {
                if (currentGroup.getTypeId() == 4) {
                    // 拓展经理的场合 (20:拓展经理   21:拓展专员)
                    if (currentPostType == 20) {
                        //  1. 查找【区域总监】所属组（固定在【拓展经理】的基础上往跳1级）
                        Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                        // 非空判断
                        if (null != leadersGroup.getGroupManagerId()
                                && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId())) {
                            int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                            // 设置【区域总监】的信息
                            this.setLeaderInfo(userId, reportAchieve);
                        } else {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(leadersGroup, reportAchieve);
                        }

                        //  2. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往跳2级）
                        Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                        Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                        // 非空判断
                        if (null != secondLeadersGroup.getGroupManagerId()
                                && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                            int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                            // 设置【事业部总经理】的信息
                            this.setLeaderInfo(userId, reportAchieve);
                        } else {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(secondLeadersGroup, reportAchieve);
                        }

                        // 拓展专员的场合
                    } else {
                        // 1. 查找【拓展经理】所属组（根据【拓展专员】的GroupManagerId找到他的上级）
                        if (null != currentGroup.getGroupManagerId()
                                && StringUtil.isNotEmpty(currentGroup.getGroupManagerId())) {
                            int userId = Integer.parseInt(currentGroup.getGroupManagerId());
                            // 设置【拓展经理】的信息
                            this.setLeaderInfo(userId, reportAchieve);
                        } else {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息 (该处拓展经理同专员所属同一个组)
                            this.setPerformTeamInfo(currentGroup, reportAchieve);
                        }

                        //  2. 查找【区域总监】所属组（固定在【拓展经理】的基础上往上跳1级）
                        Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                        // 非空判断
                        if (null != leadersGroup.getGroupManagerId()
                                && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId())) {
                            int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                            // 设置【区域总监】的信息
                            this.setLeaderInfo(userId, reportAchieve);
                        } else {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(leadersGroup, reportAchieve);
                        }

                        //  3. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                        Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                        Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                        // 非空判断
                        if (null != secondLeadersGroup.getGroupManagerId()
                                && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                            int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                            // 设置【事业部总经理】的信息
                            this.setLeaderInfo(userId, reportAchieve);
                        } else {
                            // 上级领导人不存在的场合、就只存它上级所在组的信息
                            this.setPerformTeamInfo(secondLeadersGroup, reportAchieve);
                        }
                    }

                    // 当前归属拓展人所属组为【拓展事业部】的场合
                } else if (currentGroup.getTypeId() == 8) {

                    //  查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                    Group firstLeadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                    Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                    // 非空判断
                    if (null != secondLeadersGroup.getGroupManagerId()
                            && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                        // 设置【事业部总经理】的信息
                        this.setLeaderInfo(userId, reportAchieve);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(secondLeadersGroup, reportAchieve);
                    }
                }
                // =============== 查询当前拓展人员的上级信息 end ================ //
            } else {
                boo = false;
            }
        }

        //报备Id
        reportAchieve.setReportId(reportId);
        // 操作者
        reportAchieve.setUserIdCreate(userIdCreate);
        // 创建时间
        reportAchieve.setDateCreate(new Date());
        // 删除标识(false:未删除  true:已删除)
        reportAchieve.setDelFlag(false);
        // 2.新增-业绩关联人员信息
        int n = reportAchieveMapper.createReportAchievement(reportAchieve);

        if (n <= 0) {
            boo = false;
        }

        return boo;
    }

    /**
     * 设置业绩归属门店维护人信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     *
     * @param paramUser  用户信息
     * @param paramGroup 组信息
     * @param paramPost  岗位信息
     * @return
     * @throws Exception
     */
    private void setRelatePersonInfo(User paramUser, Group paramGroup, Post paramPost, ReportAchievement reportAchieve)
            throws Exception {
        // 门店维护人所属组为【拓展组】的场合（专员和拓展经理都属于拓展组）
        if (paramGroup.getTypeId() == 4) {
            // 门店维护人拓展经理的场合 (20:拓展经理   21:拓展专员)
            if (paramPost.getTypeId() == 20) {
                // 门店维护人拓展经理工号
                reportAchieve.setMaintainerLeaderId(paramUser.getUserCode());
                // 门店维护人拓展经理姓名
                reportAchieve.setMaintainerLeader(paramUser.getUserName());
                // 门店维护人拓展经理所属组
                reportAchieve.setPerformTeam3(paramGroup.getGroupName());
                // 门店维护人拓展经理所属岗位
                reportAchieve.setPostTypeName3(paramPost.getPostName());
                // 门店维护人拓展经理所属组Id
                reportAchieve.setMaintainerLeaderGroupId(paramGroup.getGroupId());
                // 门店维护人拓展经理所属岗位Id
                reportAchieve.setMaintainerLeaderPostId(paramPost.getPostId());
            } else// 拓展专员的场合
            {
                // 门店维护人工号
                reportAchieve.setMaintainerId(paramUser.getUserCode());
                // 门店维护人姓名
                reportAchieve.setMaintainer(paramUser.getUserName());
                // 门店维护人所属组（业绩归属团队四）
                reportAchieve.setPerformTeam4(paramGroup.getGroupName());
                // 门店维护人所属岗位
                reportAchieve.setPostTypeName4(paramPost.getPostName());
                // 门店维护人所属组Id
                reportAchieve.setMaintainerGroupId(paramGroup.getGroupId());
                // 门店维护人所属岗位Id
                reportAchieve.setMaintainerPostId(paramPost.getPostId());
            }

            // 所属组为【拓展事业部】的场合
        } else if (paramGroup.getTypeId() == 8) {
            // 区域总监工号
            reportAchieve.setRegionalDirectorId(paramUser.getUserCode());
            // 区域总监姓名
            reportAchieve.setRegionalDirector(paramUser.getUserName());
            // 区域总监所属组
            reportAchieve.setPerformTeam2(paramGroup.getGroupName());
            // 区域总监所属岗位
            reportAchieve.setPostTypeName2(paramPost.getPostName());
            // 区域总监所属组Id
            reportAchieve.setRegionalDirectorGroupId(paramGroup.getGroupId());
            // 区域总监所属岗位Id
            reportAchieve.setRegionalDirectorPostId(paramPost.getPostId());

            // 所属组为【事业部】的场合
        } else if (paramGroup.getTypeId() == 7) {
            // 事业部总经理工号
            reportAchieve.setBusinessManagerId(paramUser.getUserCode());
            // 事业部总经理姓名
            reportAchieve.setBusinessManager(paramUser.getUserName());
            // 事业部总经理所属组
            reportAchieve.setPerformTeam1(paramGroup.getGroupName());
            // 事业部总经理所属岗位
            reportAchieve.setPostTypeName1(paramPost.getPostName());
            // 事业部总经理所属组Id
            reportAchieve.setBusinessManagerGroupId(paramGroup.getGroupId());
            // 事业部总经理所属岗位Id
            reportAchieve.setBusinessManagerPostId(paramPost.getPostId());
        }
    }


    /**
     * 设置业绩归属门店维护人员（上级）信息
     *
     * @param userId
     * @param reportAchieve
     */
    private void setLeaderInfo(Integer userId, ReportAchievement reportAchieve)
            throws Exception {
        // 1.查找上级领导用户信息
        User leadershipUser = userMapper.getUserByUserId(userId);

        // ------------------- 可能多岗位的场合 start -------------- //
        // 多岗的场合
        List<Post> leadershipPostList = null;
        // 2.查询门店维护人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
        leadershipPostList = postMapper.getExpandPostByUserId(leadershipUser.getUserId());
        Group leadershipGroup = new Group();
        if (leadershipPostList != null) {
            // 多岗的场合
            for (Post leadershipPost : leadershipPostList) {
                String leadershipPostGroupId = null;
                if (leadershipPost.getGroupId() != null) {
                    leadershipPostGroupId = String.valueOf(leadershipPost.getGroupId());
                }
                String leadershipUserGgroupId = null;
                if (leadershipUser.getGroupId() != null) {
                    leadershipUserGgroupId = String.valueOf(leadershipUser.getGroupId());
                }

                // 当前岗对应的组
                if (leadershipPostGroupId.equals(leadershipUserGgroupId)) {
                    // 3.查询所属组信息
                    leadershipGroup = groupMapper.selectByPrimaryKey(leadershipPost.getGroupId());
                    // 设置当前业绩归属门店维护人员信息
                    this.setRelatePersonInfo(leadershipUser, leadershipGroup, leadershipPost, reportAchieve);
                }
            }
        }
        // ------------------- 可能多岗位的场合 end -------------- //
    }

    /**
     * 设置业绩归属团队信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     *
     * @param paramGroup    组信息
     * @param reportAchieve 业绩关联人员信息
     * @throws Exception
     */
    private void setPerformTeamInfo(Group paramGroup, ReportAchievement reportAchieve)
            throws Exception {
        // 门店维护人所属组为【拓展组】的场合（门店维护人和拓展经理都属于拓展组）
        if (paramGroup.getTypeId() == 4) {
            // 拓展专员和拓展经理的场合
            // 拓展经理所属组 (业绩归属团队三)
            reportAchieve.setPerformTeam3(paramGroup.getGroupName());
            // 拓展经理所属组Id
            reportAchieve.setMaintainerLeaderGroupId(paramGroup.getGroupId());
            // 业绩拓展专员所属组（业绩归属团队四）
            reportAchieve.setPerformTeam4(paramGroup.getGroupName());
            // 拓展专员所属组Id
            reportAchieve.setMaintainerGroupId(paramGroup.getGroupId());

            // 所属组为【拓展事业部】的场合
        } else if (paramGroup.getTypeId() == 8) {
            // 区域总监所属组 (业绩归属团队二)
            reportAchieve.setPerformTeam2(paramGroup.getGroupName());
            // 区域总监所属组Id
            reportAchieve.setRegionalDirectorGroupId(paramGroup.getGroupId());

            // 所属组为【事业部】的场合
        } else if (paramGroup.getTypeId() == 7) {
            // 事业部总经理所属组 (业绩归属团队一)
            reportAchieve.setPerformTeam1(paramGroup.getGroupName());
            // 事业部总经理所属组Id
            reportAchieve.setBusinessManagerGroupId(paramGroup.getGroupId());
        }
    }

    private String getStrByCode(String code) {
        String value = "";
        if (StringUtil.isNotEmpty(code)) {
            String[] typeArr = code.split(",");
            for (String type : typeArr) {
                if (StringUtil.isEmpty(value)) {
                    value = SystemParam.getDicValueByDicCode(type);
                } else {
                    value = value + "," + SystemParam.getDicValueByDicCode(type);
                }
            }
        }
        return value;
    }

    /**
     * 报备登记(pc用)
     *
     * @param param
     * @return
     */
    @SuppressWarnings("unchecked")
    public int saveReport2(Map<?, ?> param) throws Exception {
        int cnt = 0;
        Map<String, Object> reMap = (Map<String, Object>) param;

        List<Estate> estateInfo = estateMapper.selectEstateInfo((String) reMap.get("estateId"));
        List<EstateRule> estateRuleInfo = estateRuleMapper.selectEstateRuleInfo((String) reMap.get("estateId"));
        reMap.put("estateType", estateInfo.get(0).getMgtKbn());
        reMap.put("latestProgress", 13502);
        reMap.put("confirmStatus", 13603);
        if (reMap.get("reportDate") != null && !"".equals(reMap.get("reportDate"))) {
            reMap.put("reportDate",
                    getFormatStringDate((String) reMap.get("reportDate"), "yyyy-MM-dd HH:mm:ss"));
        } else {
            reMap.put("reportDate", new Date());
        }
        reMap.put("followDate", new Date());

        Calendar addDate = Calendar.getInstance();
        addDate.setTime(new Date());
        if (estateRuleInfo != null && estateRuleInfo.size() > 0) {
            addDate.add(Calendar.DAY_OF_YEAR, estateRuleInfo.get(0).getRelationProtectPeriod().intValue());
        }
        Date validDate = addDate.getTime();
        reMap.put("validDate", validDate);

        reMap.put("crtDt", new Date());
        reMap.put("delFlg", false);

        if (reMap.get("validRelationDate") != null && !"".equals(reMap.get("validRelationDate"))) {
            reMap.put("validRelationDate",
                    getFormatStringDate((String) reMap.get("validRelationDate"), "yyyy-MM-dd HH:mm:ss"));
        }
        reMap.put("crtEmpId", param.get("createId"));

        String cityNo = null;
        String accountProject = null;
        String accountProjectNo = null;
        // 根据楼盘Id 查找城市
        String estateId = reMap.get("estateId").toString();
        List<Estate> estateList = estateMapper.selectEstateInfo(estateId);
        if (null != estateList && !estateList.isEmpty()) {
            Estate estate = estateList.get(0);
            cityNo = estate.getCityNo();
            accountProject = estate.getAccountProject();
            accountProjectNo = estate.getAccountProjectNo();
        }
        reMap.put("cityNo", cityNo);

        if (null != reMap.get("storeId")) {
            String storeId = reMap.get("storeId").toString();
            Integer storeIdInteger = null;
            if (StringUtil.isNotEmpty(storeId)) {
                storeIdInteger = Integer.valueOf(storeId);
                String contractType = contractStoreMapper.getTopContractTypeByStore(Integer.parseInt(storeId));
                reMap.put("contractType", contractType);
            }
        }
        // 门店维护人
        String maintainer = null;
        Integer centerId = null;
        if (null != reMap.get("contactId")) {
            maintainer = reMap.get("contactId").toString();
            // 业绩归属人所属中心
            centerId = Integer.valueOf(reMap.get("centerGroupId").toString());
            //Integer centerId = groupMapper.getCenterByUser(maintainer,SystemParam.getWebConfigValue("centerConfig"));
            reMap.put("centerId", centerId);
        }
        // 同步生成报备编号，创建报备
        //cnt = reportNewestReportId(reMap);
        reMap.put("reportId", seqNoAPI.getSeqNoByTypeCode("TYPE_REPORTNO").getReturnData());
        cnt = reportMapper.createReport(reMap);

        //附件信息
        if (param.get("fileList") != null && cnt > 0) {
            List<FileRecordMainDto> fileList = (List<FileRecordMainDto>) param.get("fileList");
            for (FileRecordMainDto fileRecordMainDto : fileList) {
                FileRecordMain file = new FileRecordMain();
                fileRecordMainDto.setRefId(Integer.valueOf(reMap.get("id").toString()));
                BeanUtils.copyProperties(fileRecordMainDto, file);
                fileRecordMainService.update(file);
            }
        }
        if (param.get("fangyouFileList") != null && cnt > 0) {
            List<String> fileIdList = (List<String>) param.get("fangyouFileList");
            for (String url : fileIdList) {
                FangyouReportFile fangyouFile = new FangyouReportFile();
                fangyouFile.setDateCreate(new Date());
                fangyouFile.setDelFlag("N");
                fangyouFile.setReportId(Integer.valueOf(reMap.get("id").toString()));
                fangyouFile.setReportNo(reMap.get("reportId").toString());
                fangyouFile.setTypeId("1");
                fangyouFile.setUrl(url);
                fangyouFile.setUserIdCreate(null);
                fangyouReportFileMapper.insertSelective(fangyouFile);
            }
        }

        String reportId = reMap.get("reportId").toString();
        reMap.put("countId", reportId + "-01");
        reMap.put("reportNo", reportId);
        reMap.put("progress", 13501);
        reMap.put("confirmStatus", 13601);
        reMap.put("followDate", new Date());
        reMap.put("reportDate", param.get("reportDate"));
        reMap.put("recognitionDay", new Date());
        reMap.put("accountFlg", "");
        reMap.put("accountStatus", "");
        reMap.put("commissionAccountStatus", "");

        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getRelationReward() != null) {
            reMap.put("relationReward", estateRuleInfo.get(0).getRelationReward());
        } else {
            reMap.put("relationReward", 0);
        }
        reMap.put("accountRelationReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getPledgedReward() != null) {
            reMap.put("pledgedReward", estateRuleInfo.get(0).getPledgedReward());
        } else {
            reMap.put("pledgedReward", 0);
        }
        reMap.put("accountPledgedReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getSubscribedReward() != null) {
            reMap.put("subscribedReward", estateRuleInfo.get(0).getSubscribedReward());
        } else {
            reMap.put("subscribedReward", 0);
        }
        reMap.put("accountSubscribedReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getBargainReward() != null) {
            reMap.put("bargainReward", estateRuleInfo.get(0).getBargainReward());
        } else {
            reMap.put("bargainReward", 0);
        }
        reMap.put("accountBargainReward", 0);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getCommissionSort() != null) {
            reMap.put("commission", estateRuleInfo.get(0).getCommissionSort());
        } else {
            reMap.put("commission", 0);
        }
        reMap.put("accountCommission", 0);
        reMap.put("recognitionDay", new Date());
        reMap.put("crtDt", new Date());
        reMap.put("delFlg", false);
        reMap.put("uptEmpId", param.get("createId"));
        // 创建报备确认
        cnt = reportDetailMapper.createReportDetail(reMap);

        // 创建带看未确认
        reMap.put("countId", reportId + "-02");
        reMap.put("progress", 13502);
        reMap.put("confirmStatus", 13603);
        reMap.put("uptEmpId", param.get("createId"));
        cnt = reportDetailMapper.createReportDetail(reMap);
        Boolean boo = true;
        // 门店的城市编号
        String storeCityNo = (null == reMap.get("storeCityNo")) ? "" : reMap.get("storeCityNo").toString();
        //创建报备业绩记录
        boo = this.createReportAchievement2(reportId, maintainer, storeCityNo, centerId, accountProject, accountProjectNo);
        if (!boo) {
            logger.error("APIHouseLinkageService", "saveReport2", "createReportAchievement2", null, null, null, "创建报备记录失败！reportId：" + reportId + "可能是因为没有门店维护人", null);
        }
        //更新分销合同编号
        cooperationService.updateReportCooperation(reportId);


        Map<String,Object> estateCenterMap = new HashMap<>();
        estateCenterMap.put("reportId",reportId);
        estateCenterMap.put("estateCenterId",reMap.get("estateCenterId"));
        reportMapper.updateEstateCenter(estateCenterMap);
        return cnt;
    }

    /**
     * PC创建报备业绩记录
     *
     * @param reportNo
     * @param storeId
     * @throws Exception
     */
    private Boolean createReportAchievement2(String reportNo, String maintainer, String storeCityNo, Integer centerId, String accountProject, String accountProjectNo)
            throws Exception {
        Boolean boo = true;
        //报备Id
        Integer reportId = null;
        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("reportId", reportNo);
        //查询到报备Id
        List<Report> reportlist = reportMapper.getReport(reqMap);
        if (reportlist != null && reportlist.size() > 0
                && StringUtils.isNotBlank(String.valueOf(reportlist.get(0).getId()))) {
            reportId = reportlist.get(0).getId();
        } else {
            boo = false;
        }

        //查询门店维护人相关领导和职位所属组等信息
        if (boo && null != maintainer) {
            boo = achievementService.saveAchievementInfo2(maintainer, reportId, "LinkCreate", "Link", DictionaryConstants.ACHIEVETYPE_LINK, centerId, accountProject, accountProjectNo);
        }
        return boo;
    }
}
