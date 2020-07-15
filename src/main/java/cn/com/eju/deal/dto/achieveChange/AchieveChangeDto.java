package cn.com.eju.deal.dto.achieveChange;

/**
 * Created by Administrator on 2017/6/23.
 */
public class AchieveChangeDto {
    private String storeNo;
    private String Name;
    private int storeId;
    private String changebefore;
    private String changeafter;
    private String dateCreate;
    private String usercreate;
    private String type;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUsercreate() {
        return usercreate;
    }

    public void setUsercreate(String usercreate) {
        this.usercreate = usercreate;
    }

    public String getChangebefore() {
        return changebefore;
    }

    public void setChangebefore(String changebefore) {
        this.changebefore = changebefore;
    }

    public String getChangeafter() {
        return changeafter;
    }

    public void setChangeafter(String changeafter) {
        this.changeafter = changeafter;
    }
}
