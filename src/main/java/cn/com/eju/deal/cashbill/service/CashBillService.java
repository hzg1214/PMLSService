package cn.com.eju.deal.cashbill.service;

import cn.com.eju.deal.base.code.dao.WebConfigMapper;
import cn.com.eju.deal.base.code.model.WebConfig;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.cashbill.dao.*;
import cn.com.eju.deal.cashbill.model.*;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.CommonService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.houseLinkage.estate.dao.EstateMapper;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.houseLinkage.report.dao.FangyouReportFileMapper;
import cn.com.eju.deal.houseLinkage.report.dao.ReportMapper;
import cn.com.eju.deal.houseLinkage.report.model.FangyouReportFile;
import cn.com.eju.deal.houseLinkage.report.model.Report;
import cn.com.eju.deal.open.controller.UploadOAUtil;
import cn.com.eju.deal.open.service.APIOaService;
import cn.com.eju.deal.scene.commission.dao.*;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.User;
import cn.com.eju.pmls.jsStatement.dao.PmlsJsStatementDtlMapper;
import cn.com.eju.pmls.jsStatement.dao.PmlsJsStatementMapper;
import cn.com.eju.pmls.jsStatement.dao.PmlsStatementCashBillRelateMapper;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementDtlDto;
import cn.com.eju.pmls.jsStatement.dto.PmlsJsStatementDto;
import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementDtl;
import cn.com.eju.pmls.jsStatement.model.PmlsStatementCashBillRelate;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Created by yinkun on 2018/8/13.
 */
@Service("cashBillService")
public class CashBillService extends BaseService {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private APIOaService apiOaService;

    @Resource
    private CashBillProjectMapper cashBillProjectMapper;

    @Resource
    private CashBillCompanyMapper cashBillCompanyMapper;

    @Resource
    private CashBillReportMapper cashBillReportMapper;

    @Resource
    private FileRecordMainMapper recordMainMapper;

    @Resource
    private FileRecordMainService fileService;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Resource
    private UserAPIImpl userAPI;

    @Resource
    private WebConfigMapper webConfigMapper;

    @Resource
    private CompanyService companyService;

    @Resource
    private FangyouReportFileMapper fangyouReportFileMapper;

    @Resource
    private LnkYjYjsrMapper lnkYjYjsrMapper;

    @Resource
    private LnkYjYjfyMapper lnkYjYjfyMapper;

    @Resource
    private LnkYjYjdyMapper lnkYjYjdyMapper;

    @Resource
    private LnkYjSjfyMapper lnkYjSjfyMapper;

    @Resource
    private LnkYjSjdyMapper lnkYjSjdyMapper;

    @Resource
    private LnkYjNyMapper lnkYjNyMapper;

    @Resource
    private OaCashBillReturnMapper oaCashBillReturnMapper;

    @Resource
    private OaCashBillReportReturnMapper oaCashBillReportReturnMapper;

    @Resource
    private CashBillReportAdjustTaxMapper cashBillReportAdjustTaxMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private CommonService commonService;

    @Resource
    private ReportMapper reportMapper;

    @Resource
    private QkContractFileMapper qkContractFileMapper;

    @Resource
    private PmlsJsStatementMapper pmlsJsStatementMapper;

    @Resource
    private PmlsJsStatementDtlMapper pmlsJsStatementDtlMapper;

    @Resource
    private EstateMapper estateMapper;

    @Resource
    private PmlsStatementCashBillRelateMapper pmlsStatementCashBillRelateMapper;

    public List<CashBillProject> selectCashBill(Map<String, Object> queryParam) {
        return cashBillProjectMapper.selectCashBill(queryParam);
    }

    public ResultData<String> addToBatchCash(Map<String, Object> map) {
        String reportId = map.get("reportId").toString();
        String userCode = map.get("userCode").toString();
        Integer userId = Integer.valueOf(map.get("userId").toString());
        String cityNo = "";
        if (map.containsKey("cityNo")) {
            cityNo = map.get("cityNo").toString();
        }
        ResultData<String> resultData = new ResultData<>();

        // 取得当前报备的信息
        Report bbReport = reportMapper.getByReportId(reportId);

        // 如果是成销的场合
        if (bbReport != null && bbReport.getLatestProgress().equals(13505) && bbReport.getConfirmStatus().equals(13601)) {

            // 判断该报备的 应计收入 数据是否导入
            Integer intLnkYjsrCnt = lnkYjYjsrMapper.getCountByReportId(reportId);
            if (intLnkYjsrCnt == 0) {
                resultData.setFail("该报备房源未导入[应计收入]数据，请导入完毕后再请款。");
                //resultData.setReturnMsg("该报备房源未导入[应计收入]数据，请导入完毕后再请款。");
                return resultData;
            }
            // 判断该报备的 应计返佣 数据是否导入
            Integer intLnkYjfyCnt = lnkYjYjfyMapper.getCountByReportId(reportId);
            if (intLnkYjfyCnt == 0) {
                resultData.setFail("该报备房源未导入[应计返佣]数据，请导入完毕后再请款。");
                //resultData.setReturnMsg("该报备房源未导入[应计收入]数据，请导入完毕后再请款。");
                return resultData;
            }
        }

//        // 判断该报备的 应计垫佣 数据是否导入
//        Integer intLnkYjdyCnt = lnkYjYjdyMapper.getCountByReportId(reportId);
//        if (intLnkYjdyCnt == 0) {
//            resultData.setFail("该报备房源未导入[应计垫佣]数据，请导入完毕后再请款。");
//            //resultData.setReturnMsg("该报备房源未导入[应计收入]数据，请导入完毕后再请款。");
//            return resultData;
//        }

        List<Map<String, Object>> baseInfo = cashBillProjectMapper.selectBaseInfoForCash(reportId);

        /**
         * 添加请款项目
         */
        if (CollectionUtils.isEmpty(baseInfo)) {
            resultData.setReturnMsg("报备:" + reportId + "添加失败");
        }
        Map<String, Object> estateInfo = baseInfo.get(0);
        CashBillProject project = new CashBillProject();
        project.setEstateId(estateInfo.get("estateId").toString());
        project.setProjectNo(estateInfo.get("projectNo").toString());
        project.setEstateNm(estateInfo.get("estateNm").toString());
        project.setSubmitStatus(0);
        project.setUserCode(userCode);
        project.setUserIdCreate(userId);

        //不存在时才添加
        final List<CashBillProject> cashBillProjects = cashBillProjectMapper.checkProjectUnique(project);
        Integer proRowId = null;
        if (CollectionUtils.isEmpty(cashBillProjects)) {
            cashBillProjectMapper.insertSelective(project);
            proRowId = project.getId();
        } else {
            proRowId = cashBillProjects.get(0).getId();
        }

        /**
         * 添加请款公司
         */
        Integer repRowId = null;
        int addCount = 0;
        for (Map<String, Object> comInfo : baseInfo) {
            Integer comRowId = null;
            if (proRowId != null) {
                CashBillCompany company = new CashBillCompany();
                company.setProParentId(proRowId);
                company.setCompanyId(Integer.valueOf(comInfo.get("companyId").toString()));
                company.setCompanyNo(comInfo.get("companyNo").toString());
                company.setCompanyName(comInfo.get("companyName").toString());
                company.setUserIdCreate(userId);
                company.setSubmitOaStatus(21201);
                if (!"".equals(cityNo)) {
                    company.setCityNo(cityNo);
                }

                //不存在时才添加
                final List<CashBillCompany> cashBillCompanies = cashBillCompanyMapper.checkCompanyUnique(company);
                if (CollectionUtils.isEmpty(cashBillCompanies)) {
                    cashBillCompanyMapper.insertSelective(company);
                    comRowId = company.getId();
                } else {
                    comRowId = cashBillCompanies.get(0).getId();
                }
            }

            /**
             * 添加请款报备
             */
            if (comRowId != null) {
                CashBillReport report = new CashBillReport();
                report.setProParentId(proRowId);
                report.setComParentId(comRowId);
                report.setUserIdCreate(userId);
                report.setReportId(Integer.valueOf(comInfo.get("reportId").toString()));
                report.setReportNo(comInfo.get("reportNo").toString());

                //不存在时才添加
                final List<CashBillReport> cashBillReports = cashBillReportMapper.checkReportUnique(report);
                if (CollectionUtils.isEmpty(cashBillReports)) {
                    addCount = cashBillReportMapper.insertSelective(report);
                    repRowId = report.getId();
                } else {
                    repRowId = cashBillReports.get(0).getId();
                }
            }
        }

        if (repRowId != null) {
            if (addCount > 0) {
                resultData.setReturnMsg("加入批量请款成功");
            } else {
                resultData.setReturnMsg("已添加批量请款，请勿重复添加！");
            }
        } else {
            resultData.setReturnMsg("报备:" + reportId + "添加失败");
        }

        return resultData;
    }

    public ResultData<CashBillProject> selectCashBillForPop(Map<String, Object> map) throws Exception {

        ResultData<CashBillProject> resultData = new ResultData<>();
        map.put("submitStatus", 0);
        CashBillProject project = cashBillProjectMapper.findByDynamic(map);
        if (project.getRecordTime() == null) {
            project.setRecordTime(new Date());
        }

        Map<String, Object> cmap = new HashMap<>();
        cmap.put("proParentId", project.getId());
        List<CashBillCompany> companyList = cashBillCompanyMapper.queryByParentId(cmap);

        for (CashBillCompany company : companyList) {
            List<CashBillReport> reportList = cashBillReportMapper.queryByParentId(company.getId());
            //正常记录
            List<CashBillReport> rptList = new ArrayList<>();
            //冲抵记录
            List<CashBillReport> offSetList = new ArrayList<>();
            for (CashBillReport rpt : reportList) {
                if (rpt.getOffSetFlag()) {
                    String projectNo = cashBillReportMapper.getProjectNoByReportId(rpt.getReportId());
                    List<Map<String, Object>> accountProjectList =  this.getOALnkAccountProjectList(projectNo);
                    rpt.setAccountProjectList(accountProjectList);
                    if(StringUtil.isNotEmpty(rpt.getAccountProjectNo())){
                        Map<String,Object> parmsCheckBody = new HashMap<>();
                        parmsCheckBody.put("accountProjectCode",rpt.getAccountProjectNo());
                        List<Map<String, Object>> checkBodyList = oaCashBillReturnMapper.getOACheckBodyList(parmsCheckBody);
                        rpt.setCheckBodyList(checkBodyList);
                    }
                    offSetList.add(rpt);
                } else {
                    rptList.add(rpt);
                }
            }
            company.setReportList(rptList);
            company.setOffSetList(offSetList);
            // 取得考核主体信息
            if (company.getAccountProjectNo() != null && !company.getAccountProjectNo().isEmpty()){
                Map<String,Object> parmsCheckBody = new HashMap<>();
                parmsCheckBody.put("accountProjectCode",company.getAccountProjectNo());
                List<Map<String, Object>> checkBodyList = oaCashBillReturnMapper.getOACheckBodyList(parmsCheckBody);
                company.setCheckBodyList(checkBodyList);
            }
        }

        project.setCompanyList(companyList);
        resultData.setReturnData(project);

        return resultData;
    }

    public ResultData<String> removeFromCashBill(Map<String, Object> map) {
        ResultData<String> resultData = new ResultData<>();

        final int count = cashBillReportMapper.deleteByPrimaryKey(Integer.valueOf(map.get("repRowId").toString()));
        if (count != 1) {
            resultData.setFail("移除批量请款失败");
        }

        final List<CashBillReport> cashBillReports = cashBillReportMapper.queryByParentId(Integer.valueOf(map.get("comRowId").toString()));
        if (CollectionUtils.isEmpty(cashBillReports)) {
            cashBillCompanyMapper.deleteByPrimaryKey(Integer.valueOf(map.get("comRowId").toString()));
        }

        Map<String, Object> comMap = new HashMap<>();
        comMap.put("proParentId", Integer.valueOf(map.get("proRowId").toString()));
        final List<CashBillCompany> cashBillCompanies = cashBillCompanyMapper.queryByParentId(comMap);
        if (CollectionUtils.isEmpty(cashBillCompanies)) {
            cashBillProjectMapper.deleteByPrimaryKey(Integer.valueOf(map.get("proRowId").toString()));
        }

        return resultData;
    }

    public ResultData<String> saveCashBill(CashBillProject project) throws Exception {

        ResultData<String> resultData = new ResultData<>();
        resultData.setReturnMsg("批量请款保存成功");
        String errorMsg = "";

        //验证报备是否满足条件
        List<Integer> comRowIdList = new ArrayList<>();
        List<Integer> repRowIdList = new ArrayList<>();
        List<Integer> reportIdList = new ArrayList<>();

        List<CashBillCompany> companyList = project.getCompanyList();
        for (CashBillCompany company : companyList) {

            if (company.getCompanyId() == null) continue;

            final Company byCompanyId = companyService.getByCompanyId(company.getCompanyId());
            company.setBusinessLicenseNo(byCompanyId.getBusinessLicenseNo());

            comRowIdList.add(company.getId());
            List<CashBillReport> reportList = company.getReportList();
            for (CashBillReport report : reportList) {
                repRowIdList.add(report.getId());
                reportIdList.add(report.getReportId());
            }
        }

        Map map = new HashMap<>();
        map.put("reportIdList", reportIdList);
        List<CashBillReport> unreasonableReport = cashBillReportMapper.findUnreasonableReport(map);
        if (CollectionUtils.isNotEmpty(unreasonableReport)) {
            for (CashBillReport unReport : unreasonableReport) {
                errorMsg += unReport.getReportNo() + ",";
            }
            errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
            resultData.setFail("报备：" + errorMsg + "不符合请款条件");
            return resultData;
        }

        //保存批量请款
        Integer oldFlag = project.getSubmitStatus();
        project.setSubmitStatus(0);
        cashBillProjectMapper.updateByPrimaryKeySelective(project);
        for (CashBillCompany company : companyList) {
            if (company.getCompanyId() == null) continue;

            //company.setAmountTotal(company.getAmountNoTax().add(company.getAmountTax()));
            company.setAmountTotal(company.getAmountNoTax());
            company.setAmountNoTax(company.getAmountTotal().subtract(company.getAmountTax()));
            cashBillCompanyMapper.updateByPrimaryKeySelective(company);
            List<CashBillReport> reportList = company.getReportList();
            for (CashBillReport report : reportList) {
                cashBillReportMapper.updateByPrimaryKeySelective(report);
            }
        }

        project.setSubmitStatus(oldFlag);

        String fileRecordMainIds = project.getFileRecordMainIds();

        if (StringUtil.isNotEmpty(fileRecordMainIds)) {
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", project.getId());
            delmap.put("fileSourceId", 9);
            delmap.put("fileTypeId", 1032);
            recordMainMapper.deleteByCondition(delmap);

            //新文件处理
            String[] fileIdArr = fileRecordMainIds.split(",");
            List<Integer> fileIdList = new ArrayList<>();
            for (String fileId : fileIdArr) {
                fileIdList.add(Integer.valueOf(fileId));
            }
            Map<String, Object> uptmap = new HashMap<>();
            uptmap.put("contractId", project.getId());
            uptmap.put("ids", fileIdList);
            recordMainMapper.updateByCondition(uptmap);
        }

        Map<String, String> accountProjectByEstateId = cashBillProjectMapper.getAccountProjectByEstateId(project.getEstateId());
        if (accountProjectByEstateId == null) {
            resultData.setFail("项目：" + project.getEstateNm() + "所对应城市无核算主体信息");
            return resultData;
        }
        //取选择的核算主体，不再使用表中选择
        String accountProject = project.getLnkAccountProject(); //accountProjectByEstateId.get("accountProject");
        String accountProjectNo = project.getLnkAccountProjectCode();//accountProjectByEstateId.get("accountProjectCode");
        String cashBillBatchTpl = accountProjectByEstateId.get("cashBillBatchTpl");

        //提交前获取OA供应商信息
        if (project.getSubmitStatus().intValue() == 1) {
            for (CashBillCompany company : companyList) {
                company.setAccountProject(accountProject);
                company.setAccountProjectNo(accountProjectNo);
                if (checkOaVendorInfo(project, resultData, accountProjectNo, company)) return resultData;
            }
        }

        //提交的情况再次保存批量请款
        if (project.getSubmitStatus().intValue() == 1) {
            resultData.setReturnMsg("提交批量请款成功");
            cashBillProjectMapper.updateByPrimaryKeySelective(project);
            for (CashBillCompany company : companyList) {
                if (company.getCompanyId() == null) continue;
//                company.setAmountTotal(company.getAmountNoTax());
//                company.setAmountNoTax(company.getAmountTotal().subtract(company.getAmountTax()));
                company.setSubmitOaStatus(21202);
                cashBillCompanyMapper.updateByPrimaryKeySelective(company);
                List<CashBillReport> reportList = company.getReportList();
                for (CashBillReport report : reportList) {
                    cashBillReportMapper.updateByPrimaryKeySelective(report);
                }
            }
        }

        final ResultData<User> userByCode = userAPI.getUserByCode(project.getUserCode());
        if ("200".equals(userByCode.getReturnCode())) {
            project.setUserName(userByCode.getReturnData().getUserName());
        }

        final WebConfig byName = webConfigMapper.getByName("oaPayType_" + project.getPayType());
        if (byName != null) {
            project.setPayTypeNm(byName.getWebConfigValue());
        }

        //开启线程提交OA
        if (project.getSubmitStatus().intValue() == 1) {
            for (CashBillCompany company : companyList) {
                if (company.getCompanyId() == null) continue;
                submitToOa(project, company, resultData, cashBillBatchTpl);
            }
        }

        return resultData;
    }

