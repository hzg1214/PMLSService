package cn.com.eju.deal.service.cashier;

import cn.com.eju.deal.base.dto.code.CommonCodeDto;
import cn.com.eju.deal.common.dao.FileRecordMainMapper;
import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.cashier.CashierMapper;
import cn.com.eju.deal.model.cashier.BankInfo;
import cn.com.eju.deal.model.cashier.CashierDto;
import cn.com.eju.deal.model.cashier.StoreDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
@Service("cashierService")
public class CashierService {
    @Resource
    CashierMapper cashierMapper;

    @Resource
    private FileRecordMainMapper fileRecordMainMapper;

    public List<BankInfo> getBankAccountList(BankInfo dto) {
        List<BankInfo> result = cashierMapper.getBankAccountList(dto);
        return result;
    }

    public List<CommonCodeDto> getCollectionMethodList(CashierDto dto) {
        List<CommonCodeDto> result = cashierMapper.getCollectionMethodList(dto);
        return result;
    }

    public String getSkTypeCode(CashierDto dto) {
        String skTypeCode = cashierMapper.getSkTypeCode(dto);
        return skTypeCode;
    }

    public CashierDto saveCashier(CashierDto dto) {
        String key = dto.getContractNo() + dto.getCollectionType();
        synchronized (key) {
            String[] storeNos = dto.getStoreNoStr().split(",");
            String[] storeNames = dto.getStoreNameStr().split(",");
            String[] totalMoneys = dto.getTotalMoneyStr().split(",");
            String[] receivedMoneys = dto.getReceivedMoneyStr().split(",");
            String[] receivingMoneys = dto.getReceivingMoneyStr().split(",");
            String[] nowMoneys = dto.getNowMoneyStr().split(",");
            List<StoreDto> storeDtoList = new ArrayList<>();
            for (int i = 0; i < storeNos.length; i++) {
                StoreDto storeDto = new StoreDto();
                storeDto.setStoreNo(storeNos[i]);
                storeDto.setStoreName(storeNames[i]);
                storeDto.setTotalMoney(Double.valueOf(totalMoneys[i]));
                storeDto.setReceivedMoney(Double.valueOf(receivedMoneys[i]));
                storeDto.setReceivingMoney(Double.valueOf(receivingMoneys[i]));
                storeDto.setNowMoney(Double.valueOf(nowMoneys[i]));
                storeDto.setUserId(dto.getUserId());
                // 检测实收、在途是否改变
                StoreDto result;
                if ("17904".equals(dto.getCollectionType())) {
                    result = cashierMapper.checkCashier(storeDto);
                } else {
                    result = cashierMapper.checkCashier4Service(storeDto);
                }
                if (result == null) {
                    // 实收、在途已改变
                    dto.setStoreNo(storeDto.getStoreNo());
                    return dto;
                }
                storeDtoList.add(storeDto);
            }

            cashierMapper.saveCashier(dto);
            for (StoreDto storeDto : storeDtoList) {
                storeDto.setReceiveId(dto.getId());
            }
            if ("17904".equals(dto.getCollectionType())) {
                cashierMapper.saveCashierDetail(storeDtoList);
            } else {
                cashierMapper.saveCashierDetail4Service(storeDtoList);
            }

            if ("17909".equals(dto.getCollectionMethod())) {
                //转账才有凭证
                String[] fileRecordMainIds = dto.getFileRecordMainIdStr().split(",");
                for (String fileRecordMainId : fileRecordMainIds) {
                    FileRecordMain fileRecordMain = new FileRecordMain();
                    fileRecordMain.setFileRecordMainId(Integer.valueOf(fileRecordMainId));
                    fileRecordMain.setRefId(dto.getId());
                    fileRecordMainMapper.update(fileRecordMain);
                }
            }

            return dto;
        }
    }

    public ResultData<List<ContractNewDto>> getCashierContractList(ContractNewDto dto) {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list;
        if ("cashier".equals(dto.getClickType())) {
            //待付款
            list = cashierMapper.getCashierContractList4Cashier(dto);
        } else {
            //创建订单
            list = cashierMapper.getCashierContractList4(dto);

            /*CashierDto cashierDto = new CashierDto();
            cashierDto.setCityNo(dto.getCityNo());
            List<CommonCodeDto> receiveType = cashierMapper.getReceiveType(cashierDto);
            if (receiveType != null) {
                if (receiveType.size() == 1 && receiveType.get(0).getDicCode() == 17904) {
                    list = cashierMapper.getCashierContractList4(dto);
                } else if (receiveType.size() == 2) {
                    list = cashierMapper.getCashierContractList4FWF(dto);
                } else {
                    list = new ArrayList<>();
                }
            } else {
                list = new ArrayList<>();
            }*/
        }

        resultData.setTotalCount("0");
        if (null != list && !list.isEmpty()) {
            resultData.setReturnData(list);
            resultData.setTotalCount(list.get(0).getDataCount() + "");
        }
        return resultData;
    }

    public ResultData<List<ContractNewDto>> getCashierContractById(ContractNewDto dto) {
        ResultData<List<ContractNewDto>> resultData = new ResultData<List<ContractNewDto>>();
        List<ContractNewDto> list = cashierMapper.getCashierContractById(dto);
        if (null != list && !list.isEmpty()) {
            String cityNo = list.get(0).getCityNo();
            CashierDto cashierDto = new CashierDto();
            cashierDto.setCityNo(cityNo);
            List<CommonCodeDto> collectionMethodList = cashierMapper.getCollectionMethodList(cashierDto);
            list.get(0).setCollectionMethodList(collectionMethodList);

            BankInfo bankInfo = new BankInfo();
            bankInfo.setCityNo(cityNo);
            List<BankInfo> bankAccountList = cashierMapper.getBankAccountList(bankInfo);
            list.get(0).setBankInfoList(bankAccountList);

            resultData.setReturnData(list);
        }
        return resultData;
    }

    public ResultData deleteCashierContractById(CashierDto dto) {
        ResultData resultData = new ResultData();
        if ("保证金".equals(dto.getCollectionType())) {
            cashierMapper.deleteCashierContractById(dto);
        } else {
            cashierMapper.deleteServiceContractById(dto);
        }

        return resultData;
    }

    public ResultData getCashierById(CashierDto dto) {
        ResultData resultData = new ResultData();
        CashierDto resultDto = cashierMapper.getCashierById(dto);
        resultData.setReturnData(resultDto);
        return resultData;
    }

    public ResultData getReceiveType(CashierDto dto) {
        ResultData resultData = new ResultData();
        List<CommonCodeDto> resultDto = cashierMapper.getReceiveType(dto);
        resultData.setReturnData(resultDto);
        return resultData;
    }

}
