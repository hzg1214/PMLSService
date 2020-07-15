package cn.com.eju.pmls.report.frontPanel.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.frontPanel.dto.FrontPanelDto;
import cn.com.eju.pmls.report.frontPanel.service.FrontPanelService;

@RestController
@RequestMapping(value = "frontPanelController")
public class FrontPanelController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private FrontPanelService frontPanelService;

    @RequestMapping(value = "/query/{param}", method = RequestMethod.GET)
    public String query(@PathVariable String param) {
        ResultData<FrontPanelDto> resultData = new ResultData<FrontPanelDto>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = frontPanelService.query(queryParam);
        } catch (Exception e) {
            logger.error("frontPanel.query 发生异常 param" + param, e);
            resultData.setFail();
            logger.error("frontPanel", "FrontPanelController", "query", param,
                    null, "", "", e);
        }
        return resultData.toString();
    }
}
