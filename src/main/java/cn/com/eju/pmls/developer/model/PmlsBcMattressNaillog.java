package cn.com.eju.pmls.developer.model;

/**
 * desc:修改大客户和垫佣甲方记录日志
 * @date   :2019年11月6日
 */
public class PmlsBcMattressNaillog {
	private Integer id;
	private String businessType;
	private String businessNo;
	private String businessNm;
	private Integer oldBigCustomerFlag;	//是否是大客户(22601:是 22602：否)
	private Integer oldBigCustomerId;
	private String oldBigCustomerName;
	private Integer bigCustomerFlag;
	private Integer bigCustomerId;
	private String bigCustomerName;
	private Integer oldIsYjDy;			//是否垫佣甲方(0,1) 
	private Integer oldMattressNailId;
	private String oldMattressNailName;
	private Integer isYjDy;				
	private Integer mattressNailId;
	private String mattressNailName;
	private String descMemo;  //预留字段  暂不拼接
	private String createDate;
	private String createUserCode;
	private String delFlag;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getBusinessNo() {
		return businessNo;
	}
	public void setBusinessNo(String businessNo) {
		this.businessNo = businessNo;
	}
	public String getBusinessNm() {
		return businessNm;
	}
	public void setBusinessNm(String businessNm) {
		this.businessNm = businessNm;
	}
	public Integer getOldBigCustomerFlag() {
		return oldBigCustomerFlag;
	}
	public void setOldBigCustomerFlag(Integer oldBigCustomerFlag) {
		this.oldBigCustomerFlag = oldBigCustomerFlag;
	}
	public Integer getOldBigCustomerId() {
		return oldBigCustomerId;
	}
	public void setOldBigCustomerId(Integer oldBigCustomerId) {
		this.oldBigCustomerId = oldBigCustomerId;
	}
	public String getOldBigCustomerName() {
		return oldBigCustomerName;
	}
	public void setOldBigCustomerName(String oldBigCustomerName) {
		this.oldBigCustomerName = oldBigCustomerName;
	}
	public Integer getBigCustomerFlag() {
		return bigCustomerFlag;
	}
	public void setBigCustomerFlag(Integer bigCustomerFlag) {
		this.bigCustomerFlag = bigCustomerFlag;
	}
	public Integer getBigCustomerId() {
		return bigCustomerId;
	}
	public void setBigCustomerId(Integer bigCustomerId) {
		this.bigCustomerId = bigCustomerId;
	}
	public String getBigCustomerName() {
		return bigCustomerName;
	}
	public void setBigCustomerName(String bigCustomerName) {
		this.bigCustomerName = bigCustomerName;
	}
	public Integer getOldIsYjDy() {
		return oldIsYjDy;
	}
	public void setOldIsYjDy(Integer oldIsYjDy) {
		this.oldIsYjDy = oldIsYjDy;
	}
	public Integer getOldMattressNailId() {
		return oldMattressNailId;
	}
	public void setOldMattressNailId(Integer oldMattressNailId) {
		this.oldMattressNailId = oldMattressNailId;
	}
	public String getOldMattressNailName() {
		return oldMattressNailName;
	}
	public void setOldMattressNailName(String oldMattressNailName) {
		this.oldMattressNailName = oldMattressNailName;
	}
	public Integer getIsYjDy() {
		return isYjDy;
	}
	public void setIsYjDy(Integer isYjDy) {
		this.isYjDy = isYjDy;
	}
	public Integer getMattressNailId() {
		return mattressNailId;
	}
	public void setMattressNailId(Integer mattressNailId) {
		this.mattressNailId = mattressNailId;
	}
	public String getMattressNailName() {
		return mattressNailName;
	}
	public void setMattressNailName(String mattressNailName) {
		this.mattressNailName = mattressNailName;
	}
	public String getDescMemo() {
		return descMemo;
	}
	public void setDescMemo(String descMemo) {
		this.descMemo = descMemo;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getCreateUserCode() {
		return createUserCode;
	}
	public void setCreateUserCode(String createUserCode) {
		this.createUserCode = createUserCode;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	
    
}
