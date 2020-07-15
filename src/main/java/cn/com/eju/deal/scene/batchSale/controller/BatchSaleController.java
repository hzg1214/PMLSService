package cn.com.eju.deal.scene.batchSale.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.scene.batchSale.dto.BatchSale;
import cn.com.eju.deal.scene.batchSale.dto.BatchSaleDetail;
import cn.com.eju.deal.scene.batchSale.service.BatchSaleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "batchSaleController")
public class BatchSaleController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private BatchSaleService batchSaleService;

    /**
     * 批量成销多少套
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/countBatchSaleDetail", method = RequestMethod.POST)
    public String countBatchSaleDetail(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Integer> resultData = new ResultData<>();
        try {
            Integer num = batchSaleService.countBatchSaleDetial(queryParam);
            resultData.setReturnData(num);
        } catch (Exception e) {
            resultData.setFail("查询异常");
            logger.error("scene", "batchSaleController", "countBatchSaleDetail", "", null, "", " 查询批量成销总数失败", e);
        }
        return resultData.toString();
    }

    /**
     * 新增批量成销
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/addBatchSaleDetail", method = RequestMethod.POST)
    public String addBatchSaleDetail(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Map<String, Object>> resultData = new ResultData<>();
        try {
            Map<String, Object> respCode = batchSaleService.addBatchSaleDetail(queryParam);
            resultData.setReturnData(respCode);
        } catch (Exception e) {
            resultData.setFail("添加异常！");
            logger.error("scene", "batchSaleController", "addBatchSaleDetail", "", null, "", " 新增批量成销失败", e);
        }
        return resultData.toString();
    }

    /**
     * 查询批量成销列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getBatchSale", method = RequestMethod.POST)
    public String getBatchSale(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<BatchSale> resultData = new ResultData<>();
        try {
            BatchSale batchSale = batchSaleService.getBatchSale(queryParam);
            resultData.setReturnData(batchSale);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("查询异常");
            logger.error("scene", "batchSaleController", "getBatchSaleDetailList", "", null, "", " 查询批量成销失败", e);
        }
        return resultData.toString();
    }


    @RequestMapping(value = "/getBatchSaleDetailList/{batchId}", method = RequestMethod.GET)
    public String getBatchSaleDetailList(@PathVariable("batchId") Integer batchId) {

        ResultData<List<BatchSaleDetail>> resultData = new ResultData<>();
        try {
            List<BatchSaleDetail> batchSaleDetails = batchSaleService.getBatchSaleDetailList(batchId);
            resultData.setReturnData(batchSaleDetails);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("查询异常");
            logger.error("scene", "batchSaleController", "getBatchSaleDetailList", "", null, "", " 查询批量成销失败", e);
        }

        return resultData.toString();
    }

    /**
     * 修改批量成销信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateBatchSaleDetailAll", method = RequestMethod.POST)
    public String updateBatchSaleDetailAll(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<>();
        try {
            resultData = batchSaleService.updateBatchSaleDetailAll(param);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("保存异常");
            logger.error("scene", "batchSaleController", "updateBatchSaleDetailAll", "", null, "", " 修改批量成销异常", e);
        }
        return resultData.toString();
    }

    /**
     * 删除怕批量成销子表信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/deleteBatchSaleDetailById", method = RequestMethod.POST)
    public String deleteBatchSaleDetailById(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Integer> resultData = new ResultData<>();
        try {
            Integer num = batchSaleService.deleteBatchSaleDetailById(queryParam);
            resultData.setReturnCode("0000");
            resultData.setReturnMsg("删除成功");
            resultData.setReturnData(num);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("删除异常");
            logger.error("scene", "batchSaleController", "deleteBatchSaleDetailById", "", null, "", " 删除批量成销子表信息失败", e);
        }
        return resultData.toString();
    }

    /**
     * 提交批量成销
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/submitBatchSaleAll", method = RequestMethod.POST)
    public String submitBatchSaleAll(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<>();
        try {
            resultData = batchSaleService.submitBatchSaleAll(param);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("批量成销异常");
            logger.error("scene", "batchSaleController", "submitBatchSaleAll", "", null, "", " 批量成销异常", e);
        }
        return resultData.toString();
    }

    /**
     * 修改批量成销信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkAccountProject", method = RequestMethod.POST)
    public String checkAccountProject(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<>();
        try {
            resultData = batchSaleService.checkAccountProject(param);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("查询异常");
            logger.error("scene", "batchSaleController", "CheckAccountProject", "", null, "", " 校验核算主体异常", e);
        }
        return resultData.toString();
    }

}
