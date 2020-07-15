/**
 * Copyright (c) 2017, www.ehousechina.com.
 * Project Name:PMLSService
 * File Name:FeebackController.java
 * Package Name:cn.com.eju.deal.feeback.contoller
 * Date:2017年9月19日下午3:36:16
 */
package cn.com.eju.deal.feeback.contoller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.feeback.model.Feeback;
import cn.com.eju.deal.feeback.service.IFeebackService;

/**
 * ClassName: FeebackController <br/>
 * Description: 意见反馈rest服务接口 <br/>
 * 
 * @author yinkun
 * @date: 2017年9月19日 下午3:36:16 <br/>
 * @version V1.0
 */
@RestController
@RequestMapping("feeback")
public class FeebackController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private IFeebackService feebackService;
    
    /**
     * 【描述】: 保存意见反馈
     *
     * @author yinkun
     * @date: 2017年9月19日 下午4:17:28
     * @param jsonDto
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String save(@RequestBody String jsonDto) {
        ResultData<Feeback> result = new ResultData<>();
        
        try {
            Feeback feeback = JsonUtil.parseToObject(jsonDto, Feeback.class);
            int count = feebackService.save(feeback);
            if(count != 1) {
                result.setFail();
            }
        }catch(Exception e) {
            result.setFail();
            logger.error("feeback", "FeebackController", "save", "", null, "", "保存意见反馈失败", e);
        }
        
        return result.toString();
    }
}

