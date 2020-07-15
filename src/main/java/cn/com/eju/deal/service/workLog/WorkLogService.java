package cn.com.eju.deal.service.workLog;

import cn.com.eju.deal.core.support.QueryConst;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.sweepStreets.SweepStreetsMapper;
import cn.com.eju.deal.mapper.workLog.WorkLogMapper;
import cn.com.eju.deal.model.sweepStreets.StoreInfoDto;
import cn.com.eju.deal.model.workLog.UserInfoNewDto;
import cn.com.eju.deal.model.workLog.WorkLogDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by xu on 2017/5/11.
 */
@Service("workLogService")
public class WorkLogService {
    @Resource
    private WorkLogMapper workLogMapper;

    public ResultData queryCenterStoreList(WorkLogDto dto) throws Exception{
        ResultData resultData=new ResultData();
        List<WorkLogDto> list=workLogMapper.queryCenterStoreList(dto);
        resultData.setReturnData(list);
        return resultData;
    }
    public ResultData queryCenterMaintainerList(WorkLogDto dto) throws Exception{
        ResultData resultData=new ResultData();
        List<WorkLogDto> list=workLogMapper.queryCenterMaintainerList(dto);
        resultData.setReturnData(list);
        return resultData;
    }
    public ResultData queryMaintainerStoreList(WorkLogDto dto) throws Exception{
        ResultData resultData=new ResultData();
        List<WorkLogDto> list=workLogMapper.queryMaintainerStoreList(dto);
        resultData.setReturnData(list);
        return resultData;
    }
    public ResultData getUserInfo(WorkLogDto dto) throws Exception{
        ResultData resultData=new ResultData();
        UserInfoNewDto userInfoNewDto=workLogMapper.getUserInfo(dto);
        if(userInfoNewDto.getTypeCode().equals("PT_TZZY")){//拓展专员可查看自己的业绩
            resultData.setReturnData("maintainerStore");//只查看维护人门店
        }else{
            resultData.setReturnData("centerMaintainer");//可查看中心门店和中心维护人
        }
        return resultData;
    }

}
