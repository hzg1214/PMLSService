package cn.com.eju.deal.open.controller;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.cashbill.service.CashBillService;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.service.OmsInteractiveService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.common.util.XmlTransferUtil;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.company.service.CompanyStoreService;
import cn.com.eju.deal.contract.dao.OaContractChangeReturnMapper;
import cn.com.eju.deal.contract.model.*;
import cn.com.eju.deal.contract.service.AchievementCancelService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.ExtOmsService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.company.CompanyADto;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.contract.DepositDto;
import cn.com.eju.deal.dto.op.OpGpContractDto;
import cn.com.eju.deal.dto.op.OpOperatorDto;
import cn.com.eju.deal.dto.op.OpStoreDto;
import cn.com.eju.deal.dto.store.*;
import cn.com.eju.deal.fangyou.model.FangyouAccount;
import cn.com.eju.deal.fangyou.service.FangyouAccountService;
import cn.com.eju.deal.frameContract.dao.FrameContractMapper;
import cn.com.eju.deal.frameContract.model.FrameContract;
import cn.com.eju.deal.frameContract.model.OaLnkFrameContractReturn;
import cn.com.eju.deal.frameContract.service.FrameContractService;
import cn.com.eju.deal.gpContract.dao.GpContractMapper;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContract.model.GpContractStore;
import cn.com.eju.deal.gpContract.model.OaGpContractReturn;
import cn.com.eju.deal.gpContract.service.GpContractService;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeMapper;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeStoreMapper;
import cn.com.eju.deal.gpContractChange.model.GpContractChange;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeStore;
import cn.com.eju.deal.gpContractChange.model.OaGpContractChangeReturn;
import cn.com.eju.deal.gpCsMemberContract.dao.GpCsMemberContractMapper;
import cn.com.eju.deal.gpCsMemberContract.model.GpCsMemberContract;
import cn.com.eju.deal.gpCsMemberContract.model.OaGpCsMemberContractReturn;
import cn.com.eju.deal.gpCsMemberContract.service.GpCsMemberContractService;
import cn.com.eju.deal.houseLinkage.report.model.SendContractDataToFy;
import cn.com.eju.deal.houseLinkage.report.model.SendContractFangyouLog;
import cn.com.eju.deal.houseLinkage.report.service.ReportService;
import cn.com.eju.deal.houseLinkage.report.service.YFInterfaceService;
import cn.com.eju.deal.houseLinkage.totalPackage.service.TotalPackageService;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.movementdeposit.model.MovementDeposit;
import cn.com.eju.deal.movementdeposit.service.MovementDepositService;
import cn.com.eju.deal.open.service.APIOaService;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.*;
import cn.com.eju.deal.store.service.*;
import cn.com.eju.deal.wechat.model.MsgData;
import cn.com.eju.deal.wechat.service.MsgDataService;
import cn.com.eju.deal.wechat.service.WechatSendService;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时任务接口（每天凌晨拉取合同与合同变更状态）
 *
 * @author mimi.sun
 * @date 2016年9月29日 下午2:48:05
 */
