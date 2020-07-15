package cn.com.eju.deal.model.sweepStreets;

/**
 * Created by Administrator on 2017/8/9.
 */

import java.math.BigDecimal;
import java.util.Date;

/**
 * 门店位置审批记录表
 */
public class StoreLocationAuditDto {

    private Integer id;//ID

    private Integer storeId;//门店ID

    private StoreNewDto storeNewDto;//门店实体 查询数据关联使用

    private Integer centerId;//门店中心ID

    private Integer centerUserId;//中心负责人

    private String centerUserCode;//中心负责人Code(微信用户ID 发送通知使用)

    private BigDecimal oldLongitude;//旧经度

    private BigDecimal oldLatitude;//旧纬度

    private BigDecimal newLongitude;//新经度

    private BigDecimal newLatitude;//新纬度

    private Integer auditStatus;//审批状态：0，待审批，1审批通过，2审批不通过

    private String auditRemark;//审批备注

    private Integer createUserId;//创建用户ID

    private String createUserCode;//创建用户Code(微信用户ID 发送通知使用)

    private Date createDate;//创建日期

    private Integer updateUserId;//修改用户ID

    private Date updateDate;//修改日期


    public String getCreateUserCode() {
        return createUserCode;
    }

    public void setCreateUserCode(String createUserCode) {
        this.createUserCode = createUserCode;
    }

    public String getAuditRemark() {
        return auditRemark;
    }

    public void setAuditRemark(String auditRemark) {
        this.auditRemark = auditRemark;
    }

    public String getCenterUserCode() {
        return centerUserCode;
    }

    public void setCenterUserCode(String centerUserCode) {
        this.centerUserCode = centerUserCode;
    }

    public StoreNewDto getStoreNewDto() {
        return storeNewDto;
    }

    public void setStoreNewDto(StoreNewDto storeNewDto) {
        this.storeNewDto = storeNewDto;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
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

    public Integer getCenterUserId() {
        return centerUserId;
    }

    public void setCenterUserId(Integer centerUserId) {
        this.centerUserId = centerUserId;
    }

    public BigDecimal getOldLongitude() {
        return oldLongitude;
    }

    public void setOldLongitude(BigDecimal oldLongitude) {
        this.oldLongitude = oldLongitude;
    }

    public BigDecimal getOldLatitude() {
        return oldLatitude;
    }

    public void setOldLatitude(BigDecimal oldLatitude) {
        this.oldLatitude = oldLatitude;
    }

    public BigDecimal getNewLongitude() {
        return newLongitude;
    }

    public void setNewLongitude(BigDecimal newLongitude) {
        this.newLongitude = newLongitude;
    }

    public BigDecimal getNewLatitude() {
        return newLatitude;
    }

    public void setNewLatitude(BigDecimal newLatitude) {
        this.newLatitude = newLatitude;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }
}
