package cn.com.eju.deal.api.contractChange.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.model.common.PageDto;
/**
 * 
* @Title: ContractChangeNewDto
* @Description: 撤销专用，合同相关信息
 */
public class ContractChangeNewDto extends PageDto{
	/**
	 * 合同id
	 */
	private Integer contractId;
	/**
	 * 合同业绩归属城市
	 */
	private String acCityNo;
	/**
	 * 合同编号
	 */
	private String contractNo;
	/**
	 * 协议书编号
	 */
    private String agreementNo;
    /**
     * 社会统一编码
     */
    private String registrId;
    /**
     * 法人
     */
    private String legalPerson;
    
    /**
     * 公司信息
     */
    private Integer companyId;
    private String companyNo;
    //partyB
    private String companyName;
    //地址partyAddressDetail
    private String partyBCityNo;
    private String partyBDistrictNo;
    private String partyBAddress;
    //详细地址
    private String partyAddressDetail;
    private String userCode;
    /**
     * 门店的列表，一对多
     */
    private List<StoreChangeDto> contractChangeList=new ArrayList<>();
    private String contractTypeStr;
    private Boolean isCombine;//是否为跨区经办人
    
	public String getContractTypeStr() {
		return contractTypeStr;
	}
	public void setContractTypeStr(String contractTypeStr) {
		this.contractTypeStr = contractTypeStr;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getAgreementNo() {
		return agreementNo;
	}
	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}
	public String getRegistrId() {
		return registrId;
	}
	public void setRegistrId(String registrId) {
		this.registrId = registrId;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
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
	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	
	public String getPartyBCityNo() {
		return partyBCityNo;
	}
	public void setPartyBCityNo(String partyBCityNo) {
		this.partyBCityNo = partyBCityNo;
	}
	public String getPartyBDistrictNo() {
		return partyBDistrictNo;
	}
	public void setPartyBDistrictNo(String partyBDistrictNo) {
		this.partyBDistrictNo = partyBDistrictNo;
	}
	public String getPartyBAddress() {
		return partyBAddress;
	}
	public void setPartyBAddress(String partyBAddress) {
		this.partyBAddress = partyBAddress;
	}

	public List<StoreChangeDto> getContractChangeList() {
		return contractChangeList;
	}

	public void setContractChangeList(List<StoreChangeDto> contractChangeList) {
		this.contractChangeList = contractChangeList;
	}

	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public Integer getContractId() {
		return contractId;
	}
	public void setContractId(Integer contractId) {
		this.contractId = contractId;
	}
	public String getPartyAddressDetail() {
		return partyAddressDetail;
	}
	public void setPartyAddressDetail(String partyAddressDetail) {
		this.partyAddressDetail = partyAddressDetail;
	}
	public Boolean getIsCombine() {
		return isCombine;
	}
	public void setIsCombine(Boolean isCombine) {
		this.isCombine = isCombine;
	}
	public String getAcCityNo() {
		return acCityNo;
	}
	public void setAcCityNo(String acCityNo) {
		this.acCityNo = acCityNo;
	}
	/**
	 * 事业部编号
	 */
	private String busNo;

	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
    
	//-------终止扩展字段------//
	//终止id
	private Integer contractStopId;
	//终止编号
	private String contractStopNo;
	//终止类型
	private Integer stopType;
	private String stopTypeNm;
	
	//终止日期
	private Date stopDate;
	//终止具体原因
	private String stopReason;
	//终止方案阐述
	private String stopDescribe;
	//终止备注
	private String remarks;
	//是否一并解除
	private String isReleaseFlag;
	private String isReleaseFlagNm;
	//
	private String flowId;

	private Integer cancelFlag;

	public Integer getCancelFlag() {
		return cancelFlag;
	}

	public void setCancelFlag(Integer cancelFlag) {
		this.cancelFlag = cancelFlag;
	}

	public Integer getContractStopId() {
		return contractStopId;
	}
	public void setContractStopId(Integer contractStopId) {
		this.contractStopId = contractStopId;
	}
	public String getContractStopNo() {
		return contractStopNo;
	}
	public void setContractStopNo(String contractStopNo) {
		this.contractStopNo = contractStopNo;
	}
	public Integer getStopType() {
		return stopType;
	}
	public void setStopType(Integer stopType) {
		this.stopType = stopType;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public String getStopReason() {
		return stopReason;
	}
	public void setStopReason(String stopReason) {
		this.stopReason = stopReason;
	}
	public String getStopDescribe() {
		return stopDescribe;
	}
	public void setStopDescribe(String stopDescribe) {
		this.stopDescribe = stopDescribe;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getIsReleaseFlag() {
		return isReleaseFlag;
	}
	public void setIsReleaseFlag(String isReleaseFlag) {
		this.isReleaseFlag = isReleaseFlag;
	}
	public String getStopTypeNm() {
		return SystemParam.getDicValueByDicCode(stopType+"");
	}
	public void setStopTypeNm(String stopTypeNm) {
		this.stopTypeNm = stopTypeNm;
	}
	public String getIsReleaseFlagNm() {
		return SystemParam.getDicValueByDicCode(isReleaseFlag+"");
	}
	public void setIsReleaseFlagNm(String isReleaseFlagNm) {
		this.isReleaseFlagNm = isReleaseFlagNm;
	}
	
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	/**
	 * 审核流程
	 */
	private List<OaProcessDto> oaProcesslist;

	public List<OaProcessDto> getOaProcesslist() {
		return oaProcesslist;
	}

	public void setOaProcesslist(List<OaProcessDto> oaProcesslist) {
		this.oaProcesslist = oaProcesslist;
	}
	private String fileRecordMainIds;//页面图片合集
	private List<ContractFileDto> stopContractFileList;//终止合作协议书
	private List<ContractFileDto> receiptFileList; //保证金收据
	private List<ContractFileDto> storePhotosFileList; //门店照片
	//照片private List<ContractFileDto> photosFileList;
    private List<ContractFileDto> returnProveFileList;//装修款退还证明
    private List<ContractFileDto> cancellateFileList;//注销证明
    private List<ContractFileDto> othersFileList;//合同变更其他照片
	private List<ContractFileDto> tripartiteFileList;//三方协议

	public String getFileRecordMainIds() {
		return fileRecordMainIds;
	}
	public void setFileRecordMainIds(String fileRecordMainIds) {
		this.fileRecordMainIds = fileRecordMainIds;
	}
	public List<ContractFileDto> getStopContractFileList() {
		return stopContractFileList;
	}
	public void setStopContractFileList(List<ContractFileDto> stopContractFileList) {
		this.stopContractFileList = stopContractFileList;
	}
	public List<ContractFileDto> getReceiptFileList() {
		return receiptFileList;
	}
	public void setReceiptFileList(List<ContractFileDto> receiptFileList) {
		this.receiptFileList = receiptFileList;
	}
	public List<ContractFileDto> getStorePhotosFileList() {
		return storePhotosFileList;
	}
	public void setStorePhotosFileList(List<ContractFileDto> storePhotosFileList) {
		this.storePhotosFileList = storePhotosFileList;
	}
	public List<ContractFileDto> getReturnProveFileList() {
		return returnProveFileList;
	}
	public void setReturnProveFileList(List<ContractFileDto> returnProveFileList) {
		this.returnProveFileList = returnProveFileList;
	}
	public List<ContractFileDto> getCancellateFileList() {
		return cancellateFileList;
	}
	public void setCancellateFileList(List<ContractFileDto> cancellateFileList) {
		this.cancellateFileList = cancellateFileList;
	}
	public List<ContractFileDto> getOthersFileList() {
		return othersFileList;
	}
	public void setOthersFileList(List<ContractFileDto> othersFileList) {
		this.othersFileList = othersFileList;
	}
	//批准状态
	private Integer ctEndApproveState;
	private String approveStateName;

	public Integer getCtEndApproveState() {
		return ctEndApproveState;
	}
	public void setCtEndApproveState(Integer ctEndApproveState) {
		this.ctEndApproveState = ctEndApproveState;
	}
	public String getApproveStateName() {
		return approveStateName;
	}
	public void setApproveStateName(String approveStateName) {
		this.approveStateName = approveStateName;
	}

	public List<ContractFileDto> getTripartiteFileList() {
		return tripartiteFileList;
	}

	public void setTripartiteFileList(List<ContractFileDto> tripartiteFileList) {
		this.tripartiteFileList = tripartiteFileList;
	}
}
  