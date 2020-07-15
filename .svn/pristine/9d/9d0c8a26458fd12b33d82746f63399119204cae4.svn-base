package cn.com.eju.deal.houseLinkage.totalPackage.service;

import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.houseLinkage.report.ReportDto;
import cn.com.eju.deal.dto.scene.commission.ImportDto;
import cn.com.eju.deal.dto.scene.commission.ReportdyDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.totalPackage.dao.TotalPackageDataMapper;
import cn.com.eju.deal.houseLinkage.totalPackage.dao.TotalPackageLogMapper;
import cn.com.eju.deal.houseLinkage.totalPackage.dao.TotalPackageReportMapper;
import cn.com.eju.deal.houseLinkage.totalPackage.dao.TotalPackageSettingMapper;
import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageData;
import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageLog;
import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageSetting;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by haidan on 2018/11/27.
 */
@Service("totalPackageService")
public class TotalPackageService {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    TotalPackageReportMapper totalPackageReportMapper;

    @Resource
    TotalPackageLogMapper totalPackageLogMapper;

    @Resource
    ReportDetailMapper reportDetailMapper;

    @Resource
    EstateMapper estateMapper;

    @Resource
    private ContractStoreMapper contractStoreMapper;

    @Resource
    private ReportMapper reportMapper;

    @Resource(name = "achievementService")
    private AchievementService achievementService;
    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Resource
    private TotalPackageSettingMapper totalPackageSettingMapper;

    @Resource
    private TotalPackageDataMapper totalPackageDataMapper;

    public ResultData<?> handleTotalPackage() throws Exception {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        //获取待处理数据
     /*   List<TotalPackageReport> dataLst = totalPackageReportMapper.getTotalPackageReportListForHandle();
        if (null == dataLst || dataLst.isEmpty()) {
            resultData.setFail("没有待处理的数据!");
            return resultData;
        }
        for (TotalPackageReport data : dataLst) {
            //1.报备--报备详情
            //this.handleBB(data);
        }*/
        return resultData;
    }

