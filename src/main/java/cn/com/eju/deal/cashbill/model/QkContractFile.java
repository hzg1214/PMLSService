package cn.com.eju.deal.cashbill.model;

import java.util.Date;
/**
 * desc:联动请款单-框架合同附件-entity
 * @date   :2020年1月2日
 */
public class QkContractFile {
	
    private Integer id;
    
    private Integer comId;//对应Cash_Bill_Company的id

    private String oaFileId;

    private String fileName;

    private String oaCode;//对应frameOaNo

    private String flowId;

    private Date dateCreate;

    private Boolean delFlag;

	public Integer getId() {
		return id;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOaFileId() {
		return oaFileId;
	}

	public void setOaFileId(String oaFileId) {
		this.oaFileId = oaFileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOaCode() {
		return oaCode;
	}

	public void setOaCode(String oaCode) {
		this.oaCode = oaCode;
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

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}
    
}