package cn.com.eju.deal.houseLinkage.storeDepositDeatil.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.dto.DistrictDto;
import cn.com.eju.deal.base.linkage.model.District;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.houseLinkage.storeDepositDeatil.dao.StoreDepositDeatilMapper;
import cn.com.eju.deal.houseLinkage.storeDepositDeatil.model.StoreDepositDeatilDto;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("storeDepositDeatilService")
public class StoreDepositDeatilService {
	
	/**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
     @Resource
	 private StoreDepositDeatilMapper storeDepositDeatilMapper;
	 /**
     * 联动框架合同列表
     * @param reqMap
     * @return
     */
     public ResultData queryStoreDepositDeatilList(Map<String,Object> reqMap)throws Exception{
         ResultData resultData = new ResultData();
         resultData.setFail();
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
         
         List<StoreDepositDeatilDto> storeDepositDtoList =  storeDepositDeatilMapper.queryStoreDepositDeatilList(reqMap);
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
         List<StoreDepositDeatilDto> list = storeDepositDeatilMapper.queryCityList();
         if(list !=null && list.size()>0){
         	 resultData.setReturnData(list);
 	         resultData.setSuccess();
         }
         return resultData;
     }
     /** 
      * 根据城市CityNo获取其行政区List
      * @param queryParam
      * @return
      */
     public ResultData queryAccountProject(String cityNo) throws Exception{
         //构建返回
         ResultData resultData = new ResultData();
         //查询
         List<StoreDepositDeatilDto> moList = storeDepositDeatilMapper.queryAccountProject(cityNo);
         resultData.setReturnData(moList);
         
         return resultData;
     }
     
}
  