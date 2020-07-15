package cn.com.eju.deal.service.signContract;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contract.dao.ContractChangeStoreMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.contract.service.OPCompanyHttpService;
import cn.com.eju.deal.core.support.Constant;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dataSource.DbcontextHolder;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDepositDto;
//import cn.com.eju.deal.dubbo.op.api.IOPCompanyService;
import cn.com.eju.deal.fangyou.dao.FangyouAccountMapper;
import cn.com.eju.deal.fangyou.model.FangyouAccountLog;
import cn.com.eju.deal.gpContract.dao.GpContractMapper;
import cn.com.eju.deal.mapper.followMap.FollowMapMapper;
import cn.com.eju.deal.mapper.signContract.SignContractMapper;
import cn.com.eju.deal.mapper.sweepStreets.SweepStreetsMapper;
import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.model.signContract.ContractStoreNewDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.open.dao.ContractRelatePersonMapper;
import cn.com.eju.deal.open.model.ContractRelatePerson;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.service.StoreDepositService;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.PostMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.Post;
import cn.com.eju.deal.user.model.User;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xu on 2017/5/31.
 */
@Service("signContractService")
public class SignContractService {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource(name = "gpContractMapper")
    private GpContractMapper gpContractMapper;
    @Resource
    private SignContractMapper signContractMapper;
    @Resource
    private SweepStreetsMapper sweepStreetsMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource
    private FangyouAccountMapper fangyouAccountMapper;
    @Resource(name = "groupMapper")
    private GroupMapper groupMapper;
    @Resource
    private ContractRelatePersonMapper contractRelatePersonMapper;
    @Resource
    private CitySettingMapper citySettingMapper;
    @Resource(name = "achievementService")
    private AchievementService achievementService;
/*    @Resource
    private IOPCompanyService opCompanyService;*/
    @Resource(name = "userMapper")
    private UserMapper userMapper;

    @Resource(name = "postMapper")
    private PostMapper postMapper;

    @Resource
    private FollowMapMapper followMapMapper;

    @Resource
    private ISeqNoAPI seqNoAPI;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private ContractStoreMapper contractStoreMapper;
    @Resource
    private ContractChangeStoreMapper contractChangeStoreMapper;
    @Resource
    private StoreDepositService storeDepositService;

    @Resource
    private ContactsMapper contactsMapper;

    @Resource
    private OPCompanyHttpService oPCompanyHttpService;

