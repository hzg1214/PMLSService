package cn.com.eju.deal.open.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
//import cn.com.eju.deal.dubbo.impl.IOPServiceImpl;
import cn.com.eju.deal.open.model.OPCompanyDto;
import cn.com.eju.deal.open.service.APIOPService;

/**   
* 提供给OP系统的API
* @author huan.zhang
* @date 2016年10月27日 下午2:39:16
*/
@RestController
@RequestMapping(value = "crm/op")
public class APIOPController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private APIOPService apiOPService;
    
/*    @Resource
    private IOPServiceImpl iOPServiceImpl;
    */
    
    
    /** 
     * 查询-对象
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "company/{param}", method = RequestMethod.GET)
    public String getCompanyInfo(@PathVariable String param)
    {
        //构建返回
        ResultData<List<OPCompanyDto>> resultData = new ResultData<List<OPCompanyDto>>();
        try
        {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            String comString  = (String)queryParam.get("companyNo");
                if(comString!=null && !"".equals(comString)){
                    //查询
                    List<OPCompanyDto> companyInfoDtoList = apiOPService.getCompanyInfo(comString);
                    resultData.setReturnData(companyInfoDtoList);
                   // resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
                }else{
                    resultData.setFail();
                    resultData.setReturnData(null);
                    resultData.setReturnMsg("没有公司编码");
                    return resultData.toString();
                }
           
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("APIOP", "APIOPController", "getCompanyInfo", "companyName", 1, "", "获取公司信息失败！", e);
        }
        return resultData.toString();
    }
    
    /**
     * 【描述】: 提供给op的http接口
     *
     * @author yinkun
     * @date: 2017年11月9日 下午2:53:41
     * @param companyNo
     * @return
     *//*
    @RequestMapping(value = "companyRelate/{companyNo}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<Map<String, Object>> getCompanyReleteInfo(@PathVariable String companyNo) {
        return iOPServiceImpl.getCompanyReleteInfo(companyNo);
    }
    
    *//**
     *  	【描述】: 提供给op的http接口
     *	 保证金到账
     * @author zhenggang.Huang
     * @date: 2019年02月19日
     * @param 
     * @return
     *//*
    @RequestMapping(value = "/getDeposit", method = RequestMethod.POST)
    public String getDeposit(@RequestBody String param) {
    	return iOPServiceImpl.getDepositInfo(param);
    }
    
    *//**
     *  	【描述】: 提供给op的http接口
     *	 公盘会员
     * @author zhenggang.Huang
     * @date: 2019年02月19日
     * @param 
     * @return
     *//*
    @RequestMapping(value = "/getShareAccount", method = RequestMethod.POST)
    public String getShareAccount(@RequestBody String param) {
    	return iOPServiceImpl.getShareAccount(param);
    }*/
}
