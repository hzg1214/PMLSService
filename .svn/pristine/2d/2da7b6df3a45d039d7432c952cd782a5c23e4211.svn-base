package cn.com.eju.deal.houseLinkage.lnkAchievementSum.controller;

import java.net.URLDecoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.houseLinkage.lnkAchievementSum.service.LnkAchievementSumService;

/**
* @Title: LnkAchievementSumController
* @Description: 联动业绩汇总
 */
@RestController
@RequestMapping(value = "lnkAchievementSum")
public class LnkAchievementSumController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private LnkAchievementSumService lnkAchievementSumService;
    /**
     * 查询保证金明细列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryLnkAchievementSumList/{param}", method = RequestMethod.GET)
    public ResultData queryLnkAchievementSumList(@PathVariable String param, HttpServletRequest request){
        ResultData resultData = null;
        Map<String, Object> reqMap = null;
        try {
        	param = URLDecoder.decode(request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1),"utf-8");
        	reqMap = JsonUtil.parseToObject(param, Map.class);
            resultData = lnkAchievementSumService.queryLnkAchievementSumList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("lnkAchievementSum", "LnkAchievementSumController", "queryLnkAchievementSumList", reqMap.toString(), null, "", "查询联动业绩汇总列表异常", e);
        }
        return resultData;
    }
    
    
}
  