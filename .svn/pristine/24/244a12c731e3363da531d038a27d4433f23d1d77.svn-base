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
import java.util.UUID;

/**
 * Created by Sky on 2016/3/25.
 * 添加公司
 */
public class CompanyAddConcrete extends CompanyServiceTemplate<CompanyDto, CompanyDto> {

    private CompanyMapper companyMapper = MapperFactory.getMapper("companyMapper", CompanyMapper.class);

    private ContactsMapper contactsMapper = MapperFactory.getMapper("contactsMapper", ContactsMapper.class);

    private CompanyStoreMapper companyStoreMapper = MapperFactory.getMapper("companyStoreMapper", CompanyStoreMapper.class);

    private boolean isSuccess = true;

    private String resultMessage;

    @Override
    protected ResultData<CompanyDto> checkRequest(CompanyDto request){

        return CompanyCheck.checkAddCompanyDto(request);
    }

    @Override
    protected CompanyDto coreExecute(CompanyDto request)  throws Exception{

        Company company = ConvertTo.Company(request);

        //company.setFangyouCompanyId(UUID.randomUUID().toString());

        int resultCount = companyMapper.create(company);

        if (resultCount <= 0) {
            isSuccess = false;
            resultMessage = AppMsg.getString("COMMON.SAVE_FAILURE");
            return new CompanyDto();
        }

        addAffiliateContacts(request, company.getId());

        addAffiliateStore(request, company.getId());

        return ConvertTo.CompanyDto(company);
    }


    @Override
    protected void setResultState(ResultData<CompanyDto> resultData) {
        if (isSuccess) {
            resultData.setSuccess();
        } else {
            resultData.setFail(resultMessage);
        }

    }


    /**
     * 添加公司门店附属信息
     *
     * @param companyDto 公司Dto
     * @param companyId  公司Id
     */
    private void addAffiliateStore(CompanyDto companyDto, int companyId)  throws Exception{
        List<CompanyStore> companyStoreList = ConvertTo.CompanyStoreList(companyDto.getStoreList());
        for (CompanyStore item : companyStoreList) {
            item.setCompanyId(companyId);
        }
        companyStoreMapper.batchCreate(companyStoreList);
    }

    /**
     * 添加附属联系人信息
     *
     * @param companyDto 公司Dto
     * @param companyId  公司Id
     */
    private void addAffiliateContacts(CompanyDto companyDto, int companyId)  throws Exception{
        List<Contacts> contactsList = ConvertTo.ContactsList(companyDto.getContactList());
        for (Contacts item : contactsList) {
            item.setCompanyId(companyId);
        }
        contactsMapper.batchCreate(contactsList);
    }
}
