package cn.com.eju.deal.open.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.open.DeopositStore;
import cn.com.eju.deal.dto.open.DepositRefundDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.open.dao.OmsPerformMapper;
import cn.com.eju.deal.open.model.OmsContractDto;
import cn.com.eju.deal.open.model.OmsStoreDto;
import cn.com.eju.deal.open.model.StorePerformDto;
import cn.com.eju.deal.store.dao.StoreDecorationMapper;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;

/**   
* 提供给OMS系统的API APIOmsService
* @author wenhui.zhang
* @date 2016年8月18日 下午3:52:34
*/
@Service("apiOmsService")
public class APIOmsService extends BaseService<StoreDto> {
    
    @Resource
    private ContractMapper contractMapper;
    
    @Resource
    private ContractStoreMapper contractStoreMapper;
    
    @Resource
    private OmsPerformMapper omsPerformMapper;
    
    @Resource
    private UserAPIImpl userAPIImpl;
    
  /*  @Resource
    private ContractRelatePersonMapper contractRelatePersonMapper;*/
    
    @Resource
    private StoreDecorationMapper storeDecorationMapper;
    
    /** 
     * 查询-根据合同No查询合同下面的门店
     * @param contractNo
     * @return 门店列表
     */
    public List<StoreDto> getStoreInfoByContractNo(Map<String, Object> queryParam) throws Exception
    {
    	List<StoreDto> storeDtoList = contractMapper.getStoreInfoByContractNo(queryParam);
    	return storeDtoList;
    }
    
    /**
     * 更新OMS收取的保证金 到 CRM合同到账金额
     */
    public Integer updateArvDepositFee(String contractNo, BigDecimal arrivalDepositFee) throws Exception
    {
    	// 先根据contractNo查询 总应收保证金和实际到账保证金
    	Contract dbContract = contractMapper.getContractByNo(contractNo);
    	BigDecimal dbTotleDepositFee = dbContract.getTotleDepositFee();
    	BigDecimal dbArrivalDepositFee = dbContract.getArrivalDepositFee();
    	BigDecimal dbTotalRefundAmount = dbContract.getTotalRefundAmount();
    	Contract contract = new Contract();
    	// 一次性全额到账,分多次到账，最后一次到账后自动分账
    	if((arrivalDepositFee.add(dbArrivalDepositFee)).compareTo(dbTotleDepositFee) == 0){

    		    // 根据contractId 更新 关系表
                ContractStore contractStore = new ContractStore();
                contractStore.setContractId(dbContract.getId());
                //如果已经未进行保证金退款，更新全部到账
                if(dbTotalRefundAmount.compareTo(BigDecimal.ZERO) <= 0){
                    // 是否到账:1.已到账
                    contractStore.setIsArrivalAct(1);
                    // 到账日期：当前系统时间
                    contractStore.setDateArrivalAct(new Date());
                    contractStoreMapper.update(contractStore);
                }
                contract.setDepositFeeState(17503);//到账保证金状态:完成(全部到账)  
    		
    	    
    	//应收保证金大于0    已收保证金等于0
    	}else if(dbTotleDepositFee.compareTo(BigDecimal.ZERO)>0 
    			&& (arrivalDepositFee.add(dbArrivalDepositFee)).compareTo(BigDecimal.ZERO)==0){
    		contract.setDepositFeeState(17501);//到账保证金状态:未收款  
    	}else if((arrivalDepositFee.add(dbArrivalDepositFee)).compareTo(dbTotleDepositFee) < 0){
    		contract.setDepositFeeState(17502);//到账保证金状态:部分收款
    	}
    	// 更新合同主表的到账保证金
		contract.setContractNo(contractNo);
		contract.setArrivalDepositFee(arrivalDepositFee);
    	return contractMapper.updateArvDepositFee(contract);
    }
    
    /** 
    * 获取业绩查看详情信息
    * @param contractNo 合同No
    * @param storeNo 门店No
    * @return
    * @throws Exception
    */
    public StorePerformDto getDetailInfo(String contractNo, String storeNo)
            throws Exception
	{
    	StorePerformDto storePerformDto = new StorePerformDto();
    	
    	// 1.获取合同信息
    	OmsContractDto omsContractDto = omsPerformMapper.getContractByNo(contractNo);
		storePerformDto.setOmsContractDto(omsContractDto);
		
		// 2.获取门店信息
		OmsStoreDto omsStoreDto = omsPerformMapper.getStoreByNo(storeNo);
		// 所属行政区
		omsStoreDto.setDistrictName(SystemParam.getDistrictNameByDistrictNo(omsStoreDto.getDistrictNo()));
        storePerformDto.setOmsStoreDto(omsStoreDto);
        
        // 3.获取业绩关联人员信息
       /* ContractRelatePerson relatePerson =  contractRelatePersonMapper.getByNo(contractNo);
        storePerformDto.setRelatePerson(relatePerson);*/
        
        return storePerformDto;
	}
    
