package cn.com.eju.deal.keFuWj.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * desc:问卷导入DTO
 * @author :zhenggang.Huang
 * @date   :2019年6月20日
 */
public class KeFuWjDetailDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3245077864037576595L;
	
	//问卷map
	private Map<String,Object> wjMap;
	//题目集合
	private List<Map<String, Object>> tmMapList;
	//答案集合
	private List<Map<String, Object>> daMapList;
	
	public Map<String, Object> getWjMap() {
		return wjMap;
	}
	public void setWjMap(Map<String, Object> wjMap) {
		this.wjMap = wjMap;
	}
	public List<Map<String, Object>> getTmMapList() {
		return tmMapList;
	}
	public void setTmMapList(List<Map<String, Object>> tmMapList) {
		this.tmMapList = tmMapList;
	}
	public List<Map<String, Object>> getDaMapList() {
		return daMapList;
	}
	public void setDaMapList(List<Map<String, Object>> daMapList) {
		this.daMapList = daMapList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
  