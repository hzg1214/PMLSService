package cn.com.eju.pmls.report.badDebts.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.report.badDebts.dao.BadDebtsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("badDebtsService")
public class BadDebtsService extends BaseService {

    @Autowired
    BadDebtsMapper badDebtsMapper;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

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

        List<?> list = badDebtsMapper.queryList(queryParam);

        if (list != null) {
            int size = Integer.parseInt(queryParam.get("total").toString());
            resultData.setTotalCount(String.valueOf(size));
            resultData.setReturnData(list);
        }
        return resultData;
    }


    public ResultData<String> export(Map<String, Object> queryParam) {
        ResultData<String> resultData = new ResultData<String>();
        String uuId = UUID.randomUUID().toString().replaceAll("-", "");

        String webUrl = SystemParam.getWebConfigValue("exportWebUrl");
        String filePath = SystemParam.getWebConfigValue("rptQTLnkDetail_filePath");
        String fileName = "坏账明细" + uuId + ".csv";

        String regionCodes = "";
        if(queryParam.containsKey("regionCodes")){
            regionCodes = (String)queryParam.get("regionCodes");
        }
        String areaCityCodes = "";
        if(queryParam.containsKey("areaCityCodes")){
            areaCityCodes = (String)queryParam.get("areaCityCodes");
        }
        String cityIds = "";
        if(queryParam.containsKey("cityIds")){
            cityIds = (String)queryParam.get("cityIds");
        }
        String project = "";
        if(queryParam.containsKey("project")){
            project = (String)queryParam.get("project");
        }
        String clickType = "";
        if(queryParam.containsKey("clickType")){
            clickType = queryParam.get("clickType").toString();
        }
        String debtsTypes = "";
        if(queryParam.containsKey("debtsTypes")){
            debtsTypes = (String)queryParam.get("debtsTypes");
        }
        String revenueNodes = "";
        if(queryParam.containsKey("revenueNodes")){
            revenueNodes = (String)queryParam.get("revenueNodes");
        }

        StringBuffer sql = new StringBuffer();
        sql.append(" EXEC crm.dbo.P_PMLS_RPT_badDebts  ");
        sql.append("  '" + TransactSQLInjection(regionCodes) + "'");
        sql.append(" ,'" + TransactSQLInjection(areaCityCodes) + "'");
        sql.append(" ,'" + TransactSQLInjection(cityIds) + "'");
        sql.append(" ,'" + TransactSQLInjection(project) + "'");
        sql.append(" ,'" + TransactSQLInjection(clickType) + "'");
        sql.append(" ,'" + TransactSQLInjection(debtsTypes) + "'");
        sql.append(" ,'" + TransactSQLInjection(revenueNodes) + "'");
        sql.append(" ,0,0,0");

        Map<String, Object> imparm = new HashMap<>();
        imparm.put("sqlstr", sql.toString());
        imparm.put("filePathName", filePath + "//" + fileName);
        badDebtsMapper.export(imparm);

        resultData.setReturnData(webUrl + "/" + fileName);

        return resultData;
    }


}
