package cn.com.eju.deal.model.followMap;

import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/4/26.
 */
public class FollowRecordDto {
    private Integer followId;//跟进记录id
    private Integer storeId;//门店id
    private String storeName;//门店名称
    private Integer userCreate;//创建人
    private String userNameCreate;//创建人姓名
    private String dateCreate;//创建时间
    private String content;//描述内容
    private String followType;//跟进方式
    private String followTypeName;//跟进方式名称
    private String followPurpose;//跟进目的
    private String followPurposeName;//跟进目的名称
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
    private String operationType;//操作类型（update可修改，show可查看）

    private String signPictureRefId;//签到图片
    private String signOutPictureRefId;//签退图片
    private String picString;//上传的图片字符串

    private String submitWorkSummaryString;//提交的工作总结
    private int auth;//点评权限 0:无 1：有
    private boolean commented;

    private List<FollowRecordDto> followRecordList=new ArrayList<FollowRecordDto>();
    private List<WXPictureDto> signPicList=new ArrayList<WXPictureDto>();//签到图片集合
    private List<WXPictureDto> signOutPicList=new ArrayList<WXPictureDto>();//签退图片集合
    private List<FollowCommentDto> followCommentList=new ArrayList<FollowCommentDto>();//跟进评论集合

    private Integer agentNum;//经纪人数（手动输入）
    private Integer storePersonNum;//经纪人数（选择的）

    public Integer getAgentNum() {
        return agentNum;
    }

    public void setAgentNum(Integer agentNum) {
        this.agentNum = agentNum;
    }

    public Integer getStorePersonNum() {
        return storePersonNum;
    }

    public void setStorePersonNum(Integer storePersonNum) {
        this.storePersonNum = storePersonNum;
    }

    public boolean isCommented() {
        return commented;
    }

    public void setCommented(boolean commented) {
        this.commented = commented;
    }

    public List<FollowCommentDto> getFollowCommentList() {
        return followCommentList;
    }

    public void setFollowCommentList(List<FollowCommentDto> followCommentList) {
        this.followCommentList = followCommentList;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
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

    public List<FollowRecordDto> getFollowRecordList() {
        return followRecordList;
    }

    public String getPicString() {
        return picString;
    }

    public void setPicString(String picString) {
        this.picString = picString;
    }

    public void setFollowRecordList(List<FollowRecordDto> followRecordList) {
        this.followRecordList = followRecordList;
    }

    public String getUserNameCreate() {
        return userNameCreate;
    }

    public void setUserNameCreate(String userNameCreate) {
        this.userNameCreate = userNameCreate;
    }

    private List<WorkSummaryDto> workSummarySubList=new ArrayList<WorkSummaryDto>();

    public String getSubmitWorkSummaryString() {
        return submitWorkSummaryString;
    }

    public void setSubmitWorkSummaryString(String submitWorkSummaryString) {
        this.submitWorkSummaryString = submitWorkSummaryString;
    }

    public List<WorkSummaryDto> getWorkSummarySubList() {
        return workSummarySubList;
    }

    public void setWorkSummarySubList(List<WorkSummaryDto> workSummarySubList) {
        this.workSummarySubList = workSummarySubList;
    }

    public String getSignPictureRefId() {
        return signPictureRefId;
    }

    public void setSignPictureRefId(String signPictureRefId) {
        this.signPictureRefId = signPictureRefId;
    }

    public String getSignOutPictureRefId() {
        return signOutPictureRefId;
    }

    public void setSignOutPictureRefId(String signOutPictureRefId) {
        this.signOutPictureRefId = signOutPictureRefId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Integer getFollowId() {
        return followId;
    }

    public void setFollowId(Integer followId) {
        this.followId = followId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFollowType() {
        return followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }

    public String getFollowTypeName() {
        return followTypeName;
    }

    public void setFollowTypeName(String followTypeName) {
        this.followTypeName = followTypeName;
    }

    public String getFollowPurpose() {
        return followPurpose;
    }

    public void setFollowPurpose(String followPurpose) {
        this.followPurpose = followPurpose;
    }

    public String getFollowPurposeName() {
        return followPurposeName;
    }

    public void setFollowPurposeName(String followPurposeName) {
        this.followPurposeName = followPurposeName;
    }

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
}
