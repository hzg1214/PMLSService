package cn.com.eju.deal.permission.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.permission.dao.PermissionMapper;
import cn.com.eju.deal.permission.model.PermissionDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service("permissionService")
public class PermissionService extends BaseService {
@Resource
    PermissionMapper permissionMapper;
    public ResultData<List<PermissionDto>> getPermissionList(Map<?, ?> param) throws Exception
    {
        ResultData<List<PermissionDto>> resultData = new ResultData<>();
        List<PermissionDto> moList = permissionMapper.getPermissionList(param);
        if(moList !=null && moList.size()>0){
            resultData.setReturnData(moList);
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        }

        return resultData;
    }
    public ResultData savePermission(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        String functionCode= (String)reqMap.get("functionCode");
        if(StringUtil.isEmpty(functionCode)){
            resultData.setFail("请选择要添加的模块!");
            return resultData;
        }
        List<PermissionDto> moList = permissionMapper.searchNewList(reqMap);

        String functionCodeArr[]=functionCode.split(";");
        List<String> stringfuc = Arrays.asList(functionCodeArr);

        List<String> result = new ArrayList<String>();
        result.addAll(stringfuc);
        result.removeAll(moList);
        reqMap.put("functionCodeList",result);
        if(result.size()==0){
            resultData.setFail("该用户已经拥有所有权限!");
            return resultData;
        }
        permissionMapper.savePermission(reqMap);
        resultData.setSuccess("添加成功！");
        return resultData;
    }

    public ResultData deletePermission(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        String id = (String)reqMap.get("id");
        if(StringUtil.isEmpty(id)){
            resultData.setFail("请选择要删除的人!");
            return resultData;
        }

        String idArr[]=id.split(",");
        reqMap.put("idList",idArr);
        permissionMapper.deletePermission(reqMap);
        resultData.setSuccess("删除成功！");
        return resultData;
    }

    public ResultData<List<PermissionDto>> searchList(Map<?, ?> param) throws Exception
    {

        ResultData<List<PermissionDto>> resultData = new ResultData<>();

        List<PermissionDto> permissionList = permissionMapper.searchList(param);
        resultData.setReturnData(permissionList);

        return resultData;
    }
}