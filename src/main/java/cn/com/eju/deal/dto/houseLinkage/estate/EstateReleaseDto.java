package cn.com.eju.deal.dto.houseLinkage.estate;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * 
* @Title: EstateReleaseDto
* @Description: 变更城市与变更日志的封装类
* @author:zhangwanli    
* @date:2018年1月16日下午1:54:04
 */
public class EstateReleaseDto implements Serializable{
	private static final long serialVersionUID = -5775427213591322713L;
	
	private List<EstateReleaseCityDto> estateReleaseDtoList;
	// 变更历史
	private List<EstateChangeDto> estateChangeDetails;
	private String flag;
	private String estateId;
	public String getEstateId() {
		return estateId;
	}
	public void setEstateId(String estateId) {
		this.estateId = estateId;
	}
	public List<EstateReleaseCityDto> getEstateReleaseDtoList() {
		return estateReleaseDtoList;
	}
	public void setEstateReleaseDtoList(List<EstateReleaseCityDto> estateReleaseDtoList) {
		this.estateReleaseDtoList = estateReleaseDtoList;
	}
	public List<EstateChangeDto> getEstateChangeDetails() {
		return estateChangeDetails;
	}
	public void setEstateChangeDetails(List<EstateChangeDto> estateChangeDetails) {
		this.estateChangeDetails = estateChangeDetails;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}