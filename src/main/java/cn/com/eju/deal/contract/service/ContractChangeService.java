package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.achievement.dao.AchievementMapper;
import cn.com.eju.deal.achievement.model.Achievement;
import cn.com.eju.deal.base.dto.file.FileDto;
import cn.com.eju.deal.base.file.service.FilesService;
import cn.com.eju.deal.base.file.util.FileAssist;
import cn.com.eju.deal.base.helper.FileHelper;
import cn.com.eju.deal.base.helper.WeiphotoHelper;
import cn.com.eju.deal.base.linkage.dao.CityMapper;
import cn.com.eju.deal.base.linkage.dao.DistrictMapper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.dao.OmsInteractiveMapper;
import cn.com.eju.deal.common.model.Decoration;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.OmsInteractiveService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.company.service.CompanyService;
import cn.com.eju.deal.company.service.CompanyStoreService;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contract.dao.ContractChangeMapper;
import cn.com.eju.deal.contract.dao.ContractChangeStoreMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.model.ContractChangeStore;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.company.CompanyStoreDto;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.op.OpCompanyDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.fangyou.service.FangyouAccountService;
import cn.com.eju.deal.open.model.ContractFlowDto;
import cn.com.eju.deal.store.dao.StoreDecorationMapper;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
import cn.com.eju.deal.store.service.StoreDepositService;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.User;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 合同终止Service层
 *
 * @author sunmm
 * @date 2016年8月3日 下午9:18:01
 */
@Service("stopContractService")
public class ContractChangeService extends BaseService<ContractChange> {

	@Resource
	private ContractMapper contractMapper;

	@Resource
    private ContactsMapper contactsMapper;
	@Resource
	private ContractChangeMapper contractChangeMapper;

	@Resource
	private ContractChangeStoreMapper contractChangeStoreMapper;

	@Resource
    private FileRecordMainMapper fileRecordMainMapper;

    @Resource
    private FilesService filesService;

    @Resource
    private StoreMapper storeMapper;

    @Resource
    private ContractStoreMapper contractStoreMapper;

    @Resource(name = "extOmsService")
	private ExtOmsService extOmsService;

	@Resource(name = "contractService")
	private ContractService contractService;

	@Resource(name = "storeService")
	private StoreService storeService;

	@Resource(name = "companyService")
    private CompanyService companyService;

	@Resource(name = "stopContractService")
    private ContractChangeService stopContractService;

	@Resource(name = "companyStoreService")
    private CompanyStoreService companyStoreService;

	@Resource
	private UserMapper userMapper;

	@Resource(name = "fangyouAccountService")
    private FangyouAccountService fangyouAccountService;

	@Resource
    private ISeqNoAPI seqNoAPI;

	@Resource
	private CityMapper cityMapper;

	@Resource
	private DistrictMapper districtMapper;
	@Resource
	private OmsInteractiveMapper omsInteractiveMapper;

	@Resource
	private OmsInteractiveService omsInteractiveService;

	@Resource(name = "storeDecorationMapper")
    private StoreDecorationMapper storeDecorationMapper;

	@Resource
	private StoreDepositService storeDepositService;

	@Resource
	private AchievementMapper achievementMapper;

	private static final Logger logger = LoggerFactory.getLogger(ContractChangeService.class);

	/**
	 * 根据合同ID查询门店信息
	 *
	 * @param contractId
	 *            合同ID
	 * @param type
	 *            渠道(新增:create 编辑:update)
	 * @param id
	 *            合同变更ID
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<StoreDto>> queryStore(int contractId, String type,
			int id) throws Exception {

		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

		// 获取门店信息
		List<StoreDto> storeList = new ArrayList<StoreDto>();

		List<StoreDto> storeMdlList = new ArrayList<StoreDto>();

		Map<String, Object> param = new HashMap<>();

		int changetype = 0;
		if ("update".equals(type))
		{
			ContractChange contractChange = contractChangeMapper.queryById(id);
			changetype = contractChange.getChangeType();
		}

		// 新增的场合
		if ("create".equals(type)) {

			// 查询操作
			storeMdlList = contractMapper.getByContractIdOfAdd(contractId);
            List<StoreDto> storeDtoList = contractMapper.queryLockingStoreForStop(contractId);

            for(StoreDto mainDto : storeMdlList){
                mainDto.setDisabledFlag("1");
                for(StoreDto dto : storeDtoList){
                    if(mainDto.getStoreId().equals(dto.getStoreId())){
                        mainDto.setDisabledFlag("2");
                    }
                }
            }

			// 更新的场合 （终止）
		} else if ("update".equals(type) && (changetype == 17001)) {

			param.put("contractStopId", id);
			param.put("contractId", contractId);
			// 查询操作
			storeMdlList = contractMapper.getByContractIdOfEdit(param);
            List<StoreDto> storeDtoList = contractMapper.queryLockingStoreForStop(contractId);

            for(StoreDto mainDto : storeMdlList){
                mainDto.setDisabledFlag("1");
                for(StoreDto dto : storeDtoList){
                    if(mainDto.getStoreId().equals(dto.getStoreId())){
                        mainDto.setDisabledFlag("2");
                    }
                }
            }
		}else if ("update".equals(type)  && (changetype == 17002)) {

            param.put("contractStopId", id);
            param.put("contractId", contractId);
            // 查询操作
            storeMdlList = contractMapper.getByContractIdOfView(param);
		}else if ("query".equals(type)) {

            param.put("contractStopId", id);
            param.put("contractId", contractId);
            // 查询操作
            storeMdlList = contractMapper.getByContractIdOfView(param);
        }

		for (int i = 0; i < storeMdlList.size(); i++) {

			StoreDto dbStore = storeMdlList.get(i);

			// 区域名称
			String districtName = SystemParam
					.getDistrictNameByDistrictNo(dbStore.getDistrictNo());
			dbStore.setDistrictName(districtName);

			storeList.add(dbStore);
		}
		resultData.setReturnData(storeList);
		return resultData;
	}

	/**
	 * 保存--合同变更信息
	 *
	 * @param contractInfoDto
	 *            合同变更信息
	 * @return
	 * @throws Exception
	 */
	public int create(ContractInfoDto contractInfoDto) throws Exception {
		ContractChange contractChange = new ContractChange();

		// 赋值
		BeanUtils.copyProperties(contractInfoDto.getContractChangeDto(),
				contractChange);

		// 保存--合同变更信息
		int count = contractChangeMapper.create(contractChange);
		Integer id = contractChange.getId();
		//更新FileRecordMain的refId字段
		String fileRecordMainIds = contractInfoDto.getFileRecordMainIds();

        if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
            String[] arrays = fileRecordMainIds.split(",");
            // 关联相关文件(RefId)
            for (int i = 0; i < arrays.length; i++)
            {
                if (StringUtil.isNotEmpty(arrays[i]))
                {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                    fileRecordMain.setRefId(id);
                    fileRecordMain.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }
    		/*//更新到OaAttachment的合同变更关连键
            String contractChangePicIds = contractInfoDto.getContractChangePicIds();
            
            if (contractChangePicIds != null && StringUtil.isNotEmpty(contractChangePicIds)) {
                String[] contractArrays = contractChangePicIds.split(",");
                // 关联相关文件(RefId)
                for (int i = 0; i < contractArrays.length; i++)
                {
                    if (StringUtil.isNotEmpty(contractArrays[i]))
                    {
                        OaAttachment oaAttachment = new OaAttachment();
                        oaAttachment.setId(Integer.valueOf(contractArrays[i]));
                        oaAttachment.setContractStopId(id);
                        oaAttachment.setDelFlag(false);
                        oaAttachment.setFileRecordMainId(Integer.valueOf(arrays[i]));
                        oaAttachmentMapper.update(oaAttachment);
                    }
                }
            }*/
        }
		// 重新建立新的合同变更与门店的关系
		this.addContractChangeStore(contractInfoDto, contractChange.getId(), contractChange.getContractId());

		return count;
	}

	/**
	 * 更新--合同变更信息
	 *
	 * @param param
	 * @return
	 */

	/**
	 * 更新--合同变更信息 (1.更新ContractChange表记录、2.更新ContractChangeStore记录3.更新OaAttachment 4.更新FileRecordMain)
	 *
	 * @param contractInfoDto
	 * @return
	 * @throws Exception
	 */
	public int update(ContractInfoDto contractInfoDto) throws Exception {
		ContractChange contractChange = new ContractChange();

		// 赋值
		BeanUtils.copyProperties(contractInfoDto.getContractChangeDto(),
				contractChange);

		// 更新ContractChange表记录
		int count = contractChangeMapper.update(contractChange);

		Integer id = contractChange.getId();
        //更新FileRecordMain的refId字段和删除记录
        String fileRecordMainIds = contractInfoDto.getFileRecordMainIds();

        if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
            String[] arrays = fileRecordMainIds.split(",");
            //更新删除记录
            fileRecordMainMapper.updateStatusByContractId(id);

            // 关联相关文件(RefId)
            for (int i = 0; i < arrays.length; i++)
            {
                if (StringUtil.isNotEmpty(arrays[i]))
                {
                    //更新
                    FileRecordMain fileRecordMain2 = new FileRecordMain();
                    fileRecordMain2.setFileRecordMainId(Integer.valueOf(arrays[i]));
                    fileRecordMain2.setRefId(id);
                    fileRecordMain2.setIsDelete(false);
                    fileRecordMainMapper.update(fileRecordMain2);
                }
            }
        }

