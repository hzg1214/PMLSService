package cn.com.eju.deal.common.dao;


import cn.com.eju.deal.common.model.CompanyFullName;
import cn.com.eju.deal.dto.common.CenterDto;
import cn.com.eju.deal.common.model.City;
import cn.com.eju.deal.dto.common.OrgDto;
import cn.com.eju.deal.dto.scene.commission.CommissionResultDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommonMapper {

    List<OrgDto> queryOrgList(Map<?, ?> param) throws Exception;

    List<City> queryCityList(Map<?, ?> param) throws Exception;

    List<CenterDto> queryCenterList(Map<?, ?> param) throws Exception;

    List<CompanyFullName> queryFullNameList(Map<?, ?> param) throws Exception;

    int checkSwitchMonth(@Param("reportId") String reportId, @Param("recordDate")String recordDate);

    Map<?,?> checkCitySwitchMonth(String cityNo);
}
