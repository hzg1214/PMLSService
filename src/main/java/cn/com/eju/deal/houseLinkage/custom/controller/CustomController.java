package cn.com.eju.deal.houseLinkage.custom.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.code.model.Code;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.houseLinkage.custom.CustomInfoDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportInfoDto;
import cn.com.eju.deal.dto.houseLinkage.report.ReportSearchResultDto;
import cn.com.eju.deal.houseLinkage.custom.service.CustomService;

/**   
* 服务层
* @author qianwei
* @date 2016年3月22日 下午6:05:44
*/

@RestController
@RequestMapping(value = "custom")
public class CustomController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "customService")
    private CustomService customService;

    /** 
     * 查询-对象
     * @param id
     * @return
     */
     @RequestMapping(value = "/{estateId}/{companyId}/{customerId}", method = RequestMethod.GET)
     public String getCustom(@PathVariable String estateId, @PathVariable String companyId, @PathVariable String customerId)
     {
         //构建返回
         ResultData<CustomInfoDto> resultData = new ResultData<CustomInfoDto>();
         
         //查询
        CustomInfoDto ctaDto = new CustomInfoDto();
        try
        {
            ctaDto = customService.getCustom(estateId, companyId, customerId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("houseLinkage", "CustomController", "getCustom", "", null, "", "查询-对象", e);
        }
     
         resultData.setReturnData(ctaDto);
     
         return resultData.toString();
     }
     
    /** 
    * 查询-list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<ReportSearchResultDto>> resultData = new ResultData<List<ReportSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换
        authParam(queryParam);
        
        try
        {
            resultData = customService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("houseLinkage", "CustomController", "list", "", null, "", "查询-list", e);
        }
        
        return resultData.toString();
    }
    /** 
     * 查询进度列表
     * @param param 查询条件
     * @return
     */
     @RequestMapping(value = "/process", method = RequestMethod.GET)
     public String getGroupList()
     {
         //构建返回
         ResultData<List<Code>> resultData= new ResultData<List<Code>>();
        		 
         List<Code> returnList = new ArrayList<Code>();
         List<Code> codeList = SystemParam.getCodeListByKey("135");
         for (int i = 0; i< codeList.size(); i++) {
        	 if (codeList.get(i).getDicCode() == 13507) {
            	 continue;
             }
        	 returnList.add(codeList.get(i));
         }
         resultData.setReturnData(returnList);
         return resultData.toString();
     }
}
