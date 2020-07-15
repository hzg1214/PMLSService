package cn.com.eju.deal.dto.open;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class DepositRefundDto implements Serializable
{
    /**
    * TODO(用一句话描述这个变量表示什么)
    */ 
    private static final long serialVersionUID = 8519751093142521897L;

    //剩余未分账保证金
    private BigDecimal restSplitDepositFee;
    
    //门店已退保证金状态
    private List<DeopositStore>  depositList;

    public BigDecimal getRestSplitDepositFee()
    {
        return restSplitDepositFee;
    }

    public void setRestSplitDepositFee(BigDecimal restSplitDepositFee)
    {
        this.restSplitDepositFee = restSplitDepositFee;
    }

    public List<DeopositStore> getDepositList()
    {
        return depositList;
    }

    public void setDepositList(List<DeopositStore> depositList)
    {
        this.depositList = depositList;
    }
    
}
