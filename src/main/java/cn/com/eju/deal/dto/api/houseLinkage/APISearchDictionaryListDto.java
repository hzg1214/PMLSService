package cn.com.eju.deal.dto.api.houseLinkage;

import java.io.Serializable;
import java.util.Date;

import cn.com.eju.deal.base.support.SystemParam;

public class APISearchDictionaryListDto  implements Serializable {
	
	private static final long serialVersionUID = 268231807297165902L;
	
	// 字典表类型Id
	private String TypeId;

	public String getTypeId() {
		return TypeId;
	}

	public void setTypeId(String typeId) {
		TypeId = typeId;
	}
	
	
}