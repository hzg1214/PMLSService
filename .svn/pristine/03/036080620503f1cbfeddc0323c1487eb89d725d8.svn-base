package cn.com.eju.deal.permission.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.permission.model.PermissionDto;
import cn.com.eju.deal.permission.service.PermissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "permission")
public class PermissionController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    PermissionService permissionService;

    @RequestMapping(value = "/getPermissionList/{param}/", method = RequestMethod.GET)
    public String getPermissionList(@PathVariable("param") String param)
    {
        //构建返回
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<PermissionDto>> resultData = new ResultData<>();
        try
        {
            resultData = this.permissionService.getPermissionList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("permission", "PermissionController", "getPermissionList", "", null, "", "数据列表", e);
        }
        return resultData.toString();
    }


    @RequestMapping(value = "/deletePermission", method = RequestMethod.POST)
    public ResultData deletePermission(@RequestBody String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = permissionService.deletePermission(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("删除异常！");
            }
            logger.error("CRM", "PermissionController", "deletePermission", reqMap.toString(), null, "", "删除异常", e);

        }
        return resultData;
    }

    @RequestMapping(value = "/searchList/{param}/", method = RequestMethod.GET)
    public String searchList(@PathVariable("param") String param)
    {
        //构建返回
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<PermissionDto>> resultDataQy = new ResultData<>();
        try
        {
            resultDataQy = this.permissionService.searchList(queryParam);
        }
        catch (Exception e)
        {
            resultDataQy.setFail();
            logger.error("permission", "PermissionController", "searchList", "", null, "", "数据列表", e);
        }
        return resultDataQy.toString();
    }

    @RequestMapping(value = "/savePermission", method = RequestMethod.POST)
    public ResultData savePermission(@RequestBody String param){
        ResultData resultData = null;

        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = permissionService.savePermission(reqMap);

        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
                resultData.setFail("保存异常！");
            }
            logger.error("permission", "PermissionController", "searchList", "", null, "", "保存异常", e);
        }
        return resultData;
    }

}