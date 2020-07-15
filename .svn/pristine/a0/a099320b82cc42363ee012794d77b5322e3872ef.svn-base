package cn.com.eju.pmls.rptStore.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.rptStore.dto.RptStoreDetailDto;
import cn.com.eju.pmls.rptStore.service.RptStoreDetailService;

@RestController
@RequestMapping("rptStoreDetail")
public class RptStoreDetailController extends BaseController {
	
    @Resource
    private RptStoreDetailService rptStoreDetailService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * desc:门店明细列表
     * 2020年6月17日
     */
    @RequestMapping(value = "/queryStoreDetailList/{param}", method = RequestMethod.GET)
    public ResultData<?> queryStoreDetailList(@PathVariable String param)
    {
    	//构建返回
        ResultData<List<RptStoreDetailDto>> resultData = new ResultData<>();

        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

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

            queryParam.remove("pageIndex");
            queryParam.remove("pageSize");
            queryParam.remove("curPage");

            queryParam.put("offset", (pageIndex - 1) * pageSize);
            queryParam.put("rows", pageSize);
            queryParam.put("total", 0);

            List<RptStoreDetailDto> list = rptStoreDetailService.queryStoreDetailList(queryParam);

            if (list != null) {
                int size = Integer.parseInt(queryParam.get("total").toString());
                resultData.setTotalCount(String.valueOf(size));
                resultData.setReturnData(list);
            }
        } catch (Exception e) {
        	logger.error("rptStore", "RptStoreDetailController", "queryStoreDetailList", "", null, "", "获取门店明细列表失败", e);

            resultData.setFail();
        }
        return resultData;
    }
    
}
