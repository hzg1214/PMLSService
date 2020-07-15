package cn.com.eju.deal.base.linkage.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.eju.deal.Report.service.ReportIGroupService;
import cn.com.eju.deal.base.controller.BaseController;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.linkage.model.City;
import cn.com.eju.deal.base.linkage.service.LinkageService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.user.model.Group;

/**   
* 城市、行政区、板块 联动
* @author (li_xiaodong)
* @date 2016年3月20日 下午12:29:31
*/
@RestController
@RequestMapping(value = "linkages")
public class LinkageController extends BaseController
{
    
    /**
    * 日志
    */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    
    @Resource(name = "linkageService")
    private LinkageService linkageService;
    
    @Resource
	private ReportIGroupService groupService;
    /** 
     * 获取城市list
     * @param
     * @return
     */
    @RequestMapping(value = "/city", method = RequestMethod.GET)
    public String getCityList()
    {
        
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        
        try
        {
            resultData = linkageService.getCityList();
        }
        catch (Exception e)
        {
            logger.error("Linkage", "LinkageController", "getCityList", "", 0, "", "获取城市list失败", e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /** 
    * 根据城市CityNo获取其行政区List
    * @param cityNo
    * @return
    */
    @RequestMapping(value = "/district/{cityNo}", method = RequestMethod.GET)
    public String getDistrictListByCityNo(@PathVariable String cityNo)
    {
        
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = linkageService.getDistrictListByCityNo(cityNo);
        }
        catch (Exception e)
        {
            logger.error("Linkage",
                "LinkageController",
                "getDistrictListByCityNo",
                "cityNo=" + cityNo,
                0,
                "",
                "根据城市CityNo获取其行政区List失败",
                e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    /**
    * 根据城市CityNo获取其品牌List
    * @param cityNo
    * @return
    */
    @RequestMapping(value = "/getBrandList/{cityNo}", method = RequestMethod.GET)
    public String getBrandListByCityNo(@PathVariable String cityNo)
    {

        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = linkageService.getBrandListByCityNo(cityNo);
        }
        catch (Exception e)
        {
            logger.error("Linkage",
                "LinkageController",
                "getBrandListByCityNo",
                "cityNo=" + cityNo,
                0,
                "",
                "根据城市CityNo获取其品牌List失败",
                e);

            resultData.setFail();
        }

        return resultData.toString();
    }
    /**
    * 根据城市CityNo获取其品牌List
    * @param cityNo
    * @return
    */
    @RequestMapping(value = "/getIsShowQRCode/{cityNo}", method = RequestMethod.GET)
    public String getIsShowQRCode(@PathVariable String cityNo)
    {

        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = linkageService.getIsShowQRCode(cityNo);
        }
        catch (Exception e)
        {
            logger.error("Linkage",
                "LinkageController",
                "getIsShowQRCode",
                "cityNo=" + cityNo,
                0,
                "",
                "根据城市CityNo获取新增公司页面是否显示二维码页面",
                e);

            resultData.setFail();
        }

        return resultData.toString();
    }
    
    /** 
    *根据行政区DistrictNo获取其板块List
    * @param districtNo
    * @return
    */
    @RequestMapping(value = "/area/{districtNo}", method = RequestMethod.GET)
    public String getAreaListByDistrictNo(@PathVariable String districtNo)
    {
        
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = linkageService.getAreaListByDistrictNo(districtNo);
        }
        catch (Exception e)
        {
            logger.error("Linkage",
                "LinkageController",
                "getAreaListByDistrictNo",
                "districtNo=" + districtNo,
                0,
                "",
                "根据行政区DistrictNo获取其板块List失败",
                e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
    
    /** 
     * 根据城市cityNo获取城市所有板块List
     * @param cityNo
     * @return
     */
    @RequestMapping(value = "/area/all/{cityNo}", method = RequestMethod.GET)
    public String getAreaListByCityNo(@PathVariable String cityNo)
    {
        
        //构建返回
        ResultData<?> resultData = new ResultData<>();
        try
        {
            resultData = linkageService.getAreaListByCityNo(cityNo);
        }
        catch (Exception e)
        {
            logger.error("Linkage",
                "LinkageController",
                "getAreaListByCityNo",
                "cityNo=" + cityNo,
                0,
                "",
                "根据城市cityNo获取城市所有板块List",
                e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
 
    //Add By ningchao 2017/07/03  Start
    /** 
     * 根据城市cityNo获取城市所有中心List
     * @param cityNo
     * @param typeId 查询组
     * @param typeId2 查询中心
     * @return
     */
    @RequestMapping(value = "/group/{cityNo}/{typeId}/{typeId2}", method = RequestMethod.GET)
    public String getGroupListByCityNo(@PathVariable String cityNo, @PathVariable Integer typeId, @PathVariable Integer typeId2)
    {
    	//构建返回
        ResultData<List<Group>> resultData = new ResultData<List<Group>>();
        try
        {
        	//取得城市ID
        	List<Object> cityIdlist = new ArrayList<Object>();
            City city = linkageService.getCityByCityNo(cityNo);
            if(null != city)
            {
            	cityIdlist.add(city.getId());
            }

        	Map<String, Object> queryParam = new HashMap<String, Object>();
        	queryParam.put("typeId", typeId);
        	queryParam.put("list", cityIdlist);
        	queryParam.put("organization", "2017");
        	//查询组
        	List<Group> list=groupService.selectAllGroupByTypeIdAndCityId(queryParam);
        	
        	List<Object> idlist = new ArrayList<Object>();
        	for (Group group : list) {
				String orgId = group.getOrgIdStr();
				idlist.add(orgId);
			}
        	queryParam.clear();
        	queryParam.put("list", idlist);
        	queryParam.put("typeId", typeId2);
        	queryParam.put("organization", "2017");
        	//查询中心
        	List<Group> listGroup = groupService.selectAllGroupByOrgIdAndTypeId(queryParam);

        	resultData.setReturnData(listGroup);
        }
        catch (Exception e)
        {
            logger.error("Linkage",
                "LinkageController",
                "getAreaListByCityNo",
                "cityNo=" + cityNo,
                0,
                "",
                "根据城市cityNo获取城市中心List",
                e);
            
            resultData.setFail();
        }
        
        return resultData.toString();
    }
   //Add by ningchao 2017/07/03 End
    
    
    @RequestMapping(value = "/getCityByGovCityCode/{govCityCode}", method = RequestMethod.GET)
    public String getCityByGovCityCode(@PathVariable String govCityCode){
        //构建返回
        ResultData<Map<String,Object>> resultData = new ResultData<>();
       
        Map<String,Object> city = linkageService.getCityByGovCityCode(govCityCode);
        resultData.setReturnData(city);
        resultData.setSuccess();
        return resultData.toString();
    }
}
