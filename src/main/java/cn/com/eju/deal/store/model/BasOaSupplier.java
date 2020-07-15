package cn.com.eju.deal.store.model;

public class BasOaSupplier {

    private Integer id;
    private String companyNo;
    private String oaSupplierCode;
    private String oaSupplierName;
    private String BusinessLicenseNo;
    private boolean delFlag;
    
    private String payoutId;
    private String payoutName;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
     * @return the companyNo
     */
    public String getCompanyNo() {
        return companyNo;
    }
    /**
     * @param companyNo the companyNo to set
     */
    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }
    /**
     * @return the oaSupplierCode
     */
    public String getOaSupplierCode() {
        return oaSupplierCode;
    }
    /**
     * @param oaSupplierCode the oaSupplierCode to set
     */
    public void setOaSupplierCode(String oaSupplierCode) {
        this.oaSupplierCode = oaSupplierCode;
    }
    /**
     * @return the oaSupplierName
     */
    public String getOaSupplierName() {
        return oaSupplierName;
    }
    /**
     * @param oaSupplierName the oaSupplierName to set
     */
    public void setOaSupplierName(String oaSupplierName) {
        this.oaSupplierName = oaSupplierName;
    }
    /**
     * @return the businessLicenseNo
     */
    public String getBusinessLicenseNo() {
        return BusinessLicenseNo;
    }
    /**
     * @param businessLicenseNo the businessLicenseNo to set
     */
    public void setBusinessLicenseNo(String businessLicenseNo) {
        BusinessLicenseNo = businessLicenseNo;
    }
    /**
     * @return the delFlag
     */
    public boolean isDelFlag() {
        return delFlag;
    }
    /**
     * @param delFlag the delFlag to set
     */
    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }
    /**
     * @return the payoutId
     */
    public String getPayoutId() {
        return payoutId;
    }
    /**
     * @param payoutId the payoutId to set
     */
    public void setPayoutId(String payoutId) {
        this.payoutId = payoutId;
    }
    /**
     * @return the payoutName
     */
    public String getPayoutName() {
        return payoutName;
    }
    /**
     * @param payoutName the payoutName to set
     */
    public void setPayoutName(String payoutName) {
        this.payoutName = payoutName;
    }
}
