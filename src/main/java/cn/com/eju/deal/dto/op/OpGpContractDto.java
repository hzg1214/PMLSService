package cn.com.eju.deal.dto.op;

import cn.com.eju.deal.dto.store.StoreOpGpDto;

import java.util.List;

/**
 * Created by haidan on 2018/9/25.
 */
public class OpGpContractDto {
    private String companyNo;
    private String contractNo;
    private List<StoreOpGpDto> storeList;
    private String creditCode;

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public List<StoreOpGpDto> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreOpGpDto> storeList) {
        this.storeList = storeList;
    }
}
