package cn.com.eju.deal.cashbill.model;

import cn.com.eju.deal.dto.common.FileRecordMainDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class CashBillProject {
    private Integer id;

    private String estateId;

    private String projectNo;

    private String estateNm;

    private Date recordTime;

    private Date predictPayTime;

    private Integer payType;

    private String payTypeNm;

    private BigDecimal amountNoTax;

    private BigDecimal amountTax;

    private BigDecimal amountTotal;

    private Integer submitStatus;

    private String userCode;

    private String userName;

    private Date dateCreate;

    private Integer userIdCreate;

    private Boolean delFlag;

    private String fileRecordMainIds;

    private String cityNo;

    private Integer isSpecialProject;

    private List<CashBillCompany> companyList;

    private List<FileRecordMainDto> fileList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getEstateNm() {
        return estateNm;
    }

    public void setEstateNm(String estateNm) {
        this.estateNm = estateNm;
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public Date getPredictPayTime() {
        return predictPayTime;
    }

    public void setPredictPayTime(Date predictPayTime) {
        this.predictPayTime = predictPayTime;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getAmountNoTax() {
        return amountNoTax;
    }

    public void setAmountNoTax(BigDecimal amountNoTax) {
        this.amountNoTax = amountNoTax;
    }

    public BigDecimal getAmountTax() {
        return amountTax;
    }

    public void setAmountTax(BigDecimal amountTax) {
        this.amountTax = amountTax;
    }

    public BigDecimal getAmountTotal() {
        return amountTotal;
    }

    public void setAmountTotal(BigDecimal amountTotal) {
        this.amountTotal = amountTotal;
    }

    public Integer getSubmitStatus() {
        return submitStatus;
    }

    public void setSubmitStatus(Integer submitStatus) {
        this.submitStatus = submitStatus;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Integer userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Boolean getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Boolean delFlag) {
        this.delFlag = delFlag;
    }

    public List<CashBillCompany> getCompanyList() {
        return companyList;
    }

    public void setCompanyList(List<CashBillCompany> companyList) {
        this.companyList = companyList;
    }

    public List<FileRecordMainDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRecordMainDto> fileList) {
        this.fileList = fileList;
    }

    public String getFileRecordMainIds() {
        return fileRecordMainIds;
    }

    public void setFileRecordMainIds(String fileRecordMainIds) {
        this.fileRecordMainIds = fileRecordMainIds;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPayTypeNm() {
        return payTypeNm;
    }

    public void setPayTypeNm(String payTypeNm) {
        this.payTypeNm = payTypeNm;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }
    //选择的考核主体
    private String lnkAccountProject;
    private String lnkAccountProjectCode;

	public String getLnkAccountProject() {
		return lnkAccountProject;
	}

	public void setLnkAccountProject(String lnkAccountProject) {
		this.lnkAccountProject = lnkAccountProject;
	}

	public String getLnkAccountProjectCode() {
		return lnkAccountProjectCode;
	}

	public void setLnkAccountProjectCode(String lnkAccountProjectCode) {
		this.lnkAccountProjectCode = lnkAccountProjectCode;
	}


    public Integer getIsSpecialProject() {
        return isSpecialProject;
    }

    public void setIsSpecialProject(Integer isSpecialProject) {
        this.isSpecialProject = isSpecialProject;
    }

}