package cn.com.eju.deal.scene.inCommission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.scene.inCommission.InCommissionLogDto;
import cn.com.eju.deal.dto.scene.inCommission.InCommissionResultDto;
import cn.com.eju.deal.scene.inCommission.service.SceneInCommissionService;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**   
* 服务层
* @author (sucen)
* @date 2017年8月7日 下午9:25:30
*/

@RestController
@RequestMapping(value = "sceneInCommission")
public class SceneInCommissionController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneInCommissionService")
    private SceneInCommissionService sceneInCommissionService;
    
    @Resource
    private UserAPIImpl userAPIImpl;

    /**
     * 查询内佣LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getInCommissionList/{param}",method = RequestMethod.GET)
    public String getInCommissionList(@PathVariable String param)
    {
    	//构建返回
        ResultData<List<InCommissionResultDto>> resultData = new ResultData<List<InCommissionResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        List<InCommissionResultDto> list = sceneInCommissionService.getInCommissionList(queryParam);
        
        if(!list.isEmpty())
        {
        	resultData.setReturnData(list);
        	resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
        }
        
        return resultData.toString();
    }
    
    
    /** 
     * 导入新增
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/imputAdd", method = RequestMethod.POST)
    public String insertLnkImport(@RequestBody String jsonDto)
    {
    	ResultData<String> resultData = new ResultData<>();
        Map<?, ?> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
        	resultData = this.sceneInCommissionService.insertLnkImport(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneInCommissionController", "getByEstateId", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }
    
    /** 
     * 导入更新
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/imputUp", method = RequestMethod.POST)
    public String updateLnkImport(@RequestBody String jsonDto)
    {
    	ResultData<String> resultData = new ResultData<>();
    	Map<?, ?> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);
        try
        {
        	resultData = this.sceneInCommissionService.updateLnkImport(queryParam);
        	Object needUpdateBefore = queryParam.get("needUpdateBefore");
        	if (needUpdateBefore instanceof Boolean && (Boolean)needUpdateBefore){
        		sceneInCommissionService.updateLnkImportBefore(queryParam);
        	}
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneInCommissionController", "getByEstateId", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }

    /** 
     * 根据报备ID查询数据
     * @param param 查询条件
     * @return
     */
     @RequestMapping(value = "/byReportId/{param}", method = RequestMethod.GET)
     public String getLnkImportByReportId(@PathVariable String param)
     {
         //构建返回
         ResultData<Map<?,?>> resultData = new ResultData<Map<?,?>>();
         Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
         try
         {
             resultData = sceneInCommissionService.getLnkImportByReportId(queryParam);
         }
         catch (Exception e)
         {
             resultData.setFail();
             logger.error("scene", "SceneCommissionController.java", "getLnkImportByReportId", "", null, "", "根据报备ID查询数据失败!", e);
         }
         
         return resultData.toString();
     }

     /**
      * 导入，写入log日志
      * @return
      */
     @RequestMapping(value = "/imputLog/{param}", method = RequestMethod.GET)
     public ResultData<Integer> createLogLnkImport(@PathVariable String param)
     {
    	//构建返回
    	 ResultData<Integer> resultData = new ResultData<Integer>();
         Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
         try
         {
        	 int result = sceneInCommissionService.createLogLnkImport(queryParam);
             resultData.setReturnData(result);
         }
         catch (Exception e)
         {
        	 resultData.setFail();
             logger.error("scene", "SceneCommissionController.java", "getLnkImportByReportId", "", null, "", "导入时写入log日志失败!", e);
         }
         
         return resultData;
     }
     
    /*
     * 获得佣金详情列表，并提交日志
     * 仅用于导出佣金详情，不提交日志的请求请使用getInCommissionList
     * created by wang kanlin 2017/8/15
     */
	@RequestMapping(value = "queryCommisionList/{param}", method = RequestMethod.GET)
	public String getInCommissionListForExport(
			@PathVariable String param) {
		// 获取请求参数
		@SuppressWarnings("unchecked")
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		InCommissionLogDto icLogDto = new InCommissionLogDto();//JsonUtil.parseToObject(param, InCommissionLogDto.class);
		icLogDto.setOperType("2");
		icLogDto.setOperUserId(Integer.valueOf(queryParam.get("operUserId").toString()));
		icLogDto.setOperUserName(queryParam.get("operUserName").toString());
		icLogDto.setOperDt(new Date());
	  	String year = queryParam.get("countDateStart").toString();
	  	if (year != null && year.length()>=4) year = year.substring(0, 4);
		icLogDto.setYear(Integer.valueOf(year));
		icLogDto.setFileName(queryParam.get("fileName").toString());
		icLogDto.setRemarks("");
		
		ResultData<List<InCommissionResultDto>> resultData = new ResultData<List<InCommissionResultDto>>();
		
		List<InCommissionResultDto> list= sceneInCommissionService.getInCommissionListForExport(queryParam, icLogDto);
		if(list != null)
		{
			resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
			resultData.setReturnData(list);
		}
		return resultData.toString();
	}


    /*
     * 获得对应城市关账年月
     * created by wang kanlin 2017/8/15
     */
	@RequestMapping(value = "queryCommisionSwitchMonth/{param}", method = RequestMethod.GET)
	public String getInCommissionSwitchMonth(@PathVariable String param) {
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		ResultData<Map<String, String>> resultData = new ResultData<Map<String, String>>();
		Map<String, String> switchMonth = sceneInCommissionService.getInCommissionSwitchMonth(queryParam);
		resultData.setReturnData(switchMonth);
		return resultData.toString();
	}
}
