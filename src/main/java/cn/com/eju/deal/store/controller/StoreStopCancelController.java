package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.store.model.StoreStopCancel;
import cn.com.eju.deal.store.service.StoreStopCancelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("storeStopCancel")
public class StoreStopCancelController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private StoreStopCancelService storeStopCancelService;

    @RequestMapping(value = "storeStopCancelAdd", method = RequestMethod.POST)
    public String storeStopCancelAdd(@RequestBody String jsonDto){
        StoreStopCancel storeStopCancel = JsonUtil.parseToObject(jsonDto, StoreStopCancel.class);
        //构建返回
        ResultData<String> resultData = new ResultData<>();
        try
        {
            resultData = this.storeStopCancelService.storeStopCancelAdd(storeStopCancel);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("StoreStopCancel", "StoreStopCancelController", "storeStopCancelAdd", jsonDto, null, "", "门店停业撤销保存异常", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/list/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param) {
        ResultData<List<StoreStopCancel>> resultData = new ResultData<>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeStopCancelService.queryList(queryParam);
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
        }catch(Exception e) {
            logger.error("StoreStopCancel", "StoreStopCancelController", "list", queryParam.toString(), 0, "", "查询停业撤销列表异常", e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Integer id) {
        ResultData<StoreStopCancel> resultData = new ResultData<>();

        try {
            StoreStopCancel bean = storeStopCancelService.getByStopCancelId(id);
            resultData.setReturnData(bean);
        }catch(Exception e) {
            logger.error("StoreStopCancel", "StoreStopCancelController", "view", "id="+id, 0, "", "查看停业撤销明细异常", e);
            resultData.setFail();
        }

        return resultData.toString();
    }

    @RequestMapping(value="/reject",method = RequestMethod.POST)
    public String rejectStopCancel(@RequestBody String param) {

        ResultData<StoreStopCancel> result = new ResultData<>();
        StoreStopCancel storeStopCancel = JsonUtil.parseToObject(param,StoreStopCancel.class);

        try {
            result = storeStopCancelService.rejectStopCancel(storeStopCancel);
        }catch (Exception e) {
            logger.error("StoreStopCancel", "StoreStopCancelController", "rejectStop", param, 0, "", "停业撤销驳回异常", e);
            result.setFail();
        }

        return result.toString();
    }

    @RequestMapping(value="/pass",method = RequestMethod.POST)
    public String auditPass(@RequestBody String param) {

        ResultData<String> result = new ResultData<>();
        StoreStopCancel storeStopCancel = JsonUtil.parseToObject(param,StoreStopCancel.class);

        try {
            result = storeStopCancelService.auditPass(storeStopCancel);
        }catch (Exception e) {
            logger.error("StoreStopCancel", "StoreStopCancelController", "auditPass", param, 0, "", "停业撤销审核通过异常", e);
            result.setFail();
        }

        return result.toString();
    }
}
