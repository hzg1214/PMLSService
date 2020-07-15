package cn.com.eju.deal.api.gpContract.controller;

import cn.com.eju.deal.api.gpContract.service.APIGPContractService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.gpContractChange.service.GpContractChangeService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 服务层
 * 微信端合同撤销专用
 *
 * @author zhang
 */

@RestController
@RequestMapping(value = "APIGPContract")
public class APIGPContractController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "apiGPContractService")
    private APIGPContractService apiGPContractService;

    @Resource
    private GpContractChangeService gpContractChangeService;

    /**
     * 公盘合同终止列表
     */
    @RequestMapping(value = "/getTerminateList", method = RequestMethod.POST)
    public String getTerminateList(@RequestBody String jsonDto) {
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            //权限控制,参数转换
            authParam(reqMap);
            resultData = apiGPContractService.getTerminateList(reqMap);
        } catch (Exception e) {
            if (resultData == null) {
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "getTerminateList", reqMap.toString(), null, "", "查询可终止的公盘合同列表list异常", e);
        }
        return resultData.toString();
    }

    /**
     * 公盘合同终止提交填写页面
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getGpContractZZDetail", method = RequestMethod.POST)
    public String getGpContractZZDetail(@RequestBody String param) {
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = this.apiGPContractService.getGpContractZZDetail(map);
        } catch (Exception e) {
            if (resultData == null) {
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "getGpContractZZDetail", "", null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 校验门店
     * @param param
     * @return
     */
    @RequestMapping(value = "/checkGPContractStoreForZZ", method = RequestMethod.POST)
    public String checkGPContractStoreForZZ(@RequestBody String param) {
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = this.apiGPContractService.checkGPContractStoreForZZ(map);
        } catch (Exception e) {
            if (resultData == null) {
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "checkGPContractStoreForZZ", param, null, "", "校验门店异常", e);
        }
        return resultData.toString();
    }

    /**
     * 保存合同终止信息
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/stopGpContract", method = RequestMethod.POST)
    public String stopGpContract(@RequestBody String param) {
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = this.apiGPContractService.stopGpContract(map);
        } catch (Exception e) {
            if (resultData == null) {
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "stopGpContract", map.toString(), null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 查询已终止的待提交合同列表
     */
    @RequestMapping(value = "/getTodoList", method = RequestMethod.POST)
    public String getTodoList(@RequestBody String jsonDto) {
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiGPContractService.getTodoList(reqMap);
        } catch (Exception e) {
            if (resultData == null) {
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "getTodoList", reqMap.toString(), null, "", "查询已撤销的合同列表list异常", e);
        }
        return resultData.toString();
    }

    /**
     * 合同终止查看页面
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getTodoGpDetail", method = RequestMethod.POST)
    public String getTodoGpDetail(@RequestBody String param) {
        //构建返回
        ResultData resultData = null;
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = this.apiGPContractService.getTodoGpDetail(map);
        } catch (Exception e) {
            if (resultData == null) {
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "getTodoGpDetail", map.toString(), null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * 公盘终止查询列表
     */
    @RequestMapping(value = "/getGpZZList", method = RequestMethod.POST)
    public String getGpZZList(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData();
        Map<String, Object> reqMap = JsonUtil.parseToObject(jsonDto, Map.class);
        try {
            resultData = apiGPContractService.getGpZZList(reqMap);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "getGpZZList", reqMap.toString(), null, "", "公盘终止合同查询异常", e);
        }
        return resultData.toString();
    }

    /**
     * 公盘终止查询详情
     * @param param
     * @return
     */
    @RequestMapping(value = "/getGpZZDetail", method = RequestMethod.POST)
    public String getGpZZDetail(@RequestBody String param) {
        //构建返回
        ResultData resultData = new ResultData();
        Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
        try {
            Integer gpContractStopId = Integer.valueOf(map.get("gpContractStopId").toString());
            resultData = gpContractChangeService.getGpContractStopInfoById(gpContractStopId);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("APIGPContract", "APIGPContractController", "getGpZZDetail", map.toString(), null, "", "公盘终止查询详情查询失败", e);
        }
        return resultData.toString();
    }

}
