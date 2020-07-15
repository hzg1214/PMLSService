/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:StoreBizStopService.java
 * Package Name:cn.com.eju.deal.store.service
 * Date:2017年9月26日下午5:20:38
 */
package cn.com.eju.deal.store.service;

import cn.com.eju.deal.achievement.dao.CitySettingMapper;
import cn.com.eju.deal.base.seqNo.api.ISeqNoAPI;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.dao.AchievementCancelMapper;
import cn.com.eju.deal.contract.dao.AchievementCancelMappingMapper;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.dao.ContractStoreMapper;
import cn.com.eju.deal.contract.model.AchievementCancel;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.mapper.followMap.StoreStopAuditMapper;
import cn.com.eju.deal.model.followMap.StoreStopAuditDto;
import cn.com.eju.deal.oa.model.OaOperator;
import cn.com.eju.deal.oa.service.OaOperatorService;
import cn.com.eju.deal.store.dao.StoreBizStopMapper;
import cn.com.eju.deal.store.model.StoreBizStop;
import cn.com.eju.deal.user.dao.UserMapper;
import cn.com.eju.deal.user.model.ExchangeCenter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: StoreBizStopService <br/>
 * Description: 门店停业审核 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月26日 下午5:20:38 <br/>
 * @version V1.0
 */
@Service("storeBizStopService")
public class StoreBizStopService extends BaseService<StoreBizStop> {
    
    @Resource
    private StoreBizStopMapper storeBizStopMapper;
    @Resource
    private ContractMapper contractMapper;
    @Resource
    private AchievementCancelMapper achievementCancelMapper;
    @Resource
    private AchievementCancelMappingMapper achievementCancelMappingMapper;
    @Resource
    private ContractStoreMapper contractStoreMapper;
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    @Resource(name = "seqNoAPI")
    ISeqNoAPI seqNoAPI;

    @Resource
    private UserMapper userDao;
    
    @Resource(name = "storeStopAuditMapper")
    private StoreStopAuditMapper storeStopAuditMapper;
    
    @Resource
    private CitySettingMapper citySettingMapper;

    /**
     * 【描述】: 查询停业上报审核列表
     *
     * @author yinkun
     * @date: 2017年9月26日 下午5:28:25
     * @param param
     * @return
     */
    public ResultData<List<StoreBizStop>> queryList(Map<String, Object> param) throws Exception {
        ResultData<List<StoreBizStop>> resultData = new ResultData<>();

        Integer userId = (Integer)param.get("userId");
        List<ExchangeCenter> centerList = userDao.getCenterListByUserId(userId);
        String centerIdStr = (String)param.get("centerIdStr");
        if(StringUtil.isEmpty(centerIdStr)){
            if (null != centerList && !centerList.isEmpty()) {
                for (ExchangeCenter exc : centerList) {
                    if(StringUtil.isEmpty(centerIdStr)){
                        centerIdStr = exc.getExchangeCenterId().toString();
                    }else{
                        centerIdStr = centerIdStr + ',' +exc.getExchangeCenterId().toString();
                    }
                }
            }
            if (!centerIdStr.isEmpty()) param.put("centerIdStr", centerIdStr);
        }
        List<StoreBizStop> list = storeBizStopMapper.queryList(param);
        resultData.setReturnData(list);
        
        return resultData;
    }

    /**
     * 【描述】: 查询停业上报明细
     *
     * @author yinkun
     * @date: 2017年9月27日 下午12:03:05
     * @param stopId
     * @return
     */
    public StoreBizStop getByStopId(Integer stopId) {
        
        return storeBizStopMapper.getById(stopId);
    }

    /**
     * 【描述】: 门店停业上报驳回
     *
     * @author yinkun
     * @date: 2017年9月27日 下午6:25:41
     * @param storeBizStop
     */
    public ResultData<StoreBizStop> rejectStop(StoreBizStop storeBizStop) {
        
        ResultData<StoreBizStop> result = new ResultData<StoreBizStop>();
        
        //更新停业上报
        storeBizStopMapper.update(storeBizStop);
        
        //修改门店营业状态
        Map<String,Object> param = new HashMap<>();
        param.put("storeId", storeBizStop.getStoreId());
        param.put("businessStatus", 20901);
        storeBizStopMapper.updateStoreBusinessStatus(param);
        
        return result;
    }

