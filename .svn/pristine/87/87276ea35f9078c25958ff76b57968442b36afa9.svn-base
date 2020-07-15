package cn.com.eju.deal.Report.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.com.eju.deal.Report.model.ExpandCollect;
import cn.com.eju.deal.Report.model.ExpandDetail;

public interface IExpandDetailService {

	
	/**
	 * 拓展明细
	 * @param request  type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
	 * @param mop
	 * @return
	 */
	List<ExpandDetail> queryExpandDetailList(Map<String,Object> map);
	
	
	/**
	 * 拓展明细(导出)
	 * @param request  type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
	 * @param mop
	 * @return
	 */
	String exportExpandDetailList(Map<String,Object> map);
	
    void handExpandDetailExcelTask(Map<String, Object> map,String fileName, int id) ;
    
}
