package cn.com.eju.pmls.skStatement.dao;

import cn.com.eju.pmls.skStatement.model.PmlsSkStatementDtl;

public interface PmlsSkStatementDtlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsSkStatementDtl record);

    int insertSelective(PmlsSkStatementDtl record);

    PmlsSkStatementDtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsSkStatementDtl record);

    int updateByPrimaryKey(PmlsSkStatementDtl record);

    PmlsSkStatementDtl getBySkSerialNo(String skSerialNo);
}