    /**
     * 营业执照，供应商，框架合同不存在时，依旧可以提交OA
     *
     * @param project
     * @param resultData
     * @param accountProjectNo
     * @param company
     * @return
     * @throws Exception
     */
    private boolean checkOaVendorInfo(CashBillProject project, ResultData<String> resultData, String accountProjectNo, CashBillCompany company) throws Exception {
        //供应商等银行信息
        String param = "{\"permitCode\":\"" + company.getBusinessLicenseNo() + "\",\"type\":\"新房联动\"}";
        final JSONObject vendorByLicAndType = getOAInfoByMethod("getVendorByLicAndType", param);

        JSONArray data2 = (JSONArray) vendorByLicAndType.get("data");
        if (StringUtil.isEmpty(company.getBusinessLicenseNo())) {
            //resultData.setFail("CRM中公司:  "+company.getCompanyName() + "("+company.getCompanyNo()+") 统一社会信用代码为空，请先补充统一社会信用代码信息！");
            return false;
        } else {
            if (vendorByLicAndType == null || data2 == null) {
                //resultData.setFail("供应商营业执照：" + company.getBusinessLicenseNo() + " 在OA系统中不存在，无法提交");
                return false;
            } else {
                JSONObject object = (JSONObject) data2.get(0);
                company.setVendorCode(object.get("VENDOR_ID").toString());
                company.setVendorName(object.get("VENDOR_NAME").toString());
                company.setBankName(object.get("BANK_NAME").toString());
                company.setBankAccount(object.get("BANK_ACCOUNT").toString());
            }
        }

        //供应商框架合同信息
        param = "{\"vendor_id\":\"" + company.getVendorCode() + "\",\"hs_code\":\"" + accountProjectNo + "\"}";
        final JSONObject htByVendorIdAndHszt = getOAInfoByMethod("getHtByVendorIdAndHszt", param);
        JSONArray data3 = (JSONArray) htByVendorIdAndHszt.get("data");
        if (htByVendorIdAndHszt != null && data3 != null) {

            JSONObject object = (JSONObject) data3.get(0);
            company.setFrameOaNo(object.get("PO_NUMBER").toString());
            company.setISKJ(object.get("ISKJ").toString());
        }

        //框架合同附件信息
        if (company.getFrameOaNo() != null) {
            param = "{\"po_number\":\"" + company.getFrameOaNo() + "\"}";
            final JSONObject accessoryByPo = getOAInfoByMethod("getAccessoryByPo", param);
            JSONArray data4 = (JSONArray) accessoryByPo.get("data");
            if (accessoryByPo != null && data4 != null) {

                JSONObject object = (JSONObject) data4.get(0);
                if (object.get("url") != null && object.get("filename") != null
                        && StringUtil.isNotEmpty(object.get("url").toString())
                        && StringUtil.isNotEmpty(object.get("filename").toString())) {
                    company.setFrameFileUrl(SystemParam.getWebConfigValue("frameFileUrl") + object.get("url").toString());
                    company.setFrameFileName(object.get("filename").toString());
                }
            }
        }

        //获取OA项目名称和编号
        param = "{\"project_num\":\"" + project.getProjectNo() + "\"}";
        //param = "{\"project_num\":\""+"FYHZ0611"+"\"}";
        final JSONObject projectByCode = getOAInfoByMethod("getProjectByCode", param);
        JSONArray data5 = (JSONArray) projectByCode.get("data");
        if (projectByCode == null || data5 == null) {
            //resultData.setFail("CRM项目：" + project.getProjectNo() + "在OA系统中不存在，无法提交");
            return false;
        } else {
            JSONObject object = (JSONObject) data5.get(0);
            company.setOaProjectName(object.get("PROJECT_NAME").toString());
            company.setOaProjectNo(object.get("PROJECT_NUM").toString());
        }
        return false;
    }

    /**
     * desc:获取中介合作报批单盖章附件信息  1、autoToOa=1 根据flowid拉取  2、框架协议编号拉取
     * 2019年8月27日
     * author:zhenggang.Huang
     * @throws Exception
     */
    private void getQkOaAnnex(CashBillCompany company) throws Exception {
    	String param = "";
    	//框架合同附件信息
    	if (company.getFrameOaNo() != null) {
    		//根据框架协议编号查询是否为自动发送
    		Map<String,Object> reqMap = new HashMap<>();
    		reqMap.put("frameOaNo", company.getFrameOaNo());
    		Map<String,Object> responResult = cashBillCompanyMapper.getFlowIdByFrameOaNo(reqMap);
    		JSONObject accessoryByPo = null;
    		JSONArray data4 = null;
    		if(responResult !=null && responResult.size()!=0) {//自动
    			param = "{\"flowid\":\"" + responResult.get("FlowId").toString() + "\"}";
    			accessoryByPo = getOAInfoByMethod("getZhongjieByFlowid", param);
    		}else {//不是自动
    			param = "{\"po_number\":\"" + company.getFrameOaNo() + "\"}";
//        		param = "{\"po_number\":\"FYLD2019080812\"}";
    			accessoryByPo = getOAInfoByMethod("getAccessoryByPo", param);
    		}
    		if(accessoryByPo != null && (JSONArray) accessoryByPo.get("data") != null) {
    			 data4 = (JSONArray) accessoryByPo.get("data");
				 JSONObject object = (JSONObject) data4.get(0);
				 if (object.get("url") != null && object.get("filename") != null
						 && StringUtil.isNotEmpty(object.get("url").toString())
						 && StringUtil.isNotEmpty(object.get("filename").toString())) {
					 company.setFrameFileUrl(SystemParam.getWebConfigValue("frameFileUrl") + object.get("url").toString());
					 company.setFrameFileName(object.get("filename").toString());
				 }
    		}
    	}
    }

