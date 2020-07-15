package cn.com.eju.deal.common.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.service.SHBigDistrictService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.common.SHBigDistrictDto;

/**   
* 查询上海事业部下区域
* @author 张文辉
* @date 2016年7月11日 下午1:40:53
*/
@RestController
@RequestMapping("/sHBigDistrict")
public class SHBigDistrictController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "sHBigDistrictService")
    private SHBigDistrictService sHBigDistrictService;
    
    /** 
     * 查询上海事业部区域信息
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/selectedPostId/{selectedPostId}", method = RequestMethod.GET)
    public String getBigDistrictBySelectedPostId(@PathVariable Integer selectedPostId)
    {
        ResultData<List<SHBigDistrictDto>> resultData = new ResultData<List<SHBigDistrictDto>>();
        try
        {
            resultData = sHBigDistrictService.getBigDistrictBySelectedPostId(selectedPostId);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "SHBigDistrictController", "getBigDistrictByGroupId", "", null, "", "查询上海区域失败！", e);
        }
        return resultData.toString();
    }
}
