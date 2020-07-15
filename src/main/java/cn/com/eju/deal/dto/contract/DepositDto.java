package cn.com.eju.deal.dto.contract;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.eju.deal.core.model.BaseModel;
/**
 * 
 * <p>保证金信息Dto。</p> 
 * 
 * @version 1.0
 * @author zhanghuan <br />
 * @date 日期 <br />
 * 修改履历 <br />
 *  日期 : 姓名: 修改内容<br />
 */
public class DepositDto extends BaseModel {

    /** 请记述Field的功能。 */
    private static final long serialVersionUID = 8294111531930781634L;

    /**唯一标示:ID**/
    private Integer           id;

    /**合同编码**/
    private String            contractNo;

    /**合用类型**/
    private String            contractType;

    /**合同状态**/
    private String            contractState;

    /**公司名称**/
    private String            companyName;

    /**应收金额**/
    private BigDecimal        itemAmount;

    /**已收金额**/
    private BigDecimal        receivedAmount;

    /**未收金额**/
    private BigDecimal        uncollectAmount;

    /**收款状态**/
    private Integer           receiveState;

    /**操作人**/
    private Integer userIdCreate;

    /**系统提交日期**/
    private Date dateCreate;

    /**更新日期**/
    private Date dateUpdate;

    /**更新提交人**/
    private Integer updateCreate;
    
    /**
     * 合作协议书编号
     */
    private String agreementNo;
    /**
     *  城市No
     */
    private String cityNo;
    
    private Boolean bDelFlag;

    /**
     * @return  the 【bDelFlag】
     */
    public Boolean getbDelFlag() {
    
        return bDelFlag;
    }

    /**
     * @param bDelFlag the 【bDelFlag】 to set
     */
    public void setbDelFlag(Boolean bDelFlag) {
    
        this.bDelFlag = bDelFlag;
    }

    public String getCityNo() {
		return cityNo;
	}

	public void setCityNo(String cityNo) {
		this.cityNo = cityNo;
	}

	public String getAgreementNo()
    {
        return agreementNo;
    }

    public void setAgreementNo(String agreementNo)
    {
        this.agreementNo = agreementNo;
    }


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

        this.contractNo = contractNo == null ? null : contractNo.trim();
    }


    public String getContractType() {

        return contractType;
    }


    public void setContractType(String contractType) {

        this.contractType = contractType == null ? null : contractType.trim();
    }


    public String getContractState() {

        return contractState;
    }


    public void setContractState(String contractState) {

        this.contractState = contractState == null ? null : contractState.trim();
    }


    public String getCompanyName() {

        return companyName;
    }


    public void setCompanyName(String companyName) {

        this.companyName = companyName == null ? null : companyName.trim();
    }




    
    /**
     * <p>取得itemAmount。</p>
     *
     * @return itemAmount
     */
    public BigDecimal getItemAmount() {
    
        return itemAmount;
    }


    
    /**
     * <p>取得itemAmount。</p>
     *
     * @param itemAmount itemAmount
     */
    public void setItemAmount(BigDecimal itemAmount) {
    
        this.itemAmount = itemAmount;
    }


    
    /**
     * <p>取得receivedAmount。</p>
     *
     * @return receivedAmount
     */
    public BigDecimal getReceivedAmount() {
    
        return receivedAmount;
    }


    
    /**
     * <p>取得receivedAmount。</p>
     *
     * @param receivedAmount receivedAmount
     */
    public void setReceivedAmount(BigDecimal receivedAmount) {
    
        this.receivedAmount = receivedAmount;
    }


    
    /**
     * <p>取得uncollectAmount。</p>
     *
     * @return uncollectAmount
     */
    public BigDecimal getUncollectAmount() {
    
        return uncollectAmount;
    }


    
    /**
     * <p>取得uncollectAmount。</p>
     *
     * @param uncollectAmount uncollectAmount
     */
    public void setUncollectAmount(BigDecimal uncollectAmount) {
    
        this.uncollectAmount = uncollectAmount;
    }


    public Integer getReceiveState() {

        return receiveState;
    }


    public void setReceiveState(Integer receiveState) {

        this.receiveState = receiveState;
    }


    public Integer getUserIdCreate() {

        return userIdCreate;
    }


    public void setUserIdCreate(Integer userIdCreate) {

        this.userIdCreate = userIdCreate;
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


    public Integer getUpdateCreate() {

        return updateCreate;
    }


    public void setUpdateCreate(Integer updateCreate) {

        this.updateCreate = updateCreate;
    }

}