package cn.com.eju.deal.gpContract.model;

import cn.com.eju.deal.dto.contacts.ContactsDto;
import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.dto.store.StoreDto;
import cn.com.eju.deal.model.signContract.ContractNewDto;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by haidan on 2018/9/11.
 */
public class GpContractDto extends GpContract {

    //公盘合同审核状态
    private String contractStatusNm;
    //新签续签
    private String contractTypeNm;
    //公盘合同生效状态
    private String validNm;
    //提交OA状态
    private String submitOAStatusNm;
    //公司编号
    private String companyNo;

    //操作人名称
    private String userName;

    private Integer FileSourceId;

    // 营业执照文件列表
    private List<ContractFileDto> fileBusinessList;
    //法人身份证
    private List<ContractFileDto> fileIdCardList;
    //公盘系统服务协议
    private List<ContractFileDto> fileContractList;
    //授权委托书
    private List<ContractFileDto> fileProxyList;
    //直联盘勾选表
    private List<ContractFileDto> fileCheckList;
    //易居房友经纪业务共享平台规则
    private List<ContractFileDto> fileRuleList;
    //账户变更申请书
    private List<ContractFileDto> fileAccountChangeList;
    // 附件其他文件列表
    private List<ContractFileDto> fileOtherList;
    // 文件ID（数组）
    private String fileRecordMainIds;

    // 文件列表
    private List<ContractFileDto> fileRecordMain;
    // 文件列表（保存用）
    private List<ContractFileDto> oldFileRecordMain;

    // 关联门店列表
    private List<StoreDto> storeDetails;
    // 联系人列表
    private List<ContactsDto> contactsDtoList;

    // 关联门店列表（保存用）
    private String oldStoreIdArray;

    private String partyAddressDetail;

    private BigDecimal deposit;

    public static ContractNewDto convertToContractNewDto(GpContractDto dto) {
        ContractNewDto contractNewDto = new ContractNewDto();
        BeanUtils.copyProperties(dto, contractNewDto);
        contractNewDto.setContractId(dto.getId());
        contractNewDto.setCompanyName(dto.getPartyB());
        contractNewDto.setContractNo(dto.getGpContractNo());
        contractNewDto.setBusinessLicenseNo(dto.getRegisterId());
        contractNewDto.setAgreementNo(dto.getGpAgreementNo());
        contractNewDto.setExpandingPersonnelId(dto.getExPersonId());
        contractNewDto.setExpandingPersonnel(dto.getExPerson());
        contractNewDto.setProvinceNo(dto.getAccountProvinceNo());
        contractNewDto.setProvinceName(dto.getAccountProvinceNm());
        contractNewDto.setCityNo(dto.getAccountCityNo());
        contractNewDto.setCityName(dto.getAccountCityNm());
        contractNewDto.setAccountName(dto.getAccountNm());
        contractNewDto.setAccountNo(dto.getBankAccount());
        contractNewDto.setAuthRepresentative(dto.getPartyBNm());
        contractNewDto.setAgentContact(dto.getPartyBTel());
        contractNewDto.setCityNo4GpContract(dto.getCityNo());
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        contractNewDto.setDateLifeStart(format.format(dto.getDateLifeStart()));
        contractNewDto.setDateLifeEnd(format.format(dto.getDateLifeEnd()));
        contractNewDto.setAddressDetail(dto.getPartyAddressDetail());
        contractNewDto.setCompanyNo(Integer.valueOf(dto.getCompanyNo()));
        contractNewDto.setDepositFee(dto.getDepositFee() == null ? 0 : dto.getDepositFee().doubleValue());
        return contractNewDto;
    }

    public Integer getFileSourceId() {
        return FileSourceId;
    }

    public void setFileSourceId(Integer fileSourceId) {
        FileSourceId = fileSourceId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public List<ContractFileDto> getFileCheckList() {
        return fileCheckList;
    }

    public void setFileCheckList(List<ContractFileDto> fileCheckList) {
        this.fileCheckList = fileCheckList;
    }

    public List<ContractFileDto> getFileRuleList() {
        return fileRuleList;
    }

    public void setFileRuleList(List<ContractFileDto> fileRuleList) {
        this.fileRuleList = fileRuleList;
    }

    public List<ContactsDto> getContactsDtoList() {
        return contactsDtoList;
    }

    public void setContactsDtoList(List<ContactsDto> contactsDtoList) {
        this.contactsDtoList = contactsDtoList;
    }

    public List<ContractFileDto> getFileRecordMain() {
        return fileRecordMain;
    }

    public void setFileRecordMain(List<ContractFileDto> fileRecordMain) {
        this.fileRecordMain = fileRecordMain;
    }

    public List<ContractFileDto> getOldFileRecordMain() {
        return oldFileRecordMain;
    }

    public void setOldFileRecordMain(List<ContractFileDto> oldFileRecordMain) {
        this.oldFileRecordMain = oldFileRecordMain;
    }

    public String getPartyAddressDetail() {
        return partyAddressDetail;
    }

    public void setPartyAddressDetail(String partyAddressDetail) {
        this.partyAddressDetail = partyAddressDetail;
    }

    public String getContractStatusNm() {
        return contractStatusNm;
    }

    public void setContractStatusNm(String contractStatusNm) {
        this.contractStatusNm = contractStatusNm;
    }

    public String getValidNm() {
        return validNm;
    }

    public void setValidNm(String validNm) {
        this.validNm = validNm;
    }

    public String getSubmitOAStatusNm() {
        return submitOAStatusNm;
    }

    public void setSubmitOAStatusNm(String submitOAStatusNm) {
        this.submitOAStatusNm = submitOAStatusNm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


    public List<ContractFileDto> getFileOtherList() {
        return fileOtherList;
    }

    public void setFileOtherList(List<ContractFileDto> fileOtherList) {
        this.fileOtherList = fileOtherList;
    }

    public String getFileRecordMainIds() {
        return fileRecordMainIds;
    }

    public void setFileRecordMainIds(String fileRecordMainIds) {
        this.fileRecordMainIds = fileRecordMainIds;
    }

    public List<StoreDto> getStoreDetails() {
        return storeDetails;
    }

    public void setStoreDetails(List<StoreDto> storeDetails) {
        this.storeDetails = storeDetails;
    }

    public String getOldStoreIdArray() {
        return oldStoreIdArray;
    }

    public void setOldStoreIdArray(String oldStoreIdArray) {
        this.oldStoreIdArray = oldStoreIdArray;
    }

    public String getContractTypeNm() {
        return contractTypeNm;
    }

    public void setContractTypeNm(String contractTypeNm) {
        this.contractTypeNm = contractTypeNm;
    }

    public List<ContractFileDto> getFileProxyList() {
        return fileProxyList;
    }

    public void setFileProxyList(List<ContractFileDto> fileProxyList) {
        this.fileProxyList = fileProxyList;
    }

    public List<ContractFileDto> getFileAccountChangeList() {
        return fileAccountChangeList;
    }

    public void setFileAccountChangeList(List<ContractFileDto> fileAccountChangeList) {
        this.fileAccountChangeList = fileAccountChangeList;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    /**
     * 初始会员存在标识
     */
    private Integer csMemberCtFlag;

    public Integer getCsMemberCtFlag() {
        return csMemberCtFlag;
    }

    public void setCsMemberCtFlag(Integer csMemberCtFlag) {
        this.csMemberCtFlag = csMemberCtFlag;
    }

}
