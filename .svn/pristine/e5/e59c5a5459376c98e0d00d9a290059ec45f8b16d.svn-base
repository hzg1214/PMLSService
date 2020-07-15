package cn.com.eju.deal.scene.statistic.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticCompanySearchResultDto;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticDetailSearchResultDto;
import cn.com.eju.deal.dto.scene.statistic.SceneStatisticEstateSearchResultDto;
import cn.com.eju.deal.scene.statistic.service.SceneStatisticEstateService;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.User;

/**   
* 服务层qSceneStatisticCompanyInit
* 
* @author qianwei
* @date 2016年3月22日 下午6:05:44
*/

@RestController
@RequestMapping(value = "sceneStatisticEstate")
public class SceneStatisticEstateController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneStatisticEstateService")
    private SceneStatisticEstateService sceneStatisticEstateService;
    
    @Resource
    private UserAPIImpl userAPIImpl;
    
    /** 
    * 查询-统计楼盘list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/qSceneStatisticEstate/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<SceneStatisticEstateSearchResultDto>> resultData = new ResultData<List<SceneStatisticEstateSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = sceneStatisticEstateService.querySceneStatisticEstateList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneStatisticEstateController", "list", "", null, "", "", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 查询-统计公司list
     * @param param 查询条件
     * @return
     */
     @RequestMapping(value = "/qSceneStatisticCompany/{param}", method = RequestMethod.GET)
     public String querySceneStatisticCompanyList(@PathVariable String param)
     {
         //构建返回
         ResultData<List<SceneStatisticCompanySearchResultDto>> resultData = new ResultData<List<SceneStatisticCompanySearchResultDto>>();
         
         Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
         
         //权限控制,参数转换
         authParam(queryParam);
         
         try
        {
            resultData = sceneStatisticEstateService.querySceneStatisticCompanyList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneStatisticEstateController", "querySceneStatisticCompanyList", "", null, "", "", e);
        }
         
         return resultData.toString();
     }
     
     /** 
      * 查询-统计明细list
      * @param param 查询条件
      * @return
      */
      @RequestMapping(value = "/qSceneStatisticDetail/{param}", method = RequestMethod.GET)
      public String querySceneStatisticDetailList(@PathVariable String param)
      {
          //构建返回
          ResultData<List<SceneStatisticDetailSearchResultDto>> resultData = new ResultData<List<SceneStatisticDetailSearchResultDto>>();
          
          Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
          
          //权限控制,参数转换
          authParam(queryParam);
          
          try
        {
            resultData = sceneStatisticEstateService.querySceneStatisticDetailList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneStatisticEstateController", "querySceneStatisticDetailList", "", null, "", "", e);
        }
          
          return resultData.toString();
      }
    
    /** 
     * 查询部门列表
     * @param param 查询条件
     * @return
     */
     @RequestMapping(value = "/groupId/{groupId}", method = RequestMethod.GET)
     public String getGroupList(@PathVariable String groupId)
     {
         //构建返回
    	 ResultData<List<Group>> groupResultData = new ResultData<List<Group>>();
        try
        {
            groupResultData = this.userAPIImpl.selectAllChildrenByGroupId(Integer.valueOf(groupId));
        }
        catch (Exception e)
        {
            groupResultData.setFail();
            logger.error("statistic", "SceneStatisticController", "getGroupList", "", null, "", "", e);
        }
         //ResultData<List<User>> userResultData= this.userAPIImpl.getUserByGroupId(1, Integer.valueOf(groupId));
         return groupResultData.toString();
     }
     
     /** 
      * 查询员工列表
      * @param param 查询条件
      * @return
      */
      @RequestMapping(value = "/user/{groupId}", method = RequestMethod.GET)
      public String getUserList(@PathVariable String groupId)
      {
          //构建返回
     	  ResultData<List<User>> userResultData = new ResultData<List<User>>();
        try
        {
            userResultData = this.userAPIImpl.getUserByGroupId(1, Integer.valueOf(groupId));
        }
        catch (NumberFormatException e)
        {
            userResultData.setFail();
            logger.error("scene", "SceneStatisticEstateController", "getUserList", "", null, "", "", e);
        }
          return userResultData.toString();
      }
      /** 
       * 查询楼盘名
       * @param param 查询条件
       * @return
       */
       @RequestMapping(value = "/estateId/{estateId}", method = RequestMethod.GET)
       public String getByEstateId(@PathVariable String estateId)
       {
    	   ResultData<String> resultData = new ResultData<>();
	   		try {
	   			resultData = this.sceneStatisticEstateService.getByEstateId(estateId);
	   		} catch (Exception e) {
	   		    resultData.setFail();
	   		    logger.error("scene", "SceneStatisticEstateController", "getUserList", "", null, "", "查询楼盘名失败", e);
	   		}
	   		return resultData.toString();
       }
}
