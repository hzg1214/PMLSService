/**
 * 
 */
package cn.com.eju.deal.store.service;

import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreDepositDto;
import cn.com.eju.deal.store.dao.*;
import cn.com.eju.deal.store.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yinkun
 *
 */
@Service("storeDepositService")
public class StoreDepositService extends BaseService<StoreDeposit> {
    
    @Resource
    private StoreDepositMapper storeDepositMapper;
    
    @Resource
    private StorePaymentMapper storePaymentMapper;
    
    @Resource
    private StorePaymentDtlMapper storePaymentDtlMapper;
    
    @Resource
    private StoreReceiveMapper storeReceiveMapper;
    
    @Resource
    private StoreReceiveDtlMapper storeReceiveDtlMapper;
    
    @Resource
    private ContractStoreMapper contractStoreMapper;
    
    @Resource
    private ContractMapper contractMapper;
    
    @Resource
    private ISeqNoAPI seqNoAPI;
    
    /**
     * 新签，续签，乙转甲
     * @param deposit storeId,temAmount,userIdCreate【必填】
     * @return
     */
    public int createOrUpdate(StoreDepositDto deposit) {
        StoreDeposit record = new StoreDeposit();
        record.setStoreNo(deposit.getStoreNo());
        StoreDeposit oldRecord = this.findByCondition(record);
        
        int result = 0;
        
        //新增
        if(oldRecord == null) {
            StoreDeposit newRecord = new StoreDeposit();
            newRecord.setStoreNo(deposit.getStoreNo());
            newRecord.setTmpAmount(deposit.getTmpAmount());
            newRecord.setUserIdCreate(deposit.getUserIdCreate());
            
            result = storeDepositMapper.insertSelective(newRecord);
        }
        
        if(oldRecord != null) {
            StoreDeposit uptRecord = new StoreDeposit();
            uptRecord.setId(oldRecord.getId());
            uptRecord.setTmpAmount(deposit.getTmpAmount());
            uptRecord.setUserIdUpt(deposit.getUserIdCreate());
            uptRecord.setDateUpt(new Date());
            
            result = storeDepositMapper.updateByPrimaryKeySelective(uptRecord);
        }
        
        return result;
    }
    
