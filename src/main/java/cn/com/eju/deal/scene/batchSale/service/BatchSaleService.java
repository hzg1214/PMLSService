package cn.com.eju.deal.scene.batchSale.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateRuleMapper;
import cn.com.eju.deal.houseLinkage.estate.model.EstateRule;
import cn.com.eju.deal.houseLinkage.report.dao.ReportDetailMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.houseLinkage.report.model.ReportDetail;
import cn.com.eju.deal.houseLinkage.report.model.YFStatusSync;
import cn.com.eju.deal.houseLinkage.report.service.YFInterfaceService;
import cn.com.eju.deal.scene.batchReback.dto.BatchRebackDetail;
import cn.com.eju.deal.scene.batchSale.dao.BatchSaleDetailMapper;
import cn.com.eju.deal.scene.batchSale.dao.BatchSaleMapper;
import cn.com.eju.deal.scene.batchSale.dto.BatchSale;
import cn.com.eju.deal.scene.batchSale.dto.BatchSaleDetail;
import cn.com.eju.deal.scene.batchSale.dto.ReportDetailDto;
import cn.com.eju.deal.scene.commission.dao.*;
import cn.com.eju.deal.scene.inCommission.dao.SceneInCommissionMapper;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("batchSaleService")
public class BatchSaleService extends BaseService {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private BatchSaleMapper batchSaleMapper;
    @Resource
    private BatchSaleDetailMapper batchSaleDetailMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource
    private ReportMapper reportMapper;
    @Resource
    private EstateRuleMapper estateRuleMapper;
    @Resource
    private SceneInCommissionMapper sceneInCommissionMapper;
    @Resource
    private ReportDetailMapper reportDetailMapper;

    @Resource
    private YFInterfaceService yFInterfaceService;

    @Resource
    private LnkYjReportMapper lnkYjReportMapper;

    @Resource
    private CompanyService companyService;

    @Resource
    private LnkYjYjsrMapper lnkYjYjsrMapper;

    @Resource
    private LnkYjYjfyMapper lnkYjYjfyMapper;

    @Resource
    private LnkYjYjdyMapper lnkYjYjdyMapper;

    @Resource
    private LnkYjNyMapper lnkYjNyMapper;

    @Resource
    private LnkYjFyfyMapper lnkYjFyfyMapper;


    @Resource
    private CommonMapper commonMapper;

