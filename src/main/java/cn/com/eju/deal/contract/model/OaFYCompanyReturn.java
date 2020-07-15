/**
 *OA房友公司返回结果DTO 
 */
package cn.com.eju.deal.contract.model;

import java.util.Date;

public class OaFYCompanyReturn
{
    // 主键
    private Integer id;
    
    // 合同编号
    private String contractNo;
    
    // OA单查询key
    private String oaCode;
    
	//OA审核日期
    private Date oaPerformDate;

    // 提交状态（ 0:未提交； 1:已提交）
    private Integer hasDeal;
    
    //创建时间
    private Date dateCreate;
    
	//更新时间
    private Date dateUpdate;

    // 未提交原因
    private String reason;

    // 合同Id
    private Integer contractId;

	// 公司Id
    private Integer companyId;
    
    // 公司编号
    private String companyNo;

    // 公司名称
	private String companyName;
	
    // 城市编号
	private String cityNo;	
	
	// 区域编号
	private String districtNo;
	
	// 板块编号
	private String areaNo;
	
	// 公司地址
	private String companyAddr;

	// 联系人
	private String linkMan;
	
	// 联系电话
	private String linkPhone;

	// 房友公司编号
    private String fangyouCompanyId;
    
	// 经度
    private String longitude;
    
	// 纬度
    private String latitude;

    // 房友账号开通状态（0:未开通 1:已开通)
    private String fangyouOpenStatus;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContractNo() {
		return contractNo;
	}

	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}

	public String getOaCode() {
		return oaCode;
	}

	public void setOaCode(String oaCode) {
		this.oaCode = oaCode;
	}

	public Date getOaPerformDate() {
		return oaPerformDate;
	}

	public void setOaPerformDate(Date oaPerformDate) {
		this.oaPerformDate = oaPerformDate;
	}

	public Integer getHasDeal() {
		return hasDeal;
	}

	public void setHasDeal(Integer hasDeal) {
		this.hasDeal = hasDeal;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

    public Integer getContractId() {
		return contractId;
	}

	public void setContractId(Integer contractId) {
		this.contractId = contractId;
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

	public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getDistrictNo() {
		return districtNo;
	}

	public void setDistrictNo(String districtNo) {
		this.districtNo = districtNo;
	}

	public String getAreaNo() {
		return areaNo;
	}

	public void setAreaNo(String areaNo) {
		this.areaNo = areaNo;
	}

	public String getCompanyAddr() {
		return companyAddr;
	}

	public void setCompanyAddr(String companyAddr) {
		this.companyAddr = companyAddr;
	}

	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getLinkPhone() {
		return linkPhone;
	}

	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getFangyouCompanyId() {
		return fangyouCompanyId;
	}

	public void setFangyouCompanyId(String fangyouCompanyId) {
		this.fangyouCompanyId = fangyouCompanyId;
	}

	public String getFangyouOpenStatus() {
		return fangyouOpenStatus;
	}

	public void setFangyouOpenStatus(String fangyouOpenStatus) {
		this.fangyouOpenStatus = fangyouOpenStatus;
	}

}