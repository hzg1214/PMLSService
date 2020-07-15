/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:StoreBizStopController.java
 * Package Name:cn.com.eju.deal.store.controller
 * Date:2017年9月26日下午5:29:37
 */
package cn.com.eju.deal.store.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.store.model.StoreBizStop;
import cn.com.eju.deal.store.service.StoreBizStopService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * ClassName: StoreBizStopController <br/>
 * Description: 门店停业上报 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月26日 下午5:29:37 <br/>
 * @version V1.0
 */
@RestController
@RequestMapping("/storeBizStop")
public class StoreBizStopController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private StoreBizStopService storeBizStopService;
    
    /**
     * 【描述】: 查询停业上报列表
     *
     * @author yinkun
     * @date: 2017年9月26日 下午5:35:08
     * @param param
     * @return
     */
    @RequestMapping(value = "/list/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param) {
        ResultData<List<StoreBizStop>> resultData = new ResultData<>();
        Map <String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        try {
            resultData = storeBizStopService.queryList(queryParam);
            resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
        }catch(Exception e) {
            logger.error("storeBizStop", "StoreBizStopController", "list", queryParam.toString(), 0, "", "查询停业上报列表异常", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /**
     * 【描述】: 查看停业上报明细
     *
     * @author yinkun
     * @date: 2017年9月27日 下午12:00:01
     * @param stopId
     * @return
     */
    @RequestMapping(value = "/{stopId}", method = RequestMethod.GET)
    public String view(@PathVariable Integer stopId) {
        ResultData<StoreBizStop> resultData = new ResultData<>();
        
        try {
            StoreBizStop bean = storeBizStopService.getByStopId(stopId);
            resultData.setReturnData(bean);
        }catch(Exception e) {
            logger.error("storeBizStop", "StoreBizStopController", "view", "stopId="+stopId, 0, "", "查看停业上报明细异常", e);
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /**
     * 【描述】: 停业上报驳回
     *
     * @author yinkun
     * @date: 2017年9月27日 下午6:25:15
     * @param param
     * @return
     */
    @RequestMapping(value="/reject",method = RequestMethod.POST)
    public String rejectStop(@RequestBody String param) {
        
        ResultData<StoreBizStop> result = new ResultData<>();
        StoreBizStop storeBizStop = JsonUtil.parseToObject(param,StoreBizStop.class);
        
        try {
            result = storeBizStopService.rejectStop(storeBizStop);
        }catch (Exception e) {
            logger.error("storeBizStop", "StoreBizStopController", "rejectStop", param, 0, "", "停业上报驳回异常", e);
            result.setFail();
        }
        
        return result.toString();
    }
    
    /**
     * 【描述】: 停业上报审核通过
     *
     * @author yinkun
     * @date: 2017年9月28日 上午10:54:21
     * @param param
     * @return
     */
    @RequestMapping(value="/pass",method = RequestMethod.POST)
    public String auditPass(@RequestBody String param) {
        
        ResultData<StoreBizStop> result = new ResultData<>();
        StoreBizStop storeBizStop = JsonUtil.parseToObject(param,StoreBizStop.class);
        
        try {
            result = storeBizStopService.auditPass(storeBizStop);
        }catch (Exception e) {
            logger.error("storeBizStop", "StoreBizStopController", "auditPass", param, 0, "", "停业上报审核通过异常", e);
            result.setFail();
        }
        
        return result.toString();
    }
}

