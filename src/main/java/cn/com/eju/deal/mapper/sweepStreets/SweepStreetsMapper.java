package cn.com.eju.deal.mapper.sweepStreets;

import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.model.sweepStreets.*;

import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/4/13.
 */
public interface SweepStreetsMapper {
    List<StoreInfoDto> getStoreListData(StoreNewDto dto) throws Exception;
    List<StoreInfoDto> getLocalStoreListData(StoreNewDto dto) throws Exception;
    int addStore(StoreNewDto dto);
    int checkStore(StoreNewDto dto);
    int updateStore(StoreNewDto dto);
    int updateStoreAuditStatus(StoreNewDto dto);
    int updateStoreMainTainerInfo(StoreNewDto dto);
    int addStoreMainTainer(StoreNewDto dto);
    int deleteStoreById(StoreNewDto dto);
    StoreNewDto getStoreById(StoreNewDto dto);
    List<CompanyNewDto> getCompanyListData(CompanyNewDto dto) throws Exception;
    int addAuditRecord(Map<String,String> map);

    int addCompany(CompanyNewDto dto);
    List<CompanyNewDto> checkCompany(CompanyNewDto dto);
    int updateCompany(CompanyNewDto dto);
    CompanyNewDto getCompanyById(CompanyNewDto dto);
    int addCompanyStore(CompanyStoreNewDto dto);
    int updateCompanyStore(CompanyStoreNewDto dto);
    List<StoreNewDto> getCompanyStore(CompanyNewDto dto);

    List<StoreNewDto> getNotBindStoreList(StoreNewDto dto);
    List<StoreNewDto> getContractStoreByCompanyId(CompanyNewDto dto);
    List<StoreNewDto> checkStoreIsContract(CompanyNewDto dto);

    int addStoreCenterHis(StoreNewDto dto);
    CompanyBusinessDto checkCompanyBusiness(CompanyBusinessDto dto);
    int addCompanyBusinessInfo(CompanyBusinessDto dto);

    List<StoreNewDto> getStoreManagerList();

    int checkStoreFangyouNo(String storeId);
    List<CityDto> getAllCityList();
    int updateStoreBToAAlert(StoreNewDto dto);
    List<StoreNewDto> getBToAStoreList(StoreNewDto dto);
    List<StoreNewDto> getBToAStoreListForPush(StoreNewDto dto);

    List<CompanyNewDto> checkCompanyByName(CompanyNewDto dto);

}
