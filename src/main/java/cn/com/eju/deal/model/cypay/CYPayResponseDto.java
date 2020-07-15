package cn.com.eju.deal.model.cypay;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/4/10 0010.
 */
public class CYPayResponseDto {
    private int resId;
    private String responseCode;//应答码(参考附录一)
    private String responseMsg;//应答信息
    private String traceNO;//跟踪号
    private int payer;
    private BigDecimal amount;//成功金额
    private int receiptId;//收款凭证号
    private int businessId;//业务编号
    private String toolCode;//支付工具代码
    private BigDecimal totalFee;//渠道手续费
    private String paySeq;//渠道流水号

    public int getPayer() {
        return payer;
    }

    public void setPayer(int payer) {
        this.payer = payer;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMsg() {
        return responseMsg;
    }

    public void setResponseMsg(String responseMsg) {
        this.responseMsg = responseMsg;
    }

    public String getTraceNO() {
        return traceNO;
    }

    public void setTraceNO(String traceNO) {
        this.traceNO = traceNO;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getToolCode() {
        return toolCode;
    }

    public void setToolCode(String toolCode) {
        this.toolCode = toolCode;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public String getPaySeq() {
        return paySeq;
    }

    public void setPaySeq(String paySeq) {
        this.paySeq = paySeq;
    }
}
