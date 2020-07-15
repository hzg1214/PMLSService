package cn.com.eju.deal.performanceSum.service;


import cn.com.eju.deal.base.service.BaseService;
import cn.com.eju.deal.core.support.ResultData;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumColumnDto;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumContentDto;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumDto;
import cn.com.eju.deal.performanceSum.dao.PerformanceSumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Service层
 *
 * @author tanlang
 */
@Service("performanceSumService")
public class PerformanceSumService extends BaseService<PerformanceSumDto> {

    @Autowired
    private PerformanceSumMapper performanceSumMapper;

    /**
     * 查询-list
     *
     * @param reqMap
     * @return
     * @throws Exception
     */
    public ResultData<PerformanceSumDto> queryList(Map<?, ?> param) throws Exception {

        //构建返回
        ResultData<PerformanceSumDto> resultData = new ResultData<>();
        PerformanceSumDto sumDto = new PerformanceSumDto();
        //查询列名
        List<PerformanceSumColumnDto> columns = performanceSumMapper.queryColumn(param);
        sumDto.setColumnDtos(columns);

        //查询内容
        List<PerformanceSumContentDto> contents = performanceSumMapper.queryList(param);
        sumDto.setContentDtos(contents);

        resultData.setReturnData(sumDto);

        return resultData;

    }

    public ResultData<List<PerformanceSumDto>> queryCityList(Map<?, ?> param) throws Exception {

        //构建返回
        ResultData<List<PerformanceSumDto>> resultData = new ResultData<>();
        //查询
        List<PerformanceSumDto> moList = performanceSumMapper.queryCityList(param);

        resultData.setReturnData(moList);

        return resultData;

    }

    public ResultData<List<PerformanceSumDto>> queryCenterList(Map<?, ?> param) throws Exception {

        //构建返回
        ResultData<List<PerformanceSumDto>> resultData = new ResultData<>();

        //查询
        List<PerformanceSumDto> moList = performanceSumMapper.queryCenterList(param);

        resultData.setReturnData(moList);

        return resultData;

    }

}
