package cn.com.eju.deal.gpContract.service;

import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContract.dao.*;
import cn.com.eju.deal.gpContract.model.*;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeMapper;
import cn.com.eju.deal.gpContractChange.model.GpContractChange;
import cn.com.eju.deal.gpContractChange.model.OaGpContractChangeReturn;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;
import cn.com.eju.deal.user.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;


/**
 * Created by haidan on 2018/8/30.
 */
@Service("gpContractService")
public class GpContractService{
    @Resource(name = "gpContractMapper")
    private GpContractMapper gpContractMapper;
    @Resource
    private GpContractChangeMapper gpContractChangeMapper;

    @Resource(name = "groupMapper")
    private GroupMapper groupMapper;

    @Resource
    private ContractMapper contractMapper;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private CompanyMapper companyMapper;

    @Resource(name = "contactsMapper")
    private ContactsMapper contactsMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private GpContractStoreMapper gpContractStoreMapper;

    @Resource(name = "achievementService")
    private AchievementService achievementService;

    @Resource(name = "contractService")
    private ContractService contractService;

    @Resource
    private OaGpContractReturnMapper oaGpContractReturnMapper;

    @Resource
    private ISeqNoAPI seqNoAPI;
    @Resource
    private GpBankInfoChangeLogMapper gpBankInfoChangeLogMapper;
    @Resource
    private GpBankInfoChangeLogFileMapper gpBankInfoChangeLogFileMapper;


