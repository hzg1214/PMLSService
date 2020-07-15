package cn.com.eju.deal.otherReport.dto;

import cn.com.eju.deal.otherReport.model.LnkQtLog;

public class QtLogDto extends LnkQtLog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6020070971032205714L;
	
	private String logUserCode;
	
	private String logUserName;

	public String getLogUserCode() {
		return logUserCode;
	}

	public void setLogUserCode(String logUserCode) {
		this.logUserCode = logUserCode;
	}

	public String getLogUserName() {
		return logUserName;
	}

	public void setLogUserName(String logUserName) {
		this.logUserName = logUserName;
	}
	
}