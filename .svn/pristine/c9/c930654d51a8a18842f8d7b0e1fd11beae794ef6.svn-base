package cn.com.eju.deal.picture.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.picture.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tanlang on 2017-06-12.
 */
@RestController
@RequestMapping(value = "picture")
public class PictureController extends BaseController {

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Autowired
    private PictureService pictureService;

    @RequestMapping(value = "getPictureUrl/{pictureRefId}", method = RequestMethod.GET)
    public String getPictureUrl(@PathVariable String pictureRefId) {

        ResultData<?> resultData = new ResultData<>();
        try {
            resultData = pictureService.getPictureUrl(pictureRefId);

        } catch (Exception e) {
            logger.error("picture", "PictureController", "getPictureUrl", "", null, "", " 查看图片失败！", e);
        }

        return resultData.toString();
    }

}
