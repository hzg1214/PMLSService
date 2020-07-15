package cn.com.eju.pmls.skStatement.dao;

import cn.com.eju.pmls.skStatement.dto.PmlsSkStatementDto;
import cn.com.eju.pmls.skStatement.model.PmlsSkStatement;

import java.util.List;
import java.util.Map;

public interface PmlsSkStatementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsSkStatement record);

    int insertSelective(PmlsSkStatement record);

    PmlsSkStatement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsSkStatement record);

    int updateByPrimaryKey(PmlsSkStatement record);

    List<PmlsSkStatementDto> queryList(Map<String, Object> queryParam);

    PmlsSkStatementDto getSkStatementById(Map<String, Object> queryParam);

    List<String> checkSkAllocate(Map<String, Object> queryParam);

}