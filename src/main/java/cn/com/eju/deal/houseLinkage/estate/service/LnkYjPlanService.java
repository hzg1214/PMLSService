package cn.com.eju.deal.houseLinkage.estate.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjPlanDetailMapper;
import cn.com.eju.deal.houseLinkage.estate.dao.LnkYjPlanMapper;
import cn.com.eju.deal.houseLinkage.estate.model.LnkYjPlanDetailActDto;
import cn.com.eju.deal.houseLinkage.estate.model.PmlsCompanyYjPlan;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("lnkYjPlanService")
public class LnkYjPlanService {

    @Resource
    LnkYjPlanDetailMapper lnkYjPlanDetailMapper;
    @Resource
    LnkYjPlanMapper lnkYjPlanMapper;

    public ResultData<LnkYjPlanDetailActDto> queryQueryYJAmount(Map<String, Object> queryParam) {
        ResultData<LnkYjPlanDetailActDto> resultData = new ResultData<>();
        LnkYjPlanDetailActDto dto = lnkYjPlanDetailMapper.queryQueryYJAmount(queryParam);
        resultData.setReturnData(dto);
        return resultData;
    }

    public ResultData<List<PmlsCompanyYjPlan>> queryCntYjPlanList(Map<String, Object> queryParam) {
        ResultData<List<PmlsCompanyYjPlan>> resultData = new ResultData<>();

        // 返佣方案
        List<PmlsCompanyYjPlan> list = lnkYjPlanMapper.getCntYJFYPlan(queryParam);
        // 收入方案
        PmlsCompanyYjPlan plan = lnkYjPlanMapper.getCntYJSRPlan(queryParam);

        list.add(plan);

        for (PmlsCompanyYjPlan record : list) {
            // 如果没有佣金方案或者未启动则，默认人工设置
            if (record.getNum().equals(0)) {
                record.setPlanId(0);
                record.setPlanName("人工核算");
            }
        }
        resultData.setReturnData(list);
        return resultData;
    }


}
