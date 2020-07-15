package cn.com.eju.deal.storeinformation.dao;

import java.util.List;
import java.util.Map;

import cn.com.eju.deal.dto.storeinformation.StoreInformationDetailDto;

/**
 * 
 * desc: 门店信息明细  
 * @author :zhenggang.Huang
 * @date   :2019年1月2日
 */
public interface StoreInformationDetailMapper {

    List<StoreInformationDetailDto> queryInformationDetailList(Map<?, ?> param);

//    List<LinkProjectDetailDto> queryLinkProjectDetailList(Map<?, ?> param);
//
//    List<LinkDetailDto> queryEstateList(String cityNo);
//
//    List<LinkProjectTraceDto> queryLinkProjectTraceList(Map<?, ?> param);
//
//    List<CityDto> queryCityList(Map<String, Object> queryParam);
}
