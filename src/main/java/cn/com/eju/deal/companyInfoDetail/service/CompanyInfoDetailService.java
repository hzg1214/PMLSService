package cn.com.eju.deal.companyInfoDetail.service;


import cn.com.eju.deal.companyInfoDetail.dao.CompanyInfoDetailMapper;
import cn.com.eju.deal.companyInfoDetail.model.CompanyInfoDetailDto;
import cn.com.eju.deal.core.support.ResultData;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("companyInfoDetailService")
public class CompanyInfoDetailService {
	
     @Resource
	 private CompanyInfoDetailMapper companyInfoDetailMapper;
	 /**
     * 查询公司信息明细列表
     * @return
     */
     public ResultData queryCompanyInfoDetailList(Map<String,Object> reqMap)throws Exception{
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
         
         List<CompanyInfoDetailDto> companyInfoDetailList =  companyInfoDetailMapper.queryCompanyInfoDetailList(reqMap);
         resultData.setTotalCount("0");
         
         if(companyInfoDetailList !=null && companyInfoDetailList.size()>0){
        	 resultData.setTotalCount(companyInfoDetailList.size()+"");
        	 if (exportFlag == false) {
        		 int endRow = pageIndex * pageSize;
        		 companyInfoDetailList = companyInfoDetailList.subList((pageIndex - 1) * pageSize, endRow > companyInfoDetailList.size() ? companyInfoDetailList.size() : endRow);
        	 }
             reqMap.put("pageIndex", pageIndex);
             reqMap.put("pageSize", pageSize);
             reqMap.put("curPage", curPage);
         	 resultData.setReturnData(companyInfoDetailList);
 	         resultData.setSuccess();
         }
         return resultData;
     }
     
}
  