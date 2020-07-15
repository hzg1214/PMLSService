package cn.com.eju.deal.service.achieveChange;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.achieveChange.AchieveChangeDto;
import cn.com.eju.deal.mapper.achieveChange.AchieveChangeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/23.
 */
@Service("achieveChangeService")
public class AchieveChangeService {
    @Resource
    AchieveChangeMapper achieveChangeMapper;

    public ResultData<List<AchieveChangeDto>> queryList(Map<String, Object> p){
        ResultData<List<AchieveChangeDto>> resultData = new ResultData<List<AchieveChangeDto>>();
        List<AchieveChangeDto> list= achieveChangeMapper.queryList(p);
        resultData.setReturnData(list);

        // 转换
        resultData.setTotalCount((String) p.get(QueryConst.TOTAL_COUNT));


        return resultData;

    }
}
