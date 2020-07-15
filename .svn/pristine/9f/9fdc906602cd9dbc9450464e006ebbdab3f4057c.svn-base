package cn.com.eju.deal.gpContractChange.model;

import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.model.signContract.ContractAuditRecordDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公盘合同终止Dto
 */
public class GpContractChangeDto extends GpContractChange{
		//操作人
		private String userName;
		//终止类型名称
		private String stopTypeNm;
		//审核状态名称
		private String approveStatusNm;
		//提交OA状态名称
		private String submitOAStatusNm;
		// 终止合作协议书/单方解除函
		private List<ContractFileDto> stopContractFileList;
	 	//保证金收据
	 	private List<ContractFileDto> receiptFileList;
	 	// 附件其他文件列表
	 	private List<ContractFileDto> othersFileList;
	 	// 文件ID（数组）
	 	private String fileRecordMainIds;
	 	private List<StoreDto> storeList;
	 	private List<GpContractChangeStore> gpContractChangeStoreList;
	 	
	 	//我方全称
	 	private String ourFullName;
	 	private Date dateLifeStart;
	 	private Date dateLifeEnd;
	 	private String registerId;

	//合同审批历史列表
	private List<ContractAuditRecordDto> contractAuditRecordDtoList = new ArrayList<ContractAuditRecordDto>();
	 	
	 	public String getRegisterId() {
			return registerId;
		}

		public void setRegisterId(String registerId) {
			this.registerId = registerId;
		}

		public Date getDateLifeStart() {
			return dateLifeStart;
		}

		public void setDateLifeStart(Date dateLifeStart) {
			this.dateLifeStart = dateLifeStart;
		}

		public Date getDateLifeEnd() {
			return dateLifeEnd;
		}

		public void setDateLifeEnd(Date dateLifeEnd) {
			this.dateLifeEnd = dateLifeEnd;
		}

		public String getOurFullName() {
			return ourFullName;
		}

		public void setOurFullName(String ourFullName) {
			this.ourFullName = ourFullName;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public List<GpContractChangeStore> getGpContractChangeStoreList() {
			return gpContractChangeStoreList;
		}

		public void setGpContractChangeStoreList(List<GpContractChangeStore> gpContractChangeStoreList) {
			this.gpContractChangeStoreList = gpContractChangeStoreList;
		}

		public List<StoreDto> getStoreList() {
			return storeList;
		}

		public void setStoreList(List<StoreDto> storeList) {
			this.storeList = storeList;
		}

		//编辑保存用原文件
	 	private List<ContractFileDto> oldFileRecordMain;

		public String getApproveStatusNm() {
			return approveStatusNm;
		}

		public void setApproveStatusNm(String approveStatusNm) {
			this.approveStatusNm = approveStatusNm;
		}

		public String getSubmitOAStatusNm() {
			return submitOAStatusNm;
		}

		public void setSubmitOAStatusNm(String submitOAStatusNm) {
			this.submitOAStatusNm = submitOAStatusNm;
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

		public List<ContractFileDto> getOthersFileList() {
			return othersFileList;
		}

		public void setOthersFileList(List<ContractFileDto> othersFileList) {
			this.othersFileList = othersFileList;
		}

		public String getFileRecordMainIds() {
			return fileRecordMainIds;
		}

		public void setFileRecordMainIds(String fileRecordMainIds) {
			this.fileRecordMainIds = fileRecordMainIds;
		}

		public List<ContractFileDto> getOldFileRecordMain() {
			return oldFileRecordMain;
		}

		public void setOldFileRecordMain(List<ContractFileDto> oldFileRecordMain) {
			this.oldFileRecordMain = oldFileRecordMain;
		}

		public String getStopTypeNm() {
			return stopTypeNm;
		}

		public void setStopTypeNm(String stopTypeNm) {
			this.stopTypeNm = stopTypeNm;
		}

	public List<ContractAuditRecordDto> getContractAuditRecordDtoList() {
		return contractAuditRecordDtoList;
	}

	public void setContractAuditRecordDtoList(List<ContractAuditRecordDto> contractAuditRecordDtoList) {
		this.contractAuditRecordDtoList = contractAuditRecordDtoList;
	}
}
  