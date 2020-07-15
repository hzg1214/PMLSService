package cn.com.eju.deal.gpContract.model;

import java.util.Date;

/**
 * Created by haidan on 2018/9/14.
 */
public class GpContractStore {
    private Integer id;
    // 门店编号
    private Integer storeId;
    // 合同编号
    private Integer gpContractId;
    // 是否删除
    private Boolean isDelete;
    // 合同下的门店状态 0:正常 , 1:变更中, 2:终止
    private Integer storeState;
    // 门店是否撤销
    private String isCancel;
    //合同终止时间
    private Date contractStopDate;
    //门店地址
    private String addressDetail;
    private Integer flag;
    /**
     * 类型
     */
    private Integer abTypeStore;

    /**
     * 乙类合同等级
     */
    private String bTypeStore;

    private String bTypeStoreName;

    public String getbTypeStoreName() {
        return bTypeStoreName;
    }

    public void setbTypeStoreName(String bTypeStoreName) {
        this.bTypeStoreName = bTypeStoreName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getGpContractId() {
        return gpContractId;
    }

    public void setGpContractId(Integer gpContractId) {
        this.gpContractId = gpContractId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean delete) {
        isDelete = delete;
    }

    public Integer getStoreState() {
        return storeState;
    }

    public void setStoreState(Integer storeState) {
        this.storeState = storeState;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public Date getContractStopDate() {
        return contractStopDate;
    }

    public void setContractStopDate(Date contractStopDate) {
        this.contractStopDate = contractStopDate;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getAbTypeStore() {
        return abTypeStore;
    }

    public void setAbTypeStore(Integer abTypeStore) {
        this.abTypeStore = abTypeStore;
    }

    public String getbTypeStore() {
        return bTypeStore;
    }

    public void setbTypeStore(String bTypeStore) {
        this.bTypeStore = bTypeStore;
    }
}
