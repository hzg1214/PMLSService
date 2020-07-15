package cn.com.eju.deal.scene.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.scene.commission.service.LnkYjNyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**   
* 服务层
 * @author kunyin
 * @date 2019/04/09
*/

@RestController
@RequestMapping(value = "lnkYjNy")
public class LnkYjNyController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "lnkYjNyService")
    private LnkYjNyService lnkYjNyService;

    /**
     * 查询内佣LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLnkYjNyList/{param}",method = RequestMethod.GET)
    public String getLnkYjDyList(@PathVariable String param)
    {
    	//构建返回
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = lnkYjNyService.getLnkYjNyList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("lnkYjNy", "LnkYjNyController", "getLnkYjNyList", param, null, "", "列表查询失败", e);
        }
        return resultData.toString();
    }

    
   /**
     * 导入
     * @param
     * @return
     */
    @RequestMapping(value = "/nyImport", method = RequestMethod.POST)
    public String dyImport(@RequestBody String jsonDto)
    {
    	ResultData resultData = new ResultData<>();
        try
        {
            List queryParam = JsonUtil.parseToObject(jsonDto, List.class);
        	resultData = lnkYjNyService.nyImport(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("lnkYjNy", "LnkYjNyController", "nyImport", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }
    

}
