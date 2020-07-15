package cn.com.eju.deal.oa.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.oa.OaOperatorDto;
import cn.com.eju.deal.oa.model.OaOperator;
import cn.com.eju.deal.oa.service.OaOperatorService;

/**
 * 服务层
 *
 * @author (li_xiaodong)
 * @date 2016年1月19日 下午6:05:44
 */

@RestController
@RequestMapping(value = "oa")
public class OaOperatorController extends BaseController
{
    
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "oaOperatorService")
    private OaOperatorService oaOperatorService;
    
    /**
     * 查询-对象
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id)
    {
        //构建返回
        ResultData<OaOperatorDto> resultData = new ResultData<OaOperatorDto>();
        
        //查询
        try
        {
            OaOperator mo = oaOperatorService.getById(id);
            
            //Model转换Dto
            OaOperatorDto dto = new OaOperatorDto();
            
            BeanUtils.copyProperties(mo, dto);
            
            resultData.setReturnData(dto);
            
        }
        catch (Exception e)
        {
            logger.error("OaOperator", "OaOperatorController", "getById", "", 0, "", "查询-对象", e);
            resultData.setFail();
        }
        return resultData.toString();
    }
    
    /**
     * 查询-对象
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/usercode/{userCode}", method = RequestMethod.GET)
    public String getByUserCode(@PathVariable String userCode)
    {
        //构建返回
        ResultData<OaOperatorDto> resultData = new ResultData<OaOperatorDto>();
        
        //查询
        try
        {
            OaOperator mo = oaOperatorService.getByUserCode(userCode);
            
            //Model转换Dto
            OaOperatorDto dto = new OaOperatorDto();
            
            if (null != mo)
            {
                BeanUtils.copyProperties(mo, dto);
            }
            
            resultData.setReturnData(dto);
            
        }
        catch (Exception e)
        {
            logger.error("OaOperator", "OaOperatorController", "getByUserCode", "", 0, "", "查询-对象", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /**
     * 查询-list
     *
     * @param param 查询条件
     * @return
     */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<OaOperatorDto>> resultData = new ResultData<List<OaOperatorDto>>();
        
        try
        {
            Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
            
            resultData = oaOperatorService.queryList(queryParam);
        }
        catch (Exception e)
        {
            logger.error("OaOperator", "OaOperatorController", "list", "", 0, "", "查询-list", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /**
     * 创建
     *
     * @param jsonDto 对象字符串
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto)
    {
        
        //构建返回
        ResultData<OaOperatorDto> resultData = new ResultData<OaOperatorDto>();
        
        try
        {
            OaOperatorDto dto = JsonUtil.parseToObject(jsonDto, OaOperatorDto.class);
            
            OaOperator mo = new OaOperator();
            
            //赋值
            BeanUtils.copyProperties(dto, mo);
            
            int count = oaOperatorService.create(mo);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            logger.error("OaOperator", "OaOperatorController", "create", "", 0, "", "创建", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /**
     * 更新
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String studentDtoJson)
    {
        
        //构建返回
        ResultData<OaOperatorDto> resultData = new ResultData<OaOperatorDto>();
        
        try
        {
            OaOperatorDto dto = JsonUtil.parseToObject(studentDtoJson, OaOperatorDto.class);
            
            OaOperator mo = new OaOperator();
            
            //赋值
            BeanUtils.copyProperties(dto, mo);
            
            int count = oaOperatorService.update(mo);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            logger.error("OaOperator", "OaOperatorController", "update", "", 0, "", "更新", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /**
     * 删除
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id)
    {
        //构建返回
        ResultData<OaOperatorDto> resultData = new ResultData<OaOperatorDto>();
        
        try
        {
            int count = oaOperatorService.delete(id);
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            
            logger.error("OaOperator", "OaOperatorController", "delete", "", 0, "", "删除", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }

    /**
     * 查询OA审核原因
     * @return
     */
    @RequestMapping(value="/getOAAuditInfo/{flowId}",method = RequestMethod.GET)
    public String getOAAuditInfo(@PathVariable String flowId){
        ResultData<List<ContractAuditRecordDto>> resultData = new ResultData<>();

        final List<ContractAuditRecordDto> oaAuditInfo = oaOperatorService.getOAAuditInfo(flowId);

        if(oaAuditInfo == null){
            resultData.setFail();
        }else{
            resultData.setReturnData(oaAuditInfo);
        }

        return resultData.toString();
    }
}
