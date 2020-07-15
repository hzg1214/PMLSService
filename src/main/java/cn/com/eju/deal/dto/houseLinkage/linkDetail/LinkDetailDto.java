package cn.com.eju.deal.dto.houseLinkage.linkDetail;

import java.io.Serializable;

/**
 * Created by tanlang on 2017-05-11.
 */
public class LinkDetailDto implements Serializable {

    private static final long serialVersionUID = 477597371720865651L;

    private String rowNum;

    //业绩信息
    private String yearly;
    private String accountProject;
    private String accountProjectNo;
    private String regionCode;
    private String regionName;
    private String areaCityCode;
    private String areaCityName;
    private String cityId;
    private String cityNo;
    private String cityName;
    private String cityGroupId;
    private String cityGroupName;
    private String areaGroupId;
    private String areaGroupName;
    private String centerGroupId;
    private String centerGroupName;
    private String groupId;
    private String groupName;
    private String projectCityId;
    private String projectCityName;
    private String projectDepartmentId;
    private String projectDepartmentName;
    private String srCityName;

    //案件信息
    private String customerFrom;
    private String customerFromName;
    //经纪公司
    private String storeNo;
    private String storeName;
    private String addressDetail;
    private String storeSize;
    private String contractTypeName;
    private String saleName;
    private String salePhone;
    private String companyName;
    //客户信息
    private String customerNm;
    private String customerTel;
    private String customerNum;
    //报备信息
    private String estateId;
    private String estateNm;
    private String projectNo;
    private String prepaid;
    private String prepaidName;
    private String reportId;
    private String reportDate;
    private String seeDate;
    private String pledgedDate;
    private String buildingNo;
    private String suitNum;
    //大定/成销
    private String roughArea;
    private String roughPrice;
    private String roughAmount;
    private String roughInputDate;
    private String roughAuditStatus;
    private String roughDate;
    private String roughAuditTime;
    private String dealArea;
    private String dealPrice;
    private String dealAmount;
    private String dealDate;

    /********* 应计 begin **********/
    //累积
    private String totalEPTIncome;
    private String totalEATIncome;
    private String totalEPTCommission;
    private String totalEATCommission;
    private String totalEPTProfit;
    private String totalEATProfit;
    //之前
    private String beforeEPTIncome;
    private String beforeEATIncome;
    private String beforeEPTCommission;
    private String beforeEATCommission;
    private String beforeEPTProfit;
    private String beforeEATProfit;
    //当年
    private String thisEPTIncome;
    private String thisEATIncome;
    private String thisEPTCommission;
    private String thisEATCommission;
    private String thisEPTProfit;
    private String thisEATProfit;

    //1月收入
    private String janEPTIncome;
    private String janEATIncome;
    private String janEPTCommission;
    private String janEATCommission;
    private String janEPTProfit;
    private String janEATProfit;
    //2月收入
    private String febEPTIncome;
    private String febEATIncome;
    private String febEPTCommission;
    private String febEATCommission;
    private String febEPTProfit;
    private String febEATProfit;
    //3月收入
    private String marEPTIncome;
    private String marEATIncome;
    private String marEPTCommission;
    private String marEATCommission;
    private String marEPTProfit;
    private String marEATProfit;
    //4月收入
    private String aprEPTIncome;
    private String aprEATIncome;
    private String aprEPTCommission;
    private String aprEATCommission;
    private String aprEPTProfit;
    private String aprEATProfit;
    //5月收入
    private String mayEPTIncome;
    private String mayEATIncome;
    private String mayEPTCommission;
    private String mayEATCommission;
    private String mayEPTProfit;
    private String mayEATProfit;
    //6月收入
    private String junEPTIncome;
    private String junEATIncome;
    private String junEPTCommission;
    private String junEATCommission;
    private String junEPTProfit;
    private String junEATProfit;
    //7月收入
    private String julEPTIncome;
    private String julEATIncome;
    private String julEPTCommission;
    private String julEATCommission;
    private String julEPTProfit;
    private String julEATProfit;
    //8月收入
    private String augEPTIncome;
    private String augEATIncome;
    private String augEPTCommission;
    private String augEATCommission;
    private String augEPTProfit;
    private String augEATProfit;
    //9月收入
    private String sepEPTIncome;
    private String sepEATIncome;
    private String sepEPTCommission;
    private String sepEATCommission;
    private String sepEPTProfit;
    private String sepEATProfit;
    //10月收入
    private String octEPTIncome;
    private String octEATIncome;
    private String octEPTCommission;
    private String octEATCommission;
    private String octEPTProfit;
    private String octEATProfit;
    //11月收入
    private String novEPTIncome;
    private String novEATIncome;
    private String novEPTCommission;
    private String novEATCommission;
    private String novEPTProfit;
    private String novEATProfit;
    //12月收入
    private String decEPTIncome;
    private String decEATIncome;
    private String decEPTCommission;
    private String decEATCommission;
    private String decEPTProfit;
    private String decEATProfit;

    /********* 应计 end **********/

    /********* 实际 begin **********/
    //累积
    private String totalAPTIncome;
    private String totalAATIncome;
    private String totalAPTCommission;
    private String totalAATCommission;
    private String totalAPTProfit;
    private String totalAATProfit;
    //之前
    private String beforeAPTIncome;
    private String beforeAATIncome;
    private String beforeAPTCommission;
    private String beforeAATCommission;
    private String beforeAPTProfit;
    private String beforeAATProfit;
    //当年
    private String thisAPTIncome;
    private String thisAATIncome;
    private String thisAPTCommission;
    private String thisAATCommission;
    private String thisAPTProfit;
    private String thisAATProfit;

    //1月收入
    private String janAPTIncome;
    private String janAATIncome;
    private String janAPTCommission;
    private String janAATCommission;
    private String janAPTProfit;
    private String janAATProfit;
    //2月收入
    private String febAPTIncome;
    private String febAATIncome;
    private String febAPTCommission;
    private String febAATCommission;
    private String febAPTProfit;
    private String febAATProfit;
    //3月收入
    private String marAPTIncome;
    private String marAATIncome;
    private String marAPTCommission;
    private String marAATCommission;
    private String marAPTProfit;
    private String marAATProfit;
    //4月收入
    private String aprAPTIncome;
    private String aprAATIncome;
    private String aprAPTCommission;
    private String aprAATCommission;
    private String aprAPTProfit;
    private String aprAATProfit;
    //5月收入
    private String mayAPTIncome;
    private String mayAATIncome;
    private String mayAPTCommission;
    private String mayAATCommission;
    private String mayAPTProfit;
    private String mayAATProfit;
    //6月收入
    private String junAPTIncome;
    private String junAATIncome;
    private String junAPTCommission;
    private String junAATCommission;
    private String junAPTProfit;
    private String junAATProfit;
    //7月收入
    private String julAPTIncome;
    private String julAATIncome;
    private String julAPTCommission;
    private String julAATCommission;
    private String julAPTProfit;
    private String julAATProfit;
    //8月收入
    private String augAPTIncome;
    private String augAATIncome;
    private String augAPTCommission;
    private String augAATCommission;
    private String augAPTProfit;
    private String augAATProfit;
    //9月收入
    private String sepAPTIncome;
    private String sepAATIncome;
    private String sepAPTCommission;
    private String sepAATCommission;
    private String sepAPTProfit;
    private String sepAATProfit;
    //10月收入
    private String octAPTIncome;
    private String octAATIncome;
    private String octAPTCommission;
    private String octAATCommission;
    private String octAPTProfit;
    private String octAATProfit;
    //11月收入
    private String novAPTIncome;
    private String novAATIncome;
    private String novAPTCommission;
    private String novAATCommission;
    private String novAPTProfit;
    private String novAATProfit;
    //12月收入
    private String decAPTIncome;
    private String decAATIncome;
    private String decAPTCommission;
    private String decAATCommission;
    private String decAPTProfit;
    private String decAATProfit;

    /********* 实际 end **********/

    /********* 垫佣 begin **********/
    //垫佣应计税前EPT
    private String janEPTPrepaid;
    private String febEPTPrepaid;
    private String marEPTPrepaid;
    private String aprEPTPrepaid;
    private String mayEPTPrepaid;
    private String junEPTPrepaid;
    private String julEPTPrepaid;
    private String augEPTPrepaid;
    private String sepEPTPrepaid;
    private String octEPTPrepaid;
    private String novEPTPrepaid;
    private String decEPTPrepaid;
    private String beforeEPTPrepaid;
    private String thisEPTPrepaid;
    private String totalEPTPrepaid;

    //垫佣应计税后EAT
    private String janEATPrepaid;
    private String febEATPrepaid;
    private String marEATPrepaid;
    private String aprEATPrepaid;
    private String mayEATPrepaid;
    private String junEATPrepaid;
    private String julEATPrepaid;
    private String augEATPrepaid;
    private String sepEATPrepaid;
    private String octEATPrepaid;
    private String novEATPrepaid;
    private String decEATPrepaid;
    private String beforeEATPrepaid;
    private String thisEATPrepaid;
    private String totalEATPrepaid;

    //垫佣实计税前APT
    private String janAPTPrepaid;
    private String febAPTPrepaid;
    private String marAPTPrepaid;
    private String aprAPTPrepaid;
    private String mayAPTPrepaid;
    private String junAPTPrepaid;
    private String julAPTPrepaid;
    private String augAPTPrepaid;
    private String sepAPTPrepaid;
    private String octAPTPrepaid;
    private String novAPTPrepaid;
    private String decAPTPrepaid;
    private String beforeAPTPrepaid;
    private String thisAPTPrepaid;
    private String totalAPTPrepaid;

