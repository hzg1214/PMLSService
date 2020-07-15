package cn.com.eju.deal.common.base;

import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.model.CompanyStore;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.core.util.StringUtil;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.model.Store;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Sky on 2016/3/29.
 * 类型转换
 */
public class ConvertTo {
    /**
     * 转换客户信息DTO
     *
     * @param company 客户Model对象
     * @return 返回客户信息dto
     */
    public static CompanyDto CompanyDto(Company company) {
        CompanyDto companyDto = new CompanyDto();
        BeanUtils.copyProperties(company, companyDto);
        return companyDto;
    }

    /**
     * 转换Company对象
     *
     * @param dto dto对象
     * @return CompanyModel对象
     */
    public static Company Company(CompanyDto dto) {
        Company company = new Company();
        BeanUtils.copyProperties(dto, company);
        return company;
    }

    /**
     * 转换Contacts对象
     *
     * @param contactsDto 联系人DTO
     * @return contactsModel对象
     */
    public static Contacts Contacts(ContactsDto contactsDto) {
        Contacts contacts = new Contacts();
        BeanUtils.copyProperties(contactsDto, contacts);
        return contacts;
    }

    /**
     * 转换Contacts对象
     *
     * @param contacts 联系人Model
     * @return DTO对象
     */
    public static ContactsDto ContactsDto(Contacts contacts) {
        ContactsDto contactsDto = new ContactsDto();
        BeanUtils.copyProperties(contacts, contactsDto);
        return contactsDto;
    }

    /**
     * 转换ContactsList对象
     *
     * @param contactsDtoList 联系人dto列表
     * @return contactsModel对象
     */
    public static List<Contacts> ContactsList(List<ContactsDto> contactsDtoList) {
        List<Contacts> contactsList = new ArrayList<>();

        for (ContactsDto item : contactsDtoList) {
            if(StringUtil.isNotEmpty(item.getName()) && StringUtil.isNotEmpty(item.getMobilePhone())){
                contactsList.add(Contacts(item));
            }
        }
        return contactsList;
    }

    /**
     * 转换ContactsDtoList对象
     *
     * @param contactsList 联系人集合-
     * @return dto集合
     */
    public static List<ContactsDto> ContactsDtoList(List<Contacts> contactsList) {
        List<ContactsDto> contactsDtoList = new ArrayList<>();

        for (Contacts item : contactsList) {
            contactsDtoList.add(ContactsDto(item));
        }
        return contactsDtoList;
    }

    /**
     * 转换门店Model
     *
     * @param dto dto对象
     * @return StoreModel对象
     */
    public static Store Store(StoreDto dto) {
        Store store = new Store();
        BeanUtils.copyProperties(store, dto);
        return store;
    }

    /**
     * 转换门店dto
     *
     * @param model 门店Model对象
     * @return 门店dto
     */
    public static StoreDto StoreDto(Store model) {
        StoreDto storeDto = new StoreDto();
        BeanUtils.copyProperties(model, storeDto);
        return storeDto;
    }

    /**
     * 门店集合Model转换
     *
     * @param storeDtoList 门店dto集合
     * @return 门店Model集合
     */
    public static List<Store> StoreList(List<StoreDto> storeDtoList) {
        List<Store> storeList = new ArrayList<>();
        for (StoreDto item : storeDtoList) {
            storeList.add(Store(item));
        }
        return storeList;
    }

    /**
     * 转换门店dto集合
     *
     * @param storeList 门店Model集合
     * @return dto集合
     */
    public static List<StoreDto> StoreDtoList(List<Store> storeList) {
        List<StoreDto> storeDtoList = new ArrayList<>();
        for (Store item : storeList) {
            storeDtoList.add(StoreDto(item));
        }
        return storeDtoList;
    }

    /**
     * 获取公司门店Model
     *
     * @param storeDto 门店dto
     * @return CompanyStoreModel
     */
    public static CompanyStore CompanyStore(StoreDto storeDto) {
        CompanyStore companyStore = new CompanyStore();
        companyStore.setDelete(false);
        companyStore.setStoreId(storeDto.getStoreId());
        return companyStore;
    }

    /**
     * 转换公司门店Model
     *
     * @param storeDtoList 门店dto集合
     * @return CompanyStoreModelList
     */
    public static List<CompanyStore> CompanyStoreList(List<StoreDto> storeDtoList) {
        List<CompanyStore> companyStoreList = new ArrayList<>();
        for (StoreDto item : storeDtoList) {
            companyStoreList.add(CompanyStore(item));
        }
        return companyStoreList;
    }

    /**
     * 转换客户合同
     *
     * @param contract 合同Model
     * @return 客户合同Dto
     */
    public static ContractDto CompanyContractDto(Contract contract) {
        ContractDto contactsDto = new ContractDto();
        BeanUtils.copyProperties(contract, contactsDto);
        return contactsDto;
    }

    /**
     * 转客户合同列表
     *
     * @param contractList 合同列表Model
     * @return 客户合同列表Dto
     */
    public static List<ContractDto> CompanyContractDtoList(List<Contract> contractList) {
        List<ContractDto> contractDtoList = new ArrayList<>();
        for (Contract item : contractList) {
            contractDtoList.add(CompanyContractDto(item));
        }
        return contractDtoList;
    }

    /**
     * 泛型类型转化
     *
     * @param source  元数据对象
     * @param toClass 目标类型
     * @param <T>     目标结果
     * @return 返回转换后的对象
     */
    private static <T> T ConvertToT(Objects source, Class<T> toClass) {
        T result = createInstance(toClass);
        BeanUtils.copyProperties(source, result);
        return result;
    }

    /**
     * 类型转换
     *
     * @param cls 目标类型
     * @param <T> 目标对象
     * @return 创建的目标对象l
     */
    private static <T> T createInstance(Class<T> cls) {
        T obj;
        try {
            obj = cls.newInstance();
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }
}



