@RestController
@RequestMapping(value = "crm/timer")
public class APITimerController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "extOmsService")
    private ExtOmsService extOmsService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource(name = "apiOaService")
    private APIOaService apiOaService;

    @Resource(name = "fesbService")
    private FesbService fesbService;

    @Resource(name = "stopContractService")
    private ContractChangeService stopContractService;

    @Resource(name = "storeService")
    private StoreService storeService;

    @Resource(name = "storeMapper")
    private StoreMapper storeMapper;

    @Resource(name = "achievementCancelService")
    private AchievementCancelService achievementCancelService;

    @Resource
    private OmsInteractiveService omsInteractiveService;
    @Resource
    private FrameContractService frameContractService;

    // 微信数据Service
    @Resource
    private MsgDataService msgDataService;

    // 微信发送Service
    @Resource
    private WechatSendService WechatSendService;

    @Resource(name = "companyService")
    private CompanyService companyService;

    @Resource(name = "companyStoreService")
    private CompanyStoreService companyStoreService;

    @Resource(name = "fangyouAccountService")
    private FangyouAccountService fangyouAccountService;

    @Resource(name = "storeReceiveService")
    private StoreReceiveService storeReceiveService;

    @Resource
    private BasCenterSettingService basCenterSettingService;

    @Resource
    private BasOaSupplierService basOaSupplierService;

    @Resource
    private CitySettingMapper citySettingMapper;

    @Resource
    private FileRecordMainService fileService;

    @Resource
    private SeqNoAPIImpl seqNoAPI;

    @Resource
    private StoreDepositService storeDepositService;

    @Resource
    private FrameContractMapper frameContractMapper;

    @Resource
    private ReportService reportService;

    @Resource(name = "gpContractService")
    private GpContractService gpContractService;

    @Resource
    private MovementDepositService movementDepositService;

    @Resource
    private CompanyMapper companyMapper;

    @Resource(name = "gpContractMapper")
    private GpContractMapper gpContractMapper;

    @Resource
    private YFInterfaceService yFInterfaceService;

    @Resource
    private OaContractChangeReturnMapper oaContractChangeReturnMapper;

    @Resource(name = "totalPackageService")
    private TotalPackageService totalPackageService;

    @Resource
    private GpContractChangeMapper gpContractChangeMapper;

    @Resource
    private GpContractChangeStoreMapper gpContractChangeStoreMapper;

    @Resource
    private GpCsMemberContractService gpCsMemberContractService;

    @Resource
    private GpCsMemberContractMapper gpCsMemberContractMapper;

    @Resource
    private AuthCheckService authCheckService;

    @Resource
    private CashBillService cashBillService;

    /**
     * 定时拉取【合同审核状态】
     */
    @RequestMapping(value = "/contract", method = RequestMethod.GET)
    public String contracrtHandle() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        //审核通过List
        List<String> passList = new ArrayList<String>();
        boolean result = false;

        logger.info("CRM定时获取合同审核状态 begin，this time is" + new Date());

        try {
            // OA审批结果集取得
            List<OaContractReturn> oaResultlst = getOAContractReturn();
            if (null == oaResultlst || true == oaResultlst.isEmpty()) {
                resultData.setReturnMsg("没有OA审批结果信息!");
                return resultData.toString();
            }

            List<Map<String, Object>> storeMapList = new ArrayList<>();
            //3、更新合同状态
            for (OaContractReturn keyValue : oaResultlst) {
                ContractInfoDto contractInfoDto = new ContractInfoDto();
                ContractDto contract = new ContractDto();
                // 更新该合同为"审核通过"
                contract.setContractStatus(keyValue.getContractStatus());
                contract.setFlowId(keyValue.getFlowId());
                // 合同类别(新签，续签)
                contract.setOriginalContractdistinction(keyValue.getOriginalContractdistinction());
                //审核不通过 （ 10404）
                if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(keyValue.getContractStatus())) {
                    // 审核通过时间
                    contract.setPerformDate(keyValue.getOAPerformDate());
                }
                contract.setDateUpdate(new Date());
                contractInfoDto.setContract(contract);

                //合同状态判断
                Contract dbContract = contractService.getByFlowId(keyValue.getFlowId());
                if (dbContract == null) {
                    continue;
                }
                int contractstatus = dbContract.getContractStatus();
                String acCityNo = dbContract.getAcCityNo();

                if (contractstatus != DictionaryConstants.CONTRACT_STATUS_AUDITING) {//合同状态不在审核中
                    logger.info("OA审批结果处理时 该合同不处于审核中状态 OA单号：[" + keyValue.getFlowId() + "]");
                    continue;
                }


                try {
                    // 更新合同状态
                    contractInfoDto.getContract().setCompanyId(dbContract.getCompanyId());
                    contractInfoDto.getContract().setBizType("21801|21802");
                    contractService.updateContractStatusByFlowId(contractInfoDto);

                    //更新门店，公司类型
                    if("10302".equals(dbContract.getContractType().toString())){
                        Company company = companyService.getByCompanyId(dbContract.getCompanyId());
                        if(company.getCompanyType() == null || !"23501".equals(company.getCompanyType().toString())){
                            CompanyDto companyDto = new CompanyDto();
                            companyDto.setId(company.getId());
                            companyDto.setCompanyType(23501);
                            companyService.updateCompanyOnly(companyDto);
                        }

                        List<Store> storeList = storeMapper.getBycontractId(dbContract.getId());
                        for(Store store: storeList){
                            if(store.getStoreType() == null || !"23401".equals(store.getStoreType().toString())){
                                Store uStore = new Store();
                                uStore.setStoreId(store.getStoreId());
                                uStore.setStoreType(23401);
                                storeMapper.update(uStore);
                            }
                        }
                    }


                } catch (Exception e) {
                    logger.error("open", "APITimerController", "updateContractStatusByFlowId", "", -1, "", "更新合同状态失败!", e);
                    continue;
                }

                // 已处理OA审批结果更新
                keyValue.setHasDeal(1);    // 已处理OA审批结果
                keyValue.setDateUpdate(new Date());
                updateOAContractReturn(keyValue);

                //审核不通过 （ 10404）
                if (DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS.equals(keyValue.getContractStatus())) {
                    continue;
                }


                //授牌，渠道  自动生成联动框架合同
                if("10307".equals(dbContract.getContractType().toString())
                			&&( StringUtil.isNotEmpty(dbContract.getShoupaiType())
                						&&"24603".equals(dbContract.getShoupaiType()))){
                    frameContractService.spqdAutoCreateFromeContract(dbContract.getId());
                }

                // add by huangmc 20191015 授牌合同过审后，将合同中覆盖的门店的类型强制修改与合同的授牌类型一致 start
                // 合同类型--授牌 10307
                if ("10307".equals(dbContract.getContractType().toString())) {
                    //Integer storeType = 23404; // 代理
                    // 24601 --> 23401   门店
                    if ("24601".equals(dbContract.getShoupaiType())) {
                        updateStoreType(keyValue.getFlowId(), 23401);
                    }
                    // 24602 --> 23402   社区
                    else if ("24602".equals(dbContract.getShoupaiType())) {
                        updateStoreType(keyValue.getFlowId(), 23402);
                    }
                    // 24603 --> 23403   渠道
                    else if ("24603".equals(dbContract.getShoupaiType())) {
                        updateStoreType(keyValue.getFlowId(), 23403);
                    }

                }

                // add by huangmc 20191015 授牌合同过审后，将合同中覆盖的门店的类型强制修改与合同的授牌类型一致 End

//Add By GuoPengFei 2017/05/25 合规性 start
                //更新门店资质等级
                updateABTypeStore(keyValue.getFlowId());

//Add by cning 2017/07/24 房友账号自动绑定 Start
                //bindFangyouAccountSendOP(keyValue.getFlowId(),storeMapList);
//Add by cning 2017/07/24 房友账号自动绑定 End

                //乙转甲合同不做保证金，装修数据
                if (true == isChangeB2AContract(keyValue.getFlowId())) {
                    continue;
                }
//Add By GuoPengFei 2017/05/25 合规性 end

                // 续签合同
                if (keyValue.getOriginalContractdistinction() != null && DictionaryConstants.OriginalContractdistinction_TYPE_R == keyValue.getOriginalContractdistinction().intValue()) {
                    continue;
                }

                boolean depositOpenFlag = true;
                String openFlagByCityNo = storeDepositService.getNewDepositOpenFlagByCityNo(acCityNo);
                if (openFlagByCityNo != null && openFlagByCityNo.equals("0")) {
                    //未开通新版保证金流程
                    depositOpenFlag = false;
                }
                if (!depositOpenFlag) {
                    result = makeDepositInfo(keyValue.getFlowId());
                    if (result == false) {
                        continue;
                    }
                }

                passList.add(keyValue.getFlowId());

                // 做成装修信息
                result = makeDecorationInfo(passList);
                if (result == false) {
                    continue;
                }
                passList.clear();

                //更新授牌验收状态
                storeService.updateAuthCheckStatus(keyValue.getFlowId());

                //授牌合同,门店类型是门店和渠道时 自动生成 授牌验收记录
                if("10307".equals(dbContract.getContractType().toString())){
                	authCheckService.spcontractAutocreate(dbContract.getId());
                }
            }

        } catch (Exception e) {
            logger.error("open", "APITimerController", "contracrtHandle", "", -1, "", "CRM定时获取合同审核状态失败", e);
        }
        resultData.setReturnMsg("更新合同信息成功!");
        logger.info("CRM定时获取合同审核状态 end，this time is" + new Date());
        return resultData.toString();
    }

    private boolean makeDepositInfo(String flowId) {
        boolean result = true;

        /** 审核通过 则向oms写 保证金信息 **/
        try {
            // 根据OA flowId 查询保证金不等于0的合同
            Contract contractDB = contractService.getDepoistNozeroCtrctByFlowId(flowId);
            ContractDto contractDto = new ContractDto();
            if (null != contractDB) {
                BeanUtils.copyProperties(contractDB, contractDto);
            }

            // 保证金总额
            BigDecimal itemAmountStr = new BigDecimal(contractDto.getTotleDepositFee().toString());
            // 构造传递的保证金信息
            DepositDto deposit = new DepositDto();
            if (contractDto.getContractType() != null && (contractDto.getContractType().intValue() == 10302
                    || contractDto.getContractType().intValue() == 10304)) {
                // 合同编号
                deposit.setContractNo(contractDto.getContractNo());
                // 合同类型(A版、B版、A转B版、B转A版)
                deposit.setContractType(contractDto.getContractTypeName());
                // 合同状态(草签、审核通过、审核驳回、作废)
                deposit.setContractState(contractDto.getContractStatusName());
                // 公司名称
                deposit.setCompanyName(contractDto.getPartyB());
                // 保证金总额
                deposit.setItemAmount(itemAmountStr);
                // 协议书编号
                deposit.setAgreementNo(contractDto.getAgreementNo());
                // 城市NO
                deposit.setCityNo(contractDto.getAcCityNo());
                ResultData<?> rebackResult = omsInteractiveService.createDeposit(deposit);
                Integer code = Integer.valueOf(rebackResult.getReturnCode());
                if (code != 200) {
                    logger.error("open", "APITimerController", "makeDepositInfo",
                            "deposit = " + JsonUtil.parseToJson(deposit), null, null, "调用OMS接口新增保证金失败!", null);
                    result = false;
                    return result;
                }
            }

        } catch (Exception e) {
            logger.error("open", "APITimerController", "makeDepositInfo", "", 1, "", "根据OA flowId 查询保证金不等于0的合同信息失败!",
                    e);
            result = false;
        }
        // 保证金到账
        try {
            extOmsService.createPerformNodeRecordByFlowId(flowId);
        } catch (Exception e) {
            logger.error("open", "APITimerController", "makeDepositInfo", "", 1, "", "新增合同流水失败!", e);
            result = false;
        }

        return result;
    }

    /**
     * 批量做成装修信息
     *
     * @param passList
     * @return
     */
    private boolean makeDecorationInfo(List<String> passList) {
        boolean resultData = true;
        ResultData<List<StoreDecorationDto>> result = new ResultData<List<StoreDecorationDto>>();
        try {
            if (null != passList && !passList.isEmpty()) {
                Map<String, Object> param = new HashMap<String, Object>();
                String[] arr = (String[]) passList.toArray(new String[passList.size()]);
                param.put("flowIdList", arr);
                param.put("dateUpdate", new Date());

                // 装修信息
                result = contractService.insertNewDecorationRecord(param);
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "makeDecorationInfo", "", -1, "", "定时获取合同状态更新门店装修数据！", e);
            resultData = false;
            return resultData;
        }

        // 将返回的数据更新到OMS门店装修表
        if (ReturnCode.SUCCESS.equals(result.getReturnCode())) {
            try {
                List<StoreDecorationDto> storeDecorationDtoList = result.getReturnData();
                //更新到OMS，数据组装
                List<DecorationDto> postList = new ArrayList<DecorationDto>();
                for (StoreDecorationDto storeDecorationDto : storeDecorationDtoList) {
                    DecorationDto decorationDto = new DecorationDto();
                    decorationDto.setDecorateNo(storeDecorationDto.getDecorationNo());
                    decorationDto.setStoreNo(storeDecorationDto.getStoreNo());
                    decorationDto.setContractNo(storeDecorationDto.getContractNo());
                    decorationDto.setDecorateStatus((int) storeDecorationDto.getDecorationStatus());
                    decorationDto.setStoreName(storeDecorationDto.getStoreName());
                    decorationDto.setStoreAddress(storeDecorationDto.getStoreAddress());
                    decorationDto.setContractType(storeDecorationDto.getContractTypeName());
                    decorationDto.setCompanyName(storeDecorationDto.getCompanyName());
                    decorationDto.setCityNo(storeDecorationDto.getAcCityNo());
                    decorationDto.setAgreementNo(storeDecorationDto.getAgreementNo());
                    decorationDto.setDateLifeStartStr(storeDecorationDto.getDateLifeStartStr());
                    decorationDto.setDateLifeEndStr(storeDecorationDto.getDateLifeEndStr());
                    decorationDto.setOafilpagency(storeDecorationDto.getOafilpagency());
                    postList.add(decorationDto);
                }
                if (!postList.isEmpty()) {
                    //返回数据直接调用OMS定时任务接口更新
                    extOmsService.insertToOMSDecoration(postList);
                } else {
                    logger.error("open",
                            "APITimerController",
                            "makeDecorationInfo",
                            "list = " + postList.toString(),
                            null,
                            null,
                            "定时批量更新合同状态,返回数据异常",
                            null);
                }
            } catch (Exception e) {
                logger.error("open", "APITimerController", "makeDecorationInfo", "", -1, "", "定时获取合同状态更新门店装修数据失败！", e);
                resultData = false;
                return resultData;
            }
        }

        return resultData;
    }
    // Mod By GUOPENGFEI 2017/04/24 end

    /**
     * 定时拉取【合同变更审核状态】
     * 包括合同终止、合同变更（乙转甲、三方变更、门店迁址）
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/contractChange", method = RequestMethod.GET)
    private String contractChangeHandle() {
        ResultData<?> resultData = new ResultData<>();
        List<String> passList = new ArrayList<>();
        logger.info("CRM定时获取合同变更终止审核状态 begin，this time is" + new Date());

        try {
            //获取OA审批结果集
            List<OaContractChangeReturn> oaResultLst = this.getOaContractChangeReturn("");
            if (null == oaResultLst || oaResultLst.isEmpty()) {
                resultData.setReturnMsg("没有OA审批结果信息!");
                return resultData.toString();
            }
            for (OaContractChangeReturn oaResult : oaResultLst) {
                Map<String, Object> resultMap = this.getCtrctInfoByFlowId(oaResult.getFlowId());
                Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
                List<Integer> storeIdList = (List<Integer>) resultMap.get("storeIdList");
                // 非空判断
                if (null != storeIdList && !storeIdList.isEmpty()) {
                    // 根据flowId、合同Id和、门店Ids做数据处理
                    if (oaResult.getApproveStatus() == 1) {//审核通过
                        passList.add(oaResult.getFlowId());
                        stopContractService.doPassDateUpdateAddon(oaResult.getFlowId(), contractId, storeIdList, 1, oaResult.getApproveDate());
                        /*ContractInfoDto ctaDto = contractService.getById(contractId, 0);
                        Integer companyId = ctaDto.getContract().getCompanyId();
                        unbindCompanyStore(oaResult.getFlowId(), contractId, companyId, storeIdList);*/
                    } else {//审核不通过
                        this.doNoPassDateUpdate(oaResult.getFlowId(), contractId, storeIdList, 1);
                    }
                    oaResult.setHasDeal(1);    // 已处理OA审批结果
                }else{
                    oaResult.setHasDeal(2);    // 已处理OA审批结果
                    oaResult.setDealDesc("未找到门店信息");
                }
                // 已处理OA审批结果更新
                oaResult.setDateUpdate(new Date());
                updateOaContractChangeReturn(oaResult);
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("open", "APITimerController", "contractChangeHandle", "", -1, "", "CRM定时获取【合同变更审核状态】失败！", e);
        }

        if (passList.size() > 0) {
            // 审核通过
            try {
                extOmsService.updateChgStatusToOmsSplit(passList);
            } catch (Exception e) {
                resultData.setFail();
                logger.error("open", "APITimerController", "contracrtChangeHandle", "", -1, "", "变更终止通过-更新OMS保证金中合同状态失败！", e);
            }
        }
        return resultData.toString();
    }

    /**
     * 定时拉取【合同撤销审核状态】
     */
    @SuppressWarnings({"unchecked"})
    @RequestMapping(value = "/contractCancel", method = RequestMethod.GET)
    private String contractCancelHandle() {
        ResultData<?> resultData = new ResultData<>();

        logger.info("CRM定时获取合同撤销审核状态 begin，this time is" + new Date());

        try {
            //获取OA审批结果集
            List<OaContractChangeReturn> oaResultLst = this.getOaContractChangeReturn("CX");
            if (null == oaResultLst || oaResultLst.isEmpty()) {
                resultData.setReturnMsg("没有OA审批结果信息!");
                return resultData.toString();
            }

            // 更新合同撤销状态
            for (OaContractChangeReturn oaContractChangeReturn : oaResultLst) {
                Map<String, Object> resultMap = this.getCancelInfo(oaContractChangeReturn.getFlowId());
                Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
                List<Integer> storeIdList = (List<Integer>) resultMap.get("storeIdList");
                if (null != storeIdList && !storeIdList.isEmpty()) {
                    //更新合同撤销信息
                    Map<String, Object> updateCancelStateMap = new HashMap<String, Object>();
                    String approveState, isCancel;
                    if (oaContractChangeReturn.getApproveStatus() == 1) {//审核通过
                        approveState = DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLED;
                        isCancel = DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL;
                        updateCancelStateMap.put("approveDate", oaContractChangeReturn.getApproveDate());
                    } else {//审核不通过
                        approveState = DictionaryConstants.CONTRACT_ISCANCEL_CANCELFAIL;
                        isCancel = DictionaryConstants.STORESTATE_ISCANCEL_ISNOTCANCEL;
                    }
                    updateCancelStateMap.put("achievementCancelNo", oaContractChangeReturn.getOaNo());
                    updateCancelStateMap.put("approveState", approveState);
                    achievementCancelService.UpdateCancelState(updateCancelStateMap);

                    // 更新合同门店关系表的isCancel状态
                    Map<String, Object> updateIsCancelStateMap = new HashMap<String, Object>();
                    updateIsCancelStateMap.put("isCancel", isCancel);
                    updateIsCancelStateMap.put("contractId", contractId);
                    StringBuffer buffer = new StringBuffer();
                    String string = "";
                    String storeIds[] = new String[]{};
                    if (storeIdList.size() > 0) {
                        for (Integer integer : storeIdList) {
                            buffer.append(integer);
                            buffer.append(",");
                        }
                    }
                    if (buffer != null && buffer.length() > 0) {
                        string = buffer.substring(0, buffer.length() - 1).toString();
                        storeIds = string.split(",");
                    }
                    updateIsCancelStateMap.put("storeIds", storeIds);
                    achievementCancelService.UpdateIsCancelByStoreIds(updateIsCancelStateMap);
                    oaContractChangeReturn.setHasDeal(1);    // 已处理OA审批结果
                }else{
                    oaContractChangeReturn.setHasDeal(2);    // 已处理OA审批结果
                    oaContractChangeReturn.setDealDesc("未找到门店信息");
                }
                // 已处理OA审批结果更新
                oaContractChangeReturn.setDateUpdate(new Date());
                updateOaContractChangeReturn(oaContractChangeReturn);
            }
        } catch (Exception e) {
            resultData.setFail();
            logger.error("open", "APITimerController", "contractCancelHandle", "", -1, "", "CRM定时获取【合同变更审核状态】失败！", e);
        }

        resultData.setReturnMsg("更新合同撤销审核状态成功!");
        logger.info("CRM定时获取合同撤销审核状态 end，this time is" + new Date());
        return resultData.toString();
    }

    /**
     * add by luhaidan 2018/11/13
     * 根据类型获取OA审批结果信息
     *
     * @param changeType CX：撤销；BG：变更；ZZ：终止
     * @return
     * @throws Exception
     */
    private List<OaContractChangeReturn> getOaContractChangeReturn(String changeType) throws Exception {
        List<OaContractChangeReturn> oaResult = new ArrayList<>();
        if ("CX".equals(changeType)) {
            oaResult = oaContractChangeReturnMapper.getOaContractCancelReturn();
        } else if (StringUtil.isEmpty(changeType)) {
            oaResult = oaContractChangeReturnMapper.getOaContractChangeReturn();
        }
        return oaResult;
    }

    /**
     * add by luhaidan 2018/11/13
     * 根据合同撤销对象查询合同Id以及合同下的门店Id集合
     *
     * @param achievementCancelNo
     * @param flowId
     * @return
     * @throws Exception
     */
    private Map<String, Object> getCancelInfo(String flowId) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();

        // 根据achievementCancelNo,flowId查询合同撤销下的合同和门店编码
        List<Integer> storeIdList = new ArrayList<Integer>();
        ResultData<List<AchievementCancelMapping>> resultDataCcs = achievementCancelService.getCanelStoresByFlowId(flowId);
        List<AchievementCancelMapping> contractStoreList = resultDataCcs.getReturnData();
        // 合同ID
        int contractId = 0;
        if (null != contractStoreList && !contractStoreList.isEmpty()) {
            for (AchievementCancelMapping contractStore : contractStoreList) {
                Integer storeId = contractStore.getStoreId();
                storeIdList.add(storeId);
                contractId = contractStore.getContractId();
            }
        }
        resultMap.put("contractId", contractId);
        resultMap.put("storeIdList", storeIdList);
        return resultMap;
    }

    /**
     * 更新OA审批结果
     *
     * @param oaContractChangeReturn
     * @return
     * @throws Exception
     */
    private Integer updateOaContractChangeReturn(OaContractChangeReturn oaContractChangeReturn) throws Exception {
        Integer cnt = oaContractChangeReturnMapper.updateOaContractChangeReturn(oaContractChangeReturn);
        return cnt;
    }

    //Mod By GUOPENGFEI 2017/04/21 end

    /**
     * 获取合同变更审核中的flowId集合
     */
    private List<String> getChgFlowIdStateList() {
        // 返回组装List
        List<String> flowIdList = new ArrayList<String>();
        Map<String, Object> reqMap = new HashMap<String, Object>();
        try {
            // 参数设定 (1:审核中)
            reqMap.put("approveState", 1);
            // 构建返回
            List<ContractChangeDto> contractChangeDtoList = new ArrayList<ContractChangeDto>();
            // 获取合同变更状态为审核中的FlowIdList
            contractChangeDtoList = stopContractService.getByApproveState(reqMap);
            // 非空判断
            if (null != contractChangeDtoList && !contractChangeDtoList.isEmpty()) {
                String flowId = null;
                for (ContractChangeDto contractChangeDto : contractChangeDtoList) {
                    flowId = contractChangeDto.getFlowId();
                    flowIdList.add(flowId);
                }
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "getChgFlowIdStateList", "", -1, "", "查询合同变更审核中状态失败!", e);
        }
        return flowIdList;
    }

    /**
     * 组装--合同变更审核状态
     *
     * @param flowIdList
     */
    private Map<String, Object> getChgOaAuditMap(List<String> flowIdList) {
        Map<String, Object> flowIdStateMap = new HashMap<String, Object>();
        // 审核通过List
        List<String> passList = new ArrayList<String>();
        // 作废List
        List<String> noPassList = new ArrayList<String>();
        if (null != flowIdList && !flowIdList.isEmpty()) {
            try {
                for (String flowId : flowIdList) {
                    Integer reback = -1;
                    reback = apiOaService.getOaState(flowId);
                    // 审核通过 （ 0正常结束）
                    if (0 == reback) {
                        passList.add(flowId);
                    }
                    // 作废 （ 非正常结束：5 撤销、15终止）
                    else if (5 == reback || 15 == reback) {
                        noPassList.add(flowId);
                    }
                }
            } catch (Exception e) {
                logger.error("open", "APITimerController", "getChgOaAuditMap", "", -1, "", "获取合同变更OA审核状态失败!", e);
            }
            flowIdStateMap.put("pass", passList);
            flowIdStateMap.put("noPass", noPassList);
        }
        return flowIdStateMap;
    }

    /**
     * 根据flow查询合同Id以及合同下的门店Id集合
     *
     * @return
     * @throws Exception
     */
    private Map<String, Object> getCtrctInfoByFlowId(String flowId)
            throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        // 根据FlowId查询合同变更下的门店
        List<Integer> storeIdList = new ArrayList<Integer>();
        ResultData<List<ContractChangeStore>> resultDataCcs = stopContractService.getChangeStoresByFlowIdApproving(flowId);
        List<ContractChangeStore> contractStoreList = resultDataCcs.getReturnData();
        // 合同ID
        int contractId = 0;
        if (null != contractStoreList && !contractStoreList.isEmpty()) {
            for (ContractChangeStore contractStore : contractStoreList) {
                Integer storeId = contractStore.getStoreId();
                storeIdList.add(storeId);
                contractId = contractStore.getContractId();
            }
        }
        resultMap.put("contractId", contractId);
        resultMap.put("storeIdList", storeIdList);
        return resultMap;
    }

    /**
     * 审核不通过：根据flowId、合同Id和门店Ids进行后台操作处理
     *
     * @throws Exception
     */
    private void doNoPassDateUpdate(String flowId, Integer contractId, List<Integer> storeIdList, Integer userId)
            throws Exception {
        for (Integer storeId : storeIdList) {
            // 更新变更门店为正常
            ContractStore contractStore = new ContractStore();
            contractStore.setStoreId(storeId);
            contractStore.setContractId(contractId);
            contractStore.setStoreState(0);
            contractService.updateContractStore(contractStore);

            // 更新变更记录表 状态为 审核失败
            ContractChangeDto contractChange = new ContractChangeDto();
            ContractInfoDto contractInfoDto = new ContractInfoDto();
            contractChange.setFlowId(flowId);
            contractChange.setApproveState(3); // 3:变更审核失败
            contractChange.setUpdateDate(new Date()); // 更新日期
            contractChange.setFlowEndDate(new Date()); //流程结束时间
            contractChange.setUpdateCreate(userId);// 更新人
            contractInfoDto.setContractChangeDto(contractChange);
            stopContractService.update(contractInfoDto);
        }
    }

    /**
     * 门店续签微信定时发送任务方法
     */
    @RequestMapping(value = "/sendwechat", method = RequestMethod.GET)
    public ResultData<?> doTask() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            // 获取微信数据
            List<MsgData> msgDataList = msgDataService.queryUnsendMsgData();
            // 发送微信
            if (null != msgDataList && !msgDataList.isEmpty()) {
                for (MsgData msgData : msgDataList) {
                    Boolean isSucess = WechatSendService.sendMsg(msgData);
                    if (isSucess) {
                        msgDataService.updateMsgToSended(msgData.getId());
                    }
                }
            }
        } catch (Exception e) {
            resultData.setFail();
            resultData.setReturnMsg("门店续签微信消息发送失败!");
            logger.error("weixin", "WechatSendTimeTask", "doTask", "", null, "", "", e);
        }
        return resultData;
    }

