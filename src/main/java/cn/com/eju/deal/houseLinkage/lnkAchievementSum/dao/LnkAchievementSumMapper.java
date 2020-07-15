package cn.com.eju.deal.houseLinkage.lnkAchievementSum.dao;

import cn.com.eju.deal.houseLinkage.lnkAchievementSum.model.LnkAchievementSumDto;

import java.util.List;
import java.util.Map;

public interface LnkAchievementSumMapper {
    
    /**
     * 查询联动业绩汇总
     * @param reqMap
     */
    List<LnkAchievementSumDto> queryLnkAchievementSumList(Map<String,Object> reqMap) throws Exception;
}