package cn.com.eju.deal.gpCsMemberContract.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.achievement.service.AchievementService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.UploadFileUtil;
import cn.com.eju.deal.common.util.XmlTransferUtil;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.SystemCfg;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.gpContract.model.GpContractDto;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeMapper;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeStoreMapper;
import cn.com.eju.deal.gpCsMemberContract.dao.GpCsMemberContractMapper;
import cn.com.eju.deal.gpCsMemberContract.dao.OaGpCsMemberContractReturnMapper;
import cn.com.eju.deal.gpCsMemberContract.model.GpCsMemberContract;
import cn.com.eju.deal.gpCsMemberContract.model.GpCsMemberContractDto;
import cn.com.eju.deal.gpCsMemberContract.model.OaGpCsMemberContractReturn;
import cn.com.eju.deal.houseLinkage.report.dao.SendContractFangyouLogMapper;
import cn.com.eju.deal.houseLinkage.report.model.SendContractFangyouLog;
import cn.com.eju.deal.open.controller.UploadOAUtil;
import cn.com.eju.deal.open.util.OaAttachmentUtil;
import cn.com.eju.deal.user.dao.GroupMapper;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;

import org.springframework.stereotype.Service;
import org.springframework.web.context.ContextLoader;

import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;

import javax.annotation.Resource;

import java.io.File;
import java.io.FilenameFilter;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service("gpCsMemberContractService")
public class GpCsMemberContractService extends BaseService {
	//附件数据Map key
	private final static String ATTACH_KEY = "attachment";
	//表单数据Map key
	private final static String XML_DATA_KEY = "xmlData";
	//日期
	private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";

	//组装表单数据 -修改表单 Map key
	private final static String FLAG_MODIFY_KEY = "oaModify";

	@Resource
	private GpCsMemberContractMapper gpCsMemberContractMapper;
	@Resource
	private GpContractChangeMapper gpContractChangeMapper;
	@Resource
	private GpContractChangeStoreMapper gpContractChangeStoreMapper;
	@Resource
	private FileRecordMainMapper fileRecordMainMapper;
	@Resource
	private CitySettingMapper citySettingMapper;

	@Resource
	private FileRecordMainService fileService;
	@Resource
	private ContractMapper contractMapper;
	@Resource
	private OaGpCsMemberContractReturnMapper oaGpCsMemberContractReturnMapper;

	@Resource
	private SeqNoAPIImpl seqNoAPI;
	@Resource(name = "contractService")
	private ContractService contractService;
	@Resource
	private SendContractFangyouLogMapper sendContractFangyouLogMapper;
	@Resource(name = "achievementService")
	private AchievementService achievementService;
	@Resource
	private UserMapper userMapper;
	@Resource(name = "groupMapper")
	private GroupMapper groupMapper;

	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	/**
	 * 查询公盘合同终止列表
	 *
	 * @param reqMap
	 * @return
	 */
	public ResultData<List<GpCsMemberContractDto>> getGpCsMemberContractList(Map<String, Object> reqMap) throws Exception {
		ResultData<List<GpCsMemberContractDto>> resultData = new ResultData<List<GpCsMemberContractDto>>();
		resultData.setFail();
		List<GpCsMemberContractDto> gpCsMemberContractList = gpCsMemberContractMapper.getGpCsMemberContractList(reqMap);
		if (gpCsMemberContractList != null && gpCsMemberContractList.size() > 0) {
			resultData.setReturnData(gpCsMemberContractList);
			resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
			resultData.setSuccess();
		}
		return resultData;
	}

