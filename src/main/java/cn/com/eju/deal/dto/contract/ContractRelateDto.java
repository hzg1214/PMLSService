package cn.com.eju.deal.dto.contract;

import java.io.Serializable;
import java.util.List;

import cn.com.eju.deal.dto.store.StoreRelateDto;

/**
 * 接口给OP返回信息 合同信息封装
 * 
 * @author wenhui.zhang date: 2017年3月24日 上午10:16:41
 */
public class ContractRelateDto implements Serializable {

	/**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	private String contractNo;
	private String contractType;
	private String contractTypeValue;
	private String partyB;
	private String partyBAddress;
	private String lealPerson;
	private String dateLifeStart;
	private String dateLifeEnd;
	private String dateCreate;
	private String agreementNo;
	private String contractStatus;
	private String contractStatusValue;
	private String registrId;
	private String performDate;
	
	private List<StoreRelateDto> storeRelateList;

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getContractTypeValue() {
		return contractTypeValue;
	}

	public void setContractTypeValue(String contractTypeValue) {
		this.contractTypeValue = contractTypeValue;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getPartyBAddress() {
		return partyBAddress;
	}

	public void setPartyBAddress(String partyBAddress) {
		this.partyBAddress = partyBAddress;
	}

	public String getLealPerson() {
		return lealPerson;
	}

	public void setLealPerson(String lealPerson) {
		this.lealPerson = lealPerson;
	}

	public String getDateLifeStart() {
		return dateLifeStart;
	}

	public void setDateLifeStart(String dateLifeStart) {
		this.dateLifeStart = dateLifeStart;
	}

	public String getDateLifeEnd() {
		return dateLifeEnd;
	}

	public void setDateLifeEnd(String dateLifeEnd) {
		this.dateLifeEnd = dateLifeEnd;
	}

	public String getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(String dateCreate) {
		this.dateCreate = dateCreate;
	}

	public String getAgreementNo() {
		return agreementNo;
	}

	public void setAgreementNo(String agreementNo) {
		this.agreementNo = agreementNo;
	}

	public String getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(String contractStatus) {
		this.contractStatus = contractStatus;
	}

	public String getContractStatusValue() {
		return contractStatusValue;
	}

	public void setContractStatusValue(String contractStatusValue) {
		this.contractStatusValue = contractStatusValue;
	}

	public String getRegistrId() {
		return registrId;
	}

	public void setRegistrId(String registrId) {
		this.registrId = registrId;
	}

	public String getPerformDate() {
		return performDate;
	}

	public void setPerformDate(String performDate) {
		this.performDate = performDate;
	}

	public List<StoreRelateDto> getStoreRelateList() {
		return storeRelateList;
	}

	public void setStoreRelateList(List<StoreRelateDto> storeRelateList) {
		this.storeRelateList = storeRelateList;
	}
	

}