    /**
     * 【描述】: 停业上报审核通过
     *
     * @author yinkun
     * @date: 2017年9月28日 上午10:57:17
     * @param storeBizStop
     * @return
     * @throws Exception 
     */
    public ResultData<StoreBizStop> auditPass(StoreBizStop storeBizStop) throws Exception {
        
        ResultData<StoreBizStop> result = new ResultData<StoreBizStop>();
        Integer storeIdcheck =storeBizStop.getStoreId();
        StoreBizStop contractStatusInfo = storeBizStopMapper.getContractStatusInfo(storeBizStop);
        /*StoreBizStop decorationStatusInfo = storeBizStopMapper.getDecorationStatusInfo(contractStatusInfo);
        String returnMsg = null;*/
        Integer businessStatus = null;
        Integer contractStatus = null;
        boolean cancelRecord = false;
        
        /*//合同变更判断
        if(contractStatusInfo != null && contractStatusInfo.getChangeStatus() != null) {
            switch(contractStatusInfo.getChangeStatus()) {
            case 0:
            case 1:
                //待提交审核 审核中
                returnMsg = "门店合同变更中，不允许上报门店停业!";
                break;
            case 2:
            case 3:
            }
        }
        
        //合同状态逻辑判断
        if(contractStatusInfo != null &&contractStatusInfo.getContractStatus() != null) {
            switch(contractStatusInfo.getContractStatus()) {
            case 10404:
                //审核未通过（同草签）
            case 10401:
                //草签    门店停业    合同作废
                businessStatus = 20903;
                contractStatus = 10405;
                break;
            case 10402:
                //审核中
                returnMsg = "门店合同审批中，不允许上报门店停业！";
                break;
            case 10403:
                //审核通过
                if(decorationStatusInfo != null && decorationStatusInfo.getOaMdfpStatus() != null) {
                    switch(decorationStatusInfo.getOaMdfpStatus()) {
                    case 16504:
                        //翻牌申请审批不通过    门店停业    合同保持审核通过    翻版记录保持    生成合同撤销记录
                    case 16501:
                        //未申请   门店停业    合同保持审核通过    生成合同撤销记录
                        businessStatus = 20903;
                        cancelRecord = true;
                        break;
                    case 16502:
                        //审批中
                        returnMsg = "门店翻牌申请中，不允许上报门店停业！";
                        break;
                    case 16503:
                        //审批通过
                        if(decorationStatusInfo.getOaMdysStatus() != null) {
                            switch(decorationStatusInfo.getOaMdysStatus()) {
                            case 16601:
                            case 16602:
                            case 16604:
                                //验收    未申请,审批中,审批不通过
                                returnMsg = "门店翻牌验收未完成，不允许上报门店停业！";
                                break;
                            case 16603:
                                //验收    审批通过
                                businessStatus = 20903;
                                cancelRecord = true;
                                break;
                            }
                        }else {
                            //无翻牌验收记录
                            returnMsg = "门店翻牌申请中，不允许上报门店停业！";
                            break;
                        }
                    }
                }else {
                    //无翻牌申请记录
                    businessStatus = 20903;
                    cancelRecord = true;
                }
                break;
            case 10405:
                //作废    门店停业    合同保持作废(同终止)
            case 10406:
                //终止     门店停业    合同保持终止
                businessStatus = 20903;
                break;
            }
        }else {
            // 未签   门店停业  合同无
            businessStatus = 20903;
        }*/
        
        businessStatus = 20903;
        contractStatus = 10405;
        if(contractStatusInfo.getContractId() != null && (contractStatusInfo.getContractStatus() == 10403||contractStatusInfo.getContractStatus() == 10406)) {
            contractStatus = null;
            cancelRecord = true;
        }
        
        /*if(returnMsg != null) {
            result.setFail(returnMsg);
        }else {*/
            //停业上报审核通过
            storeBizStopMapper.update(storeBizStop);
            
            //修改门店为停业
            if(businessStatus != null) {
                Map<String, Object> param = new HashMap<>();
                param.put("businessStatus", businessStatus);
                param.put("storeId", storeBizStop.getStoreId());
                storeBizStopMapper.updateStoreBusinessStatus(param);
            }
            
            //修改合同状态为作废
            if(contractStatusInfo.getContractId() != null && contractStatus != null) {
                Contract contract = new Contract();
                contract.setId(contractStatusInfo.getContractId());
                contract.setContractStatus(contractStatus);
                contractMapper.update(contract);
            }
            
            //生成合同撤销记录
            if(cancelRecord) {
                
                //存在非撤销失败的撤销记录,不重复生成撤销记录
                Map<String,Object> param = new HashMap<>();
                param.put("storeId", storeBizStop.getStoreId());
                param.put("contractId", contractStatusInfo.getContractId());
                List<Map<String,Object>> cancelList = storeBizStopMapper.queryCancelList(param);
                if(cancelList != null && !cancelList.isEmpty()) {
                    return result;
                }
                
                StoreStopAuditDto newStoreStopAuditDto = storeStopAuditMapper.getStoreStopAuditById(storeBizStop.getStopId());
                //不发起oa撤销,仅本地记录
                AchievementCancel achievementCancel = new AchievementCancel();
                achievementCancel.setAchievementCancelNo(seqNoAPI.getSeqNoByTypeCode("TYPE_BUSINESS_CANCEL").getReturnData());
                achievementCancel.setContractId(contractStatusInfo.getContractId());
                String cancelReson = "停业原因：" +newStoreStopAuditDto.getStopReasonName() +"  描述："+ newStoreStopAuditDto.getFollowDetail();
                achievementCancel.setCancelReason(cancelReson);
                achievementCancel.setRemarks("门店停业");
                achievementCancel.setDateCreate(new Date());
                achievementCancel.setUserCreate(storeBizStop.getAuditUserId());
                achievementCancel.setApproveState(DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLED);
                achievementCancel.setApproveDate(new Date());
                achievementCancel.setFlowId("");
                achievementCancel.setUpdateDate(new Date());
                achievementCancel.setUpdateUser(storeBizStop.getAuditUserId());
               //获取事业部区域及其核算主体编码
                String bussineArea ="";
                String accountProjectCode ="";
                Map<String, Object> map = citySettingMapper.getCitySettingByCityNo(contractStatusInfo.getAcCityNo());
                if(null != map) {
                	bussineArea=map.get("busCode").toString();
        			accountProjectCode=map.get("accountProjectCode").toString();
        		}
                //核算主体
                achievementCancel.setAccountSubject(accountProjectCode);
                //事业部区域
                achievementCancel.setBusDepartment(bussineArea);
                achievementCancel.setStopKbn("1");
                //插入门店撤销记录
                achievementCancelMapper.createNewCancelRecord(achievementCancel);
                
                Map<String, Object> cancelMapping = new HashMap<>();
                cancelMapping.put("achievementCancelId", achievementCancel.getId());
                cancelMapping.put("storeId", storeBizStop.getStoreId());
                cancelMapping.put("contractId", contractStatusInfo.getContractId());
                //合同，门店撤销关联表
                achievementCancelMappingMapper.createNewRecord(cancelMapping);
                
                Map<String, Object> contractStore = new HashMap<>();
                contractStore.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL);
                contractStore.put("achievementCancelNo", achievementCancel.getAchievementCancelNo());
                contractStoreMapper.updateIsCancel(contractStore);
            }
        /*}*/
            
        //解除门店合同关系
        if((contractStatusInfo.getContractId() != null && contractStatus != null) || cancelRecord) {
            Map<String, Object> contractStore = new HashMap<>();
            contractStore.put("contractId", contractStatusInfo.getContractId());
            contractStore.put("storeIdList", new String[] {storeBizStop.getStoreId().toString()});
            contractStoreMapper.updateFlag(contractStore);
        }
        
        return result;
    }
    
    private String getBussineArea(String busNo,String userCode) throws Exception{
        // 事业部区域
        String bussineArea = null;
        // 查询是否是经办人
        OaOperator oa = oaOperatorService.getByUserCode(userCode);
        
        if (null != oa){
            // （取经办人的事业部区域，如果是跨区的，取其选择的区域）
            // 暂时不考虑跨区的情况，后期有需求再改
            Boolean isCombine = oa.getIsCombine();
            if (isCombine){
                bussineArea = busNo;
            }
            else{
                bussineArea = oa.getBusCode();
            }
        }
        return bussineArea;
    }
}

