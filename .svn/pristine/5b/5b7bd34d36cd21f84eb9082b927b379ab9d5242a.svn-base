package cn.com.eju.deal.followDetail.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.followDetail.FollowDetailDto;
import cn.com.eju.deal.followDetail.service.FollowDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "followDetail")
public class FollowDetailController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private FollowDetailService followDetailService;

    /**
     * 跟进明细表
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/query/{param}", method = RequestMethod.GET)
    public String query(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<FollowDetailDto>> resultData = new ResultData<>();
        try {
            resultData = followDetailService.query(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("follow", "FollowDetailController", "query", "", null, "", "跟进明细失败", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/getMapInfo/{param}", method = RequestMethod.GET)
    public String getMapInfo(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<FollowDetailDto>> resultData = new ResultData<>();
        try {
            resultData = followDetailService.getMapInfo(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("follow", "FollowDetailController", "getMapInfo", "", null, "", "查看地图失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/getSignDetail", method = RequestMethod.POST)
    public String getSignDetail(@RequestBody String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<FollowDetailDto> resultData = new ResultData<>();
        try {
            resultData = followDetailService.getSignDetail(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("follow", "FollowDetailController", "getSignDetail", "", null, "", "查看签到详情失败", e);
        }

        return resultData.toString();
    }
}
