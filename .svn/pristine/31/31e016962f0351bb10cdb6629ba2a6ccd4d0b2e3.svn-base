package cn.com.eju.deal.fangyou.controller;

import java.util.ArrayList;
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
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.op.OpStoreDto;
import cn.com.eju.deal.dto.store.StoreADto;
import cn.com.eju.deal.fangyou.model.FangyouAccount;
import cn.com.eju.deal.fangyou.service.FangyouAccountService;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;

/**
 * Created by cning on 2017/7/6.
 * 房友账号绑定
 */
@RestController
@RequestMapping(value = "fangyouAccount")
public class FangyouAccountController  extends BaseController{
	 /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "fangyouAccountService")
    private FangyouAccountService fangyouAccountService;

    /**
     * 获取房友账号绑定列表
     *
     * @return 列表
     */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
    	
    	Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        String getList = new String();
        try
        {
        	getList = fangyouAccountService.queryList(queryParam);
        }
        catch (Exception e)
        {
            logger.error("fangyou", "FangyouAccountController", "queryList", "", null, "", "获取房友账号绑定列表", e);
        }
        return getList;
    }
    
    /** 
     * @Title: addBundling 
     * @Description: 新增房友账号绑定
     * @param jsonDto
     * @return     
     */
     @RequestMapping(value = "add", method = RequestMethod.POST)
     public String addBundling(@RequestBody String jsonDto)
     {
    	 FangyouAccount fangyouAccount = JsonUtil.parseToObject(jsonDto, FangyouAccount.class);
         //构建返回
         ResultData<FangyouAccount> resultData = new ResultData<FangyouAccount>();
         
         try {
        	//取得门店房友账号关联表，用于判断①门店绑定过房友账号②门店的房友账号为开通中
        	 ResultData<FangyouAccount> resultDataOp = this.fangyouAccountService.getById(fangyouAccount.getStoreId());
        	 FangyouAccount info = (FangyouAccount)resultDataOp.getReturnData();
             
         	  //通知op条件判定---门店绑定过房友账号,并且门店的房友账号为开通中
        	 if(null != info){
	              if("1".equals(info.getOpenStatus()))  //开通中
	              {
	            	  logger.info("该门店有正在开通中的房友账号,门店No:"+info.getStoreNo());
	            	  resultData.setFail();
	              	  resultData.setReturnMsg("该门店有正在开通中的房友账号,门店No:"+info.getStoreNo());
	              	  return resultData.toString();
	              }
        	 }
        	 
             //通知op,传递信息            
             Map<String,Object> map = this.fangyouAccountService.getOPByStoreId(fangyouAccount.getStoreId());
             if(null!=map)
             {
            	 String url = SystemParam.getWebConfigValue("opUrl") + "fyaccountbind";
                 
                 OpStoreDto opDto = new OpStoreDto();
                 if("1".equals(fangyouAccount.getOperType())) //0:解绑  1:绑定
             	 {
                	 opDto.setIsBind(1);
             	 }else{
             		 opDto.setIsBind(2);
             	 }
                 opDto.setUserIdUpdate(fangyouAccount.getUserIdCreate());
                 opDto.setCompanyNo(fangyouAccount.getFangyouNo());
                 opDto.setStoreADtoList(setStoreDtoList(map));
                 
                //jsonString设值
                 String jsonData = JsonUtil.parseToJson(opDto);	                 
                 logger.info("CRM房友账号绑定申请接口: #####请求#url="+url+"##userIdUpdate="+ opDto.getUserIdUpdate());
                 String opResult = HttpClientUtil.jsonPost(url,jsonData);
                 
                 //op返回值
                 Map<String,Object> opMap = (Map<String,Object>) JsonUtil.parseToObject(opResult,  Map.class);
                 logger.info("CRM房友账号绑定申请接口返回码："+opMap.get("returnCode").toString()+",返回信息："+opMap.get("returnMsg").toString());
                 
                 if("200".equals(opMap.get("returnCode").toString()))
                 {
                	 resultData = this.fangyouAccountService.createStr(fangyouAccount);
                 }else{
                	 resultData.setFail();
                	 resultData.setReturnCode(opMap.get("returnCode").toString());
                	 resultData.setReturnMsg(opMap.get("returnMsg").toString());
                 }
             }
 		} catch (Exception e) {
 			resultData.setFail();
            logger.error("fangyou", "FangyouAccountController", "addBundling", "", null, "", "调用房友接口失败", e);
 		}
        return resultData.toString();
     }
     
     /**
      * 门店信息设置
      * @param ContractStore 门店信息
      * @return StoreADto 门店DTO
      */
     private List<StoreADto> setStoreDtoList(Map<String,Object> map)
     {
     	List<StoreADto> storeDtoList = new ArrayList<>();

     	StoreADto storeDto = new StoreADto();
     	storeDto.setStoreNo(map.get("StoreNo").toString());
     	storeDto.setName(map.get("Name").toString());
     	storeDto.setCityNo(map.get("CityNo").toString());
     	storeDto.setDistrictNo(map.get("DistrictNo").toString());
     	storeDto.setAreaNo(map.get("AreaNo").toString());
     	storeDto.setAddress(map.get("Address").toString());
     	storeDto.setAddressDetail(map.get("AddressDetail").toString());
     	storeDto.setLongitude(map.get("Longitude").toString());
     	storeDto.setLatitude(map.get("Latitude").toString());
     	storeDtoList.add(storeDto);
         
     	return storeDtoList;
     }
     
     /**
      * 获取门店房友账号关联表
      *
      * @return 列表
      */
     @RequestMapping(value = "/storeAccount/{storeId}", method = RequestMethod.GET)
     public String getById(@PathVariable Integer storeId) {
    	 ResultData<FangyouAccount> resultData = new ResultData<>();
         try
         {
        	 resultData = fangyouAccountService.getById(storeId);
         }
         catch (Exception e)
         {
             logger.error("fangyou", "FangyouAccountController", "getById", "", null, "", "获取门店房友账号关联表", e);
         }
         return resultData.toString();
     }
     /**
      * 根据门店id查询其房友账号变更日志
      * @param storeId
      * @return
      */
     @RequestMapping(value = "/getFangyouAccountList/{storeId}", method = RequestMethod.GET)
     public ResultData getLogList(@PathVariable("storeId") Integer storeId){
         ResultData resultData = null;
         try {
             resultData = fangyouAccountService.getFangyouAccountList(storeId);
         } catch (Exception e) {
             if(resultData == null){
                 resultData = new ResultData();
             }
             resultData.setFail();
             logger.error("fangyou", "FangyouAccountController", "getFangyouAccountList", storeId + "", null, "", "查询房友账号调整日志list异常", e);
         }
         return resultData;
     }
     /**
      * 编辑房友账号
      * @param param
      * @return
      */
 	 @RequestMapping(value = "/changeFyAcount", method = RequestMethod.POST)
     public String changeFyAcount(@RequestBody String param){
     	//构建返回
     	ResultData resultData = null;
     	Map<String, Object> map = JsonUtil.parseToObject(param, Map.class);
     	try{
     		resultData = this.fangyouAccountService.changeFyAcount(map);
     	}catch (Exception e){
     		if(resultData == null){
     			resultData = new ResultData();
     		}
     		resultData.setFail();
     		logger.error("CRM", "FangyouAccountController", "changeFyAcount", "", null, "", "", e);
     	}
     	return resultData.toString();
     }
}