    @Async("threadPoolTaskExecutor")
    public void submitToOa(CashBillProject project, CashBillCompany company, ResultData<String> resultData, String cashBillBatchTpl) {
        Map<String, Object> sendOaData = new HashMap<>();

        String cashBillNo = null;
        if (StringUtil.isEmpty(company.getCashBillNo())) {
            ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_LD_QK");
            if (data != null && data.getReturnCode().equals("200")) {
                cashBillNo = data.getReturnData();
            } else {
                new NullPointerException("根据seq_type: TYPE_LD_QK 获取联动批量请款编号为空");
            }
        } else {
            cashBillNo = company.getCashBillNo();
        }

        // 模板编号
        sendOaData.put("templateCode", cashBillBatchTpl);
        // 发起者的登录名（登录协同的登录名）
        sendOaData.put("senderLoginName", project.getUserCode());
        // 协同的标题
        sendOaData.put("subject", "易居房产交易 " + cashBillNo + " " + project.getEstateNm() + " " + company.getCompanyName());

        try {
            //附件集合汇总
            List<Long> attachList = new ArrayList<>();

            StringBuffer stringBuffer = new StringBuffer("订单编号：");
            final List<CashBillReport> reportList = company.getReportList();
            for (CashBillReport report : reportList) {

                stringBuffer.append(report.getReportNo()).append(" ");

                // 带看单_
                this.getOaAttachment(attachList, report.getReportId(), 1022, project.getUserCode(), "带看单_" + report.getBuildingNo());

                // 甲方项目负责人名片_
                this.getOaAttachment(attachList, report.getReportId(), 1024, project.getUserCode(), "甲方项目负责人名片_" + report.getBuildingNo());
            }

            // 成销确认书/佣金结算资料
            this.getOaAttachment(attachList, project.getId(), 1032, project.getUserCode(), "成销确认书_");

            //框架合同附件
            if (company.getFrameFileUrl() != null) {
                String attachmentId = UploadOAUtil.oaAttachmentUpload(company.getFrameFileUrl(), company.getFrameFileName(), project.getUserCode(), "");
                if (StringUtil.isNotEmpty(attachmentId)) {
                    attachList.add(Long.valueOf(attachmentId));
                }
            }

            StringBuilder detail = new StringBuilder();
            // String rate = company.getAmountTax().divide(company.getAmountNoTax(), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2) + "%";
            BigDecimal rate = new BigDecimal(0);
            if (BigDecimal.ZERO.compareTo(company.getAmountNoTax()) != 0) {
                rate = company.getAmountTax().divide(company.getAmountNoTax(), 4, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(2,BigDecimal.ROUND_HALF_UP);
            }
            detail.append("<row>" + "<column name=\"行明细\"><value>" + "" + "</value></column>"
                    + "<column name=\"行费用类别\"><value>" + "主营业务成本-新房联动成本(交易服务)" + "</value></column>"
                    + "<column name=\"行费用类别编码\"><value>" + "6401811003" + "</value></column>"
                    + "<column name=\"行考核主体\"><value>" + "" + "</value></column>"
                    + "<column name=\"行考核主体编码\"><value>" + "" + "</value></column>"
                    + "<column name=\"行项目\"><value>" + (company.getOaProjectName() == null ? "" : company.getOaProjectName()) + "</value></column>"
                    + "<column name=\"行项目编码\"><value>" + (company.getOaProjectNo() == null ? "" : company.getOaProjectNo()) + "</value></column>"
                    + "<column name=\"行成本中心\"><value>" + "缺省" + "</value></column>"
                    + "<column name=\"行成本中心编码\"><value>" + "0" + "</value></column>"
                    + "<column name=\"行费用归档\"><value>" + "" + "</value></column>"
                    + "<column name=\"行费用归档编码\"><value>" + "" + "</value></column>"
                    + "<column name=\"未付金额\"><value>" + "0" + "</value></column>"
                    + "<column name=\"付款金额\"><value>" + company.getAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP) + "</value></column>"
                    + "<column name=\"税额\"><value>" + company.getAmountTax().setScale(2,BigDecimal.ROUND_HALF_UP) + "</value></column>"
                    + "<column name=\"税率\"><value>" + rate + "</value></column>"
                    + "<column name=\"说明\"><value>" + company.getRemark() + "</value></column>"
                    + "</row>");

            // 组装向OA发送的参数信息
            String dataXml =
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                            + "<summary id=\"-6045487298903782888\" name=\"formmain_3986\"/>" + "<definitions/>" + "<values>"
                            + "<column name=\"编码\"><value>"
                            + ""
                            + "</value></column>"
                            + "<column name=\"请款类型\"><value>"
                            + "-2247489456137744808"
                            + "</value></column>"
                            + "<column name=\"CRM编号\"><value>"
                            + cashBillNo
                            + "</value></column>"
                            + "<column name=\"所属板块\"><value>"
                            + "房产交易"
                            + "</value></column>"
                            + "<column name=\"板块编码\"><value>"
                            + "FCJY"
                            + "</value></column>"
                            + "<column name=\"核算主体\"><value>"
                            + company.getAccountProject()
                            + "</value></column>"
                            + "<column name=\"核算主体编码\"><value>"
                            + company.getAccountProjectNo()
                            + "</value></column>"
                            + "<column name=\"采购分类1\"><value>"
                            + "服务"
                            + "</value></column>"
                            + "<column name=\"采购分类2\"><value>"
                            + "业务资源"
                            + "</value></column>"
                            + "<column name=\"采购分类3\"><value>"
                            + "新房联动"
                            + "</value></column>"
                            + "<column name=\"供应商\"><value>"
                            + (company.getVendorName() == null ? "" : company.getVendorName())
                            + "</value></column>"
                            + "<column name=\"供应商编码\"><value>"
                            + (company.getVendorCode() == null ? "" : company.getVendorCode())
                            + "</value></column>"
                            + "<column name=\"合同单据号\"><value>"
                            + (company.getFrameOaNo() == null ? "" : company.getFrameOaNo())
                            + "</value></column>"
                            + "<column name=\"经办人\"><value>"
                            + project.getUserName()
                            + "</value></column>"
                            + "<column name=\"经办人工号\"><value>"
                            + project.getUserCode()
                            + "</value></column>"
                            + "<column name=\"框架合同\"><value>"
                            + (company.getISKJ() == null ? "" : company.getISKJ())
                            + "</value></column>"
                            + "<column name=\"收款银行\"><value>"
                            + ""
                            + "</value></column>"
                            + "<column name=\"银行账号\"><value>"
                            + ""
                            + "</value></column>"
                            + "<column name=\"入账日期\"><value>"
                            + DateUtil.fmtDate(project.getRecordTime(), "yyyy-MM-dd")
                            + "</value></column>"
                            + "<column name=\"最后付款日期\"><value>"
                            + DateUtil.fmtDate(project.getPredictPayTime(), "yyyy-MM-dd")
                            + "</value></column>"
                            + "<column name=\"付款方式\"><value>"
                            + project.getPayTypeNm()
                            + "</value></column>"
                            + "<column name=\"申请人\"><value>"
                            + project.getUserName()
                            + "</value></column>"
                            + "<column name=\"申请人工号\"><value>"
                            + project.getUserCode()
                            + "</value></column>"
                            + "<column name=\"备注\"><value>"
                            + stringBuffer.toString()
                            + "</value></column>"
                            + "<column name=\"不含税请款合计\"><value>"
                            + company.getAmountNoTax().setScale(2,BigDecimal.ROUND_HALF_UP)
                            + "</value></column>"
                            + "<column name=\"税额合计\"><value>"
                            + company.getAmountTax().setScale(2,BigDecimal.ROUND_HALF_UP)
                            + "</value></column>"
                            + "<column name=\"本次请款合计\"><value>"
                            + company.getAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP)
                            + "</value></column>"
                            + "</values>"
                            + "<subForms>"
                            + "<subForm>"
                            + "<definitions>"
                            + "<column id=\"field0028\" type=\"0\" name=\"行明细\" length=\"255\"/>"
                            + "<column id=\"field0029\" type=\"0\" name=\"行费用类别\" length=\"255\"/>"
                            + "<column id=\"field0030\" type=\"0\" name=\"行费用类别编码\" length=\"255\"/>"
                            + "<column id=\"field0031\" type=\"0\" name=\"行考核主体\" length=\"255\"/>"
                            + "<column id=\"field0032\" type=\"0\" name=\"行考核主体编码\" length=\"255\"/>"
                            + "<column id=\"field0033\" type=\"0\" name=\"行项目\" length=\"255\"/>"
                            + "<column id=\"field0034\" type=\"0\" name=\"行项目编码\" length=\"255\"/>"
                            + "<column id=\"field0035\" type=\"0\" name=\"行成本中心\" length=\"255\"/>"
                            + "<column id=\"field0036\" type=\"0\" name=\"行成本中心编码\" length=\"255\"/>"
                            + "<column id=\"field0037\" type=\"0\" name=\"行费用归档\" length=\"255\"/>"
                            + "<column id=\"field0038\" type=\"0\" name=\"行费用归档编码\" length=\"255\"/>"
                            + "<column id=\"field0039\" type=\"0\" name=\"未付金额\" length=\"255\"/>"
                            + "<column id=\"field0040\" type=\"0\" name=\"付款金额\" length=\"255\"/>"
                            + "<column id=\"field0041\" type=\"0\" name=\"税额\" length=\"255\"/>"
                            + "<column id=\"field0042\" type=\"0\" name=\"税率\" length=\"255\"/>"
                            + "<column id=\"field0043\" type=\"0\" name=\"说明\" length=\"255\"/>"
                            + "</definitions>"
                            + "<values>"
                            + detail.toString() + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";

            // 附件，Long型数组，值为附件的Id。
            sendOaData.put("attachments", attachList);
            sendOaData.put("data", dataXml);
            // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
            // sendOaData.put("state", "1");
            sendOaData.put("param", "1");

            // 取得REST动态客户机实例
            CTPRestClient client = getClient();
            String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
            //为登录验证后获取的身份令牌
            sendOaData.put("token", token);

            //发起申请 返回FlowId
            Long flowId = null;
            try {
                flowId = apiOaService.toOaAuth(sendOaData, cashBillBatchTpl);

            } catch (Exception e) {
                logger.error("cashBill", "CashBillService", "submitToOa", dataXml, 0, "", "批量请款提交OA失败", e);
            }

            CashBillCompany sucUpt = new CashBillCompany();
            sucUpt.setId(company.getId());
            sucUpt.setCashBillNo(cashBillNo);
            if (flowId != null) {
                sucUpt.setFlowId(flowId + "");
                sucUpt.setSubmitOaStatus(21203);
            } else {
                sucUpt.setSubmitOaStatus(21204);
            }
            sucUpt.setUserCode(project.getUserCode());
            sucUpt.setApplyTime(new Date());
            cashBillCompanyMapper.updateByPrimaryKeySelective(sucUpt);


        } catch (Exception e) {
            logger.error("cashBill", "CashBillService", "submitToOa", "", 0, "", "批量请款提交OA失败", e);
            CashBillCompany exeUpt = new CashBillCompany();
            exeUpt.setId(company.getId());
            //提交失败
            exeUpt.setCashBillNo(cashBillNo);
            exeUpt.setSubmitOaStatus(21204);
            exeUpt.setCashBillNo(cashBillNo);
            exeUpt.setUserCode(project.getUserCode());
            exeUpt.setApplyTime(new Date());
            cashBillCompanyMapper.updateByPrimaryKeySelective(exeUpt);
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("批量请款提交OA失败");
        }
    }

    private JSONObject getOAInfoByMethod(String method, String param) throws Exception {
        String url = SystemParam.getWebConfigValue("ebs_formal");
        String jsonStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap12:Body>"
                + "<GetEBSData xmlns=\"http://tempuri.org/\">"
                + "<key>3F8118A3-330A-4A11-B422-C110E28F31C5</key>"
                + "<type>" + method + "</type>"
                + "<param>" + param + "</param>"
                + "</GetEBSData>"
                + "</soap12:Body>"
                + "</soap12:Envelope>";

        String validResult = apiOaService.post(url, jsonStr);

        //多出来 的xml
        if (validResult.indexOf("<?xml") > 0) {
            validResult = validResult.substring(0, validResult.indexOf("<?xml"));
        }

        if (StringUtil.isEmpty(validResult)) {
            logger.error("cashBill", "CashBillService", method, param, 0, "", "查询供应商信息异常", new Exception("查询供应商信息异常"));
            return null;
        }

        JSONObject jsonObject = JSONObject.parseObject(validResult);

        return jsonObject;
    }

    private void getOaAttachment(List<Long> attachList, int refId, Integer fileTypeCode, String userCode, String pre) throws Exception {
        try {
            Map<String, Object> param = new HashMap<>();
            param.put("refId", refId);
            param.put("fileTypeId", fileTypeCode);
            List<FileRecordMainDto> backResult = fileService.queryList(param);
            for (FileRecordMainDto fileMain : backResult) {
                String fileUrl = fileMain.getFileUrl();

                pre += fileMain.getFileFullName();
                String attachmentId = UploadOAUtil.oaAttachmentUpload(fileUrl, pre, userCode, "");
                if (StringUtil.isNotEmpty(attachmentId)) {
                    attachList.add(Long.valueOf(attachmentId));
                }
            }

            Map<String, Object> map = new HashMap<>();
            map.put("reportId", refId);
            map.put("fileTypeCode", fileTypeCode);
            final List<FangyouReportFile> fangyouReportFiles = fangyouReportFileMapper.selectByCondition(map);
            for (FangyouReportFile file : fangyouReportFiles) {
                String fileUrl = file.getUrl();

                String attachmentId = UploadOAUtil.oaAttachmentUpload(fileUrl, pre + ".jpg", userCode, "");
                if (StringUtil.isNotEmpty(attachmentId)) {
                    attachList.add(Long.valueOf(attachmentId));
                }
            }
        } catch (Exception e) {
            logger.error("cash_bill",
                    "CashBillService",
                    "getOaAttachment",
                    "",
                    Integer.valueOf(userCode),
                    "",
                    "上传批量请款文件失败！",
                    e);
        }
    }

    /**
     * 请款单列表
     *
     * @param reqMap
     * @return
     */
    public ResultData<List<CashBillDto>> getCashBillList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<CashBillDto>> resultData = new ResultData<List<CashBillDto>>();
        resultData.setFail();
        List<CashBillDto> cashBillDtoList = cashBillCompanyMapper.getCashBillList(reqMap);
        if (cashBillDtoList != null && cashBillDtoList.size() > 0) {
            resultData.setReturnData(cashBillDtoList);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
        }
        resultData.setSuccess();
        return resultData;
    }

    /**
     * 请款单详情
     *
     * @param map
     * @return
     */
    public ResultData getCashBillDeatilById(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();
        resultData.setFail();
        List<CashBillDto> cashBillDtoList = cashBillCompanyMapper.getCashBillDeatilById(map);
        if (cashBillDtoList != null) {
            CashBillDto cashBillDto = cashBillDtoList.get(0);
            List<CashBillReport> cashBillReportList = cashBillCompanyMapper.getReportListById(map);
            BigDecimal qkDetaileAreaTotal = new BigDecimal(0.00);	//销售面积总计
            BigDecimal qkDetaileRoughAmountTotal = new BigDecimal(0.00); 	//大定总价总计
            BigDecimal qkDetaileDealAmountTotal  = new BigDecimal(0.00);	//成销总价总计
            BigDecimal qkDetaileSqYjsrAmountTotal = new BigDecimal(0.00) ;	//应计收入总计
            BigDecimal qkDetaileSqYjfyAmountTotal = new BigDecimal(0.00) ;	//应计返佣总计
            BigDecimal qkDetaileSqYjdyAmountTotal = new BigDecimal(0.00) ;	//应计垫佣总计
            BigDecimal qkDetaileSqSjsrAmountTotal = new BigDecimal(0.00) ;	//实收收入总计
            BigDecimal qkDetaileSqSjfyAmountTotal = new BigDecimal(0.00);	//实际返佣总计
            BigDecimal qkDetaileSqSjdyAmountTotal = new BigDecimal(0.00) ;	//实际垫佣总计
            BigDecimal qkDetaileRequestAmountTotal = new BigDecimal(0.00);	//含税请款金额总计
            BigDecimal qkDetaileTaxAmountTotal = new BigDecimal(0.00);	//税额总计
            if (cashBillReportList != null && cashBillReportList.size() > 0) {
            	CashBillDto reportSum = cashBillCompanyMapper.getReportSumById(map);
            	//获取合计列数据
            	for (CashBillReport cashBillReport : cashBillReportList) {
            		qkDetaileAreaTotal = qkDetaileAreaTotal.add(cashBillReport.getArea()==null?new BigDecimal(0.00):cashBillReport.getArea());
            		qkDetaileRoughAmountTotal =qkDetaileRoughAmountTotal.add(cashBillReport.getRoughAmount()==null?new BigDecimal(0.00):cashBillReport.getRoughAmount());
            		qkDetaileDealAmountTotal =qkDetaileDealAmountTotal.add(cashBillReport.getDealAmount()==null?new BigDecimal(0.00):cashBillReport.getDealAmount());
            		qkDetaileSqYjsrAmountTotal =qkDetaileSqYjsrAmountTotal.add(cashBillReport.getSqYjsrAmount()==null?new BigDecimal(0.00):cashBillReport.getSqYjsrAmount());
            		qkDetaileSqYjfyAmountTotal =qkDetaileSqYjfyAmountTotal.add(cashBillReport.getSqYjfyAmount()==null?new BigDecimal(0.00):cashBillReport.getSqYjfyAmount());
            		qkDetaileSqYjdyAmountTotal =qkDetaileSqYjdyAmountTotal.add(cashBillReport.getSqYjdyAmount()==null?new BigDecimal(0.00):cashBillReport.getSqYjdyAmount());
            		qkDetaileSqSjsrAmountTotal =qkDetaileSqSjsrAmountTotal.add(cashBillReport.getSqSjsrAmount()==null?new BigDecimal(0.00):cashBillReport.getSqSjsrAmount());
            		qkDetaileSqSjfyAmountTotal =qkDetaileSqSjfyAmountTotal.add(cashBillReport.getSqSjfyAmount()==null?new BigDecimal(0.00):cashBillReport.getSqSjfyAmount());
            		qkDetaileSqSjdyAmountTotal =qkDetaileSqSjdyAmountTotal.add(cashBillReport.getSqSjdyAmount()==null?new BigDecimal(0.00):cashBillReport.getSqSjdyAmount());
            		qkDetaileRequestAmountTotal=qkDetaileRequestAmountTotal.add(cashBillReport.getRequestAmount()==null?new BigDecimal(0.00):cashBillReport.getRequestAmount());
            		qkDetaileTaxAmountTotal =qkDetaileTaxAmountTotal.add(cashBillReport.getTaxAmount()==null?new BigDecimal(0.00):cashBillReport.getTaxAmount());
				}
            	CashBillReport qkDetailTotal = new CashBillReport();//合计列
            	qkDetailTotal.setArea(qkDetaileAreaTotal);
            	qkDetailTotal.setRoughAmount(qkDetaileRoughAmountTotal);
            	qkDetailTotal.setDealAmount(qkDetaileDealAmountTotal);
            	qkDetailTotal.setSqYjsrAmount(qkDetaileSqYjsrAmountTotal);
            	qkDetailTotal.setSqYjfyAmount(qkDetaileSqYjfyAmountTotal);
            	qkDetailTotal.setSqYjdyAmount(qkDetaileSqYjdyAmountTotal);
            	qkDetailTotal.setSqSjsrAmount(qkDetaileSqSjsrAmountTotal);
            	qkDetailTotal.setSqSjfyAmount(qkDetaileSqSjfyAmountTotal);
            	qkDetailTotal.setSqSjdyAmount(qkDetaileSqSjdyAmountTotal);
            	qkDetailTotal.setRequestAmount(qkDetaileRequestAmountTotal);
            	qkDetailTotal.setTaxAmount(qkDetaileTaxAmountTotal);
            	cashBillReportList.add(qkDetailTotal);
                cashBillDto.setReportDetails(cashBillReportList);
                cashBillDto.setAreaSum(reportSum.getAreaSum());
                cashBillDto.setRoughAmountSum(reportSum.getRoughAmountSum());
                cashBillDto.setDealAmountSum(reportSum.getDealAmountSum());
                //获取文件信息
                String fileRecordMainIds = "";
                FileRecordMain attachmentFile = new FileRecordMain();
                Integer proParentId =  (Integer) map.get("proParentId");
                attachmentFile.setRefId(proParentId);
                attachmentFile.setIsDelete(false);
                //文件
                List<ContractFileDto> orderFileList = new ArrayList<ContractFileDto>();
                List<FileRecordMain> fileMdlList = fileRecordMainMapper.getCashBillImageListByProjectId(attachmentFile);
                fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, orderFileList);
                if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
                	fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
                }
                cashBillDto.setFileRecordMainIds(fileRecordMainIds);
                cashBillDto.setCashBillFileList(orderFileList);
            }

            Map<String, String> fileMap = new HashMap<String, String>();
            fileMap.put("refId", cashBillDto.getProParentId().toString());
            fileMap.put("fileTypeId", "1032");
            fileMap.put("fileSourceId", "9");
            List<FileRecordMain> fileList1 = fileRecordMainMapper.queryList(fileMap);

            fileMap.put("fileTypeId", "1089");
            List<FileRecordMain> fileList2 = fileRecordMainMapper.queryList(fileMap);
            if(!CollectionUtils.isEmpty(fileList2)) {
            	fileList1.addAll(fileList2);
            }

            fileMap.put("fileTypeId", "1090");
            List<FileRecordMain> fileList3 = fileRecordMainMapper.queryList(fileMap);
            if(!CollectionUtils.isEmpty(fileList3)) {
            	fileList1.addAll(fileList3);
            }
            cashBillDto.setFileList(fileList1);

            // 取得考核主体信息
            if (cashBillDto.getAccountProjectNo() != null && !cashBillDto.getAccountProjectNo().isEmpty()){
                Map<String,Object> parmsCheckBody = new HashMap<>();
                parmsCheckBody.put("accountProjectCode",cashBillDto.getAccountProjectNo());
                List<Map<String, Object>> checkBodyList = oaCashBillReturnMapper.getOACheckBodyList(parmsCheckBody);
                cashBillDto.setCheckBodyList(checkBodyList);
            }

            resultData.setReturnData(cashBillDto);
            resultData.setSuccess();
        }
        return resultData;
    }

    /**
     * 图片信息
     * @param
     * @return
     */
    private String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList)
        throws Exception {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (int i = 0; i < fileMdlList.size(); i++) {
                ContractFileDto contractFileDto = new ContractFileDto();
                FileRecordMain fileRecordMain = fileMdlList.get(i);
                //对文件数据进行组装处理
                handleFileRecordMain(contractFileDto, fileRecordMain);
                //重新组装返回list
                fileList.add(contractFileDto);
                //对营业执照、法人身份证、公盘服务协议不拼接
                fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";
            }
        }
        return fileRecordMainIds;
    }

    /**
     * 对文件数据进行组装处理
     * @param contractFileDto
     * @param fileRecordMain
     * @throws Exception
     */
     private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)
         throws Exception {
         //获取图片路径
          contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
          contractFileDto.setFileName(fileRecordMain.getFileFullName());
          contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
          contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
          contractFileDto.setUrl50(fileRecordMain.getUrl50());
          contractFileDto.setFileSuffix(fileRecordMain.getFileSuffix());
     }

    /**
     * @param map estateId/userCode/companyId 必传
     * @return
     * @throws Exception
     */
    public ResultData<String> listToSubmitOa(Map map) throws Exception {

        ResultData<String> resultData = new ResultData<>();
        resultData.setReturnMsg("请款单已提交！");
        String errorMsg = "";

        //验证报备是否满足条件
        List<Integer> comRowIdList = new ArrayList<>();
        List<Integer> repRowIdList = new ArrayList<>();
        List<Integer> reportIdList = new ArrayList<>();
        List<Integer> offsetIdList = new ArrayList<>();

        //CashBillProject project = cashBillProjectMapper.findByDynamic(map);
        CashBillProject project = cashBillProjectMapper.findByproParentId(map);
        Map<String, Object> cmap = new HashMap<>();
        cmap.put("proParentId", project.getId());
        cmap.put("companyId", map.get("companyId"));
        List<CashBillCompany> companyList = cashBillCompanyMapper.queryByParentId(cmap);
        String accountProjectNo = "";
        String accountProject = "";
        for (CashBillCompany company : companyList) {
            accountProjectNo = company.getAccountProjectNo();
            accountProject = company.getAccountProject();

            final Company byCompanyId = companyService.getByCompanyId(company.getCompanyId());
            company.setBusinessLicenseNo(byCompanyId.getBusinessLicenseNo());
            comRowIdList.add(company.getId());

            List<CashBillReport> reportList = cashBillReportMapper.queryByParentId(company.getId());
            company.setReportList(reportList);
            for (CashBillReport report : reportList) {
                repRowIdList.add(report.getId());
                if(report.getOffSetFlag()){
                    offsetIdList.add(report.getReportId());
                }else{
                    reportIdList.add(report.getReportId());
                }
            }
        }
        //正常请款列表大定成销状态判断
        Map reportIdMap = new HashMap<>();
        reportIdMap.put("reportIdList", reportIdList);
        List<CashBillReport> unreasonableReport = cashBillReportMapper.findUnreasonableReport(reportIdMap);
        if (CollectionUtils.isNotEmpty(unreasonableReport)) {
            for (CashBillReport unReport : unreasonableReport) {
                errorMsg += unReport.getReportNo() + ",";
            }
            errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
            resultData.setFail("正常请款房源列表中报备：" + errorMsg + "不是大定或成销状态，不允许请款！");
            return resultData;
        }
        //冲抵请款列表大定成销状态判断
        if(CollectionUtils.isNotEmpty(offsetIdList)){
            Map offsetMap = new HashMap();
            offsetMap.put("reportIdList",offsetIdList);
            List<CashBillReport> unReport = cashBillReportMapper.findUnOffsetReport(offsetMap);
            if (CollectionUtils.isNotEmpty(unReport)) {
                for (CashBillReport unRept : unReport) {
                    errorMsg += unRept.getReportNo() + ",";
                }
                errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
                resultData.setFail("冲抵请款房源列表中报备：" + errorMsg + "不是大定或成销状态，不允许请款！");
                return resultData;
            }
        }

        Map<String, String> accountProjectByEstateId = cashBillProjectMapper.getAccountProjectByEstateId(project.getEstateId());
        if (accountProjectByEstateId == null) {
            resultData.setFail("项目：" + project.getEstateNm() + "所对应城市无核算主体信息");
            return resultData;
        }
        // 原核算主体改为联动核算主体
        //String accountProject = accountProjectByEstateId.get("accountProject");
        //String accountProjectNo = accountProjectByEstateId.get("accountProjectCode");
        String cashBillBatchTpl = accountProjectByEstateId.get("cashBillBatchTpl");
        String cashBillBatchTplName = accountProjectByEstateId.get("cashBillBatchTplName");

        final ResultData<User> userByCode = userAPI.getUserByCode(project.getUserCode());
        if ("200".equals(userByCode.getReturnCode())) {
            project.setUserName(userByCode.getReturnData().getUserName());
        }

        final WebConfig byName = webConfigMapper.getByName("oaPayType_" + project.getPayType());
        if (byName != null) {
            project.setPayTypeNm(byName.getWebConfigValue());
        }
        //提交
//        for (CashBillCompany company : companyList) {
//            //company.setAccountProject(accountProject);
//            // company.setAccountProjectNo(accountProjectNo);
////            if (checkOaVendorInfo(project, resultData, accountProjectNo, company)) return resultData;
//
////            submitToOa(project, company, resultData, cashBillBatchTpl);
//            //hzg 2019/08/16 提交oa
//            getQkOaAnnex(company);
//            submitOaCashBill(project, company, resultData,cashBillBatchTpl);
//        }

        for (CashBillCompany company : companyList) {
        	getQkOaAnnexFile(company);
			logger.info("提交请款单公司编号:"+company.getCompanyNo());
			//修改请款单-利用视图发送oa
			submitOaCashBillByView(project, company, resultData, cashBillBatchTpl,cashBillBatchTplName);
        }



        return resultData;
    }

    public List<Map<String, Object>> getLnkAccountProjectList(String cityNo) throws Exception {
        List<Map<String, Object>> map = cashBillProjectMapper.getAccountProjectByCityNo(cityNo);
        if (null != map) {
            return map;
        } else {
            return null;
        }
    }

    /**
     * OA审批联动请款处理
     */
    public ResultData<?> handleCashBill() throws Exception {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        // 获取待处理数据
        List<OaCashBillReturn> dataLst = oaCashBillReturnMapper.getOaCashBillReturn();
        if (null == dataLst || dataLst.isEmpty()) {
            resultData.setFail("没有待处理的数据!");
            return resultData;
        }

        for (OaCashBillReturn data : dataLst) {
            OaCashBillReturn uptPram = new OaCashBillReturn();
            uptPram.setId(data.getId());
            uptPram.setDateUpdate(new Date());
            try {

                // 取得请款信息（公司）
                Map<String, Object> com_param = new HashMap<>();
                com_param.put("cashBillNo", data.getCashBillNo());
                com_param.put("flowId", data.getFlowId());
                CashBillCompany cashBillCompany = cashBillCompanyMapper.getByCashBillNo(com_param);

                if (cashBillCompany != null) {
                    logger.info("请款审核定时任务执行 start##"+JSON.toJSONString(data)+"##cashBillCompany="+JSON.toJSONString(cashBillCompany));
                    CashBillCompany updateInfo = new CashBillCompany();
                    updateInfo.setId(cashBillCompany.getId());

                    if (data.getApproveStatus() == 1) {
                        logger.info("请款审核定时任务执行 审核通过##"+JSON.toJSONString(data)+"##cashBillCompany="+JSON.toJSONString(cashBillCompany));
                        //1. 取得差额结果数据集
                        Map<String, Object> adj_param = new HashMap<>();
                        adj_param.put("oaRetId",data.getId());
                        adj_param.put("comParentId",cashBillCompany.getId());
                        List<CashBillReportAdjustTax> adjustTaxList = oaCashBillReportReturnMapper.getCashBillAdjustTax(adj_param);
                        for (CashBillReportAdjustTax adjustTax:adjustTaxList) {

                            // 1.1 更新差异税额报备数据
                            CashBillReport cashBillReport_uptParms = new CashBillReport();
                            cashBillReport_uptParms.setId(adjustTax.getRepParentId());
                            cashBillReport_uptParms.setTaxAmount(adjustTax.getAdjustAfterTaxAmount());
                            cashBillReportMapper.updateByPrimaryKeySelective(cashBillReport_uptParms);

                            // 1.2 新增保存待处理应计结果（用于存储过程对应计数据进行，税额更新）
                            cashBillReportAdjustTaxMapper.insert(adjustTax);

                        }
                        if(adjustTaxList.size()>0) {
                            // 1.3 更新主表总计信息
                            CashBillCompany newTotatl = cashBillReportMapper.getTotalInfo(cashBillCompany.getId());
                            updateInfo.setTaxAmountTotal(newTotatl.getTaxAmountTotal());
                        }
                    }

                    // 更新请款信息（公司）信息

                    // 审核状态
                    if (data.getApproveStatus().intValue() == 1) {
                        logger.info("请款审核定时任务执行 审核通过##"+JSON.toJSONString(data)+"##cashBillCompany="+JSON.toJSONString(cashBillCompany));
                        updateInfo.setApproveStatus(25403);
                        updateInfo.setSyncFlag(0);
                    } else {
                        logger.info("请款审核定时任务执行 审核失败##"+JSON.toJSONString(data)+"##cashBillCompany="+JSON.toJSONString(cashBillCompany));
                        updateInfo.setApproveStatus(25404);
                        updateInfo.setSyncFlag(-1);
                    }
                    // OA 单号
                    updateInfo.setOaNo(data.getOaNo());
                    // 审核时间
                    updateInfo.setApproveTime(data.getApproveDate());
                    // 入账日期
                    updateInfo.setRecordDate(data.getRecordDate());
                    cashBillCompanyMapper.updateByPrimaryKeySelective(updateInfo);

                    // 更新状态
                    uptPram.setDescription("");
                    uptPram.setHasDeal(1);
                    oaCashBillReturnMapper.updateByPrimaryKeySelective(uptPram);


                    // 审核状态
                    if (data.getApproveStatus().intValue() == 1) {
                        logger.info("请款审核定时任务执行 审核通过execQkZeroSync##"+JSON.toJSONString(data)+"##cashBillCompany="+JSON.toJSONString(cashBillCompany));
                        BigDecimal bd = new BigDecimal("0.00");
                        if(cashBillCompany.getRequestAmountTotal()!=null
                            && bd.compareTo(cashBillCompany.getRequestAmountTotal())==0){
                            Map<String,Object> execMap = new HashMap<>();
                            execMap.put("cashBillNo",cashBillCompany.getCashBillNo());
                            execMap.put("returnCode","");
                            execMap.put("returnMsg","");
                            cashBillCompanyMapper.execQkZeroSync(execMap);
                            logger.info("请款定时任务##handleCashBill##P_CRM_CASH_BILL_ZERO_SYNC##"+ JSON.toJSONString(execMap));
                        }
                    } else {
                        logger.info("请款审核定时任务执行 审核失败execQkUptJs##"+JSON.toJSONString(data)+"##cashBillCompany="+JSON.toJSONString(cashBillCompany));
                        Map<String,Object> execMap = new HashMap<>();
                        execMap.put("id",cashBillCompany.getId());
                        execMap.put("opType",2);
                        cashBillCompanyMapper.execQkUptJs(execMap);
                    }
                }else{
                    // 更新状态
                    uptPram.setDescription("通过cashBillNo + flowId 没有找到记录，请人工干预！");
                    uptPram.setHasDeal(3);
                    oaCashBillReturnMapper.updateByPrimaryKeySelective(uptPram);
                }
            } catch (Exception ex) {
                uptPram.setHasDeal(2);
                uptPram.setDescription("同步处理发生错误！");
                oaCashBillReturnMapper.updateByPrimaryKeySelective(uptPram);
            }
        }


        return resultData;
    }


    public List<Map<String, Object>> getOALnkAccountProjectList(String projectNo) throws Exception {
        List<Map<String, Object>> map = oaCashBillReturnMapper.getOALnkAccountProjectList(projectNo);
        return map;
    }

    public List<Map<String, Object>> getOAFrmAgreementList(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> map = oaCashBillReturnMapper.getOAFrmAgreementList(param);
        return map;
    }

    public List<Map<String, Object>> getOAReceiveBankList(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> map = oaCashBillReturnMapper.getOAReceiveBankList(param);
        return map;
    }
    public List<Map<String, Object>> getOACheckBodyList(Map<String, Object> param) throws Exception {
        List<Map<String, Object>> map = oaCashBillReturnMapper.getOACheckBodyList(param);
        return map;
    }
    /**
     * desc:提交oa
     * 2019年8月16日
     * author:zhenggang.Huang
     */
