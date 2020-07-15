package cn.com.eju.deal.controller.myCollection;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import cn.com.eju.deal.service.myCollection.MyCollectionService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xu on 2017/5/22.
 */
@RestController
@RequestMapping(value = "myCollectionController")
public class MyCollectionController {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource(name = "myCollectionService")
    private MyCollectionService myCollectionService;

    @RequestMapping(value = "/getStoreListData", method = RequestMethod.POST)
    public String getStoreListData(@RequestBody String jsonDto)
    {
        StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
        ResultData<List<StoreNewDto>> resultData = new ResultData<List<StoreNewDto>>();
        try
        {
            resultData = myCollectionService.getStoreListData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("myCollectionController", "myCollectionController", "getStoreListData", "", null, "", "获取我的收藏门店列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/addCollection", method = RequestMethod.POST)
    public String addCollection(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData=myCollectionService.addCollection(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("myCollectionController", "myCollectionController", "addCollection", "", null, "", "添加收藏", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/cancelCollection", method = RequestMethod.POST)
    public String cancelCollection(@RequestBody String jsonDto)
    {
        ResultData resultData = new ResultData();
        try{
            StoreNewDto dto = JsonUtil.parseToObject(jsonDto, StoreNewDto.class);
            resultData=myCollectionService.cancelCollection(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("myCollectionController", "myCollectionController", "cancelCollection", "", null, "", "取消收藏", e);
        }
        return resultData.toString();
    }
}
