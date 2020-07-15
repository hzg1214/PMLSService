package cn.com.eju.deal.contacts.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contacts.service.ContactsService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.dto.contacts.ContactsDto;

/**   
* 联系人
* @author (li_xiaodong)
* @date 2016年3月24日 上午9:37:12
*/
@RestController
@RequestMapping(value = "contacts")
public class ContactsController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "contactsService")
    private ContactsService contactsService;
    
    /** 
    * 查询-对象
    * @param id
    * @return
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable int id)
    {
        //构建返回
        ResultData<ContactsDto> resultData = new ResultData<ContactsDto>();
        
        //查询
        Contacts mo;
        try
        {
            mo = contactsService.getById(id);
            
            //Model转换Dto
            ContactsDto dto = new ContactsDto();
            BeanUtils.copyProperties(mo, dto);
            resultData.setReturnData(dto);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contacts", "ContactsController", "getById", id + "", 0, "", "查询-对象失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
    * 查询-list
    * @param param 查询条件
    * @return
    */
    @RequestMapping(value = "/q/{param}", method = RequestMethod.GET)
    public String list(@PathVariable String param)
    {
        //构建返回
        ResultData<List<ContactsDto>> resultData = new ResultData<List<ContactsDto>>();
        
        try
        {
            Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
            
            List<Integer> userIdList = (List<Integer>)queryParam.remove("userIdList");
            if (null != userIdList && !userIdList.isEmpty())
            {
                Integer[] arr = (Integer[])userIdList.toArray(new Integer[userIdList.size()]);
                queryParam.put("userIdList", arr);
            }
            
            resultData = contactsService.queryList(queryParam);
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contacts", "ContactsController", "list", param, 0, "", "查询-list失败", e);
        }
        
        return resultData.toString();
    }
    
    /** 
    * 创建
    * @param jsonDto 对象字符串
    * @return
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String create(@RequestBody String jsonDto)
    {
        
        //构建返回
        ResultData<ContactsDto> resultData = new ResultData<ContactsDto>();
        
        try
        {
            ContactsDto dto = JsonUtil.parseToObject(jsonDto, ContactsDto.class);
            
            Contacts mo = new Contacts();
            
            //赋值
            BeanUtils.copyProperties(dto, mo);
            
            int count = contactsService.create(mo);
            
            if (count <= 0)
            {
                resultData.setFail();
            }
            
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contacts", "ContactsController", "create", jsonDto, 0, "", "创建", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 更新
     * @param param
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String update(@RequestBody String dtoJson)
    {
        
        //构建返回
        ResultData<ContactsDto> resultData = new ResultData<ContactsDto>();
        
        try
        {
            ContactsDto dto = JsonUtil.parseToObject(dtoJson, ContactsDto.class);
            
            Contacts mo = new Contacts();
            
            //赋值
            BeanUtils.copyProperties(dto, mo);
            
            int count = contactsService.update(mo);
            
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contacts", "ContactsController", "update", "", 0, "", "更新", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 删除
     * @param param
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable int id)
    {
        //构建返回
        ResultData<ContactsDto> resultData = new ResultData<ContactsDto>();
        
        try
        {
            int count = contactsService.delete(id);
            
            if (count <= 0)
            {
                resultData.setFail();
            }
        }
        catch (Exception e)
        {
            resultData.setFail();
            logger.error("Contacts", "ContactsController", "delete", "", 0, "", "删除", e);
        }
        
        return resultData.toString();
    }
    
    /** 
     * 查询当前User和当前User下属创建的联系人信息（提供给CRM微信端）
     * @param param
     * @return
     */
     @RequestMapping(value = "/qContacts/{param}", method = RequestMethod.GET)
      public String queryContactsList(@PathVariable String param)
      {
          // 构建返回
          ResultData<List<Contacts>> resultData = new ResultData<List<Contacts>>();
          
          try
          {
         	 @SuppressWarnings("unchecked")
              Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
              
              // 权限控制,参数转换
              authParam(queryParam);
              
              // 查询
              resultData = contactsService.queryContactsList(queryParam);
          }
          catch (Exception e)
          {
              resultData.setFail();
              logger.error("contacts", "ContactsController", "queryContactsList", param, 0, "", "查询当前User和当前User下属创建的联系人信息（提供给CRM微信端）", e);
          }
          
          return resultData.toString();
      }
    
}
