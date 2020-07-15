package cn.com.eju.deal.scene.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.scene.commission.service.PmlsLnkYjFyFyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/12/18.
 */
@RestController
@RequestMapping(value = "pmlsLnkYjFyFy")
public class PmlsLnkYjFyFyController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "pmlsLnkYjFyFyService")
    private PmlsLnkYjFyFyService pmlsLnkYjFyFyService;

    @RequestMapping(value="/queryList/{param}",method = RequestMethod.GET)
    public String queryList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<Map<String, Object>>> resultData = new ResultData<List<Map<String, Object>>>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = pmlsLnkYjFyFyService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("pmlsLnkYjFyFy", "PmlsLnkYjFyFyController", "queryList", param, null, "", "列表查询失败", e);
        }

        return resultData.toString();
    }

    /**
     * 导入
     * @param
     * @return
     */
    @RequestMapping(value = "/insertLinkFyFy", method = RequestMethod.POST)
    public String insertLinkFyFy(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData<>();
        try
        {
            List queryParam = JsonUtil.parseToObject(jsonDto, List.class);
            resultData = pmlsLnkYjFyFyService.insertLinkFyFy(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("pmlsLnkYjFyFy", "PmlsLnkYjFyFyController", "insertLinkFyFy", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }


}
