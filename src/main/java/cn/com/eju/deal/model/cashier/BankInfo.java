package cn.com.eju.deal.model.cashier;

import cn.com.eju.deal.core.model.BaseModel;

/**
 * 银行表
 * @author tanlang
 *
 */
public class BankInfo extends BaseModel{

	private Integer id;
	/**
	 * 银行名称
	 * 
	 */
	private String bankName;
	/**
	 * 银行编码
	 */
	private String bankCode;

    /**
     * 银行卡持有人姓名
     */
    private String accountName;

    /**
     * 卡号
     */
    private String cardNo;

    private String cityNo;
    private String oaBankId;
    private String oaBankRemak1;

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOaBankId() {
        return oaBankId;
    }

    public void setOaBankId(String oaBankId) {
        this.oaBankId = oaBankId;
    }

    public String getOaBankRemak1() {
        return oaBankRemak1;
    }

    public void setOaBankRemak1(String oaBankRemak1) {
        this.oaBankRemak1 = oaBankRemak1;
    }
}
