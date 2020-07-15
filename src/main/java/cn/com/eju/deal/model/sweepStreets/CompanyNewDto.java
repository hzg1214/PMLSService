package cn.com.eju.deal.model.sweepStreets;

import cn.com.eju.deal.dto.contract.ContractFileDto;
import cn.com.eju.deal.model.common.PageDto;

import java.util.List;

/**
 * Created by xu on 2017/4/21.
 */
public class CompanyNewDto extends PageDto {
    private Integer companyId;//公司id
    private String companyNo;//公司编号
    private String companyName;//公司名称
    private String cityNo;//城市编号
    private String cityName;//城市名称
    private String districtNo;//区域编号
    private String districtName;//区域名称
    private String address;//公司注册地址明细
    private String addressDetail;//公司注册地址明细
    private String companyAddress;//公司现在地址
    private String businessLicenseNature;//营业执照性质
    private String businessLicenseNatureName;//营业执照性质名称
    private String legalPerson;//法定代表人
    private String contactNumber;//联系电话
    private String businessLicenseNo;//营业执照号
    private Integer userCreate;//录入人
    private String dateCreate;//创建时间
    private Integer userUpdate;//最近修改人
    private String dateUpdate;//最近修改时间
    private String inputSource;//数据来源 CRMWeb,CRWWechat,WX
    private String selectOrgIdStr;//岗位组织架构
    private String companyStatus;//公司状态;
    private String storeIdStr;//关联门店id字符串
    private Integer isUpdate;//是否可修改（0不能修改，1可修改）
    private String userCode;//员工工号
    private String contractType;//合同类型
    private Integer centerId;//中心id
    private String acCityNo;//业绩所属城市编号
    private String acCityName;
    private String businessStatus;//门店营业状态
    private String bizType;
    private String fileRecordMainIds;
    private String delFileRecordMainIds;
    private Integer companyType;
    private String companyTypeName;
    private List<ContractFileDto> businessLicenceFileList;//营业执照

    private Integer establishYear;    //成立年限
    private String dockingPeo;        //对接人
    private String dockingPeoTel;   //对接人电话 
    private Integer storeNumber;    //门店数量
    private Integer comStaffNum;    //公司员工数量
    private Integer channelType;    //渠道分类  typeId=236
    private String channelTypeName;
    private String undertakeType;    //可承接项目类型  typeId=237
    private String undertakeTypeName;
    private String resourcesRange;    //资源覆盖范围
    private String specificResources;//特有资源  
    private Integer lnkScale;        //一二手联动规模	typeId=238
    private String lnkScaleName;
    private String closeDeveloper;    // 合作密切开发商

    private String searchText;
    private String storeId;
    private int depositFee; // 翻牌到账保证金

    private String releaseCity;//发布城市
    private String checkCityNo;//待判断的城市

    public int getDepositFee() {
        return depositFee;
    }

