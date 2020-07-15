package cn.com.eju.deal.scene.commission.controller;

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
import cn.com.eju.deal.dto.scene.commission.SceneCommissionDetailSearchResultDto;
import cn.com.eju.deal.dto.scene.commission.SceneCommissionSearchResultDto;
import cn.com.eju.deal.scene.commission.service.SceneCommissionService;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.Group;
import cn.com.eju.deal.user.model.User;

/**   
* 服务层
* @author qianwei
* @date 2016年3月22日 下午6:05:44
*/

@RestController
@RequestMapping(value = "sceneCommission")
public class SceneCommissionController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sceneCommissionService")
    private SceneCommissionService sceneCommissionService;
    
    @Resource
    private UserAPIImpl userAPIImpl;
    
    /** 
    * 查询-佣金楼盘list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/qSceneCommission/{param}", method = RequestMethod.GET)
    public String querySceneCommissionist(@PathVariable String param)
    {
        //构建返回
        ResultData<List<SceneCommissionSearchResultDto>> resultData = new ResultData<List<SceneCommissionSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = sceneCommissionService.querySceneCommissionList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneCommissionController.java", "querySceneCommissionist", "", null, "", "查询-佣金楼盘list", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 查询-佣金明细list
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/qSceneCommissionDetail/{param}", method = RequestMethod.GET)
    public String querySceneCommissionDetailList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<SceneCommissionDetailSearchResultDto>> resultData = new ResultData<List<SceneCommissionDetailSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = sceneCommissionService.querySceneCommissionDetailList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneCommissionController", "querySceneCommissionDetailList", "", null, "", " 查询-佣金明细list", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 修改佣金list
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/qSceneCommissionDetail/modify/{param}", method = RequestMethod.GET)
    public String sceneCommissionDetailModify(@PathVariable String param)
    {
        
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        ResultData<Integer> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneCommissionService.sceneCommissionDetailModify(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneCommissionController", "sceneCommissionDetailModify", "", null, "", " 修改佣金失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * 确认结算list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/qSceneCommissionDetail/confirm/{param}", method = RequestMethod.GET)
    public String sceneCommissionDetailConfirm(@PathVariable String param)
    {
        
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        ResultData<Integer> resultData = new ResultData<>();
        try
        {
            resultData = this.sceneCommissionService.sceneCommissionDetailConfirm(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneCommissionController", "sceneCommissionDetailConfirm", "", null, "", "修改佣金失败", e);
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
            logger.error("scene", "SceneCommissionController", "getGroupList", "", null, "", "查询部门列表", e);
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
        catch (Exception e)
        {
            userResultData.setFail();
            logger.error("scene", "SceneCommissionController", "getUserList", "", null, "", "查询员工列表", e);
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
        try
        {
            resultData = this.sceneCommissionService.getByEstateId(estateId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneCommissionController", "getByEstateId", "", null, "", "查询楼盘名失败", e);
        }
        return resultData.toString();
    }
}
