package cn.com.eju.pmls.skStatement.dao;

import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateLogDto;
import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateLog;

import java.util.List;
import java.util.Map;

public interface PmlsSkAllocateLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsSkAllocateLog record);

    int insertSelective(PmlsSkAllocateLog record);

    PmlsSkAllocateLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsSkAllocateLog record);

    int updateByPrimaryKey(PmlsSkAllocateLog record);

    List<PmlsSkAllocateLogDto> getSkAllocateLogList(Map<String, Object> queryParam);
}