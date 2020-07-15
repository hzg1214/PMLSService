package cn.com.eju.deal.company.service.concrete;

import cn.com.eju.deal.common.base.ConvertTo;
import cn.com.eju.deal.common.base.MapperFactory;
import cn.com.eju.deal.company.base.CompanyServiceTemplate;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.dao.CompanyStoreMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.company.service.CompanyCheck;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.core.support.AppMsg;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.company.CompanyDto;

import java.util.List;

/**
 * Created by Sky on 2016/3/25.
 * 更新公司信息
 */
public class CompanyUpdateConcrete extends CompanyServiceTemplate<CompanyDto, Boolean> {

    private boolean isSuccess;

    private CompanyMapper companyMapper = MapperFactory.getMapper("companyMapper", CompanyMapper.class);

    private ContactsMapper contactsMapper = MapperFactory.getMapper("contactsMapper", ContactsMapper.class);

    private CompanyStoreMapper companyStoreMapper = MapperFactory.getMapper("companyStoreMapper", CompanyStoreMapper.class);

    @Override
    protected ResultData<Boolean> checkRequest(CompanyDto request){

        return CompanyCheck.checkUpdateCompanyDto(request);

    }

    @Override
    protected Boolean coreExecute(CompanyDto request) throws Exception{
        Company company = ConvertTo.Company(request);


        int resultCount = companyMapper.update(company);

        isSuccess = resultCount > 0;
        if (!isSuccess)
            return isSuccess;


        updateAffiliateStore(request);
        updateAffiliateContacts(request);

        return true;
    }

    @Override
    protected void setResultState(ResultData<Boolean> resultData) {

        if (isSuccess)
            resultData.setSuccess();
        else
            resultData.setFail(AppMsg.getString("COMMON.UPDATE_FAILURE"));
    }

    /**
     * 更新门店信息
     *
     * @param companyDto 修改公司请求参数
     */
    private void updateAffiliateStore(CompanyDto companyDto) throws Exception{
        int companyId = companyDto.getId();
        List<CompanyStore> companyStoreList = ConvertTo.CompanyStoreList(companyDto.getStoreList());

        for (CompanyStore item : companyStoreList) {
            item.setCompanyId(companyId);
        }

        companyStoreMapper.deleteCompanyStore(companyId);
        companyStoreMapper.batchCreate(companyStoreList);
    }

    /**
     * 更新联系人信息
     *
     * @param companyDto 修改公司请求参数
     */
    private void updateAffiliateContacts(CompanyDto companyDto) {
        List<Contacts> contactsList = ConvertTo.ContactsList(companyDto.getContactList());

        for (Contacts item : contactsList) {
            contactsMapper.update(item);
        }
    }

}
