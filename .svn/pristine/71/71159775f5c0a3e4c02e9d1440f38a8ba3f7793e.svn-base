package cn.com.eju.deal.otherReport.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.otherReport.dto.LnkYjFyDto;
import cn.com.eju.deal.otherReport.service.LnkYjQtFyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/10/29.
 */
@RestController
@RequestMapping(value = "lnkYjQtFy")
public class LnkYjQtFyController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "lnkYjQtFyService")
    private LnkYjQtFyService lnkYjQtFyService;

    /**
     * 查询LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLnkYjQtFyList/{param}",method = RequestMethod.GET)
    public String getLnkYjQtFyList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<LnkYjFyDto>> resultData = new ResultData<>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = lnkYjQtFyService.getLnkYjQtFyList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("lnkYjQtFy", "LnkYjQtFyController", "getLnkYjQtFyList", "", null, "", "列表查询失败", e);
        }

        return resultData.toString();
    }

    /**
     * 导入新增
     * @param
     * @return
     */
    @RequestMapping(value = "/imputAdd", method = RequestMethod.POST)
    public String insertLnkQtFy(@RequestBody String jsonDto)
    {
        ResultData<String> resultData = new ResultData<>();

        try
        {
            LnkYjFyDto dto = JsonUtil.parseToObject(jsonDto, LnkYjFyDto.class);
            resultData = this.lnkYjQtFyService.insertLnkQtFy(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("lnkYjQtFy", "LnkYjQtFyController", "insertLnkQtFy", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }

}
