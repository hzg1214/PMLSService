package cn.com.eju.deal.gpContractChange.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.seqNo.api.impl.SeqNoAPIImpl;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.common.util.XmlTransferUtil;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.DateUtil;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dataSource.DbcontextHolder;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeMapper;
import cn.com.eju.deal.gpContractChange.dao.GpContractChangeStoreMapper;
import cn.com.eju.deal.gpContractChange.model.GpContractChange;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeDto;
import cn.com.eju.deal.gpContractChange.model.GpContractChangeStore;
import cn.com.eju.deal.mapper.signContract.SignContractMapper;
import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;
import cn.com.eju.deal.open.controller.UploadOAUtil;
import com.seeyon.client.CTPRestClient;
import com.seeyon.client.CTPServiceClientManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("gpContractChangeService")
public class GpContractChangeService extends BaseService<ContractChange> {
	//附件数据Map key
    private final static String ATTACH_KEY = "attachment";
	//表单数据Map key
    private final static String XML_DATA_KEY = "xmlData";
    //日期
    private static final String YYYYMMDD_FORMAT = "yyyy-MM-dd";
    
  //组装表单数据 -修改表单 Map key
    private final static String FLAG_MODIFY_KEY = "oaModify";
    
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
	private SignContractMapper signContractMapper;
    
