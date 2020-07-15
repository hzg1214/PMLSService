package cn.com.eju.deal.cashbill.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.com.eju.deal.common.model.FileRecordMain;
import cn.com.eju.deal.dto.contract.ContractFileDto;

/**
 * 历史遗留  CashBillCompany所有字段都存在了
 * */
public class CashBillDto {
    private Integer id;
    //请款单号
    private String cashBillNo;
    //oa单号
    private String oaNo;
    private String frameOaNo;
    //公司id
    private Integer companyId;
    //公司名称
    private String companyNo;
    private String companyName;
    //项目编号、名称
    private String projectNo;
    private String estateNm;
    //不含税总计
    private BigDecimal amountNoTax;
    //税款总计
    private BigDecimal amountTax;
    //请款总计
    private BigDecimal amountTotal;
    //提交OA状态
    private Integer submitOaStatus;
    private String submitOaStatusNm;
    //申请人
    private String userCode;
    private String userName;
    //申请时间
    private Date applyTime;
    private Integer comParentId;
    private Integer proParentId;
   
	//房源列表
    private List<CashBillReport> reportDetails;
    //楼盘estateId
    private String estateId;
    
    private Integer approveStatus;// 请款审核状态
    private String approveStatusNm;//审核状态str
    
    private BigDecimal areaTotal;	//销售面积总计	
    private BigDecimal roughAmountTotal; 	//大定金额总计
    private BigDecimal dealAmountTotal ;	//成销金额总计
    private BigDecimal sqYjsrAmountTotal ;	//应计收入总计
    private BigDecimal sqYjfyAmountTotal ;	//应计返佣总计
    private BigDecimal sqYjdyAmountTotal ;	//应计垫佣总计
    private BigDecimal sqSjsrAmountTotal ;	//实收收入总计
    private BigDecimal sqSjfyAmountTotal ;	//实际返佣总计
    private BigDecimal sqSjdyAmountTotal ;	//实际垫佣总计
    private BigDecimal requestAmountTotal;	//含税请款金额总计
    private BigDecimal taxAmountTotal 	;	//税额总计
    
    private String accountProject; //核算主体
    private String accountProjectNo ;//核算主体编号
//    private String frameOaNo; //框架协议
    private String vendorId ;//供应商ID
    private String vendorCode ;//供应商编码
    private String vendorName ;//供应商名称
    private String receiveBankName; //收款银行
    private String receiveBankAccountCardCode;//收款银行账号 
    private String receiveBankAccountName ;//收款户名
    private String receiveBankProvinceName ;//收款省份
    private String receiveBankCityName ;//收款城市
    private String receiveBankSerialNo ;//序号
    private Date recordTime ;//project入账日期
    private Date recordDate ;//company入账日期
    private Date predictPayTime ;//预付款日期
    private Integer payType ;//付款方式
    private String payTypeNm ;//付款方式
    private Date approveTime ;//审核时间
    private String fileRecordMainIds;// 文件ID（数组）
	private List<ContractFileDto> cashBillFileList;// 附件列表
	private String remarks;// 备注

	private List<Map<String,Object>> checkBodyList;

	private String frameOaName;
	private String flowId;
	private Date dateCreate;
	private Integer userIdCreate;

	private Boolean delFlag;

	private String businessLicenseNo;

	private String bankName;

	private String bankAccount;

	private String oaProjectNo;

	private String oaProjectName;

	private String frameFileUrl;

	private String frameFileName;
	private String ISKJ;
	private int syncFlag;

	/**
	 * 是否包含冲抵请款，0：不包含，1：包含
	 */
	private Boolean offSetFlag;

	private Integer formState;//发送oa状态码

	private String errmsg;//oa返回msg

	private Date oaStartDate;//oa返回时间

	private String templateCode;//模板编号

	private String templateName;//模板名称

	private String pjsNostr;//结算书编号

	private String inValid;//作废：1

	private String cityNo;

	private List<FileRecordMain> fileList=new ArrayList<FileRecordMain>();//附件图片集合

	public Boolean getOffSetFlag() {
		return offSetFlag;
	}

