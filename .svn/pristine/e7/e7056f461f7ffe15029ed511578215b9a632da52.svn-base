package cn.com.eju.deal.otherReport.dto;

import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.deal.houseLinkage.estate.model.Estate;
import cn.com.eju.deal.otherReport.model.LnkQtReport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * desc:查看详情  dto
 * @author :zhenggang.Huang
 * @date   :2019年10月15日
 */
public class QtReportDto extends LnkQtReport implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1274881265870207957L;
	
	private String reportStatusName;//最新进度
	
	private String srTypeName;//收入类型
	
	private String ackStatusName;//确认状态
	
	private List<QtReportDetailDto> qtReportDetailList;//进度
	
	private List<QtLogDto> qtLogList;//日志	
	
	private Estate estate;//项目

	private BigDecimal befYJSSAmount;

	private BigDecimal aftYJSSAmount;

	private BigDecimal befSJFYAmount;

	private BigDecimal aftSJFYAmount;

	private String brokerageUserName;//结佣姓名
	
	private String brokerageUserCode;//结佣工号
	
	private List<FileRecordMainDto> fileList;

    public List<FileRecordMainDto> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileRecordMainDto> fileList) {
        this.fileList = fileList;
    }
	
	public String getBrokerageUserName() {
		return brokerageUserName;
	}

	public void setBrokerageUserName(String brokerageUserName) {
		this.brokerageUserName = brokerageUserName;
	}

	public String getBrokerageUserCode() {
		return brokerageUserCode;
	}

	public void setBrokerageUserCode(String brokerageUserCode) {
		this.brokerageUserCode = brokerageUserCode;
	}

	public String getReportStatusName() {
		return reportStatusName;
	}

	public void setReportStatusName(String reportStatusName) {
		this.reportStatusName = reportStatusName;
	}

	public String getSrTypeName() {
		return srTypeName;
	}

	public void setSrTypeName(String srTypeName) {
		this.srTypeName = srTypeName;
	}

	public String getAckStatusName() {
		return ackStatusName;
	}

	public void setAckStatusName(String ackStatusName) {
		this.ackStatusName = ackStatusName;
	}

	public Estate getEstate() {
		return estate;
	}

	public void setEstate(Estate estate) {
		this.estate = estate;
	}

	public List<QtLogDto> getQtLogList() {
		return qtLogList;
	}

	public void setQtLogList(List<QtLogDto> qtLogList) {
		this.qtLogList = qtLogList;
	}

	public List<QtReportDetailDto> getQtReportDetailList() {
		return qtReportDetailList;
	}

	public void setQtReportDetailList(List<QtReportDetailDto> qtReportDetailList) {
		this.qtReportDetailList = qtReportDetailList;
	}

	public BigDecimal getBefYJSSAmount() {
		return befYJSSAmount;
	}

	public void setBefYJSSAmount(BigDecimal befYJSSAmount) {
		this.befYJSSAmount = befYJSSAmount;
	}

	public BigDecimal getAftYJSSAmount() {
		return aftYJSSAmount;
	}

	public void setAftYJSSAmount(BigDecimal aftYJSSAmount) {
		this.aftYJSSAmount = aftYJSSAmount;
	}

	public BigDecimal getBefSJFYAmount() {
		return befSJFYAmount;
	}

	public void setBefSJFYAmount(BigDecimal befSJFYAmount) {
		this.befSJFYAmount = befSJFYAmount;
	}

	public BigDecimal getAftSJFYAmount() {
		return aftSJFYAmount;
	}

	public void setAftSJFYAmount(BigDecimal aftSJFYAmount) {
		this.aftSJFYAmount = aftSJFYAmount;
	}

}