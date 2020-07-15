package cn.com.eju.deal.keFuWj.controller;

import java.util.HashMap;
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
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.keFuWj.dto.KeFuWjDto;
import cn.com.eju.deal.keFuWj.model.KefuWjCitymapping;
import cn.com.eju.deal.keFuWj.model.KefuWjH;
import cn.com.eju.deal.keFuWj.service.KeFuWjService;

@RestController
@RequestMapping("keFuWj")
public class KeFuWjController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private KeFuWjService keFuWjService;
    
    
    /**
     * 
     * desc:获取适用城市列表
     * 2019年6月18日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getWjCityList", method = RequestMethod.GET)
    public String getWjCityList(){
    	ResultData resultData = null;
    	try {
    		resultData = keFuWjService.getWjCityList();
    	} catch (Exception e) {
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("CRM", "KeFuWjController", "getWjCityList", null, null, "", "获取适用城市列表异常", e);
    	}
    	return resultData.toString();
    }
    /**
     *
     * desc:获取选择的城市列表
     * 2019年6月18日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getWjCheckCityList", method = RequestMethod.POST)
    public String getWjCheckCityList(@RequestBody String jsonDto){
    	ResultData resultData = new ResultData();
    	try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);
            Integer id = Integer.valueOf(queryParam.get("id").toString());
    		resultData = keFuWjService.getWjCheckCityList(id);
    	} catch (Exception e) {
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("CRM", "KeFuWjController", "getWjCheckCityList", null, null, "", "获取适用城市列表异常", e);
    	}
    	return resultData.toString();
    }

    /**
     * 查询问卷调查列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getKeFuWjList/{param}", method = RequestMethod.GET)
    public String getKeFuWjList(@PathVariable String param){
        ResultData resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        List<Map<String,Object>> list = null;
        try {
            list = keFuWjService.getKeFuWjList(reqMap);
        } catch (Exception e) {
        	e.printStackTrace();
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuWjController", "getKeFuWjList", reqMap.toString(), null, "", "查询问卷调查维护列表异常", e);
        }
        if(list !=null && list.size()>0)
        {
        	resultData.setReturnData(list);
        	resultData.setTotalCount((String)reqMap.get(QueryConst.TOTAL_COUNT));
        }
        return resultData.toString();
    }
    
    /**
     * desc:获取问卷已调查列表
     * 2019年7月29日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getInvestedList/{storeId}", method = RequestMethod.GET)
    public String getInvestedList(@PathVariable Integer storeId){
    	 ResultData resultData = null;
    	 Map<String,Object> reqMap = new HashMap<>();
    	 reqMap.put("storeId", storeId);
         try{
             resultData = keFuWjService.getInvestedList(reqMap);
         }catch (Exception e){
         	if(resultData == null){
                 resultData = new ResultData();
             }
             resultData.setFail();
             logger.error("CRM", "KeFuWjController", "getInvestedList", reqMap.toString(), null, "", "获取问卷已调查列表异常", e);
         }
         return resultData.toString();
    }

    /**
     * 问卷状态变更为未启用
     * @param param
     * @return
     */
    @RequestMapping(value = "finalize", method = RequestMethod.PUT)
    public String finalize(@RequestBody String param) {
        //构建返回
        ResultData resultData = new ResultData();
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        int count = 0;
        try {
            count = keFuWjService.finalize(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("KefuWjH", "KeFuWjContractController", "finalize", "", 0, "", "状态变更失败", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }

    /**
     * 问卷预览
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String queryKeFuWjList(@PathVariable int id)
    {
        //构建返回
        ResultData<KeFuWjDto> resultData = new ResultData<KeFuWjDto>();

        //查询
        KeFuWjDto keFuWjDto =new  KeFuWjDto();
        try
        {
            keFuWjDto = keFuWjService.queryKeFuWjList(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("KeFuWjDto", "KeFuWjContractController", "queryKeFuWjList", "", -1, "", "查询-对象失败", e);
        }

        resultData.setReturnData(keFuWjDto);

        return resultData.toString();
    }

    /**
     * 根据id查询问卷（问卷绑定城市弹窗）
     * @param id
     * @return
     */
    @RequestMapping(value = "/queryKeFuWjHById/{id}", method = RequestMethod.GET)
    public String queryKeFuWjHById(@PathVariable Integer id){
        //构建返回
        ResultData<KefuWjH> resultData = new ResultData<KefuWjH>();
        try{
            KefuWjH kefuWjH = keFuWjService.queryKeFuWjHById(id);
            resultData.setReturnData(kefuWjH);
        }catch (Exception e){
            resultData.setFail();
            logger.error("KefuWjH", "KeFuWjContractController", "queryKeFuWjHById", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 查询城市是否绑定问卷
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "queryCityIsAvailable", method = RequestMethod.POST)
    public String queryCityIsAvailable(@RequestBody String jsonDto) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();

        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);
            String strs = queryParam.get("cityNos").toString();
            Integer id = Integer.valueOf(queryParam.get("id").toString());
            resultData = keFuWjService.queryCityIsAvailable(strs,id);
        } catch (Exception e) {
            logger.error("查询中心异常", e);
            resultData.setFail();
        }
        return resultData.toString();
    }

    /**
     * 问卷绑定城市
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody String jsonDto) {
        //构建返回
        ResultData<KefuWjCitymapping> resultData = new ResultData<KefuWjCitymapping>();
        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(jsonDto, Map.class);

            resultData = keFuWjService.update(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("KefuWjCitymapping", "KeFuWjContractController", "update", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * desc:新增导入
     * 2019年6月20日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/imputAdd", method = RequestMethod.POST)
    public String imputAdd(@RequestBody String jsonDto)
    {
    	ResultData<String> resultData = new ResultData<>();
    	KeFuWjDto dto = JsonUtil.parseToObject(jsonDto, KeFuWjDto.class);
        try
        {
        	resultData = this.keFuWjService.imputAdd(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            resultData.setReturnMsg("导入数据失败！");
            logger.error("keFuWj", "keFuWjController", "imputAdd", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }

    /**
     * 问卷删除
     * @param param
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.PUT)
    public String remove(@RequestBody String param) {
        ResultData resultData = new ResultData<>();
        int count = 0;
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            count = keFuWjService.remove(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("KeFuWjH", "keFuWjController", "remove", "",
                    null, "", "", e);
        }
        if (count <= 0) {
            resultData.setFail();
        }
        return resultData.toString();
    }

    /**
     * 测评已测评列表
     * @param storeId
     * @return
     */
    @RequestMapping(value = "/getEvaluationList/{storeId}", method = RequestMethod.GET)
    public String getEvaluationList(@PathVariable Integer storeId){
        ResultData resultData = null;
        Map<String,Object> reqMap = new HashMap<>();
        reqMap.put("storeId", storeId);
        try{
            resultData = keFuWjService.getEvaluationList(reqMap);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuWjController", "getEvaluationList", reqMap.toString(), null, "", "获取测评已测评列表异常", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getEvaluationData/{id}", method = RequestMethod.GET)
    public String getEvaluationData(@PathVariable Integer id) {
        //构建返回
        ResultData resultData = null;
        try {
            resultData = this.keFuWjService.getEvaluationData(id);
        } catch (Exception e) {
            resultData = new ResultData();
            resultData.setFail();
            logger.error("CRM", "KeFuWjController", "getEvaluationData", "", null, "", "获取测评详情", e);
        }
        return resultData.toString();
    }
}
