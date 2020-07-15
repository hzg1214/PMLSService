package cn.com.eju.deal.scene.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.scene.commission.service.LnkYjDyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**   
* 服务层
 * @author (luhaidan)
 * @date 2019/04/03
*/

@RestController
@RequestMapping(value = "lnkYjDy")
public class LnkYjDyController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "lnkYjDyService")
    private LnkYjDyService lnkYjDyService;

    /**
     * 查询垫佣LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLnkYjDyList/{param}",method = RequestMethod.GET)
    public String getLnkYjDyList(@PathVariable String param)
    {
    	//构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = lnkYjDyService.getLnkYjDyList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("lnkYjDy", "LnkYjDyController", "getLnkYjDyList", "", null, "", "列表查询失败", e);
        }
        
        return resultData.toString();
    }

    
   /**
     * 导入
     * @param
     * @return
     */
    @RequestMapping(value = "/dyImport", method = RequestMethod.POST)
    public String dyImport(@RequestBody String jsonDto)
    {
    	ResultData resultData = new ResultData<>();
        try
        {
            List queryParam = JsonUtil.parseToObject(jsonDto, List.class);
        	resultData = lnkYjDyService.dyImport(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("lnkYjDy", "LnkYjDyController", "dyImport", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }
    

}