    @Resource
    private SeqNoAPIImpl seqNoAPI;
    
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /**
	 * 根据合同ID查询门店信息
	 * @param gpContractId 公盘合同ID
	 * @throws Exception
	 */
	public ResultData<List<StoreDto>> queryStoreInfoOfGpContract(Integer id,Integer gpContractId,String type) throws Exception {

		// 构建返回
		ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
		List<StoreDto> storeMdlList = new ArrayList<StoreDto>();
		if("create".equals(type)) {
			storeMdlList = gpContractChangeMapper.queryStoreInfoOfGpContract(gpContractId);
		}else {
			Map<String, Object> param = new HashMap<>();
			param.put("id", id);
			param.put("gpContractId", gpContractId);
			storeMdlList = gpContractChangeMapper.queryStoreInfoOfGpContractStopEdit(param);
		}
		// 查询操作
		//判断是否
        List<StoreDto> storeDtoList = gpContractChangeMapper.queryLockingStoreForStop(gpContractId);
        if(null !=storeDtoList && storeDtoList.size() >0){
        	for(StoreDto mainDto : storeMdlList){
        		mainDto.setDisabledFlag("1");
        		for(StoreDto dto : storeDtoList){
        			if(mainDto.getStoreId().equals(dto.getStoreId())){
        				mainDto.setDisabledFlag("2");
        			}
        		}
        	}
        }
		resultData.setReturnData(storeMdlList);
		return resultData;
	}
	/**
     * 查询公盘合同终止列表
     * @param reqMap
     * @return
     */
    public ResultData<List<GpContractChangeDto>> getGpContractStopList(Map<String, Object> reqMap) throws Exception {
        ResultData<List<GpContractChangeDto>> resultData = new ResultData<List<GpContractChangeDto>>();
        resultData.setFail();
        List<GpContractChangeDto> gpContractChangeDtoList = gpContractChangeMapper.getGpContractStopList(reqMap);
        if (gpContractChangeDtoList != null && gpContractChangeDtoList.size() > 0) {
            resultData.setReturnData(gpContractChangeDtoList);
            resultData.setTotalCount((String) reqMap.get(QueryConst.TOTAL_COUNT));
            resultData.setSuccess();
        }
        return resultData;
    }
	/**
	  * 根据id查询公盘合同终止详情
	  * @param id
	  * @return
	  * @throws Exception
	  */
   public ResultData<GpContractChangeDto> getGpContractStopInfoById(Integer id) throws Exception {
       //构建返回
   	   ResultData resultData = new ResultData();
       resultData.setFail();
       //根据框架合同id获取其详细信息
       GpContractChangeDto gpContractChangeDto =gpContractChangeMapper.getGpContractStopInfoById(id);
       
       if(gpContractChangeDto != null){
		   //add by haidan 2019/05/31
		   //获取 合同审批历史列表信息
		   if (null != gpContractChangeDto.getFlowId() && !gpContractChangeDto.getFlowId().isEmpty()) {
			   ContractAuditRecordDto contractAuditRecordDto = new ContractAuditRecordDto();
			   contractAuditRecordDto.setFlowId(gpContractChangeDto.getFlowId());
			   //设置OA数据源
			   DbcontextHolder.setDbType("dataSourceOA");
			   List<ContractAuditRecordDto> contractAuditRecordDtoList = signContractMapper.getContractAuditRecordList(contractAuditRecordDto);
			   //还原数据源
			   DbcontextHolder.setDbType("dataSourceMain");
			   gpContractChangeDto.setContractAuditRecordDtoList(contractAuditRecordDtoList);
		   }

    	   List<GpContractChangeStore> gpContractChangeStoreList  = gpContractChangeStoreMapper.getGpContractStopStoreInfoById(id);
	       	/**
	       	 *  获取文件信息：终止合作协议书/单方解除函、保证金收据、其他
	       	 */
	       	String fileRecordMainIds = "";
	       	FileRecordMain attachmentFile = new FileRecordMain();
	       	attachmentFile.setRefId(id);
	       	attachmentFile.setIsDelete(false);
	       	//终止合作协议书/单方解除函
	       	List<ContractFileDto> stopContractFileList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList1 = fileRecordMainMapper.getStopGpContractFileListByStopId(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList1, fileRecordMainIds, stopContractFileList);
	       	//保证金收据
	       	List<ContractFileDto> receiptFileList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList2 = fileRecordMainMapper.getReceiptFileListByStopId(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList2, fileRecordMainIds, receiptFileList);
	       	//附件
	       	List<ContractFileDto> othersFileList = new ArrayList<ContractFileDto>();
	       	List<FileRecordMain> fileMdlList3 = fileRecordMainMapper.getOthersFileListByStopId(attachmentFile);
	       	fileRecordMainIds = pushFileRecord(fileMdlList3, fileRecordMainIds, othersFileList);
	       	if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
	       		fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
	       	}
	       	
	       	gpContractChangeDto.setStopContractFileList(stopContractFileList);
	       	gpContractChangeDto.setReceiptFileList(receiptFileList);
	       	gpContractChangeDto.setOthersFileList(othersFileList);
	       	gpContractChangeDto.setFileRecordMainIds(fileRecordMainIds);
	       	gpContractChangeDto.setGpContractChangeStoreList(gpContractChangeStoreList);
	       	//返回
	   		resultData.setReturnData(gpContractChangeDto);
	   		resultData.setSuccess();
	    }
		return resultData;
   	} 
	/**
     * 保存或更新公盘合同终止申请
     */
	public ResultData<?> saveGpContractChange(Map<String, Object> reqMap) throws Exception {
        ResultData<String> resultData = new ResultData<>();
        GpContractChange gpContractChange = new GpContractChange();
        //公盘合同id、No、公司id、公司名称、协议书编号
        Integer gpContractId = Integer.valueOf((String) reqMap.get("gpContractId"));
        //根据公盘合同id查询详情
        GpContract gpContract = gpContractChangeStoreMapper.getGpContractById(gpContractId);
        /*String gpContractNo = (String) reqMap.get("gpContractNo");
        Integer companyId = Integer.valueOf((String) reqMap.get("companyId"));
        String companyName = (String) reqMap.get("companyName");
        String gpAgreementNo = (String) reqMap.get("gpAgreementNo");*/
        String gpContractNo = gpContract.getGpContractNo();
        Integer companyId = gpContract.getCompanyId();
        String companyName = gpContract.getCompanyName();
        String gpAgreementNo = gpContract.getGpAgreementNo();
        String gpContractCityNo = gpContract.getCityNo();
        //终止类型
        Integer stopType = Integer.valueOf((String) reqMap.get("stopType"));
        //终止理由
        String stopReason = (String) reqMap.get("stopReason");
        //终止方案描述 
        String stopDescribe = (String) reqMap.get("stopDescribe");
        //备注 
        String remarks = (String) reqMap.get("remarks");
        //终止时间 
        String stopDate = (String) reqMap.get("stopDate");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if (StringUtil.isNotEmpty(stopDate)) {
			// 合作终止时间
			gpContractChange.setStopDate(format.parse(stopDate));
		}
        //预计退款金额 
		String ptBackAmount = (String) reqMap.get("ptBackAmount");
		String fileRecordMainIds = reqMap.get("fileRecordMainIds").toString();
		
		String type = (String) reqMap.get("type");
		Integer userIdCreate = Integer.valueOf(reqMap.get("userIdCreate").toString());
		gpContractChange.setGpContractId(gpContractId);
		gpContractChange.setGpContractNo(gpContractNo);
		gpContractChange.setCompanyId(companyId);
		gpContractChange.setCompanyName(companyName);
		gpContractChange.setGpAgreementNo(gpAgreementNo);
		gpContractChange.setGpContractCityNo(gpContractCityNo);
		gpContractChange.setChangeType(17001);
		gpContractChange.setStopType(stopType);
		gpContractChange.setStopReason(stopReason);
		gpContractChange.setStopDescribe(stopDescribe);
		gpContractChange.setRemarks(remarks);
		gpContractChange.setPtBackAmount(new BigDecimal(ptBackAmount));
		gpContractChange.setApproveState(0);
		gpContractChange.setSubmitOAStatus(21201);
		gpContractChange.setDelFlag("0");
		
		String gpContractStopId = "";
		if("create".equals(type)) {
			if(reqMap.containsKey("gpContractStopNo")) {
				//终止编号
				String gpContractStopNo = (String) reqMap.get("gpContractStopNo");
				gpContractChange.setUserIdCreate(userIdCreate);
				gpContractChange.setDateCreate(new Date());
				gpContractChange.setGpContractStopNo(gpContractStopNo);
				//保存
				int count = gpContractChangeMapper.create(gpContractChange);
				if(count <=0) {
					resultData.setFail("保存公盘合同终止失败");
					return resultData;
				}
				Integer gpContractChangeId =  gpContractChange.getId();
				gpContractStopId = gpContractChangeId + "";
				
				if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
					String[] arrays = fileRecordMainIds.split(",");
					// 关联相关文件(RefId)
					for (int i = 0; i < arrays.length; i++){
						if (StringUtil.isNotEmpty(arrays[i])){
							FileRecordMain fileRecordMain = new FileRecordMain();
							fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
							fileRecordMain.setRefId(gpContractChangeId);
							fileRecordMain.setIsDelete(false);
							fileRecordMainMapper.update(fileRecordMain);
						}
					}
				}
			}else {
				resultData.setFail("保存公盘合同终止失败,无终止编号");
				return resultData;
			}
			
		} else if("update".equals(type)) {
			String oldfileRecordMainIds = reqMap.get("oldfileRecordMainIds").toString();
			//变更主键
			Integer id = Integer.valueOf((String) reqMap.get("id"));
			gpContractStopId = id + "";
			gpContractChange.setUserIdUpt(userIdCreate);
			gpContractChange.setDateUpt(new Date());
			gpContractChange.setId(id);
			int count = gpContractChangeMapper.updateStr(gpContractChange);
			if(count <=0) {
				resultData.setFail("更新公盘合同终止失败");
                return resultData;
			}
			//对原上传文件删除
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
			//删除门店关系
			gpContractChangeStoreMapper.updateById(id);
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
			
		}
		//重新建立新的公盘合同变更与门店的关系
		this.addGpContractChangeStore(reqMap, gpContractStopId);
		resultData.setSuccess();
        return resultData;
    }
	private void addGpContractChangeStore(Map<String, Object> reqMap, String gpContractStopId) throws Exception{
		String storeIds = (String) reqMap.get("storeIdArray");
		Integer userIdCreate = Integer.valueOf(reqMap.get("userIdCreate").toString());
		if (storeIds != null && StringUtil.isNotEmpty(storeIds)) {
			String[] arrays = storeIds.split(",");
			for (int i = 0; i < arrays.length; i++) {
				GpContractChangeStore gpContractChangeStore = new GpContractChangeStore();
				gpContractChangeStore.setGpContractStopId(Integer.valueOf(gpContractStopId));
				int storeId = Integer.valueOf(arrays[i]);
				String storeNo = (String) reqMap.get("storeNo" +storeId);
				String storeName = (String) reqMap.get("storeName" +storeId);
				String storeAddress = (String) reqMap.get("storeAddress" +storeId);
				String storeManager = (String) reqMap.get("storeManager" +storeId);
				String storeManagerPhone = (String) reqMap.get("storeManagerPhone" +storeId);
				String maintainer = (String) reqMap.get("maintainer" +storeId);
				String maintainerName = (String) reqMap.get("maintainerName" +storeId);
				
				gpContractChangeStore.setDelFlag("0");
				gpContractChangeStore.setCancelStatus(0);
				gpContractChangeStore.setStoreId(storeId);
				gpContractChangeStore.setStoreNo(storeNo);
				gpContractChangeStore.setStoreName(storeName);
				gpContractChangeStore.setStoreAddress(storeAddress);
				gpContractChangeStore.setStoreManager(storeManager);
				gpContractChangeStore.setStoreManagerPhone(storeManagerPhone);
				gpContractChangeStore.setMaintainer(maintainer);
				gpContractChangeStore.setMaintainerName(maintainerName);
				//入库操作
				gpContractChangeStore.setUserIdCreate(userIdCreate);
				gpContractChangeStore.setDateCreate(new Date());
				gpContractChangeStoreMapper.create(gpContractChangeStore);
			}
		}
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
     }
     /**
      * 公盘提交OA审核
      * @param map
      * @return
      */
     public ResultData<String> gpContractStopSubmitOa(Map<String, Object> map) {
         ResultData<String> resultData = new ResultData<>();
         resultData.setReturnMsg("公盘合同终止申请提交成功");
         String errorMsg = "";
         if(map.containsKey("id")) { 
        	 Integer gpContractStopId = Integer.valueOf(map.get("id").toString());
        	 Integer userIdCreate = Integer.valueOf(map.get("userIdCreate").toString());
        	 String userCode = map.get("userCode").toString();
        	 String userName = map.get("userName").toString();
        	 String cityNo = map.get("cityNo").toString();
        	 GpContractChangeDto gpContractChange= new GpContractChangeDto();
        	 gpContractChange.setId(gpContractStopId);
        	 //根据id查询其终止
        	 GpContractChangeDto gpContractChangeDto =gpContractChangeMapper.getGpContractStopInfoById(gpContractStopId);
        	 Integer approveState = gpContractChangeDto.getApproveState();
        	 Integer gpContractId = gpContractChangeDto.getGpContractId();
        	 if(approveState == 1 || approveState == 2) {
        		 resultData.setFail("该公盘合同终止申请已提交OA审核，请刷新数据！");
    			 return resultData;
        	 }
        	 List<GpContractChangeStore> gpContractChangeStoreList  = gpContractChangeStoreMapper.getGpContractStopStoreInfoById(gpContractStopId);
        	 if(null != gpContractChangeStoreList && gpContractChangeStoreList.size()>0 ){
        		 FileRecordMain attachmentFile = new FileRecordMain();
        		 attachmentFile.setRefId(gpContractStopId);
        		 attachmentFile.setIsDelete(false);
        		 List<FileRecordMain> fileMdlList1 = null;
				try {
					fileMdlList1 = fileRecordMainMapper.getStopGpContractFileListByStopId(attachmentFile);
				} catch (Exception e) {
				}
        		 if(null != fileMdlList1 && fileMdlList1.size() > 0) {
        			 String oaTemplateCode = "";
        			 String busCodeString = "";
        			 String busNoCity = "";
        			 Map<String, Object> oaMap = new HashMap<String, Object>();
        			//组装表单发起数据
    		        try {
    		            //准备发起OA
            			//公盘业绩城市
            			String gpContractCityNo = gpContractChangeDto.getGpContractCityNo();
                		 // 根据城市编号获取模板
                		Map<String, Object> oaTempLateCodeMap = getOaTempLateCode(gpContractCityNo);
                		
                		if(oaTempLateCodeMap == null) {
                			resultData.setFail();
                 			resultData.setReturnMsg("根据城市编号获取模板失败");
         		            return resultData;
                		}
                		oaTemplateCode = oaTempLateCodeMap.get("oaTemplateCode").toString();
                		busCodeString = oaTempLateCodeMap.get("busCodeString").toString();
                		//改为城市名称
                		busNoCity = SystemParam.getCityNameByCityNo(gpContractCityNo);
                		map.put("gpContractCityNo", gpContractCityNo);
                		map.put("oaTemplateCode", oaTemplateCode);
                		map.put("busNo", busCodeString);
                		map.put("busNoCity", busNoCity);
                		//更新
                		gpContractChange.setSubmitOAStatus(21202);
            			gpContractChange.setSubmitTime(new Date());
            			gpContractChange.setSubmitOAUserId(userIdCreate);
            			gpContractChange.setDateUpt(new Date());
            			gpContractChange.setApproveState(1);
                		this.gpContractChangeMapper.updateStr(gpContractChange);
                		//组装数据
                		setToOaInfo(oaMap, map, oaTemplateCode,oaTemplateCode);
    		        } catch (Exception e1) {
    		            logger.error("gpContractChange", "GpContractChangeService", "setToOaInfo", "", userIdCreate, "", "", e1);
    		            gpContractChange.setApproveState(0);
    		        	gpContractChange.setSubmitOAStatus(21204);//处理失败
    		        	gpContractChange.setDateUpt(new Date());
    		        	gpContractChange.setUserIdUpt(userIdCreate);
    		        	this.gpContractChangeMapper.updateStr(gpContractChange);
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
						logger.error("gpContractChange", "GpContractChangeService", "setToOaInfo", JsonUtil.parseToJson(oaMap), userIdCreate, "", "向OA发起提交审核失败", e);
    					gpContractChange.setApproveState(0);
    					gpContractChange.setSubmitOAStatus(21204);//处理失败
    		        	gpContractChange.setDateUpt(new Date());
    		        	gpContractChange.setUserIdUpt(userIdCreate);
    		        	this.gpContractChangeMapper.updateStr(gpContractChange);
    					resultData.setFail();
    					resultData.setReturnMsg("向OA发起提交审核失败");
    					return resultData;
    				}
    				//更新
    				//gpContractChange.setApproveState(1);
    				gpContractChange.setSubmitOAStatus(21203);
    				gpContractChange.setSubmitOAUserId(userIdCreate);
    				gpContractChange.setFlowId(flowId+"");
    				this.gpContractChangeMapper.updateStr(gpContractChange);
    				//跟新公盘合同门店表的storeState
    				for (GpContractChangeStore gpContractChangeStore : gpContractChangeStoreList) {
           			 //根据门店id跟新公盘合同表中的状态
    					Integer integer  = 1 ;
    					Map<String, Object> paramMap = new HashMap<>();
    					paramMap.put("storeId", gpContractChangeStore.getStoreId());
    					paramMap.put("gpContractId", gpContractId);
    					paramMap.put("storeState", integer);
    					gpContractChangeMapper.updateGpContractStoreByStoreId(paramMap);
           		 	}
    				
        		 }else {
        			 resultData.setFail("请上传协议书附件后再提交Oa!");
        			 return resultData;
        		 }
        	 } else {
        		 resultData.setFail("请选择公盘合同下的门店后再进行申请终止!");
    			 return resultData;
        	 }
        	 
         }else {
        	 resultData.setFail("参数错误");
        	 resultData.setFail("参数错误");
	       	 return resultData;
         }
         
         return resultData;
     }
     //获取模板编号
	private Map<String, Object> getOaTempLateCode(String gpContractCityNo) {
		Map<String, Object> hashMap = new HashMap<>();
		Map<String, Object> reposeMap = citySettingMapper.getCitySettingByCityNo(gpContractCityNo);
		
		String busCodeString="";
		String oaTemplateCode = "";
		if(null != reposeMap) {
			oaTemplateCode = reposeMap.get("gpContractChangeTpl").toString();
			busCodeString=reposeMap.get("busCode").toString();
			hashMap.put("oaTemplateCode", oaTemplateCode);
			hashMap.put("busCodeString", busCodeString);
		}
		return hashMap;
	}
	 /** 
     * 组装表单发起数据
     * @param id
     * @param oaMap 
     * @param reqMap  传入参数 
     * @param templateCode 模板
     * 
     */
    private Map<String, Object> setToOaInfo(Map<String, Object> oaMap,Map<String, Object> reqMap, String oaTemplateCode,String busNo) throws Exception {
	    String userCode = reqMap.get("userCode").toString();
	   	//末班编号
	   	reqMap.put("templateCode", oaTemplateCode);
        //发起者的登录名（登录协同的登录名）
        String senderLoginName = userCode;
        oaMap.put("senderLoginName", senderLoginName);
        //协同的标题
        oaMap.put("subject", "公盘协议终止报批单");
        //表单数据、附件数据
        Map<String, Object> rspMap = setOaAuditData(reqMap);

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
    * @param achievementCancelNo 
     * @param id
     * @return
     */
   private Map<String, Object> setOaAuditData(Map<String, Object> reqMap) throws Exception {
       //返回表单数据、返回附件数据
       Map<String, Object> rspMap = new HashMap<String, Object>();
       String dateHtml = "";
       String cityNo = reqMap.get("cityNo").toString();
       String cityName = reqMap.get("cityName").toString();
       //String oaNo = "FCJY_GPZZ"+DateUtil.fmtDate(new Date(),"yyyyMMddHHmmss");
       ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_GPCTSTOPOA");
       String oaNo = "";
       if (data != null && data.getReturnCode().equals("200")) {
    	   oaNo = data.getReturnData();
    	   System.out.println(oaNo);
       } else {
           new NullPointerException("根据seq_type: TYPE_GPCTSTOPOA 获取联动合同终止申请oa编号为空");
       }
       
       //申请人姓名
       String userName = reqMap.get("userName").toString();
       //申请人工号
       String userCode = reqMap.get("userCode").toString();
       //创建时间
       Date date = new Date();
       //申请日期
       String applicationDate = DateUtil.fmtDate(date, YYYYMMDD_FORMAT);
       //
       Integer id = Integer.valueOf(reqMap.get("id").toString());
       GpContractChangeDto ctDto = gpContractChangeMapper.getGpStopInfoById(id);
       
       //事业部编号
       String busNo = (String)reqMap.get("busNo");
       String bussineArea =busNo;
       //事业部改为城市名称
       String busNoCity = (String)reqMap.get("busNoCity");
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
       String partyB = ctDto.getCompanyName();
       //公盘合作期限
       String cooperationPeriod = DateUtil.fmtDate(ctDto.getDateLifeStart(), YYYYMMDD_FORMAT) + "至" + DateUtil.fmtDate(ctDto.getDateLifeEnd(), YYYYMMDD_FORMAT);
       //公盘编号
       String gpContractNo = ctDto.getGpContractNo();
       //终止类型
       String stopTypeNm = ctDto.getStopTypeNm();
       //公盘终止日期
       String stopDate =  DateUtil.fmtDate(ctDto.getStopDate(), YYYYMMDD_FORMAT);
       //预计退款金额
       BigDecimal ptBackAmount = ctDto.getPtBackAmount();
       //终止具体原因
       String stopReason = ctDto.getStopReason();
       String stopDescribe = ctDto.getStopDescribe();
       String remarks = ctDto.getRemarks();
       
       String storeDetail = "";
       List<GpContractChangeStore> storeList  = gpContractChangeStoreMapper.getGpContractStopStoreInfoById(id); 
       if (null != storeList && !storeList.isEmpty()) {
    	   for (int i = 0; i < storeList.size(); i++) {
    		   GpContractChangeStore gpContractChangeStore = storeList.get(i);
    		   StringBuffer sd = new StringBuffer();
    		   String addressDetail = gpContractChangeStore.getStoreAddress();
               String storeNo = gpContractChangeStore.getStoreNo();
               sd.append(storeNo).append(" ").append(addressDetail);
               if(StringUtil.isEmpty(storeDetail)){
                   storeDetail = sd.toString();
               }else{
                   storeDetail = storeDetail + System.getProperty("line.separator") + sd.toString();
               }
    	   }
       }
       //附件集合
       List<Long> attachList = new ArrayList<Long>();
       //终止合作协议书/单方解除函
       this.getOaAttachment(attachList, ctDto.getId(), 1039, userCode,"终止合作协议书/单方解除函_");
       //保证金收据
       this.getOaAttachment(attachList, ctDto.getId(), 1040, userCode,"保证金收据_");
       //其它照片
       this.getOaAttachment(attachList, ctDto.getId(), 1041, userCode,"其他_");
       //List<String> othersFileList = sendAttachMentToOa(attachList, ctDto.getId(), 1041, userInfo, tempFilePath);

       dateHtml +=
               "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<formExport version=\"2.0\">"
                       + "<summary id=\"-6907058057567780079\" name=\"formmain_4026\"/>" + "<definitions/>" + "<values>"
                       + "<column name=\"经办人\"><value>"
                       + XmlTransferUtil.transfer(userName)
                       + "</value></column>"
                       + "<column name=\"经办人工号\"><value>"
                       + XmlTransferUtil.transfer(userCode)
                       + "</value></column>"
                       + "<column name=\"城市\"><value>"
                       + XmlTransferUtil.transfer(cityName)
                       + "</value></column>"
                       + "<column name=\"城市编码\"><value>"
                       + XmlTransferUtil.transfer(cityNo)
                       + "</value></column>"
                       + "<column name=\"事业部区域\"><value>"
                       + XmlTransferUtil.transfer(busNoCity)
                       + "</value></column>"
                       + "<column name=\"我方单位\"><value>"
                       + XmlTransferUtil.transfer(companyName)
                       + "</value></column>"
                       + "<column name=\"我方全称\"><value>"
                       + XmlTransferUtil.transfer(ourFullName)
                       + "</value></column>"
                       + "<column name=\"中介全称\"><value>"
                       + XmlTransferUtil.transfer(partyB)
                       + "</value></column>"
                       + "<column name=\"统一社会信用代码\"><value>"
                       + XmlTransferUtil.transfer(ctDto.getRegisterId())
                       + "</value></column>"
                       + "<column name=\"公盘合作期限\"><value>"
                       + XmlTransferUtil.transfer(cooperationPeriod)
                       + "</value></column>"
                       + "<column name=\"公盘协议编号\"><value>"
                       + XmlTransferUtil.transfer(gpContractNo)
                       + "</value></column>"
                       + "<column name=\"公盘合同终止类型\"><value>"
                       + stopTypeNm
                       + "</value></column>"
                       + "<column name=\"公盘终止日期\"><value>"
                       + stopDate
                       + "</value></column>"
                       + "<column name=\"预计退款金额\"><value>"
                       + ptBackAmount
                       + "</value></column>"
                       + "<column name=\"终止门店地址\"><value>"
                       + XmlTransferUtil.transfer(storeDetail)
                       + "</value></column>"
                       + "<column name=\"终止具体原因\"><value>"
                       + XmlTransferUtil.transfer(stopReason)
                       + "</value></column>"
                       + "<column name=\"终止方案阐述\"><value>"
                       + XmlTransferUtil.transfer(stopDescribe)
                       + "</value></column>"
                       + "<column name=\"备注\"><value>"
                       + XmlTransferUtil.transfer(remarks)
                       + "</value></column>"
                       + "<column name=\"单方解除函\"><value>"
                       + "-1"
                       + "</value></column>"
                       + "<column name=\"保证金收据\"><value>"
                       + "-1"
                       + "</value></column>"
                       + "<column name=\"其他\"><value>"
                       + "-1"
                       + "</value></column>"
                       + "<column name=\"OA编号\"><value>"
                       + XmlTransferUtil.transfer(oaNo)
                       + "</value></column>"
                       + "<column name=\"CRM公盘合同终止编号\"><value>"
                       + XmlTransferUtil.transfer(ctDto.getGpContractStopNo())
                       + "</value></column>"
                       + "<column name=\"我方单位编码\"><value>"
                       + XmlTransferUtil.transfer(companyNo)
                       + "</value></column>"
                       + "</values>"
                       + "</formExport>";

       //附件数据
       rspMap.put(ATTACH_KEY, attachList.toArray());
       //表单数据
       rspMap.put(XML_DATA_KEY, dateHtml.toString());
       rspMap.put("cityNo",cityNo);
       logger.info("CRM 提交审核 dateHtml=", dateHtml);
       return rspMap;
   }
   private void getOaAttachment(List<Long> attachList, int refId, Integer fileTypeCode, String userCode,String pre) throws Exception {
       // 文件Code
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
       } catch (Exception e) {
           logger.error("gpContractChange","GpContractChangeService","getOaAttachment", "", Integer.valueOf(userCode),"",
                   "上传文件失败！",
                   e);
       }
   }
   public Long toOaAuth(Map<String, Object> reqMap, String oaTemplateCode)
           throws Exception {
       logger.info("CRM 提交审核 toOaAuth reqMap=", reqMap);
       // 取得REST动态客户机实例
       CTPRestClient client = getClient();
       // token认证
       client.authenticate(SystemParam.getWebConfigValue("oaUserName"),
               SystemParam.getWebConfigValue("oaPassword"));
       // 表单post
       String url = "/flow/" + oaTemplateCode;
       Long flowId = client.post(url, reqMap, Long.class);

       return flowId;
   }
   private CTPRestClient getClient() {
       // 指定协议、IP和端口，获取ClientManager
       CTPServiceClientManager clientManager = CTPServiceClientManager
               .getInstance(SystemParam.getWebConfigValue("oaUrl"));
       // 取得REST动态客户机实例
       CTPRestClient client = clientManager.getRestClient();
       return client;
   }
   /** 
    * @Title: updateStr 
    * @Description: 作废
    * @param param
    */
    public ResultData updateStatus(String param)throws Exception {
   	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
   	//queryParam.put("submitOAStatus",21201);
   	Integer resultRow	=	null;
   	Integer resultRow2	=	null;
   	ResultData<Object> resultData = new ResultData<Object>();
		resultData.setFail();
		try {
			resultRow = gpContractChangeMapper.updateStatus(queryParam);
			resultRow2 = gpContractChangeStoreMapper.updateStatus(queryParam);
			System.out.println(resultRow2);
		}catch(Exception e){
			logger.error("[pmlsService invoke gpContractChangeMapper.updateStatus error...]");
			return resultData;
		}
		if(resultRow == null || resultRow.intValue() == 0) {
			return resultData;
		}else {
			
		}
       resultData.setSuccess();
       resultData.setReturnData(resultRow);
       return resultData;
  }

	public int operateChangeCt(Integer id) {
		int count = gpContractChangeMapper.insertGpContractChangeReturn(id);
		return count;
	}
}
