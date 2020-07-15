package cn.com.eju.deal.contract.service;

import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.contract.dao.ContractChangeNewMapper;
import cn.com.eju.deal.contract.model.ContractChange;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.contract.ContractChangeDto;
import cn.com.eju.deal.dto.contract.ContractDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.contract.ContractInfoDto;
import cn.com.eju.deal.dto.store.StoreDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ContractChangeNewService extends BaseService<ContractChange> {

    @Resource
    private ContractChangeNewMapper contractChangeNewMapper;
    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    /**
     * 查询合同变更列表
     * @param contractId
     * @return
     */
    public List<ContractChange> queryContractChangeNewList(Integer contractId){
        return contractChangeNewMapper.queryContractChangeNewList(contractId);
    }

    public ContractInfoDto getContractAndStoreInfo(Integer storeId, Integer contractId) {
        ContractInfoDto contractInfo = new ContractInfoDto();
        ContractDto contract = contractChangeNewMapper.getContractById(contractId);
        contractInfo.setContract(contract);
        StoreDto store = contractChangeNewMapper.getStoreById(storeId);
        List list = new ArrayList();
        list.add(store);
        contractInfo.setStoreDetails(list);

        return contractInfo;
    }

    public ResultData<?> saveContractChange(ContractChangeDto contractChangeDto) {
        ResultData<String> resultData = new ResultData<>();

        //新增三方变更
        if(contractChangeDto.getId() == null){
            contractChangeDto.setSubmitOAStatus(21201);
            contractChangeDto.setChangeCompany(1);
            int count = contractChangeNewMapper.saveContractChange(contractChangeDto);
            if(count == 1){
                StoreDto storeDto = contractChangeDto.getStoreList().get(0);
                storeDto.setContractStopId(contractChangeDto.getId());

                if(storeDto.getABTypeStore().equals(19902)){
                    storeDto.setBTypeStore("20003");
                }

                count = contractChangeNewMapper.saveContractChangeStore(storeDto);
                if(count == 0){
                    resultData.setFail("保存失败");
                    return resultData;
                }

                //更新最新文件
                Map<String, Object> map1 = new HashMap<>();
                map1.put("contractId", contractChangeDto.getId());
                String[] array = contractChangeDto.getFileRecordMainIds().split(",");
                List<Integer> ids = new ArrayList<>();
                for(String id : array) {
                    ids.add(Integer.valueOf(id));
                }
                map1.put("ids", ids);
                fileRecordMainMapper.updateByCondition(map1);
            }else{
                resultData.setFail("更新失败");
                return resultData;
            }
        }else{
            contractChangeDto.setChangeCompany(1);
            //更新三方变更
            int count = contractChangeNewMapper.updateContractChange(contractChangeDto);
            if(count == 1){
                StoreDto storeDto = contractChangeDto.getStoreList().get(0);

                if(storeDto.getABTypeStore().equals(19902)){
                    storeDto.setBTypeStore("20003");
                }

                count = contractChangeNewMapper.updateContractChangeStore(storeDto);
                if(count == 0){
                    resultData.setFail("更新失败");
                    return resultData;
                }

                //删除原有文件
                Map<String, Object> map = new HashMap<>();
                map.put("contractId", contractChangeDto.getId());
                map.put("fileSourceId", 10);

                fileRecordMainMapper.deleteByCondition(map);

                //更新最新文件
                Map<String, Object> map1 = new HashMap<>();
                map1.put("contractId", contractChangeDto.getId());
                String[] array = contractChangeDto.getFileRecordMainIds().split(",");
                List<Integer> ids = new ArrayList<>();
                for(String id : array) {
                    ids.add(Integer.valueOf(id));
                }
                map1.put("ids", ids);
                fileRecordMainMapper.updateByCondition(map1);
            }
        }

        return resultData;
    }

    public ContractChangeDto findContractChangeNewById(Integer id) {
        ContractChangeDto contractChangeDto = contractChangeNewMapper.findContractChangeNewById(id);

        StoreDto store = contractChangeNewMapper.findContractChangeNewStoreById(id);
        List list = new ArrayList();
        list.add(store);
        contractChangeDto.setStoreList(list);

        final List<ContractFileDto> contractChangeNewFileList = contractChangeNewMapper.getContractChangeNewFileList(id);
        contractChangeDto.setPhotosFileList(contractChangeNewFileList);

        return contractChangeDto;
    }
}
