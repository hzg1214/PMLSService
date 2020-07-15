package cn.com.eju.pmls.report.linkDetail.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.report.linkDetail.dto.LinkProjectPartATraceDto;
import cn.com.eju.pmls.report.linkDetail.service.LinkProjectPartATraceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "linkProjectPartATraceController")
public class LinkProjectPartATraceController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    LinkProjectPartATraceService linkProjectPartATraceService;

    @RequestMapping(value = "queryLinkProjectPartATraceList/{param}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> queryLinkProjectPartATraceList(@PathVariable String param) {
        //构建返回
        ResultData<List<LinkProjectPartATraceDto>> resultData = new ResultData<>();

        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

            int pageIndex = 1;
            int pageSize = 10;
            int curPage = 1;

            if (queryParam.get("pageIndex") != null)
                pageIndex = Integer.parseInt(queryParam.get("pageIndex").toString());
            if (queryParam.get("pageSize") != null)
                pageSize = Integer.parseInt(queryParam.get("pageSize").toString());
            if (queryParam.get("curPage") != null)
                curPage = Integer.parseInt(queryParam.get("curPage").toString());

            queryParam.remove("pageIndex");
            queryParam.remove("pageSize");
            queryParam.remove("curPage");

            queryParam.put("offset", (pageIndex - 1) * pageSize);
            queryParam.put("rows", pageSize);
            queryParam.put("total", 0);

            List<LinkProjectPartATraceDto> list = linkProjectPartATraceService.queryLinkProjectPartATraceList(queryParam);

            if (list != null) {
                int size = Integer.parseInt(queryParam.get("total").toString());
                resultData.setTotalCount(String.valueOf(size));
                resultData.setReturnData(list);
            }
        } catch (Exception e) {
            logger.error("LinkDetail", "LinkProjectPartATraceController", "queryLinkProjectPartATraceList", "", 0, "", "查询list异常", e);

            resultData.setFail();
        }

        return resultData;
    }

}