    //处理报备
    /*private void handleBB(TotalPackageReport totalPackageReport) throws Exception {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("buildingNo", totalPackageReport.getBuildingNo());
        queryParam.put("projectNo", totalPackageReport.getProjectNo());
        Estate estate = estateMapper.getByProjectNo(totalPackageReport.getProjectNo());
        if (estate == null) return;
        TotalPackageSetting totalPackageSetting = totalPackageSettingMapper.getTotalPackageSetting(totalPackageReport.getProjectNo());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String oaPastTime = dateFormat.format(totalPackageReport.getOaPastTrialDate());
        Date oaPastDate = dateFormat.parse(oaPastTime);

        //1.Report表
        Report report = new Report();
        report.setOrderNumber(totalPackageReport.getOrderNumber());
        report.setProjectNo(totalPackageReport.getProjectNo());
        report.setEstateId(estate.getEstateId());
        report.setEstateNm(estate.getEstateNm());
        report.setCompanyNm(totalPackageSetting.getCompanyNm());
        report.setCompanyId(String.valueOf(totalPackageSetting.getCompanyId()));
        report.setCustomerNm(totalPackageSetting.getCustomerNm());
        report.setCustomerTel(totalPackageSetting.getCustomerTel());
        String storeId = String.valueOf(totalPackageSetting.getStoreId());
        report.setStoreId(storeId);
        report.setStoreNm(totalPackageSetting.getStoreNm());
        report.setReportDate(totalPackageReport.getSalesDate());
        report.setSeeDate(totalPackageReport.getSalesDate());
        report.setRoughInputDate(oaPastDate);
        report.setRoughDate(oaPastDate);
        report.setDealDate(oaPastDate);
        report.setEmpId("888888");
        report.setEmpNm("admin");
        report.setCityNo(estate.getCityNo());
        report.setValidRelationDate(totalPackageReport.getSalesDate());
        report.setContactId("888888");
        report.setContactNm("admin");
        Integer centerId = totalPackageSetting.getCenterId();
        report.setCenterId(centerId);
        report.setEstateType(estate.getMgtKbn());
        report.setLatestProgress(13505);
        report.setConfirmStatus(13601);
        report.setFollowDate(totalPackageReport.getSalesDate());
        List<EstateRule> estateRuleInfo = estateRuleMapper.selectEstateRuleInfo(estate.getEstateId());
        Calendar addDate = Calendar.getInstance();
        addDate.setTime(new Date());
        if (estateRuleInfo != null && estateRuleInfo.size() > 0) {
            addDate.add(Calendar.DAY_OF_YEAR, estateRuleInfo.get(0).getRelationProtectPeriod().intValue());
        }
        Date validDate = addDate.getTime();
        report.setValidDate(validDate);
        report.setCrtEmpId(2221);
        report.setCrtDt(new Date());
        report.setDelFlg(false);

        if (StringUtil.isNotEmpty(storeId)) {
            String contractType = contractStoreMapper.getTopContractTypeByStore(Integer.parseInt(storeId));
            if (StringUtil.isNotEmpty(contractType)) {
                report.setContractType(Integer.valueOf(contractType));
            }
        }

        String reportId = seqNoAPI.getSeqNoByTypeCode("TYPE_REPORTNO").getReturnData();
//        String tempId = "BB" + getFormatDateString(new Date(), "yyyyMMdd");
//        String reportId = tempId;
//        List<Report> maxReportList = null;
//        maxReportList = reportMapper.getMaxReportId(tempId);
//        if (maxReportList != null && maxReportList.size() > 0 && StringUtil.isNotEmpty(maxReportList.get(0).getReportId())) {
//            tempId = maxReportList.get(0).getReportId();
//            reportId = reportId + getNextNo(tempId.substring(tempId.length() - 5));
//        } else {
//            reportId = reportId + "00001";
//        }
        report.setReportId(reportId);
        report.setCustomerId(reportId.replace("BB", "CUS"));
        report.setRoughAuditStatus("1");
        report.setCustomerFrom(17401);
        report.setRoughAuditId(Long.valueOf(2221));
        report.setRoughAuditTime(oaPastDate);
        reportMapper.create(report);

        //2.ReportDetail表
        ReportDetail reportDetail = new ReportDetail();
        reportDetail.setEstateId(estate.getEstateId());
        reportDetail.setEstateNm(estate.getEstateNm());
        reportDetail.setCompanyNm(totalPackageSetting.getCompanyNm());
        reportDetail.setCompanyId(String.valueOf(totalPackageSetting.getCompanyId()));
        reportDetail.setCustomerId(reportId.replace("BB", "CUS"));
        reportDetail.setCustomerNm(totalPackageSetting.getCustomerNm());
        reportDetail.setCustomerTel(totalPackageSetting.getCustomerTel());
        reportDetail.setEmpNm("admin");
        reportDetail.setReportDate(totalPackageReport.getSalesDate());
        reportDetail.setSeeDate(totalPackageReport.getSalesDate());
        reportDetail.setPledgedDate(totalPackageReport.getSalesDate());
        reportDetail.setRoughInputDate(oaPastDate);
        reportDetail.setRoughDate(oaPastDate);
        reportDetail.setDealDate(oaPastDate);
        reportDetail.setFollowDate(totalPackageReport.getSalesDate());
        reportDetail.setCrtEmpId(2221);
        reportDetail.setCrtDt(new Date());
        reportDetail.setDelFlg(false);
        reportDetail.setCustomerFrom(17401);

        reportDetail.setRecognitionDay(totalPackageReport.getSalesDate());


        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getRelationReward() != null) {
            reportDetail.setRelationReward(estateRuleInfo.get(0).getRelationReward());
        } else {
            reportDetail.setRelationReward(BigDecimal.ZERO);
        }
        reportDetail.setAccountRelationReward(BigDecimal.ZERO);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getPledgedReward() != null) {
            reportDetail.setPledgedReward(estateRuleInfo.get(0).getPledgedReward());
        } else {
            reportDetail.setPledgedReward(BigDecimal.ZERO);
        }
        reportDetail.setAccountPledgedReward(BigDecimal.ZERO);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getSubscribedReward() != null) {
            reportDetail.setSubscribedReward(estateRuleInfo.get(0).getSubscribedReward());
        } else {
            reportDetail.setSubscribedReward(BigDecimal.ZERO);
        }
        reportDetail.setAccountSubscribedReward(BigDecimal.ZERO);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getBargainReward() != null) {
            reportDetail.setBargainReward(estateRuleInfo.get(0).getBargainReward());
        } else {
            reportDetail.setBargainReward(BigDecimal.ZERO);
        }
        reportDetail.setAccountBargainReward(BigDecimal.ZERO);
        if (estateRuleInfo != null && estateRuleInfo.size() > 0 && estateRuleInfo.get(0).getCommissionSort() != null) {
            reportDetail.setCommission(estateRuleInfo.get(0).getCommissionSort());
        } else {
            reportDetail.setCommission(BigDecimal.ZERO);
        }
        reportDetail.setAccountCommission(BigDecimal.ZERO);
        reportDetail.setCrtDt(new Date());
        reportDetail.setDelFlg(false);

        //a.报备 有效 13501 13601
        reportDetail.setCountId(reportId + "-01");
        reportDetail.setProgress(13501);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setAccountFlg(0);
        reportDetail.setAccountStatus(0);
        reportDetail.setCommissionAccountStatus(0);
        reportDetailMapper.create(reportDetail);

        //b.带看 有效 13502 13601
        reportDetail.setCountId(reportId + "-02");
        reportDetail.setProgress(13502);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(0);
        reportDetailMapper.create(reportDetail);

        //c.认筹 有效 13503 13601
        reportDetail.setCountId(reportId + "-03");
        reportDetail.setProgress(13503);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(14202);
        reportDetail.setBuildingNo(totalPackageReport.getBuildingNo());
        reportDetail.setRoughAmount(totalPackageReport.getSalesMoney());
        reportDetail.setRoughArea(BigDecimal.valueOf(100));
        reportDetailMapper.create(reportDetail);

        //d.大定 有效 13504 13601
        reportDetail.setCountId(reportId + "-04");
        reportDetail.setProgress(13504);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setRoughAmount(totalPackageReport.getSalesMoney());
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(14202);
        reportDetail.setRoughArea(BigDecimal.valueOf(100));
        reportDetailMapper.create(reportDetail);

        //e.成销 有效 13505 13601
        reportDetail.setCountId(reportId + "-05");
        reportDetail.setProgress(13505);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setRoughAmount(totalPackageReport.getSalesMoney());
        reportDetail.setDealAmount(totalPackageReport.getSalesMoney());
        reportDetail.setArea(BigDecimal.valueOf(100));
        reportDetail.setRoughArea(BigDecimal.valueOf(100));
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(14202);
        reportDetailMapper.create(reportDetail);
        Integer detailId = reportDetail.getId();
        String estateId = reportDetail.getEstateId();
        String estateNm = reportDetail.getEstateNm();

        //创建报备业绩记录
        Boolean boo = achievementService.saveAchievementInfo2("888888", report.getId(), "LinkCreate", "Link", DictionaryConstants.ACHIEVETYPE_LINK, centerId, estate.getAccountProject(), estate.getAccountProjectNo());
        if (!boo) {
            logger.error("TotalPackageService", "handleBB", "createReportAchievement2", null, null, null, "创建报备业绩记录！reportId：" + reportId, null);
        }

        totalPackageReport.setHasBBDeal(1);
        totalPackageReport.setDealBBDate(new Date());
        totalPackageReportMapper.update(totalPackageReport);
        //2.应计收入 amountType = 20701,templateType = 20601 总包服务费 taxIncome
        this.handleImport(reportId, estateId, estateNm, detailId, "20601", "20701", totalPackageReport.getOaPastTrialDate(), totalPackageReport.getPreTaxIncome(),
                totalPackageReport.getAfterTaxIncome(), totalPackageReport.getProjectNo(), totalPackageReport.getBuildingNo(), totalPackageReport.getSalesMoney());
        totalPackageReport.setHasIncomeDeal(1);
        totalPackageReport.setDealIncomeDate(new Date());
        totalPackageReportMapper.update(totalPackageReport);
        //3.应计返佣 amountType = 20702,templateType = 20601 联动服务费 taxTotalServiceFee
        this.handleImport(reportId, estateId, estateNm, detailId, "20601", "20702", totalPackageReport.getOaPastTrialDate(), totalPackageReport.getPreTaxTotalServiceFee(),
                totalPackageReport.getAfterTaxTotalServiceFee(), totalPackageReport.getProjectNo(), totalPackageReport.getBuildingNo(), totalPackageReport.getSalesMoney());
        totalPackageReport.setHasRakebackDeal(1);
        totalPackageReport.setDealRakebackDate(new Date());
        totalPackageReportMapper.update(totalPackageReport);
        //4.应计垫佣
        this.handleAccruedCommission(totalPackageReport, reportId);
        totalPackageReport.setHasPadDeal(1);
        totalPackageReport.setDealPadDate(new Date());
        totalPackageReportMapper.update(totalPackageReport);
    }*/

    /**
     * 应计收入(amountType = 20701,templateType = 20601)
     * 应计返佣(amountType = 20702,templateType = 20601)
     * 实际返佣(amountType = 20705,templateType = 20603)
     * 应收收入(amountType = 20706,templateType = 20606)
     *
     * @param reportId        报备编号
     * @param estateId        楼盘编号
     * @param estateNm        楼盘名称
     * @param detailId        报备详情编号
     * @param templateType
     * @param amountType
     * @param oaPastTrialDate oa过审日期
     * @param preFee          税前
     * @param afterFee        税后
     * @param projectNo       项目编号
     * @param buildingNo      楼室号
     * @param salesMoney      销售金额
     * @throws Exception
     */
    private void handleImport(String reportId, String estateId, String estateNm, Integer detailId, String templateType, String amountType,
                              Date oaPastTrialDate, BigDecimal preFee, BigDecimal afterFee, String projectNo, String buildingNo, BigDecimal salesMoney) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(oaPastTrialDate);
        Integer year = c.get(Calendar.YEAR);
        Integer month = c.get(Calendar.MONTH);//实际month要加1

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String oaPastTime = dateFormat.format(oaPastTrialDate);
        Date oaPastDate = dateFormat.parse(oaPastTime);

