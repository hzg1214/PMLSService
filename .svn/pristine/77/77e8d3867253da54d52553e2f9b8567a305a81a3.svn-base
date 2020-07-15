package cn.com.eju.deal.api.WXCloud.service;

import cn.com.eju.deal.api.WXCloud.dao.WXCloudGpDataMapper;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by haidan on 2019/1/25.
 */
@Service("apiWXCloudService")
public class APIWXCloudService {

    @Resource
    WXCloudGpDataMapper wxCloudGpDataMapper;

    /**
     * 将数据更新至表cloud_yyjb_gp_day_data、cloud_yyjb_gp_week_data、cloud_yyjb_gp_month_data、cloud_yyjb_gp_quarter_data
     *
     * @param listMap 日期, dateStr
     *                城市编号, cityNo
     *                城市名, cityName
     *                到当前日期为止总的门店数, storeNmAll
     *                当天新增门店数, storeNmAdd
     *                当天扣减的门店数, storeNmSubtract
     *                到当前截止日期交易进件总数, dealNmAll
     *                当天进件数, dealNmAdd
     *                当天扣减进件数, dealNmSubtract
     *                到当前日期截止的交易金额, dealAmountAll
     *                当天新增交易金额, dealAmountAdd
     *                当天扣减的交易金额, dealAmountSubtract
     *                总收入, incomeAll
     *                当天新增的收入, incomeAdd
     *                当天扣减的数据, incomeSubtract
     * @return
     */
    public ResultData<?> setGPData(List<Map<String, Object>> listMap) throws Exception{
        ResultData<?> resultData = new ResultData<>();
        resultData.setSuccess();
        for (Map<String, Object> data : listMap) {
            //校验参数
            if (null == data.get("dateStr")) {
                resultData.setFail("缺少参数dateStr");
                return resultData;
            }
            if (null == data.get("cityNo")) {
                resultData.setFail("缺少参数cityNo");
                return resultData;
            }
            if (null == data.get("cityName")) {
                resultData.setFail("缺少参数cityName");
                return resultData;
            }
            if (null == data.get("storeNmAll")) {
                resultData.setFail("缺少参数storeNmAll");
                return resultData;
            }
            if (null == data.get("storeNmAdd")) {
                resultData.setFail("缺少参数storeNmAdd");
                return resultData;
            }
            if (null == data.get("storeNmSubtract")) {
                resultData.setFail("缺少参数storeNmSubtract");
                return resultData;
            }
            if (null == data.get("dealNmAll")) {
                resultData.setFail("缺少参数dealNmAll");
                return resultData;
            }
            if (null == data.get("dealNmAdd")) {
                resultData.setFail("缺少参数dealNmAdd");
                return resultData;
            }
            if (null == data.get("dealNmSubtract")) {
                resultData.setFail("缺少参数dealNmSubtract");
                return resultData;
            }
            if (null == data.get("dealAmountAll")) {
                resultData.setFail("缺少参数dealAmountAll");
                return resultData;
            }
            if (null == data.get("dealAmountAdd")) {
                resultData.setFail("缺少参数dealAmountAdd");
                return resultData;
            }
            if (null == data.get("dealAmountSubtract")) {
                resultData.setFail("缺少参数dealAmountSubtract");
                return resultData;
            }
            if (null == data.get("incomeAll")) {
                resultData.setFail("缺少参数incomeAll");
                return resultData;
            }
            if (null == data.get("incomeAdd")) {
                resultData.setFail("缺少参数incomeAdd");
                return resultData;
            }
            if (null == data.get("incomeSubtract")) {
                resultData.setFail("缺少参数incomeSubtract");
                return resultData;
            }
        }

        for (Map<String, Object> data : listMap) {
            wxCloudGpDataMapper.mergeGpDayData(data);
            /*Integer dayRel = wxCloudGpDataMapper.mergeGpDayData(data);
            if (dayRel >= 0) {
                Map<String, Object> indexMap = wxCloudGpDataMapper.getIndexForDate(data);
                if (null != indexMap) {
                    data.put("yearStr", indexMap.get("yearStr"));
                    data.put("quarterIndex", indexMap.get("quarterIndex"));
                    data.put("monthIndex", Integer.valueOf(indexMap.get("monthIndex").toString()));
                    data.put("weekIndex", indexMap.get("weekIndex"));
                    wxCloudGpDataMapper.mergeGpQuarterData(data);
                    wxCloudGpDataMapper.mergeGpMonthData(data);
                    wxCloudGpDataMapper.mergeGpWeekData(data);
                }
            }*/
        }

        return resultData;
    }
}