//    @Async("threadPoolTaskExecutorTwo")
    	public void submitOaCashBill(CashBillProject project, CashBillCompany company,  ResultData<String> resultData, String cashBillBatchTpl) {
        Map<String, Object> sendOaData = new HashMap<>();

        String cashBillNo = null;
        if (StringUtil.isEmpty(company.getCashBillNo())) {
            ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_LD_QK");
            if (data != null && data.getReturnCode().equals("200")) {
                cashBillNo = data.getReturnData();
            } else {
                new NullPointerException("根据seq_type: TYPE_LD_QK 获取联动批量请款编号为空");
            }
        } else {
            cashBillNo = company.getCashBillNo();
        }

        // 模板编号
        sendOaData.put("templateCode", cashBillBatchTpl);
        // 发起者的登录名（登录协同的登录名）
        sendOaData.put("senderLoginName", project.getUserCode());
        // 协同的标题
        sendOaData.put("subject", "易居房产交易 " + cashBillNo + " " + project.getEstateNm() + " " + company.getCompanyName());

        try {
            //附件集合汇总
            List<Long> attachList = new ArrayList<>();

            StringBuffer stringBuffer = new StringBuffer("订单编号：");

            List<CashBillReport> reportList = company.getReportList();
            List<CashBillReport> offsetList = company.getOffSetList();
            if(CollectionUtils.isNotEmpty(offsetList)){
                reportList.addAll(offsetList);
            }

            for (CashBillReport report : reportList) {

                if(report.getReportId()==null) continue;

                stringBuffer.append(report.getReportNo()).append(" ");

                // 带看单_
                this.getOaAttachment(attachList, report.getReportId(), 1022, project.getUserCode(), "带看单_" + report.getBuildingNo());

                // 甲方项目负责人名片_
                this.getOaAttachment(attachList, report.getReportId(), 1024, project.getUserCode(), "甲方项目负责人名片_" + report.getBuildingNo());
            }

            // 成销确认书/佣金结算资料
            this.getOaAttachment(attachList, project.getId(), 1032, project.getUserCode(), "成销确认书_");

            //框架合同附件
            if (company.getFrameFileUrl() != null) {
                String attachmentId = UploadOAUtil.oaAttachmentUpload(company.getFrameFileUrl(), company.getFrameFileName(), project.getUserCode(), "");
                if (StringUtil.isNotEmpty(attachmentId)) {
                    attachList.add(Long.valueOf(attachmentId));
                }
            }

            StringBuilder detail = new StringBuilder();
          //自动生成oa编码
            ResultData<?> fcjyNo= seqNoAPI.getSeqNoByTypeCode("FCJY_LDQKC");
			String crmOaNo = (String)fcjyNo.getReturnData();
//            发送新房联动请款单 测试test/正式formal xml配置选择
            String qkXmlType = SystemParam.getWebConfigValue("lnkqkxml");
            String dataXml = "";
            if(!StringUtil.isEmpty(qkXmlType) && "test".equals(qkXmlType)) {
	            for (CashBillReport report : reportList) {
                    if(report.getReportId()==null) continue;

	//            	根据报备编号查询考核主体:bb对应的业绩所对应的  考核主体
	            	String reportNo = report.getReportNo();
//	            	Map<String,Object> reqMap = new HashMap<>();
//	            	reqMap.put("reportNo", reportNo);
//	            	Map<String,Object> checkBoby = cashBillCompanyMapper.getCheckBodyByReportNo(reqMap);
	            	//税率 税额/含税请款金额*100%，四舍五入，取整数。
	            	BigDecimal rate = new BigDecimal(0);
	            	BigDecimal requestAmount = report.getRequestAmount() == null ? BigDecimal.ZERO:report.getRequestAmount();
	            	if (BigDecimal.ZERO.compareTo(requestAmount) != 0) {
	            		rate = report.getTaxAmount().divide(requestAmount, 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(0,BigDecimal.ROUND_HALF_UP);
	            	}
	            	Integer requestType = report.getRequestType();
	            	String requestTypeStr = null;
	            	if(null != requestType){
	            		if(requestType ==1) {
	            			//8954313067664917860 返佣
	            			requestTypeStr= SystemParam.getWebConfigValue("lnkqksy_fy");
	            		}else if(requestType ==2) {
	//            			-2078367516690199310 垫佣
	            			requestTypeStr=SystemParam.getWebConfigValue("lnkqksy_dy");
	            		}
	            	}                    Map<String, Object> projectMap = cashBillReportMapper.getOaProjectByProjectNo(report.getReportId());
                    String oaProjectName = "";
                    String oaProjectNo = "";

                    if (projectMap != null) {
                        oaProjectName = (String) projectMap.get("oaProjectName");
                        oaProjectNo = (String) projectMap.get("oaProjectNo");
                    }

                    detail.append("<row>"
                            + "<column name=\"未付金额\"><value>" + "0" + "</value></column>"
                            + "<column name=\"付款金额\"><value>" + report.getRequestAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "</value></column>"
                            + "<column name=\"税额\"><value>" + report.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "</value></column>"
                            + "<column name=\"税率\"><value>" + rate + "</value></column>"
                            + "<column name=\"说明\"><value>" + report.getMemo() + "</value></column>"
                            + "<column name=\"行项目\"><value>" + oaProjectName + "</value></column>"
                            + "<column name=\"行成本中心\"><value>" + "缺省" + "</value></column>"
                            + "<column name=\"行费用类别\"><value>" + "主营业务成本-新房联动成本(交易服务)" + "</value></column>"
                            + "<column name=\"行费用归档编码\"><value>" + "" + "</value></column>"
                            + "<column name=\"行考核主体编码\"><value>" + (report.getCheckBodyId() == null ? "" : report.getCheckBodyId()) + "</value></column>"
                            + "<column name=\"行成本中心编码\"><value>" + "0" + "</value></column>"
                            + "<column name=\"行考核主体\"><value>" + (report.getCheckBodyName() == null ? "" : report.getCheckBodyName()) + "</value></column>"
                            + "<column name=\"行费用归档\"><value>" + "新房联动成本(交易服务)(应付联动暂估)" + "</value></column>"
                            + "<column name=\"行费用类别编码\"><value>" + "4721" + "</value></column>"
                            + "<column name=\"行项目编码\"><value>" + oaProjectNo + "</value></column>"
                            + "<column name=\"行明细\"><value>" + (company.getFrameOaName() == null ? "" : company.getFrameOaName()) + "</value></column>"
                            + "<column name=\"报备编号\"><value>" + (report.getReportNo() == null ? "" : report.getReportNo()) + "</value></column>"
                            + "<column name=\"请款事由\"><value>" + (requestTypeStr == null ? "" : requestTypeStr) + "</value></column>"
                            + "</row>");
                }


                // 组装向OA发送的参数信息 测试:6045487298903782888  正式:-4082679746124166366
            dataXml =
                    "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                            + "<summary id=\"-6045487298903782888\" name=\"formmain_3986\"/>" + "<definitions/>" + "<values>"
                            + "<column name=\"编码\"><value>"
                            + (crmOaNo==null?"":crmOaNo)
                            + "</value></column>"
                            + "<column name=\"请款类型\"><value>"
                            + "-2247489456137744808"
                            + "</value></column>"
                            + "<column name=\"CRM编号\"><value>"
                            + cashBillNo
                            + "</value></column>"
                            + "<column name=\"所属板块\"><value>"
                            + "房产交易"
                            + "</value></column>"
                            + "<column name=\"核算主体\"><value>"
                            + company.getAccountProject()
                            + "</value></column>"
                            + "<column name=\"采购分类1\"><value>"
                            + "服务"
                            + "</value></column>"
                            + "<column name=\"采购分类2\"><value>"
                            + "业务资源"
                            + "</value></column>"
                            + "<column name=\"采购分类3\"><value>"
                            + "新房联动"
                            + "</value></column>"
                            + "<column name=\"供应商\"><value>"
                            + (company.getVendorName() == null ? "" : company.getVendorName())
                            + "</value></column>"
                            + "<column name=\"供应商编码\"><value>"
                            + (company.getVendorId() == null ? "" : company.getVendorId())
                            + "</value></column>"
                            + "<column name=\"合同单据号\"><value>"
                            + (company.getFrameOaNo() == null ? "" : company.getFrameOaNo())
                            + "</value></column>"
                            + "<column name=\"经办人\"><value>"
                            + project.getUserName()
                            + "</value></column>"
                            + "<column name=\"经办人工号\"><value>"
                            + project.getUserCode()
                            + "</value></column>"
                            + "<column name=\"框架合同\"><value>"
//                            + (company.getISKJ() == null ? "" : company.getISKJ())
//                            + (company.getFrameOaName() == null ? "" : company.getFrameOaName())
                            + "是"
                            + "</value></column>"
                            + "<column name=\"收款银行\"><value>"
                            + (company.getReceiveBankName() == null ? "" : company.getReceiveBankName())
                            + "</value></column>"
                            + "<column name=\"银行账号\"><value>"
                            + (company.getReceiveBankAccountCardCode() == null ? "" : company.getReceiveBankAccountCardCode())
                            + "</value></column>"
                            + "<column name=\"入账日期\"><value>"
                            + (company.getRecordDate()==null?"":DateUtil.fmtDate(company.getRecordDate(), "yyyy-MM-dd"))
                            + "</value></column>"
                            + "<column name=\"最后付款日期\"><value>"
                            + (project.getPredictPayTime()==null?"":DateUtil.fmtDate(project.getPredictPayTime(), "yyyy-MM-dd"))
                            + "</value></column>"
                             + "<column name=\"付款方式\"><value>"
                            + project.getPayTypeNm()
                            + "</value></column>"
                            + "<column name=\"申请人\"><value>"
                            + project.getUserName()
                            + "</value></column>"
                            + "<column name=\"申请人工号\"><value>"
                            + project.getUserCode()
                            + "</value></column>"
                            + "<column name=\"备注\"><value>"
                            + (company.getRemarks() == null ? "" : company.getRemarks())
//                            + ""
                            + "</value></column>"
                            + "<column name=\"不含税请款合计\"><value>"
                            + (company.getRequestAmountTotal().subtract(company.getTaxAmountTotal())).setScale(2,BigDecimal.ROUND_HALF_UP)
                            + "</value></column>"
                            + "<column name=\"税额合计\"><value>"
                            + company.getTaxAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP)
                            + "</value></column>"
                            + "<column name=\"本次请款合计\"><value>"
                            + company.getRequestAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP)
                            + "</value></column>"
                            + "<column name=\"核算主体编码\"><value>"
                            + company.getAccountProjectNo()
                            + "</value></column>"
                            + "<column name=\"票据附件\"><value>"
                            + ""
                            + "</value></column>"
                            + "<column name=\"板块编码\"><value>"
                            + "FCJY"
                            + "</value></column>"
                            + "<column name=\"账户序号\"><value>"
                            + (company.getReceiveBankSerialNo() == null ? "" : company.getReceiveBankSerialNo())
                            + "</value></column>"
                            + "<column name=\"收款人户名\"><value>"
                            + (company.getReceiveBankAccountName() == null ? "" : company.getReceiveBankAccountName())
                            + "</value></column>"
                            + "<column name=\"省\"><value>"
                            + (company.getReceiveBankProvinceName() == null ? "" : company.getReceiveBankProvinceName())
                            + "</value></column>"
                            + "<column name=\"银行供应商id\"><value>"
                            + (company.getVendorId() == null ? "" : company.getVendorId())
                            + "</value></column>"
                            + "<column name=\"市\"><value>"
                            + (company.getReceiveBankCityName() == null ? "" : company.getReceiveBankCityName())
                            + "</value></column>"
                            + "</values>"
                            + "<subForms>"
                            + "<subForm>"
                            + "<definitions>"
                            + "<column id=\"field0039\" type=\"0\" name=\"未付金额\" length=\"255\"/>"
                            + "<column id=\"field0040\" type=\"0\" name=\"付款金额\" length=\"255\"/>"
                            + "<column id=\"field0041\" type=\"0\" name=\"税额\" length=\"255\"/>"
                            + "<column id=\"field0042\" type=\"0\" name=\"税率\" length=\"255\"/>"
                            + "<column id=\"field0043\" type=\"0\" name=\"说明\" length=\"255\"/>"
                            + "<column id=\"field0033\" type=\"0\" name=\"行项目\" length=\"255\"/>"
                            + "<column id=\"field0035\" type=\"0\" name=\"行成本中心\" length=\"255\"/>"
                            + "<column id=\"field0029\" type=\"0\" name=\"行费用类别\" length=\"255\"/>"
                            + "<column id=\"field0038\" type=\"0\" name=\"行费用归档编码\" length=\"255\"/>"
                            + "<column id=\"field0032\" type=\"0\" name=\"行考核主体编码\" length=\"255\"/>"
                            + "<column id=\"field0036\" type=\"0\" name=\"行成本中心编码\" length=\"255\"/>"
                            + "<column id=\"field0031\" type=\"0\" name=\"行考核主体\" length=\"255\"/>"
                            + "<column id=\"field0037\" type=\"0\" name=\"行费用归档\" length=\"255\"/>"
                            + "<column id=\"field0030\" type=\"0\" name=\"行费用类别编码\" length=\"255\"/>"
                            + "<column id=\"field0034\" type=\"0\" name=\"行项目编码\" length=\"255\"/>"
                            + "<column id=\"field0028\" type=\"0\" name=\"行明细\" length=\"255\"/>"
                            + "<column id=\"field0049\" type=\"0\" name=\"报备编号\" length=\"255\"/>"
                            + "<column id=\"field0051\" type=\"0\" name=\"请款事由\" length=\"255\"/>"
                            + "</definitions>"
                            + "<values>"
                            + detail.toString() + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";
            //正式
            }else if(!StringUtil.isEmpty(qkXmlType) && "formal".equals(qkXmlType)) {
            	for (CashBillReport report : reportList) {
                    if(report.getReportId()==null) continue;

            		//            	根据报备编号查询考核主体:bb对应的业绩所对应的  考核主体
            		String reportNo = report.getReportNo();
//            		Map<String,Object> reqMap = new HashMap<>();
//            		reqMap.put("reportNo", reportNo);
//            		Map<String,Object> checkBoby = cashBillCompanyMapper.getCheckBodyByReportNo(reqMap);
            		//税率 税额/含税请款金额*100%，四舍五入，取整数。
            		BigDecimal rate = new BigDecimal(0);
            		BigDecimal requestAmount = report.getRequestAmount() == null ? BigDecimal.ZERO:report.getRequestAmount();
            		if (BigDecimal.ZERO.compareTo(requestAmount) != 0) {
            			rate = report.getTaxAmount().divide(requestAmount, 2, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100)).setScale(0,BigDecimal.ROUND_HALF_UP);
            		}
            		Integer requestType = report.getRequestType();
            		String requestTypeStr = null;
            		if(null != requestType){
            			if(requestType ==1) {
            				//8954313067664917860 返佣
            				requestTypeStr= SystemParam.getWebConfigValue("lnkqksy_fy");
            			}else if(requestType ==2) {
            				//            			-2078367516690199310 垫佣
            				requestTypeStr=SystemParam.getWebConfigValue("lnkqksy_dy");
            			}
            		}
                    Map<String, Object> projectMap = cashBillReportMapper.getOaProjectByProjectNo(report.getReportId());
                    String oaProjectName = "";
                    String oaProjectNo = "";

                    if (projectMap != null) {
                        oaProjectName = (String) projectMap.get("oaProjectName");
                        oaProjectNo = (String) projectMap.get("oaProjectNo");
                    }
                    detail.append("<row>"
                            + "<column name=\"行明细\"><value>" + (company.getFrameOaName() == null ? "" : company.getFrameOaName()) + "</value></column>"
                            + "<column name=\"行费用类别\"><value>" + "主营业务成本-新房联动成本(交易服务)" + "</value></column>"
                            + "<column name=\"行费用类别编码\"><value>" + "6401811003" + "</value></column>"
                            + "<column name=\"行考核主体\"><value>" + (report.getCheckBodyName() == null ? "" : report.getCheckBodyName()) + "</value></column>"
                            + "<column name=\"行考核主体编码\"><value>" + (report.getCheckBodyId() == null ? "" : report.getCheckBodyId()) + "</value></column>"
                            + "<column name=\"行项目\"><value>" + oaProjectName + "</value></column>"
                            + "<column name=\"行项目编码\"><value>" + oaProjectNo + "</value></column>"
                            + "<column name=\"行成本中心\"><value>" + "缺省" + "</value></column>"
                            + "<column name=\"行成本中心编码\"><value>" + "0" + "</value></column>"
                            + "<column name=\"行费用归档\"><value>" + "新房联动成本(交易服务)(应付联动暂估)" + "</value></column>"
                            + "<column name=\"行费用归档编码\"><value>" + "4721" + "</value></column>"
                            + "<column name=\"未付金额\"><value>" + "0" + "</value></column>"
                            + "<column name=\"付款金额\"><value>" + report.getRequestAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "</value></column>"
                            + "<column name=\"税额\"><value>" + report.getTaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP) + "</value></column>"
                            + "<column name=\"税率\"><value>" + rate + "</value></column>"
                            + "<column name=\"说明\"><value>" + report.getMemo() + "</value></column>"
                            + "<column name=\"报备编号\"><value>" + (report.getReportNo() == null ? "" : report.getReportNo()) + "</value></column>"
                            + "<column name=\"请款事由\"><value>" + (requestTypeStr == null ? "" : requestTypeStr) + "</value></column>"
                            + "</row>");
                }

            	// 组装向OA发送的参数信息 测试:6045487298903782888  正式:-4082679746124166366
            	dataXml =
            			"<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
            					+ "<summary id=\"-4082679746124166366\" name=\"formmain_6718\"/>" + "<definitions/>" + "<values>"
            					+ "<column name=\"编码\"><value>"
            					+ (crmOaNo==null?"":crmOaNo)
            					+ "</value></column>"
            					+ "<column name=\"请款类型\"><value>"
            					+ "-2247489456137744808"
            					+ "</value></column>"
            					+ "<column name=\"CRM编号\"><value>"
            					+ cashBillNo
            					+ "</value></column>"
            					+ "<column name=\"所属板块\"><value>"
            					+ "房产交易"
            					+ "</value></column>"
            					+ "<column name=\"板块编码\"><value>"
            					+ "FCJY"
            					+ "</value></column>"
            					+ "<column name=\"核算主体\"><value>"
            					+ company.getAccountProject()
            					+ "</value></column>"
            					+ "<column name=\"采购分类1\"><value>"
            					+ "服务"
            					+ "</value></column>"
            					+ "<column name=\"采购分类2\"><value>"
            					+ "业务资源"
            					+ "</value></column>"
            					+ "<column name=\"采购分类3\"><value>"
            					+ "新房联动"
            					+ "</value></column>"
            					+ "<column name=\"供应商\"><value>"
            					+ (company.getVendorName() == null ? "" : company.getVendorName())
            					+ "</value></column>"
            					+ "<column name=\"供应商编码\"><value>"
            					+ (company.getVendorId() == null ? "" : company.getVendorId())
            					+ "</value></column>"
            					+ "<column name=\"合同单据号\"><value>"
            					+ (company.getFrameOaNo() == null ? "" : company.getFrameOaNo())
            					+ "</value></column>"
            					+ "<column name=\"经办人\"><value>"
            					+ project.getUserName()
            					+ "</value></column>"
            					+ "<column name=\"经办人工号\"><value>"
            					+ project.getUserCode()
            					+ "</value></column>"
            					+ "<column name=\"框架合同\"><value>"
