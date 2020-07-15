package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.model.ExpandDetail;
import cn.com.eju.deal.Report.service.StoreStopDetailServiceImpl;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * CRM报表后台
 * @author lizhenglin
 *
 */
@RestController
@RequestMapping(value="storeStopReport")
public class StoreStopReportController {

	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

	@Resource(name = "storeStopDetailService")
	private StoreStopDetailServiceImpl storeStopDetailService;

	/**
	 * 查询数据
	 * @param param  type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
	 * @return
	 */
	@RequestMapping(value = "queryStoreStopDetailList/{param}", method = RequestMethod.GET)
	public String queryStoreStopDetailList(@PathVariable String param) {

		logger.info("queryStoreStopDetailList param: " + param);
		// 获取请求参数
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

//		paramdata = ChangeParam(queryParam);
		ResultData<List<ExpandDetail>> resultData = new ResultData<>();

		int pageIndex = 1;
		int pageSize = 10;
		int curPage = 1;

		if (queryParam.get("pageIndex") != null)
			pageIndex = Integer.parseInt(queryParam.get("pageIndex").toString());
		if (queryParam.get("pageSize") != null)
			pageSize = Integer.parseInt(queryParam.get("pageSize").toString());
		if (queryParam.get("curPage") != null)
			curPage = Integer.parseInt(queryParam.get("curPage").toString());

		queryParam.remove("pageIndex");
		queryParam.remove("pageSize");
		queryParam.remove("curPage");

		List<ExpandDetail> list= storeStopDetailService.queryStoreStopDetailList(queryParam);
		if(list != null)
		{
			int size = list.size();
			resultData.setTotalCount(String.valueOf(size));

			int endRow = pageIndex * pageSize;
			list = list.subList((pageIndex - 1) * pageSize, endRow > size ? size : endRow);

			queryParam.put("pageIndex", pageIndex);
			queryParam.put("pageSize", pageSize);
			queryParam.put("curPage", curPage);

			resultData.setReturnData(list);
		}

		return resultData.toString();
	}

	/**
	 * 导出拓展明细报表
	 *
	 * @param param type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
	 * @return
	 */
	@RequestMapping(value = "exportStoreStopDetailList/{param}", method = RequestMethod.GET)
	public String exportStoreStopDetailList(@PathVariable String param) {
		System.out.println("exportStoreStopDetailList param: " + param);
		// 获取请求参数
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

		ResultData<List<ExpandDetail>> resultData = new ResultData<List<ExpandDetail>>();

		List<ExpandDetail> list= storeStopDetailService.queryStoreStopDetailList(queryParam);
		if(list != null)
		{
			int size = list.size();
			resultData.setTotalCount(String.valueOf(size));

			resultData.setReturnData(list);
		}

		return resultData.toString();
	}

}
