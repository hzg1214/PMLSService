package cn.com.eju.deal.api.WXCloud.controller;

import cn.com.eju.deal.api.WXCloud.service.APIWXCloudService;
import cn.com.eju.deal.api.houseLinkage.constant.HouseLinkageConstant;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.StringUtil;
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
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/1/25.
 */
@RestController
@RequestMapping(value = "APIWXCloud")
public class APIWXCloudController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "apiWXCloudService")
    private APIWXCloudService apiWXCloudService;

    /**
     * 将数据更新至表cloud_yyjb_gp_day_data、cloud_yyjb_gp_week_data、cloud_yyjb_gp_month_data、cloud_yyjb_gp_quarter_data
     * @param jsonStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/setGPData", method = RequestMethod.POST)
    public String setGPData(@RequestBody String jsonStr, HttpServletRequest request) {
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try {
            String jsonData = URLDecoder.decode(jsonStr, Consts.UTF_8.toString());
            if (StringUtil.isEmpty(jsonData)) {
                resultData.setReturnCode("3");
                resultData.setFail(HouseLinkageConstant.FAIL_REMOTE_DATA_IS_NULL);
                return resultData.toString();
            }
            logger.info("jsonData======>>" + jsonData);
            List<Map<String, Object>> listMap = JSON.parseObject(jsonData, new TypeReference<List<Map<String,Object>>>(){});
            resultData = apiWXCloudService.setGPData(listMap);
            logger.info(resultData.toString());
        } catch (Exception e) {
            logger.error("APIWXCloud", "APIWXCloudController", "setGPData", "", null, "", "", e);
            resultData.setFail("公盘数据灌入失败！");
            return resultData.toString();
        }
        return resultData.toString();
    }
}
