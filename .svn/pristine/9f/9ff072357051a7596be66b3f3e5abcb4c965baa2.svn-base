package cn.com.eju.deal.dataAuthority.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dataAuthority.dao.DataAuthorityMapper;
import cn.com.eju.deal.dataAuthority.model.CityRelation;
import cn.com.eju.deal.dataAuthority.model.DataAuthority;
import cn.com.eju.deal.dto.houseLinkage.estate.EstateSearchResultDto;

import com.alibaba.fastjson.JSONArray;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("dataAuthorityService")
public class DataAuthorityService extends BaseService {

    @Resource
    DataAuthorityMapper dataAuthorityMapper;

    /**
     * 查询-list
     * @param param
     * @return
     * @throws Exception
     */
    public ResultData<List<DataAuthority>> getDataAuthorityList(Map<?, ?> param) throws Exception
    {
        ResultData<List<DataAuthority>> resultData = new ResultData<>();
        List<DataAuthority> moList = dataAuthorityMapper.getDataAuthorityList(param);
        if(moList !=null && moList.size()>0){
            resultData.setReturnData(moList);
            resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        }

        return resultData;
    }
    public ResultData<List<CityRelation>> getQyInfo(Map<?, ?> param) throws Exception
    {

        ResultData<List<CityRelation>> resultData = new ResultData<>();
        List<CityRelation> qyList = dataAuthorityMapper.getQyInfo(param);

        resultData.setReturnData(qyList);

        return resultData;
    }


    public ResultData saveData(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        String userCode = (String)reqMap.get("userCode");
        String userName = (String)reqMap.get("userName");
        String authorityLevel = (String)reqMap.get("authorityLevel");
        String cityStr= (String)reqMap.get("cityList");
        if(StringUtil.isEmpty(userCode)){
            resultData.setFail("请填写工号!");
            return resultData;
        }
        if(StringUtil.isEmpty(userName)){
            resultData.setFail("请填写姓名!!");
            return resultData;
        }
        if(StringUtil.isEmpty(authorityLevel)){
            resultData.setFail("请选择等级!");
            return resultData;
        }
        if(StringUtil.isEmpty(cityStr)){
            resultData.setFail("请选择城市!");
            return resultData;
        }
        JSONArray cityList = JSONArray.parseArray(cityStr);
        reqMap.put("cityList",cityList);
        dataAuthorityMapper.insertDataAuth(reqMap);
        resultData.setSuccess("添加成功！");
        return resultData;
    }
    public ResultData deleteData(Map<String,Object> reqMap)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        String id = (String)reqMap.get("id");
        if(StringUtil.isEmpty(id)){
            resultData.setFail("请选择要删除的人!");
            return resultData;
        }

        String idArr[]=id.split(",");
        reqMap.put("idList",idArr);
        dataAuthorityMapper.deleteDataAuth(reqMap);
        resultData.setSuccess("删除成功！");
        return resultData;
    }

}




