package cn.com.eju.deal.Report.service;

import cn.com.eju.deal.Report.dao.CommonReportMapper;
import cn.com.eju.deal.Report.dto.UsrOrgHisDto;
import cn.com.eju.deal.core.support.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by eju on 2018/1/18.
 */
@Service("commonReportService")
public class CommonReportServiceImpl implements ICommonReportService {

    @Resource
    private CommonReportMapper commonReportMapper;


    @Override
    public ResultData<List<UsrOrgHisDto>> getRegion(Map<String, Object> map) throws Exception {

        ResultData resultData = new ResultData();

        List<UsrOrgHisDto> list = commonReportMapper.getRegion(map);

        resultData.setReturnData(list);

        return resultData;
    }

    @Override
    public ResultData<List<UsrOrgHisDto>> getAreaCity(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<UsrOrgHisDto> list = commonReportMapper.getAreaCity(map);

        resultData.setReturnData(list);

        return resultData;
    }

    @Override
    public ResultData<List<UsrOrgHisDto>> getCityGroup(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<UsrOrgHisDto> list = commonReportMapper.getCityGroup(map);

        resultData.setReturnData(list);

        return resultData;
    }

    @Override
    public ResultData<List<UsrOrgHisDto>> getCenterGroup(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<UsrOrgHisDto> list = commonReportMapper.getCenterGroup(map);

        resultData.setReturnData(list);

        return resultData;
    }

    @Override
    public ResultData<List<UsrOrgHisDto>> getDeptGroup(Map<String, Object> map) throws Exception {
        ResultData resultData = new ResultData();

        List<UsrOrgHisDto> list = commonReportMapper.getDeptGroup(map);

        resultData.setReturnData(list);

        return resultData;
    }
}
