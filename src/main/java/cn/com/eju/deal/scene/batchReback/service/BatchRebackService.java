package cn.com.eju.deal.scene.batchReback.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.AchieveMentChangeLogMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.houseLinkage.report.service.YFInterfaceService;
import cn.com.eju.deal.scene.batchReback.dao.BatchRebackDetailMapper;
import cn.com.eju.deal.scene.batchReback.dao.BatchRebackMapper;
import cn.com.eju.deal.scene.batchReback.dto.BatchReback;
import cn.com.eju.deal.scene.batchReback.dto.BatchRebackDetail;
import cn.com.eju.deal.scene.batchReback.dto.ReportDetailDto;
import cn.com.eju.deal.scene.commission.dao.*;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("batchRebackService")
public class BatchRebackService extends BaseService {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private BatchRebackMapper batchRebackMapper;
    @Resource
    private BatchRebackDetailMapper batchRebackDetailMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private CommonMapper commonMapper;
    @Resource
    private AchieveMentChangeLogMapper achieveMentChangeLogMapper;

    @Resource
    private FileRecordMainService fileRecordMainService;

    @Resource
    private SceneInCommissionMapper sceneInCommissionMapper;
    @Resource
    private ReportDetailMapper reportDetailMapper;


    @Resource
    private LnkYjYjsrMapper lnkYjYjsrMapper;

    @Resource
    private LnkYjYssrMapper lnkYjYssrMapper;

    @Resource
    private LnkYjYjdyMapper lnkYjYjdyMapper;

    @Resource
    private LnkYjNyMapper lnkYjNyMapper;

    @Resource
    private LnkYjSjfyMapper lnkYjSjfyMapper;

    @Resource(name = "reportService")
    private ReportService reportService;

    @Resource
    private YFInterfaceService yFInterfaceService;

