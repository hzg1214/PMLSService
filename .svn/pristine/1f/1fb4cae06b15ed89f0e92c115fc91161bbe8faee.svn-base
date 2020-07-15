package cn.com.eju.deal.Report.controller;

import cn.com.eju.deal.Report.model.ExpandDetail;
import cn.com.eju.deal.Report.model.StorePreserveSummary;
import cn.com.eju.deal.Report.model.UserCenterAuthDto;
import cn.com.eju.deal.Report.service.*;
import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.core.support.ReturnView;
import cn.com.eju.deal.core.util.JsonUtil;
import cn.com.eju.deal.user.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auther: xuliangliang
 * @Date: 2018\10\23 0023 17:27
 * @Description:
 */
@RestController
@RequestMapping(value = "storePreserve")
public class StorePreserveController {

    /*Add By DengLin 2017/05/04 Start*/
    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());
    /*Add By DengLin 2017/05/04 End*/

    @Resource
    private ReportIGroupService groupService;

    @Resource
    private ICityService cityService;

    @Resource(name = "storePreserveService")
    private StorePreserveServiceImpl storePreserveService;

    @Autowired
    private IExcelTaskService excelTaskService;

    @Autowired
    private ITempletService templetService;

    /**
     * 根据userId查询城市
     * @param param
     * @return
     */
    @RequestMapping(value = "/getUserCenterAuthCityName/{param}", method = RequestMethod.GET)
    public String getUserCenterAuthCityName(
            @PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        List<UserCenterAuthDto> list=storePreserveService.getUserCenterAuthCityName(queryParam);
        ReturnView<String, Object> jv = new ReturnView<String, Object>();
        jv.addAttribute("list", list);
        return jv.toString();
    }

    @RequestMapping(value = "/getAreaGroupName/{param}", method = RequestMethod.GET)
    public String getAreaGroupName(@PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);

        //如果cityId是全国，则分割成list集合
        String cityId = queryParam.get("cityId").toString();
        List<Object> idlist = new ArrayList<Object>();
        if (cityId.indexOf(",") > 0) {
            String[] id = cityId.split(",");
            for (int i = 0; i < id.length; i++) {
                idlist.add(id[i]);
            }

        } else {
            idlist.add(cityId);
        }

        queryParam.remove("cityId");
        queryParam.put("list", idlist);

        List<UserCenterAuthDto> list = storePreserveService.getAreaGroupName(queryParam);

        ReturnView<String, Object> jv = new ReturnView<String, Object>();

        // 设置总共有多少条记录
        jv.addAttribute("list", list);

        return jv.toString();
    }

    /**
     * 根据登录人ID,城市ID，对应区/事业部ID 拿到对应部门/中心
     * @param param
     * @return
     */
    @RequestMapping(value = "/getCenterGroupName/{param}", method = RequestMethod.GET)
    public String getCenterGroupName(@PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        String typeId = queryParam.get("typeId").toString();
        ReturnView<String, Object> jv = new ReturnView<String, Object>();
        List<UserCenterAuthDto> list = null;

        if ("1".equals(typeId)) {
            //如果orgId是全部，则分割成list集合
            String orgId = queryParam.get("orgId").toString();

            List<Object> idlist = new ArrayList<Object>();

            if (orgId.indexOf(",") > 0) {
                String[] id = orgId.split(",");
                for (int i = 0; i < id.length; i++) {
                    idlist.add(id[i]);
                }

            } else {
                idlist.add(orgId);
            }

            queryParam.put("list", idlist);
            // 获取页面显示数据
            list = storePreserveService.getCenterGroupName(queryParam);
        } else if ("2".equals(typeId)) {
            //如果orgId是全部，则分割成list集合
            String orgId = queryParam.get("orgId").toString();

            List<Object> idlist = new ArrayList<Object>();

            if (orgId.indexOf(",") > 0) {
                String[] id = orgId.split(",");
                for (int i = 0; i < id.length; i++) {
                    idlist.add(id[i]);
                }

            } else {
                idlist.add(orgId);
            }

            queryParam.put("list", idlist);
            // 获取页面显示数据
            list = storePreserveService.getGroupName(queryParam);
        }

        jv.addAttribute("typeId", typeId);
        jv.addAttribute("list", list);

        return jv.toString();
    }



    /**
     * 查询数据
     * @param param  type 1：草签  2：OA审核通过日期-  3：翻牌验收通过日期   groupIds：组  contractTypes:合同类型      address：门店地址
     * @return
     */
    @RequestMapping(value = "queryStorePreserveList/{param}", method = RequestMethod.GET)
    public String queryExpandDetailList(@PathVariable String param) {
        System.out.println("queryExpandDetailList param: " + param);
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
//		Map<String, Object> paramdata = new HashMap<String, Object>();

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

        List<ExpandDetail> list= storePreserveService.queryStorePreserve(queryParam);
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
    @RequestMapping(value = "exportStorePreserveList/{param}", method = RequestMethod.GET)
    public String exportStorePreserveList(@PathVariable String param) {
        System.out.println("exportExpandDetailList param: " + param);
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
//		Map<String, Object> paramdata = new HashMap<String, Object>();

//		paramdata = ChangeParam(queryParam);
        ResultData<List<ExpandDetail>> resultData = new ResultData<List<ExpandDetail>>();

        List<ExpandDetail> list= storePreserveService.queryStorePreserve(queryParam);
        if(list != null)
        {
            int size = list.size();
            resultData.setTotalCount(String.valueOf(size));

            resultData.setReturnData(list);
        }

        return resultData.toString();
    }

    /**
     * 根据groupId查询
     * @param param
     * @return
     */
    @RequestMapping(value = "selectGroupByOrgId/{param}", method = RequestMethod.GET)
    public String selectGroupByOrgId(
            @PathVariable String param) {
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param,  Map.class);

        //如果cityId是全国，则分割成list集合
        String orgId = queryParam.get("orgId").toString();
        orgId = orgId.replaceAll("\\|", "/");
        List<Object> idlist = new ArrayList<Object>();
        if (orgId.indexOf(",")>0)
        {
            String [] id = orgId.split(",");
            for (int i = 0; i < id.length; i++)
            {
                idlist.add(id[i]);
            }

        } else {
            idlist.add(orgId);
        }
        queryParam.remove("orgId");
        queryParam.put("list", idlist);

        // 获取页面显示数据
        List<Group> list=groupService.selectGroupByOrgId(queryParam);
        ReturnView<String, Object> jv = new ReturnView<String, Object>();
        // 设置总共有多少条记录
        /* jv.addAttribute("list", list);*/
        return jv.toString();

    }


    /**
     * 处理数据
     * @param queryParam
     */
    private void handlerParam(Map<String, Object> queryParam){
        //处理参数
        for (Map.Entry<String, Object> e : queryParam.entrySet()) {
            String key = e.getKey();
            String value = e.getValue().toString();
            if(key.contains("performTeam")){
                List<Object> list = new ArrayList<Object>();
                this.getParams(list, value);
                queryParam.put(key, list);
            }
        }
        //业务类型
        if(null != queryParam.get("contractTypes")){
            String contractType = queryParam.get("contractTypes").toString();
            List<Object> contractTypes = new ArrayList<Object>();
            if (contractType != null && contractType != "")
            {
                if (contractType.indexOf(",")>0)
                {
                    String [] id = contractType.split(",");
                    for (int i = 0; i < id.length; i++)
                    {
                        contractTypes.add(id[i]);
                        //合同类型为A时执行A转B原A的代码
                        if (id[i].equals("10301"))
                        {
                            queryParam.put("contract", "10301");
                        }
                    }

                }else {
                    contractTypes.add(contractType);
                    //合同类型为A时执行A转B原A的代码
                    if (contractType.equals("10301"))
                    {
                        queryParam.put("contract", "10301");
                    }
                }
            }
            queryParam.remove("contractTypes");
            queryParam.put("contractTypes", contractTypes);
        }
        System.out.println(queryParam);
    }

    //处理参数
    public List<Object> getParams(List<Object> performTeam,String groupId){
        if (groupId !=null && groupId != "")
        {
            if (groupId.indexOf("|")!=-1){
                groupId = groupId.replaceAll("\\|", "/");
                if (groupId.indexOf(",")>0)
                {
                    String [] id = groupId.split(",");
                    for (int i = 0; i < id.length; i++)
                    {
                        performTeam.add(id[i]);
                    }

                }else {
                    performTeam.add(groupId);
                }
                Map<String, Object> idMap = new java.util.HashMap<String, Object>();
                idMap.put("list", performTeam);
                List<Group> gList = groupService.selectGroupByOrgId(idMap);
                performTeam.clear();
                for (Group group : gList)
                {
                    performTeam.add(group.getGroupId());
                }
            }else{
                if (groupId.indexOf(",")>0)
                {
                    String [] id = groupId.split(",");
                    for (int i = 0; i < id.length; i++)
                    {
                        performTeam.add(id[i]);
                    }

                }else {
                    performTeam.add(groupId);
                }
            }
        }
        return performTeam;
    }
    @RequestMapping(value = "queryStorePreserveSummaryList/{param}", method = RequestMethod.GET)
    public String queryStorePreserveSummaryList(@PathVariable String param) {
        System.out.println("queryStorePreserveSummaryList param: " + param);
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StorePreserveSummary>> resultData = new ResultData<>();
        List<StorePreserveSummary> list= storePreserveService.queryStorePreserveSummary(queryParam);
        resultData.setReturnData(list);
        return resultData.toString();
    }
//    exportStorePreserveSummaryList
    @RequestMapping(value = "exportStorePreserveSummaryList/{param}", method = RequestMethod.GET)
    public String exportStorePreserveSummaryList(@PathVariable String param) {
        System.out.println("exportStorePreserveSummaryList param: " + param);
        // 获取请求参数
        Map<String, Object> queryParam = JsonUtil.parseToObject(param, Map.class);
        ResultData<List<StorePreserveSummary>> resultData = new ResultData<List<StorePreserveSummary>>();
        List<StorePreserveSummary> list= storePreserveService.queryStorePreserveSummaryList(queryParam);
        resultData.setReturnData(list);
        return resultData.toString();
    }
}
