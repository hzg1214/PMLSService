package cn.com.eju.deal.scene.commission.model;

/**
 * 
 * desc:佣金管理对象  查看返回数据-返回返佣对象
 * @author :zhenggang.Huang
 * @date   :2019年4月30日
 */
public class CompanyEntity {
    private String companyName;
    private String companyNo;
    private Integer defaultFlag;
    private Integer isDb;//编辑页面标识
    
	public Integer getIsDb() {
		return isDb;
	}
	public void setIsDb(Integer isDb) {
		this.isDb = isDb;
	}
	public Integer getDefaultFlag() {
		return defaultFlag;
	}
	public void setDefaultFlag(Integer defaultFlag) {
		this.defaultFlag = defaultFlag;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}
    
}
