package cn.com.eju.deal.model.followMap;

/**
 * desc:问卷调查记录 entity
 * @author :zhenggang.Huang
 * @date   :2019年7月1日
 */
public class WjdcRecordDto {
    private Integer id;//Kefu_Wj_Satisfaction id
    
    private Integer storeId;//门店id
    
    private String wjdcjd;//调查时间 -季度
    
    private String storeStatus;//门店现状 (营业24801、停业、歇业、换牌、转让、改行、迁址)
    
    private String storeStatusStr;//门店现状 (营业24801、停业、歇业、换牌、转让、改行、迁址)
    
    private String wjdcTotalscore;//用户评分

	private String cpTotalScore;//测评总分
    
	public String getStoreStatusStr() {
		return storeStatusStr;
	}
	public void setStoreStatusStr(String storeStatusStr) {
		this.storeStatusStr = storeStatusStr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getWjdcjd() {
		return wjdcjd;
	}
	public void setWjdcjd(String wjdcjd) {
		this.wjdcjd = wjdcjd;
	}
	public String getStoreStatus() {
		return storeStatus;
	}
	public void setStoreStatus(String storeStatus) {
		this.storeStatus = storeStatus;
	}
	public String getWjdcTotalscore() {
		return wjdcTotalscore;
	}
	public void setWjdcTotalscore(String wjdcTotalscore) {
		this.wjdcTotalscore = wjdcTotalscore;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getCpTotalScore() {
		return cpTotalScore;
	}

	public void setCpTotalScore(String cpTotalScore) {
		this.cpTotalScore = cpTotalScore;
	}
}
