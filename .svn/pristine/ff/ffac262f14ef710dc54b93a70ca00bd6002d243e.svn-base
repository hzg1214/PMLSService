package cn.com.eju.deal.service.gpSignContract;

import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
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
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContract.dao.GpContractMapper;
import cn.com.eju.deal.gpContract.dao.GpContractStoreMapper;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContract.model.GpContractDto;
import cn.com.eju.deal.gpContract.model.GpContractStore;
import cn.com.eju.deal.mapper.gpSignContract.GpSignContractMapper;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.model.signContract.ContractStoreNewDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;

import com.alibaba.fastjson.JSON;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by xu on 2017/5/31.
 */
@Service("gpSignContractService")
public class GpSignContractService {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private GpContractMapper gpContractMapper;
    @Resource
    private GpSignContractMapper gpSignContractMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;
    @Resource
    private GroupMapper groupMapper;
    @Resource
    private AchievementService achievementService;
    @Resource
    private CompanyMapper companyMapper;
    @Resource
    private ISeqNoAPI seqNoAPI;
    @Resource
    private GpContractStoreMapper gpContractStoreMapper;
    @Resource
    private StoreMapper storeMapper;
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private ContactsMapper contactsMapper;
    @Resource
    private ContractService contractService;
	@Resource
	private UserMapper userMapper;

