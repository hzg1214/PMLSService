package cn.com.eju.pmls.common.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.common.service.PmlsCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 服务层
 */

@RestController
@RequestMapping(value = "pmlsCommon")
public class PmlsCommonController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PmlsCommonService pmlsCommonService;

    @RequestMapping(value = "getCitySwitchMonth/{cityNo}", method = RequestMethod.GET)
    public String queryCityList(@PathVariable String cityNo) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pmlsCommonService.getCitySwitchMonth(cityNo);
        } catch (Exception e) {
            logger.error("获取城市最晚关账日期报错", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
}
