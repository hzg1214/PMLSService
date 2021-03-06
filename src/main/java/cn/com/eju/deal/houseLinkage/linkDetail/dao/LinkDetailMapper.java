package cn.com.eju.deal.houseLinkage.linkDetail.dao;

import cn.com.eju.deal.dto.common.CityDto;
import cn.com.eju.deal.dto.houseLinkage.linkDetail.LinkDetailDto;
import cn.com.eju.deal.dto.houseLinkage.linkProjectDetail.LinkProjectDetailDto;
import cn.com.eju.deal.dto.houseLinkage.linkProjectTrace.LinkProjectTraceDto;
import cn.com.eju.pmls.report.linkDetail.dto.LinkProjectPartATraceDto;

import java.util.List;
import java.util.Map;

/**
 * Created by tanlang on 2017-05-11.
 */
public interface LinkDetailMapper {

    List<LinkDetailDto> queryLinkDetailList(Map<?, ?> param);

    List<LinkProjectDetailDto> queryLinkProjectDetailList(Map<?, ?> param);
    //按项目
    List<LinkProjectDetailDto> queryLinkProjectDetailListByProject(Map<?, ?> param);

    List<LinkDetailDto> queryEstateList(String cityNo);

    List<LinkProjectTraceDto> queryLinkProjectTraceList(Map<?, ?> param);

    List<CityDto> queryCityList(Map<String, Object> queryParam);

    List<LinkProjectPartATraceDto> queryLinkProjectPartATraceList(Map<?, ?> param);


    void export(Map<String, Object> queryParam);
}