    //垫佣实计税后AAT
    private String janAATPrepaid;
    private String febAATPrepaid;
    private String marAATPrepaid;
    private String aprAATPrepaid;
    private String mayAATPrepaid;
    private String junAATPrepaid;
    private String julAATPrepaid;
    private String augAATPrepaid;
    private String sepAATPrepaid;
    private String octAATPrepaid;
    private String novAATPrepaid;
    private String decAATPrepaid;
    private String beforeAATPrepaid;
    private String thisAATPrepaid;
    private String totalAATPrepaid;
    /********* 垫佣 end **********/

    /********* 内佣 begin **********/
    private String totalNPTCommission;
    private String totalNATCommission;
    /********* 内佣 end **********/

    //人员信息
    private String expenderCode;
    private String expenderName;
    private String groupLeaderCode;
    private String groupLeaderName;
    private String centerLeaderCode;
    private String centerLeaderName;
    private String areaLeaderCode;
    private String areaLeaderName;

    private String remark;

    
    /********* 应收收入begin **********/
    private String  totalRBPTIncome; 
    private String  totalRBATIncome;
	private String  beforeRBPTIncome; 
	private String  beforeRBATIncome;
	private String  thisRBPTIncome; 
	private String  thisRBATIncome;
	 
	private String  janRBPTIncome;
	private String  janRBATIncome;
	private String  febRBPTIncome; 
	private String  febRBBATIncome;
	private String  marRBPTIncome; 
	private String  marRBATIncome;
	private String  aprRBPTIncome; 
	private String  aprRBATIncome;
	private String  mayRBPTIncome;
	private String  mayRBATIncome;
	private String  junRBPTIncome; 
	private String  junRBATIncome;
	
	private String  julRBPTIncome; 
	private String  julRBATIncome;
	private String  augRBPTIncome;
	private String  augRBATIncome;
	private String  sepRBPTIncome;
	private String  sepRBATIncome;
	private String  octRBPTIncome; 
	private String  octRBATIncome;
	private String  novRBPTIncome; 
	private String  novRBATIncome;
	private String  decRBPTIncome; 
	private String  decRBATIncome;



    /********* 应收收入end **********/

    //返佣
    private String fyObject1;
    private String befYJFY1;
    private String aftYJFY1;
    private String fyObject2;
    private String befYJFY2;
    private String aftYJFY2;
    private String fyObject3;
    private String befYJFY3;
    private String aftYJFY3;

    private String preBack;

    public String getFyObject1() {
        return fyObject1;
    }

    public void setFyObject1(String fyObject1) {
        this.fyObject1 = fyObject1;
    }

    public String getBefYJFY1() {
        return befYJFY1;
    }

    public void setBefYJFY1(String befYJFY1) {
        this.befYJFY1 = befYJFY1;
    }

    public String getAftYJFY1() {
        return aftYJFY1;
    }

    public void setAftYJFY1(String aftYJFY1) {
        this.aftYJFY1 = aftYJFY1;
    }

    public String getFyObject2() {
        return fyObject2;
    }

    public void setFyObject2(String fyObject2) {
        this.fyObject2 = fyObject2;
    }

    public String getBefYJFY2() {
        return befYJFY2;
    }

    public void setBefYJFY2(String befYJFY2) {
        this.befYJFY2 = befYJFY2;
    }

    public String getAftYJFY2() {
        return aftYJFY2;
    }

    public void setAftYJFY2(String aftYJFY2) {
        this.aftYJFY2 = aftYJFY2;
    }

    public String getFyObject3() {
        return fyObject3;
    }

    public void setFyObject3(String fyObject3) {
        this.fyObject3 = fyObject3;
    }

    public String getBefYJFY3() {
        return befYJFY3;
    }

    public void setBefYJFY3(String befYJFY3) {
        this.befYJFY3 = befYJFY3;
    }

    public String getAftYJFY3() {
        return aftYJFY3;
    }

    public void setAftYJFY3(String aftYJFY3) {
        this.aftYJFY3 = aftYJFY3;
    }

    public String getSrCityName() {
        return srCityName;
    }

    public void setSrCityName(String srCityName) {
        this.srCityName = srCityName;
    }
	
    public String getTotalRBPTIncome() {
		return totalRBPTIncome;
	}

	public void setTotalRBPTIncome(String totalRBPTIncome) {
		this.totalRBPTIncome = totalRBPTIncome;
	}

	public String getTotalRBATIncome() {
		return totalRBATIncome;
	}

	public void setTotalRBATIncome(String totalRBATIncome) {
		this.totalRBATIncome = totalRBATIncome;
	}

	public String getBeforeRBPTIncome() {
		return beforeRBPTIncome;
	}

	public void setBeforeRBPTIncome(String beforeRBPTIncome) {
		this.beforeRBPTIncome = beforeRBPTIncome;
	}

	public String getBeforeRBATIncome() {
		return beforeRBATIncome;
	}

	public void setBeforeRBATIncome(String beforeRBATIncome) {
		this.beforeRBATIncome = beforeRBATIncome;
	}

	public String getThisRBPTIncome() {
		return thisRBPTIncome;
	}

	public void setThisRBPTIncome(String thisRBPTIncome) {
		this.thisRBPTIncome = thisRBPTIncome;
	}

	public String getThisRBATIncome() {
		return thisRBATIncome;
	}

	public void setThisRBATIncome(String thisRBATIncome) {
		this.thisRBATIncome = thisRBATIncome;
	}

	public String getJanRBPTIncome() {
		return janRBPTIncome;
	}

	public void setJanRBPTIncome(String janRBPTIncome) {
		this.janRBPTIncome = janRBPTIncome;
	}

	public String getJanRBATIncome() {
		return janRBATIncome;
	}

	public void setJanRBATIncome(String janRBATIncome) {
		this.janRBATIncome = janRBATIncome;
	}

	public String getFebRBPTIncome() {
		return febRBPTIncome;
	}

	public void setFebRBPTIncome(String febRBPTIncome) {
		this.febRBPTIncome = febRBPTIncome;
	}

	public String getFebRBBATIncome() {
		return febRBBATIncome;
	}

	public void setFebRBBATIncome(String febRBBATIncome) {
		this.febRBBATIncome = febRBBATIncome;
	}

	public String getMarRBPTIncome() {
		return marRBPTIncome;
	}

	public void setMarRBPTIncome(String marRBPTIncome) {
		this.marRBPTIncome = marRBPTIncome;
	}

	public String getMarRBATIncome() {
		return marRBATIncome;
	}

	public void setMarRBATIncome(String marRBATIncome) {
		this.marRBATIncome = marRBATIncome;
	}

	public String getAprRBPTIncome() {
		return aprRBPTIncome;
	}

	public void setAprRBPTIncome(String aprRBPTIncome) {
		this.aprRBPTIncome = aprRBPTIncome;
	}

	public String getAprRBATIncome() {
		return aprRBATIncome;
	}

	public void setAprRBATIncome(String aprRBATIncome) {
		this.aprRBATIncome = aprRBATIncome;
	}

	public String getMayRBPTIncome() {
		return mayRBPTIncome;
	}

	public void setMayRBPTIncome(String mayRBPTIncome) {
		this.mayRBPTIncome = mayRBPTIncome;
	}

	public String getMayRBATIncome() {
		return mayRBATIncome;
	}

	public void setMayRBATIncome(String mayRBATIncome) {
		this.mayRBATIncome = mayRBATIncome;
	}

	public String getJunRBPTIncome() {
		return junRBPTIncome;
	}

	public void setJunRBPTIncome(String junRBPTIncome) {
		this.junRBPTIncome = junRBPTIncome;
	}

	public String getJunRBATIncome() {
		return junRBATIncome;
	}

	public void setJunRBATIncome(String junRBATIncome) {
		this.junRBATIncome = junRBATIncome;
	}

	public String getJulRBPTIncome() {
		return julRBPTIncome;
	}

	public void setJulRBPTIncome(String julRBPTIncome) {
		this.julRBPTIncome = julRBPTIncome;
	}

	public String getJulRBATIncome() {
		return julRBATIncome;
	}

	public void setJulRBATIncome(String julRBATIncome) {
		this.julRBATIncome = julRBATIncome;
	}

	public String getAugRBPTIncome() {
		return augRBPTIncome;
	}

	public void setAugRBPTIncome(String augRBPTIncome) {
		this.augRBPTIncome = augRBPTIncome;
	}

	public String getAugRBATIncome() {
		return augRBATIncome;
	}

	public void setAugRBATIncome(String augRBATIncome) {
		this.augRBATIncome = augRBATIncome;
	}

	public String getSepRBPTIncome() {
		return sepRBPTIncome;
	}

	public void setSepRBPTIncome(String sepRBPTIncome) {
		this.sepRBPTIncome = sepRBPTIncome;
	}

	public String getSepRBATIncome() {
		return sepRBATIncome;
	}

	public void setSepRBATIncome(String sepRBATIncome) {
		this.sepRBATIncome = sepRBATIncome;
	}

	public String getOctRBPTIncome() {
		return octRBPTIncome;
	}

	public void setOctRBPTIncome(String octRBPTIncome) {
		this.octRBPTIncome = octRBPTIncome;
	}

	public String getOctRBATIncome() {
		return octRBATIncome;
	}

