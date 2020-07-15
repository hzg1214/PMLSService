package cn.com.eju.deal.company.service.concrete;

import cn.com.eju.deal.base.model.UserInfo;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.common.base.ConvertTo;
import cn.com.eju.deal.common.base.MapperFactory;
import cn.com.eju.deal.company.base.CompanyServiceTemplate;
import cn.com.eju.deal.company.dao.CompanyMapper;
import cn.com.eju.deal.company.model.Company;
import cn.com.eju.deal.company.service.CompanyReleaseCityService;
import cn.com.eju.deal.contacts.dao.ContactsMapper;
import cn.com.eju.deal.contacts.model.Contacts;
import cn.com.eju.deal.contract.dao.ContractMapper;
import cn.com.eju.deal.contract.model.Contract;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnCode;
import cn.com.eju.deal.dto.company.CompanyDto;
import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.store.service.StoreService;
import cn.com.eju.deal.user.api.impl.UserAPIImpl;
import cn.com.eju.deal.user.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Sky on 2016/3/24.
 * 公司明细业务实现
 */
public class CompanyDetailConcrete extends CompanyServiceTemplate<Integer, CompanyDto> {

    private UserAPIImpl userAPIImpl = MapperFactory.getService("userAPI", UserAPIImpl.class);

    private CompanyMapper companyMapper = MapperFactory.getMapper("companyMapper", CompanyMapper.class);

    private ContactsMapper contactsMapper = MapperFactory.getMapper("contactsMapper", ContactsMapper.class);

    private ContractMapper contractMapper = MapperFactory.getMapper("contractMapper", ContractMapper.class);

    private StoreService storeService = MapperFactory.getService("storeService", StoreService.class);

    private CompanyReleaseCityService companyReleaseCityService = MapperFactory.getService("companyReleaseCityService", CompanyReleaseCityService.class);

    @Override
    protected ResultData<CompanyDto> checkRequest(Integer request) {
        ResultData<CompanyDto> checkResult = new ResultData<>();
        checkResult.setSuccess();
        return checkResult;
    }

    @Override
    protected CompanyDto coreExecute(Integer request) throws Exception {

        Company result = companyMapper.getById(request);

        if (result == null)
            return new CompanyDto();

        CompanyDto companyDto = ConvertTo.CompanyDto(result);

        //给拓展字段赋name值
        //城市Name
        companyDto.setCityName(SystemParam.getCityNameByCityNo(companyDto.getCityNo()));

        // 业绩归属城市
        companyDto.setAcCityName(SystemParam.getCityNameByCityNo(companyDto.getAcCityNo()));
        // 发布城市
        companyDto.setRealseCityName(  companyReleaseCityService.getReleaseCityName(result.getId(),companyDto.getAcCityName()));
        //行政区Name
        companyDto.setDistrictName(SystemParam.getDistrictNameByDistrictNo(companyDto.getDistrictNo()));
        //板块Name
        companyDto.setAreaName(SystemParam.getAreaNameByAreaNo(companyDto.getAreaNo()));

        companyDto.setContactList(getContactsList(result.getId()));
        companyDto.setStoreList(getStoreList(result.getId()));
        companyDto.setContractDtoList(getContract(result.getId()));



        //获取签约事业部
        String contractDept = "";
        String contractArea = "";
        String cityName = "";
        //签署人
        String contractName = "";
        Contract contract = new Contract();
        List<Contract> contracts = contractMapper.getByCompanyId(companyDto.getId());
        if (null != contracts && !contracts.isEmpty()) {
            contract = contracts.get(0);
            ResultData<UserInfo> userResultData = this.userAPIImpl.getUserInfoById(1, contract.getUserCreate());
            if (userResultData.getReturnCode() == ReturnCode.SUCCESS) {
                UserInfo userInfo = userResultData.getReturnData();
                if (null != userInfo) {
                    contractArea = userInfo.getGroupName() == null ? "" : userInfo.getGroupName();
                    cityName = userInfo.getCityName() == null ? "" : userInfo.getCityName();
                    contractName = userInfo.getUserName() == null ? "" : userInfo.getUserName();
                }
            }
            contractDept = cityName + "-" + contractArea + "-" + contractName;
        }
        companyDto.setContractDept(contractDept);
        companyDto.setBusinessLicenseNatureNm(SystemParam.getDicValueByDicCode(companyDto.getBusinessLicenseNature()));

        return companyDto;
    }

    @Override
    protected void setResultState(ResultData<CompanyDto> resultData) {
        resultData.setSuccess();
    }

    /**
     * 获取联系人列表
     *
     * @param companyId 公司编号
     * @return 联系人列表
     */
    private List<ContactsDto> getContactsList(int companyId) throws Exception {

        Map queryMap = new HashMap<>();

        queryMap.put("companyId", companyId);
        List<Contacts> contactsList = contactsMapper.queryList(queryMap);

        if (contactsList == null)
            return new ArrayList<>();

        return ConvertTo.ContactsDtoList(contactsList);
    }

    /**
     * 获取门店列表
     *
     * @param companyId 公司编号
     * @return 门店集合
     */
    private List<StoreDto> getStoreList(int companyId) throws Exception {

        Map<String, String> searchMap = new HashMap<>();
        searchMap.put("companyId", String.valueOf(companyId));

        List<StoreDto> storeDtoList = storeService.companyStoreList(searchMap);

        return storeDtoList;
    }

    /**
     * 获取合同信息
     *
     * @param companyId 客户编号
     * @return 客户的合同信息
     */
    private List<ContractDto> getContract(int companyId) throws Exception {

        List<Contract> contract = contractMapper.getByCompanyId(companyId);

        return ConvertTo.CompanyContractDtoList(contract);
    }

    /**
     * @param userId
     * @return
     * @Title: getNameByUserId
     * @Description: 跟进用户编号获取用户姓名
     */
    private String getNameByUserId(Integer userId) throws Exception {
        String userName = "";
        ResultData<User> userData = this.userAPIImpl.getUserById(userId);
        if (userData.getReturnCode() == ReturnCode.SUCCESS) {
            userName = userData.getReturnData().getUserName();
        }
        return userName;
    }

}
