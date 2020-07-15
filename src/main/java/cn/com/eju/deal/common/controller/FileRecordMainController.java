package cn.com.eju.deal.common.controller;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.common.service.FileRecordMainService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.common.FileRecordMainDto;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/** 
* @ClassName: FileRecordMainController 
* @Description: 文件Controller
* @author 陆海丹
* @date 2016年3月25日 下午2:50:27 
*/
@RestController
@RequestMapping(value = "file")
public class FileRecordMainController
{
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "fileService")
    private FileRecordMainService fileService;
    
    /** 
    * @Title: getById 
    * @Description: 根据门店编号查询文件详情
    * @param id
    * @return     
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id)
    {
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        try
        {
            resultData = this.fileService.getStrById(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "getById", "", null, "", " 根据门店编号查询文件详情失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: list 
    * @Description: 根据条件查询文件列表
    * @param param
    * @return     
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        Map<?, ?> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        try
        {
            resultData = this.fileService.queryStrList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "list", "", null, "", "根据条件查询文件列表失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: create 
    * @Description: 新增文件
    * @param jsonDto
    * @return     
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto)
    {
        FileRecordMainDto fileRecordMainDto = JsonUtil.parseToObject(jsonDto, FileRecordMainDto.class);
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        try
        {
            resultData = this.fileService.createStr(fileRecordMainDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "create", "", null, "", " 新增文件失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: update 
    * @Description: 更新文件信息
    * @param jsonDto
    * @return     
    */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String jsonDto)
    {
        FileRecordMainDto fileRecordMainDto = JsonUtil.parseToObject(jsonDto, FileRecordMainDto.class);
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        try
        {
            resultData = this.fileService.updateStr(fileRecordMainDto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "update", "", null, "", "更新文件信息失败", e);
        }
        return resultData.toString();
    }
    
    /** 
    * @Title: delete 
    * @Description: 删除文件
    * @param id
    * @return     
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id)
    {
        //构建返回
        ResultData<FileRecordMainDto> resultData = new ResultData<FileRecordMainDto>();
        try
        {
            resultData = this.fileService.deleteStr(id);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "delete", "", null, "", "删除文件失败", e);
        }
        return resultData.toString();
    }

    /**
     * @Title: list
     * @Description: 根据条件查询合同附件列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/q/contract/{param}", method = RequestMethod.GET)
    public String getContractList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        try
        {
            resultData = this.fileService.getContractList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "getContractList", "", null, "", "根据条件查询合同附件列表", e);
        }
        return resultData.toString();
    }

    /**
     * @Title: list
     * @Description: 根据条件查询合同变更附件列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/q/contractchange/{param}", method = RequestMethod.GET)
    public String getContractChangeList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        try
        {
            resultData = this.fileService.getContractChangeList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "getContractChangeList", "", null, "", "根据条件查询合同变更附件列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/q/gpContract/{param}", method = RequestMethod.GET)
    public String getGpContractList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        try
        {
            resultData = this.fileService.getGpContractList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "getContractList", "", null, "", "根据条件查询合同附件列表", e);
        }
        return resultData.toString();
    }

    @RequestMapping(value = "/q/contractChangeNew/{param}", method = RequestMethod.GET)
    public String getContractChangeNewList(@PathVariable String param)
    {
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        //构建返回
        ResultData<List<FileRecordMainDto>> resultData = new ResultData<List<FileRecordMainDto>>();
        try
        {
            resultData = this.fileService.getContractChangeNewList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("common", "FileRecordMainController", "getContractChangeList", "", null, "", "根据条件查询合同变更附件列表", e);
        }
        return resultData.toString();
    }
}