    /**
     * 添加批量成销
     *
     * @param map
     * @return
     * @throws Exception
     */
    public Map<String, Object> addBatchSaleDetail(Map<?, ?> map) throws Exception {
        Map<String, Object> respMap = new HashMap<>();

        //多返佣对象判断
        Map<String, Object> tmpMap = new HashMap<>();
        tmpMap.put("reportId", map.get("reportId"));
        List<Map<String, Object>> list = lnkYjReportMapper.getYjReportDeatilById(tmpMap);
        if (list != null && list.size() > 1) {
            //存在多个返佣对象，无法添加批量成销
            respMap.put("code", 3);
            return respMap;
        }

        //主表是否存在
        Integer yesOrNoBatchSale = batchSaleMapper.whetherExistenceBatchSale(map);
        //如果存在
        if (yesOrNoBatchSale > 0) {
            //成销是否已经添加
            Integer yesOrNoBatchSaleDetail = batchSaleDetailMapper.whetherExistenceBatchSaleDetail(map);
            if (yesOrNoBatchSaleDetail == 0) {
                BatchSale batchSale = batchSaleMapper.getBatchSaleByProjectNo(map);
                ReportDetailDto reportDetail = batchSaleMapper.getReportDetail(map.get("reportId").toString());

                BatchSaleDetail batchSaleDetail = new BatchSaleDetail();
                batchSaleDetail.setBatchId(batchSale.getBatchId());
                batchSaleDetail.setReportId(map.get("reportId").toString());
                batchSaleDetail.setFloor(reportDetail.getBuildingNo());
                batchSaleDetail.setCustomerName1(reportDetail.getCustomerNm());
                batchSaleDetail.setCustomerPhone1(reportDetail.getCustomerTel());
                batchSaleDetail.setCustomerName2(reportDetail.getCustomerNmTwo());
                batchSaleDetail.setCustomerPhone2(reportDetail.getCustomerTelTwo());
                batchSaleDetail.setSaleAmount(reportDetail.getRoughAmount());//大定金额
                batchSaleDetail.setSaleAcreage(reportDetail.getRoughArea());//大定面积
                batchSaleDetailMapper.insertBatchSaleDetail(batchSaleDetail);
                respMap.put("code", 1);
//                respMap.put("msg",batchSaleDetail.getReportId()+" 客户："+batchSaleDetail.getCustomerName1()+"  楼室号："+batchSaleDetail.getFloor()+"室的房源加入批量成销成功！");
            } else {
                ReportDetailDto reportDetail = batchSaleMapper.getReportDetail(map.get("reportId").toString());
                respMap.put("code", 2);
                respMap.put("msg", reportDetail.getReportId() + " 客户：" + reportDetail.getCustomerNm() + "  楼室号：" + reportDetail.getBuildingNo() + "室的房源已加入批量成销成功，请勿重复增加！");
            }
        } else {
            ReportDetailDto reportDetail = batchSaleMapper.getReportDetail(map.get("reportId").toString());

            BatchSale batchSale = new BatchSale();
            batchSale.setProjectNo(map.get("projectNo").toString());
            batchSale.setEstateId(map.get("estateId").toString());
            batchSale.setEstateNm(map.get("estateName").toString());
            batchSale.setUserCode(map.get("userCode").toString());
            batchSale.setCityNo(reportDetail.getEstateCityNo());
            batchSaleMapper.insertBatchSale(batchSale);

            BatchSale batchSale2 = batchSaleMapper.getBatchSaleByProjectNo(map);
            BatchSaleDetail batchSaleDetail = new BatchSaleDetail();
            batchSaleDetail.setBatchId(batchSale2.getBatchId());
            batchSaleDetail.setReportId(map.get("reportId").toString());
            batchSaleDetail.setFloor(reportDetail.getBuildingNo());
            batchSaleDetail.setCustomerName1(reportDetail.getCustomerNm());
            batchSaleDetail.setCustomerPhone1(reportDetail.getCustomerTel());
            batchSaleDetail.setCustomerName2(reportDetail.getCustomerNmTwo());
            batchSaleDetail.setCustomerPhone2(reportDetail.getCustomerTelTwo());
            batchSaleDetail.setSaleAmount(reportDetail.getRoughAmount());//大定金额
            batchSaleDetail.setSaleAcreage(reportDetail.getRoughArea());//大定面积
            batchSaleDetailMapper.insertBatchSaleDetail(batchSaleDetail);
            respMap.put("code", 1);
//            respMap.put("msg",batchSaleDetail.getReportId()+" 客户："+batchSaleDetail.getCustomerName1()+"  楼室号："+batchSaleDetail.getFloor()+"室的房源加入批量成销成功！");
        }
        return respMap;
    }


    /**
     * 批量成销列表
     *
     * @param param
     * @return
     */
    public BatchSale getBatchSale(Map<?, ?> param) throws Exception {

        BatchSale batchSale = new BatchSale();
        List<BatchSaleDetail> batchSaleDetailList = new ArrayList<BatchSaleDetail>();
        batchSale = batchSaleMapper.getBatchSaleByProjectNo(param);
        if (batchSale != null) {
//            batchSaleDetailList=batchSaleDetailMapper.getBatchSaleDetailList(batchSale.getBatchId());
//            batchSale.setBatchSaleDetails(batchSaleDetailList);
            List<FileRecordMainDto> fileList = batchSaleMapper.getFileList(batchSale.getBatchId());
            batchSale.setFileList(fileList);
        }
        return batchSale;
    }


    public List<BatchSaleDetail> getBatchSaleDetailList(Integer batchId) throws Exception {
        return batchSaleDetailMapper.getBatchSaleDetailList(batchId);
    }

