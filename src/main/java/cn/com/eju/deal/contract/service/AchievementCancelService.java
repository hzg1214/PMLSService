package cn.com.eju.deal.contract.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.AchievementCancelMapper;
import cn.com.eju.deal.contract.dao.AchievementCancelMappingMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.contract.model.AchievementCancelMapping;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.MapToEntityConvertUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.contract.AchievementCancelDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.dao.StoreMapper;
/**
 * 
* 门店撤销service
* @author wushuang
* @date 2016年10月12日 上午11:15:01
 */

@Service("achievementCancelService")
public class AchievementCancelService
{
    @Resource
    private AchievementCancelMapper achievementCancelMapper;
    
    @Resource
    private AchievementCancelMappingMapper achievementCancelMappingMapper;
    
    @Resource
    private ContractStoreMapper contractStoreMapper;
    
    @Resource
    private ContractMapper contractMapper;
    
    @Resource
    private StoreMapper storeMapper;
    /**
     * 
    * 门店撤销查询门店数据
    * @param contractId
    * @return
     * @throws Exception 
     */
    public ResultData<List<AchievementCancelDto>> getAchievementCancelInfo(Integer contractId) throws Exception
    {
        ResultData<List<AchievementCancelDto>> resultData = new ResultData<List<AchievementCancelDto>>();
        
        List<AchievementCancelDto> list = achievementCancelMapper.getAchievementCancelInfo(contractId);
        if(list.isEmpty()){    
            resultData.setFail();
            resultData.setReturnMsg("查询撤销门店列表失败！");
        }
        resultData.setReturnData(list);
            
        return resultData;
    }
    
    /**
     * 
    * 获取门店业绩撤销记录
    * @param contractId
    * @return
     * @throws Exception 
     */
    public ResultData<List<AchievementCancelDto>> getAchievementCancelRecord(Integer contractId) throws Exception
    {
        ResultData<List<AchievementCancelDto>> resultData = new ResultData<List<AchievementCancelDto>>();
        
        List<AchievementCancelDto> list = achievementCancelMapper.getAchievementCancelRecord(contractId);

        resultData.setReturnData(list);
            
        return resultData;
    }
    
