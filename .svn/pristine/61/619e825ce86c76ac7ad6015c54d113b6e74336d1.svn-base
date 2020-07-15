package cn.com.eju.deal.NoSigned.controller;

import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.NoSigned.model.NosignDetail;
import cn.com.eju.deal.NoSigned.service.NoSignDetailServiceImpl;



/**
 *
 * @author QJP
 *2017-06-26
 */
@RestController
@RequestMapping(value="noSignReport")
public class NoSignReportController {
	
	
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
	
	@Resource(name = "noSignDetailService")
	private NoSignDetailServiceImpl noSignDetailService;
				
	/**
	 * 查询未签门店数据
	 * @param request  type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
	 * @param mop
	 * @return
	 */
	@RequestMapping(value = "queryNoSignDetailList/{param}", method = RequestMethod.GET)
	public String queryNoSignDetailList(
			@PathVariable String param) {
		// 获取请求参数
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		//Map<String, Object> paramdata = new HashMap<String, Object>();
		
		//paramdata = ChangeParam(queryParam);
		ResultData<List<NosignDetail>> resultData = new ResultData<List<NosignDetail>>();
//		String city = "";
//		String dueCause = "";
//		String allCityId = "";
//		String storeCityList = "";
//		if(queryParam.containsKey("hcity") == true)
//		{
//			city = queryParam.get("hcity").toString();
//			if(city.equals("")==false)
//			{
//				String [] citytmp = city.split(",");
//				queryParam.put("citylist", citytmp);
//			}
//		}
//		// 合同结束原因
//		if(queryParam.containsKey("dueCause") == true)
//		{
//			dueCause = queryParam.get("dueCause").toString();
//			if(dueCause.equals("")==false)
//			{
//				String [] type = dueCause.split(",");
//				queryParam.put("dueCauselist", type);
//			}
//		}
//
//		//门店所属城市
//		if(queryParam.containsKey("storeCityList") == true)
//		{
//			storeCityList = queryParam.get("storeCityList").toString();
//			if(storeCityList.equals("")==false)
//			{
//				String [] citytmp = storeCityList.split(",");
//				queryParam.put("storeCityLists", citytmp);
//			}
//		}
//
//		//当前用户所有城市
//		if(queryParam.containsKey("allCityId") == true)
//		{
//			allCityId = queryParam.get("allCityId").toString();
//			if(allCityId.equals("")==false)
//			{
//				String [] citytmp = allCityId.split(",");
//				queryParam.put("allCityIdList", citytmp);
//			}
//		}
		List<NosignDetail> list= noSignDetailService.queryNoSignDetailList(queryParam);
		if(list != null)
		{
			resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
			resultData.setReturnData(list);
		}
		return resultData.toString();
	}	
	
	 /**
	 * 查询条件
	 * @param queryParam
	 */
//  	private Map<String, Object> ChangeParam(Map<String, Object> queryParam)
//  	{
//        String start = "";											//开始日期
//        String end = "";											//结束日期
//        int stage = 0;												//业务类型
//        String contracttype ="";									//合同类型
//        String city = "";											//城市
//  		String contractno = "";										//合同编号
//  		String storeno = "";										//门店编号
//  		String storeaddress = "";									//门店地址
//  		String company = "";										//经济公司
//		String group1 = "";											//区/事业部
//		String group2 = "";											//中心/事业部
//		String group3 = "";											//组
//  		
//  		
//		try {
//			
//			stage = Integer.parseInt(queryParam.get("stage").toString()); 
//			start = queryParam.get("inDate1").toString().replace('-', '/');
//			end = queryParam.get("inDate2").toString().replace('-', '/');
//			start = queryParam.get("inDate1").toString();
//			end = queryParam.get("inDate2").toString();
//			queryParam.put("inDate1", queryParam.get("inDate1").toString());
//			queryParam.put("inDate2", queryParam.get("inDate2").toString());
//			queryParam.put("createUserId", queryParam.get("createUserId").toString());
//			switch(stage)
//			{
//			case 1:													//草签日期
//				queryParam.put("DateCreateStart", start);
//				queryParam.put("DateCreateEnd", end);							
//				break;
//			case 2:													//OA审核通过日期
//				queryParam.put("PerformDateStart", start);
//				queryParam.put("PerformDateEnd", end);							
//				break;
//			case 3:													//翻牌验收通过日期
//				queryParam.put("flipPassDateStart", start);
//				queryParam.put("flipPassDateEnd", end);							
//				break;
//			case 4:													//翻牌完成日期
//				queryParam.put("flipCompleDateStart", start);
//				queryParam.put("flipCompleDateEnd", end);							
//				break;
//			case 5:													//保证金到账日期
//				queryParam.put("DateArrivalActStart", start);
//				queryParam.put("DateArrivalActEnd", end);							
//				break;
//			default:
//				//	
//			}
//			
//			//城市
//			if(queryParam.containsKey("hcity") == true)
//			{
//				city = queryParam.get("hcity").toString();
//				if(city.equals("")==false)
//				{
//					String [] citytmp = city.split(",");
//					queryParam.put("citylist", citytmp);
//				}
//			}
//			
//			// 合同类型
//			if(queryParam.containsKey("hcontracttype") == true)
//			{
//				contracttype = queryParam.get("hcontracttype").toString();
//				if(contracttype.equals("")==false)
//				{
//					String [] type = contracttype.split(",");
//					queryParam.put("contracttypelist", type);
//				}
//			}
//			
//			// 合同编号
//			if(queryParam.containsKey("contractNo") == true)
//			{
//				contractno = queryParam.get("contractNo").toString();
//				if(contractno.equals("")==false)
//				{
//					queryParam.put("contractno", contractno);
//				}
//			}
//			
//			// 门店编号
//			if(queryParam.containsKey("storeNo") == true)
//			{
//				storeno = queryParam.get("storeNo").toString();
//				if(storeno.equals("")==false)
//				{
//					queryParam.put("storeno", storeno);
//				}
//			}
//			
//			if(queryParam.containsKey("address") == true)
//			{
//				storeaddress  = queryParam.get("address").toString();
//				if(storeaddress.equals("")==false)
//				{
//					queryParam.put("storeaddress", storeaddress);
//				}
//			}
//			
//			
//			// 经济公司
//			if(queryParam.containsKey("partyB") == true)
//			{
//				company = queryParam.get("partyB").toString();
//				if(company.equals("")==false)
//				{
//					queryParam.put("company", company);
//				}
//			}
//	
//			
//			// 区/事业部
//			if(queryParam.containsKey("hgroup1") == true)
//			{
//				group1 = queryParam.get("hgroup1").toString();
//				if(group1.equals("")==false)
//				{
//					String [] group1lst = group1.split(",");
//					queryParam.put("group1", group1lst);
//				}
//			}
//			
//			// 中心/事业部
//			if(queryParam.containsKey("hgroup2") == true)
//			{
//				group2 = queryParam.get("hgroup2").toString();
//				if(group2.equals("")==false)
//				{
//					String [] group2lst = group2.split(",");
//					queryParam.put("group2", group2lst);
//				}
//			}
//			
//			// 组
//			if(queryParam.containsKey("hgroup3") == true)
//			{
//				group3 = queryParam.get("hgroup3").toString();
//				if(group3.equals("")==false)
//				{
//					String [] group3lst = group3.split(",");
//					queryParam.put("group3", group3lst);
//				}
//			}
//			
//		} catch (Exception e) 
//		{
//			throw new RuntimeException(e);
//		}
//  		
//  		
//  		return queryParam;
  	//}	
  
	

    

}
