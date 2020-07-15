package cn.com.eju.pmls.skStatement.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.dao.LnkYjNyMapper;
import cn.com.eju.deal.scene.commission.dao.LnkYjYjssMapper;
import cn.com.eju.pmls.skStatement.dao.*;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateDtlDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateLogDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateMatDto;
import cn.com.eju.pmls.skStatement.dto.PmlsSkStatementDto;
import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateDtl;
import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateLog;
import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateMat;
import cn.com.eju.pmls.skStatement.model.PmlsSkStatementDtl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("skAllocateService")
public class SkAllocateService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsSkStatementMapper pmlsSkStatementMapper;

    @Autowired
    PmlsSkAllocateDtlMapper pmlsSkAllocateDtlMapper;

    @Autowired
    PmlsSkStatementDtlMapper pmlsSkStatementDtlMapper;

    @Autowired
    PmlsSkAllocateMatMapper pmlsSkAllocateMatMapper;

    @Autowired
    PmlsSkAllocateLogMapper pmlsSkAllocateLogMapper;

    @Autowired
    LnkYjYjssMapper lnkYjYjssMapper;

    @Autowired
    LnkYjNyMapper lnkYjNyMapper;

    @Autowired
    private CitySettingMapper citySettingMapper;

    @Autowired
    private CommonMapper commonMapper;

    /**
     * 按成销日期优先
     *
     * @param queryParam
     * @return
     */
    public ResultData<List<PmlsSkAllocateDtlDto>> getAllocateListForDeal(Map<String, Object> queryParam) {
        ResultData<List<PmlsSkAllocateDtlDto>> resultData = new ResultData<>();
        List<PmlsSkAllocateDtlDto> dtlList = pmlsSkAllocateDtlMapper.getAllocateListForDeal(queryParam);
        if(!CollectionUtils.isEmpty(dtlList)){
            for(PmlsSkAllocateDtlDto dtl : dtlList){
                dtl.setUnBackAmount_bef(dtl.getYjAmount_bef().subtract(dtl.getSjAmount_bef()));
                dtl.setUnBackAmount_aft(dtl.getYjAmount_aft().subtract(dtl.getSjAmount_aft()));
                dtl.setStayAmount_bef(dtl.getUnBackAmount_bef().subtract(dtl.getAllocatAmount_bef()));
                dtl.setStayAmount_aft(dtl.getUnBackAmount_aft().subtract(dtl.getAllocatAmount_aft()));
            }
        }
        resultData.setReturnData(dtlList);
        return resultData;
    }

    /**
     * 按房源定义
     *
     * @param queryParam
     * @return
     */
    public ResultData<List<PmlsSkAllocateDtlDto>> getAllocateListForBuilding(Map<String, Object> queryParam) {
        ResultData<List<PmlsSkAllocateDtlDto>> resultData = new ResultData<>();
        List<PmlsSkAllocateDtlDto> dtlList = pmlsSkAllocateDtlMapper.getAllocateListForBuilding(queryParam);
        resultData.setReturnData(dtlList);
        return resultData;
    }

    /**
     * 提交拆分
     * @param queryParam
     * @return
     */
    public ResultData<String> submitAllocate(Map<String, Object> queryParam) throws Exception{
        ResultData resultData = new ResultData();
        Integer userId = (Integer) queryParam.get("userId");

        //税率
        String cityNo = (String) queryParam.get("cityNo");
        Map<String, Object> citySetting = citySettingMapper.getCitySettingByCityNo(cityNo);
        if (citySetting == null || StringUtil.isEmpty(citySetting.get("taxRate").toString())) {
            resultData.setFail("该城市未配置税率！");
            return resultData;
        }
        BigDecimal tax = BigDecimal.valueOf((Integer) citySetting.get("taxRate"));//6
        tax = tax.divide(new BigDecimal(100));//6%
        tax = tax.add(BigDecimal.ONE);//1.06

        Map<String, Object> reportAllocateBef = JsonUtil.parseToObject((String) queryParam.get("reportObj"), Map.class);

        PmlsSkStatementDto dtl = pmlsSkStatementMapper.getSkStatementById(queryParam);
        //获取当前城市关账到的月份
        Map<?, ?> map = commonMapper.checkCitySwitchMonth(cityNo);
        String gzLastMonth = "";
        Date closeLastDay = null;
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        if (map != null && map.size() > 0) {
            gzLastMonth = map.get("year").toString() + "年" + map.get("month").toString() + "月";
            String closeLastDayStr = map.get("year").toString() + "-" + (Integer.valueOf((String) map.get("month")) + 1) + "-01";
            closeLastDay = format1.parse(closeLastDayStr);
        }
        Date recordDate = format1.parse(dtl.getRecordDate());
        if (closeLastDay.getTime() > recordDate.getTime()) {
            resultData.setFail("联动业务已关账至" + gzLastMonth + "，不能继续进行拆分，请确认！");
            return resultData;
        }

        queryParam.put("srId",queryParam.get("id"));
        List<PmlsSkAllocateDtlDto> dtlList = pmlsSkAllocateDtlMapper.getAllocateListForBuilding(queryParam);
        //check
        if (CollectionUtils.isEmpty(dtlList)) {
            resultData.setFail("没有可拆分的订单记录！");
            return resultData;
        }
        //1.校验:本次拆分总金额+已拆分总金额不能超过收款金额
        String allocatAmountBefStr = (String) queryParam.get("allocatAmountBef");
        BigDecimal allocatAmountAllBef = BigDecimal.valueOf(Double.valueOf(allocatAmountBefStr));//本次拆分总金额（税前）
        BigDecimal allocatAmountAllAft = allocatAmountAllBef.divide(tax, 2, BigDecimal.ROUND_HALF_UP);//本次拆分总金额（税后）= 本次拆分总金额（税前）/tax

        BigDecimal allocatedAmountBef = dtl.getAllocatedAmount_bef();//已拆分总金额（税前）
        BigDecimal skAmountBef = dtl.getSkAmount_bef();//收款金额（税前）
        BigDecimal statyAmountBef = skAmountBef.subtract(allocatedAmountBef);
        if (allocatAmountAllBef.compareTo(statyAmountBef) == 1) {
            resultData.setFail("本次拆分总金额:"+ allocatAmountAllBef +"不能超过待拆分金额:"+ statyAmountBef +"！");
            return resultData;
        }

        //2.校验:单条报备中本次拆分金额应在范围内
        List<PmlsSkAllocateDtl> allocateDtlList = new ArrayList<>();//PMLS_SkAllocate_dtl
        List<CommissionResultDto> commissionList = new ArrayList<>();//YJSS
        BigDecimal allocatAmountSumAft = BigDecimal.ZERO;
        for (PmlsSkAllocateDtlDto dto : dtlList) {
            BigDecimal allocatAmount_bef = dto.getAllocatAmount_bef() == null ? BigDecimal.ZERO : dto.getAllocatAmount_bef();
            BigDecimal neAllocatAmount_bef = dto.getNeAllocatAmount_bef() == null ? BigDecimal.ZERO : dto.getNeAllocatAmount_bef();
            String reportId = dto.getReportId();
            BigDecimal allocatAmount = BigDecimal.valueOf(Double.valueOf(reportAllocateBef.get(reportId).toString()));
            if (allocatAmount.compareTo(allocatAmount_bef) == 1 || allocatAmount.compareTo(neAllocatAmount_bef) == -1) {
                resultData.setFail(reportId + "该条报备本次拆分金额大于未回款金额，请调整后重新导入！！");
                return resultData;
            }

            //调整
            BigDecimal befAmount = allocatAmount;//单条报备的税前
            BigDecimal aftAmount = befAmount.divide(tax, 2, BigDecimal.ROUND_HALF_UP);//单条报备的税后
            allocatAmountSumAft = allocatAmountSumAft.add(aftAmount);//单条税后加起来最后和allocatAmountAllAft比较看是否需要调整

            //拆分数据准备批量插入
            PmlsSkAllocateDtl allocateDtl = new PmlsSkAllocateDtl();
            BeanUtils.copyProperties(dto, allocateDtl);
            allocateDtl.setAllocatAmount_bef(befAmount);
            allocateDtl.setAllocatAmount_aft(aftAmount);
            //allocateDtl.setParentId(mat.getId());主表ID放到下面set
            allocateDtl.setUserIdCreate(userId);
            allocateDtl.setRptDtlId(dto.getDetailId());
            allocateDtl.setUserIdUpdate(userId);
            allocateDtl.setDateUpdate(new Date());
            allocateDtlList.add(allocateDtl);

            //佣金数据准备批量插入
            CommissionResultDto cDto = new CommissionResultDto();
            cDto.setReportId(dto.getReportId());
            cDto.setDetailId(dto.getDetailId().toString());
            cDto.setNum(1);
            cDto.setRecordDate(dtl.getRecordDate());
            cDto.setBefTaxAmount(befAmount.toString());
            cDto.setAftTaxAmount(aftAmount.toString());
            cDto.setCrtEmpId(userId.toString());
            cDto.setUptEmpId(userId.toString());
            cDto.setCompanyNo(dto.getCompanyNo());
            commissionList.add(cDto);
        }

        //
        PmlsSkStatementDtl sDtl = new PmlsSkStatementDtl();
        sDtl.setId(dtl.getId());
        BigDecimal sDtlAllocate_bef = dtl.getAllocatedAmount_bef().add(allocatAmountAllBef);//总已拆金额税前
        BigDecimal sDtlAllocate_aft = dtl.getAllocatedAmount_aft().add(allocatAmountAllAft);//总已拆金额税后
        sDtl.setAllocatedAmount_bef(sDtlAllocate_bef);
        sDtl.setAllocatedAmount_aft(sDtlAllocate_aft);
        if (sDtlAllocate_bef.compareTo(BigDecimal.ZERO) == 0) {
            sDtl.setAllocatedFlag(0);//未拆分
        } else if (dtl.getSkAmount_bef().compareTo(sDtlAllocate_bef) == 0) {//收款金额和已拆金额一致时为拆分完毕
            sDtl.setAllocatedFlag(-1);//拆分完毕

            //判断需不需要大调整:当收款金额税后 > 总已拆金额税后时需要调整
            if (dtl.getSkAmount_aft().compareTo(sDtlAllocate_aft) == 1) {
                BigDecimal cha = dtl.getSkAmount_aft().subtract(sDtlAllocate_aft);
                sDtl.setAllocatedAmount_aft(dtl.getSkAmount_aft());//总已拆金额税后调整成和收款金额税后一致

                allocatAmountAllAft = allocatAmountAllAft.add(cha);//税后噶差到本单拆分单上
            }
        } else if (dtl.getSkAmount_bef().compareTo(sDtlAllocate_bef) == 1) {
            sDtl.setAllocatedFlag(1);//部分拆分
        }

        //allocatAmountSumAft < allocatAmountAllAft则需要调整
        if (Math.abs(allocatAmountSumAft.doubleValue()) != Math.abs(allocatAmountAllAft.doubleValue())) {
            BigDecimal cha = allocatAmountAllAft.subtract(allocatAmountSumAft);
            allocateDtlList.get(0).setAllocatAmount_aft(allocateDtlList.get(0).getAllocatAmount_aft().add(cha));//噶差在第一条上

            BigDecimal aftTaxAmount = BigDecimal.valueOf(Double.valueOf(commissionList.get(0).getAftTaxAmount()));//噶差在第一条上
            aftTaxAmount.add(cha);
            commissionList.get(0).setAftTaxAmount(aftTaxAmount.toString());
        }

        //PMLS_SkAllocate_mat
        PmlsSkAllocateMat mat = new PmlsSkAllocateMat();
        mat.setSkSerialNo(dtl.getSkSerialNo());
        mat.setAmount_bef(allocatAmountAllBef);
        mat.setAmount_aft(allocatAmountAllAft);
        Map<String, Object> serialMap = new HashMap<>();
        serialMap.put("skSerialNo", dtl.getSkSerialNo());
        String serialNo = pmlsSkAllocateMatMapper.getMaxSerialNo(serialMap);
        String suffix;
        if (StringUtil.isEmpty(serialNo)) {
            suffix = "000";
        } else {
            suffix = serialNo.substring(serialNo.indexOf("-") + 1);
        }
        int suffixNum = 1000 + Integer.valueOf(suffix) + 1;
        serialNo = dtl.getSkSerialNo() + "-" + String.valueOf(suffixNum).substring(1);
        mat.setSerialNo(serialNo);
        mat.setRecordDate(recordDate);
        mat.setUserIdCreate(userId);
        mat.setUserIdUpdate(userId);
        mat.setDateUpdate(new Date());
        pmlsSkAllocateMatMapper.insert(mat);

        for(PmlsSkAllocateDtl allocateDtl : allocateDtlList){
            allocateDtl.setParentId(mat.getId());
        }

        //PMLS_SkAllocate_dtl
        pmlsSkAllocateDtlMapper.batchCreate(allocateDtlList);

        //应计实收
        lnkYjYjssMapper.batchMergeCreate(commissionList);
        //同步LNK_Import表
        Map<String, Object> syncMap = new HashMap<>();
        syncMap.put("empId", userId);
        syncMap.put("tabName", "LNK_YJ_YJSS");
        lnkYjNyMapper.syncLnkImport(syncMap);

        //修改PMLS_SkStatement_dtl的已拆分金额和是否已拆分完毕
        sDtl.setUserIdCreate(Integer.valueOf(userId));
        sDtl.setDateUpdate(new Date());
        pmlsSkStatementDtlMapper.updateByPrimaryKeySelective(sDtl);

        //日志PMLS_SkAllocate_log
        PmlsSkAllocateLog log = new PmlsSkAllocateLog();
        log.setSkSerialNo(dtl.getSkSerialNo());
        log.setLogMsg(serialNo + "拆分金额:" + allocatAmountBefStr + "元");
        log.setUserIdCreate(Integer.valueOf(userId));
        pmlsSkAllocateLogMapper.insert(log);

        resultData.setSuccess("提交成功");
        return resultData;
    }


    public ResultData<List<PmlsSkAllocateMatDto>> recordList(Map<String, Object> queryParam) throws Exception {
        ResultData<List<PmlsSkAllocateMatDto>> resultData = new ResultData<List<PmlsSkAllocateMatDto>>();
        List<PmlsSkAllocateMatDto> skAllocateList = pmlsSkAllocateMatMapper.getSkAllocateListBySkSerialNo(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(skAllocateList);
        return resultData;
    }

    public ResultData<List<PmlsSkAllocateLogDto>> logList(Map<String, Object> queryParam) throws Exception {
        ResultData<List<PmlsSkAllocateLogDto>> resultData = new ResultData<List<PmlsSkAllocateLogDto>>();
        List<PmlsSkAllocateLogDto> skAllocateLogList = pmlsSkAllocateLogMapper.getSkAllocateLogList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(skAllocateLogList);
        return resultData;
    }


    public ResultData<String> cancelAll(Map<String, Object> queryParam) {

        ResultData<String> resultData = new ResultData<String>();

        String userId = queryParam.get("userId").toString();
        // 拆分ID
        Integer matId = Integer.parseInt(queryParam.get("id").toString());
        // 拆分明细
        List<PmlsSkAllocateDtl> dtlList = pmlsSkAllocateDtlMapper.getByParentId(matId);
        // 拆分记录
        PmlsSkAllocateMat mat = pmlsSkAllocateMatMapper.selectByPrimaryKey(matId);
        if (null == mat) {
            resultData.setFail("撤销失败，收款记录未找到或已删除！");
            return resultData;
        }else {
            // 收款单
            PmlsSkStatementDtl skInfo = pmlsSkStatementDtlMapper.getBySkSerialNo(mat.getSkSerialNo());

            // 撤销拆分明细报备单
            pmlsSkAllocateDtlMapper.deleteByParentId(matId);
            // 撤销拆分记录
            pmlsSkAllocateMatMapper.deleteById(matId);
            // 撤销收款单
            cancelSkStatement(skInfo, mat.getAmount_bef(), mat.getAmount_aft());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String recordDate = sdf.format(mat.getRecordDate());
            // 合并同步
            for (PmlsSkAllocateDtl dtl : dtlList) {
                mergeUpdate(dtl, userId, recordDate);
            }

            // 添加日志
            PmlsSkAllocateLog log = new PmlsSkAllocateLog();

            log.setSkSerialNo(mat.getSkSerialNo());
            log.setLogMsg(mat.getSerialNo() + "]已撤销,金额：" + mat.getAmount_bef().setScale(2).toString() + "元。");
            log.setDelFlag(false);
            log.setDateCreate(new Date());
            log.setUserIdCreate(Integer.parseInt(userId));
            log.setDateUpdate(new Date());
            log.setUserIdUpdate(Integer.parseInt(userId));
            pmlsSkAllocateLogMapper.insertSelective(log);


            Map<String, Object> map = new HashMap<>();
            map.put("tabName", "LNK_YJ_YJSR");
            lnkYjNyMapper.syncLnkImport(map);
        }
        return resultData;
    }

    public ResultData<String> cancelDtl(Map<String, Object> queryParam) {

        String userId = queryParam.get("userId").toString();


        ResultData<String> resultData = new ResultData<String>();
        // 拆分明细ID
        Integer dtlId = Integer.parseInt(queryParam.get("id").toString());
        // 拆分明细记录
        PmlsSkAllocateDtl dtl = pmlsSkAllocateDtlMapper.selectByPrimaryKey(dtlId);

        if(null==dtl){
            resultData.setFail("撤销失败，收款记录未找到或已删除！");
            return resultData;
        }else {
            // 拆分记录
            PmlsSkAllocateMat mat = pmlsSkAllocateMatMapper.selectByPrimaryKey(dtl.getParentId());
            // 收款单
            PmlsSkStatementDtl skInfo = pmlsSkStatementDtlMapper.getBySkSerialNo(mat.getSkSerialNo());

            // 撤销拆分明细报备单
            pmlsSkAllocateDtlMapper.deleteById(dtlId);

            // 添加日志
            PmlsSkAllocateLog log = new PmlsSkAllocateLog();

            log.setSkSerialNo(mat.getSkSerialNo());
            log.setLogMsg(mat.getSerialNo() + "中订单[" + dtl.getReportId() + "]已撤销,金额：" + dtl.getAllocatAmount_bef().setScale(2).toString() + "元。");
            log.setDelFlag(false);
            log.setDateCreate(new Date());
            log.setUserIdCreate(Integer.parseInt(userId));
            log.setDateUpdate(new Date());
            log.setUserIdUpdate(Integer.parseInt(userId));
            pmlsSkAllocateLogMapper.insertSelective(log);


            // 撤销拆分记录上的已拆金额
            cancelSkMat(mat, dtl.getAllocatAmount_bef(), dtl.getAllocatAmount_aft(), userId);
            // 撤销收款单上的已拆金额
            cancelSkStatement(skInfo, dtl.getAllocatAmount_bef(), dtl.getAllocatAmount_aft());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String recordDate = sdf.format(mat.getRecordDate());
            // 合并同步
            mergeUpdate(dtl, userId, recordDate);

            Map<String, Object> map = new HashMap<>();
            map.put("tabName", "LNK_YJ_YJSR");
            lnkYjNyMapper.syncLnkImport(map);


        }
        return resultData;
    }

    /**
     * 撤销拆分记录
     */
    private void cancelSkMat(PmlsSkAllocateMat mat, BigDecimal bef, BigDecimal aft , String userId) {

        BigDecimal amountBef = mat.getAmount_bef().subtract(bef);
        BigDecimal amountAft = mat.getAmount_aft().subtract(aft);

        PmlsSkAllocateMat matRecord = new PmlsSkAllocateMat();
        matRecord.setId(mat.getId());
        matRecord.setAmount_bef(amountBef);
        matRecord.setAmount_aft(amountAft);
        pmlsSkAllocateMatMapper.updateByPrimaryKeySelective(matRecord);

        // 如果 拆分明细中全部撤销了，那么拆分记录就删除
        Integer matId = mat.getId();
        List<PmlsSkAllocateDtl> dtlList = pmlsSkAllocateDtlMapper.getByParentId(matId);
        if (dtlList.size() == 0) {
            pmlsSkAllocateMatMapper.deleteById(matId);
            // 添加日志
            PmlsSkAllocateLog log = new PmlsSkAllocateLog();

            log.setSkSerialNo(mat.getSkSerialNo());
            log.setLogMsg(mat.getSerialNo() + "已撤销！");
            log.setDelFlag(false);
            log.setDateCreate(new Date());
            log.setUserIdCreate(Integer.parseInt(userId));
            log.setDateUpdate(new Date());
            log.setUserIdUpdate(Integer.parseInt(userId));
            pmlsSkAllocateLogMapper.insertSelective(log);
        }

    }

    /**
     * 撤销收款单
     */
    private void cancelSkStatement(PmlsSkStatementDtl skInfo, BigDecimal bef, BigDecimal aft) {
        // 收款单（已拆分金额）
        BigDecimal amountBef = skInfo.getAllocatedAmount_bef().subtract(bef);
        BigDecimal amountAft = skInfo.getAllocatedAmount_aft().subtract(aft);

        PmlsSkStatementDtl skRecord = new PmlsSkStatementDtl();
        skRecord.setId(skInfo.getId());
        skRecord.setAllocatedAmount_bef(amountBef);
        skRecord.setAllocatedAmount_aft(amountAft);
        if (amountBef.doubleValue() == 0) {
            skRecord.setAllocatedFlag(0);
        } else if (amountBef.doubleValue() == skInfo.getSkAmount_bef().doubleValue()) {
            skRecord.setAllocatedFlag(-1);
        } else {
            skRecord.setAllocatedFlag(1);
        }
        pmlsSkStatementDtlMapper.updateByPrimaryKeySelective(skRecord);

    }

    private void mergeUpdate(PmlsSkAllocateDtl dtl, String userId,String recordDate) {

        CommissionResultDto dto = new CommissionResultDto();

        BigDecimal amountBef = dtl.getAllocatAmount_bef().multiply(new BigDecimal(-1));
        BigDecimal amountAft = dtl.getAllocatAmount_aft().multiply(new BigDecimal(-1));

        dto.setReportId(dtl.getReportId());
        dto.setBefTaxAmount(amountBef.toString());
        dto.setAftTaxAmount(amountAft.toString());
        dto.setRecordDate(recordDate);
        dto.setCompanyNo(dtl.getCompanyNo());
        dto.setDetailId(dtl.getRptDtlId().toString());
        dto.setNum(1);
        dto.setCrtEmpId(userId);
        dto.setUptEmpId(userId);

        lnkYjYjssMapper.mergeUpdate(dto);
    }

    public ResultData<List<PmlsSkAllocateDtlDto>> querySkAllocateDtlList(Map<String, Object> queryParam) {
        ResultData<List<PmlsSkAllocateDtlDto>> resultData = new ResultData<>();
        List<PmlsSkAllocateDtlDto> dtlList = pmlsSkAllocateDtlMapper.querySkAllocateDtlList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(dtlList);
        return resultData;
    }
}