        List<ImportDto> dataLst = this.getLnkImportByMap(reportId, templateType, amountType, year, detailId);
        if (null != dataLst && dataLst.size() > 0) {//原来有记录的情况
            ImportDto importDto = dataLst.get(0);
            BigDecimal subTotalPreTax = importDto.getSubTotalPreTax() == null ? BigDecimal.ZERO : importDto.getSubTotalPreTax();
            BigDecimal subtotalAfterTax = importDto.getSubTotalAfterTax() == null ? BigDecimal.ZERO : importDto.getSubTotalAfterTax();
            subTotalPreTax = subTotalPreTax.add(preFee);
            subtotalAfterTax = subtotalAfterTax.add(afterFee);
            importDto.setSubTotalPreTax(subTotalPreTax);
            importDto.setSubTotalAfterTax(subtotalAfterTax);
            this.setMonthTax(importDto, month, preFee, afterFee);
            importDto.setUptDt(new Date());
            importDto.setUptEmpId(2221);
            totalPackageDataMapper.updateLnkImport(importDto);
        } else {//原来没有记录判断以前的数据有没有
            dataLst = this.getLnkImportByMap(reportId, templateType, amountType, null, detailId);
            if (null != dataLst && dataLst.size() > 0) {//有历史数据的情况
                ImportDto importOld = dataLst.get(0);
                ImportDto importDto = new ImportDto();
                importDto.setTemplateType(templateType);
                importDto.setAmountType(amountType);
                importDto.setYear(year);
                importDto.setProjectNo(projectNo);
                importDto.setEstateId(estateId);
                importDto.setEstateNm(estateNm);
                importDto.setReportId(reportId);
                importDto.setBuildingNo(buildingNo);
                importDto.setDealAmount(salesMoney);
                importDto.setDealDate(oaPastDate);
                BigDecimal beforeAmountPreTax = importOld.getBeforeAmountPreTax() == null ? BigDecimal.ZERO : importOld.getBeforeAmountPreTax();
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getJanPreTax() == null ? BigDecimal.ZERO : importOld.getJanPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getFebPreTax() == null ? BigDecimal.ZERO : importOld.getFebPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getMarPreTax() == null ? BigDecimal.ZERO : importOld.getMarPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getAprPreTax() == null ? BigDecimal.ZERO : importOld.getAprPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getMayPreTax() == null ? BigDecimal.ZERO : importOld.getMayPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getJunPreTax() == null ? BigDecimal.ZERO : importOld.getJunPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getJulPreTax() == null ? BigDecimal.ZERO : importOld.getJulPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getAugPreTax() == null ? BigDecimal.ZERO : importOld.getAugPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getSepPreTax() == null ? BigDecimal.ZERO : importOld.getSepPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getOctPreTax() == null ? BigDecimal.ZERO : importOld.getOctPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getNovPreTax() == null ? BigDecimal.ZERO : importOld.getNovPreTax());
                beforeAmountPreTax = beforeAmountPreTax.add(importOld.getDecPreTax() == null ? BigDecimal.ZERO : importOld.getDecPreTax());
                importDto.setBeforeAmountPreTax(beforeAmountPreTax);
                importDto.setSubTotalPreTax(beforeAmountPreTax.add(preFee));

                BigDecimal beforeAmountAfterTax = importOld.getBeforeAmountAfterTax() == null ? BigDecimal.ZERO : importOld.getBeforeAmountAfterTax();
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getJanAfterTax() == null ? BigDecimal.ZERO : importOld.getJanAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getFebAfterTax() == null ? BigDecimal.ZERO : importOld.getFebAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getMarAfterTax() == null ? BigDecimal.ZERO : importOld.getMarAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getAprAfterTax() == null ? BigDecimal.ZERO : importOld.getAprAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getMayAfterTax() == null ? BigDecimal.ZERO : importOld.getMayAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getJunAfterTax() == null ? BigDecimal.ZERO : importOld.getJunAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getJulAfterTax() == null ? BigDecimal.ZERO : importOld.getJulAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getAugAfterTax() == null ? BigDecimal.ZERO : importOld.getAugAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getSepAfterTax() == null ? BigDecimal.ZERO : importOld.getSepAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getOctAfterTax() == null ? BigDecimal.ZERO : importOld.getOctAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getNovAfterTax() == null ? BigDecimal.ZERO : importOld.getNovAfterTax());
                beforeAmountAfterTax = beforeAmountAfterTax.add(importOld.getDecAfterTax() == null ? BigDecimal.ZERO : importOld.getDecAfterTax());
                importDto.setBeforeAmountAfterTax(beforeAmountAfterTax);
                importDto.setSubTotalAfterTax(beforeAmountAfterTax.add(afterFee));