    /**
     * 
        * @Title: updateDecorationRecord
        * @Description: OMS调用更新门店装修表
        * @return
        * @throws Exception
     */
    public ResultData<String> updateDecorationRecord(Map<String, Object> reqMap) throws Exception
    {
        ResultData<String> resultData = new ResultData<String>();
        int count = storeDecorationMapper.updateDecorationRecord(reqMap);
        if(count <= 0){
            resultData.setFail();
            resultData.setReturnMsg("OMS更新装修进度出错！");
        }
        return resultData;
        
    }
    /**
     * OMS翻牌申请调用接口，获取申请数据返回
    * @param reqMap
    * @return
     * @throws Exception 
     */
    public ResultData<Contract> returnDataToDecApplication(Map<String, Object> reqMap) throws Exception
    {
        ResultData<Contract> resultData = new ResultData<Contract>();
        
        Contract contract = new Contract();
        contract = contractMapper.returnDataToDecApplication(reqMap);      
        if(contract.getContractNo().equals(null) || contract.getContractNo().equals("")){
            resultData.setFail();
        }
        resultData.setReturnData(contract);
        return resultData;
    } 
    
    /**
     * @Title: updateStoreDecorationPayStatus
     * @Description: OMS调用更新门店装修 装修付款状态
     * @return
     * @throws Exception
     */
    public ResultData<String> updateStoreDecorationPayStatus(Map<String, Object> reqMap) throws Exception
    {
        ResultData<String> resultData = new ResultData<String>();
        int count = storeDecorationMapper.updateStoreDecorationPayStatus(reqMap);
        if(count <= 0){
            resultData.setFail();
            resultData.setReturnMsg("OMS更新装修付款状态出错！");
        }
        return resultData;
        
    }
    
    /**
     * 
        * @Title: updateDecorationType
        * @Description: OMS调用更新门店装修类型
        * @return
        * @throws Exception
     */
    public ResultData<String> updateDecorationType(Map<String, Object> reqMap) throws Exception
    {
        ResultData<String> resultData = new ResultData<String>();
        int count = storeDecorationMapper.updateDecorationType(reqMap);
        if(count <= 0){
            resultData.setFail();
            resultData.setReturnMsg("OMS更新装修类型出错！");
        }
        return resultData;
        
    }

    /**
     * 
    * 获取保证金退款编辑所需详情信息
    * @param contractNo
    * @return
     * @throws Exception 
     */
    public ResultData<DepositRefundDto> getDepositRefundDetail(String contractNo) throws Exception
    {
        ResultData<DepositRefundDto> resultData = new ResultData<>();
        DepositRefundDto depositRefundDto = new DepositRefundDto();
        
        List<DeopositStore> list = new ArrayList<>();
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("contractNo", contractNo);
        //门店信息
        list = contractMapper.getDepositRefundDetail(reqMap);
        if(null != list && list.size()>0 ){
            depositRefundDto.setRestSplitDepositFee(list.get(0).getRestSplitDepositFee());
            for(DeopositStore deopositStore : list){
                deopositStore.setRefundStateName(SystemParam.getDicValueByDicCode(String.valueOf(deopositStore.getRefundState())));
            }
        }
        //剩余未分账
        Map<String,BigDecimal> map =  contractMapper.getRestSplitDepositFee(reqMap);
        
        depositRefundDto.setRestSplitDepositFee(map.get("restSplitDepositFee"));
        depositRefundDto.setDepositList(list);
        resultData.setReturnData(depositRefundDto);
        return resultData;
    }

    /**
     * 
    * 获取保证金退款-校验金额
    * @param contractNo
    * @return
     */
    public ResultData<String> getDepositRefundAmount(String contractNo) throws Exception
    {
        
        ResultData<String> data = new ResultData<>();
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("contractNo", contractNo);
        //剩余未分账
        Map<String,BigDecimal> map =  contractMapper.getRestSplitDepositFee(reqMap);
        if(!map.isEmpty()){
            data.setReturnData(String.valueOf(map.get("restSplitDepositFee")));
        }else{
            data.setFail();
        }
        return data;
    }

