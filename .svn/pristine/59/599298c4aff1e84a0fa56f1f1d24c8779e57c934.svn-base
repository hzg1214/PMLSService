package cn.com.eju.deal.mapper.gpSignContract;

import cn.com.eju.deal.gpContract.model.GpContract;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import cn.com.eju.deal.model.sweepStreets.CompanyNewDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;

import java.util.List;

/**
 * Created by xu on 2017/5/31.
 */
public interface GpSignContractMapper {

    CompanyNewDto getCompanyById(CompanyNewDto dto) throws Exception;

    List<CompanyNewDto> getCompanyList(CompanyNewDto dto) throws Exception;

    List<ContractNewDto> getTodoList(ContractNewDto dto) throws Exception;

    void updateContractStore(ContractNewDto dto);

    List<StoreNewDto> getRelativeStoreList(CompanyNewDto dto);

    void addRelativeStore(List<CompanyNewDto> list);

    List<GpContract> getListByAgreementNo(ContractNewDto dto);

    List<StoreNewDto> getSignedStoreByCompanyId(CompanyNewDto dto);

    int checkAcCityNoForCompany(CompanyNewDto dto) throws Exception;
}
