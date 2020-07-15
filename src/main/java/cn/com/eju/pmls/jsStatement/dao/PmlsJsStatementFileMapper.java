package cn.com.eju.pmls.jsStatement.dao;

import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementFile;

public interface PmlsJsStatementFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsJsStatementFile record);

    int insertSelective(PmlsJsStatementFile record);

    PmlsJsStatementFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsJsStatementFile record);

    int updateByPrimaryKey(PmlsJsStatementFile record);

    int deleteByJssId(Integer id);
}