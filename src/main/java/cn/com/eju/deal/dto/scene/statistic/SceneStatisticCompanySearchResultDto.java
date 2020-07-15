package cn.com.eju.deal.dto.scene.statistic;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class SceneStatisticCompanySearchResultDto implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	// 楼盘ID
	private String estateId;
	// 报备编号
	private String reportId;
	// 公司编码
	private String companyId;
	// 公司名
	private String companyNm;
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
    private String  roughAreaSum;
    //大定总金额
    private String  roughAmountSum;
    //成销总面积
    private String  dealAreaSum;
    //成销总金额
    private String  dealAmountSum;
	
	/**
     * @return the roughAreaSum
     */
    public String getRoughAreaSum()
    {
        return roughAreaSum;
    }
    /**
     * @param roughAreaSum the roughAreaSum to set
     */
    public void setRoughAreaSum(String roughAreaSum)
    {
        this.roughAreaSum = roughAreaSum;
    }
    /**
     * @return the roughAmountSum
     */
    public String getRoughAmountSum()
    {
        return roughAmountSum;
    }
    /**
     * @param roughAmountSum the roughAmountSum to set
     */
    public void setRoughAmountSum(String roughAmountSum)
    {
        this.roughAmountSum = roughAmountSum;
    }
    /**
     * @return the dealAreaSum
     */
    public String getDealAreaSum()
    {
        return dealAreaSum;
    }
    /**
     * @param dealAreaSum the dealAreaSum to set
     */
    public void setDealAreaSum(String dealAreaSum)
    {
        this.dealAreaSum = dealAreaSum;
    }
    /**
     * @return the dealAmountSum
     */
    public String getDealAmountSum()
    {
        return dealAmountSum;
    }
    /**
     * @param dealAmountSum the dealAmountSum to set
     */
    public void setDealAmountSum(String dealAmountSum)
    {
        this.dealAmountSum = dealAmountSum;
    }
	

	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
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
    
	
}