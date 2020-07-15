package cn.com.eju.pmls.skStatement.dao;

import cn.com.eju.deal.core.dao.IDao;
import cn.com.eju.pmls.skStatement.dto.PmlsSkAllocateDtlDto;
import cn.com.eju.pmls.skStatement.model.PmlsSkAllocateDtl;

import java.util.List;
import java.util.Map;

public interface PmlsSkAllocateDtlMapper  extends IDao{
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsSkAllocateDtl record);

    int insertSelective(PmlsSkAllocateDtl record);

    PmlsSkAllocateDtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsSkAllocateDtl record);

    int updateByPrimaryKey(PmlsSkAllocateDtl record);

    List<PmlsSkAllocateDtlDto> getAllocateListForDeal(Map<String, Object> queryParam);

    List<PmlsSkAllocateDtlDto> getAllocateListForBuilding(Map<String, Object> queryParam);

    int deleteByParentId(Integer parentId);

    int deleteById(Integer id);

    List<PmlsSkAllocateDtl> getByParentId(Integer parentId);

    List<PmlsSkAllocateDtlDto> querySkAllocateDtlList(Map<String, Object> queryParam);

}