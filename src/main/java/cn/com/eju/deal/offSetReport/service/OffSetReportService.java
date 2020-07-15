package cn.com.eju.deal.offSetReport.service;

import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.cashbill.dao.CashBillReportMapper;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2019/11/29.
 */
@Service("offSetReportService")
public class OffSetReportService extends BaseService {

    /**
     * 日志
     */
    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    @Resource
    private CashBillReportMapper cashBillReportMapper;


    public ResultData<List<Map<String,Object>>> getOffSetReportList(Map<String, Object> queryParam) {
        ResultData<List<Map<String,Object>>> resultData = new ResultData<>();
        //查询
        List<Map<String,Object>> list = cashBillReportMapper.getReportForDeductDetail(queryParam);
        // 转换
        resultData.setTotalCount((String) queryParam.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);

        return resultData;
    }
}
