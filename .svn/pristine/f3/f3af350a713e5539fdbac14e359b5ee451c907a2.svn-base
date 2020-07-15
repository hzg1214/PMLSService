package cn.com.eju.deal.open.controller;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.open.service.APIFangyouService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**   
* 提供给Fangyou系统的API
* @author wenhui.zhang
* @date 2016年6月24日 下午3:52:34
*/
@RestController
@RequestMapping(value = "crm/fangyou")
public class APIFangyouController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource
    private APIFangyouService apiFangyouService;


    
    /** 
     * 查询-根据房友公司Id获取门店信息
     * @param fangyouCompanyId
     * @return
     */
    @RequestMapping(value = "store/{fangyouCompanyId}", method = RequestMethod.GET)
    public String getStoreInfoByFyCompanyId(@PathVariable String fangyouCompanyId)
    {
        //构建返回
        ResultData<List<StoreDto>> resultData = new ResultData<List<StoreDto>>();
        try
        {
            //查询
            List<StoreDto> storeList = apiFangyouService.getStoreInfoByFyCompanyId(fangyouCompanyId);
            resultData.setReturnData(storeList);
            if (null != storeList) {
                resultData.setTotalCount(storeList.size()+"");
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("APIFangyou", "APIFangyouController", "getStoreInfoByFyCompanyId", "fangyouCompanyId", 1, "", "根据房友公司Id获取门店信息失败！", e);
        }
        return resultData.toString();
    }
}
