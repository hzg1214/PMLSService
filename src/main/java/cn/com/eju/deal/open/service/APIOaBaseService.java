package cn.com.eju.deal.open.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.util.JsonUtil;

/**   
* 调用REST服务类的基类
* @author (li_xiaodong)
* @date 2016年2月16日 下午3:10:06
* @param <T>
*/
public abstract class APIOaBaseService<T>
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    /**
    * Spring提供的用于访问Rest服务的客户端
    */
    private RestTemplate restTemplate = new RestTemplate();
    
    /** 
    * post 请求 -- 鉴权
    * @param url
    * @param dto
    * @return
    * @throws Exception
    */
    public String post(String url, String jsonStr)
        throws Exception
    {
        
        //日志记录begin
        long startTime = System.currentTimeMillis();
        logger.info("request url=" + url + "; request params:" + jsonStr);
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.add("Content-Type", "application/soap+xml; charset=utf-8");
        
        
        HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
        
        String backResult = null;
        
        try
        {
            //HTTP POST
            backResult = restTemplate.postForObject(url, formEntity, String.class);
        }
        catch (Exception e)
        {
            throw e;
        }
        
        //日志记录end
        long endTime = System.currentTimeMillis();
        logger.info("response url=" + url + "; response data:" + JsonUtil.parseToJson(backResult) + "; cost time:("
            + (endTime - startTime) + ") ms");
        
        return backResult;
    }
    
}
