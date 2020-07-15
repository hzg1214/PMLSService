package cn.com.eju.deal.storeStructure.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.storeStructure.StoreStructureDto;
import cn.com.eju.deal.storeStructure.service.StoreStructureService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * CRM门店结构报表
 */
@RestController
@RequestMapping(value = "storeStructureController")
public class StoreStructureController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreStructureService storeStructureService;

    /**
     * 获取门店结构报表
     *
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "queryStoreStructure/{param}", method = RequestMethod.GET)
    public String queryStoreStructure(@PathVariable String param) {
        // 获取请求参数
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreStructureDto>> resultData = new ResultData<>();
        try {
            resultData = storeStructureService.queryStoreStructure(queryParam);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("storeStructureReportController", "storeStructureReportController", "getStoreStructure", "", null, "", "获取门店结构报表异常", e);
        }
        return resultData.toString();
    }

}
