package cn.com.eju.deal.Signed.controller;


import java.util.ArrayList;
import java.util.HashMap;
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
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.Signed.model.SignDetail;
import cn.com.eju.deal.Signed.service.SignDetailServiceImpl;
import cn.com.eju.deal.Report.model.UserCenterAuthDto;
import cn.com.eju.deal.Report.service.ICityService;

/**
 *
 * @author QJP
 *2017-06-26
 */
@RestController
@RequestMapping(value="signReport")
public class SignReportController {
	
	/*Add By DengLin 2017/05/04 Start*/
	/**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    /*Add By DengLin 2017/05/04 End*/
	
	@Resource(name = "signDetailService")
	private SignDetailServiceImpl signDetailService;
	
	@Resource
    private ICityService cityService;
		

	/**
	 * 查询数据
	 * @param request  type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
	 * @param mop
	 * @return
	 */
	@RequestMapping(value = "querySignDetailList/{param}", method = RequestMethod.GET)
	public String querySignDetailList(
			@PathVariable String param) {
		// 获取请求参数
		Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
		Map<String, Object> paramdata = new HashMap<String, Object>();
		
		paramdata = ChangeParam(queryParam);
		ResultData<List<SignDetail>> resultData = new ResultData<List<SignDetail>>();
		
		List<SignDetail> list= signDetailService.querySignDetailList(paramdata);
		if(list != null)
		{
			resultData.setTotalCount((String)queryParam.get(QueryConst.TOTAL_COUNT));
			resultData.setReturnData(list);
		}
		return resultData.toString();
	}
	
	
	
	/**
	 * 根据城市ID得到中心
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "/getCenterGroupName/{param}", method = RequestMethod.GET)
	public String getCenterGroupName(@PathVariable String param) {
		// 获取请求参数
	    Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
	    ReturnView<String, Object> jv = new ReturnView<String, Object>();
	  //如果orgId是全部，则分割成list集合
	    String cityId = queryParam.get("cityId").toString();
		List<Object> idlist = new ArrayList<Object>();
		if (cityId.indexOf(",")>0)
        {
		    String [] id = cityId.split(",");
		    for (int i = 0; i < id.length; i++)
            {
		        idlist.add(id[i]);
            }
            
        }else {
            idlist.add(cityId);
        }
		queryParam.remove("cityId");
		queryParam.put("list", idlist);
        // 获取页面显示数据
        List<UserCenterAuthDto> list = signDetailService.getCenterGroupName(queryParam);	
		// 设置总共有多少条记录
	    jv.addAttribute("list", list);
     	return jv.toString();
	}
	
	
	 /**
	 * 查询条件
	 * @param queryParam
	 */
  	private Map<String, Object> ChangeParam(Map<String, Object> queryParam)
  	{
        String city = "";											//城市  		
  		String contractType = "";                                   //合作模式
  		String centerGroupId = "";                                  //中心
  		String allCityId = "";   //所有城市ID
  		String storeCityList = "";   //门店所属城市
		try {
			
			//城市
			if(queryParam.containsKey("hcity") == true)
			{
				city = queryParam.get("hcity").toString();
				if(city.equals("")==false)
				{
					String [] citytmp = city.split(",");
					queryParam.put("citylist", citytmp);
				}
			}
			
			// 合同类型
			if(queryParam.containsKey("contractType") == true)
			{
				contractType = queryParam.get("contractType").toString();
				if(contractType.equals("")==false)
				{
					String [] type = contractType.split(",");
					queryParam.put("contracttypelist", type);
				}
			}
			
			// 合同类型
			if(queryParam.containsKey("centerGroupId") == true)
			{
				centerGroupId = queryParam.get("centerGroupId").toString();
			    if(centerGroupId.equals("")==false)
				{
					String [] type = centerGroupId.split(",");
					queryParam.put("centerlist", type);
				}
			}
			
			//当前用户所有城市
			if(queryParam.containsKey("allCityId") == true)
			{
				allCityId = queryParam.get("allCityId").toString();
				if(allCityId.equals("")==false)
				{
					String [] citytmp = allCityId.split(",");
					queryParam.put("allCityIdList", citytmp);
				}
			}
			
			//门店所属城市
			if(queryParam.containsKey("storeCityList") == true)
			{
				storeCityList = queryParam.get("storeCityList").toString();
				if(storeCityList.equals("")==false)
				{
					String [] citytmp = storeCityList.split(",");
					queryParam.put("storeCityLists", citytmp);
				}
			}
			
		} catch (Exception e) 
		{
			throw new RuntimeException(e);
		}
  		
  		
  		return queryParam;
  	}	
  
	

    

}