    /**
     * 添加批量退房
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> addBatchReback(Map<?, ?> map) throws Exception {
        Map<String, Object> respMap = new HashMap<>();

        //判断实际返佣税前>0
        String brokerageStatus = (String) map.get("brokerageStatus");
        String reportId = (String) map.get("reportId");
        String rebackFlag = (String) map.get("rebackFlag");


        Report reportDb = reportMapper.getByReportId(reportId);

        // 如果没有进行退房解锁
        if (null == reportDb.getRebackFlag() || !reportDb.getRebackFlag()) {
            //部分结佣和已结佣
            if ("22002".equals(brokerageStatus) || "22003".equals(brokerageStatus)) {//部分结佣和已结佣
                //respMap.put("该报备订单状态是已结佣或者部分结佣，请通知对口结算解锁退房!");
                respMap.put("code", 4);
                return respMap;
            } else {
                Integer relCount = lnkYjSjfyMapper.checkPreAmountForReback(map);
                if (relCount > 0) {
                    //该报备订单已有实际返佣，请通知对口结算解锁退房。
                    respMap.put("code", 3);
                    return respMap;
                }
            }
        }

        ReportDetailDto reportDetail = batchRebackMapper.getReportDetail(reportId);

        //主表是否存在
        Integer yesOrNoBatchReback = batchRebackMapper.whetherExistenceBatchReback(map);
        //如果存在
        if (yesOrNoBatchReback > 0) {
            //退房是否已经添加
            Integer yesOrNoBatchRebackDetail = batchRebackDetailMapper.whetherExistenceBatchRebackDetail(map);
            if (yesOrNoBatchRebackDetail == 0) {
                BatchReback batchReback = batchRebackMapper.getBatchRebackByProjectNo(map);

                BatchRebackDetail batchRebackDetail = new BatchRebackDetail();
                batchRebackDetail.setBatchId(batchReback.getBatchId());
                batchRebackDetail.setReportId(reportId);
                batchRebackDetail.setFloor(reportDetail.getBuildingNo());
                batchRebackDetail.setCustomerName1(reportDetail.getCustomerNm());
                batchRebackDetail.setCustomerPhone1(reportDetail.getCustomerTel());
                batchRebackDetail.setCustomerName2(reportDetail.getCustomerNmTwo());
                batchRebackDetail.setCustomerPhone2(reportDetail.getCustomerTelTwo());
                batchRebackDetail.setSaleAmount(reportDetail.getDealAmount());//成销金额
                batchRebackDetail.setSaleArea(reportDetail.getArea());//成销面积
                batchRebackDetail.setAccountProject(reportDetail.getAccountProject());
                batchRebackDetail.setAccountProjectNo(reportDetail.getAccountProjectNo());
                batchRebackDetailMapper.insertBatchRebackDetail(batchRebackDetail);
                respMap.put("code", 1);
            } else {
                respMap.put("code", 2);
                respMap.put("msg", reportDetail.getReportId() + " 客户：" + reportDetail.getCustomerNm() + "  楼室号：" + reportDetail.getBuildingNo() + "室的房源已加入批量退房成功，请勿重复增加！");
            }
        } else {
            BatchReback batchReback = new BatchReback();
            batchReback.setProjectNo(map.get("projectNo").toString());
            batchReback.setEstateId(map.get("estateId").toString());
            batchReback.setEstateNm(map.get("estateNm").toString());
            batchReback.setUserCode(map.get("userCode").toString());
            batchReback.setCityNo(reportDetail.getEstateCityNo());
            batchRebackMapper.insertBatchReback(batchReback);

            BatchReback batchReback2 = batchRebackMapper.getBatchRebackByProjectNo(map);

            BatchRebackDetail batchRebackDetail = new BatchRebackDetail();
            batchRebackDetail.setBatchId(batchReback2.getBatchId());
            batchRebackDetail.setReportId(map.get("reportId").toString());
            batchRebackDetail.setFloor(reportDetail.getBuildingNo());
            batchRebackDetail.setCustomerName1(reportDetail.getCustomerNm());
            batchRebackDetail.setCustomerPhone1(reportDetail.getCustomerTel());
            batchRebackDetail.setCustomerName2(reportDetail.getCustomerNmTwo());
            batchRebackDetail.setCustomerPhone2(reportDetail.getCustomerTelTwo());
            batchRebackDetail.setSaleAmount(reportDetail.getDealAmount());//成销金额
            batchRebackDetail.setSaleArea(reportDetail.getArea());//成销面积
            batchRebackDetail.setAccountProject(reportDetail.getAccountProject());
            batchRebackDetail.setAccountProjectNo(reportDetail.getAccountProjectNo());
            batchRebackDetailMapper.insertBatchRebackDetail(batchRebackDetail);
            respMap.put("code", 1);
        }
        return respMap;
    }


    /**
     * 批量退房列表
     *
     * @param param
     * @return
     */
    public BatchReback getBatchRebackList(Map<?, ?> param) throws Exception {

        BatchReback batchReback = new BatchReback();
        List<BatchRebackDetail> batchRebackDetailList = new ArrayList<BatchRebackDetail>();
        batchReback = batchRebackMapper.getBatchRebackByProjectNo(param);
        if (batchReback != null) {
            batchRebackDetailList = batchRebackDetailMapper.getBatchRebackDetailList(batchReback.getBatchId());
            batchReback.setBatchRebackDetails(batchRebackDetailList);
            List<FileRecordMainDto> fileList = batchRebackMapper.getFileList(batchReback.getBatchId());
            batchReback.setFileList(fileList);
        }
        return batchReback;
    }

    /**
     * 共又多少套批量成销
     *
     * @param param
     * @return
     */
    public Integer countBatchRebackDetail(Map<?, ?> param) throws Exception {
        Integer num = batchRebackDetailMapper.countBatchRebackDetail(param);
        return num;
    }

