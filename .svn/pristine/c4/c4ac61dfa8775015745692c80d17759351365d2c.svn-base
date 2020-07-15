package cn.com.eju.deal.houseLinkage.storedepositSerial.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.storeDepositDeatil.dao.StoreDepositDeatilMapper;
import cn.com.eju.deal.houseLinkage.storeDepositDeatil.model.StoreDepositDeatilDto;
import cn.com.eju.deal.houseLinkage.storedepositSerial.dao.StoreDepositSerialMapper;
import cn.com.eju.deal.houseLinkage.storedepositSerial.model.StoreDepositSerialDto;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("storeDepositSerialService")
public class StoreDepositSerialService {
	
	/**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
     @Resource
	 private StoreDepositSerialMapper storeDepositSerialMapper;
	 /**
     * 查询保证金流水列表数据
     * @return
     */
     public ResultData queryStoreDepositSerialList(Map<String,Object> reqMap)throws Exception{
         ResultData resultData = new ResultData();
         resultData.setFail();
         
         //自定义分页数据
         int pageIndex = 1;
         int pageSize = 10;
         int curPage = 1;
         boolean exportFlag = false;

         if (reqMap.get("pageIndex") != null)
             pageIndex = Integer.parseInt(reqMap.get("pageIndex").toString());
         if (reqMap.get("pageSize") != null)
             pageSize = Integer.parseInt(reqMap.get("pageSize").toString());
         if (reqMap.get("curPage") != null)
             curPage = Integer.parseInt(reqMap.get("curPage").toString());
         if (reqMap.get("optFlag") != null && reqMap.get("optFlag").toString().equals("export"))
             exportFlag = true;
         reqMap.remove("pageIndex");
         reqMap.remove("pageSize");
         reqMap.remove("curPage");
         
         List<StoreDepositSerialDto> storeDepositDtoList =  storeDepositSerialMapper.queryStoreDepositSerialList(reqMap);
         resultData.setTotalCount("0");
         
         if(storeDepositDtoList !=null && storeDepositDtoList.size()>0){
        	 resultData.setTotalCount(storeDepositDtoList.size()+"");
        	 if (exportFlag == false) {
        		 int endRow = pageIndex * pageSize;
        		 storeDepositDtoList = storeDepositDtoList.subList((pageIndex - 1) * pageSize, endRow > storeDepositDtoList.size() ? storeDepositDtoList.size() : endRow);
        	 }

             reqMap.put("pageIndex", pageIndex);
             reqMap.put("pageSize", pageSize);
             reqMap.put("curPage", curPage);
         	 resultData.setReturnData(storeDepositDtoList);
 	         resultData.setSuccess();
         }
         return resultData;
     }
     
     /**
      * 查询城市节点
      * @param dto
      * @return
     * @throws Exception 
      */
     public ResultData<?> queryCityList() throws Exception {
         ResultData resultData = new ResultData<>();
         resultData.setFail();
         List<StoreDepositSerialDto> list = storeDepositSerialMapper.queryCityList();
         if(list !=null && list.size()>0){
         	 resultData.setReturnData(list);
 	         resultData.setSuccess();
         }
         return resultData;
     }
     /** 
      * 根据城市CityNo
      * @return
      */
     public ResultData queryAccountProject() throws Exception{
         //构建返回
         ResultData resultData = new ResultData();
         //查询
         List<StoreDepositSerialDto> moList = storeDepositSerialMapper.queryAccountProject();
         resultData.setReturnData(moList);
         
         return resultData;
     }
     
}
  