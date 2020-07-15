package cn.com.eju.pmls.jsStatement.dao;

import cn.com.eju.deal.dto.common.FileRecordMainDto;
import cn.com.eju.pmls.jsStatement.dto.*;
import cn.com.eju.pmls.jsStatement.model.PmlsJsStatementDtl;

import java.util.List;
import java.util.Map;

public interface PmlsJsStatementDtlMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PmlsJsStatementDtl record);

    int insertSelective(PmlsJsStatementDtl record);

    PmlsJsStatementDtl selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PmlsJsStatementDtl record);

    int updateByPrimaryKey(PmlsJsStatementDtl record);

    /**
     * 选择请款单
     */
    List<PmlsJsStatementDtlDto> selectJsStatementDtlListCanQk(Map<String, Object> queryParam);

    List<PmlsJsCompanyDto> queryJsCompanyList(Map<String, Object> queryParam);

    List<PmlsJsProjectDto> queryJsProjectList(Map<String, Object> queryParam);

    List<PmlsJsHSCodeDto> queryJsHSCodeList(Map<String, Object> queryParam);

    List<PmlsJsKHCodeDto> queryJsKHCodeList(Map<String, Object> queryParam);

    List<PmlsJsFrameOaDto> queryJsFrameOaList(Map<String, Object> queryParam);

    List<PmlsJsReportDto> queryJsNormalReportList(Map<String, Object> queryParam);

    List<PmlsJsReportDto> queryJsOffsetReportList(Map<String, Object> queryParam);

    zkVaildAmount getZkInfo(Map<String, Object> queryParam);

    List<PmlsJsStatementDtl> getJsStatementDtlById(Map<String, Object> queryParam);

    int deleteByJssId(Integer id);

    void mergeInsert(PmlsJsStatementDtl record);

    List<FileRecordMainDto> getJsStatementFile(String id);

}