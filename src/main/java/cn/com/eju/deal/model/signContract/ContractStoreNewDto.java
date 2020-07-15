package cn.com.eju.deal.model.signContract;

/**
 * Created by Administrator on 2017/6/12.
 */
public class ContractStoreNewDto {

    /**
     * 门店编号
     */
    private String storeId;
    /**
     * 合同id
     */
    private Integer contractId;
    /**
     * 门店详细地址
     */
    private String addressDetail;
    /**
     * 门店状态（0未锁定，1已锁定）
     */
    private Integer storeStatus;
    /**
     * 合同类型
     */
    private String contractType;

    /**
     * 联系人名称    联系人信息
     */
    private String contactsName;
    /**
     * 联系人电话    联系人信息
     */
    private String contactsPhone;

    /**
     * 类型
     */
    private Integer ABTypeStore;

    /**
     * 乙类合同等级
     */
    private String BTypeStore;
    
    private String storeNo;

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public Integer getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(Integer storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public Integer getABTypeStore() {
        return ABTypeStore;
    }

    public void setABTypeStore(Integer ABTypeStore) {
        this.ABTypeStore = ABTypeStore;
    }

    public String getBTypeStore() {
        return BTypeStore;
    }

    public void setBTypeStore(String BTypeStore) {
        this.BTypeStore = BTypeStore;
    }

    /**
     * @return the storeNo
     */
    public String getStoreNo() {
        return storeNo;
    }

    /**
     * @param storeNo the storeNo to set
     */
    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }
}
