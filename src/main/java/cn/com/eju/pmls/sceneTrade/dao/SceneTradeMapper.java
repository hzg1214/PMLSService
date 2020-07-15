package cn.com.eju.pmls.sceneTrade.dao;

import cn.com.eju.pmls.sceneTrade.dto.CustomVaildDet;
import cn.com.eju.pmls.sceneTrade.dto.CustomVaildLeg;

import java.util.List;
import java.util.Map;

public interface SceneTradeMapper {

    List<CustomVaildLeg> getCustomLegList(Map<String, Object> queryParam);

    List<CustomVaildDet> getCustomDetList(Map<String, Object> queryParam);


}
