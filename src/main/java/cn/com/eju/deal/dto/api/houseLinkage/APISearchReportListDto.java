package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;

public class APISearchReportListDto  implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	// 城市NO
	private String CityNo;
	// 城市名
	private String City;
	// 区域名
	private String District;
	// 楼盘类型
	private String BuildType;
	// 最新进度
	private String LatestProgress;
	// 确认状态
	private String ConfirmStatus;
	// 日期类型
	private String DateTypeKbn;
	// 日期区间
	private String DateType;
	// 开始时间
	private String DateMin;
	// 结束时间
	private String DateMax;
	// 查询内容
	private String SearchContent;
	// 公司编号
	private String CompanyId;
	// 来源
	private String Source;
	// 查询客户名
	private String CustomerNmWeiXin;
	// 员工ID（微信号）
	private String EmpId;
	
	private String EstateId;//楼盘编号

	public String getCityNo() {
		return CityNo;
	}
	public void setCityNo(String cityNo) {
		CityNo = cityNo;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getDistrict() {
		return District;
	}
	public void setDistrict(String district) {
		District = district;
	}
	public String getBuildType() {
		return BuildType;
	}
	public void setBuildType(String buildType) {
		BuildType = buildType;
	}
	public String getLatestProgress() {
		return LatestProgress;
	}
	public void setLatestProgress(String latestProgress) {
		LatestProgress = latestProgress;
	}
	public String getConfirmStatus() {
		return ConfirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		ConfirmStatus = confirmStatus;
	}
	public String getDateTypeKbn() {
		return DateTypeKbn;
	}
	public void setDateTypeKbn(String dateTypeKbn) {
		DateTypeKbn = dateTypeKbn;
	}
	public String getDateType() {
		return DateType;
	}
	public void setDateType(String dateType) {
		DateType = dateType;
	}
	public String getDateMin() {
		return DateMin;
	}
	public void setDateMin(String dateMin) {
		DateMin = dateMin;
	}
	public String getDateMax() {
		return DateMax;
	}
	public void setDateMax(String dateMax) {
		DateMax = dateMax;
	}
	public String getSearchContent() {
		return SearchContent;
	}
	public void setSearchContent(String searchContent) {
		SearchContent = searchContent;
	}
	public String getCompanyId() {
		return CompanyId;
	}
	public void setCompanyId(String companyId) {
		CompanyId = companyId;
	}
	public String getSource() {
		return Source;
	}
	public void setSource(String source) {
		Source = source;
	}
	public String getCustomerNmWeiXin() {
		return CustomerNmWeiXin;
	}
	public void setCustomerNmWeiXin(String customerNmWeiXin) {
		CustomerNmWeiXin = customerNmWeiXin;
	}
	public String getEmpId() {
		return EmpId;
	}
	public void setEmpId(String empId) {
		EmpId = empId;
	}
    /**
     * @return the estateId
     */
    public String getEstateId()
    {
        return EstateId;
    }
    /**
     * @param estateId the estateId to set
     */
    public void setEstateId(String estateId)
    {
        EstateId = estateId;
    }
	
}