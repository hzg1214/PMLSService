package cn.com.eju.deal.dto.houseLinkage.estate;

import java.math.BigDecimal;

/**
 * 
 * desc:垫佣规则变更entity
 * @date   :2020年6月10日
 */
public class MattressControlRuleDetailDto extends MattressControlRule{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8169970091807915168L;
	//加上百分号比例
	private BigDecimal dyRatioPercent;

	public BigDecimal getDyRatioPercent() {
		return dyRatioPercent;
	}

	public void setDyRatioPercent(BigDecimal dyRatioPercent) {
		this.dyRatioPercent = dyRatioPercent;
	}

}