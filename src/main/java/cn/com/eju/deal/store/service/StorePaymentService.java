package cn.com.eju.deal.store.service;


import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.contract.ContractStoreDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.dto.store.StorePaymentDto;
import cn.com.eju.deal.dto.store.StoreReceiveBackInfoDto;
import cn.com.eju.deal.store.dao.StorePaymentDtlMapper;
import cn.com.eju.deal.store.dao.StorePaymentMapper;
import cn.com.eju.deal.store.model.StorePayment;
import cn.com.eju.deal.store.model.StorePaymentDtl;

@Service("storePaymentService")
public class StorePaymentService {
	
	/**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
	 @Resource
	 private StorePaymentMapper storePaymentMapper;
	 @Resource
	 private StorePaymentDtlMapper storePaymentDtlMapper;
	 
	 @Resource
	 private FileRecordMainMapper fileRecordMainMapper;
	 
	 @Resource
	 private CitySettingMapper citySettingMapper;
	 
	 public ResultData getStorePaymentList(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        
        List<StorePaymentDto> storePaymentList =  storePaymentMapper.getStorePaymentList(reqMap);
        if(storePaymentList !=null && storePaymentList.size()>0){
        	
        	 resultData.setReturnData(storePaymentList);
	         resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
	         resultData.setSuccess();
        }
        return resultData;
    }
	 /**
	  * 保证金信息详情表
	  * @param id
	  * @return
	  * @throws Exception
	  */
    public ResultData<StoreReceiveBackInfoDto> getBriefById(int id) throws Exception {
        //构建返回
    	ResultData resultData = new ResultData();
        resultData.setFail();
        StoreReceiveBackInfoDto storeReceiveBackInfoDto = new StoreReceiveBackInfoDto();
        //获取保证金收款信息
        StorePaymentDto storePayment =storePaymentMapper.getBriefById(id);
        List<StoreDto> storeDtoList = storePaymentMapper.getStoreInfoById(id);
        if(storePayment != null && storeDtoList != null){
        	storeReceiveBackInfoDto.setStorePayment(storePayment);
        	storeReceiveBackInfoDto.setStoreDetails(storeDtoList);
        }
    	// 获取文件信息
        String fileRecordMainIds = "";
        // 附件
        List<ContractFileDto> attachmentFileList = new ArrayList<ContractFileDto>();
		FileRecordMain attachmentFile = new FileRecordMain();
		attachmentFile.setRefId(id);
		attachmentFile.setIsDelete(false);
		List<FileRecordMain> fileMdlList = fileRecordMainMapper.getAttachmentFileByPaymentId(attachmentFile);
		fileRecordMainIds = pushFileRecord(fileMdlList, fileRecordMainIds, attachmentFileList);
		if (StringUtil.isNotEmpty(fileRecordMainIds) && fileRecordMainIds.length() > 0){
			fileRecordMainIds = fileRecordMainIds.substring(0, fileRecordMainIds.length() - 1);
		}
		storeReceiveBackInfoDto.setFileRecordMainAttachment(attachmentFileList);
		storeReceiveBackInfoDto.setFileRecordMainIds(fileRecordMainIds);
        if(storeReceiveBackInfoDto != null){
        	resultData.setReturnData(storeReceiveBackInfoDto);
        	resultData.setSuccess();
        }
		return resultData;
    } 
    /**
     * 查询信息
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<ContractInfoDto> getContractInfoById(int id) throws Exception {
    	//构建返回
    	ResultData resultData = new ResultData();
    	resultData.setFail();
    	ContractInfoDto contractInfoDto = new ContractInfoDto();
    	List<StoreDto> storeDtoList = storePaymentMapper.getStoreInfoByContractId(id);
    	List<StoreDto> storeMdlList = storePaymentMapper.getLockingStore(id);
    	ContractDto contractDto = storePaymentMapper.getContractInfoById(id);
    	
    	if(storeDtoList !=null && contractDto !=null){
    		
    		 for(StoreDto mainDto : storeDtoList){
                 mainDto.setDisabledFlag("1");
                 BigDecimal totalAmount = mainDto.getTotalAmount();
                 if(null == totalAmount || totalAmount.compareTo(new BigDecimal(0.00)) == 0){
                	 mainDto.setDisabledFlag("2");
                 }
                 for(StoreDto dto : storeMdlList){
                     if(mainDto.getStoreId().equals(dto.getStoreId())){
                         mainDto.setDisabledFlag("2");
                     }
                 }
             }
    		contractInfoDto.setStoreDetails(storeDtoList);
    		contractInfoDto.setContract(contractDto);
    	}
    	if(contractInfoDto != null){
    		resultData.setReturnData(contractInfoDto);
    		resultData.setSuccess();
    	}
    	return resultData;
    } 
    /**
     * 根据城市编码查询其退款tkTypeCode
     * @param id
     * @return
     * @throws Exception
     */
    public ResultData<?> getBasCitySettingByCityNo(String cityNo) throws Exception {
    	//构建返回
    	ResultData resultData = new ResultData();
    	resultData.setFail();
    	Map<String, Object> citySetting = citySettingMapper.getBasCitySettingByCityNo(cityNo);
    	if(citySetting != null && citySetting.size() > 0){
    		String tkTypeCode = citySetting.get("tkTypeCode").toString();
    		resultData.setReturnData(tkTypeCode);
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
     * @Title: updateStr 
     * @Description: 删除退款
     * @param param
     */
     public ResultData updateStr(String param)throws Exception {
    	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    	Integer resultRow	=	null;
    	ResultData<Object> resultData = new ResultData<Object>();
 		resultData.setFail();
 		try {
 			resultRow = storePaymentMapper.updateStr(queryParam);
 		}catch(Exception e){
 			logger.error("[PMLSService invoke storePaymentMapper.updateStr error...]");
 			return resultData;
 		}
 		if(resultRow == null || resultRow.intValue() == 0) {
 			return resultData;
 		}
 		resultData.setSuccess();
 		resultData.setReturnData(resultRow);
 		return resultData;
     }
     /**
      * 查询新增保证金合同列表
      * @param param
      * @return
      * @throws Exception
      */
     public ResultData<List<ContractDto>> getPaymentContractList(Map<?, ?> param) throws Exception {
     	ResultData<List<ContractDto>> resultData = new ResultData<List<ContractDto>>();
     	List<ContractDto> contractList = storePaymentMapper.getPaymentContractList(param);
     	resultData.setReturnData(contractList);
     	resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
     	return resultData;
     }
    /**
     * 新增保证金退款
     * @throws ParseException 
     */
    public int create(Map<String, Object> map) throws ParseException{
    	
    	//主表 总金额  orderType 单号21405   单号paymentNo 
    	//contractNo paymentType退款方式  totalAmount
    	//21601 approveStatus   21201 submitOaStatus
    	//status 0  confirmTime null  flowId null dateCreate  backDate delFlag
    	StorePayment storePayment = new StorePayment();
    	String storeIds = (String) map.get("storeIdArray");
    	String refundStateFlag = (String) map.get("refundStateFlag");
    	String userIdCreate = map.get("userIdCreate").toString();
    	String totalAmountSum = map.get("totalAmountSum").toString();
    	String contractNo = map.get("contractNo").toString();
    	String contractId = map.get("contractId").toString();
    	DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String backDate = (String)map.get("backDate");
        String paymentType = map.get("paymentType").toString();
        String paymentNo = map.get("paymentNo").toString();
        String cityNo = map.get("contractCityNo").toString();
        String fileRecordMainIds = map.get("fileRecordMainIds").toString();
        storePayment.setOrderType(21405);
        storePayment.setCityNo(cityNo);
        storePayment.setPaymentNo(paymentNo);
        storePayment.setContractNo(contractNo);
        storePayment.setPaymentType(Integer.valueOf(paymentType));
        storePayment.setTotalAmount(new BigDecimal(totalAmountSum));
        storePayment.setApproveStatus(21603);
        storePayment.setSubmitOaStatus(21203);
        storePayment.setStatus(0);
        storePayment.setUserIdCreate(Integer.valueOf(userIdCreate));
        storePayment.setDelFlag("0");
        storePayment.setDateCreate(new Date());
        if (StringUtil.isNotEmpty(backDate)){
        	storePayment.setRefundDate(format.parse(backDate));
         }
    	//根据登录人的城市编号查询其核算主体
  		List<StorePayment> accountProjectList = storePaymentMapper.queryAccountProject(cityNo);
  		if(accountProjectList.size() > 0 && null != accountProjectList){
  			String accountProject = accountProjectList.get(0).getAccountProject();
  			String accountProjectCode = accountProjectList.get(0).getAccountProjectCode();
  			//合同新增时候保存其核算主体及其编号，核算主体及其编号
  			if(accountProject != ""){
  				storePayment.setAccountProject(accountProject);
  			}
  			if (accountProjectCode != "") {
  				storePayment.setAccountProjectCode(accountProjectCode);
  			}
  		}
        //主表插入数据
        int count = storePaymentMapper.create(storePayment);
        //返回id
        Integer paymentId = storePayment.getId();
        //详情表插入数据//门店编号，门店id，门店的保证金
    	//paymentId storeNo amount
        //int totalRefundAmount = 0;
    	if (storeIds != null && StringUtil.isNotEmpty(storeIds)) {
    		String[] arrays = storeIds.split(",");
    		for (int i = 0; i < arrays.length; i++) {
    			int storeId = Integer.valueOf(arrays[i]);
    			StorePaymentDtl storePaymentDtl = new StorePaymentDtl();
    			//应退金额（已收减已退）
    			String refundableAmount = map.get("totalAmount" +storeId).toString();
    			//本次退款金额
    			String amount = map.get("amount" +storeId).toString();
    			String storeNo = map.get("storeNo" + storeId).toString();
    			String remark = map.get("remark" + storeId).toString();
    			String storeName = map.get("storeName" + storeId).toString();
    			storePaymentDtl.setAmount(new BigDecimal(amount));
    			storePaymentDtl.setStoreNo(storeNo);
    			storePaymentDtl.setStoreName(storeName);
    			storePaymentDtl.setRemark(remark);
    			storePaymentDtl.setDateCreate(new Date());
    			storePaymentDtl.setDelFlag("0");
    			storePaymentDtl.setPaymentId(paymentId);
    			storePaymentDtl.setUserIdCreate(Integer.valueOf(userIdCreate));
    			int count2 = storePaymentDtlMapper.create(storePaymentDtl);
    			//更新门店保证金的退款金额表
    			Map<String, Object> hashMap = new HashMap<String, Object>();
    			hashMap.put("storeNo", storeNo);
    			hashMap.put("paymentAmount", amount);
    			hashMap.put("userIdUpt",userIdCreate);
    			storePaymentDtlMapper.updateByStoreNo(hashMap);
    			
    			//更新门店合同关系表中退款保证金及状态
    			BigDecimal refundamount = storePaymentDtl.getAmount()==null?BigDecimal.ZERO:storePaymentDtl.getAmount();
    			if (refundamount.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(new BigDecimal(refundableAmount)) ==0) {
					//该门店全部退还
    				hashMap.put("refundState", 17803);
				}else if(refundamount.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(new BigDecimal(refundableAmount)) <0) {
					//该门店未退完
					hashMap.put("refundState", 17802);
				}
    			hashMap.put("refundAmount", amount);
    			hashMap.put("contractId", contractId);
    			hashMap.put("storeId", storeId);
    			if (StringUtil.isNotEmpty(backDate)){
    	        	hashMap.put("refundDate", format.parse(backDate));
    	         }
    			storePaymentDtlMapper.updateContractStoreByStoreId(hashMap);
    			
    			//更新合同表中退款中保证金及状态
    			hashMap.remove("refundState");
    			int flag = 1;
    			List<ContractStoreDto> contractStoreDtoList = storePaymentDtlMapper.getRefundStateByContractId(Integer.valueOf(contractId));
				for (ContractStoreDto contractStoreDto : contractStoreDtoList) {
					if(StringUtils.isEmpty(contractStoreDto.getRefundState()) || "17802".equals(contractStoreDto.getRefundState()) || "17801".equals(contractStoreDto.getRefundState())){
						flag = 0;
					}
				}
    			if(flag == 1){
    				//合同下的门店保证金全退
    				hashMap.put("refundState", 17803);
    			}else if(flag == 0){
    				//合同下的门店保证金未全退
    				hashMap.put("refundState", 17802);
    			}
    			hashMap.put("totalRefundAmount", amount);
    			storePaymentDtlMapper.updateContractByStoreId(hashMap);
    		}
    	}
    	 if (fileRecordMainIds != null && StringUtil.isNotEmpty(fileRecordMainIds)) {
             String[] arrays = fileRecordMainIds.split(",");
             // 关联相关文件(RefId)
             for (int i = 0; i < arrays.length; i++){
                 if (StringUtil.isNotEmpty(arrays[i])){
                     FileRecordMain fileRecordMain = new FileRecordMain();
                     fileRecordMain.setFileRecordMainId(Integer.valueOf(arrays[i]));
                     fileRecordMain.setRefId(paymentId);
                     fileRecordMain.setIsDelete(false);
                     fileRecordMainMapper.update(fileRecordMain);
                 }
             }
    	 }
 		return count;
 	}
}
  