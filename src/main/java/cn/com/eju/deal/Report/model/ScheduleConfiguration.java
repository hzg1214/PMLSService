package cn.com.eju.deal.Report.model;

import cn.com.eju.deal.core.model.BaseModel;

public class ScheduleConfiguration extends BaseModel  {
	
	private Integer id;
	  
	//调度ID
	private Integer scheduleId;
	
	
	//sql语句中查询列值 (key)
	private String sqlRow;
	
	//是否写入XLS中 0 不写入 1 写入  默认0
	private Integer isExport;
	
	
	//对用列值的 的内容 (value)
	private String fieldContent;
	
	//排序
	private String fieldSort;
	
	//内容类型  0 String  1:number 2:时间
	private Integer fieldType;
	
	//内容类型的格式 
	private String fieldTypeRule;
	
	//更新标记（表示当前第几次更新）
	private Integer updateMark;
	
	//老的标记时间
	private Integer oldUpdateMark;
	
	//参数
	private String jsonPcf;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getSqlRow() {
		return sqlRow;
	}

	public void setSqlRow(String sqlRow) {
		this.sqlRow = sqlRow;
	}

	public Integer getIsExport() {
		return isExport;
	}

	public void setIsExport(Integer isExport) {
		this.isExport = isExport;
	}

	public String getFieldContent() {
		return fieldContent;
	}

	public void setFieldContent(String fieldContent) {
		this.fieldContent = fieldContent;
	}

	public String getFieldSort() {
		return fieldSort;
	}

	public void setFieldSort(String fieldSort) {
		this.fieldSort = fieldSort;
	}

	public Integer getFieldType() {
		return fieldType;
	}

	public void setFieldType(Integer fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldTypeRule() {
		return fieldTypeRule;
	}

	public void setFieldTypeRule(String fieldTypeRule) {
		this.fieldTypeRule = fieldTypeRule;
	}

	public Integer getUpdateMark() {
		return updateMark;
	}

	public void setUpdateMark(Integer updateMark) {
		this.updateMark = updateMark;
	}

	public String getJsonPcf() {
		return jsonPcf;
	}

	public void setJsonPcf(String jsonPcf) {
		this.jsonPcf = jsonPcf;
	}

	public Integer getOldUpdateMark() {
		return oldUpdateMark;
	}

	public void setOldUpdateMark(Integer oldUpdateMark) {
		this.oldUpdateMark = oldUpdateMark;
	}
	
	
	
	
	
	
	

}
