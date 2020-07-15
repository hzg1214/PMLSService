package cn.com.eju.deal.houseLinkage.statistic.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.code.model.Code;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticCompanySearchResultDto;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticDetailSearchResultDto;
import cn.com.eju.deal.dto.houseLinkage.statistic.StatisticSearchResultDto;
import cn.com.eju.deal.houseLinkage.statistic.service.StatisticService;

/**   
* 服务层
* @author qianwei
* @date 2016年3月22日 下午6:05:44
*/

@RestController
@RequestMapping(value = "statistic")
public class StatisticController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "statisticService")
    private StatisticService statisticService;
    
    /** 
     * 获取日期类型列表
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/dateTypeKbn/{dateTypeKbn}", method = RequestMethod.GET)
    public String getDateKbnList(@PathVariable String dateTypeKbn)
    {
        //构建返回
        ResultData<List<Code>> dateKbnResultData = new ResultData<>();
        List<Code> codeDtos = SystemParam.getCodeListByKey(dateTypeKbn);
        dateKbnResultData.setReturnData(codeDtos);
        return dateKbnResultData.toString();
    }
    
    /** 
    * 查询-list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<StatisticSearchResultDto>> resultData = new ResultData<List<StatisticSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = statisticService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("houseLinkagew", "StatisticController", "list", "", null, "", "查询-list", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 查询-公司统计list
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/qCompany/{param}", method = RequestMethod.GET)
    public String companyList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<StatisticCompanySearchResultDto>> resultData =
            new ResultData<List<StatisticCompanySearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = statisticService.queryCompanyList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("houseLinkagew", "StatisticController", "companyList", "", null, "", "查询-公司统计list", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 查询-统计明细list
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/qStatisticDetail/{param}", method = RequestMethod.GET)
    public String statisticDetailList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<StatisticDetailSearchResultDto>> resultData =
            new ResultData<List<StatisticDetailSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = statisticService.queryStatisticDetailList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("houseLinkagew", "StatisticController", "statisticDetailList", "", null, "", "查询-统计明细list", e);
        }
        
        return resultData.toString();
    }
    
}