		// 解除原有的录合同变更与门店的关系
		if (null != contractChange.getId() ) {
            contractChangeStoreMapper.updateById(contractChange.getId());
            // 重新建立新的合同变更与门店的关系
            this.addContractChangeStore(contractInfoDto, contractChange.getId(), contractChange.getContractId());

		}
		return count;
	}

	/**
	 * 建立新的合同变更与门店的关系
	 *
	 * @param contractInfoDto
	 *            合同变更信息
	 * @param contractStopId
	 *            合同变更ID
	 * @param contractId
	 *            合同ID
	 */
	private void addContractChangeStore(ContractInfoDto contractInfoDto,
			int contractStopId, int contractId) throws Exception {
		// 建立合同变更与门店关系
		List<StoreDto> storeList = contractInfoDto.getStoreDetails();
		if (null != storeList && !storeList.isEmpty()) {
			for (StoreDto storeDto : storeList) {
				// 合同变更/门店关联信息
				ContractChangeStore bean = new ContractChangeStore();
				bean.setContractStopId(contractStopId);
				bean.setDecorationType(storeDto.getDecorationType());
				bean.setDecorateSituate(storeDto.getDecorateSituate());
				bean.setDecorateAmount(storeDto.getDecorateAmount());
				bean.setDecorateCompany(storeDto.getDecorateCompany());
				bean.setStoreId(storeDto.getStoreId());
				bean.setReceivedAmount(storeDto.getReceivedAmount());
				bean.setDepositBalance(storeDto.getDepositBalance());
				bean.setDepositBackMoney(storeDto.getDepositBackMoney());
				bean.setDelFlag(false);
				bean.setContractId(contractId);
				bean.setApproveState(0);
				// 入库操作
				contractChangeStoreMapper.create(bean);
			}
		}
	}

	/**
	 * 根据合同变更ID查询门店信息
	 *
	 * @param id
	 *            合同变更ID
	 * @return
	 * @throws Exception
	 */
	public ResultData<ContractChangeDto> queryById(int id) throws Exception {

		// 构建返回
		ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();

		// 查询操作
		ContractChange contractChange = contractChangeMapper.queryById(id);

		ContractChangeDto dto = new ContractChangeDto();
		// Model转换Dto
		BeanUtils.copyProperties(contractChange, dto);

		//查询fileRecordMain获取图片信息组装
		String fileRecordMainIds = "";
		//String contractChangePicIdsDto = "";
		//查询数据组装
        FileRecordMain getFilesParam = new FileRecordMain();
        getFilesParam.setRefId(id);
        getFilesParam.setIsDelete(false);
        //查询数据返回
		List<FileRecordMainDto> fileRecordMainDtoList = fileRecordMainMapper.getContractChangePics(getFilesParam);


		List<FileRecordMain> fileRecordMainList = new ArrayList<>();
		for(FileRecordMainDto fileRecordMainDto :fileRecordMainDtoList){
		    FileRecordMain fileRecordMain = new FileRecordMain();
		    BeanUtils.copyProperties(fileRecordMainDto, fileRecordMain);
		    fileRecordMainList.add(fileRecordMain);
		    //contractChangePicIdsDto = contractChangePicIdsDto + fileRecordMainDto.getPicId()+",";
		}

		//查询返回数据组装
		Map<?, ?> mop = pushFileRecord(fileRecordMainList, fileRecordMainIds,dto);//,contractChangePicIdsDto
		fileRecordMainIds = (String)mop.get("fileRecordMainIds");
		//String contractChangePicIds = (String)mop.get("contractChangePicIds");

		if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0)
        {
            fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
        }
	   /* if (StringUtil.isNotEmpty(contractChangePicIds) && contractChangePicIds.length() > 0)
        {
	        contractChangePicIds = contractChangePicIds.substring(0, contractChangePicIds.length() - 1);
        }*/
		dto.setFileRecordMainIds(fileRecordMainIds);
		//dto.setContractChangePicIds(contractChangePicIds);
		if (null != dto) {
			resultData.setReturnData(dto);
		}

		return resultData;
	}

	/**
	 * 根据合同ID查询合同变更信息
	 *
	 * @param contractId
	 *            合同ID
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<ContractChangeDto>> getContractChange(int contractId)
			throws Exception {

		// 构建返回
		ResultData<List<ContractChangeDto>> resultData = new ResultData<List<ContractChangeDto>>();

		// 查询操作
		List<ContractChangeDto> mdlList = contractChangeMapper.getContractChange(contractId);
		for(ContractChangeDto contractChangeDto : mdlList){
			// 合同变更状态
		    int approveState = contractChangeDto.getApproveState();
		    if(0 == approveState){
		        contractChangeDto.setApproveStateName("待提交审核");
		    }else if(1 == approveState){
		        contractChangeDto.setApproveStateName("审核中");
            }else if(2 == approveState){
                contractChangeDto.setApproveStateName("审核通过");
            }else if(3 == approveState){
                contractChangeDto.setApproveStateName("审核未通过");
            }else{
                contractChangeDto.setApproveStateName("查询状态失败！");
            }
			contractChangeDto.setStopTypeStr(SystemParam.getDicValueByDicCode(contractChangeDto.getStopType().toString()));
		}
		resultData.setReturnData(mdlList);
		return resultData;
	}

	/**
	 * 根据合同ID查询合同变更信息
	 *
	 *            合同ID
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<ContractChangeStore>> getChangeStoresByFlowId(
			String flowId) throws Exception {
		// 构建返回
		ResultData<List<ContractChangeStore>> resultData = new ResultData<List<ContractChangeStore>>();
		List<ContractChangeStore> contractChangeStoreList = contractChangeStoreMapper
				.getChangeStoresByFlowId(flowId);
		resultData.setReturnData(contractChangeStoreList);
		return resultData;
	}

	/**
	 * 根据flowId获取变更审核中的门店变更信息
	 * @param flowId
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<ContractChangeStore>> getChangeStoresByFlowIdApproving(
			String flowId) throws Exception {
		// 构建返回
		ResultData<List<ContractChangeStore>> resultData = new ResultData<List<ContractChangeStore>>();
		List<ContractChangeStore> contractChangeStoreList = contractChangeStoreMapper.getChangeStoresByFlowIdApproving(flowId);
		resultData.setReturnData(contractChangeStoreList);
		return resultData;
	}

	/**
	 * 获取合同变更状态为审核中的FlowIdList
	 *
	 * @param params
	 *            合同变更ID
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<ContractChangeDto>> queryByApproveState(
			Map<String, Object> params) throws Exception {
		// 构建返回
		ResultData<List<ContractChangeDto>> resultData = new ResultData<List<ContractChangeDto>>();
		List<ContractChangeDto> contractChangeDtoList = new ArrayList<ContractChangeDto>();
		// 查询操作
		List<ContractChange> contractChangeList = contractChangeMapper
				.queryByApproveState(params);
		for (ContractChange contractChagne : contractChangeList) {
			ContractChangeDto ccDto = new ContractChangeDto();
			BeanUtils.copyProperties(contractChagne, ccDto);
			contractChangeDtoList.add(ccDto);
		}
		resultData.setReturnData(contractChangeDtoList);
		return resultData;
	}

	/**
	 * 更新 合同变更记录表 审核状态
	 *
	 * @param params
	 * @param passFlag （pass:审核通过 、nopass:审核不通过）
	 * @return
	 * @throws Exception
	 */
	public Integer updateChgApprovaeState(Map<String, Object> params, String passFlag)
			throws Exception {
		// 更新 合同变更记录表 审核状态
		Integer num = contractChangeMapper.updateChgApprovaeState(params);

		// -------------------------------审核通过的场合、向OMS插入审批流程记录 start ----------------------------------//
		if(passFlag.equals("pass")){

			List<ContractFlowDto> dtoList = new ArrayList<ContractFlowDto>();

			Contract contract = new Contract();
			// 1.遍历flowId、根据flowId查询合同信息并插入合同操作流程信息
			String[] flowIdArr = (String[]) params.get("flowIdList");
			for (int i = 0; i < flowIdArr.length; i++) {

				// 2.根据flowId获取合同信息
				contract = contractMapper.getByFlowId(flowIdArr[i]);

				// 合同是非C版的场合
				if (contract.getContractType() != DictionaryConstants.CONTRACT_TYPE_C) {

					// 3.设置合同审批流审批信息
					ContractFlowDto contractFlowDto = this.setContractFlow(contract);

					// 4.根据flowID查询合同变更中的门店
					List<ContractChangeStore> contractChangeStoreList = contractChangeStoreMapper
							.getChangeStoresByFlowId(flowIdArr[i]);

					// 非空判断
					if (null != contractChangeStoreList && !contractChangeStoreList.isEmpty()) {
						for (ContractChangeStore contractStore : contractChangeStoreList) {
							// 根据门店ID查询门店信息
		                	Store store = storeMapper.getById(contractStore.getStoreId());
		                	// 门店ID
		    				contractFlowDto.setStoreNo(store.getStoreNo());
		    				// 门店名称
		    				contractFlowDto.setStoreName(store.getName());
		    				// 门店地址
		    				contractFlowDto.setStoreAddressDetail(store.getAddressDetail());
		    				// 城市编号
		    				contractFlowDto.setCityNo(store.getCityNo());
		    				dtoList.add(contractFlowDto);
						}
					}

				}
			}

			// 非空判断
			if(null != dtoList){

				// 5.新增--合同审批流审批信息（调用OMS接口）
				extOmsService.insertContractFlowDto(dtoList);
			}

		}
		// -------------------------------新增--合同审批流审批信息部分 end ----------------------------------//
		return num;
	}

	/**
	 * 根据合同变更ID查询门店数
	 *
	 * @param contractStopId
	 *            合同变更ID
	 * @return
	 * @throws Exception
	 */
	public int getStopStoreNum(int contractStopId) throws Exception {

		// 查询操作
		int count = contractChangeStoreMapper.getStopStoreNum(contractStopId);

		return count;
	}

	/**
	 * 根据合同变更ID查询门店地址
	 *
	 * @param contractStopId
	 *            合同变更ID
	 * @return
	 * @throws Exception
	 */
	public ResultData<List<StoreDto>> getStoreInfo(int contractStopId) throws Exception {

		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

		List<StoreDto> storeMdlList = new ArrayList<StoreDto>();
		// 查询操作
		storeMdlList = contractMapper.getStoreInfo(contractStopId);

		resultData.setReturnData(storeMdlList);
		return resultData;
	}

	   /**
	    * 更新--将flowId和审核状态(approveState)更新到变更记录中
	    * @param contractInfoDto
	    * @return
	    * @throws Exception
	    */
	    public int updateFlowId(ContractInfoDto contractInfoDto) throws Exception {
	        ContractChange contractChange = new ContractChange();

	        // 赋值
	        BeanUtils.copyProperties(contractInfoDto.getContractChangeDto(),
	                contractChange);

	        // 更新flowId和approveState
	        int count = contractChangeMapper.updateFlowId(contractChange);

	        return count;
	    }

	    /**
	    * 查询该合同ID对应的所有approveState是变更中的门店信息
	    * @param id 合同变更ID
	    * @return
	    * @throws Exception
	    */
	    public ResultData<List<ContractChangeDto>> getContractChangeById(int id)
				throws Exception {

			// 构建返回
			ResultData<List<ContractChangeDto>> resultData = new ResultData<List<ContractChangeDto>>();

			// 查询操作
			List<ContractChangeDto> mdlList = contractChangeMapper.getContractChangeById(id);
			resultData.setReturnData(mdlList);
			return resultData;
		}

	/**
	 * 新增合同操作流程信息
	 *
	 * @param contractId
	 *            合同ID
	 * @param storeIdList
	 *            门店ID集合
	 * @throws Exception
	 */
	public void createContractFlow(Integer contractId, List<Integer> storeIdList)
			throws Exception {

		// 1.根据合同ID查询合同信息
		Contract contract = contractMapper.getContractById(contractId);

		List<ContractFlowDto> dtoList = new ArrayList<ContractFlowDto>();
		// 合同是非C版的场合
		if (contract.getContractType() != DictionaryConstants.CONTRACT_TYPE_C) {
			// 遍历门店ID
			for (Integer storeId : storeIdList) {

				// 2.根据门店ID查询门店信息
            	Store store = storeMapper.getById(storeId);

				// 3.设置合同审批流审批信息
				ContractFlowDto contractFlowDto = this.setContractFlow(contract);
				// 门店ID
				contractFlowDto.setStoreNo(store.getStoreNo());
				// 门店名称
				contractFlowDto.setStoreName(store.getName());
				// 门店地址
				contractFlowDto.setStoreAddressDetail(store.getAddressDetail());
				// 城市编号
				contractFlowDto.setCityNo(store.getCityNo());
				dtoList.add(contractFlowDto);
			}
		}

		// 非空判断
		if(null != dtoList){

			// 3.新增--合同审批流审批信息（调用OMS接口）
			extOmsService.insertContractFlowDto(dtoList);
		}
	}

	/**
	 * 设置合同审批流审批信息
	 *
	 * @param contract 合同信息
	 *            合同信息
	 * @return
	 * @throws Exception
	 */
	private ContractFlowDto setContractFlow(Contract contract) throws Exception {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:sss");
		ContractFlowDto contractFlowDto = new ContractFlowDto();
		// 合同No
		contractFlowDto.setContractNo(contract.getContractNo());
		// 合同类型
		contractFlowDto.setContractType(SystemParam.getDicValueByDicCode(contract
				.getContractType() + ""));
		// 合同状态
		contractFlowDto.setContractState(SystemParam.getDicValueByDicCode(contract
				.getContractStatus() + ""));
		// 公司名称
		contractFlowDto.setCompanyName(contract.getPartyB());
		// 创建人
		contractFlowDto.setUserIdCreate(contract.getUserCreate());
		// 创建时间
		//contractFlowDto.setDateCreate(new Date());
		// 删除标识(false:未删除 true:已删除)
		contractFlowDto.setDelFlag(false);
		// 记录数量
		contractFlowDto.setRecordNum("-1");
		// 负记录原因
		contractFlowDto.setRecordReason("合同终止");
		// 业绩节点确认时间
		contractFlowDto.setPerformDateStr(sdf.format(new Date()));

		return contractFlowDto;
	}

	/*------------------------------------------------私有获取图片数据方法-----------------------------------------------------------------------------------------------------------------------*/
    /**
     * 图片信息
     *
     * @param
     * @return
     */
    private Map<String, String> pushFileRecord(List<FileRecordMain> fileMdlList, String fileRecordMainIds,
        ContractChangeDto contractChangeDto)//, String contractChangePicIdsDto
        throws Exception
    {
        //String contractChangePicIds = "";
		if (null != fileMdlList && fileMdlList.size() > 0) {
			/*if(StringUtil.isNotEmpty(contractChangePicIdsDto)){
                String[] contractChangePicIdList = contractChangePicIdsDto.split(",");*/
			List<ContractFileDto> stopContractFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> surrenderFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> receiptFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> returnProveFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> cancellateFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> photosFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> cooperateFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> oneStopFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> othersFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> changeSupplementFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> newBusinessLicenseFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> informationPublicityFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> confirmationSheetFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> storePhotosFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> changeOthersFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> transferRightsFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> newSigningFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> mainInformationPublicityFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> mainChangeOthersFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> legalCardFileList = new ArrayList<ContractFileDto>();
			List<ContractFileDto> fileNoticeList = new ArrayList<ContractFileDto>();
			for (int i = 0; i < fileMdlList.size(); i++) {
				FileRecordMain fileRecordMain = fileMdlList.get(i);
				Integer fileTypeId = fileRecordMain.getFileTypeId();
				//String picId = contractChangePicIdList[i];
				//对文件数据进行组装处理
				if (fileTypeId == 1001) {
					ContractFileDto stopContractDto = new ContractFileDto();
					handleFileRecordMain(stopContractDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + stopContractDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i] + ",";
					stopContractFileList.add(stopContractDto);
				} else if (fileTypeId == 1002) {
					ContractFileDto surrenderDto = new ContractFileDto();
					handleFileRecordMain(surrenderDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + surrenderDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					surrenderFileList.add(surrenderDto);
				} else if (fileTypeId == 1003) {
					ContractFileDto receiptDto = new ContractFileDto();
					handleFileRecordMain(receiptDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + receiptDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					receiptFileList.add(receiptDto);
				} else if (fileTypeId == 1004) {
					ContractFileDto returnProveDto = new ContractFileDto();
					handleFileRecordMain(returnProveDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + returnProveDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					returnProveFileList.add(returnProveDto);
				} else if (fileTypeId == 1005) {
					ContractFileDto cancellateDto = new ContractFileDto();
					handleFileRecordMain(cancellateDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + cancellateDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					cancellateFileList.add(cancellateDto);
				} else if (fileTypeId == 1006) {
					ContractFileDto photosDto = new ContractFileDto();
					handleFileRecordMain(photosDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + photosDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					photosFileList.add(photosDto);
				} else if (fileTypeId == 1007) {
					ContractFileDto cooperateDto = new ContractFileDto();
					handleFileRecordMain(cooperateDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + cooperateDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					cooperateFileList.add(cooperateDto);
				} else if (fileTypeId == 1008) {
					ContractFileDto oneStopDto = new ContractFileDto();
					handleFileRecordMain(oneStopDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + oneStopDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					oneStopFileList.add(oneStopDto);
				} else if (fileTypeId == 1009) {
					ContractFileDto othersDto = new ContractFileDto();
					handleFileRecordMain(othersDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + othersDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					othersFileList.add(othersDto);
				} else if (fileTypeId == 1010) {
					ContractFileDto changeSupplementDto = new ContractFileDto();
					handleFileRecordMain(changeSupplementDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + changeSupplementDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					changeSupplementFileList.add(changeSupplementDto);
				} else if (fileTypeId == 1011) {
					ContractFileDto newBusinessLicenseDto = new ContractFileDto();
					handleFileRecordMain(newBusinessLicenseDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + newBusinessLicenseDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					newBusinessLicenseFileList.add(newBusinessLicenseDto);
				} else if (fileTypeId == 1012) {
					ContractFileDto informationPublicityDto = new ContractFileDto();
					handleFileRecordMain(informationPublicityDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + informationPublicityDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					informationPublicityFileList.add(informationPublicityDto);
				} else if (fileTypeId == 1013) {
					ContractFileDto confirmationSheetDto = new ContractFileDto();
					handleFileRecordMain(confirmationSheetDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + confirmationSheetDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					confirmationSheetFileList.add(confirmationSheetDto);
				} else if (fileTypeId == 1014) {
					ContractFileDto storePhotosDto = new ContractFileDto();
					handleFileRecordMain(storePhotosDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + storePhotosDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";

					storePhotosFileList.add(storePhotosDto);
				} else if (fileTypeId == 1015) {
					ContractFileDto changeOthersDto = new ContractFileDto();
					handleFileRecordMain(changeOthersDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + changeOthersDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					changeOthersFileList.add(changeOthersDto);
				} else if (fileTypeId == 1016) {
					ContractFileDto transferRightsDto = new ContractFileDto();
					handleFileRecordMain(transferRightsDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + transferRightsDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					transferRightsFileList.add(transferRightsDto);
				} else if (fileTypeId == 1017) {
					ContractFileDto newSigningDto = new ContractFileDto();
					handleFileRecordMain(newSigningDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + newSigningDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					newSigningFileList.add(newSigningDto);
				} else if (fileTypeId == 1018) {
					ContractFileDto mainInformationPublicityDto = new ContractFileDto();
					handleFileRecordMain(mainInformationPublicityDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + mainInformationPublicityDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					mainInformationPublicityFileList.add(mainInformationPublicityDto);
				} else if (fileTypeId == 1019) {
					ContractFileDto mainChangeOthersDto = new ContractFileDto();
					handleFileRecordMain(mainChangeOthersDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + mainChangeOthersDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					mainChangeOthersFileList.add(mainChangeOthersDto);
				}else if (fileTypeId == 2) {
    			    ContractFileDto legalCardFileDto = new ContractFileDto();
    			    handleFileRecordMain(legalCardFileDto, fileRecordMain);//, picId
    			    fileRecordMainIds = fileRecordMainIds + legalCardFileDto.getFileRecordMainId() + ",";
    			    //contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
    			    legalCardFileList.add(legalCardFileDto);
    			}else if (fileTypeId == 1020) {
    				//fileRecordMainNotice
    				ContractFileDto noticeFileDto = new ContractFileDto();
					handleFileRecordMain(noticeFileDto, fileRecordMain);//, picId
					fileRecordMainIds = fileRecordMainIds + noticeFileDto.getFileRecordMainId() + ",";
					//contractChangePicIds = contractChangePicIds + contractChangePicIdList[i]+",";
					fileNoticeList.add(noticeFileDto);
    			}
			}
			contractChangeDto.setStopContractFileList(stopContractFileList);
			contractChangeDto.setSurrenderFileList(surrenderFileList);
			contractChangeDto.setReceiptFileList(receiptFileList);
			contractChangeDto.setReturnProveFileList(returnProveFileList);
			contractChangeDto.setCancellateFileList(cancellateFileList);
			contractChangeDto.setPhotosFileList(photosFileList);
			contractChangeDto.setCooperateFileList(cooperateFileList);
			contractChangeDto.setOneStopFileList(oneStopFileList);
			contractChangeDto.setOthersFileList(othersFileList);
			contractChangeDto.setChangeSupplementFileList(changeSupplementFileList);
			contractChangeDto.setNewBusinessLicenseFileList(newBusinessLicenseFileList);
			contractChangeDto.setInformationPublicityFileList(informationPublicityFileList);
			contractChangeDto.setConfirmationSheetFileList(confirmationSheetFileList);
			contractChangeDto.setStorePhotosFileList(storePhotosFileList);
			contractChangeDto.setChangeOthersFileList(changeOthersFileList);
			contractChangeDto.setTransferRightsFileList(transferRightsFileList);
			contractChangeDto.setNewSigningFileList(newSigningFileList);
			contractChangeDto.setMainInformationPublicityFileList(mainInformationPublicityFileList);
			contractChangeDto.setMainChangeOthersFileList(mainChangeOthersFileList);
			contractChangeDto.setLegalCardFileList(legalCardFileList);
			contractChangeDto.setFileRecordMainNotice(fileNoticeList);
		}
        Map<String, String> mop = new HashMap<String, String>();
        mop.put("fileRecordMainIds", fileRecordMainIds);
        //mop.put("contractChangePicIds", contractChangePicIds);
        return mop;
    }

    /**
    * 对文件数据进行组装处理
    * @param contractFileDto
    * @param fileRecordMain
    * @throws Exception
    */
    private void handleFileRecordMain(ContractFileDto contractFileDto, FileRecordMain fileRecordMain)//, String picId
        throws Exception
    {
        String fileId = fileRecordMain.getFileId();
        //获取图片路径
         contractFileDto.setFileAbbrUrl(fileRecordMain.getFileAbbrUrl());
         contractFileDto.setFileName(fileRecordMain.getFileFullName());
         contractFileDto.setFileRecordMainId(fileRecordMain.getFileRecordMainId().toString());
         contractFileDto.setFileUrl(fileRecordMain.getFileUrl());
         contractFileDto.setUrl50(fileRecordMain.getUrl50());
         /*if (StringUtil.isEmpty(fileId))
         {
        	 contractFileDto.setContractChangePicId(picId);//原图名称
         }*/
    }

    /**
    * 对文件数据进行组装处理
    * @param contractFileDto
    * @param fileRecordMain
     * @param picId
    * @throws Exception
    */
    private void handleFile(ContractFileDto contractFileDto, FileRecordMain fileRecordMain, String picId)
        throws Exception
    {
        // 1、先从关系表中取，如果取不到 ，则通过fileNo 去渠道系统主表 拿到渠道及fileCode，去相应文件系统取出文件地址；
        // 获取文件渠道系统配置（外部文件系统的配置）
        Map<?, ?> mapTemp = getChannelConfig();

        //取关系表中的文件Code
        String fileId = fileRecordMain.getFileId();

        //关系主键Id
        String fileRecordMainId = fileRecordMain.getFileRecordMainId().toString();

        if (StringUtil.isNotEmpty(fileId))
        {
            // 查询路径
            String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
            // 下载路径
            String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
            // 授权号
            String permitCode = (String)mapTemp.get("CRIC_file_permit_code");

            //获取图片路径
            String fileUrl = FileHelper.getFilePath(fileId, queryUrl, downloadUrl, permitCode);

            Map<String, Object> handleMap = new HashMap<>();
            handleMap.put("width", "100");
            handleMap.put("height", "100");
            handleMap.put("waterPic", "0");// 无水印图片
            handleMap.put("waterPosition", "0");//  3:左下角
            handleMap.put("cutType", "0");//0-不裁剪
            String fileAbbrUrl =
                FileHelper.getFilePath(fileRecordMain.getFileId(),
                    FileAssist.UPLOAD_FILE_IS_HANDLE_YES,
                    queryUrl,
                    downloadUrl,
                    permitCode,
                    handleMap);
            contractFileDto.setFileAbbrUrl(fileAbbrUrl);
            contractFileDto.setFileName(fileRecordMain.getFileFullName());
            contractFileDto.setFileRecordMainId(fileRecordMainId);
            contractFileDto.setFileUrl(fileUrl);

        }
        else
        {
            //取出fileNo
            String fileNo = fileRecordMain.getFileNo();
            //调用渠道系统，获取文件记录信息
            ResultData<?> reback = filesService.getByFileNo(fileNo);
            Map<?, ?> mapTemppp = (Map<?, ?>)reback.getReturnData();
            if (null != mapTemppp)
            {
                String fileAbbrUrl = null;
                String fileUrl = null;

                FileDto fileDto = MapToEntityConvertUtil.convert(mapTemppp, FileDto.class, "");

                //fileCode
                String fileCode = fileDto.getFileCode();
                //渠道Code
                String channelCode = fileDto.getChannelCode();
                if ("CRIC".equals(channelCode))
                {
                    // 查询路径
                    String queryUrl = (String)mapTemp.get("CRIC_queryfile_path");
                    // 下载路径
                    String downloadUrl = (String)mapTemp.get("CRIC_downloadfile_path");
                    // 授权号
                    String permitCode = (String)mapTemp.get("CRIC_file_permit_code");

                    //获取图片路径
                    fileUrl = FileHelper.getFilePath(fileCode, queryUrl, downloadUrl, permitCode);
                    Map<String, Object> handleMap = new HashMap<>();
                    handleMap.put("width", "100");
                    handleMap.put("height", "100");
                    handleMap.put("waterPic", "0");// 无水印图片
                    handleMap.put("waterPosition", "0");//  3:左下角
                    handleMap.put("cutType", "0");//0-不裁剪
                    fileAbbrUrl =
                        FileHelper.getFilePath(fileCode, FileAssist.UPLOAD_FILE_IS_HANDLE_YES, queryUrl, downloadUrl, permitCode, handleMap);

                }
                else if ("weiphoto".equals(channelCode))
                {
                    // 查询路径
                    String fileViewUrl = (String)mapTemp.get("wp_view_url");

                    fileAbbrUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "200");
                    fileUrl = WeiphotoHelper.getFilePath(fileViewUrl, fileCode, "5000");

                }
                //设值
                contractFileDto.setFileAbbrUrl(fileAbbrUrl);//缩略图地址
                contractFileDto.setFileRecordMainId(fileRecordMainId);
                contractFileDto.setFileUrl(fileUrl);//原图地址
                contractFileDto.setFileName(fileRecordMain.getFileFullName());//原图名称
                contractFileDto.setContractChangePicId(picId);//原图名称
            }
        }
    }
/*------------------------------------------------私有获取图片数据方法-----------------------------------------------------------------------------------------------------------------------*/


  //##################### 以下是定时获取OA审核状态专用方法 start (2016-10-09) ########################//
    /**
     * 获取合同变更状态为审核中的FlowIdList
     *
     * @param params
     *            合同变更ID
     * @return
     * @throws Exception
     */
    public List<ContractChangeDto> getByApproveState(Map<String, Object> params) throws Exception {
        // 构建返回
        List<ContractChangeDto> contractChangeDtoList = new ArrayList<ContractChangeDto>();
        // 查询操作
        List<ContractChange> contractChangeList = contractChangeMapper .queryByApproveState(params);
        // 非空判断
        if (null != contractChangeList && !contractChangeList.isEmpty())
        {
            for (ContractChange contractChagne : contractChangeList) {
                ContractChangeDto ccDto = new ContractChangeDto();
                BeanUtils.copyProperties(contractChagne, ccDto);
                contractChangeDtoList.add(ccDto);
            }
        }
        return contractChangeDtoList;
    }
  //##################### 以上是定时获取OA审核状态专用方法 start (2016-10-09) ########################//

    /**
     *
    * 检验变更门店是否在门店业绩撤销
    * @param queryParam
    * @return
     */
    public ResultData<String> getStoreCancelState(Map<String, Object> queryParam)
    {
        ResultData<String> resultData = new ResultData<>();
        //转换storeList为数组格式
        List<Integer> list = (ArrayList)queryParam.get("storeIdList");
        String[] storeIdList = new String[list.size()];
        for(int i =0 ;i < list.size();i++){
            storeIdList[i]=String.valueOf(list.get(i));
        }

        queryParam.put("storeIdList", storeIdList);
        //查询isCancel状态
        List<ContractStore>  storelist = contractStoreMapper.getStoreCancelState(queryParam);
        for(ContractStore contractStore : storelist){
            //门店正在业绩撤销中，请业绩撤销审核通过后再提交审核！
            if(contractStore.getIsCancel().equals(DictionaryConstants.STORESTATE_ISCANCEL_ISCANCELLING)){
                resultData.setReturnData("N");
            //门店尚未进行业绩撤销，请业绩撤销审核通过后再提交审核！
            }else if(contractStore.getIsCancel().equals(DictionaryConstants.STORESTATE_ISCANCEL_ISNOTCANCEL)){
                resultData.setReturnData("N");
            }else{
                resultData.setReturnData("Y");
            }
        }
        return resultData;
    }

    /**
     * 根据门店ID查找合同门店变更信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<ContractChangeDto> getContractChangeByStoreId(int id)throws Exception {

		// 构建返回
		ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();

		// 查询操作
		List<ContractChangeDto> mdlList = contractChangeMapper.getContractChangeByStoreId(id);
		if(CollectionUtils.isNotEmpty(mdlList))
		{
			resultData.setTotalCount("1");
		}
		else
		{
			resultData.setTotalCount("0");
		}

		return resultData;
	}

    /**
	* 获取该合同变更的门店地址
	* @param contractStopId 合同变更ID
	* @return
	* @throws Exception
	*/
	public ResultData<String> getChgStoreAddr(Integer contractStopId) throws Exception{
		// 构建返回
		ResultData<String> resultData = new ResultData<String>();
		String addressDetail = "";
		String addressDetailDb = contractChangeStoreMapper.getChgStoreAddr(contractStopId);
		if(null != addressDetailDb){
			addressDetail = addressDetailDb;
		}
		resultData.setReturnData(addressDetail);
		return resultData;
	}

	/**
	 * 根据flowId查询合同变更信息
	 * @param flowId
	 * @return
	 * @throws Exception
	 */
    public ResultData<ContractChange> getContractChangeByFlowId(String flowId)throws Exception {

		// 构建返回
		ResultData<ContractChange> resultData = new ResultData<ContractChange>();

		// 查询操作
		ContractChange mdlList = contractChangeMapper.getContractChangeByFlowId(flowId);
		resultData.setReturnData(mdlList);
		if(mdlList != null)
		{
			resultData.setTotalCount("1");
		}
		else
		{
			resultData.setTotalCount("0");
		}

		return resultData;
	}

	/**
	 * 根据flowId查询合同变更门店表信息
	 * @param flowId
	 * @return
	 * @throws Exception
	 */
    public ResultData<List<ContractChangeStore>> getContractChangeStoreByFlowId(String flowId)throws Exception {

		// 构建返回
		ResultData<List<ContractChangeStore>> resultData = new ResultData<List<ContractChangeStore>>();

		// 查询操作
		List<ContractChangeStore> list = contractChangeStoreMapper.getChangeStoresByFlowId(flowId);
		resultData.setReturnData(list);
		return resultData;
	}

//Add by qy 2017/09/12 合同变更审核通过后更新合同状态  Start
    /**
     * 审核通过：根据flowId、合同Id和门店Ids进行后台操作处理
     * @throws Exception
     */
    public void doPassDateUpdateAddon(String flowId, Integer contractId, List<Integer> storeIdList,Integer userId,Date approveDate) throws Exception
    {
    	ContractChange contractChangedata = contractChangeMapper.getContractChangeByFlowId(flowId);
    	Integer changeType = contractChangedata.getChangeType();
    	Integer b2aType = contractChangedata.getChangeCompany();//签约主体变更

    	if(b2aType == null)
    	{
    		b2aType = 0;
    	}

		// 变更类型
		switch(changeType.intValue())
		{
			case DictionaryConstants.Contract_ChangeType_B2A:
			    //乙转甲

			    ResultData<List<ContractChangeStore>> resultData = stopContractService.getContractChangeStoreByFlowId(flowId);
	            List<ContractChangeStore> stopContractStore = resultData.getReturnData();
	            Integer storeId = stopContractStore.get(0).getStoreId();

			    Store store = new Store();
			    store.setStoreId(storeId);
			    store.setABTypeStore(19901);
			    store.setBTypeStore("");

			    boolean companyChangeFlag = false;

			    //门店地址变更
                if(contractChangedata.getChangeStoreAdress() != null && contractChangedata.getChangeStoreAdress() == 1){
                    store.setCityNo(contractChangedata.getStoreCity());
                    store.setDistrictNo(contractChangedata.getStoreDistrict());
                    store.setAddress(contractChangedata.getStoreAdresss());
                    store.setAddressDetail(contractChangedata.getChangeStoreCityName() + contractChangedata.getChangeStoreDistrictName() + contractChangedata.getStoreAdresss());

                    // 修改OMS装修记录的门店地址
                    Map<String, Object> reqMapOms = new HashMap<String, Object>();
                    reqMapOms.put("contractId", contractChangedata.getContractId());
                    reqMapOms.put("storeId", storeId);
                    reqMapOms.put("storeAdress", contractChangedata.getChangeStoreCityName() + contractChangedata.getChangeStoreDistrictName() + contractChangedata.getStoreAdresss());
                    storeService.UpdateOmsStoreAdress(reqMapOms);

                    //修改合同门店关系表
                    if(b2aType.equals(0)) {
                        ContractStore contractStore = new ContractStore();
                        contractStore.setContractId(contractId);
                        contractStore.setStoreId(storeId);
                        contractStore.setAddressDetail(contractChangedata.getChangeStoreCityName() + contractChangedata.getChangeStoreDistrictName() + contractChangedata.getStoreAdresss());
                        contractStore.setABTypeStore(19901);
                        contractStore.setBTypeStore("");
                        contractStoreMapper.update(contractStore);
                    }
                }
                storeService.update(store);

                CompanyDto companyDto = new CompanyDto();
                companyDto.setId(contractChangedata.getOldCompanyId());
                ContractDto contractDto = new ContractDto();
                ContractInfoDto contractInfoDto = new ContractInfoDto();
                contractDto.setId(contractChangedata.getContractId());

                //工商更名
                if(contractChangedata.getUpdateCompanyName() != null && contractChangedata.getUpdateCompanyName() == 1) {
                    companyDto.setCompanyName(contractChangedata.getNewUpdateCompanyName());
                    contractDto.setPartyB(contractChangedata.getNewUpdateCompanyName());
                    companyChangeFlag = true;
                }

                //公司地址变更
                if(contractChangedata.getChangeCompanyAdress() != null && contractChangedata.getChangeCompanyAdress() == 1){
                    companyDto.setCityNo(contractChangedata.getCompanyCity());
                    companyDto.setDistrictNo(contractChangedata.getCompanyDistrict());
                    companyDto.setAddress(contractChangedata.getCompanyAdresss());
                    contractDto.setPartyBcityNo(contractChangedata.getCompanyCity());
                    contractDto.setPartyBdistrictNo(contractChangedata.getCompanyDistrict());
                    contractDto.setPartyBAddress(contractChangedata.getCompanyAdresss());
                    companyChangeFlag = true;
                }

                if(companyChangeFlag) {
                    contractInfoDto.setContract(contractDto);

                    //公司业务类型
					if(b2aType.equals(1)){
						companyDto.setBizType("21801|21802");
					}

                    companyService.update(companyDto);
                    contractService.updateContractCompanyAdressByFlowId(contractInfoDto);

                    //调用OP接口
                    String url = SystemParam.getWebConfigValue("opUrl") + "company";
                    String url18 = SystemParam.getWebConfigValue("opUrl18") + "companies";
                    OpCompanyDto opCompanyDto = fangyouAccountService.getOPCompanyById(companyDto.getId());
                     //jsonString设值
                    if(null != opCompanyDto.getCompanyNo() && "" != opCompanyDto.getCompanyNo()){

                        opCompanyDto.setUserIdUpdate(userId);
                        User user = userMapper.getUserByUserId(userId);
                        opCompanyDto.setUserNameUpdate(user.getUserName());

                        //工商更名
                        if(contractChangedata.getUpdateCompanyName() != null && contractChangedata.getUpdateCompanyName().intValue() ==1 ) {
                            opCompanyDto.setCompanyName(contractChangedata.getNewUpdateCompanyName());
                        }

                        //公司地址变更
                        if(contractChangedata.getChangeCompanyAdress() != null && contractChangedata.getChangeCompanyAdress().intValue() == 1){
                            opCompanyDto.setCityNo(contractChangedata.getCompanyCity());
                            opCompanyDto.setCityName(cityMapper.getCityByCityNo(contractChangedata.getCompanyCity()).getCityName());
                            opCompanyDto.setDistrictNo(contractChangedata.getCompanyDistrict());
                            opCompanyDto.setDistrictName(districtMapper.getDistrictByDistrictNo(contractChangedata.getCompanyDistrict()).getDistrictName());
                            opCompanyDto.setCompanyAddr(contractChangedata.getCompanyAdresss());
                        }
						String jsonData = JsonUtil.parseToJson(opCompanyDto);
                        try{

							logger.info("修改公司信息申请接口: #####请求#url="+url+"##userIdUpdate="+ userId);
							String opResult = HttpClientUtil.jsonPut(url,jsonData);
						}catch (Exception e){
							logger.error("open", "ContractChangeService", "doPassDateUpdateAddon", jsonData, null, null, "调OP接口异常", e);
						}
							//op返回值
							//Map<String,Object> opMap = (Map<String,Object>) JsonUtil.parseToObject(opResult,  Map.class);
							//logger.info("CRM房友账号绑定申请接口返回码："+opMap.get("returnCode").toString()+",返回信息："+opMap.get("returnMsg").toString());
						try{
							if("1".equals(SystemParam.getWebConfigValue("opUrl18Flag"))) {
								// 18版op
								logger.info("OP18版修改公司信息申请接口: #####请求#url="+url18+"##userIdUpdate="+ userId);
								String opResult18 = HttpClientUtil.jsonPut(url18,jsonData);

								//op返回值
							//	Map<String,Object> opMap18 = (Map<String,Object>) JsonUtil.parseToObject(opResult18,  Map.class);
							//	logger.info("OP18版修改公司信息申请接口返回码："+opMap18.get("returnCode").toString()+",返回信息："+opMap18.get("returnMsg").toString());
							}
						}catch (Exception e){
							logger.error("open", "ContractChangeService", "doPassDateUpdateAddon", jsonData, null, null, "调OP接口异常", e);
						}
                    }
                }

				if(b2aType.equals(1))
				{//主体变更
					doPassDateUpdate( flowId,  contractId,  storeIdList, userId,1,approveDate);

					//旧公司、门店关联关系移除
					CompanyStoreDto oldcompanyStore = new CompanyStoreDto();
					oldcompanyStore.setCompanyId(contractChangedata.getOldCompanyId());
					oldcompanyStore.setStoreId(storeId);
					companyStoreService.deleteCompanyStore(oldcompanyStore);

					//新公司、门店关联关系建立
                    CompanyStore companyStore = new CompanyStore();
                    companyStore.setCompanyId(contractChangedata.getNewCompanyId());
                    companyStore.setStoreId(storeId);
                    companyStoreService.createCompanyStore(companyStore);

                    //新公司修改业务类型
                    CompanyDto newompanyDto = new CompanyDto();
                    newompanyDto.setId(contractChangedata.getNewCompanyId());
                    newompanyDto.setBizType("21801|21802");
                    companyService.updateCompanyOnly(newompanyDto);

                    ResultData<ContractDto> contractData = contractService.getContractById(contractChangedata.getContractId());
                    ContractDto contract = contractData.getReturnData();

                    //新合同创建
                    ContractDto newContract = new ContractDto();
                    newContract.setAgreementType(contractChangedata.getAgreementType());
                    newContract.setAgreementNo(contractChangedata.getNewAgreementNo());
                    newContract.setDateLifeStart(contractChangedata.getNewDateLifeStart());
                    newContract.setDateLifeEnd(contractChangedata.getNewDateLifeEnd());
                    newContract.setAuthRepresentative(contractChangedata.getAuthRepresentative());
                    newContract.setAgentContact(contractChangedata.getAgentContact());
                    newContract.setDepositFee(contractChangedata.getTotleDepositFee());
                    newContract.setTotleDepositFee(contractChangedata.getTotleDepositFee());
                    newContract.setPenaltyFee(contractChangedata.getPenaltyFee());
                    newContract.setCompanyBankNo(contractChangedata.getCompanyBankNo());
                    newContract.setBankAccount(contractChangedata.getBankAccount());
                    newContract.setAccountName(contractChangedata.getAccountName());
                    newContract.setRecipients(contractChangedata.getRecipients());
					newContract.setAccountProject(contractChangedata.getAccountProject());
					newContract.setAccountProjectNo(contractChangedata.getAccountProjectNo());

                    if(StringUtils.isNotEmpty(contractChangedata.getCityNo())) {
                        newContract.setCityNo(contractChangedata.getCityNo());
                    }else {
                        newContract.setCityNo(contract.getCityNo());
                    }
                    if(StringUtils.isNotEmpty(contractChangedata.getDistrictNo())) {
                        newContract.setDistrictNo(contractChangedata.getDistrictNo());
                    }else {
                        newContract.setDistrictNo(contract.getDistrictNo());
                    }

                    newContract.setRecipientsAddress(contractChangedata.getRecipientsAddress());
                    newContract.setContractB2A(20201);
                    newContract.setCompanyId(contractChangedata.getNewCompanyId());
                    newContract.setUserCreate(contractChangedata.getUserIdCreate());
                    newContract.setPartyB(contractChangedata.getNewCompanyName());
                    newContract.setPartyBAddress(contractChangedata.getNewCompanyAddress());
                    newContract.setLealPerson(contractChangedata.getNewLegalPerson());
                    newContract.setRemark("乙转甲。原合同编号:"+contract.getContractNo()+",原协议书编号:"+contract.getAgreementNo()+",乙转甲单号:"+contractChangedata.getContractStopNo());
                    newContract.setContractStatus(10403);
                    newContract.setStoreNum(1);
                    newContract.setPartyBcityNo(contractChangedata.getNewCompanyAddressCityNo());
                    newContract.setPartyBdistrictNo(contractChangedata.getNewCompanyAddressDistrictNo());
                    newContract.setExpandingPersonnel(contract.getExpandingPersonnel());
                    newContract.setExpandingPersonnelId(contract.getExpandingPersonnelId());
                    newContract.setOaApproveType("17101");
                    newContract.setPerformDate(new Date());
                    //新签
                    newContract.setOriginalContractdistinction(18601);
                    newContract.setCenterId(contract.getCenterId());
                    newContract.setAcCityNo(contract.getAcCityNo());
                    newContract.setContractType(10302);
                    newContract.setContractChangeId(contractChangedata.getId());

                    Company companyData = companyService.getByCompanyId(contractChangedata.getNewCompanyId());
                    newContract.setRegistrId(companyData.getBusinessLicenseNo());
                    newContract.setCompanyId(contractChangedata.getNewCompanyId());

                    ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTRACT");
                    newContract.setContractNo(data.getReturnData());

                    //老合同编号
					Contract oldContract = contractMapper.getContractById(contractId);
					newContract.setOldContractNo(oldContract.getContractNo());

					//合同变更OA审核通过后，新生成的合同增加合同变更类型 add by haidan 2019/07/12
					newContract.setOaApproveContractType(changeType);

                    ContractInfoDto contractInfo = new ContractInfoDto();
                    contractInfo.setContract(newContract);

                    //合同门店关系
                    List<StoreDto> storeDetails = new ArrayList<>();
                    StoreDto storeDto = new StoreDto();
                    storeDto.setStoreId(Integer.valueOf(storeId));
                    storeDto.setABTypeStore(19901);
                    storeDetails.add(storeDto);
                    contractInfo.setStoreDetails(storeDetails);

                    //创建新的合同
                    contractService.create(contractInfo);
                    ResultData<List<StoreDto>> storeById = storeService.getStoreById(storeId);
                    StoreDto storeInfo = storeById.getReturnData().get(0);

                    //原合同的保证金转出，新合同的保证金转入
                    Map<String, Object> record = new HashMap<>();
                    record.put("storeId", storeId);
                    record.put("oldContractId", contractId);
                    //record.put("newContractId", contractId);
                    record.put("storeNo", storeInfo.getStoreNo());
                    record.put("storeName", storeInfo.getName());
                    record.put("oldContractNo", contract.getContractNo());
                    record.put("newContractNo", newContract.getContractNo());
                    record.put("acCityNo", contract.getAcCityNo());
                    storeDepositService.transferDeposit(record);

                    DecorationDto oldDecoration = new DecorationDto();
                    oldDecoration.setContractNo(contract.getContractNo());
                    oldDecoration.setStoreNo(storeInfo.getStoreNo());
                    int deloldDecFlag= omsInteractiveService.getDecCountByMap(oldDecoration);
                    if(deloldDecFlag==0){
	                    //创建新的装修记录
	                    List<DecorationDto> decorationList = new ArrayList<>();
	                    DecorationDto decoration = new DecorationDto();
	                    ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_STOREDECORATION");
	                    String decorationNo = back.getReturnData();
	                    decoration.setDecorateNo(decorationNo);


	                    decoration.setStoreNo(storeInfo.getStoreNo());
	                    decoration.setStoreName(storeInfo.getName());
	                    decoration.setStoreAddress(storeInfo.getAddressDetail());

	                    decoration.setContractNo(newContract.getContractNo());
	                    decoration.setContractType("B");
	                    decoration.setCompanyName(newContract.getPartyB());
	                    decoration.setDecorateStatus(16301);
	                    decoration.setDelFlag("0");
	                    decoration.setDateCreate(new Date());
	                    decoration.setOafilpagency(newContract.getPartyB());

	                    //见APITimerController452行，不知为什么取acCityNo
	                    decoration.setCityNo(newContract.getAcCityNo());
	                    decoration.setAgreementNo(newContract.getAgreementNo());
	                    decoration.setDateLifeStartStr(DateUtil.fmtDate(newContract.getDateLifeStart()));
	                    decoration.setDateLifeEndStr(DateUtil.fmtDate(newContract.getDateLifeEnd()));

	                    decorationList.add(decoration);
	                    extOmsService.insertToOMSDecoration(decorationList);

	                    //更新原装修记录为删除

	                    omsInteractiveService.updateOmsDecoration(oldDecoration);

	                    Map<String, Object> crmDecoration = new HashMap<>();
	                    crmDecoration.put("decorationNo", decorationNo);
	                    crmDecoration.put("contractNo", newContract.getContractNo());
	                    crmDecoration.put("storeNo", storeInfo.getStoreNo());
	                    crmDecoration.put("oldContractNo", contract.getContractNo());
	                    storeDecorationMapper.updateDecorationForNew(crmDecoration);
                    }
				}else
				{//非主体变更
					updateContractChangeStatus(flowId, userId,approveDate);
				}

				break;
			case DictionaryConstants.Contract_ChangeType_Third://三方变更
				doPassDateUpdateThreePart(flowId,contractId,storeIdList,userId,approveDate);
				break;
			case DictionaryConstants.Contract_ChangeType_END: //终止
				doPassDateUpdate( flowId,  contractId,  storeIdList, userId,2,approveDate);
				break;
			case DictionaryConstants.Contract_ChangeType_Relocation: //门店迁址
				doPassDateUpdateStoreRelocation( flowId,  contractId,  storeIdList, userId,approveDate);
				break;
			default:

		}
    }

    /**
     * 审核通过：更新变更状态
     * @throws Exception
     */
    private void updateContractChangeStatus(String flowId, Integer userId,Date approveDate) throws Exception{


		// 更新变更记录表 状态为 审核通过
		ContractChangeDto contractChange = new ContractChangeDto();
		ContractInfoDto contractInfoDto = new ContractInfoDto();
		contractChange.setFlowId(flowId);
		contractChange.setApproveState(2); // 2:变更审核通过
        contractChange.setApproveDate(approveDate);
		contractChange.setUpdateDate(new Date());// 更新日期
		contractChange.setFlowEndDate(new Date());// 更新日期
		contractChange.setUpdateCreate(userId);// 更新人
		contractInfoDto.setContractChangeDto(contractChange);
		this.update(contractInfoDto);
    }

    /**
     * 审核通过：根据flowId、合同Id和门店Ids进行后台操作处理
     * @throws Exception
     */
    private void doPassDateUpdate(String flowId, Integer contractId, List<Integer> storeIdList,Integer userId,int b2aFlag,Date approveDate) throws Exception {

		// 更新变更记录表 状态为 审核通过
		ContractChangeDto contractChange = new ContractChangeDto();
		ContractInfoDto contractInfoDto = new ContractInfoDto();
		contractChange.setFlowId(flowId);
		contractChange.setApproveState(2); // 2:变更审核通过
		contractChange.setApproveDate(approveDate);//审核通过日期
		contractChange.setUpdateDate(new Date());// 更新日期
		contractChange.setFlowEndDate(approveDate);// 更新日期
		contractChange.setUpdateCreate(userId);// 更新人
		contractInfoDto.setContractChangeDto(contractChange);
		this.update(contractInfoDto);

		for (Integer storeId : storeIdList) {
			// 更新变更门店为终止
			ContractStore contractStore = new ContractStore();
			contractStore.setStoreId(storeId);
			contractStore.setContractId(contractId);
			contractStore.setStoreState(2);
			contractStore.setContractStopDate(new Date());
			contractService.updateContractStore(contractStore);

			// 更新合同变更下的门店：未锁定(store.StoreStatus = 0),未签 (store.contractType = 0)
			Store store = new Store();
			store.setStoreId(storeId);

			if (b2aFlag == 1) {
				store.setContractType(10302);// 合作模式B
				store.setStoreStatus(1);// B类合同锁定门店
			} else if (b2aFlag == 2 || b2aFlag == 3) {
				store.setContractType(0);// 未签
				store.setStoreStatus(0);// 解锁

				//移除公司门店关系
                ResultData<ContractDto> data = contractService.getContractById(contractId);
                if(data != null && data.getReturnCode().equals("200")){
                    CompanyStoreDto oldCompanyStore = new CompanyStoreDto();
                    oldCompanyStore.setCompanyId(data.getReturnData().getCompanyId());
                    oldCompanyStore.setStoreId(storeId);
                    companyStoreService.deleteCompanyStore(oldCompanyStore);
                }
			}

			if(b2aFlag == 2){
				store.setAuthCheckStatus(23300);
			}

			store.setUserUpdate(userId); // 更新人
			store.setDateUpdate(new Date()); //更新时间
			storeService.update(store);
		}

		//更新装修表装修类型为空的记录为删除
		if (b2aFlag == 2){
			Map<String, Object> param = new HashMap<>();
			param.put("contractId", contractId);
			param.put("storeIdList", storeIdList.toArray(new Integer[storeIdList.size()]));
			contractChangeMapper.delOmsDecoration(param);
			contractChangeMapper.delCrmDecoration(param);
		}

		// 更新合同表的 是否变更(isChanged) 字段为 是
		ContractChange contractChangeCt = new ContractChange();
		contractChangeCt.setFlowId(flowId);
		contractService.updateCtrctStateByChgFlowId(contractChangeCt);

		// 根据FlowId查询合同下的所有门店， 如果所有门店都终止则更新合同状态为终止
		ResultData<List<ContractStore>> resultDataCS = contractService.getContractStoresByFlowId(flowId);
		List<ContractStore> csList = resultDataCS.getReturnData();
		if(null != csList && !csList.isEmpty()) {
			Boolean isAllOver = true;
			for(ContractStore contractStore : csList){
				Integer storeState = contractStore.getStoreState();
				if(storeState==null){ //2:终止
                    isAllOver = false;
                    break;
                }else if(2 != storeState){ //2:终止
					isAllOver = false;
					break;
				}
			}
			// 所有门店都终止 更新合同状态为终止
			if (isAllOver){
				ContractChange cg = new ContractChange();
				cg.setFlowId(flowId);
				contractService.updateCtrctStatusByChgFlowId(cg);

				//当前合同终止，且该合同对应公司无审核通过合同，修改公司业务类型为""
                ResultData<ContractDto> resultData = contractService.getContractById(contractId);
                Integer companyId = null;
                if(resultData.getReturnCode().equals("200")){
                    companyId = resultData.getReturnData().getCompanyId();
                }
                if(companyId != null){
                    List<Contract> list = contractService.queryConfirmContractByCompanyId(companyId);
                    if(list.size() == 0){
                        CompanyDto companyDto = new CompanyDto();
                        companyDto.setId(companyId);
                        companyDto.setBizType("");
                        companyService.updateCompanyOnly(companyDto);
                    }
                }
			}
		}
    }
//Add by qy 2017/09/12 合同变更审核通过后更新合同状态  End
    public List<ContractChangeStore> getContractChangeStoreById(Map<String, Object> queryParam) {
		List<ContractChangeStore> contractChangeStoreById = contractChangeStoreMapper.getContractChangeStoreById(queryParam);
		return contractChangeStoreById;
	}

    public String splitToSingle(Integer contractChangeId, String[] storeIdList, String[] preCodeList) {

        StringBuffer result = new StringBuffer();

        int size = storeIdList.length;
        for(int i = 0;i<size;i++) {

            Integer storeId = Integer.valueOf(storeIdList[i]);
            String code = preCodeList[i];

            //contractChange
            Map<String,Object> param = new HashMap<>();
            param.put("contractChangeId", contractChangeId);
            param.put("code", code);

            //一并终止的申请(此次)
            StringBuilder tempCode = new StringBuilder("&#13;&#10;一并终止的申请：");
            for(int j = 0;j<size;j++) {
                if(j != i) {
                    tempCode.append(preCodeList[j]).append(",");
                }
            }
            param.put("appendRemarks", tempCode.substring(0, tempCode.length() -1));

            int count = contractChangeMapper.copyForOaSplit(param);
            if(count == 1) {
                String newContractChangeId = param.get("id").toString();
                //result.append(newContractChangeId).append("-").append(storeId).append("#");

                //contractChangeStore
                Map<String,Object> param1 = new HashMap<>();
                param1.put("oldContractChangeId", contractChangeId);
                param1.put("newContractChangeId", Integer.valueOf(newContractChangeId));
                param1.put("storeId", storeId);
                int count1 = contractChangeMapper.copySubForOaSplit(param1);

                //FIL_FileRecordMain
                Map<String,Object> param2 = new HashMap<>();
                param2.put("oldRefId", contractChangeId);
                param2.put("newRefId", Integer.valueOf(newContractChangeId));
                int count2 = contractChangeMapper.copyFileForOaSplit(param2);

                if(count1 == 1 && count2 > 1 ) {
                    result.append(newContractChangeId).append("-").append(storeId).append("#");
                }
            }
        }

        //更新原主表
        ContractChange obj = new ContractChange();
        obj.setId(contractChangeId);
        obj.setDelFlag(true);
        int updateCount = contractChangeMapper.update(obj);
        if(updateCount != 1) {
            return "";
        }

        if(result.length() > 0) {
            return result.substring(0,result.length() - 1).toString();
        }else {
            return "";
        }
    }
    /**
     * 查询合同未终止的门店数量
     * @param contractId
     */
    public Integer queryStoreOfCountByConractId(Integer contractId) {
		int  count = contractChangeMapper.queryStoreOfCountByConractId(contractId);
		return count;
	}

    /**
     * 作废变更记录
     * @param id
     * @return
     * @throws Exception
     */
	public Integer delete(Integer id) throws Exception {

		// 更新ContractChange表记录
		int count = contractChangeMapper.deleteRecordById(id);
		if (count > 0) {
			//更新删除记录
			//fileRecordMainMapper.updateStatusByContractId(id);
        	// 解除原有的录合同变更与门店的关系
			//contractChangeStoreMapper.updateById(id);
		}
		return count;
	}

	/**
	 * 三方变更审核通过
	 * @param flowId
	 * @param contractId
	 * @param storeIdList
	 * @param userId
	 * @throws Exception
	 */
	public void doPassDateUpdateThreePart(String flowId, Integer contractId, List<Integer> storeIdList,Integer userId,Date approveDate) throws Exception {
		ContractChange contractChangedata = contractChangeMapper.getContractChangeByFlowId(flowId);

		//原合同相当于终止，更新其相关信息
		doPassDateUpdate(flowId, contractId, storeIdList, userId, 3,approveDate);

		//新公司、门店关联关系建立
		CompanyStore companyStore = new CompanyStore();
		companyStore.setCompanyId(contractChangedata.getNewCompanyId());
		companyStore.setStoreId(storeIdList.get(0));
		companyStoreService.createCompanyStore(companyStore);

		//新公司修改业务类型
		CompanyDto newompanyDto = new CompanyDto();
		newompanyDto.setId(contractChangedata.getNewCompanyId());
		newompanyDto.setBizType("21801|21802");
		companyService.updateCompanyOnly(newompanyDto);

		ResultData<ContractDto> contractData = contractService.getContractById(contractChangedata.getContractId());
		ContractDto contract = contractData.getReturnData();

		//新合同创建
		ContractDto newContract = new ContractDto();
		newContract.setAgreementType(contract.getAgreementType());
		newContract.setAgreementNo(contractChangedata.getNewAgreementNo());
		newContract.setDateLifeStart(contractChangedata.getNewDateLifeStart());
		newContract.setDateLifeEnd(contractChangedata.getNewDateLifeEnd());
		newContract.setAuthRepresentative(contractChangedata.getAuthRepresentative());
		newContract.setAgentContact(contractChangedata.getAgentContact());
		newContract.setDepositFee(contractChangedata.getTotleDepositFee());
		newContract.setTotleDepositFee(contractChangedata.getTotleDepositFee());
		newContract.setPenaltyFee(contractChangedata.getPenaltyFee());
		newContract.setCompanyBankNo(contractChangedata.getCompanyBankNo());
		newContract.setBankAccount(contractChangedata.getBankAccount());
		newContract.setAccountName(contractChangedata.getAccountName());
		newContract.setRecipients(contractChangedata.getRecipients());
		newContract.setAccountProject(contractChangedata.getAccountProject());
		newContract.setAccountProjectNo(contractChangedata.getAccountProjectNo());

		if (StringUtils.isNotEmpty(contractChangedata.getCityNo())) {
			newContract.setCityNo(contractChangedata.getCityNo());
		} else {
			newContract.setCityNo(contract.getCityNo());
		}
		if (StringUtils.isNotEmpty(contractChangedata.getDistrictNo())) {
			newContract.setDistrictNo(contractChangedata.getDistrictNo());
		} else {
			newContract.setDistrictNo(contract.getDistrictNo());
		}

		newContract.setRecipientsAddress(contractChangedata.getRecipientsAddress());
		newContract.setContractB2A(20202);
		newContract.setCompanyId(contractChangedata.getNewCompanyId());
		newContract.setUserCreate(contractChangedata.getUserIdCreate());
		newContract.setPartyB(contractChangedata.getNewCompanyName());
		newContract.setPartyBAddress(contractChangedata.getNewCompanyAddress());
		newContract.setLealPerson(contractChangedata.getNewLegalPerson());
		newContract.setRemark("三方变更。原合同编号:" + contract.getContractNo() + ",原协议书编号:" + contract.getAgreementNo() + ",三方变更单号:" + contractChangedata.getContractStopNo());
		newContract.setContractStatus(10403);
		newContract.setStoreNum(1);
		newContract.setPartyBcityNo(contractChangedata.getNewCompanyAddressCityNo());
		newContract.setPartyBdistrictNo(contractChangedata.getNewCompanyAddressDistrictNo());
		newContract.setExpandingPersonnel(contract.getExpandingPersonnel());
		newContract.setExpandingPersonnelId(contract.getExpandingPersonnelId());
		newContract.setOaApproveType(contract.getOaApproveType());
		newContract.setPerformDate(approveDate);
		//新签
		newContract.setOriginalContractdistinction(18601);

		//原centerId为空或中心已不存在，取门店中心
		if(contract.getCenterId() == null){
			Store store = storeMapper.getPlainInfoById(storeIdList.get(0));
			newContract.setCenterId(store.getCenterId());
		}else{
			Map map = new HashMap();
			map.put("achieveType",17901);
			map.put("centerGroupId",contract.getCenterId());
			Achievement achievement = achievementMapper.getAchievementInfoContract(map);
			if(achievement == null){
				Store store = storeMapper.getById(storeIdList.get(0));
				newContract.setCenterId(store.getCenterId());
			}else{
				newContract.setCenterId(contract.getCenterId());
			}
		}

		newContract.setAcCityNo(contract.getAcCityNo());
		newContract.setContractType(contractChangedata.getContractType());
		newContract.setContractChangeId(contractChangedata.getId());

		Company companyData = companyService.getByCompanyId(contractChangedata.getNewCompanyId());
		newContract.setRegistrId(companyData.getBusinessLicenseNo());
		newContract.setCompanyNo(companyData.getCompanyNo());

		ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTRACT");
		newContract.setContractNo(data.getReturnData());
		newContract.setContractChangeId(contractChangedata.getId());

		//老合同编号
		Contract oldContract = contractMapper.getContractById(contractId);
		newContract.setOldContractNo(oldContract.getContractNo());

		//合同变更OA审核通过后，新生成的合同增加合同变更类型 add by haidan 2019/07/12
		newContract.setOaApproveContractType(DictionaryConstants.Contract_ChangeType_Third);

		ContractInfoDto contractInfo = new ContractInfoDto();
		contractInfo.setContractCreateType("3");
		contractInfo.setOldContractId(contractId);
		contractInfo.setContract(newContract);

		//合同门店关系
		ResultData<List<ContractChangeStore>> resultData = stopContractService.getContractChangeStoreByFlowId(flowId);
		List<ContractChangeStore> stopContractStore = resultData.getReturnData();
		List<StoreDto> storeDetails = new ArrayList<>();
		StoreDto storeDto = new StoreDto();
		storeDto.setStoreId( stopContractStore.get(0).getStoreId());
		storeDto.setABTypeStore(stopContractStore.get(0).getAbTypeStore());
		storeDto.setBTypeStore(stopContractStore.get(0).getBtypeStore() + "");
		storeDetails.add(storeDto);
		contractInfo.setStoreDetails(storeDetails);

		//门店联系人
		/*ContactsDto contactsDto = new ContactsDto();
		contactsDto.setStoreId(stopContractStore.get(0).getStoreId());
		contactsDto.setName(stopContractStore.get(0).getStoreManager());
		contactsDto.setMobilePhone(stopContractStore.get(0).getStoreManagerPhone());
		List<ContactsDto> contactsDtoList = new ArrayList<>();
		contactsDtoList.add(contactsDto);
		contractInfo.setContactsDtoList(contactsDtoList);*/

		//创建新的合同
		contractService.create(contractInfo);


		try {
			// Start 三方变更增加保证金转移 原合同的保证金转出，新合同的保证金转入
			ResultData<List<StoreDto>> storeById = storeService.getStoreById(stopContractStore.get(0).getStoreId());
			StoreDto storeInfo = storeById.getReturnData().get(0);

			Map<String, Object> record = new HashMap<>();
			record.put("storeId", storeInfo.getStoreId());
			record.put("oldContractId", contractId);
			//record.put("newContractId", contractId);
			record.put("storeNo", storeInfo.getStoreNo());
			record.put("storeName", storeInfo.getName());
			record.put("oldContractNo", contract.getContractNo());
			record.put("newContractNo", newContract.getContractNo());
			record.put("acCityNo", contract.getAcCityNo());
			storeDepositService.transferDeposit(record);
			// End 三方变更增加保证金转移 原合同的保证金转出，新合同的保证金转入
		} catch (Exception ex) {
			logger.error("APITimer.contractChangeHandle", "ContractChangeService", "doPassDateUpdateThreePart", flowId, null, "", "", ex);
		}
	}
	/**
	 * 门店迁址审核通过
	 * @param flowId
	 * @param contractId
	 * @param storeIdList
	 * @param userId
	 * @throws Exception
	 */
	public void doPassDateUpdateStoreRelocation(String flowId, Integer contractId, List<Integer> storeIdList,Integer userId,Date approveDate) throws Exception {
		Store store = new Store();
		ContractChange contractChangedata = contractChangeMapper.getContractChangeByFlowId(flowId);
		//同步状态到crm
		ContractChange change = new ContractChange();
		change.setFlowId(flowId);
		change.setApproveState(2); // 2:变更审核通过
		change.setApproveDate(approveDate);//审核通过日期
		change.setUpdateDate(new Date());// 更新日期
		change.setFlowEndDate(approveDate);// 更新日期
		change.setUpdateCreate(userId);// 更新人
		contractChangeMapper.update(change);

		//详情地址
		String addressDetail ="";
		//变更后的城市编号及区域
		String cityNo = contractChangedata.getStoreCity();
		String districtNo = contractChangedata.getStoreDistrict();
		//地址
		String address = contractChangedata.getStoreAdresss();
		//地址详情
		String storeCityName = SystemParam.getCityNameByCityNo(cityNo);
		String storeDistrictName= SystemParam.getDistrictNameByDistrictNo(districtNo);
		addressDetail = storeCityName + storeDistrictName +address;
		Integer contractStopId = contractChangedata.getId();
		Map<String,Object> param = new HashMap<>();
		param.put("contractStopId", contractStopId);
		param.put("contractId", contractId);
		if(null != storeIdList && storeIdList.size() >0) {
			for (Integer storeId : storeIdList) {
				param.put("storeId", storeId);
				ContractChangeStore contractChangeStore = contractChangeMapper.getContractChangeStoreByIds(param);

				//1.更新门店中的地址
				store.setCityNo(cityNo);
				store.setDistrictNo(districtNo);
				store.setAddress(address);
				store.setAddressDetail(addressDetail);
				store.setStoreId(storeId);

				store.setStoreManager(contractChangeStore.getStoreManager());
				store.setStoreManagerPhone(contractChangeStore.getStoreManagerPhone());
				store.setABTypeStore(contractChangeStore.getAbTypeStore());
				store.setBTypeStore(contractChangeStore.getBtypeStore()+"");
				storeMapper.update(store);
				//1.2更新联系人
				Contacts contacts = new Contacts();
				contacts.setStoreId(storeId);
				contacts.setName(contractChangeStore.getStoreManager());
				contacts.setMobilePhone(contractChangeStore.getStoreManagerPhone());
				Integer contractsFlag = contactsMapper.getContracts(contacts);
				if(0 == contractsFlag.intValue()){
					//新建联系人
					ResultData<String> back1 = seqNoAPI.getSeqNoByTypeCode("TYPE_CONTACT");
					String contractsNo = back1.getReturnData();
					contacts.setIsDefault(false);
					contacts.setContactsNo(contractsNo);
					int count = contactsMapper.create(contacts);
				}
				//2.更新合同门店表中的门店详情地址
				ContractStore cStore = new ContractStore();
				cStore.setStoreId(storeId);
				cStore.setAddressDetail(addressDetail);
				cStore.setContractId(contractId);
				contractStoreMapper.update(cStore);
				//3.更新合同变更门店表中的状态
				Integer integer  = 2 ;
				Map<String, Object> paramMap = new HashMap<>();
				paramMap.put("storeId", storeId);
				paramMap.put("contractId", contractId);
				paramMap.put("contractStopId", contractChangedata.getId());
				paramMap.put("approveState", integer);
				contractChangeMapper.updateContractStoreByParam(paramMap);
				//4.判断装修记录中装修申请状态更新门店地址
				Integer decorationStatus = contractChangeMapper.getDecorationApplyStatus(storeId);
				Map<String, Object> hashMap = new HashMap<>();
				hashMap.put("storeId", storeId);
				hashMap.put("contractId", contractId);
				Decoration decoration =storeMapper.getDecorationByContractIdAndStoreId(hashMap);
				if(null != decoration) {
					decoration.setStoreAddress(addressDetail);
					hashMap.put("storeAdress", addressDetail);

					Decoration de = new Decoration();
					ResultData<String> back = seqNoAPI.getSeqNoByTypeCode("TYPE_STOREDECORATION");
					String decorationNo = back.getReturnData();//新的装修编号
					de.setDecorateNo(decorationNo);
					de.setDecorateStatus(16301);//装修状态改为未装修
					de.setStoreNo(decoration.getStoreNo());
					de.setStoreName(decoration.getStoreName());
					de.setStoreAddress(addressDetail);
					de.setContractNo(decoration.getContractNo());
					de.setContractType(decoration.getContractType());
					de.setCompanyName(decoration.getCompanyName());
					de.setDelFlag("0");
					de.setDateCreate(new Date());
					de.setOafilpagency(decoration.getOafilpagency());
					de.setCityNo(decoration.getCityNo());
					de.setAgreementNo(decoration.getAgreementNo());
					de.setDateLifeStart(decoration.getDateLifeStart());
					de.setDateLifeEnd(decoration.getDateLifeEnd());
					StoreDecorationDto newDto =new StoreDecorationDto();
					newDto.setStoreId(storeId);
					newDto.setDecorationNo(decorationNo);
					newDto.setContractNo(decoration.getContractNo());
					newDto.setStoreNo(decoration.getStoreNo());
					newDto.setDateCreate(new Date());
					newDto.setUpdateDate(new Date());
					newDto.setDelFlag(false);
					newDto.setUserCreate(2221);
					if( 1 == decorationStatus.intValue()) {//未申请直接生成
						storeMapper.UpdateOmsStoreAdress(hashMap);
					}else if(2 == decorationStatus.intValue()) {//通过  成一条装修记录老记录只能查看，不允许操作 。
						omsInteractiveMapper.createDecrt(de);
						storeDecorationMapper.addDecorationRecord(newDto);
						hashMap.clear();
						hashMap.put("operateFlag", 1);
						hashMap.put("decorateId", decoration.getDecorateId());
						omsInteractiveMapper.updateOmsDecorationById(hashMap);
					}else if(3 == decorationStatus.intValue()) {//不通过，老记录再次翻牌按钮隐藏。
						omsInteractiveMapper.createDecrt(de);
						storeDecorationMapper.addDecorationRecord(newDto);
						hashMap.clear();
						hashMap.put("operateFlag", 2);
						hashMap.put("decorateId", decoration.getDecorateId());
						omsInteractiveMapper.updateOmsDecorationById(hashMap);
					}
				}

			}
		}



	}
}
