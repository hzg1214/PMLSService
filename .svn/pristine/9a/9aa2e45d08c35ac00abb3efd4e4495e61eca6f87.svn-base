package cn.com.eju.deal.fangyou.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.dto.op.OpCompanyCreditCodeDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.follow.FollowDto;
import cn.com.eju.deal.dto.op.OpCompanyDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.fangyou.dao.FangyouAccountMapper;
import cn.com.eju.deal.fangyou.model.FangyouAccount;
import cn.com.eju.deal.fangyou.model.FangyouAccountLog;
import cn.com.eju.deal.follow.model.Follow;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import cn.com.eju.deal.store.dao.StoreMapper;
import cn.com.eju.deal.store.model.Store;

/**
 * Created by cning on 2017/7/6.
 * 房友账号绑定服务层
 */
@Service("fangyouAccountService")
public class FangyouAccountService extends BaseService<Object> {
	 /**
     * 房友接口地址
     */

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "fangyouAccountMapper")
    private FangyouAccountMapper fangyouAccountMapper;
    
    @Resource(name = "storeMapper")
    private StoreMapper storeMapper;
    /**
     * 获取房友账号绑定信息
     *
     * @return 房友账号绑定列表
     */
    public String queryList(Map<?, ?> param)
            throws Exception {

        ResultData<List<FangyouAccount>> resultData = new ResultData<>();
        
        try {
        	List<FangyouAccount> list = fangyouAccountMapper.queryList(param);
        	resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
            resultData.setReturnData(list);

        } catch (Exception e) {
            logger.error("fangyou", "FangyouService", "getEmployee", "companyId=" + param.get("storeId").toString(), -1, "", "获取房友账号绑定信息", e);
            resultData.setFail();
           
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: createStr 
    * @Description: 添加房友账号绑定（供接口调用）
    * @param fangyouAccount
    * @return     
    */
    public ResultData<FangyouAccount> createStr(FangyouAccount fangyouAccount) throws Exception
    {
        //构建返回
        ResultData<FangyouAccount> resultData = new ResultData<FangyouAccount>();

        int count = fangyouAccountMapper.create(fangyouAccount);
        if(count<=0)
        {
            resultData.setFail();
        }else {
        	//门店表房友账号修改	
        	Map<String, Object> map = new HashMap<String, Object>(); 
        	map.put("storeId", fangyouAccount.getStoreId());
        	map.put("operType", fangyouAccount.getOperType());
        	map.put("fangyouNo", fangyouAccount.getFangyouNo());
        	if("1".equals(fangyouAccount.getOperType())) //0:解绑  1:绑定
        	{
        		map.put("openStatus", "2"); //0:未开通；1:开通中;2:已开通
        	}else{
        		map.put("openStatus", "0");
        	}
        	FangyouAccount info = fangyouAccountMapper.getById(fangyouAccount.getStoreId());
        	if(info == null)
        	{
        		map.put("storeNo", fangyouAccount.getStoreNo());
        		map.put("name", fangyouAccount.getName());
        		map.put("userIdCreate", fangyouAccount.getUserIdCreate());
        		fangyouAccountMapper.createAccount(map);
        	}else{        	
        		fangyouAccountMapper.updateAccount(map);
        	}
        	//返回
        	fangyouAccount.setOpenStatus(map.get("openStatus").toString());
            resultData.setReturnData(fangyouAccount);
        }
        return resultData;
    }
    
    /////////////////////////////  门店房友账号关联表  ///////////////////////////
    
    /**
     * 获取门店房友账号关联表 
     *
     * @return 
     */
    public ResultData<FangyouAccount>  getById(Integer storeId) throws Exception {
        ResultData<FangyouAccount> resultData = new ResultData<>();
        
        try {
        	FangyouAccount fangyouAccount = fangyouAccountMapper.getById(storeId);
            resultData.setReturnData(fangyouAccount);

        } catch (Exception e) {
            logger.error("fangyou", "FangyouService", "getById", null, -1, "", "获取门店房友账号关联表 ", e);
            resultData.setFail();
           
        }
        return resultData;
    }
    
    
    /**
     * 根据门店ID查询门店信息和公司No 
     *
     * @return 
     */
    public Map<String,Object>  getOPByStoreId(Integer storeId) throws Exception {
    	Map<String,Object> map= new HashMap<String, Object>();
        
        try {
        	map = fangyouAccountMapper.getOPByStoreId(storeId);

        } catch (Exception e) {
            logger.error("fangyou", "FangyouService", "getOPByStoreId", null, -1, "", "获取门店房友账号关联表 ", e);           
        }
        return map;
    }
    
    /**
     * 根据公司No查询公司信息 ，公司修改调用OP用 
     *
     * @return 
     */
    public OpCompanyDto getOPCompanyById(Integer companyId) throws Exception {
    	OpCompanyDto companyDto= new OpCompanyDto();
        
        try {
        	companyDto = fangyouAccountMapper.getOPCompanyById(companyId);

        } catch (Exception e) {
            logger.error("fangyou", "FangyouService", "getOPCompanyById", null, -1, "", "调用OP获取公司信息 ", e);           
        }
        return companyDto;
    }

    public OpCompanyCreditCodeDto getOPCompanyCreditCodeById(Integer companyId) throws Exception {
        OpCompanyCreditCodeDto dto= new OpCompanyCreditCodeDto();

        try {
            dto = fangyouAccountMapper.getOPCompanyCreditCodeById(companyId);

        } catch (Exception e) {
            logger.error("fangyou", "FangyouService", "getOPCompanyCreditCodeById", null, -1, "", "调用OP获取公司信息（营业执照号） ", e);
        }
        return dto;
    }
    /**
     * 根据门店id查询其房友账号变更日志
     * @param storeId
     * @return
     * @throws Exception
     */
    public ResultData getFangyouAccountList(Integer storeId)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();
        List<FangyouAccountLog> fangyouAccountLogList =  fangyouAccountMapper.getFangyouAccountList(storeId);
        if(fangyouAccountLogList != null && fangyouAccountLogList.size() > 0){
            resultData.setReturnData(fangyouAccountLogList);
            resultData.setTotalCount(fangyouAccountLogList.size() + "");
            resultData.setSuccess();
        }
        return resultData;
    }
    /**
     * 编辑房友账号
     */
    public ResultData changeFyAcount(Map<String, Object> reqMap) throws Exception{
 	   ResultData resultData = new ResultData();
 	   FangyouAccountLog fangyouAccountLog = new FangyouAccountLog();
 	   Store store = new Store();
 	   String storeId = reqMap.get("storeId").toString();
 	   String storeNo = reqMap.get("storeNo").toString();
 	   String oldFyAcount = reqMap.get("oldFyAcount").toString();
 	   String userCode = reqMap.get("userCode").toString();
 	   String userName = reqMap.get("userName").toString();
 	   String userIdCreate = reqMap.get("userIdCreate").toString();
 	   String newFyAccount = reqMap.get("newFyAccount").toString().trim();
 	   String description ="";
 	   if(!"".equals(oldFyAcount) && null != oldFyAcount) {
 		   description = "门店关联房友账号"+oldFyAcount+"-->"+newFyAccount;
 		   fangyouAccountLog.setOldFyAccount(oldFyAcount);
 	   }else {
 		   fangyouAccountLog.setOldFyAccount(null);
 		   description = "门店关联房友账号"+newFyAccount;
 	   }
 	   fangyouAccountLog.setStoreId(Integer.valueOf(storeId));
 	   fangyouAccountLog.setStoreNo(storeNo);
 	   fangyouAccountLog.setUserCode(userCode);
 	   fangyouAccountLog.setUserName(userName);
 	   fangyouAccountLog.setUserIdCreate(Integer.valueOf(userIdCreate));
 	   fangyouAccountLog.setNewFyAccount(newFyAccount);
 	   fangyouAccountLog.setDateCreate(new Date());
 	   fangyouAccountLog.setDescription(description);
 	   store.setStoreId(Integer.valueOf(storeId));
 	   store.setFyAccount(newFyAccount);
 	   int count = storeMapper.update(store);
 	   if(count<=0) {
 		  resultData.setFail();
 		  resultData.setReturnMsg("编辑房友账号失败!");
 	   }
 	   int countLog = fangyouAccountMapper.insertfangyouAccountLog(fangyouAccountLog);
 	   if(countLog <= 0){
 		  resultData.setFail();
 	   }
       return resultData;
    }
}