    public ResultData<List<StoreNewDto>> getStoreList(StoreNewDto dto) throws Exception {
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list = signContractMapper.getStoreList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    public ResultData<CompanyNewDto> getCompanyByStoreId(StoreNewDto dto) throws Exception {
        ResultData<CompanyNewDto> resultData = new ResultData<CompanyNewDto>();
        int count = signContractMapper.checkCompanyStore(dto);
        if (count > 1) {
            resultData.setFail("该门店对应公司存在2家或以上，请联系中心负责人处理");
            return resultData;
        }
        //modify by haidan 2019-09-17 校验公司和门店所属城市是否一致
        int acCount = signContractMapper.checkAcCityNoForStore(dto);
        if (acCount > 0) {
            resultData.setFail("该门店所属城市对应公司的所属城市不一致，不能新签！");
            return resultData;
        }
        CompanyNewDto companyNewDto = signContractMapper.getCompanyByStoreId(dto);
        resultData.setReturnData(companyNewDto);
        return resultData;
    }

    public ResultData<List<CompanyNewDto>> getCompanyList(CompanyNewDto dto) throws Exception {
        ResultData<List<CompanyNewDto>> resultData = new ResultData<List<CompanyNewDto>>();
        List<CompanyNewDto> list = signContractMapper.getCompanyList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    public ResultData<CompanyNewDto> getCompanyById(CompanyNewDto dto) throws Exception {
        ResultData<CompanyNewDto> resultData = new ResultData<CompanyNewDto>();

        int count = signContractMapper.checkCompanyStoreForCompany(dto);
        if (count <= 0) {
            resultData.setFail("当前经纪公司没有绑定门店，请绑定后再签约！");
            return resultData;
        }

        CompanyNewDto companyNewDto = signContractMapper.getCompanyById(dto);
        resultData.setReturnData(companyNewDto);
        return resultData;
    }

    /**
     * 验证选择的门店是否能签约
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData checkContract(ContractNewDto dto) throws Exception {
        ResultData resultData = new ResultData();

        //判断保证金、违约金是否有小数
        if (dto != null) {
            if (Math.abs(dto.getDepositFee() - Math.round(dto.getDepositFee())) > 0
                    || Math.abs(dto.getPenaltyFee() - Math.round(dto.getPenaltyFee())) > 0) {
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg("保证金或违约金必须为整数！");
                return resultData;
            }
        }

        //获取公司信息
        CompanyNewDto companyNewDto = new CompanyNewDto();
        companyNewDto.setCompanyId(dto.getCompanyId());
        companyNewDto = sweepStreetsMapper.getCompanyById(companyNewDto);
        if (companyNewDto != null) {
            dto.setRegistrId(companyNewDto.getBusinessLicenseNo());
            dto.setLealPerson(companyNewDto.getLegalPerson());
            dto.setPartyB(companyNewDto.getCompanyName());
            dto.setPartyBCityNo(companyNewDto.getCityNo());
            dto.setPartyBDistrictNo(companyNewDto.getDistrictNo());
            dto.setPartyBAddress(companyNewDto.getAddress());
        } else {
            resultData.setFail("签约失败，公司信息不存在");
            return resultData;
        }
        //是否乙转甲（ 20201:是 20202:否）
        if (null != dto.getContractB2A() && "20201".equals(dto.getContractB2A())) {
            // 根据storeId查询合同变更 没有变更记录不让草签
            if (null != dto.getStoreIdStr() && !dto.getStoreIdStr().isEmpty()) {
                // 乙转甲时 只有一个门店
                Boolean hasChg = contractChangeStoreMapper.getChgByStoreId(Integer.parseInt(dto.getStoreIdStr()));
                if (!hasChg) {
                    resultData.setReturnData(0);
                    resultData.setFail();
                    resultData.setReturnMsg("必须要有乙类转甲类（签约主体）变更记录才能做乙类转甲类的合同新签！");
                    return resultData;
                }
            }
        }

        //验证签约门店是否可签合同
        ResultData rs = checkStoreLock(dto);
        if (!"200".equals(rs.getReturnCode())) {
            return rs;
        }
        return resultData;
    }

    /**
     * 验证协议书编号是否重复
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData checkAgreementNo(ContractNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int count = signContractMapper.checkAgreementNo(dto.getAgreementNo());
        if (count > 0) {
            resultData.setFail("系统中已存在相同的协议书编号，请重新填写！");
        }
        return resultData;
    }

    /**
     * 1.新增合同为草签
     * 2.建立合同门店关系
     * 3.更新门店签约类型，只有B合同更新为已锁定
     * 4.更新公司为已签约，并更新协议书类型
     * 5.更新图片关联表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData addContract(ContractNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        CompanyNewDto companyNewDto = new CompanyNewDto();
        if (dto.getContractId() == 0) {
            //获取公司信息
            companyNewDto.setCompanyId(dto.getCompanyId());
            companyNewDto = sweepStreetsMapper.getCompanyById(companyNewDto);
            if (companyNewDto != null) {
                dto.setRegistrId(companyNewDto.getBusinessLicenseNo());
                dto.setLealPerson(companyNewDto.getLegalPerson());
                dto.setPartyB(companyNewDto.getCompanyName());
                dto.setPartyBCityNo(companyNewDto.getCityNo());
                dto.setPartyBDistrictNo(companyNewDto.getDistrictNo());
                dto.setPartyBAddress(companyNewDto.getAddress());
            } else {
                resultData.setFail("签约失败，公司信息不存在");
                return resultData;
            }
        }

        //是否乙转甲（ 20201:是 20202:否）
        if (null != dto.getContractB2A() && "20201".equals(dto.getContractB2A())) {
            // 根据storeId查询合同变更 没有变更记录不让草签
            if (null != dto.getStoreIdStr() && !dto.getStoreIdStr().isEmpty()) {
                // 乙转甲时 只有一个门店
                Boolean hasChg = contractChangeStoreMapper.getChgByStoreId(Integer.parseInt(dto.getStoreIdStr()));
                if (!hasChg) {
                    resultData.setReturnData(0);
                    resultData.setFail();
                    resultData.setReturnMsg("必须要有乙类转甲类（签约主体）变更记录才能做乙类转甲类的合同新签！");
                    return resultData;
                }
            }
        }

        //门店锁定状态(0:未锁定、1:锁定)
        Integer storeStatus = 0;
        //只有B类合同才锁定门店
        if (DictionaryConstants.CONTRACT_TPYE_B == Integer.parseInt(dto.getContractType()) || DictionaryConstants.COOP_TYPE_A_2_B == Integer.parseInt(dto.getContractType())) {
            storeStatus = 1;
        }
        //获取合同门店关系信息 — 包含联系人信息
        if (dto.getStoreListJsonStr() != null && !"".equals(dto.getStoreListJsonStr())) {
            List<ContractStoreNewDto> list = JSON.parseArray(dto.getStoreListJsonStr(), ContractStoreNewDto.class);
            dto.setContractStoreNewDtoList(list);
        }
        List<ContractStoreNewDto> contractStoreNewDtoList = dto.getContractStoreNewDtoList();

        if (dto.getCenterId() != null) {
            String cityNoString = groupMapper.getCityNoByGroupId(dto.getCenterId());
            if (StringUtil.isNotEmpty(cityNoString)) {
                dto.setAcCityNo(cityNoString);

                //根据业绩城市编号查询其核算主体
                List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
                if (accountProjectList.size() > 0 && null != accountProjectList) {
                    String accountProject = accountProjectList.get(0).getAccountProject();
                    String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
                    //合同新增时候保存其核算主体及其编号，核算主体及其编号
                    if (accountProject != "") {
                        dto.setAccountProject(accountProject);
                    }
                    if (accountProjectNo != "") {
                        dto.setAccountProjectNo(accountProjectNo);
                    }
                }
            }
        }

        //设置门店个数
        dto.setStoreNum(contractStoreNewDtoList.size());
        //设置当前签约人作为业绩维护人
        dto.setExpandingPersonnel(dto.getUserName());//业绩归属人姓名
        dto.setExpandingPersonnelId(dto.getUserCode());//业绩归属人编号

        // 业绩归属人
        String expdPsonId = dto.getExpandingPersonnelId();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userCode", expdPsonId);
        param.put(Constant.SQL_UN_CONTROL, false);
        Group group = groupMapper.selectByUserCode(param);
        // 业绩归属人部门
        String pfmcAtbtDepmt = group.getOrgIdStr();
        dto.setPfmcAtbtDepmt(pfmcAtbtDepmt);

        if (dto.getContractId() == 0 && StringUtil.isEmpty(dto.getOriginalContractNo())) {
            //验证签约门店是否可签合同，修改时不需要验证
            ResultData rs = checkStoreLock(dto);
            if (!"200".equals(rs.getReturnCode())) {
                return rs;
            }
        }

        //Add 2017/04/10 cning 合同有效标识追加 Start
        Date date = new Date();
        long nd = date.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        long ds = 0;
        long de = 0;
        try {
            ds = format.parse(dto.getDateLifeStart()).getTime();
            de = format.parse(dto.getDateLifeEnd()).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (nd >= ds && ds <= de) {
            dto.setValid(DictionaryConstants.VALID_TYPE_SX);//有效标识
        } else if (nd > de) {
            dto.setValid(DictionaryConstants.VALID_TYPE_GQ);//过期标识
        } else {
            dto.setValid(DictionaryConstants.VALID_TYPE_DSX);//待生效标识
        }


        //协议书编号重复check
        ResultData<List<Contract>> resultDataList = this.getContractByAgreementNo(dto.getAgreementNo());
        List<Contract> list = resultDataList.getReturnData();
        if (list.size() > 0) {
            String strContractNo = "";
            int flag = 0;
            for (Contract info : list) {
                if (info.getContractNo().equals(dto.getContractNo())) {
                    continue;
                }
                //审核中或审核通过
                if (10402 == info.getContractStatus() || 10403 == info.getContractStatus()) {
                    strContractNo += info.getContractNo() + ",";
                    flag = 1;
                }
                //草签或审核未通过
                if (10401 == info.getContractStatus() || 10404 == info.getContractStatus()) {
                    strContractNo += info.getContractNo() + ",";
                    flag = 2;
                }
            }

            if (strContractNo.length() > 0) {
                strContractNo = strContractNo.substring(0, strContractNo.length() - 1);
            }

            if (flag == 1) {
                String strMsg = "系统中存在相同协议书编号的合同，合同编号为" + strContractNo + "。请勿重复提交！";
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg(strMsg);
                return resultData;
            } else if (flag == 2) {
                String strMsg = "系统中存在相同协议书编号的草签合同，合同编号为" + strContractNo + "。如有必要请作废后再操作，请勿重复提交！";
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg(strMsg);
                return resultData;
            }
        }

        if (dto.getContractId() == 0) {
            //1.新增合同为草签
            if (dto.getOriginalContractNo() != null) {
                dto.setOriginalContractdistinction("18602");
            } else {
                dto.setOriginalContractdistinction("18601");
            }
            int count = signContractMapper.addContract(dto);
            if (count >= 1) {
                for (ContractStoreNewDto contractStoreNewDto : contractStoreNewDtoList) {
                    Integer storeId = Integer.parseInt(contractStoreNewDto.getStoreId());
                    StoreNewDto storeNewDto = new StoreNewDto();
                    storeNewDto.setStoreId(storeId);
                    storeNewDto = sweepStreetsMapper.getStoreById(storeNewDto);
                    contractStoreNewDto.setContractId(dto.getContractId());
                    contractStoreNewDto.setAddressDetail(storeNewDto.getAddressDetail());
                    contractStoreNewDto.setStoreStatus(storeStatus);
                    contractStoreNewDto.setContractType(dto.getContractType());

                    //判断是否填写联系人
                    if (null != contractStoreNewDto.getContactsName() && !contractStoreNewDto.getContactsName().isEmpty()) {
                        //创建门店联系人
                        ContactsDto contactsDto = new ContactsDto();
                        ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
                        String contactsNo = back.getReturnData();
                        contactsDto.setContactsNo(contactsNo);
                        contactsDto.setStoreId(Integer.parseInt(contractStoreNewDto.getStoreId()));
                        contactsDto.setContactsName(contractStoreNewDto.getContactsName());
                        contactsDto.setContactsPhone(contractStoreNewDto.getContactsPhone());
                        contactsDto.setUserCreate(dto.getUserCreate().toString());
                        //判断联系人是否重复
                        int result = followMapMapper.checkContacts(contactsDto);
                        if (result > 0) {
                            //如果重复 忽略
                        } else {
                            //插入门店联系人信息
                            followMapMapper.addContacts(contactsDto);
                        }
                        //更新门店负责人信息
                        signContractMapper.updateStoreManagerInfo(contactsDto);
                    }
                    //更新房友账号
                    //设置门店的房友账号并插入日志
                    FangyouAccountLog fangyouAccountLog = new FangyouAccountLog();
                    fangyouAccountLog.setStoreId(storeNewDto.getStoreId());
                    fangyouAccountLog.setStoreNo(storeNewDto.getStoreNo());
                    fangyouAccountLog.setDateCreate(new Date());
                    String oldFyAcount = storeNewDto.getFyAccount();
                    String companyNo = companyNewDto.getCompanyNo();
                    if (null == oldFyAcount || "".equals(oldFyAcount)) {
                        String description = "门店关联房友账号" + companyNo;
                        fangyouAccountLog.setUserIdCreate(null);
                        fangyouAccountLog.setUserCode(null);
                        fangyouAccountLog.setUserName("Admin");
                        fangyouAccountLog.setDescription(description);
                        fangyouAccountLog.setNewFyAccount(companyNo);
                        fangyouAccountMapper.insertfangyouAccountLog(fangyouAccountLog);
                        storeNewDto.setFyAccount(companyNo);
                    } else {
                        //原来已有账号和公司编号相同不做处理
                        if (!oldFyAcount.equals(companyNo)) {
                            String description = "门店关联房友账号" + oldFyAcount + "-->" + companyNo;
                            fangyouAccountLog.setUserIdCreate(dto.getUserCreate());
                            fangyouAccountLog.setUserCode(null);
                            fangyouAccountLog.setUserName("Admin");
                            fangyouAccountLog.setDescription(description);
                            fangyouAccountLog.setNewFyAccount(companyNo);
                            fangyouAccountLog.setOldFyAccount(oldFyAcount);
                            fangyouAccountMapper.insertfangyouAccountLog(fangyouAccountLog);
                            storeNewDto.setFyAccount(companyNo);
                        }
                    }
                    if (null != storeNewDto.getStoreId() && !"".equals(storeNewDto.getStoreId() + "")) {
                        Map<String, Object> fyAccountparam = new HashMap<String, Object>();
                        fyAccountparam.put("storeId", storeNewDto.getStoreId());
                        fyAccountparam.put("fyAccount", storeNewDto.getFyAccount());
                        storeMapper.updateWxStorefyAccount(fyAccountparam);
                    }

                }
                //2.建立合同门店关系
                signContractMapper.addContractStore(dto);
                //2.1创建或更新门店保证金
                for (ContractStoreNewDto dbstore : contractStoreNewDtoList) {
                    StoreDepositDto deposit = new StoreDepositDto();
                    deposit.setStoreNo(dbstore.getStoreNo());
                    deposit.setTmpAmount(new BigDecimal(dto.getDepositFee()));
                    deposit.setUserIdCreate(dto.getUserCreate());
                    storeDepositService.createOrUpdate(deposit);
                }
                //3.更新门店签约类型，只有B合同更新为已锁定
                signContractMapper.updateStoreStatus(dto);

                //4.更新公司为已签约，并更新协议书类型
                companyNewDto.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y + "");
                companyNewDto.setContractType(dto.getContractType());
                signContractMapper.updateCompanyStatus(companyNewDto);

                //5.更新图片关联表
                String fileRecordMainIds = dto.getFileRecordMainIds();
                if (fileRecordMainIds != null && !"".equals(fileRecordMainIds)) {
                    String[] fileRecordMainIdArray = fileRecordMainIds.split(",");
                    for (int i = 0; i < fileRecordMainIdArray.length; i++) {
                        String fileRecordMainId = fileRecordMainIdArray[i];

                        if (fileRecordMainId != null && !"".equals(fileRecordMainId) && !"undefined".equals(fileRecordMainId)) {
                            FileRecordMain fileRecordMain = new FileRecordMain();
                            fileRecordMain.setFileRecordMainId(Integer.parseInt(fileRecordMainId));
                            fileRecordMain.setRefId(dto.getContractId());
                            fileRecordMain.setIsDelete(false);
                            fileRecordMainMapper.update(fileRecordMain);
                        }
                    }
                }

                // ------------------------ 新增业绩关联人员信息部分 ------------------------//
                // 合同城市编号
                try {
                    Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_STORE, dto.getExpandingPersonnelId(), dto.getExpandingPersonnel(),
                            dto.getContractId(), dto.getCenterId(), dto.getAccountProject(), dto.getAccountProjectNo());
                    if (!isSaved) {
                        logger.error("achievement", "SignContractService", "saveAchievementInfo", null, null, null,
                                "合同新增-保存业绩归属信息失败！contractId：" + dto.getContractId(), null);
                    }
                } catch (Exception e) {
                    logger.error("achievement", "SignContractService", "saveAchievementInfo", null, null, null,
                            "合同新增-保存业绩归属信息失败！contractId：" + dto.getContractId(), e);
                }

                //CRM草签合同向OP发起待开通房友账号接口
                try {
                    if (null != companyNewDto) {
                        try {
                            String opDubboOpen = SystemParam.getWebConfigValue("opDubboOpen");
                            if ("1".equals(opDubboOpen)) {
                                //opCompanyService.noticeOPCompany(companyNewDto.getCompanyNo());
                            } else {
                                oPCompanyHttpService.noticeOPCompany(companyNewDto.getCompanyNo());
                            }

                        } catch (Exception e) {
                            logger.error("Contract", "SignContractService", "create", null, null, null, "17版 CRM草签合同向OP发起待开通房友账号接口失败！companyNo：" + companyNewDto.getCompanyNo(), e);
                        }

                        if ("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
                            //18版OP
                            String url18 = SystemParam.getWebConfigValue("opUrl18") + "accounts/apply/{companyNo}";
                            String jsonData = "{\"companyNo\":" + companyNewDto.getCompanyNo() + "}";
                            logger.info("CRM草签合同向OP发起待开通房友账号接口: #####请求#url=" + url18 + "##data=" + jsonData);
                            String opResult = HttpClientUtil.jsonGet(url18, companyNewDto.getCompanyNo());
                            //logger.error("Contract", "SignContractService", "create", null, null, null, "CRM草签合同向18OP发起待开通房友账号接口成功！companyNo："+ companyNewDto.getCompanyNo()+"    opResult:"+opResult, null);
                        }
                    }
                } catch (Exception e) {
                    logger.error("Contract", "SignContractService", "create", null, null, null, "CRM草签合同向OP发起待开通房友账号接口失败！companyNo：" + companyNewDto.getCompanyNo(), e);
                }

            } else {
                resultData.setFail("签约失败，服务器异常");
                return resultData;
            }
        } else {
            //更新
            //查询数据库中的合同信息
            Contract dbContract = contractMapper.getById(dto.getContractId());

            //版本号是否一致的check
            Integer dbContractVersion = dbContract.getContractVersion();
            Integer contractVersion = dto.getContractVersion();
            if (dbContractVersion.equals(contractVersion)) {
                contractVersion++;
                dto.setContractVersion(contractVersion);
            } else {
                String strMsg = "该合同信息web端已修改，请返回刷新后重新进行编辑！";
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg(strMsg);
                return resultData;
            }


            for (ContractStoreNewDto contractStoreNewDto : contractStoreNewDtoList) {
                contractStoreNewDto.setContractId(dto.getContractId());
            }
            try {
                signContractMapper.updateContract(dto);

                for (ContractStoreNewDto contractStoreNewDto : contractStoreNewDtoList) {
                    Map<String, Object> map = new HashMap<>();
                    Integer storeId = Integer.valueOf(contractStoreNewDto.getStoreId());
                    map.put("storeId", storeId);
                    List<Contacts> moList = contactsMapper.queryList(map);

                    Store briefById = storeMapper.getBriefById(storeId);

                    //门店负责人，联系电话未发生变化
                    if (contractStoreNewDto.getContactsName() != null && contractStoreNewDto.getContactsPhone() != null
                            && contractStoreNewDto.getContactsName().equals(briefById.getStoreManager())
                            && contractStoreNewDto.getContactsPhone().equals(briefById.getStoreManagerPhone())) {
                        continue;
                    }
                    //更新门店负责人，新增联系人
                    ContactsDto contactsDto = new ContactsDto();
                    ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
                    String contactsNo = back.getReturnData();
                    contactsDto.setContactsNo(contactsNo);
                    contactsDto.setStoreId(storeId);
                    contactsDto.setContactsName(contractStoreNewDto.getContactsName());
                    contactsDto.setContactsPhone(contractStoreNewDto.getContactsPhone());
                    //判断联系人是否重复
                    int result = followMapMapper.checkContacts(contactsDto);
                    if (result > 0) {
                        //如果重复 忽略
                    } else {
                        //插入门店联系人信息
                        contactsDto.setUserCreate(dto.getUserCreate().toString());
                        followMapMapper.addContacts(contactsDto);
                    }
                    //更新门店负责人信息
                    signContractMapper.updateStoreManagerInfo(contactsDto);
                }

                //1.判断是否填写联系人，未做修改
                //2.更新合同门店关系edit
                signContractMapper.updateContractStore(dto);
                //3.更新门店签约类型，只有B合同更新为已锁定
                signContractMapper.updateStoreStatus(dto);
                //4.更新公司为已签约，并更新协议书类型，未做修改
                //5.更新图片关联表
                String fileRecordMainIds = dto.getFileRecordMainIds();
                if (fileRecordMainIds != null && !"".equals(fileRecordMainIds)) {
                    String[] fileRecordMainIdArray = fileRecordMainIds.split(",");
                    for (int i = 0; i < fileRecordMainIdArray.length; i++) {
                        String fileRecordMainId = fileRecordMainIdArray[i];

                        if (fileRecordMainId != null && !"".equals(fileRecordMainId) && !"undefined".equals(fileRecordMainId)) {
                            FileRecordMain fileRecordMain = new FileRecordMain();
                            fileRecordMain.setFileRecordMainId(Integer.parseInt(fileRecordMainId));
                            fileRecordMain.setRefId(dto.getContractId());
                            fileRecordMain.setIsDelete(false);
                            fileRecordMainMapper.update(fileRecordMain);
                        }
                    }
                }

                String delFileRecordMainIds = dto.getDelFileRecordMainIds();
                if (delFileRecordMainIds != null && !"".equals(delFileRecordMainIds)) {
                    String[] delFileRecordMainIdArray = delFileRecordMainIds.split(",");
                    for (int i = 0; i < delFileRecordMainIdArray.length; i++) {
                        String fileRecordMainId = delFileRecordMainIdArray[i];

                        if (fileRecordMainId != null && !"".equals(fileRecordMainId) && !"undefined".equals(fileRecordMainId)) {
                            FileRecordMain fileRecordMain = new FileRecordMain();
                            fileRecordMain.setFileRecordMainId(Integer.parseInt(fileRecordMainId));
                            fileRecordMain.setRefId(null);
                            fileRecordMain.setIsDelete(true);
                            fileRecordMainMapper.update(fileRecordMain);
                        }
                    }
                }
            } catch (Exception e) {
                resultData.setFail("修改失败，服务器异常");
                return resultData;
            }
        }

        return resultData;
    }

    /**
     * 根据协议书编号查询合同
     *
     * @return
     */
    public ResultData<List<Contract>> getContractByAgreementNo(String agreementNo) throws Exception {
        ResultData<List<Contract>> resultData = new ResultData<List<Contract>>();

        List<Contract> list = contractMapper.getContractByAgreementNo(agreementNo);
        resultData.setReturnData(list);
        return resultData;
    }

    /**
     * 设置业绩关联人员信息
     * 当前拓展人员需求只有四级
     * (即：1.拓展专员、
     * 2.拓展专员的上级是拓展经理、
     * 3.拓展经理的上级是区域总监、
     * 4.区域总监的上级是事业部总经理 )
     *
     * @param contract 合同信息
     * @return
     * @throws Exception
     */
    private ContractRelatePerson setRelatePerson(Contract contract)
            throws Exception {
        ContractRelatePerson relatePerson = new ContractRelatePerson();
        // 合同ID
        relatePerson.setContractId(contract.getId());
        // 业绩归属拓展人工号
        String expandingPersonnelId = contract.getExpandingPersonnelId();

        // 查询当前【业绩归属拓展人】的所属组和所属岗的信息
        // 1.根据userId查询用户信息
        User currentUser = userMapper.getUserByCode(expandingPersonnelId);

        relatePerson.setExpandingPersonnel(currentUser.getUserName());
        relatePerson.setExpandingPersonnelId(currentUser.getUserCode());
        // ------------------- 可能多岗位的场合 start -------------- //
        // 有可能存在多岗因此需要用List接收
        List<Post> currentPostList = null;
        // 2.查询拓展人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
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
                    this.setRelatePersonInfo(currentUser, currentGroup, currentPost, relatePerson);
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
                    if (null != leadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                        // 设置【区域总监】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(leadersGroup, relatePerson);
                    }

                    //  2. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往跳2级）
                    Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                    Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                    // 非空判断
                    if (null != secondLeadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                        // 设置【事业部总经理】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(secondLeadersGroup, relatePerson);
                    }

                    // 拓展专员的场合
                } else {
                    // 1. 查找【拓展经理】所属组（根据【拓展专员】的GroupManagerId找到他的上级）
                    if (null != currentGroup.getGroupManagerId() && StringUtil.isNotEmpty(currentGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(currentGroup.getGroupManagerId());
                        // 设置【拓展经理】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息 (该处拓展经理同专员所属同一个组)
                        this.setPerformTeamInfo(currentGroup, relatePerson);
                    }

                    //  2. 查找【区域总监】所属组（固定在【拓展经理】的基础上往上跳1级）
                    Group leadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                    // 非空判断
                    if (null != leadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(leadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(leadersGroup.getGroupManagerId());
                        // 设置【区域总监】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(leadersGroup, relatePerson);
                    }

                    //  3. 查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                    Group firstLeadersGroup = groupMapper.selectByGroupId(leadersGroup.getParentId());
                    Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                    // 非空判断
                    if (null != secondLeadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                        int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                        // 设置【事业部总经理】的信息
                        this.setLeaderInfo(userId, relatePerson);
                    } else {
                        // 上级领导人不存在的场合、就只存它上级所在组的信息
                        this.setPerformTeamInfo(secondLeadersGroup, relatePerson);
                    }
                }

                // 当前归属拓展人所属组为【拓展事业部】的场合
            } else if (currentGroup.getTypeId() == 8) {

                //  查找【事业部总经理】所属组（固定在【区域总监】的基础上往上跳2级）
                Group firstLeadersGroup = groupMapper.selectByGroupId(currentGroup.getParentId());
                Group secondLeadersGroup = groupMapper.selectByGroupId(firstLeadersGroup.getParentId());
                // 非空判断
                if (null != secondLeadersGroup.getGroupManagerId() && StringUtil.isNotEmpty(secondLeadersGroup.getGroupManagerId())) {
                    int userId = Integer.parseInt(secondLeadersGroup.getGroupManagerId());
                    // 设置【事业部总经理】的信息
                    this.setLeaderInfo(userId, relatePerson);
                } else {
                    // 上级领导人不存在的场合、就只存它上级所在组的信息
                    this.setPerformTeamInfo(secondLeadersGroup, relatePerson);
                }
            }
            // =============== 查询当前拓展人员的上级信息 end ================ //
        }
        return relatePerson;
    }

    /**
     * 设置业绩归属拓展人员信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     *
     * @param paramUser  用户信息
     * @param paramGroup 组信息
     * @param paramPost  岗位信息
     * @return
     * @throws Exception
     */
    private void setRelatePersonInfo(User paramUser, Group paramGroup, Post paramPost, ContractRelatePerson relatePerson)
            throws Exception {
        // 所属组为【拓展组】的场合（专员和拓展经理都属于拓展组）
        if (paramGroup.getTypeId() == 4) {
            // 拓展经理的场合 (20:拓展经理   21:拓展专员)
            if (paramPost.getTypeId() == 20) {
                // 拓展经理工号
                relatePerson.setExpandLeaderId(paramUser.getUserCode());
                // 拓展经理姓名
                relatePerson.setExpandLeader(paramUser.getUserName());
                // 拓展经理所属组
                relatePerson.setPerformTeam3(paramGroup.getGroupName());
                // 拓展经理所属岗位
                relatePerson.setPostTypeName3(paramPost.getPostName());
                // 拓展经理所属组Id
                relatePerson.setExpandLeaderGroupId(paramGroup.getGroupId());
                // 拓展经理所属岗位Id
                relatePerson.setExpandLeaderPostId(paramPost.getPostId());

                // 拓展专员的场合
            } else {
                // 业绩拓展专员工号
                relatePerson.setExpandingPersonnelId(paramUser.getUserCode());
                // 业绩拓展专员姓名
                relatePerson.setExpandingPersonnel(paramUser.getUserName());
                // 业绩拓展专员所属组（业绩归属团队四）
                relatePerson.setPerformTeam4(paramGroup.getGroupName());
                // 业绩拓展专员所属岗位
                relatePerson.setPostTypeName4(paramPost.getPostName());
                // 拓展专员所属组Id
                relatePerson.setExpandingPersonnelGroupId(paramGroup.getGroupId());
                // 拓展专员所属岗位Id
                relatePerson.setExpandingPersonnelPostId(paramPost.getPostId());
            }

            // 所属组为【拓展事业部】的场合
        } else if (paramGroup.getTypeId() == 8) {
            // 区域总监工号
            relatePerson.setRegionalDirectorId(paramUser.getUserCode());
            // 区域总监姓名
            relatePerson.setRegionalDirector(paramUser.getUserName());
            // 区域总监所属组
            relatePerson.setPerformTeam2(paramGroup.getGroupName());
            // 区域总监所属岗位
            relatePerson.setPostTypeName2(paramPost.getPostName());
            // 区域总监所属组Id
            relatePerson.setRegionalDirectorGroupId(paramGroup.getGroupId());
            // 区域总监所属岗位Id
            relatePerson.setRegionalDirectorPostId(paramPost.getPostId());

            // 所属组为【事业部】的场合
        } else if (paramGroup.getTypeId() == 7) {
            // 事业部总经理工号
            relatePerson.setBusinessManagerId(paramUser.getUserCode());
            // 事业部总经理姓名
            relatePerson.setBusinessManager(paramUser.getUserName());
            // 事业部总经理所属组
            relatePerson.setPerformTeam1(paramGroup.getGroupName());
            // 事业部总经理所属岗位
            relatePerson.setPostTypeName1(paramPost.getPostName());
            // 事业部总经理所属组Id
            relatePerson.setBusinessManagerGroupId(paramGroup.getGroupId());
            // 事业部总经理所属岗位Id
            relatePerson.setBusinessManagerPostId(paramPost.getPostId());
        }
    }

    /**
     * 设置业绩归属拓展人员（上级）信息
     *
     * @param userId
     * @param relatePerson
     */
    private void setLeaderInfo(Integer userId, ContractRelatePerson relatePerson)
            throws Exception {
        // 1.查找上级领导用户信息
        User leadershipUser = userMapper.getUserByUserId(userId);

        // ------------------- 可能多岗位的场合 start -------------- //
        // 多岗的场合
        List<Post> leadershipPostList = null;
        // 2.查询拓展人员所属岗位信息(可能存在多岗的场合，即用List集合接收)
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
                    // 设置当前业绩归属拓展人员信息
                    this.setRelatePersonInfo(leadershipUser, leadershipGroup, leadershipPost, relatePerson);
                }
            }
        }
        // ------------------- 可能多岗位的场合 end -------------- //
    }

    /**
     * 设置业绩归属团队信息（包含拓展专员、拓展经理、区域总监、事业部总经理）
     *
     * @param paramGroup   组信息
     * @param relatePerson 业绩关联人员信息
     * @throws Exception
     */
    private void setPerformTeamInfo(Group paramGroup, ContractRelatePerson relatePerson)
            throws Exception {
        // 所属组为【拓展组】的场合（专员和拓展经理都属于拓展组）
        if (paramGroup.getTypeId() == 4) {
            // 拓展专员和拓展经理的场合
            // 拓展经理所属组 (业绩归属团队三)
            relatePerson.setPerformTeam3(paramGroup.getGroupName());
            // 拓展经理所属组Id
            relatePerson.setExpandLeaderGroupId(paramGroup.getGroupId());
            // 业绩拓展专员所属组（业绩归属团队四）
            relatePerson.setPerformTeam4(paramGroup.getGroupName());
            // 拓展专员所属组Id
            relatePerson.setExpandingPersonnelGroupId(paramGroup.getGroupId());

            // 所属组为【拓展事业部】的场合
        } else if (paramGroup.getTypeId() == 8) {
            // 区域总监所属组 (业绩归属团队二)
            relatePerson.setPerformTeam2(paramGroup.getGroupName());
            // 区域总监所属组Id
            relatePerson.setRegionalDirectorGroupId(paramGroup.getGroupId());

            // 所属组为【事业部】的场合
        } else if (paramGroup.getTypeId() == 7) {
            // 事业部总经理所属组 (业绩归属团队一)
            relatePerson.setPerformTeam1(paramGroup.getGroupName());
            // 事业部总经理所属组Id
            relatePerson.setBusinessManagerGroupId(paramGroup.getGroupId());
        }
    }

    /**
     * 根据条件查询 签约合同列表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<List<ContractNewDto>> getSignContractList(ContractNewDto dto) throws Exception {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list = signContractMapper.getContractList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    /**
     * 根据条件查询 公盘合同列表
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<List<ContractNewDto>> getGpSignContractList(ContractNewDto dto) throws Exception {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list = signContractMapper.getGpSignContractList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    /**
     * 根据合同ID 获取合同详情信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<ContractNewDto> getSignContractInfo(ContractNewDto dto) throws Exception {
        ResultData<ContractNewDto> resultData = new ResultData<ContractNewDto>();
        ContractNewDto contractNewDto = signContractMapper.getContractInfo(dto);
        //获取 合同审批历史列表信息
        if (null != contractNewDto && null != contractNewDto.getFlowId() && !contractNewDto.getFlowId().isEmpty()) {
            ContractAuditRecordDto contractAuditRecordDto = new ContractAuditRecordDto();
            contractAuditRecordDto.setFlowId(contractNewDto.getFlowId());
            //设置OA数据源
            DbcontextHolder.setDbType("dataSourceOA");
            List<ContractAuditRecordDto> contractAuditRecordDtoList = signContractMapper.getContractAuditRecordList(contractAuditRecordDto);
            //还原数据源
            DbcontextHolder.setDbType("dataSourceMain");

            contractNewDto.setContractAuditRecordDtoList(contractAuditRecordDtoList);
        }

        //续签时没有传contractId
        int flag = 0;
        if (dto.getContractId() == null) {
            flag = 1;
            dto.setContractId(contractNewDto.getContractId());
        }

        // 获取文件信息
        String fileRecordMainIds = "";
        // 营业证
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        businessFile.setRefId(dto.getContractId());
        businessFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByContractId(businessFile);
        fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);

        // 身份证
        List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
        FileRecordMain cardFile = new FileRecordMain();
        cardFile.setRefId(dto.getContractId());
        cardFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByContractId(cardFile);
        fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);
        // 合同照片
        List<ContractFileDto> filePhotoList = new ArrayList<ContractFileDto>();
        FileRecordMain photoFile = new FileRecordMain();
        photoFile.setRefId(dto.getContractId());
        photoFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByContractId(photoFile);
        fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, filePhotoList);

        // 门店照片
        List<ContractFileDto> fileStoreList = new ArrayList<ContractFileDto>();
        FileRecordMain storeFile = new FileRecordMain();
        storeFile.setRefId(dto.getContractId());
        storeFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList4 = fileRecordMainMapper.getStoreByContractId(storeFile);
        fileRecordMainIds = pushFileRecord(fileMdlList4, fileRecordMainIds, fileStoreList);

        // 房友系统申请安装单
        List<ContractFileDto> fileInstallList = new ArrayList<ContractFileDto>();
        FileRecordMain installFile = new FileRecordMain();
        installFile.setRefId(dto.getContractId());
        installFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList5 = fileRecordMainMapper.getInstallByContractId(installFile);
        fileRecordMainIds = pushFileRecord(fileMdlList5, fileRecordMainIds, fileInstallList);

        // 重要提示函
        List<ContractFileDto> fileNoticeList = new ArrayList<ContractFileDto>();
        FileRecordMain noticeFile = new FileRecordMain();
        noticeFile.setRefId(dto.getContractId());
        noticeFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList7 = fileRecordMainMapper.getNoticeByContractId(noticeFile);
        fileRecordMainIds = pushFileRecord(fileMdlList7, fileRecordMainIds, fileNoticeList);

        // 合同补充协议
        List<ContractFileDto> fileComplementList = new ArrayList<ContractFileDto>();
        FileRecordMain complementFile = new FileRecordMain();
        complementFile.setRefId(dto.getContractId());
        complementFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList8 = fileRecordMainMapper.getComplementByContractId(complementFile);
        fileRecordMainIds = pushFileRecord(fileMdlList8, fileRecordMainIds, fileComplementList);

        // 其他文件
        List<ContractFileDto> fileOtherList = new ArrayList<ContractFileDto>();
        FileRecordMain otherFile = new FileRecordMain();
        otherFile.setRefId(dto.getContractId());
        otherFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList6 = fileRecordMainMapper.getOtherByContractId(otherFile);
        fileRecordMainIds = pushFileRecord(fileMdlList6, fileRecordMainIds, fileOtherList);

        contractNewDto.setFileRecordMainBusiness(fileBusinessList);
        contractNewDto.setFileRecordMainCard(fileCardList);
        contractNewDto.setFileRecordMainPhoto(filePhotoList);
        contractNewDto.setFileRecordMainStore(fileStoreList);
        contractNewDto.setFileRecordMainInstall(fileInstallList);
        contractNewDto.setFileRecordMainNotice(fileNoticeList);
        contractNewDto.setFileRecordMainComplement(fileComplementList);
        contractNewDto.setFileRecordMainOther(fileOtherList);

        //续签时乙类如果有乙3，只保留乙3
        if (flag == 1) {
            List<StoreNewDto> storeList = contractNewDto.getContractStoreList();
            for (StoreNewDto store : storeList) {
                String bTypeStore = store.getBTypeStore();
                if (StringUtil.isNotEmpty(bTypeStore)) {
                    String bTypeStoreNm = "";
                    if (bTypeStore.indexOf("20001") != -1) {
                        bTypeStoreNm += "乙1";
                    }
                    if (bTypeStore.indexOf("20002") != -1) {
                        bTypeStoreNm += "乙2";
                    }
                    if (bTypeStore.indexOf("20004") != -1) {
                        bTypeStoreNm += "乙4";
                    }
                    if (bTypeStore.indexOf("20003") != -1) {
                        bTypeStoreNm = "乙3";
                        //bTypeStore = "20003";
                    }
                    store.setbTypeStoreNm(bTypeStoreNm);
                    //store.setBTypeStore(bTypeStore);
                }
            }
        }

        resultData.setReturnData(contractNewDto);
        return resultData;
    }

    /**
     * 根据合同ID 获取公盘合同详情信息
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData<ContractNewDto> getGpSignContractInfo(ContractNewDto dto) throws Exception {
        ResultData<ContractNewDto> resultData = new ResultData<ContractNewDto>();
        ContractNewDto contractNewDto = signContractMapper.getGpSignContractInfo(dto);
        //获取 合同审批历史列表信息
        if (null != contractNewDto && null != contractNewDto.getFlowId() && !contractNewDto.getFlowId().isEmpty()) {
            ContractAuditRecordDto contractAuditRecordDto = new ContractAuditRecordDto();
            contractAuditRecordDto.setFlowId(contractNewDto.getFlowId());
            //设置OA数据源
            DbcontextHolder.setDbType("dataSourceOA");
            List<ContractAuditRecordDto> contractAuditRecordDtoList = signContractMapper.getContractAuditRecordList(contractAuditRecordDto);
            //还原数据源
            DbcontextHolder.setDbType("dataSourceMain");

            contractNewDto.setContractAuditRecordDtoList(contractAuditRecordDtoList);
        }

        //续签时没有传contractId
        int flag = 0;
        if (dto.getContractId() == null) {
            flag = 1;
            dto.setContractId(contractNewDto.getContractId());
        }
        //获取翻牌到账保证金
        BigDecimal deposit = gpContractMapper.queryDeposit(contractNewDto.getCompanyId());
        if (deposit != null) {
            contractNewDto.setDzDepositFee(deposit.doubleValue());
        } else {
            contractNewDto.setDzDepositFee(0.00);
        }
        // 获取文件信息
        String fileRecordMainIds = "";
        // 营业证
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        businessFile.setRefId(dto.getContractId());
        businessFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByGpContractId(businessFile);
        fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);

        // 身份证
        List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
        FileRecordMain cardFile = new FileRecordMain();
        cardFile.setRefId(dto.getContractId());
        cardFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByGpContractId(cardFile);
        fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);

        // 合同照片
        List<ContractFileDto> filePhotoList = new ArrayList<ContractFileDto>();
        FileRecordMain photoFile = new FileRecordMain();
        photoFile.setRefId(dto.getContractId());
        photoFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByGpContractId(photoFile);
        fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, filePhotoList);

        // 直联盘勾选表
        List<ContractFileDto> fileCheckList = new ArrayList<ContractFileDto>();
        FileRecordMain checkFile = new FileRecordMain();
        checkFile.setRefId(dto.getContractId());
        checkFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList7 = fileRecordMainMapper.getCheckByGpContractId(checkFile);
        fileRecordMainIds = pushFileRecord(fileMdlList7, fileRecordMainIds, fileCheckList);
        // 易居房友经纪业务共享平台规则
        List<ContractFileDto> fileRuleList = new ArrayList<ContractFileDto>();
        FileRecordMain ruleFile = new FileRecordMain();
        ruleFile.setRefId(dto.getContractId());
        ruleFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList8 = fileRecordMainMapper.getRuleByGpContractId(ruleFile);
        fileRecordMainIds = pushFileRecord(fileMdlList8, fileRecordMainIds, fileRuleList);

        // 其他文件
        List<ContractFileDto> fileOtherList = new ArrayList<ContractFileDto>();
        FileRecordMain otherFile = new FileRecordMain();
        otherFile.setRefId(dto.getContractId());
        otherFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList6 = fileRecordMainMapper.getOtherByGpContractId(otherFile);
        fileRecordMainIds = pushFileRecord(fileMdlList6, fileRecordMainIds, fileOtherList);

        contractNewDto.setFileRecordMainBusiness(fileBusinessList);
        contractNewDto.setFileRecordMainCard(fileCardList);
        contractNewDto.setFileRecordMainPhoto(filePhotoList);
        contractNewDto.setFileRecordMainCheck(fileCheckList);
        contractNewDto.setFileRecordMainRule(fileRuleList);
        contractNewDto.setFileRecordMainOther(fileOtherList);

        resultData.setReturnData(contractNewDto);
        return resultData;
    }

    /**
     * 图片信息
     *
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
                fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";

            }
        }
        return fileRecordMainIds;
    }

    /**
     * 对文件数据进行组装处理
     *
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
    }

    /**
     * 验证门店是否可签约
     *
     * @param dto
     * @return
     * @throws Exception
     */
    public ResultData checkStoreLock(ContractNewDto dto) throws Exception {
        String originalContractNo = null;//续签合同id，暂无
        //构建返回
        ResultData resultData = new ResultData();
        //合作协议书类型
        String contractTypeStr = dto.getContractType();
        Integer contractType = Integer.valueOf(contractTypeStr);
        //门店list（未签）
        List<Store> signedNo = new ArrayList<Store>();
        //门店list（签A）
        List<Store> signedA = new ArrayList<Store>();
        // 合同list（合同状态是:审核通过）
        List<Contract> auditPass = new ArrayList<Contract>();
        // 合同list（合同状态是"审核通过"以外的场合）
        List<Contract> notAuditPass = new ArrayList<Contract>();
        // 门店合同list（storeState是"变更中"的场合）
        List<ContractStore> isChange = new ArrayList<ContractStore>();
        // 门店合同list（storeState是"变更中"以外的场合）
        List<ContractStore> isnotChange = new ArrayList<ContractStore>();
        //合同到期日期
        Date dateLifeEnd = null;
        //门店list（签B）
        List<Store> signedB = new ArrayList<Store>();
        //门店撤销状态List（未撤销）
        List<Store> isnotCancel = new ArrayList<Store>();
        //门店撤销状态List（已撤销）
        List<Store> isCancel = new ArrayList<Store>();
        //门店list（签A2B）
        List<Store> signedA2B = new ArrayList<Store>();
        //门店list（签授牌）
        List<Store> signedSP = new ArrayList<Store>();
        // 门店ID
        String storeIds = dto.getStoreIdStr();
        Contract contractcancel = new Contract();

        //region CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同  2019-7-30 add by huangmc begin
        // 定义所有的A类合同都过期
        boolean allTypeAOverDue = true;
        // 定义所有的授权类合同都过期
        boolean allTypeSPOverDue = true;
        // 定义所有的B类都已撤销
        boolean allTypeBCancel = true;
        // 定义所有的A转B类都已撤销
        boolean allTypeA2BCancel = true;
        // 当前时间
        Date currect = new Date();
        //endregion

        if (StringUtil.isNotEmpty(storeIds)) {
            Store store = new Store();
            Contract contract = new Contract();
            ContractStore contractStore = new ContractStore();
            // 门店签约类型
            Integer contractTypeTemp = null;
            // 合同状态
            Integer contractStatus = null;
            // 门店合同状态
            Integer storeState = null;
            //门店撤销状态
            String cancelState = "";
            String[] arrays = storeIds.split(",");
            for (int i = 0; i < arrays.length; i++) {
                // 根据门店ID查询门店信息
                store = storeMapper.getById(Integer.valueOf(arrays[i]));
                //Add By NingChao 2017/04/07 Start
                //取得该门店的最新取消的合同
                if (null == originalContractNo) {
                    contractcancel = contractMapper.getByRenewStoreId(Integer.valueOf(arrays[i]));
                }
                //Add By NingChao 2017/04/07 End

                // 根据门店ID查询最新的合同信息
                contract = contractMapper.getByStoreId(Integer.valueOf(arrays[i]));

                if (null != contract) {
                    // 根据storeId、contractId查询合同门店关联信息
                    Map<String, Object> queryParam = new HashMap<String, Object>();
                    queryParam.put("storeId", Integer.valueOf(arrays[i]));
                    queryParam.put("contractId", contract.getId());
                    // 查询操作
                    contractStore = contractStoreMapper.getContractStore(queryParam);
                    storeState = contractStore.getStoreState();
                    //门店撤消状态
                    cancelState = contractStore.getIsCancel();
                    //合同状态
                    contractStatus = contract.getContractStatus();
                    dateLifeEnd = contract.getDateLifeEnd();
                }

                //region 门店的签约类型 的判断处理
                // 门店签约类型
                contractTypeTemp = store.getContractType();
                // 门店的签约类型是“未签”的场合
                if (null == contractTypeTemp || 0 == contractTypeTemp || DictionaryConstants.CONTRACT_STATUS_STOP.equals(contractStatus)) {
                    signedNo.add(store);
                }
                // 门店的签约类型是“A版”的场合
                else if (DictionaryConstants.CONTRACT_TPYE_A.equals(contractTypeTemp)) {
                    // 合同状态是"审核通过"的场合
                    if (DictionaryConstants.CONTRACT_STATUS_AUDIT_PASS.equals(contractStatus)) {
                        auditPass.add(contract);
                    } else {
                        notAuditPass.add(contract);
                    }

                    // 门店合同状态是"变更中"的场合
                    if (DictionaryConstants.STORESTATE_CHANGE.equals(storeState)) {
                        isChange.add(contractStore);
                    } else {
                        isnotChange.add(contractStore);
                    }

                    //门店的撤消状态
                    if (DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL.equals(cancelState) || DictionaryConstants.STORESTATE_ISCANCEL_ISCANCELLING.equals(cancelState)) {
                        isCancel.add(store);
                    } else {
                        isnotCancel.add(store);
                    }

                    signedA.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if (currect.compareTo(dateLifeEnd) <= 0) {
                        // 不是所有的A类合同都过期了
                        allTypeAOverDue = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“B版”的场合
                else if (DictionaryConstants.CONTRACT_TPYE_B.equals(contractTypeTemp)) {
                    signedB.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if (contractcancel == null) {
                        // 不是所有的B类合同都撤销了
                        allTypeBCancel = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“A转B版”的场合
                else if (DictionaryConstants.CONTRACT_TYPE_A_2_B.equals(contractTypeTemp)) {
                    signedA2B.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if (contractcancel == null) {
                        // 不是所有的A转B类合同都撤销了
                        allTypeA2BCancel = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“授牌”的场合
                else if (DictionaryConstants.CONTRACT_TYPE_SP.equals(contractTypeTemp)) {
                    signedSP.add(store);

                    // Start 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                    // 如果当前时间小于等于合同结束日期
                    if (currect.compareTo(dateLifeEnd) <= 0) {
                        // 不是所有的授牌类合同都过期了
                        allTypeSPOverDue = false;
                    }
                    // end 2019-7-30 add by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
                }
                //门店的签约类型是“未签”的场合                
                else {
                    signedNo.add(store);
                }

            }
        }

        //Add 2017/04/10 cning 续签保存 ---->
        if (null == originalContractNo) {
            //Add 2017/04/10 cning 续签保存 <----
            //协议书类型为A
            if (DictionaryConstants.CONTRACT_TPYE_A.equals(contractType)) {
                //门店都必须为未签
                if (!signedA.isEmpty() || !signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty() || !signedSP.isEmpty()) {
                    resultData.setFail("协议书类型签A的，门店只能都是未签约的！");
                    return resultData;
                }
            }
            //协议书类型为B
            else if (DictionaryConstants.CONTRACT_TPYE_B.equals(contractType)) {
                //region Start modify by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同

                //门店都必须为"未签"
                //Mod By Guopengfei 最新合同为取消的B类合同时，门店可以新签 start
                //if (!signedA.isEmpty() || !signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty())
                //if ((!signedA.isEmpty() || !signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty()) && contractcancel == null)
//                if ((!signedB.isEmpty() || !signedA2B.isEmpty() ) && contractcancel == null)
//                //Mod By Guopengfei 最新合同未取消的B类合同时，门店可以新签 end
//                {
//                    resultData.setFail("协议书类型签B的，门店只能都是未签约的！");
//                    return resultData;
//                }
//                if(!signedA.isEmpty()||!signedSP.isEmpty())
//                {
//                    Date currect = new Date();
//                    int rtn = currect.compareTo(dateLifeEnd);
//                    if((!signedA.isEmpty()) && (rtn <= 0 ))
//                    {
//                        resultData.setFail("协议书类型签B的，门店只能都是未签约的！");
//                        return resultData;
//                    }
//                }
                // 原合同签署的是B类 且存在不是撤销的 或者是 A转B类 且存在不是撤销的
                //  或者是A类且存在非过期的，或者是授牌类且存在非过期的
                if ((!signedB.isEmpty() && allTypeBCancel == false)
                        || (!signedA2B.isEmpty() && allTypeA2BCancel == false)
                        || (!signedA.isEmpty() && allTypeAOverDue == false)
                        || (!signedSP.isEmpty() && allTypeSPOverDue == false)) {
                    resultData.setFail("协议书类型签B的，门店只能都是未签约的！");
                    return resultData;
                }

                //endregion  End modify by huangmc CRMWEB-1760 门店A类合同到期后可以直接签约授牌合同
            }
            //协议书类型为C
            else if (DictionaryConstants.CONTRACT_TYPE_C.equals(contractType)) {
                //合作模式为

            }
            //协议书类型为A2B
            else if (DictionaryConstants.CONTRACT_TYPE_A_2_B.equals(contractType)) {
                // 门店都必须为已签A
                if (signedA.isEmpty() || !signedB.isEmpty() || !signedNo.isEmpty() || !signedSP.isEmpty()) {
                    resultData.setFail("协议书类型签A转B的，门店必须都是A版并且审核通过！");
                    return resultData;
                } else {
                    // 该门店对应的合同状态不是"审核通过"的场合
                    if (auditPass.isEmpty() || !notAuditPass.isEmpty()) {
                        resultData.setFail("协议书类型签A转B的，门店必须都是A版并且审核通过！");
                        return resultData;
                    }
                    //该门店必须是未撤销的
                    if (isnotCancel.isEmpty() || !isCancel.isEmpty()) {
                        resultData.setFail("协议书类型签A转B的，门店必须都是A版并且审核通过,且未进行业绩撤销的！");
                        return resultData;
                    }
                }

                // 合同状态为"变更中"的场合
                if (!isChange.isEmpty() || isnotChange.isEmpty()) {
                    resultData.setFail("门店合同变更中，不可提交合同！");
                    return resultData;
                }
            }
            //协议书类型为授牌
            else if (DictionaryConstants.CONTRACT_TYPE_SP.equals(contractType)) {
//            	  //门店都必须为未签
//                if (!signedB.isEmpty() || !signedA2B.isEmpty() || signedNo.isEmpty()||!signedSP.isEmpty())
//                {
//                    resultData.setFail("协议书类型签授牌的，门店只能都是未签约的！");
//                    return resultData;
//                }
//                if(!signedA.isEmpty())
//                {
//                    Date currect = new Date();
//                    int rtn = currect.compareTo(dateLifeEnd);
//                    if((!signedA.isEmpty()) && (rtn <= 0 ))
//                    {
//                        resultData.setFail("协议书类型签授牌的，门店只能都是未签约的！");
//                        return resultData;
//                    }
//                }

                if ((!signedA.isEmpty() && allTypeAOverDue == false)
                        || !signedB.isEmpty() || !signedA2B.isEmpty()
                        || (signedA.isEmpty() && signedNo.isEmpty()) || !signedSP.isEmpty()) {
                    resultData.setFail("协议书类型签授牌的，门店只能都是未签约的！");
                    return resultData;
                }

            }
        }
        return resultData;
    }

    public ResultData<List<StoreNewDto>> getTodoSignStoreList(StoreNewDto dto) throws Exception {
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        List<StoreNewDto> list = signContractMapper.getTodoSignStoreList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }
}
