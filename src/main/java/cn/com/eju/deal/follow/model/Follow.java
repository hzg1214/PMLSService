package cn.com.eju.deal.follow.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

public class Follow {
    private Integer followId;//跟进编号
    private Integer companyId;//客户编号
    private Integer storeId;//门店编号
    private String title;//主题
    private Integer followType;//跟进类型
    private Date dateCreate;//创建时间
    private String content;//内容
    private Integer userCreate;//用户编号
    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    private Boolean isDelete;//删除标记
    /**
     * 我的跟进位置
     */
    private String followPosition;
    //扩展字段
    private String followTypeName;//跟进类型
    private String userNameCreate;//用户姓名
    
    //扩展字段
    private String companyName;//客户名称
    private String storeName;//门店名称

    //新增字段
    private String signTime;//签到时间
    private String signLongitude;//签到纬度
    private String signLatitude;//签到经度
    private String signAddress;//签到地址
    private String signDistance;//签到位置与门店距离
    private String signOutTime;//签退时间
    private String signOutLongitude;//签退经度
    private String signOutLatitude;//签退纬度
    private String signOutAddress;//签退地址
    private String signOutDistance;//签退位置与门店距离
    private List<WXPictureDto> signPicList=new ArrayList<WXPictureDto>();//签到图片集合
    private List<WXPictureDto> signOutPicList=new ArrayList<WXPictureDto>();//签退图片集合

    public String getSignTime() {
        return signTime;
    }

    public void setSignTime(String signTime) {
        this.signTime = signTime;
    }

    public String getSignLongitude() {
        return signLongitude;
    }

    public void setSignLongitude(String signLongitude) {
        this.signLongitude = signLongitude;
    }

    public String getSignLatitude() {
        return signLatitude;
    }

    public void setSignLatitude(String signLatitude) {
        this.signLatitude = signLatitude;
    }

    public String getSignAddress() {
        return signAddress;
    }

    public void setSignAddress(String signAddress) {
        this.signAddress = signAddress;
    }

    public String getSignDistance() {
        return signDistance;
    }

    public void setSignDistance(String signDistance) {
        this.signDistance = signDistance;
    }

    public String getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(String signOutTime) {
        this.signOutTime = signOutTime;
    }

    public String getSignOutLongitude() {
        return signOutLongitude;
    }

    public void setSignOutLongitude(String signOutLongitude) {
        this.signOutLongitude = signOutLongitude;
    }

    public String getSignOutLatitude() {
        return signOutLatitude;
    }

    public void setSignOutLatitude(String signOutLatitude) {
        this.signOutLatitude = signOutLatitude;
    }

    public String getSignOutAddress() {
        return signOutAddress;
    }

    public void setSignOutAddress(String signOutAddress) {
        this.signOutAddress = signOutAddress;
    }

    public String getSignOutDistance() {
        return signOutDistance;
    }

    public void setSignOutDistance(String signOutDistance) {
        this.signOutDistance = signOutDistance;
    }

    public List<WXPictureDto> getSignPicList() {
        return signPicList;
    }

    public void setSignPicList(List<WXPictureDto> signPicList) {
        this.signPicList = signPicList;
    }

    public List<WXPictureDto> getSignOutPicList() {
        return signOutPicList;
    }

    public void setSignOutPicList(List<WXPictureDto> signOutPicList) {
        this.signOutPicList = signOutPicList;
    }

    /**
     * 门店名称
     * @return the storeName
     */
    public String getStoreName()
    {
        return storeName;
    }
    /**
     * 门店名称
     * @return the storeName
     */
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }
    /**
     * 跟进编号
     * @return the followId
     */
    public Integer getFollowId()
    {
        return followId;
    }
    /**
     * 跟进编号
     * @param followId the followId to set
     */
    public void setFollowId(Integer followId)
    {
        this.followId = followId;
    }
    /**
     * 客户编号
     * @return the companyId
     */
    public Integer getCompanyId()
    {
        return companyId;
    }
    /**
     * 客户编号
     * @param companyId the companyId to set
     */
    public void setCompanyId(Integer companyId)
    {
        this.companyId = companyId;
    }
    /**
     * 门店编号
     * @return the storeId
     */
    public Integer getStoreId()
    {
        return storeId;
    }
    /**
     * 门店编号
     * @param storeId the storeId to set
     */
    public void setStoreId(Integer storeId)
    {
        this.storeId = storeId;
    }
    /**
     * 主题
     * @return the title
     */
    public String getTitle()
    {
        return title;
    }
    /**
     * 主题
     * @param title the title to set
     */
    public void setTitle(String title)
    {
        this.title = title;
    }
    /**
     * 跟进类型
     * @return the followType
     */
    public Integer getFollowType()
    {
        return followType;
    }
    /**
     * 跟进类型
     * @param followType the followType to set
     */
    public void setFollowType(Integer followType)
    {
        this.followType = followType;
    }
    /**
     * 创建时间
     * @return the dateCreate
     */
    public Date getDateCreate()
    {
        return dateCreate;
    }
    /**
     * 创建时间
     * @param dateCreate the dateCreate to set
     */
    public void setDateCreate(Date dateCreate)
    {
        this.dateCreate = dateCreate;
    }
    /**
     * 内容
     * @return the content
     */
    public String getContent()
    {
        return content;
    }
    /**
     * 内容
     * @param content the content to set
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * 用户编号
     * @return the userCreate
     */
    public Integer getUserCreate()
    {
        return userCreate;
    }
    /**
     * 用户编号
     * @param userCreate the userCreate to set
     */
    public void setUserCreate(Integer userCreate)
    {
        this.userCreate = userCreate;
    }
    /**
     * 经度
     * @return the longitude
     */
    public BigDecimal getLongitude()
    {
        return longitude;
    }
    /**
     * 经度
     * @param longitude the longitude to set
     */
    public void setLongitude(BigDecimal longitude)
    {
        this.longitude = longitude;
    }
    /**
     * 纬度
     * @return the latitude
     */
    public BigDecimal getLatitude()
    {
        return latitude;
    }
    /**
     * 纬度
     * @param latitude the latitude to set
     */
    public void setLatitude(BigDecimal latitude)
    {
        this.latitude = latitude;
    }
    /**
     * 跟进类型
     * @return the followTypeName
     */
    public String getFollowTypeName()
    {
        return SystemParam.getDicValueByDicCode(this.followType + "");
    }
    /**
     * 跟进类型
     * @param followTypeName the followTypeName to set
     */
    public void setFollowTypeName(String followTypeName)
    {
        this.followTypeName = followTypeName;
    }
    /**
     * @return the isDelete
     */
    public Boolean getIsDelete()
    {
        return isDelete;
    }
    /**
     * @param isDelete the isDelete to set
     */
    public void setIsDelete(Boolean isDelete)
    {
        this.isDelete = isDelete;
    }
    /**
     * @return the companyName
     */
    public String getCompanyName()
    {
        return companyName;
    }
    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName)
    {
        this.companyName = companyName;
    }

    /**
     * @return the userNameCreate
     */
    public String getUserNameCreate()
    {
        return userNameCreate;
    }
    /**
     * @param userNameCreate the userNameCreate to set
     */
    public void setUserNameCreate(String userNameCreate)
    {
        this.userNameCreate = userNameCreate;
    }
    
    /** 
    * 获取我的跟进位置
    * @return followPosition 我的跟进位置
    */
    public String getFollowPosition()
    {
        return followPosition;
    }
    /** 
    * 设置我的跟进位置
    * @param followPosition 我的跟进位置
    */
    public void setFollowPosition(String followPosition)
    {
        this.followPosition = followPosition;
    }
    
}