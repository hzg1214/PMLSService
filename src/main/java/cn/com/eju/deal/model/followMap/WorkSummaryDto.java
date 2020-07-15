package cn.com.eju.deal.model.followMap;

import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xu on 2017/5/2.
 */
public class WorkSummaryDto {
    private String wsDetailId;//工作总结填写明细id
    private String wsSubId;//工作总结id
    private String wsId;//工作总结类型id
    private String wsName;//工作总结名称
    private String wsType;//工作总结分类
    private String wsTypeName;//工作总结分类名称
    private Integer sort;//排序字段
    private String answerType;//答案类型
    private String followId;//跟进id
    private String answerId;//答案id
    private String answerName;//答案名称
    private String createUser;//创建人
    private String createTime;//创建时间
    private String updateUser;//修改人
    private String updateTime;//修改时间
    private String pictureRefId;//图片关联id
    private List<AnswersDto> answerList;//答案列表

    private List<WorkSummaryDto> workSummarySubServiceList=new ArrayList<WorkSummaryDto>();//服务项目
    private List<WorkSummaryDto> workSummarySubFindList=new ArrayList<WorkSummaryDto>();//调查项目
    private List<WorkSummaryDto> workSummarySubCheckList=new ArrayList<WorkSummaryDto>();//检查项目

    private List<WXPictureDto> workSummaryPicList=new ArrayList<WXPictureDto>();//工作总结图片

    public List<WXPictureDto> getWorkSummaryPicList() {
        return workSummaryPicList;
    }

    public void setWorkSummaryPicList(List<WXPictureDto> workSummaryPicList) {
        this.workSummaryPicList = workSummaryPicList;
    }

    public List<AnswersDto> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswersDto> answerList) {
        this.answerList = answerList;
    }

    public List<WorkSummaryDto> getWorkSummarySubServiceList() {
        return workSummarySubServiceList;
    }

    public void setWorkSummarySubServiceList(List<WorkSummaryDto> workSummarySubServiceList) {
        this.workSummarySubServiceList = workSummarySubServiceList;
    }

    public List<WorkSummaryDto> getWorkSummarySubFindList() {
        return workSummarySubFindList;
    }

    public void setWorkSummarySubFindList(List<WorkSummaryDto> workSummarySubFindList) {
        this.workSummarySubFindList = workSummarySubFindList;
    }

    public List<WorkSummaryDto> getWorkSummarySubCheckList() {
        return workSummarySubCheckList;
    }

    public void setWorkSummarySubCheckList(List<WorkSummaryDto> workSummarySubCheckList) {
        this.workSummarySubCheckList = workSummarySubCheckList;
    }

    public String getWsDetailId() {
        return wsDetailId;
    }

    public void setWsDetailId(String wsDetailId) {
        this.wsDetailId = wsDetailId;
    }

    public String getWsSubId() {
        return wsSubId;
    }

    public void setWsSubId(String wsSubId) {
        this.wsSubId = wsSubId;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getPictureRefId() {
        return pictureRefId;
    }

    public void setPictureRefId(String pictureRefId) {
        this.pictureRefId = pictureRefId;
    }

    public String getFollowId() {
        return followId;
    }

    public void setFollowId(String followId) {
        this.followId = followId;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public String getWsId() {
        return wsId;
    }

    public void setWsId(String wsId) {
        this.wsId = wsId;
    }

    public String getWsName() {
        return wsName;
    }

    public void setWsName(String wsName) {
        this.wsName = wsName;
    }

    public String getWsType() {
        return wsType;
    }

    public void setWsType(String wsType) {
        this.wsType = wsType;
    }

    public String getWsTypeName() {
        return wsTypeName;
    }

    public void setWsTypeName(String wsTypeName) {
        this.wsTypeName = wsTypeName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }
}
