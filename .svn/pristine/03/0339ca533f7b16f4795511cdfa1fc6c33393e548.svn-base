package cn.com.eju.deal.houseLinkage.linkZjcbDetail.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.linkZjcbDetail.service.LinkZjcbDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * desc:联动明细(资金成本)
 * @author :zhenggang.Huang
 * @date   :2019年7月18日
 */
@RestController
@RequestMapping(value = "linkZjcbDetail")
public class LinkZjcbDetailController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LinkZjcbDetailService linkZjcbDetailService;

    /**
     * desc:查询list
     * 2019年7月22日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "queryLinkZjcbDetailList/{param}", method = RequestMethod.GET)
    public ResultData queryLinkZjcbDetailList(@PathVariable String param) {
        //构建返回
        ResultData resultData = null;
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = linkZjcbDetailService.queryLinkZjcbDetailList(queryParam);
        } catch (Exception e) {
        	if(resultData == null){
                resultData = new ResultData();
            }
            logger.error("LinkZjcbDetail", "LinkZjcbDetailController", "queryLinkZjcbDetailList", "", 0, "", "查询list异常", e);
            resultData.setFail();
        }
        return resultData;
    }
}
