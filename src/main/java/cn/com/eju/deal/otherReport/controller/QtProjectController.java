package cn.com.eju.deal.otherReport.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.scene.estate.SceneEstateSearchResultDto;
import cn.com.eju.deal.otherReport.service.QtProjectService;

/**
 * desc:其他收入-项目
 * @author :zhenggang.Huang
 * @date   :2019年10月12日
 */

@RestController
@RequestMapping(value = "qtProject")
public class QtProjectController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "qtProjectService")
    private QtProjectService qtProjectService;
    
    /** 
    * 查询-list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<SceneEstateSearchResultDto>> resultData = new ResultData<List<SceneEstateSearchResultDto>>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        
        //权限控制,参数转换   
        authParam(queryParam);
        //案场-项目  看到所有用户数据    
        queryParam.remove("userIdList");
        try
        {
            resultData = qtProjectService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("qtProject", "QtProjectController", "list", "", null, "", "查询-list", e);
        }
        return resultData.toString();
    }
    
}
