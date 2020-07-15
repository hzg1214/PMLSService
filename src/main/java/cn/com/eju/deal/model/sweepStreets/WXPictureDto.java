package cn.com.eju.deal.model.sweepStreets;


/**
 * Created by xu on 2017/5/8.
 */
public class WXPictureDto {
    private String id;//图片id
    private String pictureRefId;//图片关联id
    private String smallPictureUrl;//小图url
    private String middlePictureUrl;//中图url
    private String bigPictureUrl;//大图url
    private int pictureStatus;//图片状态（0正常，10删除）
    private String createTime;//创建时间
    private String createUser;//创建人
    private String updateTime;//修改时间
    private String updateUser;//修改人
    private String deleteTime;//删除时间
    private String deleteUser;//删除人

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public String getSmallPictureUrl() {
        return smallPictureUrl;
    }

    public void setSmallPictureUrl(String smallPictureUrl) {
        this.smallPictureUrl = smallPictureUrl;
    }

    public String getMiddlePictureUrl() {
        return middlePictureUrl;
    }

    public void setMiddlePictureUrl(String middlePictureUrl) {
        this.middlePictureUrl = middlePictureUrl;
    }

    public String getBigPictureUrl() {
        return bigPictureUrl;
    }

    public void setBigPictureUrl(String bigPictureUrl) {
        this.bigPictureUrl = bigPictureUrl;
    }

    public int getPictureStatus() {
        return pictureStatus;
    }

    public void setPictureStatus(int pictureStatus) {
        this.pictureStatus = pictureStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(String deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getDeleteUser() {
        return deleteUser;
    }

    public void setDeleteUser(String deleteUser) {
        this.deleteUser = deleteUser;
    }
}
