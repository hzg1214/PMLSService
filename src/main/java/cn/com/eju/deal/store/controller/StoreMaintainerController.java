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
import cn.com.eju.deal.dto.store.StoreMaintainerDto;
import cn.com.eju.deal.store.model.StoreMaintainer;
import cn.com.eju.deal.store.service.StoreMaintainerService;

/**   
* 门店维护人关系
* @author 张文辉
* @date 2016年6月7日 下午2:58:57
*/
@RestController
@RequestMapping(value = "storeMaintainer")
public class StoreMaintainerController extends BaseController
{
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
    @Resource(name="storeMaintainerService")
    private StoreMaintainerService storeMaintainerService;
    /**
     * @param jsonDto
     * @return
     * @Title: create
     * @Description: 新增门店维护人关系
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto)
    {
        StoreMaintainer storeMaintainer = JsonUtil.parseToObject(jsonDto, StoreMaintainer.class);
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = this.storeMaintainerService.create(storeMaintainer);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "StoreMaintainerController", "create", "", null, "", "", e);
        }
        return resultData.toString();
    }
    
    /**
     * 查询门店维护人历史
     */
    @RequestMapping(value = "/qMaintainerHis/{param}", method = RequestMethod.GET)
    public String getStoreMaintainerHisList(@PathVariable String param)
    {
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StoreMaintainer>> resultData = new ResultData<List<StoreMaintainer>>();
        try
        {
            resultData = this.storeMaintainerService.getStoreMaintainerHisList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "StoreMaintainerController", "getStoreMaintainerHisList", "", null, "", "", e);
        }
        return resultData.toString();
    }
  //Add 2017/04/10 cning 门店维护人查询--->
    /** 
     * 查询
     * @param storeId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "g/{storeId}", method = RequestMethod.GET)
    public String getByStoreId(@PathVariable int storeId) 
    {
    	 //构建返回
        ResultData<StoreMaintainerDto> resultData = new ResultData<StoreMaintainerDto>();
        
    	StoreMaintainerDto smDto=new  StoreMaintainerDto();
        //查询
        try
        {
        	smDto = storeMaintainerService.getByStoreId(storeId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contract", "ContractContrller", "getById", "", -1, "", "查询-对象失败", e);
        }
        
        resultData.setReturnData(smDto);
        return resultData.toString();   
    }
   //Add 2017/04/10 cning 门店维护人查询<---
    
	/**
	 * 查询门店是否有维护人
	 */
	@RequestMapping(value = "hasmtn/{storeId}", method = RequestMethod.GET)
	public String hasMtner(@PathVariable Integer storeId) throws Exception {
		// 构建返回
		ResultData<Boolean> resultData = new ResultData<Boolean>();
		Boolean hasFlag = false;
		// 查询
		try {
			hasFlag = storeMaintainerService.hasMtner(storeId);
			resultData.setReturnData(hasFlag);
		} catch (Exception e) {
			resultData.setFail();
			logger.error("Contract", "ContractContrller", "hasMtner", "", -1, "", "查询门店是否有维护人-失败", e);
		}
		return resultData.toString();
	}

    /**
     * 门店维护人的check
     */
    @RequestMapping(value = "/chkMaintainer/{param}", method = RequestMethod.GET)
    public String chkMaintainer(@PathVariable String param)
    {
        @SuppressWarnings("unchecked")
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<?> resultData = new ResultData<List<StoreMaintainer>>();
        try
        {
            resultData = this.storeMaintainerService.chkMaintainer(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "StoreMaintainerController", "getStoreMaintainerHisList", "", null, "", "", e);
        }
        return resultData.toString();
    }
}