	public void setOctRBATIncome(String octRBATIncome) {
		this.octRBATIncome = octRBATIncome;
	}

	public String getNovRBPTIncome() {
		return novRBPTIncome;
	}

	public void setNovRBPTIncome(String novRBPTIncome) {
		this.novRBPTIncome = novRBPTIncome;
	}

	public String getNovRBATIncome() {
		return novRBATIncome;
	}

	public void setNovRBATIncome(String novRBATIncome) {
		this.novRBATIncome = novRBATIncome;
	}

	public String getDecRBPTIncome() {
		return decRBPTIncome;
	}

	public void setDecRBPTIncome(String decRBPTIncome) {
		this.decRBPTIncome = decRBPTIncome;
	}

	public String getDecRBATIncome() {
		return decRBATIncome;
	}

	public void setDecRBATIncome(String decRBATIncome) {
		this.decRBATIncome = decRBATIncome;
	}

	public String getRoughAuditTime() {
        return roughAuditTime;
    }

    public void setRoughAuditTime(String roughAuditTime) {
        this.roughAuditTime = roughAuditTime;
    }

    public String getRoughInputDate() {
        return roughInputDate;
    }

    public void setRoughInputDate(String roughInputDate) {
        this.roughInputDate = roughInputDate;
    }

    public String getRoughAuditStatus() {
        return roughAuditStatus;
    }

    public void setRoughAuditStatus(String roughAuditStatus) {
        this.roughAuditStatus = roughAuditStatus;
    }

    public String getRowNum() {
        return rowNum;
    }

    public void setRowNum(String rowNum) {
        this.rowNum = rowNum;
    }

    public String getYearly() {
        return yearly;
    }

