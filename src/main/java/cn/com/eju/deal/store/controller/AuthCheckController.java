package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.store.model.StoreAuthCheck;
import cn.com.eju.deal.store.service.AuthCheckService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/AuthCheck")
public class AuthCheckController extends BaseController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private AuthCheckService authCheckService;

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public ResultData<?> create(HttpServletRequest request, @RequestBody String reqJson){
        ResultData<?> resultData = new ResultData<>();

        try{
            StoreAuthCheck storeAuthCheck = JsonUtil.parseToObject(reqJson, StoreAuthCheck.class);
            resultData = authCheckService.create(storeAuthCheck);
        }catch (Exception e){
            resultData.setFail("创建授牌验收失败");
            logger.error("AuthCheck", "AuthCheckController", "create", reqJson, 0, "", "创建授牌验收失败", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/getAuthCheckById",method = RequestMethod.POST)
    public ResultData<?> getAuthCheckById(HttpServletRequest request, @RequestBody String reqJson){
        ResultData<?> resultData = new ResultData<>();

        try{
            StoreAuthCheck storeAuthCheck = JsonUtil.parseToObject(reqJson, StoreAuthCheck.class);
            resultData = authCheckService.getAuthCheckById(storeAuthCheck);
        }catch (Exception e){
            resultData.setFail("获取授牌验收失败");
            logger.error("AuthCheck", "AuthCheckController", "getAuthCheckById", reqJson, 0, "", "获取授牌验收失败", e);
        }

        return resultData;
    }
    /**
     * 查询授牌验收申请列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getStoreAuthCheckList/{param}", method = RequestMethod.GET)
    public String getStoreAuthCheckList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = authCheckService.getStoreAuthCheckList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("AuthCheck", "AuthCheckController", "getStoreAuthCheckList", reqMap.toString(), null, "", "查询授牌验收申请list异常", e);
        }
        return resultData.toString();
    }
    /**
     * 根据id授牌验收详情
     */
    @RequestMapping(value = "/getStoreAuthCheckInfoById/{id}", method = RequestMethod.GET)
    public String getStoreAuthCheckInfoById(@PathVariable Integer id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.authCheckService.getStoreAuthCheckInfoById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("AuthCheck", "AuthCheckController", "getStoreAuthCheckInfoById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 授牌验收申请审核通过
     */
	@RequestMapping(value = "/auditPass",method = RequestMethod.POST)
    public ResultData<?> auditPass(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = authCheckService.auditPass(reqMap);
        }catch (Exception e){
            resultData.setFail("审核授牌验收申请失败！");
            logger.error("AuthCheck", "AuthCheckController",
                    "auditPass", param, -1, "", "审核授牌验收申请失败！", e);
        }

        return resultData;
    }

    @RequestMapping(value = "/checkSPCXCount",method = RequestMethod.POST)
    public ResultData<?> checkSPCXCount(@RequestBody Integer storeId){
        ResultData<?> resultData = new ResultData<>();
        try{
            resultData = authCheckService.checkSPCXCount(storeId);
        }catch (Exception e){
            resultData.setFail("审核授牌验收校验失败！");
            logger.error("AuthCheck", "AuthCheckController",
                    "checkSPCXCount", storeId.toString(), -1, "", "审核授牌验收校验失败！", e);
        }

        return resultData;
    }
}
