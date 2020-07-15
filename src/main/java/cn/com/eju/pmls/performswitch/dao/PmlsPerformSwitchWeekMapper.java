package cn.com.eju.pmls.performswitch.dao;

import cn.com.eju.pmls.performswitch.dto.PmlsPerformSwitchWeekDto;
import cn.com.eju.pmls.performswitch.model.PmlsPerformSwitchWeek;

import java.util.List;
import java.util.Map;

public interface PmlsPerformSwitchWeekMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsPerformSwitchWeek record);

    int insertSelective(PmlsPerformSwitchWeek record);

    PmlsPerformSwitchWeek selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsPerformSwitchWeek record);

    int updateByPrimaryKey(PmlsPerformSwitchWeek record);

    /**
     * 列表查询开关账记录
     * @param param
     * @return
     */
    List<PmlsPerformSwitchWeekDto> queryLisDto(Map<?, ?> param);

    PmlsPerformSwitchWeekDto getPmlsPerformSwitchWeekSelective(Map<String, Object> param);
}