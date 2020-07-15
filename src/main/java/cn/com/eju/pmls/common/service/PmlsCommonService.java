package cn.com.eju.pmls.common.service;


import cn.com.eju.deal.base.helper.LogHelper;
import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.common.dao.CommonMapper;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Map;

/**
 * service层
 */
@Service("pmlsCommonService")
public class PmlsCommonService extends BaseService {

    @Autowired
    private CommonMapper commonMapper;

    private final LogHelper logger = LogHelper.getLogger(this.getClass());

    public ResultData<?> getCitySwitchMonth(String cityNo) {
        ResultData<Map<?, ?>> resultData = new ResultData<>();
        Map<?, ?> map = commonMapper.checkCitySwitchMonth(cityNo);
        resultData.setReturnData(map);
        return resultData;
    }

}
