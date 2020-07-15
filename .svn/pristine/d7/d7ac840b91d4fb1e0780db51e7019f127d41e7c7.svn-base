package cn.com.eju.deal.storeinformation.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.storeinformation.service.StoreInformationDetailService;

/**
 * desc: 门店信息明细
 * @author :zhenggang.Huang
 * @date   :2018年12月28日
 */
@RestController
@RequestMapping(value = "storeInformationDetail")
public class StoreInformationDetailController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private StoreInformationDetailService storeInformationDetailService;

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryInformationDetailList/{param}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData queryInformationDetailList(@PathVariable String param) {
        //构建返回
        ResultData resultData = null;
        try {
            Map<String,Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = storeInformationDetailService.queryInformationDetailList(queryParam);
        } catch (Exception e) {
        	if(resultData == null){
                resultData = new ResultData();
            }
            logger.error("storeInformationDetail", "StoreInformationDetailController", "queryInformationDetailList", "", 0, "", "查询list异常", e);
            resultData.setFail();
        }
        return resultData;
    }
}