	/**
	 * 保存或更新公盘初始会员合同
	 */
	public ResultData<?> saveGpCsMemberContract(Map<String, Object> reqMap) throws Exception {
		ResultData<String> resultData = new ResultData<>();
		GpCsMemberContract gpCsMemberContract = new GpCsMemberContract();

		//公司相关信息
		String partyB = (String) reqMap.get("partyB");
		Integer companyId = Integer.valueOf(reqMap.get("companyId").toString());
		String companyNo = (String) reqMap.get("companyNo");
		//协议书编号
		String agreementNo = (String) reqMap.get("agreementNo");
		//公司法人
		String legalPerson = (String) reqMap.get("legalPerson");
		//公司注册地址相关信息
		String partyBCityNo = (String) reqMap.get("partyBcityNo");
		String partyBCityName = (String) reqMap.get("partyBCityName");
		String partyBDistrictNo = (String) reqMap.get("partyBdistrictNo");
		String partyBDistrictName = (String) reqMap.get("partyBDistrictName");
		String partyBAddress = (String) reqMap.get("partyBAddress");
		//统一社会信用代码
		String registerId = (String) reqMap.get("registerId");
		//我方全称
		Integer ourFullId = Integer.valueOf(reqMap.get("ourFullId").toString());
		String ourFullName = (String) reqMap.get("ourFullName");
		//初始会员费
		String csMemberAmount = (String) reqMap.get("csMemberAmount");
		String partyBNm = (String) reqMap.get("partyBNm");//甲方授权代表
		String partyBTel = (String) reqMap.get("partyBTel");//甲方联系方式
		String accountNm = (String) reqMap.get("accountNm");//开户名
		String accountProvinceNo = (String) reqMap.get("accountProvinceNo");//开户省份
		String accountProvinceNm = (String) reqMap.get("accountProvinceNm");
		String accountCityNo = (String) reqMap.get("accountCityNo");//开户城市
		String accountCityNm = (String) reqMap.get("accountCityNm");
		String bankAccountNm = (String) reqMap.get("bankAccountNm");//开户行
		String bankAccount = (String) reqMap.get("bankAccount");//银行账户
		String remark = (String) reqMap.get("remark");//备注
		String cityNo = (String) reqMap.get("cityNo");//城市编号
		Integer userIdCreate = Integer.valueOf(reqMap.get("userIdCreate").toString());
		//附件id集
		String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();
		String type = (String) reqMap.get("type");//更新或新增
		//check agreementNo是否重复,新建是直接
		Map<String, Object> hashMap = new HashMap<>();
		if ("update".equals(type)) {
			Integer id = Integer.valueOf((String) reqMap.get("id"));
			hashMap.put("id", id);
		}
		hashMap.put("agreementNo", agreementNo);
		List<GpCsMemberContract> dataList = gpCsMemberContractMapper.getListByAgreementNo(hashMap);
		if (null != dataList && dataList.size() > 0) {
			String strGpCsMemberContractNo = "";
			for (GpCsMemberContract cs : dataList) {
				strGpCsMemberContractNo += cs.getGpCsMemberContractNo() + ",";
			}

			if (strGpCsMemberContractNo.length() > 0) {
				strGpCsMemberContractNo = strGpCsMemberContractNo.substring(0, strGpCsMemberContractNo.length() - 1);
			}
			String strMsg = "系统中存在相同协议书编号的公盘初始会员合同，编号为" + strGpCsMemberContractNo + "。请勿重复提交！";
			resultData.setReturnData("0");
			resultData.setFail();
			resultData.setReturnMsg(strMsg);
			return resultData;
		}

		//id、No、公司id、公司名称、协议书编号
		gpCsMemberContract.setPartyB(partyB);
		gpCsMemberContract.setPartyBCityNo(partyBCityNo);
		gpCsMemberContract.setPartyBCityName(partyBCityName);
		gpCsMemberContract.setPartyBDistrictNo(partyBDistrictNo);
		gpCsMemberContract.setPartyBDistrictName(partyBDistrictName);
		gpCsMemberContract.setPartyBAddress(partyBAddress);
		gpCsMemberContract.setCompanyId(companyId);
		gpCsMemberContract.setCompanyNo(companyNo);
		gpCsMemberContract.setLegalPerson(legalPerson);
		gpCsMemberContract.setRegisterId(registerId);
		gpCsMemberContract.setOurFullId(ourFullId);
		gpCsMemberContract.setOurFullName(ourFullName);
		gpCsMemberContract.setAgreementNo(agreementNo);
		gpCsMemberContract.setCsMemberAmount(new BigDecimal(csMemberAmount));
		gpCsMemberContract.setPartyBNm(partyBNm);
		gpCsMemberContract.setPartyBTel(partyBTel);
		gpCsMemberContract.setAccountNm(accountNm);
		gpCsMemberContract.setAccountProvinceNo(accountProvinceNo);
		gpCsMemberContract.setAccountProvinceNm(accountProvinceNm);
		gpCsMemberContract.setAccountCityNo(accountCityNo);
		gpCsMemberContract.setAccountCityNm(accountCityNm);
		gpCsMemberContract.setBankAccountNm(bankAccountNm);
		gpCsMemberContract.setBankAccount(bankAccount);
		gpCsMemberContract.setRemark(remark);
		gpCsMemberContract.setApproveState(0);
		gpCsMemberContract.setSubmitOAStatus(21201);
		gpCsMemberContract.setDelFlag("0");

		Integer centerId = Integer.valueOf((String) reqMap.get("centerId"));
		String centerName = (String) reqMap.get("centerName");
		String exPersonId = (String) reqMap.get("exPersonId");
		String exPerson = (String) reqMap.get("exPerson");
		gpCsMemberContract.setCenterId(centerId);
		gpCsMemberContract.setCenterName(centerName);
		gpCsMemberContract.setExPerson(exPerson);
		gpCsMemberContract.setExPersonId(exPersonId);

		//考核主体
		String cityNoString = groupMapper.getCityNoByGroupId(centerId);
		if (StringUtil.isNotEmpty(cityNoString)) {
			//根据业绩城市编号查询其核算主体
			List<Contract> accountProjectList = contractMapper.queryAccountProject(cityNoString);
			if (accountProjectList.size() > 0) {
				String accountProject = accountProjectList.get(0).getAccountProject();
				String accountProjectNo = accountProjectList.get(0).getAccountProjectNo();
				//合同新增时候保存其核算主体及其编号，核算主体及其编号
				if (!Objects.equals(accountProject, "")) {
					gpCsMemberContract.setAccountProject(accountProject);
				}
				if (!Objects.equals(accountProjectNo, "")) {
					gpCsMemberContract.setAccountProjectNo(accountProjectNo);
				}
			}
		}
		User user = userMapper.getUserByUserId(Integer.valueOf(exPersonId));
		String userCode = user.getUserCode();
		if ("create".equals(type)) {
			if (reqMap.containsKey("gpCsMemberContractNo")) {

				//申请编号
				String gpCsMemberContractNo = (String) reqMap.get("gpCsMemberContractNo");
				gpCsMemberContract.setUserIdCreate(userIdCreate);
				gpCsMemberContract.setDateCreate(new Date());
				gpCsMemberContract.setCityNo(cityNo);
				gpCsMemberContract.setGpCsMemberContractNo(gpCsMemberContractNo);
				//保存
				Integer gpContractId = Integer.valueOf((String) reqMap.get("gpContractId"));
				int count = gpCsMemberContractMapper.insertSelective(gpCsMemberContract);
				if (count <= 0) {
					resultData.setFail("保存公盘初始会员合同失败");
					return resultData;
				}
				Integer gpCsMemberContractId = gpCsMemberContract.getId();
				createFileRecordByGpContract(companyId, gpContractId, gpCsMemberContractId);
				if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
					String[] arrays = fileRecordMainIds.split(",");
					// 关联相关文件(RefId)
					for (int i = 0; i < arrays.length; i++) {
						if (StringUtil.isNotEmpty(arrays[i])) {
							FileRecordMain fileRecordMain = new FileRecordMain();
							fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
							fileRecordMain.setRefId(gpCsMemberContractId);
							fileRecordMain.setIsDelete(false);
							fileRecordMain.setCompanyId(companyId);
							fileRecordMainMapper.update(fileRecordMain);
						}
					}
				}
			} else {
				resultData.setFail("保存公盘初始会员合同失败,无申请编号");
				return resultData;
			}
		} else if ("update".equals(type)) {
			String oldfileRecordMainIds = reqMap.get("oldfileRecordMainIds").toString();
			//变更主键
			Integer id = Integer.valueOf((String) reqMap.get("id"));
			gpCsMemberContract.setUserIdUpt(userIdCreate);
			gpCsMemberContract.setDateUpt(new Date());
			gpCsMemberContract.setId(id);
			int count = gpCsMemberContractMapper.updateByPrimaryKeySelective(gpCsMemberContract);
			if (count <= 0) {
				resultData.setFail("更新公盘初始会员合同失败");
				return resultData;
			}
			//对原上传文件删除(附件营业执照、法人身份证、公盘系统服务协议无需处理)
			if (oldfileRecordMainIds != null && StringUtil.isNotEmpty(oldfileRecordMainIds)) {
				String[] oldfileRecordMainIdsArray = oldfileRecordMainIds.split(",");
				for (int i = 0; i < oldfileRecordMainIdsArray.length; i++) {
					if (StringUtil.isNotEmpty(oldfileRecordMainIdsArray[i])) {
						FileRecordMain fileRecordMain = new FileRecordMain();
						fileRecordMain.setFileRecordMainId(Integer.valueOf(oldfileRecordMainIdsArray[i]));
						fileRecordMain.setRefId(null);
						fileRecordMain.setIsDelete(true);
						fileRecordMainMapper.update(fileRecordMain);
					}
				}
			}
			// 关联相关文件(RefId)
			if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
				String[] arrays = fileRecordMainIds.split(",");
				for (int i = 0; i < arrays.length; i++) {
					if (StringUtil.isNotEmpty(arrays[i])) {
						FileRecordMain fileRecordMain = new FileRecordMain();
						fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
						fileRecordMain.setRefId(Integer.valueOf(id));
						fileRecordMain.setIsDelete(false);
						fileRecordMainMapper.update(fileRecordMain);
					}
				}
			}
			//原业绩进行删除
			int reg = gpCsMemberContractMapper.deleteAchivAchievementByrelateId(id);

		}
		//保存业绩归属
		try {
			Boolean isSaved = achievementService.saveContractAchievementInfo(DictionaryConstants.ACHIEVETYPE_CS, userCode, exPerson, gpCsMemberContract.getId(), centerId, gpCsMemberContract.getAccountProject(), gpCsMemberContract.getAccountProjectNo());
			if (!isSaved) {
				logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null, "公盘初始会员合同-保存业绩归属信息失败！gpCsmemberId：" + gpCsMemberContract.getId(), null);
			}
		} catch (Exception e) {
			logger.error("achievement", "achievementService", "saveAchievementInfo", null, null, null, "公盘初始会员合同-保存业绩归属信息失败！gpCsmemberId：" + gpCsMemberContract.getId(), e);
		}
		resultData.setSuccess();
		return resultData;
	}

	/**
	 * 关联最新公盘的附件：营业执照、法人身份证、公盘系统服务协议
	 *
	 * @param gpCsMemberContractId
	 */
	private void createFileRecordByGpContract(Integer companyId, Integer gpContractId, Integer gpCsMemberContractId) throws Exception {
		FileRecordMain attachmentFile = new FileRecordMain();
		attachmentFile.setRefId(gpContractId);
		attachmentFile.setIsDelete(false);
		List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByGpContractId(attachmentFile);
		copeFileRecordByGpContractFile(companyId, gpCsMemberContractId, fileMdlList, 1057);
		List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByGpContractId(attachmentFile);
		copeFileRecordByGpContractFile(companyId, gpCsMemberContractId, fileMdlList2, 1052);
		List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByGpContractId(attachmentFile);
		copeFileRecordByGpContractFile(companyId, gpCsMemberContractId, fileMdlList3, 1053);
	}

	private void copeFileRecordByGpContractFile(Integer companyId, Integer gpCsMemberContractId, List<FileRecordMain> fileMdlList, Integer integer) {
		if (null != fileMdlList && fileMdlList.size() > 0) {
			for (FileRecordMain fileRecordMain2 : fileMdlList) {
				fileRecordMain2.setFileTypeId(integer);
				fileRecordMain2.setFileSourceId(16);
				fileRecordMain2.setRefId(gpCsMemberContractId);
				fileRecordMain2.setCompanyId(companyId);
				fileRecordMainMapper.create(fileRecordMain2);
			}
		}
	}

	/**
	 * 根据id查询公盘初始会员合同详情
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ResultData<GpCsMemberContractDto> getGpCsMemberContractById(Integer id) throws Exception {
		//构建返回
		ResultData resultData = new ResultData();
		resultData.setFail();
		GpCsMemberContractDto gpCsMemberContractDto = gpCsMemberContractMapper.getGpCsMemberContractById(id);
		if (gpCsMemberContractDto != null) {
			/**
			 *  获取文件信息：营业执照、法人身份证、公盘系统服务协议、
			 *  				初始会员协议、初始会员费付款凭证、其他
			 */
			String fileRecordMainIds = "";
			FileRecordMain attachmentFile = new FileRecordMain();
			attachmentFile.setRefId(id);
			attachmentFile.setIsDelete(false);
			//营业执照
			List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
			List<FileRecordMain> fileMdlList1 = fileRecordMainMapper.getCsMemberFileBusinessListById(attachmentFile);
			fileRecordMainIds = pushFileRecord(fileMdlList1, fileRecordMainIds, fileBusinessList);
			//法人身份证
			List<ContractFileDto> fileIdCardList = new ArrayList<ContractFileDto>();
			List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCsMemberFileIdCardListById(attachmentFile);
			fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, fileIdCardList);
			//公盘系统服务协议 fileContractList
			List<ContractFileDto> fileContractList = new ArrayList<ContractFileDto>();
			List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getCsMemberFileContractListById(attachmentFile);
			fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, fileContractList);
			//初始会员协议
			List<ContractFileDto> csMemberFileList = new ArrayList<ContractFileDto>();
			List<FileRecordMain> fileMdlList4 = fileRecordMainMapper.getCsMemberFileListById(attachmentFile);
			fileRecordMainIds = pushFileRecord(fileMdlList4, fileRecordMainIds, csMemberFileList);
			//初始会员费付款凭证
			List<ContractFileDto> csMemberPayFileList = new ArrayList<ContractFileDto>();
			List<FileRecordMain> fileMdlList5 = fileRecordMainMapper.getCsMemberPayFileListById(attachmentFile);
			fileRecordMainIds = pushFileRecord(fileMdlList5, fileRecordMainIds, csMemberPayFileList);
			//附件
			List<ContractFileDto> othersFileList = new ArrayList<ContractFileDto>();
			List<FileRecordMain> fileMdlList6 = fileRecordMainMapper.getCsMemberOthersFileListById(attachmentFile);
			fileRecordMainIds = pushFileRecord(fileMdlList6, fileRecordMainIds, othersFileList);
			if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
				fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
			}
			gpCsMemberContractDto.setFileBusinessList(fileBusinessList);
			gpCsMemberContractDto.setFileIdCardList(fileIdCardList);
			gpCsMemberContractDto.setFileContractList(fileContractList);
			gpCsMemberContractDto.setCsMemberFileList(csMemberFileList);
			gpCsMemberContractDto.setCsMemberPayFileList(csMemberPayFileList);
			gpCsMemberContractDto.setOthersFileList(othersFileList);
			gpCsMemberContractDto.setFileRecordMainIds(fileRecordMainIds);
			//返回
			resultData.setReturnData(gpCsMemberContractDto);
			resultData.setSuccess();
		}
		return resultData;
	}

	/**
	 * 图片信息
	 *
	 * @param
	 * @return
	 */
	private String pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds, List<ContractFileDto> fileList) throws Exception {
		if (null != fileMdlList && fileMdlList.size() > 0) {
			for (int i = 0; i < fileMdlList.size(); i++) {
				ContractFileDto contractFileDto = new ContractFileDto();
				FileRecordMain fileRecordMain = fileMdlList.get(i);
				//对文件数据进行组装处理
				handleFileRecordMain(contractFileDto, fileRecordMain);
				//重新组装返回list
				fileList.add(contractFileDto);
				//对营业执照、法人身份证、公盘服务协议不拼接
				if (!"1057".equals(fileRecordMain.getFileTypeId() + "") && !"1052".equals(fileRecordMain.getFileTypeId() + "") && !"1053".equals(fileRecordMain.getFileTypeId() + "")) {
					fileRecordMainIds = fileRecordMainIds + contractFileDto.getFileRecordMainId() + ",";
				}
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
	private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain) throws Exception {
		//获取图片路径
		contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
		contractFileDto.setFileName(fileRecordMain.getFileFullName());
		contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
		contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
		contractFileDto.setUrl50(fileRecordMain.getUrl50());
	}

	/**
	 * 公盘提交OA审核
	 *
	 * @param map
	 * @return
	 */
	public ResultData<String> submitGpCsMemberContractOa(Map<String, Object> map) {
		ResultData<String> resultData = new ResultData<>();
		resultData.setReturnMsg("公盘初始会员协议申请提交成功");
		String errorMsg = "";
		if (map.containsKey("id")) {
			Integer id = Integer.valueOf(map.get("id").toString());
			Integer userIdCreate = Integer.valueOf(map.get("userIdCreate").toString());
			String userCode = map.get("userCode").toString();
			String userName = map.get("userName").toString();
			//String cityNo = map.get("cityNo").toString();
			GpCsMemberContractDto gpCsMemberContract = new GpCsMemberContractDto();
			gpCsMemberContract.setId(id);
			//根据id查询
			GpCsMemberContractDto gpCsMemberContractDto = gpCsMemberContractMapper.getGpCsMemberContractById(id);
			Integer approveState = gpCsMemberContractDto.getApproveState();
			if (approveState == 1 || approveState == 2) {
				resultData.setFail("该公盘初始会员协议申请已提交OA审核，请刷新数据！");
				return resultData;
			}
			String oaTemplateCode = "";//模板编号
			String busCodeString = "";//事业部
			String busNoCity = "";//事业部城市
			String oaString = "";
			Map<String, Object> oaMap = new HashMap<String, Object>();
			//组装表单发起数据
			try {
				//准备发起OA
				//公盘业绩城市
				String cityNo = gpCsMemberContractDto.getCityNo();
				// 根据城市编号获取模板
				Map<String, Object> oaTempLateCodeMap = getOaTempLateCode(cityNo);

				if (oaTempLateCodeMap == null) {
					resultData.setFail();
					resultData.setReturnMsg("根据城市编号获取模板失败");
					return resultData;
				}
				oaTemplateCode = oaTempLateCodeMap.get("oaTemplateCode").toString();
				busCodeString = oaTempLateCodeMap.get("busCodeString").toString();
				//改为城市名称
				busNoCity = SystemParam.getCityNameByCityNo(cityNo);
				map.put("cityNo", cityNo);
				map.put("oaTemplateCode", oaTemplateCode);
				map.put("busNo", busCodeString);//事业部
				map.put("busNoCity", busNoCity);//城市名字
				//更新
				gpCsMemberContract.setSubmitOAStatus(21202);
				gpCsMemberContract.setSubmitTime(new Date());
				gpCsMemberContract.setSubmitOAUserId(userIdCreate);
				gpCsMemberContract.setDateUpt(new Date());
				gpCsMemberContract.setUserIdUpt(userIdCreate);
				gpCsMemberContract.setApproveState(1);
				int count = gpCsMemberContractMapper.updateByPrimaryKeySelective(gpCsMemberContract);
				//组装数据
				Map<String, Object> setToOaInfo = setToOaInfo(oaMap, map, oaTemplateCode, gpCsMemberContractDto);
				oaString = setToOaInfo.get("oaNo").toString();
			} catch (Exception e1) {
				logger.error("gpCsMemberContract", "GpCsMemberContractService", "setToOaInfo", "", userIdCreate, "", "", e1);
				gpCsMemberContract.setApproveState(0);
				gpCsMemberContract.setSubmitOAStatus(21204);//处理失败
				gpCsMemberContract.setDateUpt(new Date());
				gpCsMemberContract.setUserIdUpt(userIdCreate);
				this.gpCsMemberContractMapper.updateByPrimaryKeySelective(gpCsMemberContract);
				resultData.setFail();
				resultData.setReturnMsg("组装公盘合同终止数据错误");
				return resultData;
			}
			logger.info("CRM 提交审核 gpContractStopSubmitOa reqMap=", map);
			//发起申请 返回FlowId
			Long flowId;
			try {
				flowId = this.toOaAuth(oaMap, oaTemplateCode);
				map.put("flowId", flowId);
			} catch (Exception e) {
				gpCsMemberContract.setApproveState(0);
				gpCsMemberContract.setSubmitOAStatus(21204);//处理失败
				gpCsMemberContract.setDateUpt(new Date());
				gpCsMemberContract.setUserIdUpt(userIdCreate);
				this.gpCsMemberContractMapper.updateByPrimaryKeySelective(gpCsMemberContract);
				resultData.setFail();
				resultData.setReturnMsg("向OA发起提交审核失败");
				return resultData;
			}
			//更新
			gpCsMemberContract.setOaNo(oaString);
			gpCsMemberContract.setSubmitOAStatus(21203);
			gpCsMemberContract.setSubmitOAUserId(userIdCreate);
			gpCsMemberContract.setUserIdUpt(userIdCreate);
			gpCsMemberContract.setFlowId(flowId + "");
			this.gpCsMemberContractMapper.updateByPrimaryKeySelective(gpCsMemberContract);
		} else {
			resultData.setFail("参数错误");
			return resultData;
		}
		return resultData;
	}

	//获取模板编号
	private Map<String, Object> getOaTempLateCode(String gpContractCityNo) {
		Map<String, Object> hashMap = new HashMap<>();
		Map<String, Object> reposeMap = citySettingMapper.getCitySettingByCityNo(gpContractCityNo);

		String busCodeString = "";
		String oaTemplateCode = "";
		if (null != reposeMap) {
			oaTemplateCode = reposeMap.get("gpCsMemberContractTpl").toString();
			busCodeString = reposeMap.get("busCode").toString();
			hashMap.put("oaTemplateCode", oaTemplateCode);
			hashMap.put("busCodeString", busCodeString);
		}
		return hashMap;
	}

	/**
	 * 组装表单发起数据
	 *
	 * @param id
	 * @param oaMap
	 * @param reqMap                传入参数
	 * @param gpCsMemberContractDto
	 * @param templateCode          模板
	 */
	private Map<String, Object> setToOaInfo(Map<String, Object> oaMap, Map<String, Object> reqMap, String oaTemplateCode, GpCsMemberContractDto gpCsMemberContractDto) throws Exception {
		String userCode = reqMap.get("userCode").toString();
		//末班编号
		reqMap.put("templateCode", oaTemplateCode);
		//发起者的登录名（登录协同的登录名）
		oaMap.put("senderLoginName", userCode);
		//协同的标题
		oaMap.put("subject", "公盘初始会员协议报批单");
		//表单数据、附件数据
		Map<String, Object> rspMap = setOaAuditData(reqMap, gpCsMemberContractDto);
		//附件，Long型数组，值为附件的Id。
		oaMap.put("attachments", rspMap.get(ATTACH_KEY));
		//HTML正文流程为html内容；表单流程为XML格式的表单数据
		oaMap.put("data", rspMap.get(XML_DATA_KEY));
		//为控制是否流程发送。0：缺省值，发送，进入下一节点的待办（如果需要选人则保存到待发）1：不发送，保存到待发。
		oaMap.put("state", "0");
		return rspMap;
	}

	/**
	 * 组装发送OA审核的 表单数据、附件数据
	 *
	 * @param gpCsMemberContractDto
	 * @param id
	 * @return
	 */
	private Map<String, Object> setOaAuditData(Map<String, Object> reqMap, GpCsMemberContractDto ctDto) throws Exception {
		//返回表单数据、返回附件数据
		Map<String, Object> rspMap = new HashMap<String, Object>();
		String dateHtml = "";
		String cityNo = reqMap.get("cityNo").toString();
		String cityName = reqMap.get("cityName").toString();
		ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_GPCSMEMBEROA");
		String oaNo = "";
		if (data != null && data.getReturnCode().equals("200")) {
			oaNo = data.getReturnData();
			rspMap.put("oaNo", oaNo);
		} else {
			new NullPointerException("根据seq_type: TYPE_GPCTSTOPOA 获取公盘初始会员合同OA编号为空");
		}

		//申请人姓名
		String userName = reqMap.get("userName").toString();
		//申请人工号
		String userCode = reqMap.get("userCode").toString();
		//创建时间
		Date date = new Date();
		//事业部编号
		String busNo = (String) reqMap.get("busNo");
		String bussineArea = busNo;
		//事业部可改为城市名称 String busNoCity = (String)reqMap.get("busNoCity");
		//我方签约单位
		String configValue = SystemParam.getWebConfigValue(bussineArea);
		String companyNo = "";
		String companyName = "";
		if (configValue != null && "" != configValue && configValue.contains("|")) {
			String[] values = configValue.split("\\|");
			//公司编码
			companyNo = values[0];
			//公司名称
			companyName = values[1];
		}
		//我方全称
		String ourFullName = ctDto.getOurFullName();
		//公司名称
		String partyB = ctDto.getPartyB();
		//公盘初始会员编号
		String gpCsMemberContractNo = ctDto.getGpCsMemberContractNo();
		//初始会员费
		BigDecimal csMemberAmount = ctDto.getCsMemberAmount();
		//备注
		String remark = ctDto.getRemark();

		//附件集合
		List<Long> attachList = new ArrayList<Long>();
		String tempFilePath = SystemCfg.getString("tempFilePath");
		tempFilePath = MessageFormat.format(tempFilePath, gpCsMemberContractNo);
		File tempDir = new File(ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath(tempFilePath));
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
		tempFilePath = tempDir + File.separator;
		//营业执照
		this.getOaAttachment(attachList, ctDto.getId(), 1057, userCode, "营业执照_", tempFilePath);
		//法人身份证
		this.getOaAttachment(attachList, ctDto.getId(), 1052, userCode, "法人身份证_", tempFilePath);
		//公盘系统服务协议
		this.getOaAttachment(attachList, ctDto.getId(), 1053, userCode, "公盘系统服务协议_", tempFilePath);
		//初始会员协议
		this.getOaAttachment(attachList, ctDto.getId(), 1054, userCode, "初始会员协议_", tempFilePath);
		//初始会员费付款凭证
		this.getOaAttachment(attachList, ctDto.getId(), 1056, userCode, "初始会员费付款凭证_", tempFilePath);
		//其它
		this.getOaAttachment(attachList, ctDto.getId(), 1055, userCode, "其他_", tempFilePath);
		//文件压缩包上传
		sendZipAttachMentToOa(attachList, tempFilePath, userCode, gpCsMemberContractNo, "易居房产交易公盘初始会员协议报批单");
		dateHtml += "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">" + "<summary id=\"-8787840491192302895\" name=\"formmain_4054\"/>" + "<definitions/>" + "<values>" + "<column name=\"经办人\"><value>" + XmlTransferUtil.transfer(userName) + "</value></column>" + "<column name=\"经办人工号\"><value>" + XmlTransferUtil.transfer(userCode) + "</value></column>" + "<column name=\"城市\"><value>" + XmlTransferUtil.transfer(cityName) + "</value></column>" + "<column name=\"城市编码\"><value>" + XmlTransferUtil.transfer(cityNo) + "</value></column>" + "<column name=\"事业部区域\"><value>" + XmlTransferUtil.transfer(bussineArea) + "</value></column>" + "<column name=\"我方签约单位\"><value>" + XmlTransferUtil.transfer(companyName) + "</value></column>" + "<column name=\"我方全称\"><value>" + XmlTransferUtil.transfer(ourFullName) + "</value></column>" + "<column name=\"合作中介全称\"><value>" + XmlTransferUtil.transfer(partyB) + "</value></column>" + "<column name=\"CRM初始会员合同编号\"><value>" + XmlTransferUtil.transfer(ctDto.getGpCsMemberContractNo()) + "</value></column>" + "<column name=\"备注\"><value>" + XmlTransferUtil.transfer(remark) + "</value></column>" + "<column name=\"初始会员协议\"><value>" + "-1" + "</value></column>" + "<column name=\"其他\"><value>" + "-1" + "</value></column>" + "<column name=\"初始会员费\"><value>" + csMemberAmount + "</value></column>" + "<column name=\"OA编号\"><value>" + XmlTransferUtil.transfer(oaNo) + "</value></column>" + "<column name=\"初始会员协议书编号\"><value>" + XmlTransferUtil.transfer(ctDto.getAgreementNo()) + "</value></column>" + "<column name=\"供应商性质\"><value>" + XmlTransferUtil.transfer("企业法人") + "</value></column>" + "<column name=\"银行所在省份\"><value>" + XmlTransferUtil.transfer(ctDto.getAccountProvinceNm()) + "</value></column>" + "<column name=\"银行账号\"><value>" + XmlTransferUtil.transfer(ctDto.getBankAccount()) + "</value></column>" + "<column name=\"开户银行全称\"><value>" + XmlTransferUtil.transfer(ctDto.getBankAccountNm()) + "</value></column>" + "<column name=\"统一社会信用代码\"><value>" + XmlTransferUtil.transfer(ctDto.getRegisterId()) + "</value></column>" + "<column name=\"银行所在城市\"><value>" + XmlTransferUtil.transfer(ctDto.getAccountCityNm()) + "</value></column>" + "<column name=\"供应商分类ID\"><value>" + XmlTransferUtil.transfer("22007") + "</value></column>" + "<column name=\"我方签约单位编码\"><value>" + XmlTransferUtil.transfer(companyNo) + "</value></column>" + "</values>" + "</formExport>";

		//附件数据
		rspMap.put(ATTACH_KEY, attachList.toArray());
		//表单数据
		rspMap.put(XML_DATA_KEY, dateHtml.toString());
		rspMap.put("cityNo", cityNo);
		logger.info("CRM 提交审核 dateHtml=", dateHtml);
		return rspMap;
	}

	private void getOaAttachment(List<Long> attachList, int refId, Integer fileTypeCode, String userCode, String pre, String tempFilePath) throws Exception {
		// 文件Code
		try {
			Map<String, Object> param = new HashMap<>();
			param.put("refId", refId);
			param.put("fileTypeId", fileTypeCode);
			List<FileRecordMainDto> backResult = fileService.queryList(param);
			for (FileRecordMainDto fileMain : backResult) {
				String fileUrl = fileMain.getFileUrl();

				pre += fileMain.getFileFullName();
				String attachmentId = UploadOAUtil.oaAttachmentUpload(fileUrl, pre, userCode, tempFilePath);
				if (StringUtil.isNotEmpty(attachmentId)) {
					attachList.add(Long.valueOf(attachmentId));
				}
			}
		} catch (Exception e) {
			logger.error("gpCsMemberContract", "GpContractChangeService", "getOaAttachment", "", Integer.valueOf(userCode), "", "上传文件失败！", e);
		}
	}

	public void sendZipAttachMentToOa(List<Long> attachList, String tempFilePath, String userCode, String contractNo, String type) {

		String zipName = type + contractNo + ".zip";

		UploadFileUtil.createZipWithSplit(tempFilePath, tempFilePath + File.separator + zipName, 40);

		// 取得REST动态客户机实例
		CTPRestClient client = getClient();
		// 获取token认证
		String token = client.get("token/" + SystemParam.getWebConfigValue("oaUserName") + "/" + SystemParam.getWebConfigValue("oaPassword"), String.class, "text/plain");

		File d = new File(tempFilePath + File.separator);
		File[] files = d.listFiles(new ZipFileFiltger(type + contractNo));
		for (File f : files) {
			String attachmentId = OaAttachmentUtil.oaZipUpload(SystemParam.getWebConfigValue("oaUrl"), token, userCode, f.getAbsolutePath(), f.getName());
			if (StringUtil.isNotEmpty(attachmentId)) {
				attachList.add(Long.valueOf(attachmentId));
			}
		}
		UploadFileUtil.delFolder(tempFilePath);
	}

	public Long toOaAuth(Map<String, Object> reqMap, String oaTemplateCode) throws Exception {
		logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);
		// 取得REST动态客户机实例
		CTPRestClient client = getClient();
		// token认证
		client.authenticate(SystemParam.getWebConfigValue("oaUserName"), SystemParam.getWebConfigValue("oaPassword"));
		// 表单post
		String url = "/flow/" + oaTemplateCode;
		Long flowId = client.post(url, reqMap, Long.class);

		return flowId;
	}

	private CTPRestClient getClient() {
		// 指定协议、IP和端口，获取ClientManager
		CTPServiceClientManager clientManager = CTPServiceClientManager.getInstance(SystemParam.getWebConfigValue("oaUrl"));
		// 取得REST动态客户机实例
		CTPRestClient client = clientManager.getRestClient();
		return client;
	}

	/**
	 * @param param
	 * @Title: updateStr
	 * @Description: 作废
	 */
	public ResultData updateStatus(String param) throws Exception {
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		Integer resultRow = null;
		ResultData<Object> resultData = new ResultData<Object>();
		resultData.setFail();
		try {
			resultRow = gpCsMemberContractMapper.updateStatus(queryParam);
		} catch (Exception e) {
			logger.error("[pmlsService invoke gpCsMemberContractMapper.updateStatus error...]");
			return resultData;
		}
		if (resultRow == null || resultRow.intValue() == 0) {
			return resultData;
		} else {

		}
		resultData.setSuccess();
		resultData.setReturnData(resultRow);
		return resultData;
	}

	/**
	 * @Description: 通过公司id查询关联公盘列表
	 */
	public ResultData<List<GpContractDto>> getGpInfoByCompanyId(Map<?, ?> param) throws Exception {
		//构建返回
		List<GpContractDto> list = new ArrayList<>();
		ResultData<List<GpContractDto>> resultData = new ResultData<List<GpContractDto>>();
		Integer csMemberCtFlag = this.gpCsMemberContractMapper.getCsMemberCtByCompanyId(param);
		GpContractDto gpContractDto = this.gpCsMemberContractMapper.getGpInfoByCompanyId(param);
		if (null != gpContractDto) {
			// 获取文件信息
			String fileRecordMainIds = "";
			// 营业证
			List<ContractFileDto> fileBusinessList = new ArrayList<ContractFileDto>();
			FileRecordMain businessFile = new FileRecordMain();
			businessFile.setRefId(gpContractDto.getId());
			businessFile.setIsDelete(false);
			List<FileRecordMain> fileMdlList = fileRecordMainMapper.getBusinessByGpContractId(businessFile);
			fileRecordMainIds = contractService.pushFileRecord(fileMdlList, fileRecordMainIds, fileBusinessList);

			// 身份证
			List<ContractFileDto> fileCardList = new ArrayList<ContractFileDto>();
			FileRecordMain cardFile = new FileRecordMain();
			cardFile.setRefId(gpContractDto.getId());
			cardFile.setIsDelete(false);
			List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getCardByGpContractId(cardFile);
			fileRecordMainIds = contractService.pushFileRecord(fileMdlList2, fileRecordMainIds, fileCardList);

			// 公盘系统服务协议
			List<ContractFileDto> filePhotoList = new ArrayList<ContractFileDto>();
			FileRecordMain photoFile = new FileRecordMain();
			photoFile.setRefId(gpContractDto.getId());
			photoFile.setIsDelete(false);
			List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getPhotoByGpContractId(photoFile);
			fileRecordMainIds = contractService.pushFileRecord(fileMdlList3, fileRecordMainIds, filePhotoList);
			if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0) {
				fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
			}
			gpContractDto.setCsMemberCtFlag(csMemberCtFlag);
			gpContractDto.setFileRecordMainIds(fileRecordMainIds);
			gpContractDto.setFileBusinessList(fileBusinessList);
			gpContractDto.setFileIdCardList(fileCardList);
			gpContractDto.setFileContractList(filePhotoList);
			list.add(gpContractDto);
			resultData.setReturnData(list);
		}
		return resultData;
	}

	public List<OaGpCsMemberContractReturn> getOaGpCsMemberContractReturn() {
		// 查询操作
		final List<OaGpCsMemberContractReturn> oaResult = oaGpCsMemberContractReturnMapper.getOaGpCsMemberContractReturn();
		return oaResult;
	}

	public Integer updateOaGpCsMemberContract(OaGpCsMemberContractReturn oaGpCsMember) {
		Integer rtn = oaGpCsMemberContractReturnMapper.updateOaGpCsMemberContract(oaGpCsMember);
		return rtn;
	}

	public Integer updateGpCsMemberStatusByFlowId(GpCsMemberContract gpCsMemberContract) {
		Integer rtn = gpCsMemberContractMapper.updateGpCsMemberStatusByFlowId(gpCsMemberContract);
		return rtn;
	}

	public int createSendContractFangyouLog(SendContractFangyouLog sendContractFangyouLog) {
		int rtn = sendContractFangyouLogMapper.insertSelective(sendContractFangyouLog);
		return rtn;
	}

	public int operateChangeCt(Integer id) {
		int count = gpCsMemberContractMapper.insertGpCsMemberContractReturn(id);
		return count;
	}
}
class ZipFileFiltger implements FilenameFilter{
	private String preName;
	public ZipFileFiltger(String preName) {
		this.preName = preName;
	}
	public boolean accept(File dir, String name) {
		return name.startsWith(preName);
	}
	
}
