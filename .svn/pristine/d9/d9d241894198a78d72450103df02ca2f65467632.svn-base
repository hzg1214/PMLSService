package cn.com.eju.deal.contract.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.util.HttpClientUtil;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.core.util.JsonUtil;

/**
 * service层
 */
@Service("oPCompanyHttpService")
public class OPCompanyHttpService extends BaseService<Contract> {
	 /**
	    * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    public String createCmpl(String param){
	   	 String url = SystemParam.getWebConfigValue("opApiUrl") + "op-company/create";
	   	 String rspParam = HttpClientUtil.jsonPostUtf8(url, param);
	   	 logger.info("调用OP createCmpl param="+param+"&rspParam="+rspParam);
	   	 return rspParam;
    }
    
    //无调用
    public String updateCmpl(String param){
    	
    	return null;
    }
     
    //无调用
    public String delCmpl(String param){
    	
    	return null;
    }
      

     public String noticeOPCompany(String companyNo){
    	 Map<String,Object> reqMap = new HashMap<String,Object>();
    	 reqMap.put("companyNo", companyNo);
    	 String param = JsonUtil.parseToJson(reqMap);
    	 
    	 String url = SystemParam.getWebConfigValue("opApiUrl") + "op-company/open";
    	 String rspParam = HttpClientUtil.jsonPostUtf8(url, param);
    	 logger.info("调用OP noticeOPCompany param="+param+"&rspParam="+rspParam);
    	 return rspParam;
     }
}
