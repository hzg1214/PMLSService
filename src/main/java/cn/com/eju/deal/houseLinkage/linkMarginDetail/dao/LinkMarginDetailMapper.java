package cn.com.eju.deal.houseLinkage.linkMarginDetail.dao;

import cn.com.eju.deal.dto.houseLinkage.linkMarginDetail.LinkMarginDetailDto;

import java.util.List;
import java.util.Map;

/**
 * desc:联动资金成本(保证金、诚意金)
 * @author :zhenggang.Huang
 * @date   :2019年9月12日
 */
public interface LinkMarginDetailMapper {

    List<LinkMarginDetailDto> selLinkMarginDetailList(Map<String, Object> param);
    
    List<Map<String,Object>> queryCostCenterList(String organization);
}