    /**
     * 
    * 根据合同ID和门店撤销编号获取-查看-所需详细信息
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<AchievementCancelDto> getInfoToView(Map<String, Object> queryParam) throws Exception
    {
        ResultData<AchievementCancelDto> resultData = new ResultData<AchievementCancelDto>();
        
        //获取门店撤销申请撤销所需的[页面]信息【OA专用】
        AchievementCancelDto achievementCancelDto = achievementCancelMapper.getInfoToView(queryParam);
        if(achievementCancelDto.getAchievementCancelNo().isEmpty()){
            resultData.setFail("查询撤销详情失败！");
            return resultData;
        }        
        
        //获取门店撤销申请撤销所需的[门店]信息【OA专用】
        List<StoreDto> storelist = achievementCancelMapper.getStoreInfoToView(queryParam);
        if(storelist.isEmpty()){
            resultData.setFail("查询撤销详情失败！");
            return resultData;
        }
        
        achievementCancelDto.setStorelist(storelist);
        resultData.setReturnData(achievementCancelDto);
        return resultData;
    }

    /**
     * 
    * 获取门店撤销申请撤销所需的信息【OA专用】
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<AchievementCancelDto> getOAAuditInfo(Map<String, Object> queryParam) throws Exception
    {
        ResultData<AchievementCancelDto> resultData = new ResultData<AchievementCancelDto>();
        
        //获取门店撤销申请撤销所需的[页面]信息【OA专用】
        AchievementCancelDto achievementCancelDto = achievementCancelMapper.getContractInfo(queryParam);
        if(achievementCancelDto.getContractNo().isEmpty()){
            resultData.setFail("【OA】查询撤销申请页面详情失败！");
            return resultData;
        }
        
        //storeIds转换成数组
        String list = (String)queryParam.get("storeIds");
        String[] storeIds = list.split(",");
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("storeIdList", storeIds);
        
        //获取申请撤销门店信息
        List<StoreDto> storelist = achievementCancelMapper.getStoreInfo(reqMap);
        if(storelist.isEmpty()){
            resultData.setFail("【OA】查询撤销申请门店详情失败！");
            return resultData;
        }
        
        achievementCancelDto.setStorelist(storelist);
        resultData.setReturnData(achievementCancelDto);
        return resultData;
    }

    /**
     * 
    * 插入一条撤销记录 更新门店撤消状态
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<String> createNewRecord(Map<String, Object> queryParam) throws Exception
    {
        
        ResultData<String> result = new ResultData<>();
        //门店撤销审核状态初始化code,17301-正常，17302-撤销中，17303-撤销失败，17304-已撤销
        AchievementCancel achievementCancel = new AchievementCancel();
        achievementCancel = MapToEntityConvertUtil.convert(queryParam, AchievementCancel.class, "");
        achievementCancel.setFlowId(String.valueOf(queryParam.get("flowId")));
        achievementCancel.setUserCreate((int)queryParam.get("userCreate"));
        achievementCancel.setApproveState(DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLING);
        //插入门店撤销记录
        int count = achievementCancelMapper.createNewCancelRecord(achievementCancel);
        if(count<1){
            result.setFail("插入门店撤销失败！queryParam=" +queryParam.toString());
            return result;
        }
        //撤销Id
        Integer id = achievementCancel.getId();
        queryParam.put("achievementCancelId", id);
        //storeIds转换成数组
        String list = (String)queryParam.get("storeIds");
        String[] storeIds = list.split(",");
        
        for(int i = 0; i < storeIds.length;i++){
           Integer storeId =Integer.valueOf(storeIds[i]);
           queryParam.put("storeId", storeId);
           //插入关联表
           int mappingCount = achievementCancelMappingMapper.createNewRecord(queryParam);
           if(mappingCount<1){
               result.setFail("插入门店撤销关联表失败！queryParam=" +queryParam.toString());
               return result;
           }
        }
        
        //更新contractStore中的isCancel状态为17202-撤销中，--17201为正常，17203为已撤销
        queryParam.put("storeIdList", storeIds);
        queryParam.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCELLING);
        int storeCount = contractStoreMapper.updateIsCancel(queryParam);
        /**撤销通过之后对应门店解除合同关系**/
        Object cIdObj = queryParam.get("contractId");
        if(null != cIdObj && !StringUtil.isEmpty(cIdObj.toString())){
        	Integer contractId = Integer.valueOf(cIdObj.toString());
        	Map<String,Object> param = new HashMap<String,Object>();
        	param.put("contractId", contractId);
        	param.put("storeIdList", storeIds);
        	contractStoreMapper.updateFlag(param);
        }
        if(storeCount<1){
            result.setFail("更新合同门店关联表状态失败！queryParam=" +queryParam.toString());
            return result;
        }
        return result;
    }

    /**
     * 
    * 门店业绩撤销变更更新数据
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<String> updateCancelRecord(Map<String, Object> queryParam) throws Exception
    {
        ResultData<String> result = new ResultData<>();
        
        //门店撤销审核状态初始化code,17301-正常，17302-撤销中，17303-撤销失败，17304-已撤销
        AchievementCancel achievementCancel = new AchievementCancel();
        achievementCancel = MapToEntityConvertUtil.convert(queryParam, AchievementCancel.class, "");
        achievementCancel.setFlowId(String.valueOf(queryParam.get("flowId")));
        achievementCancel.setUserCreate((int)queryParam.get("userCreate"));
        achievementCancel.setApproveState(DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLING);
        achievementCancel.setUpdateDate(new Date());
        achievementCancel.setUpdateUser(Integer.valueOf((int)queryParam.get("userCreate")));
        
        //更新门店撤销记录
        int count = achievementCancelMapper.updateCancelRecord(achievementCancel);
        if(count<1){
            result.setFail("更新门店撤销失败！queryParam=" +queryParam.toString());
            return result;
        }
        
        //获取撤销Id
        AchievementCancel achievementCancelInfo = achievementCancelMapper.getAchievementCancelByNo(queryParam);
        Integer id = achievementCancelInfo.getId();
        queryParam.put("achievementCancelId", id);

        //更新旧的撤销关系表delFlag 为 1
        int updateMappingCount = achievementCancelMappingMapper.updateOldCancelMappingRecord(queryParam);
        if(updateMappingCount<1){
            result.setFail("更新旧的撤销关系表delFlag 为 1失败！queryParam=" +queryParam.toString());
            return result;
        }

        //storeIds转换成数组
        String list = (String)queryParam.get("storeIds");
        String[] storeIds = list.split(",");
        queryParam.put("storeIds", storeIds);
        
        //创建新的撤销关系表
        for(int i = 0; i < storeIds.length;i++){
            Integer storeId =Integer.valueOf(storeIds[i]);
            queryParam.put("storeId", storeId);
            //插入关联表
            int mappingCount = achievementCancelMappingMapper.createNewRecord(queryParam);
            if(mappingCount<1){
                result.setFail("变更门店撤销关联表失败！queryParam=" +queryParam.toString());
                return result;
            }
         }
        
        //更新旧contractStore中的isCancel状态为  17201-正常，17202-撤销中，17203为已撤销
        queryParam.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISNOTCANCEL);
        int oldStoreCount = contractStoreMapper.updateIsCancel(queryParam);
        if(oldStoreCount<1){
            result.setFail("更新旧合同门店关联表状态失败！queryParam=" +queryParam.toString());
            return result;
        }
        
        //更新contractStore中的isCancel状态为17202-撤销中，--17201为正常，17203为已撤销
        queryParam.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCELLING);
        int storeCount = contractStoreMapper.updateIsCancelByStoreIds(queryParam);
        if(storeCount<1){
            result.setFail("更新新合同门店关联表状态失败！queryParam=" +queryParam.toString());
            return result;
        }
        return result;
    }

//    /**
//     * 
//    * 根据合同Id查询最新合同
//    * @param contractId
//    * @return
//     */
//    public ResultData<String> getNewestContract(Integer contractId)
//    {
//        ResultData<String> resultData = new ResultData<String>();
//        
//        Contract  contract= contractMapper.getNewestContract(contractId);
//        if(!contract.getId().equals(null)){
//            //如果最新合同Id 和传入ID不同 则尊在最新合同
//            if(contract.getId().equals(contractId)){
//                resultData.setReturnData("Y");
//            }else{
//                resultData.setReturnData("N");
//            }
//        }else{
//            resultData.setFail("查询失败！");
//        }
//        return resultData;
//    }
    
    
    /**
     * 根据撤销编码查询合同撤销的Id，再根据Id到mapping表查合同和门店信息
     * 
     * @param contractId
     *            合同ID
     * @return
     * @throws Exception
     */
    public ResultData<List<AchievementCancelMapping>> getCanelStoresByFlowId(String flowId) throws Exception {
        // 构建返回
        ResultData<List<AchievementCancelMapping>> resultData = new ResultData<List<AchievementCancelMapping>>();
        List<AchievementCancelMapping> contractStores = achievementCancelMappingMapper.getAchievementCancelMappingsByFlowId(flowId);
        resultData.setReturnData(contractStores);
        return resultData;
    }
    
    /**
     * 
    * 门店业绩撤销变更更新数据
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<String> UpdateCancelState(Map<String, Object> queryParam) throws Exception
    {
        ResultData<String> result = new ResultData<>();
        //更新门店撤销记录
        int count = achievementCancelMapper.updateCancelState(queryParam);
        if(count<1){
            result.setFail("更新审核状态失败！queryParam=" +queryParam.toString());
            return result;
        }
        return result;
    }
    
    /**
     * 
    *  根据门店编码批量更新合同门店关系表的IsCancel数据
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<String> UpdateIsCancelByStoreIds(Map<String, Object> queryParam) throws Exception
    {
        ResultData<String> result = new ResultData<>();
        //更新门店撤销记录
        int count = contractStoreMapper.updateIsCancelByStoreIds(queryParam);
        if(count<1){
            result.setFail("更新门店撤销失败！queryParam=" +queryParam.toString());
            return result;
        }
        return result;
    }
    
    
   //##################### 以下是定时获取OA审核状态专用方法 start (2016-11-06) ########################//
    /**
     * 获取合同撤销状态为审核中的FlowIdList
     * 
     * @param params: 审核状态 approveState = 17302，审核中
     *           
     * @return
     * @throws Exception
     */
    public List<AchievementCancelDto> getCancelByApproveState(Map<String, Object> params) throws Exception {
        // 构建返回
        List<AchievementCancelDto> achievementCancelDtoList = new ArrayList<AchievementCancelDto>();
        // 查询操作
        List<AchievementCancel> achievementCancelList = achievementCancelMapper.getCancelByapproveState(params);
        // 非空判断
        if (null != achievementCancelList && !achievementCancelList.isEmpty())
        {
            for (AchievementCancel achievementCancel : achievementCancelList) {
                AchievementCancelDto ccDto = new AchievementCancelDto();
                BeanUtils.copyProperties(achievementCancel, ccDto);
                achievementCancelDtoList.add(ccDto);
            }
        }
        return achievementCancelDtoList;
    }
  //##################### 以上是定时获取OA审核状态专用方法 start (2016-11-06) ########################//

    /**
     * 
    * 业绩撤销,检查门店签约新合同情况
    * @param queryParam
    * @return
     * @throws Exception 
     */
    public ResultData<String> checkStoreSignContract(Map<String, Object> queryParam) throws Exception
    {
        ResultData<String> result = new ResultData<>();
        String str ="";
        Integer contractId = Integer.valueOf((String)queryParam.get("contractId"));
        
        //storeIds转换成数组
        String list = (String)queryParam.get("storeIds");
        String[] storeIds = list.split(",");
        Map<String,Object> reqMap = new HashMap<String, Object>();
        reqMap.put("storeIdList", storeIds);
        
        //根据门店ids查询最新非撤销合同Id
        List<ContractDto> conList = contractMapper.getTopOneContract(reqMap);
        
        for(ContractDto con : conList){
            Integer id = con.getId();
            if(!contractId.equals(id)){
                str = "所撤销门店"+con.getStoreNo()+"已有新签合同,请在新合同中撤销！新签合同编号:"+con.getContractNo();
                result.setFail(str);
                return result;
            }
        }
        
        return result;
    }
    
    /**
     * 根据门店ids 清空门店等级信息
     * @param queryParam
     * @return
     * @throws Exception
     */
    public ResultData<String> clearStoreABtype(Map<String, Object> queryParam) throws Exception
    {
    	ResultData<String> result = new ResultData<>();
        //更新门店信息
        int count = storeMapper.clearStoreABtype(queryParam);
        if(count<1){
            result.setFail("门店资质等级清空失败！queryParam=" +queryParam.toString());
            return result;
        }
        return result;
    }
    
  //Add cning 2017/07/04 Start
    /**
     * 
    * 获取门店业绩撤销记录
    * @param contractId
    * @return
     * @throws Exception 
     */
    public List<AchievementCancel> getCancelStore(Integer storeId, Integer contractId) throws Exception
    {
        Map<String, Object> map=new HashMap<String, Object>();
        map.put("storeId", storeId);
        map.put("contractId", contractId);
        
        List<AchievementCancel> list = achievementCancelMapper.getCancelStore(map);
  
        return list;
    }
  //Add cning 2017/07/04 End
    
    /**
     * 查询门店装修记录
     * @param queryParam
     * @return
     * @throws Exception
     */
    public ResultData<List<StoreDecorationDto>> getStoreDecorationList(Map<String, Object> queryParam) throws Exception
    {
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<List<StoreDecorationDto>>();
        
        List<StoreDecorationDto> list = achievementCancelMapper.getStoreDecorationList(queryParam);

        resultData.setReturnData(list);
            
        return resultData;
    }

    public void updateStoreIsCancel(String achievementCancelNo) {
        achievementCancelMapper.updateStoreIsCancel(achievementCancelNo);
    }
}
