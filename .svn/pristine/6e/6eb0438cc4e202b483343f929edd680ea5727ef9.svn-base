package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dao.MembershipMapper;
import cn.com.eju.deal.Report.model.Membership;
import cn.com.eju.deal.common.util.BaseUtils;
import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("membershipService")
public class MembershipService {

    @Resource
    private MembershipMapper membershipMapper;


    /*
        查询公盘会员明细数据
     */
    public ResultData<List<Membership>> queryList(Map<String, Object> map) throws Exception {

        ResultData<List<Membership>> resultData = new ResultData<List<Membership>>();

        if (map.containsKey("stageStr")) {
            map.put("stageList", BaseUtils.changeArrayToList((String)map.get("stageStr"),","));
        }
        map.remove("stageStr");

        List<Membership> list = membershipMapper.queryList(map);
        resultData.setTotalCount((String) map.get(QueryConst.TOTAL_COUNT));
        resultData.setReturnData(list);

        return resultData;
    }

}