    /**
     * 共又多少套批量成销
     *
     * @param param
     * @return
     */
    public Integer countBatchSaleDetial(Map<?, ?> param) throws Exception {
        Integer num = batchSaleDetailMapper.countBatchSaleDetail(param);
        return num;
    }

    /**
     * 删除批量成销子表信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer deleteBatchSaleDetailById(Map<?, ?> param) throws Exception {
        Integer num = batchSaleDetailMapper.deleteBachSaleDetailByBatchId(param);
        Integer num1 = batchSaleDetailMapper.countBatchSaleDetailNum(param);
        if (num1 == 0) {
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", param.get("batchId"));
            delmap.put("fileSourceId", 25);
            delmap.put("fileTypeId", 1025);
            fileRecordMainMapper.deleteByCondition(delmap);

            batchSaleMapper.deleteBatchSaleById(Integer.parseInt(param.get("batchId").toString()));
        }
        return num;
    }

    /**
     * 保存批量成销信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<String> updateBatchSaleDetailAll(String param) throws Exception {
        //获取请求参数
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) queryParam.get("listMap");
        String removeMsg = "";
        //返回
        ResultData<String> resultData = new ResultData<>();
        for (Map<String, Object> map : listMap) {
            //批量修改子表信息
            Integer num = batchSaleDetailMapper.updateBachSaleDetail(map);
            if (num < 1) {
                removeMsg += map.get("reportId").toString() + ",";
            }
            //批量修改report表核算主体
//            reportMapper.updateAccountProject(map);
            
            //20200702 提交保存时根据核算主体名称(LNK_Estate_Srlht)查询核算主体编码
            reportMapper.updateAccountProjectBySrlht(map);
        }
        //修改批量成销主表信息
        updateBatchSaleById(listMap.get(0));
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
     * 修改批量成销主表信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer updateBatchSaleById(Map<?, ?> param) throws Exception {

        String fileRecordMainIds = param.get("fileRecordMainIds").toString();

        if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", param.get("batchId"));
            delmap.put("fileSourceId", 25);
            delmap.put("fileTypeId", 1025);
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
        Integer num = batchSaleMapper.updateBatchSaleByBatchId(param);
        return num;
    }

    /**
     * 当前是否是大定状态
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer yesOrNoDaDingStatus(Map<?, ?> param) throws Exception {
        Integer num = batchSaleDetailMapper.yesOrNoDaDingStatus(param);
        return num;
    }


    /**
     * 提交批量成销
     *
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<String> submitBatchSaleAll(String param) throws Exception {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        String userCode = queryParam.get("userCode").toString();
        String userName = queryParam.get("userName").toString();
        String userId = queryParam.get("userId").toString();
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) queryParam.get("listMap");
        //返回
        ResultData<String> resultData = new ResultData<>();
        String removeMsg = "";
        List<Integer> saleRptIdList = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            //是否可以成销校验
            Integer num = yesOrNoDaDingStatus(map);
            if (num < 1) {
                removeMsg += map.get("reportId").toString() + "、";
            }
            saleRptIdList.add(Integer.valueOf(map.get("batchDetailId").toString()));
        }
        if (removeMsg != null && removeMsg != "") {
            removeMsg = removeMsg.substring(0, removeMsg.length() - 1);
            resultData.setReturnCode("0002");
            resultData.setReturnMsg("未满足成销条件");
            resultData.setReturnData(removeMsg);
            return resultData;
        }

        List<BatchSaleDetail> dbSaleList = batchSaleDetailMapper.selectByBatachId(Integer.valueOf(listMap.get(0).get("batchId").toString()));
        List<Integer> dbSaleRptIdList = new ArrayList<>();

        // 数据库中的ID都在提交的ID中、提交的ID必须都在数据库中
        for (BatchSaleDetail dbRec : dbSaleList) {
            dbSaleRptIdList.add(dbRec.getBatchDetailId());
        }
        if (compare(dbSaleRptIdList, saleRptIdList) == false) {
            resultData.setReturnCode("0003");
            resultData.setReturnMsg("成销的订单发生变更，请保存后重新打开页面！");
            resultData.setReturnData("成销的订单发生变更，请保存后重新打开页面！");
            return resultData;
        }

        //循环成销校验
        for (Map<String, Object> map : listMap) {
            map.put("userCode", userCode);
            map.put("userName", userName);

            ResultData<Integer> chkResDate = updateCheckSale(map);

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
            resultData.setReturnMsg("成销失败!");
            resultData.setReturnData(removeMsg);
            return resultData;
        }


        //循环成销
        for (Map<String, Object> map : listMap) {
            map.put("userCode", userCode);
            map.put("userName", userName);
            //拷贝附件
            copyFileList(map);
            //成销接口
            ResultData<Integer> resptData = sceneRecogonitionConfirm(map);
            if ("200".equals(resptData.getReturnCode()) && AppMsg.getString("COMMON.OPERATE_SUCCESS").equals(resptData.getReturnMsg())) {

                insertYjRecord(map, userId);

                dealCrm(map);
                //删除批量成销子表信息
                deleteBatchSaleDetailById(map);
                //更新操作记录
                batchSaleDetailMapper.updateBatchSaleLog(map);
            } else {
                removeMsg += map.get("reportId").toString() + "、";
            }
        }
        if (removeMsg != null && removeMsg != "") {
            removeMsg = removeMsg.substring(0, removeMsg.length() - 1);
            resultData.setReturnCode("0001");
            resultData.setReturnMsg("部分成销失败!");
            resultData.setReturnData(removeMsg);
            return resultData;
        } else {
            resultData.setReturnCode("0000");
            resultData.setReturnMsg("批量成销成功!");
            resultData.setReturnData(removeMsg);
            return resultData;
        }
    }

    private void insertYjRecord(Map<String, Object> map, String userId) throws Exception {
        //应计收入
        CommissionResultDto cDto = new CommissionResultDto();
        cDto.setReportId(map.get("reportId").toString());
        cDto.setBefTaxAmount(map.get("befYjsrTaxAmount").toString());
        cDto.setAftTaxAmount(map.get("aftYjsrTaxAmount").toString());
        cDto.setNum(1);
        cDto.setRecordDate(map.get("saleDate").toString());
        cDto.setCompanyNo(map.get("companyId").toString());
        cDto.setDetailId(map.get("detailId").toString());
        cDto.setCrtEmpId(userId);
        cDto.setUptEmpId(userId);
        lnkYjYjsrMapper.mergeInsert(cDto);

        //应计返佣
        cDto.setBefTaxAmount(map.get("befYjfyTaxAmount").toString());
        cDto.setAftTaxAmount(map.get("aftYjfyTaxAmount").toString());
        lnkYjYjfyMapper.mergeInsert(cDto);

        //应计垫佣
        if (map.get("befYjdyTaxAmount") != null && map.get("aftYjdyTaxAmount") != null) {
            cDto.setBefTaxAmount(map.get("befYjdyTaxAmount").toString());
            cDto.setAftTaxAmount(map.get("aftYjdyTaxAmount").toString());
            lnkYjYjdyMapper.mergeInsertByDto(cDto);
        }

        Map<String, Object> syncMap = new HashMap<>();
        syncMap.put("empId", userId);
        syncMap.put("tabName", "LNK_YJ_YJSR");
        lnkYjNyMapper.syncLnkImport(syncMap);

        syncMap.put("tabName", "LNK_YJ_YJFY");
        lnkYjNyMapper.syncLnkImport(syncMap);

        // 房友返佣信息
        if (!StringUtils.isEmpty((String) map.get("befFangyouYJFYAmount")) && !StringUtils.isEmpty((String) map.get("aftFangyouYJFYAmount"))) {
            Map<String, Object> fyMap = new HashMap<>();

            fyMap.put("reportId", map.get("reportId").toString());
            fyMap.put("befTaxAmount", String.valueOf(map.get("befFangyouYJFYAmount")));
            fyMap.put("aftTaxAmount", String.valueOf(map.get("aftFangyouYJFYAmount")));

            fyMap.put("recordDate", map.get("saleDate").toString());
            fyMap.put("companyNo", map.get("companyId").toString());
            fyMap.put("detailId", map.get("detailId").toString());
            fyMap.put("num", 1);
            fyMap.put("crtEmpId", userId);
            fyMap.put("uptEmpId", userId);

            lnkYjFyfyMapper.mergeInsert(fyMap);
        }

    }


    /**
     * 拷贝合同
     *
     * @param param
     * @return
     * @throws Exception
     */
    public Integer copyFileList(Map<?, ?> param) throws Exception {

        Integer num = 0;
        //获取主表上传过得合同集合
        List<FileRecordMainDto> fileList = batchSaleMapper.getFileList(Integer.parseInt(param.get("batchId").toString()));
        for (FileRecordMainDto fileRecordMainDto : fileList) {
            ReportDetailDto reportDetail = batchSaleMapper.getReportDetail(param.get("reportId").toString());

            //拷贝之前先清除之前的旧文件
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", reportDetail.getId());
            delmap.put("fileSourceId", 25);
            delmap.put("fileTypeId", 1025);
            fileRecordMainMapper.deleteByCondition(delmap);

            fileRecordMainDto.setRefId(reportDetail.getId());
            fileRecordMainDto.setFileSourceId(5);
            num += batchSaleMapper.saveFileInfo(fileRecordMainDto);
        }
        return num;
    }

