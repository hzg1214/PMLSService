package cn.com.eju.deal.service.exception;

import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.exception.ExceptionDto;
import cn.com.eju.deal.mapper.exception.ExceptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("exceptionService")
public class ExceptionService {

    @Autowired
    private ExceptionMapper exceptionMapper;

    public ResultData getExceptionList(ExceptionDto dto) throws Exception {
        ResultData resultData = new ResultData();
        List<ExceptionDto> list = exceptionMapper.getExceptionList(dto);
        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData queryMaintainerByCenterId(ExceptionDto dto) {
        ResultData resultData = new ResultData();
        List<ExceptionDto> list = exceptionMapper.queryMaintainerByCenterId(dto);
        resultData.setReturnData(list);

        return resultData;
    }

    public ResultData updateMaintainer(ExceptionDto dto) {
        String[] userInfos = dto.getUserCode().split(",");
        dto.setUserCode(userInfos[0]);
        dto.setUsername(userInfos[1]);
        exceptionMapper.updateMaintainer(dto);

        return new ResultData();
    }
}
