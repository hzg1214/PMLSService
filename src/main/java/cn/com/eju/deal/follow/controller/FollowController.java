/**   
* @Title: FollowController.java 
* @Package cn.com.eju.deal.follow.controller 
* @Description: 跟进Controller包
* @author 陆海丹 
* @date 2016年3月24日 下午1:57:43 
* @version V1.0   
*/
package cn.com.eju.deal.follow.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.follow.FollowDto;
import cn.com.eju.deal.follow.service.FollowService;

/** 
* @ClassName: FollowController 
* @Description: 跟进Controller
* @author 陆海丹
* @date 2016年3月24日 下午1:57:43 
*  
*/
@RestController
@RequestMapping(value = "follow")
public class FollowController extends BaseController
{
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "followService")
    private FollowService followService;
    
    /** 
    * @Title: getById 
    * @Description: 根据跟进编号查询跟进详情
    * @param id
    * @return     
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id)
    {
        //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        try
        {
            resultData = this.followService.getStrById(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "getById", "", null, "", "根据跟进编号查询跟进详情", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: list 
    * @Description: 门店详情下的跟进列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        //构建返回
        ResultData<List<FollowDto>> resultData = new ResultData<List<FollowDto>>();
        try
        {
            resultData = this.followService.queryStrList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "list", "", null, "", "门店详情下的跟进列表失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: queryfollowstorelist 
    * @Description: 我的跟进门店列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/followstores/{param}", method = RequestMethod.GET)
    public String queryfollowstorelist(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<FollowDto>> resultData = new ResultData<List<FollowDto>>();
        try
        {
            resultData = this.followService.queryFollowStoreList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "queryfollowstorelist", "", null, "", "我的跟进门店列表", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: querymyfollows 
    * @Description: 我的跟进列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/myfollows/{param}", method = RequestMethod.GET)
    public String querymyfollows(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        //经度组装
        queryParam.put("latitude", queryParam.remove("prLatitude") + "." + queryParam.remove("suLatitude"));
        
        //纬度组装
        queryParam.put("longitude", queryParam.remove("prLongitude") + "." + queryParam.remove("suLongitude"));
        
        //构建返回
        ResultData<List<FollowDto>> resultData = new ResultData<List<FollowDto>>();
        try
        {
            resultData = this.followService.queryMyFollows(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "querymyfollows", "", null, "", "我的跟进列表", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: create 
    * @Description: 新增门店
    * @param jsonDto
    * @return     
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto)
    {
        FollowDto followDto = JsonUtil.parseToObject(jsonDto, FollowDto.class);
        //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        try
        {
            resultData = this.followService.createStr(followDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "create", "", null, "", "新增门店", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: update 
    * @Description: 更新门店信息
    * @param jsonDto
    * @return     
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String jsonDto)
    {
        FollowDto followDto = JsonUtil.parseToObject(jsonDto, FollowDto.class);
        //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        try
        {
            resultData = this.followService.updateStr(followDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "update", "", null, "", "更新门店信息失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除门店
    * @param id
    * @return     
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id)
    {
        //构建返回
        ResultData<FollowDto> resultData = new ResultData<FollowDto>();
        try
        {
            resultData = this.followService.deleteStr(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "delete", "", null, "", "删除门店失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: getCompanyBySelf 
    * @Description: 获取userCreate创建的门店编号为storeId的客户信息
    * @param param storeId userCreate
    * @return     
    */
    @RequestMapping(value = "/check/{param}", method = RequestMethod.GET)
    public String getCompanyBySelf(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.followService.getCompanyBySelf(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("follow", "FollowController", "getCompanyBySelf", "", 0, "", "获取userCreate创建的门店编号为storeId的客户信息", e);
        }
        return resultData.toString();
    }
    
    
    @RequestMapping(value = "/getFollowViewById/{param}", method = RequestMethod.GET)
    public String getFollowViewById(@PathVariable String param) {
        Map<String, Object> qparam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<FollowDto> resultData  = new ResultData<FollowDto>();
        try {
            resultData = followService.getFollowViewById(qparam);
        } catch (Exception e) {
            logger.error("查询门店跟进详情异常", e);
            resultData.setFail();
            logger.error("follow", "FollowController", "getFollowViewById", "", null, "", "", e);
        }
        return resultData.toString();
    }
}