    public void setYearly(String yearly) {
        this.yearly = yearly;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getAreaCityCode() {
        return areaCityCode;
    }

    public void setAreaCityCode(String areaCityCode) {
        this.areaCityCode = areaCityCode;
    }

    public String getAreaCityName() {
        return areaCityName;
    }

    public void setAreaCityName(String areaCityName) {
        this.areaCityName = areaCityName;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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

    public String getCityGroupId() {
        return cityGroupId;
    }

    public void setCityGroupId(String cityGroupId) {
        this.cityGroupId = cityGroupId;
    }

    public String getCityGroupName() {
        return cityGroupName;
    }

    public void setCityGroupName(String cityGroupName) {
        this.cityGroupName = cityGroupName;
    }

    public String getAreaGroupId() {
        return areaGroupId;
    }

    public void setAreaGroupId(String areaGroupId) {
        this.areaGroupId = areaGroupId;
    }

    public String getAreaGroupName() {
        return areaGroupName;
    }

    public void setAreaGroupName(String areaGroupName) {
        this.areaGroupName = areaGroupName;
    }

    public String getCenterGroupId() {
        return centerGroupId;
    }

    public void setCenterGroupId(String centerGroupId) {
        this.centerGroupId = centerGroupId;
    }

    public String getCenterGroupName() {
        return centerGroupName;
    }

    public void setCenterGroupName(String centerGroupName) {
        this.centerGroupName = centerGroupName;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCustomerFrom() {
        return customerFrom;
    }

    public void setCustomerFrom(String customerFrom) {
        this.customerFrom = customerFrom;
    }

    public String getCustomerFromName() {
        return customerFromName;
    }

    public void setCustomerFromName(String customerFromName) {
        this.customerFromName = customerFromName;
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

    public String getContractTypeName() {
        return contractTypeName;
    }

    public void setContractTypeName(String contractTypeName) {
        this.contractTypeName = contractTypeName;
    }

    public String getSaleName() {
        return saleName;
    }

    public void setSaleName(String saleName) {
        this.saleName = saleName;
    }

    public String getSalePhone() {
        return salePhone;
    }

    public void setSalePhone(String salePhone) {
        this.salePhone = salePhone;
    }

    public String getCustomerNm() {
        return customerNm;
    }

    public void setCustomerNm(String customerNm) {
        this.customerNm = customerNm;
    }

    public String getCustomerTel() {
        return customerTel;
    }

    public void setCustomerTel(String customerTel) {
        this.customerTel = customerTel;
    }

    public String getCustomerNum() {
        return customerNum;
    }

    public void setCustomerNum(String customerNum) {
        this.customerNum = customerNum;
    }

    public String getEstateId() {
        return estateId;
    }

    public void setEstateId(String estateId) {
        this.estateId = estateId;
    }

    public String getEstateNm() {
        return estateNm;
    }

    public void setEstateNm(String estateNm) {
        this.estateNm = estateNm;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getSeeDate() {
        return seeDate;
    }

    public void setSeeDate(String seeDate) {
        this.seeDate = seeDate;
    }

    public String getPledgedDate() {
        return pledgedDate;
    }

    public void setPledgedDate(String pledgedDate) {
        this.pledgedDate = pledgedDate;
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo;
    }

    public String getSuitNum() {
        return suitNum;
    }

    public void setSuitNum(String suitNum) {
        this.suitNum = suitNum;
    }

    public String getRoughArea() {
        return roughArea;
    }

    public void setRoughArea(String roughArea) {
        this.roughArea = roughArea;
    }

    public String getRoughPrice() {
        return roughPrice;
    }

    public void setRoughPrice(String roughPrice) {
        this.roughPrice = roughPrice;
    }

    public String getRoughAmount() {
        return roughAmount;
    }

    public void setRoughAmount(String roughAmount) {
        this.roughAmount = roughAmount;
    }

    public String getRoughDate() {
        return roughDate;
    }

    public void setRoughDate(String roughDate) {
        this.roughDate = roughDate;
    }

    public String getDealArea() {
        return dealArea;
    }

    public void setDealArea(String dealArea) {
        this.dealArea = dealArea;
    }

    public String getDealPrice() {
        return dealPrice;
    }

    public void setDealPrice(String dealPrice) {
        this.dealPrice = dealPrice;
    }

    public String getDealAmount() {
        return dealAmount;
    }

    public void setDealAmount(String dealAmount) {
        this.dealAmount = dealAmount;
    }

    public String getDealDate() {
        return dealDate;
    }

    public void setDealDate(String dealDate) {
        this.dealDate = dealDate;
    }

    public String getTotalEPTIncome() {
        return totalEPTIncome;
    }

    public void setTotalEPTIncome(String totalEPTIncome) {
        this.totalEPTIncome = totalEPTIncome;
    }

    public String getTotalEATIncome() {
        return totalEATIncome;
    }

    public void setTotalEATIncome(String totalEATIncome) {
        this.totalEATIncome = totalEATIncome;
    }

    public String getTotalEPTCommission() {
        return totalEPTCommission;
    }

    public void setTotalEPTCommission(String totalEPTCommission) {
        this.totalEPTCommission = totalEPTCommission;
    }

    public String getTotalEATCommission() {
        return totalEATCommission;
    }

    public void setTotalEATCommission(String totalEATCommission) {
        this.totalEATCommission = totalEATCommission;
    }

    public String getTotalEPTProfit() {
        return totalEPTProfit;
    }

    public void setTotalEPTProfit(String totalEPTProfit) {
        this.totalEPTProfit = totalEPTProfit;
    }

    public String getTotalEATProfit() {
        return totalEATProfit;
    }

    public void setTotalEATProfit(String totalEATProfit) {
        this.totalEATProfit = totalEATProfit;
    }

    public String getBeforeEPTIncome() {
        return beforeEPTIncome;
    }

    public void setBeforeEPTIncome(String beforeEPTIncome) {
        this.beforeEPTIncome = beforeEPTIncome;
    }

    public String getBeforeEATIncome() {
        return beforeEATIncome;
    }

    public void setBeforeEATIncome(String beforeEATIncome) {
        this.beforeEATIncome = beforeEATIncome;
    }

    public String getBeforeEPTCommission() {
        return beforeEPTCommission;
    }

    public void setBeforeEPTCommission(String beforeEPTCommission) {
        this.beforeEPTCommission = beforeEPTCommission;
    }

    public String getBeforeEATCommission() {
        return beforeEATCommission;
    }

    public void setBeforeEATCommission(String beforeEATCommission) {
        this.beforeEATCommission = beforeEATCommission;
    }

    public String getBeforeEPTProfit() {
        return beforeEPTProfit;
    }

    public void setBeforeEPTProfit(String beforeEPTProfit) {
        this.beforeEPTProfit = beforeEPTProfit;
    }

    public String getBeforeEATProfit() {
        return beforeEATProfit;
    }

    public void setBeforeEATProfit(String beforeEATProfit) {
        this.beforeEATProfit = beforeEATProfit;
    }

    public String getThisEPTIncome() {
        return thisEPTIncome;
    }

    public void setThisEPTIncome(String thisEPTIncome) {
        this.thisEPTIncome = thisEPTIncome;
    }

    public String getThisEATIncome() {
        return thisEATIncome;
    }

    public void setThisEATIncome(String thisEATIncome) {
        this.thisEATIncome = thisEATIncome;
    }

    public String getThisEPTCommission() {
        return thisEPTCommission;
    }

    public void setThisEPTCommission(String thisEPTCommission) {
        this.thisEPTCommission = thisEPTCommission;
    }

    public String getThisEATCommission() {
        return thisEATCommission;
    }

    public void setThisEATCommission(String thisEATCommission) {
        this.thisEATCommission = thisEATCommission;
    }

    public String getThisEPTProfit() {
        return thisEPTProfit;
    }

    public void setThisEPTProfit(String thisEPTProfit) {
        this.thisEPTProfit = thisEPTProfit;
    }

    public String getThisEATProfit() {
        return thisEATProfit;
    }

    public void setThisEATProfit(String thisEATProfit) {
        this.thisEATProfit = thisEATProfit;
    }

    public String getJanEPTIncome() {
        return janEPTIncome;
    }

    public void setJanEPTIncome(String janEPTIncome) {
        this.janEPTIncome = janEPTIncome;
    }

    public String getJanEATIncome() {
        return janEATIncome;
    }

    public void setJanEATIncome(String janEATIncome) {
        this.janEATIncome = janEATIncome;
    }

    public String getJanEPTCommission() {
        return janEPTCommission;
    }

    public void setJanEPTCommission(String janEPTCommission) {
        this.janEPTCommission = janEPTCommission;
    }

    public String getJanEATCommission() {
        return janEATCommission;
    }

    public void setJanEATCommission(String janEATCommission) {
        this.janEATCommission = janEATCommission;
    }

    public String getJanEPTProfit() {
        return janEPTProfit;
    }

    public void setJanEPTProfit(String janEPTProfit) {
        this.janEPTProfit = janEPTProfit;
    }

    public String getJanEATProfit() {
        return janEATProfit;
    }

    public void setJanEATProfit(String janEATProfit) {
        this.janEATProfit = janEATProfit;
    }

    public String getFebEPTIncome() {
        return febEPTIncome;
    }

    public void setFebEPTIncome(String febEPTIncome) {
        this.febEPTIncome = febEPTIncome;
    }

    public String getFebEATIncome() {
        return febEATIncome;
    }

    public void setFebEATIncome(String febEATIncome) {
        this.febEATIncome = febEATIncome;
    }

    public String getFebEPTCommission() {
        return febEPTCommission;
    }

    public void setFebEPTCommission(String febEPTCommission) {
        this.febEPTCommission = febEPTCommission;
    }

    public String getFebEATCommission() {
        return febEATCommission;
    }

    public void setFebEATCommission(String febEATCommission) {
        this.febEATCommission = febEATCommission;
    }

    public String getFebEPTProfit() {
        return febEPTProfit;
    }

    public void setFebEPTProfit(String febEPTProfit) {
        this.febEPTProfit = febEPTProfit;
    }

    public String getFebEATProfit() {
        return febEATProfit;
    }

    public void setFebEATProfit(String febEATProfit) {
        this.febEATProfit = febEATProfit;
    }

    public String getMarEPTIncome() {
        return marEPTIncome;
    }

    public void setMarEPTIncome(String marEPTIncome) {
        this.marEPTIncome = marEPTIncome;
    }

    public String getMarEATIncome() {
        return marEATIncome;
    }

    public void setMarEATIncome(String marEATIncome) {
        this.marEATIncome = marEATIncome;
    }

    public String getMarEPTCommission() {
        return marEPTCommission;
    }

    public void setMarEPTCommission(String marEPTCommission) {
        this.marEPTCommission = marEPTCommission;
    }

    public String getMarEATCommission() {
        return marEATCommission;
    }

    public void setMarEATCommission(String marEATCommission) {
        this.marEATCommission = marEATCommission;
    }

    public String getMarEPTProfit() {
        return marEPTProfit;
    }

    public void setMarEPTProfit(String marEPTProfit) {
        this.marEPTProfit = marEPTProfit;
    }

    public String getMarEATProfit() {
        return marEATProfit;
    }

    public void setMarEATProfit(String marEATProfit) {
        this.marEATProfit = marEATProfit;
    }

    public String getAprEPTIncome() {
        return aprEPTIncome;
    }

    public void setAprEPTIncome(String aprEPTIncome) {
        this.aprEPTIncome = aprEPTIncome;
    }

    public String getAprEATIncome() {
        return aprEATIncome;
    }

    public void setAprEATIncome(String aprEATIncome) {
        this.aprEATIncome = aprEATIncome;
    }

    public String getAprEPTCommission() {
        return aprEPTCommission;
    }

    public void setAprEPTCommission(String aprEPTCommission) {
        this.aprEPTCommission = aprEPTCommission;
    }

    public String getAprEATCommission() {
        return aprEATCommission;
    }

    public void setAprEATCommission(String aprEATCommission) {
        this.aprEATCommission = aprEATCommission;
    }

    public String getAprEPTProfit() {
        return aprEPTProfit;
    }

    public void setAprEPTProfit(String aprEPTProfit) {
        this.aprEPTProfit = aprEPTProfit;
    }

    public String getAprEATProfit() {
        return aprEATProfit;
    }

    public void setAprEATProfit(String aprEATProfit) {
        this.aprEATProfit = aprEATProfit;
    }

    public String getMayEPTIncome() {
        return mayEPTIncome;
    }

    public void setMayEPTIncome(String mayEPTIncome) {
        this.mayEPTIncome = mayEPTIncome;
    }

    public String getMayEATIncome() {
        return mayEATIncome;
    }

    public void setMayEATIncome(String mayEATIncome) {
        this.mayEATIncome = mayEATIncome;
    }

    public String getMayEPTCommission() {
        return mayEPTCommission;
    }

    public void setMayEPTCommission(String mayEPTCommission) {
        this.mayEPTCommission = mayEPTCommission;
    }

    public String getMayEATCommission() {
        return mayEATCommission;
    }

    public void setMayEATCommission(String mayEATCommission) {
        this.mayEATCommission = mayEATCommission;
    }

    public String getMayEPTProfit() {
        return mayEPTProfit;
    }

    public void setMayEPTProfit(String mayEPTProfit) {
        this.mayEPTProfit = mayEPTProfit;
    }

    public String getMayEATProfit() {
        return mayEATProfit;
    }

    public void setMayEATProfit(String mayEATProfit) {
        this.mayEATProfit = mayEATProfit;
    }

    public String getJunEPTIncome() {
        return junEPTIncome;
    }

    public void setJunEPTIncome(String junEPTIncome) {
        this.junEPTIncome = junEPTIncome;
    }

    public String getJunEATIncome() {
        return junEATIncome;
    }

    public void setJunEATIncome(String junEATIncome) {
        this.junEATIncome = junEATIncome;
    }

    public String getJunEPTCommission() {
        return junEPTCommission;
    }

    public void setJunEPTCommission(String junEPTCommission) {
        this.junEPTCommission = junEPTCommission;
    }

    public String getJunEATCommission() {
        return junEATCommission;
    }

    public void setJunEATCommission(String junEATCommission) {
        this.junEATCommission = junEATCommission;
    }

    public String getJunEPTProfit() {
        return junEPTProfit;
    }

    public void setJunEPTProfit(String junEPTProfit) {
        this.junEPTProfit = junEPTProfit;
    }

    public String getJunEATProfit() {
        return junEATProfit;
    }

    public void setJunEATProfit(String junEATProfit) {
        this.junEATProfit = junEATProfit;
    }

    public String getJulEPTIncome() {
        return julEPTIncome;
    }

    public void setJulEPTIncome(String julEPTIncome) {
        this.julEPTIncome = julEPTIncome;
    }

    public String getJulEATIncome() {
        return julEATIncome;
    }

    public void setJulEATIncome(String julEATIncome) {
        this.julEATIncome = julEATIncome;
    }

    public String getJulEPTCommission() {
        return julEPTCommission;
    }

    public void setJulEPTCommission(String julEPTCommission) {
        this.julEPTCommission = julEPTCommission;
    }

    public String getJulEATCommission() {
        return julEATCommission;
    }

    public void setJulEATCommission(String julEATCommission) {
        this.julEATCommission = julEATCommission;
    }

    public String getJulEPTProfit() {
        return julEPTProfit;
    }

    public void setJulEPTProfit(String julEPTProfit) {
        this.julEPTProfit = julEPTProfit;
    }

    public String getJulEATProfit() {
        return julEATProfit;
    }

    public void setJulEATProfit(String julEATProfit) {
        this.julEATProfit = julEATProfit;
    }

    public String getAugEPTIncome() {
        return augEPTIncome;
    }

    public void setAugEPTIncome(String augEPTIncome) {
        this.augEPTIncome = augEPTIncome;
    }

    public String getAugEATIncome() {
        return augEATIncome;
    }

    public void setAugEATIncome(String augEATIncome) {
        this.augEATIncome = augEATIncome;
    }

    public String getAugEPTCommission() {
        return augEPTCommission;
    }

    public void setAugEPTCommission(String augEPTCommission) {
        this.augEPTCommission = augEPTCommission;
    }

    public String getAugEATCommission() {
        return augEATCommission;
    }

    public void setAugEATCommission(String augEATCommission) {
        this.augEATCommission = augEATCommission;
    }

    public String getAugEPTProfit() {
        return augEPTProfit;
    }

    public void setAugEPTProfit(String augEPTProfit) {
        this.augEPTProfit = augEPTProfit;
    }

    public String getAugEATProfit() {
        return augEATProfit;
    }

    public void setAugEATProfit(String augEATProfit) {
        this.augEATProfit = augEATProfit;
    }

    public String getSepEPTIncome() {
        return sepEPTIncome;
    }

    public void setSepEPTIncome(String sepEPTIncome) {
        this.sepEPTIncome = sepEPTIncome;
    }

    public String getSepEATIncome() {
        return sepEATIncome;
    }

    public void setSepEATIncome(String sepEATIncome) {
        this.sepEATIncome = sepEATIncome;
    }

    public String getSepEPTCommission() {
        return sepEPTCommission;
    }

    public void setSepEPTCommission(String sepEPTCommission) {
        this.sepEPTCommission = sepEPTCommission;
    }

    public String getSepEATCommission() {
        return sepEATCommission;
    }

    public void setSepEATCommission(String sepEATCommission) {
        this.sepEATCommission = sepEATCommission;
    }

    public String getSepEPTProfit() {
        return sepEPTProfit;
    }

    public void setSepEPTProfit(String sepEPTProfit) {
        this.sepEPTProfit = sepEPTProfit;
    }

    public String getSepEATProfit() {
        return sepEATProfit;
    }

    public void setSepEATProfit(String sepEATProfit) {
        this.sepEATProfit = sepEATProfit;
    }

    public String getOctEPTIncome() {
        return octEPTIncome;
    }

    public void setOctEPTIncome(String octEPTIncome) {
        this.octEPTIncome = octEPTIncome;
    }

    public String getOctEATIncome() {
        return octEATIncome;
    }

    public void setOctEATIncome(String octEATIncome) {
        this.octEATIncome = octEATIncome;
    }

    public String getOctEPTCommission() {
        return octEPTCommission;
    }

    public void setOctEPTCommission(String octEPTCommission) {
        this.octEPTCommission = octEPTCommission;
    }

    public String getOctEATCommission() {
        return octEATCommission;
    }

    public void setOctEATCommission(String octEATCommission) {
        this.octEATCommission = octEATCommission;
    }

    public String getOctEPTProfit() {
        return octEPTProfit;
    }

    public void setOctEPTProfit(String octEPTProfit) {
        this.octEPTProfit = octEPTProfit;
    }

    public String getOctEATProfit() {
        return octEATProfit;
    }

    public void setOctEATProfit(String octEATProfit) {
        this.octEATProfit = octEATProfit;
    }

    public String getNovEPTIncome() {
        return novEPTIncome;
    }

    public void setNovEPTIncome(String novEPTIncome) {
        this.novEPTIncome = novEPTIncome;
    }

    public String getNovEATIncome() {
        return novEATIncome;
    }

    public void setNovEATIncome(String novEATIncome) {
        this.novEATIncome = novEATIncome;
    }

    public String getNovEPTCommission() {
        return novEPTCommission;
    }

    public void setNovEPTCommission(String novEPTCommission) {
        this.novEPTCommission = novEPTCommission;
    }

    public String getNovEATCommission() {
        return novEATCommission;
    }

    public void setNovEATCommission(String novEATCommission) {
        this.novEATCommission = novEATCommission;
    }

    public String getNovEPTProfit() {
        return novEPTProfit;
    }

    public void setNovEPTProfit(String novEPTProfit) {
        this.novEPTProfit = novEPTProfit;
    }

    public String getNovEATProfit() {
        return novEATProfit;
    }

    public void setNovEATProfit(String novEATProfit) {
        this.novEATProfit = novEATProfit;
    }

    public String getDecEPTIncome() {
        return decEPTIncome;
    }

    public void setDecEPTIncome(String decEPTIncome) {
        this.decEPTIncome = decEPTIncome;
    }

    public String getDecEATIncome() {
        return decEATIncome;
    }

    public void setDecEATIncome(String decEATIncome) {
        this.decEATIncome = decEATIncome;
    }

    public String getDecEPTCommission() {
        return decEPTCommission;
    }

    public void setDecEPTCommission(String decEPTCommission) {
        this.decEPTCommission = decEPTCommission;
    }

    public String getDecEATCommission() {
        return decEATCommission;
    }

    public void setDecEATCommission(String decEATCommission) {
        this.decEATCommission = decEATCommission;
    }

    public String getDecEPTProfit() {
        return decEPTProfit;
    }

    public void setDecEPTProfit(String decEPTProfit) {
        this.decEPTProfit = decEPTProfit;
    }

    public String getDecEATProfit() {
        return decEATProfit;
    }

    public void setDecEATProfit(String decEATProfit) {
        this.decEATProfit = decEATProfit;
    }

    public String getTotalAPTIncome() {
        return totalAPTIncome;
    }

    public void setTotalAPTIncome(String totalAPTIncome) {
        this.totalAPTIncome = totalAPTIncome;
    }

    public String getTotalAATIncome() {
        return totalAATIncome;
    }

    public void setTotalAATIncome(String totalAATIncome) {
        this.totalAATIncome = totalAATIncome;
    }

    public String getTotalAPTCommission() {
        return totalAPTCommission;
    }

    public void setTotalAPTCommission(String totalAPTCommission) {
        this.totalAPTCommission = totalAPTCommission;
    }

    public String getTotalAATCommission() {
        return totalAATCommission;
    }

    public void setTotalAATCommission(String totalAATCommission) {
        this.totalAATCommission = totalAATCommission;
    }

    public String getTotalAPTProfit() {
        return totalAPTProfit;
    }

    public void setTotalAPTProfit(String totalAPTProfit) {
        this.totalAPTProfit = totalAPTProfit;
    }

    public String getTotalAATProfit() {
        return totalAATProfit;
    }

    public void setTotalAATProfit(String totalAATProfit) {
        this.totalAATProfit = totalAATProfit;
    }

    public String getBeforeAPTIncome() {
        return beforeAPTIncome;
    }

    public void setBeforeAPTIncome(String beforeAPTIncome) {
        this.beforeAPTIncome = beforeAPTIncome;
    }

    public String getBeforeAATIncome() {
        return beforeAATIncome;
    }

    public void setBeforeAATIncome(String beforeAATIncome) {
        this.beforeAATIncome = beforeAATIncome;
    }

    public String getBeforeAPTCommission() {
        return beforeAPTCommission;
    }

    public void setBeforeAPTCommission(String beforeAPTCommission) {
        this.beforeAPTCommission = beforeAPTCommission;
    }

    public String getBeforeAATCommission() {
        return beforeAATCommission;
    }

    public void setBeforeAATCommission(String beforeAATCommission) {
        this.beforeAATCommission = beforeAATCommission;
    }

    public String getBeforeAPTProfit() {
        return beforeAPTProfit;
    }

    public void setBeforeAPTProfit(String beforeAPTProfit) {
        this.beforeAPTProfit = beforeAPTProfit;
    }

    public String getBeforeAATProfit() {
        return beforeAATProfit;
    }

    public void setBeforeAATProfit(String beforeAATProfit) {
        this.beforeAATProfit = beforeAATProfit;
    }

    public String getThisAPTIncome() {
        return thisAPTIncome;
    }

    public void setThisAPTIncome(String thisAPTIncome) {
        this.thisAPTIncome = thisAPTIncome;
    }

    public String getThisAATIncome() {
        return thisAATIncome;
    }

    public void setThisAATIncome(String thisAATIncome) {
        this.thisAATIncome = thisAATIncome;
    }

    public String getThisAPTCommission() {
        return thisAPTCommission;
    }

    public void setThisAPTCommission(String thisAPTCommission) {
        this.thisAPTCommission = thisAPTCommission;
    }

    public String getThisAATCommission() {
        return thisAATCommission;
    }

    public void setThisAATCommission(String thisAATCommission) {
        this.thisAATCommission = thisAATCommission;
    }

    public String getThisAPTProfit() {
        return thisAPTProfit;
    }

    public void setThisAPTProfit(String thisAPTProfit) {
        this.thisAPTProfit = thisAPTProfit;
    }

    public String getThisAATProfit() {
        return thisAATProfit;
    }

    public void setThisAATProfit(String thisAATProfit) {
        this.thisAATProfit = thisAATProfit;
    }

    public String getJanAPTIncome() {
        return janAPTIncome;
    }

    public void setJanAPTIncome(String janAPTIncome) {
        this.janAPTIncome = janAPTIncome;
    }

    public String getJanAATIncome() {
        return janAATIncome;
    }

    public void setJanAATIncome(String janAATIncome) {
        this.janAATIncome = janAATIncome;
    }

    public String getJanAPTCommission() {
        return janAPTCommission;
    }

    public void setJanAPTCommission(String janAPTCommission) {
        this.janAPTCommission = janAPTCommission;
    }

    public String getJanAATCommission() {
        return janAATCommission;
    }

    public void setJanAATCommission(String janAATCommission) {
        this.janAATCommission = janAATCommission;
    }

    public String getJanAPTProfit() {
        return janAPTProfit;
    }

    public void setJanAPTProfit(String janAPTProfit) {
        this.janAPTProfit = janAPTProfit;
    }

    public String getJanAATProfit() {
        return janAATProfit;
    }

    public void setJanAATProfit(String janAATProfit) {
        this.janAATProfit = janAATProfit;
    }

    public String getFebAPTIncome() {
        return febAPTIncome;
    }

    public void setFebAPTIncome(String febAPTIncome) {
        this.febAPTIncome = febAPTIncome;
    }

    public String getFebAATIncome() {
        return febAATIncome;
    }

    public void setFebAATIncome(String febAATIncome) {
        this.febAATIncome = febAATIncome;
    }

    public String getFebAPTCommission() {
        return febAPTCommission;
    }

    public void setFebAPTCommission(String febAPTCommission) {
        this.febAPTCommission = febAPTCommission;
    }

    public String getFebAATCommission() {
        return febAATCommission;
    }

    public void setFebAATCommission(String febAATCommission) {
        this.febAATCommission = febAATCommission;
    }

    public String getFebAPTProfit() {
        return febAPTProfit;
    }

    public void setFebAPTProfit(String febAPTProfit) {
        this.febAPTProfit = febAPTProfit;
    }

    public String getFebAATProfit() {
        return febAATProfit;
    }

    public void setFebAATProfit(String febAATProfit) {
        this.febAATProfit = febAATProfit;
    }

    public String getMarAPTIncome() {
        return marAPTIncome;
    }

    public void setMarAPTIncome(String marAPTIncome) {
        this.marAPTIncome = marAPTIncome;
    }

    public String getMarAATIncome() {
        return marAATIncome;
    }

    public void setMarAATIncome(String marAATIncome) {
        this.marAATIncome = marAATIncome;
    }

    public String getMarAPTCommission() {
        return marAPTCommission;
    }

    public void setMarAPTCommission(String marAPTCommission) {
        this.marAPTCommission = marAPTCommission;
    }

    public String getMarAATCommission() {
        return marAATCommission;
    }

    public void setMarAATCommission(String marAATCommission) {
        this.marAATCommission = marAATCommission;
    }

    public String getMarAPTProfit() {
        return marAPTProfit;
    }

    public void setMarAPTProfit(String marAPTProfit) {
        this.marAPTProfit = marAPTProfit;
    }

    public String getMarAATProfit() {
        return marAATProfit;
    }

    public void setMarAATProfit(String marAATProfit) {
        this.marAATProfit = marAATProfit;
    }

    public String getAprAPTIncome() {
        return aprAPTIncome;
    }

    public void setAprAPTIncome(String aprAPTIncome) {
        this.aprAPTIncome = aprAPTIncome;
    }

    public String getAprAATIncome() {
        return aprAATIncome;
    }

    public void setAprAATIncome(String aprAATIncome) {
        this.aprAATIncome = aprAATIncome;
    }

    public String getAprAPTCommission() {
        return aprAPTCommission;
    }

    public void setAprAPTCommission(String aprAPTCommission) {
        this.aprAPTCommission = aprAPTCommission;
    }

    public String getAprAATCommission() {
        return aprAATCommission;
    }

    public void setAprAATCommission(String aprAATCommission) {
        this.aprAATCommission = aprAATCommission;
    }

    public String getAprAPTProfit() {
        return aprAPTProfit;
    }

    public void setAprAPTProfit(String aprAPTProfit) {
        this.aprAPTProfit = aprAPTProfit;
    }

    public String getAprAATProfit() {
        return aprAATProfit;
    }

    public void setAprAATProfit(String aprAATProfit) {
        this.aprAATProfit = aprAATProfit;
    }

    public String getMayAPTIncome() {
        return mayAPTIncome;
    }

    public void setMayAPTIncome(String mayAPTIncome) {
        this.mayAPTIncome = mayAPTIncome;
    }

    public String getMayAATIncome() {
        return mayAATIncome;
    }

    public void setMayAATIncome(String mayAATIncome) {
        this.mayAATIncome = mayAATIncome;
    }

    public String getMayAPTCommission() {
        return mayAPTCommission;
    }

    public void setMayAPTCommission(String mayAPTCommission) {
        this.mayAPTCommission = mayAPTCommission;
    }

    public String getMayAATCommission() {
        return mayAATCommission;
    }

    public void setMayAATCommission(String mayAATCommission) {
        this.mayAATCommission = mayAATCommission;
    }

    public String getMayAPTProfit() {
        return mayAPTProfit;
    }

    public void setMayAPTProfit(String mayAPTProfit) {
        this.mayAPTProfit = mayAPTProfit;
    }

    public String getMayAATProfit() {
        return mayAATProfit;
    }

    public void setMayAATProfit(String mayAATProfit) {
        this.mayAATProfit = mayAATProfit;
    }

    public String getJunAPTIncome() {
        return junAPTIncome;
    }

    public void setJunAPTIncome(String junAPTIncome) {
        this.junAPTIncome = junAPTIncome;
    }

    public String getJunAATIncome() {
        return junAATIncome;
    }

    public void setJunAATIncome(String junAATIncome) {
        this.junAATIncome = junAATIncome;
    }

    public String getJunAPTCommission() {
        return junAPTCommission;
    }

    public void setJunAPTCommission(String junAPTCommission) {
        this.junAPTCommission = junAPTCommission;
    }

    public String getJunAATCommission() {
        return junAATCommission;
    }

    public void setJunAATCommission(String junAATCommission) {
        this.junAATCommission = junAATCommission;
    }

    public String getJunAPTProfit() {
        return junAPTProfit;
    }

    public void setJunAPTProfit(String junAPTProfit) {
        this.junAPTProfit = junAPTProfit;
    }

    public String getJunAATProfit() {
        return junAATProfit;
    }

    public void setJunAATProfit(String junAATProfit) {
        this.junAATProfit = junAATProfit;
    }

    public String getJulAPTIncome() {
        return julAPTIncome;
    }

    public void setJulAPTIncome(String julAPTIncome) {
        this.julAPTIncome = julAPTIncome;
    }

    public String getJulAATIncome() {
        return julAATIncome;
    }

    public void setJulAATIncome(String julAATIncome) {
        this.julAATIncome = julAATIncome;
    }

    public String getJulAPTCommission() {
        return julAPTCommission;
    }

    public void setJulAPTCommission(String julAPTCommission) {
        this.julAPTCommission = julAPTCommission;
    }

    public String getJulAATCommission() {
        return julAATCommission;
    }

    public void setJulAATCommission(String julAATCommission) {
        this.julAATCommission = julAATCommission;
    }

    public String getJulAPTProfit() {
        return julAPTProfit;
    }

    public void setJulAPTProfit(String julAPTProfit) {
        this.julAPTProfit = julAPTProfit;
    }

    public String getJulAATProfit() {
        return julAATProfit;
    }

    public void setJulAATProfit(String julAATProfit) {
        this.julAATProfit = julAATProfit;
    }

    public String getAugAPTIncome() {
        return augAPTIncome;
    }

    public void setAugAPTIncome(String augAPTIncome) {
        this.augAPTIncome = augAPTIncome;
    }

    public String getAugAATIncome() {
        return augAATIncome;
    }

    public void setAugAATIncome(String augAATIncome) {
        this.augAATIncome = augAATIncome;
    }

    public String getAugAPTCommission() {
        return augAPTCommission;
    }

    public void setAugAPTCommission(String augAPTCommission) {
        this.augAPTCommission = augAPTCommission;
    }

    public String getAugAATCommission() {
        return augAATCommission;
    }

    public void setAugAATCommission(String augAATCommission) {
        this.augAATCommission = augAATCommission;
    }

    public String getAugAPTProfit() {
        return augAPTProfit;
    }

    public void setAugAPTProfit(String augAPTProfit) {
        this.augAPTProfit = augAPTProfit;
    }

    public String getAugAATProfit() {
        return augAATProfit;
    }

    public void setAugAATProfit(String augAATProfit) {
        this.augAATProfit = augAATProfit;
    }

    public String getSepAPTIncome() {
        return sepAPTIncome;
    }

    public void setSepAPTIncome(String sepAPTIncome) {
        this.sepAPTIncome = sepAPTIncome;
    }

    public String getSepAATIncome() {
        return sepAATIncome;
    }

    public void setSepAATIncome(String sepAATIncome) {
        this.sepAATIncome = sepAATIncome;
    }

    public String getSepAPTCommission() {
        return sepAPTCommission;
    }

    public void setSepAPTCommission(String sepAPTCommission) {
        this.sepAPTCommission = sepAPTCommission;
    }

    public String getSepAATCommission() {
        return sepAATCommission;
    }

    public void setSepAATCommission(String sepAATCommission) {
        this.sepAATCommission = sepAATCommission;
    }

    public String getSepAPTProfit() {
        return sepAPTProfit;
    }

    public void setSepAPTProfit(String sepAPTProfit) {
        this.sepAPTProfit = sepAPTProfit;
    }

    public String getSepAATProfit() {
        return sepAATProfit;
    }

    public void setSepAATProfit(String sepAATProfit) {
        this.sepAATProfit = sepAATProfit;
    }

    public String getOctAPTIncome() {
        return octAPTIncome;
    }

    public void setOctAPTIncome(String octAPTIncome) {
        this.octAPTIncome = octAPTIncome;
    }

    public String getOctAATIncome() {
        return octAATIncome;
    }

    public void setOctAATIncome(String octAATIncome) {
        this.octAATIncome = octAATIncome;
    }

    public String getOctAPTCommission() {
        return octAPTCommission;
    }

    public void setOctAPTCommission(String octAPTCommission) {
        this.octAPTCommission = octAPTCommission;
    }

    public String getOctAATCommission() {
        return octAATCommission;
    }

    public void setOctAATCommission(String octAATCommission) {
        this.octAATCommission = octAATCommission;
    }

    public String getOctAPTProfit() {
        return octAPTProfit;
    }

    public void setOctAPTProfit(String octAPTProfit) {
        this.octAPTProfit = octAPTProfit;
    }

    public String getOctAATProfit() {
        return octAATProfit;
    }

    public void setOctAATProfit(String octAATProfit) {
        this.octAATProfit = octAATProfit;
    }

    public String getNovAPTIncome() {
        return novAPTIncome;
    }

    public void setNovAPTIncome(String novAPTIncome) {
        this.novAPTIncome = novAPTIncome;
    }

    public String getNovAATIncome() {
        return novAATIncome;
    }

    public void setNovAATIncome(String novAATIncome) {
        this.novAATIncome = novAATIncome;
    }

    public String getNovAPTCommission() {
        return novAPTCommission;
    }

    public void setNovAPTCommission(String novAPTCommission) {
        this.novAPTCommission = novAPTCommission;
    }

    public String getNovAATCommission() {
        return novAATCommission;
    }

    public void setNovAATCommission(String novAATCommission) {
        this.novAATCommission = novAATCommission;
    }

    public String getNovAPTProfit() {
        return novAPTProfit;
    }

    public void setNovAPTProfit(String novAPTProfit) {
        this.novAPTProfit = novAPTProfit;
    }

    public String getNovAATProfit() {
        return novAATProfit;
    }

    public void setNovAATProfit(String novAATProfit) {
        this.novAATProfit = novAATProfit;
    }

    public String getDecAPTIncome() {
        return decAPTIncome;
    }

    public void setDecAPTIncome(String decAPTIncome) {
        this.decAPTIncome = decAPTIncome;
    }

    public String getDecAATIncome() {
        return decAATIncome;
    }

    public void setDecAATIncome(String decAATIncome) {
        this.decAATIncome = decAATIncome;
    }

    public String getDecAPTCommission() {
        return decAPTCommission;
    }

    public void setDecAPTCommission(String decAPTCommission) {
        this.decAPTCommission = decAPTCommission;
    }

    public String getDecAATCommission() {
        return decAATCommission;
    }

    public void setDecAATCommission(String decAATCommission) {
        this.decAATCommission = decAATCommission;
    }

    public String getDecAPTProfit() {
        return decAPTProfit;
    }

    public void setDecAPTProfit(String decAPTProfit) {
        this.decAPTProfit = decAPTProfit;
    }

    public String getDecAATProfit() {
        return decAATProfit;
    }

    public void setDecAATProfit(String decAATProfit) {
        this.decAATProfit = decAATProfit;
    }

    public String getTotalNPTCommission() {
        return totalNPTCommission;
    }

    public void setTotalNPTCommission(String totalNPTCommission) {
        this.totalNPTCommission = totalNPTCommission;
    }

    public String getTotalNATCommission() {
        return totalNATCommission;
    }

    public void setTotalNATCommission(String totalNATCommission) {
        this.totalNATCommission = totalNATCommission;
    }

    public String getExpenderCode() {
        return expenderCode;
    }

    public void setExpenderCode(String expenderCode) {
        this.expenderCode = expenderCode;
    }

    public String getExpenderName() {
        return expenderName;
    }

    public void setExpenderName(String expenderName) {
        this.expenderName = expenderName;
    }

    public String getGroupLeaderCode() {
        return groupLeaderCode;
    }

    public void setGroupLeaderCode(String groupLeaderCode) {
        this.groupLeaderCode = groupLeaderCode;
    }

    public String getGroupLeaderName() {
        return groupLeaderName;
    }

    public void setGroupLeaderName(String groupLeaderName) {
        this.groupLeaderName = groupLeaderName;
    }

    public String getCenterLeaderCode() {
        return centerLeaderCode;
    }

    public void setCenterLeaderCode(String centerLeaderCode) {
        this.centerLeaderCode = centerLeaderCode;
    }

    public String getCenterLeaderName() {
        return centerLeaderName;
    }

    public void setCenterLeaderName(String centerLeaderName) {
        this.centerLeaderName = centerLeaderName;
    }

    public String getAreaLeaderCode() {
        return areaLeaderCode;
    }

    public void setAreaLeaderCode(String areaLeaderCode) {
        this.areaLeaderCode = areaLeaderCode;
    }

    public String getAreaLeaderName() {
        return areaLeaderName;
    }

    public void setAreaLeaderName(String areaLeaderName) {
        this.areaLeaderName = areaLeaderName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getProjectDepartmentId() {
        return projectDepartmentId;
    }

    public void setProjectDepartmentId(String projectDepartmentId) {
        this.projectDepartmentId = projectDepartmentId;
    }

    public String getProjectDepartmentName() {
        return projectDepartmentName;
    }

    public void setProjectDepartmentName(String projectDepartmentName) {
        this.projectDepartmentName = projectDepartmentName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getProjectCityId() {
        return projectCityId;
    }

    public void setProjectCityId(String projectCityId) {
        this.projectCityId = projectCityId;
    }

    public String getProjectCityName() {
        return projectCityName;
    }

    public void setProjectCityName(String projectCityName) {
        this.projectCityName = projectCityName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getJanEPTPrepaid() {
        return janEPTPrepaid;
    }

    public void setJanEPTPrepaid(String janEPTPrepaid) {
        this.janEPTPrepaid = janEPTPrepaid;
    }

    public String getFebEPTPrepaid() {
        return febEPTPrepaid;
    }

    public void setFebEPTPrepaid(String febEPTPrepaid) {
        this.febEPTPrepaid = febEPTPrepaid;
    }

    public String getMarEPTPrepaid() {
        return marEPTPrepaid;
    }

    public void setMarEPTPrepaid(String marEPTPrepaid) {
        this.marEPTPrepaid = marEPTPrepaid;
    }

    public String getAprEPTPrepaid() {
        return aprEPTPrepaid;
    }

    public void setAprEPTPrepaid(String aprEPTPrepaid) {
        this.aprEPTPrepaid = aprEPTPrepaid;
    }

    public String getMayEPTPrepaid() {
        return mayEPTPrepaid;
    }

    public void setMayEPTPrepaid(String mayEPTPrepaid) {
        this.mayEPTPrepaid = mayEPTPrepaid;
    }

    public String getJunEPTPrepaid() {
        return junEPTPrepaid;
    }

    public void setJunEPTPrepaid(String junEPTPrepaid) {
        this.junEPTPrepaid = junEPTPrepaid;
    }

    public String getJulEPTPrepaid() {
        return julEPTPrepaid;
    }

    public void setJulEPTPrepaid(String julEPTPrepaid) {
        this.julEPTPrepaid = julEPTPrepaid;
    }

    public String getAugEPTPrepaid() {
        return augEPTPrepaid;
    }

    public void setAugEPTPrepaid(String augEPTPrepaid) {
        this.augEPTPrepaid = augEPTPrepaid;
    }

    public String getSepEPTPrepaid() {
        return sepEPTPrepaid;
    }

    public void setSepEPTPrepaid(String sepEPTPrepaid) {
        this.sepEPTPrepaid = sepEPTPrepaid;
    }

    public String getOctEPTPrepaid() {
        return octEPTPrepaid;
    }

    public void setOctEPTPrepaid(String octEPTPrepaid) {
        this.octEPTPrepaid = octEPTPrepaid;
    }

    public String getNovEPTPrepaid() {
        return novEPTPrepaid;
    }

    public void setNovEPTPrepaid(String novEPTPrepaid) {
        this.novEPTPrepaid = novEPTPrepaid;
    }

    public String getDecEPTPrepaid() {
        return decEPTPrepaid;
    }

    public void setDecEPTPrepaid(String decEPTPrepaid) {
        this.decEPTPrepaid = decEPTPrepaid;
    }

    public String getBeforeEPTPrepaid() {
        return beforeEPTPrepaid;
    }

    public void setBeforeEPTPrepaid(String beforeEPTPrepaid) {
        this.beforeEPTPrepaid = beforeEPTPrepaid;
    }

    public String getThisEPTPrepaid() {
        return thisEPTPrepaid;
    }

    public void setThisEPTPrepaid(String thisEPTPrepaid) {
        this.thisEPTPrepaid = thisEPTPrepaid;
    }

    public String getTotalEPTPrepaid() {
        return totalEPTPrepaid;
    }

    public void setTotalEPTPrepaid(String totalEPTPrepaid) {
        this.totalEPTPrepaid = totalEPTPrepaid;
    }

    public String getJanEATPrepaid() {
        return janEATPrepaid;
    }

    public void setJanEATPrepaid(String janEATPrepaid) {
        this.janEATPrepaid = janEATPrepaid;
    }

    public String getFebEATPrepaid() {
        return febEATPrepaid;
    }

    public void setFebEATPrepaid(String febEATPrepaid) {
        this.febEATPrepaid = febEATPrepaid;
    }

    public String getMarEATPrepaid() {
        return marEATPrepaid;
    }

    public void setMarEATPrepaid(String marEATPrepaid) {
        this.marEATPrepaid = marEATPrepaid;
    }

    public String getAprEATPrepaid() {
        return aprEATPrepaid;
    }

    public void setAprEATPrepaid(String aprEATPrepaid) {
        this.aprEATPrepaid = aprEATPrepaid;
    }

    public String getMayEATPrepaid() {
        return mayEATPrepaid;
    }

    public void setMayEATPrepaid(String mayEATPrepaid) {
        this.mayEATPrepaid = mayEATPrepaid;
    }

    public String getJunEATPrepaid() {
        return junEATPrepaid;
    }

    public void setJunEATPrepaid(String junEATPrepaid) {
        this.junEATPrepaid = junEATPrepaid;
    }

    public String getJulEATPrepaid() {
        return julEATPrepaid;
    }

    public void setJulEATPrepaid(String julEATPrepaid) {
        this.julEATPrepaid = julEATPrepaid;
    }

    public String getAugEATPrepaid() {
        return augEATPrepaid;
    }

    public void setAugEATPrepaid(String augEATPrepaid) {
        this.augEATPrepaid = augEATPrepaid;
    }

    public String getSepEATPrepaid() {
        return sepEATPrepaid;
    }

    public void setSepEATPrepaid(String sepEATPrepaid) {
        this.sepEATPrepaid = sepEATPrepaid;
    }

    public String getOctEATPrepaid() {
        return octEATPrepaid;
    }

    public void setOctEATPrepaid(String octEATPrepaid) {
        this.octEATPrepaid = octEATPrepaid;
    }

    public String getNovEATPrepaid() {
        return novEATPrepaid;
    }

    public void setNovEATPrepaid(String novEATPrepaid) {
        this.novEATPrepaid = novEATPrepaid;
    }

    public String getDecEATPrepaid() {
        return decEATPrepaid;
    }

    public void setDecEATPrepaid(String decEATPrepaid) {
        this.decEATPrepaid = decEATPrepaid;
    }

    public String getBeforeEATPrepaid() {
        return beforeEATPrepaid;
    }

    public void setBeforeEATPrepaid(String beforeEATPrepaid) {
        this.beforeEATPrepaid = beforeEATPrepaid;
    }

    public String getThisEATPrepaid() {
        return thisEATPrepaid;
    }

    public void setThisEATPrepaid(String thisEATPrepaid) {
        this.thisEATPrepaid = thisEATPrepaid;
    }

    public String getTotalEATPrepaid() {
        return totalEATPrepaid;
    }

    public void setTotalEATPrepaid(String totalEATPrepaid) {
        this.totalEATPrepaid = totalEATPrepaid;
    }

    public String getJanAPTPrepaid() {
        return janAPTPrepaid;
    }

    public void setJanAPTPrepaid(String janAPTPrepaid) {
        this.janAPTPrepaid = janAPTPrepaid;
    }

    public String getFebAPTPrepaid() {
        return febAPTPrepaid;
    }

    public void setFebAPTPrepaid(String febAPTPrepaid) {
        this.febAPTPrepaid = febAPTPrepaid;
    }

    public String getMarAPTPrepaid() {
        return marAPTPrepaid;
    }

    public void setMarAPTPrepaid(String marAPTPrepaid) {
        this.marAPTPrepaid = marAPTPrepaid;
    }

    public String getAprAPTPrepaid() {
        return aprAPTPrepaid;
    }

    public void setAprAPTPrepaid(String aprAPTPrepaid) {
        this.aprAPTPrepaid = aprAPTPrepaid;
    }

    public String getMayAPTPrepaid() {
        return mayAPTPrepaid;
    }

    public void setMayAPTPrepaid(String mayAPTPrepaid) {
        this.mayAPTPrepaid = mayAPTPrepaid;
    }

    public String getJunAPTPrepaid() {
        return junAPTPrepaid;
    }

    public void setJunAPTPrepaid(String junAPTPrepaid) {
        this.junAPTPrepaid = junAPTPrepaid;
    }

    public String getJulAPTPrepaid() {
        return julAPTPrepaid;
    }

    public void setJulAPTPrepaid(String julAPTPrepaid) {
        this.julAPTPrepaid = julAPTPrepaid;
    }

    public String getAugAPTPrepaid() {
        return augAPTPrepaid;
    }

    public void setAugAPTPrepaid(String augAPTPrepaid) {
        this.augAPTPrepaid = augAPTPrepaid;
    }

    public String getSepAPTPrepaid() {
        return sepAPTPrepaid;
    }

    public void setSepAPTPrepaid(String sepAPTPrepaid) {
        this.sepAPTPrepaid = sepAPTPrepaid;
    }

    public String getOctAPTPrepaid() {
        return octAPTPrepaid;
    }

    public void setOctAPTPrepaid(String octAPTPrepaid) {
        this.octAPTPrepaid = octAPTPrepaid;
    }

    public String getNovAPTPrepaid() {
        return novAPTPrepaid;
    }

    public void setNovAPTPrepaid(String novAPTPrepaid) {
        this.novAPTPrepaid = novAPTPrepaid;
    }

    public String getDecAPTPrepaid() {
        return decAPTPrepaid;
    }

    public void setDecAPTPrepaid(String decAPTPrepaid) {
        this.decAPTPrepaid = decAPTPrepaid;
    }

    public String getBeforeAPTPrepaid() {
        return beforeAPTPrepaid;
    }

    public void setBeforeAPTPrepaid(String beforeAPTPrepaid) {
        this.beforeAPTPrepaid = beforeAPTPrepaid;
    }

    public String getThisAPTPrepaid() {
        return thisAPTPrepaid;
    }

    public void setThisAPTPrepaid(String thisAPTPrepaid) {
        this.thisAPTPrepaid = thisAPTPrepaid;
    }

    public String getTotalAPTPrepaid() {
        return totalAPTPrepaid;
    }

    public void setTotalAPTPrepaid(String totalAPTPrepaid) {
        this.totalAPTPrepaid = totalAPTPrepaid;
    }

    public String getJanAATPrepaid() {
        return janAATPrepaid;
    }

    public void setJanAATPrepaid(String janAATPrepaid) {
        this.janAATPrepaid = janAATPrepaid;
    }

    public String getFebAATPrepaid() {
        return febAATPrepaid;
    }

    public void setFebAATPrepaid(String febAATPrepaid) {
        this.febAATPrepaid = febAATPrepaid;
    }

    public String getMarAATPrepaid() {
        return marAATPrepaid;
    }

    public void setMarAATPrepaid(String marAATPrepaid) {
        this.marAATPrepaid = marAATPrepaid;
    }

    public String getAprAATPrepaid() {
        return aprAATPrepaid;
    }

    public void setAprAATPrepaid(String aprAATPrepaid) {
        this.aprAATPrepaid = aprAATPrepaid;
    }

    public String getMayAATPrepaid() {
        return mayAATPrepaid;
    }

    public void setMayAATPrepaid(String mayAATPrepaid) {
        this.mayAATPrepaid = mayAATPrepaid;
    }

    public String getJunAATPrepaid() {
        return junAATPrepaid;
    }

    public void setJunAATPrepaid(String junAATPrepaid) {
        this.junAATPrepaid = junAATPrepaid;
    }

    public String getJulAATPrepaid() {
        return julAATPrepaid;
    }

    public void setJulAATPrepaid(String julAATPrepaid) {
        this.julAATPrepaid = julAATPrepaid;
    }

    public String getAugAATPrepaid() {
        return augAATPrepaid;
    }

    public void setAugAATPrepaid(String augAATPrepaid) {
        this.augAATPrepaid = augAATPrepaid;
    }

    public String getSepAATPrepaid() {
        return sepAATPrepaid;
    }

    public void setSepAATPrepaid(String sepAATPrepaid) {
        this.sepAATPrepaid = sepAATPrepaid;
    }

    public String getOctAATPrepaid() {
        return octAATPrepaid;
    }

    public void setOctAATPrepaid(String octAATPrepaid) {
        this.octAATPrepaid = octAATPrepaid;
    }

    public String getNovAATPrepaid() {
        return novAATPrepaid;
    }

    public void setNovAATPrepaid(String novAATPrepaid) {
        this.novAATPrepaid = novAATPrepaid;
    }

    public String getDecAATPrepaid() {
        return decAATPrepaid;
    }

    public void setDecAATPrepaid(String decAATPrepaid) {
        this.decAATPrepaid = decAATPrepaid;
    }

    public String getBeforeAATPrepaid() {
        return beforeAATPrepaid;
    }

    public void setBeforeAATPrepaid(String beforeAATPrepaid) {
        this.beforeAATPrepaid = beforeAATPrepaid;
    }

    public String getThisAATPrepaid() {
        return thisAATPrepaid;
    }

    public void setThisAATPrepaid(String thisAATPrepaid) {
        this.thisAATPrepaid = thisAATPrepaid;
    }

    public String getTotalAATPrepaid() {
        return totalAATPrepaid;
    }

    public void setTotalAATPrepaid(String totalAATPrepaid) {
        this.totalAATPrepaid = totalAATPrepaid;
    }

    public String getPrepaid() {
        return prepaid;
    }

    public void setPrepaid(String prepaid) {
        this.prepaid = prepaid;
    }

    public String getPrepaidName() {
        return prepaidName;
    }

    public void setPrepaidName(String prepaidName) {
        this.prepaidName = prepaidName;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }
    //合作方名称
    private String partnerNm;
    //是否大客户
    private String bigCustomerFlagStr;
    //大客户名称
    private String bigCustomerName;
	public String getPartnerNm() {
		return partnerNm;
	}

	public void setPartnerNm(String partnerNm) {
		this.partnerNm = partnerNm;
	}

	public String getBigCustomerFlagStr() {
		return bigCustomerFlagStr;
	}

	public void setBigCustomerFlagStr(String bigCustomerFlagStr) {
		this.bigCustomerFlagStr = bigCustomerFlagStr;
	}

	public String getBigCustomerName() {
		return bigCustomerName;
	}

	public void setBigCustomerName(String bigCustomerName) {
		this.bigCustomerName = bigCustomerName;
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

    //收入类型
    private String incomeStatusStr;

    public String getIncomeStatusStr() {
        return incomeStatusStr;
    }

    public void setIncomeStatusStr(String incomeStatusStr) {
        this.incomeStatusStr = incomeStatusStr;
    }

    public String getStoreSize() {
        return storeSize;
    }

    public void setStoreSize(String storeSize) {
        this.storeSize = storeSize;
    }

    public String getPreBack() {
        return preBack;
    }

    public void setPreBack(String preBack) {
        this.preBack = preBack;
    }
}