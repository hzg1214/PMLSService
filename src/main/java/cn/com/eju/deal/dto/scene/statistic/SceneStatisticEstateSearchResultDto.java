package cn.com.eju.deal.dto.scene.statistic;

import java.io.Serializable;

public class SceneStatisticEstateSearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	 //Id add by QJP 2017-09-01
	private Integer Id;
	// 楼盘ID
	private String estateId;
	// 项目编码
	private String projectId;
	// 城市
	private String cityNo;
	private String cityNoNm;
	// 区域
	private String district;
	private String districtNm;
	// 楼盘类型
	private String estateType;
	private String estateTypeNm;
	// 楼盘名
	private String estateNm;
	// 总业绩
	private String totalPerformance;
	// 带看奖励
	private String relationReward;
	// 认筹奖励
	private String pledgedReward;
	// 认购奖励
	private String subscribedReward;
	// 成交奖励
	private String bargainReward;
	// 佣金
	private String commission;
	// 报备人数
	private String reportPeopleNum;
	// 带看人数
	private String relationPeopleNum;
	// 认筹人数
	private String pledgedPeopleNum;
	// 认购人数
	private String subscribedPeopleNum;
	// 成交人数
	private String signPeopleNum;
	// 退筹人数
	private String getBackPeopleNum;
	
	// 保存前页面查询条件
	// 统计时间区分
	private String countDateTypeKbn;
	// 统计时间开始
	private String countDateStart;
	// 统计时间结束
	private String countDateEnd;
	
	//大定总面积
	private String	roughAreaSum;
	//大定总金额
	private String	roughAmountSum;
	//成销总面积
	private String	dealAreaSum;
	//成销总金额
	private String	dealAmountSum;
	//公司Id	
	private String companyId;
	
	//项目所在城市编号
    private String realityCityno;
    
    //项目所在城市名称
    private String realityCityNm;
    //项目编号add by QJP 2017-09-01
    private String projectNo; 
    
    
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getRealityCityno()
    {
        return realityCityno;
    }
    public void setRealityCityno(String realityCityno)
    {
        this.realityCityno = realityCityno;
    }
    public String getRealityCityNm()
    {
        return realityCityNm;
    }
    public void setRealityCityNm(String realityCityNm)
    {
        this.realityCityNm = realityCityNm;
    }
    /**
     * @return the companyId
     */
    public String getCompanyId()
    {
        return companyId;
    }
    /**
     * @param companyId the companyId to set
     */
    public void setCompanyId(String companyId)
    {
        this.companyId = companyId;
    }
    public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getCityNo() {
		return cityNo;
	}
	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}
	public String getCityNoNm() {
		return cityNoNm;
	}
	public void setCityNoNm(String cityNoNm) {
		this.cityNoNm = cityNoNm;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getDistrictNm() {
		return districtNm;
	}
	public void setDistrictNm(String districtNm) {
		this.districtNm = districtNm;
	}
	public String getEstateType() {
		return estateType;
	}
	public void setEstateType(String estateType) {
		this.estateType = estateType;
	}
	public String getEstateTypeNm() {
		return estateTypeNm;
	}
	public void setEstateTypeNm(String estateTypeNm) {
		this.estateTypeNm = estateTypeNm;
	}
	public String getEstateNm() {
		return estateNm;
	}
	public void setEstateNm(String estateNm) {
		this.estateNm = estateNm;
	}
	public String getTotalPerformance() {
		return totalPerformance;
	}
	public void setTotalPerformance(String totalPerformance) {
		this.totalPerformance = totalPerformance;
	}
	public String getRelationReward() {
		return relationReward;
	}
	public void setRelationReward(String relationReward) {
		this.relationReward = relationReward;
	}
	public String getPledgedReward() {
		return pledgedReward;
	}
	public void setPledgedReward(String pledgedReward) {
		this.pledgedReward = pledgedReward;
	}
	public String getSubscribedReward() {
		return subscribedReward;
	}
	public void setSubscribedReward(String subscribedReward) {
		this.subscribedReward = subscribedReward;
	}
	public String getBargainReward() {
		return bargainReward;
	}
	public void setBargainReward(String bargainReward) {
		this.bargainReward = bargainReward;
	}
	public String getCommission() {
		return commission;
	}
	public void setCommission(String commission) {
		this.commission = commission;
	}
	public String getReportPeopleNum() {
		return reportPeopleNum;
	}
	public void setReportPeopleNum(String reportPeopleNum) {
		this.reportPeopleNum = reportPeopleNum;
	}
	public String getRelationPeopleNum() {
		return relationPeopleNum;
	}
	public void setRelationPeopleNum(String relationPeopleNum) {
		this.relationPeopleNum = relationPeopleNum;
	}
	public String getPledgedPeopleNum() {
		return pledgedPeopleNum;
	}
	public void setPledgedPeopleNum(String pledgedPeopleNum) {
		this.pledgedPeopleNum = pledgedPeopleNum;
	}
	public String getSubscribedPeopleNum() {
		return subscribedPeopleNum;
	}
	public void setSubscribedPeopleNum(String subscribedPeopleNum) {
		this.subscribedPeopleNum = subscribedPeopleNum;
	}
	public String getSignPeopleNum() {
		return signPeopleNum;
	}
	public void setSignPeopleNum(String signPeopleNum) {
		this.signPeopleNum = signPeopleNum;
	}
	public String getGetBackPeopleNum() {
		return getBackPeopleNum;
	}
	public void setGetBackPeopleNum(String getBackPeopleNum) {
		this.getBackPeopleNum = getBackPeopleNum;
	}
	public String getCountDateTypeKbn() {
		return countDateTypeKbn;
	}
	public void setCountDateTypeKbn(String countDateTypeKbn) {
		this.countDateTypeKbn = countDateTypeKbn;
	}
	public String getCountDateStart() {
		return countDateStart;
	}
	public void setCountDateStart(String countDateStart) {
		this.countDateStart = countDateStart;
	}
	public String getCountDateEnd() {
		return countDateEnd;
	}
	public void setCountDateEnd(String countDateEnd) {
		this.countDateEnd = countDateEnd;
	}
	public String getRoughAreaSum() {
		return roughAreaSum;
	}
	public void setRoughAreaSum(String roughAreaSum) {
		this.roughAreaSum = roughAreaSum;
	}
	public String getRoughAmountSum() {
		return roughAmountSum;
	}
	public void setRoughAmountSum(String roughAmountSum) {
		this.roughAmountSum = roughAmountSum;
	}
	public String getDealAreaSum() {
		return dealAreaSum;
	}
	public void setDealAreaSum(String dealAreaSum) {
		this.dealAreaSum = dealAreaSum;
	}
	public String getDealAmountSum() {
		return dealAmountSum;
	}
	public void setDealAmountSum(String dealAmountSum) {
		this.dealAmountSum = dealAmountSum;
	}
    
}