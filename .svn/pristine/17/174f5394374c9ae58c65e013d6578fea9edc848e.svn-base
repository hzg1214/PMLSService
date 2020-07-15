package cn.com.eju.deal.api.contractChange.controller;

import cn.com.eju.deal.api.contractChange.service.APIContractChangeService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



/**   
* 服务层
* 微信端合同撤销专用
* @author zhang
*/

@RestController
@RequestMapping(value = "APIContractChange")
public class APIContractChangeController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "apiContractChangeService")
    private APIContractChangeService apiContractChangeService;
    /**
     * 查询合同解约列表
     */
    @RequestMapping(value = "/getContractList", method = RequestMethod.POST)
    public String getContractList(@RequestBody String jsonDto){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiContractChangeService.getContractList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractList", reqMap.toString(), null, "", "查询请款单列表list异常", e);
        }
        return resultData.toString();
    }
    /**
     * 合同撤销提交页面
     * @param param
     * @return
     */
    @RequestMapping(value = "/getContractChangeInfo", method = RequestMethod.POST)
    public String getContractChangeInfo(@RequestBody String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.apiContractChangeService.getContractChangeInfo(map);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractChangeInfo", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 合同撤销提交check
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkContractChange", method = RequestMethod.POST)
    public String checkContractChange(@RequestBody String param){
    	//构建返回
    	ResultData resultData = null;
    	Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
    	try{
    		resultData = this.apiContractChangeService.checkContractChange(map);
    	}catch (Exception e){
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("CRM", "APIContractChangeController", "checkContractChange", "", null, "", "", e);
    	}
    	return resultData.toString();
    }
    /**
     * 门店撤销业绩申请
     * 	1.根据合同id获取模板
     * 	2.组装数据
     * 	3.发起申请
     * 	4.改变本地状态
     */
    @RequestMapping(value = "/doSaveAndApplication", method = RequestMethod.POST)
    public String doSaveAndApplication(@RequestBody String param){
    	//构建返回
    	ResultData resultData = null;
    	Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
    	try{
    		resultData = this.apiContractChangeService.doSaveAndApplication(map);
    	}catch (Exception e){
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("CRM", "APIContractChangeController", "checkContractChange", "", null, "", "", e);
    	}
    	return resultData.toString();
    }
    /**
     * 查询已撤销的合同列表
     */
    @RequestMapping(value = "/getContractChangeEdList", method = RequestMethod.POST)
    public String getContractChangeEdList(@RequestBody String jsonDto){
		ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiContractChangeService.getContractChangeEdList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractChangeEdList", reqMap.toString(), null, "", "查询已撤销的合同列表list异常", e);
        }
        return resultData.toString();
    }
    /**
     * 合同撤销提交页面
     * @param param
     * @return
     */
    @RequestMapping(value = "/getContractChangeEdInfo", method = RequestMethod.POST)
    public String getContractChangeEdInfo(@RequestBody String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.apiContractChangeService.getContractChangeEdInfo(map);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractChangeEdInfo", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 合同终止列表
     */
    @RequestMapping(value = "/getContractEndList", method = RequestMethod.POST)
    public String getContractEndList(@RequestBody String jsonDto){
		ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiContractChangeService.getContractEndList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractEndList", reqMap.toString(), null, "", "查询可终止的合同列表list异常", e);
        }
        return resultData.toString();
    }
    /**
     * 合同终止提交填写页面
     * @param param
     * @return
     */
    @RequestMapping(value = "/toContractEndSubmit", method = RequestMethod.POST)
    public String toContractEndSubmit(@RequestBody String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.apiContractChangeService.toContractEndSubmit(map);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "toContractEndSubmit", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 保存合同终止信息
     * @param param
     * @return
     */
	@RequestMapping(value = "/doSaveContractEnd", method = RequestMethod.POST)
    public String doSaveContractEnd(@RequestBody String param){
    	//构建返回
    	ResultData resultData = null;
    	Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
    	try{
    		resultData = this.apiContractChangeService.doSaveContractEnd(map);
    	}catch (Exception e){
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("CRM", "APIContractChangeController", "doSaveContractEnd", "", null, "", "", e);
    	}
    	return resultData.toString();
    }
	 /**
     * 查询已终止的待提交合同列表
     */
    @RequestMapping(value = "/getContractEndToSumbmitList", method = RequestMethod.POST)
    public String getContractEndToSumbmitList(@RequestBody String jsonDto){
		ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiContractChangeService.getContractEndToSumbmitList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractEndToSumbmitList", reqMap.toString(), null, "", "查询已撤销的合同列表list异常", e);
        }
        return resultData.toString();
    }
	 /**
     * 查询已终止的合同列表
     */
    @RequestMapping(value = "/getContractEndProgressList", method = RequestMethod.POST)
    public String getContractEndProgressList(@RequestBody String jsonDto){
		ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiContractChangeService.getContractEndProgressList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractEndProgressList", reqMap.toString(), null, "", "查询已撤销的合同列表list异常", e);
        }
        return resultData.toString();
    }
	/**
     * 合同终止查看页面
     * @param param
     * @return
     */
    @RequestMapping(value = "/getContractEndToView", method = RequestMethod.POST)
    public String getContractEndToView(@RequestBody String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.apiContractChangeService.getContractEndToView(map);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "APIContractChangeController", "getContractEndToView", "", null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/checkContractStoreForTerminate", method = RequestMethod.POST)
    public String checkContractStoreForTerminate(@RequestBody String param){
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = this.apiContractChangeService.checkContractStoreForTerminate(map);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("dissolution", "APIContractChangeController", "checkContractStoreForTerminate", param, null, "", "校验门店异常", e);
        }
        return resultData.toString();
    }
}
