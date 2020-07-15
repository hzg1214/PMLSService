package cn.com.eju.deal.staffMaintain.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.dto.staffMaintain.YFCenterUserDto;
import cn.com.eju.deal.staffMaintain.service.YFCenterUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "yfCenterUser")
public class YFCenterUserController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "yfCenterUserService")
    YFCenterUserService yfCenterUserService;

    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        //构建返回
        ResultData<List<YFCenterUserDto>> resultData = new ResultData<List<YFCenterUserDto>>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            if (queryParam.containsKey("searchKey")) {
                queryParam.put("searchKey", queryParam.get("searchKey").toString().trim());
            }

            resultData = yfCenterUserService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "queryList", "",
                    null, "", "", e);
        }
        return resultData.toString();

    }

    @RequestMapping(value = "queryYfCenterUser/{userCode}", method = RequestMethod.GET)
    public String queryYfCenterUser(@PathVariable String userCode) {
        //构建返回
        ResultData<Integer> resultData = new ResultData<Integer>();
        try {
            resultData = yfCenterUserService.queryYfCenterUser(userCode);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "queryYfCenterUser", "",
                    null, "", "", e);
        }
        return resultData.toString();

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id) {

        //构建返回
        ResultData<YFCenterUserDto> resultData = new ResultData<YFCenterUserDto>();
        try {
            resultData = yfCenterUserService.getById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "getById", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody String jsonDto) {
        //构建返回
        ResultData<YFCenterUserDto> resultData = new ResultData<YFCenterUserDto>();
        try {
            YFCenterUserDto yfCenterUserDto = JsonUtil.parseToObject(jsonDto, YFCenterUserDto.class);

            resultData = yfCenterUserService.save(yfCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "save", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody String jsonDto) {
        //构建返回
        ResultData<YFCenterUserDto> resultData = new ResultData<YFCenterUserDto>();
        try {
            YFCenterUserDto yfCenterUserDto = JsonUtil.parseToObject(jsonDto, YFCenterUserDto.class);

            resultData = yfCenterUserService.update(yfCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "update", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultData delete(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData<>();
        try {
            YFCenterUserDto yfCenterUserDto = JsonUtil.parseToObject(jsonDto, YFCenterUserDto.class);

            resultData = yfCenterUserService.delete(yfCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "delete", "",
                    null, "", "", e);
        }
        return resultData;
    }


    /**
     * 获取归属城市
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getAreaCity/{param}", method = RequestMethod.GET)
    public String getAreaCity(@PathVariable String param) {

        ResultData<List<City>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = yfCenterUserService.getAreaCity(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "getAreaCity", "", -1, "", "查询归属城市失败", e);
        }

        return resultData.toString();
    }

    /**
     * 获取归属中心
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCenterGroup/{param}", method = RequestMethod.GET)
    public String getCenterGroup(@PathVariable String param) {

        ResultData<List<CenterDto>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = yfCenterUserService.getCenterGroup(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "getCenterGroup", "", -1, "", "查询归属中心失败", e);
        }

        return resultData.toString();
    }

    @RequestMapping(value = "/getCenterAuth/{param}", method = RequestMethod.GET)
    public String getCenterAuth(@PathVariable String param) {

        ResultData<List<Map<?, ?> >> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {
            resultData = yfCenterUserService.getCenterAuth(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "YFCenterUserController", "getCenterAuth", "", -1, "", "查询中心权限失败", e);
        }

        return resultData.toString();
    }
}
