package cn.com.eju.deal.houseLinkage.report.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * desc:推送合同信息到房友实体类
 * @author :zhenggang.Huang
 * @date   :2019年2月20日
 */
public class SendContractDataToFy implements Serializable
{

    /**
	 * 
	 */
	private static final long serialVersionUID = -2917007601946550543L;

    /**
     * 合同编号
     */
    private String  contract_no;

    /**
     * 合同状态（1-审核通过）
     */
    private Integer status;

    /**
     * 合同审核通过时间
     */
    private String contract_audit_time;

    /**
     * 公司编号
     */
    private String company_no;

    /**
     * 公司名称
     */
    private String company_name;

    /**
     * 费用
     */
    private BigDecimal amount;

	public String getContract_no() {
		return contract_no;
	}

	public void setContract_no(String contract_no) {
		this.contract_no = contract_no;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getContract_audit_time() {
		return contract_audit_time;
	}

//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	public void setContract_audit_time(String contract_audit_time) {
		this.contract_audit_time = contract_audit_time;
	}

	public String getCompany_no() {
		return company_no;
	}

	public void setCompany_no(String company_no) {
		this.company_no = company_no;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
