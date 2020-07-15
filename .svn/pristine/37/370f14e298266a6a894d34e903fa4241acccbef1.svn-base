package cn.com.eju.deal.otherReport.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.otherReport.dto.LnkYjWyDto;
import cn.com.eju.deal.otherReport.service.LnkYjQtWyService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/10/25.
 */
@RestController
@RequestMapping(value = "lnkYjQtWy")
public class LnkYjQtWyController extends BaseController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "lnkYjQtWyService")
    private LnkYjQtWyService lnkYjQtWyService;

    /**
     * 查询LIST
     * @param param
     * @return
     */
    @RequestMapping(value="/getLnkYjQtWyList/{param}",method = RequestMethod.GET)
    public String getLnkYjQtWyList(@PathVariable String param)
    {
        //构建返回
        ResultData<List<LnkYjWyDto>> resultData = new ResultData<>();

        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = lnkYjQtWyService.getLnkYjQtWyList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("lnkYjQtWy", "LnkYjQtWyController", "getLnkYjQtWyList", "", null, "", "列表查询失败", e);
        }

        return resultData.toString();
    }

    /**
     * 导入新增
     * @param
     * @return
     */
    @RequestMapping(value = "/imputAdd", method = RequestMethod.POST)
    public String insertLnkQtWy(@RequestBody String jsonDto)
    {
        ResultData<String> resultData = new ResultData<>();

        try
        {
            LnkYjWyDto dto = JsonUtil.parseToObject(jsonDto, LnkYjWyDto.class);
            resultData = this.lnkYjQtWyService.insertLnkQtWy(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("lnkYjQtWy", "LnkYjQtWyController", "insertLnkQtWy", "", null, "", "数据导入失败", e);
        }
        return resultData.toString();
    }

}
