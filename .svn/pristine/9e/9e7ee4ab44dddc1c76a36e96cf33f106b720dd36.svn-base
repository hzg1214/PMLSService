package cn.com.eju.deal.keFuWj.dao;

import cn.com.eju.deal.dto.statisfactionSurvey.SurveyFlDto;
import cn.com.eju.deal.keFuWj.model.KefuWjDTm;

import java.util.List;
import java.util.Map;

public interface KefuWjDTmMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(KefuWjDTm record);

    int insertSelective(KefuWjDTm record);

    KefuWjDTm selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(KefuWjDTm record);

    int updateByPrimaryKey(KefuWjDTm record);

    List<Map<String,Object>> getWjTmByWjcode(String wjCode);

    List<SurveyFlDto> getFlDtoList(Integer hid);

    List<Map<String,Object>> queryWjTMListByCode(String wjCode);
}