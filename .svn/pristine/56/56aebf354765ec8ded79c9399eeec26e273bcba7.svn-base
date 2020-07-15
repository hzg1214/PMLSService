package cn.com.eju.deal.contract.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.DictionaryConstants;
import cn.com.eju.deal.contract.model.AchievementCancelMapping;
import cn.com.eju.deal.contract.service.AchievementCancelService;
import cn.com.eju.deal.contract.service.ContractChangeService;
import cn.com.eju.deal.contract.service.ContractService;
import cn.com.eju.deal.contract.service.OaAttachmentService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.AchievementCancelDto;
import cn.com.eju.deal.dto.store.StoreDecorationDto;
import cn.com.eju.deal.store.service.StoreService;

/**
 * 门店业绩撤销
* @author (wushuang)
* @date 2016年10月11日 上午10:27:32
 */
@RestController
@RequestMapping(value = "achievement")
public class AchievementCancelController extends BaseController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "achievementCancelService")
    private AchievementCancelService achievementCancelService;
    
    @Resource(name = "stopContractService")
    private ContractChangeService stopContractService;
    
    @Resource(name = "contractService")
    private ContractService contractService;
    
    @Resource(name = "storeService")
    private StoreService storeService;
    
    @Resource(name = "oaAttachmentService")
    private OaAttachmentService oaAttachmentService;
    
    /**
     * 业绩撤销申请页面门店信息获取
     */
    @RequestMapping(value = "/cancel/storelist/{contractId}", method = RequestMethod.GET)
    public String getAchievementCancelInfo(@PathVariable("contractId") Integer contractId)
    {
        ResultData<List<AchievementCancelDto>> resultData = new ResultData<List<AchievementCancelDto>>();
        try
        {
            resultData = achievementCancelService.getAchievementCancelInfo(contractId);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "getAchievementCancelInfo",
                "",
                0,
                "",
                "查询业绩撤销门店,查询失败!",
                e);
        }
        return resultData.toString();
    }
    
    /**
     * 获取门店业绩撤销记录
     */
    @RequestMapping(value = "/cancel/cancellist/{contractId}", method = RequestMethod.GET)
    public String getAchievementCancelRecord(@PathVariable("contractId") Integer contractId)
    {
        ResultData<List<AchievementCancelDto>> resultData = new ResultData<List<AchievementCancelDto>>();
        try
        {
            resultData = achievementCancelService.getAchievementCancelRecord(contractId);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "getAchievementCancelRecord",
                "",
                0,
                "",
                "获取门店业绩撤销记录,查询失败!",
                e);
        }
        return resultData.toString();
    }
    
    /**
     * 
    * 根据合同ID和门店撤销编号获取-查看-所需详细信息
    * @param contractId
    * @return
     */
    @RequestMapping(value = "/toView/cancelinfo/{param}", method = RequestMethod.GET)
    public String getInfoToView(@PathVariable("param") String param)
    {
        ResultData<AchievementCancelDto> resultData = new ResultData<AchievementCancelDto>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try
        {
            resultData = achievementCancelService.getInfoToView(queryParam);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "getInfoToView",
                "",
                0,
                "",
                " 根据合同ID和门店撤销编号获取-查看-所需详细信息,查询失败!",
                e);
        }
        return resultData.toString();
    }
    
    /**
     * 
    *  插入一条撤销记录 更新门店撤消状态
    * @param param
    * @return
     */
    @RequestMapping(value = "/create/{param}", method = RequestMethod.GET)
    public String createNewRecord(@PathVariable("param") String param)
    {
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        try
        {
            resultData = achievementCancelService.createNewRecord(queryParam);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "createNewRecord",
                "",
                0,
                "",
                " 插入一条撤销记录 更新门店撤消状态失败!",
                e);
        }
        return resultData.toString();
    }
    
    /**
     * 
    * 获取门店撤销申请撤销所需的信息【OA专用】
    * @param param
    * @return
     */
    @RequestMapping(value = "/oa/oaauditnfo/{param}", method = RequestMethod.GET)
    public String getOAAuditInfo(@PathVariable("param") String param)
    {
        ResultData<AchievementCancelDto> resultData = new ResultData<AchievementCancelDto>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try
        {
            resultData = achievementCancelService.getOAAuditInfo(queryParam);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "getOAAuditInfo",
                "",
                0,
                "",
                " 获取门店撤销申请撤销所需的信息【OA专用】,查询失败!",
                e);
        }
        return resultData.toString();
    }
    
    /**
     * 
    * 门店业绩撤销变更更新数据
    * @param param
    * @return
     */
    @RequestMapping(value = "/update/{param}", method = RequestMethod.GET)
    public String updateCancelRecord(@PathVariable("param") String param)
    {
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        try
        {
            resultData = achievementCancelService.updateCancelRecord(queryParam);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "createNewRecord",
                "",
                0,
                "",
                " 门店业绩撤销变更更新数据 更新门店撤消状态失败!",
                e);
        }
        return resultData.toString();
    }
    
