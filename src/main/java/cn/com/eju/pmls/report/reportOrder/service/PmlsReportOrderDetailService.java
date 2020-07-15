package cn.com.eju.pmls.report.reportOrder.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.report.reportOrder.dao.PmlsReportOrderDetailMapper;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.json.Json;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("pmlsReportOrderDetailService")
public class PmlsReportOrderDetailService extends BaseService {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private PmlsReportOrderDetailMapper pmlsReportOrderDetailMapper;

    public ResultData<List<?>> queryList(Map<String, Object> queryParam) {
        ResultData<List<?>> resultData = new ResultData<List<?>>();
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

        queryParam.put("offset", (pageIndex - 1) * pageSize);
        queryParam.put("rows", pageSize);
        queryParam.put("total", 0);


        List<String> accountProjectListS = (List<String>)queryParam.get("accountProjectList");
        List<String> regionCodeListS = (List<String>)queryParam.get("regionCodeList");
        List<String> areaCityCodeListS = (List<String>)queryParam.get("areaCityCodeList");
        List<String> cityIdListS = (List<String>)queryParam.get("cityIdList");
        List<String> centerIdListS = (List<String>)queryParam.get("centerIdList");
        String accountProjectList = "";
        if(!CollectionUtils.isEmpty(accountProjectListS)){
            accountProjectList = Joiner.on(",").join(accountProjectListS);
        }

        String regionCodeList = "";
        if(!CollectionUtils.isEmpty(regionCodeListS)){
            regionCodeList = Joiner.on(",").join(regionCodeListS);
        }

        String areaCityCodeList = "";
        if(!CollectionUtils.isEmpty(areaCityCodeListS)){
            areaCityCodeList = Joiner.on(",").join(areaCityCodeListS);
        }
        String cityIdList = "";
        if(!CollectionUtils.isEmpty(cityIdListS)){
            cityIdList = Joiner.on(",").join(cityIdListS);
        }

        String centerIdList = "";
        if(!CollectionUtils.isEmpty(centerIdListS)){
            centerIdList = Joiner.on(",").join(centerIdListS);
        }

        queryParam.put("accountProjectList",accountProjectList);
        queryParam.put("regionCodeList",regionCodeList);
        queryParam.put("areaCityCodeList",areaCityCodeList);
        queryParam.put("cityIdList",cityIdList);
        queryParam.put("centerIdList",centerIdList);

        queryParam.put("p_clickType",1);
        List<?> list = pmlsReportOrderDetailMapper.queryList(queryParam);
        logger.info(JSON.toJSONString(queryParam));

        if (!CollectionUtils.isEmpty(list)) {
            int size = Integer.parseInt(queryParam.get("total").toString());
            resultData.setTotalCount(String.valueOf(size));
            resultData.setReturnData(list);
        }
        return resultData;
    }


    public ResultData<String> export(Map<String, Object> param) {
        logger.info("订单明细##CSV生成 start：param:" + JSON.toJSONString(param));
        ResultData<String> resultData = new ResultData<String>();
        String uuId = UUID.randomUUID().toString().replaceAll("-", "");

        String webUrl = SystemParam.getWebConfigValue("exportWebUrl");
        String filePath = SystemParam.getWebConfigValue("rptQTLnkDetail_filePath");
        String fileName = "pmlsOrderDetail" + uuId + ".csv";


        String organization 		     = (String)param.get("organization");
        String serachKey 			     = (String)param.get("serachKey");
        List<String> accountProjectListS = (List<String>)param.get("accountProjectList");
        List<String> regionCodeListS = (List<String>)param.get("regionCodeList");
        List<String> areaCityCodeListS = (List<String>)param.get("areaCityCodeList");
        List<String> cityIdListS = (List<String>)param.get("cityIdList");
        List<String> centerIdListS = (List<String>)param.get("centerIdList");
        String accountProjectList = "";
        if(!CollectionUtils.isEmpty(accountProjectListS)){
            accountProjectList = Joiner.on(",").join(accountProjectListS);
        }

        String regionCodeList = "";
        if(!CollectionUtils.isEmpty(regionCodeListS)){
            regionCodeList = Joiner.on(",").join(regionCodeListS);
        }

        String areaCityCodeList = "";
        if(!CollectionUtils.isEmpty(areaCityCodeListS)){
            areaCityCodeList = Joiner.on(",").join(areaCityCodeListS);
        }
        String cityIdList = "";
        if(!CollectionUtils.isEmpty(cityIdListS)){
            cityIdList = Joiner.on(",").join(cityIdListS);
        }

        String centerIdList = "";
        if(!CollectionUtils.isEmpty(centerIdListS)){
            centerIdList = Joiner.on(",").join(centerIdListS);
        }

        String estateId 				 = (String)param.get("estateId");
        String reportId 				 = (String)param.get("reportId");
        String customerFromId 		     = (String)param.get("customerFromId");
        String ywType 		             = (String)param.get("ywType");
        String dateStart 		         = (String)param.get("dateStart");
        String dateEnd  			     = (String)param.get("dateEnd");
        String userId 				     = param.get("userId").toString();


        StringBuffer sql = new StringBuffer();
        sql.append(" EXEC crm.dbo.P_PMLS_RPT_LinkReportOrderDetail_CSV  ");
        sql.append(" '" + TransactSQLInjection(organization) + "'");

        sql.append(" ,'" + TransactSQLInjection(serachKey) + "'");
        sql.append(" ,'" + TransactSQLInjection(accountProjectList) + "'");
        sql.append(" ,'" + TransactSQLInjection(regionCodeList) + "'");
        sql.append(" ,'" + TransactSQLInjection(areaCityCodeList) + "'");
        sql.append(" ,'" + TransactSQLInjection(cityIdList) + "'");
        sql.append(" ,'" + TransactSQLInjection(centerIdList) + "'");

        sql.append(" ,'" + TransactSQLInjection(estateId) + "'");
        sql.append(" ,'" + TransactSQLInjection(reportId) + "'");
        sql.append(" ,'" + TransactSQLInjection(customerFromId) + "'");
        sql.append(" ,'" + TransactSQLInjection(ywType) + "'");
        sql.append(" ,'" + TransactSQLInjection(dateStart) + "'");
        sql.append(" ,'" + TransactSQLInjection(dateEnd) + "'");
        sql.append(" ,'" + TransactSQLInjection(userId) + "'");

        sql.append(",2,0,0,0");


        Map<String, Object> imparm = new HashMap<>();
        imparm.put("sqlstr", sql.toString());
        imparm.put("filePathName", filePath + "//" + fileName);
        pmlsReportOrderDetailMapper.export(imparm);

        resultData.setReturnData(webUrl + "/" + fileName);

        String csvUrl = webUrl + "/" + fileName;
        logger.info("订单明细##CSV生成 end：param:" + JSON.toJSONString(param) +"#imparm="+ JSON.toJSONString(imparm)
                +"#csvUrl="+csvUrl);
        return resultData;
    }

}
