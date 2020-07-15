package cn.com.eju.deal.accountproject.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.accountproject.model.AccountProject;
import cn.com.eju.deal.accountproject.service.AccountProjectService;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.accountproject.AccountProjectDto;
import cn.com.eju.deal.dto.accountproject.AccountProjectList;

/**
 * desc:核算主体维护
 * @author :zhenggang.Huang
 * @date   :2019年7月26日
 */
@RestController
@RequestMapping(value = "accountProject")
public class AccountProjectController {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "accountProjectService")
    AccountProjectService accountProjectService;

    /**
     * desc:查询列表
     * 2019年7月26日
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

            resultData = accountProjectService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("accountProject", "AccountProjectController", "queryList", "",
                    null, "", "", e);
        }
        return resultData.toString();

    }

    /**
     * desc:获取核算主体下拉列表
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/getAccountProjectList/{param}", method = RequestMethod.GET)
    public String getAccountProjectList(@PathVariable String param) {

        ResultData<List<AccountProjectList>> resultData = new ResultData<>();

        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        try {

            resultData = accountProjectService.getAccountProjectList(queryParam);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("staffMaintain", "AccountProjectController", "getAccountProjectList", "", -1, "", "获取核算主体下拉列表失败", e);
        }

        return resultData.toString();
    }
    
    /**
     * desc:根据id查看和编辑
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id) {

        //构建返回
        ResultData<AccountProject> resultData = new ResultData<>();
        try {
            resultData = accountProjectService.getById(id);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("accountProject", "AccountProjectController", "getById", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * desc:保存
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestBody String jsonDto) {
        //构建返回
        ResultData<AccountProjectDto> resultData = new ResultData<>();
        try {
        	AccountProjectDto mCenterUserDto = JsonUtil.parseToObject(jsonDto, AccountProjectDto.class);

            resultData = accountProjectService.save(mCenterUserDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("accountProject", "accountProjectController", "save", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }

    /**
     * desc:跟新
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestBody String jsonDto) {
        //构建返回
        ResultData<AccountProjectDto> resultData = new ResultData<AccountProjectDto>();
        try {
        	AccountProjectDto accountProjectDto = JsonUtil.parseToObject(jsonDto, AccountProjectDto.class);

            resultData = accountProjectService.update(accountProjectDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("accountProject", "AccountProjectController", "update", "",
                    null, "", "", e);
        }
        return resultData.toString();
    }


    /**
     * desc:删除
     * 2019年7月26日
     * author:zhenggang.Huang
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultData delete(@RequestBody String jsonDto) {
        ResultData resultData = new ResultData<>();
        try {
        	AccountProjectDto accountProjectDto = JsonUtil.parseToObject(jsonDto, AccountProjectDto.class);
            resultData = accountProjectService.delete(accountProjectDto);

        } catch (Exception e) {
            resultData.setFail();
            logger.error("accountProject", "AccountProjectController", "delete", "",
                    null, "", "", e);
        }
        return resultData;
    }


}
