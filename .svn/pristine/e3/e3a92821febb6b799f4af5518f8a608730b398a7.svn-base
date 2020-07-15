package cn.com.eju.deal.contract.dao;

import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;

import java.util.List;

public interface ContractChangeNewMapper extends IDao<ContractChange> {

    List<ContractChange> queryContractChangeNewList(Integer contractId);

    ContractDto getContractById(Integer contractId);

    StoreDto getStoreById(Integer storeId);

    int saveContractChange(ContractChangeDto contractChangeDto);

    int saveContractChangeStore(StoreDto storeDto);

    int updateContractChange(ContractChangeDto contractChangeDto);

    int updateContractChangeStore(StoreDto storeDto);

    ContractChangeDto findContractChangeNewById(Integer id);

    StoreDto findContractChangeNewStoreById(Integer id);

    List<ContractFileDto> getContractChangeNewFileList(Integer refId);
}
