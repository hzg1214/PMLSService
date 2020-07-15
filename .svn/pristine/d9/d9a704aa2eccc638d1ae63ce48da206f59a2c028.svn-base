package cn.com.eju.deal.houseLinkage.linkAchieveChange.service;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.dao.AchieveMentChangeLogMapper;
import cn.com.eju.deal.houseLinkage.linkAchieveChange.model.AchieveMentChangeLog;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 联动业绩调整日志
 * Created by hzy on 2017/10/23.
 */
@Service("achieveChangeLogService")
public class AchieveChangeLogService {
    @Resource
    AchieveMentChangeLogMapper achieveMentChangeLogMapper;

    public ResultData getLogList(Integer relateId)throws Exception{
        ResultData resultData = new ResultData();
        resultData.setFail();

        List<AchieveMentChangeLog> achieveMentChangeLogList =  achieveMentChangeLogMapper.getLogList(relateId);
        if(achieveMentChangeLogList != null){
            resultData.setReturnData(achieveMentChangeLogList);
            resultData.setTotalCount(achieveMentChangeLogList.size() + "");
            resultData.setSuccess();
        }
        return resultData;
    }

}
