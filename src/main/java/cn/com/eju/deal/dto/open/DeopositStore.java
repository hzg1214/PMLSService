package cn.com.eju.deal.dto.open;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
* 门店保证金数据
* @author wushuang
* @date 2016年11月18日 上午11:36:41
 */

public class DeopositStore  implements Serializable
{
    /**
    * TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 4149121553134701149L;
    //门店编号
    private String storeNo;
    //门店店招
    private String name;
    //门店地址
    private String addressDetail;

    //每家门店应收保证金
    private BigDecimal depositFee;
    
    //门店已退保证金
    private BigDecimal refundAmount;

    //门店退款状态
    private String refundState;
    
    //门店退款状态
    private String refundStateName;
    
    //最后退款时间
    private String lastRefundDate;
    
    //剩余未分账保证金
    private BigDecimal restSplitDepositFee;
    

    public String getLastRefundDate()
    {
        return lastRefundDate;
    }
    public void setLastRefundDate(String lastRefundDate)
    {
        this.lastRefundDate = lastRefundDate;
    }
    public String getAddressDetail()
    {
        return addressDetail;
    }
    public void setAddressDetail(String addressDetail)
    {
        this.addressDetail = addressDetail;
    }
    public BigDecimal getRefundAmount()
    {
        return refundAmount;
    }
    public void setRefundAmount(BigDecimal refundAmount)
    {
        this.refundAmount = refundAmount;
    }
    public String getRefundState()
    {
        return refundState;
    }
    public void setRefundState(String refundState)
    {
        this.refundState = refundState;
    }
    public String getRefundStateName()
    {
        return refundStateName;
    }
    public void setRefundStateName(String refundStateName)
    {
        this.refundStateName = refundStateName;
    }
    public BigDecimal getRestSplitDepositFee()
    {
        return restSplitDepositFee;
    }
    public void setRestSplitDepositFee(BigDecimal restSplitDepositFee)
    {
        this.restSplitDepositFee = restSplitDepositFee;
    }
    public String getStoreNo()
    {
        return storeNo;
    }
    public void setStoreNo(String storeNo)
    {
        this.storeNo = storeNo;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public BigDecimal getDepositFee()
    {
        return depositFee;
    }
    public void setDepositFee(BigDecimal depositFee)
    {
        this.depositFee = depositFee;
    }

    
}
