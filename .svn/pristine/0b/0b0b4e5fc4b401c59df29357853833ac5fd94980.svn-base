package cn.com.eju.deal.houseLinkage.lnkAchievementSum.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.lnkAchievementSum.dao.LnkAchievementSumMapper;
import cn.com.eju.deal.houseLinkage.lnkAchievementSum.model.LnkAchievementSumDto;
import cn.com.eju.deal.houseLinkage.storedepositSerial.model.StoreDepositSerialDto;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("lnkAchievementSumService")
public class LnkAchievementSumService {
	
	/**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
     @Resource
	 private LnkAchievementSumMapper lnkAchievementSumMapper;
	 /**
     * 查询保证金流水列表数据
     * @return
     */
     public ResultData queryLnkAchievementSumList(Map<String,Object> reqMap)throws Exception{
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
         
         List<LnkAchievementSumDto> lnkAchievementSumList =  lnkAchievementSumMapper.queryLnkAchievementSumList(reqMap);
         resultData.setTotalCount("0");
         
         if(lnkAchievementSumList !=null && lnkAchievementSumList.size()>0){
        	 resultData.setTotalCount(lnkAchievementSumList.size()+"");
        	 if (exportFlag == false) {
        		 int endRow = pageIndex * pageSize;
        		 lnkAchievementSumList = lnkAchievementSumList.subList((pageIndex - 1) * pageSize, endRow > lnkAchievementSumList.size() ? lnkAchievementSumList.size() : endRow);
        	 }

             reqMap.put("pageIndex", pageIndex);
             reqMap.put("pageSize", pageSize);
             reqMap.put("curPage", curPage);
         	 resultData.setReturnData(lnkAchievementSumList);
 	         resultData.setSuccess();
         }
         return resultData;
     }
     
    
     
}
  