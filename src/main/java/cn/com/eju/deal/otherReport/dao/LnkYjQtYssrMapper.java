package cn.com.eju.deal.otherReport.dao;

import cn.com.eju.deal.otherReport.dto.LnkYjWyDto;
import cn.com.eju.deal.otherReport.model.LnkYjQtYssr;

import java.util.List;
import java.util.Map;

public interface LnkYjQtYssrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjQtYssr record);

    int insertSelective(LnkYjQtYssr record);

    LnkYjQtYssr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjQtYssr record);

    int updateByPrimaryKey(LnkYjQtYssr record);

    List<LnkYjWyDto> getList(Map<String, Object> map);

    void mergeInsertWy(LnkYjWyDto lDto);
}