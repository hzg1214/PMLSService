package cn.com.eju.deal.base.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.core.support.ReturnView;
import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (所有Controller的基类)
 *
 * @author (li_xiaodong)
 * @date 2015年10月14日 下午3:25:18
 */
public abstract class BaseController {
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    /**
     * 构造执行失败结果
     *
     * @param message 执行失败提示
     * @return
     */
    protected ReturnView<?, ?> getExeFailView(String message) throws Exception {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setReturnCode(ReturnCode.EXECUTE_ERROR);
        jsonView.setReturnMsg(message);
        return jsonView;
    }

    /**
     * 构造执行成功结果
     *
     * @param message 执行成功提示
     * @return
     */
    protected ReturnView<?, ?> getSuccView(String message) throws Exception {
        ReturnView<?, ?> jsonView = new ReturnView<String, Object>();
        jsonView.setReturnCode(ReturnCode.SUCCESS);
        jsonView.setReturnMsg(message == null ? "成功" : message);
        return jsonView;
    }

    /**
     * 权限控制,参数转换
     *
     * @param queryParam
     */
    protected void authParam(Map<String, Object> queryParam) {
        //TODO
        //权限控制，List类型 做为参数进行查询会报错，所以，进行了参数转换为数组，
        List<Integer> userIdList = (List<Integer>) queryParam.remove("userIdList");

        if (null != userIdList && !userIdList.isEmpty()) {
            Integer[] arr = (Integer[]) userIdList.toArray(new Integer[userIdList.size()]);
            queryParam.put("userIdList", arr);
        }

    }

}
