package cn.com.eju.deal.keFuOrder.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.keFuOrder.model.WxKefuOrderReply;
import cn.com.eju.deal.keFuOrder.service.KeFuOrderContractService;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("keFuOrder")
public class KeFuOrderContractController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private KeFuOrderContractService  keFuOrderContractService;
    
    /**
     * 查询客服反馈工单列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getKeFuOrderList/{param}", method = RequestMethod.GET)
    public String getKeFuOrderList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = keFuOrderContractService.getKeFuOrderList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuOrderContractController", "getKeFuOrderList", reqMap.toString(), null, "", "查询客服反馈工单列表异常", e);
        }
        return resultData.toString();
    }
    /**
     * 根据id查询客服反馈工单详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getKeFuOrderById/{id}", method = RequestMethod.GET)
    public String getKeFuOrderById(@PathVariable Integer id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.keFuOrderContractService.getKeFuOrderById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuOrderContractController", "getKeFuOrderById", "", null, "", "", e);
        }
        return resultData.toString();
    }
	/**
     * 保存或更新客服反馈工单列表
     */
	@RequestMapping(value = "/saveKeFuOrder",method = RequestMethod.POST)
    public ResultData<?> saveKeFuOrder(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = keFuOrderContractService.saveKeFuOrder(reqMap);
        }catch (Exception e){
            resultData.setFail("保存或更新客服反馈工单失败！");
            logger.error("CRM", "KeFuOrderContractController",
                    "saveKeFuOrder", param, -1, "", "保存或更新客服反馈工单失败！", e);
        }
        return resultData;
    }
	/**
     * 查询经办人列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getOperatorList/{param}", method = RequestMethod.GET)
    public String getOperatorList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = keFuOrderContractService.getOperatorList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuOrderContractController", "getOperatorList", reqMap.toString(), null, "", "查询经办人列表异常", e);
        }
        return resultData.toString();
    }
    /**
     * 根据一级分类类别查询二级分类
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCategoryTwo/{param}", method = RequestMethod.GET)
    public String getCategoryTwo(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData resultData =new ResultData();
        try{
            resultData = this.keFuOrderContractService.getCategoryTwo(queryParam);
        }
        catch (Exception e){
            resultData.setFail();
            logger.error("CRM", "KeFuOrderContractController", "getCategoryTwo", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
	 * 保存客服回复记录
	 */
	@RequestMapping(value = "/saveKeFuReply",method = RequestMethod.POST)
	public ResultData<?> saveKeFuReply(@RequestBody String param){
		ResultData<?> resultData = new ResultData<>();
		Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
		try{
			resultData = keFuOrderContractService.saveKeFuReply(reqMap);
		}catch (Exception e){
			resultData.setFail("保存回复记录失败！");
			logger.error("CRM", "KeFuOrderContractController",
					"saveKeFulReply", param, -1, "", "保存回复记录失败！", e);
		}
		return resultData;
	}

    /**
     * 保存客服回复记录
     */
    @RequestMapping(value = "/saveKeFuVerify",method = RequestMethod.POST)
    public ResultData<?> saveKeFuVerify(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try{
            resultData = keFuOrderContractService.saveKeFuVerify(reqMap);
        }catch (Exception e){
            resultData.setFail("保存核验记录失败！");
            logger.error("CRM", "KeFuOrderContractController",
                    "saveKeFuVerify", param, -1, "", "保存核验记录失败！", e);
        }
        return resultData;
    }

    /**
     * 微信根据id获取客户工单
     * @param id
     * @return
     */
    @RequestMapping(value = "/getKeFuOrderByIdForWx/{id}", method = RequestMethod.GET)
    public String getKeFuOrderByIdForWx(@PathVariable Integer id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.keFuOrderContractService.getKeFuOrderByIdForWx(id);
        }catch (Exception e){
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuOrderContractController", "getKeFuOrderByIdForWx", ""+id, null, "", "", e);
        }
        return resultData.toString();
    }

    /* 微信端回复处理*/
    @RequestMapping(value = "/reply",method = RequestMethod.POST)
    public ResultData<?> reply(@RequestBody String param){
        ResultData<?> resultData = new ResultData<>();

        try{
            WxKefuOrderReply reply = JsonUtil.parseToObject(param, WxKefuOrderReply.class);
            resultData = keFuOrderContractService.reply(reply);
        }catch (Exception e){
            resultData.setFail("回复失败");
            logger.error("CRM", "KeFuOrderContractController",
                    "reply", param, -1, "", "回复失败！", e);
        }
        return resultData;
    }
    /**
     * 根据storeId查看客服反馈工单列表
     * @param id
     * @return
     */
    @RequestMapping(value = "/getKeFuOrderListByStoreId/{storeId}", method = RequestMethod.GET)
    public String getKeFuOrderListByStoreId(@PathVariable Integer storeId){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.keFuOrderContractService.getKeFuOrderListByStoreId(storeId);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "KeFuOrderContractController", "getKeFuOrderListByStoreId", "", null, "", "", e);
        }
        return resultData.toString();
    }
}