    public ResultData<List<CompanyNewDto>> getCompanyList(CompanyNewDto dto) throws Exception {
        ResultData<List<CompanyNewDto>> resultData = new ResultData<List<CompanyNewDto>>();
        List<CompanyNewDto> list = gpSignContractMapper.getCompanyList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    public ResultData<List<StoreNewDto>> getRelativeStoreList(CompanyNewDto dto) throws Exception {
        ResultData<List<StoreNewDto>> resultData = new ResultData<>();
        List<StoreNewDto> list = gpSignContractMapper.getRelativeStoreList(dto);
        resultData.setReturnData(list);
        return resultData;
    }

    public ResultData addRelativeStore(CompanyNewDto dto) throws Exception {
        ResultData<Object> resultData = new ResultData<>();
        List<StoreNewDto> storeList = gpSignContractMapper.getSignedStoreByCompanyId(dto);
        List<CompanyNewDto> list = new ArrayList<>();
        String[] storeIds = dto.getStoreIdStr().split(",");
        if (storeList != null && storeList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (StoreNewDto store : storeList) {
                boolean contains = Arrays.asList(storeIds).contains(store.getStoreId().toString());
                if (!contains) {
                    sb.append(store.getStoreName() + "（" + store.getStoreNo() + "），");
                }
            }
            int length = sb.length();
            if (length > 0) {
                sb.deleteCharAt(length - 1).append("已签约，不能取消关联！");
                resultData.setFail(sb.toString());
                return resultData;
            }
        }

        for (String storeId : storeIds) {
            CompanyNewDto companyNewDto = new CompanyNewDto();
            companyNewDto.setStoreId(storeId);
            companyNewDto.setCompanyId(dto.getCompanyId());
            companyNewDto.setUserCreate(dto.getUserCreate());
            list.add(companyNewDto);
        }
        gpSignContractMapper.addRelativeStore(list);
        return resultData;
    }

    public ResultData<CompanyNewDto> getCompanyById(CompanyNewDto dto) throws Exception {
        ResultData<CompanyNewDto> resultData = new ResultData<CompanyNewDto>();

        //modify by haidan 2019-09-17 校验公司和门店所属城市是否一致
        int acCount = gpSignContractMapper.checkAcCityNoForCompany(dto);
        if (acCount > 0) {
            resultData.setFail("该公司所属城市对应门店的所属城市不一致，不能签约！");
            return resultData;
        }

        CompanyNewDto companyNewDto = gpSignContractMapper.getCompanyById(dto);
        resultData.setReturnData(companyNewDto);
        return resultData;
    }

    /**
     * 验证协议书编号是否重复
     */
    public ResultData checkAgreementNo(ContractNewDto dto) throws Exception {
        ResultData resultData = new ResultData();
        List<GpContract> dataList = gpContractMapper.getListByAgreementNo(dto.getAgreementNo());
        if (null != dataList && dataList.size() > 0) {
            resultData.setFail();
        }
        return resultData;
    }

    /**
     * 新增合同为草签
     */
    public ResultData addContract(ContractNewDto dto) throws Exception {
        //构建返回
        ResultData<Integer> resultData = new ResultData<>();
        //协议书编号重复check
        List<GpContract> dataList = gpSignContractMapper.getListByAgreementNo(dto);
        if (null != dataList && dataList.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (GpContract info : dataList) {
                sb.append(info.getGpContractNo()).append(",");
            }
            String contractNo = "";
            if (sb.length() > 0) {
                contractNo = sb.substring(0, sb.length() - 1);
            }
            String strMsg = "系统中存在相同协议书编号的公盘合同，合同编号为" + contractNo + "。请勿重复提交！";
            resultData.setFail(strMsg);
            return resultData;
        }

        if (dto.getContractId() == 0) {
            return addContract(dto, resultData);
        } else {
            return updateContract(dto, resultData);
        }
    }

    private ResultData updateContract(ContractNewDto dto, ResultData<Integer> resultData) throws Exception {
        //查询数据库中的合同信息
        GpContractDto gpContractDto = gpContractMapper.getByGpId(dto.getContractId());
        //版本号是否一致的check
        Integer dbContractVersion = gpContractDto.getContractVersion();
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
        GpContract gpContract = ContractNewDto.convertToGpContract(dto);
        Integer rel = gpContractMapper.update(gpContract);
        resultData.setReturnData(rel);

        // 更新门店 、门店合同
        List<GpContractStore> contractStoreList = gpContractStoreMapper.selStoreByContractId(gpContract.getId());
        if (null != contractStoreList && !contractStoreList.isEmpty()) {
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
        List<ContractStoreNewDto> storeList = JSON.parseArray(dto.getStoreListJsonStr(), ContractStoreNewDto.class);
        List<ContactsDto> contactsDtoList = new ArrayList<>();
        if (null != storeList && !storeList.isEmpty()) {
            for (ContractStoreNewDto storeDto : storeList) {
                // 建立门店合同关系
                Integer storeId = Integer.valueOf(storeDto.getStoreId());
                Store dbStore = storeMapper.getById(storeId);
                GpContractStore contractStore = new GpContractStore();
                contractStore.setGpContractId(gpContract.getId());
                contractStore.setStoreId(storeId);
                contractStore.setIsDelete(false);
                contractStore.setStoreState(0);
                contractStore.setAddressDetail(dbStore.getAddressDetail());
                gpContractStoreMapper.create(contractStore);

                dbStore.setStoreStatus(0);
                dbStore.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
                storeMapper.update(dbStore);

                //更新门店负责人、负责人电话
                if(!storeDto.getContactsName().equals(dbStore.getStoreManager()) || !storeDto.getContactsPhone().equals(dbStore.getStoreManagerPhone())){
                    Map<String, Object> storeMap = new HashMap<>();
                    storeMap.put("storeId", storeId);
                    storeMap.put("storeManager", storeDto.getContactsName());
                    storeMap.put("storeManagerPhone", storeDto.getContactsPhone());
                    storeMapper.updateStoreManager(storeMap);
                }
            }
        }

        // 关联相关文件(RefId)
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
        //对原业绩进行删除
        int reg = gpContractMapper.deleteAchivAchievementByrelateId(gpContract.getId());
        if (reg > 0) {
            //考核主体
            String cityNoString;
            if (gpContract.getCenterId() != null) {
                cityNoString = groupMapper.getCityNoByGroupId(gpContract.getCenterId());
                if (StringUtil.isNotEmpty(cityNoString)) {
                    //根据业绩城市编号查询其核算主体
                    List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
                    if (accountProjectList.size() > 0) {
                        String accountProject = accountProjectList.get(0).getAccountProject();
                        String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
                        //合同新增时候保存其核算主体及其编号，核算主体及其编号
                        if (!Objects.equals(accountProject, "")) {
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
            	User user = userMapper.getUserByUserId(Integer.valueOf(dto.getExpandingPersonnelId()));
        		String userCode = user.getUserCode();
                Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_GP, userCode, dto.getExpandingPersonnel(),
                        dto.getContractId(), dto.getCenterId(), dto.getAccountProject(), dto.getAccountProjectNo());
                if (!isSaved) {
                    logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                            "公盘合同新增-保存业绩归属信息失败！contractId：" + gpContract.getId(), null);
                }
            } catch (Exception e) {
                logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                        "公盘合同修改-保存业绩归属信息失败！contractId：" + gpContract.getId(), e);
            }
        }
        return resultData;
    }

    private ResultData addContract(ContractNewDto dto, ResultData<Integer> resultData) throws Exception {
        if (dto.getContractId() == 0) {
            //公盘合同编号
            ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_GPCONTRACT");
            dto.setContractNo(data.getReturnData());
        }

        //考核主体
        if (dto.getCenterId() != null) {
            String cityNoString = groupMapper.getCityNoByGroupId(dto.getCenterId());
            if (StringUtil.isNotEmpty(cityNoString)) {
                //根据业绩城市编号查询其核算主体
                List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
                if (accountProjectList.size() > 0) {
                    String accountProject = accountProjectList.get(0).getAccountProject();
                    String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
                    //合同新增时候保存其核算主体及其编号，核算主体及其编号
                    if (!Objects.equals(accountProject, "")) {
                        dto.setAccountProject(accountProject);
                    }
                    if (!Objects.equals(accountProjectNo, "")) {
                        dto.setAccountProjectNo(accountProjectNo);
                    }
                }
            }
        }
        // 转成GpContract对象
        GpContract gpContract = ContractNewDto.convertToGpContract(dto);
        //公盘合同编号
        String gpContractNo = seqNoAPI.getSeqNoByTypeCode("TYPE_GPCONTRACT").getReturnData();
        gpContract.setGpContractNo(gpContractNo);
        Integer rel = gpContractMapper.create(gpContract);
        resultData.setReturnData(rel);

        //获取合同门店关系信息 — 包含联系人信息
        List<StoreDto> storeList = new ArrayList<>();
        List<ContactsDto> contactsDtoList = new ArrayList<>();
        if (dto.getStoreListJsonStr() != null && !"".equals(dto.getStoreListJsonStr())) {
            List<ContractStoreNewDto> list = JSON.parseArray(dto.getStoreListJsonStr(), ContractStoreNewDto.class);
            dto.setContractStoreNewDtoList(list);
        }
        List<ContractStoreNewDto> list = dto.getContractStoreNewDtoList();
        if (list != null && list.size() > 0) {
            // 联系人编号
            for (ContractStoreNewDto contractStoreNewDto : list) {
                Integer storeId = Integer.valueOf(contractStoreNewDto.getStoreId());
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreId(storeId);
                storeList.add(storeDto);

                if (dto.getContractId() == 0) {
                    String contactsNo = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT").getReturnData();
                    // 创建联系人
                    ContactsDto contactsDto = new ContactsDto();
                    contactsDto.setContactsNo(contactsNo);
                    contactsDto.setStoreId(storeId);
                    contactsDto.setName(contractStoreNewDto.getContactsName());
                    contactsDto.setMobilePhone(contractStoreNewDto.getContactsPhone());
                    contactsDto.setUserCreate(dto.getUserCreate());
                    contactsDto.setDateCreate(new Date());
                    contactsDtoList.add(contactsDto);
                }
            }
        }

        // 联系人信息
        if (null != contactsDtoList && !contactsDtoList.isEmpty()) {
            for (ContactsDto contactsDto : contactsDtoList) {
                Store store = storeMapper.getById(contactsDto.getStoreId());
                String storeManager = store.getStoreManager();
                String storeManagerPhone = store.getStoreManagerPhone();
                if (!contactsDto.getName().equals(storeManager) || !contactsDto.getMobilePhone().equals(storeManagerPhone)) {
                    Contacts contacts = new Contacts();
                    BeanUtils.copyProperties(contactsDto, contacts);
                    contactsMapper.create(contacts);

                    //更新门店负责人、负责人电话
                    Map<String, Object> map = new HashMap<>();
                    map.put("storeId", contactsDto.getStoreId());
                    map.put("storeManager", contactsDto.getName());
                    map.put("storeManagerPhone", contactsDto.getMobilePhone());
                    storeMapper.updateStoreManager(map);
                }
            }
        }

        // 建立门店合同关系
        if (null != storeList && !storeList.isEmpty()) {
            for (StoreDto storeDto : storeList) {
                Store dbStore = storeMapper.getById(storeDto.getStoreId());
                // 建立门店合同关系
                GpContractStore contractStore = new GpContractStore();
                contractStore.setGpContractId(gpContract.getId());
                contractStore.setStoreId(storeDto.getStoreId());
                contractStore.setIsDelete(false);
                // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
                contractStore.setStoreState(0);
                contractStore.setAddressDetail(dbStore.getAddressDetail());
                gpContractStoreMapper.create(contractStore);

                dbStore.setStoreStatus(0);
                dbStore.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
                storeMapper.update(dbStore);

                Map<String, Object> reqMap = new HashMap<>();
                reqMap.put("storeId", storeDto.getStoreId());
                reqMap.put("contractId", gpContract.getId());
                reqMap.put("companyId", gpContract.getCompanyId());
                Integer contractIdRef = gpContractStoreMapper.getContractStoreContractId(reqMap);
                if (contractIdRef != null && contractIdRef > 0) {
                    GpContractStore cs = new GpContractStore();
                    cs.setGpContractId(contractIdRef);
                    cs.setStoreId(storeDto.getStoreId());
                    cs.setFlag(1);
                    gpContractStoreMapper.update(cs);
                }
            }
        }

        // 更新公司为已签约
        Company dbCompany = companyMapper.getById(dto.getCompanyId());
        dbCompany.setIsGpSign(DictionaryConstants.DIC_CODE_COMPANY_STATUS_Y);
        companyMapper.update(dbCompany);
        //---------GUOWei  修改---------Bug   Start----2019-06-05
        // 拿默认的图片ids
        Integer gpContractId = gpContract.getId();
        FileRecordMain attachmentFile = new FileRecordMain();
        GpContractDto gpDto = gpContractMapper.getRefIdByCompanyId(dto.getCompanyId());
        List<FileRecordMain> fileMdlList = new ArrayList<FileRecordMain>();
        List<FileRecordMain> fileMdlList2 = new ArrayList<FileRecordMain>();
        if(null!=gpDto){
	        Integer refId = gpDto.getId();
	        Integer fileSourceId = gpDto.getFileSourceId();
	        attachmentFile.setRefId(refId);
	        attachmentFile.setFileSourceId(fileSourceId);
	        attachmentFile.setIsDelete(false);
	        fileMdlList = fileRecordMainMapper.getBusinessByGpContractIdTwo(attachmentFile);
	        fileMdlList2 = fileRecordMainMapper.getCardByGpContractIdTwo(attachmentFile);
        }
        // 关联相关文件(RefId)
        String fileRecordMainIds = dto.getFileRecordMainIds();
        if (fileRecordMainIds != null && !"".equals(fileRecordMainIds)) {
            String[] fileRecordMainIdArray = fileRecordMainIds.split(",");
            for (int i = 0; i < fileRecordMainIdArray.length; i++) {
                String fileRecordMainId = fileRecordMainIdArray[i];

                if (fileRecordMainId != null && !"".equals(fileRecordMainId) && !"undefined".equals(fileRecordMainId)) {
                	 boolean falg = false;                  
	                    //排除从公盘带过来的file
	                 if(fileMdlList!=null && fileMdlList.size()>0)  { 
	                	 for (FileRecordMain FileRecordMain:fileMdlList) {
	                        if(fileRecordMainId.equals(String.valueOf(FileRecordMain.getFileRecordMainId()))){
                                copeFileRecordByGpContractFile(dto.getCompanyId(),gpContractId,FileRecordMain,1);
                                falg=true;
	                        }
	                    }
	                 }
	                  if(fileMdlList2!=null && fileMdlList2.size()>0) {
	                	  for (FileRecordMain FileRecordMain:fileMdlList2) {	                
	                        if(fileRecordMainId.equals(String.valueOf(FileRecordMain.getFileRecordMainId()))){
                                copeFileRecordByGpContractFile(dto.getCompanyId(),gpContractId,FileRecordMain,2);
                                falg=true;
	                        }
	                    }
	                  }
	                    if(!falg){	                    	
	                      FileRecordMain fileRecordMain = new FileRecordMain();
	                      fileRecordMain.setFileRecordMainId(Integer.parseInt(fileRecordMainId));
	                      fileRecordMain.setRefId(gpContract.getId());
	                      fileRecordMain.setIsDelete(false);
	                      fileRecordMainMapper.update(fileRecordMain);
	                    }
	                }
                }
         }
        String delFileRecordMainIds = dto.getDelFileRecordMainIds();
        if (delFileRecordMainIds != null && !"".equals(delFileRecordMainIds)) {
            String[] delFileRecordMainIdArray = delFileRecordMainIds.split(",");
            for (int i = 0; i < delFileRecordMainIdArray.length; i++) {
                String fileRecordMainId = delFileRecordMainIdArray[i];
                if (fileRecordMainId != null && !"".equals(fileRecordMainId) && !"undefined".equals(fileRecordMainId)) {
                	  boolean falg = false;   

 	                 if(fileMdlList!=null && fileMdlList.size()>0)  { 
                	  for (FileRecordMain FileRecordMain:fileMdlList) {
	                        if(fileRecordMainId.equals(String.valueOf(FileRecordMain.getFileRecordMainId()))){
                              falg=true;
	                        }
	                    }
 	                 }
	                  if(fileMdlList2!=null && fileMdlList2.size()>0) {
	                    for (FileRecordMain FileRecordMain:fileMdlList2) {
	                        if(fileRecordMainId.equals(String.valueOf(FileRecordMain.getFileRecordMainId()))){
                              falg=true;
	                        }
	                    }
	                  }
                	if(!falg){
                        FileRecordMain fileRecordMain = new FileRecordMain();
                        fileRecordMain.setFileRecordMainId(Integer.parseInt(fileRecordMainId));
                        fileRecordMain.setRefId(null);
                        fileRecordMain.setIsDelete(true);
                        fileRecordMainMapper.update(fileRecordMain);                		
                	}
                }
            }
        }

        //---------GUOWei  修改---------Bug   End----2019-06-05
        //保存业绩归属
        try {
        	
        	User user = userMapper.getUserByUserId(Integer.valueOf(dto.getExpandingPersonnelId()));
    		String userCode = user.getUserCode();
            Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_GP, userCode, dto.getExpandingPersonnel(),
            		 gpContract.getId(), dto.getCenterId(), dto.getAccountProject(), dto.getAccountProjectNo());
            if (!isSaved) {
                logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null,
                        "公盘合同新增-保存业绩归属信息失败！contractId：" + gpContract.getId(), null);
            }
        } catch (Exception e) {
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

    /**
     * 根据条件查询 签约合同列表
     */
    public ResultData<List<ContractNewDto>> getTodoList(ContractNewDto dto) throws Exception {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list = gpSignContractMapper.getTodoList(dto);
        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    /**
     * 根据合同ID 获取合同详情信息
     */
    public ResultData<ContractNewDto> getGpContractById(int id) throws Exception {
        ResultData<ContractNewDto> resultData = new ResultData<>();
        GpContractDto gpContractDto = gpContractMapper.getByGpId(id);
        String partyAddressDetail = gpContractDto.getPartyBCityName() + gpContractDto.getPartyBDistrictName() + gpContractDto.getPartyBAddress();
        gpContractDto.setPartyAddressDetail(partyAddressDetail);

        // 获取门店信息
        String oldStoreIdArray = "";
        List<StoreNewDto> storeList = new ArrayList<>();
        List<StoreDto> storeMdlList = gpContractMapper.selectStoreByContractId(id);
        for (StoreDto dbStore : storeMdlList) {
            StoreNewDto storeNewDto = new StoreNewDto();
            BeanUtils.copyProperties(dbStore, storeNewDto);
            storeNewDto.setStoreName(dbStore.getName());
            storeList.add(storeNewDto);
            oldStoreIdArray = oldStoreIdArray + dbStore.getStoreId() + ",";
        }

        // 获取文件信息
        String fileRecordMainIds = "";
        // 营业证
        List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
        FileRecordMain businessFile = new FileRecordMain();
        businessFile.setRefId(id);
        businessFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByGpContractId(businessFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);

        // 身份证
        List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
        FileRecordMain cardFile = new FileRecordMain();
        cardFile.setRefId(id);
        cardFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByGpContractId(cardFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);

        // 合同照片
        List<ContractFileDto> filePhotoList = new ArrayList<ContractFileDto>();
        FileRecordMain photoFile = new FileRecordMain();
        photoFile.setRefId(id);
        photoFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByGpContractId(photoFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList3, fileRecordMainIds, filePhotoList);

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

        // 其他文件
        List<ContractFileDto> fileOtherList = new ArrayList<ContractFileDto>();
        FileRecordMain otherFile = new FileRecordMain();
        otherFile.setRefId(id);
        otherFile.setIsDelete(false);
        List<FileRecordMain> fileMdlList6 = fileRecordMainMapper.getOtherByGpContractId(otherFile);
        fileRecordMainIds = contractService.pushFileRecord(fileMdlList6, fileRecordMainIds, fileOtherList);

        if (StringUtil.isNotEmpty(oldStoreIdArray) && oldStoreIdArray.length() > 0) {
            oldStoreIdArray = oldStoreIdArray.substring(0, oldStoreIdArray.length() - 1);
        }
        gpContractDto.setOldStoreIdArray(oldStoreIdArray);

        if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
            fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
        }

        // gpContractDto转成ContractNewDto对象
        ContractNewDto contractNewDto = GpContractDto.convertToContractNewDto(gpContractDto);
        //获取翻牌到账保证金
        BigDecimal deposit = gpContractMapper.queryDeposit(gpContractDto.getCompanyId());
        contractNewDto.setDzDepositFee(deposit == null ? 0 : deposit.doubleValue());
        contractNewDto.setStoreList(storeList);
        contractNewDto.setFileRecordMainBusiness(fileBusinessList);
        contractNewDto.setFileRecordMainCard(fileCardList);
        contractNewDto.setFileRecordMainComplement(filePhotoList);
        contractNewDto.setFileRecordMainCheck(fileCheckList);
        contractNewDto.setFileRecordMainRule(fileRuleList);
        contractNewDto.setFileRecordMainOther(fileOtherList);
        contractNewDto.setFileRecordMainIds(fileRecordMainIds);
        resultData.setReturnData(contractNewDto);
        return resultData;
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
}