    public void setDepositFee(int depositFee) {
        this.depositFee = depositFee;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    private List<StoreNewDto> storeList;//门店集合

    public String getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(String businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    public String getAcCityNo() {
        return acCityNo;
    }

    public void setAcCityNo(String acCityNo) {
        this.acCityNo = acCityNo;
    }

    public String getContractType() {
        return contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public String getStoreIdStr() {
        return storeIdStr;
    }

    public void setStoreIdStr(String storeIdStr) {
        this.storeIdStr = storeIdStr;
    }

    public List<StoreNewDto> getStoreList() {
        return storeList;
    }

    public void setStoreList(List<StoreNewDto> storeList) {
        this.storeList = storeList;
    }

    public String getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(String companyStatus) {
        this.companyStatus = companyStatus;
    }

    public String getSelectOrgIdStr() {
        return selectOrgIdStr;
    }

    public void setSelectOrgIdStr(String selectOrgIdStr) {
        this.selectOrgIdStr = selectOrgIdStr;
    }

    public Integer getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(Integer userCreate) {
        this.userCreate = userCreate;
    }

    public Integer getUserUpdate() {
        return userUpdate;
    }

    public void setUserUpdate(Integer userUpdate) {
        this.userUpdate = userUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getInputSource() {
        return inputSource;
    }

    public void setInputSource(String inputSource) {
        this.inputSource = inputSource;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
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

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictNo() {
        return districtNo;
    }

    public void setDistrictNo(String districtNo) {
        this.districtNo = districtNo;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getBusinessLicenseNature() {
        return businessLicenseNature;
    }

    public void setBusinessLicenseNature(String businessLicenseNature) {
        this.businessLicenseNature = businessLicenseNature;
    }

    public String getBusinessLicenseNatureName() {
        return businessLicenseNatureName;
    }

    public void setBusinessLicenseNatureName(String businessLicenseNatureName) {
        this.businessLicenseNatureName = businessLicenseNatureName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public void setBusinessLicenseNo(String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public String getFileRecordMainIds() {
        return fileRecordMainIds;
    }

    public void setFileRecordMainIds(String fileRecordMainIds) {
        this.fileRecordMainIds = fileRecordMainIds;
    }

    public String getDelFileRecordMainIds() {
        return delFileRecordMainIds;
    }

    public void setDelFileRecordMainIds(String delFileRecordMainIds) {
        this.delFileRecordMainIds = delFileRecordMainIds;
    }

    public List<ContractFileDto> getBusinessLicenceFileList() {
        return businessLicenceFileList;
    }

    public void setBusinessLicenceFileList(List<ContractFileDto> businessLicenceFileList) {
        this.businessLicenceFileList = businessLicenceFileList;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public String getCompanyTypeName() {
        return companyTypeName;
    }

    public void setCompanyTypeName(String companyTypeName) {
        this.companyTypeName = companyTypeName;
    }

    public Integer getEstablishYear() {
        return establishYear;
    }

    public void setEstablishYear(Integer establishYear) {
        this.establishYear = establishYear;
    }

    public String getDockingPeo() {
        return dockingPeo;
    }

    public void setDockingPeo(String dockingPeo) {
        this.dockingPeo = dockingPeo;
    }

    public String getDockingPeoTel() {
        return dockingPeoTel;
    }

    public void setDockingPeoTel(String dockingPeoTel) {
        this.dockingPeoTel = dockingPeoTel;
    }

    public Integer getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(Integer storeNumber) {
        this.storeNumber = storeNumber;
    }

    public Integer getComStaffNum() {
        return comStaffNum;
    }

    public void setComStaffNum(Integer comStaffNum) {
        this.comStaffNum = comStaffNum;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getUndertakeType() {
        return undertakeType;
    }

    public void setUndertakeType(String undertakeType) {
        this.undertakeType = undertakeType;
    }

    public String getResourcesRange() {
        return resourcesRange;
    }

    public void setResourcesRange(String resourcesRange) {
        this.resourcesRange = resourcesRange;
    }

    public String getSpecificResources() {
        return specificResources;
    }

    public void setSpecificResources(String specificResources) {
        this.specificResources = specificResources;
    }

    public Integer getLnkScale() {
        return lnkScale;
    }

    public void setLnkScale(Integer lnkScale) {
        this.lnkScale = lnkScale;
    }

    public String getCloseDeveloper() {
        return closeDeveloper;
    }

    public void setCloseDeveloper(String closeDeveloper) {
        this.closeDeveloper = closeDeveloper;
    }

    public String getChannelTypeName() {
        return channelTypeName;
    }

    public void setChannelTypeName(String channelTypeName) {
        this.channelTypeName = channelTypeName;
    }

    public String getUndertakeTypeName() {
        return undertakeTypeName;
    }

    public void setUndertakeTypeName(String undertakeTypeName) {
        this.undertakeTypeName = undertakeTypeName;
    }

    public String getLnkScaleName() {
        return lnkScaleName;
    }

    public void setLnkScaleName(String lnkScaleName) {
        this.lnkScaleName = lnkScaleName;
    }

    public String getReleaseCity() {
        return releaseCity;
    }

    public void setReleaseCity(String releaseCity) {
        this.releaseCity = releaseCity;
    }

    public String getCheckCityNo() {
        return checkCityNo;
    }

    public void setCheckCityNo(String checkCityNo) {
        this.checkCityNo = checkCityNo;
    }

    public String getAcCityName() {
        return acCityName;
    }

    public void setAcCityName(String acCityName) {
        this.acCityName = acCityName;
    }
}
