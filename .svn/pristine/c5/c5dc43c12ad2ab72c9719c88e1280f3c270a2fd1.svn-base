package cn.com.eju.deal.open.controller;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.open.service.APIOaService;

/**
 * 
* 对OA接口
* @author wushuang
* @date 2016年10月20日 下午2:45:08
 */
@RestController
@RequestMapping(value = "crm/oa")
public class APIOaController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private APIOaService  apiOaService;
    
    /**
     * 
    * OA审核后调用门店业绩撤销接口
    * @param param
    * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "cancel/approvestate", method = RequestMethod.POST)
    public String updateCancelState(@RequestBody String param)
    {
        //构建返回
        ResultData<String> resultData = new ResultData<>();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try
        {
            
            //验证数据
//            String remarks = (String)queryParam.get("remarks");
//            if(StringUtils.isBlank(remarks)){
//                resultData.setFail("备注不能为空！");
//                return resultData.toString();
//            }
            String achievementCancelNo = (String)queryParam.get("achievementCancelNo");
            if(StringUtils.isBlank(achievementCancelNo)){
                resultData.setFail("撤销编号不能为空！");
                return resultData.toString();
            }
            String approveState = (String)queryParam.get("approveState");
            if(StringUtils.isBlank(achievementCancelNo)){
                resultData.setFail("审核状态不能为空！");
                return resultData.toString();
            }
            
            //审核时间
            queryParam.put("approveDate",new Date());
            //更新
            resultData = apiOaService.updateCancelState(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail("OA审核后调用接口更新状态失败！");
            logger.error("open", "APIOaController", "updateCancelState", "", 1, "", "OA审核后调用接口更新状态失败！queryParam = " + queryParam.toString(), e);
        }
        
        return resultData.toString();
    }
}
