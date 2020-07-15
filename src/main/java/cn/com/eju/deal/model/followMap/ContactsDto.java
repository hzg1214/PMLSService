package cn.com.eju.deal.model.followMap;

/**
 * Created by xu on 2017/4/27.
 */
public class ContactsDto {
    private Integer contactsId;//联系人id
    private String contactsNo;//联系人编号
    private String contactsName;//联系人姓名
    private String contactsPhone;//联系人电话
    private Integer storeId;//门店id
    private String storeName;//门店名称
    private String isDelete;//是否删除
    private String userCreate;//创建人
    private String dateCreate;//创建时间
    private Integer employDirectFlag;//是否直聘账号

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getContactsId() {
        return contactsId;
    }

    public void setContactsId(Integer contactsId) {
        this.contactsId = contactsId;
    }

    public String getContactsNo() {
        return contactsNo;
    }

    public void setContactsNo(String contactsNo) {
        this.contactsNo = contactsNo;
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

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getEmployDirectFlag() {
        return employDirectFlag;
    }

    public void setEmployDirectFlag(Integer employDirectFlag) {
        this.employDirectFlag = employDirectFlag;
    }
}
