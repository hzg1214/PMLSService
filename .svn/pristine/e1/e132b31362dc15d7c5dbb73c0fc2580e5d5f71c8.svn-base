package cn.com.eju.deal.scene.settleconfirm.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.dto.scene.padCommission.PadCommissionResultDto;
import cn.com.eju.deal.scene.settleconfirm.service.SettleConfirmService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by yinkun on 2018/5/3.
 */
@RestController
@RequestMapping(value = "settleConfirm")
public class SettleConfirmController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name="settleConfirmService")
    private SettleConfirmService settleConfirmService;

    /**
     * 查询结算确认列表
     * @param param
     * @return
     */
    @RequestMapping(value="/getSettleConfirmList/{param}",method = RequestMethod.GET)
    public String getSettleConfirmList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<PadCommissionResultDto>> resultData = new ResultData<>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        List<PadCommissionResultDto> list = null;
        try {
            list = settleConfirmService.getSettleConfirmList(queryParam);
            if(!list.isEmpty())
            {
                resultData.setReturnData(list);
                resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
            }
        } catch (Exception e) {
            logger.error("settleConfirm", "SettleConfirmController", "getSettleConfirmList", "", null, "", "查询结算确认列表失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value="/batchUpdateSettleConfirmDate",method = RequestMethod.POST)
    public String batchUpdateSettleConfirmDate(@RequestBody String param){

        ReportInfoDto reportInfoDto = JsonUtil.parseToObject(param, ReportInfoDto.class);

        ResultData resultData = settleConfirmService.batchUpdateSettleConfirmDate(reportInfoDto.getReportDtoList());

        return resultData.toString();
    }
}
