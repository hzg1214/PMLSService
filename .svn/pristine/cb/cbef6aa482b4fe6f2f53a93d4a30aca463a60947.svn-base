package cn.com.eju.deal.mapper.signContract;

import cn.com.eju.deal.model.followMap.ContactsDto;
import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;

import java.util.List;

/**
 * Created by xu on 2017/5/31.
 */
public interface SignContractMapper {
    List<StoreNewDto> getStoreList(StoreNewDto dto) throws Exception;
    int checkCompanyStore(StoreNewDto dto) throws Exception;
    CompanyNewDto getCompanyByStoreId(StoreNewDto dto) throws Exception;
    CompanyNewDto getCompanyById(CompanyNewDto dto) throws Exception;
    List<CompanyNewDto> getCompanyList(CompanyNewDto dto) throws  Exception;
    int addContract(ContractNewDto dto) throws Exception;
    int addContractStore(ContractNewDto dto) throws Exception;
    int updateStoreStatus(ContractNewDto dto) throws Exception;
    int updateCompanyStatus(CompanyNewDto dto) throws Exception;
    List<StoreNewDto> checkStoreContract(ContractNewDto dto) throws Exception;
    int checkAgreementNo(String agreementNo) throws Exception;
    int updateStoreManagerInfo(ContactsDto dto) throws Exception;


    /**
     * 根据搜索条件（合同编号、公司名称、门店名称、门店地址、合同状态）查询签约合同列表
     * @param dto
     * @return
     * @throws Exception
     */
    List<ContractNewDto> getContractList(ContractNewDto dto) throws Exception;
 /**
     * 根据搜索条件（合同编号、公司名称、门店名称、门店地址、合同状态）查询公盘合同列表
     * @param dto
     * @return
     * @throws Exception
     */
    List<ContractNewDto> getGpSignContractList(ContractNewDto dto) throws Exception;

    /**
     * 获取合同详情信息
     * @param dto
     * @return
     * @throws Exception
     */
    ContractNewDto getContractInfo(ContractNewDto dto) throws Exception;

    /**
     * 获取公盘合同详情信息
     * @param dto
     * @return
     * @throws Exception
     */
    ContractNewDto getGpSignContractInfo(ContractNewDto dto) throws Exception;

    /**
     * 获取合同审批 列表信息
     * @param dto
     * @return
     * @throws Exception
     */
    List<ContractAuditRecordDto> getContractAuditRecordList(ContractAuditRecordDto dto) throws Exception;

    void updateContract(ContractNewDto dto);

    void updateContractStore(ContractNewDto dto);

    List<StoreNewDto> getTodoSignStoreList(StoreNewDto dto) throws Exception;

    int checkAcCityNoForStore(StoreNewDto dto) throws Exception;

    int checkCompanyStoreForCompany(CompanyNewDto dto) throws Exception;

    int checkAcCityNoForCompany(CompanyNewDto dto) throws Exception;
}
