package cn.com.eju.deal.houseLinkage.linkAchieveChange.dao;

import cn.com.eju.deal.dto.houseLinkage.linkAchieveChange.LinkAchieveChangeDto;

import java.util.List;
import java.util.Map;

/**
 * 联动业绩调整
 * Created by hzy on 2017/10/23.
 */
public interface LinkAchieveChangeMapper {

    List<LinkAchieveChangeDto> getLinkAchieveChangeList(Map<String,Object> reqMap);
}
