package cn.com.eju.pmls.commission.dao;

import cn.com.eju.pmls.commission.dto.CommissionImportDto;

import java.util.List;
import java.util.Map;

/**
* Mapper
* @author zhaozilong
* @date 2016年8月17日 上午10:14
*/
public interface CommissionMapper {

    List<CommissionImportDto> queryYjList(Map<?, ?> param);

    List<CommissionImportDto> queryKfList(Map<?, ?> param);

    List<CommissionImportDto> queryCityList(Map<?, ?> param);

    List<CommissionImportDto> queryBusinessTypeList(Map<?, ?> param);

    List<CommissionImportDto> checkData(CommissionImportDto dto);

    CommissionImportDto checkAccount(CommissionImportDto dto);

    CommissionImportDto getLatestAccountMonth(CommissionImportDto dto);

    int deleteYjData(CommissionImportDto dto);

    int deleteKfData(CommissionImportDto dto);

}