//            					+ (company.getISKJ() == null ? "" : company.getISKJ())
//            					+ (company.getFrameOaName() == null ? "" : company.getFrameOaName())
								+ "是"
            					+ "</value></column>"
            					+ "<column name=\"收款银行\"><value>"
            					+ (company.getReceiveBankName() == null ? "" : company.getReceiveBankName())
            					+ "</value></column>"
            					+ "<column name=\"银行账号\"><value>"
            					+ (company.getReceiveBankAccountCardCode() == null ? "" : company.getReceiveBankAccountCardCode())
            					+ "</value></column>"
            					+ "<column name=\"入账日期\"><value>"
            					+ (company.getRecordDate()==null?"":DateUtil.fmtDate(company.getRecordDate(), "yyyy-MM-dd"))
            					+ "</value></column>"
            					+ "<column name=\"最后付款日期\"><value>"
            					+ (project.getPredictPayTime()==null?"":DateUtil.fmtDate(project.getPredictPayTime(), "yyyy-MM-dd"))
            					+ "</value></column>"
            					+ "<column name=\"付款方式\"><value>"
            					+ project.getPayTypeNm()
            					+ "</value></column>"
            					+ "<column name=\"申请人\"><value>"
            					+ project.getUserName()
            					+ "</value></column>"
            					+ "<column name=\"申请人工号\"><value>"
            					+ project.getUserCode()
            					+ "</value></column>"
            					+ "<column name=\"备注\"><value>"
            					+ (company.getRemarks() == null ? "" : company.getRemarks())
