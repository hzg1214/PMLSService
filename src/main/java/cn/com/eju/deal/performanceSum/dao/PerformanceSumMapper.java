package cn.com.eju.deal.performanceSum.dao;


import cn.com.eju.deal.dto.performanceSum.PerformanceSumColumnDto;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumContentDto;
import cn.com.eju.deal.dto.performanceSum.PerformanceSumDto;

import java.util.List;
import java.util.Map;

/**
 * Created by tanlang on 2017-05-11.
 */
public interface PerformanceSumMapper {

    List<PerformanceSumContentDto> queryList(Map<?, ?> param);

    List<PerformanceSumColumnDto> queryColumn(Map<?, ?> param);

    List<PerformanceSumDto> queryCenterList(Map<?, ?> param);

    List<PerformanceSumDto> queryCityList(Map<?, ?> param);
}
