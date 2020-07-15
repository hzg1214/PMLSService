package cn.com.eju.deal.contacts.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contacts.ContactsDto;

/**   
* service层
* @author (li_xiaodong)
* @date 2016年3月24日 上午10:35:18
*/
@Service("contactsService")
public class ContactsService extends BaseService
{
    
    @Resource
    private ContactsMapper contactsMapper;
    
    /** 
    * 查询
    * @param id
    * @return
    */
    
    public Contacts getById(int id)
        throws Exception
    {
        Contacts contacts = contactsMapper.getById(id);
        return contacts;
    }
    
    /** 
     * 查询-list
     * @param queryParam
     * @return
     */
    
    public ResultData<List<ContactsDto>> queryList(Map<?, ?> param)
        throws Exception
    {
        
        //构建返回
        ResultData<List<ContactsDto>> resultData = new ResultData<List<ContactsDto>>();
        
        //查询
        final List<Contacts> moList = contactsMapper.queryList(param);
        
        //转换
        List<ContactsDto> dtoList = convertData(moList);
        
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        
        resultData.setReturnData(dtoList);
        
        return resultData;
    }
    
    /** 
     * 创建
     * @param param
     * @return
     */
    
    public int create(Contacts contacts)
        throws Exception
    {
        int count = contactsMapper.create(contacts);
        return count;
    }
    
    /** 
     * 更新
     * @param param
     * @return
     */
    
    public int update(Contacts contacts)
        throws Exception
    {
        int count = contactsMapper.update(contacts);
        return count;
    }
    
    /** 
    * 删除
    * @param id 
    * @param updateId 更新人
    * @param updateTime 更新时间
    * @return
    */
    
    public int delete(int id)
        throws Exception
    {
        int count = contactsMapper.deleteById(id);
        return count;
    }
    
    /** 
     * 对象转换MO--DTO
     * @param stuList
     * @return List<StudentDto>
     */
    private List<ContactsDto> convertData(List<Contacts> list) throws Exception
    {
        List<ContactsDto> dtoList = new ArrayList<ContactsDto>();
        
        if (null != list && !list.isEmpty())
        {
            ContactsDto dto = null;
            for (Contacts mo : list)
            {
                dto = new ContactsDto();
                BeanUtils.copyProperties(mo, dto);
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
    
    /** 
    * 查询当前User和当前User下属创建的联系人信息（提供给CRM微信端）
    * @param param
    * @return
    * @throws Exception
    */
    public ResultData<List<Contacts>> queryContactsList(Map<?, ?> param)
        throws Exception
    {
        // 构建返回
        ResultData<List<Contacts>> resultData = new ResultData<List<Contacts>>();
        
        // 查询
        final List<Contacts> contactsList = contactsMapper.getByUserId(param);
        
        resultData.setTotalCount((String)param.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(contactsList);
        return resultData;
    }
    
}
