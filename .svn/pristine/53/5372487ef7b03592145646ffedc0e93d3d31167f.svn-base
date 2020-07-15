package cn.com.eju.deal.controller.sweepStreets;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.sweepStreets.StoreLocationAuditDto;
import cn.com.eju.deal.service.sweepStreets.StoreLocationAuditService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 门店位置变更审批
 */
@RestController
@RequestMapping(value = "storeLocationAuditController")
public class StoreLocationAuditController extends BaseController
{
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "storeLocationAuditService")
    private StoreLocationAuditService storeLocationAuditService;


    /**
     * 根据门店ID  检查门店是否存在重复审批记录、是否有门店中心、以及是否有中心负责人信息
     * @param storeId
     * @return
     */
    @RequestMapping(value = "/checkStoreCenterByStoreId", method = RequestMethod.POST)
    public String checkStoreCenterByStoreId(@RequestBody Integer storeId) {
        ResultData<Integer> resultData = new ResultData<Integer>();
        try{
            resultData = storeLocationAuditService.checkStoreCenterByStoreId(storeId);
        }catch (Exception e){
            resultData.setFail();
            logger.error("StoreLocationAuditController", "storeLocationAuditController", "checkStoreCenterByStoreId", "", null, "", "检查门店是否存在重复审批记录、是否有门店中心、以及是否有中心负责人信息异常", e);
        }
        return resultData.toString();
    }

    /**
     * 根据ID 获取门店位置审批记录信息
     * @param Id
     * @return
     */
    @RequestMapping(value = "/getStoreLocationAuditById", method = RequestMethod.POST)
    public String getStoreLocationAuditById(@RequestBody Integer Id) {
        ResultData<StoreLocationAuditDto> resultData = new ResultData<StoreLocationAuditDto>();
        try{
            resultData = storeLocationAuditService.getStoreLocationAuditById(Id);
        }catch (Exception e){
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getStoreListData", "", null, "", "获取门店位置审批信息异常", e);
        }
        return resultData.toString();
    }

    /**
     * 更新门店位置
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/updateStoreLocation", method = RequestMethod.POST)
    public String updateStoreLocation(@RequestBody String jsonDto) {
        ResultData<StoreLocationAuditDto> resultData = new ResultData<StoreLocationAuditDto>();
        StoreLocationAuditDto dto = JsonUtil.parseToObject(jsonDto, StoreLocationAuditDto.class);
        try{
            int count = storeLocationAuditService.updateStoreLocation(dto);
            if(count<=0){
                resultData.setFail("门店重新定位失败！");
            }else{
                resultData.setSuccess("门店重新定位成功！");
                resultData.setReturnData(dto);
            }
        }catch (Exception e){
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "updateStoreLocation", "", null, "", "更新门店位置异常", e);
        }
        return resultData.toString();
    }


    /**
     * 审批通过 门店位置变更审批记录
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/updateStoreLocationAuditAndAudit", method = RequestMethod.POST)
    public String updateStoreLocationAuditAndAudit(@RequestBody String jsonDto) {
        ResultData<StoreLocationAuditDto> resultData = new ResultData<StoreLocationAuditDto>();
        StoreLocationAuditDto dto = JsonUtil.parseToObject(jsonDto, StoreLocationAuditDto.class);
        try{
            int count = storeLocationAuditService.updateStoreLocationAuditAndAudit(dto);
            if(count<=0){
                resultData.setFail("审批通过门店位置变更审批失败");
            }else{
                resultData.setReturnData(dto);
            }
        }catch (Exception e){
            resultData.setFail();
            logger.error("sweepStreetsController", "sweepStreetsController", "getStoreListData", "", null, "", "审批通过门店位置变更审批记录异常", e);
        }
        return resultData.toString();
    }


}
