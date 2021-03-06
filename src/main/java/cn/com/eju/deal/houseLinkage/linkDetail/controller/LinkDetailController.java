package cn.com.eju.deal.houseLinkage.linkDetail.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.dto.houseLinkage.linkProjectDetail.LinkProjectDetailDto;
import cn.com.eju.deal.dto.houseLinkage.linkProjectTrace.LinkProjectTraceDto;
import cn.com.eju.deal.houseLinkage.linkDetail.service.LinkDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Controller层
 * 联动明细
 *
 * @author tanlang
 */
@RestController
@RequestMapping(value = "linkDetail")
public class LinkDetailController extends BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private LinkDetailService linkDetailService;

    /**
     * 核算主体
     * @return
     */
    @RequestMapping(value = "getAccountProject", method = RequestMethod.GET)
    public String getAccountProject() {
        ResultData resultData = new ResultData<>();
        try {
            resultData = linkDetailService.getAccountProject();
        } catch (Exception e) {
            logger.error("LinkDetail", "LinkDetailController", "getAccountProject", "", 0, "", "查询核算主体异常", e);
            resultData.setFail();
        }

        return resultData.toString();
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryLinkDetailList/{param}", method = RequestMethod.GET)
    public ResultData queryLinkDetailList(@PathVariable String param) {
        //构建返回
        ResultData resultData = null;
        try {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            resultData = linkDetailService.queryLinkDetailList(queryParam);
        } catch (Exception e) {
        	if(resultData == null){
                resultData = new ResultData();
            }
            logger.error("LinkDetail", "LinkDetailController", "queryLinkDetailList", "", 0, "", "查询list异常", e);
            resultData.setFail();
        }
        return resultData;
    }

    /**
     * 查询--list
     */
    @RequestMapping(value = "queryLinkProjectDetailList/{param}", method = RequestMethod.GET)
    public String queryLinkProjectDetailList(@PathVariable String param) {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //构建返回
        ResultData<List<LinkProjectDetailDto>> resultData = new ResultData<>();

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

        queryParam.remove("pageIndex");
        queryParam.remove("pageSize");
        queryParam.remove("curPage");

        queryParam.put("offset", (pageIndex - 1) * pageSize);
        queryParam.put("rows", pageSize);
        queryParam.put("total", 0);

        try {
            List<LinkProjectDetailDto> list = linkDetailService.queryLinkProjectDetailList(queryParam);
            if (list != null) {
                int size = Integer.parseInt(queryParam.get("total").toString());
                resultData.setTotalCount(String.valueOf(size));
                resultData.setReturnData(list);
            }

        } catch (Exception e) {
            logger.error("LinkDetail", "LinkDetailController", "queryLinkProjectDetailList", "", 0, "", "查询list异常", e);
            logger.error("联动项目明细发生异常！param:"+param,e);
            resultData.setFail();
        }

        return resultData.toString();
    }

    /**
     * 查询--list pmls
     */
    @RequestMapping(value = "queryLinkProjectTraceList/{param}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> queryLinkProjectTraceList(@PathVariable String param) {
        //构建返回
        ResultData<List<LinkProjectTraceDto>> resultData = new ResultData<>();

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

            List<LinkProjectTraceDto> list = linkDetailService.queryLinkProjectTraceList(queryParam);

            if (list != null) {
                int size = Integer.parseInt(queryParam.get("total").toString());
                resultData.setTotalCount(String.valueOf(size));
                resultData.setReturnData(list);
            }
        } catch (Exception e) {
            logger.error("LinkDetail", "LinkDetailController", "queryLinkProjectTraceList", "", 0, "", "查询list异常", e);

            resultData.setFail();
        }

        return resultData;
    }

    @RequestMapping(value = "queryCityList/{param}", method = RequestMethod.GET)
    @ResponseBody
    public ResultData<?> queryCityList(@PathVariable String param) {
        //构建返回
        ResultData<List<CityDto>> resultData = new ResultData<>();

        try {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

            List<CityDto> list = linkDetailService.queryCityList(queryParam);
            resultData.setReturnData(list);

        } catch (Exception e) {
            logger.error("LinkDetail", "LinkDetailController", "queryCityList", param, 0, "", "查询用户关联城市异常", e);

            resultData.setFail();
        }

        return resultData;
    }


    @RequestMapping(value = "export/{param}", method = RequestMethod.GET)
    public ResultData<String> export(@PathVariable String param) {
        logger.info("联动明细##CSV生成 start！"+param+"###################");
        ResultData<String> resultData = new ResultData<>();
        Map<String, Object> reqMap = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = linkDetailService.export(reqMap);
        } catch (Exception e) {
            logger.error("联动明细##CSV生成 error！"+param+"###################",e);
            resultData.setFail("联动明细CSV生成异常！");
            logger.error("LinkDetail", "LinkDetailController",
                    "export", param, -1, "", "联动明细CSV生成异常！", e);
        }
        return resultData;
    }

}
