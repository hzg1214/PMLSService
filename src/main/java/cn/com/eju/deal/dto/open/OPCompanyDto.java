package cn.com.eju.deal.dto.open;

import java.io.Serializable;
import java.util.Date;

/**   
* 公司模型
* @author wenhui.zhang
* @date 2016年12月21日 上午10:30:37
*/
public class OPCompanyDto implements Serializable{
    /**
    * 序列化
    */ 
    private static final long serialVersionUID = 1L;

    private Integer id;

    /*
     * 公司编号
     */
    private String companyNo;

    /*
     * 公司名称
     */
    private String companyName;

    /*
     * 所属城市编号
     */
    private String cityNo;

    /*
     * 所属区域编号
     */
    private String districtNo;

    /*
     * 所属板块编号
     */
    private String areaNo;

    /*
     * 所属城市名称
     */
    private String cityName;

    /*
     * 所属区域名称
     */
    private String districtName;

    /*
     * 所属板块名称
     */
    private String areaName;

    /*
     * 公司地址
     */
    private String companyAddr;

    /*
     * 联系人
     */
    private String linkMan;

    /*
     * 联系电话
     */
    private String linkPhone;

    /* 
     * 来自公盘公司关系表 页面显示用
     * 审批状态: 0.无状态 18501.待审批 18502.审批通过 18503.审批不通过
     */
    private Integer approveState;

    /*
     * 账号状态: 18801.无状态 18802.正常 18803.停用 18804.移除
     */
    private Integer accountState;

    private Date dateUpdate;

    private Integer userIdUpdate;

    private String userNameUpdate;

    private Date dateCreate;

    private Integer userIdCreate;

    private String userNameCreate;

    private Boolean delFlag;

    /*
     * 房友公司ID
     */
    private String fyCompanyId;
    
    /*
     * 公司经度
     */
    private String longitude;
    
    /*
     * 公司纬度
     */
    private String latitude;
    
    /*
     * 来自公盘公司关系表 页面显示用
     * 公盘申请类型: 18701.申请加入 18702.申请退出
     */
    private Integer poApplyType;
    
    /*
     * 来自公盘表 页面显示用
     */
    private Integer publicOfferId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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

	public Integer getApproveState() {
		return approveState;
	}

	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	public Integer getAccountState() {
		return accountState;
	}

	public void setAccountState(Integer accountState) {
		this.accountState = accountState;
	}

	public Date getDateUpdate() {
		return dateUpdate;
	}

	public void setDateUpdate(Date dateUpdate) {
		this.dateUpdate = dateUpdate;
	}

	public Integer getUserIdUpdate() {
		return userIdUpdate;
	}

	public void setUserIdUpdate(Integer userIdUpdate) {
		this.userIdUpdate = userIdUpdate;
	}

	public String getUserNameUpdate() {
		return userNameUpdate;
	}

	public void setUserNameUpdate(String userNameUpdate) {
		this.userNameUpdate = userNameUpdate;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Integer getUserIdCreate() {
		return userIdCreate;
	}

	public void setUserIdCreate(Integer userIdCreate) {
		this.userIdCreate = userIdCreate;
	}

	public String getUserNameCreate() {
		return userNameCreate;
	}

	public void setUserNameCreate(String userNameCreate) {
		this.userNameCreate = userNameCreate;
	}

	public Boolean getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Boolean delFlag) {
		this.delFlag = delFlag;
	}

	public String getFyCompanyId() {
		return fyCompanyId;
	}

	public void setFyCompanyId(String fyCompanyId) {
		this.fyCompanyId = fyCompanyId;
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

	public Integer getPoApplyType() {
		return poApplyType;
	}

	public void setPoApplyType(Integer poApplyType) {
		this.poApplyType = poApplyType;
	}

	public Integer getPublicOfferId() {
		return publicOfferId;
	}

	public void setPublicOfferId(Integer publicOfferId) {
		this.publicOfferId = publicOfferId;
	}
}