package cn.com.eju.deal.store.controller;

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
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.store.service.StorePaymentService;

/**
* @Title: StorePaymentController
* @Description: 保证金退款
 */
@RestController
@RequestMapping(value = "storePayment")
public class StorepaymentController extends BaseController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Resource
    private StorePaymentService storePaymentService;
    /**
     * 查询退款列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/getList/{param}", method = RequestMethod.GET)
    public ResultData getStoreReceiveList(@PathVariable String param){
        ResultData resultData = null;
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storePaymentService.getStorePaymentList(reqMap);
        } catch (Exception e) {
            if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("CRM", "StorepaymentController", "getStoreReceiveList", reqMap.toString(), null, "", "查询保证金收款列表list异常", e);
        }
        return resultData;
    }
    /**
     * 查询退款详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/brief/{id}", method = RequestMethod.GET)
    public String getBriefById(@PathVariable int id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.storePaymentService.getBriefById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("store", "StorepaymentController", "getBriefById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * @Title: update
     * @Description: 更新保证金退款信息
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String param){
        //构建返回
    	ResultData resultData = null;
        try
        {
            resultData = this.storePaymentService.updateStr(param);
        }
        catch (Exception e){
        	if(resultData == null){
        		resultData = new ResultData();
        	}
            resultData.setFail();
            logger.error("store", "StorepaymentController", "update", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 查询新增保证金合同列表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getPaymentContractList/{param}", method = RequestMethod.GET)
    public String getPaymentContractList(@PathVariable String param)
    {
    	//构建返回
    	ResultData<List<ContractDto>> resultData = new ResultData<List<ContractDto>>();
    	try{
    		Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
    		resultData = this.storePaymentService.getPaymentContractList(queryParam);
    	}
    	catch (Exception e)
    	{
    		logger.error("storepayment", "StorepaymentController", "getPaymentContractList", "", 0, "", "新政保证金查询合同列表失败 ", e);
    		resultData.setFail();
    	}
    	return resultData.toString();
    }
    
    /**
     * 查询退款详情
     * @param id
     * @return
     */
    @RequestMapping(value = "/getContractInfoById/{id}", method = RequestMethod.GET)
    public String getContractInfoById(@PathVariable int id){
        //构建返回
        ResultData resultData = null;
        try{
            resultData = this.storePaymentService.getContractInfoById(id);
        }catch (Exception e){
        	if(resultData == null){
                resultData = new ResultData();
            }
            resultData.setFail();
            logger.error("store", "StorepaymentController", "getBriefById", "", null, "", "", e);
        }
        return resultData.toString();
    }
    /**
     * 根据城市编码查询其退款tkTypeCode
     * @param id
     * @return
     */
    @RequestMapping(value = "/getBasCitySettingByCityNo/{cityNo}", method = RequestMethod.GET)
    public String getBasCitySettingByCityNo(@PathVariable String cityNo){
    	//构建返回
    	ResultData resultData = null;
    	try{
    		resultData = this.storePaymentService.getBasCitySettingByCityNo(cityNo);
    	}catch (Exception e){
    		if(resultData == null){
    			resultData = new ResultData();
    		}
    		resultData.setFail();
    		logger.error("store", "StorepaymentController", "getBasCitySettingByCityNo", "", null, "", "", e);
    	}
    	return resultData.toString();
    }
    /**
     * 新增保证金退款
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResultData<?> create(@RequestBody String param)
    {
    	Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        
        //EstateInfoDto estateInfoDto = JsonUtil.parseToObject(jsonDto, EstateInfoDto.class);
        try {
            int count = storePaymentService.create(reqMap);
            if (count <= 0) {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "StorepaymentController", "create", param, 0, "", "创建", e);
            
        }
        return resultData;
    }
}
  