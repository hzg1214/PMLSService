package cn.com.eju.pmls.basicInformation.personnelPermissions.Controller;

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
import cn.com.eju.pmls.basicInformation.personnelPermissions.dto.UserCenterCityDto;
import cn.com.eju.pmls.basicInformation.personnelPermissions.service.PersonnelPermissionsService;

/**
 * desc:人员权限管理 date:2019-11-01
 */
@RestController
@RequestMapping(value = "personnelPermissions")
public class PersonnelPermissionsController extends BaseController {

	private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Resource(name = "personnelPermissionsService")
	private PersonnelPermissionsService personnelPermissionsService;

	/**
	 * desc:查询列表 2019年11月1日
	 */
	@RequestMapping(value = "/queryPersonnelPermissionsList/{param}", method = RequestMethod.GET)
	public String queryPersonnelPermissionsList(@PathVariable String param) {

		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		ResultData resultData = new ResultData();
		try {
			resultData = personnelPermissionsService.queryPersonnelPermissionsList(queryParam);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("personnelPermissions", "PersonnelPermissionsController", "queryPersonnelPermissionsList", "",
					null, "", "获取人员权限列表失败", e);
		}
		return resultData.toString();
	}

	//根据城市获取归属中心
	@RequestMapping(value = "/getCenterGroup/{param}", method = RequestMethod.GET)
    public String getCenterGroup(@PathVariable String param) {

        ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = personnelPermissionsService.getCenterGroupByCityNo(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("personnelPermissions", "PersonnelPermissionsController", "getCenterGroup", "", -1, "", "查询归属中心失败", e);
        }
        return resultData.toString();
    }
	
	//获取城市列表
	@RequestMapping(value = "/getCityList/{param}", method = RequestMethod.GET)
	public String getCityList(@PathVariable String param) {
		
		ResultData<List<Map<String, Object>>> resultData = new ResultData<>();
		// 获取请求参数
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		try {
			resultData = personnelPermissionsService.getCityList(queryParam);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("personnelPermissions", "PersonnelPermissionsController", "getCityList", "", -1, "", "查询归属中心失败", e);
		}
		return resultData.toString();
	}
	
    //新增人员信息
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	UserCenterCityDto dto = JsonUtil.parseToObject(json, UserCenterCityDto.class);
            resultData = personnelPermissionsService.addUser(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("personnelPermissions", "PersonnelPermissionsController", "queryPersonnelPermissionsList", "",
					null, "", "新增人员信息失败", e);
        }
        return resultData.toString();
    }
    
  //获取人员信息
    @RequestMapping(value = "/getUserInfo/{param}", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try{
            resultData = personnelPermissionsService.getUserInfo(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("personnelPermissions", "PersonnelPermissionsController", "getUserInfo", "", null, "", "获取人员信息", e);
        }
        return resultData.toString();
    }
    
    //修改人员信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	UserCenterCityDto dto = JsonUtil.parseToObject(json, UserCenterCityDto.class);
            resultData = personnelPermissionsService.updateUser(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("personnelPermissions", "PersonnelPermissionsController", "updateUser", "", null, "", "修改人员信息", e);
        }
        return resultData.toString();
    }
    
    //删除人员信息
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public String deleteUser(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        try{
        	UserCenterCityDto dto = JsonUtil.parseToObject(json, UserCenterCityDto.class);
            resultData = personnelPermissionsService.deleteUser(dto);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("personnelPermissions", "PersonnelPermissionsController", "deleteUser", "", null, "", "删除人员信息", e);
        }
        return resultData.toString();
    }
}
