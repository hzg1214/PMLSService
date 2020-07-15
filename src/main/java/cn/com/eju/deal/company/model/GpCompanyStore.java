package cn.com.eju.deal.company.model;

/**
 * Created by Sky on 2016/3/28.
 * 公司门店表
 */
public class GpCompanyStore {

    /**
     * 主键
     */
    private int id;

    /**
     * 公司编号
     */
    private int companyId;

    /**
     * 门店编号
     */
    private int storeId;

    /**
     * 是否删除
     */
    private boolean isDelete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }
}

