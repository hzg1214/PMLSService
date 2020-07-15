package cn.com.eju.pmls.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.pmls.commission.dto.CommissionImportDto;
import cn.com.eju.pmls.commission.service.CommissionService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 服务层
 */

@RestController
@RequestMapping(value = "commission")
public class CommissionController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private CommissionService commissionService;

    @RequestMapping(value = "queryCityList/{param}", method = RequestMethod.GET)
    public String queryCityList(@PathVariable String param) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);

            resultData = commissionService.queryCityList(queryParam);
        } catch (Exception e) {
            logger.error("查询城市异常", e);

            resultData.setFail();
        }

        return resultData.toString();
    }

    @RequestMapping(value = "queryBusinessTypeList/{param}", method = RequestMethod.GET)
    public String queryBusinessTypeList(@PathVariable String param) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);

            resultData = commissionService.queryBusinessTypeList(queryParam);
        } catch (Exception e) {
            logger.error("查询业务类型异常", e);

            resultData.setFail();
        }

        return resultData.toString();
    }

    @RequestMapping(value = "checkAccount", method = RequestMethod.POST)
    public String checkAccount(@RequestBody String jsonDto) {
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = commissionService.checkAccount(JsonUtil.parseToObject(jsonDto, CommissionImportDto.class));
        } catch (Exception e) {
            logger.error("Commission", "CommissionController", "checkAccount", "", 0, "", "", e);
            resultData.setFail();
        }

        return resultData.toString();
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(@RequestBody String jsonDto) {
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = commissionService.add(JsonUtil.parseToObject(jsonDto, CommissionImportDto.class));
        } catch (Exception e) {
            logger.error("Commission", "CommissionController", "add", "", 0, "", "导入异常", e);
            resultData.setFail();
        }

        return resultData.toString();
    }

    /**
     * 查询-list
     *
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        //构建返回
        ResultData<List<CommissionImportDto>> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);

            resultData = commissionService.queryList(queryParam);
        } catch (Exception e) {
            logger.error("Commission", "CommissionController", "queryList", "", 0, "", "查询list异常", e);

            resultData.setFail();
        }

        return resultData.toString();
    }
}
