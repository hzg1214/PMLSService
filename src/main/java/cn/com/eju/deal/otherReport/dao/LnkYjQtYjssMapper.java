package cn.com.eju.deal.otherReport.dao;

import cn.com.eju.deal.otherReport.dto.LnkYjWyDto;
import cn.com.eju.deal.otherReport.model.LnkYjQtYjss;

import java.util.List;
import java.util.Map;

public interface LnkYjQtYjssMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LnkYjQtYjss record);

    int insertSelective(LnkYjQtYjss record);

    LnkYjQtYjss selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LnkYjQtYjss record);

    int updateByPrimaryKey(LnkYjQtYjss record);

    List<LnkYjWyDto> getList(Map<String, Object> map);

    void mergeInsertWy(LnkYjWyDto lDto);
}