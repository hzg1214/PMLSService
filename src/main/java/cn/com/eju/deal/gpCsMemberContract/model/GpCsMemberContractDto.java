package cn.com.eju.deal.gpCsMemberContract.model;

import java.util.Date;
import java.util.List;

import cn.com.eju.deal.dto.contract.ContractFileDto;

/**
* @Title: GpCsMemberContractDto
* @Description: 公盘合同初始会员
 */
public class GpCsMemberContractDto extends GpCsMemberContract {
		//操作人
		private String userName;
		
		//审核状态名称
		private String approveStatusNm;
		//提交OA状态名称
		private String submitOAStatusNm;
		
		private Date dateLifeStart;
	 	private Date dateLifeEnd;
	 	// 营业执照文件列表
	    private List<ContractFileDto> fileBusinessList;
	    //法人身份证
	    private List<ContractFileDto> fileIdCardList;
	    //公盘系统服务协议
	    private List<ContractFileDto> fileContractList;
	 	//初始会员协议
	 	private List<ContractFileDto> csMemberFileList;
	 	//初始会员费付款凭证
	 	private List<ContractFileDto> csMemberPayFileList;
	 	// 附件其他文件列表
	 	private List<ContractFileDto> othersFileList;
	 	
	 	// 文件ID（数组）
	 	private String fileRecordMainIds;
	 	//编辑保存用原文件
	 	private List<ContractFileDto> oldFileRecordMain;
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
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
		public List<ContractFileDto> getCsMemberFileList() {
			return csMemberFileList;
		}
		public void setCsMemberFileList(List<ContractFileDto> csMemberFileList) {
			this.csMemberFileList = csMemberFileList;
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
		public List<ContractFileDto> getFileBusinessList() {
			return fileBusinessList;
		}
		public void setFileBusinessList(List<ContractFileDto> fileBusinessList) {
			this.fileBusinessList = fileBusinessList;
		}
		public List<ContractFileDto> getFileIdCardList() {
			return fileIdCardList;
		}
		public void setFileIdCardList(List<ContractFileDto> fileIdCardList) {
			this.fileIdCardList = fileIdCardList;
		}
		public List<ContractFileDto> getFileContractList() {
			return fileContractList;
		}
		public void setFileContractList(List<ContractFileDto> fileContractList) {
			this.fileContractList = fileContractList;
		}
		public List<ContractFileDto> getCsMemberPayFileList() {
			return csMemberPayFileList;
		}
		public void setCsMemberPayFileList(List<ContractFileDto> csMemberPayFileList) {
			this.csMemberPayFileList = csMemberPayFileList;
		}
	 	
}
  