package cn.com.eju.deal.store.model;

import cn.com.eju.deal.model.sweepStreets.WXPictureDto;

import java.util.Date;
import java.util.List;

public class StoreAuthCheck {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String authCheckNo;
    private Integer storeId;
    private Integer contractId;
    private Date checkDate;
    private Integer checkStatus;
    private Date authTime;
    private String remark;
    
    private Date dateCreate;
    private Integer userCreate;
    private Date dateUpdate;
    private Integer userUpdate;
    private Integer delFlag;

    private String userCreateNm;
    private String checkStatusNm;

    private String checkDateStr;
    private String dateCreateStr;

    private List<WXPictureDto> fileRecordMainDtoList;
    private List<StoreAuthCheckLog> logList;
    private String fileIds;

    private String storeNo;
    private String storeName;
    private String addressDetail;
    private String maintainer;
    private String maintainerName;
    private String userCreateCode;
    
    //审核描述
    private String auditDesc;
    //验收审核人
    private Integer auditUserId;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthCheckNo() {
        return authCheckNo;
    }

    public void setAuthCheckNo(String authCheckNo) {
        this.authCheckNo = authCheckNo;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getAuthTime() {
        return authTime;
    }

    public void setAuthTime(Date authTime) {
        this.authTime = authTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    public String getUserCreateNm() {
        return userCreateNm;
    }

    public void setUserCreateNm(String userCreateNm) {
        this.userCreateNm = userCreateNm;
    }

    public String getCheckStatusNm() {
        return checkStatusNm;
    }

    public void setCheckStatusNm(String checkStatusNm) {
        this.checkStatusNm = checkStatusNm;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public List<WXPictureDto> getFileRecordMainDtoList() {
        return fileRecordMainDtoList;
    }

    public void setFileRecordMainDtoList(List<WXPictureDto> fileRecordMainDtoList) {
        this.fileRecordMainDtoList = fileRecordMainDtoList;
    }

    public String getCheckDateStr() {
        return checkDateStr;
    }

    public void setCheckDateStr(String checkDateStr) {
        this.checkDateStr = checkDateStr;
    }

    public String getDateCreateStr() {
        return dateCreateStr;
    }

    public void setDateCreateStr(String dateCreateStr) {
        this.dateCreateStr = dateCreateStr;
    }

    public List<StoreAuthCheckLog> getLogList() {
        return logList;
    }

    public void setLogList(List<StoreAuthCheckLog> logList) {
        this.logList = logList;
    }

    public String getStoreNo() {
        return storeNo;
    }

    public void setStoreNo(String storeNo) {
        this.storeNo = storeNo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public String getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(String maintainer) {
        this.maintainer = maintainer;
    }

    public String getMaintainerName() {
        return maintainerName;
    }

    public void setMaintainerName(String maintainerName) {
        this.maintainerName = maintainerName;
    }

    public String getUserCreateCode() {
        return userCreateCode;
    }

    public void setUserCreateCode(String userCreateCode) {
        this.userCreateCode = userCreateCode;
    }
	public String getAuditDesc() {
		return auditDesc;
	}
	public void setAuditDesc(String auditDesc) {
		this.auditDesc = auditDesc;
	}

	public Integer getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(Integer auditUserId) {
		this.auditUserId = auditUserId;
	}
    
}
