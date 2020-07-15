package cn.com.eju.deal.mapper.cashier;

import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.model.cashier.BankInfo;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.model.cashier.StoreDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;

import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
public interface CashierMapper {

    List<BankInfo> getBankAccountList(BankInfo dto);

    List<ContractNewDto> getCashierContractList4(ContractNewDto dto);


    List<ContractNewDto> getCashierContractList4Cashier(ContractNewDto dto);

    List<ContractNewDto> getCashierContractById(ContractNewDto dto);

    int saveCashier(CashierDto dto);

    CashierDto getCashierById(CashierDto dto);

    int updateCashierById(CashierDto dto);

    int saveCashierDetail(List<StoreDto> list);

    List<CommonCodeDto> getCollectionMethodList(CashierDto dto);

    void deleteCashierContractById(CashierDto dto);

    CashierDto getCashierByReceiveNo(CashierDto cashierDto);

    String getSkTypeCode(CashierDto dto);

    StoreDto checkCashier(StoreDto storeDto);

    StoreDto checkCashier4Service(StoreDto storeDto);

    void saveCashierDetail4Service(List<StoreDto> storeDtoList);

    void deleteServiceContractById(CashierDto dto);

    List<CommonCodeDto> getReceiveType(CashierDto dto);
}
