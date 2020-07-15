package cn.com.eju.deal.open.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.code.service.CommonCodeService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;

/**
 * CRM dictionary服务
 *
 * @author (li_xiaodong)
 * @date 2016年1月19日 下午6:05:44
 */

@RestController
@RequestMapping(value = "crm/dictionary")
public class DictionaryController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "commonCodeService")
    private CommonCodeService commonCodeService;
    
    /**
     * 查询-对象
     *
     * @param id
     * @return
     */
//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String getCodeListByKey(@PathVariable String typeId)
//    {
//        //构建返回
//        ResultData<List<Code>> resultData = new ResultData<>();
//        
//        //查询
//        try
//        {
//            
//            List<Code> codeList = SystemParam.getCodeListByKey(typeId);
//            
//            resultData.setReturnData(codeList);
//            
//        }
//        catch (Exception e)
//        {
//            logger.error("Open",
//                "DictionaryController",
//                "getCodeListByKey",
//                "input param:  typeId = " + typeId,
//                -1,
//                "",
//                "外部系统获取字典list失败",
//                e);
//            
//            resultData.setFail();
//        }
//        
//        return resultData.toString();
//    }
    

    
    /** 
     * 查询-全部对象
     * @param id
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAll()
    {
        
        Map<?, ?> queryParam = new HashMap<String, Object>();
        
        //查询
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = commonCodeService.queryList(queryParam);
        }
        catch (Exception e)
        {   
            resultData.setFail();
            logger.error("Open",
                "DictionaryController",
                "getAll",
                "input param",
                -1,
                "",
                "外部系统获取字典失败",
                e);
        }
        
        return resultData.toString();
    }
    
}