    public ResultData<Integer> updateCheckSale(Map<String, Object> param)  throws Exception {

        ReportDetailDto reportDetail = batchSaleMapper.getReportDetail(param.get("reportId").toString());
        ResultData<Integer> resultData = new ResultData<Integer>();
        int count = 0;
        String reportId = param.get("reportId").toString();
        String estateId = reportDetail.getEstateId();
        String status = "13505";
        String operateDate = (String) param.get("saleDate");
        String buildingNo = null;
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
        if (13505 == currProgress && 13505 != latestProgress) {//成销操作判断 (成销操作前必须大定,如果相等则表示已经大定  )
            resultData.setReturnMsg("成销前必须进行大定操作");
            resultData.setReturnData(201);
            return resultData;
        }

        nextProgress = currProgress < 13505 ? currProgress + 1 : currProgress;//成交以后就没有下一个进度
        commissionProgress = nextProgress == 13505 ? 13507 : 0;//认定成交是需要更新结佣进度
        if (!"13602".equals(status)) {
            // 非无效场合
            report.setLatestProgress(nextProgress);
            report.setCommissionProgress(commissionProgress);
            validateDate = new Date();
            validateDate.setTime(new Date().getTime() + rule.getRelationProtectPeriod().longValue() * 24 * 3600 * 1000);
            report.setValidDate(validateDate);
            report.setFollowDate(new Date());
            if (currProgress == 13505) {
                report.setConfirmStatus(13601);
            }
        }
        report.setUptDt(new Date());
        if (!status.equals("13602")) {
            String checkStr = setDate(report, status, operateDate);
            if (StringUtil.isNotEmpty(checkStr)) {
                resultData.setReturnMsg(checkStr);
                resultData.setReturnData(201);
                return resultData;
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

        resultData.setReturnData(count);
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }


    public ResultData<Integer> sceneRecogonitionConfirm(Map<String, Object> param)
            throws Exception {
        ReportDetailDto reportDetail = batchSaleMapper.getReportDetail(param.get("reportId").toString());
        ResultData<Integer> resultData = new ResultData<Integer>();
        int count = 0;
        String reportId = param.get("reportId").toString();
        String estateId = reportDetail.getEstateId();
        String status = "13505";
        String operateDate = (String) param.get("saleDate");
        String buildingNo = null;
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
        if (13505 == currProgress && 13505 != latestProgress) {//成销操作判断 (成销操作前必须大定,如果相等则表示已经大定  )
            resultData.setReturnMsg("成销前必须进行大定操作");
            resultData.setReturnData(201);
            return resultData;
        }

        nextProgress = currProgress < 13505 ? currProgress + 1 : currProgress;//成交以后就没有下一个进度
        commissionProgress = nextProgress == 13505 ? 13507 : 0;//认定成交是需要更新结佣进度
        if (!"13602".equals(status)) {
            // 非无效场合
            report.setLatestProgress(nextProgress);
            report.setCommissionProgress(commissionProgress);
            validateDate = new Date();
            validateDate.setTime(new Date().getTime() + rule.getRelationProtectPeriod().longValue() * 24 * 3600 * 1000);
            report.setValidDate(validateDate);
            report.setFollowDate(new Date());
            if (currProgress == 13505) {
                report.setConfirmStatus(13601);
            }
        }
        report.setUptDt(new Date());
        if (!status.equals("13602")) {
            String checkStr = setDate(report, status, operateDate);
            if (StringUtil.isNotEmpty(checkStr)) {
                resultData.setReturnMsg(checkStr);
                resultData.setReturnData(201);
                return resultData;
            }
        }
        if (null != param.get("customerName1")) {
            report.setCustomerNm((String) param.get("customerName1"));
        }
        if (null != param.get("customerPhone1")) {
            report.setCustomerTel((String) param.get("customerPhone1"));
        }
        if (null != param.get("customerName2")) {
            report.setCustomerNmTwo((String) param.get("customerName2"));
        }
        if (null != param.get("customerPhone2")) {
            report.setCustomerTelTwo((String) param.get("customerPhone2"));
        }
        //20200702 提交时更新核算主体(从项目得收入类合同获取核算主体)
//        reportMapper.update(report);
        reportMapper.updateNew(report);

        //更新报备详细表
        queryParam.clear();
        queryParam.put("estateId", estateId);
        queryParam.put("companyId", report.getCompanyId());
        queryParam.put("customerId", report.getCustomerId());
        queryParam.put("progress", latestProgress);
        List<ReportDetail> updateDetailList = reportDetailMapper.getReportDetail(queryParam);

//        if (null == updateDetailList || updateDetailList.isEmpty()) {
//            resultData.setFail("查询出数据为空");
//            return resultData;
//        }
        updateDetail = updateDetailList.get(0);
        //设置佣金所需字段
        param.put("detailId", updateDetail.getId());
        param.put("companyId", updateDetail.getCompanyId());

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
            insertDetail.setCustomerNm((String) param.get("customerName1"));
            insertDetail.setCustomerTel((String) param.get("customerPhone1"));

            if (param.get("customerName2") != null) {
                insertDetail.setCustomerNmTwo((String) param.get("customerName2"));
            }
            if (param.get("customerPhone2") != null) {
                insertDetail.setCustomerTelTwo((String) param.get("customerPhone2"));
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
                buildingNo = reportDetail.getBuildingNo();
                areaStr = (String) param.get("saleAcreage");
                roughAmountStr = reportDetail.getRoughAmount().toString();
                dealAmountStr = (String) param.get("saleAmount");
                roughAreaStr = reportDetail.getRoughArea().toString();
                insertDetail.setBuildingNo(buildingNo);

                if (StringUtils.isNotBlank(areaStr))
                    insertDetail.setArea(new BigDecimal(areaStr));

                insertDetail.setRoughAmount(new BigDecimal(roughAmountStr));

                if (StringUtils.isNotBlank(dealAmountStr))
                    insertDetail.setDealAmount(new BigDecimal(dealAmountStr));
                if (StringUtils.isNotBlank(roughAreaStr))
                    insertDetail.setRoughArea(new BigDecimal(roughAreaStr));
            }
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
                    insertDetail.setCustomerNm((String) param.get("customerName1"));
                    insertDetail.setCustomerTel((String) param.get("customerPhone1"));
                    if (param.get("customerName2") != null) {
                        insertDetail.setCustomerNmTwo((String) param.get("customerName2"));
                    }
                    if (param.get("customerPhone2") != null) {
                        insertDetail.setCustomerTelTwo((String) param.get("customerPhone2"));
                    }

                    // 点击大定时 INSERT楼市号、面积、大定总价、成销总价  (insert 成销无效)
                    buildingNo = reportDetail.getBuildingNo();
                    areaStr = (String) param.get("saleAcreage");
                    roughAmountStr = reportDetail.getRoughAmount().toString();
                    dealAmountStr = (String) param.get("saleAmount");
                    roughAreaStr = reportDetail.getRoughArea().toString();
                    insertDetail.setBuildingNo(buildingNo);

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
                    reportDetailMapper.create(insertDetail);
                }
                buildingNo = reportDetail.getBuildingNo();

                areaStr = (String) param.get("saleAcreage");

                roughAmountStr = reportDetail.getRoughAmount().toString();
                dealAmountStr = (String) param.get("saleAmount");
                roughAreaStr = reportDetail.getRoughArea().toString();
                updateDetail.setBuildingNo(buildingNo);

                if (StringUtils.isNotBlank(areaStr))
                    updateDetail.setArea(new BigDecimal(areaStr));
                updateDetail.setRoughAmount(new BigDecimal(roughAmountStr));
                if (StringUtils.isNotBlank(dealAmountStr))
                    updateDetail.setDealAmount(new BigDecimal(dealAmountStr));
                if (StringUtils.isNotBlank(roughAreaStr))
                    updateDetail.setRoughArea(new BigDecimal(roughAreaStr));

                updateDetail.setCustomerNm((String) param.get("customerName1"));
                updateDetail.setCustomerTel((String) param.get("customerPhone1"));
                if (param.get("customerName2") != null) {
                    updateDetail.setCustomerNmTwo((String) param.get("customerName2"));
                }
                if (param.get("customerPhone2") != null) {
                    updateDetail.setCustomerTelTwo((String) param.get("customerPhone2"));
                }
                //结算确认日期
                if (param.get("settlementDate") != null) {
                    updateDetail.setSettleConfirmDate(DateUtil.getDate(param.get("settlementDate").toString(), "yyyy-MM-dd"));
                }
            }
        } else {
            updateDetail.setConfirmStatus(13602);
            updateDetail.setRecognitionDay(new Date());
        }
        count += reportDetailMapper.update(updateDetail);