    /**
     * 
    * OMS退款后-更新金额、状态到本地
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<String> saveDepositRefundState(List<?> queryParam) throws Exception
    {
        ResultData<String> data = new ResultData<>();
        
        //参数分析
        for (int i=0;i<queryParam.size();i++)
        {
            Map<String, Object> map = (Map)queryParam.get(i);
            
            //查询当前合同中 已到账 和 已撤销总额
            ContractDto con = contractMapper.getDepositInfoByNo(map);
            
            //退款类型，0-未分账金额，1-门店保证金
            String type = (String)map.get("type");
            
            //如果是未分账--17301。注：17301，17302是OMS字典表中值
            if(type.equals("17301")){
                Map<String, Object> mop = new HashMap<String, Object>();
                
                mop.put("contractNo", map.get("contractNo"));
                
                //更新最退款金额:原合同退款金额+新退款金额
                BigDecimal toltal = con.getTotalRefundAmount().add((new BigDecimal(String.valueOf(map.get("refundAmount"))).setScale(2,BigDecimal.ROUND_HALF_DOWN)));
                mop.put("totalRefundAmount", toltal);
                
                //根据已到帐金额 和 已退款金额 比较，更新退款状态--17801:未退款，17802：部分退款，17803：已退款
                if(toltal.equals(BigDecimal.ZERO)){
                    //如果是0，则未退款---用于走流程
                    mop.put("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_ISNOTREFUND);
                }else if(con.getArrivalDepositFee().setScale(2,BigDecimal.ROUND_HALF_DOWN).equals(toltal.setScale(2,BigDecimal.ROUND_HALF_DOWN))){
                    //金额相等，则已退款
                    mop.put("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_ISREFUNDED);
                }else{
                    //金额不相等也不为0，则是部分退款
                    mop.put("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_PARTREFUND);
                }
                //更新合同
                int n = contractMapper.updateRefundInfo(mop);
            }
            
            //如果是门店保证金--17302.注：17301，17302是OMS字典表中值
            if(type.equals("17302")){
                //保存到合同，contractNo,storeNo,refundAmount,refundDate,refundState
                Map<String, Object> mop = new HashMap<String, Object>();
                mop.put("contractNo", map.get("contractNo"));
                mop.put("storeNo", map.get("storeNo"));
                
                //更新最退款金额:原合同退款金额+新退款金额
                BigDecimal toltal = con.getTotalRefundAmount().add((new BigDecimal(String.valueOf(map.get("refundAmount"))).setScale(2,BigDecimal.ROUND_HALF_DOWN)));
                mop.put("totalRefundAmount", toltal);
                
                //根据已到帐金额 和 已退款金额 比较，更新退款状态--17801:未退款，17802：部分退款，17803：已退款
                if(toltal.equals(BigDecimal.ZERO)){
                    //如果是0，则未退款---用于走流程
                    mop.put("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_ISNOTREFUND);
                }else if(con.getArrivalDepositFee().setScale(2,BigDecimal.ROUND_HALF_DOWN).equals(toltal.setScale(2,BigDecimal.ROUND_HALF_DOWN))){
                    //金额相等，则已退款
                    mop.put("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_ISREFUNDED);
                }else{
                    //金额不相等也不为0，则是部分退款
                    mop.put("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_PARTREFUND);
                }
                //更新合同
                int c = contractMapper.updateRefundInfo(mop);
                
                BigDecimal refundAmount = con.getRefundAmount().add((new BigDecimal(String.valueOf(map.get("refundAmount"))).setScale(2,BigDecimal.ROUND_HALF_DOWN)));
                mop.put("refundAmount", refundAmount);
                mop.put("refundDate", (String)map.get("refundDate"));
                
                //根据已到帐金额 和 已退款金额 比较，更新退款状态--17801:未退款，17802：部分退款，17803：已退款
                if(refundAmount.equals(BigDecimal.ZERO)){
                    //如果是0，则未退款---用于走流程
                    mop.replace("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_ISNOTREFUND);
                }else if(con.getDepositFee().setScale(2,BigDecimal.ROUND_HALF_DOWN).equals(refundAmount.setScale(2,BigDecimal.ROUND_HALF_DOWN))){
                    //金额相等，则已退款
                    mop.replace("refundState", DictionaryConstants.STORESTATE_REFUNDSTATE_ISREFUNDED);
                }else{
                    //金额不相等也不为0，则是部分退款
                    mop.replace("refundState",DictionaryConstants.STORESTATE_REFUNDSTATE_PARTREFUND);
                }
                //更新合同门店关系表
                int cs = contractStoreMapper.updateRefundInfo(mop);
            }
        }
        return data;
    }
}