//    /**
//     * 
//    * 根据合同Id查询最新合同
//    * @param contractId
//    * @return
//     */
//    @RequestMapping(value = "/getnewest/{contractId}", method = RequestMethod.GET)
//    public String getNewestContract(@PathVariable("contractId") Integer contractId)
//    {
//        ResultData<String> resultData = new ResultData<>();
//        try
//        {
//            resultData = achievementCancelService.getNewestContract(contractId);
//        }
//        catch (Exception e)
//        {
//            logger.error("contract",
//                "AchievementCancelController",
//                "getNewestContract",
//                "",
//                0,
//                "",
//                "根据合同Id查询最新合同,查询失败!",
//                e);
//        }
//        return resultData.toString();
//    }
    
    /** 
     * 根据 流程Id及审核状态 更新合同信息 
     * @param flowId 流程ID 
     * @param auditRst 审核状态
     * @param userId 操作人ID
     * @return 处理结果
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/flowId/auditRst/{achievementCancelNo}/{flowId}/{auditRst}/{userId}", method = RequestMethod.GET)
    public String updateCtrctRelateInfo(@PathVariable String achievementCancelNo, @PathVariable String flowId,
        @PathVariable Integer auditRst, @PathVariable Integer userId)
    {
        // 构建返回
        ResultData<?> rstData = new ResultData<>();
        try
        {
            // 根据FlowId查询合同变更下的门店
            Map<String, Object> resultMap = getCtrctInfoByFlowId(flowId);
            Integer contractId = Integer.valueOf(resultMap.get("contractId").toString());
            List<Integer> storeIdList = (List<Integer>)resultMap.get("storeIdList");
            if (null != storeIdList && !storeIdList.isEmpty())
            {
                if (0 == auditRst)
                {// 审核通过
                 // 审核通过：根据flowId、合同Id和门店Ids进行后台操作处理
                    doPassDateUpdate(achievementCancelNo, flowId, contractId, storeIdList, userId);
                    rstData.setReturnMsg("撤销审核通过!更新合同信息成功!");
                }
                else if (5 == auditRst || 15 == auditRst)
                { // 审核未通过
                  // 审核不通过：根据flowId、合同Id和门店Ids进行后台操作处理
                    doNoPassDateUpdate(achievementCancelNo, flowId, contractId, storeIdList, userId);
                    rstData.setReturnMsg("撤销审核不通过!更新合同信息成功!");
                }
            }
            else
            {
                rstData.setReturnMsg("没有变更的门店!");
            }
        }
        catch (Exception e)
        {
            rstData.setFail();
            logger.error("contract",
                "ContractChangeController",
                "updateCtrctRelateInfo",
                "",
                -1,
                "",
                "根据审核状态更新合同信息失败！",
                e);
        }
        return rstData.toString();
    }
    
    /**
     * 根据flow查询合同Id以及合同下的门店Id集合
     * @return
     * @throws Exception 
     */
    private Map<String, Object> getCtrctInfoByFlowId(String flowId)
        throws Exception
    {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        
        // 根据achievementCancelNo,FlowId查询合同撤销下的合同和门店编码
        List<Integer> storeIdList = new ArrayList<Integer>();
        ResultData<List<AchievementCancelMapping>> resultDataCcs =
            achievementCancelService.getCanelStoresByFlowId(flowId);
        List<AchievementCancelMapping> contractStoreList = resultDataCcs.getReturnData();
        // 合同ID
        int contractId = 0;
        if (null != contractStoreList && !contractStoreList.isEmpty())
        {
            for (AchievementCancelMapping contractStore : contractStoreList)
            {
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
     * 审核通过：根据flowId、合同Id和门店Ids进行后台操作处理
     * @throws Exception 
     */
    private void doPassDateUpdate(String achievementCancelNo, String flowId, Integer contractId,
        List<Integer> storeIdList, Integer userId)
        throws Exception
    {
        
        //更新合同撤销信息为审核通过
        Map<String, Object> updateCancelStateMap = new HashMap<String, Object>();
        updateCancelStateMap.put("achievementCancelNo", achievementCancelNo);
        updateCancelStateMap.put("approveState", DictionaryConstants.CONTRACT_ISCANCEL_ISCANCELLED);
        updateCancelStateMap.put("approveDate", new Date());
        achievementCancelService.UpdateCancelState(updateCancelStateMap);
        
        // 更新合同门店关系表的isCancel状态
        Map<String, Object> updateIsCancelStateMap = new HashMap<String, Object>();
        updateIsCancelStateMap.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISCANCEL);
        updateIsCancelStateMap.put("contractId", contractId);
        
        StringBuffer buffer = new StringBuffer();
        String string = "";
        String storeIds[] = new String[] {};
        if (storeIdList != null && storeIdList.size() > 0)
        {
            for (Integer integer : storeIdList)
            {
                buffer.append(integer);
                buffer.append(",");
            }
        }
        if (buffer != null && buffer.length() > 0)
        {
            string = buffer.substring(0, buffer.length() - 1).toString();
            storeIds = string.split(",");
        }
        updateIsCancelStateMap.put("storeIds", storeIds);
        achievementCancelService.UpdateIsCancelByStoreIds(updateIsCancelStateMap);
        
        // 根据门店ids 清空门店等级信息
        Map<String, Object> clearStoreABtypeMap = new HashMap<String, Object>();
        clearStoreABtypeMap.put("storeIds", storeIds);
        achievementCancelService.clearStoreABtype(clearStoreABtypeMap);
        
    }
    
    /**
     * 审核不通过：根据flowId、合同Id和门店Ids进行后台操作处理
     * @throws Exception 
     */
    private void doNoPassDateUpdate(String achievementCancelNo, String flowId, Integer contractId,
        List<Integer> storeIdList, Integer userId)
        throws Exception
    {
        
        //更新合同撤销信息为审核通过
        Map<String, Object> updateCancelStateMap = new HashMap<String, Object>();
        updateCancelStateMap.put("achievementCancelNo", achievementCancelNo);
        updateCancelStateMap.put("approveState", DictionaryConstants.CONTRACT_ISCANCEL_CANCELFAIL);
        updateCancelStateMap.put("approveDate", new Date());
        achievementCancelService.UpdateCancelState(updateCancelStateMap);
        
        // 更新合同门店关系表的isCancel状态
        Map<String, Object> updateIsCancelStateMap = new HashMap<String, Object>();
        updateIsCancelStateMap.put("isCancel", DictionaryConstants.STORESTATE_ISCANCEL_ISNOTCANCEL);
        updateIsCancelStateMap.put("contractId", contractId);
        StringBuffer buffer = new StringBuffer();
        String string = "";
        String storeIds[] = new String[] {};
        if (storeIdList != null && storeIdList.size() > 0)
        {
            for (Integer integer : storeIdList)
            {
                buffer.append(integer);
                buffer.append(",");
            }
        }
        if (buffer != null && buffer.length() > 0)
        {
            string = buffer.substring(0, buffer.length() - 1).toString();
            storeIds = string.split(",");
        }
        updateIsCancelStateMap.put("storeIds", storeIds);
        achievementCancelService.UpdateIsCancelByStoreIds(updateIsCancelStateMap);
        
    }
    
    /**
     * 
    * 业绩撤销,检查门店签约新合同情况
    * @param param
    * @return
     */
    @RequestMapping(value = "/check/sign/{param}", method = RequestMethod.GET)
    public String checkStoreSignContract(@PathVariable("param") String param)
    {
        ResultData<String> resultData = new ResultData<String>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        try
        {
            resultData = achievementCancelService.checkStoreSignContract(queryParam);
        }
        catch (Exception e)
        {
            logger.error("contract",
                "AchievementCancelController",
                "checkStoreSignContract",
                "",
                0,
                "",
                " 业绩撤销,检查门店签约新合同情况失败!",
                e);
        }
        return resultData.toString();
    }
    
    /**
     * 获取门店装修记录
     */
    @RequestMapping(value = "/getStoreDecorationList/{param}", method = RequestMethod.GET)
    public String getStoreDecorationList(@PathVariable("param") String param)
    {
    	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
    	//storeIds转换成数组
        String list = (String)queryParam.get("storeIds");
        String[] storeIds = list.split(",");
        queryParam.put("storeIdList", storeIds);
        ResultData<List<StoreDecorationDto>> resultData = new ResultData<List<StoreDecorationDto>>();
        try
        {
            resultData = achievementCancelService.getStoreDecorationList(queryParam);
        }
        catch (Exception e)
        {
            logger.error("contract","AchievementCancelController","getStoreDecorationList","",0,"","获取门店装修记录记录,查询失败!",e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/operateAuditCt/{achievementCancelNo}", method = RequestMethod.GET)
    public String operateAuditCt(@PathVariable String achievementCancelNo)
    {
// 构建返回
        ResultData<?> rstData = new ResultData<>();
        Map<String, Object> updateCancelStateMap = new HashMap<String, Object>();
        updateCancelStateMap.put("achievementCancelNo", achievementCancelNo);
        updateCancelStateMap.put("approveState", DictionaryConstants.CONTRACT_ISCANCEL_CANCELFAIL);
        updateCancelStateMap.put("approveDate", new Date());
        try {
            rstData = achievementCancelService.UpdateCancelState(updateCancelStateMap);
            achievementCancelService.updateStoreIsCancel(achievementCancelNo);
        } catch (Exception e) {
            rstData.setFail("状态变更失败");
            logger.error("contract",
                    "AchievementCancelController",
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
