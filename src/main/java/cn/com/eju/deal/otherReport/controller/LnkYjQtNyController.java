package cn.com.eju.deal.otherReport.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.otherReport.dto.LnkYjNyDto;
import cn.com.eju.deal.otherReport.service.LnkYjQtNyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/10/28.
 */
@RestController
@RequestMapping(value = "lnkYjQtNy")
public class LnkYjQtNyController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "lnkYjQtNyService")
    private LnkYjQtNyService lnkYjQtNyService;

    /**
     * 查询外佣LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLnkYjQtNyList/{param}",method = RequestMethod.GET)
    public String getLnkYjQtNyList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<LnkYjNyDto>> resultData = new ResultData<>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = lnkYjQtNyService.getLnkYjQtNyList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("lnkYjQtNy", "LnkYjQtNyController", "getLnkYjQtNyList", "", null, "", "列表查询失败", e);
        }

        return resultData.toString();
    }

    /**
     * 导入新增
     * @param
     * @return
     */
    @RequestMapping(value = "/imputAdd", method = RequestMethod.POST)
    public String insertLnkQtNy(@RequestBody String jsonDto)
    {
        ResultData<String> resultData = new ResultData<>();

        try
        {
            LnkYjNyDto dto = JsonUtil.parseToObject(jsonDto, LnkYjNyDto.class);
            resultData = this.lnkYjQtNyService.insertLnkQtNy(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("lnkYjQtNy", "LnkYjQtNyController", "insertLnkQtNy", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }

}
