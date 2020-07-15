package cn.com.eju.deal.Report.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.com.eju.deal.base.helper.LogHelper;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.Report.model.ExcelTask;
import cn.com.eju.deal.Report.service.IExcelTaskService;


@RestController
@RequestMapping("excelTask")
public class ExcelTaskController {

	/**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
	
	@Resource
	private IExcelTaskService excelTaskService;

    /**
     * 查询Templet列表
     * @param request
     * @param mop
     * @return
     */
    @RequestMapping(value = "queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param,HttpServletRequest request, ModelMap mop)
    {
    	ResultData<List<ExcelTask>> resultData = new ResultData<List<ExcelTask>>();
    	
        // 获取请求参数
    	Map<String, Object> queryParam = JsonUtil.parseToObject(param,Map.class);
    	
    	try{
	        // 获取页面显示数据
	    	resultData = excelTaskService.queryExcelTaskByParam(queryParam);
    	}catch(Exception e)
    	{
	   		 resultData.setFail();
	   		 logger.error("ExcelTask", "ExcelTaskController", "queryList", "", -1, "", "报表列表查询失败", e);
    	}
        
    	return resultData.toString();
    }
    
    
    @RequestMapping(value ="delete",method = RequestMethod.POST)
    public String delete(@RequestBody String basScheduleDtoJson,HttpServletRequest request, HttpServletResponse response)
        throws SQLServerException
    {
    	ReturnView<String, Object> jv = new ReturnView<String, Object>();
    	ExcelTask excelTask = JsonUtil.parseToObject(basScheduleDtoJson,
    			ExcelTask.class);
    	String TpAddress=excelTask.getDownloadurl();
        if (TpAddress != null && !"".equals(TpAddress))
        {
        	//获取模板地址 删除模板
        	if(StringUtil.isNotEmpty(TpAddress)){
        		File file=new File(TpAddress);
        		file.delete();
        	}
        	ExcelTask obj = new ExcelTask();
        	obj.setId(excelTask.getId());
        	obj.setStatus(3);
        	excelTaskService.update(obj);
            jv.put("isDelete", 1);
            jv.setSuccess();
            
        }
        else
        {
            jv.setFail();
        }
        return jv.toString();
    }
    
}