    @Resource
    private UserMapper userMapper;
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 创建
     *
     * @param gpContractDto
     * @return
     */
    public ResultData<Integer> create(GpContractDto gpContractDto) throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);

        //协议书编号重复check
        List<GpContract> dataList = gpContractMapper.getListByAgreementNo(gpContract.getGpAgreementNo());
        if (null != dataList && dataList.size() > 0) {
            String strContractNo = "";
            for (GpContract info : dataList) {
                strContractNo += info.getGpContractNo() + ",";
            }

            if (strContractNo.length() > 0) {
                strContractNo = strContractNo.substring(0, strContractNo.length() - 1);
            }
            String strMsg = "系统中存在相同协议书编号的公盘合同，合同编号为" + strContractNo + "。请勿重复提交！";
            resultData.setReturnData(0);
            resultData.setFail();
            resultData.setReturnMsg(strMsg);
            return resultData;
        }
        //公盘合同编号
        ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_GPCONTRACT");
        gpContract.setGpContractNo(data.getReturnData());

        // 合同状态为待审状态
        if(gpContract.getContractStatus() == null) {
            gpContract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_PENDING);
        }
        gpContractDto.setSubmitOAStatus(21201);
        //考核主体
        String cityNoString;
        if(gpContract.getCenterId()!=null){
            cityNoString= groupMapper.getCityNoByGroupId(gpContract.getCenterId());
            if (StringUtil.isNotEmpty(cityNoString))
            {
                //根据业绩城市编号查询其核算主体
                List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
                if(accountProjectList.size() > 0){
                    String accountProject = accountProjectList.get(0).getAccountProject();
                    String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
                    //合同新增时候保存其核算主体及其编号，核算主体及其编号
                    if(!Objects.equals(accountProject, "")){
                        gpContract.setAccountProject(accountProject);
                    }
                    if (!Objects.equals(accountProjectNo, "")) {
                        gpContract.setAccountProjectNo(accountProjectNo);
                    }
                }
            }
        }
        Integer rel = gpContractMapper.create(gpContract);
        resultData.setReturnData(rel);

        // 联系人信息
        List<ContactsDto> contactsDtoList = gpContractDto.getContactsDtoList();
        if (null != contactsDtoList && !contactsDtoList.isEmpty())
        {
            for (ContactsDto contactsDto : contactsDtoList)
            {
                Store store = storeMapper.getById(contactsDto.getStoreId());
                String storeManager = store.getStoreManager();
                String storeManagerPhone = store.getStoreManagerPhone();
                if(!contactsDto.getName().equals(storeManager) || !contactsDto.getMobilePhone().equals(storeManagerPhone)){
                    Contacts contacts = new Contacts();
                    BeanUtils.copyProperties(contactsDto, contacts);
                    contactsMapper.create(contacts);

                    //更新门店负责人、负责人电话
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("storeId", contactsDto.getStoreId());
                    map.put("storeManager", contactsDto.getName());
                    map.put("storeManagerPhone", contactsDto.getMobilePhone());
                    storeMapper.updateStoreManager(map);
                }
            }
        }

        // 建立门店合同关系
        List<StoreDto> storeList = gpContractDto.getStoreDetails();
        if (null != storeList && !storeList.isEmpty())
        {
            for (StoreDto storeDto : storeList)
            {
                Store dbStore = storeMapper.getById(storeDto.getStoreId());
                // 建立门店合同关系
                GpContractStore contractStore = new GpContractStore();
                contractStore.setGpContractId(gpContract.getId());
                contractStore.setStoreId(storeDto.getStoreId());
                contractStore.setIsDelete(false);
                // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
                contractStore.setStoreState(0);
                contractStore.setAddressDetail(dbStore.getAddressDetail());
                /*contractStore.setAbTypeStore(storeDto.getABTypeStore());
                contractStore.setbTypeStore(storeDto.getBTypeStore());*/
                gpContractStoreMapper.create(contractStore);

                dbStore.setStoreStatus(0);
                dbStore.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
                storeMapper.update(dbStore);

                Map<String,Object> reqMap = new HashMap<>();
                reqMap.put("storeId", storeDto.getStoreId());
                reqMap.put("contractId", gpContract.getId());
                reqMap.put("companyId", gpContract.getCompanyId());
                Integer contractIdRef = gpContractStoreMapper.getContractStoreContractId(reqMap);
                if(contractIdRef!=null && contractIdRef>0){
                    GpContractStore cs = new GpContractStore();
                    cs.setGpContractId(contractIdRef);
                    cs.setStoreId(storeDto.getStoreId());
                    cs.setFlag(1);
                    gpContractStoreMapper.update(cs);
                }
            }
        }

        // 更新公司为已签约
        Company dbCompany = companyMapper.getById(gpContractDto.getCompanyId());
        dbCompany.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
        companyMapper.update(dbCompany);

        // 关联相关文件(RefId)
        Integer gpContractId = gpContract.getId();
        FileRecordMain attachmentFile = new FileRecordMain();
        GpContractDto gpDto = gpContractMapper.getRefIdByCompanyId(gpContractDto.getCompanyId());

        if(null!=gpDto){
	        Integer refId = gpDto.getId();
	        Integer fileSourceId = gpDto.getFileSourceId();
	        attachmentFile.setRefId(refId);
	        attachmentFile.setFileSourceId(fileSourceId);
	        attachmentFile.setIsDelete(false);
	        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByGpContractIdTwo(attachmentFile);
	        List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByGpContractIdTwo(attachmentFile);
	        if (gpContractDto.getFileRecordMain() != null && gpContractDto.getFileRecordMain().size() > 0)
	        {
	            List<ContractFileDto> contractFileDtos = gpContractDto.getFileRecordMain();

	            for (int i = 0; i < contractFileDtos.size(); i++)
	            {
	                boolean falg = false;
	                if (StringUtil.isNotEmpty(contractFileDtos.get(i).getFileRecordMainId()))
	                {
	                    //排除从公盘带过来的file
	                    for (FileRecordMain FileRecordMain:fileMdlList) {
	                        if(Integer.valueOf(contractFileDtos.get(i).getFileRecordMainId()).equals(FileRecordMain.getFileRecordMainId())){
                                copeFileRecordByGpContractFile(gpContractDto.getCompanyId(),gpContractId,FileRecordMain,1);
	                            falg = true;
	                        }
	                    }
	                    for (FileRecordMain FileRecordMain:fileMdlList2) {
	                        if(Integer.valueOf(contractFileDtos.get(i).getFileRecordMainId()).equals(FileRecordMain.getFileRecordMainId())){
                                copeFileRecordByGpContractFile(gpContractDto.getCompanyId(),gpContractId,FileRecordMain,2);
	                            falg = true;
	                        }
	                    }
	                    if(!falg){
	                        FileRecordMain fileRecordMain = new FileRecordMain();
	                        fileRecordMain.setFileRecordMainId(Integer.valueOf(contractFileDtos.get(i).getFileRecordMainId()));
	                        fileRecordMain.setRefId(gpContract.getId());
	                        fileRecordMain.setIsDelete(false);
	                        //主要为了排序（当存在通过的合同时会自动带出身份证和营业执照，为了防止图片顺序错位。更新的同时把创建时间重新更新下。）
	                        fileRecordMain.setDateCreate(new Date());
	                        fileRecordMainMapper.update(fileRecordMain);
	                    }
	                }
	            }
	        }
        }else{
            List<ContractFileDto> contractFileDtos = gpContractDto.getFileRecordMain();
            for (int i = 0; i < contractFileDtos.size(); i++)
            {
                FileRecordMain fileRecordMain = new FileRecordMain();
                fileRecordMain.setFileRecordMainId(Integer.valueOf(contractFileDtos.get(i).getFileRecordMainId()));
                fileRecordMain.setRefId(gpContract.getId());
                fileRecordMain.setIsDelete(false);
                //主要为了排序（当存在通过的合同时会自动带出身份证和营业执照，为了防止图片顺序错位。更新的同时把创建时间重新更新下。）
                fileRecordMain.setDateCreate(new Date());
                fileRecordMainMapper.update(fileRecordMain);
            }
        }
        //保存业绩归属
        try {
            String userCode = "";
            if (StringUtil.isNotEmpty(gpContract.getExPersonId())) {
                User user = userMapper.getUserByUserId(Integer.valueOf(gpContract.getExPersonId()));
                userCode = user.getUserCode();
            }
            Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_GP, userCode, gpContract.getExPerson(),
                    gpContract.getId(), gpContract.getCenterId(), gpContract.getAccountProject(), gpContract.getAccountProjectNo());
            if (!isSaved) {
                logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                        "公盘合同新增-保存业绩归属信息失败！contractId：" + gpContract.getId(), null);
            }
        }catch(Exception e){
            logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                    "公盘合同新增-保存业绩归属信息失败！contractId：" + gpContract.getId(), e);
        }

        if (rel > 0) {
            resultData.setSuccess();

        } else {
            resultData.setFail("新增失败");
        }
        return resultData;
    }

    private void copeFileRecordByGpContractFile(Integer companyId, Integer gpContractId, List<FileRecordMain> fileMdlList, int code) throws Exception {
        if (null != fileMdlList && fileMdlList.size() > 0) {
            for (FileRecordMain fileRecordMain2 : fileMdlList) {
                fileRecordMain2.setFileTypeId(code);
                fileRecordMain2.setFileSourceId(12);
                fileRecordMain2.setRefId(gpContractId);
                fileRecordMain2.setCompanyId(companyId);
                fileRecordMainMapper.createTwo(fileRecordMain2);
            }
        }
    }

    private void copeFileRecordByGpContractFile(Integer companyId, Integer gpContractId, FileRecordMain fileMdlVo, int code) throws Exception {
        if (null != fileMdlVo) {
            fileMdlVo.setFileTypeId(code);
            fileMdlVo.setFileSourceId(12);
            fileMdlVo.setRefId(gpContractId);
            fileMdlVo.setCompanyId(companyId);
                fileRecordMainMapper.createTwo(fileMdlVo);
        }
    }

    /**
     * 修改公盘合同信息
     * @param gpContractDto
     * @return
     * @throws Exception
     */
    public ResultData<Integer> updateBrief(GpContractDto gpContractDto) throws Exception{
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);
        Integer rel = gpContractMapper.update(gpContract);
        resultData.setReturnData(rel);
        return resultData;
    }

    /**
     * 修改
     *
     * @param gpContractDto
     * @return
     */
    public ResultData<Integer> updateInfo(GpContractDto gpContractDto) throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);

        //协议书编号重复check
        List<GpContract> dataList = gpContractMapper.getListByAgreementNo(gpContract.getGpAgreementNo());
        if(dataList.size()>0)
        {
            String strContractNo="";
            for (GpContract info : dataList) {
                if(info.getGpContractNo() .equals(gpContract.getGpContractNo()))
                {
                    continue;
                }
                strContractNo += info.getGpContractNo()+",";
            }

            if(strContractNo.length()>0)
            {
                strContractNo = strContractNo.substring(0, strContractNo.length()-1);
                String strMsg = "系统中存在相同协议书编号的公盘合同，合同编号为"+strContractNo+"。请勿重复提交！";
                resultData.setReturnData(0);
                resultData.setFail();
                resultData.setReturnMsg(strMsg);
                return resultData;
            }
        }
        Integer rel = gpContractMapper.update(gpContract);
        resultData.setReturnData(rel);

        // 联系人信息
        List<ContactsDto> contactsDtoList = gpContractDto.getContactsDtoList();
        if (null != contactsDtoList && !contactsDtoList.isEmpty())
        {
            for (ContactsDto contactsDto : contactsDtoList)
            {
                Store store = storeMapper.getById(contactsDto.getStoreId());
                String storeManager = store.getStoreManager();
                String storeManagerPhone = store.getStoreManagerPhone();
                if(!contactsDto.getName().equals(storeManager) || !contactsDto.getMobilePhone().equals(storeManagerPhone)){
                    Contacts contacts = new Contacts();
                    BeanUtils.copyProperties(contactsDto, contacts);
                    contactsMapper.create(contacts);

                    //更新门店负责人、负责人电话
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("storeId", contactsDto.getStoreId());
                    map.put("storeManager", contactsDto.getName());
                    map.put("storeManagerPhone", contactsDto.getMobilePhone());
                    storeMapper.updateStoreManager(map);
                }
            }
        }


        // 更新门店 、门店合同
        List<GpContractStore> contractStoreList = gpContractStoreMapper.selStoreByContractId(gpContract.getId());
        if (null != contractStoreList && !contractStoreList.isEmpty())
        {
            for (GpContractStore aContractStoreList : contractStoreList) {
                //更新掉原有门店的状态
                Store dbStore = storeMapper.getById(aContractStoreList.getStoreId());
                dbStore.setStoreStatus(0);
                dbStore.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N);
                storeMapper.update(dbStore);
            }
        }
        //解除合同门店的关系
        gpContractStoreMapper.deleteByContractId(gpContract.getId());

        // 建立门店合同关系
        List<StoreDto> storeList = gpContractDto.getStoreDetails();
        if (null != storeList && !storeList.isEmpty())
        {
            for (StoreDto storeDto : storeList)
            {
                // 建立门店合同关系
                Store dbStore = storeMapper.getById(storeDto.getStoreId());
                GpContractStore contractStore = new GpContractStore();
                contractStore.setGpContractId(gpContract.getId());
                contractStore.setStoreId(storeDto.getStoreId());
                contractStore.setIsDelete(false);
                contractStore.setStoreState(0);
                contractStore.setAddressDetail(dbStore.getAddressDetail());
                /*contractStore.setAbTypeStore(storeDto.getABTypeStore());
                contractStore.setbTypeStore(storeDto.getBTypeStore());*/
                gpContractStoreMapper.create(contractStore);

                dbStore.setStoreStatus(0);
                dbStore.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
                storeMapper.update(dbStore);
            }
        }

        // 关联相关文件(RefId)
        List<ContractFileDto> contractFiles = gpContractDto.getOldFileRecordMain();
        if (null != contractFiles && !contractFiles.isEmpty())
        {
            for (ContractFileDto contractFile : contractFiles) {
                String fileId = contractFile.getFileRecordMainId();
                if (!"null".equals(fileId)) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(fileId));
                    fileRecordMain.setRefId(null);
                    fileRecordMain.setIsDelete(true);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        List<ContractFileDto> newcontractFiles = gpContractDto.getFileRecordMain();
        if (null != newcontractFiles && !newcontractFiles.isEmpty())
        {
            for (ContractFileDto newcontractFile : newcontractFiles) {
                String fileId = newcontractFile.getFileRecordMainId();
                if (!"null".equals(fileId) && StringUtil.isNotEmpty(fileId)) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(fileId));
                    fileRecordMain.setRefId(gpContract.getId());
                    fileRecordMain.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
        }
        //对原业绩进行删除
        int reg =  gpContractMapper.deleteAchivAchievementByrelateId(gpContract.getId());
        if(reg>0){
        	//考核主体
        	String cityNoString;
        	if(gpContract.getCenterId()!=null){
        		cityNoString= groupMapper.getCityNoByGroupId(gpContract.getCenterId());
        		if (StringUtil.isNotEmpty(cityNoString)){
        			//根据业绩城市编号查询其核算主体
        			List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
        			if(accountProjectList.size() > 0){
        				String accountProject = accountProjectList.get(0).getAccountProject();
        				String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
        				//合同新增时候保存其核算主体及其编号，核算主体及其编号
        				if(!Objects.equals(accountProject, "")){
        					gpContract.setAccountProject(accountProject);
        				}
        				if (!Objects.equals(accountProjectNo, "")) {
        					gpContract.setAccountProjectNo(accountProjectNo);
        				}
        			}
        		}
        	}
        	//保存业绩归属
        	try {
        		String userCode = "";
        		if (StringUtil.isNotEmpty(gpContract.getExPersonId())) {
        			User user = userMapper.getUserByUserId(Integer.valueOf(gpContract.getExPersonId()));
        			userCode = user.getUserCode();
        		}
        		Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_GP, userCode, gpContract.getExPerson(),
        				gpContract.getId(), gpContract.getCenterId(), gpContract.getAccountProject(), gpContract.getAccountProjectNo());
        		if (!isSaved) {
        			logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
        					"公盘合同修改-保存业绩归属信息失败！contractId：" + gpContract.getId(), null);
        		}
        	}catch(Exception e){
        		logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
        				"公盘合同修改-保存业绩归属信息失败！contractId：" + gpContract.getId(), e);
        	}
        }
        return resultData;
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public ResultData<GpContractDto> getById(int id) throws Exception {
        ResultData<GpContractDto> resultData = new ResultData<>();
        GpContractDto gpContractDto  = gpContractMapper.getByGpId(id);

        String partyAddressDetail = gpContractDto.getPartyBCityName() + gpContractDto.getPartyBDistrictName() + gpContractDto.getPartyBAddress();
        gpContractDto.setPartyAddressDetail(partyAddressDetail);

        //获取翻牌到账保证金
        BigDecimal deposit = gpContractMapper.queryDeposit(gpContractDto.getCompanyId());
        if(null!=deposit){
            gpContractDto.setDeposit(deposit);
        }else{
            gpContractDto.setDeposit(new BigDecimal(0));
        }

        // 获取门店信息
        String oldStoreIdArray = "";
        List<StoreDto> storeList = new ArrayList<StoreDto>();
        List<StoreDto> storeMdlList = gpContractMapper.selectStoreByContractId(id);
        for (StoreDto dbStore : storeMdlList) {
            //GpContractStore gpContractStore = gpContractStoreMapper.getStoreByContractIdStoreId(id, dbStore.getStoreId());
            /*dbStore.setABTypeStore(gpContractStore.getAbTypeStore());
            dbStore.setBTypeStore(gpContractStore.getbTypeStore());
            dbStore.setBTypeStoreName(gpContractStore.getbTypeStoreName());*/
            if (dbStore.getStoreState() == null || dbStore.getStoreState() == 0) {
                dbStore.setStoreStateName("正常");
            } else if (dbStore.getStoreState() == 1) {
                dbStore.setStoreStateName("变更中");
            } else {
                dbStore.setStoreStateName("终止");
            }
            storeList.add(dbStore);
            oldStoreIdArray = oldStoreIdArray + String.valueOf(dbStore.getStoreId()) + ",";
        }

        // 获取文件信息
        String fileRecordMainIds = "";
        // 营业证
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        businessFile.setRefId(id);
        businessFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessOrderByCreate(businessFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);

        // 身份证
        List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
        FileRecordMain cardFile = new FileRecordMain();
        cardFile.setRefId(id);
        cardFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardOrderByCreate(cardFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);

        // 合同照片
        List<ContractFileDto> filePhotoList = new ArrayList<ContractFileDto>();
        FileRecordMain photoFile = new FileRecordMain();
        photoFile.setRefId(id);
        photoFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByGpContractId(photoFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList3, fileRecordMainIds, filePhotoList);
        // 授权委托书
        List<ContractFileDto> fileProxyList = new ArrayList<ContractFileDto>();
        FileRecordMain proxyFile = new FileRecordMain();
        proxyFile.setRefId(id);
        proxyFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList4 = fileRecordMainMapper.getProxyByGpContractId(proxyFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList4, fileRecordMainIds, fileProxyList);
        // 直联盘勾选表
        List<ContractFileDto> fileCheckList = new ArrayList<ContractFileDto>();
        FileRecordMain checkFile = new FileRecordMain();
        checkFile.setRefId(id);
        checkFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList7 = fileRecordMainMapper.getCheckByGpContractId(checkFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList7, fileRecordMainIds, fileCheckList);
        // 易居房友经纪业务共享平台规则
        List<ContractFileDto> fileRuleList = new ArrayList<ContractFileDto>();
        FileRecordMain ruleFile = new FileRecordMain();
        ruleFile.setRefId(id);
        ruleFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList8 = fileRecordMainMapper.getRuleByGpContractId(ruleFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList8, fileRecordMainIds, fileRuleList);
        // 账户变更申请书
        List<ContractFileDto> fileAccountChangeList = new ArrayList<ContractFileDto>();
        //根据id查询最新改动的日志id
        GpBankInfoChangeLog gpBankInfoChangeLog = gpBankInfoChangeLogMapper.getGPBankInfoChangeLogId(id);
        if(null != gpBankInfoChangeLog) {
        	Integer gpBankInfoChangeLogId = gpBankInfoChangeLog.getId();
        	Map<String,Object> hashMap = new HashMap<>();
        	hashMap.put("gpContractId", id);
        	hashMap.put("gpBankInfoChangeLogId", gpBankInfoChangeLogId);
        	List<FileRecordMain> fileMdlList5 = fileRecordMainMapper.getAccountChangeByLogId(hashMap);
        	fileRecordMainIds = contractService.pushFileRecord(fileMdlList5, fileRecordMainIds, fileAccountChangeList);
        }

        // 其他文件
        List<ContractFileDto> fileOtherList = new ArrayList<ContractFileDto>();
        FileRecordMain otherFile = new FileRecordMain();
        otherFile.setRefId(id);
        otherFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList6 = fileRecordMainMapper.getOtherByGpContractId(otherFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList6, fileRecordMainIds, fileOtherList);

        gpContractDto.setStoreDetails(storeList);
        gpContractDto.setFileAccountChangeList(fileAccountChangeList);
        gpContractDto.setFileBusinessList(fileBusinessList);
        gpContractDto.setFileIdCardList(fileCardList);
        gpContractDto.setFileContractList(filePhotoList);
        gpContractDto.setFileOtherList(fileOtherList);
        gpContractDto.setFileProxyList(fileProxyList);
        gpContractDto.setFileCheckList(fileCheckList);
        gpContractDto.setFileRuleList(fileRuleList);

        if (StringUtil.isNotEmpty(oldStoreIdArray) && oldStoreIdArray.length() > 0)
        {
            oldStoreIdArray = oldStoreIdArray.substring(0, oldStoreIdArray.length() - 1);
        }
        gpContractDto.setOldStoreIdArray(oldStoreIdArray);

        if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0)
        {
            fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
        }
        gpContractDto.setFileRecordMainIds(fileRecordMainIds);

        resultData.setReturnData(gpContractDto);
        return resultData;
    }

    /**
     * 列表
     *
     * @param param
     * @return
     */
    public ResultData<List<GpContract>> queryList(Map<?, ?> param) {
        ResultData<List<GpContract>> resultData = new ResultData<>();
        List<GpContract> gpContractList = gpContractMapper.queryList(param);
        if (null != gpContractList && gpContractList.size() > 0) {
            resultData.setReturnData(gpContractList);
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        }
        return resultData;
    }

    public ResultData<Integer> updateFlowId(GpContractDto gpContractDto) throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);
        Integer rel = gpContractMapper.updateFlowIdById(gpContract);
        if (rel > 0) {
            resultData.setSuccess();
            resultData.setReturnData(1);
        } else {
            resultData.setFail("修改失败");
        }
        return resultData;
    }

    /**
     * 审核
     * @param gpContractDto
     * @return
     */
    public ResultData<Integer> audit(GpContractDto gpContractDto) {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);
        // 合同状态为审核中
        gpContract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDITING);

        // 锁定客户(公司更新为已签约)
        Company company = new Company();
        company.setId(gpContract.getCompanyId());
        company.setCompanyStatus(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
        company.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
        companyMapper.update(company);
        // 更新合同状态
        int count = gpContractMapper.update(gpContract);
        gpContractStoreMapper.updateByContractId(gpContract.getId());
        resultData.setReturnData(count);
        return resultData;
    }

    public ResultData<Integer> updateStatus(GpContractDto gpContractDto) {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);
        // 更新合同状态
        Integer count = gpContractMapper.update(gpContract);
        // 合同作废后 解除门店合同关系
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("gpContractId", gpContract.getId());
        gpContractStoreMapper.updateFlag(param);
        resultData.setReturnData(count);
        return resultData;
    }

    public void invalid(int id) throws Exception {
        List<GpContractStore> contractStoreList = gpContractStoreMapper.selStoreByContractId(id);
        for (GpContractStore aContractStoreList : contractStoreList) {
            Store store = new Store();
            store.setStoreId(aContractStoreList.getStoreId());
            store.setStoreStatus(0);
            store.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_N);
            storeMapper.update(store);
        }
    }

    /**
     * 取得OA审批结果信息
     * @return OA审批结果信息
     * @throws Exception
     */
    public List<OaGpContractReturn>getOAGpContractReturn()throws Exception
    {
        // 查询操作
        final List<OaGpContractReturn> oaResult = oaGpContractReturnMapper.getOAGpContractReturn();
        return oaResult;

    }

    /**
     * 更新OA审批结果
     * @param oAContractReturn
     * @return 更新数
     * @throws Exception
     */
    public Integer updateOAContractReturn(OaGpContractReturn oAContractReturn) throws Exception
    {

        Integer rtn = oaGpContractReturnMapper.updateOAGpContractReturn(oAContractReturn);
        return rtn;
    }
    public Integer updateOAContractChangeReturn(OaGpContractChangeReturn oAContractReturn) throws Exception
    {
    	
    	Integer rtn = oaGpContractReturnMapper.updateOAContractChangeReturn(oAContractReturn);
    	return rtn;
    }

    /**
     * 根据flowId获取公盘合同
     * @param flowId
     * @return
     * @throws Exception
     */
    public GpContract getByFlowId(String flowId) throws Exception
    {
        GpContract contract = gpContractMapper.getByFlowId(flowId);
        return contract;
    }
    /**
     * 根据flowId获取公盘合同终止信息
     * @param flowId
     * @return
     * @throws Exception
     */
    public GpContractChange getContractChangeByFlowId(String flowId) throws Exception
    {
    	GpContractChange contract = gpContractMapper.getContractChangeByFlowId(flowId);
    	return contract;
    }

    public void updateContractStatusByFlowId(GpContract gpContract) throws Exception {
        gpContractMapper.updateContractStatusByFlowId(gpContract);
    }

    // 根据合同flowID查询合同中的门店
    public List<GpContractStore> getContractStoresByFlowId(String flowId) throws Exception
    {
        List<GpContractStore> contractStoreList = gpContractStoreMapper.getContractStoresByFlowId(flowId);
        return contractStoreList;
    }
    /**
     * 根据公司ID查询合同状态为未签，审核未通过的合同
     */      
    public ResultData<List<GpContract>> getGpContractByCompanyId(Integer companyId)
            throws Exception   {
        //构建返回
        ResultData<List<GpContract>> resultData = new ResultData<List<GpContract>>();
        List<GpContract> list = gpContractMapper.getGpContractByCompanyId(companyId);
        resultData.setReturnData(list);
        return resultData;
    }
    /**
     * 修改公司-->
     * 修改公盘合同里未签，审核未通过的公盘合同的公司信息
     * @return
     */
    
    public ResultData<Integer> updateByGpContractId(GpContractDto gpContractDto)
        throws Exception {
    	//构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        GpContract gpContract = new GpContract();
        //赋值
        BeanUtils.copyProperties(gpContractDto, gpContract);
        String partyBCityNo = gpContract.getPartyBCityNo();
        if (!"".equals(partyBCityNo) && null != partyBCityNo) {
            String cityName = SystemParam.getCityNameByCityNo(partyBCityNo);
            gpContract.setPartyBCityName(cityName);
        }
        String partyBDistrictNo = gpContract.getPartyBDistrictNo();
        if (!"".equals(partyBDistrictNo) && null != partyBDistrictNo) {
            String districtName = SystemParam.getDistrictNameByDistrictNo(partyBDistrictNo);
            gpContract.setPartyBDistrictName(districtName);
        }
        Integer count = gpContractMapper.update(gpContract);
        resultData.setReturnData(count);
        return resultData;
    }
    
    /** 
     * @Description: 修改门店信息修改公盘合同里的门店信息
     */
     public ResultData updateByGpStoreId(String param)throws Exception {
 	   	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
 	   	ResultData<Integer> resultData = new ResultData<Integer>();
 		resultData.setFail();
 		//查询草签和审核未通过的公盘合同
 		if(queryParam.containsKey("storeId")) {
 			String storeId = queryParam.get("storeId").toString();
 			//目前公盘合同中只有地址详情
 			Store dbStore = storeMapper.getById(Integer.valueOf(storeId));
 			String addressDetail = dbStore.getAddressDetail();
 			queryParam.put("addressDetail", addressDetail);
 			int count  = gpContractMapper.updateByGpStoreId(queryParam);
 			resultData.setSuccess();
 			resultData.setReturnData(count);
 		}
        return resultData;
     }
     public List<GpContract> selectNewestGpContractByCompanyId(Integer companyId) {
     	return  gpContractMapper.selectNewestGpContractByCompanyId(companyId);
     }
     public GpContract selectNewestGpContract(Integer storeId) {
         return gpContractMapper.selectNewestGpContract(storeId);
     }

	public List<OaGpContractChangeReturn> getOAGpContractChangeReturn() {
		 // 查询操作
        final List<OaGpContractChangeReturn> oaResult = oaGpContractReturnMapper.getOAGpContractChangeReturn();
        return oaResult;
	}
	public void updateContractChangeStatusByFlowId(GpContractChange gpContract) throws Exception {
		gpContractMapper.updateContractChangeStatusByFlowId(gpContract);
    }
	/**
	 * 保存银行信息
	 * @param param
	 * @return
	 */
	public ResultData saveBankInfoAdjut(Map<String,Object> reqMap)throws Exception{
		ResultData resultData = new ResultData();
		resultData.setFail();
		//获取参数
		Integer companyId = Integer.valueOf((String)reqMap.get("companyId"));
		String partyB = reqMap.get("partyB").toString();
		Integer gpContractId = Integer.valueOf((String)reqMap.get("gpContractId"));
		/*String gpContractNo = reqMap.get("gpContractNo").toString();
		String oldAccountNm = reqMap.get("oldAccountNm").toString();
		String oldAccountProvinceNo = reqMap.get("oldAccountProvinceNo").toString();
		String oldAccountProvinceNm = reqMap.get("oldAccountProvinceNm").toString();
		String oldAccountCityNo = reqMap.get("oldAccountCityNo").toString();
		String oldAccountCityNm = reqMap.get("oldAccountCityNm").toString();
		String oldBankAccountNm = reqMap.get("oldBankAccountNm").toString();
		String oldBankAccount = reqMap.get("oldBankAccount").toString();*/
		String accountNm = reqMap.get("accountNm").toString();
		String accountProvinceNo = reqMap.get("accountProvinceNo").toString();
		String accountProvinceNm = reqMap.get("accountProvinceNm").toString();
		String accountCityNo = reqMap.get("accountCityNo").toString();
		String accountCityNm = reqMap.get("accountCityNm").toString();
		String bankAccountNm = reqMap.get("bankAccountNm").toString();
		String bankAccount = reqMap.get("bankAccount").toString();
		//附件id
		String fileRecordMainIds = (String)reqMap.get("fileRecordMainIds");
		//操作人
        String userCode = (String)reqMap.get("userCode");
        String userName = (String)reqMap.get("userName");
        Integer userIdCreate = Integer.valueOf((String)reqMap.get("userIdCreate"));
        //查询公盘合同列表中所有的这个公司
        List<GpContract> gpContractList = gpContractMapper.getGpContractByCompanyInfo(companyId);
        if(gpContractList != null && gpContractList.size() > 0) {
        	for (GpContract gp : gpContractList) {
        		//更新公盘主表信息
        		GpContract gpContract = new GpContract();
        		gpContract.setId(gp.getId());
        		gpContract.setAccountNm(accountNm);
        		gpContract.setAccountProvinceNm(accountProvinceNm);
        		gpContract.setAccountProvinceNo(accountProvinceNo);
        		gpContract.setAccountCityNm(accountCityNm);
        		gpContract.setAccountCityNo(accountCityNo);
        		gpContract.setBankAccountNm(bankAccountNm);
        		gpContract.setBankAccount(bankAccount);
        		Integer count = gpContractMapper.update(gpContract);
        		
        		// 账户变更申请书
                FileRecordMain accountFile = new FileRecordMain();
                accountFile.setRefId(gp.getId());
                accountFile.setIsDelete(false);
                List<FileRecordMain> fileList = fileRecordMainMapper.getAccountChangeByGpContractId(accountFile);
        		
        		//变更内容
        		String message ="公盘"+ gp.getGpContractNo() +"的银行信息变更内容： 开户名 "
        				+gp.getAccountNm() +"-->"+accountNm+" 开户省市 "+gp.getAccountProvinceNm() +gp.getAccountCityNm()
        				+"-->"+accountProvinceNm+accountCityNm+" 开户行 "+
        				gp.getBankAccountNm()+"-->"+bankAccountNm +" 银行账号 "
        				+gp.getBankAccount()+"-->"+bankAccount;
        		//添加银行信息变更详情日志
        		GpBankInfoChangeLog log = new GpBankInfoChangeLog();
        		log.setCompanyId(companyId);
        		log.setPartyB(partyB);
        		log.setGpContractId(gp.getId());
        		log.setGpContractNo(gp.getGpContractNo());
        		log.setChangeContent(message);
        		log.setOldAccountNm(gp.getAccountNm());
        		log.setOldAccountProvinceNm(gp.getAccountProvinceNm());
        		log.setOldAccountProvinceNo(gp.getAccountProvinceNo());
        		log.setOldAccountCityNo(gp.getAccountCityNo());
        		log.setOldAccountCityNm(gp.getAccountCityNm());
        		log.setOldBankAccountNm(gp.getBankAccountNm());
        		log.setOldBankAccount(gp.getBankAccount());
        		log.setAccountNm(accountNm);
        		log.setAccountProvinceNm(accountProvinceNm);
        		log.setAccountProvinceNo(accountProvinceNo);
        		log.setAccountCityNo(accountCityNo);
        		log.setAccountCityNm(accountCityNm);
        		log.setBankAccountNm(bankAccountNm);
        		log.setBankAccount(bankAccount);
        		log.setUserCode(userCode);
        		log.setUserIdCreate(userIdCreate);
        		log.setUserName(userName);
        		log.setDateCreate(new Date());
        		log.setDelFlag("0");
        		if( gp.getId() == gpContractId ){
        			log.setStartFlag(0);
        		}else {
        			log.setStartFlag(1);
        		}
        		int insertSelective = gpBankInfoChangeLogMapper.insertSelective(log);
        		System.out.println(insertSelective);
        		Integer gpBankInfoChangeLogId = log.getId();
        		// 关联相关文件(RefId)
        		if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
    				String[] arrays = fileRecordMainIds.split(",");
    				for (int i = 0; i < arrays.length; i++) {
    					if (StringUtil.isNotEmpty(arrays[i])) {
    						GpBankInfoChangeLogFile gpBankInfoLogFile = new GpBankInfoChangeLogFile();
    	        			gpBankInfoLogFile.setFileRecordMainId(Integer.valueOf(arrays[i]));
    						gpBankInfoLogFile.setGpBankInfoChangeLogId(gpBankInfoChangeLogId);
    						gpBankInfoLogFile.setGpContractId(gp.getId());
    						gpBankInfoLogFile.setDateCreate(new Date());
    						gpBankInfoLogFile.setDelFlag("0");
    						gpBankInfoLogFile.setUserIdCreate(userIdCreate);
    						int insertSelective2 = gpBankInfoChangeLogFileMapper.insertSelective(gpBankInfoLogFile);
    						System.out.println(insertSelective2);
    					}
    				}
    			}
			}
        }
		resultData.setSuccess("修改成功！");
		return resultData;
	}
	/**
	 * 根据用户id获取中心
	 * @param userId
	 * @throws Exception
	 */
	public List<ExchangeCenter> getLinkUserCenter(Integer userId) throws Exception {
		List<ExchangeCenter> centerList = userMapper.getCenterListByUserId(userId);
		return centerList;
	}

    /**
     * 运营变更合同状态
     * @param gpContractDto
     * @return
     */

    public int operateChangeCt(GpContractDto gpContractDto)
            throws Exception {
        GpContract contract = new GpContract();
        //赋值
        Integer id = gpContractDto.getId();
        contract.setId(id);
        // 合同状态变为审核不通过
        contract.setContractStatus(DictionaryConstants.CONTRACT_STATUS_AUDIT_NO_PASS);
        contract.setDateUpdate(new Date());
        contract.setUserUpdate(gpContractDto.getSubmitOAUserId());
        // 更新合同状态
        int count = gpContractMapper.update(contract);
        if(count>0){
            gpContractMapper.insertContractReturn(id);
        }
        return count;
    }


    public ResultData<BigDecimal> queryDeposit(Map<String, Object> queryParam) {
        ResultData<BigDecimal> resultData = new ResultData<BigDecimal>();
        BigDecimal deposit = gpContractMapper.queryDeposit(Integer.valueOf(String.valueOf(queryParam.get("companyId"))));
        if (null != deposit)
        {
            resultData.setReturnData(deposit);
        }else{
            resultData.setReturnData(new BigDecimal(0));
        }
        return resultData;
    }

    public ResultData<GpContractDto> queryFileList(Map<String, Object> param) throws Exception {
        ResultData<GpContractDto> resultData = new ResultData<GpContractDto>();
        GpContractDto gpContractDto = new GpContractDto();
        //根据companyId获取最新审核通过的refId
        Integer companyId = Integer.valueOf(String.valueOf(param.get("companyId")));
        GpContractDto gpDto = gpContractMapper.getRefIdByCompanyId(companyId);
        // 获取文件信息
        String fileRecordMainIds = "";
        if(null!=gpDto){
            // 营业证
            Integer refId = gpDto.getId();
            Integer fileSourceId = gpDto.getFileSourceId();
            List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
            FileRecordMain businessFile = new FileRecordMain();
            businessFile.setRefId(refId);
            businessFile.setFileSourceId(fileSourceId);
            businessFile.setIsDelete(false);
            List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByGpContractIdTwo(businessFile);
            fileRecordMainIds = contractService.pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);

            // 身份证
            List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
            FileRecordMain cardFile = new FileRecordMain();
            cardFile.setRefId(refId);
            cardFile.setFileSourceId(fileSourceId);
            cardFile.setIsDelete(false);
            List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByGpContractIdTwo(cardFile);
            fileRecordMainIds = contractService.pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);

            gpContractDto.setFileRecordMainIds(fileRecordMainIds);
            gpContractDto.setFileBusinessList(fileBusinessList);
            gpContractDto.setFileIdCardList(fileCardList);
            resultData.setReturnData(gpContractDto);
        }
        return resultData;
    }

    public void updateTypeByGpContractNo(String gpContractNo) {
        gpContractStoreMapper.updateTypeByGpContractNo(gpContractNo);
    }
}
