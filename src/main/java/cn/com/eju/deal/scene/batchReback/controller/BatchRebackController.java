package cn.com.eju.deal.scene.batchReback.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.scene.batchReback.dto.BatchReback;
import cn.com.eju.deal.scene.batchReback.service.BatchRebackService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "batchRebackController")
public class BatchRebackController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private BatchRebackService batchRebackService;

    /**
     * 批量退房多少套
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/countBatchRebackDetail", method = RequestMethod.POST)
    public String countBatchRebackDetail(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Integer> resultData = new ResultData<>();
        try {
            Integer num = batchRebackService.countBatchRebackDetail(queryParam);
            resultData.setReturnMsg("查询成功");
            resultData.setReturnData(num);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("查询异常");
            logger.error("scene", "BatchRebackController", "countBatchRebackDetail", "", null, "", " 查询批量退房总数失败", e);
        }
        return resultData.toString();
    }

    /**
     * 新增批量退房
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/addBatchReback", method = RequestMethod.POST)
    public String addBatchReback(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Map<String, Object>> resultData = new ResultData<>();
        try {
            Map<String, Object> respCode = batchRebackService.addBatchReback(queryParam);
            resultData.setReturnMsg("新增成功");
            resultData.setReturnData(respCode);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("新增异常");
            logger.error("scene", "BatchRebackController", "addBatchReback", "", null, "", " 新增批量退房失败", e);
        }
        return resultData.toString();
    }

    /**
     * 查询批量退房列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getBatchRebackList", method = RequestMethod.POST)
    public String getBatchRebackList(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<BatchReback> resultData = new ResultData<>();
        try {
            BatchReback batchReback = batchRebackService.getBatchRebackList(queryParam);
            resultData.setReturnMsg("查询成功");
            resultData.setReturnData(batchReback);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("查询异常");
            logger.error("scene", "BatchRebackController", "getBatchRebackList", "", null, "", " 查询批量退房列表失败", e);
        }
        return resultData.toString();
    }


    /**
     * 修改批量退房信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateBatchRebackDetailAll", method = RequestMethod.POST)
    public String updateBatchRebackDetailAll(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<>();
        try {
            resultData = batchRebackService.updateBatchRebackDetailAll(param);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("保存异常");
            logger.error("scene", "BatchRebackController", "updateBatchRebackDetailAll", param, null, "", " 保存批量退房异常", e);
        }
        return resultData.toString();
    }

    /**
     * 删除怕批量退房子表信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/deleteBatchRebackDetailById", method = RequestMethod.POST)
    public String deleteBatchRebackDetailById(@RequestBody String param) {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<Integer> resultData = new ResultData<>();
        try {
            Integer num = batchRebackService.deleteBatchRebackDetailById(queryParam);
            resultData.setReturnMsg("删除成功");
            resultData.setReturnData(num);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("删除异常");
            logger.error("scene", "BatchRebackController", "deleteBatchRebackDetailById", "", null, "", " 删除批量退房子表信息失败", e);
        }
        return resultData.toString();
    }

    /**
     * 提交批量退房
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/submitBatchReback", method = RequestMethod.POST)
    public String submitBatchReback(@RequestBody String param) {
        ResultData<String> resultData = new ResultData<>();
        try {
            resultData = batchRebackService.submitBatchReback(param);
        } catch (Exception e) {
            resultData.setReturnCode("-1");
            resultData.setReturnMsg("批量退房异常");
            logger.error("scene", "BatchRebackController", "submitBatchReback", param, null, "", " 批量退房异常", e);
        }
        return resultData.toString();
    }


}