//Add By Kaku 2017/04/24 start

    /**
     * 取得OA审批结果信息
     *
     * @return OA审批结果信息
     * @throws Exception
     */
    private List<OaContractReturn> getOAContractReturn() throws Exception {
        // 返回组装List
        List<OaContractReturn> oaResult = null;

        oaResult = contractService.getOAContractReturn();
        return oaResult;

    }

    /**
     * 更新OA审批结果
     *
     * @param oAContractReturn
     * @return 更新数
     * @throws Exception
     */
    private Integer updateOAContractReturn(OaContractReturn oAContractReturn) throws Exception {
        // 返回组装List
        Integer cnt = 0;
        cnt = contractService.updateOAContractReturn(oAContractReturn);
        return cnt;

    }

    //Add By Kaku 2017/04/24 end

    //Add By Kaku 2017/05/19 合同状态取得 start

    /**
     * 根据OA单号取得合同状态
     *
     * @param flowId OA单号
     * @return 合同状态
     */
    private int getContractStatus(String flowId) {
        int contractstatus = 0;

        try {
            Contract contract = contractService.getByFlowId(flowId);
            contractstatus = contract.getContractStatus();
        } catch (Exception e) {
            logger.error("open", "APITimerController", "GetContractStatus", "", -1, "", "根据OA单号取得合同状态失败 ", e);
        }

        return contractstatus;
    }

    //Add By Kaku 2017/05/19 合同状态取得 end

//Add By GuoPengFei 2017/05/25 合规性 start

    /**
     * 是否乙转甲合同
     *
     * @param flowId
     * @return
     */
    private boolean isChangeB2AContract(String flowId) {
        int contractB2A = 0;
        boolean rtn = false;

        try {
            Contract contract = contractService.getByFlowId(flowId);
            contractB2A = contract.getContractB2A().intValue();
            if (DictionaryConstants.Contract_TypeB2A_YES == contractB2A) {
                rtn = true;
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "isChangeB2A", "", -1, "", "是否乙转甲合同判断失败", e);
        }

        return rtn;
    }

    /**
     * 跟新门店资质等级
     *
     * @param flowId
     * @return
     */
    private void updateABTypeStore(String flowId) {
        try {
            ResultData<List<ContractStore>> resultDataCS = contractService.getContractStoresByContractFlowId(flowId);
            List<ContractStore> csList = resultDataCS.getReturnData();
            for (ContractStore contractStore : csList) {
                Store store = new Store();
                store.setStoreId(contractStore.getStoreId());
                store.setABTypeStore(contractStore.getABTypeStore());
                store.setBTypeStore(contractStore.getBTypeStore());
                storeService.update(store);
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "updateABTypeStore", "", -1, "", "跟新门店资质等级", e);
        }

    }


    private void updateStoreType(String flowId,int storeType) {
        try {
            ResultData<List<ContractStore>> resultDataCS = contractService.getContractStoresByContractFlowId(flowId);
            List<ContractStore> csList = resultDataCS.getReturnData();
            for (ContractStore contractStore : csList) {
                Store store = new Store();
                store.setStoreId(contractStore.getStoreId());
                store.setStoreType(storeType);
                storeService.update(store);
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "updateStoreType", "", -1, "", "修改门店类型", e);
        }

    }


//Add By GuoPengFei 2017/05/25 合规性 end

