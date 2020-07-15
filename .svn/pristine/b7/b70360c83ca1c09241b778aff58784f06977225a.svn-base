package cn.com.eju.deal.houseLinkage.linkMarginDetail.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.linkMarginDetail.service.LinkMarginDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * desc:联动资金成本(保证金、诚意金)
 * @author :zhenggang.Huang
 * @date   :2019年9月12日
 */
@RestController
@RequestMapping(value = "linkMarginDetail")
public class LinkMarginDetailController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LinkMarginDetailService linkMarginDetailService;

    /**
     * desc:查询list
     * 2019年9月12日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "queryLinkMarginDetailList/{param}", method = RequestMethod.GET)
    public ResultData queryLinkMarginDetailList(@PathVariable String param) {
        //构建返回
        ResultData resultData = null;
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = linkMarginDetailService.queryLinkMarginDetailList(queryParam);
        } catch (Exception e) {
        	e.printStackTrace();
        	if(resultData == null){
                resultData = new ResultData();
            }
            logger.error("LinkMarginDetail", "LinkMarginDetailController", "queryLinkMarginDetailList", "", 0, "", "查询list异常", e);
            resultData.setFail();
        }
        return resultData;
    }
    
    /**
     * desc:成本中心列表
     * 2019年9月24日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/queryCostCenterList/{organization}", method = RequestMethod.GET)
    public String queryCostCenterList(@PathVariable String organization){
        ResultData resultData = new ResultData<>();
        List<Map<String,Object>> list = null;
        try {
            list = linkMarginDetailService.queryCostCenterList(organization);
        } catch (Exception e) {
            e.printStackTrace();
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("LinkMarginDetail", "LinkMarginDetailController", "queryCostCenterList", organization, null, "", "查询成本中心列表异常", e);
        }
        resultData.setReturnData(list);
        return resultData.toString();
    }
}
