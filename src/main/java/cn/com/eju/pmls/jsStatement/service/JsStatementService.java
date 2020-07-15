package cn.com.eju.pmls.jsStatement.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.pmls.jsStatement.dao.PmlsJsStatementDtlMapper;
import cn.com.eju.pmls.jsStatement.dao.PmlsJsStatementFileMapper;
import cn.com.eju.pmls.jsStatement.dao.PmlsJsStatementMapper;
import cn.com.eju.pmls.jsStatement.dao.PmlsStatementCashBillRelateMapper;
import cn.com.eju.pmls.jsStatement.dto.*;
import cn.com.eju.pmls.jsStatement.model.PmlsJsStatement;
import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementDtl;
import cn.com.eju.pmls.jsStatement.model.PmlsStatementCashBillRelate;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

@Service("jsStatementService")
public class JsStatementService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    PmlsJsStatementMapper pmlsJsStatementMapper;

    @Autowired
    PmlsJsStatementDtlMapper pmlsJsStatementDtlMapper;

    @Autowired
    PmlsJsStatementFileMapper pmlsJsStatementFileMapper;

    @Autowired
    PmlsStatementCashBillRelateMapper pmlsStatementCashBillRelateMapper;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Autowired
    private CitySettingMapper citySettingMapper;

    @Autowired
    private EstateMapper estateMapper;

    @Autowired
    private FileRecordMainMapper fileRecordMainMapper;


    /**
     * 查询
     */
    public ResultData<List<PmlsJsStatementDto>> queryList(Map<String, Object> queryParam) {
        ResultData<List<PmlsJsStatementDto>> resultData = new ResultData<List<PmlsJsStatementDto>>();
        List<PmlsJsStatementDto> list = pmlsJsStatementMapper.queryList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 作废
     */
    public ResultData toInvalid(PmlsJsStatement statement) throws Exception {
        ResultData resultData = new ResultData();
        Integer id = statement.getId();
        PmlsJsStatement record = pmlsJsStatementMapper.selectByPrimaryKey(id);
        // 10401:草稿的场合 or 10404:审核不通过的场合 可以修改为作废
        if ("10401".equals(record.getApproveStatus()) || "10404".equals(record.getApproveStatus())) {
            pmlsJsStatementMapper.updateByPrimaryKeySelective(statement);
        } else {
            resultData.setReturnCode("201");
            resultData.setReturnMsg("本结算单当前状态已变更！");
        }
        return resultData;
    }

    /**
     * 终止
     */
    public ResultData toTermination(PmlsJsStatement statement) throws Exception {
        ResultData resultData = new ResultData();
        Integer id = statement.getId();
        PmlsJsStatement record = pmlsJsStatementMapper.selectByPrimaryKey(id);
        // 10403: 审核通过 且 结算书没有被请款过 才可以修改为终止
        if ("10403".equals(record.getApproveStatus())) {
            PmlsStatementCashBillRelate relate = pmlsStatementCashBillRelateMapper.getTopByStatementId(id);
            if (relate == null) {
                pmlsJsStatementMapper.updateByPrimaryKeySelective(statement);
            } else {
                resultData.setReturnCode("202");
                resultData.setReturnMsg("本结算单已请款！");
            }
        } else {
            resultData.setReturnCode("201");
            resultData.setReturnMsg("本结算单当前状态已变更！");
        }
        return resultData;
    }


    public ResultData<List<PmlsJsCompanyDto>> queryJsCompanyList(Map<String, Object> queryParam) {

        ResultData<List<PmlsJsCompanyDto>> resultData = new ResultData<List<PmlsJsCompanyDto>>();
        List<PmlsJsCompanyDto> list = pmlsJsStatementDtlMapper.queryJsCompanyList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<PmlsJsProjectDto>> queryJsProjectList(Map<String, Object> queryParam) {

        ResultData<List<PmlsJsProjectDto>> resultData = new ResultData<List<PmlsJsProjectDto>>();
        List<PmlsJsProjectDto> list = pmlsJsStatementDtlMapper.queryJsProjectList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<PmlsJsHSCodeDto>> queryJsHSCodeList(Map<String, Object> queryParam) {

        ResultData<List<PmlsJsHSCodeDto>> resultData = new ResultData<List<PmlsJsHSCodeDto>>();
        List<PmlsJsHSCodeDto> list = pmlsJsStatementDtlMapper.queryJsHSCodeList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<PmlsJsKHCodeDto>> queryJsKHCodeList(Map<String, Object> queryParam) {

        ResultData<List<PmlsJsKHCodeDto>> resultData = new ResultData<List<PmlsJsKHCodeDto>>();
        List<PmlsJsKHCodeDto> list = pmlsJsStatementDtlMapper.queryJsKHCodeList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<PmlsJsFrameOaDto>> queryJsFrameOaList(Map<String, Object> queryParam) {

        ResultData<List<PmlsJsFrameOaDto>> resultData = new ResultData<List<PmlsJsFrameOaDto>>();
        List<PmlsJsFrameOaDto> list = pmlsJsStatementDtlMapper.queryJsFrameOaList(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<PmlsJsReportDto>> queryJsNormalReportList(Map<String, Object> queryParam) {

        this.overridePageMap(queryParam);
        ResultData<List<PmlsJsReportDto>> resultData = new ResultData<List<PmlsJsReportDto>>();
        List<PmlsJsReportDto> list = pmlsJsStatementDtlMapper.queryJsNormalReportList(queryParam);
        resultData.setTotalCount(queryParam.get(QueryConst.TOTAL_COUNT).toString());
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData<List<PmlsJsReportDto>> queryJsOffsetReportList(Map<String, Object> queryParam) {

        this.overridePageMap(queryParam);
        ResultData<List<PmlsJsReportDto>> resultData = new ResultData<List<PmlsJsReportDto>>();
        List<PmlsJsReportDto> list = pmlsJsStatementDtlMapper.queryJsOffsetReportList(queryParam);
        resultData.setTotalCount(queryParam.get(QueryConst.TOTAL_COUNT).toString());
        resultData.setReturnData(list);
        return resultData;
    }

    private Map<String, Object> overridePageMap(Map<String, Object> queryParam) {

        int pageIndex = 1;
        int pageSize = 10;
        if (queryParam.get("pageIndex") != null)
            pageIndex = Integer.parseInt(queryParam.get("pageIndex").toString());
        if (queryParam.get("pageSize") != null)
            pageSize = Integer.parseInt(queryParam.get("pageSize").toString());
        queryParam.remove("pageIndex");
        queryParam.remove("pageSize");

        queryParam.put("offset", (pageIndex - 1) * pageSize);
        queryParam.put("rows", pageSize);
        queryParam.put("totalCount", 0);

        return queryParam;
    }

    /**
     * desc:结算书详情
     * 2020年5月28日
     */
    public ResultData getJsStatementDetail(Map<String, Object> queryParam) {
        ResultData resultData = new ResultData<>();
        PmlsJsStatementDto pmlsJsStatementDto = pmlsJsStatementMapper.getJsStatementDetail(queryParam);
        List<PmlsJsStatementDtl> jsStatementDtlList = new ArrayList<>();
        if (!StringUtils.isEmpty(pmlsJsStatementDto)) {
            jsStatementDtlList = pmlsJsStatementDto.getJsStatementDtlList();

            BigDecimal jsCxAreaTotal = new BigDecimal(0.00);//面积总计
            BigDecimal jsCxAmountTotal = new BigDecimal(0.00);//签约总价合计
            BigDecimal jsContractYdAmountTotal = new BigDecimal(0.00);//合同约定金额合计
            BigDecimal jsJsAmountTotal = new BigDecimal(0.00);//本次结算金额合计
            BigDecimal jsKpAmountTotal = new BigDecimal(0.00);//实际开票金额合计
            BigDecimal kpTaxAmountTotal = new BigDecimal(0.00);//税额合计
            if (!CollectionUtils.isEmpty(jsStatementDtlList)) {
                for (PmlsJsStatementDtl pmlsJsStatementDtl : jsStatementDtlList) {
                    jsCxAreaTotal = jsCxAreaTotal.add(pmlsJsStatementDtl.getCxArea() == null ? new BigDecimal(0.00) : pmlsJsStatementDtl.getCxArea());
                    jsCxAmountTotal = jsCxAmountTotal.add(pmlsJsStatementDtl.getCxAmount() == null ? new BigDecimal(0.00) : pmlsJsStatementDtl.getCxAmount());
                    jsContractYdAmountTotal = jsContractYdAmountTotal.add(pmlsJsStatementDtl.getContractYdAmount() == null ? new BigDecimal(0.00) : pmlsJsStatementDtl.getContractYdAmount());
                    jsJsAmountTotal = jsJsAmountTotal.add(pmlsJsStatementDtl.getJsAmount() == null ? new BigDecimal(0.00) : pmlsJsStatementDtl.getJsAmount());
                    jsKpAmountTotal = jsKpAmountTotal.add(pmlsJsStatementDtl.getKpAmount() == null ? new BigDecimal(0.00) : pmlsJsStatementDtl.getKpAmount());
                    kpTaxAmountTotal = kpTaxAmountTotal.add(pmlsJsStatementDtl.getKpTaxAmount() == null ? new BigDecimal(0.00) : pmlsJsStatementDtl.getKpTaxAmount());
                }
                PmlsJsStatementDtl pmlsJsStatementDtlTotal = new PmlsJsStatementDtl();//合计列
                pmlsJsStatementDtlTotal.setCxArea(jsCxAreaTotal);
                pmlsJsStatementDtlTotal.setCxAmount(jsCxAmountTotal);
                pmlsJsStatementDtlTotal.setContractYdAmount(jsContractYdAmountTotal);
                pmlsJsStatementDtlTotal.setJsAmount(jsJsAmountTotal);
                pmlsJsStatementDtlTotal.setKpAmount(jsKpAmountTotal);
                pmlsJsStatementDtlTotal.setKpTaxAmount(kpTaxAmountTotal);
                jsStatementDtlList.add(pmlsJsStatementDtlTotal);
                pmlsJsStatementDto.setJsStatementDtlList(jsStatementDtlList);
            }
        }
        resultData.setReturnData(pmlsJsStatementDto);
        return resultData;
    }

    /**
     * desc:获取详情中得请款单对应得数据
     * 2020年6月1日
     */
    public ResultData<Map<String, Object>> getCashBillDeatilByCashBillNo(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();
        Map<String, Object> list = pmlsJsStatementMapper.getCashBillDeatilByCashBillNo(map);
        resultData.setReturnData(list);
        return resultData;
    }

    /* 取得总控数据信息*/
    public ResultData<zkVaildAmount> getZkInfo(Map<String, Object> queryParam) {
        ResultData<zkVaildAmount> resultData = new ResultData<>();
        zkVaildAmount info = pmlsJsStatementDtlMapper.getZkInfo(queryParam);
        resultData.setReturnData(info);
        return resultData;

    }

    public ResultData save(PmlsJsStatementVto statement) {
        logger.info("结算书新增处理：statement;" + JsonUtil.parseToJson(statement));
        ResultData resultData = new ResultData();

        // 1.保存校验
        if (checkSave(statement, resultData) == false) {
            return resultData;
        }

        // 2.补充信息
        improveSaveInfo(statement, true);

        // 3.新增结算主表信息
        PmlsJsStatement record = new PmlsJsStatement();
        BeanUtils.copyProperties(statement, record);
        logger.info("结算书新增处理：record;" + JsonUtil.parseToJson(record));
        pmlsJsStatementMapper.insertSelective(record);

        // 4.新增结算详细信息
        List<PmlsJsStatementDtl> list = statement.getReportList();
        if (CollectionUtils.isNotEmpty(list)) {
            for (PmlsJsStatementDtl dtl : list) {
                dtl.setJssId(record.getId());
                dtl.setUserIdCreate(statement.getUserId());
                dtl.setUserIdUpdate(statement.getUserId());
                dtl.setDateCreate(new Date());
                dtl.setDateUpdate(new Date());
                dtl.setDelFlag(false);
                pmlsJsStatementDtlMapper.insertSelective(dtl);
            }
        }

        // 5.删除结算书文件
        pmlsJsStatementFileMapper.deleteByJssId(record.getId());

        //6. 保存提交文件
        if (statement.getFileRecordMainIds() != null && StringUtil.isNotEmpty(statement.getFileRecordMainIds())) {
            String[] fileIdArr = statement.getFileRecordMainIds().split(",");
            List<Integer> fileIdList = new ArrayList<>();
            for (String fileId : fileIdArr) {
                fileIdList.add(Integer.valueOf(fileId));
            }
            Map<String, Object> uptmap = new HashMap<>();
            uptmap.put("contractId", record.getId());
            uptmap.put("ids", fileIdList);
            fileRecordMainMapper.updateByCondition(uptmap);
        }

        // 7.提交场合OA处理
        if (1 == statement.getSubmitStatus()) {

            submitToOaDeal();
        }

        return resultData;

    }

    // 保存校验
    private boolean checkSave(PmlsJsStatementVto statement, ResultData resultData) {

        // 渠道公司
        if (statement.getCompanyId() == null || StringUtil.isEmpty(statement.getCompanyNo()) || StringUtil.isEmpty(statement.getCompanyName())) {
            resultData.setFail("请选择渠道公司!");
            return false;
        }

        // 项目
        if (StringUtil.isEmpty(statement.getProjectNo()) || StringUtil.isEmpty(statement.getProjectName())) {
            resultData.setFail("请选择项目！");
            return false;
        }

        // 提交的场合校验
        if (1 == statement.getSubmitStatus()) {
            // 核算主体
            if (StringUtil.isEmpty(statement.getHsCode()) || StringUtil.isEmpty(statement.getHsName())) {
                resultData.setFail("请选择核算主体！");
                return false;
            }
            // 考核主体
            if (StringUtil.isEmpty(statement.getKhCode()) || StringUtil.isEmpty(statement.getKhName())) {
                resultData.setFail("请选择考核主体！");
                return false;
            }
            // 合同
            if (StringUtil.isEmpty(statement.getFrameOaName()) || StringUtil.isEmpty(statement.getFrameOaName())) {
                resultData.setFail("请选择合同！");
                return false;
            }
            // 供应商
            if (StringUtil.isEmpty(statement.getVender_id()) || StringUtil.isEmpty(statement.getVender_code()) || StringUtil.isEmpty(statement.getVender_name())) {
                resultData.setFail("请确认供应商！");
                return false;
            }
            // 供应商税率
            if (statement.getKpRate() == null) {
                resultData.setFail("请选择供应商税率！");
                return false;
            }
            //是否包含冲抵结算
            if (statement.getOffSetFlag() == null) {
                resultData.setFail("请选择[是否包含冲抵结算]！");
                return false;
            }

            // 文件必填校验
            if (StringUtil.isEmpty(statement.getFileRecordMainIds())) {
                resultData.setFail("请上传必填文件！");
                return false;
            }


            // 大定垫佣
            if (StringUtil.isEmpty(statement.getIsSpecialProject())) {
                statement.setIsSpecialProject("0");
            }
            BigDecimal vaildFYAmountTotal = new BigDecimal("0"); // 正常结算可返金额(总控)
            BigDecimal vaildDYAmountTotal = new BigDecimal("0"); // 正常结算可垫金额(总控)

            Map<String, Object> dtlParam = new HashMap<>();
            dtlParam.put("companyNo", statement.getCompanyNo());
            dtlParam.put("projectNo", statement.getProjectNo());
            dtlParam.put("offset", 0);
            dtlParam.put("rows", 100);
            dtlParam.put("totalCount", 0);
            int normalSize = 0;
            int offsetSize = 0;
            BigDecimal contractYdTotalAmount = new BigDecimal("0");
            BigDecimal jsTotalAmount = new BigDecimal("0");
            BigDecimal kpTotalAmount = new BigDecimal("0");
            BigDecimal kpTotalTaxAmount = new BigDecimal("0");


            String normal_reportIdList = "";
            String offset_reportIdList = "";

            Map<String, PmlsJsReportDto> normalMap = new HashMap<>();
            Map<String, PmlsJsReportDto> offsetMap = new HashMap<>();

            for (PmlsJsStatementDtl d : statement.getReportList()) {
                if (d.getOffSetFlag().equals(0)) {
                    normal_reportIdList = normal_reportIdList + d.getReportId().toString() + ',';
                }
                if (d.getOffSetFlag().equals(1)) {
                    offset_reportIdList = offset_reportIdList + d.getReportId().toString() + ',';
                }
            }


            if (normal_reportIdList.length() > 0) {
                normal_reportIdList.substring(0, normal_reportIdList.length() - 1);
                dtlParam.put("reportNo", normal_reportIdList);
                logger.info("#pmlsJsStatementDtlMapper.queryJsNormalReportList(dtlParam) Start");
                List<PmlsJsReportDto> jsNormalList = pmlsJsStatementDtlMapper.queryJsNormalReportList(dtlParam);
                logger.info("#pmlsJsStatementDtlMapper.queryJsNormalReportList(dtlParam) End");
                for (PmlsJsReportDto dto : jsNormalList) {
                    normalMap.put(dto.getReportId(), dto);
                }
            }

            if (offset_reportIdList.length() > 0) {
                offset_reportIdList.substring(0, offset_reportIdList.length() - 1);
                dtlParam.put("reportNo", offset_reportIdList);
                logger.info("#pmlsJsStatementDtlMapper.queryJsOffsetReportList(dtlParam) Start");
                List<PmlsJsReportDto> jsOffsetList = pmlsJsStatementDtlMapper.queryJsOffsetReportList(dtlParam);
                logger.info("#pmlsJsStatementDtlMapper.queryJsOffsetReportList(dtlParam) End");
                for (PmlsJsReportDto dto : jsOffsetList) {
                    offsetMap.put(dto.getReportId(), dto);
                }
            }

            for (PmlsJsStatementDtl dtl : statement.getReportList()) {
                String reportNo = dtl.getReportId();
                String block = dtl.getOffSetFlag() == 0 ? "正常结算：" : "冲抵结算";

                //region 共同校验项目
                // 服务费比例
                if (StringUtil.isEmpty(dtl.getServiceFeeDes())) {
                    resultData.setFail(block + "请输入订单编号[" + reportNo + "]的服务费比例！");
                    return false;
                }
                // 合同约定金额
                if (dtl.getContractYdAmount() == null) {
                    resultData.setFail(block + "请输入订单编号[" + reportNo + "]的合同约定金额！");
                    return false;
                }
                // 本次结算金额
                if (dtl.getJsAmount() == null) {
                    resultData.setFail(block + "请输入订单编号[" + reportNo + "]的本次结算金额！");
                    return false;
                }
                // 实际开票金额
                if (dtl.getKpAmount() == null) {
                    resultData.setFail(block + "请输入订单编号[" + reportNo + "]的实际开票金额！");
                    return false;
                }
                // 实际税额
                if (dtl.getKpTaxAmount() == null) {
                    resultData.setFail(block + "请输入订单编号[" + reportNo + "]的实际税额！");
                    return false;
                }
                // 开票税后
                BigDecimal kpTaxAmountAfter = dtl.getKpAmount().subtract(dtl.getKpTaxAmount()).setScale(2, BigDecimal.ROUND_HALF_UP);
                if (dtl.getKpTaxAmountAfter() == null || kpTaxAmountAfter.doubleValue() != dtl.getKpTaxAmountAfter().doubleValue()) {
                    dtl.setKpTaxAmountAfter(kpTaxAmountAfter);
                }

                // 结算类型
                if (dtl.getJsStatementType() == null || (dtl.getJsStatementType() != 1 && dtl.getJsStatementType() != 2)) {
                    resultData.setFail(block + "订单编号[" + reportNo + "]不符合结算条件，请从列表中删除。");
                    return false;
                }
                // 税率区间
                if (!checkRate(dtl.getKpTaxAmount(), dtl.getKpAmount())) {
                    resultData.setFail(block + "订单编号[" + reportNo + "]税率没有落在税率区间内 请修改!");
                    return false;
                }

                // 本次结算金额：值小于或等于合同约定金额
                if (Math.abs(dtl.getJsAmount().doubleValue()) > Math.abs(dtl.getContractYdAmount().doubleValue())) {
                    resultData.setFail(block + "订单编号[" + reportNo + "]的本次结算金额应当小于或等于合同约定金额！");
                    return false;
                }
                // 实际开票金额：值小于等于本次结算金额
                if (Math.abs(dtl.getKpAmount().doubleValue()) > Math.abs(dtl.getJsAmount().doubleValue())) {
                    resultData.setFail(block + "订单编号[" + reportNo + "]的实际开票金额应当小于或等于本次结算金额！");
                    return false;
                }
                // 实际税额：值小于等于实际开票金额
                if (Math.abs(dtl.getKpTaxAmount().doubleValue()) > Math.abs(dtl.getKpAmount().doubleValue())) {
                    resultData.setFail(block + "订单编号[" + reportNo + "]的实际税额应当小于或等于实际开票金额！");
                    return false;
                }

                // 核算主体
                if (dtl.getHsCode() == null || dtl.getHsName() == null) {
                    resultData.setFail(block + "请选择订单编号[" + reportNo + "]的核算主体！");
                    return false;
                }

                //endregion

                //region 正常结算
                if (dtl.getOffSetFlag() == 0) {
                    normalSize++;
                    // 合同约定金额>0
                    if (dtl.getContractYdAmount().doubleValue() <= 0) {
                        resultData.setFail(block + "订单编号[" + reportNo + "]的合同约定金额应大于0！");
                        return false;
                    }
                    //本次结算金额>0
                    if (dtl.getJsAmount().doubleValue() <= 0) {
                        resultData.setFail(block + "订单编号[" + reportNo + "]的本次结算金额应大于0！");
                        return false;
                    }
                    // 实际开票金额
                    if (dtl.getKpAmount().doubleValue() <= 0) {
                        resultData.setFail(block + "订单编号[" + reportNo + "]的实际开票金额应大于0！");
                        return false;
                    }
                    // 实际税额
                    if (dtl.getKpTaxAmount().doubleValue() < 0) {
                        resultData.setFail(block + "订单编号[" + reportNo + "]的实际税额应大于0！");
                        return false;
                    }
                    PmlsJsReportDto db = new PmlsJsReportDto();
                    if (normalMap.containsKey(dtl.getReportId())) {
                        db = normalMap.get(dtl.getReportId());
                    }
                    if (db.getVaildDYAmount() == null) db.setVaildDYAmount(new BigDecimal("0"));
                    if (db.getVaildFYAmount() == null) db.setVaildFYAmount(new BigDecimal("0"));
                    if (db.getOldJsTotalAmount() == null) db.setOldJsTotalAmount(new BigDecimal("0"));

                    // 历史结算金额总和 + 本次结算金额<=本次合同约定金额
                    if (db.getOldJsTotalAmount().add(dtl.getJsAmount()).doubleValue() > dtl.getContractYdAmount().doubleValue()) {
                        resultData.setFail(block + "订单编号[" + reportNo + "]的历史结算金额总和与本次结算金额之和应当小于或等于本次合同约定金额！");
                        return false;
                    }

                    // 返佣
                    if (dtl.getJsStatementType() == 1) {
                        if (dtl.getJsAmount().doubleValue() > db.getVaildFYAmount().doubleValue()) {
                            resultData.setFail(block + "订单编号[" + reportNo + "]本次结算金额不能大于可返金额！");
                            return false;
                        }
                        if (dtl.getIsGlobalControl().equals("1")) {
                            vaildFYAmountTotal = vaildFYAmountTotal.add(dtl.getJsAmount());
                        }
                    }
                    // 垫佣
                    else if (dtl.getJsStatementType() == 2) {
                        if (!statement.getIsSpecialProject().equals("1") && !statement.getIsSpecialProject().equals("2")) {
                            if (dtl.getJsAmount().doubleValue() > db.getVaildDYAmount().doubleValue()) {
                                resultData.setFail(block + "订单编号[" + reportNo + "]本次结算金额不能大于可垫金额！");
                                return false;
                            }
                            if (dtl.getIsGlobalControl().equals("1")) {
                                vaildDYAmountTotal = vaildDYAmountTotal.add(dtl.getJsAmount());
                            }
                        }
                    }

                }
                //endregion

                //region 冲抵结算
                if (dtl.getOffSetFlag() == 1) {
                    offsetSize++;
                    // 合同约定金额>0
                    if (dtl.getContractYdAmount().doubleValue() >= 0) {
                        resultData.setFail(block + "订单编号" + reportNo + "的合同约定金额应小于0！");
                        return false;
                    }
                    //本次结算金额>0
                    if (dtl.getJsAmount().doubleValue() >= 0) {
                        resultData.setFail(block + "订单编号" + reportNo + "的本次结算金额应小于0！");
                        return false;
                    }
                    // 实际开票金额
                    if (dtl.getKpAmount().doubleValue() >= 0) {
                        resultData.setFail(block + "订单编号" + reportNo + "的实际开票金额应小于0！");
                        return false;
                    }
                    // 实际税额
                    if (dtl.getKpTaxAmount().doubleValue() > 0) {
                        resultData.setFail(block + "订单编号" + reportNo + "的实际税额应小于0！");
                        return false;
                    }

                    PmlsJsReportDto db = new PmlsJsReportDto();
                    if (offsetMap.containsKey(dtl.getReportId())) {
                        db = offsetMap.get(dtl.getReportId());
                    }
                    if (db.getVaildDYAmount() == null) db.setVaildDYAmount(new BigDecimal("0"));
                    if (db.getVaildFYAmount() == null) db.setVaildFYAmount(new BigDecimal("0"));
                    if (db.getOldJsTotalAmount() == null) db.setOldJsTotalAmount(new BigDecimal("0"));

                    // 返佣
                    if (dtl.getJsStatementType() == 1) {
                        if (Math.abs(dtl.getJsAmount().doubleValue()) > Math.abs(db.getVaildFYAmount().doubleValue())) {
                            resultData.setFail(block + "订单编号[" + reportNo + "]本次结算金额不能超出实际可返金额！");
                            return false;
                        }
                    }
                    // 垫佣
                    else if (dtl.getJsStatementType() == 2) {
                        if (Math.abs(dtl.getJsAmount().doubleValue()) > Math.abs(db.getVaildDYAmount().doubleValue())) {
                            resultData.setFail(block + "订单编号[" + reportNo + "]本次结算金额不能超出实际可垫金额！");
                            return false;
                        }
                    }
                }
                //endregion

                contractYdTotalAmount = contractYdTotalAmount.add(dtl.getContractYdAmount());
                jsTotalAmount = jsTotalAmount.add(dtl.getJsAmount());
                kpTotalAmount = kpTotalAmount.add(dtl.getKpAmount());
                kpTotalTaxAmount = kpTotalTaxAmount.add(dtl.getKpTaxAmount());
            }

            if (normalSize == 0) {
                resultData.setFail("请选择正常结算订单!");
                return false;
            }
            if (statement.getOffSetFlag() == 1) {
                if (offsetSize == 0) {
                    resultData.setFail("请选择冲抵结算订单!");
                    return false;
                }
            } else {
                if (offsetSize > 0) {
                    resultData.setFail("请确认[是否包含冲抵结算]！");
                    return false;
                }
            }
            statement.setContractYdTotalAmount(contractYdTotalAmount);
            statement.setJsTotalAmount(jsTotalAmount);
            if (statement.getJsTotalAmount().doubleValue() < 0) {
                resultData.setFail("请确认[本次结算金额总计不能小于零]！");
                return false;
            }
            statement.setKpTotalAmount(kpTotalAmount);
            if (statement.getKpTotalAmount().doubleValue() < 0) {
                resultData.setFail("请确认[实际开票金额总计不能小于零]！");
                return false;
            }
            statement.setKpTotalTaxAmount(kpTotalTaxAmount);
            if (statement.getKpTotalTaxAmount().doubleValue() < 0) {
                resultData.setFail("请确认[实际开票税额总计不能小于零]！");
                return false;
            }
            Map<String, Object> zkParam = new HashMap<>();
            zkParam.put("projectNo", statement.getProjectNo());
            zkParam.put("companyNo", statement.getCompanyNo());
            zkVaildAmount zkInfo = pmlsJsStatementDtlMapper.getZkInfo(zkParam);
            if (zkInfo == null) {
                if (zkInfo.getCom_ZK_vaildDYAmount() == null) zkInfo.setCom_ZK_vaildDYAmount(new BigDecimal("0"));
                if (zkInfo.getCom_ZK_vaildDYAmount() == null) zkInfo.setCom_ZK_vaildDYAmount(new BigDecimal("0"));
            }
            // 总控公司可垫金额 > 本次计算金额（总控& 垫佣结算）
            if (!statement.getIsSpecialProject().equals("1") && !statement.getIsSpecialProject().equals("2")) {
                if (vaildDYAmountTotal.doubleValue() > 0 && zkInfo.getCom_ZK_vaildDYAmount().doubleValue() < vaildDYAmountTotal.doubleValue()) {
                    resultData.setFail("正常结算：本次计算金额的总控垫佣结算金额不能大于总控公司可垫金额!");
                    return false;
                }
            }
            // 总控公司可返金额 > 本次计算金额（总控& 返佣结算）
            if (vaildFYAmountTotal.doubleValue() > 0 && zkInfo.getCom_ZK_vaildFYAmount().doubleValue() < vaildFYAmountTotal.doubleValue()) {
                resultData.setFail("正常结算：本次计算金额的总控返佣结算金额不能大于总控公司可返金额!");
                return false;
            }
        }
        return true;
    }

    private boolean checkRate(BigDecimal kpTaxAmount, BigDecimal kpAmount) {
        BigDecimal taxrate = new BigDecimal("0");
        if (kpAmount.doubleValue() != 0) {
            taxrate = kpTaxAmount.divide(kpAmount, 6, BigDecimal.ROUND_HALF_UP);
        }

        if ((taxrate.doubleValue() >= 0.166 && taxrate.doubleValue() <= 0.175) ||
                (taxrate.doubleValue() >= 0.156 && taxrate.doubleValue() <= 0.165) ||
                (taxrate.doubleValue() >= 0.126 && taxrate.doubleValue() <= 0.135) ||
                (taxrate.doubleValue() >= 0.106 && taxrate.doubleValue() <= 0.115) ||
                (taxrate.doubleValue() >= 0.096 && taxrate.doubleValue() <= 0.105) ||
                (taxrate.doubleValue() >= 0.056 && taxrate.doubleValue() <= 0.065) ||
                (taxrate.doubleValue() >= 0.046 && taxrate.doubleValue() <= 0.055) ||
                (taxrate.doubleValue() >= 0.026 && taxrate.doubleValue() <= 0.035) ||
                (taxrate.doubleValue() >= 0.012 && taxrate.doubleValue() <= 0.017) ||
                (taxrate.doubleValue() >= 0.008 && taxrate.doubleValue() <= 0.012) ||
                taxrate.doubleValue() == 0) {
            return true;

        }
        return false;
    }

    // 补充相关信息
    private void improveSaveInfo(PmlsJsStatementVto statement, boolean flag) {
        // flag: 新增的场合
        if (flag) {
            Map<String, Object> citySetting = citySettingMapper.getCitySettingByCityNo(statement.getCityNo());
            String jssTemplate = "";
            String jssTemplateName = "";
            if (citySetting != null) {
                if (citySetting.containsKey("jssTemplate") && citySetting.get("jssTemplate") != null) {
                    jssTemplate = citySetting.get("jssTemplate").toString();
                }
                if (citySetting.containsKey("jssTemplateName") && citySetting.get("jssTemplateName") != null) {
                    jssTemplateName = citySetting.get("jssTemplateName").toString();
                }
            }
            statement.setTemplateCode(jssTemplate);
            statement.setTemplateName(jssTemplateName);

            statement.setJssNo(seqNoAPI.getSeqNoByTypeCode("PMLS_JSS_NO").getReturnData());         // 结算书编号
            statement.setPlate("FCJY");      // 版块
            statement.setJyTotalAmount(new BigDecimal("0"));
            statement.setUserIdCreate(statement.getUserId());
            statement.setDateCreate(new Date());
        }
        statement.setApprovedDate(null);
        statement.setUserIdUpdate(statement.getUserId());
        statement.setDateUpdate(new Date());
        statement.setDelFlag(false);
        statement.setErrmsg(null);
        statement.setFlowId(null);

        // 提交的场合
        if (statement.getSubmitStatus() == 1) {
            statement.setOaNo(seqNoAPI.getSeqNoByTypeCode("PMLS_JSS_OANO").getReturnData());    // 结算书OA 编号
            statement.setFormState(0);
            statement.setOaStartDate(new Date());
            statement.setApproveStatus("10402");
            statement.setSendUserCode(statement.getUserCode());
            statement.setSendUserName(statement.getUserName());
            statement.setSqdate(new Date());
        } else {
            statement.setOaNo(null);
            statement.setFormState(null);
            statement.setOaStartDate(null);
            statement.setApproveStatus("10401");
            statement.setSendUserCode(null);
            statement.setSendUserName(null);
            statement.setSqdate(null);
        }
    }

    // 结算书信息
    public ResultData<PmlsJsStatementBo> getJsStatementById(Map<String, Object> queryParam) throws Exception {
        ResultData<PmlsJsStatementBo> resultData = new ResultData<PmlsJsStatementBo>();
        PmlsJsStatementBo pmlsJsStatementBo = new PmlsJsStatementBo();
        Integer id = Integer.parseInt(queryParam.get("id").toString());

        PmlsJsStatement info = pmlsJsStatementMapper.selectByPrimaryKey(id);
        if (info != null) {
            if (info.getDateUpdate() != null) {
                pmlsJsStatementBo.setExclude(info.getDateUpdate().getTime());
            } else {
                pmlsJsStatementBo.setExclude((new Date()).getTime());
            }
            // 项目信息
            Estate project = estateMapper.getByProjectNo(info.getProjectNo());
            BeanUtils.copyProperties(info, pmlsJsStatementBo);
            pmlsJsStatementBo.setIsSpecialProject(project.getIsSpecialProject());

            // 核算主体
            Map<String, Object> hsParam = new HashMap<>();
            hsParam.put("projectNo", info.getProjectNo());
            List<PmlsJsHSCodeDto> hsCodelist = pmlsJsStatementDtlMapper.queryJsHSCodeList(hsParam);
            pmlsJsStatementBo.setHsCodeList(hsCodelist);

            // 总控数据
            Map<String, Object> zkParam = new HashMap<>();
            zkParam.put("projectNo", info.getProjectNo());
            zkParam.put("companyNo", info.getCompanyNo());
            zkVaildAmount zkInfo = pmlsJsStatementDtlMapper.getZkInfo(zkParam);
            pmlsJsStatementBo.setZkInfo(zkInfo);
        }
        resultData.setReturnData(pmlsJsStatementBo);
        return resultData;
    }

    // 结算书明细信息
    public ResultData<PmlsJsStatementBo> getJsStatementDtlById(Map<String, Object> queryParam) throws Exception {
        ResultData<PmlsJsStatementBo> resultData = new ResultData<PmlsJsStatementBo>();
        PmlsJsStatementBo pmlsJsStatementBo = new PmlsJsStatementBo();
        List<PmlsJsStatementDtlBo> normalList = new ArrayList<>();
        List<PmlsJsStatementDtlBo> offsetList = new ArrayList<>();
        Integer id = Integer.parseInt(queryParam.get("id").toString());
        logger.info("#pmlsJsStatementMapper.selectByPrimaryKey(id);Start");
        PmlsJsStatement info = pmlsJsStatementMapper.selectByPrimaryKey(id);
        logger.info("#pmlsJsStatementMapper.selectByPrimaryKey(id);End");
        if (info != null) {
            BeanUtils.copyProperties(info, pmlsJsStatementBo);

            logger.info("#pmlsJsStatementMapper.getJsStatementDtlById(queryParam);Start");
            List<PmlsJsStatementDtl> reportList = pmlsJsStatementDtlMapper.getJsStatementDtlById(queryParam);
            logger.info("#pmlsJsStatementMapper.getJsStatementDtlById(queryParam); END");
            // region 详情记录
            Map<String, Object> dtlParam = new HashMap<>();
            dtlParam.put("companyNo", info.getCompanyNo());
            dtlParam.put("projectNo", info.getProjectNo());
            dtlParam.put("offset", 0);
            dtlParam.put("rows", 100);
            dtlParam.put("totalCount", 0);

            String normal_reportIdList = "";
            String offset_reportIdList = "";
            List<String> projectNoList = new ArrayList<>();

            Map<String, PmlsJsReportDto> normalMap = new HashMap<>();
            Map<String, PmlsJsReportDto> offsetMap = new HashMap<>();
            Map<String, List<PmlsJsHSCodeDto>> jsHSMap = new HashMap<>();

            for (PmlsJsStatementDtl d : reportList) {
                if (d.getOffSetFlag().equals(0)) {
                    normal_reportIdList = normal_reportIdList + d.getReportId().toString() + ',';
                }
                if (d.getOffSetFlag().equals(1)) {
                    offset_reportIdList = offset_reportIdList + d.getReportId().toString() + ',';
                    projectNoList.add(d.getProjectCode());
                }
            }
            if (normal_reportIdList.length() > 0) {
                normal_reportIdList.substring(0, normal_reportIdList.length() - 1);
                dtlParam.put("reportNo", normal_reportIdList);
                logger.info("#pmlsJsStatementDtlMapper.queryJsNormalReportList(dtlParam) Start");
                List<PmlsJsReportDto> jsNormalList = pmlsJsStatementDtlMapper.queryJsNormalReportList(dtlParam);
                logger.info("#pmlsJsStatementDtlMapper.queryJsNormalReportList(dtlParam) End");
                for (PmlsJsReportDto dto : jsNormalList) {
                    normalMap.put(dto.getReportId(), dto);
                }
            }

            if (offset_reportIdList.length() > 0) {
                offset_reportIdList.substring(0, offset_reportIdList.length() - 1);
                dtlParam.put("reportNo", offset_reportIdList);
                logger.info("#pmlsJsStatementDtlMapper.queryJsOffsetReportList(dtlParam) Start");
                List<PmlsJsReportDto> jsOffsetList = pmlsJsStatementDtlMapper.queryJsOffsetReportList(dtlParam);
                logger.info("#pmlsJsStatementDtlMapper.queryJsOffsetReportList(dtlParam) End");
                for (PmlsJsReportDto dto : jsOffsetList) {
                    offsetMap.put(dto.getReportId(), dto);
                }
                Map<String, Object> hsParam = new HashMap<>();
                hsParam.put("projectNoList", projectNoList);
                logger.info("#pmlsJsStatementDtlMapper.queryJsHSCodeList(hsParam) Start");
                List<PmlsJsHSCodeDto> hsCodelist = pmlsJsStatementDtlMapper.queryJsHSCodeList(hsParam);
                logger.info("#pmlsJsStatementDtlMapper.queryJsHSCodeList(hsParam) End");
                for (PmlsJsHSCodeDto dto : hsCodelist) {
                    List<PmlsJsHSCodeDto> tmp = new ArrayList<>();
                    if (jsHSMap.containsKey(dto.getXmCode())) {
                        tmp = jsHSMap.get(dto.getXmCode());
                    }
                    tmp.add(dto);
                    jsHSMap.put(dto.getXmCode(), tmp);
                }
            }
            //endregion
            logger.info(" #for (PmlsJsStatementDtl dtl : reportList) Start");
            for (PmlsJsStatementDtl dtl : reportList) {
                PmlsJsStatementDtlBo record = new PmlsJsStatementDtlBo();
                BeanUtils.copyProperties(dtl, record);

                // 正常结算
                if (dtl.getOffSetFlag().equals(0)) {
                    if (normalMap.containsKey(record.getReportId())) {
                        BeanUtils.copyProperties(normalMap.get(record.getReportId()), record);
                    }
                    normalList.add(record);
                }
                // 红冲结算
                if (dtl.getOffSetFlag().equals(1)) {
                    if (offsetMap.containsKey(record.getReportId())) {
                        BeanUtils.copyProperties(offsetMap.get(record.getReportId()), record);
                    }
                    record.setHsCodeList(jsHSMap.get(record.getProjectCode()));
                    offsetList.add(record);
                }
            }
            logger.info(" #for (PmlsJsStatementDtl dtl : reportList) End");
            pmlsJsStatementBo.setNormalList(normalList);
            pmlsJsStatementBo.setOffsetList(offsetList);
        }
        resultData.setReturnData(pmlsJsStatementBo);
        logger.info(" #end");
        return resultData;
    }

    public ResultData update(PmlsJsStatementVto statement) {
        logger.info("结算书更新处理：statement;" + JsonUtil.parseToJson(statement));

        ResultData resultData = new ResultData();

        // 1.保存校验
        if (checkSave(statement, resultData) == false) {
            return resultData;
        }
        // 2.补充信息
        improveSaveInfo(statement, false);


        PmlsJsStatement dbRecord = pmlsJsStatementMapper.selectByPrimaryKey(statement.getId());
        logger.info("结算书更新处理：dbRecord:" + JsonUtil.parseToJson(dbRecord));


        // 3.排他校验
        if (dbRecord != null && dbRecord.getDateUpdate() != null) {
            Long timeStamp = dbRecord.getDateUpdate().getTime();
            if (!timeStamp.equals(statement.getExclude())) {
                resultData.setReturnCode("301");
                resultData.setReturnMsg("他人已对本结算书进行修改，请刷新后操作！");
                return resultData;
            }
        }

        // 4.修改结算主表信息
        PmlsJsStatement record = new PmlsJsStatement();
        BeanUtils.copyProperties(dbRecord, record);
        this.copyPropertiesIgnoreNull(statement, record);
        record.setErrmsg(null);
        record.setApprovedDate(null);
        statement.setFlowId(null);
        record.setOaNo(statement.getOaNo());
        record.setFormState(statement.getFormState());
        record.setOaStartDate(statement.getOaStartDate());
        record.setApproveStatus(statement.getApproveStatus());
        record.setSendUserCode(statement.getSendUserCode());
        record.setSendUserName(statement.getSendUserName());
        record.setSqdate(statement.getSqdate());

        logger.info("结算书更新处理：record:" + JsonUtil.parseToJson(record));
        pmlsJsStatementMapper.updateByPrimaryKey(record);

        synchronized (this) {

            // 5.删除结算单详情
            pmlsJsStatementDtlMapper.deleteByJssId(record.getId());

            // 6.MergInsert结算详细信息
            List<PmlsJsStatementDtl> list = statement.getReportList();
            if (CollectionUtils.isNotEmpty(list)) {
                for (PmlsJsStatementDtl dtl : list) {
                    dtl.setJssId(record.getId());
                    dtl.setUserIdCreate(statement.getUserId());
                    dtl.setUserIdUpdate(statement.getUserId());
                    dtl.setDateCreate(new Date());
                    dtl.setDateUpdate(new Date());
                    dtl.setDelFlag(false);
                    pmlsJsStatementDtlMapper.mergeInsert(dtl);
                }
            }
        }

        // 7.删除结算书文件
        pmlsJsStatementFileMapper.deleteByJssId(record.getId());


        // 8. 保存提交文件

        // 8.1 删除文件
        Map<String, Object> delmap = new HashMap<>();
        delmap.put("contractId", record.getId());
        delmap.put("fileSourceId", 31);
        fileRecordMainMapper.deleteByCondition(delmap);

        if (statement.getFileRecordMainIds() != null && StringUtil.isNotEmpty(statement.getFileRecordMainIds())) {
            // 8.2 保存绑定关联RefId
            String[] fileIdArr = statement.getFileRecordMainIds().split(",");
            List<Integer> fileIdList = new ArrayList<>();
            for (String fileId : fileIdArr) {
                fileIdList.add(Integer.valueOf(fileId));
            }
            Map<String, Object> uptmap = new HashMap<>();
            uptmap.put("contractId", record.getId());
            uptmap.put("ids", fileIdList);
            fileRecordMainMapper.updateByCondition(uptmap);
        }

        // 提交场合OA处理
        if (1 == statement.getSubmitStatus()) {

            submitToOaDeal();
        }

        return resultData;
    }

    private void submitToOaDeal() {
        try {


        } catch (Exception e) {

        }
    }

    public static void copyPropertiesIgnoreNull(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));

    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public ResultData<List<FileRecordMainDto>> getJsStatementFile(String id) {
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<>();
        List<FileRecordMainDto> fileList = pmlsJsStatementDtlMapper.getJsStatementFile(id);
        resultData.setReturnData(fileList);
        return resultData;
    }
}
