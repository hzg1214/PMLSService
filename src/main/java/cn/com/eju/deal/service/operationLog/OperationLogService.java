package cn.com.eju.deal.service.operationLog;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.mapper.myCollection.MyCollectionMapper;
import cn.com.eju.deal.mapper.operationLog.OperationLogMapper;
import cn.com.eju.deal.model.operationLog.WXOperationLogDto;
import cn.com.eju.deal.model.sweepStreets.StoreNewDto;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by xu on 2017/6/27.
 */
@Service("operationLogService")
public class OperationLogService {
    @Resource
    private OperationLogMapper operationLogMapper;
    public ResultData addOperationLogs(WXOperationLogDto dto) throws Exception {
        ResultData resultData = new ResultData();
        int result=operationLogMapper.addOperationLogs(dto);
        if(result<=0){
            resultData.setFail("操作失败");
            return resultData;
        }
        resultData.setSuccess();
        return resultData;
    }
}