//Add By Sucen 2017/07/17 OA房友公司返回状态 start

    /**
     * 定时拉取【OA房友公司返回状态】
     */
    @RequestMapping(value = "/oaFYCompanyReturn", method = RequestMethod.GET)
    public String oaFYCompanyReturntHandle() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        Integer rtn = 0;

        logger.info("CRM定时获取OA房友公司返回状态 begin，this time is" + new Date());

        try {
            // OA房友公司返回结果集取得
            List<OaFYCompanyReturn> oaResultlst = getOaFYCompanyReturn();
            // OA房友公司返回信息取得无
            if (null == oaResultlst || true == oaResultlst.isEmpty()) {
                resultData.setReturnMsg("没有OA房友公司返回状态结果信息!");
                return resultData.toString();
            }

            // OA房友公司返回信息循环处理
            for (OaFYCompanyReturn keyValue : oaResultlst) {
                // 合同Id不存在
                if (keyValue.getContractId() == null) {
                    keyValue.setReason("合同不存在");                    // 未提交原因
                    keyValue.setDateUpdate(new Date());                    // 更新时间

                    // 更新OA房友公司返回信息（未提交原因）
                    rtn = updateOAFYCompanyReason(keyValue);

                    resultData.setReturnMsg("合同表中合同Id不存在!");
                    continue;
                }

                // 公司Id不存在
                if (keyValue.getCompanyId() == null) {
                    keyValue.setReason("公司不存在");                    // 未提交原因
                    keyValue.setDateUpdate(new Date());                    // 更新时间

                    // 更新OA房友公司返回信息（未提交原因）
                    rtn = updateOAFYCompanyReason(keyValue);

                    resultData.setReturnMsg("公司表中公司Id不存在!");
                    continue;
                }

                // 房友账号开通状态 = 1已开通
                if (("1").equals(keyValue.getFangyouOpenStatus())) {
                    keyValue.setReason("公司门店房友账号已存在");            // 未提交原因
                    keyValue.setDateUpdate(new Date());                    // 更新时间

                    // 更新OA房友公司返回信息（未提交原因）
                    rtn = updateOAFYCompanyReason(keyValue);

                    resultData.setReturnMsg("公司门店房友账号已存在!");
                    continue;
                }

                //-- 房友账号开通状态 = 1已开通以外：--//
                // 合同门店信息集取得
                List<ContractStore> contractStorelst = contractService.getContractStoreByContractId(keyValue.getContractId());
                List<ContractStore> storelst = new ArrayList<>();
                // 合同门店信息取得无
                if (null == contractStorelst || true == contractStorelst.isEmpty()) {
                    resultData.setReturnMsg("合同门店表中合同不存在!");
                    continue;
                }
                // 合同门店信息循环处理
                for (ContractStore keyStore : contractStorelst) {
                    // 门店编号不存在
                    if (keyStore.getStoreNo() == null) {
                        resultData.setReturnMsg("门店编号不存在!");
                        continue;
                    }

                    // 门店房友账号关联表取得
                    StoreFangyouAccount oaAccount = getStoreFangyouAccount(keyValue.getCompanyNo(), keyStore.getStoreNo());
                    // 门店房友账号关联信息无
                    if (null == oaAccount) {
                        // 新建门店房友账号关联：开通中(openStatus = 1)
                        StoreFangyouAccount storeFangyouAccount = new StoreFangyouAccount();
                        storeFangyouAccount.setStoreId(keyStore.getStoreId());        // 门店Id
                        storeFangyouAccount.setStoreNo(keyStore.getStoreNo());        // 门店编号
                        storeFangyouAccount.setName(keyStore.getName());            // 门店名称
                        storeFangyouAccount.setFangyouNo(keyValue.getCompanyNo());    // 房友账号（公司NO）
                        storeFangyouAccount.setOpenStatus("1");                        // 开通状态 = 1:开通中
                        storeFangyouAccount.setOperType("1");                        // 绑定状态 = 1:绑定
                        storeFangyouAccount.setDateCreate(new Date());                // 创建时间
                        storeFangyouAccount.setDelFlag("0");                        // 删除标识 = 0:未删除
                        storeFangyouAccount.setUserIdCreate(0);                     // 创建人
                        // 新建门店房友账号关联表数据
                        rtn = createStoreFangyouAccount(storeFangyouAccount);

                        //门店房友账号绑定/解绑日志添加
                        createLogStoreFangyouAccount(storeFangyouAccount);
                        resultData.setReturnMsg("新建门店房友账号成功!");
                    } else {
                        // 开通状态 为 null or 0:未开通
                        if (oaAccount.getOpenStatus() == null || "0".equals(oaAccount.getOpenStatus())) {
                            // 开通状态 = 1:开通中
                            oaAccount.setOpenStatus("1");
                        }
                        // 开通状态 为 2:已开通
                        if ("2".equals(oaAccount.getOpenStatus())) {
                            continue;
                        }
                        // 绑定状态 = 1:绑定
                        oaAccount.setOperType("1");

                        // 更新门店房友账号关联的开通绑定状态
                        rtn = updateStoreFangyouAccountStatus(oaAccount);

                        //门店房友账号绑定/解绑日志添加
                        createLogStoreFangyouAccount(oaAccount);
                    }

                    storelst.add(keyStore);
                }

                //调用OP
                setOpStatus(keyValue, storelst);

                storelst.clear();
            }
            // 更新OA房友公司返回的提交状态 为 1：已提交
            rtn = updateOAFYCompanyHasDeal();

        } catch (Exception e) {
            logger.error("open", "APITimerController", "oaFYCompanyReturntHandle", "", -1, "", "CRM定时获取OA房友公司返回状态失败", e);
        }
        resultData.setReturnMsg("更新OA房友公司返回表成功!");
        logger.info("CRM定时获取OA房友公司返回状态 end，this time is" + new Date());
        return resultData.toString();
    }

    /**
     * 取得OA房友公司返回状态结果信息
     *
     * @return OA房友公司返回状态结果信息
     * @throws Exception
     */
    private List<OaFYCompanyReturn> getOaFYCompanyReturn() throws Exception {
        // 返回组装List
        List<OaFYCompanyReturn> oaResult = null;

        oaResult = contractService.getOaFYCompanyReturn();
        return oaResult;
    }

    /**
     * 取得门店房友账号关联信息
     *
     * @return 门店房友账号关联信息
     * @throws Exception
     */
    private StoreFangyouAccount getStoreFangyouAccount(String fangyouNo, String storeNo) throws Exception {
        // 返回组装
        StoreFangyouAccount oaResult = null;

        oaResult = contractService.getStoreFangyouAccount(fangyouNo, storeNo);
        return oaResult;

    }

    /**
     * 更新OA房友公司返回的未提交原因
     *
     * @param oAFYCompanyReturn
     * @return 更新数
     * @throws Exception
     */
    private Integer updateOAFYCompanyReason(OaFYCompanyReturn oAFYCompanyReturn) throws Exception {
        Integer rtn = contractService.updateOAFYCompanyReason(oAFYCompanyReturn);
        return rtn;

    }

    /**
     * 更新OA房友公司返回的提交状态
     *
     * @return 更新数
     * @throws Exception
     */
    private Integer updateOAFYCompanyHasDeal() throws Exception {
        Integer rtn = contractService.updateOAFYCompanyHasDeal();
        return rtn;
    }

    /**
     * 新建门店房友账号关联表数据
     *
     * @param storeFangyouAccount
     * @return 新建数
     * @throws Exception
     */
    public Integer createStoreFangyouAccount(StoreFangyouAccount storeFangyouAccount) throws Exception {
        Integer rtn = contractService.createStoreFangyouAccount(storeFangyouAccount);
        return rtn;
    }

    /**
     * 更新门店房友账号关联的开通绑定状态
     *
     * @param storeFangyouAccount
     * @return 更新数
     * @throws Exception
     */
    public Integer updateStoreFangyouAccountStatus(StoreFangyouAccount storeFangyouAccount) throws Exception {
        Integer rtn = contractService.updateStoreFangyouAccountStatus(storeFangyouAccount);
        return rtn;

    }

    /**
     * CRM发起房友账号申请
     *
     * @param companyDto 公司DTO
     *                   storeList 门店DTOLIST
     * @return
     */
    private void setOpStatus(OaFYCompanyReturn companyDto, List<ContractStore> storeList) {
        CompanyADto companyADto = new CompanyADto();
        List<StoreADto> storeDtoList = new ArrayList<>();
        String url = SystemParam.getWebConfigValue("opUrl") + "applyfyaccount";

//    	String userIdUpdate = UserInfoHolder.get().getUserCode();
        OpOperatorDto opDto = new OpOperatorDto();
        //公司信息设置
        companyADto = setCompanyDto(companyDto);
        //门店信息设置
        storeDtoList = setStoreDtoList(storeList);
        //操作人ID
        opDto.setUserIdUpdate(0);
        //公司DTO
        opDto.setCompanyADto(companyADto);
        //门店DTOLIST
        opDto.setStoreADtoList(storeDtoList);
        logger.info("CRM发起房友账号申请接口: #####请求#url=" + url + "##userIdUpdate=" + opDto.getUserIdUpdate());
        //jsonString设值
        String jsonData = JsonUtil.parseToJson(opDto);
        try {
            String param = HttpClientUtil.jsonPost(url, jsonData);
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            logger.info("CRM发起房友账号申请接口: " + queryParam.get("returnMsg"));
        } catch (Exception e) {
            logger.error("open", "APITimerController", "setOpStatus", "", -1, "", "CRM发起房友账号申请失败 ", e);
        }

    }

    /**
     * 公司信息设置
     *
     * @param companyDto 公司信息
     * @return CompanyADto 公司DTO
     */
    private CompanyADto setCompanyDto(OaFYCompanyReturn companyDto) {
        CompanyADto companyADto = new CompanyADto();
        companyADto.setCompanyNo(companyDto.getCompanyNo());
        companyADto.setCompanyName(companyDto.getCompanyName());
        companyADto.setCityNo(companyDto.getCityNo());
        companyADto.setDistrictNo(companyDto.getDistrictNo());
        companyADto.setAreaNo(companyDto.getAreaNo());
        companyADto.setCompanyAddr(companyDto.getCompanyAddr());
        companyADto.setLinkMan(companyDto.getLinkMan());
        companyADto.setLinkPhone(companyDto.getLinkPhone());
        companyADto.setFyCompanyId(companyDto.getFangyouCompanyId());
        companyADto.setLongitude(companyDto.getLongitude());
        companyADto.setLatitude(companyDto.getLatitude());
        return companyADto;
    }

    /**
     * 门店信息设置
     *
     * @param storeList 门店信息
     * @return StoreADto 门店DTO
     */
    private List<StoreADto> setStoreDtoList(List<ContractStore> storeList) {
        List<StoreADto> storeDtoList = new ArrayList<>();

        for (ContractStore contractStore : storeList) {
            StoreADto storeDto = new StoreADto();
            storeDto.setStoreNo(contractStore.getStoreNo());
            storeDto.setName(contractStore.getName());
            storeDto.setCityNo(contractStore.getCityNo());
            storeDto.setDistrictNo(contractStore.getDistrictNo());
            storeDto.setAreaNo(contractStore.getAreaNo());
            storeDto.setAddress(contractStore.getAddress());
            storeDto.setAddressDetail(contractStore.getAddressDetail());
            storeDto.setLongitude(contractStore.getLongitude());
            storeDto.setLatitude(contractStore.getLatitude());
            storeDtoList.add(storeDto);
        }
        return storeDtoList;
    }
    //Add By Sucen 2017/07/17 OA房友公司返回状态 end

    //Add by cning 2017/07/24 房友账号自动绑定 Start
    private int bindFangyouAccountSendOP(String flowId, List<Map<String, Object>> storeMapList) {
        //取得合同ID
        try {
            Contract opContract = contractService.getByFlowId(flowId);
            if (null == opContract) {
                return 0;
            }
            //①签合同公司一定要有房友账号
            Company company = companyService.getByCompanyId(opContract.getCompanyId());
            if (null == company) {
                return 0;
            }
            //房友账号开通状态（0:未开通 1:已开通)
            if (!"1".equals(company.getFangyouOpenStatus()) || "".equals(company.getFangyouOpenStatus())) {
                return 0;
            }
            //②签合同的门店不能有绑定关系
            //获取公司签约门店
            List<CompanyStore> storeList = companyStoreService.getCompanyStoreList(opContract.getCompanyId());
            for (CompanyStore companyStore : storeList) {
                //取得门店信息和公司编号
                Map<String, Object> storeInfo = this.fangyouAccountService.getOPByStoreId(companyStore.getStoreId());

                //取得门店房友账号关联信息
                ResultData<FangyouAccount> rtnData = fangyouAccountService.getById(companyStore.getStoreId());
                FangyouAccount fangyouAccount = (FangyouAccount) rtnData.getReturnData();
                if (fangyouAccount != null) {
                    if ("1".equals(fangyouAccount.getOperType()))//绑定状态（1:绑定;0:解绑）
                    {
                        continue;
                    }
                }
                storeMapList.add(storeInfo);

                //添加修改门店房友绑定关系
                StoreFangyouAccount storeFangyouAccount = new StoreFangyouAccount();
                storeFangyouAccount.setStoreId(companyStore.getStoreId());
                storeFangyouAccount.setStoreNo(storeInfo.get("StoreNo").toString());
                storeFangyouAccount.setName(storeInfo.get("Name").toString());
                storeFangyouAccount.setOperType("1");    //绑定状态（1:绑定;0:解绑）
                storeFangyouAccount.setFangyouNo(company.getCompanyNo());
                storeFangyouAccount.setOpenStatus("2"); //0:未开通；1:开通中;2:已开通
                storeFangyouAccount.setUserIdCreate(0);
                storeFangyouAccount.setDateCreate(new Date());
                storeFangyouAccount.setDelFlag("0");
                if (null == fangyouAccount) {
                    createStoreFangyouAccount(storeFangyouAccount);
                }
                //未开通状态下提交OP，并修改状态为已开通，绑定状态为已绑定
                else {
                    storeFangyouAccount.setId(fangyouAccount.getId());
                    updateStoreFangyouAccountStatus(storeFangyouAccount);
                }

                //门店房友账号绑定/解绑日志添加
                createLogStoreFangyouAccount(storeFangyouAccount);
            }

            //提交到OP
            String url = SystemParam.getWebConfigValue("opUrl") + "fyaccountbind";
            OpStoreDto opDto = new OpStoreDto();
            opDto.setIsBind(1);   //1:绑定 2：解绑
            opDto.setUserIdUpdate(0);
            opDto.setCompanyNo(company.getCompanyNo());
            opDto.setStoreADtoList(setOPStoreDtoList(storeMapList));

            //jsonString设值
            String jsonData = JsonUtil.parseToJson(opDto);
            logger.info("CRM房友账号绑定申请接口: #####请求#url=" + url + "##userIdUpdate=" + opDto.getUserIdUpdate());
            String opResult = HttpClientUtil.jsonPost(url, jsonData);

            //op返回值
            Map<String, Object> opMap = (Map<String, Object>) JsonUtil.parseToObject(opResult, Map.class);
            logger.info("CRM房友账号绑定申请接口返回码：" + opMap.get("returnCode").toString() + ",返回信息：" + opMap.get("returnMsg").toString());
        } catch (Exception e) {
            logger.error("open", "APITimerController", "bindFangyouAccountSendOP", "", -1, "", "CRM发起房友账号申请失败 ", e);
        }
        return 1;
    }

    /**
     * 门店信息设置
     *
     * @param storeList 门店信息
     * @return StoreADto 门店DTO
     */
    private List<StoreADto> setOPStoreDtoList(List<Map<String, Object>> storeList) {
        List<StoreADto> storeDtoList = new ArrayList<>();
        for (Map<String, Object> storeADto : storeList) {
            StoreADto storeDto = new StoreADto();
            storeDto.setStoreNo(storeADto.get("StoreNo").toString());
            storeDto.setName(storeADto.get("Name").toString());
            storeDto.setCityNo(storeADto.get("CityNo").toString());
            storeDto.setDistrictNo(storeADto.get("DistrictNo").toString());
            storeDto.setAreaNo(storeADto.get("AreaNo").toString());
            storeDto.setAddress(storeADto.get("Address").toString());
            storeDto.setAddressDetail(storeADto.get("AddressDetail").toString());
            storeDto.setLongitude(storeADto.get("Longitude").toString());
            storeDto.setLatitude(storeADto.get("Latitude").toString());
            storeDtoList.add(storeDto);
        }

        return storeDtoList;
    }

    /**
     * 门店房友账号绑定/解绑日志添加
     *
     * @param storeFangyouAccount
     * @return
     * @throws Exception
     */
    private int createLogStoreFangyouAccount(StoreFangyouAccount storeFangyouAccount) throws Exception {
        FangyouAccount fangyouAccount = new FangyouAccount();
        BeanUtils.copyProperties(storeFangyouAccount, fangyouAccount);
        Integer rtn = contractService.createLogStoreFangyouAccount(fangyouAccount);
        return rtn;
    }
    //Add by cning 2017/07/24 房友账号自动绑定 End

    /*
     * 解除公司和门店的绑定
     * add by wang kanlin 2017/09/21
     */
    private void unbindCompanyStore(String flowId, Integer contractId, Integer companyId, List<Integer> storeIdList) throws Exception {
        ResultData<ContractChange> contractChangedata = stopContractService.getContractChangeByFlowId(flowId);
        ContractChange contractChange = contractChangedata.getReturnData();
        Integer changeType = contractChange.getChangeType();
        Integer b2aType = contractChange.getChangeCompany();//签约主体变更

        if (changeType.equals(DictionaryConstants.Contract_ChangeType_B2A) && b2aType.equals(1)) {
            for (Integer storeId : storeIdList) {
                CompanyStoreDto companyStoreDto = new CompanyStoreDto();
                companyStoreDto.setCompanyId(companyId);
                companyStoreDto.setStoreId(storeId);
                companyStoreService.deleteCompanyStore(companyStoreDto);
            }
        }
    }


    @RequestMapping(value = "/setReceiveToOa", method = RequestMethod.GET)
    public String receiveToOa() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("定时获取待发的收款单数据 begin，this time is" + new Date());

        try {
            // 查找待发的收款单
            List<StoreReceive> receiveList = storeReceiveService.getReceiveListForOa();
            if (null == receiveList || true == receiveList.isEmpty()) {
                resultData.setReturnMsg("");
                return resultData.toString();
            } else {
                //批量更新为处理中
                this.lockReceiveListForOa(receiveList);
            }

            //循环处理并提交OA
            for (StoreReceive receive : receiveList) {
                try {
                    String contractNo = receive.getContractNo();

                    BasCenterSetting centerSetting = basCenterSettingService.selectByContractNo(contractNo);
                    if (centerSetting == null) {
                        throw new NullPointerException("根据合同编号:" + contractNo + "(合同里的centerId)查询BasCenterSetting为空");
                    }

                    String defaultPayoutId = SystemParam.getWebConfigValue("default_payout_id");
                    String defaultPayoutName = SystemParam.getWebConfigValue("default_payout_name");

                    if (receive.getFeeType().intValue() == 17905) {
                        defaultPayoutId = "1122001001";
                        defaultPayoutName = "应收账款开票";
                    }

                    BasOaSupplier oaSupplier = basOaSupplierService.selectByContractNo(contractNo);
                    if (oaSupplier == null) {
                        //查询供应商默认值
                        String defaultSupplierCode = SystemParam.getWebConfigValue("default_supplier_code");
                        String defaultSupplierName = SystemParam.getWebConfigValue("default_supplier_name");
                        if (receive.getFeeType().intValue() == 17905) {
                            defaultSupplierCode = "K1003724";
                            defaultSupplierName = "二手房系统收款";
                        }
                        oaSupplier = new BasOaSupplier();
                        oaSupplier.setOaSupplierCode(defaultSupplierCode);
                        oaSupplier.setOaSupplierName(defaultSupplierName);
                    }

                    Map<String, Object> citySetting = citySettingMapper.getCitySettingByContractAcCityNo(contractNo);
                    if (citySetting == null || StringUtil.isEmpty(citySetting.get("storeReceiveTpl").toString())) {
                        throw new NullPointerException("根据合同编号:" + contractNo + "(合同里的acCityNo)查询Bas_CitySetting为空");
                    }

                    Map<String, Object> sendOaData = new HashMap<>();

                    // 门店收款OA模板
                    String oaTemplateCode = citySetting.get("storeReceiveTpl").toString();
                    // 模板编号
                    sendOaData.put("templateCode", oaTemplateCode);
                    // 发起者的登录名（登录协同的登录名）
                    sendOaData.put("senderLoginName", centerSetting.getSendUserCode());
                    // 协同的标题
                    sendOaData.put("subject", "保证金收款单");

                    //支付方式
                    String toolCode = receive.getToolCode();
                    String paySeq = null;
                    if (toolCode == null) {
                        receive.setReceiveTypeNm("银行转账");
                    } else if ("18603".equals(toolCode)) {
                        receive.setReceiveTypeNm("微信");
                        receive.setBankCode("1015001003");
                        receive.setBankName("房友_其他货币资金_微信");
                        receive.setOaBankId("1015001003");
                        receive.setOaBankName("房友_其他货币资金_微信");
                    } else if ("18604".equals(toolCode)) {
                        receive.setReceiveTypeNm("支付宝");
                        receive.setBankCode("1015001002");
                        receive.setBankName("房友_其他货币资金_支付宝");
                        receive.setOaBankId("1015001002");
                        receive.setOaBankName("房友_其他货币资金_支付宝");
                        paySeq = receive.getPaySeq();
                    }

                    //门店信息xml
                    List<StoreReceiveDtl> dtlList = storeReceiveService.getDtlList(receive.getId());
                    StringBuilder sb = new StringBuilder();
                    if (dtlList != null && dtlList.size() > 0) {
                        for (StoreReceiveDtl dtl : dtlList) {
                            sb.append("<row>" + "<column name=\"收入类别\"><value>" + defaultPayoutName + "</value></column>"
                                    + "<column name=\"收入类别编码\"><value>" + defaultPayoutId + "</value></column>"
                                    + "<column name=\"供应商\"><value>" + oaSupplier.getOaSupplierName() + "</value></column>"
                                    + "<column name=\"供应商编码\"><value>" + oaSupplier.getOaSupplierCode() + "</value></column>"
                                    + "<column name=\"收款金额\"><value>" + dtl.getAmount().setScale(2) + "</value></column>"
                                    + "<column name=\"门店编号\"><value>" + dtl.getStoreNo() + "</value></column>"
                                    + "<column name=\"门店名称\"><value>" + dtl.getStoreName() + "</value></column>"
                                    + "</row>");
                        }
                    }

                    //支付宝手续费明细
                    BigDecimal payFee = BigDecimal.ZERO;
                    if (paySeq != null) {
                        CashierDto cashierDto = omsInteractiveService.getPayFeeByPaySeq(paySeq);
                        if (cashierDto == null) {
                            throw new NullPointerException("根据paySeq: " + paySeq + " 获取oms.dbo.CRM_CashierOrder支付宝手续费明细为空");
                        }
                        payFee = payFee.add(cashierDto.getPayTotalFee());
                    }

                    BigDecimal totalAmount = receive.getTotalAmount();
                    if (BigDecimal.ZERO.compareTo(payFee) != 0) {
                        totalAmount = totalAmount.add(payFee);
                        sb.append("<row>" + "<column name=\"收入类别\"><value>财务费用-手续费-支付宝手续费</value></column>"
                                + "<column name=\"收入类别编码\"><value>6603901003</value></column>"
                                + "<column name=\"供应商\"><value>" + oaSupplier.getOaSupplierName() + "</value></column>"
                                + "<column name=\"供应商编码\"><value>" + oaSupplier.getOaSupplierCode() + "</value></column>"
                                + "<column name=\"收款金额\"><value>" + payFee.setScale(2) + "</value></column>"
                                + "<column name=\"门店编号\"><value>" + "" + "</value></column>"
                                + "<column name=\"门店名称\"><value>" + "" + "</value></column>"
                                + "</row>");
                    }

                    //附件集合汇总
                    List<Long> attachList = new ArrayList<Long>();
                    // 银行凭证
                    this.getOaAttachment(attachList, receive.getId(), 1027, centerSetting.getSendUserCode());

                    Date dateRecorded = new Date();
                    ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_RECEIVE_OANO");
                    String oaNo = null;
                    if (data != null && data.getReturnCode().equals("200")) {
                        oaNo = data.getReturnData();
                    } else {
                        throw new NullPointerException("根据seq_type: TYPE_RECEIVE_OANO 获取oaNo为空");
                    }

                    String remark = "";
                    if (StringUtil.isNotEmpty(receive.getRemark())) {
                        remark += XmlTransferUtil.transfer(receive.getRemark()) + "\r\n经纪公司：" + receive.getCompanyName();
                    } else {
                        remark = "经纪公司：" + XmlTransferUtil.transfer(receive.getCompanyName());
                    }
                    // 组装向OA发送的参数信息
                    String dataXml =
                            "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                                    + "<summary id=\"-8896389543745008481\" name=\"formmain_3960\"/>" + "<definitions/>" + "<values>"
                                    + "<column name=\"编码\"><value>"
                                    + oaNo
                                    + "</value></column>"
                                    + "<column name=\"核算主体\"><value>"
                                    + centerSetting.getAccountProject()
                                    + "</value></column>"
                                    + "<column name=\"核算主体编码\"><value>"
                                    + centerSetting.getAccountProjectCode()
                                    + "</value></column>"
                                    + "<column name=\"考核主体\"><value>"
                                    + centerSetting.getCheckBodyName()
                                    + "</value></column>"
                                    + "<column name=\"考核主体编码\"><value>"
                                    + centerSetting.getCheckBodyId()
                                    + "</value></column>"
                                    + "<column name=\"成本中心\"><value>"
                                    + centerSetting.getCostName()
                                    + "</value></column>"
                                    + "<column name=\"成本中心编码\"><value>"
                                    + centerSetting.getCostCode()
                                    + "</value></column>"
                                    + "<column name=\"收款银行\"><value>"
                                    + receive.getOaBankName()
                                    + "</value></column>"
                                    + "<column name=\"收款银行编码\"><value>"
                                    + receive.getOaBankId()
                                    + "</value></column>"
                                    + "<column name=\"入账日期\"><value>"
                                    + DateUtil.fmtDate(dateRecorded, "yyyy-MM-dd")
                                    + "</value></column>"
                                    + "<column name=\"合同编号\"><value>"
                                    + contractNo
                                    + "</value></column>"
                                    + "<column name=\"收款方式\"><value>"
                                    + receive.getReceiveTypeNm()
                                    + "</value></column>"
                                    + "<column name=\"收款人\"><value>"
                                    + receive.getUserName()
                                    + "</value></column>"
                                    + "<column name=\"收款人工号\"><value>"
                                    + receive.getUserCode()
                                    + "</value></column>"
                                    + "<column name=\"收款类型\"><value>"
                                    + receive.getFeeTypeNm()
                                    + "</value></column>"
                                    + "<column name=\"收款金额合计\"><value>"
                                    + totalAmount.setScale(2)
                                    + "</value></column>"
                                    + "<column name=\"备注\"><value>"
                                    + remark
                                    + "</value></column>"
                                    + "</values>"
                                    + "<subForms>"
                                    + "<subForm>"
                                    + "<definitions>"
                                    + "<column id=\"field0017\" type=\"0\" name=\"收入类别\" length=\"255\"/>"
                                    + "<column id=\"field0023\" type=\"0\" name=\"收入类别编码\" length=\"255\"/>"
                                    + "<column id=\"field0018\" type=\"0\" name=\"供应商\" length=\"255\"/>"
                                    + "<column id=\"field0019\" type=\"0\" name=\"供应商编码\" length=\"255\"/>"
                                    + "<column id=\"field0020\" type=\"0\" name=\"收款金额\" length=\"255\"/>"
                                    + "<column id=\"field0021\" type=\"0\" name=\"门店编号\" length=\"255\"/>"
                                    + "<column id=\"field0022\" type=\"0\" name=\"门店名称\" length=\"255\"/>"
                                    + "</definitions>"
                                    + "<values>"
                                    + sb.toString() + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";

                    // 附件，Long型数组，值为附件的Id。
                    sendOaData.put("attachments", attachList);
                    sendOaData.put("data", dataXml);
                    // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
                    sendOaData.put("state", "0");

                    // 取得REST动态客户机实例
                    CTPRestClient client = getClient();
                    String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
                    //为登录验证后获取的身份令牌
                    sendOaData.put("token", token);

                    StoreReceive updateReceive = new StoreReceive();
                    updateReceive.setId(receive.getId());
                    updateReceive.setDateUpt(new Date());
                    updateReceive.setDateRecorded(dateRecorded);
                    updateReceive.setOaNo(oaNo);

                    //发起申请 返回FlowId
                    Long flowId = null;
                    try {
                        //提交OA
                        flowId = apiOaService.toOaAuth(sendOaData, citySetting.get("storeReceiveTpl").toString());
                    } catch (Exception e) {
                        logger.error("open", "APITimerController", "toOaAuth", "", 0, "", "CRM定时提交OA收款单失败,合同编号:" + contractNo, e);
                    }

                    //更新收款主表信息
                    StoreReceiveDtl updateDtl = null;
                    if (flowId != null) {
                        updateReceive.setFlowId(flowId + "");
                        //已提交
                        updateReceive.setSubmitOaStatus(21203);
                        //审批中
                        updateReceive.setApproveStatus(21602);
                        updateReceive.setAccountProject(centerSetting.getAccountProject());
                        updateReceive.setAccountProjectCode(centerSetting.getAccountProjectCode());
                        updateReceive.setCostCode(centerSetting.getCostCode());
                        updateReceive.setCostName(centerSetting.getCostName());
                        updateReceive.setCheckBodyId(centerSetting.getCheckBodyId());
                        updateReceive.setCheckBodyName(centerSetting.getCheckBodyName());

                        //设置明细表信息
                        updateDtl = new StoreReceiveDtl();
                        updateDtl.setProviderCode(oaSupplier.getOaSupplierCode());
                        updateDtl.setProviderName(oaSupplier.getOaSupplierName());
                        updateDtl.setPayoutId(defaultPayoutId);
                        updateDtl.setPayoutName(defaultPayoutName);
                        updateDtl.setDateUpt(new Date());
                        updateDtl.setReceiveId(receive.getId());
                    } else {
                        updateReceive.setSubmitOaStatus(21204);
                    }
                    storeReceiveService.updateByPrimaryKeySelective(updateReceive);

                    //更新收款明细表信息
                    if (updateDtl != null) {
                        storeReceiveService.batchUpdateDtl(updateDtl);
                    }

                } catch (Exception e) {
                    logger.error("open", "APITimerController", "receiveToOa", "", -1, "", "CRM定时提交OA收款单失败", e);

                    StoreReceive exeUpt = new StoreReceive();
                    exeUpt.setId(receive.getId());
                    //提交失败
                    exeUpt.setSubmitOaStatus(21204);
                    storeReceiveService.updateByPrimaryKeySelective(exeUpt);
                }
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "receiveToOa", "", -1, "", "CRM定时提交OA收款单失败", e);
        }
        logger.info("定时获取待发的收款单数据 end，this time is" + new Date());
        return resultData.toString();
    }

    private CTPRestClient getClient() {
        // 指定协议、IP和端口，获取ClientManager
        CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance(SystemParam.getWebConfigValue("oaUrl"));
        // 取得REST动态客户机实例
        CTPRestClient client = clientManager.getRestClient();
        return client;
    }

    private void getOaAttachment(List<Long> attachList, int receiveId, Integer fileTypeCode, String userCode) throws Exception {
        StringBuilder oaFile = new StringBuilder();
        // 文件Code
        String sbFileCode = null;
        try {
            Map<String, Object> param = new HashMap<>();
            String attachStrs = "";
            param.put("refId", receiveId);
            param.put("fileTypeId", fileTypeCode);
            List<FileRecordMainDto> backResult = fileService.queryList(param);
            for (FileRecordMainDto fileMain : backResult) {
                String fileUrl = fileMain.getFileUrl();

                String fileFullName = fileMain.getFileFullName();
                String attachmentId = UploadOAUtil.oaAttachmentUpload(fileUrl, fileFullName, userCode, "");
                if (StringUtil.isNotEmpty(attachmentId)) {
                    // 信息拼接
                    sbFileCode = "<value>" + attachmentId + "</value>";
                    oaFile.append(sbFileCode);

                    attachList.add(Long.valueOf(attachmentId));
                    if (StringUtil.isEmpty(attachStrs)) {
                        attachStrs = attachmentId;
                    } else {
                        attachStrs = attachStrs + "|" + attachmentId;
                    }
                }
            }
        } catch (Exception e) {
            logger.error("store_recieve",
                    "APITimerController",
                    "getOaAttachment",
                    "",
                    Integer.valueOf(userCode),
                    "",
                    "上传文件Code查询失败！",
                    e);
        }
    }

    public void lockReceiveListForOa(List<StoreReceive> upList) {

        List<Integer> idList = new ArrayList<Integer>();
        if (upList != null && upList.size() > 0) {
            for (StoreReceive o : upList) {
                if (o.getId() != null) {
                    idList.add(o.getId());
                }
            }
        }

        if (idList.size() > 0) {
            Map<String, Object> param = new HashMap<>();
            param.put("idList", idList);
            param.put("submitOaStatus", 21202);
            storeReceiveService.batchUpdate(param);
        }
    }

    @RequestMapping(value = "/frameContract", method = RequestMethod.GET)
    public String frameContracrtHandle() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        logger.info("CRM定时获取框架合同审核状态 begin，this time is" + new Date());

        try {
            // OA审批结果集取得
            List<OaLnkFrameContractReturn> oaResultlst = frameContractService.getOaLnkFrameContractReturn();
            if (null == oaResultlst || true == oaResultlst.isEmpty()) {
                resultData.setReturnMsg("没有OA审批结果信息!");
                return resultData.toString();
            }
            //3、更新合同状态
            for (OaLnkFrameContractReturn keyValue : oaResultlst) {
                FrameContract frameContractDto = new FrameContract();
                //更新框架合同的状态为审核通过
                frameContractDto.setApproveState(keyValue.getApproveStatus());

                frameContractDto.setApprovePassDate(keyValue.getApproveDate());
                String flowId = keyValue.getFlowId();
                frameContractDto.setDateUpt(new Date());

                //合同状态判断
                FrameContract frameContract = frameContractService.getByFlowId(flowId);
                if (frameContract == null) {
                    continue;
                }
                int approveState = frameContract.getApproveState();
                frameContractDto.setId(frameContract.getId());

                if (approveState != DictionaryConstants.CONTRACT_STATUS_AUDITING) {//合同状态不在审核中
                    logger.info("OA审批结果处理时 该框架合同不处于审核中状态 OA单号：[" + keyValue.getFlowId() + "]");
                    continue;
                }

                try {
                    // 更新框架合同状态
                    frameContractMapper.updateByPrimaryKeySelective(frameContractDto);

                    //审核通过记录银行信息
                    if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(keyValue.getApproveStatus())) {
                        //****** 记录银行信息********
                        frameContractMapper.addCompanyBandInfo(frameContract);
                        //更新渠道公司供应商信息(改为新增分销合同时更新)
                        //frameContractMapper.updateCompanyVendorInfo(frameContract);
                    }
                } catch (Exception e) {
                    logger.error("FrameContract", "APITimerController", "frameContracrtHandle", "", -1, "", "更新框架合同状态失败!", e);
                    continue;
                }

                // 已处理OA审批结果更新
                keyValue.setHasDeal(1);    // 已处理OA审批结果
                keyValue.setDateUpdate(new Date());
                Integer cnt = frameContractService.updateOaLnkFrameContractReturn(keyValue);

                //审核不通过 （ 10404）
                if (DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS.equals(keyValue.getApproveStatus())) {
                    continue;
                }

                //审核通过后才更新业务类型
                Map<String, Object> hashMap = new HashMap<>();
                hashMap.put("companyNo", frameContract.getCompanyNo());
                hashMap.put("bizType", "21802");
                frameContractMapper.updateByCompanyNo(hashMap);

                //审批通过，通知OP
//                if ("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
//                    //18版OP
//                    String url18 = SystemParam.getWebConfigValue("opUrl18") + "accounts/apply/{companyNo}?businessType=3&receivePhone=" + frameContract.getFyAccountNmTel();
//                    String opResult = null;
//                    try {
//                        opResult = HttpClientUtil.jsonGet(url18, frameContract.getCompanyNo());
//                    } catch (Exception e) {
//                        FrameContract frameContract1 = new FrameContract();
//                        frameContract1.setId(frameContract.getId());
//                        frameContract1.setFyNoticeStatus("0");
//                        frameContractMapper.updateByPrimaryKeySelective(frameContract1);
//                        logger.error("FrameContract", "APITimerController", "frameContracrtHandle", null, null, null, "CRM草签框架合同向OP发起待开通新房账号接口失败！Company：" + frameContract.getCompanyNo() + "  opResult: " + opResult, e);
//
//                        continue;
//                    }
//
//                    try {
//                        Map<String, String> opMap = (Map<String, String>) JsonUtil.parseToObject(opResult, Map.class);
//                        if (opMap.get("returnCode").equals("200")) {
//                            logger.error("FrameContract", "APITimerController", "frameContracrtHandle", opResult, null, null, "CRM草签框架合同向OP发起待开通新房账号接口失败！Company：" + frameContract.getCompanyNo() + "  opResult: " + opResult, null);
//                        }
//                    } catch (Exception e) {
//                        logger.error("FrameContract", "APITimerController", "frameContracrtHandle", opResult, null, null, "CRM草签框架合同向OP发起待开通新房账号接口失败！Company：" + frameContract.getCompanyNo() + "  opResult: " + opResult, e);
//                    }
//                }
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "frameContracrtHandle", "", -1, "", "CRM定时获取框架合同审核状态失败", e);
        }
        resultData.setReturnMsg("更新框架合同信息成功!");
        logger.info("CRM定时获取框架合同审核状态 end，this time is" + new Date());
        return resultData.toString();
    }

    /**
     * 收款调整单发OA
     *
     * @return
     */
    @RequestMapping(value = "/receiveAdjustToOa", method = RequestMethod.GET)
    public String receiveAdjustToOa() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("定时获取待发的收款调整单数据 begin，this time is" + new Date());

        try {
            // 查找待发的收款调整单
            List<StoreReceive> receiveList = storeReceiveService.getReceiveTDListForOa();
            if (null == receiveList || true == receiveList.isEmpty()) {
                resultData.setReturnMsg("");
                return resultData.toString();
            } else {
                //批量更新为处理中
                this.lockReceiveListForOa(receiveList);
            }

            //循环处理并提交OA
            for (StoreReceive receive : receiveList) {
                try {
                    String storeNo = receive.getStoreNo();

                    BasCenterSetting centerSetting = basCenterSettingService.selectByStoreNo(storeNo);
                    if (centerSetting == null) {
                        throw new NullPointerException("根据门店编号:" + storeNo + "(门店里的centerId)查询BasCenterSetting为空");
                    }

                    String defaultPayoutId = SystemParam.getWebConfigValue("default_payout_id");
                    String defaultPayoutName = SystemParam.getWebConfigValue("default_payout_name");
                    if (receive.getFeeType().intValue() == 17905) {
                        defaultPayoutId = "1122001001";
                        defaultPayoutName = "应收账款开票";
                    }

                    Map<String, Object> citySetting = citySettingMapper.getCitySettingByCityNo(receive.getCityNo());
                    if (citySetting == null || StringUtil.isEmpty(citySetting.get("storeReceiveTpl").toString())) {
                        throw new NullPointerException("根据cityNo编号:" + receive.getCityNo() + "查询Bas_CitySetting的storeReceiveTpl为空");
                    }

                    Map<String, Object> sendOaData = new HashMap<>();

                    // 门店收款OA模板
                    String oaTemplateCode = citySetting.get("storeReceiveTpl").toString();
                    // 模板编号
                    sendOaData.put("templateCode", oaTemplateCode);
                    // 发起者的登录名（登录协同的登录名）
                    sendOaData.put("senderLoginName", centerSetting.getSendUserCode());
                    // 协同的标题
                    sendOaData.put("subject", "保证金调整单");

                    //门店信息xml
                    List<StoreReceiveDtl> dtlList = storeReceiveService.getDtlList(receive.getId());
                    StringBuilder sb = new StringBuilder();
                    if (dtlList != null && dtlList.size() > 0) {
                        for (StoreReceiveDtl dtl : dtlList) {
                            sb.append("<row>" + "<column name=\"收入类别\"><value>" + defaultPayoutName + "</value></column>"
                                    + "<column name=\"收入类别编码\"><value>" + defaultPayoutId + "</value></column>"
                                    + "<column name=\"供应商\"><value>" + dtl.getProviderName() + "</value></column>"
                                    + "<column name=\"供应商编码\"><value>" + dtl.getProviderCode() + "</value></column>"
                                    + "<column name=\"收款金额\"><value>" + dtl.getAmount().setScale(2) + "</value></column>"
                                    + "<column name=\"门店编号\"><value>" + dtl.getStoreNo() + "</value></column>"
                                    + "<column name=\"门店名称\"><value>" + dtl.getStoreName() + "</value></column>"
                                    + "</row>");
                        }
                    }

                    Date dateRecorded = new Date();
                    ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_RECEIVE_OANO");
                    String oaNo = null;
                    if (data != null && data.getReturnCode().equals("200")) {
                        oaNo = data.getReturnData();
                    } else {
                        throw new NullPointerException("根据seq_type: TYPE_RECEIVE_OANO 获取oaNo为空");
                    }
                    // 组装向OA发送的参数信息
                    String dataXml =
                            "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                                    + "<summary id=\"-8896389543745008481\" name=\"formmain_3960\"/>" + "<definitions/>" + "<values>"
                                    + "<column name=\"编码\"><value>"
                                    + oaNo
                                    + "</value></column>"
                                    + "<column name=\"核算主体\"><value>"
                                    + receive.getAccountProject()
                                    + "</value></column>"
                                    + "<column name=\"核算主体编码\"><value>"
                                    + receive.getAccountProjectCode()
                                    + "</value></column>"
                                    + "<column name=\"考核主体\"><value>"
                                    + receive.getCheckBodyName()
                                    + "</value></column>"
                                    + "<column name=\"考核主体编码\"><value>"
                                    + receive.getCheckBodyId()
                                    + "</value></column>"
                                    + "<column name=\"成本中心\"><value>"
                                    + receive.getCostName()
                                    + "</value></column>"
                                    + "<column name=\"成本中心编码\"><value>"
                                    + receive.getCostCode()
                                    + "</value></column>"
                                    + "<column name=\"收款银行\"><value>"
                                    + receive.getOaBankName()
                                    + "</value></column>"
                                    + "<column name=\"收款银行编码\"><value>"
                                    + receive.getOaBankId()
                                    + "</value></column>"
                                    + "<column name=\"入账日期\"><value>"
                                    + DateUtil.fmtDate(dateRecorded, "yyyy-MM-dd")
                                    + "</value></column>"
                                    + "<column name=\"合同编号\"><value>"
                                    + ""
                                    + "</value></column>"
                                    + "<column name=\"收款方式\"><value>"
                                    + "调整单"
                                    + "</value></column>"
                                    + "<column name=\"收款人\"><value>"
                                    + ""
                                    + "</value></column>"
                                    + "<column name=\"收款人工号\"><value>"
                                    + ""
                                    + "</value></column>"
                                    + "<column name=\"收款类型\"><value>"
                                    + receive.getFeeTypeNm()
                                    + "</value></column>"
                                    + "<column name=\"收款金额合计\"><value>"
                                    + "0.00"
                                    + "</value></column>"
                                    + "<column name=\"备注\"><value>"
                                    + "调整单"
                                    + "</value></column>"
                                    + "</values>"
                                    + "<subForms>"
                                    + "<subForm>"
                                    + "<definitions>"
                                    + "<column id=\"field0017\" type=\"0\" name=\"收入类别\" length=\"255\"/>"
                                    + "<column id=\"field0023\" type=\"0\" name=\"收入类别编码\" length=\"255\"/>"
                                    + "<column id=\"field0018\" type=\"0\" name=\"供应商\" length=\"255\"/>"
                                    + "<column id=\"field0019\" type=\"0\" name=\"供应商编码\" length=\"255\"/>"
                                    + "<column id=\"field0020\" type=\"0\" name=\"收款金额\" length=\"255\"/>"
                                    + "<column id=\"field0021\" type=\"0\" name=\"门店编号\" length=\"255\"/>"
                                    + "<column id=\"field0022\" type=\"0\" name=\"门店名称\" length=\"255\"/>"
                                    + "</definitions>"
                                    + "<values>"
                                    + sb.toString() + "</values>" + "</subForm>" + "</subForms>" + "</formExport>";

                    sendOaData.put("data", dataXml);
                    // 为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
                    sendOaData.put("state", "0");

                    // 取得REST动态客户机实例
                    CTPRestClient client = getClient();
                    String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");
                    //为登录验证后获取的身份令牌
                    sendOaData.put("token", token);

                    StoreReceive updateReceive = new StoreReceive();
                    updateReceive.setId(receive.getId());
                    updateReceive.setDateUpt(new Date());
                    updateReceive.setDateRecorded(dateRecorded);
                    updateReceive.setOaNo(oaNo);

                    //发起申请 返回FlowId
                    Long flowId = null;
                    try {
                        //提交OA
                        flowId = apiOaService.toOaAuth(sendOaData, oaTemplateCode);
                    } catch (Exception e) {
                        logger.error("open", "APITimerController", "toOaAuth", "", 0, "", "CRM定时提交OA收款调整单失败,编号:" + receive.getReceiveNo(), e);
                    }

                    //更新收款主表信息
                    if (flowId != null) {
                        updateReceive.setFlowId(flowId + "");
                        //已提交
                        updateReceive.setSubmitOaStatus(21203);
                        //审批中
                        updateReceive.setApproveStatus(21602);
                    } else {
                        updateReceive.setSubmitOaStatus(21204);
                    }
                    storeReceiveService.updateByPrimaryKeySelective(updateReceive);
                } catch (Exception e) {
                    logger.error("open", "APITimerController", "receiveAdjustToOa", "", -1, "", "CRM定时提交OA收款调整单失败", e);

                    StoreReceive exeUpt = new StoreReceive();
                    exeUpt.setId(receive.getId());
                    //提交失败
                    exeUpt.setSubmitOaStatus(21204);
                    storeReceiveService.updateByPrimaryKeySelective(exeUpt);
                }
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "receiveAdjustToOa", "", -1, "", "CRM定时提交OA收款调整单失败", e);
        }
        logger.info("定时获取待发的收款调整单数据 end，this time is" + new Date());
        return resultData.toString();
    }


    /**
     * 结佣定时推送房友
     */
    @RequestMapping(value = "/sendFangyou", method = RequestMethod.GET)
    public String sendFangyou() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        logger.info("CRM结佣定时推送房友 begin，this time is" + new Date());

        resultData = reportService.sendFangyou();

        resultData.setReturnMsg("结佣定时推送房友成功!");
        logger.info("结佣定时推送房友 end，this time is" + new Date());
        return resultData.toString();
    }

   /**
    * desc:合同信息推送房友
    * 2019年2月20日
    * author:zhenggang.Huang
    */