//            					+ ""
            					+ "</value></column>"
            					+ "<column name=\"不含税请款合计\"><value>"
            					+ (company.getRequestAmountTotal().subtract(company.getTaxAmountTotal())).setScale(2,BigDecimal.ROUND_HALF_UP)
            					+ "</value></column>"
            					+ "<column name=\"税额合计\"><value>"
            					+ company.getTaxAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP)
            					+ "</value></column>"
            					+ "<column name=\"本次请款合计\"><value>"
            					+ company.getRequestAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP)
            					+ "</value></column>"
            					+ "<column name=\"核算主体编码\"><value>"
            					+ company.getAccountProjectNo()
            					+ "</value></column>"
            					+ "<column name=\"票据附件\"><value>"
            					+ ""
            					+ "</value></column>"
            					+ "<column name=\"账户序号\"><value>"
            					+ (company.getReceiveBankSerialNo() == null ? "" : company.getReceiveBankSerialNo())
            					+ "</value></column>"
            					+ "<column name=\"收款人户名\"><value>"
            					+ (company.getReceiveBankAccountName() == null ? "" : company.getReceiveBankAccountName())
            					+ "</value></column>"
            					+ "<column name=\"省\"><value>"
            					+ (company.getReceiveBankProvinceName() == null ? "" : company.getReceiveBankProvinceName())
            					+ "</value></column>"
            					+ "<column name=\"银行供应商id\"><value>"
            					+ (company.getVendorId() == null ? "" : company.getVendorId())
            					+ "</value></column>"
            					+ "<column name=\"市\"><value>"
            					+ (company.getReceiveBankCityName() == null ? "" : company.getReceiveBankCityName())
            					+ "</value></column>"
            					+ "</values>"
            					+ "<subForms>"
            					+ "<subForm>"
            					+ "<definitions>"
            					+ "<column id=\"field0029\" type=\"0\" name=\"行明细\" length=\"255\"/>"
            					+ "<column id=\"field0030\" type=\"0\" name=\"行费用类别\" length=\"255\"/>"
            					+ "<column id=\"field0031\" type=\"0\" name=\"行费用类别编码\" length=\"255\"/>"
            					+ "<column id=\"field0032\" type=\"0\" name=\"行考核主体\" length=\"255\"/>"
            					+ "<column id=\"field0033\" type=\"0\" name=\"行考核主体编码\" length=\"255\"/>"
            					+ "<column id=\"field0034\" type=\"0\" name=\"行项目\" length=\"255\"/>"
            					+ "<column id=\"field0035\" type=\"0\" name=\"行项目编码\" length=\"255\"/>"
            					+ "<column id=\"field0036\" type=\"0\" name=\"行成本中心\" length=\"255\"/>"
            					+ "<column id=\"field0037\" type=\"0\" name=\"行成本中心编码\" length=\"255\"/>"
            					+ "<column id=\"field0038\" type=\"0\" name=\"行费用归档\" length=\"255\"/>"
            					+ "<column id=\"field0039\" type=\"0\" name=\"行费用归档编码\" length=\"255\"/>"
            					+ "<column id=\"field0040\" type=\"0\" name=\"未付金额\" length=\"255\"/>"
            					+ "<column id=\"field0041\" type=\"0\" name=\"付款金额\" length=\"255\"/>"
            					+ "<column id=\"field0042\" type=\"0\" name=\"税额\" length=\"255\"/>"
            					+ "<column id=\"field0043\" type=\"0\" name=\"税率\" length=\"255\"/>"
            					+ "<column id=\"field0044\" type=\"0\" name=\"说明\" length=\"255\"/>"
            					+ "<column id=\"field0050\" type=\"0\" name=\"报备编号\" length=\"255\"/>"
            					+ "<column id=\"field0051\" type=\"0\" name=\"请款事由\" length=\"255\"/>"
            					+ "</definitions>"
            					+ "<values>"
            					+ detail.toString() + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";

            }
            // 附件，Long型数组，值为附件的Id。
            sendOaData.put("attachments", attachList);
            sendOaData.put("data", dataXml);
            // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
//             sendOaData.put("state", "0");
            //0已发 1待发
            sendOaData.put("param", "0");

            // 取得REST动态客户机实例
            CTPRestClient client = getClient();
            String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
            //为登录验证后获取的身份令牌
            sendOaData.put("token", token);

            //发起申请 返回FlowId
            Long flowId = null;
            try {
                flowId = apiOaService.toOaAuth(sendOaData, cashBillBatchTpl);

            } catch (Exception e) {
                //logger.error("cashBill", "CashBillService", "submitOaCashBill", "", 0, "", "批量请款提交OA失败",null );
            }

            CashBillCompany sucUpt = new CashBillCompany();
            sucUpt.setId(company.getId());
            sucUpt.setCashBillNo(cashBillNo);
            if (flowId != null) {
                sucUpt.setFlowId(flowId + "");
                sucUpt.setSubmitOaStatus(21203);
                sucUpt.setApproveStatus(25402);//审批状态为25402 审批中
                sucUpt.setOaNo(crmOaNo);
            } else {
                sucUpt.setSubmitOaStatus(21204);
            }
            sucUpt.setUserCode(project.getUserCode());
            sucUpt.setApplyTime(new Date());
            cashBillCompanyMapper.updateByPrimaryKeySelective(sucUpt);


        } catch (Exception e) {
            //logger.error("cashBill", "CashBillService", "submitOaCashBill", "", 0, "", "批量请款提交OA失败", e);
            CashBillCompany exeUpt = new CashBillCompany();
            exeUpt.setId(company.getId());
            //提交失败
            exeUpt.setCashBillNo(cashBillNo);
            exeUpt.setSubmitOaStatus(21204);
            exeUpt.setCashBillNo(cashBillNo);
            exeUpt.setUserCode(project.getUserCode());
            exeUpt.setApplyTime(new Date());
            cashBillCompanyMapper.updateByPrimaryKeySelective(exeUpt);
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("批量请款提交OA失败");
        }
    }

    private CTPRestClient getClient() {
        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance(SystemParam.getWebConfigValue("oaUrl"));
        // 取得REST动态客户机实例
        CTPRestClient client = clientManager.getRestClient();
        return client;
    }

    public ResultData<String> saveOACashBill(CashBillProject project)  throws Exception{
        final ResultData<String> resultData = new ResultData<>();
        resultData.setReturnMsg("批量请款保存成功");
        String errorMsg = "";

        //验证报备是否满足条件
        List<Integer> reportIdList = new ArrayList<>();
        List<Integer> offsetIdList = new ArrayList<>();

        List<Integer> cashRptIdList = new ArrayList<>();


        List<CashBillCompany> companyList = project.getCompanyList();
        for (CashBillCompany company : companyList) {

            if (company.getCompanyId() == null) continue;

            final Company byCompanyId = companyService.getByCompanyId(company.getCompanyId());
            company.setBusinessLicenseNo(byCompanyId.getBusinessLicenseNo());


            List<CashBillReport> reportList = company.getReportList();
            for (CashBillReport report : reportList) {
                if(report.getReportId()==null) continue;

                reportIdList.add(report.getReportId());
                if(report.getId()!=null) {
                    cashRptIdList.add(report.getId());
                }
            }
            //冲抵记录
            List<CashBillReport> offsetList = company.getOffSetList();
            if(CollectionUtils.isNotEmpty(offsetList)){
                for (CashBillReport report:offsetList) {
                    if(report.getReportId()==null) continue;

                    offsetIdList.add(report.getReportId());
                    if(report.getId()!=null) {
                        cashRptIdList.add(report.getId());
                    }
                }
            }
        }

        if(project.getSubmitStatus().intValue() == 1) {

            //预计付款日期只能选择今天及以后的日期。
            if(project.getPredictPayTime()!=null){
                LocalDate now = LocalDate.now();
                LocalDateTime predictPayTime = LocalDateTime.ofInstant(project.getPredictPayTime().toInstant(), ZoneOffset.ofHours(8));
                LocalDate predictPayDate = predictPayTime.toLocalDate();

                if(predictPayDate.isBefore(now)){
                    resultData.setFail("预计付款日期只能选择今天及以后的日期。！");
                    return resultData;
                }
            }

            //提交时校验每个报备是否为大定或成销状态，否则提示： 报备编号BB2018080600002不是大定或成销状态，不允许请款，请从请款列表中删除！
            Map map = new HashMap<>();
            map.put("reportIdList", reportIdList);
            List<CashBillReport> unreasonableReport = cashBillReportMapper.findUnreasonableReport(map);
            if (CollectionUtils.isNotEmpty(unreasonableReport)) {
                for (CashBillReport unReport : unreasonableReport) {
                    errorMsg += unReport.getReportNo() + ",";
                }
                errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
                resultData.setFail("报备：" + errorMsg + "不是大定或成销状态，不允许请款，请从正常请款房源列表中删除！");
                return resultData;
            }
            //冲抵请款列表大定成销状态判断
            if(CollectionUtils.isNotEmpty(offsetIdList)){
                Map offsetMap = new HashMap();
                offsetMap.put("reportIdList",offsetIdList);
                List<CashBillReport> unReport = cashBillReportMapper.findUnOffsetReport(offsetMap);
                if (CollectionUtils.isNotEmpty(unReport)) {
                    for (CashBillReport unRept : unReport) {
                        errorMsg += unRept.getReportNo() + ",";
                    }
                    errorMsg = errorMsg.substring(0, errorMsg.length() - 1);
                    resultData.setFail("报备：" + errorMsg + "不是大定或成销状态，不允许请款，请从冲抵请款房源列表中删除！");
                    return resultData;
                }
            }

            //提交时校验入账日期是否处于关账月份，OA提供接口，如是则提示：入账日期在EBS系统已关账，请重新选择！
            Map ccs_parm = new HashMap<>();
            ccs_parm.put("relateSystem", "17402");
            Map<String, Object> mapSwitch = commonService.getSwitchLnk(ccs_parm);
            String cityNo = project.getCityNo();
            if(mapSwitch!=null){
                if ("52F1D510-DDA2-4A8D-9FD3-E165E933CEEC".equals(cityNo)) {//昆山当做上海处理
                    cityNo = "AAAD4421-21B1-46FD-9DE4-D5A3183CE260";
                }
                String yearMonth = (String) mapSwitch.get(cityNo);
                if(yearMonth!=null && project.getRecordTime()!=null) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(yearMonth);
                    if(date.after(project.getRecordTime())) {
                        resultData.setFail("入账日期在EBS系统已关账，请重新选择！");
                        return resultData;
                    }
                }
            }

            // 排他问题处理
            List<CashBillReport> dbCashReportList = cashBillReportMapper.selectByProParentId(project.getId());
            List<Integer> dbCashRptIdList = new ArrayList<>();
            // 数据库中的ID都在提交的ID中、提交的ID必须都在数据库中
            for (CashBillReport dbRec : dbCashReportList) {
                dbCashRptIdList.add(dbRec.getId());
            }
            if (compare(dbCashRptIdList, cashRptIdList) == false) {
                resultData.setFail("请款的订单发生变更，请保存后重新打开页面！");
                return resultData;
            }
        }



        //保存批量请款
        //Integer oldFlag = project.getSubmitStatus();
        // 提交的场合处理
