package cn.com.eju.deal.op.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.op.service.OpService;

/**
 * op接口Controller
 */
@RestController
@RequestMapping(value = "opApi")
public class OpController extends BaseService{

    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());

     @Resource(name = "opService")
     private OpService opService;
     
    /** 
    * OP返回判定接口
    * @param companyNo 公司编号
    * @return
    */
    @RequestMapping(value = "opRollBackCRM", method = RequestMethod.GET)
    public String opRollBackCRM(@RequestParam("companyNo") String companyNo)
    {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        
        logger.info("op回传CRM start#####请求参数#companyNo="+companyNo);
    	if(StringUtil.isEmpty(companyNo)){
            resultData.setFail("参数companyNo不能为空!");
            return resultData.toString();
    	}
        try
        {
        	int result = opService.opRollBackCRM(companyNo);
        	resultData.setSuccess("成功");
        	resultData.setTotalCount(result+"");
        }
        catch (Exception e)
        {
            logger.error("Op", "OpController", "", "", -1, "", "op回传CRM业务处理出现异常！", e);
            resultData.setFail("CRM业务处理出现异常!");
            return resultData.toString();
        }
        logger.info("op回传CRM end#####请求参数#companyNo="+companyNo+"，返回信息="+resultData.toString());
        return resultData.toString();
    }
}
