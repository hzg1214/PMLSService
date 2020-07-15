package cn.com.eju.deal.contract.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.fesb.FesbService;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.service.OmsInteractiveService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.contract.model.ContractStore;
import cn.com.eju.deal.dto.contract.DepositDto;
import cn.com.eju.deal.dto.contract.PerformNodeRecordDto;
import cn.com.eju.deal.dto.store.DecorationDto;
import cn.com.eju.deal.open.model.ContractFlowDto;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;
/**
 * CRM调用OMS类
 * @author  wushuang
 * @date 2016年9月20日 下午4:15:11
 */
@Service("extOmsService")
public class ExtOmsService extends BaseService<Contract> {
	@Resource
    private ContractMapper contractMapper;
	@Resource
    private ContractStoreMapper contractStoreMapper;
	@Resource
    private StoreMapper storeMapper;
	
	@Resource(name = "fesbService")
    private FesbService fesbService;
	
	@Resource
	private OmsInteractiveService omsInteractiveService;
	
	/**
	 * 新增业绩节点记录
	 * @param flowId
	 * @throws Exception
	 */
	public void createPerformNodeRecordByFlowId (String flowId)  throws Exception {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:sss"); 
        Contract contract = new Contract();
        // 根据flowId获取合同信息
        contract = contractMapper.getByFlowId(flowId);
        // 合同是非C版的场合
        if (contract.getContractType() != DictionaryConstants.CONTRACT_TYPE_C) {
            // 获取门店信息
            List<ContractStore> storeList = contractStoreMapper.selStoreByContractId(contract.getId());
            if (null != storeList && !storeList.isEmpty()) {
                for (ContractStore contractStore : storeList) {
                    // 根据门店ID查询门店信息
                    Store store = storeMapper.getById(contractStore.getStoreId());
                    //组装dto
                    PerformNodeRecordDto nodeRecordDto=new PerformNodeRecordDto();
                    nodeRecordDto.setContractNo(contract.getContractNo());//合同编号
                    nodeRecordDto.setCompanyName(contract.getPartyB());//门店编号
                    nodeRecordDto.setContractState(SystemParam.getDicValueByDicCode(String.valueOf(contract.getContractStatus())));//合同状态
                    nodeRecordDto.setContractType(SystemParam.getDicValueByDicCode(String.valueOf(contract.getContractType())));//合同类型
                    nodeRecordDto.setStoreNo(store.getStoreNo());//门店编号
                    nodeRecordDto.setStoreName(store.getName());//门店店招
                    nodeRecordDto.setStoreAddressDetail(store.getAddressDetail());//门店店招
                    nodeRecordDto.setOaApprovalDateStr(sdf.format(new Date()));//OA审批通过时间
                    nodeRecordDto.setCityNo(store.getCityNo());//城市No
                    omsInteractiveService.createPerformNodeRecord(nodeRecordDto);
                }
            }
        }
      //dubbo end

	}
	/**
	 * 根据(合同ID门店ID)修改门店的保证金 到账日期
	 * @param contractStore
	 * @throws Exception
	 */
	public void updatePerformNodeRecord(Integer contractId,Integer storeId)  throws Exception {
	    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:sss"); 
        //给OMS的参数 
        PerformNodeRecordDto nodeRecordDto=new PerformNodeRecordDto();
        //  组装dto
        Contract contract=contractMapper.getContractById(contractId);
        Store store=storeMapper.getById(storeId);
        nodeRecordDto.setContractNo(contract.getContractNo());
        nodeRecordDto.setStoreNo(store.getStoreNo());
        nodeRecordDto.setDepositArrivalDateStr(sdf.format(new Date()));
        omsInteractiveService.updatePerformNodeRecord(nodeRecordDto);
	}
	/**
	 * 新增合同变更记录
	 * @param listdto
	 * @throws Exception
	 */
	public void insertContractFlowDto(List<ContractFlowDto> listdto) throws Exception {
	    omsInteractiveService.batchCreateContractFlow(listdto);
	}
	
    /**
	 * 
	    * @Title: updateChgStatusToOmsSplit
	    * @Description: 根据FlowId查询合同信息更新OMS保证金合同状态
	    * @return
	    * @throws Exception
	 */
    public void updateChgStatusToOmsSplit(List<String> passList) throws Exception
    {
        /*String[] flowIdList = passList.toArray(new String[passList.size()]);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("flowIdList", flowIdList);
        
        //1、根据passList查询到合同编号和合同状态
        List<Contract> list = contractMapper.getByFlowIdList(reqMap);
        
        //2、将查询出来的信息传输到OMS
        //2.1 组装传输数据
        List<DepositDto> templist = new ArrayList<>();
        
        for(Contract contract : list){
            if(contract.getContractStatus() == 10406 ){
                DepositDto deposit = new DepositDto();
                deposit.setContractNo(contract.getContractNo());
                deposit.setContractState(SystemParam.getDicValueByDicCode("10406"));
                templist.add(deposit);
            }
        }
        
        //如果合同状态为终止（10406），则更新OMS，否则不更新 
        if(null != templist && !templist.isEmpty()){
            //2.2 传输到OMS
            fesbService.httpPost(templist, FesbMethodConstants.FESB_METHOD_CODE_CONTRACT_DEPOSIT_BATCH);
        }*/
        
        
        //dubbo begin
        String[] flowIdList = passList.toArray(new String[passList.size()]);
        Map<String, Object> reqMap = new HashMap<String, Object>();
        reqMap.put("flowIdList", flowIdList);
        
        //1、根据passList查询到合同编号和合同状态
        List<Contract> list = contractMapper.getByFlowIdList(reqMap);
        
        //2、将查询出来的信息传输到OMS
        //2.1 组装传输数据
        List<DepositDto> templist = new ArrayList<>();
        
        for(Contract contract : list){
            if(contract.getContractStatus() == 10406 ){
                DepositDto deposit = new DepositDto();
                deposit.setContractNo(contract.getContractNo());
                deposit.setContractState(SystemParam.getDicValueByDicCode("10406"));
                templist.add(deposit);
            }
        }
        
        //如果合同状态为终止（10406），则更新OMS，否则不更新 
        if(null != templist && !templist.isEmpty()){
            //2.2 传输到OMS
            omsInteractiveService.batchUpdateContractState(templist);
        }
        //dubbo end
        
    }
    /**
     * 
    *新增门店装修-定时任务
    * @param listdto
    * @throws Exception
     */
    public void insertToOMSDecoration(List<DecorationDto> postList) throws Exception {
        omsInteractiveService.batchCreateDecoration(postList);
    }

}