    /**
     * 删除批量成销子表信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer deleteBatchRebackDetailById(Map<?, ?> param) throws Exception {
        Integer num = batchRebackDetailMapper.deleteBachRebackDetailByBatchId(param);
        Integer num1 = batchRebackDetailMapper.countBatchRebackDetailNum(param);
        if (num1 == 0) {
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", param.get("batchId"));
            delmap.put("fileSourceId", 5);
            delmap.put("fileTypeId", 1065);
            fileRecordMainMapper.deleteByCondition(delmap);

            batchRebackMapper.deleteBatchRebackById(Integer.parseInt(param.get("batchId").toString()));
        }
        return num;
    }

    /**
     * @param param
     * @return
     * @throws Exception
     */
    public Integer checkReback(Map<?, ?> param) throws Exception {
        Integer num = batchRebackDetailMapper.checkReback(param);
        return num;
    }

    /**
     * 保存批量退房信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<String> updateBatchRebackDetailAll(String param) throws Exception {
        //获取请求参数
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) queryParam.get("listMap");
        String removeMsg = "";
        //返回
        ResultData<String> resultData = new ResultData<>();
        for (Map<String, Object> map : listMap) {
            //批量修改子表信息
            Integer num = batchRebackDetailMapper.updateBachRebackDetail(map);
            if (num < 1) {
                removeMsg += map.get("reportId").toString() + ",";
            }
        }
        updateBatchRebackFile(listMap.get(0));
        if (removeMsg != "") {
            resultData.setReturnCode("0001");
            resultData.setReturnMsg("部分修改失败");
            resultData.setReturnData(removeMsg);
            return resultData;
        } else {
            resultData.setReturnCode("0000");
            resultData.setReturnMsg("修改成功");
            return resultData;
        }
    }


    /**
     * 修改批量退房附件
     *
     * @param param
     * @return
     * @throws Exception
     */
    public void updateBatchRebackFile(Map<?, ?> param) throws Exception {

        String fileRecordMainIds = param.get("fileRecordMainIds").toString();

        if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", param.get("batchId"));
            delmap.put("fileSourceId", 5);
            delmap.put("fileTypeId", 1065);
            fileRecordMainMapper.deleteByCondition(delmap);

            //新文件处理
            String[] fileIdArr = fileRecordMainIds.split(",");
            List<Integer> fileIdList = new ArrayList<>();
            for (String fileId : fileIdArr) {
                fileIdList.add(Integer.valueOf(fileId));
            }
            Map<String, Object> uptmap = new HashMap<>();
            uptmap.put("contractId", param.get("batchId"));
            uptmap.put("ids", fileIdList);
            fileRecordMainMapper.updateByCondition(uptmap);
        }
    }


    /**
     * 提交批量成销
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<String> submitBatchReback(String param) throws Exception {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String userCode = queryParam.get("userCode").toString();
        String userName = queryParam.get("userName").toString();
        String userId = queryParam.get("userId").toString();
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) queryParam.get("listMap");
        //返回
        ResultData<String> resultData = new ResultData<>();
        String removeMsg = "";
        List<Integer> rebackRptIdList = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            //是否可以退房校验
            Integer num = checkReback(map);
            if (num >= 1) {
                removeMsg += map.get("reportId").toString() + "、";
            }
            rebackRptIdList.add(Integer.valueOf(map.get("batchDetailId").toString()));
        }
        if (StringUtil.isNotEmpty(removeMsg)) {
            removeMsg = removeMsg.substring(0, removeMsg.length() - 1);
            resultData.setReturnCode("0002");
            resultData.setReturnMsg("未满足退房条件");
            resultData.setReturnData(removeMsg);
            return resultData;
        }
        List<BatchRebackDetail> dbRebackList = batchRebackDetailMapper.selectByBatachId(Integer.valueOf(listMap.get(0).get("batchId").toString()));
        List<Integer> dbRebackRptIdList = new ArrayList<>();

        // 数据库中的ID都在提交的ID中、提交的ID必须都在数据库中
        for (BatchRebackDetail dbRec : dbRebackList) {
            dbRebackRptIdList.add(dbRec.getBatchDetailId());
        }
        if (compare(dbRebackRptIdList, rebackRptIdList) == false) {
            resultData.setReturnCode("0003");
            resultData.setReturnMsg("退房的订单发生变更，请保存后重新打开页面！");
            resultData.setReturnData("退房的订单发生变更，请保存后重新打开页面！");
            return resultData;
        }

        //循环退房校验
        for (Map<String, Object> map : listMap) {
            map.put("userCode", userCode);
            map.put("userName", userName);
            ResultData<Integer> chkResDate = updateCheckReback(map);

            if ("200".equals(chkResDate.getReturnCode())
                    && AppMsg.getString("COMMON.OPERATE_SUCCESS").equals(chkResDate.getReturnMsg())) {
                continue;
            } else {
                removeMsg += map.get("reportId").toString() + chkResDate.getReturnMsg() + "、";
            }
        }

        if (removeMsg != null && removeMsg != "") {
            removeMsg = removeMsg.substring(0, removeMsg.length() - 1);
            resultData.setReturnCode("0003");
            resultData.setReturnMsg("退房失败!");
            resultData.setReturnData(removeMsg);
            return resultData;
        }

        //循环退房
        for (Map<String, Object> map : listMap) {
            map.put("userCode", userCode);
            map.put("userName", userName);
            //处理附件
            copyFileList(map);

            ResultData<Integer> resptData = updateReback(map);
            if ("200".equals(resptData.getReturnCode()) && AppMsg.getString("COMMON.OPERATE_SUCCESS").equals(resptData.getReturnMsg())) {
                insertYjRecord(map, userId);
                //删除批量成销子表信息
                deleteBatchRebackDetailById(map);
                //更新操作记录
                batchRebackDetailMapper.updateBatchRebackLog(map);
            } else {
                removeMsg += map.get("reportId").toString() + resptData.getReturnMsg() + "、";
            }
        }
        if (removeMsg != null && removeMsg != "") {
            removeMsg = removeMsg.substring(0, removeMsg.length() - 1);
            resultData.setReturnCode("0001");
            resultData.setReturnMsg("部分退房失败!");
            resultData.setReturnData(removeMsg);
            return resultData;
        } else {
            resultData.setReturnCode("0000");
            resultData.setReturnMsg("批量退房成功!");
            resultData.setReturnData(removeMsg);
            return resultData;
        }
    }

    private void insertYjRecord(Map<String, Object> map, String userId) throws Exception {
        CommissionResultDto cDto = new CommissionResultDto();
        cDto.setReportId(map.get("reportId").toString());
        cDto.setNum(-1);
        cDto.setRecordDate(map.get("rebackDate").toString());
        cDto.setCompanyNo(map.get("companyId").toString());
        cDto.setDetailId(map.get("detailId").toString());
        cDto.setCrtEmpId(userId);
        cDto.setUptEmpId(userId);

        String befYjsrTaxAmount = (String) map.get("befYjsrAmount");
        String aftYjsrTaxAmount = (String) map.get("aftYjsrAmount");
        String befYssrTaxAmount = (String) map.get("befYssrAmount");
        String aftYssrTaxAmount = (String) map.get("aftYssrAmount");
        /*String befYjdyTaxAmount = (String) map.get("befYjdyAmount");
        String aftYjdyTaxAmount = (String) map.get("aftYjdyAmount");*/
        //应计收入
        if (StringUtil.isNotEmpty(befYjsrTaxAmount) && new BigDecimal(befYjsrTaxAmount).compareTo(BigDecimal.ZERO) > 0
                && StringUtil.isNotEmpty(aftYjsrTaxAmount) && new BigDecimal(aftYjsrTaxAmount).compareTo(BigDecimal.ZERO) > 0) {
            befYjsrTaxAmount = "-" + befYjsrTaxAmount;
            aftYjsrTaxAmount = "-" + aftYjsrTaxAmount;
            cDto.setBefTaxAmount(befYjsrTaxAmount);
            cDto.setAftTaxAmount(aftYjsrTaxAmount);
            lnkYjYjsrMapper.mergeInsert(cDto);

            Map<String, Object> syncMap = new HashMap<>();
            syncMap.put("empId", userId);
            syncMap.put("tabName", "LNK_YJ_YJSR");
            lnkYjNyMapper.syncLnkImport(syncMap);
        }

        //应收收入
        if (StringUtil.isNotEmpty(befYssrTaxAmount) && new BigDecimal(befYssrTaxAmount).compareTo(BigDecimal.ZERO) > 0
                && StringUtil.isNotEmpty(aftYssrTaxAmount) && new BigDecimal(aftYssrTaxAmount).compareTo(BigDecimal.ZERO) > 0) {
            befYssrTaxAmount = "-" + befYssrTaxAmount;
            aftYssrTaxAmount = "-" + aftYssrTaxAmount;
            cDto.setBefTaxAmount(befYssrTaxAmount);
            cDto.setAftTaxAmount(aftYssrTaxAmount);
            lnkYjYssrMapper.mergeInsert(cDto);

            Map<String, Object> syncMap = new HashMap<>();
            syncMap.put("empId", userId);
            syncMap.put("tabName", "LNK_YJ_YSSR");
            lnkYjNyMapper.syncLnkImport(syncMap);
        }

        /*//应计垫佣
        if (StringUtil.isNotEmpty(befYjdyTaxAmount) && new BigDecimal(befYjdyTaxAmount).compareTo(BigDecimal.ZERO) > 0
                && StringUtil.isNotEmpty(aftYjdyTaxAmount) && new BigDecimal(aftYjdyTaxAmount).compareTo(BigDecimal.ZERO) > 0) {
            befYjdyTaxAmount = "-" + befYjdyTaxAmount;
            aftYjdyTaxAmount = "-" + aftYjdyTaxAmount;
            cDto.setBefTaxAmount(befYjdyTaxAmount);
            cDto.setAftTaxAmount(aftYjdyTaxAmount);
            lnkYjYjdyMapper.mergeInsertByDto(cDto);
        }*/
    }


    /**
     * 处理附件
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer copyFileList(Map<?, ?> param) throws Exception {

        Integer num = 0;
        //获取主表上传过得集合
        List<FileRecordMainDto> fileList = batchRebackMapper.getFileList(Integer.parseInt(param.get("batchId").toString()));
        for (FileRecordMainDto fileRecordMainDto : fileList) {
            ReportDetailDto reportDetail = batchRebackMapper.getReportDetail(param.get("reportId").toString());
            fileRecordMainDto.setRefId(reportDetail.getId());
            //拷贝之前先清除之前的旧文件
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", reportDetail.getId());
            delmap.put("fileSourceId", 5);
            delmap.put("fileTypeId", 1065);
            fileRecordMainMapper.deleteByCondition(delmap);

            num += batchRebackMapper.saveFileInfo(fileRecordMainDto);
        }
        return num;
    }

    public ResultData<Integer> updateCheckReback(Map<String, Object> param) throws Exception {

        ReportDetailDto reportDetail = batchRebackMapper.getReportDetail(param.get("reportId").toString());
        ResultData<Integer> resultData = new ResultData<Integer>();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> reMap = new HashMap<String, Object>();
        String reportId = param.get("reportId").toString();
        String estateId = reportDetail.getEstateId();
        String rebackDate = (String) param.get("rebackDate");
        Date dealBackDate = format1.parse(rebackDate);

        reMap.put("estateId", estateId);
        reMap.put("reportId", reportId);
        Report reportDb = reportMapper.getReport(reMap).get(0);


        // 退房日期加入关账判断
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

//        int num = reportMapper.checkRebackBrokerage(reportDb.getReportId());

        if (reportDb.getDealDate() != null && dealBackDate.before(reportDb.getDealDate())) {
            resultData.setReturnMsg("退房时间必须大于等于成销时间");
            resultData.setReturnData(201);
            return resultData;
        }
        logger.info("批量退房处理，"+reportId+ "rebackDate:"+rebackDate + "switchDate:" + switchDate.toString());
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

        resultData.setReturnData(0);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;

    }

    public ResultData<Integer> updateReback(Map<String, Object> param)
            throws Exception {
        ReportDetailDto reportDetail = batchRebackMapper.getReportDetail(param.get("reportId").toString());
        ResultData<Integer> resultData = new ResultData<Integer>();
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

        Map<String, Object> reMap = new HashMap<String, Object>();
        String reportId = param.get("reportId").toString();
        String estateId = reportDetail.getEstateId();
        String rebackDate = (String) param.get("rebackDate");
        Date dealBackDate = format1.parse(rebackDate);

        reMap.put("estateId", estateId);
        reMap.put("reportId", reportId);
        Report reportDb = reportMapper.getReport(reMap).get(0);


        Report updateReport = new Report();
        updateReport.setReportId(reportId);
        updateReport.setEstateId(estateId);
        updateReport.setDealBackDate(dealBackDate);
        reportMapper.updateReback(updateReport);//回退

        //修改detail表
        ReportDetail tempReportDetail = new ReportDetail();
        tempReportDetail.setEstateId(estateId);
        tempReportDetail.setCountId(reportId);
        tempReportDetail.setProgress(13505);
        //补
        ReportDetail copyPledged = reportDetailMapper.getCopyByPledged(tempReportDetail);
        StringBuilder changeMsg = new StringBuilder();
        changeMsg.append("案件记录由大定（客户名:").append(reportDb.getCustomerNm()).append(";")
                .append("电话:").append(reportDb.getCustomerTel()).append(";")
                .append("楼室号:").append(copyPledged.getBuildingNo()).append("室;")
                .append("成销面积:").append(copyPledged.getArea()).append("㎡;")
                .append("成销总价:").append(copyPledged.getDealAmount()).append("元;")
                .append("成销时间:").append(DateUtil.fmtDate(copyPledged.getDealDate(), "yyyy-MM-dd"))
                .append("）修改为退房,退房日期:").append(DateUtil.fmtDate(dealBackDate, "yyyy-MM-dd"));

        if (copyPledged == null) {
            resultData.setReturnMsg("报备数据异常,请联系系统管理员！");
            resultData.setReturnData(201);
            return resultData;
        }
        //设置佣金所需字段
        param.put("detailId", copyPledged.getId());
        param.put("companyId", copyPledged.getCompanyId());

        tempReportDetail.setProgress(null);//清除
        tempReportDetail.setDealBackDate(dealBackDate);
        //更新退房时间 和回退
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
        reportDetailMapper.create(copyPledged);

        //删除带看之后的附件
        Map<String, Object> fileRecMap = new HashMap<>();
        fileRecMap.put("refId", reportDb.getId());
        fileRecMap.put("fileSourceId", 5);
        fileRecordMainService.deletePartReportFile(fileRecMap);

        //添加日志信息
        AchieveMentChangeLog achieveMentChangeLog = new AchieveMentChangeLog();
        achieveMentChangeLog.setRelateId(reportDb.getId());
        achieveMentChangeLog.setChangeContent(changeMsg.toString());
        achieveMentChangeLog.setCreateUserCode((String) param.get("userCode"));
        achieveMentChangeLog.setCreateUserName((String) param.get("userName"));
        achieveMentChangeLog.setCreateDate(new Date());
        achieveMentChangeLog.setDelFlag(false);
        achieveMentChangeLogMapper.insertSelective(achieveMentChangeLog);

        if (reportDb.getCustomerFrom() != null && "17405".equals(reportDb.getCustomerFrom().toString())) {    //退房
            reportService.backToFangyou(reportDb, (String) param.get("userCode"), 2);
        }
        yFInterfaceService.noticeYx(reportDb.getReportId(), "13", (String) param.get("userCode"), "0");//退房


        //可退房标记清空
        Map<String, Object> rebackMap = new HashMap<>();
        rebackMap.put("reportId", reportDb.getId());
        rebackMap.put("rebackFlag", false);
        reportMapper.unlockReback(rebackMap);

        resultData.setReturnData(0);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }


}
