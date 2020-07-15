package cn.com.eju.deal.otherReport.dao;

import cn.com.eju.deal.otherReport.dto.LnkYjWyDto;
import cn.com.eju.deal.otherReport.model.LnkYjQtYjsr;

import java.util.List;
import java.util.Map;

public interface LnkYjQtYjsrMapper {

    LnkYjQtYjsr selectByPrimaryKey(Integer id);

    int mergeInsert(LnkYjQtYjsr record);

    List<LnkYjWyDto> getList(Map<String, Object> map);

    void mergeInsertWy(LnkYjWyDto lDto);
}