package cn.com.eju.deal.base.code.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.code.service.WebConfigService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.util.WebConfigRSAUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
/**   
* 系统配置
* @author (li_xiaodong)
* @date 2016年3月20日 下午12:29:31
*/
@RestController
@RequestMapping(value = "webconfigs")
public class WebConfigController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "webConfigService")
    private WebConfigService webConfigService;
    
    public final static Long WEBCONFIG_TIME_INTERVAL = 300000L;
    /** 
     * 查询-对象
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAll(HttpServletRequest request)
    {
        Map<?, ?> queryParam = new HashMap<String, Object>();
        //查询
        ResultData<?> resultData = new ResultData<>();
        
        
        String paramKey = request.getParameter("paramKey");
        String plainTextKey = null;
        try {
        	plainTextKey = WebConfigRSAUtil.decrypt(paramKey);
		} catch (Exception e1) {
			logger.info("WebConfigController getAll 解密异常paramKey="+paramKey);
			resultData.setFail("解密异常");
            logger.error("base", "WebConfigController", "getAll", "", null, "", "解密异常paramKey="+paramKey, e1);
            return resultData.toString();
		}
        
        if(StringUtil.isEmpty(plainTextKey)){
        	logger.info("WebConfigController getAll 请求不合法！");
        	resultData.setFail("请求不合法！");
            return resultData.toString();
        }
        
        Date currDate = new Date();
        Long currDateNum = currDate.getTime();
        Long paramDateNum = Long.parseLong(plainTextKey);
        long interval = currDateNum - paramDateNum;
        if(interval > WEBCONFIG_TIME_INTERVAL){
        	logger.info("WebConfigController getAll 请求超时！");
        	resultData.setFail("请求超时！");
            return resultData.toString();
        }
        
        
        try
        {
            resultData = webConfigService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("base", "WebConfigController", "getAll", "", null, "", "", e);
        }
        
        return resultData.toString();
    }
    
}