	public void setOffSetFlag(Boolean offSetFlag) {
		this.offSetFlag = offSetFlag;
	}
    
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public String getFileRecordMainIds() {
		return fileRecordMainIds;
	}
	public void setFileRecordMainIds(String fileRecordMainIds) {
		this.fileRecordMainIds = fileRecordMainIds;
	}
	public List<ContractFileDto> getCashBillFileList() {
		return cashBillFileList;
	}
	public void setCashBillFileList(List<ContractFileDto> cashBillFileList) {
		this.cashBillFileList = cashBillFileList;
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
	public String getAccountProject() {
		return accountProject;
	}
	public void setAccountProject(String accountProject) {
		this.accountProject = accountProject;
	}
	public String getAccountProjectNo() {
		return accountProjectNo;
	}
	public void setAccountProjectNo(String accountProjectNo) {
		this.accountProjectNo = accountProjectNo;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	public String getVendorCode() {
		return vendorCode;
	}
	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public String getReceiveBankName() {
		return receiveBankName;
	}
	public void setReceiveBankName(String receiveBankName) {
		this.receiveBankName = receiveBankName;
	}
	public String getReceiveBankAccountCardCode() {
		return receiveBankAccountCardCode;
	}
	public void setReceiveBankAccountCardCode(String receiveBankAccountCardCode) {
		this.receiveBankAccountCardCode = receiveBankAccountCardCode;
	}
	public String getReceiveBankAccountName() {
		return receiveBankAccountName;
	}
	public void setReceiveBankAccountName(String receiveBankAccountName) {
		this.receiveBankAccountName = receiveBankAccountName;
	}
	public String getReceiveBankProvinceName() {
		return receiveBankProvinceName;
	}
	public void setReceiveBankProvinceName(String receiveBankProvinceName) {
		this.receiveBankProvinceName = receiveBankProvinceName;
	}
	public String getReceiveBankCityName() {
		return receiveBankCityName;
	}
	public void setReceiveBankCityName(String receiveBankCityName) {
		this.receiveBankCityName = receiveBankCityName;
	}
	public String getReceiveBankSerialNo() {
		return receiveBankSerialNo;
	}
	public void setReceiveBankSerialNo(String receiveBankSerialNo) {
		this.receiveBankSerialNo = receiveBankSerialNo;
	}
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getPayTypeNm() {
		return payTypeNm;
	}
	public void setPayTypeNm(String payTypeNm) {
		this.payTypeNm = payTypeNm;
	}
	public Date getApproveTime() {
		return approveTime;
	}
	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}
	public BigDecimal getAreaTotal() {
		return areaTotal;
	}
	public void setAreaTotal(BigDecimal areaTotal) {
		this.areaTotal = areaTotal;
	}
	public BigDecimal getRoughAmountTotal() {
		return roughAmountTotal;
	}
	public void setRoughAmountTotal(BigDecimal roughAmountTotal) {
		this.roughAmountTotal = roughAmountTotal;
	}
	public BigDecimal getDealAmountTotal() {
		return dealAmountTotal;
	}
	public void setDealAmountTotal(BigDecimal dealAmountTotal) {
		this.dealAmountTotal = dealAmountTotal;
	}
	public BigDecimal getSqYjsrAmountTotal() {
		return sqYjsrAmountTotal;
	}
	public void setSqYjsrAmountTotal(BigDecimal sqYjsrAmountTotal) {
		this.sqYjsrAmountTotal = sqYjsrAmountTotal;
	}
	public BigDecimal getSqYjfyAmountTotal() {
		return sqYjfyAmountTotal;
	}
	public void setSqYjfyAmountTotal(BigDecimal sqYjfyAmountTotal) {
		this.sqYjfyAmountTotal = sqYjfyAmountTotal;
	}
	public BigDecimal getSqYjdyAmountTotal() {
		return sqYjdyAmountTotal;
	}
	public void setSqYjdyAmountTotal(BigDecimal sqYjdyAmountTotal) {
		this.sqYjdyAmountTotal = sqYjdyAmountTotal;
	}
	public BigDecimal getSqSjsrAmountTotal() {
		return sqSjsrAmountTotal;
	}
	public void setSqSjsrAmountTotal(BigDecimal sqSjsrAmountTotal) {
		this.sqSjsrAmountTotal = sqSjsrAmountTotal;
	}
	public BigDecimal getSqSjfyAmountTotal() {
		return sqSjfyAmountTotal;
	}
	public void setSqSjfyAmountTotal(BigDecimal sqSjfyAmountTotal) {
		this.sqSjfyAmountTotal = sqSjfyAmountTotal;
	}
	public BigDecimal getSqSjdyAmountTotal() {
		return sqSjdyAmountTotal;
	}
	public void setSqSjdyAmountTotal(BigDecimal sqSjdyAmountTotal) {
		this.sqSjdyAmountTotal = sqSjdyAmountTotal;
	}
	public BigDecimal getRequestAmountTotal() {
		return requestAmountTotal;
	}
	public void setRequestAmountTotal(BigDecimal requestAmountTotal) {
		this.requestAmountTotal = requestAmountTotal;
	}
	public BigDecimal getTaxAmountTotal() {
		return taxAmountTotal;
	}
	public void setTaxAmountTotal(BigDecimal taxAmountTotal) {
		this.taxAmountTotal = taxAmountTotal;
	}
	public String getApproveStatusNm() {
		return approveStatusNm;
	}
	public void setApproveStatusNm(String approveStatusNm) {
		this.approveStatusNm = approveStatusNm;
	}
	public Integer getApproveStatus() {
		return approveStatus;
	}
	public void setApproveStatus(Integer approveStatus) {
		this.approveStatus = approveStatus;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCashBillNo() {
		return cashBillNo;
	}
	public void setCashBillNo(String cashBillNo) {
		this.cashBillNo = cashBillNo;
	}
	public String getOaNo() {
		return oaNo;
	}
	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
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
	public Integer getSubmitOaStatus() {
		return submitOaStatus;
	}
	public void setSubmitOaStatus(Integer submitOaStatus) {
		this.submitOaStatus = submitOaStatus;
	}
	public String getSubmitOaStatusNm() {
		return submitOaStatusNm;
	}
	public void setSubmitOaStatusNm(String submitOaStatusNm) {
		this.submitOaStatusNm = submitOaStatusNm;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public List<CashBillReport> getReportDetails() {
		return reportDetails;
	}
	public void setReportDetails(List<CashBillReport> reportDetails) {
		this.reportDetails = reportDetails;
	}
	public Integer getComParentId() {
		return comParentId;
	}
	public void setComParentId(Integer comParentId) {
		this.comParentId = comParentId;
	}
	public Integer getProParentId() {
		return proParentId;
	}
	public void setProParentId(Integer proParentId) {
		this.proParentId = proParentId;
	}
	//总面积
	private Long areaSum;
	//大定合计
    private BigDecimal roughAmountSum;
    //成销合计
    private BigDecimal dealAmountSum;

	public Long getAreaSum() {
		return areaSum;
	}
	public void setAreaSum(Long areaSum) {
		this.areaSum = areaSum;
	}
	public BigDecimal getRoughAmountSum() {
		return roughAmountSum;
	}
	public void setRoughAmountSum(BigDecimal roughAmountSum) {
		this.roughAmountSum = roughAmountSum;
	}
	public BigDecimal getDealAmountSum() {
		return dealAmountSum;
	}
	public void setDealAmountSum(BigDecimal dealAmountSum) {
		this.dealAmountSum = dealAmountSum;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getFrameOaNo() {
		return frameOaNo;
	}
	public void setFrameOaNo(String frameOaNo) {
		this.frameOaNo = frameOaNo;
	}

	public String getPjsNostr() {
		return pjsNostr;
	}

	public void setPjsNostr(String pjsNostr) {
		this.pjsNostr = pjsNostr;
	}

	public String getInValid() {
		return inValid;
	}

	public void setInValid(String inValid) {
		this.inValid = inValid;
	}

	public List<Map<String, Object>> getCheckBodyList() {
		return checkBodyList;
	}

	public void setCheckBodyList(List<Map<String, Object>> checkBodyList) {
		this.checkBodyList = checkBodyList;
	}

	public String getFrameOaName() {
		return frameOaName;
	}

	public void setFrameOaName(String frameOaName) {
		this.frameOaName = frameOaName;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
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

	public String getBusinessLicenseNo() {
		return businessLicenseNo;
	}

	public void setBusinessLicenseNo(String businessLicenseNo) {
		this.businessLicenseNo = businessLicenseNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getOaProjectNo() {
		return oaProjectNo;
	}

	public void setOaProjectNo(String oaProjectNo) {
		this.oaProjectNo = oaProjectNo;
	}

	public String getOaProjectName() {
		return oaProjectName;
	}

	public void setOaProjectName(String oaProjectName) {
		this.oaProjectName = oaProjectName;
	}

	public String getFrameFileUrl() {
		return frameFileUrl;
	}

	public void setFrameFileUrl(String frameFileUrl) {
		this.frameFileUrl = frameFileUrl;
	}

	public String getFrameFileName() {
		return frameFileName;
	}

	public void setFrameFileName(String frameFileName) {
		this.frameFileName = frameFileName;
	}

	public String getISKJ() {
		return ISKJ;
	}

	public void setISKJ(String ISKJ) {
		this.ISKJ = ISKJ;
	}

	public int getSyncFlag() {
		return syncFlag;
	}

	public void setSyncFlag(int syncFlag) {
		this.syncFlag = syncFlag;
	}

	public Integer getFormState() {
		return formState;
	}

	public void setFormState(Integer formState) {
		this.formState = formState;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public Date getOaStartDate() {
		return oaStartDate;
	}

	public void setOaStartDate(Date oaStartDate) {
		this.oaStartDate = oaStartDate;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public List<FileRecordMain> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileRecordMain> fileList) {
		this.fileList = fileList;
	}

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
}