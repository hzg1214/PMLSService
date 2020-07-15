package cn.com.eju.deal.dto.exception;

/**
 * Created by tanlang on 2017-07-24.
 */
public class ExceptionDto {

    private Integer centerId;
    private Integer storeId;
    private String storeIdStr;
    private String storeNo;
    private String storeName;
    private String storeCenter;
    private String userCode;
    private String username;
    private Integer status;
    private String usernameCenter;

    private Integer createrId;
    private String createrCode;
    private String createrName;
    private String reason;
    private String addressDetail;//门店地址
    private String contractType;//签约类型

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getStoreIdStr() {
        return storeIdStr;
    }

    public void setStoreIdStr(String storeIdStr) {
        this.storeIdStr = storeIdStr;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getCreaterCode() {
        return createrCode;
    }

    public void setCreaterCode(String createrCode) {
        this.createrCode = createrCode;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public String getCreaterName() {
        return createrName;
    }

    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getStoreCenter() {
        return storeCenter;
    }

    public void setStoreCenter(String storeCenter) {
        this.storeCenter = storeCenter;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCenter() {
        return usernameCenter;
    }

    public void setUsernameCenter(String usernameCenter) {
        this.usernameCenter = usernameCenter;
    }
}
