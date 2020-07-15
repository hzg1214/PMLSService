package cn.com.eju.deal.companyInfoDetail.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.companyInfoDetail.service.CompanyInfoDetailService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;

/**
* @Description: 公司信息明细表
 */
@RestController
@RequestMapping(value = "companyInfoDetail")
public class CompanyInfoDetailController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private CompanyInfoDetailService companyInfoDetailService;
    /**
     * 查询公司信息明细列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryCompanyInfoDetailList/{param}", method = RequestMethod.GET)
    public ResultData queryCompanyInfoDetailList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = companyInfoDetailService.queryCompanyInfoDetailList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "CompanyInfoDetailController", "queryCompanyInfoDetailList", reqMap.toString(), null, "", "查询保证金明细异常", e);
        }
        return resultData;
    }
    
}
  