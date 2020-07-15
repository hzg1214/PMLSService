package cn.com.eju.deal.scene.commission.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import cn.com.eju.deal.scene.commission.service.SceneLinkCommissionService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/4/3.
 */
@RestController
@RequestMapping(value = "sceneLinkCommission")
public class SceneLinkCommissionController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "sceneLinkCommissionService")
    private SceneLinkCommissionService sceneLinkCommissionService;

    /**
     * 查询应收收入LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLinkCommissionList/{param}",method = RequestMethod.GET)
    public String getLinkCommissionList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<CommissionResultDto>> resultData = new ResultData<List<CommissionResultDto>>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        resultData = sceneLinkCommissionService.getLinkCommissionList(queryParam);

        return resultData.toString();
    }

    /**
     * 导入新增
     * @param
     * @return
     */
    @RequestMapping(value = "/imputAdd", method = RequestMethod.POST)
    public String insertLnkImport(@RequestBody String jsonDto)
    {
        ResultData<String> resultData = new ResultData<>();

        try
        {
            CommissionResultDto dto = JsonUtil.parseToObject(jsonDto, CommissionResultDto.class);
            resultData = this.sceneLinkCommissionService.insertLnkImport(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("scene", "SceneYSSRCommissionController", "insertLnkImport", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }
}
