package cn.com.eju.pmls.report.projectDetail.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.projectDetail.dto.ProjectDetailDto;
import cn.com.eju.pmls.report.projectDetail.service.PmlsProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2020/6/17.
 */
@RestController
@RequestMapping(value = "pmlsProjectDetail")
public class PmlsProjectDetailController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    @Autowired
    private PmlsProjectDetailService pmlsProjectDetailService;

    @RequestMapping(value = "/queryList/{param}", method = RequestMethod.GET)
    public String queryList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<ProjectDetailDto>> resultData = new ResultData<>();

        try {
            resultData = pmlsProjectDetailService.queryList(queryParam);
        } catch (Exception e) {
            resultData.setFail();
            logger.error("pmlsProjectDetail", "PmlsProjectDetailController", "queryList", param, null, "", "查询开发项目明细列表失败", e);
        }
        return resultData.toString();
    }
}