//    @RequestMapping(value = "/sendContractDataToFangyou", method = RequestMethod.POST)
//    public String sendContractDataToFangyou(@RequestBody SendContractDataToFy sendContractDataToFy) {
//    	//构建返回
//    	ResultData<?> resultData = new ResultData<>();
//
//    	logger.info("CRM公盘初始会员合同信息推送房友 begin，this time is" + new Date());
//
//    	resultData = reportService.sendContractDataToFangyou(sendContractDataToFy);
//
//    	resultData.setReturnMsg("CRM公盘初始会员合同定时推送房友成功!");
//    	logger.info("CRM公盘初始会员合同信息推送房友 end，this time is" + new Date());
//    	return resultData.toString();
//    }

    /**
     * 定时拉取【公盘合同审核状态】
     */
    @RequestMapping(value = "/gpContract", method = RequestMethod.GET)
    public String gpContractHandle() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        //公盘审核通过List
        List<String> passList = new ArrayList<>();
        boolean result;

        logger.info("CRM定时获取公盘合同审核状态 begin，this time is" + new Date());

        try {
            // OA审批结果集取得
            List<OaGpContractReturn> oaResultlst = getOAGpContractReturn();
            if (null == oaResultlst || oaResultlst.isEmpty()) {
                resultData.setReturnMsg("没有OA审批结果信息!");
                return resultData.toString();
            }
            //3、更新合同状态
            for (OaGpContractReturn keyValue : oaResultlst) {
                GpContract gpContract = new GpContract();
                gpContract.setContractStatus(keyValue.getContractStatus());
                gpContract.setFlowId(keyValue.getFlowId());
                if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(keyValue.getContractStatus())) {
                    // 审核通过时间
                    gpContract.setPerformDate(keyValue.getOAPerformDate());
                }
                gpContract.setDateUpdate(new Date());
                //合同状态判断
                GpContract dbContract = gpContractService.getByFlowId(keyValue.getFlowId());
                if (dbContract == null) {
                    continue;
                }
                int contractStatus = dbContract.getContractStatus();
                if (contractStatus != DictionaryConstants.CONTRACT_STATUS_AUDITING) {//合同状态不在审核中
                    logger.info("OA审批结果处理时 该公盘合同不处于审核中状态 OA单号：[" + keyValue.getFlowId() + "]");
                    continue;
                }
                try {// 更新合同状态
                    gpContractService.updateContractStatusByFlowId(gpContract);

                    //审核通过且保证金为0，更新保证金状态
                    if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(keyValue.getContractStatus())
                            && dbContract.getDepositFee().compareTo(BigDecimal.ZERO) == 0) {
                        MovementDeposit movementDeposit = new MovementDeposit();
                        movementDeposit.setGpContractNo(dbContract.getGpContractNo());
                        movementDeposit.setTransferAccount(BigDecimal.ZERO);
                        movementDeposit.setPastTrialDate(keyValue.getOAPerformDate());
                        movementDeposit.setDateCreate(new Date());
                        movementDeposit.setDateUpdate(new Date());
                        movementDeposit.setDelFlag(false);
                        movementDepositService.insert(movementDeposit);
                    }
                } catch (Exception e) {
                    logger.error("open", "APITimerController", "gpContractService.updateContractStatusByFlowId", "", -1, "", "更新公盘合同状态失败!", e);
                    continue;
                }

                // 已处理OA审批结果更新
                keyValue.setHasDeal(1);    // 已处理OA审批结果
                keyValue.setDateUpdate(new Date());
                updateOAGpContractReturn(keyValue);

                //审核不通过 （ 10404）
                if (DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS.equals(keyValue.getContractStatus())) {
                    continue;
                } else if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(keyValue.getContractStatus())) {
                    // CRM草签合同向OP发起待开通房友账号接口
                    try {
                        String url = SystemParam.getWebConfigValue("opShareContract");
                        Company com = companyMapper.getById(dbContract.getCompanyId());
                        List<StoreDto> storeDtoList = gpContractMapper.selectStoreByContractId(dbContract.getId());
                        OpGpContractDto opGpContractDto = new OpGpContractDto();
                        opGpContractDto.setCreditCode(dbContract.getRegisterId());
                        if (null != com) {
                            opGpContractDto.setCompanyNo(com.getCompanyNo());
                            opGpContractDto.setContractNo(dbContract.getGpContractNo());
                        }
                        if (null != storeDtoList && storeDtoList.size() > 0) {
                            List<StoreOpGpDto> storeGpList = new ArrayList<>();
                            for (StoreDto storeDto : storeDtoList) {
                                StoreOpGpDto store = new StoreOpGpDto();
                                store.setStoreNo(storeDto.getStoreNo());
                                store.setStoreName(storeDto.getName());
                                store.setStoreAddress(storeDto.getAddressDetail());

                                store.setCityNo(storeDto.getCityNo());
                                store.setCityName(storeDto.getCityName());
                                store.setDistrictNo(storeDto.getDistrictNo());
                                store.setDistrictName(storeDto.getDistrictName());

                                storeGpList.add(store);
                            }
                            opGpContractDto.setStoreList(storeGpList);
                        }
                        //判断公盘保证金是否为0
                        GpContract gpContract1 = gpContractMapper.selectByGpContractNo(keyValue.getGpContractNo());
                        if(gpContract1.getDepositFee().compareTo(BigDecimal.ZERO)==0){
                            //更新公盘合同门店关系表的contractType字段
                            gpContractService.updateTypeByGpContractNo(keyValue.getGpContractNo());
                        }
                        //jsonString设值
                        String jsonData = JsonUtil.parseToJson(opGpContractDto);
                        String opResult = HttpClientUtil.jsonPostFangyou(url, jsonData);
                        logger.error("GpContract", "APITimerController", "gpContracrtHandle", jsonData, null, null, "CRM公盘草签合同向18 OP发起待开通房友账号接口成功！Company：" + com.getCompanyNo() + "  opResult: " + opResult, null);
                        logger.info("CRM公盘草签合同向18 OP发起待开通房友账号接口成功！jsonData：" + jsonData + "  opResult: " + opResult);
                    } catch (Exception e) {
                        logger.error("GpContract", "APITimerController", "gpContracrtHandle", null, null, null, "CRM公盘草签合同向OP发起待开通房友账号接口失败！CompanyId：" + dbContract.getCompanyId(), e);
                    }
                }
                //更新门店资质等级和门店业务类型
                //updateGpStore(keyValue.getFlowId());

                passList.add(keyValue.getFlowId());
                passList.clear();
            }

        } catch (Exception e) {
            logger.error("open", "APITimerController", "gpContracrtHandle", "", -1, "", "CRM定时获取公盘合同审核状态失败", e);
        }
        resultData.setReturnMsg("更新公盘合同信息成功!");
        logger.info("CRM定时获取公盘合同审核状态 end，this time is" + new Date());
        return resultData.toString();
    }
    /**
     * 定时拉取【公盘合同终止审核状态】
     */
    @RequestMapping(value = "/gpContractChange", method = RequestMethod.GET)
    public String gpContractChangeHandle() {
    	//构建返回
    	ResultData<?> resultData = new ResultData<>();
    	//公盘审核通过List
    	List<String> passList = new ArrayList<>();
    	boolean result;
    	logger.info("CRM定时获取公盘合同终止审核状态 begin，this time is" + new Date());

    	try {
    		// OA审批结果集取得
    		List<OaGpContractChangeReturn> oaResultlst = getOAGpContractChangeReturn();
    		if (null == oaResultlst || oaResultlst.isEmpty()) {
    			resultData.setReturnMsg("没有OA审批结果信息!");
    			return resultData.toString();
    		}
    		//3、更新合同状态
    		for (OaGpContractChangeReturn keyValue : oaResultlst) {
    			String flowId = keyValue.getFlowId();
    			//根据flowId查询公盘合同下所有门店
    			GpContractChange dto = gpContractChangeMapper.getContractChangeByFlowId(flowId);
    			if(dto==null){
    				continue;
    			}
    			Integer oaApproveState=keyValue.getApproveStatus();
    			Integer gpContractId = dto.getGpContractId();

    			List<GpContractChangeStore> gpContractChangeStoreList  = gpContractChangeStoreMapper.getGpContractStopStoreInfoById(dto.getId());

    			GpContractChange gpContractChange = new GpContractChange();

    			gpContractChange.setFlowId(flowId);
    			if (1==oaApproveState.intValue()){
    				//审批通过
    				gpContractChange.setApproveState(2);
    				// 审核通过时间
    				gpContractChange.setApprovePassDate(keyValue.getApproveDate());
    			}else{
    				//审批不通过
    				gpContractChange.setApproveState(3);
    			}

    			gpContractChange.setDateUpt(new Date());
    			//合同状态判断
    			GpContractChange dbChange = gpContractService.getContractChangeByFlowId(keyValue.getFlowId());
    			if (dbChange == null) {
    				continue;
    			}
    			try {// 更新合同状态
    				gpContractService.updateContractChangeStatusByFlowId(gpContractChange);
    			} catch (Exception e) {
    				logger.error("open", "APITimerController", "gpContractService.updateContractChangeStatusByFlowId", "", -1, "", "更新公盘合同终止申请状态失败!", e);
    				continue;
    			}

    			// 已处理OA审批结果更新
    			keyValue.setHasDeal(1);    // 已处理OA审批结果
    			keyValue.setDateUpdate(new Date());
    			updateOAGpContractChangeReturn(keyValue);

    			//审核不通过
    			if (3==oaApproveState.intValue()) {
    				if(gpContractChangeStoreList != null && gpContractChangeStoreList.size()>0){
    					for (GpContractChangeStore gpContractChangeStore : gpContractChangeStoreList) {
	           			 //根据门店id跟新公盘合同表中的状态
	    					Integer integer  = 0 ;
	    					Map<String, Object> paramMap = new HashMap<>();
	    					paramMap.put("storeId", gpContractChangeStore.getStoreId());
	    					paramMap.put("gpContractId", gpContractId);
	    					paramMap.put("storeState", integer);
	    					gpContractChangeMapper.updateGpContractStoreByStoreId(paramMap);
	           		 	}
    				}
    				continue;
    			} else if (1==oaApproveState.intValue()) {//审核通过
    				// 公盘合同下所有门店审核终止
    				if(gpContractChangeStoreList != null && gpContractChangeStoreList.size()>0){
    					for (GpContractChangeStore gpContractChangeStore : gpContractChangeStoreList) {
	           			 //根据门店id跟新公盘合同表中的状态
	    					Integer integer  = 2 ;
	    					Map<String, Object> paramMap = new HashMap<>();
	    					paramMap.put("storeId", gpContractChangeStore.getStoreId());
	    					paramMap.put("gpContractId", gpContractId);
	    					paramMap.put("storeState", integer);
	    					paramMap.put("contractStopDate",keyValue.getApproveDate());
	    					gpContractChangeMapper.updateGpContractStoreByStoreId(paramMap);
	           		 	}
    				}
    				//根据公盘id查询其公盘合同门店数量
    				String c= gpContractChangeMapper.getNoStopStoreSum(gpContractId)+"";
    				if("0" .equals(c)) {
    					//公盘合同下的所有门店都已终止
    					//设置公盘为已终止
    					gpContractChangeMapper.updateGpContractStatus(gpContractId);
    					//设置门店表中的isGpSign的已签改为未签
    					List<Integer> stopedStoreIdlist = gpContractChangeMapper.getStopedStoreIdlist(gpContractId);
    					if(null != stopedStoreIdlist && stopedStoreIdlist.size() > 0) {
    						for (Integer storeId : stopedStoreIdlist) {
    							System.out.println(storeId);
    							gpContractChangeMapper.updateStoreIsGpSign(storeId);
    						}
    					}

    					//通知op
    					//gpContractChangeMapper.getStopGpContractInfo
    					try {/*
	    					String url = SystemParam.getWebConfigValue("opShareContract");
	    					Company com = companyMapper.getById(dbContract.getCompanyId());
	    					List<StoreDto> storeDtoList = gpContractMapper.selectStoreByContractId(dbContract.getId());
	    					OpGpContractDto opGpContractDto = new OpGpContractDto();
	    					opGpContractDto.setCreditCode(dbContract.getRegisterId());
	    					if (null != com) {
	    						opGpContractDto.setCompanyNo(com.getCompanyNo());
	    						opGpContractDto.setContractNo(dbContract.getGpContractNo());
	    					}
	    					if (null != storeDtoList && storeDtoList.size() > 0) {
	    						List<StoreOpGpDto> storeGpList = new ArrayList<>();
	    						for (StoreDto storeDto : storeDtoList) {
	    							StoreOpGpDto store = new StoreOpGpDto();
	    							store.setStoreNo(storeDto.getStoreNo());
	    							store.setStoreName(storeDto.getName());
	    							store.setStoreAddress(storeDto.getAddressDetail());
	    							storeGpList.add(store);
	    						}
	    						opGpContractDto.setStoreList(storeGpList);
	    					}
	    					//jsonString设值
	    					String jsonData = JsonUtil.parseToJson(opGpContractDto);
	    					String opResult = HttpClientUtil.jsonPostFangyou(url, jsonData);
	    					logger.error("GpContract", "APITimerController", "gpContracrtHandle", jsonData, null, null, "CRM公盘草签合同向18 OP发起待开通房友账号接口成功！Company：" + com.getCompanyNo() + "  opResult: " + opResult, null);
	    					logger.info("CRM公盘草签合同向18 OP发起待开通房友账号接口成功！jsonData：" + jsonData + "  opResult: " + opResult);
	    					*/} catch (Exception e) {
	    						//logger.error("GpContract", "APITimerController", "gpContracrtHandle", null, null, null, "CRM公盘草签合同向OP发起待开通房友账号接口失败！CompanyId：" + dbContract.getCompanyId(), e);
	    					}
    				}else {
    					//审核通过的将门店设置为未签
    					List<Integer> stopedStoreIdlist = gpContractChangeMapper.getPassStopStoreIdlistByGpContractId(gpContractId);
    					if(null != stopedStoreIdlist && stopedStoreIdlist.size() > 0) {
    						for (Integer storeId : stopedStoreIdlist) {
    							gpContractChangeMapper.updateStoreIsGpSign(storeId);
    						}
    					}
    				}
    			}
    			passList.add(keyValue.getFlowId());
    			passList.clear();
    		}

    	} catch (Exception e) {
    		logger.error("open", "APITimerController", "gpContractChangeHandle", "", -1, "", "CRM定时获取公盘合同终止审核状态失败", e);
    	}
    	resultData.setReturnMsg("更新公盘合同终止信息成功!");
    	logger.info("CRM定时获取公盘合同终止审核状态 end，this time is" + new Date());
    	return resultData.toString();
    }

    /**
     * 取得OA审批结果信息
     *
     * @return OA审批结果信息
     * @throws Exception
     */
    private List<OaGpContractReturn> getOAGpContractReturn() throws Exception {
        // 返回组装List
        List<OaGpContractReturn> oaResult = gpContractService.getOAGpContractReturn();
        return oaResult;
    }
    /**
     * 取得OA审批结果信息
     * @return OA审批结果信息
     * @throws Exception
     */
    private List<OaGpContractChangeReturn> getOAGpContractChangeReturn() throws Exception {
    	// 返回组装List
    	List<OaGpContractChangeReturn> oaResult = gpContractService.getOAGpContractChangeReturn();
    	return oaResult;
    }

    /**
     * 更新OA审批结果
     *
     * @param oAContractReturn
     * @return 更新数
     * @throws Exception
     */
    private Integer updateOAGpContractReturn(OaGpContractReturn oAContractReturn) throws Exception {
        // 返回组装List
        Integer cnt = 0;
        cnt = gpContractService.updateOAContractReturn(oAContractReturn);
        return cnt;
    }
    private Integer updateOAGpContractChangeReturn(OaGpContractChangeReturn oAContractReturn) throws Exception {
    	// 返回组装List
    	Integer cnt = 0;
    	cnt = gpContractService.updateOAContractChangeReturn(oAContractReturn);
    	return cnt;
    }

    /**
     * 跟新门店资质等级和门店业务类型
     *
     * @param flowId
     * @return
     */
    private void updateGpStore(String flowId) {
        try {
            List<GpContractStore> contractStoreList = gpContractService.getContractStoresByFlowId(flowId);
            for (GpContractStore contractStore : contractStoreList) {
                Store store = new Store();
                store.setStoreId(contractStore.getStoreId());
                /*store.setABTypeStore(contractStore.getAbTypeStore());
                store.setBTypeStore(contractStore.getbTypeStore());*/
                store.setBizType(22501);
                storeService.update(store);
            }
        } catch (Exception e) {
            logger.error("open", "APITimerController", "updateABTypeStore", "", -1, "", "跟新门店资质等级", e);
        }

    }

    /**
     * 新房联动有房补偿机制
     */
    @RequestMapping(value = "/retryYouFang", method = RequestMethod.GET)
    public String retryYouFang() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        logger.info("CRM新房联动有房补偿机制重试 begin，this time is" + new Date());

        yFInterfaceService.taskYFFailLog();

        resultData.setReturnMsg("定时补偿房友结束!");
        logger.info("定时补偿房友结束 end，this time is" + new Date());
        return resultData.toString();
    }

    /**
     * 总包数据处理
     * @return
     */
    @RequestMapping(value = "/handleTotalPackage",method = RequestMethod.GET)
    public String handleTotalPackage(){
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("总包数据处理 begin，this time is" + new Date());
        try {
            resultData = totalPackageService.handleTotalPackage();
        }catch (Exception e){
            logger.error("open", "APITimerController", "handleTotalPackage", "", -1, "", "总包数据处理失败", e);
            resultData.setFail("总包数据处理失败！");
            return resultData.toString();
        }
        logger.info("总包数据处理 end，this time is" + new Date());
        return resultData.toString();
    }

    @RequestMapping(value = "/handleRevenues",method = RequestMethod.GET)
    public String handleRevenues(){
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("总包应收收入数据处理 begin，this time is" + new Date());
        try {
            resultData = totalPackageService.handleRevenues();
        }catch (Exception e){
            logger.error("open", "APITimerController", "handleRevenues", "", -1, "", "总包应收收入数据处理失败", e);
            resultData.setFail("总包应收收入数据处理失败！");
            return resultData.toString();
        }
        logger.info("总包应收收入数据处理 end，this time is" + new Date());
        return resultData.toString();
    }

    /**
     * 总包应计实收
     * @return
     */
    @RequestMapping(value = "/handleYJSS",method = RequestMethod.GET)
    public String handleYJSS(){
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("总包应计实收数据处理 begin，this time is" + new Date());
        try {
            resultData = totalPackageService.handleYJSS();
        }catch (Exception e){
            logger.error("open", "APITimerController", "handleYJSS", "", -1, "", "总包应计实收数据处理失败", e);
            resultData.setFail("总包应计实收数据处理失败！");
            return resultData.toString();
        }
        logger.info("总包应计实收数据处理 end，this time is" + new Date());
        return resultData.toString();
    }
    /**
     * 定时拉取【公盘初始会员合同审核状态】
     */
    @RequestMapping(value = "/gpCsMemberContract", method = RequestMethod.GET)
    public String gpCsMemberContractHandle() {
    	//构建返回
    	ResultData<?> resultData = new ResultData<>();
    	//公盘审核通过List
    	List<String> passList = new ArrayList<>();
    	boolean result;
    	logger.info("CRM定时获取公盘初始会员合同审核状态 begin，this time is" + new Date());

    	try {
    		// OA审批结果集取得
    		List<OaGpCsMemberContractReturn> oaResultlst = getOaGpCsMemberContractReturn();
    		if (null == oaResultlst || oaResultlst.isEmpty()) {
    			resultData.setReturnMsg("没有OA审批结果信息!");
    			return resultData.toString();
    		}
    		//3、更新合同状态
    		for (OaGpCsMemberContractReturn keyValue : oaResultlst) {
    			String flowId = keyValue.getFlowId();
    			//根据flowId查询初始会员合同
    			GpCsMemberContract dto = gpCsMemberContractMapper.getCsMemberContractByFlowId(flowId);
    			if(dto==null){
    				continue;
    			}
    			Integer oaApproveState=keyValue.getApproveStatus();

    			GpCsMemberContract gpCsMemberContract = new GpCsMemberContract();

    			gpCsMemberContract.setFlowId(flowId);
    			if (1==oaApproveState.intValue()){
    				//审批通过
    				gpCsMemberContract.setApproveState(2);
    				// 审核通过时间
    				gpCsMemberContract.setApprovePassDate(keyValue.getApproveDate());
    			}else{
    				//审批不通过
    				gpCsMemberContract.setApproveState(3);
    			}

    			gpCsMemberContract.setDateUpt(new Date());
    			int count2 = 0;
    			try {// 更新初始会员合同状态
    				count2 = gpCsMemberContractService.updateGpCsMemberStatusByFlowId(gpCsMemberContract);
    			} catch (Exception e) {
    				logger.error("open", "APITimerController", "gpCsMemberContractService.updateGpCsMemberStatusByFlowId", "", -1, "", "更新公盘初始会员合同申请状态失败!", e);
    				continue;
    			}
    			if(1==oaApproveState.intValue() && count2 > 0){
    				try {
    					String url = SystemParam.getWebConfigValue("ophyContract") + "api/member/contract_sync";
    					String opResult = null;
    			    	SendContractDataToFy sendContractDataToFy = new SendContractDataToFy();

    			    	sendContractDataToFy.setContract_no(dto.getGpCsMemberContractNo());
    			    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			    	String dateString = formatter.format(gpCsMemberContract.getApprovePassDate());
    			    	sendContractDataToFy.setContract_audit_time(dateString);
    			    	sendContractDataToFy.setStatus(1);
    			    	sendContractDataToFy.setCompany_name(dto.getPartyB());
    			    	sendContractDataToFy.setAmount(dto.getCsMemberAmount());
    			    	sendContractDataToFy.setCompany_no(dto.getCompanyNo());

    			    	SendContractFangyouLog sendContractFangyouLog = new SendContractFangyouLog();
    			    	sendContractFangyouLog.setUserIdCreate(0);
    			    	sendContractFangyouLog.setTypeId("1");

    			    	String jsonData = JsonUtil.parseToJson(sendContractDataToFy);
    					opResult = HttpClientUtil.jsonPostFangyou(url,jsonData);
    					sendContractFangyouLog.setReqParamJson(jsonData);
    					sendContractFangyouLog.setRspParamJson(opResult);
    					createSendContractFangyouLog(sendContractFangyouLog);
    					Map<String,Object> result2 = JsonUtil.parseToObject(opResult,Map.class);
    					//returnMsg = result2.get("returnMsg").toString();
    					//resultData.setReturnMsg(returnMsg);
    					logger.info("推送合同信息到房友,返回码："+result2.get("returnCode").toString()+",入参："+jsonData+",响应参数："+opResult);
    					logger.error("open", "APITimerController", "gpCsMemberContractHandle", jsonData, null, null, "CRM公盘草签初始会员合同向OP发起初始会员合同信息同步接口失败！GpCsMemberContractNo：" + dto.getGpCsMemberContractNo() + "  opResult: " + opResult, null);

    				} catch (Exception e) {
    					logger.error("open", "APITimerController", "gpCsMemberContractHandle", null, null, null, "CRM公盘草签初始会员合同向OP发起初始会员合同信息同步接口失败！", e);
    				}
    			}

    			// 已处理OA审批结果更新
    			keyValue.setHasDeal(1);    // 已处理OA审批结果
    			keyValue.setDateUpdate(new Date());
    			keyValue.setOaNo(dto.getOaNo());
    			updateOaGpCsMemberContract(keyValue);
    			passList.add(keyValue.getFlowId());
    			passList.clear();
    		}

    	} catch (Exception e) {
    		logger.error("open", "APITimerController", "gpContractChangeHandle", "", -1, "", "CRM定时获取公盘初始会员合同审核状态失败", e);
    	}
    	resultData.setReturnMsg("更新公盘初始会员合同信息成功!");
    	logger.info("CRM定时获取公盘初始会员合同审核状态 end，this time is" + new Date());
    	return resultData.toString();
    }
    /**
     * 取得OA审批结果信息
     * @return OA审批结果信息
     */
    private List<OaGpCsMemberContractReturn> getOaGpCsMemberContractReturn() throws Exception {
    	// 返回组装List
    	List<OaGpCsMemberContractReturn> oaResult = gpCsMemberContractService.getOaGpCsMemberContractReturn();
    	return oaResult;
    }
    private Integer updateOaGpCsMemberContract(OaGpCsMemberContractReturn oaGpCsMember) throws Exception {
    	// 返回组装List
    	Integer cnt = 0;
    	cnt = gpCsMemberContractService.updateOaGpCsMemberContract(oaGpCsMember);
    	return cnt;
    }
    private int createSendContractFangyouLog (SendContractFangyouLog sendContractFangyouLog) throws Exception {
    	// 返回组装List
    	int cnt = gpCsMemberContractService.createSendContractFangyouLog(sendContractFangyouLog);
    	return cnt;
    }


    @RequestMapping(value = "/frameContractAutoToOA", method = RequestMethod.GET)
    public String frameContracrtAutoOaHandle() {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        logger.info("CRM定时自动发送联动框架合同 begin，this time is" + new Date());

        try {
            // 获取需要自动发送的框架合同
        	frameContractService.AutoToOA();

        } catch (Exception e) {
            logger.error("open", "APITimerController", "frameContractAutoToOA", "", -1, "", "CRM定时自动发送联动框架合同", e);
        }
        resultData.setReturnMsg("更新框架合同信息成功!");
        logger.info("CRM定时自动发送联动框架合同 begin，this time is" + new Date());
        return resultData.toString();
    }


    /**
     * 定时处理 OA审批联动请款的处理
     * @return
     */
    @RequestMapping(value = "/handleCashBill" , method = RequestMethod.GET)
    public String handleCashBill(){

        //构建返回
        ResultData<?> resultData = new ResultData<>();
        logger.info("联动请款 begin，this time is" + new Date());
        try {
            resultData = cashBillService.handleCashBill();
        }catch (Exception e){
            logger.error("open", "APITimerController", "handleCashBill", "", -1, "", "联动请款处理失败", e);
            resultData.setFail("联动请款处理失败！");
            return resultData.toString();
        }
        logger.info("联动请款 end，this time is" + new Date());
        return resultData.toString();

    }
}
