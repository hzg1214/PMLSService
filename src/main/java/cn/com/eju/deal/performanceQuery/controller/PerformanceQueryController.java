package cn.com.eju.deal.performanceQuery.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.performanceQuery.PerformanceQueryDto;
import cn.com.eju.deal.performanceQuery.service.PerformanceQueryService;

/**
 * 业绩查询报表
 */
@RestController
@RequestMapping(value="performanceQuery")
public class PerformanceQueryController {
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Autowired
	private PerformanceQueryService performanceQueryService;

    /**
     * 查询条件
     * @param queryParam
     */
    private Map<String, Object> ChangeParam(Map<String, Object> queryParam)
    {
        try {

            queryParam.put("dateStart", queryParam.get("dateStart").toString());
            queryParam.put("dateEnd", queryParam.get("dateEnd").toString());
            if (queryParam.containsKey("storeNo"))
                queryParam.put("storeNo", queryParam.get("storeNo").toString());
            else
                queryParam.put("storeNo", "");
            if (queryParam.containsKey("storeAddress"))
                queryParam.put("storeAddress", queryParam.get("storeAddress").toString());
            else
                queryParam.put("storeAddress", "");
            if (queryParam.containsKey("companyName"))
                queryParam.put("companyName", queryParam.get("companyName").toString());
            else
                queryParam.put("companyName", "");
            if (queryParam.containsKey("cityId"))
                queryParam.put("cityId", queryParam.get("cityId").toString());
            else
                queryParam.put("cityId", "");
            if (queryParam.containsKey("centerId"))
                queryParam.put("centerId", queryParam.get("centerId").toString());
            else
                queryParam.put("centerId", "");
            if (queryParam.containsKey("sumBy"))
                queryParam.put("sumBy", queryParam.get("sumBy").toString());
            else
                queryParam.put("sumBy", "");
            if (queryParam.containsKey("sortBy"))
                queryParam.put("sortBy", queryParam.get("sortBy").toString());
            else
                queryParam.put("sortBy", "");
            if (queryParam.containsKey("cookieName"))
                queryParam.put("cookieName", queryParam.get("cookieName").toString());
            else
                queryParam.put("cookieName", "");
            if (queryParam.containsKey("optFlag"))
                queryParam.put("optFlag", queryParam.get("optFlag").toString());
            else
                queryParam.put("optFlag", "search");

            queryParam.put("userId", queryParam.get("userId").toString());

        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return queryParam;
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryPerformanceList/{param}", method = RequestMethod.GET)
    public String queryPerformanceList(@PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        Map<String, Object> paramdata = new HashMap<String, Object>();

        paramdata = ChangeParam(queryParam);
        ResultData<List<PerformanceQueryDto>> resultData = new ResultData<List<PerformanceQueryDto>>();

        int pageIndex = 1;
        int pageSize = 10;
        int curPage = 1;
        boolean exportFlag = false;

        if (queryParam.get("pageIndex") != null)
            pageIndex = Integer.parseInt(queryParam.get("pageIndex").toString());
        if (queryParam.get("pageSize") != null)
            pageSize = Integer.parseInt(queryParam.get("pageSize").toString());
        if (queryParam.get("curPage") != null)
            curPage = Integer.parseInt(queryParam.get("curPage").toString());
        if (queryParam.get("optFlag") != null && queryParam.get("optFlag").toString().equals("export"))
            exportFlag = true;

        paramdata.remove("pageIndex");
        paramdata.remove("pageSize");
        paramdata.remove("curPage");

        List<PerformanceQueryDto> list = performanceQueryService.queryPerformanceList((paramdata));
        if(list != null)
        {
            int size = list.size();
            resultData.setTotalCount(String.valueOf(size));

            if (exportFlag == false) {
                int endRow = pageIndex * pageSize;
                list = list.subList((pageIndex - 1) * pageSize, endRow > size ? size : endRow);
            }

            paramdata.put("pageIndex", pageIndex);
            paramdata.put("pageSize", pageSize);
            paramdata.put("curPage", curPage);

            resultData.setReturnData(list);
        }

        return resultData.toString();
    }

}
