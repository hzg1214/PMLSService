package cn.com.eju.deal.controller.followMap;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.followMap.StoreStopAuditDto;
import cn.com.eju.deal.service.followMap.StoreStopAuditService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 门店停业上报审批
 */
@RestController
@RequestMapping(value = "storeStopAuditController")
public class StoreStopAuditController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "storeStopAuditService")
    private StoreStopAuditService storeStopAuditService;


    /**
     * 根据门店ID  检查门店是否存在重复审批记录、是否有门店中心、以及是否有中心负责人信息
     * @param storeId
     * @return
     */
    @RequestMapping(value = "/checkStoreCenterByStoreId", method = RequestMethod.POST)
    public String checkStoreCenterByStoreId(@RequestBody Integer storeId) {
        ResultData resultData = new ResultData<Integer>();
        try{
            resultData = storeStopAuditService.checkStoreCenterByStoreId(storeId);
        }catch (Exception e){
            resultData.setFail();
            logger.error("StoreStopAuditController", "storeStopAuditController", "checkStoreCenterByStoreId", "", null, "", "检查门店是否存在重复审批记录、是否有门店中心、以及是否有中心负责人信息异常", e);
        }
        return resultData.toString();
    }

    /**
     * 根据ID 获取门店停业上报审批记录信息
     * @param Id
     * @return
     */
    @RequestMapping(value = "/getStoreStopAuditById", method = RequestMethod.POST)
    public String getStoreStopAuditById(@RequestBody Integer Id) {
        ResultData<StoreStopAuditDto> resultData = new ResultData<StoreStopAuditDto>();
        try{
            resultData = storeStopAuditService.getStoreStopAuditById(Id);
        }catch (Exception e){
            resultData.setFail();
            logger.error("StoreStopAuditController", "StoreStopAuditController", "getStoreStopAuditById", "", null, "", "获取门店停业上报审批记录信息异常", e);
        }
        return resultData.toString();
    }
    /**
     * 根据ID 获取门店停业上报审批记录信息
     * @param Id
     * @return
     */
    @RequestMapping(value = "/getNotStoreStopAuditList", method = RequestMethod.POST)
    public String getNotStoreStopAuditList() {
        ResultData<StoreStopAuditDto> resultData = new ResultData<StoreStopAuditDto>();
        try{
            resultData = storeStopAuditService.getNotStoreStopAuditList();
        }catch (Exception e){
            resultData.setFail();
            logger.error("StoreStopAuditController", "StoreStopAuditController", "getNotStoreStopAuditList", "", null, "", "获取门店停业上报审批记录信息异常", e);
        }
        return resultData.toString();
    }

    /**
     * 新增门店停业上报审批记录
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/addStoreStopAudit", method = RequestMethod.POST)
    public String addStoreStopAudit(@RequestBody String jsonDto) {
        ResultData<StoreStopAuditDto> resultData = new ResultData<StoreStopAuditDto>();
        StoreStopAuditDto dto = JsonUtil.parseToObject(jsonDto, StoreStopAuditDto.class);
        try{
            int count = storeStopAuditService.addStoreStopAudit(dto);
            if(count<=0){
                resultData.setFail("新增门店停业上报审批失败");
            }else{
                resultData.setReturnData(dto);
            }
        }catch (Exception e){
            resultData.setFail();
            logger.error("StoreStopAuditController", "StoreStopAuditController", "addStoreStopAudit", "", null, "", "新增门店停业上报审批记录异常", e);
        }
        return resultData.toString();
    }


    /**
     * 审批通过 门店停业上报审批记录
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/updateStoreStopAudit", method = RequestMethod.POST)
    public String updateStoreStopAudit(@RequestBody String jsonDto) {
        ResultData<StoreStopAuditDto> resultData = new ResultData<StoreStopAuditDto>();
        StoreStopAuditDto dto = JsonUtil.parseToObject(jsonDto, StoreStopAuditDto.class);
        try{
            int count = storeStopAuditService.updateStoreStopAudit(dto);
            if(count<=0){
                resultData.setFail("审批通过门店停业上报审批失败");
            }else{
                resultData.setReturnData(dto);
            }
        }catch (Exception e){
            resultData.setFail();
            logger.error("StoreStopAuditController", "StoreStopAuditController", "updateStoreStopAudit", "", null, "", "审批通过门店停业上报审批记录异常", e);
        }
        return resultData.toString();
    }


}