                this.setMonthTax(importDto, month, preFee, afterFee);
                importDto.setCrtEmpId(2221);
                importDto.setNum(1);
                importDto.setDetailId(detailId);
                importDto.setUptEmpId(2221);
                totalPackageDataMapper.createLnkImport(importDto);

            } else {//没有历史记录的Insert
                ImportDto importDto = new ImportDto();
                importDto.setTemplateType(templateType);
                importDto.setAmountType(amountType);
                importDto.setYear(year);
                importDto.setProjectNo(projectNo);
                importDto.setEstateId(estateId);
                importDto.setEstateNm(estateNm);
                importDto.setReportId(reportId);
                importDto.setBuildingNo(buildingNo);
                importDto.setDealAmount(salesMoney);
                importDto.setDealDate(oaPastDate);
                importDto.setSubTotalPreTax(preFee);
                importDto.setSubTotalAfterTax(afterFee);
                this.setMonthTax(importDto, month, preFee, afterFee);
                importDto.setCrtEmpId(2221);
                importDto.setNum(1);
                importDto.setDetailId(detailId);
                importDto.setUptEmpId(2221);
                totalPackageDataMapper.createLnkImport(importDto);
            }
        }
    }

    private List<ImportDto> getLnkImportByMap(String reportId, String templateType, String amountType, Integer year, Integer detailId) {
        Map<String, Object> param = new HashMap<>();
        param.put("reportId", reportId);
        param.put("templateType", templateType);
        param.put("amountType", amountType);
        param.put("detailId", detailId);
        if (year != null) {
            param.put("year", year);
        }
        return totalPackageDataMapper.getLnkImportByMap(param);
    }

    private void setMonthTax(ImportDto importDto, Integer month, BigDecimal preTax, BigDecimal afterTax) {
        switch (month) {
            case 0:
                if (null != importDto.getJanPreTax()) {
                    preTax = preTax.add(importDto.getJanPreTax());
                }
                if (null != importDto.getJanAfterTax()) {
                    afterTax = afterTax.add(importDto.getJanAfterTax());
                }
                importDto.setJanPreTax(preTax);
                importDto.setJanAfterTax(afterTax);
                break;
            case 1:
                if (null != importDto.getFebPreTax()) {
                    preTax = preTax.add(importDto.getFebPreTax());
                }
                if (null != importDto.getFebAfterTax()) {
                    afterTax = afterTax.add(importDto.getFebAfterTax());
                }
                importDto.setFebPreTax(preTax);
                importDto.setFebAfterTax(afterTax);
                break;
            case 2:
                if (null != importDto.getMarPreTax()) {
                    preTax = preTax.add(importDto.getMarPreTax());
                }
                if (null != importDto.getMarAfterTax()) {
                    afterTax = afterTax.add(importDto.getMarAfterTax());
                }
                importDto.setMarPreTax(preTax);
                importDto.setMarAfterTax(afterTax);
                break;
            case 3:
                if (null != importDto.getAprPreTax()) {
                    preTax = preTax.add(importDto.getAprPreTax());
                }
                if (null != importDto.getAprAfterTax()) {
                    afterTax = afterTax.add(importDto.getAprAfterTax());
                }
                importDto.setAprPreTax(preTax);
                importDto.setAprAfterTax(afterTax);
                break;
            case 4:
                if (null != importDto.getMayPreTax()) {
                    preTax = preTax.add(importDto.getMayPreTax());
                }
                if (null != importDto.getMayAfterTax()) {
                    afterTax = afterTax.add(importDto.getMayAfterTax());
                }
                importDto.setMayPreTax(preTax);
                importDto.setMayAfterTax(afterTax);
                break;
            case 5:
                if (null != importDto.getJunPreTax()) {
                    preTax = preTax.add(importDto.getJunPreTax());
                }
                if (null != importDto.getJunAfterTax()) {
                    afterTax = afterTax.add(importDto.getJunAfterTax());
                }
                importDto.setJunPreTax(preTax);
                importDto.setJunAfterTax(afterTax);
                break;
            case 6:
                if (null != importDto.getJulPreTax()) {
                    preTax = preTax.add(importDto.getJulPreTax());
                }
                if (null != importDto.getJulAfterTax()) {
                    afterTax = afterTax.add(importDto.getJulAfterTax());
                }
                importDto.setJulPreTax(preTax);
                importDto.setJulAfterTax(afterTax);
                break;
            case 7:
                if (null != importDto.getAugPreTax()) {
                    preTax = preTax.add(importDto.getAugPreTax());
                }
                if (null != importDto.getAugAfterTax()) {
                    afterTax = afterTax.add(importDto.getAugAfterTax());
                }
                importDto.setAugPreTax(preTax);
                importDto.setAugAfterTax(afterTax);
                break;
            case 8:
                if (null != importDto.getSepPreTax()) {
                    preTax = preTax.add(importDto.getSepPreTax());
                }
                if (null != importDto.getSepAfterTax()) {
                    afterTax = afterTax.add(importDto.getSepAfterTax());
                }
                importDto.setSepPreTax(preTax);
                importDto.setSepAfterTax(afterTax);
                break;
            case 9:
                if (null != importDto.getOctPreTax()) {
                    preTax = preTax.add(importDto.getOctPreTax());
                }
                if (null != importDto.getOctAfterTax()) {
                    afterTax = afterTax.add(importDto.getOctAfterTax());
                }
                importDto.setOctPreTax(preTax);
                importDto.setOctAfterTax(afterTax);
                break;
            case 10:
                if (null != importDto.getNovPreTax()) {
                    preTax = preTax.add(importDto.getNovPreTax());
                }
                if (null != importDto.getNovAfterTax()) {
                    afterTax = afterTax.add(importDto.getNovAfterTax());
                }
                importDto.setNovPreTax(preTax);
                importDto.setNovAfterTax(afterTax);
                break;
            case 11:
                if (null != importDto.getDecPreTax()) {
                    preTax = preTax.add(importDto.getDecPreTax());
                }
                if (null != importDto.getDecAfterTax()) {
                    afterTax = afterTax.add(importDto.getDecAfterTax());
                }
                importDto.setDecPreTax(preTax);
                importDto.setDecAfterTax(afterTax);
                break;
        }
    }

    //处理应计垫佣type=1和实际垫佣type=2
    private void handleReportDy(TotalPackageData totalPackageData, String reportId, Integer type) throws Exception {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("reportId", reportId);
        queryParam.put("recordDate", totalPackageData.getOaPastTrialDate());
        List<ReportdyDto> dyLst = totalPackageReportMapper.getReportdy(queryParam);

        ReportdyDto reportdy = new ReportdyDto();
        if (dyLst != null && dyLst.size() > 0) {//如果原来有记录
            reportdy = dyLst.get(0);
            if (type == 1) {
                BigDecimal befTaxYjAmount = reportdy.getBefTaxYjAmount() == null ? BigDecimal.ZERO : reportdy.getBefTaxYjAmount();
                BigDecimal aftTaxYjAmount = reportdy.getAftTaxYjAmount() == null ? BigDecimal.ZERO : reportdy.getAftTaxYjAmount();
                befTaxYjAmount = befTaxYjAmount.add(totalPackageData.getPreTaxTotalServiceFee());
                aftTaxYjAmount = aftTaxYjAmount.add(totalPackageData.getAfterTaxTotalServiceFee());
                reportdy.setBefTaxYjAmount(befTaxYjAmount);
                reportdy.setAftTaxYjAmount(aftTaxYjAmount);
            } else if (type == 2) {
                BigDecimal befTaxSjAmount = reportdy.getBefTaxSjAmount() == null ? BigDecimal.ZERO : reportdy.getBefTaxSjAmount();
                BigDecimal aftTaxSjAmount = reportdy.getAftTaxSjAmount() == null ? BigDecimal.ZERO : reportdy.getAftTaxSjAmount();
                befTaxSjAmount = befTaxSjAmount.add(totalPackageData.getPreTaxTotalServiceFee());
                aftTaxSjAmount = aftTaxSjAmount.add(totalPackageData.getAfterTaxTotalServiceFee());
                reportdy.setBefTaxSjAmount(befTaxSjAmount);
                reportdy.setAftTaxSjAmount(aftTaxSjAmount);
            }
            totalPackageReportMapper.updateReportdy(reportdy);
        } else {//如果原来没有记录
            if (type == 1) {
                reportdy.setAftTaxYjAmount(totalPackageData.getAfterTaxTotalServiceFee());
                reportdy.setBefTaxYjAmount(totalPackageData.getPreTaxTotalServiceFee());
                reportdy.setRecordDate(totalPackageData.getOaPastTrialDate());
                reportdy.setReportId(reportId);
            } else if (type == 2) {
                reportdy.setAftTaxSjAmount(totalPackageData.getAfterTaxTotalServiceFee());
                reportdy.setBefTaxSjAmount(totalPackageData.getPreTaxTotalServiceFee());
                reportdy.setRecordDate(totalPackageData.getOaPastTrialDate());
                reportdy.setReportId(reportId);
            }
            totalPackageReportMapper.createReportdy(reportdy);
        }
    }

    //处理应计垫佣
    private void handleAccruedCommission(TotalPackageData totalPackageData, String reportId) throws Exception {
        List<ReportdyDto> dyLst = this.getDyList(reportId, totalPackageData.getOaPastTrialDate(), 1);
        if (dyLst != null && dyLst.size() > 0) {//如果原来有记录
            ReportdyDto reportdy = dyLst.get(0);
            BigDecimal befTaxYjAmount = reportdy.getBefTaxYjAmount();
            BigDecimal aftTaxYjAmount = reportdy.getAftTaxYjAmount();
            befTaxYjAmount = befTaxYjAmount.add(totalPackageData.getPreTaxTotalServiceFee());
            aftTaxYjAmount = aftTaxYjAmount.add(totalPackageData.getAfterTaxTotalServiceFee());
            reportdy.setBefTaxYjAmount(befTaxYjAmount);
            reportdy.setAftTaxYjAmount(aftTaxYjAmount);
            totalPackageReportMapper.updateReportdy(reportdy);
        } else {//如果原来没有记录
            ReportdyDto reportdy = new ReportdyDto();
            reportdy.setAftTaxYjAmount(totalPackageData.getAfterTaxTotalServiceFee());
            reportdy.setBefTaxYjAmount(totalPackageData.getPreTaxTotalServiceFee());
            reportdy.setRecordDate(totalPackageData.getOaPastTrialDate());
            reportdy.setReportId(reportId);
            totalPackageReportMapper.createReportdy(reportdy);
        }
    }

    //处理实际垫佣
    private void handleActualCommission(TotalPackageData totalPackageData, String reportId) throws Exception {
        List<ReportdyDto> dyLst = this.getDyList(reportId, totalPackageData.getOaPastTrialDate(), 2);
        if (dyLst != null && dyLst.size() > 0) {//如果原来有记录
            ReportdyDto reportdy = dyLst.get(0);
            BigDecimal befTaxSjAmount = reportdy.getBefTaxSjAmount();
            BigDecimal aftTaxSjAmount = reportdy.getAftTaxSjAmount();
            befTaxSjAmount = befTaxSjAmount.add(totalPackageData.getPreTaxTotalServiceFee());
            aftTaxSjAmount = aftTaxSjAmount.add(totalPackageData.getAfterTaxTotalServiceFee());
            reportdy.setBefTaxSjAmount(befTaxSjAmount);
            reportdy.setAftTaxSjAmount(aftTaxSjAmount);
            totalPackageReportMapper.updateReportdy(reportdy);
        } else {//如果原来没有记录
            ReportdyDto reportdy = new ReportdyDto();
            reportdy.setAftTaxSjAmount(totalPackageData.getAfterTaxTotalServiceFee());
            reportdy.setBefTaxSjAmount(totalPackageData.getPreTaxTotalServiceFee());
            reportdy.setRecordDate(totalPackageData.getOaPastTrialDate());
            reportdy.setReportId(reportId);
            totalPackageReportMapper.createReportdy(reportdy);
        }
    }

    private List<ReportdyDto> getDyList(String reportId, Date recordDate, Integer type) {
        Map<String, Object> queryParam = new HashMap<>();
        queryParam.put("reportId", reportId);
        queryParam.put("recordDate", recordDate);
        queryParam.put("type", type);//1表示应计垫佣；2表示实际垫佣
        return totalPackageReportMapper.getReportdy(queryParam);
    }


    /**
     * 确认支付接口
     *
     * @param dataList
     * @param log
     * @return
     * @throws Exception
     */
    public ResultData<?> confirmPay(List<Map<String, Object>> dataList, TotalPackageLog log) throws Exception {
        ResultData<?> resultData = new ResultData<>();
        List<TotalPackageData> totalPackageDataList;
        try {
            totalPackageDataList = this.getDataFromMap(dataList);
        } catch (Exception e) {
            resultData.setFail("对象转换失败");
            return resultData;
        }
        if (totalPackageDataList != null && totalPackageDataList.size() > 0) {
            TotalPackageSetting totalPackageSetting = totalPackageSettingMapper.getTotalPackageSetting(totalPackageDataList.get(0).getProjectNo());
            Estate estate = estateMapper.getByProjectNo(totalPackageDataList.get(0).getProjectNo());
            if (estate == null) {
                resultData.setFail("没有找到对应的楼盘记录！");
                return resultData;
            }

            for (TotalPackageData totalPackageData : totalPackageDataList) {
                totalPackageDataMapper.create(totalPackageData);
                log.setReferId(totalPackageData.getId());
                log.setReferTable("LNK_TotalPackageData");

                String reportId;
                Integer detailId = null;
                String estateId = estate.getEstateId();
                String estateNm = estate.getEstateNm();

                Map<String, Object> queryParam = new HashMap<>();
                queryParam.put("estateId", estateId);
                queryParam.put("orderNumber", totalPackageData.getOrderNumber());
                queryParam.put("projectNo",totalPackageData.getProjectNo());
                //处理报备流程
                List<Report> rpts = reportMapper.getReport(queryParam);

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String oaPastTime = dateFormat.format(totalPackageData.getOaPastTrialDate());
                Date oaPastDate = dateFormat.parse(oaPastTime);

                Integer num = totalPackageData.getSalesNum();

                if (rpts == null || rpts.size() == 0) {//表中不存在报备则新增报备流程
                    if(num == -1){
                        resultData.setFail("没有找到对应的报备记录，无法补退房记录！");
                        return resultData;
                    }
                    //1.Report表
                    Report report = this.handleReport(totalPackageData, estate, totalPackageSetting, oaPastDate);
                    reportId = report.getReportId();

                    //2.ReportDetail表
                    detailId = this.handleReportDetail(totalPackageData, estate, totalPackageSetting, oaPastDate, reportId);

                    //3.创建报备业绩记录
                    Boolean boo = achievementService.saveAchievementInfo2("888888", report.getId(), "LinkCreate", "Link", DictionaryConstants.ACHIEVETYPE_LINK, report.getCenterId(), estate.getAccountProject(), estate.getAccountProjectNo());
                    if (!boo) {
                        logger.error("TotalPackageService", "handleBB", "createReportAchievement2", null, null, null, "创建报备业绩记录！reportId：" + reportId, null);
                    }

                    //4.LNK_YJ_REPORT返佣对象表加记录
                    ReportDto reportDto = new ReportDto();
                    reportDto.setReportId(report.getReportId());
                    reportDto.setCompanyId(report.getCompanyId());
                    reportDto.setRoughAuditId(report.getRoughAuditId());
                    reportMapper.insertYjReport(reportDto);

                } else {//表中存在报备则直接取reportId
                    Report report = rpts.get(0);
                    reportId = report.getReportId();

                    if (num == -1) {//补退房记录
                        report.setDealBackDate(oaPastDate);
                        reportMapper.updateReback(report);

                        //reportDetail
                        ReportDetail tempReportDetail = new ReportDetail();
                        tempReportDetail.setEstateId(report.getEstateId());
                        tempReportDetail.setCountId(report.getReportId());
                        tempReportDetail.setProgress(13505);
                        ReportDetail copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
                        if (copyPledged == null) copyPledged = new ReportDetail();
                        tempReportDetail.setProgress(null);//清除
                        tempReportDetail.setDealBackDate(oaPastDate);
                        //更新退筹、退定、退房时间 和回退
                        tempReportDetail.setRoughDateClear("1");
                        reportDetailMapper.updateOperateDate(tempReportDetail);
                        reportDetailMapper.updateOtherStatus(tempReportDetail);

                        //补数据
                        String countId = reportDetailMapper.getMaxCountId(tempReportDetail);
                        String prefix = countId.substring(0, countId.indexOf("-"));
                        String suffix = countId.substring(countId.indexOf("-") + 1);
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
                        copyPledged.setReportNo(reportId);
                        copyPledged.setEstateId(estateId);
                        reportDetailMapper.create(copyPledged);
                    }

                    queryParam.put("projectNo",totalPackageData.getProjectNo());
                    List<ReportDetail> rptLst = reportDetailMapper.getTotalPackageReport(queryParam);
                    if (rptLst != null && rptLst.size() > 0) {
                        ReportDetail reportDetail = rptLst.get(0);
                        detailId = reportDetail.getId();
                    }

                    //LNK_YJ_REPORT返佣对象表加记录
                    ReportDto reportDto = new ReportDto();
                    reportDto.setReportId(report.getReportId());
                    reportDto.setCompanyId(report.getCompanyId());
                    reportDto.setRoughAuditId(report.getRoughAuditId());
                    reportMapper.insertYjReport(reportDto);
                }

                //应计收入:LNK_YJ_YJSR；应收收入:LNK_YJ_YSSR；应计实收:LNK_YJ_YJSS（还没）；应计返佣:LNK_YJ_YJFY；实际返佣:LNK_YJ_SJFY；应计垫佣:LNK_YJ_YJDY；实际垫佣:LNK_YJ_SJDY；
                Map<String, Object> yjMap = new HashMap<>();
                yjMap.put("reportId", reportId);
                yjMap.put("companyNo", totalPackageSetting.getCompanyId());
                yjMap.put("detailId", detailId);
                yjMap.put("num", 1);
                yjMap.put("recordDate", totalPackageData.getOaPastTrialDate());

                //应计收入:LNK_YJ_YJSR
                yjMap.put("befTaxAmount", totalPackageData.getPreTaxIncome());
                yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxIncome());
                totalPackageDataMapper.handleYJSR(yjMap);
                //应计收入 amountType = 20701,templateType = 20601 总包服务费 taxIncome
                this.handleImport(reportId, estateId, estateNm, detailId, "20601", "20701", totalPackageData.getOaPastTrialDate(), totalPackageData.getPreTaxIncome(),
                        totalPackageData.getAfterTaxIncome(), totalPackageData.getProjectNo(), totalPackageData.getBuildingNo(), totalPackageData.getSalesMoney());

                //应计返佣:LNK_YJ_YJFY
                yjMap.put("befTaxAmount", totalPackageData.getPreTaxTotalServiceFee());
                yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxTotalServiceFee());
                totalPackageDataMapper.handleYJFY(yjMap);
                //应计返佣 amountType = 20702,templateType = 20601 联动服务费 taxTotalServiceFee
                this.handleImport(reportId, estateId, estateNm, detailId, "20601", "20702", totalPackageData.getOaPastTrialDate(), totalPackageData.getPreTaxTotalServiceFee(),
                        totalPackageData.getAfterTaxTotalServiceFee(), totalPackageData.getProjectNo(), totalPackageData.getBuildingNo(), totalPackageData.getSalesMoney());

                //应计垫佣:LNK_YJ_YJDY
                yjMap.put("befTaxAmount", totalPackageData.getPreTaxTotalServiceFee());
                yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxTotalServiceFee());
                totalPackageDataMapper.handleYJDY(yjMap);
                //应计垫佣
                this.handleReportDy(totalPackageData, reportId, 1);
