package cn.com.eju.deal.staffMaintain.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.staffMaintain.MCenterUserDto;
import cn.com.eju.deal.staffMaintain.model.MCenterUser;
import cn.com.eju.deal.staffMaintain.service.MCenterUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * desc:管理人员维护
 * @author :zhenggang.Huang
 * @date   :2019年5月14日
 */
@RestController
@RequestMapping(value = "mCenterUser")
public class MCenterUserController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "mCenterUserService")
    MCenterUserService mCenterUserService;

    /**
     * desc:查询列表
     * 2019年5月14日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        //构建返回
        ResultData<List<Map<String, Object> >> resultData = new ResultData<>();
        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            if (queryParam.containsKey("searchKey")) {
                queryParam.put("searchKey", queryParam.get("searchKey").toString().trim());
            }

            resultData = mCenterUserService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "MCenterUserController", "queryList", "",
                    null, "", "", e);
        }
        return resultData.toString();

    }

    /**
     * desc:根据id查看和编辑
     * 2019年5月14日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id) {

        //构建返回
        ResultData<MCenterUser> resultData = new ResultData<>();
        try {
            resultData = mCenterUserService.getById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "MCenterUserController", "getById", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * desc:保存
     * 2019年5月14日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody String jsonDto) {
        //构建返回
        ResultData<MCenterUserDto> resultData = new ResultData<>();
        try {
            MCenterUserDto mCenterUserDto = JsonUtil.parseToObject(jsonDto, MCenterUserDto.class);

            resultData = mCenterUserService.save(mCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "MCenterUserController", "save", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * desc:跟新
     * 2019年5月14日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody String jsonDto) {
        //构建返回
        ResultData<MCenterUserDto> resultData = new ResultData<MCenterUserDto>();
        try {
            MCenterUserDto mCenterUserDto = JsonUtil.parseToObject(jsonDto, MCenterUserDto.class);

            resultData = mCenterUserService.update(mCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "MCenterUserController", "update", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }


    /**
     * desc:删除
     * 2019年5月14日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultData delete(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData<>();
        try {
            MCenterUserDto mCenterUserDto = JsonUtil.parseToObject(jsonDto, MCenterUserDto.class);
            resultData = mCenterUserService.delete(mCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "MCenterUserController", "delete", "",
                    null, "", "", e);
        }
        return resultData;
    }

    /**
     * 微信调用，校验该用户是否是微信管理人员
     * @param param
     * @return
     */
    @RequestMapping(value = "/getManagerCheck", method = RequestMethod.POST)
    public String getManagerCheck(@RequestBody String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData resultData = new ResultData();
        try
        {
            resultData = mCenterUserService.getManagerCheck(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("staffMaintain", "MCenterUserController", "getManagerCheck", "", null, "", "校验该用户是否是微信管理人员", e);
        }
        return resultData.toString();
    }

}
