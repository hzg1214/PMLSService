package cn.com.eju.deal.houseLinkage.totalPackage.controller;

import cn.com.eju.deal.api.houseLinkage.constant.HouseLinkageConstant;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.houseLinkage.totalPackage.model.TotalPackageLog;
import cn.com.eju.deal.houseLinkage.totalPackage.service.TotalPackageService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.http.Consts;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2018/11/27.
 */
@RestController
@RequestMapping(value = "TotalPackage")
public class TotalPackageController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "totalPackageService")
    private TotalPackageService totalPackageService;

    @RequestMapping(value = "/confirmPay", method = RequestMethod.POST)
    public String confirmPay(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        TotalPackageLog log = new TotalPackageLog();
        log.setDateCreate(new Date());
        log.setDelFlag("N");
        log.setSourceSys("OMS");
        String jsonData;
        try {
            jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);
            log.setReqParamJson(jsonData);
            List<Map<String, Object>> listMap = JSON.parseObject(jsonData, new TypeReference<List<Map<String,Object>>>(){});
            resultData = totalPackageService.confirmPay(listMap, log);
        } catch (Exception e) {
            logger.error("TotalPackage", "TotalPackageController", "confirmPay", "", null, "", "", e);
            resultData.setFail("确认支付失败！");
            log.setRspParamJson(resultData.toString());
            try {
                totalPackageService.addLog(log);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            return resultData.toString();
        }

        try {
            log.setRspParamJson(resultData.toString());
            totalPackageService.addLog(log);
        } catch (Exception e) {
            logger.error("TotalPackage", "TotalPackageService", "addLog", "", null, "", "添加日志失败", e);
        }
        logger.info(resultData.toString());
        return resultData.toString();
    }

}