//        project.setSubmitStatus(0);
        cashBillProjectMapper.updateByPrimaryKeySelective(project);
//        String accountProjectNo ="";
//        String accountProject   ="";
        for (CashBillCompany company : companyList) {
//        	accountProjectNo = company.getAccountProjectNo();
//            accountProject = company.getAccountProject();
            if (company.getCompanyId() == null) continue;

            // 提交的场合处理
            if(project.getSubmitStatus().intValue() == 1) {
                company.setSubmitOaStatus(21202);
            }

            company.setRecordDate(project.getRecordTime());
            company.setAmountTotal(company.getRequestAmountTotal());
            company.setAmountTax(company.getTaxAmountTotal());
            if(company.getAmountTotal()!=null && company.getAmountTax()!=null) {
                company.setAmountNoTax(company.getAmountTotal().subtract(company.getAmountTax()));
            }else if(company.getAmountTotal()!=null ){
                company.setAmountNoTax(company.getAmountTotal());
            }else if(company.getAmountTax()!=null) {
                company.setAmountNoTax(new BigDecimal("0").subtract(company.getAmountTax()));
            }else{
                company.setAmountNoTax(new BigDecimal("0"));
            }
            cashBillCompanyMapper.updateByPrimaryKeySelective(company);
            List<CashBillReport> reportList = company.getReportList();
            for (CashBillReport report : reportList) {
                if(report.getReportId()==null) continue;

                cashBillReportMapper.updateByPrimaryKeySelective(report);
            }

            //冲抵记录
            List<CashBillReport> offsetList = company.getOffSetList();
            if(CollectionUtils.isNotEmpty(offsetList)){
                for (CashBillReport report:offsetList) {
                    if(report.getReportId()==null) continue;
                    if(report.getId() == null){
                        report.setProParentId(project.getId());
                        report.setComParentId(company.getId());
                        report.setDateCreate(new Date());
                        report.setDelFlag(false);
                        report.setOffSetFlag(true);
                        cashBillReportMapper.insertSelective(report);
                    }else{
                        cashBillReportMapper.updateByPrimaryKeySelective(report);
                    }
                }
            }
        }

        //project.setSubmitStatus(oldFlag);

        String fileRecordMainIds = project.getFileRecordMainIds();

        if (StringUtil.isNotEmpty(fileRecordMainIds)) {
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", project.getId());
            delmap.put("fileSourceId", 9);
            delmap.put("fileTypeId", 1032);
            recordMainMapper.deleteByCondition(delmap);

            //新文件处理
            String[] fileIdArr = fileRecordMainIds.split(",");
            List<Integer> fileIdList = new ArrayList<>();
            for (String fileId : fileIdArr) {
                fileIdList.add(Integer.valueOf(fileId));
            }
            Map<String, Object> uptmap = new HashMap<>();
            uptmap.put("contractId", project.getId());
            uptmap.put("ids", fileIdList);
            recordMainMapper.updateByCondition(uptmap);
        }

        final ResultData<User> userByCode = userAPI.getUserByCode(project.getUserCode());
        if ("200".equals(userByCode.getReturnCode())) {
            project.setUserName(userByCode.getReturnData().getUserName());
        }

        final WebConfig byName = webConfigMapper.getByName("oaPayType_" + project.getPayType());
        if (byName != null) {
            project.setPayTypeNm(byName.getWebConfigValue());
        }

        //获取模板
        Map<String, String> accountProjectByEstateId = cashBillProjectMapper.getAccountProjectByEstateId(project.getEstateId());
        if (accountProjectByEstateId == null) {
            resultData.setFail("项目：" + project.getEstateNm() + "所对应城市无核算主体信息");
            return resultData;
        }
        final String cashBillBatchTpl = accountProjectByEstateId.get("cashBillBatchTpl");
        final String cashBillBatchTplName = accountProjectByEstateId.get("cashBillBatchTplName");
        //开启线程提交OA
