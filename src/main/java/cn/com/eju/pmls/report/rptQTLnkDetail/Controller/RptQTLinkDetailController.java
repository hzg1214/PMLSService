package cn.com.eju.pmls.report.rptQTLnkDetail.Controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.rptQTLnkDetail.Service.RptQTLinkDetailService;
import cn.com.eju.pmls.report.rptQTLnkDetail.model.RptQTLnkDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "rptQTLinkDetail")
public class RptQTLinkDetailController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    RptQTLinkDetailService rptQTLinkDetailService;

    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        //构建返回
        ResultData<List<RptQTLnkDetail>> resultData = new ResultData<List<RptQTLnkDetail>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = rptQTLinkDetailService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("rptQTLnkDetail", "RptQTLinkDetailController", "queryList", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/export",method = RequestMethod.POST)
    public ResultData<String> export(@RequestBody String param){
        ResultData<String> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = rptQTLinkDetailService.export(reqMap);
        }catch (Exception e){
            resultData.setFail("导出异常！");
            logger.error("rptQTLnkDetail", "RptQTLinkDetailController",
                    "export", param, -1, "", "其他收入明细导出异常！", e);
        }
        return resultData;
    }
}
