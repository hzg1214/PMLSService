package cn.com.eju.deal.scene.commission.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.scene.commission.model.LnkYjReportInfo;
import cn.com.eju.deal.scene.commission.model.YjCompany;
import cn.com.eju.deal.scene.commission.service.LnkYjReportService;

/**
 * desc:返佣维护对象  服务层
 * @author :zhenggang.Huang
 * @date   :2019年4月29日
 */

@RestController
@RequestMapping(value = "lnkYjReport")
public class LnkYjReportController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "lnkYjReportService")
    private LnkYjReportService lnkYjReportService;

    /**
     * 查询垫佣LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLnkYjReportList/{param}",method = RequestMethod.GET)
    public String getLnkYjReportList(@PathVariable String param)
    {
    	//构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        List<Map<String,Object>> list = null;
        try {
            list = lnkYjReportService.getLnkYjReportList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("lnkYjReport", "LnkYjReportController", "getLnkYjReportList", "", null, "", "列表查询失败", e);
        }

        if(list !=null && list.size()>0)
        {
        	resultData.setReturnData(list);
        	resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
        }
        
        return resultData.toString();
    }
    
    /**
     * desc:查看
     * 2019年4月29日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getYjReportDeatilById/{param}", method = RequestMethod.GET)
    public String getYjReportDeatilById(@PathVariable String param){
        //构建返回
    	ResultData<LnkYjReportInfo> resultData = new ResultData<>();
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        LnkYjReportInfo lnkYjReportInfo = null;
        try{
        	lnkYjReportInfo = lnkYjReportService.getYjReportDeatilById(map);
        }catch (Exception e){
            resultData.setFail();
            logger.error("CRM", "LnkYjReportController", "getYjReportDeatilById", "", null, "", "", e);
        }
        if(!StringUtils.isEmpty(lnkYjReportInfo))
        {
        	resultData.setReturnData(lnkYjReportInfo);
        	resultData.setTotalCount((String)map.get(QueryConst.TOTAL_COUNT));
        }
        return resultData.toString();
    }
    /**
     * desc:保存返佣对象
     * 2019年4月30日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/saveMaintenance", method = RequestMethod.POST)
    public String saveMaintenance(@RequestBody String param){
    	//构建返回
    	ResultData resultData = null;
    	Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
    	
    	try{
    		resultData = lnkYjReportService.saveMaintenance(map);
    	}catch (Exception e){
    		if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("变更返佣维护对象异常！");
            }
    		logger.error("CRM", "LnkYjReportController", "getYjReportDeatilById", "", null, "", "", e);
    	}
    	return resultData.toString();
    }
    
    /**
     * desc:保存编辑后返佣对象
     * 2019年4月30日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/saveEditMaintenance", method = RequestMethod.POST)
    public String saveEditMaintenance(@RequestBody String param){
    	//构建返回
    	ResultData resultData = null;
    	Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
    	
    	try{
    		resultData = lnkYjReportService.saveEditMaintenance(map);
    	}catch (Exception e){
    		if(resultData == null){
    			resultData = new ResultData();
    			resultData.setFail("变更返佣维护对象异常！");
    		}
    		logger.error("CRM", "LnkYjReportController", "getYjReportDeatilById", "", null, "", "", e);
    	}
    	return resultData.toString();
    }

    /**
     * desc:模糊查询公司
     * 2019年5月5日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getCompanyByCondition/{param}", method = RequestMethod.GET)
    public String getCompanyByCondition(@PathVariable String param)
    {
        //构建返回
        ResultData<List<YjCompany>> resultData = new ResultData<List<YjCompany>>();
        try
        {
            Map<String,Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = lnkYjReportService.getCompanyByCondition(queryParam);
        }
        catch (Exception e)
        {
            logger.error("LnkYjReport", "LnkYjReportController", "getCompanyByCondition", "", 0, "", "模糊查询公司失败 ", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
}
