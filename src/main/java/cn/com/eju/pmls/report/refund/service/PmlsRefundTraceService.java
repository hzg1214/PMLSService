package cn.com.eju.pmls.report.refund.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.base.support.SystemParam;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.pmls.report.refund.dao.PmlsRefundTraceMapper;
import cn.com.eju.pmls.report.refund.dto.RefundTrace;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service("pmlsRefundTraceService")
public class PmlsRefundTraceService extends BaseService {
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    PmlsRefundTraceMapper pmlsRefundTraceMapper;

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

        List<?> list = pmlsRefundTraceMapper.queryList(queryParam);

        if (!CollectionUtils.isEmpty(list)) {
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
        String fileName = "联动项目周回款跟踪" + uuId + ".csv";

        String cityNo = "";
        if(queryParam.containsKey("cityNo")){
            cityNo = (String)queryParam.get("cityNo");
        }
        String project = "";
        if(queryParam.containsKey("project")){
            project = (String)queryParam.get("project");
        }
        String userCode = (String)queryParam.get("userCode");


        String p_endDate = (String)queryParam.get("yearMonth");
        String p_year    = (String)queryParam.get("year");
        String p_moth    = (String)queryParam.get("month");
        String p_weekNo  = (String)queryParam.get("weekNo");
        String p_sortNo  = (String)queryParam.get("sortNo");
/*        String clickType = "";
        if(queryParam.containsKey("clickType")){
            clickType = queryParam.get("clickType").toString();
        }*/

        StringBuffer sql = new StringBuffer();
        sql.append(" EXEC crm.dbo.P_PMLS_RPT_ProjectPartA_Week_Track  ");
        sql.append("  '" + TransactSQLInjection(cityNo) + "'");
        sql.append(" ,'" + TransactSQLInjection(project) + "'");
        sql.append(" ,'" + TransactSQLInjection(userCode) + "'");


        sql.append(" ,'" + TransactSQLInjection(p_endDate) + "'");
        sql.append(" ,'" + TransactSQLInjection(p_year) + "'");
        sql.append(" ,'" + TransactSQLInjection(p_moth) + "'");
        sql.append(" ,'" + TransactSQLInjection(p_weekNo) + "'");
        sql.append(" ,'" + TransactSQLInjection(p_sortNo) + "'");
        sql.append(",2,0,0,0");


        Map<String, Object> imparm = new HashMap<>();
        imparm.put("sqlstr", sql.toString());
        imparm.put("filePathName", filePath + "//" + fileName);
        pmlsRefundTraceMapper.export(imparm);

        resultData.setReturnData(webUrl + "/" + fileName);

        return resultData;
    }

}
