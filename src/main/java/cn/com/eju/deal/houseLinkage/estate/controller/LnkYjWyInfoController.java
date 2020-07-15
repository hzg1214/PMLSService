package cn.com.eju.deal.houseLinkage.estate.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjWyInfo;
import cn.com.eju.deal.houseLinkage.estate.service.LnkYjWyInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "lnkYjWyInfo")
public class LnkYjWyInfoController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "lnkYjWyInfoService")
    private LnkYjWyInfoService lnkYjWyInfoService;


    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list() {
        //构建返回
        ResultData<List<LnkYjWyInfo>> resultData = new ResultData<List<LnkYjWyInfo>>();
        try {
            resultData = lnkYjWyInfoService.getWyInfoList();
        } catch (Exception e) {
            resultData.setFail();
            logger.error("查询-物业类型list异常", e);
            logger.error("houseLinkage", "LnkYjWyInfoController", "list",
                    "", null, "", "查询-物业类型list", e);
        }

        return resultData.toString();
    }

}
