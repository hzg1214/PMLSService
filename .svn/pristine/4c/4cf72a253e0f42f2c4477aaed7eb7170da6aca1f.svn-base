package cn.com.eju.deal.contract.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.file.model.OaAttachment;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.contract.model.ContractChangeStore;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.contract.service.AchievementCancelService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.OaAttachmentService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.company.CompanyStoreDtoNew;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.contract.ContractSearchResultDto;
import cn.com.eju.deal.dto.oa.OaAttachmentDto;
import cn.com.eju.deal.dto.store.StoreDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 合同变更Controller层
 * 
 * @author sunmm
 * @date 2016年8月3日 下午8:39:34
 */
@RestController
@RequestMapping(value = "stopcontract")
public class ContractChangeController extends BaseController {

	/**
	 * 日志
	 */
	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Resource(name = "stopContractService")
	private ContractChangeService stopContractService;
	
	@Resource(name = "contractService")
	private ContractService contractService;
	
	@Resource(name = "oaAttachmentService")
	private OaAttachmentService oaAttachmentService;
	
	@Resource
	private AchievementCancelService achievmentCancelServer;
	/**
	 * 根据合同ID查询门店信息 (合同变更-新增页面用)
	 * 
	 * @param contractId
	 *            合同ID
	 * @return
	 */
	@RequestMapping(value = "/add/{contractId}", method = RequestMethod.GET)
	public String queryStoreOfAdd(@PathVariable int contractId) {
		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

		try {

			// 查询操作
			resultData = stopContractService.queryStore(contractId, "create", 0);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"queryStoreOfAdd", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 根据合同ID查询门店信息 (合同变更-更新页面用)
	 * 
	 * @param id
	 *            合同变更ID
	 * @param contractId
	 *            合同ID
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}/{contractId}", method = RequestMethod.GET)
	public String queryStoreOfEdit(@PathVariable int id, @PathVariable int contractId) {
		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

		try {

			// 查询操作
			resultData = stopContractService.queryStore(contractId, "update", id);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"queryStoreOfEdit", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}

	/**
	 * 根据合同变更ID查询门店信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/q/{id}", method = RequestMethod.GET)
	public String queryById(@PathVariable int id) {
		// 构建返回
		ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();

		try {

			// 查询操作
			resultData = stopContractService.queryById(id);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController", "queryById", "",
					-1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}

	/**
	 * 根据合同ID查询合同变更信息
	 * 
	 * @param contractId
	 *            合同ID
	 * @return
	 */
	@RequestMapping(value = "/get/{contractId}", method = RequestMethod.GET)
	public String getContractChange(@PathVariable int contractId) {
		// 构建返回
		ResultData<List<ContractChangeDto>> resultData = new ResultData<List<ContractChangeDto>>();

		try {

			// 查询操作
			resultData = stopContractService.getContractChange(contractId);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChange", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}

	/**
	 * 保存--合同变更信息
	 * 
	 * @param jsonDto
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@RequestBody String jsonDto) {
		// 构建返回
		ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();

		ContractInfoDto contractInfoDto = JsonUtil.parseToObject(jsonDto,ContractInfoDto.class);
		try {
			// 保存操作
			int count = stopContractService.create(contractInfoDto);

			if (count <= 0) {
				resultData.setFail();
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("Contract", "ContractChangeController", "create", "",
					-1, "", "新增合同变更信息异常！", e);
		}
		return resultData.toString();
	}
	
	/** 
     * 更新--合同变更信息
     * @param param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String contractInfoDtoJson)
    {
        
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        
        try
        {
        	// 更新操作
        	stopContractService.update(contractInfoDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractChangeController", "update", "", -1, "", "更新失败", e);
        }
        
        return resultData.toString();
    }

    /** 
	* 根据 流程Id及审核状态 更新合同信息 
	* @param flowId 流程ID 
	* @param auditRst 审核状态
	* @param userId 操作人ID
	* @return 处理结果
	* @throws Exception
	*/
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/flowId/auditRst/{flowId}/{auditRst}/{userId}", method = RequestMethod.GET)
	public String updateCtrctRelateInfo(@PathVariable String flowId, @PathVariable Integer auditRst, @PathVariable Integer userId) {
		// 构建返回
		ResultData<?> rstData = new ResultData<>();
		try {
			// 根据FlowId查询合同变更下的门店
			Map<String,Object> resultMap = getCtrctInfoByFlowId(flowId);
			Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
			List<Integer> storeIdList = (List<Integer>)resultMap.get("storeIdList");
			if(null != storeIdList && !storeIdList.isEmpty()) {
				if(0 == auditRst) {// 审核通过
					// 审核通过：根据flowId、合同Id和门店Ids进行后台操作处理
					//doPassDateUpdate(flowId,contractId,storeIdList,userId);
					//doPassDateUpdateAddon(flowId,contractId,storeIdList,userId);
					stopContractService.doPassDateUpdateAddon(flowId,contractId,storeIdList,userId,null);
					rstData.setReturnMsg("变更审核通过!更新合同信息成功!");
				}else if (5 == auditRst || 15 == auditRst){ // 审核未通过
					// 审核不通过：根据flowId、合同Id和门店Ids进行后台操作处理
					doNoPassDateUpdate(flowId,contractId,storeIdList,userId);
					rstData.setReturnMsg("变更审核不通过!更新合同信息成功!");
				}
			} else {
				rstData.setReturnMsg("没有变更的门店!");
			}
		} catch (Exception e) {
			rstData.setFail();
			logger.error("contract", "ContractChangeController",
					"updateCtrctRelateInfo", "", -1, "", "根据审核状态更新合同信息失败！", e);
		}
		return rstData.toString();
	}
    
    /**
	 * 查询审核中的合同变更信息
	 * @param contractId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/approveState/{param}", method = RequestMethod.GET)
	public String queryByApproveState(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		// 构建返回
		ResultData<List<ContractChangeDto>> resultData = new ResultData<List<ContractChangeDto>>();
		try {
			// 查询操作
			resultData = stopContractService.queryByApproveState(queryParam);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController","getContractChange", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 定时更新合同变更的审核状态
	 * @param param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/job/chgState/{param}", method = RequestMethod.GET)
	public String updateChgStatusByParam(@PathVariable String param) {
		// 构建返回
		ResultData<String> resultData = new ResultData<String>();
		Map<String, Object> queryParam = JsonUtil.parseToObject(param,
				Map.class);
		logger.info("CRM Service 定时任务，param = ", param);
		List<String> passList = (List<String>) queryParam.remove("pass");
		List<String> nopassList = (List<String>) queryParam.remove("noPass");
		try {
			// 审核通过
			if (null != passList && !passList.isEmpty()) {
				for(String flowId:passList){
					// 根据FlowId查询合同变更下的门店
					Map<String,Object> resultMap = getCtrctInfoByFlowId(flowId);
					Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
					List<Integer> storeIdList = (List<Integer>)resultMap.get("storeIdList");
					// 根据flowId、合同Id和、门店Ids做数据处理
					if(null != storeIdList && !storeIdList.isEmpty()) {
						//doPassDateUpdate(flowId, contractId, storeIdList,1);
						//doPassDateUpdateAddon(flowId, contractId, storeIdList,1);
						stopContractService.doPassDateUpdateAddon(flowId, contractId, storeIdList,1,null);
						resultData.setReturnMsg("变更审核通过!更新合同信息成功!");
					}
				}
			}
			// 审核不通过
			if (null != nopassList && !nopassList.isEmpty()) {
				for(String flowId:nopassList){
					// 根据FlowId查询合同变更下的门店
					Map<String,Object> resultMap = getCtrctInfoByFlowId(flowId);
					Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
					List<Integer> storeIdList = (List<Integer>)resultMap.get("storeIdList");
					// 根据flowId、合同Id和、门店Ids做数据处理
					if(null != storeIdList && !storeIdList.isEmpty()) {
						doNoPassDateUpdate(flowId, contractId, storeIdList,1);
						resultData.setReturnMsg("变更审核通过!更新合同信息成功!");
					}
				}
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("Contract", "ContractContrller",
					"updateStatusByParam", "", -1, "", "定时批量更新状态失败", e);
		}
		return resultData.toString();
	}
	
	/** 
	* 根据合同变更ID查询门店数
	* @param id 合同变更ID
	* @return
	*/
	@RequestMapping(value = "/getstorenum/{id}", method = RequestMethod.GET)
	public String getStopStoreNum(@PathVariable int id) {
		// 构建返回
		ResultData<Integer> resultData = new ResultData<Integer>();

		try {

			// 查询操作
			Integer count = stopContractService.getStopStoreNum(id);
			resultData.setReturnData(count);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController", "getStopStoreNum", "",
					-1, "", "查询合同变更门店数失败！", e);
		}
		return resultData.toString();
	}

	/** 
	* 根据合同变更ID查询门店地址
	* @param contractStopId 合同变更ID
	* @return
	*/
	@RequestMapping(value = "/getstore/{contractStopId}", method = RequestMethod.GET)
	public String getStoreInfo(@PathVariable int contractStopId) {
		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

		try {
			// 查询操作
			resultData = stopContractService.getStoreInfo(contractStopId);
			
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getStoreInfo", "", -1, "", "查询门店地址失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 根据contractStopId和fileTypeCode查询附件关联信息
	 * 
	 * @param contractStopId
	 *            合同变更ID
	 * @param fileTypeCode 
	 * 			  文件类型
	 * @return
	 */
	@RequestMapping(value = "/oaAttachment/{contractStopId}/{fileTypeCode}", method = RequestMethod.GET)
	public String getOaAttachment(@PathVariable int contractStopId, @PathVariable String fileTypeCode)
    {
		// 构建返回
		ResultData<List<OaAttachmentDto>> resultData = new ResultData<List<OaAttachmentDto>>();
		try {
			// 查询操作
			resultData = oaAttachmentService.getOaAttachment(contractStopId, fileTypeCode);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController","getOaAttachment", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	
	/** 
     * 更新--合同门店关联表中的StoreState
     * @param param
     * @return
     */
    @RequestMapping(value = "/update/{storeId}/{contractId}", method = RequestMethod.GET)
    public String updateStoreState(@PathVariable  Integer storeId, @PathVariable  Integer contractId)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        try
        {
        	// 更新变更门店为终止
    		ContractStore contractStore = new ContractStore();
    		// 门店ID
    		contractStore.setStoreId(storeId);
    		// 合同ID
    		contractStore.setContractId(contractId);
    		// 门店状态 (1 : 变更中)
    		contractStore.setStoreState(1);
    		
    		// 更新操作
    		Integer num = contractService.updateContractStore(contractStore);
    		
    		resultData.setReturnData(num);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractChangeController", "updateStoreState", "", -1, "", "更新失败！", e);
        }
        
        return resultData.toString();
    }
    
    /** 
    * 更新--将flowId和审核状态(approveState)更新到变更记录中
    * @param contractInfoDtoJson
    * @return
    */
    @RequestMapping(value = "/updateFlowId", method = RequestMethod.PUT)
    public String updateFlowId(@RequestBody String contractInfoDtoJson)
    {
        
        //构建返回
        ResultData<ContractInfoDto> resultData = new ResultData<ContractInfoDto>();
        
        ContractInfoDto contractInfoDto = JsonUtil.parseToObject(contractInfoDtoJson, ContractInfoDto.class);
        
        try
        {
            // 更新操作
            stopContractService.updateFlowId(contractInfoDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractChangeController", "updateFlowId", "", -1, "", "更新失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
    * 查询该合同ID对应的所有approveState是变更中的门店信息
    * @param id 合同变更ID
    * @return
    */
    @RequestMapping(value = "/get/store/{id}", method = RequestMethod.GET)
	public String getContractChangeById(@PathVariable int id) {
		// 构建返回
		ResultData<List<ContractChangeDto>> resultData = new ResultData<List<ContractChangeDto>>();

		try {

			// 查询操作
			resultData = stopContractService.getContractChangeById(id);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeById", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
    
    /** 
     * 保存信息到图片关系表返回PicId
     * @param param
     * @return
     */
    @RequestMapping(value = "oa/upload", method = RequestMethod.POST)
    public String createOAPicIdRecord(@RequestBody String changeDto)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        try
        {
            ContractInfoDto contractInfoDto = JsonUtil.parseToObject(changeDto, ContractInfoDto.class);
            ContractChangeDto contractChangeDto = contractInfoDto.getContractChangeDto();
            OaAttachment oaAttachment = new OaAttachment();
            
            oaAttachment.setFileCode(contractChangeDto.getFileCode());
            oaAttachment.setFileName(contractChangeDto.getFileName());
            oaAttachment.setFileTypeCode(contractChangeDto.getFileTypeCode());
            oaAttachment.setUserIdCreate(contractChangeDto.getUserCreate());
            oaAttachment.setDelFlag(contractChangeDto.getDelFlag());
            
            
            if(contractChangeDto.getContractChangePicId() == null || contractChangeDto.getContractChangePicId() <= 0 ){
                // 插入操作
                Integer backId = oaAttachmentService.createOAPicIdRecord(oaAttachment);
                if(backId==null || backId < 0){
                    resultData.setFail();
                }else{
                    resultData.setReturnData(backId);
                }
            }else{
                //更新操作
                oaAttachment.setId(contractChangeDto.getContractChangePicId());
                oaAttachmentService.updateOAPicByPicId(oaAttachment);
                //标记为更新返回值
                resultData.setReturnData(0);
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractChangeController", "createOAPicIdRecord", "", -1, "", "新增图片至OaAttachment失败", e);
        }
        
        
        return resultData.toString();
    }
    
    /**
     * 根据合同变更ID查询门店信息
     * 
     * @param id
     *            合同变更ID
     * @param contractId
     *            合同ID
     * @return
     */
    @RequestMapping(value = "/view/{id}/{contractId}", method = RequestMethod.GET)
    public String queryStoreOfView(@PathVariable int id ,@PathVariable int contractId) {
        // 构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();

        try {

            // 查询操作
            resultData = stopContractService.queryStore(contractId, "query", id);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("contract", "ContractChangeController",
                    "queryStoreOfView", "", -1, "", "查询-对象失败！", e);
        }
        return resultData.toString();
    }
    
    /** 
     *  根据门店Id查询签约的合同
     * @param storeId 门店Id
     * @return
     */
    @RequestMapping(value = "/store/{param}", method = RequestMethod.GET)
    public String getSignHisByStoreId(@PathVariable String param)
    {
        List<?> storeList = JsonUtil.parseToObject(param, List.class);
        Map<String, Object> queryParam =new HashMap<String, Object>();
        
        queryParam.put("topOne", "topOne");
        if (null != storeList && !storeList.isEmpty())
        {
            Integer[] arr = (Integer[])storeList.toArray(new Integer[storeList.size()]);
            queryParam.put("storeList",arr);
        }
        //构建返回
        ResultData<List<ContractSearchResultDto>> resultData = new ResultData<List<ContractSearchResultDto>>();
        try
        {
            resultData = contractService.getSignHisByStoreId(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getSignHisByStoreId", "", -1, "", "根据门店Id查询签约的合同失败", e);
        }
        return resultData.toString();
    }
    
    /**
     * 根据flow查询合同Id以及合同下的门店Id集合
     * @return
     * @throws Exception 
     */
    private Map<String,Object> getCtrctInfoByFlowId(String flowId) throws Exception{
    	Map<String,Object> resultMap = new HashMap<String,Object>();
    	// 根据FlowId查询合同变更下的门店
		List<Integer> storeIdList = new ArrayList<Integer>();
		ResultData<List<ContractChangeStore>> resultDataCcs = stopContractService.getChangeStoresByFlowId(flowId);
		List<ContractChangeStore> contractStoreList = resultDataCcs.getReturnData();
		// 合同ID
		int contractId = 0;
		if(null != contractStoreList && !contractStoreList.isEmpty()) {
			for(ContractChangeStore contractStore : contractStoreList){
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
     * @throws Exception 
     */
    private void doNoPassDateUpdate(String flowId, Integer contractId, List<Integer> storeIdList,Integer userId) throws Exception{
    	for(Integer storeId :storeIdList){
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
			contractChange.setUpdateCreate(userId);// 更新人
			contractInfoDto.setContractChangeDto(contractChange);
			stopContractService.update(contractInfoDto);
		}
    }
    
    /**
     * 
    * 检验变更门店是否在门店业绩撤销中
    * @param param
    * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "contractstore/getcancelstate/{param}", method = RequestMethod.GET)
    public String getStoreCancelState(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        // 构建返回
        ResultData<String> resultData = new ResultData<>();
        try {
            // 查询操作
            resultData = stopContractService.getStoreCancelState(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("contract", "ContractChangeController","getStoreCancelState", "", -1, "", "检验变更门店是否在门店业绩撤销中,查询-对象失败！", e);
        }
        return resultData.toString();
    }
    
    /**
     * 根据门店ID查找合同门店变更信息 
     * @param storeId
     * @return
     */
	@RequestMapping(value = "getstorechange/{storeId}", method = RequestMethod.GET)
	public String getContractChangeByStoreId(@PathVariable int storeId) {
		// 构建返回
		ResultData<ContractChangeDto> resultData = new ResultData<ContractChangeDto>();

		try {

			// 查询操作
			resultData = stopContractService.getContractChangeByStoreId(storeId);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeByStoreId", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
    
	/** 
	* 获取该合同变更的门店地址
	* @param contractStopId 合同变更ID
	* @return
	* @throws Exception
	*/
	@RequestMapping(value = "/getchgstoreaddr/{contractStopId}", method = RequestMethod.GET)
	public String getChgStoreAddr(@PathVariable Integer contractStopId) throws Exception{
		ResultData<String> resultData = new ResultData<String>();
		try {
			// 查询操作
			resultData = stopContractService.getChgStoreAddr(contractStopId);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getChgStoreAddr", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 根据flowId查询合同变更信息
	 * @param flowId
	 * @return
	 */
	@RequestMapping(value = "getContractChangeByFlowId/{flowId}", method = RequestMethod.GET)
	public String getContractChangeByFlowId(@PathVariable String flowId) {
		// 构建返回
		ResultData<ContractChange> resultData = new ResultData<ContractChange>();

		try {

			// 查询操作
			resultData = stopContractService.getContractChangeByFlowId(flowId);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeByFlowId", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	
	/**
	 * 根据flowId查询合同变更门店表信息
	 * @param flowId
	 * @return
	 */
	@RequestMapping(value = "getContractChangeStoreByFlowId/{flowId}", method = RequestMethod.GET)
	public String getContractChangeStoreByFlowId(@PathVariable String flowId) {
		// 构建返回
		ResultData<List<ContractChangeStore>> resultData = new ResultData<List<ContractChangeStore>>();

		try {

			// 查询操作
			resultData = stopContractService.getContractChangeStoreByFlowId(flowId);

		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeStoreByFlowId", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	
	//Add by cning 2017/07/04 Start
	/**
     * 根据门店ID和合同ID查找合同对应的门店是否撤销 
     * @param storeId
     * @return
     */
	@RequestMapping(value = "getContractStoreByParm/{storeId}/{contractId}", method = RequestMethod.GET)
	public String getContractStoreByParm(@PathVariable int storeId,@PathVariable int contractId) {
		// 构建返回
		ResultData<List<AchievementCancel>> resultData = new ResultData<List<AchievementCancel>>();

		try {
			Map<String,Object> queryParam=new HashMap<String,Object>();
			queryParam.put("storeId", storeId);
			queryParam.put("contractId", contractId);
			
			// 查询操作
			List<AchievementCancel> list = achievmentCancelServer.getCancelStore(storeId, contractId);
			resultData.setReturnData(list);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeByStoreId", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	/**
	//Add by cning 2017/07/04 End
	 * 根据门店ID和合同ID查找合同对应的门店是否撤销 
	 * @param storeId
	 * @return
	 */
	
	@RequestMapping(value = "/getContractChangeStoreById/{storeId}/{contractStopId}", method = RequestMethod.GET)
	public String getContractChangeStoreById(@PathVariable int storeId,@PathVariable int contractStopId) {
		// 构建返回
		ResultData<ContractChangeStore> resultData = new ResultData<ContractChangeStore>();
		
		try {
			Map<String,Object> queryParam=new HashMap<String,Object>();
			queryParam.put("storeId", storeId);
			queryParam.put("contractStopId", contractStopId);
			
			// 查询操作
			List<ContractChangeStore> list = stopContractService.getContractChangeStoreById(queryParam);
			if(list !=null){
				
				resultData.setReturnData(list.get(0));
			}
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeStoreById", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	@RequestMapping(value = "/splitToSingle/{id}/{storeIds}/{codes}", method = RequestMethod.GET)
	public String splitToSingle(@PathVariable Integer id,@PathVariable String storeIds,@PathVariable String codes) {
		
		// 构建返回
		ResultData<String> resultData = new ResultData<>();
		
		try {
		    String[] storeIdList = storeIds.split(",");
		    String[] preCodeList = codes.split(",");
			String data = stopContractService.splitToSingle(id,storeIdList,preCodeList);
			resultData.setReturnData(data);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"getContractChangeStoreById", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}
	@RequestMapping(value = "/queryStoreOfCountByConractId/{contractId}", method = RequestMethod.GET)
	public String queryStoreOfCountByConractId(@PathVariable Integer contractId) {
		
		// 构建返回
		ResultData<Integer> resultData = new ResultData<>();
		
		try {
			
			Integer data = stopContractService.queryStoreOfCountByConractId(contractId);
			resultData.setReturnData(data);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("contract", "ContractChangeController",
					"queryStoreOfCountByConractId", "", -1, "", "查询-对象失败！", e);
		}
		return resultData.toString();
	}

    /**
     * 删除变更记录
     * @param id
     * @return
     */
	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable Integer id)
	{

		//构建返回
		ResultData<?> resultData = new ResultData<ContractInfoDto>();
		try
		{
			// 更新操作
			stopContractService.delete(id);
		}
		catch (Exception e)
		{
			resultData.setFail("变更记录作废失败");
			logger.error("Contract", "ContractChangeController", "delete", "", -1, "", "变更记录作废失败", e);
		}

		return resultData.toString();
	}
	/** 
     *  根据门店和合同查询已解除关联的门店公司
     * @param storeId 门店Id
     * @return
     */
    @RequestMapping(value = "/getIsRelieveCompany/{contractId}/{param}", method = RequestMethod.GET)
    public String getIsRelieveCompany(@PathVariable Integer contractId,@PathVariable String param){
        List<?> storeList = JsonUtil.parseToObject(param, List.class);
        Map<String, Object> queryParam =new HashMap<String, Object>();
        queryParam.put("contractId", contractId);
        if (null != storeList && !storeList.isEmpty()){
            Integer[] arr = (Integer[])storeList.toArray(new Integer[storeList.size()]);
            queryParam.put("storeList",arr);
        }
        //构建返回
        ResultData<List<CompanyStoreDtoNew>> resultData = new ResultData<List<CompanyStoreDtoNew>>();
        try{
            resultData = contractService.getIsRelieveCompany(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getIsRelieveCompany", "", -1, "", "根据门店和合同查询已解除关联的门店公司失败", e);
        }
        return resultData.toString();
    }

	/**
	 * 三方变更审核通过
	 * @param flowId
	 * @param auditRst
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/updateInfoForThreePart/{flowId}/{auditRst}/{userId}", method = RequestMethod.GET)
	public String updateInfoForThreePart(@PathVariable String flowId, @PathVariable Integer auditRst, @PathVariable Integer userId) {
		// 构建返回
		ResultData<?> rstData = new ResultData<>();
		try {
			// 根据FlowId查询合同变更下的门店
			Map<String,Object> resultMap = getCtrctInfoByFlowId(flowId);
			Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
			List<Integer> storeIdList = (List<Integer>)resultMap.get("storeIdList");
			if(null != storeIdList && !storeIdList.isEmpty()) {
				if(0 == auditRst) {// 审核通过
					stopContractService.doPassDateUpdateThreePart(flowId,contractId,storeIdList,userId,null);
					rstData.setReturnMsg("变更审核通过!更新合同信息成功!");
				}else if (5 == auditRst || 15 == auditRst){ // 审核未通过
					doNoPassDateUpdate(flowId,contractId,storeIdList,userId);
					rstData.setReturnMsg("变更审核不通过!更新合同信息成功!");
				}
			} else {
				rstData.setReturnMsg("没有变更的门店!");
			}
		} catch (Exception e) {
			rstData.setFail();
			logger.error("contract", "ContractChangeController",
					"updateInfoForThreePart", "", -1, "", "根据审核状态更新合同信息失败！", e);
		}
		return rstData.toString();
	}

	@RequestMapping(value = "/operateAuditCt/{id}", method = RequestMethod.GET)
	public String operateAuditCt(@PathVariable Integer id)
	{
// 构建返回
		ResultData<?> rstData = new ResultData<>();
		ContractInfoDto contractInfoDto = new ContractInfoDto();
		ContractChangeDto contractChange = new ContractChangeDto();
		contractChange.setId(id);
		contractChange.setApproveState(3);
		contractChange.setUpdateDate(new Date());
		contractInfoDto.setContractChangeDto(contractChange);
		try {
			int rel = stopContractService.updateFlowId(contractInfoDto);
		} catch (Exception e) {
			rstData.setFail("状态变更失败");
			logger.error("contract",
					"ContractChangeController",
					"operateAuditCt",
					"",
					-1,
					"",
					"状态变更失败！",
					e);
		}
		rstData.setReturnMsg("状态变更成功!");
		return rstData.toString();
	}
}

    