    /**
     * 乙转甲（因合同直接审核通过）storeId,oldContractId,storeNo,oldContractNo,newContractNo
     * 三方变更
     * 新签，续签 合同审核通过时 flowId
     * @param record 
     */
    public void transferDeposit(Map<String,Object> record) throws Exception{
        
        Contract oldContract = null;
        Contract newContract = null;
        
        if(record.containsKey("flowId") && record.get("flowId") != null) {
            //审核通过来源
            newContract = contractMapper.getByFlowId(record.get("flowId").toString());
            
            /*String flagByCityNo = this.getNewDepositOpenFlagByCityNo(newContract.getAcCityNo());
            if(flagByCityNo != null && "0".equals(flagByCityNo)) {
                //未开通新保证金流程，不作转移
                return;
            }*/
            
            List<ContractStore> newCsList = contractStoreMapper.getContractStoresByContractFlowId(record.get("flowId").toString());
            
            if(newContract.getOriginalContractdistinction() != null && newContract.getOriginalContractdistinction().equals(18602)) {
                for(ContractStore newCs : newCsList) {
                    //续签:原合同可直接获取到
                    Map<String,Object> qParam = new HashMap<>();
                    qParam.put("contractNo", newContract.getOriginalContractNo());
                    oldContract = storeDepositMapper.getTopOneContract(qParam);
                    
                    //查询原合同门店表收退款信息
                    Map<String, Object> csParam = new HashMap<>();
                    csParam.put("storeId", newCs.getStoreId());
                    csParam.put("contractId", oldContract.getId());
                    ContractStore dbOldContractStore = contractStoreMapper.getContractStore(csParam);
                    //此门店保证金需要刷数据才有
                    BigDecimal oldDepositFee = dbOldContractStore.getDepositFee()==null?BigDecimal.ZERO:dbOldContractStore.getDepositFee();
                    BigDecimal oldRefundAmount = dbOldContractStore.getRefundAmount()==null?BigDecimal.ZERO:dbOldContractStore.getRefundAmount();
                    BigDecimal realReceiveAmount = oldDepositFee.subtract(oldRefundAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    
                    if(realReceiveAmount.compareTo(BigDecimal.ZERO) <= 0) {
                        continue ;
                    }
                    
                    record.put("storeId", newCs.getStoreId());
                    record.put("oldContractId", oldContract.getId());
                    record.put("storeNo", oldContract.getStoreNo());
                    record.put("storeName", newCs.getName());
                    record.put("oldContractNo", oldContract.getContractNo());
                    record.put("newContractNo", newContract.getContractNo());
                    record.put("accountProject",oldContract.getAccountProject());
                    record.put("accountProjectNo",oldContract.getAccountProjectNo());
                    record.put("acCityNo",oldContract.getAcCityNo());
                    
                    //1.store_payment表  旧合同转出 
                    createPayment(record, realReceiveAmount);
                    
                    //2.store_receive表  新合同转入 
                    createRecieve(record, realReceiveAmount);
                    
                    //3.contractstore,contract  旧合同退款 
                    updateTransfer(record, oldContract, realReceiveAmount);
                    
                    //4.contractstore,contract  新合同收款 
                    updateDeposit(record, newContract, realReceiveAmount);
                }
            }else {
                for(ContractStore newCs : newCsList) {
                    //新签:原合同由新合同所签门店查询到
                    Map<String,Object> qParam = new HashMap<>();
                    qParam.put("storeId", newCs.getStoreId());
                    qParam.put("thisContractNo", newContract.getContractNo());
                    oldContract = storeDepositMapper.getTopOneContract(qParam);
                    if(oldContract == null) {
                        continue;
                    }
                    
                    //查询原合同门店表收退款信息
                    Map<String, Object> csParam = new HashMap<>();
                    csParam.put("storeId", newCs.getStoreId());
                    csParam.put("contractId", oldContract.getId());
                    ContractStore dbOldContractStore = contractStoreMapper.getContractStore(csParam);
                    //此门店保证金需要刷数据才有
                    BigDecimal oldDepositFee = dbOldContractStore.getDepositFee()==null?BigDecimal.ZERO:dbOldContractStore.getDepositFee();
                    BigDecimal oldRefundAmount = dbOldContractStore.getRefundAmount()==null?BigDecimal.ZERO:dbOldContractStore.getRefundAmount();
                    BigDecimal realReceiveAmount = oldDepositFee.subtract(oldRefundAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    
                    if(realReceiveAmount.compareTo(BigDecimal.ZERO) <= 0) {
                        continue ;
                    }
                    
                    record.put("storeId", newCs.getStoreId());
                    record.put("oldContractId", oldContract.getId());
                    record.put("storeNo", oldContract.getStoreNo());
                    record.put("storeName", newCs.getName());
                    record.put("oldContractNo", oldContract.getContractNo());
                    record.put("newContractNo", newContract.getContractNo());
                    record.put("accountProject",oldContract.getAccountProject());
                    record.put("accountProjectNo",oldContract.getAccountProjectNo());
                    record.put("acCityNo",oldContract.getAcCityNo());
                    
                    //1.store_payment表  旧合同转出 
                    createPayment(record, realReceiveAmount);
                    
                    //2.store_receive表  新合同转入 
                    createRecieve(record, realReceiveAmount);
                    
                    //3.contractstore,contract  旧合同退款 
                    updateTransfer(record, oldContract, realReceiveAmount);
                    
                    //4.contractstore,contract  新合同收款 
                    updateDeposit(record, newContract, realReceiveAmount);
                }
            }
            
            for(ContractStore cs : newCsList) {
                //store_deposit表tmpAmount->totalAmount
                record.put("storeNo", cs.getStoreNo());
                updateDepositTotalAmount(record);
            }
        }else {
            
            /*String flagByCityNo = this.getNewDepositOpenFlagByCityNo(record.get("acCityNo").toString());
            if(flagByCityNo != null && "0".equals(flagByCityNo)) {
                //未开通新保证金流程，不作转移
                return;
            }*/
            
            //乙转甲来源
            oldContract = contractMapper.getContractByNo(record.get("oldContractNo").toString());
            newContract = contractMapper.getContractByNo(record.get("newContractNo").toString());

            record.put("accountProject",oldContract.getAccountProject());
            record.put("accountProjectNo",oldContract.getAccountProjectNo());
            record.put("acCityNo",oldContract.getAcCityNo());
            
            //查询原合同门店表收退款信息
            Map<String, Object> csParam = new HashMap<>();
            csParam.put("storeId", record.get("storeId"));
            csParam.put("contractId", record.get("oldContractId"));
            ContractStore dbOldContractStore = contractStoreMapper.getContractStore(csParam);
            //此门店保证金需要刷数据才有
            BigDecimal oldDepositFee = dbOldContractStore.getDepositFee()==null?BigDecimal.ZERO:dbOldContractStore.getDepositFee();
            BigDecimal oldRefundAmount = dbOldContractStore.getRefundAmount()==null?BigDecimal.ZERO:dbOldContractStore.getRefundAmount();
            BigDecimal realReceiveAmount = oldDepositFee.subtract(oldRefundAmount).setScale(2, BigDecimal.ROUND_HALF_DOWN);
            
            //store_deposit表tmpAmount->totalAmount
            updateDepositTotalAmount(record);
            
            if(realReceiveAmount.compareTo(BigDecimal.ZERO) <= 0) {
                return ;
            }
            
            //1.store_payment表转出 旧合同
            createPayment(record, realReceiveAmount);
            
            //2.store_receive表转入 新合同
            createRecieve(record, realReceiveAmount);
            
            //3.contractstore转移,contract退款 旧合同
            updateTransfer(record, oldContract, realReceiveAmount);
            
            //4.contractstore,contract收款 新合同
            updateDeposit(record, newContract, realReceiveAmount);
        }
    }

    private void updateDepositTotalAmount(Map<String, Object> record) {
        StoreDeposit deposit = new StoreDeposit();
        deposit.setStoreNo(record.get("storeNo").toString());
        
        StoreDeposit dbDeposit = storeDepositMapper.findByCondition(deposit);
        if(dbDeposit!=null){
        	dbDeposit.setTotalAmount(dbDeposit.getTmpAmount());
        	dbDeposit.setDateUpt(new Date());
        	storeDepositMapper.updateByPrimaryKeySelective(dbDeposit);
        }
    }

    private void updateDeposit(Map<String, Object> record, Contract newContract, BigDecimal realReceiveAmount)
            throws Exception {
        Map<String,Object> csParam = new HashMap<>();
        csParam.put("storeId", record.get("storeId"));
        csParam.put("contractId", newContract.getId());
        ContractStore dbNewContractStore = contractStoreMapper.getContractStore(csParam);
        
        BigDecimal depositFee = newContract.getDepositFee()==null?BigDecimal.ZERO:newContract.getDepositFee();
        BigDecimal newDepositFee = dbNewContractStore.getDepositFee()==null?BigDecimal.ZERO:dbNewContractStore.getDepositFee();
        ContractStore depositCs = new ContractStore();
        depositCs.setStoreId(Integer.valueOf(record.get("storeId").toString()));
        depositCs.setContractId(newContract.getId());
        BigDecimal uNewDepositFee = newDepositFee.add(realReceiveAmount);
        depositCs.setDepositFee(uNewDepositFee.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        if(depositFee.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(uNewDepositFee.setScale(2, BigDecimal.ROUND_HALF_DOWN)) <= 0) {
            //已到账
            depositCs.setIsArrivalAct(1);
        }else if(depositFee.setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(uNewDepositFee.setScale(2, BigDecimal.ROUND_HALF_DOWN)) > 0) {
            //部分到账
            depositCs.setIsArrivalAct(0);
        }
        depositCs.setDateArrivalAct(new Date());
        
        //退款状态若为已退款，则改为部分退款
        if(dbNewContractStore.getRefundState() != null && dbNewContractStore.getRefundState().equals("17803")) {
            depositCs.setRefundState("17802");
        }

        contractStoreMapper.update(depositCs);
        
        Contract depositC = new Contract();
        //在sql上加上原值
        depositC.setArrivalDepositFee(realReceiveAmount);
        if((newContract.getArrivalDepositFee().add(realReceiveAmount)).setScale(2, BigDecimal.ROUND_HALF_DOWN)
                .compareTo(newContract.getTotleDepositFee().setScale(02, BigDecimal.ROUND_HALF_DOWN)) >= 0) {
            depositC.setDepositFeeState(17503);
        }else if((newContract.getArrivalDepositFee().add(realReceiveAmount)).setScale(2, BigDecimal.ROUND_HALF_DOWN)
                .compareTo(newContract.getTotleDepositFee().setScale(2, BigDecimal.ROUND_HALF_DOWN)) < 0) {
            depositC.setDepositFeeState(17502);
        }
        depositC.setContractNo(record.get("newContractNo").toString());
        
        //退款状态若为已退款，则改为部分退款
        if(newContract.getRefundState() != null && newContract.getRefundState().equals("17803")) {
            depositC.setRefundState("17802");
        }
        
        contractMapper.updateArvDepositFee(depositC);
    }

    private void updateTransfer(Map<String, Object> record, Contract oldContract,BigDecimal realReceiveAmount) throws Exception {
        ContractStore refundCs = new ContractStore();
        refundCs.setStoreId(Integer.valueOf(record.get("storeId").toString()));
        refundCs.setContractId(Integer.valueOf(record.get("oldContractId").toString()));
        refundCs.setTransferAmt(realReceiveAmount.setScale(2, BigDecimal.ROUND_HALF_DOWN));
        contractStoreMapper.update(refundCs);
        
        //contract表转移 旧合同
        Contract uOldContract = new Contract();
        uOldContract.setId(oldContract.getId());
        BigDecimal transferAmt = oldContract.getTransferAmt();
        transferAmt = transferAmt.add(realReceiveAmount);
        uOldContract.setTransferAmt(transferAmt);
        contractMapper.update(uOldContract);
    }

    private void createRecieve(Map<String, Object> record, BigDecimal realReceiveAmount) {
        StoreReceive receive = new StoreReceive();
        //转入
        receive.setOrderType(21403);
        ResultData<String> data1 = seqNoAPI.getSeqNoByTypeCode("TYPE_SK_ZD");
        receive.setReceiveNo(data1.getReturnData());
        receive.setContractNo(record.get("newContractNo").toString());
        //保证金
        receive.setFeeType(17904);
        receive.setTotalAmount(realReceiveAmount);
        //审核通过
        receive.setApproveStatus(21603);
        //账单确认
        receive.setStatus(30);
        receive.setConfirmTime(new Date());
        receive.setUserIdCreate(0);
        receive.setAccountProject(record.get("accountProject").toString());
        receive.setAccountProjectCode(record.get("accountProjectNo").toString());
        receive.setCityNo(record.get("acCityNo").toString());
        storeReceiveMapper.insertSelective(receive);
        
        StoreReceiveDtl receiveDtl = new StoreReceiveDtl();
        receiveDtl.setReceiveId(receive.getId());
        receiveDtl.setStoreNo(record.get("storeNo").toString());
        receiveDtl.setStoreName(record.get("storeName").toString());
        receiveDtl.setAmount(realReceiveAmount);
        receiveDtl.setUserIdCreate(0);
        storeReceiveDtlMapper.insertSelective(receiveDtl);
    }

    private void createPayment(Map<String, Object> record, BigDecimal realReceiveAmount) {
        StorePayment payment = new StorePayment();
        payment.setOrderType(21404);
        ResultData<String> data = seqNoAPI.getSeqNoByTypeCode("TYPE_TK_ZD");
        payment.setPaymentNo(data.getReturnData());
        payment.setContractNo(record.get("oldContractNo").toString());
        payment.setTotalAmount(realReceiveAmount);
        payment.setApproveStatus(21603);
        payment.setStatus(30);
        payment.setCityNo(record.get("acCityNo").toString());
        payment.setConfirmTime(new Date());
        payment.setUserIdCreate(0);
        payment.setAccountProject(record.get("accountProject").toString());
        payment.setAccountProjectCode(record.get("accountProjectNo").toString());
        storePaymentMapper.insertSelective(payment);
        
        StorePaymentDtl paymentDtl = new StorePaymentDtl();
        paymentDtl.setPaymentId(payment.getId());
        paymentDtl.setStoreNo(record.get("storeNo").toString());
        paymentDtl.setStoreName(record.get("storeName").toString());
        paymentDtl.setAmount(realReceiveAmount);
        paymentDtl.setUserIdCreate(0);
        storePaymentDtlMapper.insertSelective(paymentDtl);
    }
    
    public StoreDeposit findByCondition(StoreDeposit record) {
        return storeDepositMapper.findByCondition(record);
    }

    /**
     * 判断是否有审核中的保证金收款或退款记录
     * @param storeNo
     * @return
     * @throws Exception
     */
    public Integer checkStoreDepositLock(String storeNo) throws Exception {
        Integer result = 0;
        StoreDeposit record = new StoreDeposit();
        record.setStoreNo(storeNo);
        StoreDeposit deposit = storeDepositMapper.findByCondition(record);
        if (deposit != null) {
            int pay = deposit.getPaymentLockAmt().compareTo(BigDecimal.ZERO);
            int receive = deposit.getReceiveLockAmt().compareTo(BigDecimal.ZERO);
            if (pay == 1 || receive == 1) {
                result = 1;
            }
        }
        return result;
    }
    
    /**
     * 判断是否有未退款，在途保证金
     * @param querryMap
     * @return
     * @throws Exception
     */
    public Map<String,Object> checkStoreDeposit(Map<String, Object> querryMap) throws Exception {
        Map<String,Object> result = new HashMap<>();
        result.put("flag", 0);
        
        String storeNo = null;
        if(querryMap.get("storeNo") != null) {
            storeNo =  querryMap.get("storeNo").toString();
            Integer flag = checkStoreDepositByStoreNo(storeNo);
            result.put("flag", flag);
            result.put("storeNo", storeNo);
        }else if(querryMap.get("contractId") != null){
            Integer contractId = Integer.valueOf(querryMap.get("contractId").toString());
            List<ContractStore> list = contractStoreMapper.getContractStoreByContractId(contractId);
            for(ContractStore cs : list) {
                storeNo = cs.getStoreNo();
                Integer flag = checkStoreDepositByStoreNo(storeNo);
                if(flag == 1) {
                    result.put("flag", flag);
                    result.put("storeNo", storeNo);
                    break;
                }
            }
        }
        return result;
    }

    public Integer checkStoreDepositByStoreNo(String storeNo) {
        StoreDeposit record = new StoreDeposit();
        record.setStoreNo(storeNo);
        StoreDeposit deposit = storeDepositMapper.findByCondition(record);
        if (deposit != null) {
            
            BigDecimal real = deposit.getReceiveAmount().subtract(deposit.getPaymentAmount()).setScale(2, BigDecimal.ROUND_HALF_DOWN);
            int realFlag = real.compareTo(BigDecimal.ZERO);
            
            int pay = deposit.getPaymentLockAmt().setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(BigDecimal.ZERO);
            int receive = deposit.getReceiveLockAmt().setScale(2, BigDecimal.ROUND_HALF_DOWN).compareTo(BigDecimal.ZERO);
            if (pay == 1 || receive == 1 || realFlag == 1) {
                return 1;
            }
        }
        
        return 0;
    }
    
    public String getNewDepositOpenFlagByCityNo(String cityNo) {
        return storeDepositMapper.getNewDepositOpenFlagByCityNo(cityNo);
    }

    public String getNewDepositOpenFlagByCenterId(Integer centerId) {
        return storeDepositMapper.getNewDepositOpenFlagByCenterId(centerId);
    }
}