//        final CashBillProject projectFin = project;
//        Executor executor = Executors.newFixedThreadPool(companyList.size());
//        if (project.getSubmitStatus().intValue() == 1) {
//        	for (CashBillCompany company : companyList) {
//        		final CashBillCompany companyFin = company;
//        		if (companyFin.getCompanyId() == null) continue;
//        		getQkOaAnnex(company);
//        		executor.execute(new Runnable() {
//    				@Override
//    				public void run() {
//    					logger.info("提交请款单公司编号:"+companyFin.getCompanyNo());
//    					submitOaCashBill(projectFin, companyFin, resultData, cashBillBatchTpl);
//    				}
//    			});
//        	}
//        }


        //改成视图发送oa
        if (project.getSubmitStatus().intValue() == 1) {
        	for (CashBillCompany company : companyList) {
        		if (company.getCompanyId() == null) continue;
        		//获取框架合同附件
        		getQkOaAnnexFile(company);
    				logger.info("提交请款单公司编号:"+company.getCompanyNo());
    				//修改请款单-利用视图发送oa
    				submitOaCashBillByView(project, company, resultData, cashBillBatchTpl,cashBillBatchTplName);
        	}
        }
        return resultData;
    }


    /**
     * new
     * 结算请款单提交
     * */
    @Transactional(rollbackFor = Exception.class)
    public ResultData<String> saveOACashBillNew(CashBillCompany cashBillCompany)  throws Exception{
        final ResultData<String> resultData = new ResultData<>();
        resultData.setFail("失败");
        Integer userId = cashBillCompany.getUserIdCreate();
        Integer proParentId = cashBillCompany.getProParentId();
        Integer comParentId = cashBillCompany.getId();
        String vendorName = null;
        boolean addFlag = false;
        if(comParentId==null || comParentId==0){
            addFlag = true;
        }
        boolean submitFlag = false;
        if(cashBillCompany.getSubmitOaStatus().intValue() == 1){
            submitFlag = true;
        }

        try {
            //1.
            if(!addFlag){
                CashBillCompany companyDb = cashBillCompanyMapper.selectByPrimaryKey(comParentId);
                cashBillCompany.setCashBillNo(companyDb.getCashBillNo());
                if (
                        (
                                (companyDb.getApproveStatus() == null || companyDb.getApproveStatus().intValue()==25401)
                                        && (companyDb.getSubmitOaStatus()==null || companyDb.getSubmitOaStatus().intValue()==21201 || companyDb.getSubmitOaStatus().intValue()==21204)
                                        && !"1".equals(companyDb.getInValid())
                        )
                                || (companyDb.getApproveStatus()!=null && companyDb.getApproveStatus().intValue()==25404 && !"1".equals(companyDb.getInValid()))
                ){
                      //过
                }else {
                    resultData.setFail("请款单状态发生变化 请刷新页面再操作");
                    return resultData;
                }
            }

            Map<String,Object> jsReqMap = new HashMap<>();
            jsReqMap.put("jssNo",cashBillCompany.getPjsNostr());//多结算书待改
            jsReqMap.put("approveStatus","10403");
            List<PmlsJsStatementDto> jssList = pmlsJsStatementMapper.queryList(jsReqMap);
            if(CollectionUtils.isEmpty(jssList)){
                resultData.setFail("结算单状态发生变化，不能允许请款！");
                return resultData;
            }

            PmlsJsStatementDto pmlsJsStatementDto = jssList.get(0);
            vendorName = pmlsJsStatementDto.getVender_name();
            String projectNo = pmlsJsStatementDto.getProjectNo();
            Estate estate = estateMapper.getByProjectNo(projectNo);
            if(estate==null || StringUtil.isEmpty(estate.getEstateId())){
                resultData.setFail("项目不存在，请联系管理员！");
                return resultData;
            }

            if(submitFlag){
                cashBillCompany.setAmountTotal(cashBillCompany.getAmountTotal().setScale(2,BigDecimal.ROUND_HALF_UP));

                if(cashBillCompany.getAmountTotal().compareTo(BigDecimal.ZERO)<0){
                    resultData.setFail("含税请款金额总计不能小于0！");
                    return resultData;
                }


                vendorName = cashBillCompanyMapper.getVendorNameByVendorId(pmlsJsStatementDto.getVender_id());
                if(StringUtil.isEmpty(vendorName)){
                    resultData.setFail("供应商在OA已经下架");
                    return resultData;
                }

                boolean flag = false;
                StringBuffer sbf = new StringBuffer("");
                int j = 0;
                for (CashBillReport cashBillReport : cashBillCompany.getReportList()) {
                    j++;
                    PmlsJsStatementDtl sJsDtlDb = pmlsJsStatementDtlMapper.selectByPrimaryKey(cashBillReport.getPjsdId());
                    if(BigDecimal.ZERO.compareTo(sJsDtlDb.getRequestAmount())!=0){
                        sbf.append(j);
                        sbf.append(",");
                        flag = true;
                    }
                }
                if(flag){
                    String sbfMsg = sbf.toString();
                    sbfMsg = sbfMsg.substring(0,sbfMsg.length()-1);
                    resultData.setFail("第"+sbfMsg+"条请款订单已请款，请移除该订单");
                    return resultData;
                }
                if((cashBillCompany.getAmountTotal().add(pmlsJsStatementDto.getRequestAmount())).compareTo(pmlsJsStatementDto.getKpTotalAmount())>0){
                    resultData.setFail("请款金额超出结算书的开票金额，请重新选择！");
                    return resultData;
                }

                int i = 0;
                for (CashBillReport cashBillReport : cashBillCompany.getReportList()) {
                    i++;
            /*        PmlsJsStatementDtl sJsDtlDb = pmlsJsStatementDtlMapper.selectByPrimaryKey(cashBillReport.getPjsdId());
                    if(BigDecimal.ZERO.compareTo(sJsDtlDb.getRequestAmount())!=0){
                        resultData.setFail("第"+i+"条请款订单已请款，请移除该订单");
                        return resultData;
                    }*/
                    if(BigDecimal.ZERO.compareTo(cashBillReport.getRequestAmount())==0){
                        resultData.setFail("第"+i+"条请款订单请款金额为0，不允许提交请款申请");
                        return resultData;
                    }
                    if(cashBillReport.getTaxAmount()==null){
                        resultData.setFail("第"+i+"条请款订单税额为空，请重新填写");
                        return resultData;
                    }

                    if(StringUtil.isEmpty(cashBillReport.getIsGlobalControl())){
                        resultData.setFail("第"+i+"条请款订单垫佣控制规则为空，不允许提交请款申请");
                        return resultData;
                    }
                }
            }


            //2.project
            CashBillProject project = new CashBillProject();
            project.setEstateId(estate.getEstateId());
            project.setProjectNo(projectNo);
            project.setEstateNm(estate.getEstateNm());
            project.setRecordTime(cashBillCompany.getRecordDate());//page
            project.setPredictPayTime(cashBillCompany.getPredictPayTime());
            project.setPayType(cashBillCompany.getPayType());
            project.setAmountNoTax(new BigDecimal("0.00"));//cashBillCompany.getAmountNoTax()  原逻辑写死0.00
            project.setAmountTax(new BigDecimal("0.00"));//cashBillCompany.getAmountTax()      原逻辑写死0.00
            project.setAmountTotal(new BigDecimal("0.00"));//cashBillCompany.getAmountTotal()  原逻辑写死0.00
            project.setSubmitStatus(cashBillCompany.getSubmitOaStatus());
            project.setDelFlag(false);
            project.setUserCode(cashBillCompany.getUserCode());//web
            if(addFlag){
                cashBillCompany.setDateCreate(new Date());
                project.setUserIdCreate(cashBillCompany.getUserIdCreate());//web
                project.setDateCreate(new Date());
                cashBillProjectMapper.insertSelective(project);
                proParentId = project.getId();
            }else {
                project.setId(cashBillCompany.getProParentId());
                cashBillProjectMapper.updateByPrimaryKeySelective(project);
                cashBillProjectMapper.updateEditByPrimaryKey(project);
            }

            //3.cashBillCompany
            cashBillCompany.setProParentId(proParentId);
            cashBillCompany.setOaNo(null);
            cashBillCompany.setFrameOaNo(pmlsJsStatementDto.getFrameOaNo());
            cashBillCompany.setCompanyId(pmlsJsStatementDto.getCompanyId());
            cashBillCompany.setCompanyNo(pmlsJsStatementDto.getCompanyNo());
            cashBillCompany.setCompanyName(pmlsJsStatementDto.getCompanyName());
//            cashBillCompany.setAmountNoTax();//page
//            cashBillCompany.setAmountTax();//page
//            cashBillCompany.setAmountTotal();//page
            cashBillCompany.setRemark(null);
            cashBillCompany.setSubmitOaStatus(21201);
            //cashBillCompany.setUserCode(); web
            if(submitFlag){
                cashBillCompany.setSubmitOaStatus(21202);
                cashBillCompany.setApplyTime(new Date());
            }
            cashBillCompany.setVendorName(vendorName);//重新从OA获取的
            cashBillCompany.setVendorCode(pmlsJsStatementDto.getVender_code());
            cashBillCompany.setAccountProject(pmlsJsStatementDto.getHsName());
            cashBillCompany.setAccountProjectNo(pmlsJsStatementDto.getHsCode());
            cashBillCompany.setFlowId(null);
            cashBillCompany.setApproveStatus(25401);
            cashBillCompany.setApproveTime(null);
            cashBillCompany.setDelFlag(false);
            cashBillCompany.setCityNo(pmlsJsStatementDto.getCityNo());
            // cashBillCompany.setReceiveBankName();  //page
//            cashBillCompany.setReceiveBankAccountCardCode();//page
//            cashBillCompany.setReceiveBankAccountName();//page
//            cashBillCompany.setReceiveBankProvinceName();//page
//            cashBillCompany.setReceiveBankCityName();//page
//            cashBillCompany.setReceiveBankSerialNo();//page
            cashBillCompany.setVendorId(pmlsJsStatementDto.getVender_id());
            //cashBillCompany.setRecordDate();  page
            cashBillCompany.setSyncFlag(0);
//            cashBillCompany.setAreaTotal();//page
//            cashBillCompany.setRoughAmountTotal();//null
//            cashBillCompany.setDealAmountTotal();//page
            cashBillCompany.setSqYjsrAmountTotal(null);
            cashBillCompany.setSqYjfyAmountTotal(null);
            cashBillCompany.setSqYjdyAmountTotal(null);
            cashBillCompany.setSqSjsrAmountTotal(null);
            cashBillCompany.setSqSjfyAmountTotal(null);
            cashBillCompany.setSqSjdyAmountTotal(null);
//            cashBillCompany.setRequestAmountTotal(); //page
//            cashBillCompany.setTaxAmountTotal();    //page
            cashBillCompany.setFrameOaName(pmlsJsStatementDto.getFrameOaName());
            cashBillCompany.setOaProjectNo(null);
            cashBillCompany.setOaProjectName(null);
            //cashBillCompany.setRemarks(); //page
            //cashBillCompany.setOffSetFlag();//page
            cashBillCompany.setFormState(0);
            cashBillCompany.setErrmsg(null);
            cashBillCompany.setOaStartDate(null);
            cashBillCompany.setTemplateCode(null);
            cashBillCompany.setTemplateName(null);
            //cashBillCompany.setPjsNostr();  page
            cashBillCompany.setInValid("0");
            if(addFlag){
                String cashBillNo = null;
                ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_LD_QK");
                if (data != null && data.getReturnCode().equals("200")) {
                    cashBillNo = data.getReturnData();
                }
                cashBillCompany.setCashBillNo(cashBillNo);
                cashBillCompanyMapper.insertSelective(cashBillCompany);
                comParentId = cashBillCompany.getId();
            }else {
                cashBillCompanyMapper.updateByPrimaryKeySelective(cashBillCompany);
                cashBillCompanyMapper.updateEditByPrimaryKey(cashBillCompany);
            }

            //4.report
            cashBillReportMapper.updateByComparentId(comParentId);
            for (CashBillReport cashBillReport : cashBillCompany.getReportList()) {
                cashBillReport.setProParentId(proParentId);
                cashBillReport.setComParentId(comParentId);
                //cashBillReport.setReportId(); page
                //cashBillReport.setReportNo(); page
                //cashBillReport.setBuildingNo(); page
                //cashBillReport.setCustomerNm(); page
                //cashBillReport.setArea(); page
                //cashBillReport.setRoughAmount();
                //cashBillReport.setDealAmount();//page 签约总价
                cashBillReport.setDateCreate(new Date());
                cashBillReport.setUserIdCreate(cashBillCompany.getUserIdCreate());
                cashBillReport.setDelFlag(false);
                cashBillReport.setSqYjsrAmount(null);
                cashBillReport.setSqYjfyAmount(null);
                cashBillReport.setSqYjdyAmount(null);
                cashBillReport.setSqSjsrAmount(null);
                cashBillReport.setSqSjfyAmount(null);
                cashBillReport.setSqSjdyAmount(null);
//                cashBillReport.setRequestAmount();  page
//                cashBillReport.setTaxAmount();      page
                //cashBillReport.setRequestType();     page
               // cashBillReport.setMemo();            page
                //cashBillReport.setReportDetailId();  page
//                cashBillReport.setCheckBodyId();     page
//                cashBillReport.setCheckBodyName();  page
//                cashBillReport.setOffSetFlag();     page
//                cashBillReport.setAccountProject(); page
//                cashBillReport.setAccountProjectNo(); page
//                cashBillReport.setPjsdId();       page
//                cashBillReport.setIsGlobalControl(); page
//                cashBillReport.setJssId();  page
                  cashBillReportMapper.insertSelective(cashBillReport);
            }

            //5.relate
            PmlsStatementCashBillRelate jsBillRelate = new PmlsStatementCashBillRelate();
            jsBillRelate.setCompId(comParentId);
            pmlsStatementCashBillRelateMapper.updateByCompId(jsBillRelate);
            jsBillRelate.setDateCreate(new Date());
            jsBillRelate.setDelFlag(0);
            jsBillRelate.setPscompId(pmlsJsStatementDto.getId());
            jsBillRelate.setUserIdCreate(cashBillCompany.getUserIdCreate());
            pmlsStatementCashBillRelateMapper.insertSelective(jsBillRelate);

            //6
            String fileRecordMainIds = cashBillCompany.getFileRecordMainIds();
            //旧文件处理
            Map<String, Object> delmap = new HashMap<>();
            delmap.put("refId", project.getId());
            delmap.put("fileSourceId", 9);
            recordMainMapper.deleteQkFile(delmap);

            if (StringUtil.isNotEmpty(fileRecordMainIds)) {
                //新文件处理
                String[] fileIdArr = fileRecordMainIds.split(",");
                List<Integer> fileIdList = new ArrayList<>();
                for (String fileId : fileIdArr) {
                    fileIdList.add(Integer.valueOf(fileId));
                }
                Map<String, Object> uptmap = new HashMap<>();
                uptmap.put("contractId", project.getId());
                uptmap.put("ids", fileIdList);
                recordMainMapper.updateByCondition(uptmap);
            }



            //获取模板
            Map<String, String> accountProjectByEstateId = cashBillProjectMapper.getAccountProjectByEstateId(project.getEstateId());
            if (accountProjectByEstateId == null) {
                resultData.setFail("项目：" + project.getEstateNm() + "所对应城市无核算主体信息");
                return resultData;
            }
            final String cashBillBatchTpl = accountProjectByEstateId.get("cashBillBatchTpl");
            final String cashBillBatchTplName = accountProjectByEstateId.get("cashBillBatchTplName");


            //改成视图发送oa
            if (submitFlag) {
                //for (CashBillCompany company : companyList) {

                    //获取框架合同附件
                    getQkOaAnnexFile(cashBillCompany);
                    logger.info("提交请款单公司编号:"+cashBillCompany.getCompanyNo());
                    //修改请款单-利用视图发送oa
                    submitOaCashBillByView(project, cashBillCompany, resultData, cashBillBatchTpl,cashBillBatchTplName);

                    Map<String,Object> execMap = new HashMap<>();
                    execMap.put("id",comParentId);
                    execMap.put("opType",1);
                    cashBillCompanyMapper.execQkUptJs(execMap);
                //}
            }else{
                cashBillCompanyMapper.updateEditOaByPrimaryKey(comParentId);
            }



        }catch (Exception e){
            logger.error("保存提交异常",e);
            logger.error("cash_bill","CashBillService","saveOACashBillNew",
                    "",userId,"保存提交异常","！",e);
            resultData.setFail("操作失败");
            return resultData;
        }
        resultData.setSuccess("操作成功");
        return resultData;
    }

    /**
     * desc:获取中介合作报批单盖章附件信息
     * 2020年1月2日
     */
    private void getQkOaAnnexFile(CashBillCompany company) {
    	if (company.getFrameOaNo() != null) {
    		//根据框架协议编号查询是否为自动发送
    		Map<String,Object> reqMap = new HashMap<>();
    		reqMap.put("frameOaNo", company.getFrameOaNo());
    		Map<String,Object> responResult = cashBillCompanyMapper.getFlowIdByFrameOaNo(reqMap);
    		List<QkContractFile> qkContractFileList = null;
    		if(responResult !=null && responResult.size()!=0) {//自动
    			String flowId = responResult.get("FlowId").toString();
    			reqMap.put("flowId", flowId);
//    			reqMap.put("flowId", "3000027263224549");
    			// 查询视图v4x.dbo.V_FCJY_ZJHZ_File  中介合作
    			qkContractFileList = qkContractFileMapper.selZJHZFileByFlowId(reqMap);
    		}else {//不是自动
    			// 查询视图v4x.dbo.V_FCJY_LDKJ_File 联动框架
//    			reqMap.put("frameOaNo", "FYLD2019050190");
    			qkContractFileList = qkContractFileMapper.selLDKJFileByFrameOaNo(reqMap);
    		}
    		//获取的数据存入PMLS_QkContractFile
    		if(qkContractFileList !=null && qkContractFileList.size()>0) {
    			for (QkContractFile qkContractFile : qkContractFileList) {
    				qkContractFile.setDelFlag(false);
    				qkContractFile.setComId(company.getId());
    				qkContractFile.setDateCreate(new Date());
    				qkContractFileMapper.insertSelective(qkContractFile);
				}
    		}
    	}
	}

	/**
     * desc:发送请款单(视图)
     * 2019年12月27日
     */
    protected void submitOaCashBillByView(CashBillProject projectFin, CashBillCompany company,
			ResultData<String> resultData, String cashBillBatchTpl, String cashBillBatchTplName) {
    	//发送请款单之前需要生成的数据，提前存储cashBillCompany
    	String cashBillNo = null;
        if (StringUtil.isEmpty(company.getCashBillNo())) {
            ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_LD_QK");
            if (data != null && data.getReturnCode().equals("200")) {
                cashBillNo = data.getReturnData();
            } else {
                new NullPointerException("根据seq_type: TYPE_LD_QK 获取联动批量请款编号为空");
            }
        } else {
            cashBillNo = company.getCashBillNo();
        }

        //自动生成oa编码
        ResultData<?> fcjyNo= seqNoAPI.getSeqNoByTypeCode("FCJY_LDQKC");
		String crmOaNo = (String)fcjyNo.getReturnData();

		CashBillCompany companyBre = new CashBillCompany();
		companyBre.setCashBillNo(cashBillNo);
		companyBre.setId(company.getId());
		companyBre.setOaNo(crmOaNo);
		companyBre.setTemplateCode(cashBillBatchTpl);
		companyBre.setTemplateName(cashBillBatchTplName);
		companyBre.setFormState(0);
		companyBre.setApplyTime(new Date());
		companyBre.setUserCode(projectFin.getUserCode());
		int count = cashBillCompanyMapper.updateByPrimaryKeySelective(companyBre);
		if(count>0){
            resultData.setSuccess("发起OA申请成功");
        }else{
        	resultData.setReturnCode("-1");
            resultData.setFail("发起OA申请失败");
        }
	}

    /**
     *
     * desc:删除
     * 2019年9月23日
     * author:zhenggang.Huang
     */
    public int remove(Map<String, Object> queryParam) {
		int count = 0;

		if(queryParam.containsKey("comParentId") && queryParam.get("comParentId") != null){
			//逻辑删除cash_bill_company
			CashBillCompany company = new CashBillCompany();
			company.setId(Integer.parseInt(queryParam.get("comParentId").toString()));
			company.setDelFlag(true);
			count = cashBillCompanyMapper.updateByPrimaryKeySelective(company);
		}
		if(count > 0) {
			count = cashBillReportMapper.updateByComparentId(Integer.parseInt(queryParam.get("comParentId").toString()));
		}
		return count;
	}

    public List<Map<String,Object>> getOffsetInfoList(Map<String, Object> reqMap) {
        String reportNoStr = reqMap.get("reportNoList").toString();
        String[] arr = reportNoStr.split(",");
        List<String> reportNoList = new ArrayList<>();
        for (String reportNo : arr) {
            reportNoList.add(reportNo);
        }
        reqMap.put("reportNoList",reportNoList);
        List<Map<String, Object>> map = reportMapper.getOffsetInfoList(reqMap);
        return map;
    }



    /**
     * 查询结算明细单
     */
    public ResultData<List<PmlsJsStatementDtlDto>> selectJsStatementDtlListCanQk(Map<String, Object> queryParam) {
        ResultData<List<PmlsJsStatementDtlDto>> resultData = new ResultData<List<PmlsJsStatementDtlDto>>();
        List<PmlsJsStatementDtlDto> list = pmlsJsStatementDtlMapper.selectJsStatementDtlListCanQk(queryParam);
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * desc:作废
     */
    public ResultData invalidBill(Map<String, Object> queryParam) {
        ResultData resultData = new ResultData<>();
        resultData.setFail();
        if(queryParam.containsKey("comParentId") && queryParam.get("comParentId") != null){
            Integer comParentId = Integer.parseInt(queryParam.get("comParentId").toString());
            CashBillCompany companyDb = cashBillCompanyMapper.selectByPrimaryKey(comParentId);
            if (
                    (
                            (companyDb.getApproveStatus() == null || companyDb.getApproveStatus().intValue()==25401)
                          && (companyDb.getSubmitOaStatus()==null || companyDb.getSubmitOaStatus().intValue()==21201 || companyDb.getSubmitOaStatus().intValue()==21204)
                          && !"1".equals(companyDb.getInValid())
                    )
                    || (companyDb.getApproveStatus()!=null && companyDb.getApproveStatus().intValue()==25404 && !"1".equals(companyDb.getInValid()))
            ){
                CashBillCompany company = new CashBillCompany();
                company.setId(comParentId);
                company.setInValid("1");
                company.setSyncFlag(-1);
                cashBillCompanyMapper.updateByPrimaryKeySelective(company);

                //relate
                PmlsStatementCashBillRelate jsBillRelate = new PmlsStatementCashBillRelate();
                jsBillRelate.setCompId(comParentId);
                pmlsStatementCashBillRelateMapper.updateByCompId(jsBillRelate);

                resultData.setSuccess();
            }else {
                resultData.setFail("请款单状态发生变化 不允许作废");
            }
        }
        return resultData;
    }



    /**
     * 请款单详情
     * @param map
     * @return
     */
    public ResultData getCashBillDeatilByIdNew(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();
        resultData.setFail();
        List<CashBillDto> cashBillDtoList = cashBillCompanyMapper.getCashBillDeatilById(map);
        if (cashBillDtoList != null) {
            CashBillDto cashBillDto = cashBillDtoList.get(0);
            List<CashBillReport> cashBillReportList = cashBillCompanyMapper.getReportListById(map);
            cashBillDto.setReportDetails(cashBillReportList);

            Map<String, String> fileMap = new HashMap<String, String>();
            fileMap.put("refId", cashBillDto.getProParentId().toString());
            fileMap.put("fileTypeId", "1032");
            fileMap.put("fileSourceId", "9");
            List<FileRecordMain> fileList1 = fileRecordMainMapper.queryList(fileMap);

            fileMap.put("fileTypeId", "1089");
            fileMap.put("fileSourceId", "9");
            List<FileRecordMain> fileList2 = fileRecordMainMapper.queryList(fileMap);
            if(!CollectionUtils.isEmpty(fileList2)) {
                fileList1.addAll(fileList2);
            }

            fileMap.put("fileTypeId", "1090");
            fileMap.put("fileSourceId", "9");
            List<FileRecordMain> fileList3 = fileRecordMainMapper.queryList(fileMap);
            if(!CollectionUtils.isEmpty(fileList3)) {
                fileList1.addAll(fileList3);
            }
            cashBillDto.setFileList(fileList1);

            resultData.setReturnData(cashBillDto);
            resultData.setSuccess();
        }
        return resultData;
    }



}
