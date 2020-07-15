package cn.com.eju.deal.storeContribution.service;

import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("storeContributionService")
public class StoreContributionService {


    /*
      查询公盘会员明细数据
   */
    public ResultData<?> query(Map<String, Object> map) throws Exception {

        ResultData<?> resultData = new ResultData<>();
        resultData.setReturnData(null);
        return resultData;
    }



}
