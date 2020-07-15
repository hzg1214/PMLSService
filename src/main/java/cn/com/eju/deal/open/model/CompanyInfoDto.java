package cn.com.eju.deal.open.model;

import java.util.List;

import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.store.StoreDto;

/**   
* 提供给Exchange系统的数据Model
* @author wenhui.zhang
* @date 2016年6月23日 下午4:03:16
*/
public class CompanyInfoDto
{
    private CompanyDto companyDto;
    
    private List<StoreDto> storeList;
    
    private List<ContactsDto> contactsList;
    
    public CompanyDto getCompanyDto()
    {
        return companyDto;
    }

    public void setCompanyDto(CompanyDto companyDto)
    {
        this.companyDto = companyDto;
    }

    public List<StoreDto> getStoreList()
    {
        return storeList;
    }

    public void setStoreList(List<StoreDto> storeList)
    {
        this.storeList = storeList;
    }

    public List<ContactsDto> getContactsList()
    {
        return contactsList;
    }

    public void setContactsList(List<ContactsDto> contactsList)
    {
        this.contactsList = contactsList;
    }

}
