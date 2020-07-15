package cn.com.eju.pmls.skStatement.dao;

import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateMatDto;
import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateMat;

import java.util.List;
import java.util.Map;

public interface PmlsSkAllocateMatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsSkAllocateMat record);

    int insertSelective(PmlsSkAllocateMat record);

    PmlsSkAllocateMat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsSkAllocateMat record);

    int updateByPrimaryKey(PmlsSkAllocateMat record);

    List<PmlsSkAllocateMatDto> getSkAllocateListBySkSerialNo(Map<String, Object> queryParam);

    int deleteById(Integer id);

    String getMaxSerialNo(Map<String, Object> queryParam);
}