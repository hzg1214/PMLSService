package cn.com.eju.deal.store.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.store.ConcernDto;
import cn.com.eju.deal.store.service.ConcernedStoreService;

/** 
* @ClassName: ConcernedStoreController 
* @Description: 关注的门店
* @author 陆海丹 
* @date 2016年4月5日 下午9:32:29 
*/
@RestController
@RequestMapping(value = "concernedStore")
public class ConcernedStoreController extends BaseController
{
    /**
     * 日志
     */
     private final LogHelper logger = LogHelper.getLogger(this.getClass());
     
    @Resource(name="concernedStoreService")
    private ConcernedStoreService concernedStoreService;
    
    /** 
     * @Title: getById 
     * @Description: 根据门店编号查询关注门店详情
     * @param id
     * @return     
     *//*
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
     public String getById(@PathVariable int id)
     {
         //构建返回
         ResultData<ConcernDto> resultData = this.concernedStoreService.getById(id);
         return resultData.toString();
     }*/
     
     /** 
     * @Title: list 
     * @Description: 根据条件查询门店列表
     * @param param
     * @return     
     */
     @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
     public String list(@PathVariable String param)
     {
         Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
         //构建返回
         ResultData<List<ConcernDto>> resultData = new ResultData<List<ConcernDto>>();
        try
        {
            resultData = this.concernedStoreService.queryConcernedStoreList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "list", "", null, "", "", e);
        }        
         return resultData.toString();
     }
     
     /** 
     * @Title: create 
     * @Description: 新增门店
     * @param jsonDto
     * @return     
     */
     @RequestMapping(value = "", method = RequestMethod.POST)
     public String create(@RequestBody String jsonDto)
     {
         ConcernDto ConcernDto = JsonUtil.parseToObject(jsonDto, ConcernDto.class);
         //构建返回
         ResultData<ConcernDto> resultData = new ResultData<ConcernDto>();
        try
        {
            resultData = this.concernedStoreService.createStr(ConcernDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "create", "", null, "", "", e);
        }
         return resultData.toString();
     }
     
     /** 
     * @Title: update 
     * @Description: 更新门店信息
     * @param jsonDto
     * @return     
     */
     @RequestMapping(value = "", method = RequestMethod.PUT)
     public String update(@RequestBody String jsonDto)
     {
         ConcernDto ConcernDto = JsonUtil.parseToObject(jsonDto, ConcernDto.class);
         //构建返回
         ResultData<ConcernDto> resultData =new ResultData<>();// this.concernedStoreService.updateStr(ConcernDto);
         return resultData.toString();
     }
     
     /** 
     * @Title: delete 
     * @Description: 删除门店
     * @param id
     * @return     
     */
     @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
     public String delete(@PathVariable int id)
     {
         //构建返回
         ResultData<ConcernDto> resultData = new ResultData<ConcernDto>();
        try
        {
            resultData = this.concernedStoreService.cancelStr(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("store", "ConcernedStoreController", "delete", "", null, "", "", e);
        }
         return resultData.toString();
     }
}
