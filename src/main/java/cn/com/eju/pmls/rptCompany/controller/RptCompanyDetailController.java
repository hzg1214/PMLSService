package cn.com.eju.pmls.rptCompany.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.pmls.rptCompany.dto.RptCompanyDetailDto;
import cn.com.eju.pmls.rptCompany.service.RptCompanyDetailService;

@RestController
@RequestMapping("rptCompanyDetail")
public class RptCompanyDetailController extends BaseController {
	
    @Resource
    private RptCompanyDetailService rptCompanyDetailService;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * desc:经纪公司明细列表
     * 2020年6月17日
     */
    @RequestMapping(value = "/queryCompanyDetailList/{param}", method = RequestMethod.GET)
    public ResultData<?> queryCompanyDetailList(@PathVariable String param)
    {
    	
    	//构建返回
        ResultData<List<RptCompanyDetailDto>> resultData = new ResultData<>();

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

            List<RptCompanyDetailDto> list = rptCompanyDetailService.queryCompanyDetailList(queryParam);

            if (list != null) {
                int size = Integer.parseInt(queryParam.get("total").toString());
                resultData.setTotalCount(String.valueOf(size));
                resultData.setReturnData(list);
            }
        } catch (Exception e) {
        	logger.error("rptCompany", "RptCompanyDetailController", "queryCompanyDetailList", "", null, "", "获取经纪公司明细列表失败", e);

            resultData.setFail();
        }
//        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
//        ResultData resultData = new ResultData();
//        try{
//            resultData = rptCompanyDetailService.queryCompanyDetailList(queryParam);
//        }catch (Exception e)
//        {
//            resultData.setFail();
//            logger.error("rptCompany", "RptCompanyDetailController", "queryCompanyDetailList", "", null, "", "获取经纪公司明细列表失败", e);
//        }
//        return resultData.toString();
        return resultData;
    }
    
	/**
	 * desc:获取表头
	 * 2020年6月18日
	 */
    @RequestMapping(value = "/getTitle", method = RequestMethod.POST)
    public String getTitle(@RequestBody String json)
    {
        ResultData resultData = new ResultData();
        Map<String, Object> queryParam = JsonUtil.parseToObject(json, Map.class);
        try{
        	resultData = rptCompanyDetailService.getTitle(queryParam);
        }catch (Exception e)
        {
            resultData.setFail();
            logger.error("rptCompany", "RptCompanyDetailController", "getTitle", "",
					null, "", "获取经纪公司/门店明细表头失败", e);
        }
        return resultData.toString();
    }
    
}