//                this.handleAccruedCommission(totalPackageData, reportId);

                //实际垫佣:LNK_YJ_SJDY
                yjMap.put("befTaxAmount", totalPackageData.getPreTaxTotalServiceFee());
                yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxTotalServiceFee());
                totalPackageDataMapper.handleSJDY(yjMap);
                //实际垫佣
                this.handleReportDy(totalPackageData, reportId, 2);
//                this.handleActualCommission(totalPackageData, reportId);
                totalPackageData.setHasPadDeal(1);
                totalPackageData.setDealPadDate(new Date());
                totalPackageDataMapper.update(totalPackageData);

                //实际返佣:LNK_YJ_SJFY
                yjMap.put("befTaxAmount", totalPackageData.getPreTaxTotalServiceFee());
                yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxTotalServiceFee());
                totalPackageDataMapper.handleSJFY(yjMap);
                //实际返佣 amountType = 20705,templateType = 20603
                this.handleImport(reportId, estateId, estateNm, detailId, "20603", "20705", totalPackageData.getOaPastTrialDate(), totalPackageData.getPreTaxTotalServiceFee(),
                        totalPackageData.getAfterTaxTotalServiceFee(), totalPackageData.getProjectNo(), totalPackageData.getBuildingNo(), totalPackageData.getSalesMoney());
                totalPackageData.setHasRakeback(1);
                totalPackageData.setRakebackDate(new Date());
                totalPackageDataMapper.update(totalPackageData);

                //应收收入 如果应收收入日期<=当前日期则处理 amountType = 20706,templateType = 20606
                if (totalPackageData.getReceivableDate() != null) {
                    if ((totalPackageData.getReceivableDate()).getTime() <= (new Date()).getTime()) {
                        //应收收入:LNK_YJ_YSSR
                        yjMap.put("befTaxAmount", totalPackageData.getPreTaxIncome());
                        yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxIncome());
                        yjMap.put("recordDate", totalPackageData.getReceivableDate());
                        totalPackageDataMapper.handleYSSR(yjMap);

                        this.handleImport(reportId, estateId, estateNm, detailId, "20606", "20706", totalPackageData.getReceivableDate(), totalPackageData.getPreTaxIncome(),
                                totalPackageData.getAfterTaxIncome(), totalPackageData.getProjectNo(), totalPackageData.getBuildingNo(), totalPackageData.getSalesMoney());
                        totalPackageData.setHasRevenues(1);
                        totalPackageData.setRevenuesDate(new Date());
                        totalPackageDataMapper.update(totalPackageData);
                    }
                }
            }
        }
        return resultData;
    }

    private List<TotalPackageData> getDataFromMap(List<Map<String, Object>> dataList) {
        List<TotalPackageData> totalPackageDataList = new ArrayList<>();
        for (Map<String, Object> data : dataList) {
            TotalPackageData totalPackageData = new TotalPackageData();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            totalPackageData.setOrderNumber((String) data.get("orderNumber"));
            Date receivableDate = formatter.parse((String) data.get("receivableDate"), new ParsePosition(0));
            totalPackageData.setReceivableDate(receivableDate);
            BigDecimal afterTaxIncome = new BigDecimal((String) data.get("afterTaxIncome"));
            totalPackageData.setAfterTaxIncome(afterTaxIncome);
            BigDecimal afterTaxTotalServiceFee = new BigDecimal((String) data.get("afterTaxTotalServiceFee"));
            totalPackageData.setAfterTaxTotalServiceFee(afterTaxTotalServiceFee);
            totalPackageData.setBuildingNo((String) data.get("buildingNo"));
            Date oaPastTrialDate = formatter.parse((String) data.get("oaPastTrialDate"), new ParsePosition(0));
            totalPackageData.setOaPastTrialDate(oaPastTrialDate);
            BigDecimal preTaxIncome = new BigDecimal((String) data.get("preTaxIncome"));
            totalPackageData.setPreTaxIncome(preTaxIncome);
            BigDecimal preTaxTotalServiceFee = new BigDecimal((String) data.get("preTaxTotalServiceFee"));
            totalPackageData.setPreTaxTotalServiceFee(preTaxTotalServiceFee);
            totalPackageData.setProjectNo((String) data.get("projectNo"));
            Date salesDate = formatter.parse((String) data.get("salesDate"), new ParsePosition(0));
            totalPackageData.setSalesDate(salesDate);
            BigDecimal salesMoney = new BigDecimal((String) data.get("salesMoney"));
            totalPackageData.setSalesMoney(salesMoney);
            totalPackageData.setSettlementNo((String) data.get("settlementNo"));

            Integer num = 1;
            if (null != data.get("salesNum")) {
                num = Integer.valueOf(data.get("salesNum").toString());
            }
            totalPackageData.setSalesNum(num);
            totalPackageDataList.add(totalPackageData);
        }
        return totalPackageDataList;
    }

    /**
     * 新增报备
     *
     * @param totalPackageData
     * @param estate
     * @param totalPackageSetting
     * @param oaPastDate
     * @return Report
     */
    private Report handleReport(TotalPackageData totalPackageData, Estate estate, TotalPackageSetting totalPackageSetting, Date oaPastDate) {
        Report report = new Report();
        report.setAccountProject(estate.getAccountProject());
        report.setAccountProjectNo(estate.getAccountProjectNo());
        report.setOrderNumber(totalPackageData.getOrderNumber());
        report.setProjectNo(totalPackageData.getProjectNo());
        report.setEstateId(estate.getEstateId());
        report.setEstateNm(estate.getEstateNm());
        report.setCompanyNm(totalPackageSetting.getCompanyNm());
        report.setCompanyId(String.valueOf(totalPackageSetting.getCompanyId()));
        report.setCustomerNm(totalPackageSetting.getCustomerNm());
        report.setCustomerTel(totalPackageSetting.getCustomerTel());
        String storeId = String.valueOf(totalPackageSetting.getStoreId());
        report.setStoreId(storeId);
        report.setStoreNm(totalPackageSetting.getStoreNm());
        report.setReportDate(totalPackageData.getSalesDate());
        report.setSeeDate(totalPackageData.getSalesDate());
        report.setRoughInputDate(oaPastDate);
        report.setRoughDate(oaPastDate);
        report.setDealDate(oaPastDate);
        report.setEmpId("888888");
        report.setEmpNm("admin");
        report.setCityNo(estate.getCityNo());
        report.setValidRelationDate(totalPackageData.getSalesDate());
        report.setContactId("888888");
        report.setContactNm("admin");
        Integer centerId = totalPackageSetting.getCenterId();
        report.setCenterId(centerId);
        report.setEstateType(estate.getMgtKbn());
        report.setLatestProgress(13505);
        report.setConfirmStatus(13601);
        report.setFollowDate(totalPackageData.getSalesDate());
        report.setValidDate(totalPackageData.getOaPastTrialDate());
        report.setCrtEmpId(2221);
        report.setCrtDt(new Date());
        report.setDelFlg(false);

        if (StringUtil.isNotEmpty(storeId)) {
            String contractType = contractStoreMapper.getTopContractTypeByStore(Integer.parseInt(storeId));
            if (StringUtil.isNotEmpty(contractType)) {
                report.setContractType(Integer.valueOf(contractType));
            }
        }

        String reportId = seqNoAPI.getSeqNoByTypeCode("TYPE_REPORTNO").getReturnData();
        report.setReportId(reportId);
        report.setCustomerId(reportId.replace("BB", "CUS"));
        report.setRoughAuditStatus("1");
        report.setCustomerFrom(17401);
        report.setRoughAuditId(Long.valueOf(2221));
        report.setRoughAuditTime(oaPastDate);
        reportMapper.create(report);
        return report;
    }

    /**
     * 新增报备详情
     *
     * @param totalPackageData
     * @param estate
     * @param totalPackageSetting
     * @param oaPastDate
     * @param reportId
     * @return
     */
    private Integer handleReportDetail(TotalPackageData totalPackageData, Estate estate, TotalPackageSetting totalPackageSetting, Date oaPastDate, String reportId) {
        Integer detailId = null;
        ReportDetail reportDetail = new ReportDetail();
        reportDetail.setReportNo(reportId);
        reportDetail.setEstateId(estate.getEstateId());
        reportDetail.setEstateNm(estate.getEstateNm());
        reportDetail.setCompanyNm(totalPackageSetting.getCompanyNm());
        reportDetail.setCompanyId(String.valueOf(totalPackageSetting.getCompanyId()));
        reportDetail.setCustomerId(reportId.replace("BB", "CUS"));
        reportDetail.setCustomerNm(totalPackageSetting.getCustomerNm());
        reportDetail.setCustomerTel(totalPackageSetting.getCustomerTel());
        reportDetail.setEmpNm("admin");
        reportDetail.setReportDate(totalPackageData.getSalesDate());
        reportDetail.setSeeDate(totalPackageData.getSalesDate());
        reportDetail.setPledgedDate(totalPackageData.getSalesDate());
        reportDetail.setRoughInputDate(oaPastDate);
        reportDetail.setRoughDate(oaPastDate);
        reportDetail.setDealDate(oaPastDate);
        reportDetail.setFollowDate(totalPackageData.getSalesDate());
        reportDetail.setCrtEmpId(2221);
        reportDetail.setCrtDt(new Date());
        reportDetail.setDelFlg(false);
        reportDetail.setCustomerFrom(17401);
        reportDetail.setRecognitionDay(totalPackageData.getSalesDate());

        reportDetail.setRelationReward(BigDecimal.ZERO);
        reportDetail.setAccountRelationReward(BigDecimal.ZERO);
        reportDetail.setPledgedReward(BigDecimal.ZERO);
        reportDetail.setAccountPledgedReward(BigDecimal.ZERO);
        reportDetail.setSubscribedReward(BigDecimal.ZERO);
        reportDetail.setAccountSubscribedReward(BigDecimal.ZERO);
        reportDetail.setBargainReward(BigDecimal.ZERO);
        reportDetail.setAccountBargainReward(BigDecimal.ZERO);
        reportDetail.setCommission(BigDecimal.ZERO);
        reportDetail.setAccountCommission(BigDecimal.ZERO);
        reportDetail.setCrtDt(new Date());
        reportDetail.setDelFlg(false);

        //a.报备 有效 13501 13601
        reportDetail.setCountId(reportId + "-01");
        reportDetail.setProgress(13501);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setAccountFlg(0);
        reportDetail.setAccountStatus(0);
        reportDetail.setCommissionAccountStatus(0);
        reportDetailMapper.create(reportDetail);

        //b.带看 有效 13502 13601
        reportDetail.setCountId(reportId + "-02");
        reportDetail.setProgress(13502);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(0);
        reportDetailMapper.create(reportDetail);

        //c.认筹 有效 13503 13601
        reportDetail.setCountId(reportId + "-03");
        reportDetail.setProgress(13503);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(14202);
        reportDetail.setBuildingNo(totalPackageData.getBuildingNo());
        reportDetail.setRoughAmount(totalPackageData.getSalesMoney());
        reportDetail.setRoughArea(BigDecimal.valueOf(100));
        reportDetailMapper.create(reportDetail);

        //d.大定 有效 13504 13601
        reportDetail.setCountId(reportId + "-04");
        reportDetail.setProgress(13504);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setRoughAmount(totalPackageData.getSalesMoney());
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(14202);
        reportDetail.setRoughArea(BigDecimal.valueOf(100));
        reportDetailMapper.create(reportDetail);

        //e.成销 有效 13505 13601
        reportDetail.setCountId(reportId + "-05");
        reportDetail.setProgress(13505);
        reportDetail.setConfirmStatus(13601);
        reportDetail.setRoughAmount(totalPackageData.getSalesMoney());
        reportDetail.setDealAmount(totalPackageData.getSalesMoney());
        reportDetail.setArea(BigDecimal.valueOf(100));
        reportDetail.setRoughArea(BigDecimal.valueOf(100));
        reportDetail.setAccountFlg(15801);
        reportDetail.setAccountStatus(14202);
        reportDetail.setCommissionAccountStatus(14202);
        reportDetailMapper.create(reportDetail);
        detailId = reportDetail.getId();

        return detailId;
    }

    /**
     * 添加接口调用日志
     *
     * @param log
     */
    public void addLog(TotalPackageLog log) throws Exception {
        totalPackageLogMapper.create(log);
    }

    /**
     * 总包应收收入数据处理
     *
     * @return
     */
    public ResultData<?> handleRevenues() throws Exception {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        //获取待处理数据
        List<TotalPackageData> dataLst = totalPackageDataMapper.getRevenuesForHandle();
        if (null == dataLst || dataLst.isEmpty()) {
            resultData.setFail("没有待处理的数据!");
            return resultData;
        }
//        TotalPackageSetting totalPackageSetting = totalPackageSettingMapper.getTotalPackageSetting(dataLst.get(0).getProjectNo());
        for (TotalPackageData totalPackageData : dataLst) {
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("orderNumber", totalPackageData.getOrderNumber());
            queryParam.put("projectNo",totalPackageData.getProjectNo());
            List<ReportDetail> rptLst = reportDetailMapper.getTotalPackageReport(queryParam);
            if (rptLst != null && rptLst.size() > 0) {
                ReportDetail reportDetail = rptLst.get(0);
                String reportId;
                queryParam.put("estateId", reportDetail.getEstateId());
                List<Report> rpts = reportMapper.getReport(queryParam);
                if (rpts == null || rpts.size() == 0) {
                    resultData.setFail("没有找到对应的报备记录！");
                    return resultData;
                }
                Report report = rpts.get(0);
                reportId = report.getReportId();
                if (totalPackageData.getReceivableDate() != null) {
                    if ((totalPackageData.getReceivableDate()).getTime() <= (new Date()).getTime()) {
                        //应收收入:LNK_YJ_YSSR
                        Map<String, Object> yjMap = new HashMap<>();
                        yjMap.put("reportId", reportId);
                        yjMap.put("companyNo", report.getCompanyId());
                        yjMap.put("detailId", reportDetail.getId());
                        yjMap.put("num", 1);
                        yjMap.put("recordDate", totalPackageData.getReceivableDate());
                        yjMap.put("befTaxAmount", totalPackageData.getPreTaxIncome());
                        yjMap.put("aftTaxAmount", totalPackageData.getAfterTaxIncome());
                        totalPackageDataMapper.handleYSSR(yjMap);

                        this.handleImport(reportId, reportDetail.getEstateId(), reportDetail.getEstateNm(), reportDetail.getId(), "20606", "20706", totalPackageData.getReceivableDate(), totalPackageData.getPreTaxIncome(),
                                totalPackageData.getAfterTaxIncome(), totalPackageData.getProjectNo(), totalPackageData.getBuildingNo(), totalPackageData.getSalesMoney());
                        totalPackageData.setHasRevenues(1);
                        totalPackageData.setRevenuesDate(new Date());
                        totalPackageDataMapper.update(totalPackageData);
                    }
                }
            }
        }
        return resultData;
    }

    /**
     * 总包应计实收数据处理
     *
     * @return
     */
    public ResultData<?> handleYJSS() throws Exception {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        //获取待处理数据
        List<Map<String, Object>> dataLst = totalPackageDataMapper.getYJSSForHandle();
        if (null == dataLst || dataLst.isEmpty()) {
            resultData.setFail("没有待处理的数据!");
            return resultData;
        }
        for (Map<String, Object> dataMap : dataLst) {
            List<ReportDetail> rptLst = reportDetailMapper.getTotalPackageReport(dataMap);
            if (rptLst != null && rptLst.size() > 0) {
                ReportDetail reportDetail = rptLst.get(0);
                String reportId;
                dataMap.put("estateId", reportDetail.getEstateId());
                List<Report> rpts = reportMapper.getReport(dataMap);
                if (rpts == null || rpts.size() == 0) {
                    resultData.setFail("没有找到对应的报备记录！");
                    return resultData;
                }
                Report report = rpts.get(0);
                reportId = report.getReportId();
                //应收收入:LNK_YJ_YJSS
                Map<String, Object> yjMap = new HashMap<>();
                yjMap.put("reportId", reportId);
                yjMap.put("companyNo", report.getCompanyId());
                yjMap.put("detailId", reportDetail.getId());
                yjMap.put("num", 1);
                yjMap.put("recordDate", dataMap.get("oaPastTrialDate"));
                yjMap.put("befTaxAmount", dataMap.get("preTaxAmount"));
                yjMap.put("aftTaxAmount", dataMap.get("afterTaxAmount"));
                totalPackageDataMapper.handleYJSS(yjMap);

                Date oaPastTrialDate = (Date) dataMap.get("oaPastTrialDate");
                BigDecimal preTaxAmount = (BigDecimal) dataMap.get("preTaxAmount");
                BigDecimal afterTaxAmount = (BigDecimal) dataMap.get("afterTaxAmount");


                this.handleImport(reportId, reportDetail.getEstateId(), reportDetail.getEstateNm(), reportDetail.getId(), "20603", "20704", oaPastTrialDate, preTaxAmount,
                        afterTaxAmount, report.getProjectNo(), reportDetail.getBuildingNo(), reportDetail.getDealAmount());

                totalPackageDataMapper.updateYJSS((Integer) dataMap.get("id"));
            }
        }
        return resultData;
    }
}