        //更新带看、认筹、大定、成销时间
        ReportDetail updateTemp = new ReportDetail();
        updateTemp.setSeeDate(report.getSeeDate());
        updateTemp.setPledgedDate(report.getPledgedDate());
        updateTemp.setRoughInputDate(report.getRoughInputDate());
        updateTemp.setDealDate(report.getDealDate());
        updateTemp.setEstateId(estateId);
        updateTemp.setCountId(reportId);
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
        historyMap.put("customerNm", (String) param.get("customerName1"));
        historyMap.put("customerTel", (String) param.get("customerPhone1"));
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
                return "时间必须大于等于带看时间";
            }
        }

        // 认筹、大定、成销日期加入关账判断
        // added by wang kanlin 2017/09/12
        Map<?, ?> switchMonth = null;
        Date switchDate = new Date();
        int year = 0;
        int month = 0;
        try {
            Map<String, Object> queryParam = new HashMap<>();
            queryParam.put("cityNo", report.getCityNo());
            //switchMonth = sceneInCommissionMapper.getInCommissionSwitchMonth(queryParam);
            switchMonth = commonMapper.checkCitySwitchMonth(report.getCityNo());
            month = Integer.valueOf(switchMonth.get("month").toString());
            year = Integer.valueOf(switchMonth.get("year").toString());
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
                return "时间必须大于等于报备日期";
            }
            report.setSeeDate(date1);
        } else if ("13503".equals(status)) {//认筹
            if ((report.getPledgedBackDate() != null && date.before(report.getPledgedBackDate()))
                    || (report.getRoughBackDate() != null && date.before(report.getRoughBackDate()))
                    || (report.getDealBackDate() != null && date.before(report.getDealBackDate()))) {
                return "认筹时间必须大于等于之前的退筹/退定/退房时间，请重新填写！";
            }
            if (date.before(switchDate)) {
                return failDate.replace("y", year + "").replace("m", month + "");
            }
            report.setPledgedDate(date1);
        } else if ("13504".equals(status)) {//大定
            if (report.getPledgedDate() != null) {
                if (date.before(report.getPledgedDate())) {
                    return "时间必须大于等于认筹时间";
                }
            }
            if ((report.getPledgedBackDate() != null && date.before(report.getPledgedBackDate()))
                    || (report.getRoughBackDate() != null && date.before(report.getRoughBackDate()))
                    || (report.getDealBackDate() != null && date.before(report.getDealBackDate()))) {
                return "大定时间必须大于等于之前的退筹/退定/退房时间，请重新填写！";
            }
            if (date.before(switchDate)) {
                return failDate.replace("y", year + "").replace("m", month + "");
            }
            report.setRoughInputDate(date1);
        } else if ("13505".equals(status)) {//成销
            if (report.getRoughDate() != null) {
                if (date.before(report.getRoughDate())) {
                    return "时间必须大于等于大定时间";
                }
            }
            if ((report.getPledgedBackDate() != null && date.before(report.getPledgedBackDate()))
                    || (report.getRoughBackDate() != null && date.before(report.getRoughBackDate()))
                    || (report.getDealBackDate() != null && date.before(report.getDealBackDate()))) {
                return "成销时间必须大于等于之前的退筹/退定/退房时间，请重新填写！";
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
                    && "17403".equals(reportDb.getCustomerFrom().toString()) ){
                flag = true;
                crmDealDto.setCrmUserId(user.getUserId());
                crmDealDto.setCustomerCellNumber((String) queryParam.get("customerPhone1"));
                crmDealDto.setCustomerName((String) queryParam.get("customerName1"));
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                crmDealDto.setDealTime(dateFormat.parse( (String)queryParam.get("saleDate") ));
                crmDealDto.setHouseAddress(queryParam.get("floor").toString());
                crmDealDto.setHouseArea(new BigDecimal((String)queryParam.get("saleAcreage")));
                crmDealDto.setRegisterId(Long.parseLong(reportDb.getFyReportId()));
                BigDecimal dealAmount = new BigDecimal((String)queryParam.get("saleAmount"));
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
                yFStatusSync.setSuccess_sale_time((String) queryParam.get("saleDate"));
                yFStatusSync.setBuilding_house_code(queryParam.get("floor").toString());
                yFStatusSync.setBuilding_area((String) queryParam.get("saleAcreage"));
                yFStatusSync.setTotal_price((String) queryParam.get("saleAmount"));
                //新增字段 yf_report_id 2019/01/09  批量成销
                yFStatusSync.setYf_report_id(reportDb.getFyReportId());
                resultData = yFInterfaceService.getyFInterfaceInfo("/CRMReportStatusSync", JSON.toJSONString(yFStatusSync), "5", 0, reportDb.getReportId());
            } catch (Exception e) {
                resultData.setFail("操作异常");
                e.printStackTrace();
                logger.error("batchSaleService", "BatchSaleService", "dealCrm", "", null, "", "成销通知有房失败", e);
                return resultData;
            }
        }
        resultData.setReturnMsg(AppMsg.getString("COMMON.OPERATE_SUCCESS"));
        return resultData;
    }

    public ResultData<String> checkAccountProject(String param) {
        //获取请求参数
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        List<Map<String, Object>> listMap = (List<Map<String, Object>>) queryParam.get("accountProjectList");
        //返回
        ResultData<String> resultData = new ResultData<>();
        for (int i = 0; i < listMap.size(); i++) {
            //批量修改子表信息
            Integer num = batchSaleDetailMapper.checkAccountProjectByProjectNo(listMap.get(i).get("accountProject").toString(), queryParam.get("projectNo").toString());
            if (num < 1) {
                int size = i + 1;
                resultData.setReturnMsg("第" + size + "行房源填写的核算主体错误！");
                break;
            } else {
                resultData.setReturnMsg(null);
            }
        }
        return resultData;
    }
}